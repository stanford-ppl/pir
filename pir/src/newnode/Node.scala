package pir.newnode

import pir._

import pirc._
import pirc.enums._
import pirc.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

trait IR extends pirc.node.IR { 
  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }
  def ctrl(ctrler:Container)(implicit design:PIR):this.type = {
    (this, ctrler) match {
      case (self:Counter, _) => 
      case (self:Controller, ctrler:Controller) => self.tree.setParent(ctrler.tree)
      case (self:Def, ctrler) => self.setParent(ctrler) 
      case (_,_) =>
    }
    this
  }
  override def toString = {
    val default = s"${this.getClass.getSimpleName}$id"
    if (name == null) default else name.getOrElse(default) 
  }
}

abstract class Node(implicit design:PIR) extends pirc.node.Node[Node] with IR { self:Product =>
  design.addNode(this)
  type P = Container
}

abstract class Container(implicit design:PIR) extends Node with pirc.node.SubGraph[Node] { self:Product =>
}

import pirc.newcollection.mutable._
abstract class Module(implicit design: PIR) extends Node with pirc.node.Atom[Node] { self:Product =>

  val ioMap = new BiManyToOneMap[String, IO]()
  //override def values = fields.map { field => ioMap(field).src } //TODO

  def connect(io:IO)(implicit design:PIR):IO = {
    io match {
      case io:Input => new Output()(this, design).connect(io)
      case io:Output => new Input()(this, design).connect(io)
    }
  }

  override def connectField(x:Node, field:String)(implicit design:Design):Unit = {
    implicit val pir = design.asInstanceOf[PIR]
    val io = x match {
      case x:Def => this.connect(x.out)
      case x:Memory => this.connect(x.out) // StoreDef override this function. it connects to Memory.in
      case x:Counter => this.connect(x.out)
    }
    ioMap += field -> io
  }
}

//object Def with Collector {
  //def getField[T:ClassTag](d:Module, field:String):T = typeTag[T] match {
    //case tt if tt <:< typeTag[Def] =>
    //case tt if tt <:< typeTag[Option[Def]] =>
    //case tt if tt <:< typeTag[Iterator[Def]] =>
    //case tt =>
  //}
  //def unapply[T1:ClassTag](d:Product1[T1] with Module):Option[T1] = {
    //val f1::_ = d.fields
    //Some(getField[T1](f1))
  //}
//}

abstract class IO(src:Module)(implicit design:PIR) extends pirc.node.Edge[Node](src) {
  override def connect(p:E):this.type = {
    err(this.isInstanceOf[Input] && this.isConnected && !this.isConnectedTo(p), s"$this is already connected to ${connected}, reconnecting to $p")
    super.connect(p)
  }
}
class Input(implicit src:Module, design:PIR) extends IO(src) with pirc.node.Input[Node] {
  type E = Output
  def from = connected.head
}
class Output(implicit src:Module, design:PIR) extends IO(src) with pirc.node.Output[Node] {
  type E = Input
  def to = connected
}

case class DRAM()(implicit design:PIR) extends IR

case class ArgInFringe()(implicit design:PIR) extends Module

abstract class Memory(implicit design:PIR) extends Module { self:Product =>
  val in = new Input
  val out = new Output
}

case class SRAM(size:Int, banking:Banking)(implicit design:PIR) extends Memory

case class Reg(val init:Option[AnyVal])(implicit design:PIR) extends Memory
object Reg {
  def apply(init:AnyVal)(implicit design:PIR):Reg = Reg(Some(init))
  def apply()(implicit design:PIR):Reg = Reg(None)
}

case class StreamIn(field:String)(implicit design:PIR) extends Memory
case class StreamOut(field:String)(implicit design:PIR) extends Memory

trait GlobalContainer extends Container { self:Product => }

case class CUContainer(contains:Node*)(implicit design:PIR) extends GlobalContainer

case class FringeContainer(dram:DRAM, contains:Node*)(implicit design:PIR) extends GlobalContainer

case class ArgContainer()(implicit design:PIR) extends GlobalContainer {
  val argInFringe = ArgInFringe().setParent(this)
}

case class Top()(implicit design: PIR) extends Container { 
  val argContainer = ArgContainer().setParent(this)

  val argIns = mutable.ListBuffer[Reg]()
  val argOuts = mutable.ListBuffer[Reg]()
  val dramAddresses = mutable.Map[DRAM, Reg]()

  def argIn(init:AnyVal)(implicit design:PIR) = {
    val reg = Reg(init).setParent(this)
    argIns += reg
    argContainer.argInFringe.connect(reg.in)
    reg
  }

  def argOut(init:AnyVal)(implicit design:PIR) = {
    val reg = Reg(init)
    argOuts += reg
    argContainer.addChild(reg)
    reg
  }

  def dramAddress(dram:DRAM)(implicit design:PIR) = {
    val reg = Reg().setParent(this)
    reg.name(s"DramAddr${reg.id}")
    dramAddresses += dram -> reg
    argContainer.argInFringe.connect(reg.in)
    LoadDef(List(reg), None)
  }

}

case class ControlHierarchy(controller:Controller)(implicit design:PIR) extends pirc.node.SubGraph[ControlHierarchy] {
  type P = ControlHierarchy 
}

case class Controller(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIR) extends Container {
  override def className = s"$style"
  val tree = ControlHierarchy(this)

  def cchains = children.collect { case c:CounterChain => c }

  def defs = children.collect { case d:Def => d }

  def isInnerControl = tree.children.isEmpty 
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
  def depDefs:Set[Def] = deps.collect { case d:Def => d } 
  def localDepDefs = localDeps.collect { case d:Def => d } 
  def depedDefs:Set[Def] = depeds.collect { case d:Def => d } 
  def localDepedDefs = localDepeds.collect { case d:Def => d } 

  val out = new Output()(this,design)
}

trait StoreNode extends Module { self:Product =>
  override def connectField(x:Node, field:String)(implicit design:Design):Unit = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Memory => this.connect(x.in)
      case x => super.connectField(x, field)
    }
  }
}

case class IterDef(counter:Counter, offset:Option[Int])(implicit design:PIR) extends Def 
case class OpDef(op:Op, inputs:List[Def])(implicit design:PIR) extends Def
case class ReduceDef(op:Op, input:Def)(implicit design:PIR) extends Def
case class AccumDef(op:Op, input:Def, accum:Def)(implicit design:PIR) extends Def

// Generated IR from spatial
case class LoadDef(mems:List[Memory], addrs:Option[List[Def]])(implicit design:PIR) extends Def
case class StoreDef(mems:List[Memory], addrs:Option[List[Def]], data:Def)(implicit design:PIR) extends Def with StoreNode

// Lowered IR
case class MemLoad(mem:Memory, addrs:Option[List[Def]])(implicit design:PIR) extends Def
case class MemStore(mem:Memory, addrs:Option[List[Def]], data:Def)(implicit design:PIR) extends Def with StoreNode

// IR's doesn't matter in spatial. such as valid for counters. Should be dead code eliminated
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
