package pir
package pass

import pir.node._
import prism.graph._

class DependencyDuplication(implicit compiler:PIR) extends DependencyAnalyzer {

  override def runPass = {
    val ctxs = pirTop.collectDown[Context]()
    // Compute and mirror in two passes to avoid duplication in mirroring
    dupDeps(ctxs)
  }

}

trait DependencyAnalyzer extends PIRPass with Transformer {
  
  private def bound(from:Option[Context], to:Context,visitFunc:Node[PIRNode] => List[PIRNode]):Node[PIRNode] => List[PIRNode] = { n:Node[PIRNode] =>
    var deps = visitFunc(n)
    deps = deps.filter {
      case x:Memory => false
      case x:HostWrite => false
      case x:LocalInAccess => false
      case x:GlobalInput => false
      case x:GlobalOutput => false
      case x =>
        //dbg(s"$x $to")
        //dbg(!x.isDescendentOf(to))
        //dbg(from.fold(true) { from => x.isDescendentOf(from) })
        !x.isDescendentOf(to) && 
        from.fold(true) { from => x.isDescendentOf(from) } 
    }
    deps = cover[PIRNode, Controller](deps)
    deps
  }

  def getDeps(
    x:PIRNode, 
    from:Option[Context]=None,
    to:Option[Context]=None,
    visitFunc:Node[PIRNode] => List[PIRNode] = visitGlobalIn _
  ):List[PIRNode] = dbgblk(s"getDeps($x)"){
    val toCtx = to.getOrElse(x.ancestorTree.collectFirst { case ctx:Context => ctx }.get)
    var deps = x.accum(visitFunc=bound(from,toCtx,visitFunc))
    deps = deps.filterNot(_ == x)
    if (compiler.hasRun[DependencyDuplication]) {
      // If dependency contains controller, copy all dependencies of the leaf controller
      val ctrlers = deps.collect { case ctrler:Controller => ctrler }.distinct
      val leaf = assertOneOrLess(ctrlers.flatMap { _.leaves }.distinct, 
        s"leaf of ${ctrlers}")
      dbg(s"leaf=$leaf")
      leaf.foreach { leaf =>
        if (leaf != x && !deps.contains(leaf)) {
          deps ++= (leaf.descendentTree ++ getDeps(leaf, from, Some(toCtx), visitFunc))
          deps = deps.distinct
        }
      }
      from.foreach { from =>
        val hasInput = (deps ++ toCtx.children).exists { _.isInstanceOf[LocalOutAccess] }
        val hasCtrler = (toCtx.children ++ ctrlers).exists { _.isInstanceOf[Controller] }
        if (!hasInput && !hasCtrler) {
          val leaf = assertOneOrLess(from.collectDown[Controller]().filter { _.isLeaf }, s"$from.leaf ctrler")
          leaf.foreach { leaf =>
            deps ++= (leaf.descendentTree ++ getDeps(leaf, Some(from), Some(toCtx), visitFunc))
            deps = deps.distinct
          }
        }
      }
    }
    dbg(s"deps=$deps")
    deps
  }

  def dupDeps(ctx:Context, from:Option[Context]):Map[PIRNode,PIRNode] = dbgblk(s"dupDeps($ctx)") {
    val deps = getDeps(ctx, from)
    within(ctx) { mirrorAll(deps).toMap }.as[Map[PIRNode, PIRNode]]
  }

  def swapDeps(ctx:Context, mapping:Map[PIRNode,PIRNode]) = dbgblk(s"swapDeps($ctx)"){
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
    val nonMemNeighbors = ctx.neighbors.filterNot { n => 
      n.isInstanceOf[Memory] || n.isInstanceOf[LocalAccess] || n.isInstanceOf[GlobalIO]
    }
    assert(nonMemNeighbors.isEmpty, 
      s"$ctx has non Memory neighbors after DependencyDuplication = $nonMemNeighbors")
  }

  def dupDeps(ctxs:List[Context], from:Option[Context]=None):Unit = {
    val mappings = ctxs.map { ctx => (ctx, dupDeps(ctx, from)) }
    mappings.foreach { case (ctx, mapping) => swapDeps(ctx, mapping) }
    ctxs.foreach { check }
  }

}
