package pir.mapper

import spade.node._

import scala.collection.mutable

class DynamicCUPlacer(implicit compiler:PIR) extends PIRPass with BackTracking {
  import pirmeta._

  def shouldRun = PIRConfig.mapping && isMesh(compiler.arch.top) && isDynamic(compiler.arch.top)

  override def runPass =  {
    pirMap = pirMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap => 
        log(bind[CUMap.K, CUMap.V, CUMap](
          init=cumap
        ))
      }
    }
  }

}
