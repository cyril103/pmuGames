package com.cyril103

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Main  {
  def checkOrder(finish: List[Int], MyFive :List[Int]): Boolean = finish == MyFive
  
  def checkUnorder(finish : List[Int], myFive : List[Int]): Boolean =    
    !(finish == myFive) && (finish.sorted == myFive.sorted)
    
  def checkBonus3(finish : List[Int], MyFive : List[Int]): Boolean = {
    val first3 = finish.take(3).toSet
    val (count , countOther) = MyFive.foldLeft(0 -> 0){case (count -> countOther , e) =>      
      if first3(e) then (count + 1) -> countOther
      else if finish.contains(e) then count -> (countOther + 1)
      else count -> countOther
    }
    count  == 3 && countOther == 0  
  }
  
  def checkBonus4(finish : List[Int], MyFive : List[Int]): Boolean = {
    MyFive.foldLeft(0){(count, e) =>
      if finish.contains(e) then count + 1 else count
    } == 4
  }

  def gamesWins(finish : List[Int], games : List[List[Int]]): (ListBuffer[String], ListBuffer[String], ListBuffer[String], ListBuffer[String]) = {
    val order = mutable.ListBuffer[String]()
    val unorder = mutable.ListBuffer[String]()
    val bonus4 = mutable.ListBuffer[String]()
    val bonus3 = mutable.ListBuffer[String]()
    for{
      game <- games
    }{

      if checkOrder(finish,game) then order +=  game.mkString(",")
      else if checkUnorder(finish,game) then  unorder += game.mkString(",")
      else if checkBonus4(finish,game) then bonus4 += game.mkString(",")
      else if checkBonus3(finish,game) then bonus3 += game.mkString(",")
    }
    (order,unorder,bonus4,bonus3)
  }

  def games(base: List[Int], assoc: List[Int]): (Vector[String], Vector[String], List[List[Int]]) = {
    val nCombination = 5 - base.length
    val combinations = assoc.combinations(nCombination)
    var n = 0
    val unitaires = for {
      c <- combinations

    } yield {
      base ++ c
    }

    val tirages = unitaires.toList
    val (simple , reduit) = tirages.groupBy(_.init).map{case (xs,ys) =>
      val ass = ys.map(_.last)
      (xs,ass)
    }.toVector.partition{_._2.length == 1}
    (simple.map{
      case (a,b) => (a ++ b).mkString("unitaire: "," ", ".")
    },
    reduit.map{
      case (a,b) => a.mkString("champ reduit: ", " ", ".") + b.mkString(" associés: ", " ", ".")
    }, tirages)

  }

/*

  val base = List(12,10,14)
  val assoc = List(13,16,9,6,1)

  val (unitaires, champs, tirages) = games(base, assoc)
  champs.foreach(println)
  println()
  unitaires.foreach(println)
  println()

  val (order,unorder,bonus4,bonus3) = gamesWins(List(13,9,10,5,14),tirages)
  println("gains:  ")
  println(order.mkString("ordre: "," | ","."))
  println(unorder.mkString("desordre: "," | ","."))
  println(bonus4.mkString("bonus4: "," | ","."))
  println(bonus3.mkString("bonus3: "," | ","."))

*/




}
