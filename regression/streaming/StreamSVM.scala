import spatial.dsl._
import utils.io.files._
import spatial.lib.ML._

class StreamSVM_0 extends StreamSVM[scala.Float,Float]
class StreamSVM_1 extends StreamSVM[scala.Float,Float](op=2)
class StreamSVM_3 extends StreamSVM[scala.Float,Float](ip=4)
class StreamSVM_4 extends StreamSVM[scala.Float,Float](ip=4, op=2)
class StreamSVM_5 extends StreamSVM[scala.Float,Float](ip=8, op=4)

// Given a set of centroids, compute the closest centroid and output centroid indices for a stream of
// records
@spatial abstract class StreamSVM[HT:Fractional,T:Arith:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val op:scala.Int = 1,
  val vp:scala.Int = 1,
  val ip:scala.Int = 8,
  val kernel:java.lang.String = "inner", // options: "inner", "poly"
  val kernel_c:HT = 3, // polynomial kernel parameters
  val kernel_d:scala.Int = 2, // polynomial kernel parameters
)(implicit ev:Cast[HT,T]) extends StreamInference[HT,T,Bit] {

  val r = scala.util.Random
  val vectors:Seq[(Seq[HT],scala.Boolean)] = Seq.tabulate(8) { i => 
    val vec = Seq.tabulate(field) { f => r.nextFloat }
    val bool = r.nextBoolean
    (vec,bool)
  }
  val alphas:Seq[HT] = Seq.tabulate(vectors.size) { i =>
    r.nextFloat
  }
  val b:HT = r.nextFloat

  def hostBody(inData:Seq[Seq[HT]]) = {
    val K = kernel match {
      case "inner" => unstaged_inner_kernel[HT] _
      case "poly" => unstaged_polynomial_kernel[HT](kernel_c,kernel_d) _
    }
    inData.map { record =>
      unstaged_SVMR_infer[HT](alphas,b,vectors,K)(record)
    }
  }

  def accelBody(insram:SRAM2[T]):SRAM1[Bit] = { 
    val n = num[HT]
    import n._
    val aLUT = LUT.fromSeq[T](alphas.map { _.to[T] })
    val vecX = LUT.fromSeq[T](vectors.map { _._1.map { _.to[T] } })
    val vecY = LUT.fromSeq[T](vectors.map { case (vec,true) => (one).to[T]; case (vec,false) => (-one).to[T] })
    val outsram = SRAM[Bit](batch)
    Foreach(0 until batch par op) { ib =>
      outsram(ib) = SVMC_infer[T](vectors.size, vp, b.to[T]){ v => 
        val K = kernel match {
          case "inner" => inner_kernel[T](field, ip) { f => (insram(ib,f), vecX(v,f))}
          case "poly" => polynomial_kernel[T](kernel_c.to[T], kernel_d, field, ip) { f => (insram(ib,f), vecX(v,f))}
        }
        (aLUT(v), vecY(v),K)
      }
    }
    outsram
  }

}
