package pir.mapper

import pir.node._
import spade.node._

trait StaticCUPlacer extends PIRPass with BackTracking with Routing {
  import pirmeta._

  def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = {
    val unmapped = pmap.cumap.freeKeys.toSet
    pmap.flatMap[CUMap] { cumap => 
      dbgblk(1, s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
    }.flatMap { pmap =>
      breakPoint(pmap) {
        route(cuP, addIOs(pmap,cuP))
      }
    }
  }

  def staticPlace(pmap:PIRMap) = log {
    bind[CUMap.K, CUMap.V, PIRMap](
      pnodes=pmap.cumap.freeKeys.toSeq,
      snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap.freeValues(cuP).toList.sortBy { case v => -m.cumap.freeKeys(v).size},
      init=pmap,
      bindLambda=bindLambda _
    )
  }

}
