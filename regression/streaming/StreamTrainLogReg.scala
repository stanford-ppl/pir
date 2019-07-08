//import spatial.dsl._
//import spatial.lib.ML._
//import utils.io.files._
//import spatial.lang.{FileBus,FileEOFBus}

//class StreamTrainLogReg_0 extends StreamTrainLogReg[Float]

//// Reference https://blog.goodaudience.com/logistic-regression-from-scratch-in-numpy-5841c09e425f 
//@spatial abstract class StreamTrainLogReg[T:Num](
  //val K:scala.Int = 16, // Number of centroids
  //val field:scala.Int = 8,
  //val numBatch:scala.Int = 16,
  //val batch:scala.Int = 4,
  //val iters:scala.Int = 2,
  //val learnRate:scala.Float = 0.01f,
  //val op:scala.Int = 1,
  //val kp:scala.Int = 1,
  //val ip1:scala.Int = 8, // field
  //val ip2:scala.Int = 8, // batch
//)(implicit ev:Cast[Text,T]) extends StreamTraining {

  //def main(args: Array[String]): Unit = {
    //val inFile = buildPath(IR.config.genDir, "tungsten", "in.csv")
    //val inDataMat = generateRandomInput[scala.Float](inFile)

    //val in  = StreamIn[Tup2[T,Bit]](FileEOFBus[Tup2[T,Bit]](inFile))
    //val dram = DRAM[T](4)
    //Accel{
      //val stop = Reg[Bit](false)
      //val weights = SRAM[T](field)
      //val wDRAM = DRAM[T](field)
      //val bias = Reg[T](0.1f.to[T])
      //val bArg = ArgOut[T]
      //Stream(breakWhen=stop).Foreach(*) { _ =>
        //val (trainX, trainY, lastBit) = transposeTrainInput[T](in) // trainX [batch, field], trainY [batch]
        //Sequential.Foreach(iters by 1) { epoch =>
          //val dZ = SRAM[T](batch)
          //Foreach(0 until batch par op) { b =>
            //val dot = Reg[T]
            //Reduce(dot)(0 until field par ip1) { f =>
              //trainX(b,f) * weights(f)
            //} { _ + _ }
            //val A = sigmoid(dot.value + bias.value)
            //dZ(b) = A - trainY(b)
          //}
          //val dW = SRAM[T](field)
          //Foreach(0 until field) { f =>
            //val sum = Reg[T]
            //Reduce(sum)(0 until batch par ip2) { b =>
              //trainX(b,f) * dZ(b) * (1.0/batch).to[T]
            //} { _ + _ }
            //dW(f) = sum
          //}
          //val dB = Reg[T]
          //Reduce(dB)(0 until batch par ip2) { b =>
            //dZ(b)
          //} { _ + _ }
          //Foreach(0 until field) { f =>
            //weights(f) = weights(f) - learnRate.to[T] * dW(f)
          //}
          //Pipe {
            //bias := bias.value - learnRate.to[T] * dB.value
          //}
          ////stop := lastBit.deq
          //stop := false
        //}
      //}
      //wDRAM(0::field) store weights
      //bArg := bias.value
    //}
    //val cksum = true//TODO
    //println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    //assert(cksum)
  //}
//}
