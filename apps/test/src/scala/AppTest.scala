
import pir._
import pir.test._
import pir.util.misc._
import pir.util.enums._
import pir.util._
import pir.graph._
import pir.exceptions.PIRException
import plasticine.main._

import org.scalatest._
import scala.language.reflectiveCalls

class AppTests extends UnitTest { self =>

  def test(app:PIRApp, args:String, argOuts:String) = {
    s"$app [$args] ($argOuts)" should "success" in { 
      try {
        val design = app 
        //Config.codegen = false
        //Config.debug = false
        if (design.pirMapping.hasRun) {
          design.setArgs(args.split(" "))
          design.simulator.reset
          design.simulator.run
        } else {
          design.main(args)
        }
        assert(!design.simulator.timeOut)
        argOuts.split(" ").foreach { aos =>
          val aon::aov::_ = aos.split("=").toList
          val ao = design.top.sins.filter { _.scalar.name==Some(aon) }.head
          val pao = design.mapping.get.vimap(ao)
          assert(pao.values(0).value==Some(toValue(aov)), s"Result incorrect")
        }
      } catch {
        case e:Exception => errmsg(s"$e"); throw e
      }
    }
  }

  def testDotProduct(app:PIRApp, startA:Int, startB:Int, N:Int) = {
    val a = (startA until startA+N).toList
    val b = (startB until startB+N).toList
    val gold = a.zip(b).map{ case (aa,bb) => aa * bb }.sum.toFloat
    val default = Config.simulationTimeOut
    Config.simulationTimeOut = 100
    test(app, args=s"x1019=$N x1037=${startA*4} x1056=${startB*4}", argOuts=s"x1026_x1096=$gold")
    Config.simulationTimeOut = default
  }

  //def testTPCHQ6(app:PIRApp, startA:Int, startB:Int, startC:Int, startD:Int, N:Int) = {
    //val a = (startA until startA+N).toList
    //val b = (startB until startB+N).toList
    //val c = (startC until startC+N).toList
    //val d = (startD until startD+N).toList
    //val gold = a.zip(b).map{ case (aa,bb) => aa * bb }.sum.toFloat
    //val default = Config.simulationTimeOut
    //Config.simulationTimeOut = 100
    //test(app, args=s"x1019=$N x1037=${startA*4} x1056=${startB*4}", argOuts=s"x1026_x1096=$gold")
    //Config.simulationTimeOut = default
  //}

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
  //"OuterProduct" should "success" in { OuterProduct.main(Array("OuterProduct")) }
  //"BlackScholes" should "success" in { BlackScholes.main(Array("BlackScholes")) }
  //"TPCHQ6" should "success" in { TPCHQ6.main(Array("TPCHQ6")) }
  //"MatMult_inner" should "success" in { MatMult_inner.main(Array("MatMult_inner")) }
  //"GDA" should "success" in { GDA.main(Array("GDA")) }
  //"LogReg" should "success" in { LogReg.main(Array("LogReg")) }
  
  Config.debug = false
  Config.waveform = false
  Config.verbose = false
  //test(InOutArg, args="x222=4", argOuts="x223_x227=8.0")
  //test(SRAMReadWrite, args="", argOuts="x1026_x1096=10416.0")
  //test(SimpleSequential, args="x343=2 x342=10", argOuts="x344_x356=20.0")
  //test(SimpleSequential, args="x343=1 x342=10", argOuts="x344_x356=10.0")
  //test(SimpleReduce, args="x350=10", argOuts="x351_x365=1200.0")
  testDotProduct(DotProductSeq, startA=0, startB=16, N=32)
  testDotProduct(DotProductSeq, startA=0, startB=16, N=64)
  testDotProduct(DotProductMeta, startA=0, startB=16, N=32)
  testDotProduct(DotProductMeta, startA=0, startB=16, N=64)
  //testTPCHQ6(TPCHQ6, startA=0, startB=16, N=32)

}
