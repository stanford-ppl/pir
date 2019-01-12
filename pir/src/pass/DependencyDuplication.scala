package pir
package pass

import pir.node._
import prism.graph._

class DependencyDuplication(implicit compiler:PIR) extends DependencyAnalyzer {

  override def runPass = {
    val ctxs = pirTop.collectDown[Context]()
    // Compute and mirror in two passes to avoid duplication in mirroring
    val mappings = ctxs.map { ctx => (ctx, dupDeps(ctx)) }
    mappings.foreach { case (ctx, mapping) => swapDeps(ctx, mapping) }
    ctxs.foreach { check }
  }

}

trait DependencyAnalyzer extends PIRPass with Transformer {

  private def bound(visitFunc:N => List[N]):N => List[N] = { n:N =>
    visitFunc(n).filter{ 
      case x:Memory => false
      case x:HostWrite => false
      case x:LocalInAccess => false
      //case x:LocalOutAccess => // prevent infinate loop in case of cycle
        //val from = x.in.T
        //from != n && !from.isDescendentOf(n)
      case x:GlobalInput => false
      case x:GlobalOutput => false
      case _ => true
    }
  }

  def getDeps(
    x:PIRNode, 
    visitFunc:N => List[N] = visitGlobalIn _
  ):Seq[PIRNode] = dbgblk(s"getDeps($x)"){
    var deps = x.accum(visitFunc=cover[Controller](bound(visitFunc)))
    deps = deps.filterNot(_ == x)
    if (compiler.hasRun[DependencyDuplication]) {
      val ctrlers = deps.collect { case ctrler:Controller => ctrler }
      val leaf = assertOneOrLess(ctrlers.flatMap { _.leaves }.distinct, 
        s"leaf of ${ctrlers}")
      dbg(s"leaf=$leaf")
      leaf.foreach { leaf =>
        if (leaf != x) {
          deps ++= leaf +: (leaf.descendents++getDeps(leaf, visitFunc))
          deps = deps.distinct
        }
      }
    }
    deps.as[List[PIRNode]]
  }

  def dupDeps(ctx:Context) = dbgblk(s"dupDeps($ctx)") {
    val deps = getDeps(ctx)
    within(ctx) { mirrorAll(deps).toMap }
  }

  def swapDeps(ctx:Context, mapping:Map[N,N]) = dbgblk(s"swapDeps($ctx)"){
    mapping.foreach { case (dep, mdep) =>
      dep.localDepeds.filter { _.isDescendentOf(ctx) }.foreach { n =>
        swapDep(n, dep, mdep)
      }
    }
    //breakPoint(s"swapDep($ctx)", None)
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
