package pir
package mapper

import pir.node._
import spade.node._

trait DimensionOrderRouting extends DynamicRouting {

  override def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge,
    backPointers:BackPointer
  ):List[Edge] = if (isDynamic(tail) && (routingAlgo=="dor" || routingAlgo=="DOR")) {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})",buffer=false) {
      tail.connected.map { _.asInstanceOf[Edge] }
    }
  } else super.tailToHead(pmap, start)(tail, backPointers)

}
