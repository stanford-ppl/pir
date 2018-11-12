package pir
package mapper

import pir.node._
import spade.node._

trait MeshHeuristicCost extends HeuristicCost { self:Routing =>
  import spademeta._

  override def heuristicCost(
    next:Routable,
    end:Routable
  ) = if (isMesh(designS) || isCMesh(designS)) dbgblk(s"MeshHeuristicCost(next=${quote(next)}, end=${quote(end)})") {
    zipMap(indexOf.get(next), indexOf.get(end)) { case (List(nx, ny), List(ex, ey)) =>
      // Manhattan distance between two points
      Math.abs(nx - ex) + Math.abs(ny - ey)
    }.getOrElse(0)
  } else super.heuristicCost(next, end)
}

