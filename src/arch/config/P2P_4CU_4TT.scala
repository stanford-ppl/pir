package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.graph.enums._

// 4 cu + 2 tt

// Assume no scalarIn and scalarOut buffer are before and after pipeline stages.
// Still have scalarIn and scalarOut as node but make sure # scalarIn and # scalarOut always equal
// to outports and inports of inbus and outbus
object P2P_4CU_4TT extends PointToPointNetwork {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numPCUs = 4
  private val numMCs = 4

  private val numArgIns = numLanes
  private val numArgOuts = numLanes

  val memCtrlCommandFIFOEnqBusIdx:Int = 0
  val memCtrlDataFIFOEnqBusIdx:Int = 1
  val memCtrlCommandFIFONotFullBusIdx:Int = 0
  val memCtrlDataFIFONotFullBusIdx:Int = 1
  val memCtrlDataValidBusIdx:Int = 2

  // Top level controller ~= Host
  override val top = Top(numArgIns, numArgOuts).index(-1)

  override val pcus = List.tabulate(numPCUs) { i =>
    new ComputeUnit()
        .numSRAMs(2)
        .numCtrs(8)
        .numRegs(20)
        .numSinReg(8)
        .vectorIO.addIns(2, numLanes)
        .vectorIO.addOuts(1, numLanes)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .ctrlBox(numUDCs=4)
        .index(i)
        .genConnections
        .genMapping(vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
  } 

  override val mcus = Nil

  override val mcs = Nil

  override val scus = List.tabulate(numMCs) { i =>
    new ScalarComputeUnit()
        .numSRAMs(2)
        .numCtrs(4)
        .numRegs(20)
        .numSinReg(6)
        .vectorIO.addIns(2, numLanes)
        .vectorIO.addOuts(1, numLanes)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .ctrlBox(numUDCs=4)
        .index(i+pcus.size)
        .genConnections
        .genMapping(vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
  }

  /* Network Constrain */ 
  {
    val (ttload, ttstore) = scus.splitAt(2)
    val cus = ttload ++ pcus ++ ttstore 
    scus(0).vins(0) <== cus(2).vout
    scus(0).vins(0) <== cus(3).vout
    scus(1).vins(0) <== cus(2).vout
    scus(1).vins(0) <== cus(3).vout
    cus(2).vins(0) <== cus(4).vout
    cus(2).vins(1) <== cus(5).vout
    cus(2).vins(2) <== cus(3).vout
    cus(3).vins(0) <== cus(4).vout
    cus(3).vins(1) <== cus(5).vout
    cus(3).vins(2) <== cus(2).vout
    cus(4).vins(0) <== cus(6).vout
    cus(4).vins(1) <== cus(7).vout
    cus(4).vins(2) <== cus(5).vout
    cus(5).vins(0) <== cus(6).vout
    cus(5).vins(1) <== cus(7).vout
    cus(5).vins(2) <== cus(4).vout

    coordOf(cus(0)) = (0,0)
    coordOf(cus(1)) = (1,0)
    coordOf(cus(2)) = (0,1)
    coordOf(cus(3)) = (1,1)
    coordOf(cus(4)) = (0,2)
    coordOf(cus(5)) = (1,2)
    coordOf(cus(6)) = (0,3)
    coordOf(cus(7)) = (1,3)
  }

}
