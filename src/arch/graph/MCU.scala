package pir.plasticine.graph

import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.config.ConfigFactory
import pir.plasticine.simulation._
import pir.plasticine.util._
import pir.exceptions._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set

case class MemoryComputeUnitParam(
  sbufSize:Int = 16,
  vbufSize:Int = 16,
  numRegs:Int = 16,
  numCtrs:Int = 8,
  numUDCs:Int = 0
) extends ComputeUnitParam() {
  val numSRAMs = 1
  val sramSize = 512
  override val numLanes = 1

  /* Parameters */
  def config(cu:MemoryComputeUnit)(implicit spade:Spade) = {
    cu.addWAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    cu.addRAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    cu.numScalarBufs(4)
    cu.numVecBufs(cu.vins.size)
    cu.color(0 until numCtrs, CounterReg)
    cu.color(7, ReadAddrReg).color(8, WriteAddrReg)
    cu.color(8 until 8 + cu.numScalarBufs, ScalarInReg)
    cu.color(12 until 12 + cu.numVecBufs, VecInReg)
    cu.genConnections
  }
}

class MemoryComputeUnit(override val param:MemoryComputeUnitParam=MemoryComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "mcu"
  import spademeta._
  import param._

  override lazy val ctrlBox:MemoryCtrlBox = new MemoryCtrlBox(numUDCs)

  private val _wastages:ListBuffer[WAStage] = ListBuffer.empty // Write Addr Stages
  private val _rastages:ListBuffer[RAStage] = ListBuffer.empty // Read Addr Stages
  def wastages:List[WAStage] = _wastages.toList // Write Addr Stages
  def rastages:List[RAStage] = _rastages.toList // Read Addr Stages
  def addWAstages(stages:List[WAStage]) = { _wastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRAstages(stages:List[RAStage]) = { _rastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addWAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addWAstages(List.fill(numStage) { WAStage(numOprds=numOprds, regs, ops)}); this // Write Addr stage 
  } 
  def addRAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addRAstages(List.fill(numStage) { RAStage(numOprds=numOprds, regs, ops)}); this // Read Addr stage 
  } 
  def sram = srams.head
  override def config = param.config(this)
}

