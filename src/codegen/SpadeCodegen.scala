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

  val traitName = s"PlasticineArch"
  lazy val dir = sys.env("PLASTICINE_HOME") + "/src/main/scala/spade/gen"
  override lazy val stream:OutputStream = newStream(dir, s"$traitName.scala") 
  
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
    emitBlock(s"trait $traitName extends ${(0 until fileNumber).map(i => s"$traitName$i").mkString(" with ")}") {
      emitln(s"self:Plasticine =>")
      emitDec
    }
  }

  def emitSplit = {
    printer = new Printer {
      override lazy val stream:OutputStream = newStream(dir, s"$traitName$fileNumber.scala") 
    }
    emitImport
    printer.emitBSln(s"trait $traitName$fileNumber")
    printer.emitln(s"self:$traitName with Plasticine =>")
    emitAlloc
    emitNetwork
    printer.emitBEln
  }
  
  override def finPass = {
    super.finPass
    close
  }

  def emitRegs(cu:ComputeUnit) = {
    emitln(s"val regColors = ListBuffer[List[RegColor]]()")
    cu.regs.foreach { reg =>
      emitln(s"regColors += List(${reg.colors.mkString(",")})")
    }
  }
  
  def emitStages(cu:ComputeUnit) = {
    //emitln(s"val stageTypes = ListBuffer[StageType]()")
    //cu.stages.foreach { stage =>
      //emitln(s"stageTypes += ${quote(stage)}")
    //}
    cu match {
      case cu:MemoryComputeUnit =>
        emitln(s"val d = ${cu.fustages.size}")
        emitln(s"val wd = ${cu.wastages.size}")
      case cu =>
        emitln(s"val d = ${cu.fustages.size}")
    }
  }

  def emitParam = {
    val pcu = spade.pcus.head
    emitBlock(s"case class MyPCUParams(numScalarIn:Int, numScalarOut:Int, numVectorIn:Int, numVectorOut:Int, numControlIn:Int, numControlOut:Int) extends PCUParams") {
      emitln(s"val w = ${spade.wordWidth}")
      emitln(s"val v = ${spade.numLanes}")
      emitln(s"val numCounters = ${pcu.numCtrs}")
      emitln(s"val numUDCs = ${pcu.numUDCs}")
      emitRegs(pcu)
      emitStages(pcu)
      emitln(s"val r = regColors.size")
    }
    emitln(1)
    val mcu = spade.mcus.head
    emitBlock(s"case class MyPMUParams(numScalarIn:Int, numScalarOut:Int, numVectorIn:Int, numVectorOut:Int, numControlIn:Int, numControlOut:Int) extends PMUParams") {
      emitln(s"val w = ${spade.wordWidth}")
      emitln(s"val v = ${spade.numLanes}")
      emitln(s"val numCounters = ${mcu.numCtrs}")
      emitln(s"val numUDCs = ${mcu.numUDCs}")
      emitRegs(mcu)
      emitStages(mcu)
      emitln(s"val r = regColors.size")
    }
  }

  def emitDec = {
    val cuArray = spade.cuArray
    emitln(s"val cus = Array.fill(${cuArray.size})(Array.ofDim[CU](${cuArray.head.size}))")
    val sbs = spade.sbArray
    emitln(s"val csbs = Array.fill(${sbs.size})(Array.ofDim[ControlSwitch](${sbs.head.size}))")
    emitln(s"val vsbs = Array.fill(${sbs.size})(Array.ofDim[VectorSwitch](${sbs.head.size}))")
    emitln(s"val ssbs = Array.fill(${sbs.size})(Array.ofDim[ScalarSwitch](${sbs.head.size}))")
  }

  def emitAlloc = {
    val cuArray = spade.cuArray
    val sbs = spade.sbArray
    cuArray.foreach { case row =>
      row.foreach { case cu =>
        val temp = cu match {
          case mcu:MemoryComputeUnit => s"new PCU(MyPCUParams(${cu.sins.size}, ${cu.souts.size}, ${cu.vins.size}, ${cu.vouts.size}, ${cu.cins.size}, ${cu.couts.size}))"
          case cu:ComputeUnit => s"new PMU(MyPMUParams(${cu.sins.size}, ${cu.souts.size}, ${cu.vins.size}, ${cu.vouts.size}, ${cu.cins.size}, ${cu.couts.size}))"
        }
        emitln(s"${quote(cu)} = Module($temp)")
      }
    }

    //val scus = spade.scuArray
    //emitln(s"val scus = Array.fill(${cuArray.size})(Array.ofDim[Module](${cuArray.head.size}))")
    //scus.foreach { scu =>
      //emitln(s"${quote(scu)} = Module(new SCU(scuParam)))")
    //}

    //val mcs = spade.mcs
    //emitLambda(s"val mcs = List.tabulate(${2*(numRows+1)})", "i",) {
    //mcs.zipWithIndex.foreach { case (row, i) =>
    //emitln(s"Module())")
    //}
    //}


    sbs.foreach { row =>
      row.foreach { sb =>
        emitln(s"${qv(sb)} = Module(new VectorSwitch(VectorSwitchParams(numIns=${sb.vins.size}, numOuts=${sb.vouts.size}, v=${spade.numLanes}, w=${spade.wordWidth})))")
        emitln(s"${qs(sb)} = Module(new ScalarSwitch(ScalarSwitchParams(numIns=${sb.sins.size}, numOuts=${sb.souts.size}, w=${spade.wordWidth})))")
        emitln(s"${qc(sb)} = Module(new ControlSwitch(ControlSwitchParams(numIns=${sb.cins.size}, numOuts=${sb.couts.size})))")
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
          emitln(s"${qs(out)} <> ${qs(in)}")
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

  def qv(io:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:SwitchBox if io.isIn => s"${qv(n)}.io.ins($i)"
      case n:SwitchBox if io.isOut => s"${qv(n)}.io.outs($i)"
      case n if io.isIn => s"${quote(n)}.io.vecIn($i)"
      case n if io.isOut => s"${quote(n)}.io.vecOut($i)"
    }
  }

  def qs(io:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:Top if io.isIn => s"self.io.argOuts($i)"
      case n:Top if io.isOut => s"self.io.argIns($i)"
      case n:SwitchBox if io.isIn => s"${qs(n)}.io.ins($i)"
      case n:SwitchBox if io.isOut => s"${qs(n)}.io.outs($i)"
      case n if io.isIn => s"${quote(n)}.io.scalarIn($i)" 
      case n if io.isOut => s"${quote(n)}.io.scalarOut($i)" 
    }
  }

  def qc(io:IO[_,_]):String = {
    val n = io.src
    val i = io.index
    n match {
      case n:Top if io.isIn => s"self.io.done"
      case n:Top if io.isOut => s"self.io.enable"
      case n:SwitchBox if io.isIn => s"${qc(n)}.io.ins($i)"
      case n:SwitchBox if io.isOut => s"${qc(n)}.io.outs($i)"
      case n if io.isIn => s"${quote(n)}.io.controlIn($i)" 
      case n if io.isOut => s"${quote(n)}.io.controlOut($i)" 
    }
  }

}
