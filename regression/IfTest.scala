import spatial.dsl._

class IfTest_0 extends IfTest(op=1)

@spatial abstract class IfTest(
  N:scala.Int = 64,
  ts:scala.Int = 16,
  ip:scala.Int = 16,
  op:scala.Int = 1,
) extends DSETest with SpatialTest {

  type T = Int

  def main(args: Array[String]): Unit = {

    val out = DRAM[T](N)

    Accel {
      Foreach(N by ts par op){ i =>
        val sram1 = SRAM[T](ts)
        val sram2 = SRAM[T](ts)
        if ((i / ts % 2) == 0) {
          Foreach(0 until ts par ip) { ii =>
            sram1(ii) = 0.to[T]
          }
          out(i::i+ts) store sram1
        } else {
          Foreach(0 until ts par ip) { ii =>
            sram2(ii) = 1.to[T]
          }
          out(i::i+ts) store sram2
        }
      }
    }

    printArray(getMem(out))
    val cksum = getMem(out).reduce { _ + _ } == (N / 2)
    println("PASS: " + cksum)
    assert(cksum)

  }
}
