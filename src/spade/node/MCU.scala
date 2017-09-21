package spade.node

import spade._
import spade.util._

import pirc.enums._
import pirc.util._
import pirc.exceptions._

import scala.collection.mutable.ListBuffer

case class PreloadMemoryComputeParam (
  override val sbufSize:Int = 16,
  override val vbufSize:Int = 16,
  override val numRegs:Int = 16,
  override val numCtrs:Int = 8,
  override val muxSize:Int = 10,
  override val numUDCs:Int = 5
) extends MemoryComputeUnitParam (
  numVins = ConfigFactory.plasticineConf.vinPmu,
  numVouts = ConfigFactory.plasticineConf.voutPmu,
  numSins = ConfigFactory.plasticineConf.sinPmu,
  numSouts = ConfigFactory.plasticineConf.soutPmu,
  numRegs  = ConfigFactory.plasticineConf.regsPmu,
  numStages = ConfigFactory.plasticineConf.rw
) with PreLoadSpadeParam

class MemoryComputeUnitParam(
  val sbufSize:Int = 16,
  val vbufSize:Int = 16,
  val numVins:Int = 4,
  val numVouts:Int = 4,
  val numSins:Int = 4,
  val numSouts:Int = 4,
  val numRegs:Int = 16,
  val numStages:Int = 8,
  val numCtrs:Int = 8,
  val muxSize:Int = 10,
  val numUDCs:Int = 0
) extends ComputeUnitParam() {
  val numSRAMs = 1
  val sramSize = 512 * 1024 / 4
  override lazy val numLanes = 1

  /* Parameters */
  def config(cu:MemoryComputeUnit)(implicit spade:Spade) = {
    cu.addRegstages(numStage=numStages, numOprds=3, fixOps ++ otherOps)
    //cu.addWAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    //cu.addRAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    assert(cu.sins.size >= numSins, s"sins=${cu.sins.size} numSins=${numSins}")
    assert(cu.vins.size >= numVins, s"vins=${cu.vins.size} numVins=${numVins}")
    assert(cu.souts.size >= numSouts, s"souts=${cu.souts.size} numSouts=${numSouts}")
    assert(cu.vouts.size >= numVouts, s"vouts=${cu.vouts.size} numVouts=${numVouts}")
    cu.numScalarBufs(numSins)
    cu.numVecBufs(cu.vins.size)
    cu.mems.foreach(_.writePortMux.addInputs(muxSize))
    cu.sram.writeAddrMux.addInputs(muxSize)
    cu.sram.readAddrMux.addInputs(muxSize)
    cu.color(0 until numCtrs, CounterReg)
    //cu.color(7, ReadAddrReg).color(8, WriteAddrReg)
    cu.color(7 until 7 + cu.numScalarBufs, ScalarInReg)
    cu.color(12 until 12 + cu.numVecBufs, VecInReg)
    cu.genConnections
  }
}

class MemoryComputeUnit(override val param:MemoryComputeUnitParam=new MemoryComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "mcu"
  import spademeta._
  import param._

  lazy val ctrlBox:MemoryCtrlBox = new MemoryCtrlBox()

  //private val _wastages:ListBuffer[WAStage] = ListBuffer.empty // Write Addr Stages
  //private val _rastages:ListBuffer[RAStage] = ListBuffer.empty // Read Addr Stages
  //def wastages:List[WAStage] = _wastages.toList // Write Addr Stages
  //def rastages:List[RAStage] = _rastages.toList // Read Addr Stages
  //def addWAstages(stages:List[WAStage]) = { _wastages ++= stages; _fustages ++= stages; addStages(stages) }
  //def addRAstages(stages:List[RAStage]) = { _rastages ++= stages; _fustages ++= stages; addStages(stages) }
  //def addWAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    //addWAstages(List.fill(numStage) { WAStage(numOprds=numOprds, regs, ops)}); this // Write Addr stage 
  //} 
  //def addRAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    //addRAstages(List.fill(numStage) { RAStage(numOprds=numOprds, regs, ops)}); this // Read Addr stage 
  //} 
  def sram = srams.head
  override def config = param.config(this)
}

