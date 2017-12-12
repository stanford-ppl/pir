package pir.mapper

import pir._
import pir.util.typealias.{Seq => _, _}

import spade._
import spade.traversal.PlasticineGraphTraversal

import pirc._

import scala.language.existentials
import scala.reflect.runtime.universe._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

abstract class AStarRouter(implicit design:PIR) extends UniformCostRouter {

  def heuristic(current:S, end:S):Int = {
    if (current.isInstanceOf[PTop] || end.isInstanceOf[PTop]) return 0
    val (cx, cy) = current.coord
    val (ex, ey) = end.coord
    Math.abs(cx - ex) + Math.abs(cy - ey)
  }

  override def advance[T<:IO,H<:IO,PT<:PIO,PH<:PIO](
    mp:M, 
    tail:T, 
    head:H
  )(n:S, prevEdge:Option[(PT, PH)], c:C):Seq[(S, (PT,PH), C)] = {
    
    val results = super.advance(mp, tail, head)(n, prevEdge, c)

    // Span
    if (!mp.pmmap.contains(ctrler(head))) return results

    // Search
    val end = mp.pmmap(ctrler(head))

    val hcurrent = heuristic(n, end)
    results.map { case (next, edge, cost) =>
      (next, edge, cost + heuristic(next, end) - hcurrent)
    }
  }

}

class VectorAStarRouter()(implicit val design:PIR) extends AStarRouter with VectorRouter

class ScalarAStarRouter()(implicit val design:PIR) extends AStarRouter with ScalarRouter

class ControlAStarRouter()(implicit val design:PIR) extends AStarRouter with ControlRouter
