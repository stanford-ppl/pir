import spatial.dsl._

case class DotProductParam(
  N:scala.Int = 1024,
  op:scala.Int = 1,
  ts:scala.Int = 32,
  ip:scala.Int = 16
) extends Param[DotProductParam]

class DotProduct_0 extends DotProduct
class DotProduct_1 extends DotProduct { override lazy val param = DotProductParam(ip=1, op=1) }
class DotProduct_2 extends DotProduct { override lazy val param = DotProductParam(ip=1, op=2) }
class DotProduct_3 extends DotProduct { override lazy val param = DotProductParam(op=2) }

@spatial abstract class DotProduct extends DSETest {
  type X = FixPt[TRUE,_32,_0]

  lazy val param = DotProductParam()
  import param._

  def dotproduct[T:Num](aIn: Array[T], bIn: Array[T]): T = {

    val a = DRAM[T](N)
    val b = DRAM[T](N)
    val out = ArgOut[T]
    setMem(a, aIn)
    setMem(b, bIn)

    Accel {
      val accO = Reg[T](0.to[T])
      out := Reduce(accO)(N by ts par op){i =>
        val aBlk = SRAM[T](ts)
        val bBlk = SRAM[T](ts)
        Parallel {
          aBlk load a(i::i+ts par ip)
          bBlk load b(i::i+ts par ip)
        }
        val accI = Reg[T](0.to[T])
        Reduce(accI)(ts par ip){ii => aBlk(ii) * bBlk(ii) }{_+_}
      }{_+_}
    }
    getArg(out)
  }


  def main(args: Array[String]): Unit = {
    val a = Array.fill(N){ random[X](4) }
    val b = Array.fill(N){ random[X](4) }

    val result = dotproduct(a, b)
    val gold = a.zip(b){_*_}.reduce{_+_}

    println("expected: " + gold)
    println("result: " + result)

    val cksum = gold == result
    println("PASS: " + cksum + " (DotProduct)")
    assert(cksum)
  }
}
