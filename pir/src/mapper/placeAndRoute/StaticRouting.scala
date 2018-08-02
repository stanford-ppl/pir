package pir
package mapper

import pir.node._
import spade.node._

trait StaticRouting extends Routing {

  override def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge,
    backPointers:BackPointer
  ):List[Edge] = if (isStaticLink(tail)) {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})") {
      val (marked, unmarked) = tail.connected.partition { head => getStaticMarkerOf(pmap, head).nonEmpty }
      val markedAndMatched = marked.filter { head => staticMarkerOf(pmap, head) == marker }
      dbg(s"marked=${quote(marked)}")
      dbg(s"unmarked=${quote(unmarked)}")
      dbg(s"markedAndMatched=${quote(markedAndMatched)}")
      // In search always search from input -> output
      // Once there's a matched source, take the matched source to coalescing braodcast
      (tail match {
        case out:OutputEdge[_] =>  // in search always from out to in
          //val sources = markedAndMatched.flatMap { in => pmap.fimap.get(in.asInstanceOf[FIMap.K]) }.toSet
          //val otherSource = sources.filter { _ != out }
          //if (otherSource.nonEmpty) {
            /*
             *       +----------+
             * other | ---------| markedAndMatched
             * out   |          | unmarked
             *       +----------+
             * */
            //val onlyFromOut = unmarked.filter { in => otherSource.forall { !_.isConnectedTo(in) } }
            //onlyFromOut
          //} else { // I am the only source
            //markedAndMatched ++ unmarked
          //}
          markedAndMatched.filter { head =>
            pmap.fimap.get(head.asInstanceOf[FIMap.K]).fold { true } { _ == tail }
          } ++ unmarked
        case in:InputEdge[_] if pmap.fimap.contains(in) => List(pmap.fimap(in))
        case in:InputEdge[_] if markedAndMatched.nonEmpty => markedAndMatched
        case in:InputEdge[_] => unmarked // one to one
      }).toList.asInstanceOf[List[Edge]]
    }
  } else super.tailToHead(pmap, start)(tail, backPointers)

  override def set(
    pmap:PIRMap, 
    route:Route, 
    marker:MKMap.V
  ):EOption[PIRMap] = if (isStaticLink(route)) dbgblk(s"set route for ${quote(marker)}",buffer=false){
    var pm = pmap
    try {
      pm = setFanIn(pm, route, marker)
      pm = setMarker(pm, route, marker)
    } catch {
      case e:PIRException => throw InvalidMapping(pmap, e)
      case x:Throwable => throw x
    }
    Right(pm)
  } else super.set(pmap, route, marker)

}

case class InvalidMapping(pmap:PIRMap, e:PIRException) extends PIRException {
  def msg = s"${e.msg}"
}
