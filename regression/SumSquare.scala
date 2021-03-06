import spatial.dsl._

class SumSquare_0 extends SumSquare
class SumSquare_1 extends SumSquare(ip=1)
class SumSquare_2 extends SumSquare(ip=3)
class SumSquare_3 extends SumSquare(ip=1, op=2)
//class SumSquare_4 extends SumSquare(ip=1, op=3)
class SumSquare_5 extends SumSquare(ip=3, op=2)
//class SumSquare_6 extends SumSquare(ip=3, op=3)

@spatial abstract class SumSquare(
  N:scala.Int = 1024,
  op:scala.Int = 1,
  ts:scala.Int = 32,
  ip:scala.Int = 16
) extends SpatialTest {
  type T = Int

  def sumSquare(aIn: Array[T]): T = {

    val out = ArgOut[T]

    Accel {
      val accO = Reg[T](0.to[T])
      out := Reduce(accO)(N by ts par op){i =>
        val accI = Reg[T](0.to[T])
        Reduce(accI)(ts par ip){ j => 
          val k = i + j
          k * k
        }{_+_}
      }{_+_}
    }
    getArg(out)
  }


  def main(args: Array[String]): Unit = {
    val a = Array.fill(N){ random[T](4) }

    val result = sumSquare(a)
    //val gold = a.map { aa => aa * aa }.reduce{_+_}
    val gold = List.tabulate(N) { i => i * i }.sum

    println("expected: " + gold)
    println("result: " + result)

    val cksum = approxEql[T](gold,result)
    println("PASS: " + cksum + " (SumSquare)")
    assert(cksum)
  }
}

