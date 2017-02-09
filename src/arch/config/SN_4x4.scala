package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.immutable.{Map => IMap}
import scala.reflect.runtime.universe._
import pir.graph.enums._
import scala.util.{Try, Success, Failure}

trait SN_temp extends SwitchNetwork {

  override val ctrlNetwork = new {

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
  override val mcsbChannelWidth = 1
  // switch to MC channel width
  override val sbmcChannelWidth = 1

  // MC to SCU channel width
  // override val mcscChannelWidth = 4
  // SCU to MC channel width
  // override val scmcChannelWidth = 4
    
  // OCU to switch channel width
  //val ocsbChannelWidth = 2
  // switch to OCU channel width
  //val sbocChannelWidth = 2

  } with CtrlNetwork()

  override val vectorNetwork = new {

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
  override val mcsbChannelWidth = 1
  // switch to MC channel width
  override val sbmcChannelWidth = 1
    
  // MC to SCU channel width
  // override val mcscChannelWidth = 4
  // SCU to MC channel width
  // override val scmcChannelWidth = 4
  
  // OCU to switch channel width
  //val ocsbChannelWidth = 2
  // switch to OCU channel width
  //val sbocChannelWidth = 2
  } with VectorNetwork()

  override val scalarNetwork = new {

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
  } with ScalarNetwork()

  override def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) {
      new ComputeUnit()
        .numRegs(16)
        .numCtrs(8)
        .numSRAMs(4)
        .addRegstages(numStage=2, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .numSinReg(8)
        .ctrlBox(numUDCs=4)
        .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    } else {
      new MemoryComputeUnit()
        .numRegs(16)
        .numCtrs(8)
        .numSRAMs(4)
        .addWAstages(numStage=3, numOprds=3, ops)
        .addRAstages(numStage=3, numOprds=3, ops)
        .numSinReg(8)
        .ctrlBox(numUDCs=4)
        .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    }
  }

  override def scuAt(c:Int, r:Int) = {
    new ScalarComputeUnit()
        .numRegs(6)
        .numCtrs(6)
        .numSRAMs(4)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .numSinReg(6)
        .ctrlBox(numUDCs=4)
        .genConnections
        //.genMapping(vinsPtr=0, voutPtr=0, sinsPtr=0, soutsPtr=0, ctrsPtr=0, waPtr=0, wpPtr=0, loadsPtr=0, rdPtr=0)
  } 

}

object SN_4x4 extends SwitchNetwork(numRows=4, numCols=4, numArgIns=5, numArgOuts=5) with SN_temp {

}

object SN_2x2 extends SwitchNetwork(numRows=2, numCols=2, numArgIns=5, numArgOuts=5) with SN_temp {

}
