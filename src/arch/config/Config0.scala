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
object Config0 extends Spade {
  override def toString = "Plasticine_Config0"

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 2
  private val numTTs = 2 

  private val numArgIns = numLanes  // need to be multiple of numLanes
  private val numArgOuts = numLanes // need to be multiple of numLanes 

  // Top level controller ~= Host
  override val top = Top(numLanes, numArgIns, numArgOuts).index(-1)

  override val rcus = List.tabulate(numRCUs) { i =>
    ConfigFactory.genRCU(numLanes, numSRAMs = 2, numCtrs = 8, numRegs = 20).index(i)
  } 

  override val ttcus = List.tabulate(numTTs) { i =>
    ConfigFactory.genTT(numLanes, numSRAMs = 0, numCtrs = 4, numRegs = 20).index(i+rcus.size)
  }

  override val sbs = Nil

  /* Network Constrain */ 
  rcus(0).vins(0) <== ttcus(0).vout 
  rcus(0).vins(1) <== ttcus(1).vout
  rcus(1).vins(0) <== rcus(0).vout 
  rcus(1).vins(1) <== rcus(0).vout

  ConfigFactory.genArgIOConnection
}
