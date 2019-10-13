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
class UnalignStore_1 extends UnalignStore(ip=5, op=3)

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

class UnalignLoad_0 extends UnalignLoad(ip=5, op=1)
class UnalignLoad_1 extends UnalignLoad(ip=5, op=3)

@spatial abstract class UnalignLoad(
  N:scala.Int = 64,
  ts:scala.Int = 16,
  ip:scala.Int = 16,
  op:scala.Int = 1,
) extends SpatialTest { // Regression (Dense) // Args: 640 640

  type T = Int

  def main(args: Array[String]): Unit = {

    val aIn = Array.tabulate(N){ i => i.to[T] }
    val a = DRAM[T](N)
    val out = ArgOut[T]
    setMem(a, aIn)

    Accel {
      val accO = Reg[T](0.to[T])
      out := Reduce(accO)(N by ts par op){i =>
        val aBlk = SRAM[T](ts)
        aBlk load a(i::i+ts par ip)
        val sum = Reg[T]
        Reduce(sum)(ts par ip) { i => aBlk(i) } { _ + _ }
        sum.value
      }{_+_}
    }
    
    val gold = (0::N) { i => i }.reduce { _ + _ }

    val cksum = checkGold(out, gold)
    println("PASS: " + cksum)
    assert(cksum)

  }
}

@spatial class FIFOTest extends SpatialTest { self =>

  type X = Float

  val N:scala.Int = 32
  val ts = 16
  val op:scala.Int = 2
  val ip:scala.Int = 8

  val margin = 1

  def main(args: Array[String]): Unit = {
    val dram = DRAM[Int](N)
    Accel {
      val sram = SRAM[Int](N)
      val fifos = Seq.tabulate(op) { o => FIFO[Int](16) }
      Foreach(N by ts*op) { ii =>
        Foreach(ts*op by ts par op) { kk =>
          Foreach(ts par ip) { jj =>
            fifos.zipWithIndex.foreach { case (fifo, o) =>
              fifo.enq(ii+kk+jj, kk/ts==o)
            }
          }
        }
      }
      Foreach(N by ts*op) { ii =>
        Foreach(ts*op by ts par op) { kk =>
          Foreach(ts par ip) { jj =>
            val v = fifos.zipWithIndex.map { case (fifo, o) =>
              val valid = kk/ts == o
              mux(valid,fifo.deq(valid),0)
            }.reduce { _ + _ }
            sram(ii+kk+jj) = v
          }
        }
      }
      dram(0::N) store sram
    }

    val cksum = checkGold(dram, (0::N) { i => i })
    assert(cksum)
  }

}

