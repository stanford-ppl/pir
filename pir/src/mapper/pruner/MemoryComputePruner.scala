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
          Right(fg ++ (k, vs) ++ (ks, getAvailableCUs.toSet))
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

  lazy val scheduler = new PIRTraversal with BFSTopologicalTraversal with Scheduler { 
    val forward = true
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
    // Deped[Parallel[Context]]
    val actxs = mem.accesses.map { _.ctx.get }
    val ctxs = k.collectChildren[Context].filterNot { ctx =>
      var cond = ctx.hasChild[Access]
      if (!memPrunerHasRun) {
        cond |= ctx.hasChild[Shuffle]
      }
      cond
    }
    val (depFree, deped) = ctxs.partition { scheduler.isDepFree(_) }
    val (empty,nonEmpty) = depFree.partition { _.getCost[StageCost].quantity==0 }
    val addrCtx = nonEmpty.maxOptionBy { _.getCost[StageCost].quantity } orElse {
      if (deped.isEmpty) None else empty.headOption
    }
    dbg(s"depFree=$depFree nonEmpty=$nonEmpty empty=$empty")
    addrCtx.map { addrCtx =>
      dbg(s"addrCtx=$addrCtx")
      //breakPoint(s"split(${k}) move=$addrCtx depFree=$depFree")
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
        Set[CUMap.K](global)
      }
    }.getOrElse(Set.empty[CUMap.K])
  }
}

