package pir
package mapper

import pir.node._
import pir.pass._
import spade.node._

trait CUConstrain extends Constrain {
  type K = CUMap.K
  type V = CUMap.V
  type FG = CUMap
  val fgct:ClassTag[FG] = classTag[FG]

  def inputsP(cuP:K) = {
    cuP.ins.groupBy { in =>
      in.from.src
    }.map { case (src, ins) => ins.head.src }
  }
  def outputsP(cuP:K) = {
    cuP.outs.map { out => out.src }
  }
  lazy val isPartitioner = pass.isInstanceOf[GlobalPartioner]

  def fifosP(cuP:K) = {
    val fifos = cuP.collectDown[pir.node.FIFO]()
    val rfifos = if (isPartitioner) {
      cuP.collectDown[pir.node.RetimingFIFO]() ++ 
      inputsP(cuP).filterNot { _.isInstanceOf[LocalStore] }
    } else {
      cuP.collectDown[pir.node.RetimingFIFO]()
    }
    fifos ++ rfifos
  }
}
trait CUCostConstrain extends CUConstrain with CostConstrain 
trait CUPrefixConstrain extends CUCostConstrain with PrefixConstrain
trait CUQuantityConstrain extends CUCostConstrain with QuantityConstrain
class CUArcConsistencyConstrain(implicit pass:CUPruner) extends CUConstrain with ArcConsistencyConstrain
class CUMatchingConstrain(implicit pass:CUPruner) extends CUConstrain with MatchingConstrain
class AFGConstrain(implicit pass:CUPruner) extends CUPrefixConstrain  {
  import pass._
  def prefixKey(cuP:K):Boolean = isAFG(cuP)
  def prefixValue(cuS:V):Boolean = cuS.isInstanceOf[spade.node.ArgFringe]
}
class MCConstrain(implicit pass:CUPruner) extends CUPrefixConstrain {
  import pass._
  def prefixKey(cuP:K):Boolean = isDFG(cuP) || isSFG(cuP)
  def prefixValue(cuS:V):Boolean = cuS.isInstanceOf[MC]
}
class SramConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = cuP.collectDown[pir.node.SRAM]().size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.numSrams; case _ => 0 }
}
class ControlFIFOConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = fifosP(cuP).filter(n => isBit(n)).size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.numControlFifos; case _ => 0 }
}
class ScalarFIFOConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = fifosP(cuP).filter(n => isWord(n)).size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.numScalarFifos; case _ => 0 }
}
class VectorFIFOConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = fifosP(cuP).filter(n => isVector(n)).size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.numVectorFifos; case _ => 0 }
}
class VectorInputConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = inputsP(cuP).filter(n => isVector(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isVector(n)).head.inputs.size
}
class ScalarInputConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = inputsP(cuP).filter(n => isWord(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isWord(n)).head.inputs.size
}
class ControlInputConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = inputsP(cuP).filter(n => isBit(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isBit(n)).head.inputs.size
}
class VectorOutputConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = outputsP(cuP).filter(n => isVector(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isVector(n)).head.outputs.size
}
class ScalarOutputConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = outputsP(cuP).filter(n => isWord(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isWord(n)).head.outputs.size
}
class ControlOutputConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = outputsP(cuP).filter(n => isBit(n)).size
  def numSnodes(cuS:V):Int = cuS.collectDown[Bundle[_]]().filter(n => isBit(n)).head.outputs.size
}
class StageConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = cuP.collectDown[StageDef]().size
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.simdParam.fold(0) { _.stageParams.size }; case _ => 0 }
}
class LaneConstrain(implicit pass:CUPruner) extends CUQuantityConstrain {
  import pass._
  def numPNodes(cuP:K):Int = cuP.collectDown[StageDef]().map(s => pass.getParOf(s)).reduceOption{ _ max _}.getOrElse(1)
  def numSnodes(cuS:V):Int = cuS match { case cuS:CU => cuS.param.simdParam.fold(1) { _.numLanes }; case _ => 1 }
}
