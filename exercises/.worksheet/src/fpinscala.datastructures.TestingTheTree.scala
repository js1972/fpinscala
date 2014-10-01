package fpinscala.datastructures

import Tree._

object TestingTheTree {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(105); val res$0 = 
  size(Branch(Leaf(1), Leaf(2)));System.out.println("""res0: Int = """ + $show(res$0));$skip(36); val res$1 = 
  maximum(Branch(Leaf(1), Leaf(2)));System.out.println("""res1: Int = """ + $show(res$1));$skip(34); val res$2 = 
  depth(Branch(Leaf(1), Leaf(2)));System.out.println("""res2: Int = """ + $show(res$2));$skip(51); val res$3 = 
  depth(Branch(Branch(Leaf(1), Leaf(3)), Leaf(4)));System.out.println("""res3: Int = """ + $show(res$3));$skip(17); val res$4 = 
  depth(Leaf(1));System.out.println("""res4: Int = """ + $show(res$4));$skip(56); val res$5 = 
  map(Branch(Branch(Leaf(1), Leaf(3)), Leaf(4)))(_ + 1);System.out.println("""res5: fpinscala.datastructures.Tree[Int] = """ + $show(res$5))}
}
