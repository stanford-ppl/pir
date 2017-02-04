package pir.plasticine.graph

import pir.graph.enums._
import pir.plasticine.main._
import pir.plasticine.config.ConfigFactory

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set

/* Routable element at interconnection level */
trait NetworkElement extends Node {
  def scalarIO:GridIO[this.type]
  def vectorIO:GridIO[this.type]
  def ctrlIO:GridIO[this.type]
}

/* Controller */
abstract class Controller(implicit spade:Spade) extends NetworkElement {
  implicit val ctrler:this.type = this 

  def ctrlBox:CtrlBox[Controller]
  val scalarIO:GridIO[this.type] = ScalarIO(this)
  val vectorIO:GridIO[this.type] = VectorIO(this)
  val ctrlIO:GridIO[this.type] = ControlIO(this)

  def sins:List[InBus[Controller]] = scalarIO.ins // Scalar Inputs
  def souts:List[OutBus[Controller]] = scalarIO.outs // Scalar Outputs
  def vins:List[InBus[Controller]] = vectorIO.ins// Input Buses/Vector inputs
  def vouts:List[OutBus[Controller]] = vectorIO.outs // Output Buses/Vector outputs
  def cins:List[InBus[Controller]] = ctrlIO.ins // Control inputs
  def couts:List[OutBus[Controller]] = ctrlIO.outs // Control outputs
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(numArgIns:Int, numArgOuts:Int)(implicit spade:Spade) extends Controller { self =>
  //implicit override val ctrler:Top = self

  //lazy val sins:List[ScalarIn] = List.tabulate(numVins, sbw) { case (ib, ia) =>
    //ScalarIn(vins(ib).outports(ia)).index(sbw*ib + ia)
  //}.flatten
  //lazy val souts:List[ScalarOut] = List.tabulate(numVouts, sbw) { case (ib, ia) =>
    //ScalarOut(vouts(ib).inports(ia))
  //}.flatten
  val clk = OutWire(this, s"clk")

  val ctrlBox:CtrlBox[Top] = new CtrlBox(0)
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox()(implicit spade:SwitchNetwork) extends NetworkElement {
  override val typeStr = "sb"
  val scalarIO:GridIO[this.type] = ScalarIO(this)
  val vectorIO:GridIO[this.type] = VectorIO(this)
  val ctrlIO:GridIO[this.type] = ControlIO(this)
}
object SwitchBox {
  def fourDirections = { "W" :: "N" :: "E" :: "S" ::Nil }
  def eightDirections = { "W" :: "NW" :: "N" :: "NE" :: "E" ::  "SE" :: "S" :: "SW" ::Nil }
  def diagDirections = {"NW":: "NE":: "SE":: "SW" :: Nil}
}
/*
 * ComputeUnit
 * */
class ComputeUnit()(implicit spade:Spade) extends Controller {
  //override implicit val ctrler:ComputeUnit = this 
  override val typeStr = "cu"

  var regs:List[Reg] = _
  var srams:List[SRAM] = _
  var ctrs:List[Counter] = _
  var numSinReg:Int = _

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

  var _ctrlBox:CtrlBox[ComputeUnit] = _
  def ctrlBox:CtrlBox[ComputeUnit] = _ctrlBox

  val reduce = RMOutPort(this, s"${this}.reduce")

  def numRegs(num:Int):this.type = { 
    regs = List.tabulate(num) { ir => Reg().index(ir) }
    addETStage(EmptyStage(regs))
    this
  }
  def numCtrs(num:Int):this.type = { ctrs = List.tabulate(num) { ic => Counter().index(ic) }; this }
  def numSRAMs(num:Int):this.type = { srams = List.tabulate(num) { is => SRAM().index(is) }; this }
  def numSinReg(num:Int):this.type = { numSinReg = num; this }
  def addRegstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = { 
    addRegstages(List.fill(numStage) { FUStage(numOprds=numOprds, regs, ops) }); this // Regular stages
  }
  def addRdstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addRdstages(List.fill(numStage) { ReduceStage(numOprds=numOprds, regs, ops)}); this // Reduction stage 
  } 
  def ctrlBox(numUDCs:Int):this.type = { _ctrlBox = new CtrlBox(numUDCs); this }
  def genConnections:this.type = { ConfigFactory.genConnections(this); this } 
  def genMapping(vinsPtr:Int, voutPtr:Int, sinsPtr:Int, soutsPtr:Int, ctrsPtr:Int, waPtr:Int, wpPtr:Int, loadsPtr:Int, rdPtr:Int):this.type = {
    ConfigFactory.genMapping(this, vinsPtr, voutPtr, sinsPtr, soutsPtr, ctrsPtr, waPtr, wpPtr, loadsPtr, rdPtr)
    this
  }

}

class MemoryComputeUnit()(implicit spade:Spade) extends ComputeUnit {
  override val typeStr = "mcu"
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
}
class MemoryController()(implicit spade:Spade) extends Controller {
  override val typeStr = "mc"
  var _ctrlBox:CtrlBox[MemoryController] = _
  def ctrlBox:CtrlBox[MemoryController] = _ctrlBox
  def ctrlBox(numUDCs:Int):this.type = { _ctrlBox = new CtrlBox(numUDCs); this }
}
