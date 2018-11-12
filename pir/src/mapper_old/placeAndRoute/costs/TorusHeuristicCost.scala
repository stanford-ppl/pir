package pir
package mapper

import pir.node._
import spade.node._

trait TorusHeuristicCost extends HeuristicCost { self:Routing =>
  import spademeta._

  lazy val maxWidth = designS.top match {
    case top:StaticGridTop => top.sbrx - top.sblx
    case top:DynamicGridTop => top.rtrx - top.rtlx
  }
  lazy val maxHeight = designS.top match {
    case top:StaticGridTop => top.sbuy - top.sbby
    case top:DynamicGridTop => top.rtuy - top.rtby
  }
  override def heuristicCost(
    next:Routable,
    end:Routable
  ) = if (isTorus(designS)) dbgblk(s"TorusHeuristicCost(next=${quote(next)}, end=${quote(end)})") {
    zipMap(indexOf.get(next), indexOf.get(end)) { case (List(nx, ny), List(ex, ey)) =>
      // Manhattan distance between two points with wrap around
      val xdelt = Math.abs(nx - ex)
      val ydelt = Math.abs(ny - ey)
      val xdist = Math.min(xdelt, maxWidth - xdelt)
      val ydist = Math.min(ydelt, maxHeight - ydelt)
      xdist + ydist
    }.getOrElse(0)
  } else super.heuristicCost(next, end)
}
