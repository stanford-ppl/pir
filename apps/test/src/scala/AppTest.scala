
import pir._
import pir.test._
import pir.util.misc._
import pir.util.enums._
import pir.graph._
import pir.exceptions.PIRException
import plasticine.main._

import org.scalatest._
import scala.language.reflectiveCalls

class AppTests extends UnitTest { self =>

  def testInOutArg = {
    "InOutArg" should "success" in { 
      InOutArg.main(Array("InOutArg"))
      val argOuts = InOutArg.arch.top.sins.map(_.values.head.value)
      assert(argOuts.contains(Some(8.0) ), s"Result incorrect argOuts=[${argOuts.mkString(", ")}]")
    }
  }

  def testSRAMReadWrite = {
    "SRAMReadWrite" should "success" in { 
      SRAMReadWrite.main(Array("SRAMReadWrite"))
      val argOuts = SRAMReadWrite.arch.top.sins.map(_.values.head.value)
      assert(argOuts.contains(Some(10416.0), s"Result incorrect argOuts=[${argOuts.mkString(", ")}]")
    }
  }

  //intercept[PIRException] {
  // No offchip access 
  //"ArgInOutDesign" should "success" in { ArgInOutDesign.main(Array("ArgInOutDesign")) }
  //"DotProductLite" should "success" taggedAs(WIP) in { DotProductLite.main(Array("DotProductLite")) }
  //"StreamPipe" should "success" in { StreamPipe.main(Array("StreamPipe")) }
  //"InOutArgDesign" should "success" in { InOutArgDesign.main(Array("InOutArgDesign")) }
  //"NiterDesign" should "success" in { NiterDesign.main(Array("NiterDesign")) }
  //"SimpleReduceDesign" should "success" in { SimpleReduceDesign.main(Array("SimpleReduceDesign")) }
  //"SimpleSequentialDesign" should "success" in { SimpleSequentialDesign.main(Array("SimpleSequentialDesign")) }
  //"DeviceMemcpyDesign" should "success" in { DeviceMemcpyDesign.main(Array("DeviceMemcpyDesign")) }

  //// With offchip access
  //"BlockReduce1DDesign" should "success" in { BlockReduce1DDesign.main(Array("BlockReduce1DDesign")) }
  //"DotProductDesign" should "success" in { DotProductDesign.main(Array("DotProductDesign")) }
  //"Memcpy2DDesign" should "success" in { Memcpy2DDesign.main(Array("Memcpy2DDesign")) }
  //"SimpleFoldDesign" should "success" in { SimpleFoldDesign.main(Array("SimpleFoldDesign")) }
  //// Sometimes fail
  //"SimpleTileLoadStoreDesign" should "success" in { SimpleTileLoadStoreDesign.main(Array("SimpleTileLoadStoreDesign")) }
  
  // Apps 
  //"DotProduct" should "success" in { DotProduct.main(Array("DotProduct")) }
  //"OuterProduct" should "success" in { OuterProduct.main(Array("OuterProduct")) }
  //"BlackScholes" should "success" in { BlackScholes.main(Array("BlackScholes")) }
  //"TPCHQ6" should "success" in { TPCHQ6.main(Array("TPCHQ6")) }
  //"MatMult_inner" should "success" in { MatMult_inner.main(Array("MatMult_inner")) }
  //"GDA" should "success" in { GDA.main(Array("GDA")) }
  //"LogReg" should "success" in { LogReg.main(Array("LogReg")) }
  
  testInOutArg
  testSRAMReadWrite
}
