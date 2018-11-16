package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._

class HardConstrainPruner(implicit compiler:PIR) extends ConstrainPruner {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap =>
      flatFold(x.freeValues, x) { case (x, v) =>
        val prefixs = getPrefixs(v.params.get)
        x.filterNotAtValue(v) { k => getPrefixs(k) != prefixs }
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

  def getPrefixs(n:N) = memorize("getPrefixs", n) { n =>
    dbgblk(s"getPrefixs($n)") {
      n match {
        case n:GlobalContainer =>
          val isAFG = n.isInstanceOf[ArgFringe]
          val isMC = n.isInstanceOf[DRAMFringe]
          val isDAG = n.siblingDepeds.exists{_.isInstanceOf[DRAMFringe]}
          (isAFG, isMC, isDAG)
        case n:Parameter =>
          val isDAG:Boolean = n.to[DramAGParam].map { _ => true }.getOrElse {
            val pattern = n.collectOut[Pattern]().head
            val hasDAG = pattern.cuParams.exists { _.isInstanceOf[DramAGParam] }
            n.to[CUParam].fold (false) { _ => !hasDAG }
          }
          val isMC = n.isInstanceOf[MCParam]
          val isAFG = n.isInstanceOf[ArgFringeParam]
          (isAFG, isMC, isDAG)
      }
    }
  }

}
