import spatial.dsl._

class StreamTrainKmeans_0 extends StreamTrainKmeans[scala.Int, Int]

@spatial abstract class StreamTrainKmeans[HT:Numeric, T:Bits](
  val K:scala.Int = 16, // Number of centroids
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val iters:scala.Int = 2,
  val op:scala.Int = 1,
  val kp:scala.Int = 1,
  val ip:scala.Int = 8
)(implicit ev:Cast[Text,T]) extends StreamTraining[HT, T, T] {
  def main(in:StreamIn[Tup2[T,Bit]], inDataMat:Seq[Seq[HT]]):Bit = {
    //val outFile = buildPath(IR.config.genDir, "tungsten", "out.csv")
    //val goldFile = buildPath(IR.config.genDir, "tungsten", "gold.csv")
    val dram = DRAM[T](4)
    Accel{
      val outsram = SRAM[T](4)
      val stop = Reg[Bit](false)
      Stream(breakWhen=stop).Foreach(*) { _ =>
        val (insram, lastBit) = transposeInput(in)
        stop := lastBit.deq

        //val newCents = SRAM[T](K,D)
        //Sequential.Foreach(iters by 1) { epoch =>
        //}

      }
      dram(0::4) store outsram
    }
    true
  }
}
