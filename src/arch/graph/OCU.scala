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

case class OuterComputeUnitParam (
  sbufSize:Int = 16,
  vbufSize:Int = 16,
  numRegs:Int = 0,
  numCtrs:Int = 6,
  numUDCs:Int = 15
) extends ComputeUnitParam() {
  val numSRAMs:Int = 0
  val sramSize:Int = 0
  override val numLanes:Int = 1

  def config(cu:OuterComputeUnit)(implicit spade:Spade) = {
    cu.numScalarBufs(4)
    cu.genConnections
  }
}

class OuterComputeUnit(override val param:OuterComputeUnitParam=new OuterComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  import spademeta._
  import param._
  override val typeStr = "ocu"
  override def config = param.config(this)

  lazy val ctrlBox:OuterCtrlBox = new OuterCtrlBox()
}
