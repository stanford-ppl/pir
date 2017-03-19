package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.codegen._
import pir.plasticine.main._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

/* 
 * An input port of a module that can be recofigured to other's output ports
 * fanIns stores the list of ports the input port can configured to  
 * */
trait IO[P<:LinkType, +S<:Node] extends Node {
  val src:S
  def isConnected: Boolean
  def disconnect:Unit
}

/* Input pin. Can only connects to output of the same level */
trait Input[P<:LinkType, +S<:Node] extends IO[P, S] { 
  type O <: Output[P, Node]
  // List of connections that can map to
  val fanIns = ListBuffer[O]()
  def connect(n:O):Unit = { fanIns += n }
  def <==(n:O) = { connect(n) }
  def <==(ns:List[O]) = ns.foreach(n => connect(n))
  def ms = s"${this}=mp[${fanIns.mkString(",")}]"
  def canFrom(n:O):Boolean = fanIns.contains(n)
  def isConnected = fanIns.size!=0
  def disconnect = fanIns.clear
}
/* Output pin. Can only connects to input of the same level */
trait Output[P<:LinkType, +S<:Node] extends IO[P, S] { 
  type I <: Input[P, Node]
  val fanOuts = ListBuffer[I]()
  def connectedTo(n:I):Unit = fanOuts += n
  def ==>(n:I):Unit = { n.connect(this.asInstanceOf[n.O]) }
  def ==>(ns:List[I]):Unit = ns.foreach { n => ==>(n) }
  def mt = s"${this}=mt[${fanOuts.mkString(",")}]" 
  def canTo(n:I):Boolean = fanOuts.contains(n)
  def isConnected = fanOuts.size!=0
  def disconnect = fanOuts.clear
} 

trait LinkType extends Node
/* Three types of pin */
/* Bit level IO pin */
trait Wire extends LinkType
/* Word level IO pin */
trait Port extends LinkType {
  def spade:Spade
  lazy val wordWidth:Int = spade.wordWidth
}
/* Bus level IO pin */
trait Bus extends LinkType {
  def spade:Spade
  lazy val wordWidth:Int = spade.wordWidth
  lazy val numLanes:Int = spade.numLanes
}

case class InWire[+S<:Node](src:S)(implicit spade:Spade) extends Wire with Input[Wire, S] {
  override type O = OutWire[Node]
  override val typeStr = "iw"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
}
object InWire {
  def apply[S<:Node](s:S, sf: =>String)(implicit spade:Spade):InWire[S] = new InWire[S](s) {
    override def toString = sf
  }
}
case class OutWire[+S<:Node](src:S)(implicit spade:Spade) extends Wire with Output[Wire, S] { 
  override type I = InWire[Node]
  override val typeStr = "ow"
}
object OutWire {
  def apply[S<:Node](s:S, sf: =>String)(implicit spade:Spade):OutWire[S] = new OutWire[S](s) {
    override def toString = sf
  }
}

class InPort[+S<:Node](override val src:S)(implicit val spade:Spade) extends Port with Input[Port, S] {
  override type O = OutPort[Node]
  override val typeStr = "ip"
  def <==(r:PipeReg):Unit = connect(r.out)
  override def connect(n:O):Unit = {super.connect(n); n.connectedTo(this)}

}
object InPort {
  def apply[S<:Node](s:S)(implicit spade:Spade) = new InPort(s) 
  def apply[S<:Node](s:S, sf: =>String)(implicit spade:Spade):InPort[S] = new InPort[S](s) {
    override def toString = sf
  }
}
/*
 * An output port of a module.
 * src is a pointer to the module
 * */
class OutPort[+S<:Node](override val src:S)(implicit val spade:Spade) extends Port with Output[Port, S] { 
  override type I = InPort[Node]
  override val typeStr = "op"
}
object OutPort {
  def apply[S<:Node](s:S)(implicit spade:Spade):OutPort[S] = new OutPort(s)
  def apply[S<:Node](s:S, sf: =>String)(implicit spade:Spade):OutPort[S] = new OutPort[S](s) {
    override def toString = sf
  }
}
trait RMPort extends Port {
  val mappedRegs = Set[Reg]()
  def mappedTo(reg:Reg) = { mappedRegs += reg }
  def isMappedTo(reg:Reg) = mappedRegs.contains(reg)
}
class RMInPort[+S<:Node](s:S)(implicit spade:Spade) extends InPort[S](s) with RMPort {
  override def ms = s"${super.ms} regs=[${mappedRegs.map(r => DotCodegen.quote(r, spade)).mkString(",")}]"
  override def connect(n:O):Unit = {
    super.connect(n)
    n.src match {
      case PipeReg(stage, reg) => mappedTo(reg)
      case _ =>
    }
  } 
}
object RMInPort {
  def apply[S<:Node](s:S)(implicit spade:Spade) = new RMInPort[S](s)
  def apply[S<:Node](s:S, sf: =>String)(implicit spade:Spade) = new RMInPort[S](s) {
    override def toString = sf
  }
}
class RMOutPort[+S<:Node](s:S)(implicit spade:Spade) extends OutPort[S](s) with RMPort {
  override def mt = s"${super.mt} regs=[${mappedRegs.map(r => DotCodegen.quote(r, spade)).mkString(",")}]"
  override def connectedTo(n:I):Unit = {
    super.connectedTo(n)
    n.src match {
      case PipeReg(stage, reg) => mappedTo(reg)
      case _ =>
    }
  }
}
object RMOutPort {
  def apply[S<:Node](s:S)(implicit spade:Spade) = new RMOutPort[S](s) 
  def apply[S<:Node](s:S, sf: =>String)(implicit spade:Spade) = new RMOutPort[S](s) {
    override def toString = sf
  }
}
trait Stagable {
  val stage:Stage
}

class InBus[+S<:NetworkElement](override val src:S, numPort:Int)(implicit val spade:Spade) extends Bus with Input[Bus, S] {
  override type O = OutBus[NetworkElement]
  val outports = List.tabulate(numPort) { i => 
    (if (i==0) new RMOutPort[InBus[S]](this)
    else OutPort[InBus[S]](this)).index(i)
  }
  override val typeStr = "ib"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
  val viport:RMOutPort[InBus[S]] = outports(0).asInstanceOf[RMOutPort[InBus[S]]]
}
object InBus extends Metadata {
  def apply[S<:NetworkElement](src:S, numPort:Int)(implicit spade:Spade):InBus[S] = {
    new InBus[S](src, numPort)
  }
}
object InBuses {
  def apply[S<:NetworkElement](src:S, num:Int, numPort:Int)(implicit spade:Spade) = 
    List.tabulate(num) { is => InBus[S](src, numPort) }
}

class OutBus[+S<:NetworkElement](override val src:S, numPort:Int)(implicit val spade:Spade) extends Bus with Output[Bus, S] {
  override type I = InBus[NetworkElement]
  val inports = List.tabulate(numPort) { i => 
    (if (i==0) new RMInPort[OutBus[S]](this)
    else InPort[OutBus[S]](this)).index(i)
  }
  override val typeStr = "ob"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val voport:RMInPort[OutBus[S]] = inports(0).asInstanceOf[RMInPort[OutBus[S]]]
}
object OutBus {
  def apply[S<:NetworkElement](src:S, numPort:Int)(implicit spade:Spade):OutBus[S] = {
    new OutBus(src, numPort)
  }
}
object OutBuses {
  def apply[S<:NetworkElement](src:S, num:Int, numLanes:Int)(implicit spade:Spade) = 
    List.tabulate(num) { is => OutBus[S](src, numLanes) }
}

trait GridIO[+NE<:NetworkElement] extends Node {
  private val inMap = Map[String, ListBuffer[InBus[NetworkElement]]]()
  private val outMap = Map[String, ListBuffer[OutBus[NetworkElement]]]()

  def src:NE
  def inBuses(num:Int, width:Int)(implicit spade:Spade):List[InBus[NE]] = InBuses(src, num, width)
  def outBuses(num:Int, width:Int)(implicit spade:Spade):List[OutBus[NE]] = OutBuses(src, num, width)
  def addInAt(dir:String, num:Int, width:Int)(implicit spade:Spade):List[InBus[NE]] = { 
    val ibs = inBuses(num, width)
    ibs.zipWithIndex.foreach { case (ib, i) => ib }
    inMap.getOrElseUpdate(dir, ListBuffer.empty) ++= ibs
    ibs
  }
  def addOutAt(dir:String, num:Int, width:Int)(implicit spade:Spade):List[OutBus[NE]] = {
    val obs = outBuses(num, width)
    obs.zipWithIndex.foreach { case (ob, i) => ob }
    outMap.getOrElseUpdate(dir, ListBuffer.empty) ++= obs
    obs
  }
  def addIOAt(dir:String, num:Int, width:Int)(implicit spade:Spade):NE = {
    addInAt(dir,num, width)
    addOutAt(dir,num, width)
    src
  }
  def addIns(num:Int, width:Int)(implicit spade:Spade):NE = { 
    addInAt("N", num, width)
    src
  }
  def addOuts(num:Int, width:Int)(implicit spade:Spade):NE = {
    addOutAt("N", num, width)
    src
  }
  def inAt(dir:String):List[InBus[NE]] = { inMap.getOrElse(dir, ListBuffer.empty).toList.asInstanceOf[List[InBus[NE]]] }
  def outAt(dir:String):List[OutBus[NE]] = { outMap.getOrElse(dir, ListBuffer.empty).toList.asInstanceOf[List[OutBus[NE]]] }
  def ins:List[InBus[NE]] = GridIO.eightDirections.flatMap { dir => inAt(dir) } 
  def outs:List[OutBus[NE]] = GridIO.eightDirections.flatMap { dir => outAt(dir) }  
  def io(in:InBus[NetworkElement]) = {
    val dirs = inMap.filter{ case (dir, l) => l.contains(in) }
    assert(dirs.size==1)
    val (dir, list) = dirs.head
    s"${dir.toLowerCase}_${list.indexOf(in)}"
  }
  def clearIO:Unit = {
    inMap.clear
    outMap.clear
  }
}
object GridIO {
  def fourDirections = { "W" :: "N" :: "E" :: "S" ::Nil }
  def eightDirections = { "W" :: "NW" :: "N" :: "NE" :: "E" ::  "SE" :: "S" :: "SW" ::Nil }
  def diagDirections = {"NW":: "NE":: "SE":: "SW" :: Nil}
}

case class ScalarIO[+N<:NetworkElement](src:N)(implicit spade:Spade) extends GridIO[N] {
  override def toString = s"${src}.scalarIO"
}

case class VectorIO[+N<:NetworkElement](src:N)(implicit spade:Spade) extends GridIO[N] {
  override def toString = s"${src}.vectorIO"
}

case class ControlIO[+N<:NetworkElement](src:N)(implicit spade:Spade) extends GridIO[N] {
  override def toString = s"${src}.ctrlIO"
}
