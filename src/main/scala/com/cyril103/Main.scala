package com.cyril103


import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Main  extends App {

  def games(base: List[Int], assoc: List[Int]): (Vector[String], Vector[String], List[List[Int]]) = {
    val nCombination = 5 - base.length
    val combinations = assoc.combinations(nCombination)
    val unitaires = for (c <- combinations) yield  base ++ c
    val tirages = unitaires.toList
    val (simple , reduit) = tirages.groupBy(_.init).map{case (xs,ys) =>
      val ass = ys.map(_.last)
      (xs,ass)
    }.toVector.partition{_._2.sizeIs == 1}
    (simple.map{
      case (a,b) => (a ++ b).mkString("unitaire: "," ", ".")
    },
    reduit.map{
      case (a,b) => s"${a.mkString("champ réduit: ", " ", ".")}${b.mkString(" associés: ", " ", ".")}"
    }, tirages)

  }
  val base = List(1,2,3)
  val assoc = List(8,7,6)
  val finish = List(2,1,3,4,5)
  val (unitaires, champs, tirages) = games(base, assoc)
  champs.foreach(println)
  println()
  unitaires.foreach(println)
  println()
  val order = Order(finish)
  val unOrder = UnOrder(finish)
  val bonus4 = Bonus4(finish)
  val bonus3 = Bonus3(finish)

  println(tirages.foldLeft("ordre: ")(order.addIfCheck))
  println(tirages.foldLeft("desordre: ")(unOrder.addIfCheck))
  println(tirages.foldLeft("bonus4: ")(bonus4.addIfCheck))
  println(tirages.foldLeft("bonus3: ")(bonus3.addIfCheck))







}
