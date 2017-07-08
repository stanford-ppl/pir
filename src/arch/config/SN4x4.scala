package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.immutable.{Map => IMap}
import scala.reflect.runtime.universe._
import pir.util.enums._
import scala.util.{Try, Success, Failure}

object SN8x8 extends SwitchNetwork(new SwitchNetworkParam(numRows=8, numCols=8, numArgIns=6, numArgOuts=5)) {
  config
}

object SN5x5 extends SwitchNetwork(new SwitchNetworkParam(numRows=5, numCols=5, numArgIns=6, numArgOuts=5)) {
  config
}

object SN4x4 extends SwitchNetwork(new SwitchNetworkParam(numRows=4, numCols=4, numArgIns=6, numArgOuts=5)) {
  override def pcuAt(i:Int, j:Int) = {
    new PatternComputeUnit(new PatternComputeUnitParam{
      override val numRegs = 20
      override def config(cu:PatternComputeUnit)(implicit spade:Spade) = {
        cu.addRegstages(numStage=13, numOprds=3, ops)
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
    })
  }
  config
}
object SN2x3 extends SwitchNetwork(new SwitchNetworkParam(numRows=2, numCols=3, numArgIns=5, numArgOuts=3)) {
  config
}
object SN2x2 extends SwitchNetwork(new SwitchNetworkParam(numRows=2, numCols=2, numArgIns=3, numArgOuts=3)) {
  config
}
object SN1x1 extends SwitchNetwork(new SwitchNetworkParam(numRows=1, numCols=1, numArgIns=3, numArgOuts=3)) {
  config
}

object SN2x2Test extends SwitchNetwork(new SwitchNetworkParam(numRows=2, numCols=2, numArgIns=3, numArgOuts=3)) {
  override lazy val ctrlNetwork = new CtrlNetwork {
    channelWidth("pos"->List("left", "right")) = 0
  }

  override lazy val vectorNetwork = new VectorNetwork {
    channelWidth("pos"->List("left", "right")) = 0
  }

  override lazy val scalarNetwork = new ScalarNetwork {
    channelWidth("pos"->List("left", "right")) = 0
  }
  config
}
