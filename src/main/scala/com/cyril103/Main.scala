package com.cyril103


import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Main  extends App {

  val base = List(1,2,3)
  val assoc = List(8,5,4)
  val finish = List(2,1,3,4,5)

  val (unitaires, champs, tirages) = Games.calculate(base, assoc)

  champs.foreach(println)
  println()
  unitaires.foreach(println)
  println()

  val wins = List(Order(finish),UnOrder(finish),Bonus4(finish),Bonus3(finish))

  wins.foreach( draw => println(draw.winDraws(tirages)))

}
