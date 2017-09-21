package spade.node

import spade._
import spade.util._

import pirc.enums._

case class PreloadPatternComputeParam (
  override val sbufSize:Int = 16,
  override val vbufSize:Int = 16,
  override val numCtrs:Int = 8,
  override val muxSize:Int = 10,
  override val numUDCs:Int = 5
) extends PatternComputeUnitParam (
  numVins = ConfigFactory.plasticineConf.vinPcu,
  numVouts = ConfigFactory.plasticineConf.voutPcu,
  numSins = ConfigFactory.plasticineConf.sinPcu,
  numSouts = ConfigFactory.plasticineConf.soutPcu,
  numRegs  = ConfigFactory.plasticineConf.regsPcu,
  numStages = ConfigFactory.plasticineConf.comp
) with PreLoadSpadeParam

case class SRAMAddrGenParam (
  override val sbufSize:Int = 16,
  override val vbufSize:Int = 16,
  override val numVins:Int = 4,
  override val numVouts:Int = 2,
  override val numSins:Int = 4,
  override val numSouts:Int = 2,
  override val numRegs:Int = 5,
  override val numStages:Int = 4,
  override val numCtrs:Int = 4,
  override val muxSize:Int = 3,
  override val numUDCs:Int = 5
) extends PatternComputeUnitParam (
  reduction = false
) with PreLoadSpadeParam

class PatternComputeUnitParam (
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
  val numUDCs:Int = 5,
  val reduction:Boolean = true
) extends ComputeUnitParam() {
  val numSRAMs:Int = 0
  val sramSize:Int = 0

  def config(cu:PatternComputeUnit)(implicit spade:Spade) = {
    val numReduceStages = if (reduction) Math.ceil(Math.log(numLanes) / Math.log(2)).toInt else 0
    val numFrontStages = numStages - (numReduceStages + 2)
    assert(numFrontStages >= 0, s"numFrontStages=$numFrontStages numStage=$numStages")
    assert(cu.sins.size >= numSins, s"sins=${cu.sins.size} numSins=${numSins}")
    assert(cu.vins.size >= numVins, s"vins=${cu.vins.size} numVins=${numVins}")
    assert(cu.souts.size >= numSouts, s"souts=${cu.souts.size} numSouts=${numSouts}")
    assert(cu.vouts.size >= numVouts, s"vouts=${cu.vouts.size} numVouts=${numVouts}")
    cu.addRegstages(numStage=numFrontStages, numOprds=3, ops)
    cu.addRdstages(numStage=numReduceStages, numOprds=3, ops)
    cu.addRegstages(numStage=2, numOprds=3, ops)
    cu.numScalarBufs(numSins)
    cu.numVecBufs(cu.vins.size)
    cu.mems.foreach(_.writePortMux.addInputs(muxSize))
    cu.color(0 until numCtrs, CounterReg)
    cu.color(0, ReduceReg).color(1, AccumReg)
    cu.color(numRegs-cu.numScalarBufs until numRegs, ScalarInReg)
    cu.color(numRegs-cu.souts.size until numRegs, ScalarOutReg)
    cu.color(numRegs-cu.numVecBufs until numRegs, VecInReg)
    cu.color(numRegs-cu.vouts.size until numRegs, VecOutReg)
    cu.genConnections
  }
}

class PatternComputeUnit(override val param:PatternComputeUnitParam=new PatternComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "pcu"

  lazy val ctrlBox:InnerCtrlBox = new InnerCtrlBox()
  override def config = param.config(this)
}
