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
  } with ScalarNetwork()

  override def config = {
    pcus.foreach { cu =>
      cu.numRegs(16)
        .numCtrs(8).color(0 until 0 + cu.numCtrs, CounterReg)
        .addRegstages(numStage=14, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .numScalarIns(scalarNetwork.io(cu).numIns).color(8 until 8 + cu.numScalarIns, ScalarInReg)
        .numScalarOuts(scalarNetwork.io(cu).numOuts).color(0 until 0 + cu.numScalarOuts, ScalarOutReg)
        .numVecIns(vectorNetwork.io(cu).numIns).color(12 until 12 + cu.numVecIns, VecInReg)
        .numVecOuts(vectorNetwork.io(cu).numOuts).color(0 until 0 + cu.numVecOuts, VecOutReg)
        .color(0, ReduceReg)
        .ctrlBox(numUDCs=5)
        .genConnections
    }
    mcus.foreach { cu =>
      cu.numRegs(16)
        .numCtrs(8).color(0 until 0 + cu.numCtrs, CounterReg)
        .numSRAMs(1).color(8, LoadReg).color(7, ReadAddrReg).color(8, WriteAddrReg).color(9, StoreReg)
        .addWAstages(numStage=3, numOprds=3, ops)
        .addRAstages(numStage=3, numOprds=3, ops)
        .ctrlBox(numUDCs=4)
        .genConnections
    }
    scus.foreach { cu =>
      cu.numRegs(6)
        .numCtrs(6)
        .numSRAMs(4)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .ctrlBox(numUDCs=4)
        .genConnections
        //.genMapping(vinsPtr=0, voutPtr=0, sinsPtr=0, soutsPtr=0, ctrsPtr=0, waPtr=0, wpPtr=0, loadsPtr=0, rdPtr=0)
    }
    mcs.foreach { mc =>
      mc.ctrlBox(numUDCs=0)
    }
    ocus.foreach { cu =>
      cu.numCtrs(6)
      .ctrlBox(numUDCs=4)
      .genConnections
    }
    scalarNetwork
    ctrlNetwork
    vectorNetwork
  }

}

object SN_8x8 extends SwitchNetwork(numRows=8, numCols=8, numArgIns=5, numArgOuts=5) with SN_temp {
  override def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) {
      new ComputeUnit()
        .numRegs(16)
        .numCtrs(8)
        .numSRAMs(4)
        .addRegstages(numStage=14, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .ctrlBox(numUDCs=6)
        .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    } else {
      new MemoryComputeUnit()
        .numRegs(16)
        .numCtrs(8)
        .numSRAMs(4)
        .addWAstages(numStage=3, numOprds=3, ops)
        .addRAstages(numStage=3, numOprds=3, ops)
        .ctrlBox(numUDCs=6)
        .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    }
  }
}

object SN_5x5 extends SwitchNetwork(numRows=5, numCols=5, numArgIns=5, numArgOuts=5) with SN_temp {
}

object SN_4x4 extends SwitchNetwork(numRows=4, numCols=4, numArgIns=5, numArgOuts=5) with SN_temp {

}

object SN_2x2 extends SwitchNetwork(numRows=2, numCols=2, numArgIns=5, numArgOuts=5) with SN_temp {

}
