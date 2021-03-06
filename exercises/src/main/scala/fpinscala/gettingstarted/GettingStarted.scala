package fpinscala.gettingstarted

import scala.annotation.tailrec

// A comment!
/* Another comment */
/** A documentation comment */
object MyModule {
  def abs(n: Int): Int =
    if (n < 0) -n
    else n

  private def formatAbs(x: Int) = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }

  def main(args: Array[String]): Unit =
    println(formatAbs(-42))

  // A definition of factorial, using a local, tail recursive function
  def factorial(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n*acc)

    go(n, 1)
  }

  // Another implementation of `factorial`, this time with a `while` loop
  def factorial2(n: Int): Int = {
    var acc = 1
    var i = n
    while (i > 0) { acc *= i; i -= 1 }
    acc
  }

  // Exercise 1: Write a function to compute the nth fibonacci number
  // I changed it to make it 1-based instead of zero-based as suggested
  // in the answers as technically when asking for the nth value it 
  // should start with 1.

  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, curr: Int): Int =
      if (n <= 1) prev
      else loop(n-1, curr, prev + curr)
      
    loop(n, 0, 1)
  }

  // This definition and `formatAbs` are very similar..
  private def formatFactorial(n: Int) = {
    val msg = "The absolute value of %d is %d."
    msg.format(n, factorial(n))
  }

  // We can generalize `formatAbs` and `formatFactorial` to
  // accept a _function_ as a parameter
  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }
}

object FormatAbsAndFactorial {

  import MyModule._

  // Now we can use our general `formatResult` function
  // with both `abs` and `factorial`
  def main(args: Array[String]): Unit = {
    println(formatResult("absolute value", -42, abs))
    println(formatResult("factorial", 7, factorial))
    println(formatResult("nth fibonacci number", 0, fib))
    println(formatResult("nth fibonacci number", 1, fib))
    println(formatResult("nth fibonacci number", 3, fib))
    println(formatResult("nth fibonacci number", 4, fib))
  }
}

// Functions get passed around so often in FP that it's
// convenient to have syntax for constructing a function
// *without* having to give it a name
object AnonymousFunctions {
  import MyModule._

  // Some examples of anonymous functions:
  def main(args: Array[String]): Unit = {
    println(formatResult("absolute value", -42, abs))
    println(formatResult("factorial", 7, factorial))
    println(formatResult("increment", 7, (x: Int) => x + 1))
    println(formatResult("increment2", 7, (x) => x + 1))
    println(formatResult("increment3", 7, x => x + 1))
    println(formatResult("increment4", 7, _ + 1))
    println(formatResult("increment5", 7, x => { val r = x + 1; r }))
  }
}

object MonomorphicBinarySearch {

  // First, a binary search implementation, specialized to `Double`,
  // another primitive type in Scala, representing 64-bit floating
  // point numbers
  // Ideally, we could generalize this to work for any `Array` type,
  // so long as we have some way of comparing elements of the `Array`
  def binarySearch(ds: Array[Double], key: Double): Int = {
    @annotation.tailrec
    def go(low: Int, mid: Int, high: Int): Int = {
      if (low > high) -mid - 1
      else {
        val mid2 = (low + high) / 2
        val d = ds(mid2) // We index into an array using the same
                         // syntax as function application
        if (d == key) mid2
        else if (d > key) go(low, mid2, mid2-1)
        else go(mid2 + 1, mid2, high)
      }
    }
    go(0, 0, ds.length - 1)
  }

}

object PolymorphicFunctions {

  // Here's a polymorphic version of `binarySearch`, parameterized on
  // a function for testing whether an `A` is greater than another `A`.
  def binarySearch[A](as: Array[A], key: A, gt: (A,A) => Boolean): Int = {
    @annotation.tailrec
    def go(low: Int, mid: Int, high: Int): Int = {
      if (low > high) -mid - 1
      else {
        val mid2 = (low + high) / 2
        val a = as(mid2)
        val greater = gt(a, key)
        if (!greater && !gt(key,a)) mid2
        else if (greater) go(low, mid2, mid2-1)
        else go(mid2 + 1, mid2, high)
      }
    }
    go(0, 0, as.length - 1)
  }

  // Exercise 2: Implement a polymorphic function to check whether
  // an `Array[A]` is sorted
  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean = 
      if (n == as.length) true
      else if (gt(as(n), as(n-1))) loop(n+1) 
      else false
      
    if (as.length == 0) true
    loop(1)
  }

  // Polymorphic functions are often so constrained by their type
  // that they only have one implementation! Here's an example:

  // Exercise 3: Implement `partial1`.

  def partial1[A,B,C](a: A, f: (A,B) => C): B => C =
    b => f(a, b)

  // Exercise 4: Implement `curry`.

  // Note that `=>` associates to the right, so we could
  // write the return type as `A => B => C`
  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    a => b => f(a, b)
    //f.curried
  }

  // NB: The `Function2` trait has a `curried` method already

  // Exercise 5: Implement `uncurry`
  def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
    (a, b) => f(a)(b)
  }

  /*
  NB: There is a method on the `Function` object in the standard library,
  `Function.uncurried` that you can use for uncurrying.

  Note that we can go back and forth between the two forms. We can curry
  and uncurry and the two forms are in some sense "the same". In FP jargon,
  we say that they are _isomorphic_ ("iso" = same; "morphe" = shape, form),
  a term we inherit from category theory.
  */

  // Exercise 6: Implement `compose`

  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    a => f(g(a))
  }
    
  def main(args: Array[String]): Unit = {
    val aSorted = Array(1, 2, 3, 4, 5)
    val aUnsorted = Array(1, 2, 3, 5, 4)
    val aStrings = Array("first", "second", "third")
    
    println("is sorted (with sorted Int array): " + isSorted(aSorted, (x: Int, y: Int) => x > y))
    println("is sorted (with unsorted Int array): " + isSorted(aUnsorted, (x: Int, y: Int) => x > y))
    println("is sorted (with sorted String array): " + isSorted(aStrings, (x: String, y: String) => x > y))
  }
}

object ListTest{
  def main(args: Array[String]): Unit = {
    sealed trait List[+A]
    case object Nil extends List[Nothing]
    case class Cons[+A](head: A, tail: List[A]) extends List[A]
    
    object List {
      def sum(ints: List[Int]): Int = ints match {
        case Nil => 0
        case Cons(x, xs) => x + sum(xs)
      }
      
      def product(ds: List[Double]): Double = ds match {
        case Nil => 1.0
        case Cons(0.0, _) => 0.0
        case Cons(x, xs) => x * product(xs)
      }
      
      def apply[A](as: A*): List[A] = 
        if (as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))
      
      def tail[A](l: List[A]): List[A] = l match {
          case Nil        => sys.error("tail of empty list")
          case Cons(_, t) => t
      }
      
      def setHead[A](h: A, l: List[A]): List[A] = l match {
        case Nil        => sys.error("set head of empty list")
        case Cons(_, t) => Cons(h, t)
      }
      
      def drop[A](l: List[A], n: Int): List[A] = l match {
        case Nil        => Nil
        case Cons(_, t) => if (n <= 0) l else drop(t, n-1) 
      }

      // slightly different way from the answers...
      def drop2[A](l: List[A], n: Int): List[A] = 
        if (n <= 0) l
        else l match {
          case Nil => Nil
          case Cons(_, t) => drop(t, n-1) 
        }
      
      // I'm using a curried function so that scalac can do type inference on the predicate function...
      def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
        case Cons(h, t) if f(h) => dropWhile(t)(f)
        case _                  => l
      }
      
      // this is not tail recursive so long lists will blow the stack
      def init[A](l: List[A]): List[A] = l match {
        case Nil => Nil
        case Cons(_, Nil) => Nil
        case Cons(h, t) => Cons(h, init(t))
        
      }
    }
    
    import List._
    val x = List(1, 2, 3, 4, 5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y  // this one matches first !!
      case Cons(h, t) => h + sum(t)
      case _ => 101
    }
    
    println(x)
    
    println(tail(List(1, 2, 3, 4)))
    //println(tail(List()))   <- generates exception as expected
    
    println(setHead(1, List(2, 3, 4)))
    
    println(drop(List(1, 2, 3), 1))
    println(drop(List(1, 2, 3), 2))
    println(drop(List(1, 2, 3), 3))
    println(drop(List(1, 2, 3), 4))
    println(drop(List(1, 2, 3), -3)) // <- should we raise an exception here instead of allowing it...
    
    println(dropWhile(List(1, 2, 3))(_ < 2))
    println(dropWhile(List(1, 2, 3))(_ < 4))
    println(dropWhile(List(1, 2, 3))(_ < 10))
    
    println()
    println("init function on List(1, 2, 3, 4): " + init(List(1, 2, 3, 4)))
    println("init function on List(1, 2): " + init(List(1, 2)))
    println("init function on List(1): " + init(List(1)))
  }
}