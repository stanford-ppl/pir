package pir
package mapper
import pir.pass._
import prism.graph._
import prism.collection.immutable._

trait CUPruner extends ConstrainPruner with CUCostUtil with PIRTransformer with DependencyAnalyzer {
  def getCosts(x:Any):List[Cost[_]] = Nil

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys, x) { case (x, k) =>
        val kc = getCosts(k)
        val pruned = x.filterNotAtKey(k) { v => notFit(kc, getCosts(v)) }
        recover(pruned)
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

  def recover(x:EOption[CUMap]):EOption[CUMap] = x

  def newFG(fg:CUMap, k:CUMap.K, ks:Set[CUMap.K], vs:Set[CUMap.V]) = {
    assert(ks.size > 1, s"$k not partitioned")
    val vcosts = vs.map { v => getCosts(v) }
    ks.foreach { k => 
      val kcost = getCosts(k)
      val canFit = vcosts.exists { vcost => fit(kcost, vcost) }
      if (!canFit) bug(s"After splitting $k's cost $kcost > vcosts=$vcosts")
    }
    Right(fg.mapFreeMap { _ - k ++ (ks, vs) })
  }

  override def removeNodes[N<:Node[N]](nodes:Iterable[Node[N]]) = {
    super.removeNodes(nodes)
    nodes.foreach { removeCache }
  }

  def removeCache(n:Any) = {
    resetCacheOn {
      case `n` => true
      case (`n`, _) => true
      case _ => false
    }
  }

  override def fail(f:Any) = {
    super.fail(f)
    f match {
      case e@InvalidFactorGraph(fg, k:CUMap.K) =>
        err[Unit](s"Constrain failed on $k", exception=false)
        err[Unit](s"$k costs:", exception=false)
        val kc = getCosts(k)
        kc.foreach { kc =>
          err[Unit](s"${kc}:", exception=false)
        }
      case _ =>
    }
  }

}
