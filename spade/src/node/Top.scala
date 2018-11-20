package spade
package node

import param._
import prism.graph._

case class Top(param:TopParam)(implicit env:Env) extends SpadeNode
trait Routable extends SpadeNode
case class CU(param:CUParam)(implicit env:Env) extends Routable
case class ArgFringe(param:ArgFringeParam)(implicit env:Env) extends Routable
case class MC(param:MCParam)(implicit env:Env) extends Routable
case class Connector()(implicit env:Env) extends Routable
case class StaticBundle(param:BundleParam)(implicit env:Env) extends Routable
case class DynamicBundle(param:BundleParam)(implicit env:Env) extends Routable

trait SpadeNodeUtil extends CollectorImplicit {
  implicit class SpadeNodeOp(n:SpadeNode) {
    def isConnector = n match {
      case n:Connector => true
      case _ => false
    }
  }
  implicit class SpadeTopOp(n:Top) {
    def cus = n.collectDown[Routable]().filterNot { _.isConnector }
  }
}
