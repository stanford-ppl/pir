import spatial.dsl._
import utils.io.files._

class UnalignParTest_0 extends UnalignParTest(op2=3)
class UnalignParTest_1 extends UnalignParTest(op2=2)
class UnalignParTest_2 extends UnalignParTest(ips1=5)
class UnalignParTest_3 extends UnalignParTest(op2=3,ips1=5)

@spatial abstract class UnalignParTest(
  M:scala.Int = 64,
  N:scala.Int = 64,
  ts1:scala.Int = 16,
  ts2:scala.Int = 16,
  ip:scala.Int = 16,
  ips1:scala.Int = 16,
  ips2:scala.Int = 1,
  ip1:scala.Int = 16,
  ip2:scala.Int = 1,
  op1:scala.Int = 1,
  op2:scala.Int = 1
) extends SpatialTest { // Regression (Dense) // Args: 640 640

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
          out(i::i+ts1 par ips2, j::j+ts2 par ips1) store outTile
        }
      }
    }
    out
  }

  def main(args: Array[String]): Unit = {
    val a = Array.tabulate(M) { i => i }
    val b = Array.tabulate(N) { i => i }

    val out = outerproduct(a, b)
    val gold = (0 :: M, 0 :: N) { (i,j) => a(i) * b(j) }.flatten
    val result = getMem(out)

    writeCSV1D(gold, buildPath(IR.config.genDir, "tungsten", "gold.csv"),delim="\n")
    writeCSV1D(result, buildPath(IR.config.genDir, "tungsten", "out.csv"),delim="\n")
    val cksum = approxEql(result, gold, 0)
    println("PASS: " + cksum)
    assert(cksum)

  }
}


class UnalignStore_0 extends UnalignStore(ip=5)

@spatial abstract class UnalignStore(
  N:scala.Int = 64,
  ts:scala.Int = 16,
  ip:scala.Int = 16,
  op:scala.Int = 1,
) extends SpatialTest { // Regression (Dense) // Args: 640 640

  type T = Int

  def main(args: Array[String]): Unit = {

    val out = DRAM[T](N)

    Accel {
      Foreach(N by ts par op){ i =>
        val outTile = SRAM[T](ts)
        Foreach(ts par ip){ ii => outTile(ii) = i + ii } // 2
        out(i::i+ts par ip) store outTile
      }
    }
    
    val gold = (0::N) { i => i }
    val result = getMem(out)

    writeCSV1D(gold, buildPath(IR.config.genDir, "tungsten", "gold.csv"),delim="\n")
    writeCSV1D(result, buildPath(IR.config.genDir, "tungsten", "out.csv"),delim="\n")
    val cksum = approxEql(result, gold, 0)
    println("PASS: " + cksum)
    assert(cksum)

  }
}

