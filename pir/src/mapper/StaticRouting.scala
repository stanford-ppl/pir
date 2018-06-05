package pir
package mapper

import pir.node._
import spade.node._

trait StaticRouting extends Routing {

  private def getMarkerOf(mkmap:MKMap, port:PT):Option[MKMap.V] = {
    val mks = mkmap.get(port)
    mks.flatMap { mks =>
      assert(mks.size<=1)
      mks.headOption
    }
  }

  private def getMarkerOf(pmap:PIRMap, port:PT):Option[MKMap.V] = getMarkerOf(pmap.mkmap, port)
  private def getMarkerOf(pmap:PIRMap, edge:prism.node.Edge[_]):Option[MKMap.V] = getMarkerOf(pmap, edge.src.asInstanceOf[PT])
  private def markerOf(pmap:PIRMap, port:PT):MKMap.V = getMarkerOf(pmap, port).get
  private def markerOf(pmap:PIRMap, edge:prism.node.Edge[_]):MKMap.V = getMarkerOf(pmap, edge).get

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

    val (marked, unmarked) = ports.partition { port => getMarkerOf(pmap, port).nonEmpty }
    val markedAndMatched = marked.filter { port => markerOf(pmap, port) == marker }
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
    tail:Edge
  ):List[(Edge, C)] = if (isStatic(tail)) {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})",buffer=false, flush=false) {
      val (marked, unmarked) = tail.connected.partition { head => getMarkerOf(pmap, head).nonEmpty }
      val markedAndMatched = marked.filter { head => markerOf(pmap, head) == marker }
      dbg(s"marked=${quote(marked)}")
      dbg(s"unmarked=${quote(unmarked)}")
      dbg(s"markedAndMatched=${quote(markedAndMatched)}")
      val heads = (tail match {
        case out:OutputEdge[_] => markedAndMatched ++ unmarked // one to many
        case in:InputEdge[_] if markedAndMatched.nonEmpty => markedAndMatched // one to one
        case in:InputEdge[_] => unmarked // one to one
      }).toList.asInstanceOf[List[Edge]]
      // Cost is just the hop counts
      heads.map { head => (head, if (isInternal(tail, head)) 0 else 1) }
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
    getMarkerOf(mkmap, port).foreach { mk => assert(mk == marker, s"$mk != marker") }
    mkmap + (port, marker)
  }

  override def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = if (isStatic(route)) dbgblk(s"set route from ${quote(headP)} to ${quote(tailP)}",buffer=false){
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
