package spade
package node

import param._
case class AsicTop(
  override val param:AsicTopParam
)(implicit design:SpadeDesign) extends Top {
  val networks = Nil
}
