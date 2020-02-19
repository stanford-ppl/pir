import spatial.dsl._

@spatial class SimpleSparse extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

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
        val backwardBarrier = Barrier[Token](1) // Always 1 because sparse memory is not multi-buffered
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
