import spatial.dsl._

@spatial class NestedScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 4096
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out) (2 by 1 par 1) { _ =>
        val itCount = SRAM[Int](N)
        Foreach (N by 1 par ip) { i =>
          itCount(i) = mux(i < 16, 1000, 500)
        }
        val fifo = FIFO[U32](16)
        Foreach(N by 1 par ip) { j =>
          val mask = mux(j == 0 || j == 32, 0xA.to[U32], 0.to[U32])
          fifo.enq(mask)
        }
        Reduce(Reg[T])(Scan(1, N, "or", fifo.deq)) { case List(j, xA) =>
          val n_it = itCount(j)
          Reduce(Reg[T])(n_it by 1 par 1) { i =>
            1.to[Int]
          } { _ + _ }
        } { _ + _ }
      } { _ + _ }
    }

    val gold = 6000

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (NestedScan)")
    assert(cksum)
  }
}

 /*@spatial class NestedDataScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 64
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out) (2 by 1 par 1) { _ =>
        val itCount = SRAM[Int](N)
        Foreach (N by 1 par ip) { i =>
          itCount(i) = mux(i < 16, 1000, 50)
        }
        val fifo = FIFO[I32](16)
        Foreach(N by 1 par ip) { j =>
          val mask = mux(j == 0 || j == 32, 0xA.to[I32], 0.to[I32])
          fifo.enq(mask)
        }
        Reduce(Reg[T])(DataScan(N, fifo.deq)) { case List(j, xA) =>
       // Reduce(out)(DataScan(N, fifo.deq)) { case List(j, xA) =>
          val n_it = itCount(j)
          Reduce(Reg[T])(n_it by 1 par 1) { i =>
            1.to[Int]
          } { _ + _ }
        } { _ + _ }
      } { _ + _ }
    }

    val gold = 2000

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (NestedDataScan)")
    assert(cksum)
  }
}
  */
