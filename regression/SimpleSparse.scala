import spatial.dsl._

@spatial class SimpleSparse extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  // init in barrier is the number of initial tokens in the barrier buffers. If set to x, the accesses reading
  // (popping) the barrier can proceed x times before waiting on the writer (pusher). 
  // The location where the barrier is declared determines when the token is pushed/popped
  // In this example the forward barrier force the read to wait for write every ts iterations.
  // The backwardBarrier forces the write of i+1 iteration to wait on read of previous
  // iteration to prevent overriding the data before they get read. SparseSRAMs are not
  // multi-buffered so init=1.
  // If the barrier is declared outside the loop N by ts, the read waits the writer runs finishes
  // the entire (N by ts) and (ts by 1) loop before proceed, which in this case produce incorrect
  // result.
  
  def main(args: Array[String]): Unit = {

    val N = 128
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseSRAM[T](ts)
      Reduce(out)(N by ts) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(j, i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.barrierRead(j, Seq(forwardBarrier.pop, backwardBarrier.push))
        } { _ + _ }
      } { _ + _ }
    }

    val gold = List.tabulate(N) { i => i }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleSparse)")
    assert(cksum)
  }
}

@spatial class SimpleSparseDRAM extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 1024
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseDRAM[T](2)(N)
      Reduce(out)(N by ts par 2) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(i+j, i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.barrierRead(i+j, Seq(forwardBarrier.pop, backwardBarrier.push))
        } { _ + _ }
      } { _ + _ }
    }

    val gold = List.tabulate(N) { i => i }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleSparseDRAM)")
    assert(cksum)
  }
}

@spatial class SimpleScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ts = 16
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out)(N by ts) { i =>
        val sram = SRAM[U32](ts)
        Foreach(ts by 1) { j =>
          sram(j) = (i+j).to[U32]
        }
        val fifo = FIFO[U32](16)
        Foreach(ts by 1 par ip) { j =>
          val mask = sram(j)
          fifo.enq(mask)
        }
        Reduce(Reg[T])(Scan(fifo.deq)) { j =>
          j.to[T]
        } { _ + _ }
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N, ts).map { i =>
      val mask = scala.collection.immutable.Range(0, ts).map{ j => 
        val bi = (i+j).toBinaryString
        val pad = "0" * (32 - bi.size) + bi
        pad.reverse
      }.reduce { _ + _ }
      Console.println(mask)
      val nonZero = mask.count { _ == '1' }
      val sum = mask.zipWithIndex.map { case (char, k) =>
        char match {
          case '1' => 
            Console.println(k)
            k 
          case _ => 0
        }
      }.sum
      Console.println(s"nonZero=$nonZero, partial sum $sum")
      sum
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleScan)")
    assert(cksum)
  }
}

// Spatial Bug: Using scan iterator == 0 in reduce is incorrect!
@spatial abstract class OuterScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ts = 16
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out)(N by ts) { i =>
        val sram = SRAM[U32](ts)
        Foreach(ts by 1) { j =>
          sram(j) = (i+j).to[U32]
        }
        val fifo = FIFO[U32](16)
        Foreach(ts by 1 par ip) { j =>
          val mask = sram(j)
          fifo.enq(mask)
        }
        Reduce(Reg[T])(Scan(fifo.deq)) { j =>
          Reduce(Reg[T])(10 by 1) { _ =>
            j.to[T]
          } { _ + _ }
        } { _ + _ }
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N, ts).map { i =>
      val mask = scala.collection.immutable.Range(0, ts).map{ j => 
        val bi = (i+j).toBinaryString
        val pad = "0" * (32 - bi.size) + bi
        pad.reverse
      }.reduce { _ + _ }
      Console.println(mask)
      val nonZero = mask.count { _ == '1' }
      val sum = mask.zipWithIndex.map { case (char, k) =>
        val idx = char match {
          case '1' => k
          case _ => 0
        }
        idx * 10
      }.sum
      Console.println(s"nonZero=$nonZero, partial sum $sum")
      sum
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (OuterScan)")
    assert(cksum)
  }
}


import spatial.metadata.memory.{Barrier => _,_}
@spatial class SparseDRAMAlias extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 32
    val ip = 16

    val dram = DRAM[T](N)
    //val out = ArgOut[T]
    setMem(dram, (0 until N) { i => i })

    Accel {
      // Test dense read/write and RMW
      val mem = SparseDRAM[T](1)(N)
      mem.alias = dram
      val barrier1 = Barrier[Token](0)
      Foreach(N by 1 par ip) { i =>
        mem.RMW(i,
          i.to[T],
          op = "add",
          order = "unordered",
          bs = Seq(barrier1.push))
      }
    }

    val gold = (0 until N) { i => i+i }

    val cksum = checkGold[T](dram, gold)
    println("PASS: " + cksum + " (SparseDRAMAlias)")
    assert(cksum)
  }
}

@spatial class SimpleRMW extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 32
    val ip = 16

    val dram = DRAM[T](N)
    val out = ArgOut[T]
    setMem(dram, (0 until N) { i => i })

    Accel {
      // Test dense read/write and RMW
      val mem = SparseSRAM[T](N)
      val fifo = FIFO[T](16)
      Foreach(N by 1 par ip) { i =>
        val elem = mem.RMW(i,
          i.to[T],
          op = "add",
          order = "unordered",
          bs = Seq())
        fifo.enq(elem)
      }
      Reduce(out)(N by 1 par ip) { i =>
        fifo.deq
      } { _ + _ }
    }

    val gold = (0 until N) { i => i+i }.reduce { _ + _ }

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleRMW)")
    assert(cksum)
  }
}
