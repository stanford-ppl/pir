package pir.pass

import pir._
import pir.node._
import pir.mapper._
import spade.node._
import prism._
import prism.util._

import scala.collection.mutable

class CUPruning(implicit compiler:PIR) extends PIRPass with ResourcePruning {
  import pirmeta._

  type N = PIRNode with Product
  def shouldRun = true

  constrains :+= SwitchConstrain
  constrains :+= AFGConstrain
  constrains :+= DFGConstrain
  constrains :+= SramConstrain
  constrains :+= VectorFIFOConstrain
  constrains :+= ScalarFIFOConstrain
  constrains :+= ControlFIFOConstrain
  constrains :+= StageConstrain
  constrains :+= LaneConstrain

  def initCUMap:CUMap = {
    var cumap = CUMap.empty
    val topP = compiler.top
    val topS = compiler.arch.top
    val pnodes = topP.collectDown[CUMap.K]()
    val snodes = topS.collectDown[CUMap.V]()
    cumap ++= pnodes.toSet -> snodes.toSet
    cumap
  }

  override def runPass(runner:RunPass[_]) =  {
    import runner._
    pirMap = pirMap.flatMap { pmap => prune(pmap.set[CUMap](initCUMap)) }
    pirMap.foreach { pmap =>
      dbgblk(s"mapping") {
        pmap.cumap.freeMap.keys.foreach { pn =>
          dbg(s"${quote(pn)} <- ${pmap.cumap.sortedFreeValues(pn).map(quote)}")
        }
      }
    }
  }

}
