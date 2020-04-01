import spatial.dsl._

class MemReduceTest_0 extends MemReduceTest
class MemReduceTest_1 extends MemReduceTest(op=2)
class MemReduceTest_2 extends MemReduceTest(op=3)

@spatial abstract class MemReduceTest(
  D:scala.Int = 1024,
  N:scala.Int = 100,
  op:scala.Int = 1,
) extends SpatialTest {

  type T = Int

  val ip = 16

  def main(args: Array[String]): Unit = {

    val accumDRAM = DRAM[T](D)
  
    Accel {
      val accum = SRAM[T](D)
      MemReduce(accum par ip)(N by 1 par op){ i =>
        val inner = SRAM[T](D)
        Foreach(0 until D) { j =>
          inner(j) = i
        }
        inner
      } { _ + _ }
  
      accumDRAM(0::D par ip) store accum
    }

    val gold = (0 until D) { j => List.tabulate(N) { i => i }.sum.to[Int] }

    val result = getMem(accumDRAM)

    printArray(gold, "gold: ")
    printArray(result, "result: ")

    val cksum = approxEql(result, gold)
    println("PASS: " + cksum  + " (MemReduceTest)")
    assert(cksum)

  }

}
