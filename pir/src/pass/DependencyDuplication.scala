package pir
package pass

import pir.node._
import prism.graph._

class DependencyDuplication(implicit compiler:PIR) extends PIRPass with Transformer {

  override def runPass = {
    val ctxs = pirTop.collectDown[Context]()
    // Compute and mirror in two passes to avoid duplication in mirroring
    val mappings = ctxs.map { ctx => (ctx, dupDeps(ctx)) }
    mappings.foreach { case (ctx, mapping) => swapDeps(ctx, mapping) }
    ctxs.foreach { check }
  }

  def bound(visitFunc:N => List[N]):N => List[N] = { n:N =>
    visitFunc(n).filter{ 
      case x:Memory => false
      case x:HostWrite => false
      case x:LocalInAccess => false
      case x:LocalOutAccess => 
        val from = x.in.T
        from != n && !from.isDescendentOf(n)
      case _ => true
    }
  }

  def dupDeps(ctx:Context) = dbgblk(s"dupDeps($ctx)") {
    var deps = ctx.accum(visitFunc=cover[Controller](bound(visitGlobalIn _)), logger=Some(this))
    deps = deps.filterNot(n => n == ctx)
    within(ctx) { mirrorAll(deps).toMap }
  }

  def swapDeps(ctx:Context, mapping:Map[N,N]) = dbgblk(s"swapDeps($ctx)"){
    mapping.foreach { case (dep, mdep) =>
      dep.localDepeds.filter { _.isDescendentOf(ctx) }.foreach { n =>
        swapDep(n, dep, mdep)
      }
    }
  }

  def check(ctx:Context) = {
    ctx.collectDown[Controller]().groupBy { _.ctrl.get }.foreach { case (ctrl, ctrlers) =>
      assert(ctrlers.size==1, s"More than one controller for $ctrl in $ctx. ctrlers=$ctrlers")
    }
    val nonMemNeighbors = ctx.neighbors.filterNot { n => n.isInstanceOf[Memory] || n.isInstanceOf[LocalAccess] }
    assert(nonMemNeighbors.isEmpty, 
      s"$ctx has non Memory neighbors after DependencyDuplication = $nonMemNeighbors")
  }

}
