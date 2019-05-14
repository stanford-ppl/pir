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

@spatial abstract class StreamSumFields extends DSETest {
  val inFile = "in.csv"
  val outFile = "out.csv"

  lazy val param = StreamSumFieldsParam()

  def main(args: Array[String]): Unit = {
    import param._

    val N = numBatch * batch
    val numToken = N * field
    type T = Int

    val inData = Matrix.tabulate(numToken,2) { (i,j) =>
      if (j == 0) random[T](numToken) else (i==numToken-1).to[T]
    }
    writeCSV2D(inData, inFile)
    val in  = StreamIn[Tup2[T,Bit]](FileBusLastBit[Tup2[T,Bit]](inFile))
    val out = StreamOut[Tup2[T,Bit]](FileBusLastBit[Tup2[T,Bit]](outFile))
    val one = ArgIn[Int]
    setArg(one, 1)
    Accel(*){
      Foreach(0 until one) { _ =>
        val insram = SRAM[T](batch, field)
        val lastBit = FIFO[Bit](10)
        Foreach(0 until field) { f =>
          Foreach(0 until batch par batch) { b =>
            val token = in.value
            insram(b,f) = token._1
            lastBit.enq(token._2, f==field-1)
          }
        }
        val outsram = SRAM[T](batch)
        Foreach(0 until batch par op) { b =>
          val dot = Reg[T]
          Reduce(dot)(0 until field par field) { f =>
            insram(b,f)
          } { _ + _ }
          outsram(b) = dot.value
        }
        Foreach(0 until batch par batch) { b =>
          out := Tup2(outsram(b), lastBit.deq)
        }
      }
    }
    val outData = loadCSV2D[Int](outFile)

    val inDataOnly = Array.tabulate(numToken) { i => inData(i,0) }
    val inDataMat = inDataOnly.reshape(numBatch, field, batch)
    val goldMat = Matrix.tabulate(numBatch, batch) { (i, b) =>
      Array.tabulate(field) { f => inDataMat(i, f, b) }.reduce { _ + _ }
    }
    val goldFlat = goldMat.flatten
    val gold = Matrix.tabulate(N, 2) { (i,j) => if (j==0) goldFlat(i) else (i==(N-1)).to[Int] }
    println(s"inDataOnly:")
    printArray(inDataOnly)
    println(s"inDataMat:")
    printTensor3(inDataMat)
    println(s"inData:")
    printMatrix(inData)
    println(s"outData:")
    printMatrix(outData)
    println(s"gold:")
    printMatrix(gold)

    val cksum = outData == gold
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }
}
