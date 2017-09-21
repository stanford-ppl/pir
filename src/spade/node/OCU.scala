package spade.node

import spade._
import spade.util._

import pirc.enums._
import pirc.util._

case class OuterComputeUnitParam (
  sbufSize:Int = 16,
  numSins:Int = 5,
  numRegs:Int = 0,
  numStages:Int = 0,
  numCtrs:Int = 6,
  muxSize:Int = 10,
  numUDCs:Int = 15
) extends ComputeUnitParam() {
  val numVins:Int = 0
  val numVouts:Int = 0
  val numSouts:Int = 0
  val vbufSize:Int = 0
  val numSRAMs:Int = 0
  val sramSize:Int = 0
  override lazy val numLanes:Int = 1

  def config(cu:OuterComputeUnit)(implicit spade:Spade) = {
    assert(cu.sins.size >= numSins, s"sins=${cu.sins.size} numSins=${numSins}")
    cu.numScalarBufs(numSins)
    cu.mems.foreach(_.writePortMux.addInputs(muxSize))
    cu.genConnections
  }
}

case class OuterComputeUnitConfig (
  isSeq:Boolean,
  isMeta:Boolean
) extends ControllerConfig(Map.empty)

class OuterComputeUnit(override val param:OuterComputeUnitParam=new OuterComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) with Configurable {
  import spademeta._
  import param._

  type CT = OuterComputeUnitConfig
  override val typeStr = "ocu"
  override def config = param.config(this)

  lazy val ctrlBox:OuterCtrlBox = new OuterCtrlBox()
}
