package fpinscala.state

import State._
import RNG._

object state_testing {
  // using scala's built in random number generator
  val rngScala = new scala.util.Random            //> rngScala  : scala.util.Random = scala.util.Random@4351aa16
  rngScala.nextDouble                             //> res0: Double = 0.4566570858094301
  rngScala.nextDouble                             //> res1: Double = 0.4964541677563077
  rngScala.nextInt                                //> res2: Int = 745846429
  rngScala.nextInt                                //> res3: Int = -148495605
  rngScala.nextInt(10)                            //> res4: Int = 5
  rngScala.nextInt(6)                             //> res5: Int = 4
  
  // using our simple RNG from State.scala
  val rng = Simple(42)                            //> rng  : fpinscala.state.RNG.Simple = Simple(42)
  val (n1, rng2) = rng.nextInt                    //> n1  : Int = 16159453
                                                  //| rng2  : fpinscala.state.RNG = Simple(1059025964525)
  val (n2, rng3) = rng2.nextInt                   //> n2  : Int = -1281479697
                                                  //| rng3  : fpinscala.state.RNG = Simple(197491923327988)
  
  Int.MinValue                                    //> res6: Int(-2147483648) = -2147483648
  Int.MaxValue                                    //> res7: Int(2147483647) = 2147483647
  
  val (n3, rng4) = nonNegativeInt(rng3)           //> n3  : Int = 340305901
                                                  //| rng4  : fpinscala.state.RNG = Simple(259172689157871)
  val ((n4, d1), rng5) = intDouble(rng4)          //> n4  : Int = -2015756020
                                                  //| d1  : Double = 0.8242210922762752
                                                  //| rng5  : fpinscala.state.RNG = Simple(115998806404289)
  val ((d2, d3, d4), rng6) = double3(rng5)        //> d2  : Double = 0.9008632311597466
                                                  //| d3  : Double = 0.4730720594525337
                                                  //| d4  : Double = 0.5418585799634457
                                                  //| rng6  : fpinscala.state.RNG = Simple(205215161057714)
  
  val (l, rng7) = ints(5)(rng6)                   //> l  : List[Int] = List(-94901159, 1837487774, -122709694, 141607732, -1571634
                                                  //| 817)
                                                  //| rng7  : fpinscala.state.RNG = Simple(178476317395497)
   
  _ints(5)(rng7)                                  //> res8: (List[Int], fpinscala.state.RNG) = (List(-1660936491, 1911657181, 2500
                                                  //| 29321, -384268462, -1166376033),Simple(205035357064836))
  
  def rollDie: RNG.Rand[Int] = nonNegativeLessThan(6)
                                                  //> rollDie: => fpinscala.state.RNG.Rand[Int]
  val zero = rollDie(Simple(5))._1                //> zero  : Int = 0
  
  def rollDie2: RNG.Rand[Int] = map(nonNegativeLessThan(6))(_ + 1)
                                                  //> rollDie2: => fpinscala.state.RNG.Rand[Int]
  val x = rollDie2(Simple(5))._1                  //> x  : Int = 1
}