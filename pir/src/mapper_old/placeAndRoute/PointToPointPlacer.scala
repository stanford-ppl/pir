package pir
package mapper

import pir.node._

trait PointToPointPlacer extends Placer with BackTrackingMatch {
  import pirmeta._

  private def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = {
    pmap.flatMap[CUMap] { cumap => 
      dbgblk(s"set ${dquote(cuP)} -> ${dquote(cuS)}") { cumap.set(cuP,cuS) }
    }
  }

  override def place(pmap:PIRMap) = if (isPointToPoint(designS)) {
    bind(
      pnodes=pmap.cumap.freeKeys.toSeq,
      snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap.freeValues(cuP).toList,
      init=pmap,
      bindLambda=bindLambda _
    )(
      rankP = { case (cuP, m) => -m.cumap.freeValues(cuP).size },
      rankS = { case (cuP, cuS, m) => -m.cumap.freeKeys(cuS).size }
    )
  } else super.place(pmap)

}
