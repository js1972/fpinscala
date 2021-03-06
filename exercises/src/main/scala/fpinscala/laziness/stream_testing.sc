package fpinscala.laziness

import Stream._

object stream_testing {
  Stream(1, 2, 3, 4, 5).toList                    //> res0: List[Int] = List(1, 2, 3, 4, 5)
  
  (Stream(1, 2, 3, 4, 5) drop 2).toList           //> res1: List[Int] = List(3, 4, 5)
  (Stream(1, 2, 3, 4, 5) take 2).toList           //> res2: List[Int] = List(1, 2)
  (Stream(1, 2, 3, 4, 5) takeWhile(_ < 4)).toList //> res3: List[Int] = List(1, 2, 3)
  Stream(1, 2, 3, 4, 5) forAll { _ < 3 }          //> res4: Boolean = false
  (Stream(1, 2, 3, 4, 5) takeWhile { _ < 4 }).toList
                                                  //> res5: List[Int] = List(1, 2, 3)
  
  Stream(1, 2, 3, 4, 5) headOption                //> res6: Option[Int] = Some(1)
  
  Stream(1, 2, 3, 4, 5) headOptionUsingFoldRight  //> res7: Option[Int] = Some(1)
  
  
  // some infinite stream processing
  
  ones.take(5).toList                             //> res8: List[Int] = List(1, 1, 1, 1, 1)
  ones.exists(_ % 2 != 0)                         //> res9: Boolean = true
  ones.map(_ + 1).exists(_ % 2 == 0)              //> res10: Boolean = true
  ones.takeWhile(_ == 1).take(5).toList           //> res11: List[Int] = List(1, 1, 1, 1, 1)
  ones.forAll(_ != 1)                             //> res12: Boolean = false
  
  constant(4).take(5).toList                      //> res13: List[Int] = List(4, 4, 4, 4, 4)
  from(1).take(5).toList                          //> res14: List[Int] = List(1, 2, 3, 4, 5)
  fibs.take(10).toList                            //> res15: List[Int] = List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
  
  onesUsingUnfold.take(5).toList                  //> res16: List[Int] = List(1, 1, 1, 1, 1)
  constantUsingUnfold(4).take(5).toList           //> res17: List[Int] = List(4, 4, 4, 4, 4)
  fromUsingUnfold(12).take(5).toList              //> res18: List[Int] = List(12, 13, 14, 15, 16)
  
  Stream(1, 2, 3, 4, 5).takeViaUnfold(3).toList   //> res19: List[Int] = List(1, 2, 3)
  
  Stream(1, 2, 3, 4, 5).startsWith(Stream(1, 2, 3))
                                                  //> res20: Boolean = true
  Stream(1, 2, 3, 4, 5).tails.take(5).toList      //> res21: List[fpinscala.laziness.Stream[Int]] = List(Cons(<function0>,<functio
                                                  //| n0>), Cons(<function0>,<function0>), Cons(<function0>,<function0>), Cons(<fu
                                                  //| nction0>,<function0>), Cons(<function0>,<function0>))
  Stream(1, 2, 3, 4, 5) hasSubsequence(Stream(1, 2, 3))
                                                  //> res22: Boolean = true
  
  (Stream(1, 2, 3, 4, 5).scanRight(0){_ + _}).toList
                                                  //> res23: List[Int] = List(15, 14, 12, 9, 5, 0)
}