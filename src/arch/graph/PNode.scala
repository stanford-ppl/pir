package pir.plasticine.graph

import pir.graph._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

class Node { 
  val id : Int = Node.nextId
  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  val typeStr = this.getClass().getSimpleName()
  override def toString = s"${typeStr}${id}" 
}
object Node {
  var nextSym = 0
  def nextId = {val temp = nextSym; nextSym +=1; temp}
}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM(idx:Int) extends Node{
  override val typeStr = "sram"
  override def toString = s"${super.toString}[${idx}]"
  val readPort = RMOutPort(this, s"${this}.rp")
  val writePort = RMInPort(this, s"${this}.wp")
  val readAddr = RMInPort(this, s"${this}.ra")
  val writeAddr = RMInPort(this, s"${this}.wa")
}

/** Physical Counter  */
case class Counter(idx:Int) extends Node {
  override val typeStr = "ctr"
  override def toString = s"${super.toString}[${idx}]"
  val min = InPort(this, s"${this}.min")
  val max = InPort(this, s"${this}.max")
  val step = InPort(this, s"${this}.step")
  val out = RMOutPort(this, s"${this}.out")
  val en = InWire(this, s"${this}.en")
  val done = OutWire(this, s"${this}.done")
  def isDep(c:Counter) = en.canFrom(c.done)
}

/** 1 fanIns of pipeline register (1 row of reg for all stages) */
case class Reg(idx:Int) extends Node {
  override val typeStr = "reg"
}
case class PipeReg(stage:Stage, reg:Reg) extends Node {
  override val typeStr = "pr"
  override def toString = s"pr(${stage},${reg})"
  val in = RegInPort(this, s"${this}.i") 
  val out = RegOutPort(this, s"${this}.o")
}

trait ScalarBuffer extends Node {
  val in = InPort(this, s"${this}.i") 
  val out = OutPort(this, s"${this}.o")
} 
/* If ScalarIn is connecting to the vector network, its input connects to 1 out port of the 
 * InBus */
case class ScalarIn(outport:Option[BusOutPort]) extends ScalarBuffer {
  outport.foreach { this.in <== _ }
  override val typeStr = "si"
  override val out = RMOutPort(this, s"${this}.o")
  def inBus:InBus = outport.get.src.get.asInstanceOf[InBus]
  def idx = outport.get.idx
} 
object ScalarIn {
  def apply(outport:BusOutPort):ScalarIn = ScalarIn(Some(outport))
}
/* If ScalarOut is connecting to the vector network, its output connects to 1 in port of the
 * OutBus */
case class ScalarOut(inport:Option[BusInPort]) extends ScalarBuffer {
  inport.foreach( _ <== this.out )
  override val typeStr = "so"
  override val in = RMInPort(this, s"${this}.i")
  def outBus:OutBus = inport.get.src.get.asInstanceOf[OutBus]
  def idx = inport.get.idx
}
object ScalarOut {
  def apply(inport:BusInPort):ScalarOut = ScalarOut(Some(inport))
}
/* ScalarOut of TileTransfer CU, whos AddrOut has dedicated scalar network */
trait AddrOut extends ScalarOut {
  override val typeStr = "ado"
}
object AddrOut {
  def apply() = new ScalarOut(None) with AddrOut
}

case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage) extends Node {
  override val typeStr = "fu"
  val operands = List.fill(numOprds) (new FUInPort(this)) 
  val out = new FUOutPort(this)
}

class Stage(regs:List[Reg]) extends Node {
  val funcUnit:Option[FuncUnit] = None
  val prs = Map[Reg, PipeReg]()
  regs.foreach { reg => prs += (reg -> PipeReg(this, reg)) }
  var pre:Option[Stage] = _ // Set up in controller
  var next:Option[Stage] = _
  var idx:Int = _
  def before(s:Stage) = idx < s.idx
  def after(s:Stage) = idx > s.idx
  override val typeStr = "st"
  override def toString = s"${super.toString}[${idx}]"
}
trait EmptyStage extends Stage {
  override val typeStr = "etst"
}
object EmptyStage {
  def apply(regs:List[Reg]) = new Stage(regs) with EmptyStage
}
trait FUStage extends Stage {
  def fu:FuncUnit = funcUnit.get 
}
object FUStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op]):FUStage = 
    new Stage(regs) with FUStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}
trait ReduceStage extends FUStage {
  override val typeStr = "rdst"
}
object ReduceStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op]):ReduceStage = 
    new Stage(regs) with ReduceStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}
trait WAStage extends FUStage {
  override val typeStr = "wast"
}
object WAStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op]):WAStage = 
    new Stage(regs) with WAStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}

trait Controller extends Node {
  val sins:List[ScalarIn]
  val souts:List[ScalarOut]
  val vins:List[InBus]
  val vouts:List[OutBus]
}

case class Top(argIns:List[ScalarOut], argOuts:List[ScalarIn], 
               argInBuses:List[OutBus], argOutBuses:List[InBus]) extends Controller {
  override val sins:List[ScalarIn] = argOuts
  override val souts:List[ScalarOut] = argIns
  override val vins:List[InBus] = argOutBuses
  override val vouts:List[OutBus] = argInBuses
  val clk = OutWire(this, s"clk")
  def numArgIn = argIns.size
  def numArgOut = argOuts.size
}

case class ComputeUnit(regs:List[Reg], srams:List[SRAM], ctrs:List[Counter], 
  sins:List[ScalarIn], souts:List[ScalarOut], vins:List[InBus], vout:OutBus,
  stages:List[Stage], ctrlBox:CtrlBox) extends Controller{
  override val typeStr = "cu"
  override val vouts = List(vout)

  for (i <- 0 until stages.size) {
    stages(i).pre = if (i!=0) Some(stages(i-1)) else None
    stages(i).next = if (i!=stages.size-1) Some(stages(i+1)) else None
    stages(i).idx = i-1 // Empty stage is -1
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
}

trait TileTransfer extends ComputeUnit{
  override val typeStr = "tt"
}
object TileTransfer {
  def apply(regs:List[Reg], srams:List[SRAM], ctrs:List[Counter],  sins:List[ScalarIn],
    souts:List[ScalarOut], vins:List[InBus], vout:OutBus, stages:List[Stage], 
    ctrlBox:CtrlBox) = {
    new ComputeUnit(regs, srams, ctrs, sins, souts, vins, vout, stages, ctrlBox) with TileTransfer
  }
}

/* 
 * An input port of a module that can be recofigured to other's output ports
 * fanIns stores the list of ports the input port can configured to  
 * */

trait Input {
  type O <: Output
  // List of connections that can map to
  val fanIns = ListBuffer[O]()
  def connect(n:O):Unit = fanIns += n
  def <==(n:O) = connect(n)
  def <==(ns:List[O]) = ns.foreach(n => connect(n))
  def ms = s"${this}=mp[${fanIns.mkString(",")}]"
  def canFrom(n:O):Boolean = fanIns.contains(n)
}
trait Output {
  type I <: Input
  val fanOuts = ListBuffer[I]()
  def connectedTo(n:I):Unit = fanOuts += n
  def mt = s"${this}=mt[${fanOuts.mkString(",")}]" 
  def canTo(n:I):Boolean = fanOuts.contains(n)
} 

trait IO extends Node{
  var src:Option[Node] = None
  def connTo(n:Node) = src.fold(false){_ == n}
}

trait Control

trait Wire extends IO with Control
trait Port extends IO
trait Bus extends IO

trait InWire extends Wire with Input {
  type O = OutWire
  override val typeStr = "iw"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
}
object InWire {
  def apply() = new Node with InWire
  def apply(s:Node) = new Node with InWire {src = Some(s)}
  def apply(s:Node, sf: =>String) = new Node with InWire {
    src = Some(s)
    override def toString = sf
  }
}
trait OutWire extends Port with Output { 
  type I = InWire
  override val typeStr = "ow"
}
object OutWire {
  def apply() = new Node with OutWire
  def apply(s:Node) = new Node with OutWire {src = Some(s)}
  def apply(s:Node, sf: =>String) = new Node with OutWire {
    src = Some(s)
    override def toString = sf
  }
}

trait InPort extends Port with Input {
  type O = OutPort
  override val typeStr = "ip"
  def <==(r:PipeReg):Unit = connect(r.out)
  override def connect(n:O):Unit = {super.connect(n); n.connectedTo(this)}
}
object InPort {
  def apply() = new Node with InPort
  def apply(s:Node) = new Node with InPort {src = Some(s)}
  def apply(s:Node, sf: =>String) = new Node with InPort {
    src = Some(s)
    override def toString = sf
  }
}
/*
 * An output port of a module.
 * src is a pointer to the module
 * */
trait OutPort extends Port with Output { 
  type I = InPort
  override val typeStr = "op"
}
object OutPort {
  def apply() = new Node with OutPort
  def apply(s:Node) = new Node with OutPort {src = Some(s)}
  def apply(s:Node, sf: =>String) = new Node with OutPort {
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
  def apply(s:Node, sf: =>String) = new Node with RMInPort {
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
  def apply(s:Node, sf: =>String) = new Node with RMOutPort {
    src = Some(s)
    override def toString = sf
  }
}
trait Stagable {
  val stage:Stage
}
trait RegInPort extends RMInPort with Stagable
object RegInPort {
  def apply(s:PipeReg, sf: =>String) = new Node with RegInPort {
    src = Some(s)
    override val stage = s.stage
    override def toString = sf
  }
}
trait RegOutPort extends RMOutPort with Stagable
object RegOutPort {
  def apply(s:PipeReg, sf: =>String) = new Node with RegOutPort {
    src = Some(s)
    override val stage = s.stage
    override def toString = sf
  }
}
class FUInPort(fu:FuncUnit) extends RMInPort with Stagable{
  src = Some(fu)
  override val stage = fu.stage
  override def toString = s"${fu}.oprd${id}"
}
class FUOutPort(fu:FuncUnit) extends OutPort {
  src = Some(fu)
  override def toString = s"${fu}.out"
}

case class InBus(idx:Int, outports:List[BusOutPort]) extends Bus with Input {
  type O = OutBus
  override val typeStr = "ib"
  override def toString = s"${super.toString}[${idx}]"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
  outports.foreach(_.src = Some(this))
  val viport:RMOutPort = outports(0).asInstanceOf[RMOutPort]
}
object InBus {
  def apply(idx:Int, numPort:Int):InBus = {
    val outports = List.tabulate(numPort) { i => 
      if (i==0) new BusOutPort(i) with RMOutPort { src = Some(this) }
      else new BusOutPort(i) {src = Some(this)}
    }
    InBus(idx, outports)
  }
  def apply(idx:Int, ops:List[BusOutPort], s:Node):InBus = new InBus(idx, ops) {src = Some(s)}
}

case class OutBus(idx:Int, inports:List[BusInPort]) extends Bus with Output {
  type I = InBus
  override val typeStr = "ob"
  override def toString = s"${super.toString}[${idx}]"
  inports.foreach(_.src = Some(this))
  val voport:RMInPort = inports(0).asInstanceOf[RMInPort]
}
object OutBus {
  def apply(idx:Int, numPort:Int):OutBus = {
    val inports = List.tabulate(numPort) { i => 
      if (i==0) new BusInPort(i) with RMInPort { src = Some(this) }
      else new BusInPort(i) {src = Some(this)}
    }
    OutBus(idx, inports)
  }
  def apply(idx:Int, ips:List[BusInPort], s:Node) = new OutBus(idx, ips) {src = Some(s)}
}

case class BusInPort(idx:Int) extends InPort
case class BusOutPort(idx:Int) extends OutPort

case class Const(v:String) extends Node {
  val out = OutPort(this, s"Const")
}
object Const extends Node { 
  override def toString = "Const"
  val out = RMOutPort(this, s"Const")
}

trait LUT extends Node {
  val numIns:Int
}
case class EnLUT(idx:Int, numIns:Int) extends LUT {
  override val typeStr = "enlut"
  override def toString = s"${super.toString}[${idx}]"
}
case class TokenOutLUT(idx:Int) extends LUT{
  override val typeStr = "tolut"
  override def toString = s"${super.toString}[${idx}]"
  override val numIns = 2
}
case class TokenDownLUT(numIns:Int) extends LUT {
  override val typeStr = "tdlut"
}
case class UDCounter(idx:Int) extends Node {
  override val typeStr = "udlut"
  override def toString = s"${super.toString}[${idx}]"
  //val init = InPort(this, s"${this}.init")
  //val inc = InPort(this, s"${this}.inc")
  //val dec = InPort(this, s"${this}.dec")
  //val out = OutPort(this, s"${this}.out")
}

case class CtrlBox(numCtrs:Int, numTokenIns:Int, numTokenOuts:Int) extends Node {
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
