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
object P2P_4CU_4TT extends Spade {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 4
  private val numTTs = 4

  private val numArgIns = numLanes  // need to be multiple of numLanes
  private val numArgOuts = numLanes // need to be multiple of numLanes 

  // Top level controller ~= Host
  override val top = Top(numLanes, numArgIns, numArgOuts).index(-1)

  override val rcus = List.tabulate(numRCUs) { i =>
    ConfigFactory.genRCU(numLanes, numSRAMs = 4, numCtrs = 8, numRegs = 20).index(i)
  } 

  override val ttcus = List.tabulate(numTTs) { i =>
    ConfigFactory.genTT(numLanes, numSRAMs = 0, numCtrs = 4, numRegs = 20).index(i+rcus.size)
  }

  override val sbs = Nil

  /* Network Constrain */ 
  {
    val (ttload, ttstore) = ttcus.splitAt(2)
    val cus = ttload ++ rcus ++ ttstore 
    ttcus(0).vins(0) <== cus(2).vout
    ttcus(0).vins(0) <== cus(3).vout
    ttcus(1).vins(0) <== cus(2).vout
    ttcus(1).vins(0) <== cus(3).vout
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
