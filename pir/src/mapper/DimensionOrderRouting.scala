package pir
package mapper

import pir.node._
import spade.node._

trait DimensionOrderRouting extends DynamicRouting {

  override protected def tailToHeadEdges (
    pmap:PIRMap, 
    marker:MKMap.V
  )(
    tail:Edge
  ):List[Edge] = if (routingAlgo=="dor" || routingAlgo=="DOR") {
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})",buffer=false) {
      tail.connected.map { _.asInstanceOf[Edge] }
    }
  } else super.tailToHeadEdges(pmap, marker)(tail)

}
