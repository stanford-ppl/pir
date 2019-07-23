import spatial.dsl._
import utils.io.files._
import scala.reflect._

class StreamInfDotProduct_0 extends StreamInfDotProduct[scala.Int,Int]()()
class StreamInfDotProduct_1 extends StreamInfDotProduct[scala.Int,Int]()(opb=2)
class StreamInfDotProduct_2 extends StreamInfDotProduct[scala.Int,Int]()(ipf=4)
class StreamInfDotProduct_3 extends StreamInfDotProduct[scala.Int,Int]()(ipf=4, opb=2)
class StreamInfDotProduct_4 extends StreamInfDotProduct[scala.Int,Int]()(ipf=8, opb=4)
class StreamInfDotProduct_5 extends StreamInfDotProduct[scala.Int,Int]()(transpose=false)

@spatial abstract class StreamInfDotProduct[HT:Numeric:ClassTag,T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
)(
  val opb:scala.Int = 1,
  val ipf:scala.Int = math.min(field, 16),
  val ipb:scala.Int = math.min(batch, 16),
  override val transpose:scala.Boolean = true,
)(implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  val weights = Seq.tabulate(field) { i => i }

  def hostBody(inData:Seq[Seq[HT]]) = {
    inData.map { fields => fields.zip(weights).map { case(f,w) => tonum(f)*w }.reduce { _ + _ } }
  }

  def accelBody(insram:SRAM2[T]):SRAM1[T] = { 
    val wLUT = LUT.fromSeq[T](weights.map { _.to[T] })
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par opb) { b =>
      val dot = Reg[T]
      Reduce(dot)(0 until field par ipf) { f =>
        insram(b,f) * wLUT(f)
      } { _ + _ }
      outsram(b) = dot.value
    }
    outsram
  }

  override def accelBody(in:StreamIn[Tup2[T,Bit]], out:StreamOut[Tup2[T,Bit]]) = {
    val wLUT = LUT.fromSeq[T](weights.map { _.to[T] })
    val lastBit = FIFO[Bit](10)
    Foreach(*) { _ =>
      val accum = SRAM[T](batch).buffer
      Foreach(0 until field) { f =>
        val w = wLUT(f)
        Foreach(0 until batch par ipb) { b =>
          val token = in.value
          val firstIter = f == 0
          val prod = token._1 * w
          accum(b) = mux(firstIter, prod, prod + accum(b))
          lastBit.enq(token._2, f==field-1)
        }
      }
      Foreach(0 until batch par ipb) { b =>
        out := Tup2(accum(b), lastBit.deq)
      }
    }
  }

}
