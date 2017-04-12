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

class ConfigCodegen(implicit design: Design) extends Codegen with ScalaCodegen with MultiFileCodegen {
  def shouldRun = true
  import spademeta._

  val traitName = s"$design".replace(s"$$", "")
  lazy val dir = sys.env("PLASTICINE_HOME") + s"/apps/$design"
  override lazy val stream:OutputStream = newStream(dir, s"$traitName.scala") 
  
  def mapping = design.mapping.get
  def fimap = mapping.fimap
  def clmap = mapping.clmap
  def ctmap = mapping.ctmap
  
  def top = spade.top
  def sbs = spade.sbArray
  def cus = spade.cuArray

  override implicit def spade = design.arch.asInstanceOf[SwitchNetwork]
  lazy val numRows = spade.numRows
  lazy val numCols = spade.numCols

  def emitHeader = {
    emitln(s"package plasticine.arch")
    emitln(s"import chisel3._")
    emitln(s"import chisel3.util._")
    emitln(s"import scala.collection.mutable.ListBuffer")
    emitln(1)
  }

  override def splitPreHeader:Unit = {
    emitHeader
  }

  override def splitPostHeader:Unit = {
  }

  def traverse = {
    emitHeader
    emitSplit {
      emitCrossbarBits
      emitCUBits
    }
    emitMixed(emitPlasticineBits)
  }
  
  def muxIdx(in:Input[_<:PortType,Module]) = {
    fimap.get(in).fold(-1) { out => in.indexOf(out) }
  }
  def muxIdx(out:GlobalOutput[_<:PortType,Module]) = {
    fimap.get(out.ic).fold(-1) { _.src.index }
  }

  def cuParams = s"GeneratedTopParams.cuParams"

  def emitCrossbarBits = {
    sbs.foreach { 
      _.foreach { sb =>
        emitln(s"${qv(sb)} = CrossbarBits(${quote(sb.vouts.map( out => muxIdx(out)))})")
        emitln(s"${qs(sb)} = CrossbarBits(${quote(sb.souts.map( out => muxIdx(out)))})")
        emitln(s"${qc(sb)} = CrossbarBits(${quote(sb.couts.map( out => muxIdx(out)))})")
      }
    }
  }

  def emitCtrBits(pcu:ComputeUnit) = {
    val cu = clmap.pmap(pcu)
    pcu.ctrs.foreach { pctr =>
      val ctrBit = ctmap.pmap.get(pctr) match { 
        case None => s"CounterRCBits.zeros(${spade.wordWidth})" 
        case Some(ctr) => 
          s"CounterRCBits(max=)"
      }
      emitln(s"val ${q(pcu, pctr)} = $ctrBit")
    }
  }

  def emitCChainBis(pcu:ComputeUnit) = {
    val cu = clmap.pmap(pcu)
    val pctrs = pcu.ctrs
    val chain = List.tabulate(pctrs.size-1) { i =>
      if (ctmap.pmap.contains(pctrs(i)) && ctmap.pmap.contains(pctrs(i+1))) {
        val ctr = ctmap.pmap(pctrs(i))
        val ctrp1 = ctmap.pmap(pctrs(i+1)) 
        if (ctrp1.en.from == ctr.done) 1
        else 0
      } else 0
    }
    emitln(s"val ${q(pcu, "cc")} = CounterChainBits(${quote(chain)}, ${quote(pcu.ctrs.map(ctr => q(pcu, ctr)))})")
  }

  def emitCUBits = {
    cus.foreach { 
      _.foreach { pcu =>
        val (x,y) = pcu.coord
        val bitTp = pcu match {
          case cu:MemoryComputeUnit => "MCUBits"
          case cu:ComputeUnit => "PCUBits"
        }
        val cuBit = clmap.pmap.get(pcu) match { 
          case None => s"$bitTp.zeros($cuParams($x)($y))" 
          case Some(cu) => 
            emitCtrBits(pcu)
            emitCChainBis(pcu)
            s"$bitTp(${q(pcu, "cc")}, ${q(pcu, "sx")}, ${q(pcu, "sp")}, ${q(pcu, "st")}, ${q(pcu, "cb")}, ${q(pcu, "si")}, ${0})" //TODO
        }
        emitln(s"${quote(pcu)} = $cuBit")
      }
    }
  }

  def toBinary(x: Any, w: Int): List[Int] = x match {
    //case num: Int => List.tabulate(w) { j => if (BigInt(num).testBit(j)) 1 else 0 }
    case num: Float => toBinary(java.lang.Float.floatToRawIntBits(num), w)
    case l: List[Any] => l.map { e => toBinary(e, w/l.size) }.flatten
    case _ => throw new Exception("Unsupported type for toBinary")
  }

  def emitRegs(cu:ComputeUnit) = {
  }
  
  def emitStages(cu:ComputeUnit) = {
  }

  def emitPlasticineBits = {
    val cuArray = spade.cuArray
    emitln(s"val cus = Array.fill(${cuArray.size})(Array.ofDim[ComputeUnitBits](${cuArray.head.size}))")
    emitln(s"val csbs = Array.fill(${sbs.size})(Array.ofDim[CrossbarBits](${sbs.head.size}))")
    emitln(s"val vsbs = Array.fill(${sbs.size})(Array.ofDim[CrossbarBits](${sbs.head.size}))")
    emitln(s"val ssbs = Array.fill(${sbs.size})(Array.ofDim[CrossbarBits](${sbs.head.size}))")
    implicit val ms = new CollectionStatus(false)
    emitInst(s"val plasticineBits = PlasticineBits") { implicit ms:CollectionStatus =>
      emitComma(s"cu=cus")
      emitComma(s"vectorSwitch=vsbs")
      emitComma(s"scalarSwitch=ssbs")
      emitComma(s"controlSwitch=csbs")
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

  def q(n:ComputeUnit, pm:String):String = {
    val (x, y) = coordOf(n)
    s"${pm}_${x}_${y}"
  }

  def q(n:ComputeUnit, ctr:Counter):String = {
    val (x, y) = coordOf(n)
    s"ctr${ctr.index}_${x}_${y}"
  }
}
