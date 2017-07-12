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

case class PatternComputeUnitParam(
  sbufSize:Int = 16,
  vbufSize:Int = 16,
  numRegs:Int = 16,
  numCtrs:Int = 8,
  numUDCs:Int = 5
) extends ComputeUnitParam() {
  val numSRAMs:Int = 0
  val sramSize:Int = 0

  def config(cu:PatternComputeUnit)(implicit spade:Spade) = {
    cu.addRegstages(numStage=2, numOprds=3, ops)
    cu.addRdstages(numStage=4, numOprds=3, ops)
    cu.addRegstages(numStage=2, numOprds=3, ops)
    cu.numScalarBufs(4)
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

class PatternComputeUnit(override val param:PatternComputeUnitParam=PatternComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "pcu"

  lazy val ctrlBox:InnerCtrlBox = new InnerCtrlBox()
  override def config = param.config(this)
}
