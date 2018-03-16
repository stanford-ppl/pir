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

  val constrains = {
    var list:List[Constrain] = Nil
    list :+= SwitchConstrain
    list :+= AFGConstrain
    list :+= DFGConstrain
    list :+= SramConstrain
    list :+= VectorFIFOConstrain
    list :+= ScalarFIFOConstrain
    list :+= ControlFIFOConstrain
    list :+= StageConstrain
    list :+= LaneConstrain
    list
  }

  def initCUMap:CUMap = {
    var cumap = CUMap.empty
    val topP = compiler.top
    val topS = compiler.arch.top
    val pnodes = topP.collectDown[CUMap.K]()
    val snodes = topS.collectDown[CUMap.V]()
    cumap ++= pnodes.toSet -> snodes.toSet
    prune(cumap)
  }

  override def runPass(runner:RunPass[_]) =  {
    import runner._
    pirmeta.pirMap = Some(PIRMap.empty(this))
    val cumap = pirMap.get.cumap
    dbgblk(s"mapping") {
      cumap.freeMap.keys.foreach { pn =>
        dbg(s"${quote(pn)} <- ${cumap.sortedFree(pn).map(quote)}")
      }
    }
  }

}
