package com.cyril103

object Games {

  def calculate(base: List[Int], assoc: List[Int]): (Vector[String], Vector[String], List[List[Int]]) = {
    require(base.sizeIs >= 1 && base.sizeIs <= 4 && base.size + assoc.size >= 5)
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
  

}
