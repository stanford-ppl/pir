import spatial.dsl._
import spatial.lib.ML._

class DotProduct_0 extends DotProduct
class DotProduct_1 extends DotProduct(ip=1, op=1)
class DotProduct_2 extends DotProduct(ip=1, op=2)
class DotProduct_3 extends DotProduct(op=2)
class DotProduct_4 extends DotProduct(op=8)
class DotProduct_5 extends DotProduct(op=3)
class DotProduct_6 extends DotProduct(op=1, ip=5)
class DotProduct_7 extends DotProduct(op=3, ip=5)

@spatial abstract class DotProduct(
  N:scala.Int = 1024,
  op:scala.Int = 1,
  ts:scala.Int = 32,
  ip:scala.Int = 16
) extends SpatialTest {
  type X = Int

  def dotproduct[T:Num](aIn: Array[T], bIn: Array[T]) = {

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
        dp_flat(ts, ip) { ii => (aBlk(ii), bBlk(ii)) }
      }{_+_}
    }
    out
  }


  def main(args: Array[String]): Unit = {
    val a = Array.tabulate(N){ i => i.to[X] }
    val b = Array.tabulate(N){ i => i.to[X] }

    val out = dotproduct[X](a, b)
    val gold = a.zip(b){_*_}.reduce{_+_}

    val cksum = checkGold(out, gold)
    println("PASS: " + cksum + " (DotProduct)")
    assert(cksum)
  }
}
