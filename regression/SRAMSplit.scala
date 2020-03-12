import spatial.dsl._
import spatial.lib.ML._

class SRAMSplit_0 extends SRAMSplit
class SRAMSplit_1 extends SRAMSplit(ip=16, op=1, N=4*64*1024)
class SRAMSplit_2 extends SRAMSplit(ip=16, op=3, N=4*64*1024)
class SRAMSplit_3 extends SRAMSplit(ip=16, mp=2, op=3, N=8*64*1024)
class SRAMSplit_4 extends SRAMSplit(ip=16, mp=2, op=3, N=8*64*1024) {
  override def pirArgs = super.pirArgs + " --bcread"
}
class SRAMSplit_5 extends SRAMSplit(ip=16, op=1, N=1024)

@spatial abstract class SRAMSplit(
  N:scala.Int = 4*64*1024,
  ts:scala.Int = 64,
  op:scala.Int = 1,
  mp:scala.Int = 1,
  ip:scala.Int = 1
) extends SpatialTest {
  type T = Int

  def main(args: Array[String]): Unit = {
    val out = ArgOut[T]

    Accel {
      val sram = SRAM[T](N)
      Foreach(N by ts par op) { i =>
        Foreach(ts by 1 par ip) { ii =>
          sram(i+ii) = i + ii
        }
      }
      Reduce(out)(N by 1 par op){i =>
        val acc = Reg[T]
        Reduce(acc)(mp by 1 par mp) { j =>
          val accI = Reg[T]
          Reduce(accI)(ip by 1 par ip) { k =>
            sram(i)
          } { _ + _ }
        } { max(_,_) }
        acc.value
      }{_+_}
    }

    val gold = (0 until N) { i => i }.reduce{_+_}

    val cksum = checkGold(out, gold)
    println("PASS: " + cksum + " (SRAMSplit)")
    assert(cksum)
  }
}

