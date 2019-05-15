import spatial.dsl._

case class LUTTestParam(
  N:scala.Int = 128,
  op:scala.Int = 1,
  ts:scala.Int = 32,
  ip:scala.Int = 16
) extends Param[LUTTestParam]

class LUTTest_0 extends LUTTest
class LUTTest_1 extends LUTTest { override lazy val param = LUTTestParam(ip=1, op=1) }
class LUTTest_2 extends LUTTest { override lazy val param = LUTTestParam(ip=1, op=2) }
class LUTTest_3 extends LUTTest { override lazy val param = LUTTestParam(op=2) }
class LUTTest_4 extends LUTTest { override lazy val param = LUTTestParam(ts=16, op=2, N=32) }

@spatial abstract class LUTTest extends DSETest {
  type T = Float

  lazy val param = LUTTestParam()
  import param._

  def main(args: Array[String]): Unit = {
    val a = Seq.tabulate(N){ i => i.to[T] }
    val b = Seq.tabulate(N){ i => i.to[T] }

    val out = ArgOut[T]
    Accel {
      val lutA = LUT.fromSeq[T](a.size)(a)
      val lutB = LUT.fromSeq[T](b.size)(b)
      Reduce(out)(N by ts par op){ i =>
        val dot = Reg[T]
        Reduce(dot)(ts par ip){ii => lutA(i+ii) * lutB(i+ii) }{_+_}
      } {_ + _}
    }
    val result = getArg(out)

    val gold = Array.fromSeq(a).zip(Array.fromSeq(b)){_*_}.reduce{_+_}

    println("expected: " + gold)
    println("result: " + result)

    val cksum = abs(gold - result) < (gold * 0.05f)
    println("PASS: " + cksum + " (LUTTest)")
    assert(cksum)
  }
}

