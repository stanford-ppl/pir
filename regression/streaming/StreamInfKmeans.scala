import spatial.dsl._
import utils.io.files._
import scala.reflect._

class StreamInfKmeans_0 extends StreamInfKmeans[scala.Int,Int]()()
class StreamInfKmeans_1 extends StreamInfKmeans[scala.Int,Int]()(opb=2)
class StreamInfKmeans_2 extends StreamInfKmeans[scala.Int,Int]()(opk=2)
class StreamInfKmeans_3 extends StreamInfKmeans[scala.Int,Int]()(ipf=4)
class StreamInfKmeans_4 extends StreamInfKmeans[scala.Int,Int]()(ipf=4, opk=2)
class StreamInfKmeans_6 extends StreamInfKmeans[scala.Int,Int]()(ipf=8, opk=4)
class StreamInfKmeans_7 extends StreamInfKmeans[scala.Int,Int]()(ipf=8, opk=4, opb=2)

// Given a set of centroids, compute the closest centroid and output centroid indices for a stream of
// records
@spatial abstract class StreamInfKmeans[HT:Numeric:ClassTag,T:Arith:Num](
  val K:scala.Int = 16, // Number of centroids
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
)(
  val opb:scala.Int = 1,
  val opk:scala.Int = 1,
  val ipb:scala.Int = math.min(16,batch),
  val ipf:scala.Int = math.min(field, 16),
  val ipk:scala.Int = math.min(K, 16),
) extends StreamInference[HT,T,Int] {

  val r = scala.util.Random
  val centroids = Seq.tabulate(K) { k => Seq.tabulate(field) { f => r.nextInt(numToken) } }

  def hostBody(inData:Seq[Seq[HT]]) = {
    inData.map { record =>
      centroids.zipWithIndex.minBy { case (cent, i) =>
        record.zip(cent).map { case (r,c) => 
          val d = num[HT].abs(tonum(r)-c)
          d * d
        }.sum
      }._2
    }
  }

  def accelBody(insram:SRAM2[T]):SRAM1[Int] = { 
    val cLUT = LUT.fromSeq[T](centroids.map { _.map { _.to[T] } })
    val outsram = SRAM[Int](batch)
    Foreach(0 until batch par opb) { b =>
      val dists = SRAM[T](K)
      Foreach(0 until K par opk) { k =>
        val dist = Reg[T]
        Reduce(dist)(0 until field par ipf) { f =>
          val d = Arith[T].abs(insram(b,f) - cLUT(k,f))
          d * d
        } { _ + _ }
        dists(k) = dist.value
      }
      val minDist = Reg[T]
      Reduce(minDist)(0 until K par ipk) { k => dists(k) } { Num[T].min(_,_) }
      val minIdx = Reg[Int]
      Reduce(minIdx)(0 until K par ipk) { k =>
        mux(dists(k) == minDist.value, k, K+1)
      } { min(_,_) }
      outsram(b) = minIdx.value
    }
    outsram
  }

}
