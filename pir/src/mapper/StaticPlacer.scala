package pir
package mapper

import pir.node._

trait StaticDynamicPlacer extends Placer with StaticDynamicRouter with BackTrackingMatch {
  import pirmeta._

  private def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = {
    pmap.flatMap[CUMap] { cumap => 
      dbgblk(s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
    }.flatMap { pmap => route(cuP, pmap) }.map { pmap => snapshot(pmap) }
  }

  override def place(pmap:PIRMap) = if (isStatic(designS) || isDynamic(designS)) {
    bind(
      pnodes=pmap.cumap.freeKeys.toSeq,
      snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap.freeValues(cuP).toList,
      init=pmap,
      bindLambda=bindLambda _
    )(
      rankP = { case (cuP, m) => (-m.cumap.freeValues(cuP).size, cuP.collectDown[GlobalIO]().size) },
      rankS = { case (cuS,m) => -m.cumap.freeKeys(cuS).size }
    )
  } else super.place(pmap)

}

trait StaticDynamicRouter extends Routing 
  with StaticRouting 
  with DynamicRouting 
    with DimensionOrderRouting with StaticPlanedRouting 
    with HopCountCost with TrafficBalancedCost
