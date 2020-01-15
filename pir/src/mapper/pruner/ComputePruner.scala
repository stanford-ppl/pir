package pir
package mapper

import pir.node._
import prism.graph._
import prism.codegen.CSVPrinter
import spade.param._
import prism.collection.immutable._

class ComputePruner(implicit compiler:PIR) extends CUPruner with ComputePartitioner with CSVPrinter { self =>

  override def runPass = {
    withCSV(config.splitDir, "splitTime.csv") {
      super.runPass
    }
  }

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
        tic
        val ks = withAlgo(config.splitAlgo) { split(k, vcost).toSet }
        val splitTime = toc("ms")
        val row = newRow
        row("global") = k.id
        row("time_ms") = splitTime
        val srcCtx = k.collectDown[Context]().flatMap { ctx => ctx.getCtrl.srcCtx.v }.mkString(",")
        info(s"Split $k ${srcCtx} into ${ks.size} CUs $kcost in ${splitTime}ms")
        //breakPoint(s"$k")
        val (rs, cs) = ks.partition { case cu:MemoryContainer => true; case _ => false }
        newFG(fg, k, cs, vs).map { _ ++ (rs -> spadeTop.cus.toSet) }
      case x => super.recover(x)
    }
  }

}
