
import spatial.dsl._
import spatial.lib.ML._

class CountZero_0 extends CountZero

@spatial abstract class CountZero(
  N:scala.Int = 10,
  op:scala.Int = 1,
  ip:scala.Int = 1 
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val gold = Seq.tabulate(N) { i => Seq.tabulate(i) { j => j.to[T] } }.flatten

    val dram = DRAM[T](gold.size)

    Accel {
      val fifo = FIFO[T](20)
      Foreach(0 until N par op) { i =>
        Foreach(0 until i par ip) { j =>
          fifo.enq(j)
        }
      }
      val sram = SRAM[T](gold.size)
      Foreach(0 until gold.size) { k =>
        sram(k) = fifo.deq
      }
      dram(0 until gold.size) store sram
    }

    val cksum = checkGold(dram, Array.fromSeq[T](gold))
    println("PASS: " + cksum + " (CountZero)")
    assert(cksum)
  }
}

class VarDeq_0 extends VarDeq

@spatial abstract class VarDeq(
  N:scala.Int = 10,
  op:scala.Int = 1,
  ip:scala.Int = 1 
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val gold = (0 until N) { i => (0 until i%4) { i => i }.reduce { _ + _ } }
    val dram = DRAM[T](N)
    Accel {
      val sram = SRAM[T](N)
      Foreach(0 until N par op) { i =>
        val fifo = FIFO[T](20)
        val max = i % 4
        Foreach(0 until max par ip) { j =>
          fifo.enq(j)
        }
        val count = Reg[T]
        Reduce(count)(0 until max par ip) { j => 1 } { _ + _ }
        val sum = Reg[T]
        Reduce(sum)(0 until count.value par ip) { j =>
          fifo.deq
        } { _ + _ }
        sram(i) = sum.value
      }
      dram(0 until N) store sram
    }

    val cksum = checkGold(dram, gold)
    println("PASS: " + cksum + " (VarDeq)")
    assert(cksum)
  }
}
