import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit,Bus}
import utils.io.files._

class StreamDotProduct_0 extends StreamDotProduct[scala.Int,Int]
class StreamDotProduct_1 extends StreamDotProduct[scala.Int,Int](op=2)
class StreamDotProduct_2 extends StreamDotProduct[scala.Int,Int](ip=4)
class StreamDotProduct_3 extends StreamDotProduct[scala.Int,Int](ip=4, op=2)
class StreamDotProduct_4 extends StreamDotProduct[scala.Int,Int](ip=8, op=4)

@spatial abstract class StreamDotProduct[HT:Numeric,T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val op:scala.Int = 1,
  val ip:scala.Int = 8
)(implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  val weights = Seq.tabulate(field) { i => i }

  def hostBody(inData:Seq[Seq[HT]]) = {
    inData.map { fields => fields.zip(weights).map { case(f,w) => tonum(f)*w }.reduce { _ + _ } }
  }

  def accelBody(insram:SRAM2[T]):SRAM1[T] = { 
    val wLUT = LUT.fromSeq[T](weights.map { _.to[T] })
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par op) { b =>
      val dot = Reg[T]
      Reduce(dot)(0 until field par ip) { f =>
        insram(b,f) * wLUT(f)
      } { _ + _ }
      outsram(b) = dot.value
    }
    outsram
  }

}
