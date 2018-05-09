package pir.mapper

import pir.node._
import spade.node._

class CUPlacer(implicit compiler:PIR) extends PIRPass with StaticCUPlacer with DynamicCUPlacer {
  import pirmeta._

  override def runPass =  {
    pirMap = pirMap.flatMap { pmap =>
      compiler.arch.top match {
        case top if isMesh(top) && isStatic(top) => staticPlace(pmap)
        case top if isMesh(top) && isDynamic(top) => dynamicPlace(pmap)
      }
    }

  }

  override def finPass:Unit = {
    super.finPass
    pirMap.fold ({ failure => fail(failure) },{ mapping => succeed })
  }

}

