package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.config._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._
import scala.language.existentials

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

  val cuArray = List.tabulate(numRows, numCols) { case (y, x) => cuAt(y, x).coord(x, y) }
  val scuArray = List.tabulate(numRows+1, 2) { case (y, x) => 
    val scu = scuAt(y, x)
    if (x==0) {
      scu.coord(-1, y)
    } else {
      scu.coord(numCols, y)
    }
    scu
  }
  def scus = scuArray.flatten
  val mcArray = List.tabulate(numRows+1, 2) { case (y, x) => 
    val mc = mcAt(y, x)
    if (x==0) {
      mc.coord(-1, y)
    } else {
      mc.coord(numCols, y)
    }
    mc
  }
  def mcs = mcArray.flatten
  lazy val mcus = cuArray.flatten.collect { case mcu:MemoryComputeUnit => mcu }
  lazy val pcus = cuArray.flatten.filterNot { _.isInstanceOf[MemoryComputeUnit] }
  val ocuArray = List.tabulate(numRows+1, numCols+1) { case (y, x) => ocuAt(y, x).coord(x, y) }
  def ocus:List[OuterComputeUnit] = ocuArray.flatten

  val sbArray:List[List[SwitchBox]] = List.tabulate(numRows+1, numCols+1) { case (y, x) => SwitchBox().coord(x, y) }
  def sbs:List[SwitchBox] = sbArray.flatten

  lazy val ctrlNetwork = new CtrlNetwork()

  lazy val vectorNetwork = new VectorNetwork()

  lazy val scalarNetwork = new ScalarNetwork()

  def config = {
    pcus.foreach { cu =>
      cu.numRegs(16)
        .numCtrs(8).color(0 until 0 + cu.numCtrs, CounterReg)
        .addRegstages(numStage=14, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .numScalarBufs(scalarNetwork.io(cu).numIns).color(8 until 8 + cu.numScalarBufs, ScalarInReg)
        .numVecBufs(vectorNetwork.io(cu).numIns).color(12 until 12 + cu.numVecBufs, VecInReg)
        .color(8 until 8 + scalarNetwork.io(cu).numIns, ScalarOutReg)
        .color(12 until 12 + vectorNetwork.io(cu).numOuts, VecOutReg)
        .color(0, ReduceReg)
        .ctrlBox(numUDCs=5)
        .genConnections
        .genMapping
    }
    mcus.foreach { cu =>
      cu.numRegs(16)
        .numCtrs(8).color(0 until 0 + cu.numCtrs, CounterReg)
        .numSRAMs(1).color(8, LoadReg).color(7, ReadAddrReg).color(8, WriteAddrReg).color(9, StoreReg)
        .addWAstages(numStage=3, numOprds=3, ops)
        .addRAstages(numStage=3, numOprds=3, ops)
        .numScalarBufs(scalarNetwork.io(cu).numIns).color(8 until 8 + cu.numScalarBufs, ScalarInReg)
        .numVecBufs(vectorNetwork.io(cu).numIns).color(12 until 12 + cu.numVecBufs, VecInReg)
        .ctrlBox(numUDCs=4)
        .genConnections
        .genMapping
    }
    scus.foreach { cu =>
      cu.numRegs(6)
        .numCtrs(6)
        .numSRAMs(4)
        .addRegstages(numStage=0, numOprds=3, ops)
        .addRdstages(numStage=4, numOprds=3, ops)
        .addRegstages(numStage=2, numOprds=3, ops)
        .ctrlBox(numUDCs=4)
        .genConnections
        .genMapping
    }
    mcs.foreach { mc =>
      mc.ctrlBox(numUDCs=0)
    }
    ocus.foreach { cu =>
      cu.numCtrs(6)
      .ctrlBox(numUDCs=4)
      .genConnections
      .genMapping
    }
    scalarNetwork
    ctrlNetwork
    vectorNetwork
  }
  config
}

abstract class GridNetwork()(implicit spade:SwitchNetwork) {

  type P <: PortType
  def io(cu:NetworkElement):GridIO[P, NetworkElement]

  def cuArray:List[List[ComputeUnit]] = spade.cuArray
  def mcArray:List[List[MemoryController]] = spade.mcArray
  def mcs:List[MemoryController] = spade.mcs
  def scuArray:List[List[ScalarComputeUnit]] = spade.scuArray
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

  // Top to switch channel width
  val tpsbChannelWidth = 1
  // switch to Top channel width
  val sbtpChannelWidth = 1

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
  for (y <- 0 until numRows) {
    for (x <- 0 until numCols) {
      // CU to CU (Horizontal)
      if (y!=numRows-1) {
        // W -> E
        connect(cuArray(y)(x), "E", cuArray(y+1)(x), "W", cuChannelWidthWE)
        // E -> W
        connect(cuArray(y+1)(x), "W", cuArray(y)(x), "E", cuChannelWidthEW)
      }
      //// CU to CU (Vertical)
      if (x!=numCols-1) {
        // S -> N
        connect(cuArray(y)(x), "N", cuArray(y)(x+1), "S", cuChannelWidthSN)
        // N -> S 
        connect(cuArray(y)(x+1), "S", cuArray(y)(x), "N", cuChannelWidthNS)
      }
    }
  }

  // Switch to Switch connections
  for (y <- 0 until numRows+1) {
    for (x <- 0 until numCols+1) {
      // SB to SB (Horizontal)
      if (y!=numRows) {
        // W -> E 
        connect(sbs(y)(x), "E", sbs(y+1)(x), "W", sbChannelWidthWE)
        // E -> W
        connect(sbs(y+1)(x), "W", sbs(y)(x), "E", sbChannelWidthEW)
      }
      // SB to SB (Vertical)
      if (x!=numCols) {
        // S -> N
        connect(sbs(y)(x), "N", sbs(y)(x+1), "S", sbChannelWidthSN)
        // N -> S 
        connect(sbs(y)(x+1), "S", sbs(y)(x), "N", sbChannelWidthNS)
      }

      // Top to SB
      // Top Switches
      if (y==numRows) {
        connect(sbs(y)(x), "N", top, "S", sbtpChannelWidth) // bottom up 
        connect(top, "S", sbs(y)(x), "N", tpsbChannelWidth) // top down
      }
      // Bottom Switches
      if (y==0) {
        connect(sbs(y)(x), "S", top, "N", sbtpChannelWidth) // top down 
        connect(top, "N", sbs(y)(x), "S", tpsbChannelWidth) // bottom up
      }
    }
  }

  // CU and SB connection
  for (y <- 0 until numRows) {
    for (x <- 0 until numCols) {
      // CU to SB 
      // NW (top left)
      connect(cuArray(y)(x), "NW", sbs(y+1)(x), "SE", cusbChannelWidthNW)
      // NE (top right)
      connect(cuArray(y)(x), "NE", sbs(y+1)(x+1), "SW", cusbChannelWidthNE)
      // SW (bottom left)
      connect(cuArray(y)(x), "SW", sbs(y)(x), "NE", cusbChannelWidthSW)
      // SE (bottom right)
      connect(cuArray(y)(x), "SE", sbs(y)(x+1), "NW", cusbChannelWidthSE)

      // SB to CU
      // NW (top left)
      connect(sbs(y+1)(x), "SE", cuArray(y)(x), "NW", sbcuChannelWidthNW)
      // NE (top right)
      connect(sbs(y+1)(x+1), "SW", cuArray(y)(x), "NE", sbcuChannelWidthNE)
      // SW (bottom left)
      connect(sbs(y)(x), "NE", cuArray(y)(x), "SW", sbcuChannelWidthSW)
      // SE (bottom right)
      connect(sbs(y)(x+1), "NW", cuArray(y)(x), "SE", sbcuChannelWidthSE)
    }
  }

  // OCU and SB connection
  for (y <- 0 until numRows+1) {
    for (x <- 0 until numCols+1) {
      // OCU to SB 
      connect(ocuArray(y)(x), "W", sbs(y)(x), "E", ocsbChannelWidth)

      // SB to OCU
      connect(sbs(y)(x), "E", ocuArray(y)(x), "W", sbocChannelWidth)
    }
  }

  //// SCU and SB connection
  for (y <- 0 until scuArray.size) { //rows
    for (x <- 0 until scuArray.headOption.map(_.size).getOrElse(0)) { //cols
      if (x==0) {
        // SCU to SB (W -> E) (left side)
        connect(scuArray(y)(x), "E", sbs(y)(0), "W", scsbChannelWidth)
        // SB to SCU (E -> W) (left side)
        connect(sbs(y)(0), "W", scuArray(y)(x), "E", sbscChannelWidth)
      } else {
        // SCU to SB (E -> W) (right side)
        connect(scuArray(y)(x), "W", sbs(y)(numCols), "E", scsbChannelWidth)
        // SB to SCU (W -> E) (right side)
        connect(sbs(y)(numCols), "E", scuArray(y)(x), "W", sbscChannelWidth)
      }
    }
  }

  //// MC and SB connection
  for (y <- 0 until mcArray.size) { //rows
    for (x <- 0 until mcArray.headOption.map(_.size).getOrElse(0)) { //cols
      if (x==0) {
        // MC to SB (W -> E) (left side)
        connect(mcArray(y)(x), "E", sbs(y)(0), "W", mcsbChannelWidth)
        // SB to MC (E -> W) (left side)
        connect(sbs(y)(0), "W", mcArray(y)(x), "E", sbmcChannelWidth)
      } else {
        // MC to SB (E -> W) (right side)
        connect(mcArray(y)(x), "W", sbs(y)(numCols), "E", mcsbChannelWidth)
        // SB to MC (W -> E) (right side)
        connect(sbs(y)(numCols), "E", mcArray(y)(x), "W", sbmcChannelWidth)
      }
    }
  }

  //// MC and SCU connection
  for (i <- 0 until mcs.size) {
    // MC to SCU (S -> N)
    connect(mcs(i), "N", scus(i), "S", mcscChannelWidth)
    // SCU to MC (N -> S)
    connect(scus(i), "S", mcs(i), "N", scmcChannelWidth)
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
  type P = VectorIO.P
  def io(pne:NetworkElement) = pne.vectorIO
}

class ScalarNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = ScalarIO.P
  def io(pne:NetworkElement) = pne.scalarIO
}

class CtrlNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = ControlIO.P
  def io(pne:NetworkElement) = pne.ctrlIO
}

