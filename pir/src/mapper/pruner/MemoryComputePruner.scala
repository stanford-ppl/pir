package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class MemoryComputePruner(implicit compiler:PIR) extends CUPruner with MemoryComputePartitioner {

  override def getCosts(x:Any):List[Cost[_]] = getComputeCost(x)

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case e@Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val vs = fg.freeValuesOf(k)
        val kcost = getCosts(k)
        val vcost = assertOne(vs.map { getCosts(_) }, s"MemoryCU vcost")
        dbg(s"Recover $k")
        dbg(s"kcost=$kcost")
        dbg(s"vcost=$vcost")
        val ks = split(k, vcost).toSet
        val mem = quoteSrcCtx(k.collectDown[Memory]().head)
        info(s"Split $k ${mem} into ${ks.size + 1} CUs $kcost")
        //breakPoint(s"$k")
        val nkcost = getCosts(k)
        val memPrunerHasRun = compiler.hasRun[MemoryPruner]
        if (memPrunerHasRun && notFit(nkcost, vcost)) {
          warn(s"$k still not fit after splitting $nkcost")
          //breakPoint(s"k=$k")
          e
        } else {
          Right(fg ++ (k, vs) ++ (ks, spadeTop.cus.toSet))
        }
      case x => super.recover(x)
    }
  }

}

trait MemoryComputePartitioner extends PIRTransformer with CUCostUtil {

  def getComputeCost(x:Any):List[Cost[_]] = {
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

  lazy val schedular = new PIRTraversal with BFSTopologicalTraversal with TreeSchedular { 
    val forward = false
    override def visitIn(n:N) = visitLocalIn(n).collect { case ctx:Context if !ctx.hasChild[Access] => ctx }
    override def visitOut(n:N) = visitLocalOut(n).collect { case ctx:Context if !ctx.hasChild[Access] => ctx }
  }

  /*
   * Recursively split k by first remove out the largest addr calculation ctx that doesn't have
   * local dependency.
   * */
  def split(k:GlobalContainer, vcost:List[Cost[_]]):Set[CUMap.K] = dbgblk(s"split($k)"){
    val memPrunerHasRun = compiler.hasRun[MemoryPruner]
    val mem = k.collectDown[Memory]().head
    val addrCtxs = mem.accesses.map { access =>
      val actx = access.ctx.get
      schedular.scheduleNode(actx).map { _.filterNot { 
          case ctx:Context =>
            var cond = ctx.hasChild[Access]
            if (!memPrunerHasRun) {
              cond |= ctx.hasChild[Shuffle]
            }
            cond
          case _ => true
        }
      }.filterNot { _.isEmpty }
    }.filterNot { _.isEmpty }
    if (addrCtxs.isEmpty) return Set.empty

    val addrCtx = addrCtxs.toStream.map { _.last }.flatten.maxBy { _.getCost[StageCost].quantity }
    dbg(s"addrCtxs=$addrCtxs")
    dbg(s"move addrCtx=$addrCtx")
    //breakPoint(s"move addrCtx=$addrCtx")
    val global = within(pirTop) { ComputeContainer() }
    swapParent(addrCtx, global)
    resetCacheOn {
      case `k` => true
      case (`k`, _) => true
      case `addrCtx` => true
      case (`addrCtx`, _) => true
      case _ => false
    }
    val nkcost = getComputeCost(k)
    //breakPoint(s"split $k")
    if (notFit(nkcost, vcost)) {
      split(k, vcost) + global
    } else {
      Set(global)
    }
  }
}

