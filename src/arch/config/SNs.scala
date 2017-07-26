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

class SN(numRows:Int=2, numCols:Int=2, numArgIns:Int=3, numArgOuts:Int=3, pattern:Pattern=MixAll) extends SwitchNetwork (
  new SwitchNetworkParam(numRows=numRows, numCols=numCols, numArgIns=numArgIns, numArgOuts=numArgOuts, pattern=pattern)) {
  config
}
object SN1x1 extends SN(numRows=1, numCols=1, numArgIns=3, numArgOuts=3, pattern=MixAll) 
object SN1x2 extends SN(numRows=1, numCols=2, numArgIns=3, numArgOuts=3, pattern=MixAll) 
object SN2x2 extends SN(numRows=1, numCols=1, numArgIns=3, numArgOuts=3, pattern=MixAll) 
object SN2x3 extends SN(numRows=2, numCols=3, numArgIns=3, numArgOuts=3, pattern=MixAll) 
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
object SN4x4 extends SN(numRows=4, numCols=4, numArgIns=3, numArgOuts=3, pattern=MixAll) 
object SN8x8 extends SN(numRows=8, numCols=8, numArgIns=3, numArgOuts=3, pattern=MixAll) 

object SN16x8 extends SwitchNetwork(PreloadSwitchNetworkParam(numRows=16, numCols=8, numArgIns=10, numArgOuts=5, pattern=Checkerboard)) {

  override def pcuAt(i:Int, j:Int):PatternComputeUnit = new PatternComputeUnit(PreloadPatternComputeParam())

  override def mcuAt(i:Int, j:Int):MemoryComputeUnit = new MemoryComputeUnit(PreloadMemoryComputeParam())

  override def scuAt(i:Int, j:Int):ScalarComputeUnit = new ScalarComputeUnit(PreloadScalarComputeParam())

  override lazy val vectorNetwork = new VectorNetwork() {
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 6
  }

  override lazy val scalarNetwork = new ScalarNetwork() {
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb", "srcDir"->List("NS", "SN")) = 4

    // switch to SCU channel width
    channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"scu") = 4
  }

  override lazy val ctrlNetwork = new CtrlNetwork() {
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 12

    // switch to CU channel width
    channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu", "mu", "mcu")) = 4

    // CU to Switch channel width
    channelWidth("pos"->"center", "src"->List("pcu", "mu", "mcu"), "dst"->"sb") = 2
      
    // OCU to switch channel width
    channelWidth("pos"->"center", "src"->"ocu", "dst"->"sb") = 4
    // switch to OCU channel width
    channelWidth("pos"->"center", "src"->"sb", "dst"->"ocu") = 4
  }
  config
}
