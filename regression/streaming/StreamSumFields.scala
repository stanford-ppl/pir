import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit}

case class StreamSumFieldsParam(
  field:scala.Int = 8,
  numBatch:scala.Int = 16,
  batch:scala.Int = 4,
  op:scala.Int = 1
) extends Param[StreamSumFieldsParam]

class StreamSumFields_0 extends StreamSumFields
class StreamSumFields_1 extends StreamSumFields { override lazy val param = StreamSumFieldsParam(op=2) }

@spatial abstract class StreamSumFields extends StreamTemplate {

  def body(insram:SRAM2[T], outsram:SRAM2[T]) = {
    Foreach(0 until batch par op) { b =>
      val dot = Reg[T]
      Reduce(dot)(0 until field par field) { f =>
        insram(b,f)
      } { _ + _ }
      outsram(b) = dot.value
    }
  }
}
