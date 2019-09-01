import spatial.dsl._

class OuterProduct_0 extends OuterProduct
class OuterProduct_1 extends OuterProduct(ip=1)
class OuterProduct_2 extends OuterProduct(ip=1, op2=2)
class OuterProduct_3 extends OuterProduct(ip=1, op1=2)
class OuterProduct_4 extends OuterProduct(ip=1, op1=2, op2=2)
class OuterProduct_5 extends OuterProduct(ip=16, op1=2, op2=2)
class OuterProduct_6 extends OuterProduct(ip=16, op2=1, ip1=2, ip2=8)
class OuterProduct_7 extends OuterProduct(ip=16, op2=2, ip1=2, ip2=8)
class OuterProduct_8 extends OuterProduct(ip=16, op2=2, ip1=4, ip2=4)
class OuterProduct_9 extends OuterProduct(op2=3)
class OuterProduct_10 extends OuterProduct(op1=3)
class OuterProduct_11 extends OuterProduct(op1=3,op2=3)

@spatial abstract class OuterProduct(
  M:scala.Int = 256,
  N:scala.Int = 256,
  ts1:scala.Int = 64,
  ts2:scala.Int = 32,
  ip:scala.Int = 16,
  ip1:scala.Int = 16,
  ip2:scala.Int = 1,
  op1:scala.Int = 1,
  op2:scala.Int = 1
) extends DSETest with SpatialTest { // Regression (Dense) // Args: 640 640

  type X = Int


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
          Foreach(ts1 par ip2, ts2 par ip1){ (ii,jj) => outTile(ii, jj) = b1(ii) * b2(jj) } // 2
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

    val cksum = checkGold(out, gold, 0)
    println("PASS: " + cksum + " (OuterProduct)")
    assert(cksum)

  }
}

