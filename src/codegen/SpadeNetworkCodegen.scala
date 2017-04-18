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

class SpadeNetworkCodegen(implicit design: Design) extends Codegen with ScalaCodegen with MultiFileCodegen {
  def shouldRun = true
  import spademeta._

  val traitName = s"PlasticineArch"
  lazy val dir = sys.env("PLASTICINE_HOME") + "/src/main/scala/arch/gen"
  override lazy val stream:OutputStream = newStream(dir, s"$traitName.scala") 
  
  override implicit lazy val spade = design.arch.asSwitchNetwork
  lazy val numRows = spade.numRows
  lazy val numCols = spade.numCols

  override def splitPreHeader:Unit = {
    emitHeader
  }

  val arguments = {
    s"(io:PlasticineIO, argOutMuxes:List[MuxN], cus:Array[Array[CU]], vsbs:Array[Array[VectorSwitch]], ssbs:Array[Array[ScalarSwitch]], csbs:Array[Array[ControlSwitch]], lcus:Array[Array[PMU]])"
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
    emitln(s"import scala.collection.mutable.ListBuffer")
    emitln(s"import plasticine.templates.MuxN")
    emitln(1)
  }

  def traverse = {
    emitHeader
    emitSplit(emitNetwork)
    emitMixed {
      emitln(s"self:$traitName with Plasticine =>")
      emitBlock(s"def connect$arguments:Unit = ") {
        (0 until fileNumber).foreach { i =>
          emitln(s"connect${i+1}(io, argOutMuxes, cus, vsbs, ssbs, csbs, lcus)")
        }
      }
    }
  }

  def emitNetwork = {
    emitComment("VectorNetwork Connection")
    spade.pnes.foreach { pne =>
      pne.vectorIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"${qv(out)} <> ${qv(in)}")
        }
      }
    }
    emitComment("ScalarNetwork Connection")
    spade.pnes.foreach { pne =>
      pne.scalarIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"${qs(out)} <> ${qs(in, from=out)}")
        }
      }
    }
    emitComment("ControlNetwork Connection")
    spade.pnes.foreach { pne =>
      pne.ctrlIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"${qc(out)} <> ${qc(in)}")
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
      }
    case n:MemoryController => 
      val (x, y) = coordOf(n)
      x match {
        case -1 => s"mc(0)($y)"
        case `numCols` => s"scus(1)($y)"
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
      case n:Node if io.isIn => s"${quote(n)}.io.scalarIn($i)" 
      case n:Node if io.isOut => s"${quote(n)}.io.scalarOut($i)" 
    }
  }

  def qs(io:IO[_,_], from:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:Top if io.isIn => s"argOutMuxes($i).io.ins(${io.indexOf(from)})"
      case n => qs(io)
    }
  }

  def qc(io:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:Top if io.isIn => s"io.done"
      case n:Top if io.isOut => s"io.enable"
      case n:SwitchBox if io.isIn => s"${qc(n)}.io.ins($i)"
      case n:SwitchBox if io.isOut => s"${qc(n)}.io.outs($i)"
      case n:Node if io.isIn => s"${quote(n)}.io.controlIn($i)" 
      case n:Node if io.isOut => s"${quote(n)}.io.controlOut($i)" 
    }
  }

}

