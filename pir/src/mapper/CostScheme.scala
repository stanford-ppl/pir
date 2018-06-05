package pir
package mapper

import pir.node._
import spade.node._

trait HopCountCost extends CostScheme { self:Routing =>

  // Use hop count for cost
  override def linkCost(
    pmap:PIRMap,
    start:GlobalIO,
    end:Option[GlobalIO]
  )(
    from:PT,
    to:PT
  ) = {
    val cost = 1
    cost + super.linkCost(pmap, start, end)(from, to) 
  }

}

trait TrafficBalancedCost extends CostScheme { self:Routing =>

  //TODO:
  override def linkCost(
    pmap:PIRMap,
    start:GlobalIO,
    end:Option[GlobalIO]
  )(
    from:PT,
    to:PT
  )  = {
    val cost = if (PIRConfig.enableBalancedCost) 0 else 0
    cost + super.linkCost(pmap, start, end)(from, to) 
  }

}

trait CostScheme { self:Routing =>

  import PIRConfig._

  def linkCost(
    pmap:PIRMap,
    start:GlobalIO,
    end:Option[GlobalIO]
  )(
    from:PT,
    to:PT
  ) = 0

}

trait HeuristicCost extends CostScheme { self:Routing =>

  override def linkCost(
    pmap:PIRMap,
    start:GlobalIO,
    end:Option[GlobalIO]
  )(
    from:PT,
    to:PT
  ) = {
    val cost = end.fold(0) { end =>
      if (PIRConfig.enableHeuristicCost) {
        val ecuP = globalOf(end).get
        val ecuS = pmap.cumap.mappedValue(ecuP)
        val ncuS = routableOf(to.src).get
        heuristicCost(ncuS, ecuS)
      } else 0
    }
    cost + super.linkCost(pmap, start, end)(from, to) 
  }

  def heuristicCost(
    next:Routable,
    end:Routable
  ):C = throw PIRException(s"Unsupported Target")

}

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
