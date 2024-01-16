package com.cyril103
import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.scalajs.dom


object Main  extends App {
  var checkedBoxes = List.empty[Int]

  val checkBoxes = dom.document.querySelectorAll("ul:first-of-type input[type=checkbox]")
  for (i <- 0 until checkBoxes.length) {
    checkBoxes(i).addEventListener("change", (e: dom.Event) => {
      val target = e.target.asInstanceOf[dom.html.Input]
      if (target.checked) {
        checkedBoxes =  checkedBoxes :+ target.value.toInt
      } else {
        checkedBoxes = checkedBoxes.filter(_ != target.value.toInt)
      }
      //println(checkedBoxes)
    })}
  @JSExportTopLevel("storeCheckedBoxes")
  def storeCheckedBoxes(): Unit = {
    println(checkedBoxes)
  }
  val mise = BigDecimal(2.00)
  val gainOrdre = BigDecimal(330.40)
  val gainDesordre = BigDecimal(9.00)
  val gainBonus4 = BigDecimal(2.40)
  val gainBonus3 = BigDecimal(2.20)

  val base = List(10,15,7)
  val assoc = List(8,13,12,6,14)
  val finish = List(10,5,11,9,3)

  val (unitaires, champs, tirages) = Games.calculate(base, assoc)

  champs.foreach(println)
  println()
  unitaires.foreach(println)
  println()
  val miseTotale = mise * tirages.size
  println(s"mise totale: $miseTotale€")
  println()

  val wins = Array(Order(finish,gainOrdre),UnOrder(finish,gainDesordre),
    Bonus4(finish,gainBonus4),Bonus3(finish,gainBonus3))

  val result = tirages.groupMap{horses =>
    wins.foldLeft("Aucun gains :")((acc,checkable) =>
      if checkable.check(horses) then  checkable.toString else acc)
  }{horses => horses.mkString(",")}

  wins.foreach{x =>
    println(s"${x.toString}${result.getOrElse(x.toString,Nil).mkString(" | ")}" )
  }
  val totalGain = wins.map{x =>
    result.getOrElse(x.toString,Nil).size * x.gain
  }.sum - miseTotale


  println(s"${if totalGain >= 0 then "gain: " else "perte: "}$totalGain€")

}
