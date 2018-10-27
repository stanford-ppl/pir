package pir
package pass

import pir.node._
import prism.graph._

class DependencyDuplication(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with Transformer with UnitTraversal {
  val forward = false

  override def visitNode(n:N) = {
    n match {
      case n:Context => duplicateDependency(n)
      case n =>
    }
    super.visitNode(n)
  }

  def duplicateDependency(ctx:Context):Unit = dbgblk(s"duplicateDependency($ctx)"){
    var depedCtxs = ctx.accumTill[Memory](visitFunc=lift[Context](visitGlobalOut _)).collect { case x:Context => x }
    depedCtxs = depedCtxs.filterNot(n => n == ctx)
    dbg(s"depedCtxs=$depedCtxs")
    depedCtxs.foreach { depedCtx =>duplicateDependency(depedCtx) }
    var deps = ctx.accumTill[Memory](visitFunc=cover[Controller](visitGlobalIn _))
    deps = deps.filterNot(n => n == ctx || n.isInstanceOf[Memory])
    dbg(s"deps=$deps")
    val mapping = within(ctx) { mirrorAll(deps) }
    dbg(s"mapping=$mapping")
    mapping.foreach { case (dep, mdep) =>
      dep.localDepeds.filter { _.isDescendentOf(ctx) }.foreach { n =>
        swapDep(n, dep, mdep)
      }
    }
  }

  def check(ctx:Context) = {
    ctx.collectDown[Controller]().groupBy { _.ctrl.get }.foreach { case (ctrl, ctrlers) =>
      dbg(s"ctrl=$ctrl, ctrlers=$ctrlers")
      assert(ctrlers.size==1, s"More than one controller for $ctrl in $ctx. ctrlers=$ctrlers")
    }

    val nonMemNeighbors = ctx.neighbors.filterNot { _.isInstanceOf[Memory] }
    assert(nonMemNeighbors.nonEmpty, 
      s"$ctx has non Memory neighbors after DependencyDuplication = $nonMemNeighbors")
  }

  def visitDeps(n:N):List[N] = visitGlobalIn(n).flatMap {
    case counter:Counter => 
      val ctrl = counter.collectUp[Controller]().head
      ctrl::ctrl.descendents
    case x => List(x)
  }.distinct

}

trait ContextTraversal extends PIRTraversal with TopologicalTraversal {
  override def visitIn(n:N):List[N] = visitGlobalIn(n).map {
    case x:Memory if !x.isBuffer => x
    case x:Context => x
    case x => x.collectUp[Context]().head
  }
  override def visitOut(n:N):List[N] = visitGlobalOut(n).map {
    case x:Memory if !x.isBuffer => x
    case x:Context => x
    case x => x.collectUp[Context]().head
  }
  override def runPass = {
    val contexts = top.collectDown[Context]()
    val mems = top.collectDown[Memory]().filterNot(_.isBuffer)
    traverseScope(contexts ++ mems, zero)
  }
}

