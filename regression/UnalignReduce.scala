import spatial.dsl._

class UnalignReduce_0 extends UnalignReduce(op=3, ip=5)

@spatial abstract class UnalignReduce(
  N:scala.Int = 64,
  ts1:scala.Int = 16,
  ip:scala.Int = 16,
  op:scala.Int = 1,
) extends SpatialTest {

  type T = Int

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]
    Accel {
      Reduce(out)(N by ts1 par op){ i =>
        val fifo = FIFO[T](10)
        val sram = SRAM[T](ts1)
        Foreach(0 until ts1 par ip) { ii =>
          sram(ii) = ii + i
          fifo.enq(ii)
        }
        val iacc = Reg[T]
        Reduce(iacc)(0 until ts1 par ip) { ii =>
          val a = sram(ii)
          val b = fifo.deq
          a + b
        } { _ + _ }
        iacc.value
      } { _ + _ }
    }

    val gold = (0 :: N) { i => 2.to[T] * i }.reduce { _ + _ }

    val cksum = checkGold(out, gold, 0)
    println("PASS: " + cksum)
    assert(cksum)

  }
}
