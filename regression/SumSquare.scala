import spatial.dsl._

case class SumSquareParam(
  N:scala.Int = 64,
  op:scala.Int = 1,
  ts:scala.Int = 32,
  ip:scala.Int = 16
) extends Param[SumSquareParam]

class SumSquare_0 extends SumSquare

@spatial abstract class SumSquare extends DSETest {
  type T = Int

  lazy val param = SumSquareParam()
  import param._

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
    val gold = a.map { aa => aa * aa }.reduce{_+_}

    println("expected: " + gold)
    println("result: " + result)

    val cksum = gold == result
    println("PASS: " + cksum + " (SumSquare)")
    assert(cksum)
  }
}

