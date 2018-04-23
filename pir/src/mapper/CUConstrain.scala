package pir.mapper

import pir.node._
import spade.node._

trait CUConstrain extends Constrain {
  type K = CUMap.K
  type V = CUMap.V
  type FG = CUMap
  val fgct:ClassTag[FG] = classTag[FG]
}
trait CUCostConstrain extends CUConstrain with CostConstrain 
trait CUPrefixConstrain extends CUCostConstrain with PrefixConstrain
trait CUQuantityConstrain extends CUCostConstrain with QuantityConstrain
class CUArcConsistencyConstrain(implicit pass:PIRPass) extends CUConstrain with ArcConsistencyConstrain
class CUMatchingConstrain(implicit pass:PIRPass) extends CUConstrain with MatchingConstrain
class AFGConstrain(implicit pass:PIRPass) extends CUPrefixConstrain  {
  def prefixKey(cuP:K):Boolean = isAFG(cuP)
  def prefixValue(cuS:V):Boolean = cuS.isInstanceOf[spade.node.ArgFringe]
}
class DFGConstrain(implicit pass:PIRPass) extends CUPrefixConstrain {
  def prefixKey(cuP:K):Boolean = isDFG(cuP)
  def prefixValue(cuS:V):Boolean = cuS.isInstanceOf[MC]
}
class SramConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[pir.node.SRAM]().size
  def numSnodes(cuS:V):Int = cuS.collectDown[spade.node.SRAM]().size
}
class ControlFIFOConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = (cuP.collectDown[pir.node.FIFO]() ++ cuP.collectDown[RetimingFIFO]()).filter(n => isBit(n)).size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.numControlFifos; case _ => 0 }
}
class ScalarFIFOConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = (cuP.collectDown[pir.node.FIFO]() ++ cuP.collectDown[RetimingFIFO]()).filter(n => isWord(n)).size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.numScalarFifos; case _ => 0 }
}
class VectorFIFOConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = (cuP.collectDown[pir.node.FIFO]() ++ cuP.collectDown[RetimingFIFO]()).filter(n => isVector(n)).size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.numVectorFifos; case _ => 0 }
}
class VectorInputConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[GlobalInput]().filter(n => isVector(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isVector(n)).head.inputs.size
}
class ScalarInputConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[GlobalInput]().filter(n => isWord(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isWord(n)).head.inputs.size
}
class ControlInputConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[GlobalInput]().filter(n => isBit(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isBit(n)).head.inputs.size
}
class VectorOutputConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[GlobalOutput]().filter(n => isVector(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isVector(n)).head.outputs.size
}
class ScalarOutputConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[GlobalOutput]().filter(n => isWord(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isWord(n)).head.outputs.size
}
class ControlOutputConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[GlobalOutput]().filter(n => isBit(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isBit(n)).head.outputs.size
}
class StageConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[StageDef]().size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.simdParam.fold(0) { _.stageParams.size }; case _ => 0 }
}
class LaneConstrain(implicit pass:PIRPass) extends CUQuantityConstrain {
  def numPNodes(cuP:K):Int = cuP.collectDown[StageDef]().map(s => parOf(s).get).reduceOption{ _ max _}.getOrElse(1)
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.simdParam.fold(1) { _.numLanes }; case _ => 1 }
}
