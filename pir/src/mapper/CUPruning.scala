package pir.mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

import scala.collection.mutable

class CUPruning(implicit compiler:PIR) extends PIRPass with ResourcePruning {
  import pirmeta._

  type N = PIRNode with Product

  constrains += new AFGConstrain
  constrains += new DFGConstrain
  constrains += new SramConstrain
  constrains += new VectorFIFOConstrain
  constrains += new ScalarFIFOConstrain
  constrains += new ControlFIFOConstrain
  constrains += new VectorInputConstrain
  constrains += new ScalarInputConstrain
  constrains += new ControlInputConstrain
  constrains += new VectorOutputConstrain
  constrains += new ScalarOutputConstrain
  constrains += new ControlOutputConstrain
  constrains += new StageConstrain
  constrains += new LaneConstrain
  //constrains += new CUArcConsistencyConstrain
  constrains += new CUMatchingConstrain

  def initCUMap:CUMap = {
    var cumap = CUMap.empty
    val topP = compiler.top
    val topS = compiler.arch.top
    val pnodes = topP.collectDown[CUMap.K]()
    val snodes = topS.collectDown[CUMap.V]().filterNot { _.isInstanceOf[SwitchBox] }
    cumap ++= pnodes.toSet -> snodes.toSet
    cumap
  }

  override def runPass =  {
    pirMap = pirMap.flatMap { pmap => log(prune(pmap.set[CUMap](initCUMap))) }
  }

  override def finPass = {
    super.finPass
    pirMap.left.map {
      case f:MappingFailure =>
        fail(s"CUPruning failed. ${f}")
        runner.setFailed
    }
  }

}
