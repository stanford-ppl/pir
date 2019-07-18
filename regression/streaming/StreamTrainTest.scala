import spatial.dsl._
import spatial.lib.ML._
import utils.io.files._
import spatial.lang.{FileBus,FileEOFBus}
import spatial.metadata.bounds._
import spatial.metadata.memory._

class StreamTrainTest_0 extends StreamTrainTest[Float]()()

// Reference https://blog.goodaudience.com/logistic-regression-from-scratch-in-numpy-5841c09e425f 
@spatial abstract class StreamTrainTest[T:Num](
  val K:scala.Int = 16, // Number of centroids
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val iters:scala.Int = 2,
)(
  val op:scala.Int = 1,
  val kp:scala.Int = 1,
  val ipf:scala.Int = math.min(field, 16),
  val ipb:scala.Int = math.min(batch, 16), // batch
)(implicit ev:Cast[T,Text], ev2:Cast[Text,T]) extends StreamTraining {

  def main(args: Array[String]): Unit = {
    val inFile = buildPath(IR.config.genDir, "tungsten", "in.csv")
    val goldXFile = buildPath(IR.config.genDir, "tungsten", "goldx.csv")
    val (trainX, trainY) = generateRandomTrainInput[scala.Float](inFile) // return N x field
    val goldX = trainX.reduce[Seq[scala.Float]]{ case (r1, r2) => r1.zip(r2).map { case (f1,f2) => f1 + f2 } }.map { _ * iters }
    writeCSVNow(goldX, goldXFile)
    val goldY = (trainY.sum * iters).to[T]
    writeCSVNow2D(trainX, buildPath(IR.config.genDir, "tungsten", "trainX.csv"))
    writeCSVNow(trainY, buildPath(IR.config.genDir, "tungsten", "trainY.csv"))

    val in  = StreamIn[Tup2[T,Bit]](FileEOFBus[Tup2[T,Bit]](inFile))
    in.count = numBatch * (field + 1)
    val xDRAM = DRAM[T](field)
    val bArg = ArgOut[T]
    Accel{
      val sumX = SRAM[T](field)
      val sumY = Reg[T](0.to[T])
      val stop = Reg[Bit](false)
      //Stream(breakWhen=stop).Foreach(*) { _ =>
      Sequential(breakWhen=stop).Foreach(*) { _ =>
        val (trainX, trainY, lastBit, lastBatch) = transposeTrainInput[T](in) // trainX [batch, field], trainY [batch]
        Sequential.Foreach(iters by 1) { iter =>
          Foreach(0 until batch) { b =>
            Foreach(0 until field par ipf) { f =>
              sumX(f) = sumX(f) + trainX(b,f)
            }
          }
          val ySum = Reg[T]
          Reduce(ySum)(0 until batch par ipb) { b =>
            trainY(b)
          } { _ + _ }
          Pipe {
            sumY := sumY.value + ySum.value
          }
        }
        if (lastBatch) {
          xDRAM(0::field par ipf).store(sumX)
        }
        bArg.write(sumY.value)
        stop := lastBatch
      }
    }
    val cksum = checkGold[T](xDRAM, goldXFile) & checkGold[T](bArg, goldY)
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }
}
