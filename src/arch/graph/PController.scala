package pir.plasticine.graph

import pir.graph.enums._
import pir.plasticine.main._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

/* Routable element at interconnection level */
trait NetworkElement extends Node {
  def vins:List[InBus[NetworkElement]] // Input Buses
  def vouts:List[OutBus[NetworkElement]] // Output Buses
  def coord(c:(Int, Int))(implicit spade:Spade):this.type = { coordOf(this) = c; this} // Coordinate
}

/* Controller */
trait Controller extends NetworkElement {
  implicit val ctrler:Controller = this 
  def sins:List[ScalarIn] // Scalar Inputs
  def souts:List[ScalarOut] // Scalar Outputs
  def vins:List[InBus[Controller]] // Input Buses/Vector inputs
  def vouts:List[OutBus[Controller]] // Output Buses/Vector outputs

  def cins:List[InBus[Controller]] // Control inputs 
  def couts:List[OutBus[Controller]] // Control outputs 
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(numArgIns:Int, numArgOuts:Int)(implicit spade:Spade) extends Controller {
  val sbw = spade.scalarBandwidth
  assert(numArgIns % sbw == 0)
  assert(numArgOuts % sbw == 0)
  val numVouts = numArgIns / sbw 
  val numVins = numArgOuts / sbw 
  val vins:List[InBus[Top]] = InBuses(this, numVins, sbw)
  val vouts:List[OutBus[Top]] = OutBuses(this, numVouts, sbw)
  val sins:List[ScalarIn] = List.tabulate(numVins, sbw) { case (ib, ia) =>
    ScalarIn(vins(ib).outports(ia)).index(numVins*ib + ia)
  }.flatten
  val souts:List[ScalarOut] = List.tabulate(numVouts, sbw) { case (ib, ia) =>
    ScalarOut(vouts(ib).inports(ia))
  }.flatten
  val clk = OutWire(this, s"clk")

  val cin = InBus(this, 0, 1)
  val cout = OutBus(this, 0, 1)
  def cins = cin::Nil
  def couts = cout::Nil 
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox(map:Map[String, Int], width:Int)(implicit spade:Spade) extends NetworkElement {
  var vinMap = Map[String, List[InBus[SwitchBox]]]()
  var voutMap = Map[String, List[OutBus[SwitchBox]]]()
  map.foreach { case (d, bw) =>
    val (io, dir) = d.splitAt(1)
    if (io=="i")
      vinMap += s"$dir" -> InBuses(this, bw, width)
    else
      voutMap += s"$dir" -> OutBuses(this, bw, width)
  }

  val vins:List[InBus[SwitchBox]] = SwitchBox.eightDirections.flatMap { dir => vinMap.getOrElse(dir, Nil) } 
  val vouts:List[OutBus[SwitchBox]] = SwitchBox.eightDirections.flatMap { dir => voutMap.getOrElse(dir, Nil) }  
 
  override val typeStr = "sb"
}
object SwitchBox {
  def fourDirections = { "W" :: "N" :: "E" :: "S" ::Nil }
  def eightDirections = { "W" :: "NW" :: "N" :: "NE" :: "E" ::  "SE" :: "S" :: "SW" ::Nil }
  def full(bw:Int, width:Int)(implicit spade:Spade) = {
    var map = Map[String, Int]()
    eightDirections.foreach { dir =>
      map += s"i$dir" -> bw
      map += s"o$dir" -> bw
    }
    SwitchBox(map, width)
  }
}
/*
 * ComputeUnit
 * */
class ComputeUnit(numBusIns:Int)(implicit spade:Spade) extends Controller {
  override implicit val ctrler:ComputeUnit = this 
  override val typeStr = "cu"

  var regs:List[Reg] = _
  var srams:List[SRAM] = _
  var ctrs:List[Counter] = _
  val bandWidth = 1
  var vinMap = Map[String, List[InBus[ComputeUnit]]]()
  var voutMap = Map[String, List[OutBus[ComputeUnit]]]()
  spade match {
    case sn:SwitchNetwork =>
      List("W", "NW", "N", "SW").foreach { dir =>
        vinMap += s"$dir" -> InBuses(this, bandWidth, spade.numLanes)
      }
      List("E").foreach { dir =>
        voutMap += s"$dir" -> OutBuses(this, bandWidth, spade.numLanes)
      }
    case pn:PointToPointNetwork =>
  }
  // Bus Output with numLanes words. Assume only single bus output per CU for now
  val vins:List[InBus[ComputeUnit]] = spade match {
    case sn:SwitchNetwork =>
      SwitchBox.eightDirections.flatMap { dir => vinMap.getOrElse(dir, Nil) } 
    case pn:PointToPointNetwork =>
      InBuses(this, numBusIns, spade.numLanes)
  }
  
  val vouts:List[OutBus[ComputeUnit]] = spade match {
    case sn:SwitchNetwork =>
      SwitchBox.eightDirections.flatMap {  dir => voutMap.getOrElse(dir, Nil) }  
    case pn:PointToPointNetwork =>
      OutBuses(this, 1, spade.numLanes)
  }
  val vout = vouts.head
 // Scalar inputs. 1 per word in bus input 
  val sins:List[ScalarIn] = List.tabulate(vins.size, spade.scalarBandwidth) { case (ib, is) =>
      ScalarIn(vins(ib).outports(is)).index(vins.size*ib + is)
  }.flatten
  val souts:List[ScalarOut] = List.tabulate(spade.scalarBandwidth) { is => ScalarOut(vout.inports(is)) }
  private var _etstage:EmptyStage = _ // Empty Stage
  private var _wastages:List[WAStage] = Nil // Write Addr Stages
  private var _rastages:List[FUStage] = Nil // Read Addr Stages
  private var _regstages:List[FUStage] = Nil  // Regular Stages
  private var _rdstages:List[ReduceStage] = Nil // Reduction Stages
  private var _fustages:List[FUStage] = Nil // Function Unit Stages
  private var _stages:List[Stage] = Nil // All stages
  def etstage:EmptyStage = _etstage // Empty Stage
  def wastages:List[WAStage] = _wastages // Write Addr Stages
  def rastages:List[FUStage] = _rastages // Read Addr Stages
  def regstages:List[FUStage] = _regstages  // Regular Stages
  def rdstages:List[ReduceStage] = _rdstages // Reduction Stages
  def fustages:List[FUStage] = _fustages // Function Unit Stages
  def stages:List[Stage] = _stages // All stages

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
  assert(vins.size>0, "ComputeUnit must have at least 1 vector input")

  def numRegs(num:Int):this.type = { 
    regs = List.tabulate(num) { ir => Reg(ir).index(ir) }
    addETStage(EmptyStage(regs))
    this
  }
  def numCtrs(num:Int):this.type = { ctrs = List.tabulate(num) { ic => Counter(ic) }; this }
  def numSRAMs(num:Int):this.type = { srams = List.tabulate(num) { is => SRAM(is) }; this }
  def ctrlBox(numTokenIns:Int, numTokenOutLUTs:Int, numTokenDownLUTs:Int):this.type = { 
    ctrlBox = new CtrlBox(ctrs.size, numTokenIns, numTokenOutLUTs, numTokenDownLUTs); this
  }

  def cins = ctrlBox.ctrlIns
  def couts = ctrlBox.ctrlOuts

}

/* A spetial type of CU used for memory loader/storer */
class TileTransfer(numBusIns:Int)(implicit spade:Spade) 
extends ComputeUnit(numBusIns) {
  override val typeStr = "tt"
  val addrOut = AddrOut()
  override val souts = List(addrOut)
}
