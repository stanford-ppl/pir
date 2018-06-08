package pir
package mapper

import pir.node._
import spade.node._

trait DimensionOrderRouting extends DynamicRouting {
  import spademeta._
  import PIRConfig._

  override def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge,
    backPointers:BackPointer
  ):List[Edge] = if (isDynamic(tail) && (routingAlgo=="dor" || routingAlgo=="DOR")) {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})",buffer=false) {
      var heads = tail.connected.map { _.asInstanceOf[Edge] }
      if (isExternal(tail)) {
        val current = tail.src.asInstanceOf[PT].src.asInstanceOf[Bundle[_]]
        val (nodes, actions, cost) = extractHistory(current, backPointers)
        heads = heads.filter { head =>
          val reached = head.src.asInstanceOf[PT].src.asInstanceOf[Bundle[_]]
          isValidTurn(start, nodes:+current, reached)
        }
      }
      heads
    }
  } else super.tailToHead(pmap, start)(tail, backPointers)

  def isExternal(tail:Edge) = {
    val pt = tail.src.asInstanceOf[PT]
    pt.external == tail
  }

  def isValidTurn(start:GlobalIO, prevBundles:List[Bundle[_]], nextBundle:Bundle[_]) = {
    val prevs = prevBundles.map { b => routableOf(b).get }
    val next = routableOf(nextBundle).get
    dbgblk(s"isValidTurn(prevs=${quote(prevs)}, next=${quote(next)})") {
      (prevs, next) match {
        case (List(p1:Terminal), _) => true // Any direction
        case (List(p2:Terminal, p1:Router), _) => true // First step, any direction
        case (prevs, n:Terminal) => true // Reaching terminal, any direction
        case (rest:+(p2:Router):+(p1:Router), n:Router) =>
          val List(p2x, p2y) = indexOf(p2)
          val List(p1x, p1y) = indexOf(p1)
          val List(tx, ty) = indexOf(next)
          dbg(s"${quote(p2)} -> ${quote(p1)} -> ${quote(next)}")
          val dir1 = getDirection(p2x, p2y, p1x, p1y)
          val dir2 = getDirection(p1x, p1y, tx, ty)
          dbg(s"directions: $dir1 $dir2")
          (start, dir1, dir2) match {
            case (start, dir1, dir2) if dir1 == dir2 => true
            case (start:GlobalOutput, Up, Right) => true
            case (start:GlobalOutput, Right, Down) => true
            case (start:GlobalOutput, Down, Left) => true
            case (start:GlobalOutput, Left, Up) => true
            case (start:GlobalInput, Up, Left) => true
            case (start:GlobalInput, Right, Up) => true
            case (start:GlobalInput, Down, Right) => true
            case (start:GlobalInput, Left, Down) => true
            case _ => false
          }
      }
    }
  }

  sealed trait Direction
  case object Up extends Direction
  case object Down extends Direction
  case object Left extends Direction
  case object Right extends Direction

  def getDirection(fx:Int, fy:Int, tx:Int, ty:Int) = {
    if ((fx == tx) && (fy < ty)) Up
    else if ((fx == tx) && (fy > ty)) Down
    else if ((fx < tx) && (fy == ty)) Right
    else if ((fx > tx) && (fy == ty)) Left
    else throw PIRException(s"Unexpected direction $fx $fy $tx $ty")
  }

}
