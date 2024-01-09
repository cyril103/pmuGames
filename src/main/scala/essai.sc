import scala.annotation.tailrec

def sum(f: Int => Int, a: Int, b: Int): Int =
  @tailrec
  def loop(a: Int, acc: Int): Int =
    if a > b then acc
    else loop(a + 1, f(a) + acc)

  loop(a, 0)
val xs : List[Boolean | Int] = List(true,1)
List(1,2,3) == List(2,1,3)

val m = collection.mutable.Map[String, String]()



sum(x=>x*x,1,3)