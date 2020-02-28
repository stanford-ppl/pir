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

    val N = 65536
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseDRAM[T](8)(16*N)
      Reduce(out)(N by ts par 8) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(7*(i+j), i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.barrierRead(7*(i+j), Seq(forwardBarrier.pop, backwardBarrier.push))
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
  val N = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      val sram = SRAM[U512](N)
      Foreach(N by 1) { i =>
        sram(i) = i.to[U512]
      }
      Reduce(out)(N by 1) { i =>
        val mask = sram(i)
        Reduce(Reg[T])(Scan(mask)) { j =>
          j.to[T]
        } { _ + _ }
      } { _ + _ }
    }

    assert(true)
    //val cksum = checkGold[T](out, gold)
    //println("PASS: " + cksum + " (SimpleScan)")
    //assert(cksum)
  }
}
