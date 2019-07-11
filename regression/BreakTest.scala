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
