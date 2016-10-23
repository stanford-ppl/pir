
import pir._
import pir.test._
import pir.misc._
import pir.graph.enums._
import pir.graph._
import pir.graph.mapper.PIRException
import plasticine.main._

import org.scalatest._
import scala.language.reflectiveCalls

class AppTests extends UnitTest { self =>

  //intercept[PIRException] {
  "DotProduct" should "success" in { DotProduct.main(Array("DotProduct")) }
  "DotProductLite" should "success" taggedAs(WIP) in { DotProductLite.main(Array("DotProductLite")) }
  "ArgInOutDesign" should "success" in { ArgInOutDesign.main(Array("ArgInOutDesign")) }
  "BlockReduce1DDesign" should "success" in { BlockReduce1DDesign.main(Array("BlockReduce1DDesign")) }
  "DeviceMemcpyDesign" should "success" in { DeviceMemcpyDesign.main(Array("DeviceMemcpyDesign")) }
  "DotProductDesign" should "success" in { DotProductDesign.main(Array("DotProductDesign")) }
  "InOutArgDesign" should "success" in { InOutArgDesign.main(Array("InOutArgDesign")) }
  "Memcpy2DDesign" should "success" in { Memcpy2DDesign.main(Array("Memcpy2DDesign")) }
  "NiterDesign" should "success" in { NiterDesign.main(Array("NiterDesign")) }
  "SimpleFoldDesign" should "success" in { SimpleFoldDesign.main(Array("SimpleFoldDesign")) }
  "SimpleReduceDesign" should "success" in { SimpleReduceDesign.main(Array("SimpleReduceDesign")) }
  "SimpleSequentialDesign" should "success" in { SimpleSequentialDesign.main(Array("SimpleSequentialDesign")) }
  "SimpleTileLoadStoreDesign" should "success" in { SimpleTileLoadStoreDesign.main(Array("SimpleTileLoadStoreDesign")) }

}

