import spatial.dsl._
import utils.math.{log2}
import spatial.lib.metaprogramming._

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
case class DRAMMergeSort_4() extends DRAMMergeSort(ip=16, op=4)
case class DRAMMergeSort_5() extends DRAMMergeSort(ip=16, op=2, nway=4)

@spatial abstract class DRAMMergeSort(
  op:scala.Int = 1, // Outer loop par
  ip:scala.Int = 16, // inner loop vectorization
  nway:scala.Int = 2,
) extends SpatialTest with MetaProgramming {
  type T = Int

  override def runtimeArgs = s"16"

  def main(args: Array[String]): Unit = {
    val N = 1.to[Int] << args(0).to[I16]
    //val iters = args(1).to[Int]
    val itersGold = (ln((N / ip).to[Float]) / math.log(nway)).to[Int] + 1
    println("iters:" + itersGold)

    val nArg = ArgIn[Int]
    val iterArg = ArgIn[Int]
    setArg(nArg, N)
    //setArg(iterArg, iters)
    setArg(iterArg,itersGold)

    val ways = scala.List.tabulate(nway) { i => i }
    val ops = scala.List.tabulate(op) { i => i }

    val in = Array.tabulate(2*N) { i => if(i < N) N-1-i else 0  }

    val dram = DRAM[T](2*N)
    setMem(dram, in)
    
    // 2^16: 7 iter

    // j = i - 1 // iteration without init
    //
    // ms[1] = ip
    // ms[2] = ip * ways
    // ms[j] = ms[j-1] * ways
    // ms[j] = ms[j-2] * (ways ^ 2)
    // ms[j] = ms[1] * (ways ^ (j-1)) = ip * (ways ^ (i-2))
    // ms[i-1] = ip * 2 ^ (log2(ways) * (i-2))
    // ms[i] = ip * 2 ^ (log2(ways) * (i-1))
    
    //val iterArg = ArgIn[Int]
    //setArg(iterArg, args(0).to[Int])

    Accel {
      Foreach(iterArg.value by 1) { i =>
        val ms = if (i <= 1) ip else ip.to[Int] << (log2(nway) * (i-1)).to[I16]
        val bs = ms * nway

        val fifos = ways.map { w => FIFOs[T](op, 16) }
        ForeachWithLane(nArg.value by bs par op) { (t,lane) =>
          val blockStart = (i%2 * nArg.value) + t
          ways.foreach { w =>
            val loadFIFO = FIFO[T](16)
            val start = blockStart + (ms * w)
            loadFIFO alignload dram(start::start+ms par ip)
            Foreach(0 until ms par ip) { i =>
              fifos(w).enq(lane,loadFIFO.deq)
            }
          }
        }
        ForeachWithLane(nArg.value by bs par op) { (t,lane) =>
          val mergeBuf = MergeBuffer[T](nway, ip)
          mergeBuf.init(i == 0)
          ways.foreach { w =>
            mergeBuf.bound(w, ms)
            Foreach(0 until ms par ip) { j =>
              val fifoRead = fifos(w).deq(lane)
              mergeBuf.enq(w, fifoRead)
            }
          }
          val storeFIFO = FIFO[T](16)
          Foreach(0 until bs par ip) { j =>
            val sorted = mergeBuf.deq()
            storeFIFO.enq(sorted)
          }
          val start2 = ((i+1)%2 * nArg.value) + t
          dram(start2::start2+bs par ip) alignstore storeFIFO
        }

      }
    }

    val result = getMem(dram)
    writeCSV1D(result, "out.csv")
    //printArray(result)

    val sortedStart = if (itersGold % 2 == 0) 0 else N

    val cksum = (0 until N) { i => result(sortedStart + i) === i }.reduce { _ && _ }
    assert(cksum)

  }
}

//def log2(i:Int) = (math.log(i) / math.log(2)).toInt
//val ip = 16
//val iters = 4

//def calcIter(N:Int, ip:Int, nway:Int) = {
    //val iters = math.ceil(math.log(N / ip) / math.log(nway)).toInt + 1
      //println("iters:" + iters)
        //(0 until iters).foreach { i =>
            //val ms = if (i <= 1) ip else ip << (i-1) * log2(nway)
            //println(ms)
          //}
          
//}

//calcIter(N=256, ip=16, nway=2)
//calcIter(N=256, ip=16, nway=4)
//calcIter(N=1024, ip=16, nway=2)
//calcIter(N=1024, ip=16, nway=4)
