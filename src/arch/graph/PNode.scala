package pir.plasticine.graph

import pir.graph._

import scala.collection.mutable.ListBuffer

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
  val readPort = OutPort(this, s"${this}.rp")
  val writePort = InPort(this, s"${this}.wp")
  val readAddr = InPort(this, s"${this}.ra")
  val writeAddr = InPort(this, s"${this}.wa")
}

/** Physical Counter  */
case class Counter() extends Node {
  override val typeStr = "ctr"
  val min = InPort(this, s"${this}.min")
  val max = InPort(this, s"${this}.max")
  val step = InPort(this, s"${this}.step")
  val out = OutPort(this, s"${this}.out")
}

/** 1 mapping of pipeline register (1 row of reg for all stages) */
class Reg() extends Node {
  override val typeStr = "reg"
  val in = InPort(this, s"${this}.i") 
  val out = OutPort(this, s"${this}.o")
  def <=(r:Reg) = in.connect(r.out)
  def <=(n:OutPort) = in.connect(n)
  def <=(ns:List[OutPort]) = ns.foreach(n => in.connect(n))
}
object Reg {
  def apply() = new Reg()
}

trait ScalarBuffer extends Reg
case class ScalarIn() extends ScalarBuffer {
  override val typeStr = "si"
} 
case class ScalarOut() extends ScalarBuffer {
  override val typeStr = "so"
}

case class ComputeUnit(val pregs:List[Reg], val srams:List[SRAM], val ctrs:List[Counter], 
  val sins:List[ScalarIn], val souts:List[ScalarOut], val vins:List[InBus], val vout:OutBus) extends Node{
  override val typeStr = "cu"

  val reduce = OutPort(this, s"${this}.reduce")
  vins.foreach(_.src = Some(this))
  vout.src = Some(this)
  assert(vins.size>0, "ComputeUnit must have at least 1 vector input")

  def numPRs = pregs.size 
  def numCtrs = ctrs.size
  def numSRAMs = srams.size
  def numScalarIn = sins.size
  def numScalarOut = souts.size 
}

trait MemoryController extends ComputeUnit{
  override val typeStr = "mc"
}
object MemoryController {
  def apply(pregs:List[Reg], srams:List[SRAM], ctrs:List[Counter],  sins:List[ScalarIn],
    souts:List[ScalarOut], vins:List[InBus], vout:OutBus) = {
    new ComputeUnit(pregs, srams, ctrs, sins, souts, vins, vout) with MemoryController
  }
}

/* 
 * An input port of a module that can be recofigured to other's output ports
 * mapping stores the list of ports the input port can configured to  
 * */

trait Input {
  type O <: Output
  // List of connections that can map to
  val mapping = ListBuffer[O]()
  def connect(n:O) = { if (!mapping.contains(n)) mapping += n }
  def <=(n:O) = connect(n)
  def <=(ns:List[O]) = ns.foreach(n => connect(n))
  def ms = s"${this}=mp[${mapping.mkString(",")}]"
  def isConn(n:O) = mapping.contains(n)
}
trait Output {
  type I <: Input
  val mappedTo = ListBuffer[I]()
  def connectedTo(n:I) = if (!mappedTo.contains(n)) mappedTo += n
  def mt = s"${this}=mt[${mappedTo.mkString(",")}]" 
  def isConn(n:I) = mappedTo.contains(n)
} 

trait IO extends Node{
  var src:Option[Node] = None
}
trait Port extends IO
trait Bus extends IO

trait InPort extends Port with Input {
  type O = OutPort
  override val typeStr = "ip"
  def <=(r:Reg) = connect(r.out)
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
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
object Const extends OutPort {
  override def toString = "Const"
}

trait InBus extends Bus with Input {
  type O = OutBus
  override val typeStr = "ib"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
  val outports:List[OutPort]
  outports.foreach(_.src = Some(this))
}
object InBus {
  def apply(ops:List[OutPort]) = new {val outports = ops} with OutPort with InBus
  def apply(ops:List[OutPort], s:Node) = new {val outports = ops} with OutPort with InBus {src = Some(s)}
}

trait OutBus extends Bus with Output {
  type I = InBus
  override val typeStr = "ob"
  val inports:List[InPort]
  inports.foreach(_.src = Some(this))
}
object OutBus {
  def apply(ips:List[InPort]) = new {val inports = ips } with InPort with OutBus
  def apply(ips:List[InPort], s:Node) = new {val inports = ips} with InPort with OutBus {src = Some(s)}
}
