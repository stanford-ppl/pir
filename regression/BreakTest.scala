import spatial.dsl._

@spatial class BreakTest extends SpatialTest {
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

@spatial class BreakTest2 extends SpatialTest {
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

//@spatial class Deadlock extends SpatialTest {
  //def main(args: Array[String]): Unit = {
    //val out = ArgOut[Int]
    //Accel{
      //val stop = Reg[Bit](false)
      //val fifo = FIFO[Bit](2)
      //Foreach(0 until 10) { i =>
        //fifo.enq(i==11)
      //}
      //Stream(breakWhen=stop).Foreach(*) { i =>
        //val stopWhen = fifo.deq
        //out.write(i, stopWhen)
        //stop := stopWhen
      //}
    //}
    //val o = getArg(out)
    //println("out: " + o)
    //val cksum = o == 9
    //println("PASS: " + cksum + " (BreakTest)")
    //assert(cksum)
  //}
//}

//@spatial class Deadlock2 extends SpatialTest {
  //def main(args: Array[String]): Unit = {
    //val N = 1000
    //val out = ArgOut[Int]
    //Accel{
      //val fifo1 = FIFO[Int](2)
      //val fifo2 = FIFO[Int](16)
      //Foreach(0 until N) { i =>
        //fifo1.enq(i)
        //fifo2.enq(i)
      //}
      //val accum = Reg[Int]
      //Reduce(accum)(0 until N) { i =>
        //fifo1.deq
      //} { _ + _ }
      //Reduce(out)(0 until N) { i =>
        //accum.value + fifo2.deq
      //} { _ + _ }
    //}
    //val o = getArg(out)
    //val gold = List.tabulate(N) { i => i }.sum * (N+1)
    //println("out: " + o)
    //println("gold: " + gold)
    //val cksum = o == gold
    //println("PASS: " + cksum + " (Deadlock2)")
    //assert(cksum)
  //}
//}
