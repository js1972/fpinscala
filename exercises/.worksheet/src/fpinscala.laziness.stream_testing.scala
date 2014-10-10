package fpinscala.laziness

import Stream._

object stream_testing {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(99); val res$0 = 
  Stream(1, 2, 3, 4, 5).toList;System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(43); val res$1 = 
  
  (Stream(1, 2, 3, 4, 5) drop 2).toList;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(40); val res$2 = 
  (Stream(1, 2, 3, 4, 5) take 2).toList;System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(50); val res$3 = 
  (Stream(1, 2, 3, 4, 5) takeWhile(_ < 4)).toList;System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(41); val res$4 = 
  Stream(1, 2, 3, 4, 5) forAll { _ < 3 };System.out.println("""res4: Boolean = """ + $show(res$4));$skip(53); val res$5 = 
  (Stream(1, 2, 3, 4, 5) takeWhile { _ < 4 }).toList;System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(38); val res$6 = 
  
  Stream(1, 2, 3, 4, 5) headOption;System.out.println("""res6: Option[Int] = """ + $show(res$6));$skip(52); val res$7 = 
  
  Stream(1, 2, 3, 4, 5) headOptionUsingFoldRight;System.out.println("""res7: Option[Int] = """ + $show(res$7));$skip(68); val res$8 = 
  
  
  // some infinite stream processing
  
  ones.take(5).toList;System.out.println("""res8: List[Int] = """ + $show(res$8));$skip(26); val res$9 = 
  ones.exists(_ % 2 != 0);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(37); val res$10 = 
  ones.map(_ + 1).exists(_ % 2 == 0);System.out.println("""res10: Boolean = """ + $show(res$10));$skip(40); val res$11 = 
  ones.takeWhile(_ == 1).take(5).toList;System.out.println("""res11: List[Int] = """ + $show(res$11));$skip(22); val res$12 = 
  ones.forAll(_ != 1);System.out.println("""res12: Boolean = """ + $show(res$12));$skip(32); val res$13 = 
  
  constant(4).take(5).toList;System.out.println("""res13: List[Int] = """ + $show(res$13));$skip(25); val res$14 = 
  from(1).take(5).toList;System.out.println("""res14: List[Int] = """ + $show(res$14));$skip(23); val res$15 = 
  fibs.take(10).toList;System.out.println("""res15: List[Int] = """ + $show(res$15));$skip(36); val res$16 = 
  
  onesUsingUnfold.take(5).toList;System.out.println("""res16: List[Int] = """ + $show(res$16));$skip(40); val res$17 = 
  constantUsingUnfold(4).take(5).toList;System.out.println("""res17: List[Int] = """ + $show(res$17));$skip(37); val res$18 = 
  fromUsingUnfold(12).take(5).toList;System.out.println("""res18: List[Int] = """ + $show(res$18));$skip(51); val res$19 = 
  
  Stream(1, 2, 3, 4, 5).takeViaUnfold(3).toList;System.out.println("""res19: List[Int] = """ + $show(res$19));$skip(55); val res$20 = 
  
  Stream(1, 2, 3, 4, 5).startsWith(Stream(1, 2, 3));System.out.println("""res20: Boolean = """ + $show(res$20));$skip(45); val res$21 = 
  Stream(1, 2, 3, 4, 5).tails.take(5).toList;System.out.println("""res21: List[fpinscala.laziness.Stream[Int]] = """ + $show(res$21));$skip(56); val res$22 = 
  Stream(1, 2, 3, 4, 5) hasSubsequence(Stream(1, 2, 3));System.out.println("""res22: Boolean = """ + $show(res$22));$skip(56); val res$23 = 
  
  (Stream(1, 2, 3, 4, 5).scanRight(0){_ + _}).toList;System.out.println("""res23: List[Int] = """ + $show(res$23))}
}
