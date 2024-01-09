import org.scalatest.funsuite.AnyFunSuite
import com.cyril103.Main._


class CheckfunTest extends AnyFunSuite {
  val finish = List(1, 2, 3, 4, 5)

  test("checkOrder") {
    assert(checkOrder(finish, List(1, 2, 3, 4, 5)), "must be true!")
    assert(!checkOrder(finish, List(2, 1, 3, 4, 5)), "must be false!")
    assert(!checkOrder(finish, List()), "must be false!")
  }

  test("checkunOrder") {
    assert(checkUnorder(finish,List(2,1,3,4,5)),"must be true")
    assert(!checkUnorder(finish,finish),"must be false")
    assert(!checkUnorder(finish,List(1,2,3,4,5)),"must be false")
    assert(!checkUnorder(finish,List()),"must be false")
  }

  val bonus4: Iterator[List[Int]] = finish.combinations(4).map(l => 6 :: l)
  test("check bonus4") {
    for (l <- bonus4) {
      assert(checkBonus4(finish, l), "must be true")
    }
    assert(!checkBonus4(finish,List()),"must be false")
    assert(!checkBonus4(finish,finish),"must be false")
    assert(!checkBonus4(finish,List(2,1,3,4,5)),"must be false")

  }
  val first3: List[Int] = finish.take(3)
  val permutations: Iterator[List[Int]] = (6::7::first3).permutations
  test("checkBonus3"){
    for(xs <- permutations){
      assert(checkBonus3(finish,xs),"must be true")
    }
    assert(!checkBonus3(finish,List(1,2,3,4,6)),"must be false")
    assert(!checkBonus3(finish,List(4,5,3,6,7)),"must be false")
  }


}
