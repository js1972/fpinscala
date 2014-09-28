package fpinscala.datastructures

import List._

object TestingTheList {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(207); val res$0 = 
  
  // What happens when we call foldRight using Nil and Cons themselves...
  
  foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _));System.out.println("""res0: fpinscala.datastructures.List[Int] = """ + $show(res$0));$skip(415); val res$1 = 
  
  // This just gives us back the original list..!
  // One way of thinking about what `foldRight` "does" is it replaces the `Nil` constructor
  // of the list with the `z` argument, and it replaces the `Cons` constructor with the
  // given function, `f`. If we just supply `Nil` for `z` and `Cons` for `f`, then we get
  // back the input list.
  
  // Testing the length function
  length(List(1, 2, 3, 4, 5));System.out.println("""res1: Int = """ + $show(res$1));$skip(14); val res$2 = 
  length(Nil);System.out.println("""res2: Int = """ + $show(res$2));$skip(18); val res$3 = 
  length(List(1));System.out.println("""res3: Int = """ + $show(res$3));$skip(27); val res$4 = 
 
  sum3(List(1, 2, 3, 4));System.out.println("""res4: Int = """ + $show(res$4));$skip(29); val res$5 = 
  product3(List(1, 2, 3, 4));System.out.println("""res5: Double = """ + $show(res$5));$skip(28); val res$6 = 
  length2(List(1, 2, 3, 4));System.out.println("""res6: Int = """ + $show(res$6));$skip(31); val res$7 = 
  
  reverse(List(1, 2, 3, 4));System.out.println("""res7: fpinscala.datastructures.List[Int] = """ + $show(res$7))}
}
