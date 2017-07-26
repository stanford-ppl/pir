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

case class PreloadPatternComputeParam (
  override val sbufSize:Int = 16,
  override val vbufSize:Int = 16,
  override val numRegs:Int = 16,
  override val numCtrs:Int = 8,
  override val numUDCs:Int = 5
) extends PatternComputeUnitParam (
  sbufSize = sbufSize,
  vbufSize = vbufSize,
  numVins = ConfigFactory.plasticineConf.vinPcu,
  numVouts = ConfigFactory.plasticineConf.voutPcu,
  numSins = ConfigFactory.plasticineConf.sinPcu,
  numSouts = ConfigFactory.plasticineConf.soutPcu,
  numRegs  = ConfigFactory.plasticineConf.regsPcu,
  numStages = ConfigFactory.plasticineConf.comp,
  numCtrs  = numCtrs,
  numUDCs  = numUDCs  
) with PreLoadSpadeParam

class PatternComputeUnitParam(
  val sbufSize:Int = 16,
  val vbufSize:Int = 16,
  val numVins:Int = 4,
  val numVouts:Int = 4,
  val numSins:Int = 4,
  val numSouts:Int = 4,
  val numRegs:Int = 16,
  val numStages:Int = 8,
  val numCtrs:Int = 8,
  val numUDCs:Int = 5
) extends ComputeUnitParam() {
  val numSRAMs:Int = 0
  val sramSize:Int = 0

  def config(cu:PatternComputeUnit)(implicit spade:Spade) = {
    val numReduceStages = Math.ceil(Math.log(numLanes) / Math.log(2)).toInt
    val numFrontStages = numStages - (numReduceStages + 2)
    assert(numFrontStages >= 0, s"numFrontStages=$numFrontStages numStage=$numStages")
    assert(cu.sins.size >= numSins, s"sins=${cu.sins.size} numSins=${numSins}")
    assert(cu.vins.size >= numVins, s"vins=${cu.vins.size} numVins=${numVins}")
    assert(cu.souts.size >= numSouts, s"souts=${cu.souts.size} numSouts=${numSouts}")
    assert(cu.vouts.size >= numVouts, s"vouts=${cu.vouts.size} numVouts=${numVouts}")
    cu.addRegstages(numStage=2, numOprds=3, ops)
    cu.addRdstages(numStage=numReduceStages, numOprds=3, ops)
    cu.addRegstages(numStage=2, numOprds=3, ops)
    cu.numScalarBufs(numSins)
    cu.numVecBufs(cu.vins.size)
    cu.color(0 until numCtrs, CounterReg)
    cu.color(0, ReduceReg).color(1, AccumReg)
    cu.color(5 until 5 + cu.numScalarBufs, ScalarInReg)
    cu.color(5 until 5 + cu.souts.size, ScalarOutReg)
    cu.color(9 until 9 + cu.numVecBufs, VecInReg)
    cu.color(9 until 9 + cu.vouts.size, VecOutReg)
    cu.genConnections
  }
}

class PatternComputeUnit(override val param:PatternComputeUnitParam=new PatternComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "pcu"

  lazy val ctrlBox:InnerCtrlBox = new InnerCtrlBox()
  override def config = param.config(this)
}
