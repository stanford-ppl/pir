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
object CU2 extends Spade {
  override def toString = "Plasticine_CU2"

  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numArgIns = numLanes  // need to be multiple of numLanes
  private val numArgOuts = numLanes // need to be multiple of numLanes 
  private val numRCUs = 2

  // Top level controller ~= Host
  override val top = Top(numLanes, numArgIns, numArgOuts)

  override val rcus = List.tabulate(numRCUs) { i =>
    ConfigFactory.genRCU(numLanes, numSRAMs = 2, numCtrs = 8, numRegs = 20).index(i)
  } 
  override val ttcus = Nil 

  override val sbs = Nil 

  /* Network Constrain */ 
  rcus(1).vins(0) <== rcus(0).vout 

  ConfigFactory.genArgIOConnection

}
