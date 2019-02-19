import spatial.dsl._

case class OuterProductParam(
  M:scala.Int = 256,
  N:scala.Int = 256,
  ts1:scala.Int = 64,
  ts2:scala.Int = 32,
  ip:scala.Int = 16,
  op1:scala.Int = 1,
  op2:scala.Int = 1
) extends Param[OuterProductParam]

class OuterProduct_0 extends OuterProduct
class OuterProduct_1 extends OuterProduct {override lazy val param = OuterProductParam(ip=1)}
class OuterProduct_2 extends OuterProduct {override lazy val param = OuterProductParam(ip=1, op2=2)}
class OuterProduct_3 extends OuterProduct {override lazy val param = OuterProductParam(ip=1, op1=2)}
class OuterProduct_4 extends OuterProduct {override lazy val param = OuterProductParam(ip=1, op1=2, op2=2)}
//class OuterProduct_2 extends OuterProduct {override lazy val param = OuterProductParam(op2=4)}
//class OuterProduct_3 extends OuterProduct {override lazy val param = OuterProductParam(op2=6)}
//class OuterProduct_4 extends OuterProduct {override lazy val param = OuterProductParam(op2=8)}
//class OuterProduct_5 extends OuterProduct {override lazy val param = OuterProductParam(op2=10)}

@spatial abstract class OuterProduct extends DSETest { // Regression (Dense) // Args: 640 640

  lazy val param = OuterProductParam()
  import param._

  type X = FixPt[TRUE,_32,_0]


  def outerproduct[T:Num](a: Array[T], b: Array[T]) = {

    //val sizeA = ArgIn[Int]
    //val sizeB = ArgIn[Int]
    //setArg(sizeA, a.length); bound(a.length) = M
    //setArg(sizeB, b.length); bound(b.length) = N
    val sizeA = N
    val sizeB = N

    val vec1 = DRAM[T](sizeA)
    val vec2 = DRAM[T](sizeB)
    val out = DRAM[T](sizeA, sizeB)

    setMem(vec1, a)
    setMem(vec2, b)

    Accel {
      Foreach(sizeA by ts1 par op1){ i =>
        val b1 = SRAM[T](ts1)
        b1 load vec1(i::i+ts1 par ip)
        Foreach(sizeB by ts2 par op2) { j =>
          val b2 = SRAM[T](ts2)
          b2 load vec2(j::j+ts2 par ip)
          val outTile = SRAM[T](ts1, ts2)
          // (64, 32)
          Foreach(ts1 by 1, ts2 par ip){ (ii,jj) => outTile(ii, jj) = b1(ii) * b2(jj) } // 2
          out(i::i+ts1, j::j+ts2 par ip) store outTile
        }
      }
    }
    out
  }

  def main(args: Array[String]): Unit = {
    val a = Array.tabulate(M) { i => i }
    val b = Array.tabulate(N) { i => i }

    val out = outerproduct(a, b)
    val gold = (0 :: M, 0 :: N) { (i,j) => a(i) * b(j) }
    //println(s"Result:")
    //printMatrix(getMatrix(out))
    //println(s"Gold:")
    //printMatrix(gold)
    val gold_cksum = gold.flatten.map(a => a).reduce{_+_}
    val result_cksum = getMem(out).map(a => a).reduce{_+_}
    println("expected cksum: " + gold_cksum)
    println("result cksum:   " + result_cksum)
    // (0 until M*N) foreach { i => assert(result(i) == gold(i)) }

    val cksum = result_cksum == gold_cksum
    println("PASS: " + cksum + " (OuterProduct)")
    assert(cksum)

  }
}

