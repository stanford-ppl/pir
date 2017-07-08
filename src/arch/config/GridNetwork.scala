package pir.plasticine.config

import pir.plasticine.graph._
import scala.language.implicitConversions
import pir.plasticine.main._
import pir.util.enums._
import scala.language.existentials
import pir.util.tables.mutable._

abstract class GridNetwork()(implicit spade:SwitchNetwork) {

  implicit def self:GridNetwork = this

  type P <: PortType
  def io(cu:Routable):GridIO[P, Routable]

  def isVectorNetwork = this.isInstanceOf[VectorNetwork]
  def isScalarNetwork = this.isInstanceOf[ScalarNetwork]
  def isControlNetwork = this.isInstanceOf[CtrlNetwork]

  def cuArray:List[List[Controller]] = spade.cuArray
  def mcArray:List[List[MemoryController]] = spade.mcArray
  def mcs:List[MemoryController] = spade.mcs
  def scuArray:List[List[ScalarComputeUnit]] = spade.scuArray
  def scus:List[ScalarComputeUnit] = spade.scus
  def ocuArray:List[List[OuterComputeUnit]] = spade.ocuArray
  def sbs:List[List[SwitchBox]] = spade.sbArray

  val channelWidth = Table[String, String, Int](
    values=Map(
      "pos"->List("left", "right","center","top","bottom"), 
      "src"->List("Top", "pcu", "ocu", "mcu", "mu", "scu", "mc", "sb"), 
      "dst"->List("Top", "pcu", "ocu", "mcu", "mu", "scu", "mc", "sb"), 
      "srcDir"->GridIO.eightDirections, 
      "dstDir"->GridIO.eightDirections
    ), 
    default=0
  )

  lazy val numRows = cuArray.head.length
  lazy val numCols = cuArray.length

  lazy val top = spade.top
  
  def connect(out:Routable, outDir:String, in:Routable, inDir:String, pos:String) = {
    val cw = channelWidth("pos"->pos, "src"->out.typeStr, "dst"->in.typeStr, "srcDir"->inDir, "dstDir"->outDir)
    (out, in) match {
      case (out:Top, in) =>
        val outs = io(out).outs
        outs.foreach { argIn =>
          val ins = io(in).addInAt(inDir, cw)
          ins.foreach { _ <== argIn }
        }
      case (out, in:Top) =>
        val outs = io(out).addOutAt(outDir, cw)
        val ins = io(in).ins
        ins.foreach { _ <== outs }
      case (out, in) =>
        val outs = io(out).addOutAt(outDir, cw)
        val ins = io(in).addInAt(inDir, cw)
        outs.zip(ins).foreach { case (o, i) => o ==> i }
    }
  }

  def reset = {
    spade.prts.foreach { prt =>
      io(prt).ins.foreach { _.disconnect }
      io(prt).outs.foreach { _.disconnect }
      io(prt).clearIO
    }
  }

  def config = {
    // CU to CU Connection
    for (y <- 0 until numRows) {
      for (x <- 0 until numCols) {
        // CU to CU (Horizontal)
        if (x!=numCols-1) {
          // W -> E
          connect(cuArray(x)(y), "E", cuArray(x+1)(y), "W", "center")
          // E -> W
          connect(cuArray(x+1)(y), "W", cuArray(x)(y), "E", "center")
        }
        //// CU to CU (Vertical)
        if (y!=numRows-1) {
          // S -> N
          connect(cuArray(x)(y), "N", cuArray(x)(y+1), "S", "center")
          // N -> S 
          connect(cuArray(x)(y+1), "S", cuArray(x)(y), "N", "center")
        }
      }
    }

    // Switch to Switch connections
    for (y <- 0 until numRows+1) {
      for (x <- 0 until numCols+1) {
        // SB to SB (Horizontal)
        if (x!=numCols) {
          // W -> E 
          connect(sbs(x)(y), "E", sbs(x+1)(y), "W", "center")
          // E -> W
          connect(sbs(x+1)(y), "W", sbs(x)(y), "E", "center")
        }
        // SB to SB (Vertical)
        if (y!=numRows) {
          //// S -> N
          connect(sbs(x)(y), "N", sbs(x)(y+1), "S", "center")
          //// N -> S 
          connect(sbs(x)(y+1), "S", sbs(x)(y), "N", "center")
          //TODO
          //HACK: double the bandwidth on two side
          //if (x==0 | x==numCols) {
            //// S -> N
            //connect(sbs(x)(y), "N", sbs(x)(y+1), "S", 2*sbChannelWidthSN)
            //// N -> S 
            //connect(sbs(x)(y+1), "S", sbs(x)(y), "N", 2*sbChannelWidthNS)
          //} else {
            //// S -> N
            //connect(sbs(x)(y), "N", sbs(x)(y+1), "S", sbChannelWidthSN)
            //// N -> S 
            //connect(sbs(x)(y+1), "S", sbs(x)(y), "N", sbChannelWidthNS)
          //}
        }

        // Top to SB
        // Top Switches
        if (y==numRows) {
          // S -> N
          connect(sbs(x)(y), "N", top, "S", "top") // bottom up 
          // N -> S
          connect(top, "S", sbs(x)(y), "N", "top") // top down
        }
        // Bottom Switches
        if (y==0) {
          // N -> S
          connect(sbs(x)(y), "S", top, "N", "bottom") // top down 
          // S -> N
          connect(top, "N", sbs(x)(y), "S", "bottom") // bottom up
        }
      }
    }

    // CU and SB connection
    for (y <- 0 until numRows) {
      for (x <- 0 until numCols) {
        // CU to SB 
        // NW (top left)
        connect(cuArray(x)(y), "NW", sbs(x)(y+1), "SE", "center")
        // NE (top right)
        connect(cuArray(x)(y), "NE", sbs(x+1)(y+1), "SW", "center")
        // SW (bottom left)
        connect(cuArray(x)(y), "SW", sbs(x)(y), "NE", "center")
        // SE (bottom right)
        connect(cuArray(x)(y), "SE", sbs(x+1)(y), "NW", "center")

        // SB to CU
        // NW (top left)
        connect(sbs(x)(y+1), "SE", cuArray(x)(y), "NW", "center")
        // NE (top right)
        connect(sbs(x+1)(y+1), "SW", cuArray(x)(y), "NE", "center")
        // SW (bottom left)
        connect(sbs(x)(y), "NE", cuArray(x)(y), "SW", "center")
        // SE (bottom right)
        connect(sbs(x+1)(y), "NW", cuArray(x)(y), "SE", "center")
      }
    }

    // OCU and SB connection
    for (y <- 0 until numRows+1) {
      for (x <- 0 until numCols+1) {
        // OCU to SB 
        connect(ocuArray(x)(y), "W", sbs(x)(y), "E", "center")

        // SB to OCU
        connect(sbs(x)(y), "E", ocuArray(x)(y), "W", "center")
      }
    }

    //// SCU and SB connection
    for (y <- 0 until scuArray.headOption.map(_.size).getOrElse(0)) { //cols
      for (x <- 0 until scuArray.size) { //rows
        if (x==0) {
          // SCU to SB (W -> E) (left side)
          connect(scuArray(x)(y), "E", sbs(0)(y), "W", "left")
          // SB to SCU (E -> W) (left side)
          connect(sbs(0)(y), "W", scuArray(x)(y), "E", "left")
        } else {
          // SCU to SB (E -> W) (right side)
          connect(scuArray(x)(y), "W", sbs(numCols)(y), "E", "right")
          // SB to SCU (W -> E) (right side)
          connect(sbs(numCols)(y), "E", scuArray(x)(y), "W", "right")
        }
      }
    }

    //// MC and SB connection
    for (y <- 0 until mcArray.headOption.map(_.size).getOrElse(0)) { //cols
      for (x <- 0 until mcArray.size) { //rows
        if (x==0) {
          // MC to SB (W -> E) (left side)
          connect(mcArray(x)(y), "E", sbs(0)(y), "W", "left")
          // SB to MC (E -> W) (left side)
          connect(sbs(0)(y), "W", mcArray(x)(y), "E", "left")
        } else {
          // MC to SB (E -> W) (right side)
          connect(mcArray(x)(y), "W", sbs(numCols)(y), "E", "right")
          // SB to MC (W -> E) (right side)
          connect(sbs(numCols)(y), "E", mcArray(x)(y), "W", "right")
        }
      }
    }

    //// MC and SCU connection
    for (i <- 0 until mcs.size) {
      val pos = if (i==0) "left" else "right"
      // MC to SCU (S -> N)
      connect(mcs(i), "N", scus(i), "S", pos)
      // SCU to MC (N -> S)
      connect(scus(i), "S", mcs(i), "N", pos)
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
