package pir
package mapper

import pir.node._
import spade.node._

trait StaticCUPlacer extends PIRPass with BackTrackingMatch with Routing {
  import pirmeta._

  def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = {
    pmap.flatMap[CUMap] { cumap => 
      dbgblk(s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
    }.flatMap { pmap => route(cuP, pmap) }
  }

  def staticPlace(pmap:PIRMap) = log {
    bind[CUMap.K, CUMap.V, PIRMap](
      pnodes=pmap.cumap.freeKeys.toSeq,
      snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap.freeValues(cuP).toList.sortBy { v => -m.cumap.freeKeys(v).size },
      init=pmap,
      bindLambda=bindLambda _
    )
  }

}
