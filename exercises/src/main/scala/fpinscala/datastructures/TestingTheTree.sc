package fpinscala.datastructures

import Tree._

object TestingTheTree {
  size(Branch(Leaf(1), Leaf(2)))                  //> res0: Int = 3
  maximum(Branch(Leaf(1), Leaf(2)))               //> res1: Int = 2
  depth(Branch(Leaf(1), Leaf(2)))                 //> res2: Int = 1
  depth(Branch(Branch(Leaf(1), Leaf(3)), Leaf(4)))//> res3: Int = 2
  depth(Leaf(1))                                  //> res4: Int = 0
  map(Branch(Branch(Leaf(1), Leaf(3)), Leaf(4)))(_ + 1)
                                                  //> res5: fpinscala.datastructures.Tree[Int] = Branch(Branch(Leaf(2),Leaf(4)),Le
                                                  //| af(5))
}