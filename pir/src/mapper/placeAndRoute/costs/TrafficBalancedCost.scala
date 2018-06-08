package pir
package mapper

import pir.node._
import spade.node._

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

