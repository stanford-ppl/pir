import spatial.dsl._

class SimpleLock_0 extends SimpleLock
class SimpleLock_1 extends SimpleLock(P=16)

@spatial abstract class SimpleLock(
  d:scala.Int = 14,
  N:scala.Int = 64,
  P:scala.Int = 1,
) extends SpatialTest {
  def main(args: Array[String]): Unit = {
    val result = DRAM[I32](d)
    Accel{
      val lockSRAM = LockSRAM[I32](d).buffer // Buffer because w - w/r accesses in pipeline
      val lockSRAMUnit = Lock[I32](P)

      Sequential.Foreach(4 by 1 par 1) { i =>

        Foreach(d by 1) { j => lockSRAM(j) = 0 } // W1
        Foreach(N by 1 par P) { j =>
          val addr = j % d
          val id = addr // % 5

          val lock = lockSRAMUnit.lock(id) 
          val old: I32 = lockSRAM(addr, lock) // R1
          val next: I32 = old + j
          lockSRAM(addr, lock) = next // W2
        }

      }
      result(0::d) store lockSRAM // R2

    }

    val goldSeq = List.tabulate(N){j => j}.grouped(d).toList.reduce { (a,b) => 
      List.tabulate(math.max(a.size,b.size)) { j => a(j) + b.lift(j).getOrElse(0) }
    }
    val gold = Array.fromSeq[I32](goldSeq.map { _.to[I32] })

    assert(checkGold(result, gold))
  }
}

class OuterLock_0 extends OuterLock(op=1)
class OuterLock_1 extends OuterLock(op=2)

@spatial abstract class OuterLock(
  d:scala.Int = 14,
  N:scala.Int = 128,
  ts:scala.Int = 32,
  op:scala.Int = 2,
  ip:scala.Int = 16,
) extends SpatialTest {
  def main(args: Array[String]): Unit = {
    val result = DRAM[I32](d)
    Accel{
      val lockSRAM = LockSRAM[I32](d).buffer // Buffer because w - w/r accesses in pipeline
      val lockSRAMUnit = Lock[I32](op)

      Sequential.Foreach(4 by 1 par 1) { i =>
        Foreach(d by 1) { j => lockSRAM(j) = 0 } // W1
        Foreach(N by ts par op) { j =>
          Foreach(ts by 1 par ip) { k =>
            val addr = (j + k) % d
            val id = addr // % 5

            val lock = lockSRAMUnit.lock(id) 
            val old = lockSRAM(addr, lock) // R1
            val next = old + j + k
            lockSRAM(addr, lock) = next // W2
          }
        }
      }
      result(0::d) store lockSRAM // R2
    }

    val goldSeq = List.tabulate(N){j => j}.grouped(d).toList.reduce { (a,b) => 
      List.tabulate(math.max(a.size,b.size)) { j => a(j) + b.lift(j).getOrElse(0) }
    }
    val gold = Array.fromSeq[I32](goldSeq.map { _.to[I32] })

    assert(checkGold(result, gold))
  }
}
