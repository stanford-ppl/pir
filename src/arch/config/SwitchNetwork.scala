package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.config._
import scala.collection.immutable.{Map => IMap}
import pir.graph.enums._

abstract class SwitchNetwork(val numRows:Int, val numCols:Int, val numArgIns:Int, val numArgOuts:Int) extends Spade {
  implicit override def spade:SwitchNetwork = this
  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  val memCtrlCommandFIFOEnqBusIdx:Int = 0
  val memCtrlDataFIFOEnqBusIdx:Int = 1
  val memCtrlCommandFIFONotFullBusIdx:Int = 0
  val memCtrlDataFIFONotFullBusIdx:Int = 1
  val memCtrlDataValidBusIdx:Int = 2

  override def pnes = ctrlers ++ Nil

  // Top level controller ~= Host
  val top = Top(numArgIns, numArgOuts)

  def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) {
      new ComputeUnit()
        .numRegs(16)
        .numCtrs(8)
        .numSRAMs(4)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .numSinReg(8)
        .ctrlBox(numUDCs=4)
        .coord(i, j)
        .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    } else {
      new MemoryComputeUnit()
        .numRegs(16)
        .numCtrs(8)
        .numSRAMs(4)
        .addWAstages(numStage=3, numOprds=3, ops)
        .addRAstages(numStage=3, numOprds=3, ops)
        .numSinReg(8)
        .ctrlBox(numUDCs=4)
        .coord(i, j)
        .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    }
  }

  def scuAt(c:Int, r:Int):ScalarComputeUnit = {
    new ScalarComputeUnit()
        .numRegs(6)
        .numCtrs(6)
        .numSRAMs(4)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .numSinReg(6)
        .ctrlBox(numUDCs=4)
        .genConnections
        //.genMapping(vinsPtr=0, voutPtr=0, sinsPtr=0, soutsPtr=0, ctrsPtr=0, waPtr=0, wpPtr=0, loadsPtr=0, rdPtr=0)
  } 

  def mcAt(c:Int, r:Int):MemoryController = {
    new MemoryController()
        .ctrlBox(numUDCs=0)
  }

  val cuArray = List.tabulate(numRows, numCols) { case (i, j) => cuAt(i,j) }

  val scus = List.tabulate(2, numRows+1) { case (c, r) =>
    val cu = scuAt(c,r)
    if (c==0) {
      cu.coord(-1, r)
    } else {
      cu.coord(numCols, r)
    }
    cu
  }.flatten

  val mcs = List.tabulate(2, numRows+1) { case (c, r) =>
    val cu = mcAt(c,r)
    if (c==0) {
      cu.coord(-2, r)
    } else {
      cu.coord(numCols + 1, r)
    }
    cu
  }.flatten

  def pcus = cuArray.flatten.filterNot { _.isInstanceOf[MemoryComputeUnit] }
  def mcus = cuArray.flatten.collect { case mcu:MemoryComputeUnit => mcu }

  val sbs = List.tabulate(numRows+1, numCols+1) { case (i, j) => SwitchBox().coord(i,j) }

  val ctrlNetwork = new CtrlNetwork()

  val vectorNetwork = new VectorNetwork()

  val scalarNetwork = new ScalarNetwork()

  def switchNetworkDataBandwidth:Int = {
    sbs.flatten.map{ sb =>
      sb.vectorIO.ins.filter(_.isConnected).flatMap { vin =>
        vin.fanIns.filter{_.src.isInstanceOf[SwitchBox]}.headOption.map{ _.src }
      }.groupBy( k => k ).map{case (k, l)  => l.size}.max
    }.max
  }
  def switchNetworkCtrlBandwidth:Int = {
    sbs.flatten.map{ sb =>
      sb.ctrlIO.ins.filter(_.isConnected).flatMap { vin =>
        vin.fanIns.filter{_.src.isInstanceOf[SwitchBox]}.headOption.map{ _.src }
      }.groupBy( k => k ).map{ case (k, l)  => l.size}.max
    }.max
  }

}

abstract class ConnectionNetwork(linkWidth:Int)(implicit spade:SwitchNetwork) {

  def grid(cu:NetworkElement):GridIO[NetworkElement]

  def cuArray:List[List[ComputeUnit]] = spade.cuArray
  def mcs:List[MemoryController] = spade.mcs
  def sbs:List[List[SwitchBox]] = spade.sbs

  // switch to switch channel width
  val sbChannelWidth = 4
  val sbChannelWidthWE = sbChannelWidth
  val sbChannelWidthEW = sbChannelWidth
  val sbChannelWidthNS = sbChannelWidth
  val sbChannelWidthSN = sbChannelWidth

  // CU to CU channel width
  val cuChannelWidth = 1
  val cuChannelWidthWE = cuChannelWidth
  val cuChannelWidthEW = cuChannelWidth
  val cuChannelWidthNS = cuChannelWidth
  val cuChannelWidthSN = cuChannelWidth

  // switch to CU channel width
  val scChannelWidth = 1
  val scChannelWidthNW = scChannelWidth
  val scChannelWidthNE = scChannelWidth
  val scChannelWidthSW = scChannelWidth
  val scChannelWidthSE = scChannelWidth

  // CU to Switch channel width
  val csChannelWidth = 1
  val csChannelWidthNW = csChannelWidth
  val csChannelWidthNE = csChannelWidth
  val csChannelWidthSW = csChannelWidth
  val csChannelWidthSE = csChannelWidth

  // MC to switch channel width
  val msChannelWidth = 8
  // switch to MC channel width
  val smChannelWidth = 8

  val numRows = cuArray.length
  val numCols = cuArray.head.length

  val top = spade.top
  
  spade.pnes.foreach { pne =>
    grid(pne).ins.foreach { _.disconnect }
    grid(pne).outs.foreach { _.disconnect }
    grid(pne).clearIO
  }

  def connect(out:NetworkElement, outDir:String, in:NetworkElement, inDir:String, channelWidth:Int) = {
    grid(out).addOutAt(outDir, channelWidth, linkWidth)
    grid(in).addInAt(inDir, channelWidth, linkWidth)
    grid(out).outAt(outDir).zip(grid(in).inAt(inDir)).foreach { case (o, i) => o ==> i }
  }

  // CU to CU Connection
  for (i <- 0 until numRows) {
    for (j <- 0 until numCols) {
      // CU to CU (Horizontal)
      if (i!=numRows-1) {
        // W -> E
        connect(cuArray(i)(j), "E", cuArray(i+1)(j), "W", cuChannelWidthWE)
        // E -> W
        connect(cuArray(i+1)(j), "W", cuArray(i)(j), "E", cuChannelWidthEW)
      }
      //// CU to CU (Vertical)
      if (j!=numCols-1) {
        // S -> N
        connect(cuArray(i)(j), "N", cuArray(i)(j+1), "S", cuChannelWidthSN)
        // N -> S 
        connect(cuArray(i)(j+1), "S", cuArray(i)(j), "N", cuChannelWidthNS)
      }
    }
  }

  // Switch to Switch connections
  for (i <- 0 until numRows+1) {
    for (j <- 0 until numCols+1) {
      // SB to SB (Horizontal)
      if (i!=numRows) {
        // W -> E 
        connect(sbs(i)(j), "E", sbs(i+1)(j), "W", sbChannelWidthWE)
        // E -> W
        connect(sbs(i+1)(j), "W", sbs(i)(j), "E", sbChannelWidthEW)
      }
      // SB to SB (Vertical)
      if (j!=numCols) {
        // S -> N
        connect(sbs(i)(j), "N", sbs(i)(j+1), "S", sbChannelWidthSN)
        // N -> S 
        connect(sbs(i)(j+1), "S", sbs(i)(j), "N", sbChannelWidthNS)
      }

      // Top to SB
      // Top Switches
      if (j==numCols) {
        connect(sbs(i)(j), "N", top, "S", 1) // bottom up 
        connect(top, "S", sbs(i)(j), "N", 1) // top down
      }
      // Bottom Switches
      if (j==0) {
        connect(sbs(i)(j), "S", top, "N", 1) // top down 
        connect(top, "N", sbs(i)(j), "S", 1) // bottom up
      }
    }
  }

  // CU and SB connection
  for (i <- 0 until numRows) {
    for (j <- 0 until numCols) {
      // CU to SB 
      // NW (top left)
      connect(cuArray(i)(j), "NW", sbs(i)(j+1), "SE", csChannelWidthNW)
      // NE (top right)
      connect(cuArray(i)(j), "NE", sbs(i+1)(j+1), "SW", csChannelWidthNE)
      // SW (bottom left)
      connect(cuArray(i)(j), "SW", sbs(i)(j), "NE", csChannelWidthSW)
      // SE (bottom right)
      connect(cuArray(i)(j), "SE", sbs(i+1)(j), "NW", csChannelWidthSE)

      // SB to CU
      // NW (top left)
      connect(sbs(i)(j+1), "SE", cuArray(i)(j), "NW", scChannelWidthNW)
      // NE (top right)
      connect(sbs(i+1)(j+1), "SW", cuArray(i)(j), "NE", scChannelWidthNE)
      // SW (bottom left)
      connect(sbs(i)(j), "NE", cuArray(i)(j), "SW", scChannelWidthSW)
      // SE (bottom right)
      connect(sbs(i+1)(j), "NW", cuArray(i)(j), "SE", scChannelWidthSE)
    }
  }

  //// MC and SB connection
  for (j <- 0 until mcs.size/2) {
    // MC to SB (W -> E) (left side)
    connect(mcs(j), "E", sbs(0)(j), "W", msChannelWidth)
    // SB to MC (E -> W) (left side)
    connect(sbs(0)(j), "W", mcs(j), "E", smChannelWidth)
  }
  for (j <- mcs.size/2 until mcs.size) {
    // MC to SB (E -> W) (right side)
    connect(mcs(j), "W", sbs(numCols)(j-mcs.size/2), "E", msChannelWidth)
    // SB to MC (W -> E) (right side)
    connect(sbs(numCols)(j-mcs.size/2), "E", mcs(j), "W", smChannelWidth)
  }

  sbs.foreach { row =>
    row.foreach { sb =>
      grid(sb).ins.zipWithIndex.foreach { case (vi, idx) => vi.index(idx) }
      grid(sb).outs.zipWithIndex.foreach { case (vo, idx) => vo.index(idx) }
    }
  }
  cuArray.foreach { row =>
    row.zipWithIndex.foreach { case (cu, j) =>
      grid(cu).ins.zipWithIndex.foreach { case (ci, idx) => ci.index(idx) }
      grid(cu).outs.zipWithIndex.foreach { case (co, idx) => co.index(idx) }
    }
  }
  mcs.foreach { mc =>
    grid(mc).ins.zipWithIndex.foreach { case (ci, idx) => ci.index(idx) }
    grid(mc).outs.zipWithIndex.foreach { case (co, idx) => co.index(idx) }
  }

}

class VectorNetwork()(implicit spade:SwitchNetwork) extends ConnectionNetwork(linkWidth=spade.numLanes) {
  def grid(pne:NetworkElement):GridIO[NetworkElement] = pne.vectorIO
}

class ScalarNetwork()(implicit spade:SwitchNetwork) extends ConnectionNetwork(linkWidth=1) {
  def grid(pne:NetworkElement):GridIO[NetworkElement] = pne.scalarIO
}

class CtrlNetwork()(implicit spade:SwitchNetwork) extends ConnectionNetwork(linkWidth=1) {
  def grid(pne:NetworkElement):GridIO[NetworkElement] = pne.ctrlIO
}

