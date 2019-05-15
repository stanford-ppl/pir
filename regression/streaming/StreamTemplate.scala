import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit}

@spatial trait StreamTemplate extends DSETest {
  val inFile = "in.csv"
  val outFile = "out.csv"

  lazy val param = StreamSumFieldsParam()

  def body(insram:SRAM[T], outsram:SRAM[T])

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
    Accel{
      Foreach(*) { _ =>
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
        body(insram, outsram)
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

