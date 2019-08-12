import spatial.dsl._
import spatial.lib.ML._
import utils.io.files._
import spatial.lang.{FileBus,FileEOFBus}
import spatial.metadata.bounds._

class StreamTrainMLP_0 extends StreamTrainMLP[Float]()()
class StreamTrainMLP_1 extends StreamTrainMLP[Float](numBatch=16, iters=4)()
//class StreamTrainMLP_1 extends StreamTrainMLP[Float]()(opb=2)
//class StreamTrainMLP_2 extends StreamTrainMLP[Float]()(opf=2)
//class StreamTrainMLP_3 extends StreamTrainMLP[Float]()(opb=2, opf=2)
//class StreamTrainMLP_4 extends StreamTrainMLP[Float]()(opb=4, opf=2)
//class StreamTrainMLP_5 extends StreamTrainMLP[Float]()(opb=2, opf=4)
//class StreamTrainMLP_6 extends StreamTrainMLP[Float](field=5)()

// Reference https://blog.goodaudience.com/logistic-regression-from-scratch-in-numpy-5841c09e425f 
@spatial abstract class StreamTrainMLP[T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 2,
  val batch:scala.Int = 8,
  val iters:scala.Int = 4,
  val learnRate:scala.Float = 1e-6f,
  val L1:scala.Int = 4,
  val L2:scala.Int = 8,
)(
  val op1:scala.Int = 1,
  val op2:scala.Int = 1,
  val mp1:scala.Int = 1, // L1/ip1
  val mp2:scala.Int = 1, // L2/ip2
  val opb:scala.Int = 1,
  val ipf:scala.Int = math.min(field, 16),
  val ip1:scala.Int = math.min(L1,16),
  val ip2:scala.Int = math.min(L2,16),
  val ipb:scala.Int = math.min(16,batch),
)(implicit ev:Cast[Text,T], ev2:Cast[T,Text]) extends StreamTraining with StreamTrainMLPHost {
  val mpf = 1
  val opf = 1
  val op3 = 1
  val mp3 = 1
  val ip3 = 1
  val ts1 = 16
  val ts2 = 16
  val ts3 = 16
  val mpb = 1
  val tsb = 16
  val L3:scala.Int = 1

  def infile = buildPath(IR.config.genDir, "tungsten", "in.csv")
  def outfile = buildPath(IR.config.genDir, "tungsten", "out.csv")
  def w1file = buildPath(IR.config.genDir, "tungsten", "w1.csv")
  def w2file = buildPath(IR.config.genDir, "tungsten", "w2.csv")
  def w3file = buildPath(IR.config.genDir, "tungsten", "w3.csv")
  def b1file = buildPath(IR.config.genDir, "tungsten", "b1.csv")
  def b2file = buildPath(IR.config.genDir, "tungsten", "b2.csv")
  def b3file = buildPath(IR.config.genDir, "tungsten", "b3.csv")

  def newSRAM2(D1:scala.Int, D2:scala.Int) = {
    val sram = SRAM[T](D1, D2)
    Foreach(0 until D1, 0 until D2) { case (i,j) =>
      sram(i,j) = init.to[T]
    }
    sram
  }
  def newSRAM1(D1:scala.Int) = {
    val sram = SRAM[T](D1)
    Foreach(0 until D1) { i =>
      sram(i) = init.to[T]
    }
    sram
  }

  def main(args: Array[String]): Unit = {
    hostMain
    val in  = StreamIn[Tup2[T,Bit]](FileEOFBus[Tup2[T,Bit]](infile))
    in.count = numBatch * (field + 1)
    val out  = StreamOut[Tup2[T,Bit]](FileEOFBus[Tup2[T,Bit]](outfile))
    Accel{
      val w1 = newSRAM2(field, L1)
      val w2 = newSRAM2(L1, L2)
      val w3 = newSRAM2(L2, L3)
      val b1 = newSRAM1(L1)
      val b2 = newSRAM1(L2)
      val b3 = newSRAM1(L3)
      Foreach(*) { _ =>
        val (trainX, trainY, lastBit, lastBatch) = transposeTrainInput[T](in) // trainX [batch, field], trainY [batch]
        Sequential.Foreach(iters by 1) { iter =>
          val h1 = SRAM[T](batch,L1)
          val h2 = SRAM[T](batch,L2)
          val h3 = SRAM[T](batch,L3)
          val l1 = SRAM[T](batch,L1)
          val l2 = SRAM[T](batch,L2)
          val l3 = SRAM[T](batch,L3)
          val dyhat = SRAM[T](batch, L3)
          Foreach(0 until batch par opb) { b =>
            denselayer[T](w1, b1, relu[T] _, in=trainX(b,_), nlout=h1.update(b,_,_), lout=l1.update(b,_,_))(ipf, mpf, op1)
          }
          Foreach(0 until batch par opb) { b =>
            denselayer[T](w2, b2, relu[T] _, in=h1(b,_), nlout=h2.update(b,_,_), lout=l2.update(b,_,_))(ip1, mp1, op2)
          }
          Foreach(0 until batch par opb) { b =>
            denselayer[T](w3, b3, identity[T], in=h2(b,_), nlout={ case (i, pred) => 
              h3(b,i) = pred
              dyhat(b,i) = loss_squre_backward(pred, trainY(b))
            }, lout=l3.update(b,_,_))(ip2, mp2, op3)
          }
          val dl2 = denselayer_backward[T](w3, b3, batch, learnRate, identity_backward[T], in=h2(_,_), nlout=h3(_,_), lout=l3(_,_), dnlout=dyhat(_,_))(opb, tsb, mpb, ipb, op3, ts3, mp3, ip3, op2)
          val dl1 = denselayer_backward[T](w2, b2, batch, learnRate, relu_backward[T], in=h1(_,_), nlout=h2(_,_), lout=l2(_,_), dnlout=dl2(_,_))(opb, tsb, mpb, ipb, op2, ts2, mp2, ip2, op1)
          val dx = denselayer_backward[T](w1, b1, batch, learnRate, relu_backward[T], in=trainX(_,_), nlout=h1(_,_), lout=l1(_,_), dnlout=dl1(_,_))(opb, tsb, mpb, ipb, op1, ts1, mp1, ip1, opf)
        }
        Foreach(0 until batch par ipb) { b =>
          val v = mux(b==(batch-1), w1(0,0),
                  mux(b==(batch-2), w2(0,0),
                  mux(b==(batch-3), w3(0,0),
                  mux(b==(batch-4), b1(0),
                  mux(b==(batch-5), b2(0),
                  mux(b==(batch-6), b3(0),
                  0.to[T]))))))
          val lb = lastBit.deq
          out := Tup2(v, lb)
        }
      }
    }
    val outData:Matrix[T] = loadCSV2D[T](outfile)
    val gw1 = loadCSV2D[T](w1file)
    val gw2 = loadCSV2D[T](w2file)
    val gw3 = loadCSV2D[T](w3file)
    val gb1 = loadCSV1D[T](b1file)
    val gb2 = loadCSV1D[T](b2file)
    val gb3 = loadCSV1D[T](b3file)
    val cksum = approxEql[T](outData(N-1,0),gw1(0,0)) && 
                approxEql[T](outData(N-2,0),gw2(0,0)) &&
                approxEql[T](outData(N-3,0),gw3(0,0)) &&
                approxEql[T](outData(N-4,0),gb1(0)) &&
                approxEql[T](outData(N-5,0),gb2(0)) &&
                approxEql[T](outData(N-6,0),gb3(0)) 
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }
}
