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
  def apply(idx:Int)(implicit spade:Spade):SRAM = {
    val s = SRAM()
    indexOf(s) = idx
    s
  }
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
  def apply(idx:Int)(implicit spade:Spade):Counter = {
    val s = Counter()
    indexOf(s) = idx
    s
  }
}

/* Logical register (1 row of pipeline registers for all stages) */
case class Reg(implicit spade:Spade) extends Node {
  override val typeStr = "reg"
}
object Reg extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):Reg = {
    val s = Reg()
    indexOf(s) = idx
    s
  }
}

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:Reg)(implicit spade:Spade) extends Node {
  override val typeStr = "pr"
  override def toString = s"pr(${stage},${reg})"
  val in = RegInPort(this, s"${this}.i") 
  val out = RegOutPort(this, s"${this}.o")
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
class ScalarBuffer(implicit spade:Spade) extends Node {
  val in = InPort(this, s"${this}.i") 
  val out = OutPort(this, s"${this}.o")
} 
/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarIn(outport:Option[BusOutPort])(implicit spade:Spade) extends ScalarBuffer {
  outport.foreach { this.in <== _ }
  override val typeStr = "si"
  override val out = RMOutPort(this, s"${this}.o")
  def inBus:InBus = outport.get.src.get.asInstanceOf[InBus]
  def idx = indexOf(outport.get)
} 
object ScalarIn {
  def apply(outport:BusOutPort)(implicit spade:Spade):ScalarIn = ScalarIn(Some(outport))
}
/* Scalar buffer between the last stage and the bus output. Output connects to 1 in port 
 * of the OutBus */
case class ScalarOut(inport:Option[BusInPort])(implicit spade:Spade) extends ScalarBuffer {
  inport.foreach( _ <== this.out )
  override val typeStr = "so"
  override val in = RMInPort(this, s"${this}.i")
  def outBus:OutBus = inport.get.src.get.asInstanceOf[OutBus]
  def idx = indexOf(inport.get)
}
object ScalarOut {
  def apply(inport:BusInPort)(implicit spade:Spade):ScalarOut = ScalarOut(Some(inport))
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
  val operands = List.fill(numOprds) (new FUInPort(this)) 
  val out = new FUOutPort(this)
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
trait Controller extends Node {
  val sins:List[ScalarIn]
  val souts:List[ScalarOut]
  val vins:List[InBus]
  val vouts:List[OutBus]
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(argIns:List[ScalarOut], argOuts:List[ScalarIn], 
               argInBuses:List[OutBus], argOutBuses:List[InBus])(implicit spade:Spade) extends Controller {
  override val sins:List[ScalarIn] = argOuts
  override val souts:List[ScalarOut] = argIns
  override val vins:List[InBus] = argOutBuses
  override val vouts:List[OutBus] = argInBuses
  val clk = OutWire(this, s"clk")
  def numArgIn = argIns.size
  def numArgOut = argOuts.size
}

/* ComputeUnit
 * */
case class ComputeUnit(regs:List[Reg], srams:List[SRAM], ctrs:List[Counter], 
  sins:List[ScalarIn], souts:List[ScalarOut], vins:List[InBus], vout:OutBus,
  stages:List[Stage], ctrlBox:CtrlBox)(implicit spade:Spade) extends Controller{
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
  vins.foreach(_.src = Some(this))
  vout.src = Some(this)
  assert(vins.size>0, "ComputeUnit must have at least 1 vector input")

  def numRegs = regs.size 
  def numCtrs = ctrs.size
  def numSRAMs = srams.size
  def numScalarIn = sins.size
  def numScalarOut = souts.size 

  vins.foreach { _.cu = this }
}

/* A spetial type of CU used for memory loader/storer */
trait TileTransfer extends ComputeUnit{
  override val typeStr = "tt"
}
object TileTransfer {
  def apply(regs:List[Reg], srams:List[SRAM], ctrs:List[Counter],  sins:List[ScalarIn],
    souts:List[ScalarOut], vins:List[InBus], vout:OutBus, stages:List[Stage], 
    ctrlBox:CtrlBox)(implicit spade:Spade) = {
    new ComputeUnit(regs, srams, ctrs, sins, souts, vins, vout, stages, ctrlBox) with TileTransfer
  }
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox(vins:List[InBus], vouts:List[OutBus])(implicit spade:Spade) 
  extends Node {
  override val typeStr = "sb"
  vins.foreach { _.src = Some(this) }
  vouts.foreach { _.src = Some(this) }
}
object SwitchBoxes {
  def apply(numRow:Int, numCol:Int, numLanes:Int)(implicit spade:Spade) = {
    List.fill(numRow, numCol) (SwitchBox(InBuses(6, numLanes), OutBuses(6, numLanes)))
  }
}

/* 
 * An input port of a module that can be recofigured to other's output ports
 * fanIns stores the list of ports the input port can configured to  
 * */

trait IO extends Node{
  var src:Option[Node] = None
}

/* Input pin. Can only connects to output of the same level */
trait Input[P<:IO] extends IO { this:P =>
  type O = Output[P]
  // List of connections that can map to
  val fanIns = ListBuffer[O]()
  def connect(n:O):Unit = fanIns += n
  def <==(n:O) = connect(n)
  def <==(ns:List[O]) = ns.foreach(n => connect(n))
  def ms = s"${this}=mp[${fanIns.mkString(",")}]"
  def canFrom(n:O):Boolean = fanIns.contains(n)
}
/* Output pin. Can only connects to input of the same level */
trait Output[P<:IO] extends IO { this:P =>
  type I = Input[P]
  val fanOuts = ListBuffer[I]()
  def connectedTo(n:I):Unit = fanOuts += n
  def mt = s"${this}=mt[${fanOuts.mkString(",")}]" 
  def canTo(n:I):Boolean = fanOuts.contains(n)
} 

/* Three types of pin */
/* Bit level IO pin */
trait Wire extends IO
/* Word level IO pin */
trait Port extends IO
/* Bus level IO pin */
trait Bus extends IO

trait InWire extends Wire with Input[Wire] {
  override val typeStr = "iw"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
}
object InWire {
  def apply()(implicit spade:Spade) = new Node with InWire
  def apply(s:Node)(implicit spade:Spade) = new Node with InWire {src = Some(s)}
  def apply(s:Node, sf: =>String)(implicit spade:Spade) = new Node with InWire {
    src = Some(s)
    override def toString = sf
  }
}
trait OutWire extends Wire with Output[Wire] { 
  override val typeStr = "ow"
}
object OutWire {
  def apply()(implicit spade:Spade) = new Node with OutWire
  def apply(s:Node)(implicit spade:Spade) = new Node with OutWire {src = Some(s)}
  def apply(s:Node, sf: =>String)(implicit spade:Spade) = new Node with OutWire {
    src = Some(s)
    override def toString = sf
  }
}

trait InPort extends Port with Input[Port] {
  override val typeStr = "ip"
  def <==(r:PipeReg):Unit = connect(r.out)
  override def connect(n:O):Unit = {super.connect(n); n.connectedTo(this)}
}
object InPort {
  def apply()(implicit spade:Spade) = new Node with InPort
  def apply(s:Node)(implicit spade:Spade) = new Node with InPort {src = Some(s)}
  def apply(s:Node, sf: =>String)(implicit spade:Spade) = new Node with InPort {
    src = Some(s)
    override def toString = sf
  }
}
/*
 * An output port of a module.
 * src is a pointer to the module
 * */
trait OutPort extends Port with Output[Port] { 
  override val typeStr = "op"
}
object OutPort {
  def apply()(implicit spade:Spade) = new Node with OutPort
  def apply(s:Node)(implicit spade:Spade) = new Node with OutPort {src = Some(s)}
  def apply(s:Node, sf: =>String)(implicit spade:Spade) = new Node with OutPort {
    src = Some(s)
    override def toString = sf
  }
}
trait RMPort extends Port {
  val mappedRegs = Set[Reg]()
  def mappedTo(reg:Reg) = { mappedRegs += reg }
  def isMappedTo(reg:Reg) = mappedRegs.contains(reg)
}
trait RMInPort extends InPort with RMPort {
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
  def apply(s:Node, sf: =>String)(implicit spade:Spade) = new Node with RMInPort {
    src = Some(s)
    override def toString = sf
  }
}
trait RMOutPort extends OutPort with RMPort {
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
  def apply(s:Node, sf: =>String)(implicit spade:Spade) = new Node with RMOutPort {
    src = Some(s)
    override def toString = sf
  }
}
trait Stagable {
  val stage:Stage
}
trait RegInPort extends RMInPort with Stagable
object RegInPort {
  def apply(s:PipeReg, sf: =>String)(implicit spade:Spade) = new Node with RegInPort {
    src = Some(s)
    override val stage = s.stage
    override def toString = sf
  }
}
trait RegOutPort extends RMOutPort with Stagable
object RegOutPort {
  def apply(s:PipeReg, sf: =>String)(implicit spade:Spade) = new Node with RegOutPort {
    src = Some(s)
    override val stage = s.stage
    override def toString = sf
  }
}
class FUInPort(fu:FuncUnit)(implicit spade:Spade) extends RMInPort with Stagable{
  src = Some(fu)
  override val stage = fu.stage
  override def toString = s"${fu}.oprd${id}"
}
class FUOutPort(fu:FuncUnit)(implicit spade:Spade) extends OutPort {
  src = Some(fu)
  override def toString = s"${fu}.out"
}

case class InBus(outports:List[BusOutPort])(implicit spade:Spade) extends Bus with Input[Bus] {
  var cu:ComputeUnit = _
  override val typeStr = "ib"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
  outports.foreach(_.src = Some(this))
  val viport:RMOutPort = outports(0).asInstanceOf[RMOutPort]
}
object InBus extends Metadata {
  def apply(idx:Int, numPort:Int)(implicit spade:Spade):InBus = {
    val outports = List.tabulate(numPort) { i => 
      if (i==0) new BusOutPort(i) with RMOutPort { src = Some(this) }
      else new BusOutPort(i) {src = Some(this)}
    }
    val ib = InBus(outports)
    indexOf(ib) = idx
    ib
  }
  def apply(idx:Int, ops:List[BusOutPort], s:Node)(implicit spade:Spade):InBus = {
    val ib = new InBus(ops) {src = Some(s) }
    indexOf(ib) = idx
    ib
  }
}
object InBuses {
  def apply(num:Int, numLanes:Int)(implicit spade:Spade) = List.tabulate(num) { is => InBus(is, numLanes) }
}

case class OutBus(inports:List[BusInPort])(implicit spade:Spade) extends Bus with Output[Bus] {
  override val typeStr = "ob"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  inports.foreach(_.src = Some(this))
  val voport:RMInPort = inports(0).asInstanceOf[RMInPort]
}
object OutBus extends Metadata {
  def apply(idx:Int, numPort:Int)(implicit spade:Spade):OutBus = {
    val inports = List.tabulate(numPort) { i => 
      if (i==0) new BusInPort(i) with RMInPort { src = Some(this) }
      else new BusInPort(i) {src = Some(this)}
    }
    val ob = OutBus(inports)
    indexOf(ob) = idx
    ob
  }
  def apply(idx:Int, ips:List[BusInPort], s:Node)(implicit spade:Spade) = {
    val ob = new OutBus(ips) {src = Some(s)}
    indexOf(ob) = idx
    ob
  }
}
object OutBuses {
  def apply(num:Int, numLanes:Int)(implicit spade:Spade) = List.tabulate(num) { is => OutBus(is, numLanes) }
}

case class BusInPort(implicit spade:Spade) extends InPort with Metadata {
  def this(idx:Int)(implicit spade:Spade) = {
    this()
    indexOf.update(this,idx)(spade)
  }
}
object BusInPort extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):BusInPort = new BusInPort(idx) 
  def apply(idx:Int, s:Node)(implicit spade:Spade):BusInPort = { 
    val bip = BusInPort(idx); bip.src = Some(s); bip
  }
}
case class BusOutPort(implicit spade:Spade) extends OutPort with Metadata {
  def this(idx:Int)(implicit spade:Spade) = {
    this()
    indexOf.update(this,idx)(spade)
  }
}
object BusOutPort extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):BusOutPort = new BusOutPort(idx) 
  def apply(idx:Int, s:Node)(implicit spade:Spade):BusOutPort = { 
    val bop = BusOutPort(idx); bop.src = Some(s); bop
  }
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
  def apply(idx:Int, numIns:Int)(implicit spade:Spade):EnLUT = {
    val lut = EnLUT(numIns)
    indexOf(lut) = idx
    lut
  }
}
case class TokenOutLUT(implicit spade:Spade) extends LUT{
  override val typeStr = "tolut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override val numIns = 2
}
object TokenOutLUT extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):TokenOutLUT = {
    val lut = TokenOutLUT()
    indexOf(lut) = idx
    lut
  }
}
case class TokenDownLUT(numIns:Int)(implicit spade:Spade) extends LUT {
  override val typeStr = "tdlut"
}
object TokenDownLUT extends Metadata {
  def apply(idx:Int, numIns:Int)(implicit spade:Spade):TokenDownLUT = {
    val lut = TokenDownLUT(numIns)
    indexOf(lut) = idx
    lut
  }
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
  def apply(idx:Int)(implicit spade:Spade):UDCounter = {
    val lut = UDCounter()
    indexOf(lut) = idx
    lut
  }
}

case class CtrlBox(numCtrs:Int, numTokenIns:Int, numTokenOuts:Int)(implicit spade:Spade) extends Node {
  val tokenIns = List.tabulate(numTokenIns) {i => BusInPort(i)}
  val tokenOuts = List.tabulate(numTokenOuts) {i => BusOutPort(i)}
  val numEnLUTs = numCtrs
  val numUDCs = numEnLUTs
  val numTokOutLUTs = numTokenOuts - 1
  val udcs = List.tabulate(numUDCs) { i => UDCounter(i) }
  val tokDownLUT = TokenDownLUT(1 + numUDCs)
  val tokOutLUTs = List.tabulate(numTokOutLUTs) { i => TokenOutLUT(i) }
  val enLUTs = List.tabulate(numEnLUTs) { i => EnLUT(i, numUDCs) }
  def luts:List[LUT] = enLUTs ++ tokOutLUTs :+ tokDownLUT
}
