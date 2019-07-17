import spatial.dsl._
import utils.io.files._
import spatial.lib.ML._

class StreamInfSVM_0 extends StreamInfSVM[scala.Float,Float]()()
class StreamInfSVM_1 extends StreamInfSVM[scala.Float,Float]()(opb=2)
class StreamInfSVM_3 extends StreamInfSVM[scala.Float,Float]()(ipf=4)
class StreamInfSVM_4 extends StreamInfSVM[scala.Float,Float]()(ipf=4, opb=2)
class StreamInfSVM_5 extends StreamInfSVM[scala.Float,Float]()(ipf=8, opb=4)

// Given a set of centroids, compute the closest centroid and output centroid indices for a stream of
// records
@spatial abstract class StreamInfSVM[HT:Fractional,T:Arith:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val V:scala.Int = 8,
  val kernel:java.lang.String = "inner", // options: "inner", "poly"
  val kernel_c:HT = 3, // polynomial kernel parameters
  val kernel_d:scala.Int = 2, // polynomial kernel parameters
)(
  val opb:scala.Int = 1,
  val opv:scala.Int = 1,
  val ipf:scala.Int = 8,
  val ipb:scala.Int = 8,
)(implicit ev:Cast[HT,T]) extends StreamInference[HT,T,Bit] {

  val r = scala.util.Random
  val vectors:Seq[(Seq[HT],scala.Boolean)] = Seq.tabulate(V) { i => 
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
    Foreach(0 until batch par opb) { ib =>
      outsram(ib) = SVMC_infer[T](vectors.size, opv, b.to[T]){ v => 
        val K = kernel match {
          case "inner" => inner_kernel[T](field, ipf) { f => (insram(ib,f), vecX(v,f))}
          case "poly" => polynomial_kernel[T](kernel_c.to[T], kernel_d, field, ipf) { f => (insram(ib,f), vecX(v,f))}
        }
        (aLUT(v), vecY(v),K)
      }
    }
    outsram
  }

}
