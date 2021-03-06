import spatial.dsl._

@spatial class BroadcastTest extends SpatialTest { self =>

  type X = Float

  val N:scala.Int = 32
  val op:scala.Int = 2
  val ip:scala.Int = 8

  def vectest[T: Num](vector: Array[T]) = {

    val vDRAM = DRAM[T](N)
    val mDRAM = DRAM[T](N, N)
    setMem(vDRAM, vector)

    Accel {
      val vTile = SRAM[T](N)
      vTile load vDRAM(0 :: N par ip) // Load mu0
      val mTile = SRAM[T](N, N)
      Foreach(N by 1 par op) { ii =>
        Foreach(N par ip) { jj =>
          mTile(ii, jj) = vTile(ii)
        }
      }
      mDRAM(0 :: N, 0 :: N par ip) store mTile
    }

    getMem(mDRAM)
  }

  def main(args: Array[String]): Unit = {
    val vector = Array.tabulate(N) { i => i }
    val result = vectest(vector)

    val gold = Array.tabulate(N) { i => Array.tabulate(N) { j => vector(i) } }.flatten

    printArray(gold, "gold: ")
    printArray(result, "result: ")

    val cksum = approxEql(result, gold)
    println("PASS: " + cksum  + " (BroadcastTest)")
    assert(cksum)
  }

}

