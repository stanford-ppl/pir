package pir
package pass

import pir.node._
import prism.graph._

class DependencyDuplication(implicit compiler:PIR) extends DependencyAnalyzer with BufferAnalyzer {

  override def runPass = {
    val ctxs = pirTop.collectDown[Context]()
    dupDeps(ctxs)
    // ctxs.foreach { counterValidsToControllers }
    ctxs.foreach { insertControlBlock }
    ctxs.foreach { ctx => check(ctx) }
  }

}

trait DependencyAnalyzer extends PIRTransformer {
  
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
    deps = cover[PIRNode, ControlBlock](deps)
    deps
  }

  def getDeps(
    x:PIRNode, 
    from:Option[Context]=None,
    to:Option[Context]=None,
    logger:Option[Logging]=None,
  ):List[PIRNode] = dbgblk(s"getDeps($x, from=$from, to=$to)"){
    val toCtx = to.getOrElse(x.ancestorTree.collectFirst { case ctx:Context => ctx }.get)
    dbg(s"toCtx=$toCtx")
    def accumDeps(x:PIRNode) = x.accum(visitFunc=bound(from,toCtx,visitGlobalIn), logger=logger)
    var deps = accumDeps(x)
    deps = deps.filterNot(_ == x)
    dbg(s"rawDeps=$deps")
    if (compiler.hasRun[DependencyDuplication]) {
      val depcb = deps.collectFirst { case cb:ControlBlock => cb }
      val tocb = toCtx.collectFirstChild[ControlBlock]
      assert(depcb.isEmpty || tocb.isEmpty, s"Dep contains $depcb but $toCtx already contains $tocb")
      from.foreach { from =>
        val hasInput = (deps ++ toCtx.children).exists { _.isInstanceOf[LocalOutAccess] }
        val hasCtrler = depcb.nonEmpty || tocb.nonEmpty
        if (!hasInput && !hasCtrler) {
          val cb = from.collectFirstChild[ControlBlock]
          deps ++= cb.map(accumDeps).getOrElse(Nil)
          deps ++= cb.map { _.descendents.toList }.getOrElse(Nil)
          deps = deps.distinct
        }
      }
    } else {
      def copyCtrlers:Unit = {
        if (toCtx.streaming.get) return
        val toCtrlers = toCtx.children.collect { case c:Controller => c}
        if (toCtrlers.exists { _.getCtrl == toCtx.getCtrl }) return
        val depCtrlers = deps.collect { case ctrler:Controller => ctrler }.distinct
        if (depCtrlers.exists { _.getCtrl == toCtx.getCtrl }) return
        toCtx.getCtrl.ctrler.v.foreach { ctxCtrler =>
          deps ++= (ctxCtrler.descendentTree ++ accumDeps(ctxCtrler))
          deps = deps.distinct
        }
      }
      copyCtrlers
    }
    dbg(s"deps=$deps")
    deps
  }

  def mirrorDeps(to:Context, from:Option[Context]):Map[IR,IR] = dbgblk(s"mirrorDeps($to, $from)") {
    val deps = getDeps(to, from, Some(to),logger=Some(this))
    within(to) { mirrorAll(deps).toMap }
  }

  def swapDeps(ctx:Context, mapping:Map[IR,IR]) = dbgblk(s"swapDeps($ctx)"){
    mapping.foreach { 
      case (dep:PIRNode, mdep:PIRNode) =>
        dep.localDepeds.filter { _.isDescendentOf(ctx) }.foreach { n =>
          swapDep(n, dep, mdep)
        }
      case (dep, mdep) =>
    }
    //breakPoint(s"swapDep($ctx)", None)
  }

  def dupDeps(ctx:Context, from:Option[Context]):Map[IR,IR] = dbgblk(s"dupDeps(to=$ctx, from=$from)"){
    val mapping = mirrorDeps(ctx, from=from)
    swapDeps(ctx, mapping)
    mapping
  }

  def check(ctx:Context) = {
    ctx.collectDown[Controller]().groupBy { _.ctrl.get }.foreach { case (ctrl, ctrlers) =>
      assert(ctrlers.size==1, s"More than one controller for $ctrl in $ctx. ctrlers=$ctrlers")
    }
    val girun = compiler.hasRun[GlobalInsertion]
    val nonMemNeighbors = ctx.neighbors.filterNot { 
      case n:Memory => true
      case n:GlobalIO => true
      case n:LocalAccess => true
      case n => false
    }
    assert(nonMemNeighbors.isEmpty, 
      s"$ctx has non Memory neighbors after DependencyDuplication = $nonMemNeighbors")
  }

  def insertControlBlock(ctx:Context) = {
    val ctrlers = ctx.collectDown[Controller]().sortBy { _.getCtrl.ancestors.size }
    if (ctx.followToken.connected.length != 0) {
      assert(ctx.followToken.connected.length == 1)
      dbg(s"$ctx has followToken connected!")
      ctrlers.map { ctrl =>
        dbg(s"\tCtrler: $ctrl")
      }
      ctx.followToken.disconnect
    }
    if (ctrlers.size > 0) {
      val cb = within(ctx, ctx.getCtrl) {
        ControlBlock().ctrlers(ctrlers)
      }
      dbg(s"add $cb in $ctx")
    }
  }

  def counterValidsToControllers(ctx:Context) = {
    val counters = ctx.collectDown[Counter]()
    dbg(s"Valids to Controllers: $ctx")
    counters.filter { _.isInstanceOf[StridedCounter] }.map { ctr =>
      val ctrler = ctr.ctrler
      val valids = ctr.valids
      def indices = valids.flatMap { valid =>
        if (valid.out.connected.size != 0 && valid.is.size == 1) {
          Some(valid.is.head)
        } else {
          None
        }
      }
      if (indices.size != 0 && indices.forall(_ == indices.head)) {
        val idx = indices.head
        dbg(s"\tCounter: $ctr controller: $ctrler idx: $idx")
        valids.map { valid =>
          dbg(s"\t\tValid: $valid (${valid.is}) -> (${valid.out.connected})")
          within(ctx, ctrler) {
            val trueVal = stage(Const(true))
            // valid.out.disconnect
            // TODO: swap this to ctrlr.laneValid
            swapOutput(valid.out, trueVal.out)
            dbg(s"\t\tBecomes: ${trueVal} -> (${trueVal.out.connected})")
          }
        }
        ctr.valIter(idx)
        indices.map { i => assert(i == idx) }
      } else {
        dbg(s"\tCounter: $ctr controller: $ctrler NO MATCH")
        valids.map { valid =>
          dbg(s"\t\tValid: $valid (${valid.is}) -> (${valid.out.connected})")
        }
      }
    }
  }

  def dupDeps(ctxs:List[Context], from:Option[Context]=None):Unit = {
    val mappings = ctxs.map { ctx => (ctx, mirrorDeps(ctx, from)) }
    mappings.foreach { case (ctx, mapping) => swapDeps(ctx, mapping) }
  }

}
