import spatial.dsl._

@spatial class DRAMCompression extends SpatialTest {
  type T = Int

  val N:scala.Int = 1024
  val op:scala.Int = 1
  val ts:scala.Int = 32
  val ip:scala.Int = 16

  def main(args: Array[String]): Unit = {
    val array = Array.tabulate(N){ i => i.to[T] }

    val a = DRAM[T](N)
    val out = ArgOut[T]
    setMem(a, array)
    compress(a, ts.to[I32])

    Accel {
      val accO = Reg[T](0.to[T])
      out := Reduce(accO)(N by ts par op){i =>
        val aBlk = SRAM[T](ts)
        Parallel {
          aBlk load a(i::i+ts par ip)
        }
        Reduce(Reg[T])(ts by 1 par ip) { j =>
          aBlk(j)
        } { _ + _ }
      }{_+_}
    }

    val gold = array.reduce{_+_}

    val cksum = checkGold(out, gold)
    println("PASS: " + cksum + " (DRAMCompression)")
    assert(cksum)
  }
}

@spatial class DRAMBWTest extends SpatialTest {
  type T = Int

  val N:scala.Int = 16*1024*1024
  val ip = 16

  def main(args: Array[String]): Unit = {
    val array = Array.tabulate(N){ i => i.to[T] }

    val a = DRAM[T](N)
    val out = ArgOut[T]
    setMem(a, array)

    Accel {
      val accO = Reg[T](0.to[T])
      val in = FIFO[T](16)
      in load a(0::N par ip)
      out := Reduce(accO)(N par ip){i =>
        in.deq
      }{_+_}
    }

    val gold = array.reduce{_+_}

    val cksum = checkGold(out, gold)
    println("PASS: " + cksum + " (DRAMBWTest)")
    assert(cksum)
  }
}
