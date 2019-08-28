import spatial.dsl._

@spatial class BreakTest extends DSETest {
  def main(args: Array[String]): Unit = {
    val out = ArgOut[Int]
    Accel{
      val stop = Reg[Bit](false)
      Stream(breakWhen=stop).Foreach(*) { i =>
        val stopWhen = i == 10
        if (stopWhen) {
          stop := true
          out := i
        }
      }
    }
    val o = getArg(out)
    println("out: " + o)
    val cksum = o == 10
    println("PASS: " + cksum + " (BreakTest)")
    assert(cksum)
  }
}

@spatial class BreakTest2 extends DSETest {
  def main(args: Array[String]): Unit = {
    val out = ArgOut[Int]
    Accel{
      val stop = Reg[Bit](false)
      val fifo = FIFO[Bit](2)
      Foreach(0 until 10) { i =>
        fifo.enq(i==9)
      }
      Stream(breakWhen=stop).Foreach(*) { i =>
        val stopWhen = fifo.deq
        out.write(i, stopWhen)
        stop := stopWhen
      }
    }
    val o = getArg(out)
    println("out: " + o)
    val cksum = o == 9
    println("PASS: " + cksum + " (BreakTest)")
    assert(cksum)
  }
}
