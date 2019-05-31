import utils.io.files._

abstract class StreamHostTemplate {
  val field:scala.Int
  val numBatch:scala.Int
  val batch:scala.Int

  def N = numBatch * batch
  def numToken = N * field

  // Return inData in size N x field
  def generateRandomInput[HT:Numeric](inFile:String):Seq[Seq[HT]] = {
    createDirectories(dirName(inFile))
    val r = scala.util.Random
    val inDataOnly = Seq.tabulate(numToken) { i => r.nextInt(numToken) }
    val inData = Seq.tabulate(numToken,2) { (i,j) =>
      if (j == 0) inDataOnly(i)
      else if (i == numToken-1) 1
      else 0
    }
    writeCSVNow2D(inData, inFile)
    // size in numBatch x field x batch
    val inputMat = inDataOnly.grouped(batch).toSeq.grouped(field).toSeq
    // size in (numBatch x batch) x field = N x field
    inputMat.map { _.transpose }.flatten
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
  }
  implicit def int_to_tt[HT:Numeric](x:Int):HT = num[HT].fromInt(x)
  implicit def intseq_to_ttseq[HT:Numeric](x:Seq[Int]):Seq[HT] = x.map { x => num[HT].fromInt(x) }
  implicit def intseqseq_to_ttseqseq[HT:Numeric](x:Seq[Seq[Int]]):Seq[Seq[HT]] = x.map { _.map { x => num[HT].fromInt(x) } }

  implicit def tonum[HT:Numeric](x:HT):NumOp[HT] = new NumOp[HT](x)
}

