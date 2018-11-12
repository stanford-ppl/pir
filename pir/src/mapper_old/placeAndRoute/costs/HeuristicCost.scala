package pir
package mapper

import pir.node._
import spade.node._

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
  ):C = throw PIRException(s"Unsupported Target isMesh=${isMesh(designS)} isTorus=${isTorus(designS)} isCMesh=${isCMesh(designS)}")

}
