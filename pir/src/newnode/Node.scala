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
abstract class Node(implicit design:PIR) extends Serializable { 

  val id = design.nextId

  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName

  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }

  override def toString = name.getOrElse(s"$className$id") 

}

trait SubGraph[C<:SubGraph[C]] extends Node with Serializable { self:C =>
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

  def ios:List[IO] = descendents.flatMap { _.ios }
  def ins = ios.collect{ case io:Input => io }.toList
  def outs = ios.collect{ case io:Output => io }.toList

  def ctrl(ctrler:Container)(implicit design:PIR):this.type = {
    (ctrler, ctrler) match {
      case (self:Controller, ctrler:Controller) => self.tree.parent(ctrler.tree)
      case (self:Memory, _) => self.parent(design.newTop) 
      case (self:DRAM, _) => 
      case (self, ctrler) => self.parent(ctrler) 
    }
    this
  }
}

trait Container extends SubGraph[Container]

abstract class Module(implicit design: PIR) extends Node with Container { 
  implicit val self:Module = this

  val _ios = mutable.ListBuffer[IO]()
  def addIO(io:IO) = _ios += io
  def removeIO(io:IO) = _ios -= io
  override def ios:List[IO] = _ios.toList ++ super.ios 

  def connect(io:IO)(implicit design:PIR):IO = {
    io match {
      case io:Input => new Output()(this, design).connect(io)
      case io:Output => new Input()(this, design).connect(io)
    }
  }

  def connectInput(x:Any)(implicit design:PIR):Unit = {
    x match {
      case x:Def => this.connect(x.out)
      case x:Memory => this.connect(x.out)
      case Some(x) => connectInput(x)
      case x:Iterable[_] => x.foreach(connectInput)
      case x:Iterator[_] => x.foreach(connectInput)
      case x =>
    }
  }

  this match {
    case p:Product => connectInput(p.productIterator)
    case _ =>
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
  val in = new Input
  val out = new Output
}

case class SRAM(size:Int, banking:Banking)(implicit design:PIR) extends Memory

case class Reg(val init:Option[Const[_<:AnyVal]])(implicit design:PIR) extends Memory
object Reg {
  def apply(init:Const[_<:AnyVal])(implicit design:PIR):Reg = Reg(Some(init))
  def apply()(implicit design:PIR):Reg = Reg(None)
}

case class StreamIn(field:String)(implicit design:PIR) extends Memory
case class StreamOut(field:String)(implicit design:PIR) extends Memory

case class CUContainer(contain:Node*)(implicit design:PIR) extends Container {
  def isFringe = children.collect { case dram:DRAM => dram }.nonEmpty
}

class Top(implicit design: PIR) extends Container { 
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

class ControlHierarchy(controller:Controller)(implicit design:PIR) extends SubGraph[ControlHierarchy]

case class Controller(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIR) extends Container {
  val tree = new ControlHierarchy(this)

  def cchains = children.collect { case c:CounterChain => c }

  def defs = children.collect { case d:Def => d }
}

case class CounterChain(counters:List[Counter])(implicit design:PIR) extends Container 
object CounterChain {
  def unit(implicit design:PIR) = {
    CounterChain(List(Counter(Const(0), Const(1), Const(1), 1)))
  }
}

case class Counter(min:Def, max:Def, step:Def, par:Int)(implicit design:PIR) extends Module {
  val out = new Output
}

abstract class Def(implicit design:PIR) extends Module { self:Product =>
  val out = new Output()(this,design)
  def deps:List[Def] = ins.map { _.from.src }.collect { case d:Def => d }
  def localDeps = deps.filter { _.parent == this.parent }
  def depeds:List[Def] = outs.flatMap { _.to.map { _.src } }.collect { case d:Def => d }
  def localDepeds = depeds.filter { _.parent == this.parent }
}

case class IterDef(counter:Counter, offset:Option[Int])(implicit design:PIR) extends Def 
case class OpDef(op:Op, inputs:List[Def])(implicit design:PIR) extends Def
case class ReduceDef(op:Op, input:Def)(implicit design:PIR) extends Def
case class AccumDef(op:Op, input:Def, accum:Def)(implicit design:PIR) extends Def

case class LoadDef(mems:List[Memory], addrs:Option[List[Def]])(implicit design:PIR) extends Def
case class StoreDef(mems:List[Memory], addrs:Option[List[Def]], data:Def)(implicit design:PIR) extends Def

case class MemLoad(mem:Memory, addrs:Option[List[Def]])(implicit design:PIR) extends Def
case class MemStore(mem:Memory, addrs:Option[List[Def]], data:Def)(implicit design:PIR) extends Def

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

  def visitUp(n:Node):Iterable[Node] = {
    n match {
      case n:SubGraph[_] => n.parent.toList
      case _ => Nil
    }
  }

  def visitDown(n:Node):Iterable[Node] = {
    n match {
      case n:SubGraph[_] => n.children
      case _ => Nil
    }
  }

  def traverse(n:Node, zero:T, visitFunc:Node => Iterable[Node]):T = {
    visitFunc(n).foldLeft(zero) { case (prev, n) => traverse(n, prev, visitFunc) }
  }

  def traverseUp(n:Node, zero:T):T = traverse(n, zero, visitUp)

  def traverseDown(n:Node, zero:T):T = traverse(n, zero, visitDown)

  def collect[M<:Node:ClassTag](n:Node, visitFunc:Node => Iterable[Node], depth:Int):Iterable[M] = {
    def recurse(n:Node, depth:Int):Iterable[M] = {
      n match {
        case n:M => List(n)
        case n if depth > 0 => visitFunc(n).flatMap { n => recurse(n, depth - 1) }
        case n => Nil
      }
    }
    visitFunc(n).flatMap { n => recurse(n, depth - 1) }
  }

  def collectUp[M<:Module:ClassTag](m:Module, depth:Int=10):Iterable[M] = {
    collect(m, visitUp, depth)
  }
}

trait Transformer {
  def removeNode(node:Container) = {
    node.ios.foreach { io => io.disconnect }
    node.parent.foreach { parent =>
      parent.removeChild(node)
      node.unsetParent
      (parent.children.filterNot { _ == node } :+ parent).foreach(removeUnusedIOs)
    }
  }

  def removeUnusedIOs(node:Container) = {
    node.ios.foreach { io => if (!io.isConnected) io.src.removeIO(io) }
  }
}
