import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit}

case class StreamSumFieldsParam(
  field:scala.Int = 8,
  numBatch:scala.Int = 16,
  batch:scala.Int = 4,
  op:scala.Int = 1
) extends StreamTemplateParam

class StreamSumFields_0 extends StreamSumFields
class StreamSumFields_1 extends StreamSumFields { override lazy val param = StreamSumFieldsParam(op=2) }

@spatial abstract class StreamSumFields extends StreamTemplate {

  lazy val param = StreamSumFieldsParam()
  import param._

  def accelBody(insram:SRAM2[T]) = {
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par op) { b =>
      val dot = Reg[T]
      Reduce(dot)(0 until field par field) { f =>
        insram(b,f)
      } { _ + _ }
      outsram(b) = dot.value
    }
    outsram
  }

  def hostBody(inDataMat:Tensor3[T]):Matrix[T] = {
    Matrix.tabulate(numBatch, batch) { (i, b) =>
      Array.tabulate(field) { f => inDataMat(i, f, b) }.reduce { _ + _ }
    }
  }
}
