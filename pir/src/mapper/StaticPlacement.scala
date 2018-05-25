package pir
package mapper

import pir.node._

trait StaticPlacement extends BackTrackingMatch with StaticRouting { self:PIRPass =>
  import pirmeta._

  def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = {
    pmap.flatMap[CUMap] { cumap => 
      dbgblk(s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
    }.flatMap { pmap => route(cuP, pmap) }
  }

  def staticPlace(pmap:PIRMap) = log {
    bind(
      pnodes=pmap.cumap.freeKeys.toSeq,
      snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap.freeValues(cuP).toList,
      init=pmap,
      bindLambda=bindLambda _
    )(
      rankP = { case (cuP, m) => (-m.cumap.freeValues(cuP).size, cuP.collectDown[GlobalIO]().size) },
      rankS = { case (cuS,m) => m.cumap.freeKeys(cuS).size }
    )
  }

}
