import spatial.dsl._

class SimpleDotProduct_0 extends SimpleDotProduct
class SimpleDotProduct_1 extends SimpleDotProduct(op=2)
//class SimpleDotProduct_2 extends SimpleDotProduct(op=3) // Not working yet

@spatial abstract class SimpleDotProduct(
  N:scala.Int = 1024,
  op:scala.Int = 1,
  ts:scala.Int = 32,
  ip:scala.Int = 1
) extends SpatialTest {
  type T = FixPt[TRUE,_32,_0]

  def dotproduct(aIn: Array[T], bIn: Array[T]): T = {

    val out = ArgOut[T]
    Accel {
      val accO = Reg[T](0.to[T])
      out := Reduce(accO)(N by ts par op){i =>
        val aBlk = SRAM[T](ts)
        Foreach(ts par ip) { ii =>
          aBlk(ii) = i + ii
        }
        val accI = Reg[T](0.to[T])
        Reduce(accI)(ts par ip){ii => aBlk(ii) * aBlk(ii) }{_+_}
      }{_+_}
    }
    getArg(out)
  }


  def main(args: Array[String]): Unit = {
    val a = Array.tabulate(N){ i => i }
    val b = Array.tabulate(N){ i => i }

    val result = dotproduct(a, b)
    val gold = a.zip(b){_*_}.reduce{_+_}

    println("expected: " + gold)
    println("result: " + result)

    val cksum = approxEql(gold,result)
    println("PASS: " + cksum + " (SimpleDotProduct)")
    assert(cksum)
  }
}
