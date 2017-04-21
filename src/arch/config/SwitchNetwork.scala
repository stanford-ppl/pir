package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.config._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._
import scala.language.existentials

abstract class SwitchNetwork(val numRows:Int, val numCols:Int, val numArgIns:Int, val numArgOuts:Int) extends Spade {
  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  override def pnes = super.pnes ++ sbs

  def diameter = (numRows + numCols - 1)

  // Top level controller ~= Host
  val top = Top(numArgIns, numArgOuts)

  def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) new ComputeUnit()
    else new MemoryComputeUnit()
  }

  def scuAt(c:Int, r:Int):ScalarComputeUnit = new ScalarComputeUnit()

  def mcAt(c:Int, r:Int):MemoryController = new MemoryController()

  def ocuAt(c:Int, r:Int):OuterComputeUnit = new OuterComputeUnit()

  val cuArray = List.tabulate(numCols, numRows) { case (x, y) => cuAt(x, y).coord(x, y) }
  val scuArray = List.tabulate(2, numRows+1) { case (x, y) => 
    val scu = scuAt(x, y)
    if (x==0) {
      scu.coord(-1, y)
    } else {
      scu.coord(numCols, y)
    }
    scu
  }
  def scus = scuArray.flatten
  val mcArray = List.tabulate(2, numRows+1) { case (x, y) => 
    val mc = mcAt(x, y)
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
  val ocuArray = List.tabulate(numCols+1, numRows+1) { case (x, y) => ocuAt(x, y).coord(x, y) }
  def ocus:List[OuterComputeUnit] = ocuArray.flatten

  val sbArray:List[List[SwitchBox]] = List.tabulate(numCols+1, numRows+1) { case (x, y) => SwitchBox().coord(x, y) }
  def sbs:List[SwitchBox] = sbArray.flatten

  lazy val ctrlNetwork = new CtrlNetwork()

  lazy val vectorNetwork = new VectorNetwork()

  lazy val scalarNetwork = new ScalarNetwork()

  override def config:Unit = {
    scalarNetwork.reset
    ctrlNetwork.reset
    vectorNetwork.reset
    scalarNetwork.config
    ctrlNetwork.config
    vectorNetwork.config
    top.genConnections
    sbs.foreach { _.genConnections }
    pcus.foreach { _.config }
    mcus.foreach { _.config }
    scus.foreach { _.config }
    mcs.foreach { _.config }
    ocus.foreach { _.config }
  }
}

abstract class GridNetwork()(implicit spade:SwitchNetwork) {

  implicit def self:GridNetwork = this

  type P <: PortType
  def io(cu:NetworkElement):GridIO[P, NetworkElement]

  def isVectorNetwork = this.isInstanceOf[VectorNetwork]
  def isScalarNetwork = this.isInstanceOf[ScalarNetwork]
  def isControlNetwork = this.isInstanceOf[CtrlNetwork]

  def cuArray:List[List[ComputeUnit]] = spade.cuArray
  def mcArray:List[List[MemoryController]] = spade.mcArray
  def mcs:List[MemoryController] = spade.mcs
  def scuArray:List[List[ScalarComputeUnit]] = spade.scuArray
  def scus:List[ScalarComputeUnit] = spade.scus
  def ocuArray:List[List[OuterComputeUnit]] = spade.ocuArray
  def sbs:List[List[SwitchBox]] = spade.sbArray

  // switch to switch channel width
  lazy val sbChannelWidth = 4
  lazy val sbChannelWidthWE = sbChannelWidth
  lazy val sbChannelWidthEW = sbChannelWidth
  lazy val sbChannelWidthNS = sbChannelWidth
  lazy val sbChannelWidthSN = sbChannelWidth

  // CU to CU channel width
  lazy val cuChannelWidth = 0
  lazy val cuChannelWidthWE = cuChannelWidth
  lazy val cuChannelWidthEW = cuChannelWidth
  lazy val cuChannelWidthNS = cuChannelWidth
  lazy val cuChannelWidthSN = cuChannelWidth

  // switch to CU channel width
  lazy val sbcuChannelWidth = 1
  lazy val sbcuChannelWidthNW = sbcuChannelWidth
  lazy val sbcuChannelWidthNE = sbcuChannelWidth
  lazy val sbcuChannelWidthSW = sbcuChannelWidth
  lazy val sbcuChannelWidthSE = sbcuChannelWidth

  // CU to Switch channel width
  lazy val cusbChannelWidth = 1
  lazy val cusbChannelWidthNW = cusbChannelWidth
  lazy val cusbChannelWidthNE = cusbChannelWidth
  lazy val cusbChannelWidthSW = cusbChannelWidth
  lazy val cusbChannelWidthSE = cusbChannelWidth

  // SCU to switch channel width
  lazy val scsbChannelWidth = 4
  // switch to SCU channel width
  lazy val sbscChannelWidth = 4

  // MC to switch channel width
  lazy val mcsbChannelWidth = 4
  // switch to MC channel width
  lazy val sbmcChannelWidth = 4

  // MC to SCU channel width
  lazy val mcscChannelWidth = 2
  // SCU to MC channel width
  lazy val scmcChannelWidth = 2

  // OCU to switch channel width
  lazy val ocsbChannelWidth = 2
  // switch to OCU channel width
  lazy val sbocChannelWidth = 4

  // Top to switch channel width
  lazy val tpsbChannelWidth = 1
  // switch to Top channel width
  lazy val sbtpChannelWidth = 1

  lazy val numRows = cuArray.head.length
  lazy val numCols = cuArray.length

  lazy val top = spade.top
  
  def connect(out:NetworkElement, outDir:String, in:NetworkElement, inDir:String, channelWidth:Int) = {
    (out, in) match {
      case (out:Top, in) =>
        val outs = io(out).outs
        outs.foreach { argIn =>
          val ins = io(in).addInAt(inDir, channelWidth)
          ins.foreach { _ <== argIn }
        }
      case (out, in:Top) =>
        val outs = io(out).addOutAt(outDir, channelWidth)
        val ins = io(in).ins
        ins.foreach { _ <== outs }
      case (out, in) =>
        val outs = io(out).addOutAt(outDir, channelWidth)
        val ins = io(in).addInAt(inDir, channelWidth)
        outs.zip(ins).foreach { case (o, i) => o ==> i }
    }
  }

  def reset = {
    spade.pnes.foreach { pne =>
      io(pne).ins.foreach { _.disconnect }
      io(pne).outs.foreach { _.disconnect }
      io(pne).clearIO
    }
  }

  def config = {
    // CU to CU Connection
    for (y <- 0 until numRows) {
      for (x <- 0 until numCols) {
        // CU to CU (Horizontal)
        if (x!=numCols-1) {
          // W -> E
          connect(cuArray(x)(y), "E", cuArray(x+1)(y), "W", cuChannelWidthWE)
          // E -> W
          connect(cuArray(x+1)(y), "W", cuArray(x)(y), "E", cuChannelWidthEW)
        }
        //// CU to CU (Vertical)
        if (y!=numRows-1) {
          // S -> N
          connect(cuArray(x)(y), "N", cuArray(x)(y+1), "S", cuChannelWidthSN)
          // N -> S 
          connect(cuArray(x)(y+1), "S", cuArray(x)(y), "N", cuChannelWidthNS)
        }
      }
    }

    // Switch to Switch connections
    for (y <- 0 until numRows+1) {
      for (x <- 0 until numCols+1) {
        // SB to SB (Horizontal)
        if (x!=numCols) {
          // W -> E 
          connect(sbs(x)(y), "E", sbs(x+1)(y), "W", sbChannelWidthWE)
          // E -> W
          connect(sbs(x+1)(y), "W", sbs(x)(y), "E", sbChannelWidthEW)
        }
        // SB to SB (Vertical)
        if (y!=numRows) {
          // S -> N
          connect(sbs(x)(y), "N", sbs(x)(y+1), "S", sbChannelWidthSN)
          // N -> S 
          connect(sbs(x)(y+1), "S", sbs(x)(y), "N", sbChannelWidthNS)
        }

        // Top to SB
        // Top Switches
        if (y==numRows) {
          connect(sbs(x)(y), "N", top, "S", sbtpChannelWidth) // bottom up 
          connect(top, "S", sbs(x)(y), "N", tpsbChannelWidth) // top down
        }
        // Bottom Switches
        if (y==0) {
          connect(sbs(x)(y), "S", top, "N", sbtpChannelWidth) // top down 
          connect(top, "N", sbs(x)(y), "S", tpsbChannelWidth) // bottom up
        }
      }
    }

    // CU and SB connection
    for (y <- 0 until numRows) {
      for (x <- 0 until numCols) {
        // CU to SB 
        // NW (top left)
        connect(cuArray(x)(y), "NW", sbs(x)(y+1), "SE", cusbChannelWidthNW)
        // NE (top right)
        connect(cuArray(x)(y), "NE", sbs(x+1)(y+1), "SW", cusbChannelWidthNE)
        // SW (bottom left)
        connect(cuArray(x)(y), "SW", sbs(x)(y), "NE", cusbChannelWidthSW)
        // SE (bottom right)
        connect(cuArray(x)(y), "SE", sbs(x+1)(y), "NW", cusbChannelWidthSE)

        // SB to CU
        // NW (top left)
        connect(sbs(x)(y+1), "SE", cuArray(x)(y), "NW", sbcuChannelWidthNW)
        // NE (top right)
        connect(sbs(x+1)(y+1), "SW", cuArray(x)(y), "NE", sbcuChannelWidthNE)
        // SW (bottom left)
        connect(sbs(x)(y), "NE", cuArray(x)(y), "SW", sbcuChannelWidthSW)
        // SE (bottom right)
        connect(sbs(x+1)(y), "NW", cuArray(x)(y), "SE", sbcuChannelWidthSE)
      }
    }

    // OCU and SB connection
    for (y <- 0 until numRows+1) {
      for (x <- 0 until numCols+1) {
        // OCU to SB 
        connect(ocuArray(x)(y), "W", sbs(x)(y), "E", ocsbChannelWidth)

        // SB to OCU
        connect(sbs(x)(y), "E", ocuArray(x)(y), "W", sbocChannelWidth)
      }
    }

    //// SCU and SB connection
    for (y <- 0 until scuArray.headOption.map(_.size).getOrElse(0)) { //cols
      for (x <- 0 until scuArray.size) { //rows
        if (x==0) {
          // SCU to SB (W -> E) (left side)
          connect(scuArray(x)(y), "E", sbs(0)(y), "W", scsbChannelWidth)
          // SB to SCU (E -> W) (left side)
          connect(sbs(0)(y), "W", scuArray(x)(y), "E", sbscChannelWidth)
        } else {
          // SCU to SB (E -> W) (right side)
          connect(scuArray(x)(y), "W", sbs(numCols)(y), "E", scsbChannelWidth)
          // SB to SCU (W -> E) (right side)
          connect(sbs(numCols)(y), "E", scuArray(x)(y), "W", sbscChannelWidth)
        }
      }
    }

    //// MC and SB connection
    for (y <- 0 until mcArray.headOption.map(_.size).getOrElse(0)) { //cols
      for (x <- 0 until mcArray.size) { //rows
        if (x==0) {
          // MC to SB (W -> E) (left side)
          connect(mcArray(x)(y), "E", sbs(0)(y), "W", mcsbChannelWidth)
          // SB to MC (E -> W) (left side)
          connect(sbs(0)(y), "W", mcArray(x)(y), "E", sbmcChannelWidth)
        } else {
          // MC to SB (E -> W) (right side)
          connect(mcArray(x)(y), "W", sbs(numCols)(y), "E", mcsbChannelWidth)
          // SB to MC (W -> E) (right side)
          connect(sbs(numCols)(y), "E", mcArray(x)(y), "W", sbmcChannelWidth)
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

}

class VectorNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = VectorIO.P
  def io(pne:NetworkElement) = pne.vectorIO

  // switch to switch channel width
  //override lazy val sbChannelWidth = 0
  //override lazy val sbChannelWidthWE = 1
  //override lazy val sbChannelWidthEW = 1
  //override lazy val sbChannelWidthNS = 1
  //override lazy val sbChannelWidthSN = 1

  // CU to CU channel width
  //override lazy val cuChannelWidth = 0
  //override lazy val cuChannelWidthWE = 1
  //override lazy val cuChannelWidthEW = 1
  //override lazy val cuChannelWidthNS = 1
  //override lazy val cuChannelWidthSN = 1

  // switch to CU channel width
  //override lazy val sbcuChannelWidth = 0
  //override lazy val sbcuChannelWidthNW = 1
  //override lazy val sbcuChannelWidthNE = 1
  //override lazy val sbcuChannelWidthSW = 1
  //override lazy val sbcuChannelWidthSE = 1

  // CU to Switch channel width
  //override lazy val cusbChannelWidth = 0
  //override lazy val cusbChannelWidthNW = 1
  //override lazy val cusbChannelWidthNE = 1
  //override lazy val cusbChannelWidthSW = 1
  //override lazy val cusbChannelWidthSE = 1
  
  // SCU to switch channel width
  override lazy val scsbChannelWidth = 0
  // switch to SCU channel width
  override lazy val sbscChannelWidth = 0

  // MC to switch channel width
  override lazy val mcsbChannelWidth = 1
  // switch to MC channel width
  override lazy val sbmcChannelWidth = 1
    
  // MC to SCU channel width
  override lazy val mcscChannelWidth = 0
  // SCU to MC channel width
  override lazy val scmcChannelWidth = 0
  
  // OCU to switch channel width
  override lazy val ocsbChannelWidth = 0
  // switch to OCU channel width
  override lazy val sbocChannelWidth = 0
  
  // Top to switch channel width
  override lazy val tpsbChannelWidth = 0
  // switch to Top channel width
  override lazy val sbtpChannelWidth = 0
}

class ScalarNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = ScalarIO.P
  def io(pne:NetworkElement) = pne.scalarIO

  // switch to switch channel width
  //override lazy val sbChannelWidth = 0
  //override lazy val sbChannelWidthWE = 1
  //override lazy val sbChannelWidthEW = 1
  //override lazy val sbChannelWidthNS = 1
  //override lazy val sbChannelWidthSN = 1

  // CU to CU channel width
  //override lazy val cuChannelWidth = 0
  //override lazy val cuChannelWidthWE = 1
  //override lazy val cuChannelWidthEW = 1
  //override lazy val cuChannelWidthNS = 1
  //override lazy val cuChannelWidthSN = 1

  // switch to CU channel width
  //override lazy val sbcuChannelWidth = 0
  //override lazy val sbcuChannelWidthNW = 1
  //override lazy val sbcuChannelWidthNE = 1
  //override lazy val sbcuChannelWidthSW = 1
  //override lazy val sbcuChannelWidthSE = 1

  // CU to Switch channel width
  //override lazy val cusbChannelWidth = 0
  //override lazy val cusbChannelWidthNW = 1
  //override lazy val cusbChannelWidthNE = 1
  //override lazy val cusbChannelWidthSW = 1
  //override lazy val cusbChannelWidthSE = 1
  
  // SCU to switch channel width
  //override lazy val scsbChannelWidth = 4
  // switch to SCU channel width
  //override lazy val sbscChannelWidth = 4

  // MC to switch channel width
  override lazy val mcsbChannelWidth = 0
  // switch to MC channel width
  override lazy val sbmcChannelWidth = 0
    
  // MC to SCU channel width
   override lazy val mcscChannelWidth = 0
  // SCU to MC channel width
   override lazy val scmcChannelWidth = 2
  
  // OCU to switch channel width
  //lazy val ocsbChannelWidth = 2
  // switch to OCU channel width
  //lazy val sbocChannelWidth = 2
  
  // Top to switch channel width
  //lazy val tpsbChannelWidth = 1
  // switch to Top channel width
  //lazy val sbtpChannelWidth = 1
  
  override def config = {
    // Add ins and outs to Top
    io(top).addInAt("S", top.numArgOuts)
    io(top).addOutAt("S", top.numArgIns)
    super.config
  }
}

class CtrlNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = ControlIO.P
  def io(pne:NetworkElement) = pne.ctrlIO

  // switch to switch channel width
  //override lazy val sbChannelWidth = 0
  //override lazy val sbChannelWidthWE = 1
  //override lazy val sbChannelWidthEW = 1
  //override lazy val sbChannelWidthNS = 1
  //override lazy val sbChannelWidthSN = 1

  // CU to CU channel width
  //override lazy val cuChannelWidth = 0
  //override lazy val cuChannelWidthWE = 1
  //override lazy val cuChannelWidthEW = 1
  //override lazy val cuChannelWidthNS = 1
  //override lazy val cuChannelWidthSN = 1

  // switch to CU channel width
  override lazy val sbcuChannelWidth = 2
  //override lazy val sbcuChannelWidthNW = 1
  //override lazy val sbcuChannelWidthNE = 1
  //override lazy val sbcuChannelWidthSW = 1
  //override lazy val sbcuChannelWidthSE = 1

  // CU to Switch channel width
  //override lazy val cusbChannelWidth = 0
  //override lazy val cusbChannelWidthNW = 1
  //override lazy val cusbChannelWidthNE = 1
  //override lazy val cusbChannelWidthSW = 1
  //override lazy val cusbChannelWidthSE = 1
  
  // SCU to switch channel width
  //override lazy val scsbChannelWidth = 4
  // switch to SCU channel width
  //override lazy val sbscChannelWidth = 4

  // MC to switch channel width
  override lazy val mcsbChannelWidth = 1
  // switch to MC channel width
  override lazy val sbmcChannelWidth = 1

  // MC to SCU channel width
  // override lazy val mcscChannelWidth = 4
  // SCU to MC channel width
  // override lazy val scmcChannelWidth = 4
    
  // OCU to switch channel width
  override lazy val ocsbChannelWidth = 3
  // switch to OCU channel width
  override lazy val sbocChannelWidth = 8

  // Top to switch channel width
  //lazy val tpsbChannelWidth = 1
  // switch to Top channel width
  //lazy val sbtpChannelWidth = 1
  override def config = {
    // Add ins and outs to Top
    io(top).addInAt("S", 1) // status 
    io(top).addOutAt("S", 1) // command
    super.config
  }
}

