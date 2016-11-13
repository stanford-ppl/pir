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
class SwitchNetworkInst(numRowCUs:Int, numColCUs:Int) extends SwitchNetwork {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 16
  override val scalarBandwidth = 2 // BO, how many scalar value can be transmitted on bus 
  override val numScalarInReg = 4 // BO, how many scalar registers connected to the crossbar 
  
  private val numArgIns = scalarBandwidth  // need to be a multiple of scalarBandwidth 
  private val numArgOuts = scalarBandwidth // need to be multiple of scalarBandwidth 
  private val ctrlBandWidth = 8

  val memCtrlCommandFIFOEnqBusIdx:Int = 0
  val memCtrlDataFIFOEnqBusIdx:Int = 1
  val memCtrlCommandFIFONotFullBusIdx:Int = 0
  val memCtrlDataFIFONotFullBusIdx:Int = 1

  // Top level controller ~= Host
  override val top = Top(numArgIns, numArgOuts)

  val cuArray = List.tabulate(numRowCUs, numColCUs) { case (i, j) =>
    val cu = ConfigFactory.genRCU(numSRAMs = 4, numCtrs = 8, numRegs = 16).coord(i, j)
      .ctrlBox(numTokenOutLUTs=8, numTokenDownLUTs=8, inBandwidth=1, outBandwidth=1)
    val bandWidth = 1
    List("W", "NW", "S", "SW").foreach { dir => cu.addVinAt(dir, bandWidth, numLanes) }
    List("E").foreach { dir => cu.addVoutAt(dir, bandWidth, numLanes) }
    cu.vins.zipWithIndex.foreach { case (vi, idx) => vi.index(idx) }
    cu.vouts.zipWithIndex.foreach { case (vo, idx) => vo.index(idx) }
    ConfigFactory.genMapping(cu, vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    cu
  }
  override val rcus = cuArray.flatten 
  override val mcs = List.tabulate(numRowCUs) { i =>
    val cu = ConfigFactory.genMC(numCtrs = 8, numRegs = 16).coord(-1, i)
      .ctrlBox(numTokenOutLUTs=8, numTokenDownLUTs=8, inBandwidth=8, outBandwidth=8)
    List("E").foreach { dir => cu.addVinAt(dir, 4, numLanes) }
    List("E").foreach { dir => cu.addVoutAt(dir, 1, numLanes) }
    cu.vins.zipWithIndex.foreach { case (vi, idx) => vi.index(idx) }
    cu.vouts.zipWithIndex.foreach { case (vo, idx) => vo.index(idx) }
    ConfigFactory.genMapping(cu, vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    cu
  } 

  override val sbs = List.tabulate(numRowCUs+1, numColCUs+1) { case (i, j) =>
    var map = IMap[String, Int]()
    val sb = SwitchBox().coord(i,j)
    SwitchBox.fourDirections.foreach { dir =>
      sb.addVinAt(dir, 1, numLanes)
      sb.addVoutAt(dir, 1, numLanes)
    }
    sb.addVinAt("NW", 1, numLanes)
    sb.addVinAt("SW", 1, numLanes)
    sb.addVoutAt("NE", 1, numLanes)
    sb.addVoutAt("SE", 1, numLanes)
    if (i==0) sb.addVoutAt("W", 3, numLanes)
    sb.vins.zipWithIndex.foreach { case (vi, idx) => vi.index(idx) }
    sb.vouts.zipWithIndex.foreach { case (vo, idx) => vo.index(idx) }
    sb
  }
  override val csbs = List.tabulate(numRowCUs+1, numColCUs+1) { case (i, j) =>
    val sb = if (i==0) {
      val sb = SwitchBox().coord(i,j)
      sb.addVioAt("N", ctrlBandWidth, numLanes)
      sb.addVioAt("NE", ctrlBandWidth, numLanes)
      sb.addVioAt("E", ctrlBandWidth, numLanes)
      sb.addVioAt("S", ctrlBandWidth, numLanes)
      sb.addVioAt("SE", ctrlBandWidth, numLanes)
      sb.addVioAt("W", 8, numLanes)
      sb
    } else {
      SwitchBox.full(bw=ctrlBandWidth, width=1).coord(i,j)
    }
    sb.vins.zipWithIndex.foreach { case (vi, idx) => vi.index(idx) }
    sb.vouts.zipWithIndex.foreach { case (vo, idx) => vo.index(idx) }
    sb
  }

  ConfigFactory.genSwitchNetwork(cuArray, mcs, sbs)
  ConfigFactory.genCtrlSwitchNetwork(cuArray, mcs, csbs)

  ConfigFactory.genArgIOConnection

}

object SN_4x4 extends SwitchNetworkInst(4, 4)
object SN_2x2 extends SwitchNetworkInst(2, 2)
