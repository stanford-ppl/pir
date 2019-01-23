package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class MemoryComputePruner(implicit compiler:PIR) extends CUPruner {

  override def getCosts(x:Any):List[Cost[_]] = {
    x match {
      case _:ComputeContainer => Nil
      case _:DRAMFringe => Nil
      case _ => 
        x.getCost[StageCost] ::
        x.getCost[InputCost] ::
        x.getCost[OutputCost] ::
        //x.getCost[FIFOCost] ::
        Nil
    }
  }

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val vs = fg.freeValuesOf(k)
        val kcost = getCosts(k)
        val vcost = assertOne(vs.map { getCosts(_) }, s"MemoryCU vcost")
        dbg(s"Recover $k")
        dbg(s"kcost=$kcost")
        dbg(s"vcost=$vcost")
        val ks = split(k).toSet
        newFG(fg, k, ks+k, vs)
      case x => super.recover(x)
    }
  }

  def split(k:GlobalContainer):Set[CUMap.K] = {
    val ctxs = k.collectDown[Context]()
    val globals = ctxs.map { split(_,k) }.toSet
    k.children.foreach { 
      case gin:GlobalInput if gin.out.T.isEmpty => removeNodes(List(gin))
      case gin => 
    }
    resetCacheOn {
      case `k` => true
      case (`k`, _) => true
      case k if ctxs.contains(k) => true
      case (k, _) if ctxs.contains(k) => true
      case _ => false
    }
    globals
  }

  def split(ctx:Context, k:GlobalContainer):GlobalContainer = dbgblk(s"split($ctx)") {
    val newCtx = within(k, ctx.ctrl.get) { Context() }
    dbg(s"newCtx=$newCtx")
    var localNnodes:List[PIRNode] = ctx.collectDown[Access]() 
    localNnodes ++= localNnodes.flatMap { _.accum(visitFunc=visitLocalOut _) }.distinct
    localNnodes ++= ctx.collectDown[TokenWrite]() 
    localNnodes ++= ctx.collectDown[TokenRead]()
    localNnodes.foreach { local => swapParent(local, newCtx) }
    bufferInput(newCtx)
    dupDeps(List(newCtx), from=Some(ctx))
    val global = within(pirTop) { ComputeContainer() }
    val gouts = ctx.depeds.collect { case gout:GlobalOutput => gout }
    swapParent(ctx, global)
    gouts.foreach { gout => swapParent(gout, global) }
    insertGlobalIO(global)
    insertGlobalIO(k)
    if (k.id==768) breakPoint(s"split $ctx in $k")
    global
  }

}
