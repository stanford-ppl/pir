package pir
package mapper

import pir.node._
import spade.node._

trait RoutingUtil extends SpadeNodeUtil with PIRNodeUtil { self:Logging =>

  val spademeta:SpadeMetadata
  import spademeta._

  private type PT = Port[_<:PinType]
  private type A = (PT, PT)
  private type Route = List[A]

  private def underRouter(x:SpadeNode) = {
    routableOf(x).get.isInstanceOf[Router]
  }
  def isDynamicLink(from:Edge, to:Edge):Boolean = {
    underRouter(from.src) && underRouter(to.src)
  }
  def isDynamicLink(edge:Edge):Boolean = {
    underRouter(edge.src) && edge.connected.forall { edge => underRouter(edge.src) }
  }
  def isDynamicLink(port:PT):Boolean = isDynamicLink(port.external)
  def isDynamicLink(route:Route):Boolean = route.map { _._1 }.exists { port => underRouter(port) }
  def isStaticLink(from:Edge, to:Edge):Boolean = !isDynamicLink(from, to)
  def isStaticLink(edge:Edge):Boolean = !isDynamicLink(edge)
  def isStaticLink(route:Route):Boolean = !isDynamicLink(route)
  def isStaticLink(port:PT):Boolean = !isDynamicLink(port)

  def markerOf(gio:GlobalIO) = gio match {
    case gio:GlobalInput => goutOf(gio).get
    case gio:GlobalOutput => gio
  }

  def getStaticMarkerOf(mkmap:MKMap, port:PT):Option[MKMap.V] = {
    val mks = mkmap.get(port)
    mks.flatMap { mks =>
      assert(mks.size<=1)
      mks.headOption
    }
  }

  def getStaticMarkerOf(pmap:PIRMap, port:PT):Option[MKMap.V] = getStaticMarkerOf(pmap.mkmap, port)
  def getStaticMarkerOf(pmap:PIRMap, edge:prism.node.Edge[_]):Option[MKMap.V] = getStaticMarkerOf(pmap, edge.src.asInstanceOf[PT])
  def staticMarkerOf(pmap:PIRMap, port:PT):MKMap.V = getStaticMarkerOf(pmap, port).get
  def staticMarkerOf(pmap:PIRMap, edge:prism.node.Edge[_]):MKMap.V = getStaticMarkerOf(pmap, edge).get

  def setMarker(mkmap:MKMap, port:PT, marker:GlobalOutput):MKMap = {
    if (isStatic(port))
      getStaticMarkerOf(mkmap, port).foreach { mk => assert(mk == marker, s"$mk != marker") }
    mkmap + (port, marker)
  }

  def setMarker(
    pmap:PIRMap, 
    route:Route, 
    marker:MKMap.V,
  ):PIRMap = {
    pmap.map[MKMap] { mkmap =>
      var mk = mkmap
      var info = s"setMarker(${quote(marker)}): "
      route.foreach { case (tailS, headS) =>
        mk = setMarker(mk, tailS, marker)
        mk = setMarker(mk, headS, marker)
        info += s",${quote(tailS)},${quote(headS)}"
      }
      dbg(info)
      mk
    }
  }

  def setFanIn(fimap:FIMap, tail:Edge, head:Edge):FIMap = {
    (tail, head) match {
      case (tail:FIMap.K, head:FIMap.V) =>
        fimap + (tail, head)
      case (tail:FIMap.V, head:FIMap.K) =>
        fimap + (head, tail)
      case (tail, head) => throw PIRException(s"impossible case $tail, $head")
    }
  }

  def setFanIn(
    pmap:PIRMap, 
    route:Route, 
    marker:MKMap.V,
  ):PIRMap = {
    pmap.map[FIMap] { fimap =>
      var fm = fimap
      var info = s"setFanIn(${quote(marker)}): "
      route.iterator.sliding(size=2,step=1).foreach {
        case List((tail1S, head1S), (tail2S, head2S)) =>
          fm = setFanIn(fm, tail1S.external, head1S.external)
          fm = setFanIn(fm, head1S.internal, tail2S.internal)
          info += s", ${quote(tail1S.external)} - ${quote(head1S.external)}, ${quote(head1S.internal)} - ${quote(tail2S.internal)}"
        case List((tail1S, head1S)) =>  // If only 1 element in route
      }
      val (tailS, headS) = route.last
      fm = setFanIn(fm, tailS.external, headS.external)
      info += s", ${quote(tailS.external)} - ${quote(headS.external)}"
      dbg(info)
      fm
    }
  }

  sealed trait RelativeDirection
  case object U extends RelativeDirection
  case object D extends RelativeDirection
  case object L extends RelativeDirection
  case object R extends RelativeDirection

  def getRelativeDirection(from:Routable, to:Routable) = {
    val List(fx, fy) = indexOf(from)
    val List(tx, ty) = indexOf(to)
    if ((fx == tx) && (fy < ty)) U
    else if ((fx == tx) && (fy > ty)) D
    else if ((fx < tx) && (fy == ty)) R
    else if ((fx > tx) && (fy == ty)) L
    else throw PIRException(s"Unexpected direction $fx $fy $tx $ty")
  }

  sealed trait Direction
  case object N extends Direction
  case object S extends Direction
  case object W extends Direction
  case object E extends Direction

  def getDirection(from:Routable, to:Routable) = {
    val List(fx, fy) = indexOf(from)
    val List(tx, ty) = indexOf(to)
    if ((fx == tx) && (fy < ty)) N
    else if ((fx == tx) && (fy > ty)) S
    else if ((fx < tx) && (fy == ty)) E
    else if ((fx > tx) && (fy == ty)) W
    else throw PIRException(s"Unexpected direction $fx $fy $tx $ty")
  }

  def getDimension(from:Routable, to:Routable):Int = {
    assertOne(indexOf(from).zip(indexOf(to)).zipWithIndex.flatMap{ case ((f, t), dim) => 
      if (f != t) Some(dim) else None
    }, s"changing dimension from ${quote(from)} to ${quote(to)}")
  }

}
