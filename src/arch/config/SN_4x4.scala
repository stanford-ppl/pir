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

// Interconnection network with switch boxes

// Assume no scalarIn and scalarOut buffer are before and after pipeline stages.
// Still have scalarIn and scalarOut as node but make sure # scalarIn and # scalarOut always equal
// to outports and inports of inbus and outbus

object SN_4x4 extends SwitchNetwork(numRows=4, numCols=4, numArgIns=5, numArgOuts=5) {
}

object SN_2x2 extends SwitchNetwork(numRows=2, numCols=2, numArgIns=5, numArgOuts=5) {

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
  //override val scChannelWidth = 0
  //override val scChannelWidthNW = 1
  //override val scChannelWidthNE = 1
  //override val scChannelWidthSW = 1
  //override val scChannelWidthSE = 1

  // CU to Switch channel width
  //override val csChannelWidth = 0
  //override val csChannelWidthNW = 1
  //override val csChannelWidthNE = 1
  //override val csChannelWidthSW = 1
  //override val csChannelWidthSE = 1
  
  override val msChannelWidth = 4
  // switch to MC channel width
  override val smChannelWidth = 4
    
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
  //override val scChannelWidth = 0
  //override val scChannelWidthNW = 1
  //override val scChannelWidthNE = 1
  //override val scChannelWidthSW = 1
  //override val scChannelWidthSE = 1

  // CU to Switch channel width
  //override val csChannelWidth = 0
  //override val csChannelWidthNW = 1
  //override val csChannelWidthNE = 1
  //override val csChannelWidthSW = 1
  //override val csChannelWidthSE = 1
  
  override val msChannelWidth = 8
  // switch to MC channel width
  override val smChannelWidth = 8
    
  } with VectorNetwork()

  override def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) {
      new ComputeUnit()
        .numRegs(16)
        .numCtrs(8)
        .numSRAMs(4)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .numSinReg(8)
        .ctrlBox(numUDCs=4)
        .coord(i, j)
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
        .coord(i, j)
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
