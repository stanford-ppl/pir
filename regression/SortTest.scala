import spatial.dsl._

case class InitSort_0() extends InitSort
case class InitSort_1() extends InitSort(ip=16)
case class InitSort_2() extends InitSort(ip=16, N=128, op=2)
case class InitSort_3() extends InitSort(ip=16, N=128, op=3)

@spatial abstract class InitSort(
  N:scala.Int = 64,
  op:scala.Int = 1,
  ip:scala.Int = 1
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val dram = DRAM[T](N)

    val nway = 2
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
        }
        ways.foreach { w =>
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
case class DRAMMergeSort_1() extends DRAMMergeSort(ip=16, op=2)

@spatial abstract class DRAMMergeSort(
  N:scala.Int = 64,
  op:scala.Int = 1, // Outer loop par
  ip:scala.Int = 16 // inner loop vectorization
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val nway = 2
    val ways = scala.List.tabulate(nway) { i => i }

    val in = Seq.tabulate(N) { i => i }.reverse // reverse list

    val dram1 = DRAM[T](N)
    val dram2 = DRAM[T](N)
    setMem(dram1, Array.fromSeq(in.map { _.to[T] }))
    
    val initbs = ip * nway
    val iters = (math.log(N / ip) / math.log(nway)).toInt + 1 // Fix this for nway > 2

    Accel {
      val mergeBuf = MergeBuffer[T](nway, ip)
      Sequential.Foreach(iters by 1) { i =>
        //TODO: the left shift amount should be a function of ways as well to build the tree
        val bs = if (i <= 1) initbs else initbs.to[Int] << (i - 1).to[I16]
        val mergeSize = bs / nway
        val first = (i % 2 == 0)
        val second = !first
        Sequential.Foreach(N by bs par op) { t =>
          mergeBuf.init(i == 0)
          ways.foreach { w =>
            mergeBuf.bound(w, mergeSize)
          }
          val loadfifo1 = FIFO[T](16)
          val loadfifo2 = FIFO[T](16)
          if (first) {
            loadfifo1 alignload dram1(t::t+bs par ip)
          } else {
            loadfifo2 alignload dram2(t::t+bs par ip)
          }
          val fifos = ways.map { w => FIFO[T](16) }
          Foreach(0 until nway, 0 until mergeSize par ip) { (w, j) =>
            val loaded = mux(first, loadfifo1.deq(first), loadfifo2.deq(second))
            fifos.zipWithIndex.foreach { case (fifo, i) =>
              fifos(i).enq(loaded, w === i.to[Int])
            }
          }
          ways.foreach { w =>
            Foreach(0 until mergeSize par ip) { j =>
              mergeBuf.enq(w, fifos(w).deq)
            }
          }
          val storefifo1 = FIFO[T](16)
          val storefifo2 = FIFO[T](16)
          Foreach(0 until bs par ip) { j =>
            val sorted = mergeBuf.deq()
            storefifo1.enq(sorted, first)
            storefifo2.enq(sorted, second)
          }
          if (first) {
            dram2(t::t+bs par ip) alignstore storefifo1
          } else {
            dram1(t::t+bs par ip) alignstore storefifo2
          }
        }
      }
    }

    val gold = Array.tabulate(N) { i => i.to[T] }

    val output = IfElse(iters % 2 == 0) { dram1 } { dram2 } // Unstaged if statement
    
    assert(checkGold(output, gold))
  }
}
