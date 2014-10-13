package fpinscala.state

import State._

object state_testing {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(154); 
  // using scala's built in random number generator
  val rngScala = new scala.util.Random;System.out.println("""rngScala  : scala.util.Random = """ + $show(rngScala ));$skip(22); val res$0 = 
  rngScala.nextDouble;System.out.println("""res0: Double = """ + $show(res$0));$skip(22); val res$1 = 
  rngScala.nextDouble;System.out.println("""res1: Double = """ + $show(res$1));$skip(19); val res$2 = 
  rngScala.nextInt;System.out.println("""res2: Int = """ + $show(res$2));$skip(19); val res$3 = 
  rngScala.nextInt;System.out.println("""res3: Int = """ + $show(res$3));$skip(23); val res$4 = 
  rngScala.nextInt(10);System.out.println("""res4: Int = """ + $show(res$4));$skip(22); val res$5 = 
  rngScala.nextInt(6);System.out.println("""res5: Int = """ + $show(res$5));$skip(73); 
  
  // using our simple RNG from State.scala
  val rng = RNG.Simple(42);System.out.println("""rng  : fpinscala.state.RNG.Simple = """ + $show(rng ));$skip(31); 
  val (n1, rng2) = rng.nextInt;System.out.println("""n1  : Int = """ + $show(n1 ));System.out.println("""rng2  : fpinscala.state.RNG = """ + $show(rng2 ));$skip(32); 
  val (n2, rng3) = rng2.nextInt;System.out.println("""n2  : Int = """ + $show(n2 ));System.out.println("""rng3  : fpinscala.state.RNG = """ + $show(rng3 ));$skip(19); val res$6 = 
  
  Int.MinValue;System.out.println("""res6: Int(-2147483648) = """ + $show(res$6));$skip(15); val res$7 = 
  Int.MaxValue;System.out.println("""res7: Int(2147483647) = """ + $show(res$7));$skip(47); 
  
  val (n3, rng4) = RNG.nonNegativeInt(rng3);System.out.println("""n3  : Int = """ + $show(n3 ));System.out.println("""rng4  : fpinscala.state.RNG = """ + $show(rng4 ));$skip(45); 
  val ((n4, d1), rng5) = RNG.intDouble(rng4);System.out.println("""n4  : Int = """ + $show(n4 ));System.out.println("""d1  : Double = """ + $show(d1 ));System.out.println("""rng5  : fpinscala.state.RNG = """ + $show(rng5 ));$skip(47); 
  val ((d2, d3, d4), rng6) = RNG.double3(rng5);System.out.println("""d2  : Double = """ + $show(d2 ));System.out.println("""d3  : Double = """ + $show(d3 ));System.out.println("""d4  : Double = """ + $show(d4 ));System.out.println("""rng6  : fpinscala.state.RNG = """ + $show(rng6 ));$skip(23); val res$8 = 
  
  RNG.ints(5)(rng6);System.out.println("""res8: (List[Int], fpinscala.state.RNG) = """ + $show(res$8))}
}
