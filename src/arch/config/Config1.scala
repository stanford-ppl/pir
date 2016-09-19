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
object Config1 extends Spade {
  override def toString = "Plasticine_Config0"

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
    Config0.genRCU(numLanes, numSRAMs = 4, numCtrs = 8, numRegs = 20)
  }
  override val rcus = allCUs.flatten 
  override val ttcus = Nil 

  val switchBoxes = SwitchBoxes(numRowCUs+1, numColCUs+1, numLanes)
  override val sbs = switchBoxes.flatten 

  genNetwork(allCUs, switchBoxes)

  def genNetwork(cus:List[List[ComputeUnit]], sbs:List[List[SwitchBox]]) = {
    val numRowCUs = cus.size
    val numColCus = cus.head.size
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // CU to CU (Horizontal)
        if (i!=numRowCUs-1)
          cus(i+1)(j).vins(0) <== cus(i)(j).vout
        // CU to CU (Vertical)
        if (j!=numColCUs-1)
          cus(i)(j+1).vins(2) <== cus(i)(j).vout
      }
    }
    for (i <- 0 until numRowCUs+1) {
      for (j <- 0 until numColCUs+1) {
        // SB to SB (Horizontal)
        if (i!=numRowCUs) {
          sbs(i+1)(j).vins(2) <== sbs(i)(j).vouts(1)
          sbs(i)(j).vins(5) <== sbs(i+1)(j).vouts(4)
        }
        // SB to SB (Vertical)
        if (j!=numColCUs) {
          sbs(i)(j+1).vins(0) <== sbs(i)(j).vouts(2)
          sbs(i)(j).vins(4) <== sbs(i)(j+1).vouts(0)
        }
      }
    }
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // SB to CU (NW -> SE)
        cus(i)(j).vins(1) <== sbs(i)(j).vouts(3)
        // SB to CU (SW -> NE)
        cus(i)(j).vins(3) <== sbs(i)(j+1).vouts(5)
        // CU to SB (SW -> NE)
        sbs(i+1)(j).vins(3) <== cus(i)(j).vout
        // CU to SB (NW -> SE)
        sbs(i+1)(j+1).vins(1) <== cus(i)(j).vout
      }
    }
  }

  /* Connnect all ArgIns to scalarIns of all CUs and all ArgOuts to scalarOuts of all CUs*/
  cus.foreach { cu =>
    top.vouts.foreach { aib =>
      cu.vins.foreach { vin =>
        vin <== aib
      }
    }
    top.vins.foreach { aob => 
      aob <== cu.vout
    }
  }

}
