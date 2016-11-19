
import pir._
import pir.test._
import pir.misc._
import pir.graph.enums._
import pir.graph._
import pir.graph.mapper.PIRException
import plasticine.main._

import org.scalatest._
import scala.language.reflectiveCalls

class UnitCodegenTest extends UnitTest { self =>

  //intercept[PIRException] {
  // No offchip access 
  "ArgInOutDesign"              should "success" in { ArgInOutDesign.main(Array("ArgInOutDesign")) }
  "DotProductLite"              should "success" in { DotProductLite.main(Array("DotProductLite")) }
  "StreamPipe"                  should "success" in { StreamPipe.main(Array("StreamPipe")) }
  "InOutArgDesign"              should "success" in { InOutArgDesign.main(Array("InOutArgDesign")) }
  "NiterDesign"                 should "success" in { NiterDesign.main(Array("NiterDesign")) }
  "SimpleReduceDesign"          should "success" in { SimpleReduceDesign.main(Array("SimpleReduceDesign")) }
  "SimpleSequentialDesign"      should "success" in { SimpleSequentialDesign.main(Array("SimpleSequentialDesign")) }
  "DeviceMemcpyDesign"          should "success" in { DeviceMemcpyDesign.main(Array("DeviceMemcpyDesign")) }

  //// With offchip access
  "DotProduct"                  should "success" in { DotProduct.main(Array("DotProduct")) }
  //"BlockReduce1DDesign"       should "success" in { BlockReduce1DDesign.main(Array("BlockReduce1DDesign")) }
  //"DotProductDesign"          should "success" in { DotProductDesign.main(Array("DotProductDesign")) }
  //"Memcpy2DDesign"            should "success" in { Memcpy2DDesign.main(Array("Memcpy2DDesign")) }
  //"SimpleFoldDesign"          should "success" in { SimpleFoldDesign.main(Array("SimpleFoldDesign")) }
  //// Sometimes fail
  //"SimpleTileLoadStoreDesign" should "success" in { SimpleTileLoadStoreDesign.main(Array("SimpleTileLoadStoreDesign")) }
}

class AppTest extends UnitTest { self =>
  "DotProductDesign"    should "success" in { DotProductDesign.main(Array("DotProductDesign")) }
  "OuterProductDesign"  should "success" in { OuterProductDesign.main(Array("OuterProductDesign")) }
  "TPCHQ6Design"        should "success" in { TPCHQ6Design.main(Array("TPCHQ6Design")) }
  "BlackScholesDesign"  should "success" in { BlackScholesDesign.main(Array("BlackScholesDesign")) }
  "MatMult_innerDesign" should "success" in { MatMult_innerDesign.main(Array("MatMult_innerDesign")) }
  "MatMult_outerDesign" should "success" in { MatMult_outerDesign.main(Array("MatMult_outerDesign")) }
  "LogRegDesign"        should "success" in { LogRegDesign.main(Array("LogRegDesign")) }
  "SGDDesign"           should "success" in { SGDDesign.main(Array("SGDDesign")) }
  "KmeansDesign"        should "success" in { Kmeans_fissionDesign.main(Array("KmeansDesign")) }
  "GDADesign"           should "success" in { GDADesign.main(Array("GDADesign")) }
  "Convolution"         should "success" in { Convolution.main(Array("ConvolutionDesign")) }
  //"PageRankDesign"      should "success" in { PageRankDesign.main(Array("PageRankDesign")) }
}
