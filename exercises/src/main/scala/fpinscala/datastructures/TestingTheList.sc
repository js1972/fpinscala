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
}