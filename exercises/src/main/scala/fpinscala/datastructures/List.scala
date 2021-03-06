package fpinscala.datastructures

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A] // Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`, which may be `Nil` or another `Cons`.

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  } 
  
  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }
  
  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42 
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101 
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def appendViaFoldRight[A](a1: List[A], a2: List[A]): List[A] =
    foldRight(a1, a2)(Cons(_, _))
    
  def appendViaFoldLeft[A](a1: List[A], a2: List[A]): List[A] =
    foldLeft(reverse(a1), a2)((a, b) => Cons(b, a))
    
  //
  // Note that when using foldRight you can --NEVER -- early terminate (or short circuit) 
  // because the arguments to the function must be evaluated first, which in the case of
  // foldRight means traversing the entire List all the way to the end...
  // foldRight is NOT stack-safe!
  //
  
  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  
  def sum2(ns: List[Int]) = 
    foldRight(ns, 0)((x,y) => x + y)
  
  def product2(ns: List[Double]) = 
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar
  
  def tail[A](l: List[A]): List[A] = l match {
    case Nil        => sys.error("tail of empty list")
    case Cons(_, t) => t
  }
  
  def head[A](l: List[A]): A = l match {
    case Nil => sys.error("head of empty list")
    case Cons(h, _) => h
  }
  
  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil        => sys.error("set head of empty list")
    case Cons(_, t) => Cons(h, t)
  }

  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Nil        => Nil
    case Cons(_, t) => if (n <= 0) l else drop(t, n-1) 
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

  def length[A](l: List[A]): Int = foldRight(l, 0)((_, acc) => acc + 1)

  // foldLeft is stack-safe!
  @annotation.tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f)
  }

  def sum3(ns: List[Int]) = 
    foldLeft(ns, 0)((x,y) => x + y)
  
  def product3(ns: List[Double]) = 
    foldLeft(ns, 1.0)(_ * _)
  
  def length2[A](l: List[A]): Int = foldLeft(l, 0)((acc, _) => acc + 1)
  
  def reverse[A](l: List[A]): List[A] = 
    foldLeft(l, Nil: List[A])((acc, h) => Cons(h, acc))
  
  // Lets try and implement foldRight in terms of foldLeft so that its 
  // tail-recursive..
  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A, B) => B): B =
    foldLeft(reverse(l), z)((b, a) => f(a, b))
    
  def concat[A](ll: List[List[A]]): List[A] =
    foldRight(ll, Nil: List[A])(append)
    
  def addOneToListElements(l: List[Int]): List[Int] =
    foldRight(l, Nil: List[Int])((h, t) => Cons(h + 1, t))
    
  def listDoubleToString(l: List[Double]): List[String] =
    foldRight(l, Nil: List[String])((h, t) => Cons(h.toString, t))
  
  def map[A,B](l: List[A])(f: A => B): List[B] = 
    foldRight(l, Nil: List[B])((h, t) => Cons(f(h), t))
  
  // Stack-safe version...
  def map2[A,B](l: List[A])(f: A => B): List[B] = 
    foldRightViaFoldLeft(l, Nil: List[B])((h, t) => Cons(f(h), t))
    
  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    foldRight(as, Nil: List[A])((h, t) => if (f(h)) Cons(h, t) else t)
    
  // Stack-safe version again...
  def filter2[A](as: List[A])(f: A => Boolean): List[A] =
    foldRightViaFoldLeft(as, Nil: List[A])((h, t) => if (f(h)) Cons(h, t) else t)
  
  // hasSubsequence...
  def startsWith[A](l: List[A], prefix: List[A]): Boolean = (l,prefix) match {
    case (_, Nil) => true
    case (Cons(h, t), Cons(h2, t2)) if h == h2 => startsWith(t, t2)
    case _ => false
  }
  @annotation.tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
    case Nil => false
    case Cons(h,t) if startsWith(sup, sub) => true
    case Cons(h, t) => hasSubsequence(t, sub)
  }
    
}