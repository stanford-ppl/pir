package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.config._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._

abstract class SwitchNetwork(val numRows:Int, val numCols:Int, val numArgIns:Int, val numArgOuts:Int) extends Spade {
  implicit override def spade:SwitchNetwork = this
  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  override def pnes = super.pnes ++ sbs

  def diameter = (numRows + numCols - 1)

  // Top level controller ~= Host
  val top = Top(numArgIns, numArgOuts)

  def cuAt(i:Int, j:Int) = if ((i+j) % 2 == 0) new ComputeUnit() else new MemoryComputeUnit()

  def scuAt(c:Int, r:Int):ScalarComputeUnit = new ScalarComputeUnit()

  def mcAt(c:Int, r:Int):MemoryController = new MemoryController()

  def ocuAt(c:Int, r:Int):OuterComputeUnit = new OuterComputeUnit()

  val cuArray = List.tabulate(numRows, numCols) { case (c,r) => cuAt(c,r).coord(c,r) }
  val scus = List.tabulate(2, numRows+1) { case (c, r) => 
    val scu = scuAt(c,r)
    if (c==0) {
      scu.coord(-1, r)
    } else {
      scu.coord(numCols, r)
    }
    scu
  }.flatten
  val mcs = List.tabulate(2, numRows+1) { case (c, r) => 
    val mc = mcAt(c,r)
    if (c==0) {
      mc.coord(-1, r)
    } else {
      mc.coord(numCols, r)
    }
    mc
  }.flatten
  lazy val mcus = cuArray.flatten.collect { case mcu:MemoryComputeUnit => mcu }
  lazy val pcus = cuArray.flatten.filterNot { _.isInstanceOf[MemoryComputeUnit] }
  val ocuArray = List.tabulate(numRows+1, numCols+1) { case (c, r) => ocuAt(c,r).coord(c,r) }
  def ocus:List[OuterComputeUnit] = ocuArray.flatten

  val sbArray:List[List[SwitchBox]] = List.tabulate(numRows+1, numCols+1) { case (i, j) => SwitchBox().coord(i,j) }
  def sbs:List[SwitchBox] = sbArray.flatten

  lazy val ctrlNetwork = new CtrlNetwork()

  lazy val vectorNetwork = new VectorNetwork()

  lazy val scalarNetwork = new ScalarNetwork()

  def config = {
    pcus.foreach { pcu =>
      pcu.numRegs(16)
      .numCtrs(8)
      .addRegstages(numStage=0, numOprds=3, ops)
      .addRdstages(numStage=4, numOprds=3, ops)
      .addRegstages(numStage=2, numOprds=3, ops)
      .ctrlBox(numUDCs=4)
      .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    }
    mcus.foreach { mcu =>
      mcu.numRegs(16)
        .numCtrs(8)
        .addWAstages(numStage=3, numOprds=3, ops)
        .addRAstages(numStage=3, numOprds=3, ops)
        .ctrlBox(numUDCs=4)
        .genConnections
        //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=8, soutsPtr=0, ctrsPtr=0, waPtr=8, wpPtr=9, loadsPtr=8, rdPtr=0)
    }
    scus.foreach { scu =>
      scu.numRegs(6)
        .numCtrs(6)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .ctrlBox(numUDCs=4)
        .genConnections
        //.genMapping(vinsPtr=0, voutPtr=0, sinsPtr=0, soutsPtr=0, ctrsPtr=0, waPtr=0, wpPtr=0, loadsPtr=0, rdPtr=0)
    }
    mcs.foreach { mc =>
      mc.ctrlBox(numUDCs=0)
    }
    ocus.foreach { ocu =>
      ocu.numCtrs(6)
      .ctrlBox(numUDCs=4)
      .genConnections
    }
  }
  config
}

abstract class GridNetwork()(implicit spade:SwitchNetwork) {

  def io(cu:NetworkElement):GridIO[NetworkElement]

  def cuArray:List[List[ComputeUnit]] = spade.cuArray
  def mcs:List[MemoryController] = spade.mcs
  def scus:List[ScalarComputeUnit] = spade.scus
  def ocuArray:List[List[OuterComputeUnit]] = spade.ocuArray
  def sbs:List[List[SwitchBox]] = spade.sbArray

  // switch to switch channel width
  val sbChannelWidth = 4
  val sbChannelWidthWE = sbChannelWidth
  val sbChannelWidthEW = sbChannelWidth
  val sbChannelWidthNS = sbChannelWidth
  val sbChannelWidthSN = sbChannelWidth

  // CU to CU channel width
  val cuChannelWidth = 0
  val cuChannelWidthWE = cuChannelWidth
  val cuChannelWidthEW = cuChannelWidth
  val cuChannelWidthNS = cuChannelWidth
  val cuChannelWidthSN = cuChannelWidth

  // switch to CU channel width
  val sbcuChannelWidth = 1
  val sbcuChannelWidthNW = sbcuChannelWidth
  val sbcuChannelWidthNE = sbcuChannelWidth
  val sbcuChannelWidthSW = sbcuChannelWidth
  val sbcuChannelWidthSE = sbcuChannelWidth

  // CU to Switch channel width
  val cusbChannelWidth = 1
  val cusbChannelWidthNW = cusbChannelWidth
  val cusbChannelWidthNE = cusbChannelWidth
  val cusbChannelWidthSW = cusbChannelWidth
  val cusbChannelWidthSE = cusbChannelWidth

  // SCU to switch channel width
  val scsbChannelWidth = 4
  // switch to SCU channel width
  val sbscChannelWidth = 4

  // MC to switch channel width
  val mcsbChannelWidth = 4
  // switch to MC channel width
  val sbmcChannelWidth = 4

  // MC to SCU channel width
  val mcscChannelWidth = 2
  // SCU to MC channel width
  val scmcChannelWidth = 2

  // OCU to switch channel width
  val ocsbChannelWidth = 2
  // switch to OCU channel width
  val sbocChannelWidth = 4

  lazy val numRows = cuArray.length
  lazy val numCols = cuArray.head.length

  val top = spade.top
  
  spade.pnes.foreach { pne =>
    io(pne).ins.foreach { _.disconnect }
    io(pne).outs.foreach { _.disconnect }
    io(pne).clearIO
  }

  def connect(out:NetworkElement, outDir:String, in:NetworkElement, inDir:String, channelWidth:Int) = {
    val outs = io(out).addOutAt(outDir, channelWidth)
    val ins = io(in).addInAt(inDir, channelWidth)
    outs.zip(ins).foreach { case (o, i) => o ==> i }
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
      connect(cuArray(i)(j), "NW", sbs(i)(j+1), "SE", cusbChannelWidthNW)
      // NE (top right)
      connect(cuArray(i)(j), "NE", sbs(i+1)(j+1), "SW", cusbChannelWidthNE)
      // SW (bottom left)
      connect(cuArray(i)(j), "SW", sbs(i)(j), "NE", cusbChannelWidthSW)
      // SE (bottom right)
      connect(cuArray(i)(j), "SE", sbs(i+1)(j), "NW", cusbChannelWidthSE)

      // SB to CU
      // NW (top left)
      connect(sbs(i)(j+1), "SE", cuArray(i)(j), "NW", sbcuChannelWidthNW)
      // NE (top right)
      connect(sbs(i+1)(j+1), "SW", cuArray(i)(j), "NE", sbcuChannelWidthNE)
      // SW (bottom left)
      connect(sbs(i)(j), "NE", cuArray(i)(j), "SW", sbcuChannelWidthSW)
      // SE (bottom right)
      connect(sbs(i+1)(j), "NW", cuArray(i)(j), "SE", sbcuChannelWidthSE)
    }
  }

  // OCU and SB connection
  for (i <- 0 until numRows+1) {
    for (j <- 0 until numCols+1) {
      // OCU to SB 
      connect(ocuArray(i)(j), "W", sbs(i)(j), "E", ocsbChannelWidth)

      // SB to OCU
      connect(sbs(i)(j), "E", ocuArray(i)(j), "W", sbocChannelWidth)
    }
  }

  //// SCU and SB connection
  for (j <- 0 until scus.size/2) {
    // SCU to SB (W -> E) (left side)
    connect(scus(j), "E", sbs(0)(j), "W", scsbChannelWidth)
    // SB to SCU (E -> W) (left side)
    connect(sbs(0)(j), "W", scus(j), "E", sbscChannelWidth)
  }
  for (j <- scus.size/2 until scus.size) {
    // SCU to SB (E -> W) (right side)
    connect(scus(j), "W", sbs(numCols)(j-scus.size/2), "E", scsbChannelWidth)
    // SB to SCU (W -> E) (right side)
    connect(sbs(numCols)(j-scus.size/2), "E", scus(j), "W", sbscChannelWidth)
  }

  //// MC and SB connection
  for (j <- 0 until mcs.size/2) {
    // MC to SB (W -> E) (left side)
    connect(mcs(j), "E", sbs(0)(j), "W", mcsbChannelWidth)
    // SB to MC (E -> W) (left side)
    connect(sbs(0)(j), "W", mcs(j), "E", sbmcChannelWidth)
  }
  for (j <- mcs.size/2 until mcs.size) {
    // MC to SB (E -> W) (right side)
    connect(mcs(j), "W", sbs(numCols)(j-mcs.size/2), "E", mcsbChannelWidth)
    // SB to MC (W -> E) (right side)
    connect(sbs(numCols)(j-mcs.size/2), "E", mcs(j), "W", sbmcChannelWidth)
  }

  //// MC and SCU connection
  for (j <- 0 until mcs.size) {
    // MC to SCU (S -> N)
    connect(mcs(j), "N", scus(j), "S", mcscChannelWidth)
    // SCU to MC (N -> S)
    connect(scus(j), "S", mcs(j), "N", scmcChannelWidth)
  }

  sbs.foreach { row =>
    row.foreach { sb =>
      io(sb).ins.zipWithIndex.foreach { case (in, idx) => in.index(idx) }
      io(sb).outs.zipWithIndex.foreach { case (out, idx) => out.index(idx) }
    }
  }
  cuArray.flatten.foreach { cu =>
    io(cu).ins.zipWithIndex.foreach { case (in, idx) => in.index(idx) }
    io(cu).outs.zipWithIndex.foreach { case (out, idx) => out.index(idx) }
  }
  scus.foreach { scu =>
    io(scu).ins.zipWithIndex.foreach { case (in, idx) => in.index(idx) }
    io(scu).outs.zipWithIndex.foreach { case (out, idx) => out.index(idx) }
  }
  ocuArray.flatten.foreach { ocu =>
    io(ocu).ins.zipWithIndex.foreach { case (in, idx) => in.index(idx) }
    io(ocu).outs.zipWithIndex.foreach { case (out, idx) => out.index(idx) }
  }
  mcs.foreach { mc =>
    io(mc).ins.zipWithIndex.foreach { case (in, idx) => in.index(idx) }
    io(mc).outs.zipWithIndex.foreach { case (out, idx) => out.index(idx) }
  }
  io(top).ins.zipWithIndex.foreach { case (in, idx) => in.index(idx) }
  io(top).outs.zipWithIndex.foreach { case (out, idx) => out.index(idx) }

}

class VectorNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  def io(pne:NetworkElement):GridIO[_<:NetworkElement] = pne.vectorIO
}

class ScalarNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  def io(pne:NetworkElement):GridIO[_<:NetworkElement] = pne.scalarIO
}

class CtrlNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  def io(pne:NetworkElement):GridIO[_<:NetworkElement] = pne.ctrlIO
}

