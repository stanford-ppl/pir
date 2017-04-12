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

  override def splitPreHeader:Unit = {
    emitHeader
  }

  override def splitPostHeader:Unit = {
    emitln(s"self:TopParams =>")
    emitln(s"import plasticineParams._")
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
    emitSplit(emitPCUParams)
    emitTopParams
    emitParamClass
    emitMixed(splitPostHeader)
  }

  def emitTopParams = {
    emitBlock(s"object GeneratedTopParams extends TopParams with GeneratedParams") {
      emitFringeParam
      emitPlasticineParams
    }
  }

  def emitFringeParam = {
    emitBlock(s"val fringeParams = new FringeParams") {
      emitln(s"val numArgIns = ${spade.top.numArgIns}")
      emitln(s"val numArgOuts = ${spade.top.numArgOuts}")
      emitln(s"override val dataWidth = ${spade.wordWidth}")
    }
  }
  
  def emitPlasticineParams = {
    val cuArray = spade.cuArray
    emitBlock(s"val plasticineParams = new PlasticineParams") {
      emitln(s"override val w = ${spade.wordWidth}")
      emitln(s"override val numRows = ${numRows}")
      emitln(s"override val numCols = ${numCols}")
      emitln(s"override val cuParams = Array.fill(${cuArray.size})(Array.ofDim[CUParams](${cuArray.head.size}))")
      val sbs = spade.sbArray
      emitln(s"val vectorSwitchParams = Array.fill(${sbs.size})(Array.ofDim[VectorSwitchParams](${sbs.head.size}))")
      emitln(s"val scalarSwitchParams = Array.fill(${sbs.size})(Array.ofDim[ScalarSwitchParams](${sbs.head.size}))")
      emitln(s"val controlSwitchParams = Array.fill(${sbs.size})(Array.ofDim[ControlSwitchParams](${sbs.head.size}))")
      emitln(s"val numArgOutSelections = ${quote(spade.top.sins.map(_.fanIns.size))}")
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

  def emitPCUParams = {
    val cuArray = spade.cuArray
    val sbs = spade.sbArray
    cuArray.foreach { case row =>
      row.foreach { case cu =>
        val temp = cu match {
          case mcu:MemoryComputeUnit => s"GeneratedPCUParams(${cu.sins.size}, ${cu.souts.size}, ${cu.vins.size}, ${cu.vouts.size}, ${cu.cins.size}, ${cu.couts.size})"
          case cu:ComputeUnit => s"GeneratedPMUParams(${cu.sins.size}, ${cu.souts.size}, ${cu.vins.size}, ${cu.vouts.size}, ${cu.cins.size}, ${cu.couts.size})"
        }
        emitln(s"${quote(cu)} = $temp")
      }
    }

    //val scus = spade.scuArray
    //emitln(s"val scus = Array.fill(${cuArray.size})(Array.ofDim[Module](${cuArray.head.size}))")
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
