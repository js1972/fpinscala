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
  
  reverse(List(1, 2, 3, 4));System.out.println("""res7: fpinscala.datastructures.List[Int] = """ + $show(res$7));$skip(57); val res$8 = 
  
  foldRightViaFoldLeft(List(1, 2, 3, 4, 5), 0)(_ + _);System.out.println("""res8: Int = """ + $show(res$8));$skip(53); val res$9 = 
  
  appendViaFoldLeft(List(1, 2, 3), List(4, 5, 6));System.out.println("""res9: fpinscala.datastructures.List[Int] = """ + $show(res$9));$skip(51); val res$10 = 
  appendViaFoldRight(List(1, 2, 3), List(4, 5, 6));System.out.println("""res10: fpinscala.datastructures.List[Int] = """ + $show(res$10));$skip(54); val res$11 = 
  
  concat(List(List(1, 2), List(3, 4), List(5, 6)));System.out.println("""res11: fpinscala.datastructures.List[Int] = """ + $show(res$11));$skip(40); val res$12 = 
 
  addOneToListElements(List(1, 2, 3));System.out.println("""res12: fpinscala.datastructures.List[Int] = """ + $show(res$12));$skip(42); val res$13 = 
  listDoubleToString(List(1.0, 2.3, 3.0));System.out.println("""res13: fpinscala.datastructures.List[String] = """ + $show(res$13));$skip(28); val res$14 = 
  map(List(1, 2, 3))(_ + 1);System.out.println("""res14: fpinscala.datastructures.List[Int] = """ + $show(res$14));$skip(29); val res$15 = 
  map2(List(1, 2, 3))(_ + 1);System.out.println("""res15: fpinscala.datastructures.List[Int] = """ + $show(res$15));$skip(44); val res$16 = 
  
  filter(List(1, 2, 3, 4, 5, 6))(_ >= 3);System.out.println("""res16: fpinscala.datastructures.List[Int] = """ + $show(res$16));$skip(42); val res$17 = 
  filter2(List(1, 2, 3, 4, 5, 6))(_ >= 3);System.out.println("""res17: fpinscala.datastructures.List[Int] = """ + $show(res$17))}
}
