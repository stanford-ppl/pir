package pir
package mapper

import pir.node._
import spade.node._

trait DynamicRouting extends Routing {

  import PIRConfig._
  
  lazy val costScheme = option[String]("routing-cost")
  lazy val routingAlgo = option[String]("routing-algo")

  protected def linkCost(pmap:PIRMap, marker:MKMap.V, tail:Edge, head:Edge):Int = 
    throw PIRException(s"Unsupported Cost Scheme $costScheme")

  override def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge
  ):List[(Edge, C)] = if (isDynamic(tail)) {
    val marker = markerOf(start)
    val heads = tailToHeadEdges(pmap, marker)(tail)
    heads.map { head => (head, if (isInternal(tail, head)) 0 else linkCost(pmap, marker, tail, head)) }
  } else super.tailToHead(pmap, start)(tail)

  protected def tailToHeadEdges (
    pmap:PIRMap, 
    marker:MKMap.V
  )(
    tail:Edge
  ):List[Edge] = throw PIRException(s"Unsupported Routing Algorithm $routingAlgo")

  private def set(mkmap:MKMap, port:PT, marker:GlobalOutput):MKMap = {
    dbg(s"setMKMap: ${quote(port.src)}.${quote(port)} - ${quote(marker)}")
    mkmap + (port, marker)
  }

  override def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = if (isDynamic(route)) dbgblk(s"set route from ${quote(headP)} to ${quote(tailP)}",buffer=false){
    Right(pmap).map { pmap =>
      pmap.map[MKMap] { mkmap =>
        var mk = mkmap
        val marker = markerOf(headP)
        route.foreach { case (tailS, headS) =>
          mk = set(mk, tailS, marker)
          mk = set(mk, headS, marker)
        }
        mk
      }
    }
  } else super.set(pmap, route, headP, tailP)

}
