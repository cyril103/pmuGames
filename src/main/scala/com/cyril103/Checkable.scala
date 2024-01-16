package com.cyril103

trait Checkable {
    def gain : BigDecimal
    def check(horses : List[Int]) : Boolean 
}

class Order(finish : List[Int], val gain : BigDecimal = BigDecimal(0.0)) extends Checkable{

  override def check(horses: List[Int]): Boolean = finish == horses

  override def toString: String = "ordre: "
}

class UnOrder(finish : List[Int], val gain : BigDecimal = BigDecimal(0.0)) extends Checkable{
  
  override def check(horses: List[Int]): Boolean =
    !(finish == horses) && (finish.sorted == horses.sorted)

  override def toString: String = "désordre: "
}

class Bonus4(finish : List[Int], val gain : BigDecimal = BigDecimal(0.0)) extends Checkable{
  
  override def check(horses: List[Int]): Boolean = {
    horses.foldLeft(0){(count, e) =>
      if finish.contains(e) then count + 1 else count
    } == 4
  }
  
  override def toString: String = "Bonus4: "
}

class Bonus3(finish : List[Int], val gain : BigDecimal = BigDecimal(0.0)) extends Checkable{
  
  override def check(horses: List[Int]): Boolean = {
    val first3 = finish.take(3).toSet
    val (count , countOther) = horses.foldLeft(0 -> 0){case (c -> co , e) =>
      if first3(e) then (c + 1) -> co
      else if finish.contains(e) then c -> (co + 1)
      else c -> co
    }
    count  == 3 && countOther == 0
  }

  override def toString: String = "Bonus3: "
}


