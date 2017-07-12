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

case class ScalarComputeUnitParam (
  sbufSize:Int = 16,
  vbufSize:Int = 16,
  numRegs:Int = 16,
  numCtrs:Int = 6,
  numUDCs:Int = 4
) extends ComputeUnitParam() {
  val numSRAMs:Int = 0
  val sramSize:Int = 0
  override val numLanes:Int = 1

  /* Parameters */
  def config(cu:ScalarComputeUnit)(implicit spade:Spade) = {
    cu.addRegstages(numStage=6, numOprds=3, fixOps ++ bitOps ++ otherOps)
    cu.numScalarBufs(6)
    cu.numVecBufs(cu.vins.size)
    cu.color(0 until numCtrs, CounterReg)
    cu.color(7 until 7 + cu.numScalarBufs, ScalarInReg)
    cu.color(8 until 8 + cu.souts.size, ScalarOutReg)
    cu.color(12 until 12 + cu.numVecBufs, VecInReg)
    cu.genConnections
  }
}
/* A spetial type of CU used for memory loader/storer */
class ScalarComputeUnit(override val param:ScalarComputeUnitParam=new ScalarComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "scu"
  lazy val ctrlBox:InnerCtrlBox = new InnerCtrlBox()
  override def config = param.config(this)
}
