package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.graph.enums._
import scala.util.{Try, Success, Failure}

// Interconnection network with switch boxes

// Assume no scalarIn and scalarOut buffer are before and after pipeline stages.
// Still have scalarIn and scalarOut as node but make sure # scalarIn and # scalarOut always equal
// to outports and inports of inbus and outbus
object SN_4x4 extends SwitchNetwork {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numArgIns = numLanes  // need to be multiple of numLanes
  private val numArgOuts = numLanes // need to be multiple of numLanes 
  private val numRowCUs = 4
  private val numColCUs = 4

  // Top level controller ~= Host
  override val top = Top(numLanes, numArgIns, numArgOuts)

  private val allCUs = List.tabulate(numRowCUs, numColCUs) { case (i, j) =>
    ConfigFactory.genRCU(numLanes, numSRAMs = 4, numCtrs = 8, numRegs = 20).coord(i, j)
  }
  override val rcus = allCUs.flatten 
  override val ttcus = List.tabulate(numRowCUs) { i =>
    ConfigFactory.genTT(numLanes, numSRAMs = 0, numCtrs = 5, numRegs = 20).coord(-1, i)
  } 

  override val sbs = SwitchBoxes(numRowCUs+1, numColCUs+1, numLanes)
  for (i <- 0 until sbs.size) {
    for (j <- 0 until sbs.head.size) {
      coordOf(sbs(i)(j)) = (i,j) 
      if (i==0 && j<numRowCUs) {
        ttcus(j).vins(0) <== sbs(i)(j).vouts(4)
        sbs(i)(j).vins(1) <== ttcus(j).vouts(0) 
      }
    }
  }

  ConfigFactory.genSwitchNetwork(allCUs, sbs)

  ConfigFactory.genArgIOConnection

}
