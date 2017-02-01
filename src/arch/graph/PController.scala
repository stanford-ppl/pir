package pir.plasticine.graph

import pir.graph.enums._
import pir.plasticine.main._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set

/* Routable element at interconnection level */
trait NetworkElement extends Node {
  def vins:List[InBus[NetworkElement]] // Input Buses
  def vouts:List[OutBus[NetworkElement]] // Output Buses
}

trait GridIO[+NE<:NetworkElement] {
  private val vinMap = MMap[String, ListBuffer[InBus[NetworkElement]]]()
  private val voutMap = MMap[String, ListBuffer[OutBus[NetworkElement]]]()

  def inBuses(num:Int, width:Int):List[InBus[NE]]
  def outBuses(num:Int, width:Int):List[OutBus[NE]]
  def addVinAt(dir:String, num:Int, width:Int)(implicit spade:Spade):this.type = { 
    val ibs = inBuses(num, width)
    ibs.zipWithIndex.foreach { case (ib, i) => ib }
    vinMap.getOrElseUpdate(dir, ListBuffer.empty) ++= ibs
    this
  }
  def addVoutAt(dir:String, num:Int, width:Int)(implicit spade:Spade):this.type = {
    val obs = outBuses(num, width)
    obs.zipWithIndex.foreach { case (ob, i) => ob }
    voutMap.getOrElseUpdate(dir, ListBuffer.empty) ++= obs
    this
  }
  def addVioAt(dir:String, num:Int, width:Int)(implicit spade:Spade):this.type = {
    addVinAt(dir,num, width)
    addVoutAt(dir,num, width)
    this
  }
  def addVins(num:Int, width:Int)(implicit spade:Spade):this.type = { 
    addVinAt("N", num, width)
    this
  }
  def addVouts(num:Int, width:Int)(implicit spade:Spade):this.type = {
    addVoutAt("N", num, width)
    this
  }
  def vinAt(dir:String):List[InBus[NE]] = { vinMap.getOrElse(dir, ListBuffer.empty).toList.asInstanceOf[List[InBus[NE]]] }
  def voutAt(dir:String):List[OutBus[NE]] = { voutMap.getOrElse(dir, ListBuffer.empty).toList.asInstanceOf[List[OutBus[NE]]] }
  def vins:List[InBus[NE]] = SwitchBox.eightDirections.flatMap { dir => vinAt(dir) } 
  def vouts:List[OutBus[NE]] = SwitchBox.eightDirections.flatMap { dir => voutAt(dir) }  
  def io(vin:InBus[NetworkElement]) = {
    val dirs = vinMap.filter{ case (dir, l) => l.contains(vin) }
    assert(dirs.size==1)
    val (dir, list) = dirs.head
    s"${dir.toLowerCase}_${list.indexOf(vin)}"
  }
  def clearIO:Unit = {
    vinMap.clear
    voutMap.clear
  }
}

/* Controller */
trait Controller extends NetworkElement with GridIO[Controller] {
  implicit val ctrler:Controller = this 
  def sins:List[ScalarIn] // Scalar Inputs
  def souts:List[ScalarOut] // Scalar Outputs
  def vins:List[InBus[Controller]] // Input Buses/Vector inputs
  def vouts:List[OutBus[Controller]] // Output Buses/Vector outputs

  def ctrlBox:CtrlBox
  def cins:List[InBus[Controller]] = ctrlBox.ctrlIns // Control inputs 
  def couts:List[OutBus[Controller]] = ctrlBox.ctrlOuts // Control outputs 
  def cinAt(dir:String):List[InBus[Controller]] = ctrlBox.cinAt(dir) 
  def coutAt(dir:String):List[OutBus[Controller]] = ctrlBox.coutAt(dir) 
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(numArgIns:Int, numArgOuts:Int)(implicit spade:Spade) extends Controller with GridIO[Top] {

  override def inBuses(num:Int, width:Int):List[InBus[Top]] = InBuses(this, num, width)
  override def outBuses(num:Int, width:Int):List[OutBus[Top]] = OutBuses(this, num, width)

  //TODO
  val sbw = spade.numLanes
  //assert(numArgIns % sbw == 0)
  //assert(numArgOuts % sbw == 0)
  val numVouts = numArgIns / sbw 
  val numVins = numArgOuts / sbw 
  lazy val sins:List[ScalarIn] = List.tabulate(numVins, sbw) { case (ib, ia) =>
    ScalarIn(vins(ib).outports(ia)).index(sbw*ib + ia)
  }.flatten
  lazy val souts:List[ScalarOut] = List.tabulate(numVouts, sbw) { case (ib, ia) =>
    ScalarOut(vouts(ib).inports(ia))
  }.flatten
  val clk = OutWire(this, s"clk")

  val ctrlBox = new CtrlBox(0, 0, 0)
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox()(implicit spade:Spade) extends NetworkElement with GridIO[SwitchBox] {
  override val typeStr = "sb"
  def inBuses(num:Int, width:Int):List[InBus[SwitchBox]] = InBuses(this, num, width)
  def outBuses(num:Int, width:Int):List[OutBus[SwitchBox]] = OutBuses(this, num, width)
}
object SwitchBox {
  def fourDirections = { "W" :: "N" :: "E" :: "S" ::Nil }
  def eightDirections = { "W" :: "NW" :: "N" :: "NE" :: "E" ::  "SE" :: "S" :: "SW" ::Nil }
  def diagDirections = {"NW":: "NE":: "SE":: "SW" :: Nil}
  def full(bw:Int, width:Int)(implicit spade:Spade) = {
    val sb = SwitchBox()
    eightDirections.foreach { dir =>
      sb.addVinAt(dir, bw, width)
      sb.addVoutAt(dir, bw, width)
    }
    sb.vins.zipWithIndex.foreach { case (vi, idx) => vi.index(idx) }
    sb.vouts.zipWithIndex.foreach { case (vo, idx) => vo.index(idx) }
    sb
  }
}
/*
 * ComputeUnit
 * */
class ComputeUnit()(implicit spade:Spade) extends Controller with GridIO[ComputeUnit] {
  override implicit val ctrler:ComputeUnit = this 
  override val typeStr = "cu"

  var regs:List[Reg] = _
  var srams:List[SRAM] = _
  var ctrs:List[Counter] = _
  var numSinReg:Int = _
  // Bus Output with numLanes words. Assume only single bus output per CU for now
  def inBuses(num:Int, width:Int):List[InBus[ComputeUnit]] = InBuses(this, num, width)
  def outBuses(num:Int, width:Int):List[OutBus[ComputeUnit]] = OutBuses(this, num, width)
  def vout = vouts.head
  
 // Scalar inputs. 1 per word in bus input 
  lazy val _sins:List[ScalarIn] = List.tabulate(vins.size, spade.numLanes) { case (ib, is) =>
      ScalarIn(vins(ib).outports(is)).index(spade.numLanes*ib + is)
  }.flatten
  def sins = _sins
  lazy val _souts:List[ScalarOut] = List.tabulate(spade.numLanes) { is => ScalarOut(None) }
  def souts = _souts
  private var _etstage:EmptyStage = _ // Empty Stage
  private val _wastages:ListBuffer[WAStage] = ListBuffer.empty // Write Addr Stages
  private val _rastages:ListBuffer[FUStage] = ListBuffer.empty // Read Addr Stages
  private val _regstages:ListBuffer[FUStage] = ListBuffer.empty  // Regular Stages
  private val _rdstages:ListBuffer[ReduceStage] = ListBuffer.empty // Reduction Stages
  private val _fustages:ListBuffer[FUStage] = ListBuffer.empty // Function Unit Stages
  private val _stages:ListBuffer[Stage] = ListBuffer.empty // All stages
  def etstage:EmptyStage = _etstage // Empty Stage
  def wastages:List[WAStage] = _wastages.toList // Write Addr Stages
  def rastages:List[FUStage] = _rastages.toList // Read Addr Stages
  def regstages:List[FUStage] = _regstages.toList // Regular Stages
  def rdstages:List[ReduceStage] = _rdstages.toList // Reduction Stages
  def fustages:List[FUStage] = _fustages.toList // Function Unit Stages
  def stages:List[Stage] = _stages.toList // All stages

  def addETStage(stage:EmptyStage) = { _etstage = stage; addStages(stage::Nil) }
  def addWAstages(stages:List[WAStage]) = { _wastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRAstages(stages:List[FUStage]) = { _rastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRegstages(stages:List[FUStage]) = { _regstages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRdstages(stages:List[ReduceStage]) = { _rdstages ++= stages; _fustages ++= stages; addStages(stages) }

  private def addStages(sts:List[Stage]) = {
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

  var ctrlBox:CtrlBox = _

  val reduce = RMOutPort(this, s"${this}.reduce")

  def numRegs(num:Int):this.type = { 
    regs = List.tabulate(num) { ir => Reg().index(ir) }
    addETStage(EmptyStage(regs))
    this
  }
  def numCtrs(num:Int):this.type = { ctrs = List.tabulate(num) { ic => Counter().index(ic) }; this }
  def numSRAMs(num:Int):this.type = { srams = List.tabulate(num) { is => SRAM().index(is) }; this }
  def numSinReg(num:Int):this.type = { numSinReg = num; this }
  def ctrlBox(numTokenOutLUTs:Int, numTokenDownLUTs:Int):this.type = { 
    ctrlBox = new CtrlBox(ctrs.size, numTokenOutLUTs, numTokenDownLUTs); this
  }
}

/* A spetial type of CU used for memory loader/storer */
class TileTransfer()(implicit spade:Spade) extends ComputeUnit with GridIO[TileTransfer]{
  override val typeStr = "tt"
  override def inBuses(num:Int, width:Int):List[InBus[TileTransfer]] = InBuses(this, num, width)
  override def outBuses(num:Int, width:Int):List[OutBus[TileTransfer]] = OutBuses(this, num, width)
  val addrOut = AddrOut()
  override val souts = List(addrOut)
}
class MemoryController()(implicit spade:Spade) extends ComputeUnit with GridIO[MemoryController]{
  override val typeStr = "mc"
  override def inBuses(num:Int, width:Int):List[InBus[MemoryController]] = InBuses(this, num, width)
  override def outBuses(num:Int, width:Int):List[OutBus[MemoryController]] = OutBuses(this, num, width)
}
