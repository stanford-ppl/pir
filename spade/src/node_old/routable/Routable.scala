package spade
package node
import param._

trait Routable extends Module {
  val param:Parameter
  val bundles:List[Bundle[_<:PinType]]
  bundles.foreach { _.setParent(this) }

  def bundle[B<:PinType:ClassTag] = bundles.flatMap{ bundle => as[Bundle,B](bundle) }.headOption
}
