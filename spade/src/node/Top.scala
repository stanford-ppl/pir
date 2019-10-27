package spade
package node

import param._
import prism.graph._

case class Top(param:TopParam)(implicit env:Env) extends SpadeNode
trait Routable extends SpadeNode
trait Terminal extends Routable {
  val param:Parameter
}
case class CU(param:CUParam)(implicit env:Env) extends Terminal
case class ArgFringe(param:ArgFringeParam)(implicit env:Env) extends Terminal
case class MC(param:MCParam)(implicit env:Env) extends Terminal
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
    def cus(implicit compiler:Compiler) = {
      val rts = n.collectDown[Terminal]()
      if (n.param.isAsic || n.param.isInf) rts else {
        var reservePCUs = compiler.config.option[Int]("reserve-pcu")
        var reservePMUs = compiler.config.option[Int]("reserve-pmu")
        rts.filterNot { cu =>
          cu.params match {
            case Some(param:PCUParam) if reservePCUs > 0 => reservePCUs-=1; true
            case Some(param:PMUParam) if reservePMUs > 0 => reservePMUs-=1; true
            case param => false
          }
        }
      }
    }
  }
}
