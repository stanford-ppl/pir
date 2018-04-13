package pir.mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

import scala.collection.mutable

class CUPruning(implicit compiler:PIR) extends PIRPass with ResourcePruning {
  import pirmeta._

  type N = PIRNode with Product
  def shouldRun = PIRConfig.mapping

  constrains += AFGConstrain
  constrains += DFGConstrain
  constrains += SramConstrain
  constrains += VectorFIFOConstrain
  constrains += ScalarFIFOConstrain
  constrains += ControlFIFOConstrain
  constrains += VectorInputConstrain
  constrains += ScalarInputConstrain
  constrains += ControlInputConstrain
  constrains += VectorOutputConstrain
  constrains += ScalarOutputConstrain
  constrains += ControlOutputConstrain
  constrains += StageConstrain
  constrains += LaneConstrain
  //constrains += CUArcConsistencyConstrain

  def initCUMap:CUMap = {
    var cumap = CUMap.empty
    val topP = compiler.top
    val topS = compiler.arch.top
    val pnodes = topP.collectDown[CUMap.K]()
    val snodes = topS.collectDown[CUMap.V]().filterNot { _.isInstanceOf[SwitchBox] }
    cumap ++= pnodes.toSet -> snodes.toSet
    cumap
  }

  override def runPass(runner:RunPass[_]) =  {
    pirMap = pirMap.flatMap { pmap => log(prune(pmap.set[CUMap](initCUMap))) }
    pirMap.left.map {
      case f@InvalidFactorGraph(fg, k) =>
        fail(s"CUPruning failed. ${f}")
    }
  }

}
