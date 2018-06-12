package pir
package mapper

import pir.node._
import spade.node._

trait StaticRouting extends Routing {

  override def portsS(
    gio:GlobalIO, 
    cuS:Routable, 
    pmap:PIRMap
  ) (implicit 
    portTp:PT => ClassTag[_<:PinType], 
    gioTp:GlobalIO => ClassTag[_<:PinType]
  ):List[PT] = {
    val marker = markerOf(gio)
    val markerTp = (gioTp(marker), isGlobalInput(gio))
    val ports = cuS.collectDown[PT]().filter { port => (portTp(port), isInput(port)) == markerTp }

    val (marked, unmarked) = ports.partition { port => getStaticMarkerOf(pmap, port).nonEmpty }
    val markedAndMatched = marked.filter { port => staticMarkerOf(pmap, port) == marker }
    gio match {
      case gio:GlobalOutput => markedAndMatched ++ unmarked // one to many
      case gio:GlobalInput if markedAndMatched.nonEmpty => markedAndMatched // one to one
      case gio:GlobalInput => unmarked // one to one
    }
  }

  override def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge,
    backPointers:BackPointer
  ):List[Edge] = if (isStatic(tail)) {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})") {
      val (marked, unmarked) = tail.connected.partition { head => getStaticMarkerOf(pmap, head).nonEmpty }
      val markedAndMatched = marked.filter { head => staticMarkerOf(pmap, head) == marker }
      dbg(s"marked=${quote(marked)}")
      dbg(s"unmarked=${quote(unmarked)}")
      dbg(s"markedAndMatched=${quote(markedAndMatched)}")
      (tail match {
        case out:OutputEdge[_] => markedAndMatched ++ unmarked // one to many
        case in:InputEdge[_] if markedAndMatched.nonEmpty => markedAndMatched // one to one
        case in:InputEdge[_] => unmarked // one to one
      }).toList.asInstanceOf[List[Edge]]
    }
  } else super.tailToHead(pmap, start)(tail, backPointers)

  override def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = if (isStatic(route)) dbgblk(s"set route from ${quote(headP)} to ${quote(tailP)}",buffer=false){
    var pm = pmap
    pm = setFanIn(pmap, route, headP, tailP)
    pm = setMarker(pmap, route, headP, tailP)
    Right(pm)
  } else super.set(pmap, route, headP, tailP)

}
