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
          e
        } else {
          Right(fg ++ (k, vs) ++ (ks, spadeTop.cus.toSet))
        }
      case x => super.recover(x)
    }
  }

  /*
   * Recursively split k by first remove out the largest addr calculation ctx that doesn't have
   * local dependency.
   * */
  def split(k:GlobalContainer, vcost:List[Cost[_]]):Set[CUMap.K] = dbgblk(s"split($k)"){
    val memPrunerHasRun = compiler.hasRun[MemoryPruner]
    val addrCtxs = k.collectDown[Context]().filterNot { ctx => 
      var cond = ctx.hasChild[Access]
      if (!memPrunerHasRun) {
        cond |= ctx.hasChild[Shuffle]
      }
      cond
    }
    if (addrCtxs.isEmpty) return Set.empty

    val addrCtx = addrCtxs.maxBy { _.getCost[StageCost].quantity }
    dbg(s"move addrCtx=$addrCtx")
    val global = within(pirTop) { ComputeContainer() }
    swapParent(addrCtx, global)
    resetCacheOn {
      case `k` => true
      case (`k`, _) => true
      case `addrCtx` => true
      case (`addrCtx`, _) => true
      case _ => false
    }
    val nkcost = getCosts(k)
    //breakPoint(s"split $k")
    if (notFit(nkcost, vcost)) {
      split(k, vcost) + global
    } else {
      Set(global)
    }
  }

}
