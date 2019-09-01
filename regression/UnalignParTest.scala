import spatial.dsl._

class UnalignParTest_0 extends UnalignParTest(op2=3)
class UnalignParTest_1 extends UnalignParTest(op2=2)

@spatial abstract class UnalignParTest(
  M:scala.Int = 64,
  N:scala.Int = 64,
  ts1:scala.Int = 16,
  ts2:scala.Int = 16,
  ip:scala.Int = 16,
  ip1:scala.Int = 16,
  ip2:scala.Int = 1,
  op1:scala.Int = 1,
  op2:scala.Int = 1
) extends DSETest with SpatialTest { // Regression (Dense) // Args: 640 640

  type T = Int

  def outerproduct(a: Array[T], b: Array[T]) = {

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
        Foreach(0 until ts1 par ip) { ii =>
          b1(ii) = ii + i
        }
        Foreach(sizeB by ts2 par op2) { j =>
          val b2 = SRAM[T](ts2)
          Foreach(0 until ts2 par ip) { jj =>
            b2(jj) = jj + j
          }
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
    println("PASS: " + cksum)
    assert(cksum)

  }
}


