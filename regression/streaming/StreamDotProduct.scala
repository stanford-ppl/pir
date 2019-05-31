import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit,Bus}
import utils.io.files._

case class StreamDotProductParam(
  field:scala.Int = 8,
  numBatch:scala.Int = 16,
  batch:scala.Int = 4,
  op:scala.Int = 1,
  ip:scala.Int = 8
) extends StreamTemplateParam

class StreamDotProduct_0 extends StreamDotProduct[scala.Int,Int]
class StreamDotProduct_1 extends StreamDotProduct[scala.Int,Int] { override lazy val param = StreamDotProductParam(op=2) }
class StreamDotProduct_2 extends StreamDotProduct[scala.Int,Int] { override lazy val param = StreamDotProductParam(ip=4) }
class StreamDotProduct_3 extends StreamDotProduct[scala.Int,Int] { override lazy val param = StreamDotProductParam(ip=4, op=2) }
class StreamDotProduct_4 extends StreamDotProduct[scala.Int,Int] { override lazy val param = StreamDotProductParam(ip=8, op=4) }

@spatial abstract class StreamDotProduct[HT:Numeric,T:Num](implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  lazy val param = StreamDotProductParam()
  import param._

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
