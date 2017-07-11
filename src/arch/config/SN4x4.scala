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

case class SN(numRows:Int, numCols:Int, numArgIns:Int=3, numArgOuts:Int=3, pattern:Pattern=MixAll) extends SwitchNetwork(
  new SwitchNetworkParam(numRows=numRows, numCols=numCols, numArgIns=numArgIns, numArgOuts=numArgOuts, pattern=pattern)
) {
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

sealed trait Pattern {
  def cuAt(sn:SwitchNetwork)(i:Int, j:Int):ComputeUnit
}
case object Checkerboard extends Pattern {
  def cuAt(sn:SwitchNetwork)(i:Int, j:Int):ComputeUnit = {
    if ((i+j) % 2 == 0) sn.pcuAt(i,j) 
    else sn.mcuAt(i,j) 
  }
}
case object MixAll extends Pattern {
  def cuAt(sn:SwitchNetwork)(i:Int, j:Int):ComputeUnit = {
    if (i % 2 == 0) {
      if (j % 2 == 0) sn.pcuAt(i,j)
      else sn.mcuAt(i,j)
    }
    else sn.scuAt(i,j)
  }
}

