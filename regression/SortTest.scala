import spatial.dsl._
import utils.math.{log2}

case class InitSort_0() extends InitSort
case class InitSort_1() extends InitSort(ip=16)
case class InitSort_2() extends InitSort(ip=16, N=128, op=2)
case class InitSort_3() extends InitSort(ip=16, N=128, op=3)
case class InitSort_4() extends InitSort(ip=16, nway=4, N=128)

@spatial abstract class InitSort(
  N:scala.Int = 64,
  op:scala.Int = 1,
  ip:scala.Int = 1,
  nway:scala.Int = 2
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val dram = DRAM[T](N)

    val ways = scala.List.tabulate(nway) { i => i }

    val bs = nway * ip

    Accel {
      val mergeSize = ip
      Sequential.Foreach(N by bs par op) { i =>
        val mergeBuf = MergeBuffer[T](nway, ip)
        val insram = SRAM[T](bs) // Initially in reverse order
        Foreach(0 until bs) { j =>
          insram(j) = bs - 1 - j
        }
        mergeBuf.init(true)
        ways.foreach { w =>
          mergeBuf.bound(w, mergeSize)
          Foreach(0 until mergeSize par ip) { j =>
            mergeBuf.enq(w, insram(mergeSize*w + j))
          }
        }
        val fifo = FIFO[T](bs)
        Foreach(0 until bs par ip) { j =>
          fifo.enq(mergeBuf.deq())
        }
        dram(i::i+bs par ip) store fifo
      }
    }

    val result = getMem(dram)
    printArray(result)

    val goldSum = (N by bs) { i =>
      (0 until bs) { j => j }.reduce { _ + _ }
    }.reduce { _ + _ }

    val sum = result.reduce { _ + _ }

    val ordered = (0 until N by ip) { i =>
      (0 until ip) { j => 
        val idx = i + j
        val prev = idx - 1
        if (j > 0) result(idx) >= result(prev) else true
      }.reduce { _ & _ }
    }.reduce { _ & _ }

    val cksum = ordered & (goldSum === sum)
    
    println("PASS: " + cksum + " (InitSort)")
    assert(cksum)
  }
}

case class DRAMMergeSort_0() extends DRAMMergeSort
case class DRAMMergeSort_1() extends DRAMMergeSort(ip=16)
case class DRAMMergeSort_2() extends DRAMMergeSort(ip=16, op=2)
case class DRAMMergeSort_3() extends DRAMMergeSort(ip=16, op=3)
case class DRAMMergeSort_4() extends DRAMMergeSort(ip=16, op=1, nway=4, N=256)
case class DRAMMergeSort_5() extends DRAMMergeSort(ip=16, op=2, nway=4, N=256)
case class DRAMMergeSort_6() extends DRAMMergeSort(ip=16, op=3, nway=4, N=256)

@spatial abstract class DRAMMergeSort(
  N:scala.Int = 64,
  op:scala.Int = 1, // Outer loop par
  ip:scala.Int = 16, // inner loop vectorization
  nway:scala.Int = 2,
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val ways = scala.List.tabulate(nway) { i => i }

    val in = Seq.tabulate(2*N) { i => IfElse(i < N) { N-1-i } { 0 } }

    val dram = DRAM[T](2*N)
    setMem(dram, Array.fromSeq(in.map { _.to[T] }))
    
    val iters = math.ceil(math.log(N / ip) / math.log(nway)).toInt + 1

    // j = i - 1 // iteration without init
    //
    // ms[1] = ip
    // ms[2] = ip * ways
    // ms[j] = ms[j-1] * ways
    // ms[j] = ms[j-2] * (ways ^ 2)
    // ms[j] = ms[1] * (ways ^ (j-1)) = ip * (ways ^ (i-2))
    // ms[j] = ip * 2 ^ (log2(ways) + (i-2))
    //

    Accel {
      Sequential.Foreach(iters by 1) { i =>
        val ms = if (i <= 1) ip else ip.to[Int] << (i + (-2 + log2(nway))).to[I16] //  ip * 2^(i-2) * ways
        val bs = ms * nway
        Foreach(N by bs par op) { t =>
          val mergeBuf = MergeBuffer[T](nway, ip)
          mergeBuf.init(i == 0)
          val blockStart = (i%2 * N) + t
          ways.foreach { w =>
            mergeBuf.bound(w, ms)
            val fifo = FIFO[T](16)
            val start = blockStart + (ms * w)
            fifo alignload dram(start::start+ms par ip)
            Foreach(0 until ms par ip) { j =>
              mergeBuf.enq(w, fifo.deq)
            }
          }
          val storeFIFO = FIFO[T](16)
          Foreach(0 until bs par ip) { j =>
            val sorted = mergeBuf.deq()
            storeFIFO.enq(sorted)
          }
          val start2 = ((i+1)%2 * N) + t
          dram(start2::start2+bs par ip) alignstore storeFIFO
        }
      }
    }

    val gold = Array.tabulate(N) { i => i.to[T] }
    val result = getMem(dram)
    println("iters :" + iters)
    printArray(result)
    val start = if (iters % 2 == 0) 0 else N
    val cksum = (0 until N) { i => result(start+i) === gold(i) }.reduce { _ && _ }
    assert(cksum)

  }
}

@spatial class MergeBufferTest extends SpatialTest {
  type T = Int

  val N = 64
  val ip = 16
  val op = 1

  def main(args: Array[String]): Unit = {
    val dram = DRAM[T](N)

    val nway = 2
    val ways = scala.List.tabulate(nway) { i => i }

    val bs = nway * ip

    Accel {
      val mergeSize = ip
      Foreach(N by bs par op) { i =>
        val mergeBuf = MergeBuffer[T](nway, ip)
        val insram = SRAM[T](bs) // Initially in reverse order
        Foreach(0 until bs) { j =>
          insram(j) = bs - 1 - j
        }
        mergeBuf.init(true)
        ways.foreach { w =>
          mergeBuf.bound(w, mergeSize)
          Foreach(0 until mergeSize par ip) { j =>
            mergeBuf.enq(w, insram(mergeSize*w + j))
          }
        }
        val fifo = FIFO[T](bs)
        Foreach(0 until bs par ip) { j =>
          fifo.enq(mergeBuf.deq())
        }
        dram(i::i+bs par ip) store fifo
      }
    }

    assert(true)
  }
}