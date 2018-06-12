package pir
package mapper

import pir.node._
import spade.node._

trait HopCountCost extends CostScheme { self:Routing =>

  import PIRConfig._
  // Use hop count for cost
  override def linkCost(
    pmap:PIRMap,
    start:GlobalIO,
    end:Option[GlobalIO]
  )(
    from:PT,
    to:PT
  ) = {
    val cost = if (enableHopCountCost) 1 else 0
    cost + super.linkCost(pmap, start, end)(from, to) 
  }

}

