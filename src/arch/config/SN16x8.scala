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

object SN_16x8 extends SwitchNetwork(numRows=16, numCols=8, numArgIns=40, numArgOuts=5) {
  override lazy val vectorNetwork = new VectorNetwork() {
    // switch to switch channel width
    override lazy val sbChannelWidth = 6
    //override lazy val sbChannelWidthWE = sbChannelWidth
    //override lazy val sbChannelWidthEW = sbChannelWidth
    //override lazy val sbChannelWidthNS = sbChannelWidth
    //override lazy val sbChannelWidthSN = sbChannelWidth

    // CU to CU channel width
    //override lazy val cuChannelWidth = 0
    //override lazy val cuChannelWidthWE = cuChannelWidth
    //override lazy val cuChannelWidthEW = cuChannelWidth
    //override lazy val cuChannelWidthNS = cuChannelWidth
    //override lazy val cuChannelWidthSN = cuChannelWidth

    // switch to CU channel width
    //override lazy val sbcuChannelWidth = 1
    //override lazy val sbcuChannelWidthNW = sbcuChannelWidth
    //override lazy val sbcuChannelWidthNE = sbcuChannelWidth
    //override lazy val sbcuChannelWidthSW = sbcuChannelWidth
    //override lazy val sbcuChannelWidthSE = sbcuChannelWidth

    // CU to Switch channel width
    //override lazy val cusbChannelWidth = 1
    //override lazy val cusbChannelWidthNW = cusbChannelWidth
    //override lazy val cusbChannelWidthNE = cusbChannelWidth
    //override lazy val cusbChannelWidthSW = cusbChannelWidth
    //override lazy val cusbChannelWidthSE = cusbChannelWidth
    
    // SCU to switch channel width
    //override lazy val scsbChannelWidth = 0
    // switch to SCU channel width
    //override lazy val sbscChannelWidth = 0

    // MC to switch channel width
    //override lazy val mcsbChannelWidth = 1
    // switch to MC channel width
    //override lazy val sbmcChannelWidth = 1
      
    // MC to SCU channel width
    //override lazy val mcscChannelWidth = 0
    // SCU to MC channel width
    //override lazy val scmcChannelWidth = 0
    
    // OCU to switch channel width
    //override lazy val ocsbChannelWidth = 0
    // switch to OCU channel width
    //override lazy val sbocChannelWidth = 0
    
    // Top to switch channel width
    //override lazy val tpsbChannelWidth = 0
    // switch to Top channel width
    //override lazy val sbtpChannelWidth = 0
  }

  override lazy val scalarNetwork = new ScalarNetwork() {
    // switch to switch channel width
    //override lazy val sbChannelWidth = 4
    //override lazy val sbChannelWidthWE = sbChannelWidth
    //override lazy val sbChannelWidthEW = sbChannelWidth
    override lazy val sbChannelWidthNS = 8// sbChannelWidth
    override lazy val sbChannelWidthSN = 8 //sbChannelWidth

    // CU to CU channel width
    //override lazy val cuChannelWidth = 0
    //override lazy val cuChannelWidthWE = cuChannelWidth
    //override lazy val cuChannelWidthEW = cuChannelWidth
    //override lazy val cuChannelWidthNS = cuChannelWidth
    //override lazy val cuChannelWidthSN = cuChannelWidth

    // switch to CU channel width
    override lazy val sbcuChannelWidth = 1
    //override lazy val sbcuChannelWidthNW = 2 
    //override lazy val sbcuChannelWidthNE = 1 
    //override lazy val sbcuChannelWidthSW = 2 
    //override lazy val sbcuChannelWidthSE = 1 

    // CU to Switch channel width
    override lazy val cusbChannelWidth = 1
    //override lazy val cusbChannelWidthNW = 2
    //override lazy val cusbChannelWidthNE = 1
    //override lazy val cusbChannelWidthSW = 2
    //override lazy val cusbChannelWidthSE = 1 
    
    // SCU to switch channel width
    //override lazy val scsbChannelWidth = 4
    // switch to SCU channel width
    override lazy val sbscChannelWidth = 6

    // MC to switch channel width
    //override lazy val mcsbChannelWidth = 0
    // switch to MC channel width
    //override lazy val sbmcChannelWidth = 1
      
    // MC to SCU channel width
     //override lazy val mcscChannelWidth = 0
    // SCU to MC channel width
     //override lazy val scmcChannelWidth = 2
    
    // OCU to switch channel width
    //override lazy val ocsbChannelWidth = 0
    // switch to OCU channel width
    //lazy val sbocChannelWidth = 2
    
    // Top to switch channel width
    //lazy val tpsbChannelWidth = 1
    // switch to Top channel width
    //lazy val sbtpChannelWidth = 1
  }

  override lazy val ctrlNetwork = new CtrlNetwork() {
    // switch to switch channel width
    override lazy val sbChannelWidth = 12
    //override lazy val sbChannelWidthWE = 1
    //override lazy val sbChannelWidthEW = 1
    //override lazy val sbChannelWidthNS = 1
    //override lazy val sbChannelWidthSN = 1

    // CU to CU channel width
    //override lazy val cuChannelWidth = 0
    //override lazy val cuChannelWidthWE = 1
    //override lazy val cuChannelWidthEW = 1
    //override lazy val cuChannelWidthNS = 1
    //override lazy val cuChannelWidthSN = 1

    // switch to CU channel width
    override lazy val sbcuChannelWidth = 4
    //override lazy val sbcuChannelWidthNW = 1
    //override lazy val sbcuChannelWidthNE = 1
    //override lazy val sbcuChannelWidthSW = 1
    //override lazy val sbcuChannelWidthSE = 1

    // CU to Switch channel width
    override lazy val cusbChannelWidth = 2
    //override lazy val cusbChannelWidthNW = 1
    //override lazy val cusbChannelWidthNE = 1
    //override lazy val cusbChannelWidthSW = 1
    //override lazy val cusbChannelWidthSE = 1
    
    // SCU to switch channel width
    //override lazy val scsbChannelWidth = 4
    // switch to SCU channel width
    //override lazy val sbscChannelWidth = 4

    // MC to switch channel width
    //override lazy val mcsbChannelWidth = 1
    // switch to MC channel width
    //override lazy val sbmcChannelWidth = 1

    // MC to SCU channel width
     //override lazy val mcscChannelWidth = 2
    // SCU to MC channel width
     //override lazy val scmcChannelWidth = 0
      
    // OCU to switch channel width
    override lazy val ocsbChannelWidth = 4
    // switch to OCU channel width
    override lazy val sbocChannelWidth = 6
  }

}
