package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class SoftConstrainPruner(implicit compiler:PIR) extends ConstrainPruner with CUCostUtil with DFSPartitioner {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys, x) { case (x, k) =>
        val kc = getCosts(k,k)
        recover(x.filterNotAtKey(k) { v => notFit(kc, getCosts(k,v)) })
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

  override def fail(f:Any) = {
    super.fail(f)
    f match {
      case e@InvalidFactorGraph(fg, k:CUMap.K) =>
        err(s"Constrain failed on $k", exception=false)
        err(s"$k costs:", exception=false)
        val kc = getCosts(k,k)
        kc.foreach { kc =>
          err(s"${kc}:", exception=false)
        }
      case _ =>
    }
  }

}
