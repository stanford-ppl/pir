import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit}
import utils.io.files._

trait StreamTemplateParam{
  val field:scala.Int
  val numBatch:scala.Int
  val batch:scala.Int
}

@spatial trait StreamTemplate extends DSETest {
  val param:StreamTemplateParam
  import param._

  type T = Int
  type TT = scala.Int
  def N = numBatch * batch
  def numToken = N * field

  def accelBody(insram:SRAM2[T]):SRAM1[T]
  // inDataMat in size numBatch x field x batch
  // return outMat in size numBatch x batch
  def hostBody(inDataMat:Seq[Seq[Seq[TT]]]):Seq[Seq[TT]]

  def main(args: Array[String]): Unit = {
    val inFile = "in.csv"
    val outFile = "out.csv"
    val goldFile = "gold.csv"

    val r = scala.util.Random
    val inDataOnly = Seq.tabulate(numToken) { i => r.nextInt(numToken) }
    val inData = Seq.tabulate(numToken,2) { (i,j) =>
      if (j == 0) inDataOnly(i) else if (i==numToken-1) 1 else 0
    }
    val inDataMat = inDataOnly.grouped(batch).toSeq.grouped(field).toSeq
    val goldMat = hostBody(inDataMat) 
    val goldFlat = goldMat.flatten
    val gold = Seq.tabulate(N, 2) { (i,j) => if (j==0) goldFlat(i) else if (i==(N-1)) 1 else 0 }
    writeCSVNow2D(gold, goldFile)
    writeCSVNow2D(inData, inFile)

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
        val outsram = accelBody(insram)
        Foreach(0 until batch par batch) { b =>
          out := Tup2(outsram(b), lastBit.deq)
        }
      }
    }
    val outData = loadCSV2D[Int](outFile)
    val cksum = outData == loadCSV2D[T](goldFile)
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }
}

