import spatial.dsl._

case class DRAMDoubleBuffer_0() extends DRAMDoubleBuffer
case class DRAMDoubleBuffer_1() extends DRAMDoubleBuffer(ip=16)
case class DRAMDoubleBuffer_2() extends DRAMDoubleBuffer(ip=16, op=2)
case class DRAMDoubleBuffer_3() extends DRAMDoubleBuffer(ip=16, op=3)

@spatial abstract class DRAMDoubleBuffer(
  N:scala.Int = 64,
  ts:scala.Int = 16,
  iters:scala.Int=4,
  op:scala.Int = 1,
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
        Sequential.Foreach(N by ts par op) { j =>
          val fifoLoad1 = FIFO[T](ts)
          val fifoLoad2 = FIFO[T](ts)
          val first = i%2 == 0
          val second = !first
          if (first) {
            fifoLoad1 load dram1(j::j+ts par ip)
          } else {
            fifoLoad2 load dram2(j::j+ts par ip)
          }
          val storeFIFO1 = FIFO[T](ts)
          val storeFIFO2 = FIFO[T](ts)
          Foreach(ts by 1 par ip) { k =>
            val loaded = mux(first, fifoLoad1.deq(first), fifoLoad2.deq(second))
            val toStore = k.to[T] + j.to[T] + loaded 
            storeFIFO1.enq(toStore, first)
            storeFIFO2.enq(toStore, second)
          }
          if (first) {
            dram2(j::j+ts par ip) store storeFIFO1
          } else {
            dram1(j::j+ts par ip) store storeFIFO2
          }
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

case class DRAMDoubleBuffer2_0() extends DRAMDoubleBuffer2
case class DRAMDoubleBuffer2_1() extends DRAMDoubleBuffer2(ip=1)
case class DRAMDoubleBuffer2_2() extends DRAMDoubleBuffer2(ip=1,op=2)
case class DRAMDoubleBuffer2_3() extends DRAMDoubleBuffer2(ip=1,op=3)
case class DRAMDoubleBuffer2_4() extends DRAMDoubleBuffer2(ip=16,op=3)
@spatial abstract class DRAMDoubleBuffer2(
  N:scala.Int = 64,
  ts:scala.Int = 16,
  iters:scala.Int=4,
  op:scala.Int = 1,
  ip:scala.Int = 1
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val inArray = Array.fill(N) { 0.to[T] }
    val dram = DRAM[T](2*N)
    setMem(dram, inArray)
    Accel {
      Sequential.Foreach(iters by 1) { i =>
        Foreach(N by ts par op) { j =>
          val fifoLoad = FIFO[T](ts)
          val start = (i%2 * N) + j
          fifoLoad load dram(start::start+ts par ip)
          val storeFIFO = FIFO[T](ts)
          Foreach(ts by 1 par ip) { k =>
            val loaded = fifoLoad.deq()
            val toStore = k.to[T] + j.to[T] + loaded 
            storeFIFO.enq(toStore)
          }
          val start2 = ((i+1)%2 * N) + j
          dram(start2::start2+ts par ip) store storeFIFO
        }
      }
    }

    val gold = Array.tabulate(N) { i => i * iters }
    val result = getMem(dram)
    printArray(result)
    
    val start = if (iters % 2 == 0) 0 else N
    val cksum = (0 until N) { i => result(start+i) === gold(i) }.reduce { _ && _ }
    println("PASS: " + cksum + " (DRAMDoubleBuffer2)")
    assert(cksum)
  }
}
