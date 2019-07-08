import spatial.dsl._
import spatial.lang.{FileBus,FileEOFBus}
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

  def transposeTrainInput[T:Bits](in:StreamIn[Tup2[T,Bit]]) = {
    val trainX = SRAM[T](batch, field)
    val trainY = SRAM[T](batch)
    val lastBit = FIFO[Bit](10)
    val lastBitFIFO = FIFO[Bit](10)
    Foreach(0 until field+1) { f =>
      Foreach(0 until batch par batch) { b =>
        val token = in.value
        if (f != field) {
          trainX(b,f) = token._1
        }
        lastBit.enq(token._2, f==field)
        lastBitFIFO.enq(token._2, f==field)
        trainY(b) = token._1
      }
    }
    val lastBatch = Reg[Bit]
    Reduce(lastBatch)(0 until batch par batch) { b =>
      lastBitFIFO.deq
    } { _ | _ }
    (trainX, trainY, lastBit, lastBatch.value)
  }

  // Takes an output sram of size [batch] and last bit FIFO, send the data to a output stream
  // vectorized by batch
  def transposeOutput[T:Bits](outsram:SRAM1[T], lastBit:FIFO[Bit], out:StreamOut[Tup2[T,Bit]]) = {
    Foreach(0 until batch par batch) { b =>
      out := Tup2(outsram(b), lastBit.deq)
    }
  }


  def checkGold[T:Bits](dram:DRAM1[T], goldFile:java.lang.String)(implicit ev:Cast[Text,T]) = {
    val result = getMem(dram)
    println(s"${dram.name.getOrElse(s"$dram")} Result: ")
    printArray(result)

    val goldData = loadCSV1D[T](goldFile)
    println(s"${dram.name.getOrElse(s"$dram")} Gold: ")
    printArray(goldData)

    approxEql[T](result, goldData)
  }

  def checkGold[T:Bits](reg:Reg[T], gold:T)(implicit ev:Cast[T,Text]) = {
    val result = getArg(reg)
    println(s"${reg.name.getOrElse(s"$reg")} Result: " + result)
    println(s"${reg.name.getOrElse(s"$reg")} Gold: " + gold)
    approxEql[T](result, gold)
  }

}

@spatial abstract class StreamInference[HI:Numeric,TI:Bits,TO:Bits](implicit ev:Cast[Text,TO]) extends StreamTemplate {
  val tibits = implicitly[Bits[TI]]
  val tobits = implicitly[Bits[TO]]

  // Takes in a matrix in [N x field] and returns a matrix in N
  def hostBody(inDataMat:Seq[Seq[HI]]):Seq[Any]
  // Takes in a 2D sram in shape [batch x field] and return a sram of size [batch]
  def accelBody(insram:SRAM2[TI]):SRAM1[TO]

  def main(args: Array[String]): Unit = {
    val inFile = buildPath(IR.config.genDir, "tungsten", "in.csv")
    val outFile = buildPath(IR.config.genDir, "tungsten", "out.csv")
    val goldFile = buildPath(IR.config.genDir, "tungsten", "gold.csv")
    val inDataMat = generateRandomInput[HI](inFile)
    val goldMat = hostBody(inDataMat) 
    writeGoldStream(goldMat, goldFile)

    val in  = StreamIn[Tup2[TI,Bit]](FileEOFBus[Tup2[TI,Bit]](inFile))
    val out = StreamOut[Tup2[TO,Bit]](FileEOFBus[Tup2[TO,Bit]](outFile))
    Accel{
      Foreach(*) { _ =>
        val (insram, lastBit) = transposeInput(in)
        val outsram = accelBody(insram)
        transposeOutput(outsram, lastBit, out)
      }
    }
    val outData:Matrix[TO] = loadCSV2D[TO](outFile)
    val goldData:Matrix[TO] = loadCSV2D[TO](goldFile)
    val cksum = approxEql[TO](outData,goldData)
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }

  implicit def tseq_to_bitsseq[T:Bits](x:Seq[T]):Seq[Bits[T]] = x.map { case Bits(x) => x.asInstanceOf[Bits[T]] }
  implicit def tseqseq_to_bitsseqseq[T:Bits](x:Seq[Seq[T]]):Seq[Seq[Bits[T]]] = x.map { x => tseq_to_bitsseq(x) }
}

trait StreamTraining extends StreamTemplate {

  //def hostCheck(inDataMat:Seq[Seq[HI]]):Bit
  //def main(in:StreamIn[Tup2[TI,Bit]], inDataMat:Seq[Seq[HI]]):Bit

  //def main(args: Array[String]): Unit = {
    //val inFile = buildPath(IR.config.genDir, "tungsten", "in.csv")
    //val inDataMat = generateRandomInput[HI](inFile)

    //val in  = StreamIn[Tup2[TI,Bit]](FileEOFBus[Tup2[TI,Bit]](inFile))
    //val cksum = main(in, inDataMat)
    //println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    //assert(cksum)
  //}
}

