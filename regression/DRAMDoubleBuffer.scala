import spatial.dsl._

case class DRAMDoubleBuffer_0() extends DRAMDoubleBuffer
case class DRAMDoubleBuffer_1() extends DRAMDoubleBuffer(ip=16)

@spatial abstract class DRAMDoubleBuffer(
  N:scala.Int = 16,
  iters:scala.Int=4,
  ip:scala.Int = 1
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val inArray = Array.fill(N) { 0.to[T] }
    val dram1 = DRAM[T](N)
    val dram2 = DRAM[T](N)
    setMem(dram1, inArray)
    setMem(dram2, inArray)
    Accel {
      Sequential.Foreach(iters by 1) { i =>
        val sramLoad1 = FIFO[T](N)
        val sramLoad2 = FIFO[T](N)
        val first = i%2 == 0
        val second = !first
        if (first) {
          sramLoad1 load dram1(0::N par ip)
        } else {
          sramLoad2 load dram2(0::N par ip)
        }
        val storeFIFO1 = FIFO[T](N)
        val storeFIFO2 = FIFO[T](N)
        Foreach(N by 1 par ip) { j =>
          val loaded = mux(first, sramLoad1.deq(first), sramLoad2.deq(second))
          val toStore = j.to[T] + loaded 
          storeFIFO1.enq(toStore, first)
          storeFIFO2.enq(toStore, second)
        }
        if (first) {
          dram2(0::N par ip) store storeFIFO1
        } else {
          dram1(0::N par ip) store storeFIFO2
        }
      }
    }

    val gold = Array.tabulate(N) { i => i * iters }
    print(getMem(dram2))
    
    val cksum = checkGold(dram1, gold)
    println("PASS: " + cksum + " (DRAMDoubleBuffer)")
    assert(cksum)
  }
}

