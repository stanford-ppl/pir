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
trait IO[P<:LinkType, +S<:Module] extends Node {
  val src:S
  def isConnected: Boolean
  def disconnect:Unit
  def canConnect(n:Any):Boolean
}

/* Input pin. Can only connects to output of the same level */
trait Input[P<:LinkType, +S<:Module] extends IO[P, S] { 
  type O <: Output[P, Module]
  // List of connections that can map to
  val fanIns = ListBuffer[O]()
  def connect(n:O):Unit = { fanIns += n }
  def <==(n:O) = { connect(n) }
  def <==(ns:List[O]) = ns.foreach(n => connect(n))
  def ms = s"${this}=mp[${fanIns.mkString(",")}]"
  def canConnect(n:Any):Boolean = fanIns.contains(n)
  def isConnected = fanIns.size!=0
  def disconnect = fanIns.clear
}
/* Output pin. Can only connects to input of the same level */
trait Output[P<:LinkType, +S<:Module] extends IO[P, S] { 
  type I <: Input[P, Module]
  val fanOuts = ListBuffer[I]()
  def connectedTo(n:I):Unit = fanOuts += n
  def ==>(n:I):Unit = { n.connect(this.asInstanceOf[n.O]) }
  def ==>(ns:List[I]):Unit = ns.foreach { n => ==>(n) }
  def mt = s"${this}=mt[${fanOuts.mkString(",")}]" 
  def canConnect(n:Any):Boolean = fanOuts.contains(n)
  def isConnected = fanOuts.size!=0
  def disconnect = fanOuts.clear
} 

trait LinkType extends Node
/* Three types of pin */
/* Bit level IO pin */
trait Wire extends LinkType
/* Word level IO pin */
trait Port extends LinkType
/* Bus level IO pin */
trait Bus extends LinkType {
  val busWidth:Int
}

case class InWire[+S<:Module](src:S)(implicit spade:Spade) extends Wire with Input[Wire, S] {
  src.addIO(this)
  override type O = OutWire[Module]
  override val typeStr = "iw"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
}
object InWire {
  def apply[S<:Module](s:S, sf: =>String)(implicit spade:Spade):InWire[S] = new InWire[S](s) {
    override def toString = sf
  }
}
case class OutWire[+S<:Module](src:S)(implicit spade:Spade) extends Wire with Output[Wire, S] { 
  src.addIO(this)
  override type I = InWire[Module]
  override val typeStr = "ow"
}
object OutWire {
  def apply[S<:Module](s:S, sf: =>String)(implicit spade:Spade):OutWire[S] = new OutWire[S](s) {
    override def toString = sf
  }
}

class InPort[+S<:Module](override val src:S)(implicit spade:Spade) extends Port with Input[Port, S] {
  src.addIO(this)
  override type O = OutPort[Module]
  override val typeStr = "ip"
  def <==(r:PipeReg):Unit = connect(r.out)
  override def connect(n:O):Unit = {super.connect(n); n.connectedTo(this)}

}
object InPort {
  def apply[S<:Module](s:S)(implicit spade:Spade) = new InPort(s) 
  def apply[S<:Module](s:S, sf: =>String)(implicit spade:Spade):InPort[S] = new InPort[S](s) {
    override def toString = sf
  }
}
/*
 * An output port of a module.
 * src is a pointer to the module
 * */
class OutPort[+S<:Module](override val src:S)(implicit spade:Spade) extends Port with Output[Port, S] { 
  src.addIO(this)
  override type I = InPort[Module]
  override val typeStr = "op"
}
object OutPort {
  def apply[S<:Module](s:S)(implicit spade:Spade):OutPort[S] = new OutPort(s)
  def apply[S<:Module](s:S, sf: =>String)(implicit spade:Spade):OutPort[S] = new OutPort[S](s) {
    override def toString = sf
  }
}
trait RMPort extends Port {
  val mappedRegs = Set[Reg]()
  def mappedTo(reg:Reg) = { mappedRegs += reg }
  def isMappedTo(reg:Reg) = mappedRegs.contains(reg)
}
class RMInPort[+S<:Module](s:S)(implicit spade:Spade) extends InPort[S](s) with RMPort {
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
  def apply[S<:Module](s:S)(implicit spade:Spade) = new RMInPort[S](s)
  def apply[S<:Module](s:S, sf: =>String)(implicit spade:Spade) = new RMInPort[S](s) {
    override def toString = sf
  }
}
class RMOutPort[+S<:Module](s:S)(implicit spade:Spade) extends OutPort[S](s) with RMPort {
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
  def apply[S<:Module](s:S)(implicit spade:Spade) = new RMOutPort[S](s) 
  def apply[S<:Module](s:S, sf: =>String)(implicit spade:Spade) = new RMOutPort[S](s) {
    override def toString = sf
  }
}

trait Stagable {
  val stage:Stage
}

class InBus[+S<:NetworkElement](override val src:S, val busWidth:Int)(implicit spade:Spade) extends Bus with Input[Bus, S] {
  src.addIO(this)
  override type O = OutBus[NetworkElement]
  val outports:List[OutPort[S]] = List.tabulate(busWidth) { i => 
    (if (i==0) new RMOutPort(src)
    else OutPort(src)).index(i).bus(this)
  }
  override val typeStr = "ib"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override def connect(n:O) = {super.connect(n); n.connectedTo(this)}
  val viport:RMOutPort[S] = outports(0).asInstanceOf[RMOutPort[S]]
}
object InBus extends Metadata {
  def apply[S<:NetworkElement](src:S, busWidth:Int)(implicit spade:Spade):InBus[S] = {
    new InBus[S](src, busWidth)
  }
}
object InBuses {
  def apply[S<:NetworkElement](src:S, num:Int, busWidth:Int)(implicit spade:Spade) = 
    List.tabulate(num) { is => InBus[S](src, busWidth) }
}

class OutBus[+S<:NetworkElement](override val src:S, val busWidth:Int)(implicit spade:Spade) extends Bus with Output[Bus, S] {
  override type I = InBus[NetworkElement]
  val inports:List[InPort[S]] = List.tabulate(busWidth) { i => 
    (if (i==0) new RMInPort[S](src)
    else InPort[S](src)).index(i).bus(this)
  }
  override val typeStr = "ob"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val voport:RMInPort[S] = inports(0).asInstanceOf[RMInPort[S]]
}
object OutBus {
  def apply[S<:NetworkElement](src:S, busWidth:Int)(implicit spade:Spade):OutBus[S] = {
    new OutBus(src, busWidth)
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
  def inBuses(num:Int, busWidth:Int)(implicit spade:Spade):List[InBus[NE]] = InBuses(src, num, busWidth)
  def outBuses(num:Int, busWidth:Int)(implicit spade:Spade):List[OutBus[NE]] = OutBuses(src, num, busWidth)
  def addInAt(dir:String, num:Int, busWidth:Int)(implicit spade:Spade):List[InBus[NE]] = { 
    val ibs = inBuses(num, busWidth)
    ibs.zipWithIndex.foreach { case (ib, i) => ib }
    inMap.getOrElseUpdate(dir, ListBuffer.empty) ++= ibs
    ibs
  }
  def addOutAt(dir:String, num:Int, busWidth:Int)(implicit spade:Spade):List[OutBus[NE]] = {
    val obs = outBuses(num, busWidth)
    obs.zipWithIndex.foreach { case (ob, i) => ob }
    outMap.getOrElseUpdate(dir, ListBuffer.empty) ++= obs
    obs
  }
  def addIOAt(dir:String, num:Int, busWidth:Int)(implicit spade:Spade):NE = {
    addInAt(dir,num, busWidth)
    addOutAt(dir,num, busWidth)
    src
  }
  def addIns(num:Int, busWidth:Int)(implicit spade:Spade):NE = { 
    addInAt("N", num, busWidth)
    src
  }
  def addOuts(num:Int, busWidth:Int)(implicit spade:Spade):NE = {
    addOutAt("N", num, busWidth)
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
