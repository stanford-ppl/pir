package pir.plasticine.graph

import pir.graph.enums._
import pir.plasticine.main._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
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
case class Top(numLanes:Int, numArgIns:Int, numArgOuts:Int)(implicit spade:Spade) extends Controller {
  assert(numArgIns % numLanes == 0)
  assert(numArgOuts % numLanes == 0)
  val numVouts = numArgIns / numLanes
  val numVins = numArgOuts / numLanes
  val vins:List[InBus[Top]] = InBuses(this, numVins, numLanes)
  val vouts:List[OutBus[Top]] = OutBuses(this, numVouts, numLanes)
  val sins:List[ScalarIn] = List.tabulate(numVins, numLanes) { case (ib, ia) =>
    ScalarIn(vins(ib).outports(ia))
  }.flatten
  val souts:List[ScalarOut] = List.tabulate(numVouts, numLanes) { case (ib, ia) =>
    ScalarOut(vouts(ib).inports(ia))
  }.flatten
  val clk = OutWire(this, s"clk")

  val cin = InBus(this, 0, 1)
  val cout = OutBus(this, 0, 1)
  def cins = cin::Nil
  def couts = cout::Nil 
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox(numVins:Int, numVouts:Int, width:Int)(implicit spade:Spade) extends NetworkElement {
  val vins:List[InBus[SwitchBox]] = InBuses(this, numVins, width)
  val vouts:List[OutBus[SwitchBox]] = OutBuses(this, numVouts, width)
  override val typeStr = "sb"
}
/* ComputeUnit
 * */
class ComputeUnit(numLanes:Int, numBusIns:Int)(implicit spade:Spade) extends Controller {
  override implicit val ctrler:ComputeUnit = this 
  override val typeStr = "cu"

  var regs:List[Reg] = _
  var srams:List[SRAM] = _
  var ctrs:List[Counter] = _
  val vins:List[InBus[ComputeUnit]] = InBuses(this, numBusIns, numLanes) // Bus Input with numLanes words
  // Bus Output with numLanes words. Assume only single bus output per CU for now
  val vout:OutBus[ComputeUnit] = OutBus(this, 0, numLanes) 
  val vouts = List(vout)
  val sins:List[ScalarIn] = List.tabulate(vins.size, numLanes) { case (ib, is) => // Scalar inputs. 1 per word in bus input 
      ScalarIn(vins(ib).outports(is))
  }.flatten
  val souts:List[ScalarOut] = List.tabulate(vout.inports.size) { is => ScalarOut(vout.inports(is)) }
  var etstage:EmptyStage = _ // Empty Stage
  var wastages:List[WAStage] = _ // Write Addr Stages
  var rastages:List[FUStage] = _ // Read Addr Stages
  var regstages:List[FUStage] = _  // Regular Stages
  var rdstages:List[ReduceStage] = _ // Reduction Stages
  def fustages:List[FUStage] = wastages ++ rastages ++ regstages ++ rdstages // Stages with fu 
  def stages:List[Stage] = {
    val sts = etstage :: fustages
    if (indexOf.get(etstage).isEmpty) {
      for (i <- 0 until sts.size) {
        sts(i).pre = if (i!=0) Some(sts(i-1)) else None
        sts(i).next = if (i!=sts.size-1) Some(sts(i+1)) else None
        sts(i).index(i-1) // Empty stage is -1
      }
    }
    sts
  }
  var ctrlBox:CtrlBox = _

  val reduce = RMOutPort(this, s"${this}.reduce")
  assert(vins.size>0, "ComputeUnit must have at least 1 vector input")

  def numRegs(num:Int):this.type = { 
    regs = List.tabulate(num) { ir => Reg(ir) }
    etstage = EmptyStage(regs)
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
class TileTransfer(numLanes:Int, numBusIns:Int)(implicit spade:Spade) 
extends ComputeUnit(numLanes, numBusIns) {
  override val typeStr = "tt"
  val addrOut = AddrOut()
  override val souts = List(addrOut)
  regstages = Nil
  rdstages = Nil
}
