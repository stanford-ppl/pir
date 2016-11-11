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
  override val scalarBandwidth = numLanes // BO, how many scalar registers can be read from each bus
  override val numScalarInReg = numLanes // BO, how many scalar registers can be read from each bus
  
  private val numRCUs = 4
  private val numMCs = 4

  private val numArgIns = scalarBandwidth  // need to be multiple of scalarBandwith 
  private val numArgOuts = scalarBandwidth // need to be multiple of scalarBandwith 

  // Top level controller ~= Host
  override val top = Top(numArgIns, numArgOuts).index(-1)

  override val rcus = List.tabulate(numRCUs) { i =>
    val cu = ConfigFactory.genRCU(numSRAMs = 2, numCtrs = 8, numRegs = 20).addVins(4, numLanes).addVouts(1, numLanes).index(i)
    ConfigFactory.genMapping(cu, vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
    cu
  } 

  override val mcs = List.tabulate(numMCs) { i =>
    val cu = ConfigFactory.genMC(numCtrs = 4, numRegs = 20).addVins(4, numLanes).addVouts(1, numLanes).index(i+rcus.size)
    ConfigFactory.genMapping(cu, vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
    cu
  }

  /* Network Constrain */ 
  {
    val (ttload, ttstore) = mcs.splitAt(2)
    val cus = ttload ++ rcus ++ ttstore 
    mcs(0).vins(0) <== cus(2).vout
    mcs(0).vins(0) <== cus(3).vout
    mcs(1).vins(0) <== cus(2).vout
    mcs(1).vins(0) <== cus(3).vout
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

  ConfigFactory.genArgIOConnection
}
