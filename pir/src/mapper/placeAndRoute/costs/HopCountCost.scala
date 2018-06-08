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

