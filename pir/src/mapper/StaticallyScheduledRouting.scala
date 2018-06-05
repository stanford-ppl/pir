package pir
package mapper

import pir.node._
import spade.node._

trait StaticPlanedRouting extends DynamicRouting {

  override def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge
  ):List[Edge] = if (isDynamic(tail) && routingAlgo=="planed") {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})",buffer=false) {
      tail.connected.map { _.asInstanceOf[Edge] }
    }
  } else super.tailToHead(pmap, start)(tail)

}
