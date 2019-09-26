import spatial.dsl._
import spatial.lib.ML._

@spatial class SharedLoader extends SpatialTest {
  type T = Int

  val N:scala.Int = 32
  val ip:scala.Int = 1

  def main(args: Array[String]): Unit = {
    val in = (0 until N) { i => i }
    val dram = DRAM[T](N)
    setMem(dram, in)
    val argOut = ArgOut[T]
    Accel {
      val fifos = scala.List.tabulate(N) { k => FIFO[T](16) }
      val loadFIFO = FIFO[T](16)
      loadFIFO load dram(0 :: N par ip)
      Foreach(N par ip) { i =>
        val v = loadFIFO.deq
        fifos.zipWithIndex.foreach { case (fifo,k) => fifo.enq(v, i==k)}
      }
      val sum = fifos.map { fifo => fifo.deq }.reduce { _ + _ }
      argOut := sum
    }
    val gold = in.reduce { _ + _ }
    val cksum = checkGold(argOut, gold)
    println("PASS: " + cksum + " (SharedLoader)")
    assert(cksum)
  }
}


@spatial class SharedLoader2 extends SpatialTest {
  type T = Int

  val N:scala.Int = 32
  val ip:scala.Int = 1

  def main(args: Array[String]): Unit = {
    val in = (0 until N) { i => i }
    val dram = DRAM[T](N)
    setMem(dram, in)
    val argOut = ArgOut[T]
    Accel {
      val fifos = scala.List.tabulate(N) { k => FIFO[T](16) }
      val loadFIFO = FIFO[T](16)
      loadFIFO load dram(0 :: N par ip)
      fifos.zipWithIndex.grouped(4).foreach { fifos =>
        Foreach(N par ip) { i =>
          val v = loadFIFO.deq
          fifos.foreach { case (fifo,k) => fifo.enq(v, i==k)}
        }
      }
      val sum = fifos.map { fifo => fifo.deq }.reduce { _ + _ }
      argOut := sum
    }
    val gold = in.reduce { _ + _ }
    val cksum = checkGold(argOut, gold)
    println("PASS: " + cksum + " (SharedLoader)")
    assert(cksum)
  }
}


