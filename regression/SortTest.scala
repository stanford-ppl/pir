import spatial.dsl._

case class InitSort_0() extends InitSort
case class InitSort_1() extends InitSort(ip=16)

@spatial abstract class InitSort(
  N:scala.Int = 64,
  ts:scala.Int = 32,
  ip:scala.Int = 1
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val dram = DRAM[T](N)

    val nway = 2
    val ways = scala.List.tabulate(nway) { i => i }

    Accel {
      val mergeBuf = MergeBuffer[T](nway, ip)
      val mergeSize = ts / nway
      Sequential.Foreach(N by ts) { i =>
        val insram = SRAM[T](ts) // Initially in reverse order
        Foreach(0 until ts) { j =>
          insram(j) = ts - j
        }
        mergeBuf.init(true)
        ways.foreach { w =>
          mergeBuf.bound(w, mergeSize)
        }
        ways.foreach { w =>
          Foreach(0 until mergeSize par ip) { j =>
            mergeBuf.enq(w, insram(mergeSize*w + j))
          }
        }
        val fifo = FIFO[T](ts)
        Foreach(0 until ts) { j =>
          fifo.enq(mergeBuf.deq())
        }
        dram(i::i+ts par ip) store fifo
      }
    }

    val gold = (0 until N by ts) { i => (0 until ts) { i => i } }.flatten
    
    val cksum = checkGold(dram, gold)
    println("PASS: " + cksum + " (InitSort)")
    assert(cksum)
  }
}


