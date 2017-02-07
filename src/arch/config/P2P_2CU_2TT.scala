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
object P2P_2CU_2TT extends PointToPointNetwork {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numPCUs = 2
  private val numMCs = 2 

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
        .numSinReg(6)
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

  override val ocus = Nil 

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
  pcus(0).vins(0) <== scus(0).vout 
  pcus(0).vins(1) <== scus(1).vout
  pcus(1).vins(0) <== pcus(0).vout 
  pcus(1).vins(1) <== pcus(0).vout

}
