package pir
package mapper

import pir.node._
import spade.node._
import prism.collection.immutable._
import prism.util.Memorization

import scala.collection.mutable

trait CUPruner extends PIRPass with Memorization {
  import pirmeta._

  val constrains = ListBuffer[CUConstrain]()
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

  def initCUMap:EOption[PIRMap] = dbgblk(s"initCUMap") {
    pirMap.map { _.set[CUMap] { 
      var cumap = CUMap.empty
      val topP = compiler.top
      val topS = compiler.arch.top
      val pnodes = topP.collectDown[CUMap.K]()
      val snodes = topS.collectDown[CUMap.V]().filterNot { _.isInstanceOf[SwitchBox] }
      cumap ++= pnodes.toSet -> snodes.toSet
      cumap
    } }
  }

  memorizing = true
  def prune(cumap:CUMap):EOption[CUMap] = dbgblk(s"prune") {
    resetAllCaches
    flatFold(constrains, cumap) { case (cumap, constrain) => constrain.prune(cumap) }
  }

  override def resetAllCaches = {
    super.resetAllCaches
    dbg(s"resetAllCaches")
  }

}

