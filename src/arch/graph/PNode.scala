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
case class SRAM() extends Node{
  override val typeStr = "sram"
  val readPort = RMOutPort(this, s"${this}.rp")
  val writePort = RMInPort(this, s"${this}.wp")
  val readAddr = RMInPort(this, s"${this}.ra")
  val writeAddr = RMInPort(this, s"${this}.wa")
}

/** Physical Counter  */
case class Counter() extends Node {
  override val typeStr = "ctr"
  val min = InPort(this, s"${this}.min")
  val max = InPort(this, s"${this}.max")
  val step = InPort(this, s"${this}.step")
  val out = RMOutPort(this, s"${this}.out")
  val en = InWire(this, s"${this}.en")
  val sat = OutWire(this, s"${this}.sat")
  def isDep(c:Counter) = en.isConn(c.sat)
}

/** 1 fanIns of pipeline register (1 row of reg for all stages) */
case class Reg() extends Node {
  override val typeStr = "reg"
  //val mapping = Map[Stage, ListBuffer[RMPort]]() 
  //def mapTo (p:RMPort, stage:Stage) = {
  //  if (!mapping.contains(stage)) mapping += (stage -> ListBuffer[RMPort]())
  //  mapping(stage) += p
  //  p.mappedTo(this) 
  //}
  //def <-- (p:RMPort, stage:Stage) = mapTo(p, stage)
  //def <-- (ps:List[RMPort], stage:Stage) = ps.foreach(p => mapTo(p, stage))
  //def <-- (p:RMPort, stages:List[Stage]) = stages.foreach(stage => mapTo(p, stage))
  //def ms = s"mapping=[${mapping.mkString(",")}]"
}
case class PipeReg(stage:Stage, reg:Reg) extends Node {
  override val typeStr = "pr"
  val in = InPort(this, s"${this}.i") 
  val out = OutPort(this, s"${this}.o")
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

case class FuncUnit(numOprds:Int, ops:List[Op]) extends Node {
  override val typeStr = "fu"
  val operands = List.fill(numOprds) (new FUInPort(this)) 
  val out = new FUOutPort(this)
}

class Stage(val funcUnit:Option[FuncUnit], regs:List[Reg]) extends Node {
  val prs = Map[Reg, PipeReg]()
  regs.foreach { reg => prs += (reg -> PipeReg(this, reg)) }
  override val typeStr = "st"
}
trait EmptyStage extends Stage {
  override val typeStr = "etst"
}
object EmptyStage {
  def apply(regs:List[Reg]) = new Stage(None, regs) with EmptyStage
}
trait FUStage extends Stage {
  def fu:FuncUnit = funcUnit.get 
}
object FUStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op]):FUStage = 
    new Stage(Some(FuncUnit(numOprds, ops)), regs) with FUStage
}
trait ReduceStage extends FUStage {
  override val typeStr = "rdst"
}
object ReduceStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op]):ReduceStage = 
    new Stage(Some(FuncUnit(numOprds, ops)), regs) with ReduceStage
}
trait WAStage extends FUStage
object WAStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op]):WAStage = 
    new Stage(Some(FuncUnit(numOprds, ops)), regs) with WAStage 
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
  override val sins:List[ScalarIn], override val souts:List[ScalarOut], 
  override val vins:List[InBus], vout:OutBus, stages:List[Stage]) extends Controller{
  override val typeStr = "cu"
  override val vouts = List(vout)

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
    souts:List[ScalarOut], vins:List[InBus], vout:OutBus, stages:List[Stage]) = {
    new ComputeUnit(regs, srams, ctrs, sins, souts, vins, vout, stages) with TileTransfer
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
  def connect(n:O):Unit = { if (!fanIns.contains(n)) fanIns += n }
  def <==(n:O) = connect(n)
  def <==(ns:List[O]) = ns.foreach(n => connect(n))
  def ms = s"${this}=mp[${fanIns.mkString(",")}]"
  def isConn(n:O):Boolean = fanIns.contains(n)
}
trait Output {
  type I <: Input
  val fanOuts = ListBuffer[I]()
  def connectedTo(n:I):Unit = if (!fanOuts.contains(n)) fanOuts += n
  def mt = s"${this}=mt[${fanOuts.mkString(",")}]" 
  def isConn(n:I):Boolean = fanOuts.contains(n)
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
object Const extends OutPort { override def toString = "Const" }
trait RMPort extends Port {
  val mappedRegs = Set[Reg]()
  def mappedTo(reg:Reg) = { mappedRegs += reg }
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
//trait RegInPort extends InPort {
//  override def connect(n:O):Unit = {
//    if (!n.isInstanceOf[RMOutPort]) throw new Exception(s"Register can only connect to RMPort")
//    super.connect(n)
//  } 
//}
//object RegInPort {
//  def apply(s:Node, sf: =>String) = new Node with RegInPort {
//    src = Some(s)
//    override def toString = sf
//  }
//}
//trait RegOutPort extends OutPort {
//  override def connectedTo(n:I):Unit = {
//    if (!n.isInstanceOf[RMInPort]) throw new Exception(s"Register can only connect to RMPort")
//    super.connectedTo(n)
//  }
//}
//object RegOutPort {
//  def apply(s:Node, sf: =>String) = new Node with RegOutPort {
//    src = Some(s)
//    override def toString = sf
//  }
//}
class FUInPort(fu:FuncUnit) extends InPort {
  src = Some(fu)
  override def toString = s"${fu}.oprd${id}"
}
class FUOutPort(fu:FuncUnit) extends OutPort {
  src = Some(fu)
  override def toString = s"${fu}.out"
}

case class InBus(outports:List[BusOutPort]) extends Bus with Input {
  type O = OutBus
  override val typeStr = "ib"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
  outports.foreach(_.src = Some(this))
  val viport:RMOutPort = outports(0).asInstanceOf[RMOutPort]
}
object InBus {
  def apply(numPort:Int):InBus = {
    val outports = List.tabulate(numPort) { i => 
      if (i==0) new BusOutPort(i) with RMOutPort { src = Some(this) }
      else new BusOutPort(i) {src = Some(this)}
    }
    InBus(outports)
  }
  def apply(ops:List[BusOutPort], s:Node):InBus = new InBus(ops) {src = Some(s)}
}

case class OutBus(inports:List[BusInPort]) extends Bus with Output {
  type I = InBus
  override val typeStr = "ob"
  inports.foreach(_.src = Some(this))
  val voport:RMInPort = inports(0).asInstanceOf[RMInPort]
}
object OutBus {
  def apply(numPort:Int):OutBus = {
    val inports = List.tabulate(numPort) { i => 
      if (i==0) new BusInPort(i) with RMInPort { src = Some(this) }
      else new BusInPort(i) {src = Some(this)}
    }
    OutBus(inports)
  }
  def apply(ips:List[BusInPort], s:Node) = new OutBus(ips) {src = Some(s)}
}

case class BusInPort(idx:Int) extends InPort
case class BusOutPort(idx:Int) extends OutPort
