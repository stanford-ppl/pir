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

object SN2x3 extends SwitchNetwork(new SwitchNetworkParam(numRows=2, numCols=3, numArgIns=5, numArgOuts=3)) {
  config
}
object SN2x2 extends SwitchNetwork(new SwitchNetworkParam(numRows=2, numCols=2, numArgIns=3, numArgOuts=3)) {
  config
}
case class SN(numRows:Int, numCols:Int) extends SwitchNetwork(
  new SwitchNetworkParam(numRows=numRows, numCols=numCols, numArgIns=3, numArgOuts=3)
) {
  override def toString = s"SN${numRows}x${numCols}"
  config
}
case class SN_MCU(numRows:Int, numCols:Int, numArgIns:Int=3, numArgOuts:Int=3) extends SwitchNetwork(
  new SwitchNetworkParam(numRows=numRows, numCols=numCols, numArgIns=numArgIns, numArgOuts=numArgOuts)
) {
  override def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) pcuAt(i,j) 
    else mcuAt(i,j) 
  }
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
