import spatial.dsl._

@spatial class BreakTest extends DSETest {
  def main(args: Array[String]): Unit = {
    val out = ArgOut[Int]
    Accel{
      val stop = Reg[Bit](false)
      Stream(breakWhen=stop).Foreach(*) { i =>
        stop := i == 10
        out := i
      }
    }
    val o = getArg(out)
    println("out: " + o)
    val cksum = o == 10
    println("PASS: " + cksum + " (BreakTest)")
    assert(cksum)
  }
}
