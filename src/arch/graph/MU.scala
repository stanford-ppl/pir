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

case class MemoryUnitParam(
  sbufSize:Int = 16,
  vbufSize:Int = 16
) extends ControllerParam() {
  val sramSize = 128
  override val numLanes = 1

  /* Parameters */
  def config(cu:MemoryUnit)(implicit spade:Spade) = {
    cu.numScalarBufs(3)
    cu.numVecBufs(cu.vins.size)
    cu.genConnections
  }
}

class MemoryUnit(override val param:MemoryUnitParam=MemoryUnitParam())(implicit spade:Spade) 
  extends Controller(param) {
  override val typeStr = "mu"
  import spademeta._
  import param._

  override lazy val ctrlBox:MUCtrlBox = new MUCtrlBox()

  val sram = SRAM(sramSize)
  override def config = param.config(this)
}

