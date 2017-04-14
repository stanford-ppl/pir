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

class SpadeParamCodegen(implicit design: Design) extends Codegen with ScalaCodegen with MultiFileCodegen {
  def shouldRun = true
  import spademeta._

  val traitName = s"GeneratedParams"
  lazy val dir = sys.env("PLASTICINE_HOME") + "/src/main/scala/spade/gen"
  override lazy val stream:OutputStream = newStream(dir, s"$traitName.scala") 
  
  override implicit def spade = design.arch.asInstanceOf[SwitchNetwork]
  lazy val numRows = spade.numRows
  lazy val numCols = spade.numCols

  lazy val cus = spade.cuArray
  lazy val sbs = spade.sbArray
  lazy val ocus = spade.ocuArray

  override def splitPreHeader:Unit = {
    emitHeader
  }

  override def splitPostHeader:Unit = {
    emitln(s"self:TopParams =>")
    emitln(s"import plasticineParams._")
    val fn = if (isSplitting) s"${fileNumber}" else s""
    emitBSln(s"def genParams$fn:Unit = ")
  }

  override def splitPreFooter:Unit = {
    emitBEln
  }

  def emitHeader = {
    emitln(s"package plasticine.spade")
    emitln(s"import chisel3._")
    emitln(s"import chisel3.util._")
    emitln(s"import scala.collection.mutable.ListBuffer")
    emitln(1)
  }

  def traverse = {
    emitHeader
    emitSplit(emitParams)
    emitTopParams
    emitParamClass
    emitMixed {
      splitPostHeader
      (0 until fileNumber).foreach { i =>
        emitln(s"genParams${i+1}")
      }
      splitPreFooter
    }
  }

  def emitTopParams = {
    emitBlock(s"object GeneratedTopParams extends TopParams") {
      emitFringeParam
      emitPlasticineParams
    }
  }

  def emitFringeParam = {
    emitBlock(s"override lazy val fringeParams = new FringeParams") {
      emitln(s"override lazy val numArgIns = ${spade.top.numArgIns}")
      emitln(s"override lazy val numArgOuts = ${spade.top.numArgOuts}")
      emitln(s"override lazy val dataWidth = ${spade.wordWidth}")
    }
  }
  
  def emitPlasticineParams = {
    emitBlock(s"override lazy val plasticineParams = new PlasticineParams") {
      emitln(s"override lazy val w = ${spade.wordWidth}")
      emitln(s"override lazy val numRows = ${numRows}")
      emitln(s"override lazy val numCols = ${numCols}")
      emitln(s"override lazy val cuParams = Array.fill(${cus.size})(Array.ofDim[CUParams](${cus.head.size}))")
      emitln(s"override lazy val vectorSwitchParams = Array.fill(${sbs.size})(Array.ofDim[VectorSwitchParams](${sbs.head.size}))")
      emitln(s"override lazy val scalarSwitchParams = Array.fill(${sbs.size})(Array.ofDim[ScalarSwitchParams](${sbs.head.size}))")
      emitln(s"override lazy val controlSwitchParams = Array.fill(${sbs.size})(Array.ofDim[ControlSwitchParams](${sbs.head.size}))")
      emitln(s"override lazy val switchCUParams = Array.fill(${sbs.size})(Array.ofDim[PMUParams](${sbs.head.size}))")
      emitln(s"override lazy val numArgOutSelections = ${quote(spade.top.sins.map(_.fanIns.size))}")
    }
  }

  def emitRegs(cu:ComputeUnit) = {
    //emitln(s"val regColors = ListBuffer[List[RegColor]]()")
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
        emitln(s"override val d = ${cu.fustages.size}")
        emitln(s"override val wd = ${cu.wastages.size}")
      case cu =>
        emitln(s"override val d = ${cu.fustages.size}")
    }
  }

  def emitParamClass = {
    val pcu = spade.pcus.head
    emitBlock(s"case class GeneratedPCUParams(override val numScalarIn:Int, override val numScalarOut:Int, override val numVectorIn:Int, override val numVectorOut:Int, override val numControlIn:Int, override val numControlOut:Int) extends PCUParams") {
      emitln(s"override val w = ${spade.wordWidth}")
      emitln(s"override val v = ${spade.numLanes}")
      emitln(s"override val numCounters = ${pcu.numCtrs}")
      emitln(s"override val numUDCs = ${pcu.numUDCs}")
      emitRegs(pcu)
      emitStages(pcu)
      emitln(s"override val r = regColors.size")
    }
    emitln(1)
    val mcu = spade.mcus.head
    emitBlock(s"case class GeneratedPMUParams(override val numScalarIn:Int, override val numScalarOut:Int, override val numVectorIn:Int, override val numVectorOut:Int, override val numControlIn:Int, override val numControlOut:Int) extends PMUParams") {
      emitln(s"override val w = ${spade.wordWidth}")
      emitln(s"override val v = ${spade.numLanes}")
      emitln(s"override val numCounters = ${mcu.numCtrs}")
      emitln(s"override val numUDCs = ${mcu.numUDCs}")
      emitRegs(mcu)
      emitStages(mcu)
      emitln(s"override val r = regColors.size")
    }
  }

  def emitParams = {
    cus.foreach { case row =>
      row.foreach { case cu =>
        val param = cu match {
          case mcu:MemoryComputeUnit => s"GeneratedPCUParams(${cu.sins.size}, ${cu.souts.size}, ${cu.vins.size}, ${cu.vouts.size}, ${cu.cins.size}, ${cu.couts.size})"
          case cu:ComputeUnit => s"GeneratedPMUParams(${cu.sins.size}, ${cu.souts.size}, ${cu.vins.size}, ${cu.vouts.size}, ${cu.cins.size}, ${cu.couts.size})"
        }
        emitln(s"${quote(cu)} = $param")
      }
    }

    //val scus = spade.scuArray
    //emitln(s"val scus = Array.fill(${cus.size})(Array.ofDim[Module](${cus.head.size}))")
    //scus.foreach { scu =>
      //emitln(s"${quote(scu)} = Module(new SCU(scuParam)))")
    //}

    //val mcs = spade.mcs

    sbs.foreach { row =>
      row.foreach { sb =>
        emitln(s"${qv(sb)} = VectorSwitchParams(numIns=${sb.vins.size}, numOuts=${sb.vouts.size}, v=${spade.numLanes}, w=${spade.wordWidth})")
        emitln(s"${qs(sb)} = ScalarSwitchParams(numIns=${sb.sins.size}, numOuts=${sb.souts.size}, w=${spade.wordWidth})")
        emitln(s"${qc(sb)} = ControlSwitchParams(numIns=${sb.cins.size}, numOuts=${sb.couts.size})")
      }
    }

    ocus.foreach { row =>
      row.foreach { cu =>
        //TODO
        val param = s"GeneratedPMUParams(${cu.sins.size}, ${cu.souts.size}, ${cu.vins.size}, ${cu.vouts.size}, ${cu.cins.size}, ${cu.couts.size})"
        emitln(s"${quote(cu)} = $param")
      }
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
      s"cuParams($x)($y)"
    case n:OuterComputeUnit =>
      val (x, y) = coordOf(n)
      s"switchCUParams($x)($y)"
    case n:ComputeUnit =>
      val (x, y) = coordOf(n)
      s"cuParams($x)($y)"
  }

  def qv(n:Any):String = n match {
    case n:SwitchBox => 
      val (x, y) = coordOf(n)
      s"vectorSwitchParams($x)($y)"
    case n => quote(n)
  }

  def qs(n:Any):String = n match {
    case n:SwitchBox => 
      val (x, y) = coordOf(n)
      s"scalarSwitchParams($x)($y)"
    case n => quote(n)
  }

  def qc(n:Any):String = n match {
    case n:SwitchBox => 
      val (x, y) = coordOf(n)
      s"controlSwitchParams($x)($y)"
    case n => quote(n)
  }

}
