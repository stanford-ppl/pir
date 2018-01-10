package pir.newnode

import pir._

import pirc.enums._
import pirc.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

/** Base class for all PIR nodes. 
  * @param name: optional user name for a node 
  */
@SerialVersionUID(123L)
abstract class Node(val id:Int) extends Serializable { 

  def this()(implicit design: PIR) = {
    this(design.nextId)
  }

  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName

  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }

  override def toString = name.getOrElse(s"$className$id") 

}

trait Container[C<:Container[C]] extends Node with Serializable { self:C =>
  var _parent:Option[C] = None
  def parent:Option[C] = _parent
  def parent(p:C):this.type =  {
    if (p.isParentOf(this)) return this
    _parent = Some(p)
    p.addChild(this)
    this
  }
  def unsetParent = {
    parent.foreach { p =>
      _parent = None
      p.removeChild(this.asInstanceOf[C])
    }
  }
  def isParentOf(m:C) = m.parent == Some(this)

  val _children = mutable.ListBuffer[C]()
  def children = _children.toList
  def isChildOf(p:C) = p.children.contains(this)
  def addChild(c:C):Unit = { 
    if (c.isChildOf(this)) return
    _children += c
    c.parent(this)
  }
  def addChildren(cs:C*):this.type = { cs.foreach(addChild); this }
  def removeChild(c:C):Unit = {
    if (!isParentOf(c)) return
    _children -= c
    c.unsetParent
  }

  def ancestors:List[C] = parent.toList.flatMap { parent => parent :: parent.ancestors }
  def descendents:List[C] = children.flatMap { child => child :: child.descendents }
}

abstract class Module(implicit design: PIR) extends Node with Container[Module] { 
  implicit val self:Module = this

  val _ios = mutable.ListBuffer[IO]()
  def addIO(io:IO) = _ios += io
  def removeIO(io:IO) = _ios -= io
  def ios:List[IO] = _ios.toList ++ descendents.flatMap { _.ios }
  def ins = ios.collect{ case io:Input => io }.toList
  def outs = ios.collect{ case io:Output => io }.toList

  def connect(io:IO)(implicit design:PIR):IO = {
    io match {
      case io:Input => new Output()(this, design).connect(io)
      case io:Output => new Input()(this, design).connect(io)
    }
  }

  def ctrl(ctrler:Module)(implicit design:PIR):this.type = {
    (this, ctrler) match {
      case (self:Controller, ctrler:Controller) => self.tree.parent(ctrler.tree)
      case (self:Memory, _) => self.parent(design.newTop) 
      case (self:DRAM, _) => 
      case (self, ctrler) => self.parent(ctrler) 
    }
    this
  }
}

object Module {
  def toOutput(a:Any) = { // TODO: change this to implicit conversion inside this package
    a match {
      case a:Output => a
      case a:Def => a.out
      case a => throw new Exception(s"Don't know how to convert $a to Output")
    }
  }
}

trait Traversal {
  type T

  def visitUp(m:Module):Iterable[Module] = {
    m.parent.toList
  }

  def visitDown(m:Module):Iterable[Module] = {
    m.children
  }

  def traverse(m:Module, zero:T, visitFunc:Module => Iterable[Module]):T = {
    visitFunc(m).foldLeft(zero) { case (prev, m) => traverse(m, prev, visitFunc) }
  }

  def traverseUp(m:Module, zero:T):T = traverse(m, zero, visitUp)

  def traverseDown(m:Module, zero:T):T = traverse(m, zero, visitDown)

  def collect[M<:Module:ClassTag](m:Module, visitFunc:Module => Iterable[Module], depth:Int):Iterable[M] = {
    def recurse(m:Module, depth:Int):Iterable[M] = {
      m match {
        case m:M => List(m)
        case m if depth > 0 => visitFunc(m).flatMap { m => recurse(m, depth - 1) }
        case m => Nil
      }
    }
    visitFunc(m).flatMap { m => recurse(m, depth - 1) }
  }

  def collectUp[M<:Module:ClassTag](m:Module, depth:Int=10):Iterable[M] = {
    collect(m, visitUp, depth)
  }
}

trait Transformer {
  def removeModule(module:Module) = {
    module.ios.foreach { io => io.disconnect }
    module.parent.foreach { parent =>
      parent.removeChild(module)
      module.unsetParent
      (parent.children.filterNot { _ == module } :+ parent).foreach(removeUnusedIOs)
    }
  }

  def removeUnusedIOs(module:Module) = {
    module.ios.foreach { io => if (!io.isConnected) module.removeIO(io) }
  }
}

class Top(implicit design: PIR) extends Module { 
  val argIns = mutable.ListBuffer[Reg]()
  val argOuts = mutable.ListBuffer[Reg]()
  val dramAddresses = mutable.Map[DRAM, Reg]()

  def argIn(init:Const[_<:AnyVal])(implicit design:PIR) = {
    val reg = Reg(init).parent(this)
    argIns += reg
    reg
  }

  def argOut(init:Const[_<:AnyVal])(implicit design:PIR) = {
    val reg = Reg(init).parent(this)
    argOuts += reg
    reg
  }

  def dramAddress(dram:DRAM)(implicit design:PIR) = {
    val reg = Reg().parent(this)
    dramAddresses += dram -> reg
    LoadDef(List(reg), None)
  }
}


abstract class IO(val src:Module)(implicit design:PIR) extends Node {
  src.addIO(this)
  type P <: IO

  protected val _connected = mutable.ListBuffer[P]()
  def connected:List[P] = _connected.toList
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(p:IO) = connected.contains(p)
  def connect(p:P):this.type = {
    if (isConnectedTo(p)) return this
    err(this.isInstanceOf[Input] && this.isConnected, s"$this is already connected to ${connected}, reconnecting to $p")
    _connected += p 
    p.connect(this.asInstanceOf[p.P])
    this
  }

  def disconnectFrom(io:IO):Unit = {
    _connected -= io.asInstanceOf[P]
    if (io.isConnectedTo(this)) io.disconnectFrom(this)
  }
  def disconnect = connected.foreach(_.disconnectFrom(this))
}

class Input(implicit override val src:Module, design:PIR) extends IO(src) {
  type P = Output
  def from = connected.head
}

class Output(implicit override val src:Module, design:PIR) extends IO(src) {
  type P = Input
  def to = connected
}

case class DRAM()(implicit design:PIR) extends Module

abstract class Memory(implicit design:PIR) extends Module {
  val writePort = new Input
  val readPort = new Output
}

class SRAM(val size:Int, val banking:Banking)(implicit design:PIR) extends Memory
object SRAM {
  def apply(size:Int, banking:Banking)(implicit design:PIR) = new SRAM(size, banking)
}

class Reg(val init:Option[Const[_<:AnyVal]])(implicit design:PIR) extends Memory
object Reg {
  def apply(init:Const[_<:AnyVal])(implicit design:PIR):Reg = new Reg(Some(init))
  def apply()(implicit design:PIR):Reg = new Reg(None)
}

case class StreamIn(field:String)(implicit design:PIR) extends Memory
case class StreamOut(field:String)(implicit design:PIR) extends Memory

class CUContainer(implicit design:PIR) extends Module {
  def isFringe = children.collect { case dram:DRAM => dram }.nonEmpty
}
object CUContainer {
  def apply()(implicit design:PIR) = new CUContainer()
}

class ControlHierarchy(controller:Controller)(implicit design:PIR) extends Container[ControlHierarchy]

case class Controller(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIR) extends Module {
  val tree = new ControlHierarchy(this)

  def cchains = children.collect { case c:CounterChain => c }

  def defs = children.collect { case d:Def => d }

  cchain.original = this
}

class CounterChain()(implicit design:PIR) extends Module {
  var original:Controller = _

  def counters = children.collect { case c:Counter => c }
}
object CounterChain {
  def apply(counters:List[Counter])(implicit design:PIR) = {
    val cchain = new CounterChain()
    counters.foreach { _.parent(cchain) }
    cchain
  }
  def unit(implicit design:PIR) = {
    CounterChain(List(Counter(Const(0), Const(1), Const(1), 1)))
  }
}

class Counter(val par:Int)(implicit design:PIR) extends Module {
  val min = new Input
  val max = new Input
  val step = new Input
  val out = new Output
}
object Counter {
  def apply(min:Any, max:Any, step:Any, par:Int)(implicit design:PIR) = {
    val counter = new Counter(par)
    counter.min.connect(Module.toOutput(min))
    counter.max.connect(Module.toOutput(max))
    counter.step.connect(Module.toOutput(step))
    counter
  }
}

// Dummy constructor to call during deserialization
abstract class Def(dummy:Int)(implicit design:PIR) extends Module { self:Product =>
  val out = new Output()(this,design)
  def deps = productIterator.toList.collect { case dep:Def => dep ; case Some(dep:Def) => dep}

  def this()(implicit design:PIR) = {
    this(0)
    deps.foreach { dep => this.connect(dep.out)(design) }
  }
}

case class IterDef(counter:Counter, offset:Option[Int])(implicit design:PIR) extends Def 

case class OpDef(op:Op, inputs:List[Def])(implicit design:PIR) extends Def
case class ReduceDef(op:Op, input:Def)(implicit design:PIR) extends Def
case class AccumDef(op:Op, input:Def, accum:Def)(implicit design:PIR) extends Def

case class MemLoad(mems:List[Memory], addrs:Option[List[Def]])(implicit design:PIR) extends Def
case class MemStore(mems:List[Memory], addrs:Option[List[Def]])(implicit design:PIR) extends Def

case class LoadDef(mems:List[Memory], addrs:Option[List[Def]])(implicit design:PIR) extends Def
case class StoreDef(mems:List[Memory], addrs:Option[List[Def]], data:Def)(implicit design:PIR) extends Def
case class DummyDef()(implicit design:PIR) extends Def
case class Const[T<:AnyVal](value:T)(implicit design:PIR) extends Def

sealed trait IOType extends Enum
case object Vector extends IOType
case object Scalar extends IOType
case object Control extends IOType

sealed trait ControlStyle extends Enum
case object InnerPipe extends ControlStyle
case object SeqPipe extends ControlStyle
case object MetaPipe extends ControlStyle
case object StreamPipe extends ControlStyle

sealed trait ControlLevel extends Enum
case object InnerControl extends ControlLevel
case object OuterControl extends ControlLevel
