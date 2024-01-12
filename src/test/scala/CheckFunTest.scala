import org.scalatest.funsuite.AnyFunSuite
import com.cyril103._


class CheckFunTest extends AnyFunSuite {
  val finish = List(1, 2, 3, 4, 5)
  val order: Order = Order(finish)
  val unorder: UnOrder = UnOrder(finish)
  val bonus4: Bonus4 = Bonus4(finish)
  val bonus3: Bonus3 = Bonus3(finish)

  test("checkOrder") {
    assertResult(order.check(List(1, 2, 3, 4, 5)))(true)
    assertResult(order.check(List(2, 1, 3, 4, 5)))(false)
    assertResult(order.check(List()))(false)
  }

  test("checkUnOrder") {
    assertResult(unorder.check(List(2,1,3,4,5)))(true)
    assertResult(unorder.check(finish))(false)
    assertResult(unorder.check(List(1,2,3,4,5)))(false)
    assertResult(unorder.check(List()))(false)
  }

  val bonus: Iterator[List[Int]] = finish.combinations(4).map(l => 6 :: l)
  test("check bonus4") {
    for (l <- bonus) {
      assertResult(bonus4.check(l))( true)
    }
    assertResult(bonus4.check(List()))(false)
    assertResult(bonus4.check(finish))(false)
    assertResult(bonus4.check(List(2,1,3,4,5)))(false)

  }

  val first3: List[Int] = finish.take(3)
  val permutations: Iterator[List[Int]] = (6::7::first3).permutations
  test("checkBonus3"){
    for(xs <- permutations){
      assertResult(bonus3.check(xs))(true)
    }
    assertResult(bonus3.check(List(1,2,3,4,6)))(false)
    assertResult(bonus3.check(List(4,5,3,6,7)))(false)
  }
}
