package fpinscala.laziness

import Stream._
import scala.annotation.tailrec
trait Stream[+A] {
  def foldRight[B](z: => B)(f: (A, => B) => B): B = // The arrow `=>` in front of the argument type `B` means that the function `f` takes its second argument by name and may choose not to evaluate it.
    this match {
      case Cons(h,t) => f(h(), t().foldRight(z)(f)) // If `f` doesn't evaluate its second argument, the recursion never occurs.
      case _ => z
    }

  def exists(p: A => Boolean): Boolean = 
    foldRight(false)((a, b) => p(a) || b) // Here `b` is the unevaluated recursive step that folds the tail of the stream. If `p(a)` returns `true`, `b` will never be evaluated and the computation terminates early.

  @annotation.tailrec
  final def find(f: A => Boolean): Option[A] = this match {
    case Empty => None
    case Cons(h, t) => if (f(h())) Some(h()) else t().find(f)
  }
  
  def take(n: Int): Stream[A] = this match {
    case Empty => empty
    case Cons(h,t) => if (n <= 0) empty else cons(h(), t().take(n-1))
  }

  def drop(n: Int): Stream[A] = this match { 
    case Empty => empty
    case Cons(h,t) => if (n <= 0) this else t().drop(n-1)
  }

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h,t) if p(h()) => cons(h(), t() takeWhile p)
    case _ => empty
  }
  
  // Note how we need to type annotation on the empty zero value, otherwise the h in cons(h, t) does not type check - why?
  def takeWhileUsingFoldRight(p: A => Boolean): Stream[A] = 
    foldRight(empty[A])((h, t) => if (p(h)) cons(h, t) else empty)

  /* 
  Since `&&` is non-strict in its second argument, this terminates the traversal as soon as a nonmatching element is found.
  Remember how this expands out:
    With a Stream(1, 2, 3) and predicate of (_ < 4)
    true && (true && (true && zero_value))
  */
  def forAll(p: A => Boolean): Boolean = 
    foldRight(true)((a, b) => p(a) && b)

  def headOption: Option[A] = this match {
    case Cons(h,t) => Some(h())
    case _ => None
  } 
  
  def headOptionUsingFoldRight: Option[A] = 
    foldRight(None: Option[A])((h,_) => Some(h))
  
  def startsWith[B](s: Stream[B]): Boolean = sys.error("todo")
  
  // The natural recursive solution
  def toListRecursive: List[A] = this match {
    case Cons(h,t) => h() :: t().toListRecursive
    case _ => List()
  }
  
  /*
  The above solution will stack overflow for large streams, since it's
  not tail-recursive. Here is a tail-recursive implementation. At each
  step we cons onto the front of the `acc` list, which will result in the
  reverse of the stream. Then at the end we reverse the result to get the
  correct order again.
  */
  def toList: List[A] = {
    @annotation.tailrec
    def go(s: Stream[A], acc: List[A]): List[A] = s match {
      case Cons(h, t) => go(t(), h() :: acc)
      case _ => acc
    }
    go(this, List()).reverse
  }
  
  /* 
  In order to avoid the `reverse` at the end, we could write it using a
  mutable list buffer and an explicit loop instead. Note that the mutable
  list buffer never escapes our `toList` method, so this function is
  still _pure_.
  */
  def toListFast: List[A] = {
    val buf = new collection.mutable.ListBuffer[A] 
    @annotation.tailrec
    def go(s: Stream[A]): List[A] = s match {
      case Cons(h,t) =>
        buf += h()
        go(t())
      case _ => buf.toList
    }
    go(this)
  }  
  
  def map[B](f: A => B): Stream[B] =
    foldRight(empty[B])((h,t) => cons(f(h), t))
    
  def filter(f: A => Boolean): Stream[A] =
    foldRight(empty[A])((h,t) => if (f(h)) cons(h, t) else t)
    
  def append[B>:A](s: => Stream[B]): Stream[B] = 
    foldRight(s)((h,t) => cons(h, t))
    
  def flatMap[B](f: A => Stream[B]): Stream[B] = 
    foldRight(empty[B])((h,t) => f(h) append t)
}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty 
    else cons(as.head, apply(as.tail: _*))

  val ones: Stream[Int] = Stream.cons(1, ones)
  
  def constant[A](a: A): Stream[A] = Stream.cons(a, constant(a))
  
  def from(n: Int): Stream[Int] = Stream.cons(n, from(n+1))

  def fibs: Stream[Int] = {
    def loop(prev: Int, curr: Int): Stream[Int] =
      cons(prev, loop(curr, prev + curr))
      
    loop(0, 1)
  }
    
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = ???
}