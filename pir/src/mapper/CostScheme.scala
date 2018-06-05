package pir
package mapper

import pir.node._
import spade.node._

trait HopCountCost extends DynamicRouting {

  // Only use hop count for cost
  override protected def linkCost(
    pmap:PIRMap, 
    marker:MKMap.V, 
    tail:Edge, 
    head:Edge
  ) = if (costScheme=="hop") 1 else super.linkCost(pmap, marker, tail, head)

}

trait TrafficBalancedCost extends DynamicRouting {

  override protected def linkCost(
    pmap:PIRMap, 
    marker:MKMap.V, 
    tail:Edge, 
    head:Edge
  ) = if (costScheme=="balanced") 1 else super.linkCost(pmap, marker, tail, head)

}

