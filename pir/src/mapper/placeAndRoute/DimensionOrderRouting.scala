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
  ):List[Edge] = if (isDynamicLink(tail) && (routingAlgo=="dor" || routingAlgo=="DOR" || routingAlgo == "proute")) {
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
          dbg(s"${quote(p2)} -> ${quote(p1)} -> ${quote(next)}")
          val dim1 = getDimension(p2, p1)
          val dim2 = getDimension(p1, next)
          dbg(s"dimension: $dim1 $dim2")
          (start, dim1, dim2) match {
            case (start, dim1, dim2) if dim1 == dim2 => true // same direction
            case (start:GlobalOutput, 0, 1) => true // out -> input: dimension 0 followed by dimension 1
            case (start:GlobalInput, 1, 0) => true //  input -> output: dimension 1 followed by dimension 0
            case _ => false
          }
      }
    }
  }

}
