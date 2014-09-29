package fpinscala.datastructures

import List._

object TestingTheList {
  
  // What happens when we call foldRight using Nil and Cons themselves...
  
  foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
                                                  //> res0: fpinscala.datastructures.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
  
  // This just gives us back the original list..!
  // One way of thinking about what `foldRight` "does" is it replaces the `Nil` constructor
  // of the list with the `z` argument, and it replaces the `Cons` constructor with the
  // given function, `f`. If we just supply `Nil` for `z` and `Cons` for `f`, then we get
  // back the input list.
  
  // Testing the length function
  length(List(1, 2, 3, 4, 5))                     //> res1: Int = 5
  length(Nil)                                     //> res2: Int = 0
  length(List(1))                                 //> res3: Int = 1
 
  sum3(List(1, 2, 3, 4))                          //> res4: Int = 10
  product3(List(1, 2, 3, 4))                      //> res5: Double = 24.0
  length2(List(1, 2, 3, 4))                       //> res6: Int = 4
  
  reverse(List(1, 2, 3, 4))                       //> res7: fpinscala.datastructures.List[Int] = Cons(4,Cons(3,Cons(2,Cons(1,Nil))
                                                  //| ))
  
  foldRightViaFoldLeft(List(1, 2, 3, 4, 5), 0)(_ + _)
                                                  //> res8: Int = 15
  
  appendViaFoldLeft(List(1, 2, 3), List(4, 5, 6)) //> res9: fpinscala.datastructures.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(
                                                  //| 5,Cons(6,Nil))))))
  appendViaFoldRight(List(1, 2, 3), List(4, 5, 6))//> res10: fpinscala.datastructures.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons
                                                  //| (5,Cons(6,Nil))))))
  
  concat(List(List(1, 2), List(3, 4), List(5, 6)))//> res11: fpinscala.datastructures.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons
                                                  //| (5,Cons(6,Nil))))))
 
  addOneToListElements(List(1, 2, 3))             //> res12: fpinscala.datastructures.List[Int] = Cons(2,Cons(3,Cons(4,Nil)))
  listDoubleToString(List(1.0, 2.3, 3.0))         //> res13: fpinscala.datastructures.List[String] = Cons(1.0,Cons(2.3,Cons(3.0,N
                                                  //| il)))
  map(List(1, 2, 3))(_ + 1)                       //> res14: fpinscala.datastructures.List[Int] = Cons(2,Cons(3,Cons(4,Nil)))
  map2(List(1, 2, 3))(_ + 1)                      //> res15: fpinscala.datastructures.List[Int] = Cons(2,Cons(3,Cons(4,Nil)))
  
  filter(List(1, 2, 3, 4, 5, 6))(_ >= 3)          //> res16: fpinscala.datastructures.List[Int] = Cons(3,Cons(4,Cons(5,Cons(6,Nil
                                                  //| ))))
  filter2(List(1, 2, 3, 4, 5, 6))(_ >= 3)         //> res17: fpinscala.datastructures.List[Int] = Cons(3,Cons(4,Cons(5,Cons(6,Nil
                                                  //| ))))
}