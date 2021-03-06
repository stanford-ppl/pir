import spatial.dsl._
import scala.reflect._

class StreamSumFields_0 extends StreamSumFields[scala.Int,Int]()()
class StreamSumFields_1 extends StreamSumFields[scala.Int,Int]()(op=2)

@spatial abstract class StreamSumFields[HT:Numeric:ClassTag,T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
)(
  val op:scala.Int = 1,
  val ipb:scala.Int = math.min(16,batch),
  val ipf:scala.Int = math.min(16,field)
)(implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  def accelBody(insram:SRAM2[T]) = {
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par op) { b =>
      val dot = Reg[T]
      Reduce(dot)(0 until field par ipf) { f =>
        insram(b,f)
      } { _ + _ }
      outsram(b) = dot.value
    }
    outsram
  }

  def hostBody(inDataMat:Seq[Seq[HT]]) = {
    inDataMat.map { _.reduce { _ + _ } }
  }
}
