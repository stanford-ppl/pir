import spatial.dsl._
import spatial.lib.ML._

class BankEnable_0 extends BankEnable

@spatial abstract class BankEnable(
  N:scala.Int = 32,
  ip:scala.Int = 16
) extends SpatialTest {
  type T = Float

  def main(args: Array[String]): Unit = {
    val in = Seq.tabulate(N) { i => i.to[T] }
    val dram = DRAM[T](N)
    Accel {
      val accO = Reg[T](0.to[T])
      val lut = LUT.fromSeq(in)
      val blk = SRAM[T](N)
      Foreach(N par ip) { i =>
        val en = i%2 == 0
        blk.write(lut(i), Seq(i), Set(en))
      }
      dram store blk
    }

    val gold = in.zipWithIndex.map { 
      case (e, i) if i%2==0 => e
      case (e, i) => 0.to[T]
    }
    
    val cksum = checkGold(dram, Array.fromSeq(gold))
    println("PASS: " + cksum + " (BankEnable)")
    assert(cksum)
  }
}

