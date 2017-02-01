package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.graph.enums._
import scala.util.{Try, Success, Failure}

// Same as Config0 excepts only has 2 CU without TileTransfer CU 

// Assume no scalarIn and scalarOut buffer are before and after pipeline stages.
// Still have scalarIn and scalarOut as node but make sure # scalarIn and # scalarOut always equal
// to outports and inports of inbus and outbus
object P2P_2CU extends PointToPointNetwork {

  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 2
  private val numArgIns = 4 
  private val numArgOuts = 4 

  val memCtrlCommandFIFOEnqBusIdx:Int = 0
  val memCtrlDataFIFOEnqBusIdx:Int = 1
  val memCtrlCommandFIFONotFullBusIdx:Int = 0
  val memCtrlDataFIFONotFullBusIdx:Int = 1
  val memCtrlDataValidBusIdx:Int = 2

  // Top level controller ~= Host
  override val top = Top(numArgIns, numArgOuts)

  override val rcus = List.tabulate(numRCUs) { i =>
    val cu = ConfigFactory.genRCU(numSRAMs = 2, numCtrs = 8, numRegs = 20).numSinReg(6).addVins(2, numLanes).addVouts(1, numLanes).index(i)
    ConfigFactory.genMapping(cu, vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
    cu
  } 
  override val mcs = Nil 

  /* Network Constrain */ 
  rcus(1).vins(0) <== rcus(0).vout 

}
