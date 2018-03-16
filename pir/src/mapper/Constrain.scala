package pir.mapper

import pir._
import pir.node._

import spade.node._

import prism._
import prism.util._

trait Constrain {
  override def toString = this.getClass.getSimpleName.replace("$","")
  def prune(cumap:CUMap)(implicit pass:PIRPass):CUMap
}
abstract class PrefixConstrain extends Constrain {
  def prefix(cuP:CUMap.K)(implicit pass:PIRPass):Boolean
  def prefix(cuS:CUMap.V)(implicit pass:PIRPass):Boolean
  def prune(cumap:CUMap)(implicit pass:PIRPass):CUMap = {
    import pass.{pass => _, _}
    cumap.multiplyFactor { case (cuP,cuS) =>
      val factor = if (prefix(cuP) == prefix(cuS)) 1 else 0
      dbg(s"$this ${quote(cuP)} -> ${quote(cuS)} factor=$factor")
      factor
    }
  }
}
object AFGConstrain extends PrefixConstrain {
  def prefix(cuP:CUMap.K)(implicit pass:PIRPass):Boolean = isAFG(cuP)
  def prefix(cuS:CUMap.V)(implicit pass:PIRPass):Boolean = cuS.isInstanceOf[spade.node.ArgFringe]
}
object DFGConstrain extends PrefixConstrain {
  def prefix(cuP:CUMap.K)(implicit pass:PIRPass):Boolean = isDFG(cuP)
  def prefix(cuS:CUMap.V)(implicit pass:PIRPass):Boolean = cuS.isInstanceOf[MC]
}
object SwitchConstrain extends PrefixConstrain {
  def prefix(cuP:CUMap.K)(implicit pass:PIRPass):Boolean = false
  def prefix(cuS:CUMap.V)(implicit pass:PIRPass):Boolean = cuS.isInstanceOf[SwitchBox]
}
abstract class QuantityConstrain extends Constrain {
  def numPNodes(cuP:CUMap.K)(implicit pass:PIRPass):Int
  def numSnodes(cuS:CUMap.V)(implicit pass:PIRPass):Int
  def prune(cumap:CUMap)(implicit pass:PIRPass):CUMap = {
    import pass.{pass => _, _}
    cumap.multiplyFactor { case (cuP,cuS) =>
      val factor = if (numPNodes(cuP) > numSnodes(cuS)) 0 else 1
      dbg(s"$this ${quote(cuP)} -> ${quote(cuS)} factor=$factor")
      factor
    }
  }
}
object SramConstrain extends QuantityConstrain {
  def numPNodes(cuP:CUMap.K)(implicit pass:PIRPass):Int = cuP.collectDown[pir.node.SRAM]().size
  def numSnodes(cuS:CUMap.V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.SRAM]().size
}
object ControlFIFOConstrain extends QuantityConstrain {
  def numPNodes(cuP:CUMap.K)(implicit pass:PIRPass):Int = cuP.collectDown[pir.node.FIFO]().filter{ fifo => isBit(fifo) }.size
  def numSnodes(cuS:CUMap.V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.FIFO[_]]().filter(is[Bit]).size
}
object ScalarFIFOConstrain extends QuantityConstrain {
  def numPNodes(cuP:CUMap.K)(implicit pass:PIRPass):Int = cuP.collectDown[pir.node.FIFO]().filter{ fifo => isScalar(fifo) }.size
  def numSnodes(cuS:CUMap.V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.FIFO[_]]().filter(is[Word]).size
}
object VectorFIFOConstrain extends QuantityConstrain {
  def numPNodes(cuP:CUMap.K)(implicit pass:PIRPass):Int = cuP.collectDown[pir.node.FIFO]().filter{ fifo => isVector(fifo) }.size
  def numSnodes(cuS:CUMap.V)(implicit pass:PIRPass):Int = cuS.collectDown[spade.node.FIFO[_]]().filter(is[Vector]).size
}
object StageConstrain extends QuantityConstrain {
  def numPNodes(cuP:CUMap.K)(implicit pass:PIRPass):Int = cuP.collectDown[StageDef]().size
  def numSnodes(cuS:CUMap.V)(implicit pass:PIRPass):Int = cuS.collectDown[Stage]().size
}
object LaneConstrain extends QuantityConstrain {
  def numPNodes(cuP:CUMap.K)(implicit pass:PIRPass):Int = cuP.collectDown[StageDef]().map(s => parOf(s).get).reduceOption{ _ max _}.getOrElse(1)
  def numSnodes(cuS:CUMap.V)(implicit pass:PIRPass):Int = cuS.collectDown[SIMDUnit]().headOption.map{_.param.numLanes}.getOrElse(1)
}

