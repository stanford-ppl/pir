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

trait SN_temp extends SwitchNetwork {

  override lazy val ctrlNetwork = new {

  // switch to switch channel width
  //override val sbChannelWidth = 0
  //override val sbChannelWidthWE = 1
  //override val sbChannelWidthEW = 1
  //override val sbChannelWidthNS = 1
  //override val sbChannelWidthSN = 1

  // CU to CU channel width
  //override val cuChannelWidth = 0
  //override val cuChannelWidthWE = 1
  //override val cuChannelWidthEW = 1
  //override val cuChannelWidthNS = 1
  //override val cuChannelWidthSN = 1

  // switch to CU channel width
  override val sbcuChannelWidth = 2
  //override val sbcuChannelWidthNW = 1
  //override val sbcuChannelWidthNE = 1
  //override val sbcuChannelWidthSW = 1
  //override val sbcuChannelWidthSE = 1

  // CU to Switch channel width
  //override val cusbChannelWidth = 0
  //override val cusbChannelWidthNW = 1
  //override val cusbChannelWidthNE = 1
  //override val cusbChannelWidthSW = 1
  //override val cusbChannelWidthSE = 1
  
  // SCU to switch channel width
  //override val scsbChannelWidth = 4
  // switch to SCU channel width
  //override val sbscChannelWidth = 4

  // MC to switch channel width
  override val mcsbChannelWidth = 1
  // switch to MC channel width
  override val sbmcChannelWidth = 1

  // MC to SCU channel width
  // override val mcscChannelWidth = 4
  // SCU to MC channel width
  // override val scmcChannelWidth = 4
    
  // OCU to switch channel width
  override val ocsbChannelWidth = 3
  // switch to OCU channel width
  override val sbocChannelWidth = 8

  // Top to switch channel width
  //val tpsbChannelWidth = 1
  // switch to Top channel width
  //val sbtpChannelWidth = 1
  } with CtrlNetwork()

  override lazy val vectorNetwork = new {

  // switch to switch channel width
  //override val sbChannelWidth = 0
  //override val sbChannelWidthWE = 1
  //override val sbChannelWidthEW = 1
  //override val sbChannelWidthNS = 1
  //override val sbChannelWidthSN = 1

  // CU to CU channel width
  //override val cuChannelWidth = 0
  //override val cuChannelWidthWE = 1
  //override val cuChannelWidthEW = 1
  //override val cuChannelWidthNS = 1
  //override val cuChannelWidthSN = 1

  // switch to CU channel width
  //override val sbcuChannelWidth = 0
  //override val sbcuChannelWidthNW = 1
  //override val sbcuChannelWidthNE = 1
  //override val sbcuChannelWidthSW = 1
  //override val sbcuChannelWidthSE = 1

  // CU to Switch channel width
  //override val cusbChannelWidth = 0
  //override val cusbChannelWidthNW = 1
  //override val cusbChannelWidthNE = 1
  //override val cusbChannelWidthSW = 1
  //override val cusbChannelWidthSE = 1
  
  // SCU to switch channel width
  override val scsbChannelWidth = 0
  // switch to SCU channel width
  override val sbscChannelWidth = 0

  // MC to switch channel width
  override val mcsbChannelWidth = 1
  // switch to MC channel width
  override val sbmcChannelWidth = 1
    
  // MC to SCU channel width
  override val mcscChannelWidth = 0
  // SCU to MC channel width
  override val scmcChannelWidth = 0
  
  // OCU to switch channel width
  override val ocsbChannelWidth = 0
  // switch to OCU channel width
  override val sbocChannelWidth = 0
  
  // Top to switch channel width
  override val tpsbChannelWidth = 0
  // switch to Top channel width
  override val sbtpChannelWidth = 0
  } with VectorNetwork()

  override lazy val scalarNetwork = new {

  // switch to switch channel width
  //override val sbChannelWidth = 0
  //override val sbChannelWidthWE = 1
  //override val sbChannelWidthEW = 1
  //override val sbChannelWidthNS = 1
  //override val sbChannelWidthSN = 1

  // CU to CU channel width
  //override val cuChannelWidth = 0
  //override val cuChannelWidthWE = 1
  //override val cuChannelWidthEW = 1
  //override val cuChannelWidthNS = 1
  //override val cuChannelWidthSN = 1

  // switch to CU channel width
  //override val sbcuChannelWidth = 0
  //override val sbcuChannelWidthNW = 1
  //override val sbcuChannelWidthNE = 1
  //override val sbcuChannelWidthSW = 1
  //override val sbcuChannelWidthSE = 1

  // CU to Switch channel width
  //override val cusbChannelWidth = 0
  //override val cusbChannelWidthNW = 1
  //override val cusbChannelWidthNE = 1
  //override val cusbChannelWidthSW = 1
  //override val cusbChannelWidthSE = 1
  
  // SCU to switch channel width
  //override val scsbChannelWidth = 4
  // switch to SCU channel width
  //override val sbscChannelWidth = 4

  // MC to switch channel width
  //override val mcsbChannelWidth = 4
  // switch to MC channel width
  //override val sbmcChannelWidth = 4
    
  // MC to SCU channel width
  // override val mcscChannelWidth = 4
  // SCU to MC channel width
  // override val scmcChannelWidth = 4
  
  // OCU to switch channel width
  //val ocsbChannelWidth = 2
  // switch to OCU channel width
  //val sbocChannelWidth = 2
  
  // Top to switch channel width
  //val tpsbChannelWidth = 1
  // switch to Top channel width
  //val sbtpChannelWidth = 1
  } with ScalarNetwork()

}

object SN_8x8 extends SwitchNetwork(numRows=8, numCols=8, numArgIns=5, numArgOuts=5) with SN_temp {
}

object SN_5x5 extends SwitchNetwork(numRows=5, numCols=5, numArgIns=5, numArgOuts=5) with SN_temp {
}

object SN_4x4 extends SwitchNetwork(numRows=4, numCols=4, numArgIns=5, numArgOuts=5) with SN_temp {
}

object SN_2x2 extends SwitchNetwork(numRows=2, numCols=2, numArgIns=5, numArgOuts=5) with SN_temp {
}
