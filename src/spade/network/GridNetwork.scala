package pir.spade.network

import pir.spade.node._
import scala.language.implicitConversions
import pir.spade.main._
import pir.util.enums._
import scala.language.existentials
import pir.util.tables.mutable._

abstract class GridNetwork()(implicit spade:SwitchNetwork) {
  import spade._

  implicit def self:GridNetwork = this

  type P <: PortType
  def io(cu:Routable):GridIO[P, Routable]

  def isVectorNetwork = this.isInstanceOf[VectorNetwork]
  def isScalarNetwork = this.isInstanceOf[ScalarNetwork]
  def isControlNetwork = this.isInstanceOf[CtrlNetwork]

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

  lazy val pcuVins:Int = spade.pcus.headOption.map{ _.param.numVins }.getOrElse(0)
  lazy val pcuVouts:Int = spade.pcus.headOption.map{ _.param.numVouts }.getOrElse(0)
  lazy val pcuSins:Int = spade.pcus.headOption.map{ _.param.numSins }.getOrElse(0)
  lazy val pcuSouts:Int = spade.pcus.headOption.map{ _.param.numSouts }.getOrElse(0)

  lazy val mcuVins:Int = spade.mcus.headOption.map{ _.param.numVins }.getOrElse(0)
  lazy val mcuVouts:Int = spade.mcus.headOption.map{ _.param.numVouts }.getOrElse(0)
  lazy val mcuSins:Int = spade.mcus.headOption.map{ _.param.numSins }.getOrElse(0)
  lazy val mcuSouts:Int = spade.mcus.headOption.map{ _.param.numSouts }.getOrElse(0)

  lazy val ucuSins:Int = spade.dramAGs.flatten.headOption.map{ _.param.numSins }.getOrElse(0)
  lazy val ucuSouts:Int = spade.dramAGs.flatten.headOption.map{ _.param.numSouts }.getOrElse(0)

  def roundUp(num:Double):Int = Math.ceil(num).toInt

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
    
    /** ----- Central Array Connection ----- **/
    for (y <- 0 until numRows) {
      for (x <- 0 until numCols) {
        /* ----- CU to CU Connection ----- */
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


        /* ----- CU to SB Connection ----- */
        // NW (top left)
        connect(cuArray(x)(y), "NW", sbArray(x)(y+1), "SE", "center")
        // NE (top right)
        connect(cuArray(x)(y), "NE", sbArray(x+1)(y+1), "SW", "center")
        // SW (bottom left)
        connect(cuArray(x)(y), "SW", sbArray(x)(y), "NE", "center")
        // SE (bottom right)
        connect(cuArray(x)(y), "SE", sbArray(x+1)(y), "NW", "center")

        // SB to CU
        // NW (top left)
        connect(sbArray(x)(y+1), "SE", cuArray(x)(y), "NW", "center")
        // NE (top right)
        connect(sbArray(x+1)(y+1), "SW", cuArray(x)(y), "NE", "center")
        // SW (bottom left)
        connect(sbArray(x)(y), "NE", cuArray(x)(y), "SW", "center")
        // SE (bottom right)
        connect(sbArray(x+1)(y), "NW", cuArray(x)(y), "SE", "center")
      }
    }

    for (y <- 0 until numRows+1) {
      for (x <- 0 until numCols+1) {

        /* ---- SB to SB connections ----*/
        // SB to SB (Horizontal)
        if (x!=numCols) {
          // W -> E 
          connect(sbArray(x)(y), "E", sbArray(x+1)(y), "W", "center")
          // E -> W
          connect(sbArray(x+1)(y), "W", sbArray(x)(y), "E", "center")
        }
        // SB to SB (Vertical)
        if (y!=numRows) {
          // S -> N
          connect(sbArray(x)(y), "N", sbArray(x)(y+1), "S", "center")
          // N -> S 
          connect(sbArray(x)(y+1), "S", sbArray(x)(y), "N", "center")
        }

        // Top to SB
        // Top Switches
        if (y==numRows) {
          // S -> N
          connect(sbArray(x)(y), "N", top, "S", "top") // bottom up 
          // N -> S
          connect(top, "S", sbArray(x)(y), "N", "top") // top down
        }
        // Bottom Switches
        if (y==0) {
          // N -> S
          connect(sbArray(x)(y), "S", top, "N", "bottom") // top down 
          // S -> N
          connect(top, "N", sbArray(x)(y), "S", "bottom") // bottom up
        }


        /* ---- OCU and SB connection ----*/
        // OCU to SB 
        connect(ocuArray(x)(y), "W", sbArray(x)(y), "E", "center")

        // SB to OCU
        connect(sbArray(x)(y), "E", ocuArray(x)(y), "W", "center")
      }
    }

    /** ----- Fringe Connection ----- **/
    for (y <- 0 until mcArray.headOption.map(_.size).getOrElse(0)) { //cols
      for (x <- 0 until mcArray.size) { //rows

        /* ---- DramAddrGen and SwitchBox connection ---- */
        if (x==0) {
          // DAG to SB (W -> E) (left side)
          connect(dramAGs(x)(y), "E", sbArray(0)(y), "W", "left")
          // SB to DAG (E -> W) (left side)
          connect(sbArray(0)(y), "W", dramAGs(x)(y), "E", "left")
        } else {
          // DAG to SB (E -> W) (right side)
          connect(dramAGs(x)(y), "W", sbArray(numCols)(y), "E", "right")
          // SB to DAG (W -> E) (right side)
          connect(sbArray(numCols)(y), "E", dramAGs(x)(y), "W", "right")
        }

        /* ---- SramAddrGen and SwitchBox connection ---- */
        if (x==0) {
          // SAG to SB (W -> E) (left side)
          connect(sramAGs(x)(y), "E", sbArray(0)(y), "W", "left")
          // SB to SAG (E -> W) (left side)
          connect(sbArray(0)(y), "W", sramAGs(x)(y), "E", "left")
        } else {
          // SAG to SB (E -> W) (right side)
          connect(sramAGs(x)(y), "W", sbArray(numCols)(y), "E", "right")
          // SB to SAG (W -> E) (right side)
          connect(sbArray(numCols)(y), "E", sramAGs(x)(y), "W", "right")
        }

        /* ---- MC and SwitchBox connection ---- */
        if (x==0) {
          // MC to SB (W -> E) (left side)
          connect(mcArray(x)(y), "E", sbArray(0)(y), "W", "left")
          // SB to MC (E -> W) (left side)
          connect(sbArray(0)(y), "W", mcArray(x)(y), "E", "left")
        } else {
          // MC to SB (E -> W) (right side)
          connect(mcArray(x)(y), "W", sbArray(numCols)(y), "E", "right")
          // SB to MC (W -> E) (right side)
          connect(sbArray(numCols)(y), "E", mcArray(x)(y), "W", "right")
        }

        /* ---- MC and DramAddrGen connection ---- */
        val pos = if (x==0) "left" else "right"
        // MC to DAG (S -> N)
        connect(mcArray(x)(y), "N", dramAGs(x)(y), "S", pos)
        // DAG to MC (N -> S)
        connect(dramAGs(x)(y), "S", mcArray(x)(y), "N", pos)
      }
    }

    // Mark inputs and outputs order
    prts.foreach { prt =>
      io(prt).ins.zipWithIndex.foreach { case (in, idx) => in.index(idx) }
      io(prt).outs.zipWithIndex.foreach { case (out, idx) => out.index(idx) }
    }
  }

}
