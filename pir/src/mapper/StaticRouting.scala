package pir
package mapper

import pir.node._
import spade.node._

trait StaticRouting extends Routing {

  private def markerOf(gio:GlobalIO) = gio match {
    case gio:GlobalInput => goutOf(gio).get
    case gio:GlobalOutput => gio
  }

  override def portsS(
    gio:GlobalIO, 
    cuS:Routable, 
    pmap:PIRMap
  ) (implicit 
    portTp:PT => ClassTag[_<:PinType], 
    gioTp:GlobalIO => ClassTag[_<:PinType]
  ):List[PT] = if (isStatic(designS)) {
    val marker = markerOf(gio)
    val markerTp = (gioTp(marker), isGlobalInput(gio))
    val ports = cuS.collectDown[PT]().filter { port => (portTp(port), isInput(port)) == markerTp }

    val (marked, unmarked) = ports.partition { port => pmap.mkmap.contains(port) }
    val markedAndMatched = marked.filter { port => pmap.mkmap(port) == marker }
    gio match {
      case gio:GlobalOutput => markedAndMatched ++ unmarked // one to many
      case gio:GlobalInput if markedAndMatched.nonEmpty => markedAndMatched // one to one
      case gio:GlobalInput => unmarked // one to one
    }
  } else super.portsS(gio, cuS, pmap)

  override def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge
  ):List[Edge] = if (isStatic(designS)) {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})",buffer=false) {
      val (marked, unmarked) = tail.connected.partition { head => pmap.mkmap.contains(head.src.asInstanceOf[PT]) }
      val markedAndMatched = marked.filter { head => pmap.mkmap(head.src.asInstanceOf[PT]) == marker }
      dbg(s"marked=${quote(marked)}")
      dbg(s"unmarked=${quote(unmarked)}")
      dbg(s"markedAndMatched=${quote(markedAndMatched)}")
      (tail match {
        case out:OutputEdge[_] => markedAndMatched ++ unmarked // one to many
        case in:InputEdge[_] if markedAndMatched.nonEmpty => markedAndMatched // one to one
        case in:InputEdge[_] => unmarked // one to one
      }).toList.asInstanceOf[List[Edge]]
    }
  } else super.tailToHead(pmap, start)(tail)

  private def set(fimap:FIMap, tail:Edge, head:Edge):FIMap = {
    dbg(s"setFIMap: ${quote(tail.src)}.${quote(tail)} - ${quote(head.src)}.${quote(head)}")
    (tail, head) match {
      case (tail:FIMap.K, head:FIMap.V) =>
        fimap + (tail, head)
      case (tail:FIMap.V, head:FIMap.K) =>
        fimap + (head, tail)
      case (tail, head) => throw PIRException(s"impossible case $tail, $head")
    }
  }

  private def set(mkmap:MKMap, port:PT, marker:GlobalOutput):MKMap = {
    dbg(s"setMKMap: ${quote(port.src)}.${quote(port)} - ${quote(marker)}")
    mkmap + (port, marker)
  }

  override def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = if (isStatic(designS)) dbgblk(s"set route from ${quote(headP)} to ${quote(tailP)}",buffer=false){
    Right(pmap).map { pmap =>
      pmap.map[FIMap] { fimap =>
        var fm = fimap
        route.iterator.sliding(size=2,step=1).foreach {
          case List((tail1S, head1S), (tail2S, head2S)) =>
            fm = set(fm, tail1S.external, head1S.external)
            fm = set(fm, head1S.internal, tail2S.internal)
          case List((tail1S, head1S)) =>  // If only 1 element in route
        }
        val (tailS, headS) = route.last
        fm = set(fm, tailS.external, headS.external)
        fm
      }
    }.map { pmap =>
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
