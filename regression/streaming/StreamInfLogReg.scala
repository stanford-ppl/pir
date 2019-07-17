import spatial.dsl._
import utils.io.files._
import spatial.lib.ML._

class StreamInfLogReg_0 extends StreamInfLogReg[Float]()()
class StreamInfLogReg_1 extends StreamInfLogReg[Float]()(opb=2)
class StreamInfLogReg_2 extends StreamInfLogReg[Float]()(ipf=4)
class StreamInfLogReg_3 extends StreamInfLogReg[Float]()(ipf=4, opb=2)
class StreamInfLogReg_4 extends StreamInfLogReg[Float]()(ipf=8, opb=4)
class StreamInfLogReg_5 extends StreamInfLogReg[Int]()(ipf=8, opb=4)

@spatial abstract class StreamInfLogReg[T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
)(
  val opb:scala.Int = 1,
  val ipb:scala.Int = 8,
  val ipf:scala.Int = 8,
)(implicit ev:Cast[Text,T]) extends StreamInference[scala.Float,T,Bit] {

  val weights:Seq[scala.Float] = Seq.tabulate(field) { i => i }
  val bias:scala.Float = 3

  def hostBody(inData:Seq[Seq[scala.Float]]) = {
    inData.map { fields => 
      val dot = fields.zip(weights).map { case(f,w) => tonum(f)*w }.reduce { _ + _ }
      unstaged_sigmoid(dot + bias) > 0.5
    }
  }

  def accelBody(insram:SRAM2[T]):SRAM1[Bit] = { 
    val wLUT = LUT.fromSeq[T](weights.map { _.to[T] })
    val outsram = SRAM[Bit](batch)
    Foreach(0 until batch par opb) { b =>
      val dot = Reg[T]
      Reduce(dot)(0 until field par ipf) { f =>
        insram(b,f) * wLUT(f)
      } { _ + _ }
      outsram(b) = sigmoid(dot.value + bias.to[T]) > 0.5.to[T]
    }
    outsram
  }

}
