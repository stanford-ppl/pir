import spatial.dsl._
import spatial.lib.ML._
import utils.io.files._
import spatial.lang.{FileBus,FileEOFBus}

class StreamTrainLogReg_0 extends StreamTrainLogReg[Float]()()
class StreamTrainLogReg_1 extends StreamTrainLogReg[Float]()(opb=2)
class StreamTrainLogReg_2 extends StreamTrainLogReg[Float]()(opf=2)
class StreamTrainLogReg_3 extends StreamTrainLogReg[Float]()(opb=2, opf=2)
class StreamTrainLogReg_4 extends StreamTrainLogReg[Float]()(opb=4, opf=2)
class StreamTrainLogReg_5 extends StreamTrainLogReg[Float]()(opb=2, opf=4)

// Reference https://blog.goodaudience.com/logistic-regression-from-scratch-in-numpy-5841c09e425f 
@spatial abstract class StreamTrainLogReg[T:Num](
  val K:scala.Int = 16, // Number of centroids
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val iters:scala.Int = 2,
  val learnRate:scala.Float = 0.01f,
)(
  val opb:scala.Int = 1,
  val opf:scala.Int = 1,
  val ipf:scala.Int = field, // field
  val ipb:scala.Int = batch, // batch
)(implicit ev:Cast[Text,T], ev2:Cast[T,Text]) extends StreamTraining {
  val init = 0.1f

  def hostMain(inFile:java.lang.String, wFile:java.lang.String) = {
    val (trainX, trainY) = generateRandomTrainInput[scala.Float](inFile) // return N x field
    val weights = scala.Array.fill[scala.Float](field)(init)
    val bias = scala.Array.fill[scala.Float](1)(init) // Spatial stage var
    //val pw = new java.io.PrintWriter(new java.io.File(buildPath(IR.config.genDir, "tungsten", "host.log")))
    trainX.grouped(batch).zip(trainY.grouped(batch)).foreach { case (trainX, trainY) =>
      //pw.write(s"=================================== \n")
      //pw.write(s"trainX: \n")
      //trainX.foreach { rec => pw.write(s"[${rec.toList.mkString(",")}]\n") }
      //pw.write(s"trainY=[${trainY.mkString(",")}] \n")
      Range(start=0, end=iters, step=1).foreach { epoch =>
        //pw.write(s"epoch=$epoch \n")
        val dZ:Seq[scala.Float] = (trainX,trainY).zipped.map { case (record, label) =>
          val pc = unstaged_dp(record, weights) + bias(0)
          val A = unstaged_sigmoid(pc)
          A - label
        }
        //pw.write(s"dZ=[${dZ.mkString(",")}] \n")
        val dW = trainX.transpose.map { batches =>
          (batches, dZ).zipped.map { case (x, dZ) =>
            x * dZ * (1.0f / batch)
          }.sum
        }
        //pw.write(s"dW=[${dW.mkString(",")}] \n")
        val dB = dZ.sum
        //pw.write(s"dB=$dB \n")
        Range(start=0,end=field,step=1).foreach { f =>
          weights(f) = weights(f) - learnRate * dW(f)
        }
        //pw.write(s"weights=[${weights.mkString(",")}] \n")
        bias(0) = bias(0) - learnRate * dB
        //pw.write(s"bias=${bias(0)} \n")
      }
    }
    //pw.close
    writeCSVNow(weights, wFile)
    bias(0)
  }

  def main(args: Array[String]): Unit = {
    val inFile = buildPath(IR.config.genDir, "tungsten", "in.csv")
    val wFile = buildPath(IR.config.genDir, "tungsten", "weights.csv")
    val goldBias = hostMain(inFile, wFile)

    val in  = StreamIn[Tup2[T,Bit]](FileEOFBus[Tup2[T,Bit]](inFile))
    val wDRAM = DRAM[T](field)
    val bArg = ArgOut[T]
    Accel{
      val stop = Reg[Bit](false)
      val weights = SRAM[T](field)
      val bias = Reg[T](init.to[T])
      Foreach(0 until field) { f =>
        weights(f) = init.to[T]
      }
      Sequential(breakWhen=stop).Foreach(*) { _ =>
        val (trainX, trainY, lastBit, lastBatch) = transposeTrainInput[T](in) // trainX [batch, field], trainY [batch]
        Sequential.Foreach(iters by 1) { epoch =>
          val dZ = SRAM[T](batch)
          Foreach(0 until batch par opb) { b =>
            val dot = Reg[T]
            Reduce(dot)(0 until field par ipf) { f =>
              trainX(b,f) * weights(f)
            } { _ + _ }
            val A = sigmoid(dot.value + bias.value)
            dZ(b) = A - trainY(b)
          }
          val dW = SRAM[T](field)
          Foreach(0 until field par opf) { f =>
            val sum = Reg[T]
            Reduce(sum)(0 until batch par ipb) { b =>
              trainX(b,f) * dZ(b) * (1.0/batch).to[T]
            } { _ + _ }
            dW(f) = sum
          }
          val dB = Reg[T]
          Reduce(dB)(0 until batch par ipb) { b => dZ(b) } { _ + _ }
          Foreach(0 until field par ipf) { f =>
            weights(f) = weights(f) - learnRate.to[T] * dW(f)
          }
          bias := bias.value - learnRate.to[T] * dB.value
        }
        if (lastBatch) wDRAM(0::field par ipf) store weights
        bArg := bias.value
        stop := lastBatch
      }
    }
    val cksum = checkGold[T](wDRAM, wFile) && checkGold[T](bArg, goldBias)
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }
}
