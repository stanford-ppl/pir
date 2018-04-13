package pir.mapper

import pir.node._
import spade.node._

abstract class CUConstrain extends Constrain {
  type K = CUMap.K
  type V = CUMap.V
  type FG = CUMap
  val fgct:ClassTag[FG] = classTag[FG]
}
trait CUCostConstrain extends CUConstrain with CostConstrain 
trait CUPrefixConstrain extends CUCostConstrain with PrefixConstrain
trait CUQuantityConstrain extends CUCostConstrain with QuantityConstrain
object CUArcConsistencyConstrain extends CUConstrain with ArcConsistencyConstrain
object AFGConstrain extends CUPrefixConstrain  {
  def prefixKey(cuP:K)(implicit pass:PIRPass):Boolean = isAFG(cuP)
  def prefixValue(cuS:V)(implicit pass:PIRPass):Boolean = cuS.isInstanceOf[spade.node.ArgFringe]
}
object DFGConstrain extends CUPrefixConstrain {
  def prefixKey(cuP:K)(implicit pass:PIRPass):Boolean = isDFG(cuP)
  def prefixValue(cuS:V)(implicit pass:PIRPass):Boolean = cuS.isInstanceOf[MC]
}
object SramConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[pir.node.SRAM]().size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.SRAM]().size
}
object ControlFIFOConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = (cuP.collectDown[pir.node.FIFO]() ++ cuP.collectDown[RetimingFIFO]()).filter(n => isBit(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.FIFO[_]]().filter(n => isBit(n)).size
}
object ScalarFIFOConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = (cuP.collectDown[pir.node.FIFO]() ++ cuP.collectDown[RetimingFIFO]()).filter(n => isWord(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.FIFO[_]]().filter(n => isWord(n)).size
}
object VectorFIFOConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = (cuP.collectDown[pir.node.FIFO]() ++ cuP.collectDown[RetimingFIFO]()).filter(n => isVector(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.FIFO[_]]().filter(n => isVector(n)).size
}
object VectorInputConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[GlobalInput]().filter(n => isVector(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[Bundle[_]]().filter(n => isVector(n)).head.inputs.size
}
object ScalarInputConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[GlobalInput]().filter(n => isWord(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[Bundle[_]]().filter(n => isWord(n)).head.inputs.size
}
object ControlInputConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[GlobalInput]().filter(n => isBit(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[Bundle[_]]().filter(n => isBit(n)).head.inputs.size
}
object VectorOutputConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[GlobalOutput]().filter(n => isVector(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[Bundle[_]]().filter(n => isVector(n)).head.outputs.size
}
object ScalarOutputConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[GlobalOutput]().filter(n => isWord(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[Bundle[_]]().filter(n => isWord(n)).head.outputs.size
}
object ControlOutputConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[GlobalOutput]().filter(n => isBit(n)).size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[Bundle[_]]().filter(n => isBit(n)).head.outputs.size
}
object StageConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[StageDef]().size
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[Stage]().size
}
object LaneConstrain extends CUQuantityConstrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int = cuP.collectDown[StageDef]().map(s => parOf(s).get).reduceOption{ _ max _}.getOrElse(1)
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int = cuS.collectDown[SIMDUnit]().headOption.map{_.param.numLanes}.getOrElse(1)
}
