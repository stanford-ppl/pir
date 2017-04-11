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

class SpadeCodegen(implicit design: Design) extends Codegen {
  def shouldRun = true
  import spademeta._

  lazy val dir = sys.env("PLASTICINE_HOME") + "/arch"

  override lazy val stream:OutputStream = newStream(dir, s"Plasticine.scala") 
  
  override implicit def spade = design.arch.asInstanceOf[SwitchNetwork]
  def numRows = spade.numRows
  def numCols = spade.numCols

  override def initPass = {
    super.initPass
    emitHeader
  }

  def emitHeader = {
    emitln(s"package plasticine.arch")
    //emitln(s"import Chisel._")
    emitln(s"import plasticine.templete.dummy._")
    emitln(s"import scala.collection.mutable.ListBuffer")
    emitln(3)
  }

  def emitAlloc = {
    emitBlock(s"val archParam = new ArchParam") {
      emitln(s"val wordWidth = ${spade.wordWidth}")
      emitln(s"val numLanes = ${spade.numLanes}")
      emitln(s"val numRows = ${spade.numRows}")
      emitln(s"val numCols = ${spade.numCols}")
      emitComment(s"val numMCs = TODO")
    }

    val pcu = spade.pcus.head
    emitBlock(s"val pcuParam = new PCUParam") {
      emitln(s"val numCtrs = ${pcu.numCtrs}")
      emitln(s"val regColors = ListBuffer[List[RegColor]]()")
      pcu.regs.foreach { reg =>
        emitln(s"regColors += List(${reg.colors.mkString(",")})")
      }
      emitln(s"val numUDCs = ${pcu.numUDCs}")
      emitln(s"val stageTypes = ListBuffer[StageType]()")
      pcu.stages.foreach { stage =>
        emitln(s"stageTypes += ${quote(stage)}")
      }
    }

    val mcu = spade.mcus.head
    emitBlock(s"val pmuParam = new PMUParam") {
      emitln(s"val numCtrs = ${mcu.numCtrs}")
      emitln(s"val numSRAMs = ${mcu.numSRAMs}")
      emitln(s"val regColors = ListBuffer[List[RegColor]]()")
      mcu.regs.foreach { reg =>
        emitln(s"regColors += List(${reg.colors.mkString(",")})")
      }
      emitln(s"val numUDCs = ${mcu.numUDCs}")
      emitln(s"val stageTypes = ListBuffer[StageType]()")
      mcu.stages.foreach { stage =>
        emitln(s"stageTypes += ${quote(stage)}")
      }
    }

    val cuArray = spade.cuArray
    emitln(s"val cus = Array.fill(${cuArray.size})(Array.ofDim[Module[CU]](${cuArray.head.size}))")
    cuArray.foreach { case row =>
      row.foreach { case cu =>
        val temp = cu match {
          case mcu:MemoryComputeUnit => s"new PCU(pcuParam)"
          case cu:ComputeUnit => s"new PMU(pmuParam)"
        }
        emitln(s"${quote(cu)} = Module($temp))")
      }
    }

    val scus = spade.scuArray
    emitln(s"val scus = Array.fill(${cuArray.size})(Array.ofDim[Module[CU]](${cuArray.head.size}))")
    scus.foreach { scu =>
      emitln(s"${quote(scu)} = Module(new SCU(scuParam)))")
    }

    val mcs = spade.mcs
    //emitLambda(s"val mcs = List.tabulate(${2*(numRows+1)})", "i",) {
    //mcs.zipWithIndex.foreach { case (row, i) =>
    //emitln(s"Module())")
    //}
    //}

    val sbs = spade.sbArray
    emitln(s"val csbs = Array.fill(${sbs.size})(Array.ofDim[Module[CU]](${sbs.head.size}))")
    emitln(s"val vsbs = Array.fill(${sbs.size})(Array.ofDim[Module[CU]](${sbs.head.size}))")
    emitln(s"val ssbs = Array.fill(${sbs.size})(Array.ofDim[Module[CU]](${sbs.head.size}))")

    sbs.foreach { row =>
      row.foreach { sb =>
        emitln(s"${qv(sb)} = Module(new VectorSwitch(switchParam)))")
        emitln(s"${qs(sb)} = Module(new ScalarSwitch(switchParam)))")
        emitln(s"${qc(sb)} = Module(new ControlSwitch(switchParam)))")
      }
    }
  }

  def emitNetwork = {
    emitln(s"val ${spade.top} = this.top")

    emitComment("VectorNetwork Connection")
    spade.pnes.foreach { pne =>
      pne.vectorIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"connect(${qv(pne)}, ${qv(in.src)}, ${out.index}, ${in.index})")
        }
      }
    }
    emitComment("ScalarNetwork Connection")
    spade.pnes.foreach { pne =>
      pne.scalarIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"connect(${qs(pne)}, ${qs(in.src)}, ${out.index}, ${in.index})")
        }
      }
    }
    emitComment("ControlNetwork Connection")
    spade.pnes.foreach { pne =>
      pne.ctrlIO.outs.foreach { out =>
        out.fanOuts.foreach { in =>
          emitln(s"connect(${qc(pne)}, ${qc(in.src)}, ${out.index}, ${in.index})")
        }
      }
    }
  }

  def traverse = {
    emitBlock(s"trait SwitchNetwork") {
      emitln(s"self:Plasticine =>")
      emitAlloc
      emitNetwork
    }
  }
  
  override def finPass = {
    super.finPass
    close
  }

  def emitLambda(s:String, ins:Any*)(block: =>Any) = { 
    emitBS(s"$s ")
    val input = if (ins.size==1) { s"${ins.head}" } else { s"case (${ins.mkString(",")})" }
    emitln(s"${input} =>")
    block
    emitBEln
  }

  def emitComment(s:Any) = {
    emitln(s"// $s")
  }

  def quote(n:Iterable[_]):String = s"List(${n.mkString(",")})"

  override def quote(n:Any):String = n match {
    case n:EmptyStage => s"EmptyStage"
    case n:WAStage => s"WAStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    case n:RAStage => s"RAStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    case n:FUStage => s"FUStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    case n:ScalarComputeUnit => 
      val (x, y) = coordOf(n)
      if (x==-1)
      s"sbs()"
    case n:MemoryComputeUnit =>
      val (x, y) = coordOf(n)
      s"cus($x)($y)"
    case n:ComputeUnit =>
      val (x, y) = coordOf(n)
      s"cus($x)($y)"
    case n:NetworkElement => s"$n"
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
