package pir.plasticine.config

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.main._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._
import scala.language.existentials

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
          //// S -> N
          //connect(sbs(x)(y), "N", sbs(x)(y+1), "S", sbChannelWidthSN)
          //// N -> S 
          //connect(sbs(x)(y+1), "S", sbs(x)(y), "N", sbChannelWidthNS)
          //TODO
          //HACK: double the bandwidth on two side
          if (x==0 | x==numCols) {
            // S -> N
            connect(sbs(x)(y), "N", sbs(x)(y+1), "S", 2*sbChannelWidthSN)
            // N -> S 
            connect(sbs(x)(y+1), "S", sbs(x)(y), "N", 2*sbChannelWidthNS)
          } else {
            // S -> N
            connect(sbs(x)(y), "N", sbs(x)(y+1), "S", sbChannelWidthSN)
            // N -> S 
            connect(sbs(x)(y+1), "S", sbs(x)(y), "N", sbChannelWidthNS)
          }
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
