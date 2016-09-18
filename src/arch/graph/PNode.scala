package pir.plasticine.graph

import pir.graph._
import pir.graph.enums._
import pir.plasticine.main._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

/* Spade Node */
class Node(implicit spade:Spade) extends Metadata { 
  val id : Int = spade.nextId
  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }
  val typeStr = this.getClass().getSimpleName()
  override def toString = s"${typeStr}${id}" 
  def index(i:Int)(implicit spade:Spade):this.type = { indexOf(this) = i; this }
}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM(implicit spade:Spade) extends Node{
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val readPort = RMOutPort(this, s"${this}.rp")
  val writePort = RMInPort(this, s"${this}.wp")
  val readAddr = RMInPort(this, s"${this}.ra")
  val writeAddr = RMInPort(this, s"${this}.wa")
}

object SRAM extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):SRAM = SRAM().index(idx)
}

/** Physical Counter  */
case class Counter(implicit spade:Spade) extends Node {
  override val typeStr = "ctr"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val min = InPort(this, s"${this}.min")
  val max = InPort(this, s"${this}.max")
  val step = InPort(this, s"${this}.step")
  val out = RMOutPort(this, s"${this}.out")
  val en = InWire(this, s"${this}.en")
  val done = OutWire(this, s"${this}.done")
  def isDep(c:Counter) = en.canFrom(c.done)
}
object Counter extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):Counter = Counter().index(idx)
}

/* Logical register (1 row of pipeline registers for all stages) */
case class Reg(implicit spade:Spade) extends Node {
  override val typeStr = "reg"
}
object Reg extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):Reg = Reg().index(idx)
}

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:Reg)(implicit spade:Spade) extends Node {
  override val typeStr = "pr"
  override def toString = s"pr(${stage},${reg})"
  val in = new InPort[this.type]() with Stagable { 
    override def toString = s"$src.i"
    override val stage = PipeReg.this.stage
  }.src(this)
  val out = new OutPort[this.type]() with Stagable {
    override def toString = s"$src.o"
    override val stage = PipeReg.this.stage
  } 
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
class ScalarBuffer(implicit spade:Spade) extends Node {
  val in:InPort[this.type] = InPort(this, s"${this}.i") 
  val out:OutPort[this.type] = OutPort(this, s"${this}.o")
} 
/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarIn(outport:Option[OutPort[InBus[NetworkElement]]])(implicit spade:Spade) extends ScalarBuffer {
  outport.foreach { this.in <== _ }
  override val typeStr = "si"
  override val out:RMOutPort[this.type] = RMOutPort(this, s"${this}.o")
  def inBus:InBus[NetworkElement] = outport.get.src.get
  def idx = indexOf(outport.get)
} 
object ScalarIn {
  def apply(outport:OutPort[InBus[NetworkElement]])(implicit spade:Spade):ScalarIn = ScalarIn(Some(outport))
}
/* Scalar buffer between the last stage and the bus output. Output connects to 1 in port 
 * of the OutBus */
case class ScalarOut(inport:Option[InPort[OutBus[NetworkElement]]])(implicit spade:Spade) extends ScalarBuffer {
  inport.foreach( _ <== this.out )
  override val typeStr = "so"
  override val in:RMInPort[this.type] = RMInPort(this, s"${this}.i")
  def outBus:OutBus[NetworkElement] = inport.get.src.get
  def idx = indexOf(inport.get)
}
object ScalarOut {
  def apply(inport:InPort[OutBus[NetworkElement]])(implicit spade:Spade):ScalarOut = ScalarOut(Some(inport))
}
/* ScalarOut of TileTransfer CU, whos AddrOut has dedicated scalar network that goes to
 * Memory Controller */
trait AddrOut extends ScalarOut {
  override val typeStr = "ado"
}
object AddrOut {
  def apply()(implicit spade:Spade) = new ScalarOut(None) with AddrOut
}

/* Function unit. 
 * @param numOprds number of operands
 * @param ops List of supported ops
 * @param stage which stage the FU locates
 * */
case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage)(implicit spade:Spade) extends Node {
  override val typeStr = "fu"
  val operands = List.fill(numOprds) (
    new InPort[this.type]() with Stagable { 
      override def toString = s"$src.oprd${id}"
      override val stage = FuncUnit.this.stage
    }.src(this)
  ) 
  val out = new OutPort[this.type]() with Stagable {
    override def toString = s"$src.out"
    override val stage = FuncUnit.this.stage
  } 
}

/*
 * Phyical stage. 1 column of FU and Pipeline Register block accross lanes. 
 * @param reg Logical registers in current register block
 * */
class Stage(regs:List[Reg])(implicit spade:Spade) extends Node {
  val funcUnit:Option[FuncUnit] = None
  val prs = Map[Reg, PipeReg]() // Mapping between logical register and physical register
  regs.foreach { reg => prs += (reg -> PipeReg(this, reg)) }
  var pre:Option[Stage] = _ // Set up in controller
  var next:Option[Stage] = _
  def before(s:Stage) = indexOf(this) < indexOf(s)
  def after(s:Stage) = indexOf(this) > indexOf(s)
  override val typeStr = "st"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
}
/* Dummy stage that only has register block */
trait EmptyStage extends Stage {
  override val typeStr = "etst"
}
object EmptyStage {
  def apply(regs:List[Reg])(implicit spade:Spade) = new Stage(regs) with EmptyStage
}
/* Stage with Function unit */
trait FUStage extends Stage {
  def fu:FuncUnit = funcUnit.get 
}
object FUStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op])(implicit spade:Spade):FUStage = 
    new Stage(regs) with FUStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}
/* Reduction stage */
trait ReduceStage extends FUStage {
  override val typeStr = "rdst"
}
object ReduceStage {
  /*
   * Create a list of reduction stages
   * @param numOprds number of operand
   * @param regs list of logical registers in the stage
   * @param ops reduction operations
   * */
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op])(implicit spade:Spade):ReduceStage = 
    new Stage(regs) with ReduceStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}
/* WriteAddr calculation stage */
trait WAStage extends FUStage {
  override val typeStr = "wast"
}
object WAStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op])(implicit spade:Spade):WAStage = 
    new Stage(regs) with WAStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}

/* Controller */
trait Controller extends Node with NetworkElement {
  val sins:List[ScalarIn]
  val souts:List[ScalarOut]
  val vins:List[InBus[_]]
  val vouts:List[OutBus[_]]
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(argIns:List[ScalarOut], argOuts:List[ScalarIn], argInBuses:List[OutBus[Top]], 
  argOutBuses:List[InBus[Top]])(implicit spade:Spade) extends Controller {
  override val sins:List[ScalarIn] = argOuts
  override val souts:List[ScalarOut] = argIns
  override val vins:List[InBus[Top]] = argOutBuses
  override val vouts:List[OutBus[Top]] = argInBuses
  val clk = OutWire(this, s"clk")
  def numArgIn = argIns.size
  def numArgOut = argOuts.size
}

/* ComputeUnit
 * */
class ComputeUnit(val regs:List[Reg], val srams:List[SRAM], val ctrs:List[Counter], 
  override val sins:List[ScalarIn], override val souts:List[ScalarOut], 
  override val vins:List[InBus[ComputeUnit]], val vout:OutBus[ComputeUnit],
  val stages:List[Stage], val ctrlBox:CtrlBox)(implicit spade:Spade) extends Controller {
  override val typeStr = "cu"
  override val vouts = List(vout)

  for (i <- 0 until stages.size) {
    stages(i).pre = if (i!=0) Some(stages(i-1)) else None
    stages(i).next = if (i!=stages.size-1) Some(stages(i+1)) else None
    indexOf(stages(i)) = i-1 // Empty stage is -1
  }
  private val es::fs = stages 
  val etstage:EmptyStage = es.asInstanceOf[EmptyStage]
  val fustages:List[FUStage] = fs.asInstanceOf[List[FUStage]]
  val rdstages:List[ReduceStage] = stages.collect {case s:ReduceStage => s}

  val reduce = RMOutPort(this, s"${this}.reduce")
  vins.foreach(_.src(this))
  vout.src(this)
  assert(vins.size>0, "ComputeUnit must have at least 1 vector input")

  def numRegs = regs.size 
  def numCtrs = ctrs.size
  def numSRAMs = srams.size
  def numScalarIn = sins.size
  def numScalarOut = souts.size 

  vins.foreach { _.cu = this }
}
object ComputeUnit {
  def apply(regs:List[Reg], srams:List[SRAM], ctrs:List[Counter],  sins:List[ScalarIn],
    souts:List[ScalarOut], vins:List[InBus[ComputeUnit]], vout:OutBus[ComputeUnit], 
    stages:List[Stage], ctrlBox:CtrlBox)(implicit spade:Spade) = {
    new ComputeUnit(regs, srams, ctrs, sins, souts, vins, vout, stages, ctrlBox)
  }
}

/* A spetial type of CU used for memory loader/storer */
class TileTransfer(regs:List[Reg], srams:List[SRAM], ctrs:List[Counter], sins:List[ScalarIn], 
  souts:List[ScalarOut], override val vins:List[InBus[TileTransfer]], 
  override val vout:OutBus[TileTransfer], stages:List[Stage], ctrlBox:CtrlBox)(implicit spade:Spade) 
extends ComputeUnit(regs, srams, ctrs, sins, souts, vins, vout, stages, ctrlBox) {
  override val typeStr = "tt"
}
object TileTransfer {
  def apply(regs:List[Reg], srams:List[SRAM], ctrs:List[Counter],  sins:List[ScalarIn],
    souts:List[ScalarOut], vins:List[InBus[TileTransfer]], vout:OutBus[TileTransfer], 
    stages:List[Stage], ctrlBox:CtrlBox)(implicit spade:Spade) = {
    new TileTransfer(regs, srams, ctrs, sins, souts, vins, vout, stages, ctrlBox)
  }
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox(vins:List[InBus[SwitchBox]], vouts:List[OutBus[SwitchBox]])(implicit spade:Spade) extends Node with NetworkElement {
  override val typeStr = "sb"
  vins.foreach { _.src(this) }
  vouts.foreach { _.src(this) }
}
object SwitchBoxes {
  def apply(numRow:Int, numCol:Int, numLanes:Int)(implicit spade:Spade) = {
    List.fill(numRow, numCol) (SwitchBox(InBuses(6, numLanes), OutBuses(6, numLanes)))
  }
}

trait NetworkElement {
  val vins:List[InBus[_]]
  val vouts:List[OutBus[_]]
}

/* 
 * An input port of a module that can be recofigured to other's output ports
 * fanIns stores the list of ports the input port can configured to  
 * */

trait IO[S] extends Node {
  var _src:Option[S] = None
  def src = _src
  def src(s:S):this.type = { _src = Some(s); this }
}

/* Input pin. Can only connects to output of the same level */
trait Input[P<:Link, S] extends IO[S] { 
  type O = Output[P, _]
  // List of connections that can map to
  val fanIns = ListBuffer[O]()
  def connect(n:O):Unit = fanIns += n
  def <==(n:O) = connect(n)
  def <==(ns:List[O]) = ns.foreach(n => connect(n))
  def ms = s"${this}=mp[${fanIns.mkString(",")}]"
  def canFrom(n:O):Boolean = fanIns.contains(n)
}
/* Output pin. Can only connects to input of the same level */
trait Output[P<:Link, S] extends IO[S] { 
  type I = Input[P, _]
  val fanOuts = ListBuffer[I]()
  def connectedTo(n:I):Unit = fanOuts += n
  def mt = s"${this}=mt[${fanOuts.mkString(",")}]" 
  def canTo(n:I):Boolean = fanOuts.contains(n)
} 

trait Link extends Node
/* Three types of pin */
/* Bit level IO pin */
trait Wire extends Link
/* Word level IO pin */
trait Port extends Link
/* Bus level IO pin */
trait Bus extends Link

case class InWire[S]()(implicit spade:Spade) extends Wire with Input[Wire, S] {
  override val typeStr = "iw"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
}
object InWire {
  def apply[S](s:S)(implicit spade:Spade):InWire[S] = InWire().src(s)
  def apply[S](s:S, sf: =>String)(implicit spade:Spade):InWire[S] = new InWire[S]() {
    override def toString = sf
  }.src(s)
}
case class OutWire[S]()(implicit spade:Spade) extends Wire with Output[Wire, S] { 
  override val typeStr = "ow"
}
object OutWire {
  def apply[S](s:S)(implicit spade:Spade):OutWire[S] = OutWire().src(s)
  def apply[S](s:S, sf: =>String)(implicit spade:Spade):OutWire[S] = new OutWire[S]() {
    override def toString = sf
  }.src(s)
}

case class InPort[S]()(implicit spade:Spade) extends Port with Input[Port, S] {
  override val typeStr = "ip"
  def <==(r:PipeReg):Unit = connect(r.out)
  override def connect(n:O):Unit = {super.connect(n); n.connectedTo(this)}

}
object InPort {
  def apply[S](s:S)(implicit spade:Spade):InPort[S] = InPort[S]().src(s)
  def apply[S](s:S, sf: =>String)(implicit spade:Spade):InPort[S] = new InPort[S]() {
    override def toString = sf
  }.src(s)
}
/*
 * An output port of a module.
 * src is a pointer to the module
 * */
case class OutPort[S]()(implicit spade:Spade) extends Port with Output[Port, S] { 
  override val typeStr = "op"
}
object OutPort {
  def apply[S](s:S)(implicit spade:Spade):OutPort[S] = OutPort().src(s)
  def apply[S](s:S, sf: =>String)(implicit spade:Spade):OutPort[S] = new OutPort[S]() {
    override def toString = sf
  }.src(s)
}
trait RMPort extends Port {
  val mappedRegs = Set[Reg]()
  def mappedTo(reg:Reg) = { mappedRegs += reg }
  def isMappedTo(reg:Reg) = mappedRegs.contains(reg)
}
trait RMInPort[S] extends InPort[S] with RMPort {
  override def ms = s"${super.ms} regs=[${mappedRegs.mkString(",")}]"
  override def connect(n:O):Unit = {
    super.connect(n)
    n.src match {
      case Some(PipeReg(stage, reg)) => mappedTo(reg)
      case _ =>
    }
  } 
}
object RMInPort {
  def apply[S](s:S)(implicit spade:Spade) = new RMInPort[S]{}.src(s)
  def apply[S](s:S, sf: =>String)(implicit spade:Spade) = new RMInPort[S] {
    override def toString = sf
  }.src(s)
}
trait RMOutPort[S] extends OutPort[S] with RMPort {
  override def mt = s"${super.mt} regs=[${mappedRegs.mkString(",")}]"
  override def connectedTo(n:I):Unit = {
    super.connectedTo(n)
    n.src match {
      case Some(PipeReg(stage, reg)) => mappedTo(reg)
      case _ =>
    }
  }
}
object RMOutPort {
  def apply[S](s:S, sf: =>String)(implicit spade:Spade) = new RMOutPort[S] {
    override def toString = sf
  }.src(s)
}
trait Stagable {
  val stage:Stage
}

case class InBus[S](outports:List[OutPort[InBus[S]]])(implicit spade:Spade) extends Bus with Input[Bus, S] {
  var cu:ComputeUnit = _
  override val typeStr = "ib"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
  outports.foreach(_.src(this))
  val viport:RMOutPort[InBus[S]] = outports(0).asInstanceOf[RMOutPort[InBus[S]]]
}
object InBus extends Metadata {
  def apply[S](idx:Int, numPort:Int)(implicit spade:Spade):InBus[S] = {
    val outports = List.tabulate(numPort) { i => 
      (if (i==0) new RMOutPort[InBus[S]] {}
      else OutPort[InBus[S]]()).index(i)
    }
    InBus[S](outports).index(idx)
  }
  def apply[S](idx:Int, ops:List[OutPort[InBus[S]]], s:S)(implicit spade:Spade):InBus[S] =
    InBus(ops).src(s).index(idx)
}
object InBuses {
  def apply[S](num:Int, numLanes:Int)(implicit spade:Spade) = 
    List.tabulate(num) { is => InBus[S](is, numLanes) }
}

case class OutBus[S](inports:List[InPort[OutBus[S]]])(implicit spade:Spade) 
  extends Bus with Output[Bus, S] {
  override val typeStr = "ob"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  inports.foreach(_.src(this))
  val voport:RMInPort[OutBus[S]] = inports(0).asInstanceOf[RMInPort[OutBus[S]]]
}
object OutBus {
  def apply[S](idx:Int, numPort:Int)(implicit spade:Spade):OutBus[S] = {
    val inports = List.tabulate(numPort) { i => 
      (if (i==0) new RMInPort[OutBus[S]]{}
      else InPort[OutBus[S]]()).index(i)
    }
    OutBus(inports).index(idx)
  }
  def apply[S](idx:Int, ips:List[InPort[OutBus[S]]], s:S)(implicit spade:Spade):OutBus[S] = {
    OutBus[S](ips).src(s).index(idx)
  }
}
object OutBuses {
  def apply[S](num:Int, numLanes:Int)(implicit spade:Spade) = 
    List.tabulate(num) { is => OutBus[S](is, numLanes) }
}

case class ConstVal(v:String)(implicit spade:Spade) extends Node {
  val out = OutPort(this, s"Const")
}
case class Const()(implicit spade:Spade) extends Node {
  val out = RMOutPort(this, s"Const")
}

trait LUT extends Node {
  val numIns:Int
}
case class EnLUT(numIns:Int)(implicit spade:Spade) extends LUT {
  override val typeStr = "enlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
}
object EnLUT extends Metadata {
  def apply(idx:Int, numIns:Int)(implicit spade:Spade):EnLUT = EnLUT(numIns).index(idx)
}
case class TokenOutLUT(implicit spade:Spade) extends LUT{
  override val typeStr = "tolut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override val numIns = 2
}
object TokenOutLUT extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):TokenOutLUT = TokenOutLUT().index(idx)
}
case class TokenDownLUT(numIns:Int)(implicit spade:Spade) extends LUT {
  override val typeStr = "tdlut"
}
object TokenDownLUT extends Metadata {
  def apply(idx:Int, numIns:Int)(implicit spade:Spade):TokenDownLUT = TokenDownLUT(numIns).index(idx)
}
case class UDCounter(implicit spade:Spade) extends Node {
  override val typeStr = "udlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  //val init = InPort(this, s"${this}.init")
  //val inc = InPort(this, s"${this}.inc")
  //val dec = InPort(this, s"${this}.dec")
  //val out = OutPort(this, s"${this}.out")
}
object UDCounter extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):UDCounter = UDCounter().index(idx)
}

case class CtrlBox(numCtrs:Int, numTokenIns:Int, numTokenOuts:Int)(implicit spade:Spade) extends Node {
  val tokenIns = List.tabulate(numTokenIns) {i => InPort().index(i) }
  val tokenOuts = List.tabulate(numTokenOuts) {i => OutPort().index(i) }
  val numEnLUTs = numCtrs
  val numUDCs = numEnLUTs
  val numTokOutLUTs = numTokenOuts - 1
  val udcs = List.tabulate(numUDCs) { i => UDCounter(i) }
  val tokDownLUT = TokenDownLUT(1 + numUDCs)
  val tokOutLUTs = List.tabulate(numTokOutLUTs) { i => TokenOutLUT(i) }
  val enLUTs = List.tabulate(numEnLUTs) { i => EnLUT(i, numUDCs) }
  def luts:List[LUT] = enLUTs ++ tokOutLUTs :+ tokDownLUT
}
