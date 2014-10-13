package fpinscala.state

import State._

object state_testing {
  // using scala's built in random number generator
  val rngScala = new scala.util.Random            //> rngScala  : scala.util.Random = scala.util.Random@5ca32803
  rngScala.nextDouble                             //> res0: Double = 0.07207159125252915
  rngScala.nextDouble                             //> res1: Double = 0.861125034171589
  rngScala.nextInt                                //> res2: Int = -1861314180
  rngScala.nextInt                                //> res3: Int = 1420256111
  rngScala.nextInt(10)                            //> res4: Int = 3
  rngScala.nextInt(6)                             //> res5: Int = 4
  
  // using our simple RNG from State.scala
  val rng = RNG.Simple(42)                        //> rng  : fpinscala.state.RNG.Simple = Simple(42)
  val (n1, rng2) = rng.nextInt                    //> n1  : Int = 16159453
                                                  //| rng2  : fpinscala.state.RNG = Simple(1059025964525)
  val (n2, rng3) = rng2.nextInt                   //> n2  : Int = -1281479697
                                                  //| rng3  : fpinscala.state.RNG = Simple(197491923327988)
  
  Int.MinValue                                    //> res6: Int(-2147483648) = -2147483648
  Int.MaxValue                                    //> res7: Int(2147483647) = 2147483647
  
  val (n3, rng4) = RNG.nonNegativeInt(rng3)       //> n3  : Int = 340305901
                                                  //| rng4  : fpinscala.state.RNG = Simple(259172689157871)
  val ((n4, d1), rng5) = RNG.intDouble(rng4)      //> n4  : Int = -2015756020
                                                  //| d1  : Double = 0.8242210922762752
                                                  //| rng5  : fpinscala.state.RNG = Simple(115998806404289)
  val ((d2, d3, d4), rng6) = RNG.double3(rng5)    //> d2  : Double = 0.9008632311597466
                                                  //| d3  : Double = 0.4730720594525337
                                                  //| d4  : Double = 0.5418585799634457
                                                  //| rng6  : fpinscala.state.RNG = Simple(205215161057714)
  
  RNG.ints(5)(rng6)                               //> res8: (List[Int], fpinscala.state.RNG) = (List(-94901159, 1837487774, -12270
                                                  //| 9694, 141607732, -1571634817),Simple(178476317395497))
}