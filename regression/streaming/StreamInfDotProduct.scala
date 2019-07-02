import spatial.dsl._
import utils.io.files._

class StreamInfDotProduct_0 extends StreamInfDotProduct[scala.Int,Int]
class StreamInfDotProduct_1 extends StreamInfDotProduct[scala.Int,Int](op=2)
class StreamInfDotProduct_2 extends StreamInfDotProduct[scala.Int,Int](ip=4)
class StreamInfDotProduct_3 extends StreamInfDotProduct[scala.Int,Int](ip=4, op=2)
class StreamInfDotProduct_4 extends StreamInfDotProduct[scala.Int,Int](ip=8, op=4)

@spatial abstract class StreamInfDotProduct[HT:Numeric,T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val op:scala.Int = 1,
  val ip:scala.Int = 8,
  val weightFile:Option[java.lang.String] = None
)(implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  val weights = Seq.tabulate(field) { i => i }

  def hostBody(inData:Seq[Seq[HT]]) = {
    inData.map { fields => fields.zip(weights).map { case(f,w) => tonum(f)*w }.reduce { _ + _ } }
  }

  def accelBody(insram:SRAM2[T]):SRAM1[T] = { 
    val wLUT = weightFile match {
      case Some(f) => LUT.fromFile[T](field)(f)
      case None => LUT.fromSeq[T](weights.map { _.to[T] })
    }

    val outsram = SRAM[T](batch)
    Foreach(0 until batch par op) { b =>
      val dot = Reg[T]
      Pipe {
      Reduce(dot)(0 until field par ip) { f =>
        insram(b,f) * wLUT(f)
      } { _ + _ }
      }
      // binary classifier
      outsram(b) = mux(dot.value > 0.to[T], 1.to[T], 0.to[T])
    }
    outsram
  }

}
