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

object SN8x8 extends SwitchNetwork(numRows=8, numCols=8, numArgIns=6, numArgOuts=5) {
  config
}

object SN5x5 extends SwitchNetwork(numRows=5, numCols=5, numArgIns=6, numArgOuts=5) {
  config
}

object SN4x4 extends SwitchNetwork(numRows=4, numCols=4, numArgIns=6, numArgOuts=5) {
  config
}
object SN2x2 extends SwitchNetwork(numRows=2, numCols=2, numArgIns=3, numArgOuts=3) {
  config
}
object SN1x1 extends SwitchNetwork(numRows=1, numCols=1, numArgIns=3, numArgOuts=3) {
  config
}

object SN2x2Test extends SwitchNetwork(numRows=2, numCols=2, numArgIns=3, numArgOuts=3) {
  override lazy val ctrlNetwork = new CtrlNetwork {
    // SCU to switch channel width
    override lazy val scsbChannelWidth = 0
    // switch to SCU channel width
    override lazy val sbscChannelWidth = 0
    // MC to switch channel width
    override lazy val mcsbChannelWidth = 0
    // switch to MC channel width
    override lazy val sbmcChannelWidth = 0
    // MC to SCU channel width
    override lazy val mcscChannelWidth = 0
    // SCU to MC channel width
    override lazy val scmcChannelWidth = 0
  }

  override lazy val vectorNetwork = new VectorNetwork {
    // SCU to switch channel width
    override lazy val scsbChannelWidth = 0
    // switch to SCU channel width
    override lazy val sbscChannelWidth = 0
    // MC to switch channel width
    override lazy val mcsbChannelWidth = 0
    // switch to MC channel width
    override lazy val sbmcChannelWidth = 0
    // MC to SCU channel width
    override lazy val mcscChannelWidth = 0
    // SCU to MC channel width
    override lazy val scmcChannelWidth = 0
  }

  override lazy val scalarNetwork = new ScalarNetwork {
    // SCU to switch channel width
    override lazy val scsbChannelWidth = 0
    // switch to SCU channel width
    override lazy val sbscChannelWidth = 0
    // MC to switch channel width
    override lazy val mcsbChannelWidth = 0
    // switch to MC channel width
    override lazy val sbmcChannelWidth = 0
    // MC to SCU channel width
    override lazy val mcscChannelWidth = 0
    // SCU to MC channel width
    override lazy val scmcChannelWidth = 0
  }
  config
}
