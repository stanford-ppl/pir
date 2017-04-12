package pir.codegen

import pir.Design
import pir.plasticine.main._
import pir.plasticine.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class ConfigCodegen(implicit design: Design) extends Codegen with ScalaCodegen {
  def shouldRun = true
  import spademeta._

  val traitName = s"$design".replace(s"$$", "")
  lazy val dir = sys.env("PLASTICINE_HOME") + s"/apps/$design"
  override lazy val stream:OutputStream = newStream(dir, s"$traitName.scala") 
  
  def mapping = design.mapping.get
  def fimap = mapping.fimap
  def clmap = mapping.clmap
  
  def top = spade.top
  def sbs = spade.sbArray
  def cus = spade.cuArray

  override implicit def spade = design.arch.asInstanceOf[SwitchNetwork]
  lazy val numRows = spade.numRows
  lazy val numCols = spade.numCols

  override def initPass = {
    super.initPass
    emitHeader
  }

  var lineNumber:Int = 0
  var fileNumber:Int = 0
  var printer:Printer = this

  override def emitln(s:String):Unit = { 
    if (lineNumber > 100) {
      printer.emitBEln
      printer.close
      printer = new Printer {
        override lazy val stream:OutputStream = newStream(dir, s"$traitName$fileNumber.scala") 
      }
      fileNumber += 1
      lineNumber = 0
      emitImport
      printer.emitBSln(s"trait $traitName$fileNumber")
      printer.emitln(s"self:$traitName with Plasticine =>")
    }
    if (printer == this)
      super.emitln(s)
    else
      printer.emitln(s); lineNumber += 1
  }

  def emitImport = {
    printer.emitln(s"package plasticine.arch")
    printer.emitln(s"import chisel3._")
    printer.emitln(s"import chisel3.util._")
    printer.emitln(s"import scala.collection.mutable.ListBuffer")
    printer.emitln(1)
  }

  def emitHeader = {
    emitImport
    emitParam
    emitln(1)
  }

  def traverse = {
    emitSplit
    printer.close
    printer = this
    lineNumber = 0
    emitBlock(s"object $traitName extends ${(0 until fileNumber).map(i => s"$traitName$i").mkString(" with ")}") {
      emitDec
    }
  }
  
  override def finPass = {
    super.finPass
    close
  }

  def emitSplit = {
    printer = new Printer {
      override lazy val stream:OutputStream = newStream(dir, s"$traitName$fileNumber.scala") 
    }
    emitImport
    printer.emitBSln(s"trait $traitName$fileNumber")
    printer.emitln(s"self:$traitName with Plasticine =>")
    emitConfig
    printer.emitBEln
  }

  def muxIdx(in:Input[_<:PortType,Module]) = {
    fimap.get(in).fold(-1) { out => in.indexOf(out) }
  }
  def muxIdx(out:GlobalOutput[_<:PortType,Module]) = {
    fimap.get(out.ic).fold(-1) { _.src.index }
  }

  def emitConfig = {
    sbs.foreach { 
      _.foreach { sb =>
        emitln(s"${qv(sb)} = CrossbarBits(${quote(sb.vouts.map( out => muxIdx(out)))})")
        emitln(s"${qs(sb)} = CrossbarBits(${quote(sb.souts.map( out => muxIdx(out)))})")
        emitln(s"${qc(sb)} = CrossbarBits(${quote(sb.couts.map( out => muxIdx(out)))})")
      }
    }
    cus.foreach { 
      _.foreach { cu =>
        clmap.pmap.get(cu) match { 
          case None => emitln(s"PCUBits.zeros()")
          case Some(cu) =>
        }
      }
    }
  }

  def emitRegs(cu:ComputeUnit) = {
  }
  
  def emitStages(cu:ComputeUnit) = {
  }

  def emitParam = {
    val pcu = spade.pcus.head
    emitln(1)
    val mcu = spade.mcus.head
  }

  def emitDec = {
    val cuArray = spade.cuArray
    emitln(s"val cus = Array.fill(${cuArray.size})(Array.ofDim[ComputeUnitBits](${cuArray.head.size}))")
    emitln(s"val csbs = Array.fill(${sbs.size})(Array.ofDim[CrossbarBits](${sbs.head.size}))")
    emitln(s"val vsbs = Array.fill(${sbs.size})(Array.ofDim[CrossbarBits](${sbs.head.size}))")
    emitln(s"val ssbs = Array.fill(${sbs.size})(Array.ofDim[CrossbarBits](${sbs.head.size}))")
    implicit val ms = new CollectionStatus(false)
    emitInst(s"val plasticineBits = PlasticineBits") { implicit ms:CollectionStatus =>
      emitComma(s"cu=cus.toList")
      emitComma(s"vectorSwitch=vsbs.toList")
      emitComma(s"scalarSwitch=ssbs.toList")
      emitComma(s"controlSwitch=csbs.toList")
      emitComma(s"argOutMuxSelect=${quote(top.sins.map { in => muxIdx(in) })}")
    }
  }

  def quote(n:Node):String = n match {
    case n:EmptyStage => s"EmptyStage"
    case n:WAStage => s"WAStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    case n:RAStage => s"RAStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    case n:FUStage => s"FUStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    case n:ScalarComputeUnit => 
      val (x, y) = coordOf(n)
      x match {
        case -1 => s"scus(0)($y)"
        case `numCols` => s"scus(1)($y)"
      }
    case n:MemoryComputeUnit =>
      val (x, y) = coordOf(n)
      s"cus($x)($y)"
    case n:ComputeUnit =>
      val (x, y) = coordOf(n)
      s"cus($x)($y)"
  }

  def qv(n:Any):String = n match {
    case n:SwitchBox => 
      val (x, y) = coordOf(n)
      s"vsbs($x)($y)"
    case n => quote(n)
  }

  def qs(n:Any):String = n match {
    case n:SwitchBox => 
      val (x, y) = coordOf(n)
      s"ssbs($x)($y)"
    case n => quote(n)
  }

  def qc(n:Any):String = n match {
    case n:SwitchBox => 
      val (x, y) = coordOf(n)
      s"csbs($x)($y)"
    case n => quote(n)
  }

}
