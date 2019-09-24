package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class ComputePruner(implicit compiler:PIR) extends CUPruner with ComputePartitioner {

  override def getCosts(x:Any):List[Cost[_]] = {
    x match {
      case _:MemoryContainer => Nil
      case _:DRAMFringe => Nil
      case x => 
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
        val vcost = vs.map { v => (getCosts(v), v.params.get) }.maxBy { 
          case (List(StageCost(sc), InputCost(sin, vin), OutputCost(sout,vout)), param) => 
            (param.isInstanceOf[PCUParam], sc, vin, vout, sin, sout)
        }._1
        dbg(s"Recover $k")
        dbg(s"kcost=$kcost")
        dbg(s"vcost=$vcost")
        val ctx = k.collectDown[Context]().flatMap { ctx => ctx.getCtrl.srcCtx.v }.mkString(",")
        val ks = split(k, vcost).toSet
        info(s"Split $k ${ctx} into ${ks.size} CUs $kcost")
        breakPoint(s"$k")
        newFG(fg, k, ks, vs)
      case x => super.recover(x)
    }
  }

}
