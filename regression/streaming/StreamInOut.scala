import spatial.dsl._
import spatial.lang.{FileBus,FileEOFBus}

@spatial class StreamInOutStop extends DSETest {
  val inFile = "in.csv"
  val outFile = "out.csv"
  val numToken = 10
  def main(args: Array[String]): Unit = {
    val inData = Matrix.tabulate(numToken,2) { (i,j) =>
      if (j == 0) random[Int](numToken) else (i==numToken-1).to[Int]
    }
    writeCSV2D(inData, inFile)
    val in  = StreamIn[Tup2[Int,Bit]](FileEOFBus[Tup2[Int,Bit]](inFile))
    val out = StreamOut[Tup2[Int,Bit]](FileEOFBus[Tup2[Int,Bit]](outFile))
    Accel(*){
      out := in.value
    }
    val outData = loadCSV2D[Int](outFile)
    println(s"inData:")
    printMatrix(inData)
    println(s"outData:")
    printMatrix(outData)
    val cksum = inData == outData
    println("PASS: " + cksum + " (StreamInOutStop)")  
    assert(cksum)
  }
}
