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
object SN_4x4 extends SwitchNetwork {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  override val scalarBandwidth = 2 // BO, how many scalar value can be transmitted on bus 
  override val numScalarInReg = 2 // BO, how many scalar registers connected to the crossbar 
  
  private val numArgIns = scalarBandwidth  // need to be a multiple of scalarBandwidth 
  private val numArgOuts = scalarBandwidth // need to be multiple of scalarBandwidth 
  private val numRowCUs = 4
  private val numColCUs = 4

  // Top level controller ~= Host
  override val top = Top(numArgIns, numArgOuts)

  val cuArray = List.tabulate(numRowCUs, numColCUs) { case (i, j) =>
    val cu = ConfigFactory.genRCU(numSRAMs = 4, numCtrs = 8, numRegs = 16).coord(i, j)
    ConfigFactory.genMapping(cu, vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
    cu
  }
  override val rcus = cuArray.flatten 
  override val ttcus = List.tabulate(numRowCUs) { i =>
    val cu = ConfigFactory.genTT(numSRAMs = 0, numCtrs = 8, numRegs = 16).coord(-1, i)
    ConfigFactory.genMapping(cu, vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
    cu
  } 

  override val sbs = List.tabulate(numRowCUs+1, numColCUs+1) { case (i, j) =>
    var map = IMap[String, Int]()
    SwitchBox.fourDirections.foreach { dir =>
      map += s"i$dir" -> 1 
      map += s"o$dir" -> 1 
    }
    if (i!=0) {
      map += s"iNW" -> 1
      map += s"iSW" -> 1
      map += s"oNE" -> 1
      map += s"oSE" -> 1
    } else {
      map += s"iSW" -> 2
      map += s"oNE" -> 1
      map += s"oSE" -> 1
      map += s"oSW" -> 1
    }
    if (i==0) SwitchBox(map, numLanes).coord(i,j) // extra for tileload
    else SwitchBox(map, numLanes).coord(i,j)
  }
  override val csbs = List.tabulate(numRowCUs+1, numColCUs+1) { case (i, j) =>
    SwitchBox.full(bw=2, width=1).coord(i,j)
  }

  for (i <- 0 until sbs.size) {
    for (j <- 0 until sbs.head.size) {
      coordOf(sbs(i)(j)) = (i,j) 
      if (i==0 && j<numRowCUs) {
        ttcus(j).vins(0) <== sbs(i)(j).vouts(4)
        ttcus(j).vins(1) <== sbs(i)(j).vouts(6)
        sbs(i)(j).vins(1) <== ttcus(j).vouts(0) 
      }
    }
  }

  ConfigFactory.genSwitchNetwork(cuArray, sbs)
  ConfigFactory.genCtrlSwitchNetwork(cuArray, ttcus, csbs)

  ConfigFactory.genArgIOConnection

}
