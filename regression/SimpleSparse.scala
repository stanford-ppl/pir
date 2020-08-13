import spatial.dsl._
import spatial.metadata.memory.{Barrier => _,_}

@spatial class SimpleBVBuild extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 64

    val dram = DRAM[U32](N)
    setMem(dram, (0 until N) { i => 0.to[U32] })

    Accel {
      // val len = FIFO[I32](16)
      val idx = FIFO[I32](16)

      val bv = FIFO[U32](16)
      val l = FIFO[Bit](16)

      // len.enq(5)
      Foreach (10 by 1 par 1) { i =>
        val base = mux(i >= 5, 1024.to[I32], 0)
        val off = i % 5
        idx.enq(base+2*off)
      }

      gen_bitvector(0, N*32, 10, idx, bv)

      // dram dynstore_vec(0, bv, l)
      dram(0::N) store bv
    }

    val gold = (0 until N) { i => 
      if (i == 0 || i == 32) {
        341.to[U32]
      } else {
        0.to[U32]
      }.to[U32]
    }

    val cksum = checkGold[U32](dram, gold)
    println("PASS: " + cksum + " (TestSimpleBV)")
    assert(cksum)
  }
}

@spatial class SimpleBVBuildTree extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 64

    val dram = DRAM[U32](N)
    setMem(dram, (0 until N) { i => 0.to[U32] })
    val prevdram = DRAM[I32](N)
    setMem(prevdram, (0 until N) { i => 0.to[I32] })

    Accel {
      // val len = FIFO[I32](16)
      val idx = FIFO[I32](16)

      val bv = FIFO[U32](16)
      val prev = FIFO[I32](16)
      val l = FIFO[Bit](16)
      val lA = FIFO[Bit](16)
      val lB = FIFO[Bit](16)
      val gen_len = FIFO[I32](16)

      // len.enq(5)
      Foreach (10 by 1 par 1) { i =>
        val base = mux(i >= 5, 2048.to[I32], 0)
        val off = i % 5
        idx.enq(base+2*off)
      }

      gen_bitvector_tree(0, 10, idx, bv, prev)
      gen_bitvector_tree_len(0, 10, idx, gen_len)
      //Stream (*) {
        //val x = l.deq
        //lA.enq(x)
        //lB.enq(x)
      //}

      dram(0::gen_len.deq*16) store bv
      prevdram(0::gen_len.deq*16) store prev
      // dram dynstore_vec(0, bv, l, storedA)
      // prevdram dynstore_vec(0, prev, l, storedB)
    }

    val gold = (0 until N) { i => 
      if (i == 0 || i == 16) {
        341.to[U32]
      } else {
        0.to[U32]
      }.to[U32]
    }
    val gold2 = (0 until N) { i => 
      if (i == 16) { 
        5.to[I32]
      } else {
        0.to[I32]
      }
    }

    val cksum = checkGold[U32](dram, gold)
    val cksum2 = checkGold[I32](prevdram, gold2)
    println("PASS: " + cksum + " (TestSimpleBVTree)")
    assert(cksum)
  }
}
/*
@spatial class SimpleDynStore extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 128

    val dram = DRAM[I32](N)
    setMem(dram, (0 until N) { i => 0.to[I32] })

    Accel {
      val d = FIFO[I32](16)
      val l = FIFO[Bit](16)
      val s = FIFO[Int](16)

      Foreach (N by 1 par 16) { i =>
        d.enq(i)
      }
      Foreach (N by 1 par 16) { i =>
        l.enq(i >= 32)
      }

      // dram coalesce(0, d, v, N)
      dram dynstore_vec(0, d, l, s)
    }

    val gold = (0 until N) { i => 
      if (i < 48) {
        i 
      } else {
        0
      }
    }

    val cksum = checkGold[T](dram, gold)
    println("PASS: " + cksum + " (TestCoalesce)")
    assert(cksum)
  }
} */

@spatial class TestCoalesce extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 128

    val dram = DRAM[I32](N/3)

    Accel {
      val d = FIFO[I32](16)
      val v = FIFO[Bit](16)

      Foreach (N by 1 par 1) { i =>
        d.enq(i)
        v.enq(i%3 == 0)
      }

      // dram coalesce(0, d, v, N)
      dram coalesce(0, d, v, N) // TESTING ONLY
    }

    val gold = (0 until N/3) { i => 3*i }

    val cksum = checkGold[T](dram, gold)
    println("PASS: " + cksum + " (TestCoalesce)")
    assert(cksum)
  }
}

@spatial class TestCoalesceVec extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 128

    val dram = DRAM[I32](N/3)

    Accel {
      val d = FIFO[I32](16)
      val v = FIFO[Bit](16)

      Foreach (N by 1 par 16) { i =>
        d.enq(i)
        v.enq(i%3 == 0)
      }

      dram coalesce_vec(0, d, v, N)
    }

    val gold = (0 until N/3) { i => 3*i }

    val cksum = checkGold[T](dram, gold)
    println("PASS: " + cksum + " (TestCoalesce)")
    assert(cksum)
  }
}

@spatial class TestCoalesceComputed extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 128

    val dram = DRAM[I32](N/3)

    val zero = 0
    val zeroArg = ArgIn[I32]
    setArg(zeroArg, zero.to[Int])

    Accel {
      val d = FIFO[I32](16)
      val v = FIFO[Bit](16)

      Foreach (N by 1 par 1) { i =>
        d.enq(i)
        v.enq(i%3 == 0)
      }

      val coalBase = Reg[I32](0)
      coalBase := zeroArg.value/(2*N)
      dram coalesce(coalBase.value, d, v, N)
    }

    val gold = (0 until N/3) { i => 3*i }

    val cksum = checkGold[T](dram, gold)
    println("PASS: " + cksum + " (TestCoalesce)")
    assert(cksum)
  }
}

@spatial class SparseAutoBarrierDRAM extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  
  def main(args: Array[String]): Unit = {

    val N = 4096
    val ip = 16

    val out = ArgOut[I32]
    val dram = DRAM[I32](N)
    setMem(dram, (0 until N) { i => 0.to[I32] })

    Accel {
      val sp = SparseDRAM[I32](2)(N)
      sp.alias = dram
      // val testBar = Barrier[I32oken](0)
      Reduce(out) (2 by 1 par 2) { ol =>
        Foreach (N/2 par ip) { i =>
          sp.barrierWrite(N*(ol+1)/2-i-1, 10, Seq())
        }
        Reduce(Reg[I32])(N/2 par ip) { i =>
          sp.barrierRead(i+(N*ol)/2, Seq())
        } { _ + _ }
      } { _ + _ }
    }

    val gold = N*10

    val cksum = checkGold[I32](out, gold)
    println("PASS: " + cksum + " (SparseAutoBarrierDRAM)")
    assert(cksum)
  }
}

@spatial class SparseAutoBarrier extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 4096
    val ip = 16

    val out = ArgOut[T]

    Accel {
      val s1 = SparseSRAM[T](N)
      // val testBar = Barrier[Token](0)
      Foreach (N par ip) { i =>
        s1.barrierWrite(N-i-1, 0, Seq())
      }
      Foreach (N par ip) { i =>
        s1.RMW(i, 1, "add", "unordered", Seq())
      }
      Reduce(out)(N par ip) { i =>
        s1.barrierRead(N-i-1, Seq())
      } { _ + _ }
    }

    val gold = N

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SparseAutoBarrier)")
    assert(cksum)
  }
}

@spatial class SparseAutoBarrierRev extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 4096
    val ip = 16

    val out = ArgOut[T]

    Accel {
      Reduce(out) (3 by 1) { j =>
        val s1 = SparseSRAM[T](N)
        // val testBar = Barrier[Token](0)
        Foreach (N par ip) { i =>
          s1.barrierWrite(N-i-1, 0, Seq())
        }
        Foreach (N par ip) { i =>
          s1.RMW(i, j, "add", "unordered", Seq())
        }
        Reduce(Reg[T])(N par ip) { i =>
          s1.barrierRead(N-i-1, Seq())
        } { _ + _ }
      } { _ + _ }
    }

    val gold = 3*N

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SparseAutoBarrierRev)")
    assert(cksum)
  }
}

@spatial class SparseAutoBarrierRevB extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int
  
  def main(args: Array[String]): Unit = {

    val N = 4096
    val ip = 16

    val out = ArgOut[T]

    Accel {
      val s1 = SparseSRAM[T](N)
      Reduce(out) (3 by 1) { j =>
        Foreach (N par ip) { i =>
          s1.barrierWrite(N-i-1, 0, Seq())
        }
        Foreach (N par ip) { i =>
          s1.RMW(i, j, "add", "unordered", Seq())
        }
        Reduce(Reg[T])(N par ip) { i =>
          s1.barrierRead(N-i-1, Seq())
        } { _ + _ }
      } { _ + _ }
    }

    val gold = 3*N

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SparseAutoBarrierRevB)")
    assert(cksum)
  }
}

@spatial class SimpleSparse extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  // init in barrier is the number of initial tokens in the barrier buffers. If set to x, the accesses reading
  // (popping) the barrier can proceed x times before waiting on the writer (pusher). 
  // The location where the barrier is declared determines when the token is pushed/popped
  // In this example the forward barrier force the read to wait for write every ts iterations.
  // The backwardBarrier forces the write of i+1 iteration to wait on read of previous
  // iteration to prevent overriding the data before they get read. SparseSRAMs are not
  // multi-buffered so init=1.
  // If the barrier is declared outside the loop N by ts, the read waits the writer runs finishes
  // the entire (N by ts) and (ts by 1) loop before proceed, which in this case produce incorrect
  // result.
  
  def main(args: Array[String]): Unit = {

    val N = 128
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseSRAM[T](ts)
      Reduce(out)(N by ts) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(j, i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.barrierRead(j, Seq(forwardBarrier.pop, backwardBarrier.push))
        } { _ + _ }
      } { _ + _ }
    }

    val gold = List.tabulate(N) { i => i }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleSparse)")
    assert(cksum)
  }
}

@spatial class SimpleSparseParSRAM extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 1024
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseParSRAM[T](2)(N, false)
      Reduce(out)(N by ts par 2) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(i+j, i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.barrierRead(i+j, Seq(forwardBarrier.pop, backwardBarrier.push))
        } { _ + _ }
      } { _ + _ }
    }

    val gold = List.tabulate(N) { i => i }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleSparseParSRAM)")
    assert(cksum)
  }
}

@spatial class SimpleSparseDRAM extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 1024
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseDRAM[T](2)(N)
      Reduce(out)(N by ts par 2) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(i+j, i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.barrierRead(i+j, Seq(forwardBarrier.pop, backwardBarrier.push))
        } { _ + _ }
      } { _ + _ }
    }

    val gold = List.tabulate(N) { i => i }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleSparseDRAM)")
    assert(cksum)
  }
}

@spatial class SparseParSRAM_SepRMW extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 1024
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseParSRAM[T](2)(N, false)
      Reduce(out)(N by ts par 2) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(i+j, i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Foreach (ts by 1 par ip) { j =>
          s1.RMW(i+j, 0, "read", "unordered", Seq(forwardBarrier.pop), 1)
        }

        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.RMWData(j, j, Seq(backwardBarrier.push), 1)
        } { _ + _ }
      } { _ + _ }
    }

    val gold = List.tabulate(N) { i => i }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SparseParSRAM_SepRMW)")
    assert(cksum)
  }
}

@spatial class SparseDRAM_SepRMW extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 1024
    val ts = 32
    val ip = 16

    val out = ArgOut[T]

    Accel {
      // Test dense read/write and RMW
      val s1 = SparseDRAM[T](2)(N)
      Reduce(out)(N by ts par 2) { i =>
        val forwardBarrier = Barrier[Token](0)
        val backwardBarrier = Barrier[Token](init=1) 
        Foreach(ts by 1 par ip) { j =>
          s1.barrierWrite(i+j, i+j, Seq(forwardBarrier.push, backwardBarrier.pop))
        }
        Foreach (ts by 1 par ip) { j =>
          s1.RMW(i+j, 0, "read", "unordered", Seq(forwardBarrier.pop), 1)
        }

        Reduce(Reg[T])(ts by 1 par ip) { j =>
          s1.RMWData(j, j, Seq(backwardBarrier.push), 1)
        } { _ + _ }
      } { _ + _ }
    }

    val gold = List.tabulate(N) { i => i }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SparseDRAM_SepRMW)")
    assert(cksum)
  }
}

@spatial class SimpleDataScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out)(N by ip) { i =>
        val sram = SRAM[I32](ip)
        Foreach(ip by 1) { j =>
          val x = i+j
          sram(j) = x*(x%3)
        }
        val fifo = FIFO[I32](16)
        Foreach(ip by 1 par ip) { j =>
          val mask = sram(j)
          fifo.enq(mask)
        }
        Reduce(Reg[T])(DataScan(16, fifo.deq)) { case List(idx, data) =>
          data
        } { _ + _ }
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N).map { i =>
      i*(i%3)
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleDataScan)")
    assert(cksum)
  }
}

@spatial class MultiDataScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      val fifo = FIFO[I32](16)
      Foreach (N par ip) { i =>
        fifo.enq((i)%3)
      }
      Reduce(out)(DataScan(N, fifo.deq)) { case List(idx, data) =>
        idx
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N).map { i =>
      val test = i%3
      test match {
        case 0 => 0
        case _ => i
      }
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (MultiDataScan)")
    assert(cksum)
  }
}

@spatial class ScanTestAND extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      val fifoA = FIFO[U32](16)
      val fifoB = FIFO[U32](16)
      Foreach(N par ip) { i =>
        fifoA.enq(i.to[U32])
        fifoB.enq(0xAAAA.to[U32])
      }
      Reduce(out)(Scan(16, N*32, "and", fifoA.deq, fifoB.deq)) { case List(a, iA, b, iB) =>
        a.to[T]
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N, ip).map { i =>
      val mask = scala.collection.immutable.Range(0, ip).map{ j => 
        val bi = ((i+j)&0xAAAA).toBinaryString
        val pad = "0" * (32 - bi.size) + bi
        pad.reverse
      }.reduce { _ + _ }
      Console.println(mask)
      val nonZero = mask.count { _ == '1' }
      val sum = mask.zipWithIndex.map { case (char, k) =>
        char match {
          case '1' => 
            Console.println(k+(i*32))
            k + (i*32)
          case _ => 0
        }
      }.sum
      Console.println(s"nonZero=$nonZero, partial sum $sum")
      sum
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (ScanTestAND)")
    assert(cksum)
  }
}

@spatial class ScanMultiVecIdx extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      val fifo = FIFO[U32](16)
      Foreach(N par ip) { i =>
        fifo.enq(i.to[U32])
      }
      Reduce(out)(Scan(1, N*32, "or", fifo.deq)) { case List(j, idx) =>
        idx.to[T]
      } { _ + _ }
    }

    val mask = scala.collection.immutable.Range(0, N).map { i =>
      val bi = (i).toBinaryString
      val pad = "0" * (32 - bi.size) + bi
      pad.reverse
    }.reduce { _ + _ }
    Console.println(mask)
    val nonZero = mask.count { _ == '1' }
    val gold = nonZero*(nonZero-1)/2
    Console.println(s"nonZero=$nonZero, sum $gold")

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (ScanMultiVecIdx)")
    assert(cksum)
  }
}

@spatial class ScanMultiVec extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      val fifo = FIFO[U32](16)
      Foreach(N par ip) { i =>
        fifo.enq(i.to[U32])
      }
      Reduce(out)(Scan(1, N*32, "or", fifo.deq)) { case List(j, xA) =>
        j.to[T]
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N, ip).map { i =>
      val mask = scala.collection.immutable.Range(0, ip).map{ j => 
        val bi = (i+j).toBinaryString
        val pad = "0" * (32 - bi.size) + bi
        pad.reverse
      }.reduce { _ + _ }
      Console.println(mask)
      val nonZero = mask.count { _ == '1' }
      val sum = mask.zipWithIndex.map { case (char, k) =>
        char match {
          case '1' => 
            Console.println(k+(i*32))
            k + (i*32)
          case _ => 0
        }
      }.sum
      Console.println(s"nonZero=$nonZero, partial sum $sum")
      sum
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleScan)")
    assert(cksum)
  }
}

@spatial class SimpleScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out)(N by ip) { i =>
        val sram = SRAM[U32](ip)
        Foreach(ip by 1) { j =>
          sram(j) = (i+j).to[U32]
        }
        val fifo = FIFO[U32](16)
        Foreach(ip by 1 par ip) { j =>
          val mask = sram(j)
          fifo.enq(mask)
        }
        Reduce(Reg[T])(Scan(1, 512, "or", fifo.deq)) { case List(j, xA) =>
          j.to[T]
        } { _ + _ }
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N, ip).map { i =>
      val mask = scala.collection.immutable.Range(0, ip).map{ j => 
        val bi = (i+j).toBinaryString
        val pad = "0" * (32 - bi.size) + bi
        pad.reverse
      }.reduce { _ + _ }
      Console.println(mask)
      val nonZero = mask.count { _ == '1' }
      val sum = mask.zipWithIndex.map { case (char, k) =>
        char match {
          case '1' => 
            Console.println(k)
            k 
          case _ => 0
        }
      }.sum
      Console.println(s"nonZero=$nonZero, partial sum $sum")
      sum
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleScan)")
    assert(cksum)
  }
}

// Spatial Bug: Using scan iterator == 0 in reduce is incorrect!
@spatial abstract class OuterScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out)(N by ip) { i =>
        val sram = SRAM[U32](ip)
        Foreach(ip by 1) { j =>
          sram(j) = (i+j).to[U32]
        }
        val fifo = FIFO[U32](16)
        Foreach(ip by 1 par ip) { j =>
          val mask = sram(j)
          fifo.enq(mask)
        }
        Reduce(Reg[T])(Scan(1, 512, "or", fifo.deq)) { case List(j, xA) =>
          Reduce(Reg[T])(10 by 1) { _ =>
            j.to[T]
          } { _ + _ }
        } { _ + _ }
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N, ip).map { i =>
      val mask = scala.collection.immutable.Range(0, ip).map{ j => 
        val bi = (i+j).toBinaryString
        val pad = "0" * (32 - bi.size) + bi
        pad.reverse
      }.reduce { _ + _ }
      Console.println(mask)
      val nonZero = mask.count { _ == '1' }
      val sum = mask.zipWithIndex.map { case (char, k) =>
        val idx = char match {
          case '1' => k
          case _ => 0
        }
        idx * 10
      }.sum
      Console.println(s"nonZero=$nonZero, partial sum $sum")
      sum
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (OuterScan)")
    assert(cksum)
  }
}

@spatial class SparseDRAMAlias_SepRMW extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 32
    val ip = 16

    val dram = DRAM[T](N)
    //val out = ArgOut[T]
    setMem(dram, (0 until N) { i => i })

    Accel {
      // Test dense read/write and RMW
      val mem = SparseDRAM[T](1)(N)
      mem.alias = dram
      Foreach(N by 1 par ip) { i =>
        mem.RMW(i,
          i.to[T],
          op = "add",
          order = "unordered",
          bs = Seq(),
          key = 1)
      }

      Foreach(N by 1 par ip) { i =>
        mem.RMWData(i,
          i.to[T],
          bs = Seq(),
          key = 1)
      }
    }

    val gold = (0 until N) { i => i+i }

    val cksum = checkGold[T](dram, gold)
    println("PASS: " + cksum + " (SparseDRAMAlias)")
    assert(cksum)
  }
}


@spatial class SparseDRAMAlias extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 32
    val ip = 16

    val dram = DRAM[T](N)
    //val out = ArgOut[T]
    setMem(dram, (0 until N) { i => i })

    Accel {
      // Test dense read/write and RMW
      val mem = SparseDRAM[T](1)(N)
      mem.alias = dram
      val barrier1 = Barrier[Token](0)
      Foreach(N by 1 par ip) { i =>
        mem.RMW(i,
          i.to[T],
          op = "add",
          order = "unordered",
          bs = Seq(barrier1.push))
      }
    }

    val gold = (0 until N) { i => i+i }

    val cksum = checkGold[T](dram, gold)
    println("PASS: " + cksum + " (SparseDRAMAlias)")
    assert(cksum)
  }
}

@spatial class ZeroScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      val fifo = FIFO[U32](4096)
      Foreach(N by 1) { i =>
        Foreach(ip by 1 par ip) { j =>
          val mask = (i+j % 10).to[U32]
          fifo.enq(mask)
        }
      }
      Reduce(out)(N by 1) { i =>
        Reduce(Reg[T])(Scan(1, 512, "or", fifo.deq)) { case List(j, xA) =>
          j.to[T]
        } { _ + _ }
      } { _ + _ }
    }

    val gold = scala.collection.immutable.Range(0, N).map { i =>
      val mask = scala.collection.immutable.Range(0, ip).map{ j => 
        val bi = (i+j % 10).toBinaryString
        val pad = "0" * (32 - bi.size) + bi
        pad.reverse
      }.reduce { _ + _ }
      Console.println(mask)
      val nonZero = mask.count { _ == '1' }
      val sum = mask.zipWithIndex.map { case (char, k) =>
        char match {
          case '1' => 
            Console.println(k)
            k 
          case _ => 0
        }
      }.sum
      Console.println(s"nonZero=$nonZero, partial sum $sum")
      sum
    }.sum

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (ZeroScan)")
    assert(cksum)
  }
}

@spatial class SimpleRMW extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 32
    val ip = 16

    val dram = DRAM[T](N)
    val out = ArgOut[T]
    setMem(dram, (0 until N) { i => i })

    Accel {
      // Test dense read/write and RMW
      val mem = SparseDRAM[T](1)(N)
      mem.alias = dram
      val fifo = FIFO[T](16)
      Foreach(N by 1 par ip) { i =>
        val elem = mem.RMW(i,
          i.to[T],
          op = "add",
          order = "unordered",
          bs = Seq())
        fifo.enq(elem)
      }
      Reduce(out)(N by 1 par ip) { i =>
        fifo.deq
      } { _ + _ }
    }

    val gold = (0 until N) { i => i+i }.reduce { _ + _ }

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleRMW)")
    assert(cksum)
  }
}

@spatial class OuterWrite extends SpatialTest {
  override def runtimeArgs: Args = "32"
  //type T = FixPt[TRUE, _16, _16]
  type T = Int

  def main(args: Array[String]): Unit = {

    val N = 64
    val ts = 32
    val ip = 16

    val dram = DRAM[T](N)
    val out = ArgOut[T]
    setMem(dram, (0 until N) { i => i })

    Accel {
      // Test dense read/write and RMW
      val sram = SRAM[T](N)
      Foreach(N by 1) { i =>
        Foreach(ts by ip) { j =>
          sram(i) = i
        }
      }
      Reduce(out)(N by 1){ i =>
        sram(i)
      } { _ + _ }
    }

    val gold = (0 until N) { i => i }.reduce { _ + _ }

    val cksum = checkGold[T](out, gold)
    println("PASS: " + cksum + " (SimpleRMW)")
    assert(cksum)
  }
}

@spatial class VecScan extends SpatialTest {
  override def runtimeArgs: Args = "32"
  type T = Int
  val N = 32
  val ip = 16

  def main(args: Array[String]): Unit = {

    val out = ArgOut[T]

    Accel {
      Reduce(out)(N by ip) { i =>
        val sram = SRAM[U32](ip)
        Foreach(ip by 1) { j =>
          sram(j) = (i+j).to[U32]
        }
        val fifo1 = FIFO[U32](16)
        val fifo2 = FIFO[U32](16)
        Foreach(ip by 1 par ip) { j =>
          val mask = sram(j)
          fifo1.enq(mask)
          // Error in VecScan if fifo1 and fifo2 are the same fifo. VecScan will pop both fifos even
          // though they have the same pointer
          fifo2.enq(mask + 1) 
        }
        Reduce(Reg[T])(Scan(16, 512, "or", fifo1.deq, fifo2.deq)) { case List(j,xA,k,xB) =>
          j.to[T] + k.to[T]
        } { _ + _ }
      } { _ + _ }
    }

    //println("PASS: " + cksum + " (SimpleScan)")
    println("out: " + getArg(out))
    assert(true)
  }
}
