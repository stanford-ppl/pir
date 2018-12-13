import spatial.dsl._

case class DotProductParam(
  N:scala.Int = 1048576,
  op:scala.Int = 1,
  ts:scala.Int = 32768
) extends Param[DotProductParam]

class DotProduct_0 extends DotProduct
class DotProduct_1 extends DotProduct { override lazy val param = DotProductParam(op=2) }
class DotProduct_2 extends DotProduct { override lazy val param = DotProductParam(op=4) }
class DotProduct_3 extends DotProduct { override lazy val param = DotProductParam(op=6) }
class DotProduct_4 extends DotProduct { override lazy val param = DotProductParam(op=8) }
class DotProduct_5 extends DotProduct { override lazy val param = DotProductParam(op=10) }
class DotProduct_6 extends DotProduct { override lazy val param = DotProductParam(op=12) }
//class DotProduct_7 extends DotProduct { override lazy val param = DotProductParam(op=14) }
//class DotProduct_8 extends DotProduct { override lazy val param = DotProductParam(op=16) }

@spatial abstract class DotProduct extends DSETest {
  type X = FixPt[TRUE,_32,_0]

  lazy val param = DotProductParam()
  import param._

  def dotproduct[T:Num](aIn: Array[T], bIn: Array[T]): T = {
    val ip = 16

    //val size = aIn.length; bound(size) = 1920000

    //val N = ArgIn[Int]
    //setArg(N, size)

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
