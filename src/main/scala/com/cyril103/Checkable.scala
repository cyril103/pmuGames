package com.cyril103

trait Checkable {
  def finish : List[Int]
  def check(horses : List[Int]) : Boolean
  def addIfCheck(start : String, horses : List[Int]) : String = {
    if check(horses) then s"$start${horses.mkString(",")} | "
    else  s"$start"
  }
}

class Order(val finish : List[Int]) extends Checkable{
  override def check(horses: List[Int]): Boolean = finish == horses
}

class UnOrder(val finish : List[Int]) extends Checkable{
  override def check(horses: List[Int]): Boolean =
    !(finish == horses) && (finish.sorted == horses.sorted)
}

class Bonus4(val finish : List[Int]) extends Checkable{
  override def check(horses: List[Int]): Boolean = {
    horses.foldLeft(0){(count, e) =>
      if finish.contains(e) then count + 1 else count
    } == 4
  }
}

class Bonus3(val finish : List[Int]) extends Checkable{
  override def check(horses: List[Int]): Boolean = {
    val first3 = finish.take(3).toSet
    val (count , countOther) = horses.foldLeft(0 -> 0){case (c -> co , e) =>
      if first3(e) then (c + 1) -> co
      else if finish.contains(e) then c -> (co + 1)
      else c -> co
    }
    count  == 3 && countOther == 0

  }
}


