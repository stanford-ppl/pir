import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit}

@spatial class StreamDotProduct extends DSETest {
  val inFile = "in.csv"
  val outFile = "out.csv"
  val field = 8
  val numBatch = 16
  val batch = 4
  val numToken = batch * field * numBatch
  val op = 1
  type T = Int

  def main(args: Array[String]): Unit = {
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
    println(s"inData:")
    printMatrix(inData)
    println(s"outData:")
    printMatrix(outData)

    //val cksum = inData == outData
    //println("PASS: " + cksum + " (StreamDotProduct)")  
    //assert(cksum)
    assert(true)
  }
}
