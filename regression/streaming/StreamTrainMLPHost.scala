
import spatial.lib.ML._
import utils.io.files._
import scala.collection.mutable.{Seq => MSeq, ListBuffer}

// Reference https://blog.goodaudience.com/logistic-regression-from-scratch-in-numpy-5841c09e425f 
trait StreamTrainMLPHost extends StreamHostTemplate {
  val iters:Int
  val learnRate:Float
  val init:Float = 0.1f
  val L1:Int
  val L2:Int
  val L3:Int

  def infile:String
  def w1file:String
  def w2file:String
  def w3file:String
  def b1file:String
  def b2file:String
  def b3file:String
  //val r = scala.util.Random
  //lazy val W1:MSeq[MSeq[Float]] = MSeq.tabulate(field, L1) { (i,j) => init*r.nextFloat }
  //lazy val W2:MSeq[MSeq[Float]] = MSeq.tabulate(L1, L2) { (i,j) => init*r.nextFloat }
  //lazy val W3:MSeq[MSeq[Float]] = MSeq.tabulate(L2, L3) { (i,j) => init*r.nextFloat }
  //lazy val B1:MSeq[Float] = MSeq.tabulate(L1) { i => init*r.nextFloat}
  //lazy val B2:MSeq[Float] = MSeq.tabulate(L2) { i => init*r.nextFloat}
  //lazy val B3:MSeq[Float] = MSeq.tabulate(L3) { i => init*r.nextFloat}
  lazy val W1:MSeq[MSeq[Float]] = MSeq.tabulate(field, L1) { (i,j) => init }
  lazy val W2:MSeq[MSeq[Float]] = MSeq.tabulate(L1, L2) { (i,j) => init }
  lazy val W3:MSeq[MSeq[Float]] = MSeq.tabulate(L2, L3) { (i,j) => init }
  lazy val B1:MSeq[Float] = MSeq.tabulate(L1) { i => init }
  lazy val B2:MSeq[Float] = MSeq.tabulate(L2) { i => init }
  lazy val B3:MSeq[Float] = MSeq.tabulate(L3) { i => init }

  // x [B,field]
  // l1 = W1x + b1 [B, L1]
  // h1 = act(l1) [B,L1]
  // l2 = W2h1 + b2 [B,L2]
  // yhat = act(l2) [B,L2]
  // score = 1/2 * (yhat - ylabel)^2 [B,L2]
  // dscore/dyhat = (yhat - ylabel) [B,L2]
  // dscore/dl2 = dscore/dyhat * dyhat/dl2 = dscore/dyhat * dact(l2) [B,L2]
  // dscore/dW2 = dscore/dl2 * dl2/dW2 
  //            = dscore/dl2 * h1 [batch,L2] * [batch,L1] = [L1,L2]
  // dscore/dh2 = dscore/dl2 * dl2/dh2
  //            = dscore/dl2 * 1 [batch, L2]
  // dscore/dl1 = dscore/dl2 * dl2/h1 * dh1/l1 = dscore/dl2 * W2 * dact(l1) = [B,L1]
  // dscore/dW1 = dscore/l1 * dl1/dW1 
  //            = dscore/l1 * x [batch,L1] * [batch,field] = [field,L1]
  // dscore/dh1 = dscore/l1 * dl1/db1
  //            = dscore/l1 * 1

  type Grad = (Seq[Seq[Float]], Seq[Seq[Float]], Seq[Seq[Float]], Seq[Float], Seq[Float], Seq[Float], Float)

  def hostMain = {
    val (trainX, trainY) = generateRandomTrainInput[Float](infile) // return N x field
    val pw = new java.io.PrintWriter(new java.io.File(infile.replace("in.csv","host.log")))
    trainX.grouped(batch).zip(trainY.grouped(batch)).foreach { case (trainX, trainY) =>
      dprintln(pw,s"=================================== ")
      printMatrix(pw,"trainX",trainX)
      printVector(pw,"trainY",trainY)
      Range(start=0, end=iters, step=1).foreach { iter =>
        val (h1, l1) = trainX.map { fields => unstaged_denselayer[Float](fields, W1, B1, unstaged_relu _) }.unzip
        val (h2, l2) = h1.map { h1 => unstaged_denselayer[Float](h1, W2, B2, unstaged_relu _) }.unzip
        val (h3, l3) = h2.map { h2 => unstaged_denselayer[Float](h2, W3, B3, unstaged_identity _) }.unzip
        val (loss, dyhat) = (h3, trainY).zipped.map { case (h3, label) =>
          h3.map { yhat =>
            val loss = unstaged_loss_square[Float](yhat, label)
            val dyhat = unstaged_loss_square_backward[Float](yhat, label)
            (loss, dyhat)
          }.unzip
        }.unzip
        val (dW3, dB3, dl2) = unstaged_denselayer_backward[Float](W3,B3,h2,h3,l3,dyhat,unstaged_identity_backward _, learnRate)
        val (dW2, dB2, dl1) = unstaged_denselayer_backward[Float](W2,B2,h1,h2,l2,dl2,unstaged_relu_backward _, learnRate)
        val (dW1, dB1, dx)  = unstaged_denselayer_backward[Float](W1,B1,trainX,h1,l1,dl1,unstaged_relu_backward _, learnRate)

        val avgLoss = loss.map { _.head }.sum/batch
        dprintln(pw,s"loss: ${avgLoss}")
        dprintln(pw,s"check: ${B3(0)}, ${B2(0)}, ${B1(0)}, ${W3(0)(0)}, ${W2(0)(0)}, ${W1(0)(0)}")
        printMatrix(pw,"dyhat", dyhat)
        printMatrix(pw,"h1", h1)
        printMatrix(pw,"h2", h2)
        printMatrix(pw,"h3", h3)
        printMatrix(pw,"W1", W1)
        printMatrix(pw,"W2", W2)
        printMatrix(pw,"W3", W3)
        printVector(pw,"B1", B1)
        printVector(pw,"B2", B2)
        printVector(pw,"B3", B3)
        printMatrix(pw,"dl1", dl1)
        printMatrix(pw,"dl2", dl2)
        pw.flush
        assert(!avgLoss.isNaN)
      }
    }
    pw.close
    writeCSVNow2D(W1, w1file)
    writeCSVNow2D(W2, w2file)
    writeCSVNow2D(W3, w3file)
    writeCSVNow(B1, b1file)
    writeCSVNow(B2, b2file)
    writeCSVNow(B3, b3file)
  }

  val debug = false
  def dprintln(pw:java.io.PrintWriter, msg:String) = {
    if (debug) {
      pw.write(msg + "\n")
    }
  }

  def printMatrix[T](pw:java.io.PrintWriter, name:String, mat:Seq[Seq[T]]):Unit = {
    dprintln(pw,s"$name:")
    mat.foreach { v => dprintln(pw,s"${v.mkString(",")}") }
  }

  def printVector[T](pw:java.io.PrintWriter, name:String, vec:Seq[T]):Unit = {
    dprintln(pw, s"$name:")
    dprintln(pw, s"${vec.mkString(",")}")
  }

}

object StreamTrainMLPHostTest extends StreamTrainMLPHost {
  val iters = 1
  val learnRate = 0.1f
  val L1 = 4
  val L2 = 8
  val L3 = 1
  val batch = 4
  val numBatch = 5
  val field = 3

  val infile = "in.csv" 
  val w1file = "w1.csv"
  val w2file = "w2.csv"
  val w3file = "w3.csv"
  val b1file = "b1.csv"
  val b2file = "b2.csv"
  val b3file = "b3.csv"
}
