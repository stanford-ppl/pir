package spade
package node

import param._
case class ArgFringe(
  param:ArgFringeParam, 
  bundles:List[Bundle[_<:PinType]]
  )(implicit design:SpadeDesign) extends Terminal {
}
