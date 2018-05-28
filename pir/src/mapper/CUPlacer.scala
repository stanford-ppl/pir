package pir
package mapper

import pir.node._

class CUPlacer(implicit compiler:PIR) extends PIRPass with StaticPlacement with DynamicPlacement {
  import pirmeta._

  override def runPass =  {
    pirMap = pirMap.flatMap { pmap =>
      compiler.arch.top match {
        case topS if isStatic(topS) => staticPlace(pmap)
        case topS if isDynamic(topS) => dynamicPlace(pmap)
        case topS if isAsic(topS) => Right(pmap)
      }
    }

  }

  override def finPass:Unit = {
    super.finPass
    pirMap.fold ({ failure => fail(failure) },{ mapping => succeed })
  }

}

