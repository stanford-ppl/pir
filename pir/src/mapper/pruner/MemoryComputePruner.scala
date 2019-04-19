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
        info(s"Split $k into ${ks.size + 1} CUs")
        val nkcost = getCosts(k)
        if (notFit(nkcost, vcost)) {
          warn(s"$k still not fit after splitting $nkcost")
          e
        } else {
          Right(fg ++ (k, vs) ++ (ks, spadeTop.cus.toSet))
        }
      case x => super.recover(x)
    }
  }

  /*
   * Recursively split k by first remove out the largest addr calculation stage.
   * */
  def split(k:GlobalContainer, vcost:List[Cost[_]]):Set[CUMap.K] = dbgblk(s"split($k)"){
    val addrCtxs = k.collectDown[Context]().filter { ctx => ctx.collectDown[Access]().isEmpty }
    if (addrCtxs.isEmpty) return Set.empty

    val addrCtx = addrCtxs.maxBy { _.getCost[StageCost].quantity }
    val global = within(pirTop) { ComputeContainer() }
    val gouts = addrCtx.depeds.collect { case gout:GlobalOutput => gout }
    swapParent(addrCtx, global)
    gouts.foreach { gout => swapParent(gout, global) }
    insertGlobalIO(global)
    insertGlobalIO(k)
    k.collectChildren[GlobalInput].foreach { gin =>
      if (!gin.out.isConnected) removeNodes(List(gin))
    }
    resetCacheOn {
      case `k` => true
      case (`k`, _) => true
      case `addrCtx` => true
      case (`addrCtx`, _) => true
      case _ => false
    }
    val nkcost = getCosts(k)
    if (notFit(nkcost, vcost)) {
      split(k, vcost) + global
    } else {
      Set(global)
    }
  }

}
