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

  def getCtrlerDeps(from:Context, to:Option[Context]=None) = {
    val leaf = assertOneOrLess(from.collectDown[Controller]().filter { _.isLeaf }, s"$from.leaf ctrler")
    leaf.fold(List.empty[PIRNode]) { leaf =>
      (leaf.descendentTree ++ getDeps(leaf, Some(from), to)).distinct.toList
    }
  }


  def getDeps(
    x:PIRNode, 
    from:Option[Context]=None,
    to:Option[Context]=None,
  ):List[PIRNode] = dbgblk(s"getDeps($x, from=$from, to=$to)"){
    val toCtx = to.getOrElse(x.ancestorTree.collectFirst { case ctx:Context => ctx }.get)
    dbg(s"toCtx=$toCtx")
    def accumDeps(x:PIRNode) = x.accum(visitFunc=bound(from,toCtx,visitGlobalIn))
    var deps = accumDeps(x)
    deps = deps.filterNot(_ == x)
    dbg(s"rawDeps=$deps")
    val depCtrlers = deps.collect { case ctrler:Controller => ctrler }.distinct
    val toCtrlers = toCtx.children.collect { case c:Controller => c}
    if (compiler.hasRun[DependencyDuplication]) {
      // If dependency contains controller, copy all dependencies of the leaf controller
      assert(depCtrlers.isEmpty || toCtrlers.isEmpty, s"Dep contains controller=${depCtrlers} and $toCtx already contains controller=$toCtrlers")
      val leaf = assertOneOrLess(depCtrlers.flatMap { _.leaves }.distinct, 
        s"leaf of ${depCtrlers}")
      dbg(s"leaf=$leaf")
      leaf.foreach { leaf =>
        if (leaf != x && !deps.contains(leaf)) {
          deps ++= (leaf.descendentTree ++ accumDeps(leaf))
          deps = deps.distinct
        }
      }
      from.foreach { from =>
        val hasInput = (deps ++ toCtx.children).exists { _.isInstanceOf[LocalOutAccess] }
        val hasCtrler = depCtrlers.nonEmpty || toCtrlers.nonEmpty
        if (!hasInput && !hasCtrler) {
          deps ++= getCtrlerDeps(from, Some(toCtx))
          deps = deps.distinct
        }
      }
    } else {
      val cmds = toCtx.collectFirstChild[FringeCommand]
      if (cmds.isEmpty && !(depCtrlers ++ toCtrlers).exists { _.getCtrl == toCtx.getCtrl }) {
        toCtx.getCtrl.ctrler.v.foreach { ctxCtrler =>
          deps ++= (ctxCtrler.descendentTree ++ accumDeps(ctxCtrler))
          deps = deps.distinct
        }
      }
    }
    dbg(s"deps=$deps")
    deps
  }

  def mirrorDeps(to:Context, from:Option[Context]):Map[PIRNode,PIRNode] = dbgblk(s"mirrorDeps($to, $from)") {
    val deps = getDeps(to, from, Some(to))
    within(to) { mirrorAll(deps).toMap }.as[Map[PIRNode, PIRNode]]
  }

  def swapDeps(ctx:Context, mapping:Map[PIRNode,PIRNode]) = dbgblk(s"swapDeps($ctx)"){
    mapping.foreach { case (dep, mdep) =>
      dep.localDepeds.filter { _.isDescendentOf(ctx) }.foreach { n =>
        swapDep(n, dep, mdep)
      }
    }
    //breakPoint(s"swapDep($ctx)", None)
  }

  def dupDeps(ctx:Context, from:Context) = dbgblk(s"dupDeps(to=$ctx, from=$from)"){
    val mapping = mirrorDeps(ctx, from=Some(from))
    swapDeps(ctx, mapping)
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
    val mappings = ctxs.map { ctx => (ctx, mirrorDeps(ctx, from)) }
    mappings.foreach { case (ctx, mapping) => swapDeps(ctx, mapping) }
    ctxs.foreach { check }
  }

}
