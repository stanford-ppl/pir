import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit,BlackBoxBus}
import utils.io.files._

@spatial abstract class StreamTemplate extends StreamHostTemplate with DSETest {

  // Takes in input stream and put last bit into a FIFO,
  // transpose the data into SRAM in size [batch x field]
  // where batch is the vector dimension of the stream and fields are sent in
  // in multiple cycles
  def transposeInput[T:Bits](in:StreamIn[Tup2[T,Bit]]) = {
    val insram = SRAM[T](batch, field)
    val lastBit = FIFO[Bit](10)
    Foreach(0 until field) { f =>
      Foreach(0 until batch par batch) { b =>
        val token = in.value
        insram(b,f) = token._1
        lastBit.enq(token._2, f==field-1)
      }
    }
    (insram, lastBit)
  }

  // Takes an output sram of size [batch] and last bit FIFO, send the data to a output stream
  // vectorized by batch
  def transposeOutput[T:Bits](outsram:SRAM1[T], lastBit:FIFO[Bit], out:StreamOut[Tup2[T,Bit]]) = {
    Foreach(0 until batch par batch) { b =>
      out := Tup2(outsram(b), lastBit.deq)
    }
  }
}

@spatial abstract class StreamInference[HI:Numeric,TI:Bits,TO:Bits](implicit ev:Cast[Text,TI]) extends StreamTemplate {
  val tibits = implicitly[Bits[TI]]
  val tobits = implicitly[Bits[TO]]

  // Takes in a matrix in [N x field] and returns a matrix in N
  def hostBody(inDataMat:Seq[Seq[HI]]):Seq[Any]
  // Takes in a 2D sram in shape [batch x field] and return a sram of size [batch]
  def accelBody(insram:SRAM2[TI]):SRAM1[TO]

  def accelBody(in:StreamIn[Tup2[TI,Bit]], out:StreamOut[Tup2[TO,Bit]]):Unit = {
    Accel{
      Foreach(*) { _ =>
        val (insram, lastBit) = transposeInput(in)
        val outsram = accelBody(insram)
        transposeOutput(outsram, lastBit, out)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val inFile = buildPath(IR.config.genDir, "tungsten", "in.csv")
    val outFile = buildPath(IR.config.genDir, "tungsten", "out.csv")
    val goldFile = buildPath(IR.config.genDir, "tungsten", "gold.csv")
    val inDataMat = generateRandomInput(inFile)
    val goldMat = hostBody(inDataMat) 
    writeGoldStream(goldMat, goldFile)

    val in  = StreamIn[Tup2[TI,Bit]](FileBusLastBit[Tup2[TI,Bit]](inFile))
    val out = StreamOut[Tup2[TO,Bit]](FileBusLastBit[Tup2[TO,Bit]](outFile))
    accelBody(in,out)
    val outData = loadCSV2D[Int](outFile)
    val cksum = outData == loadCSV2D[TI](goldFile)
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }

  implicit def tseq_to_bitsseq[T:Bits](x:Seq[T]):Seq[Bits[T]] = x.map { case Bits(x) => x.asInstanceOf[Bits[T]] }
  implicit def tseqseq_to_bitsseqseq[T:Bits](x:Seq[Seq[T]]):Seq[Seq[Bits[T]]] = x.map { x => tseq_to_bitsseq(x) }
}

trait BlackBoxedStreamInference[HI,TI,TO] extends StreamInference[HI,TI,TO] {
  implicit val imp_tibits = tibits
  implicit val imp_tobits = tobits
  override def main(args: Array[String]): Unit = {
    val in  = StreamIn[Tup2[TI,Bit]](BlackBoxBus[Tup2[TI,Bit]]("input"))
    val out = StreamOut[Tup2[TO,Bit]](BlackBoxBus[Tup2[TO,Bit]]("output"))
    accelBody(in,out)
    assert(true)
  }
}
