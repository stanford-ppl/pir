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
case class DRAMMergeSort_4() extends DRAMMergeSort(ip=16, op=4)
//case class DRAMMergeSort_4() extends DRAMMergeSort(ip=16, op=1, nway=4, N=256)
//case class DRAMMergeSort_5() extends DRAMMergeSort(ip=16, op=2, nway=4, N=256)
//case class DRAMMergeSort_6() extends DRAMMergeSort(ip=16, op=3, nway=4, N=256)
//case class DRAMMergeSort_7() extends DRAMMergeSort(ip=16, op=4, nway=4, N=1024)

@spatial abstract class DRAMMergeSort(
  op:scala.Int = 1, // Outer loop par
  ip:scala.Int = 16, // inner loop vectorization
  nway:scala.Int = 2,
) extends SpatialTest {
  type T = Int

  override def runtimeArgs = s"10"

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
        val fifos = ops.map { op => ways.map { w => FIFO[T](16) } }
        val msArg = Reg[Int]
        val bsArg = Reg[Int]
        val obs = Reg[Int]
        val ofst = ops.map { op => Reg[Int] }
        Pipe {
          val ms =  if (i <= 1) ip else ip.to[Int] << (log2(nway) * (i-1)).to[I16]
          val bs = ms * nway
          msArg := ms
          bsArg := bs
          obs := bs * op
          ops.foreach { o => ofst(o) := o * bs }
        }
        Parallel {
          ops.foreach { o =>
            Foreach(ofst(o).value until nArg.value by obs.value) { t =>
              val blockStart = (i%2 * nArg.value) + t
              ways.foreach { w =>
                val start = blockStart + (msArg.value * w)
                fifos(o)(w) alignload dram(start::start+msArg.value par ip)
              }
            }
          }
        }
        Parallel {
          ops.foreach { o =>
            Foreach(ofst(o).value until nArg.value by obs.value) { t =>
              val mergeBuf = MergeBuffer[T](nway, ip)
              mergeBuf.init(i == 0)
              ways.foreach { w =>
                mergeBuf.bound(w, msArg.value)
                Foreach(0 until msArg.value par ip) { j =>
                  mergeBuf.enq(w, fifos(o)(w).deq)
                }
              }
              val storeFIFO = FIFO[T](16)
              Foreach(0 until bsArg.value par ip) { j =>
                val sorted = mergeBuf.deq()
                storeFIFO.enq(sorted)
              }
              val start2 = ((i+1)%2 * nArg.value) + t
              dram(start2::start2+bsArg.value par ip) alignstore storeFIFO
            }
          }
        }
      }
    }

    val result = getMem(dram)
    writeCSV1D(result, "out.csv")
    //printArray(result)

    val itersEven = (itersGold % 2 == 0)
    val sortedStart = if (itersEven) 0 else N
    val doubleStart = if (itersEven) N else 0

    val sortedBuffer = Array.tabulate(N) { i => result(sortedStart + i) }
    val doubleBuffer = Array.tabulate(N) { i => result(doubleStart + i) }

    println("sorted:")
    printArray(sortedBuffer)
    println("double:")
    printArray(doubleBuffer)

    val cksum = (0 until N) { i => sortedBuffer(i) === i }.reduce { _ && _ }
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
//
//@spatial class FIFOTest extends SpatialTest { self =>

  //type X = Float

  //val N:scala.Int = 32
  //val op:scala.Int = 2
  //val ip:scala.Int = 8

  //val margin = 1

  //def main(args: Array[String]): Unit = {
    //val dram = DRAM[Int](N)
    //Accel {
      //val sram = SRAM[Int](N)
      //val fifo = FIFO[Int](16)
      //Foreach(N by 1 par op) { ii =>
        //Foreach(N par ip) { jj =>
          //fifo.enq(jj)
        //}
      //}
      //Foreach(N by 1 par op) { ii =>
        //Foreach(N par ip) { jj =>
          //sram(ii + jj) = fifo.deq
        //}
      //}
      //dram(0::N) store sram
    //}

    //assert(true)
  //}

//}

