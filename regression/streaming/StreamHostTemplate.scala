import utils.io.files._
import scala.reflect._

abstract class StreamHostTemplate {
  val field:scala.Int
  val numBatch:scala.Int
  val batch:scala.Int

  def N = numBatch * batch
  def numToken = N * field

  // Return inData in size N x field
  def generateRandomInput[HT:Numeric:ClassTag](inFile:String, field:scala.Int=field):Seq[Seq[HT]] = {
    createDirectories(dirName(inFile))
    val r = scala.util.Random
    val numToken = N * field
    val inDataOnly = Array.ofDim[Int](numToken)
    val inData = Array.ofDim[Int](numToken,2)
    (0 until numToken).foreach { i =>
      val t = r.nextInt(numToken)
      inDataOnly(i) = t
      (0 until 2).foreach { j =>
        inData(i)(j) = if (j == 0) inDataOnly(i)
                      else if (i == numToken-1) 1
                      else 0
      }
    }
    writeCSVNow2D(inData.map{_.toSeq}.toSeq, inFile)
    // size in numBatch x field x batch
    val inputMat = inDataOnly.grouped(batch).toSeq.grouped(field).toSeq
    // size in (numBatch x batch) x field = N x field
    inputMat.map { _.transpose }.flatten
  }

  def generateRandomTrainInput[HT:Numeric:ClassTag](inFile:String):(Seq[Seq[HT]], Seq[HT]) = {
    generateRandomInput(inFile, field=field+1).map { record =>
      val (fields, Seq(label)) = record.splitAt(record.size-1)
      (fields, label)
    }.unzip
  }

  // Takes in a gold vector in N
  // Write to output file with last Bit
  def writeGoldStream(goldData:Seq[Any], goldFile:String) = {
    val gold = Seq.tabulate(N, 2) { (i,j) => 
      if (j == 0) goldData(i)
      else if (i == N-1) 1
      else 0
    }
    writeCSVNow2D(gold, goldFile)
  }

  def num[HT:Numeric]:Numeric[HT] = implicitly[Numeric[HT]]
  class NumOp[HT:Numeric](tt:HT) {
    def * (x:HT) = num[HT].times(tt,x)
    def + (x:HT) = num[HT].plus(tt,x)
    def - (x:HT) = num[HT].minus(tt,x)
    def > (x:HT) = num[HT].gt(tt,x)
    def >= (x:HT) = num[HT].gteq(tt,x)
    def < (x:HT) = num[HT].lt(tt,x)
    def <= (x:HT) = num[HT].lteq(tt,x)
  }
  implicit def int_to_tt[HT:Numeric](x:Int):HT = num[HT].fromInt(x)
  implicit def intseq_to_ttseq[HT:Numeric](x:Seq[Int]):Seq[HT] = x.map { x => num[HT].fromInt(x) }
  implicit def intseqseq_to_ttseqseq[HT:Numeric](x:Seq[Seq[Int]]):Seq[Seq[HT]] = x.map { _.map { x => num[HT].fromInt(x) } }

  implicit def tonum[HT:Numeric](x:HT):NumOp[HT] = new NumOp[HT](x)

  def frac[HT:Fractional]:Fractional[HT] = implicitly[Fractional[HT]]
  implicit def float_to_frac[T:Fractional](x:Float):T = {
    val t:T = frac[T] match {
      case f if f == frac[Float] => x.asInstanceOf[T]
      case f if f == frac[Double] => f.toDouble(x).asInstanceOf[T]
    }
    t
  }
  implicit def fltseq_to_ttseq[HT:Fractional](x:Seq[Float]):Seq[HT] = x.map { x => float_to_frac[HT](x) }
  implicit def fltseqseq_to_ttseqseq[HT:Fractional](x:Seq[Seq[Float]]):Seq[Seq[HT]] = x.map { _.map { x => float_to_frac[HT](x) } }
}

