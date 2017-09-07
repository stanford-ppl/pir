package pir.codegen

import pir.Design
import pir.spade.main._
import pir.spade.graph._
import pir.Config

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class SpadeNetworkCodegen(implicit design: Design) extends Codegen with ScalaCodegen with MultiFileCodegen {
  def shouldRun = Config.codegen
  import spademeta._
  import spade.param._

  val traitName = s"PlasticineArch"
  lazy val dir = sys.env("PLASTICINE_HOME") + "/src/main/scala/arch/gen"
  override lazy val stream:OutputStream = newStream(dir, s"$traitName.scala") 
  
  override implicit lazy val spade = design.arch.asSwitchNetwork

  override def splitPreHeader:Unit = {
    emitHeader
  }

  val arguments = {
    s"(io:PlasticineIO, argOutMuxIns:Array[Array[DecoupledIO[UInt]]], doneOuts:Array[Bool], cus:Array[Array[CU]], scus:Array[Array[ScalarCU]] , mcs:Array[Array[MemoryChannel]], vsbs:Array[Array[VectorSwitch]], ssbs:Array[Array[ScalarSwitch]], csbs:Array[Array[ControlSwitch]], lcus:Array[Array[SwitchCU]])"
  }

  override def splitPostHeader:Unit = {
    //emitln(s"self:$traitName with Plasticine =>")
    emitBSln(s"def connect${fileNumber}$arguments:Unit = ")
  }

  override def splitPreFooter:Unit = {
    emitBEln
  }

  def emitHeader = {
    emitln(s"package plasticine.arch")
    emitln(s"import chisel3._")
    emitln(s"import chisel3.util._")
    emitln(s"import plasticine.templates.MuxN")
    emitln(s"import scala.language.reflectiveCalls")
    emitln(s"import scala.collection.mutable.ListBuffer")
    emitln(1)
  }

  addPass {
    emitHeader
    emitSplit(emitNetwork)
    emitMixed {
      emitln(s"self:$traitName with Plasticine =>")
      emitBlock(s"def connect$arguments:Unit = ") {
        (0 until fileNumber).foreach { i =>
          emitln(s"connect${i+1}(io, argOutMuxIns, doneOuts, cus, scus, mcs, vsbs, ssbs, csbs, lcus)")
        }
      }
    }
  }

  def emitNetwork = {
    emitComment("VectorNetwork Connection")
    spade.prts.foreach { prt =>
      prt.vectorIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"${qv(out)} <> ${qv(in)}")
        }
      }
    }
    emitComment("ScalarNetwork Connection")
    spade.prts.foreach { prt =>
      prt.scalarIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"${qs(out)} <> ${qs(in, from=out)}")
        }
      }
    }
    emitComment("ControlNetwork Connection")
    spade.prts.foreach { prt =>
      prt.ctrlIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"${qc(out)} <> ${qc(in, from=out)}")
        }
      }
    }
  }

  def quote(n:Node):String = n match {
    case n:ScalarComputeUnit => 
      val (x, y) = coordOf(n)
      x match {
        case -1 => s"scus(0)($y)"
        case `numCols` => s"scus(1)($y)"
        case _ => s"cu($x)($y)"
      }
    case n:MemoryController => 
      val (x, y) = coordOf(n)
      x match {
        case -1 => s"mcs(0)($y)"
        case `numCols` => s"mcs(1)($y)"
      }
    case n:OuterComputeUnit =>
      val (x, y) = coordOf(n)
      s"lcus($x)($y)"
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

  def qv(io:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:MemoryController if io.isIn => s"${quote(n)}.io.plasticine.vecIn" 
      case n:MemoryController if io.isOut => s"${quote(n)}.io.plasticine.vecOut" 
      case n:SwitchBox if io.isIn => s"${qv(n)}.io.ins($i)"
      case n:SwitchBox if io.isOut => s"${qv(n)}.io.outs($i)"
      case n:Node if io.isIn => s"${quote(n)}.io.vecIn($i)"
      case n:Node if io.isOut => s"${quote(n)}.io.vecOut($i)"
    }
  }

  def qs(io:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:Top if io.isOut => s"io.argIns($i)"
      case n:SwitchBox if io.isIn => s"${qs(n)}.io.ins($i)"
      case n:SwitchBox if io.isOut => s"${qs(n)}.io.outs($i)"
      case n:MemoryController if io.isIn => s"${quote(n)}.io.plasticine.scalarIn($i)" 
      case n:MemoryController if io.isOut => s"${quote(n)}.io.plasticine.scalarOut($i)" 
      case n:Node if io.isIn => s"${quote(n)}.io.scalarIn($i)" 
      case n:Node if io.isOut => s"${quote(n)}.io.scalarOut($i)" 
    }
  }

  def qs(io:IO[_<:PortType,Module], from:IO[_<:PortType,Module]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:Top if io.isIn => s"argOutMuxIns($i)(${io.indexOf(from)})"
      case n => qs(io)
    }
  }

  def qc(io:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:Top if io.isOut => s"io.enable"
      case n:SwitchBox if io.isIn => s"${qc(n)}.io.ins($i)"
      case n:SwitchBox if io.isOut => s"${qc(n)}.io.outs($i)"
      case n:MemoryController if io.isIn => s"${quote(n)}.io.plasticine.controlIn($i)" 
      case n:MemoryController if io.isOut => s"${quote(n)}.io.plasticine.controlOut($i)" 
      case n:Node if io.isIn => s"${quote(n)}.io.controlIn($i)" 
      case n:Node if io.isOut => s"${quote(n)}.io.controlOut($i)" 
    }
  }

  def qc(io:IO[_<:PortType,Module], from:IO[_<:PortType,Module]):String = {
    val n = io.src
    n match {
      case n:Top if io.isIn => s"doneOuts(${io.indexOf(from)})"
      case n => qc(io)
    }
  }

}

