package pir.plasticine.graph

import pir.util.enums._
import pir.plasticine.main._
import pir.plasticine.config.ConfigFactory
import pir.plasticine.simulation._
import pir.plasticine.util._
import pir.exceptions._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set

/* Routable element at interconnection level */
trait NetworkElement extends Module with Simulatable {
  def scalarIO:GridIO[this.type]
  def vectorIO:GridIO[this.type]
  def ctrlIO:GridIO[this.type]
}

/* Controller */
abstract class Controller(implicit spade:Spade) extends NetworkElement {
  implicit val ctrler:this.type = this 
  import spademeta._

  def ctrlBox:CtrlBox
  val scalarIO:GridIO[this.type] = ScalarIO(this)
  val vectorIO:GridIO[this.type] = VectorIO(this)
  val ctrlIO:GridIO[this.type] = ControlIO(this)

  def sins:List[Input[Bus, Controller]] = scalarIO.ins // Scalar Inputs
  def souts:List[Output[Bus, Controller]] = scalarIO.outs // Scalar Outputs
  def vins:List[Input[Bus, Controller]] = vectorIO.ins// Input Buses/Vector inputs
  def vouts:List[Output[Bus, Controller]] = vectorIO.outs // Output Buses/Vector outputs
  def cins:List[Input[Bus, Controller]] = ctrlIO.ins // Control inputs
  def couts:List[Output[Bus, Controller]] = ctrlIO.outs // Control outputs
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(numArgIns:Int, numArgOuts:Int)(implicit spade:Spade) extends Controller { self =>
  import spademeta._
  val ctrlBox:CtrlBox = new CtrlBox(0)
  override def register(implicit sim:Simulator):Unit = {
    super.register
    import sim._
    ctrlIO.outs.foreach { o => v(o).set { sim => uv(o).value.map( v => 
      Some(true)
    ) } }
  }
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox()(implicit spade:SwitchNetwork) extends NetworkElement {
  import spademeta._
  override val typeStr = "sb"
  val scalarIO:GridIO[this.type] = ScalarIO(this)
  val vectorIO:GridIO[this.type] = VectorIO(this)
  val ctrlIO:GridIO[this.type] = ControlIO(this)
  override def register(implicit sim:Simulator):Unit = {
    super.register
    import sim._
    val xbmap = mapping.xbmap
    (scalarIO.outs ++ vectorIO.outs ++ ctrlIO.outs).foreach { out =>
      xbmap.get(out).foreach { in =>
        val vout = v(out)
        if (!vout.isV(v(in))) throw PIRException(s"Cannot eval $out (tp=${v(out).tp}) to $in (tp=${v(in).tp})")
        vout.set{ sim => sim.ev(in).value }
      }
    }
  }
}
/*
 * ComputeUnit
 * */
class ComputeUnit()(implicit spade:Spade) extends Controller {
  import spademeta._
  //override implicit val ctrler:ComputeUnit = this 
  override val typeStr = "cu"

  var regs:List[ArchReg] = Nil
  var srams:List[SRAM] = Nil
  var ctrs:List[Counter] = Nil
  var scalarIns:List[ScalarIn] = Nil
  var scalarOuts:List[ScalarOut] = Nil
  var vectorIns:List[VectorIn] = Nil
  var vectorOuts:List[VectorOut] = Nil

  def vout = vouts.head
  
 // Scalar inputs. 1 per word in bus input 
  //lazy val _sins:List[ScalarIn] = List.tabulate(vins.size, spade.numLanes) { case (ib, is) =>
      //ScalarIn(vins(ib).outports(is)).index(spade.numLanes*ib + is)
  //}.flatten
  //def sins = _sins
  //lazy val _souts:List[ScalarOut] = List.tabulate(spade.numLanes) { is => ScalarOut(None) }
  //def souts = _souts
  protected var _etstage:EmptyStage = _ // Empty Stage
  protected val _regstages:ListBuffer[FUStage] = ListBuffer.empty  // Regular Stages
  protected val _rdstages:ListBuffer[ReduceStage] = ListBuffer.empty // Reduction Stages
  protected val _fustages:ListBuffer[FUStage] = ListBuffer.empty // Function Unit Stages
  protected val _stages:ListBuffer[Stage] = ListBuffer.empty // All stages
  def etstage:EmptyStage = _etstage // Empty Stage
  def regstages:List[FUStage] = _regstages.toList // Regular Stages
  def rdstages:List[ReduceStage] = _rdstages.toList // Reduction Stages
  def fustages:List[FUStage] = _fustages.toList // Function Unit Stages
  def stages:List[Stage] = _stages.toList // All stages

  def addETStage(stage:EmptyStage) = { _etstage = stage; addStages(stage::Nil) }
  def addRegstages(stages:List[FUStage]) = { _regstages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRdstages(stages:List[ReduceStage]) = { _rdstages ++= stages; _fustages ++= stages; addStages(stages) }

  protected def addStages(sts:List[Stage]) = {
    sts.zipWithIndex.foreach { case (stage, i) =>
      stage match {
        case etstage:EmptyStage =>
          stage.index(-1) // Empty stage is -1
        case _ =>
          stage.index(i + stages.last.index + 1) // Empty stage is -1
          if (i==0) {
            stage.pre = Some(stages.last)
          } else {
            stage.pre = Some(sts(i-1))
          }
          stage.pre.get.next = Some(stage)
      }
    }
    _stages ++= sts
  }

  var _ctrlBox:CtrlBox = _
  def ctrlBox:CtrlBox = _ctrlBox

  def numRegs(num:Int):this.type = { 
    regs = List.tabulate(num) { ir => ArchReg().index(ir) }
    addETStage(EmptyStage(regs))
    this
  }
  def numCtrs(num:Int):this.type = { ctrs = List.tabulate(num) { i => Counter().index(i) }; this }
  def numCtrs = ctrs.size
  def numSRAMs(num:Int):this.type = { srams = List.tabulate(num) { i => SRAM().index(i) }; this }
  def numSRAMs = srams.size
  def numScalarIns(num:Int):this.type = { scalarIns = List.tabulate(num)  { i => ScalarIn().index(i) }; this }
  def numScalarIns:Int = scalarIns.size
  def numScalarOuts(num:Int):this.type = { scalarOuts = List.tabulate(num)  { i => ScalarOut().index(i) }; this }
  def numScalarOuts:Int = scalarOuts.size
  def numVecIns(num:Int):this.type = { vectorIns = List.tabulate(num) { i => VectorIn().index(i) }; this }
  def numVecIns:Int = vectorIns.size
  def numVecOuts(num:Int):this.type = { vectorOuts = List.tabulate(num) { o => VectorOut().index(o) }; this }
  def numVecOuts:Int = vectorOuts.size
  def addRegstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = { 
    addRegstages(List.fill(numStage) { FUStage(numOprds=numOprds, regs, ops) }); this // Regular stages
  }
  def addRdstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addRdstages(List.fill(numStage) { ReduceStage(numOprds=numOprds, regs, ops)}); this // Reduction stage 
  } 
  def ctrlBox(numUDCs:Int):this.type = { _ctrlBox = new CtrlBox(numUDCs); this }

  def map(io:IO[Bus, ComputeUnit], buf:LocalBuffer) = {
    (io.tp, buf) match {
      case (Bus(_, _:Word), _:VectorBuffer) => bufsOf(io) += buf; busesOf(buf) += io
      case (Bus(_,_:Bit), _:ScalarBuffer) => bufsOf(io) += buf; busesOf(buf) += io 
      case _ => throw PIRException(s"Cannot map $io to $buf")
    }
  }
  def genConnections:this.type = { ConfigFactory.genConnections(this); this } 
  def color(range:Range, color:RegColor):this.type = { range.foreach { i => regs(i).color(color) }; this }
  def color(i:Int, color:RegColor):this.type = { regs(i).color(color); this }
  def genMapping(vins:Range, vouts:Range, sins:Range, souts:Range, ctrs:Range, wa:Int, ra:Int, loads:Range, rd:Int):this.type = {
    //ConfigFactory.genMapping(this, vinsPtr, voutPtr, sinsPtr, soutsPtr, ctrsPtr, waPtr, wpPtr, loadsPtr, rdPtr)
    this
  }

}

class OuterComputeUnit()(implicit spade:Spade) extends ComputeUnit {
  import spademeta._
  override val typeStr = "ocu"
  this.numRegs(0)
  this.numSRAMs(0)
  this.addRegstages(numStage=0, numOprds=0, ops)
}

class MemoryComputeUnit()(implicit spade:Spade) extends ComputeUnit {
  override val typeStr = "mcu"
  import spademeta._
  private val _wastages:ListBuffer[WAStage] = ListBuffer.empty // Write Addr Stages
  private val _rastages:ListBuffer[FUStage] = ListBuffer.empty // Read Addr Stages
  def wastages:List[WAStage] = _wastages.toList // Write Addr Stages
  def rastages:List[FUStage] = _rastages.toList // Read Addr Stages
  def addWAstages(stages:List[WAStage]) = { _wastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRAstages(stages:List[FUStage]) = { _rastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addWAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addWAstages(List.fill(numStage) { WAStage(numOprds=numOprds, regs, ops)}); this // Write Addr stage 
  } 
  def addRAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addRAstages(List.fill(numStage) { FUStage(numOprds=numOprds, regs, ops)}); this // Read Addr stage 
  } 
}

/* A spetial type of CU used for memory loader/storer */
class ScalarComputeUnit()(implicit spade:Spade) extends ComputeUnit {
  override val typeStr = "scu"
  import spademeta._
}
class MemoryController()(implicit spade:Spade) extends Controller {
  override val typeStr = "mc"
  import spademeta._
  var _ctrlBox:CtrlBox = _
  def ctrlBox:CtrlBox = _ctrlBox
  def ctrlBox(numUDCs:Int):this.type = { _ctrlBox = new CtrlBox(numUDCs); this }
}
