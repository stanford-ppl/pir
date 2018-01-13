package pir.newnode

import pir._

import pirc._
import pirc.enums._
import pirc.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

abstract class Node(implicit design:PIR) extends pirc.node.Node[Node, Container] { self:Product =>
  design.addNode(this)

  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }
  override def toString = {
    val default = s"${this.getClass.getSimpleName}$id"
    if (name == null) default else name.getOrElse(default) 
  }

  def ctrl(ctrler:Container)(implicit design:PIR):this.type = {
    (this, ctrler) match {
      case (self:Counter, _) => 
      case (self:Controller, ctrler:Controller) => self.tree.setParent(ctrler.tree)
      case (self:Def, ctrler) => self.setParent(ctrler) 
      case (_,_) =>
    }
    this
  }
}

abstract class Container(implicit design:PIR) extends Node with pirc.node.SubGraph[Node, Container] { self:Product =>
}

abstract class Module(implicit design: PIR) extends Node with pirc.node.Atom[Node, Container] { self:Product =>
  def connect(io:IO)(implicit design:PIR):IO = {
    io match {
      case io:Input => new Output()(this, design).connect(io)
      case io:Output => new Input()(this, design).connect(io)
    }
  }

  override def connectField(x:Node)(implicit design:Design):Unit = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Def => this.connect(x.out)
      case x:Memory => this.connect(x.out) // StoreDef override this function. it connects to Memory.in
      case x:Counter => this.connect(x.out)
    }
  }
}

abstract class IO(src:Module)(implicit design:PIR) extends pirc.node.Edge[Node, Container](src) {
  override def connect(p:E):this.type = {
    err(this.isInstanceOf[Input] && this.isConnected && !this.isConnectedTo(p), s"$this is already connected to ${connected}, reconnecting to $p")
    super.connect(p)
  }
}
class Input(implicit src:Module, design:PIR) extends IO(src) with pirc.node.Input[Node, Container] {
  type E = Output
  def from = connected.head
}
class Output(implicit src:Module, design:PIR) extends IO(src) with pirc.node.Output[Node, Container] {
  type E = Input
  def to = connected
}

case class DRAM()(implicit design:PIR) extends Module 

abstract class Memory(implicit design:PIR) extends Module { self:Product =>
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

case class Top()(implicit design: PIR) extends Container { 
  val argIns = mutable.ListBuffer[Reg]()
  val argOuts = mutable.ListBuffer[Reg]()
  val dramAddresses = mutable.Map[DRAM, Reg]()

  def argIn(init:Const[_<:AnyVal])(implicit design:PIR) = {
    val reg = Reg(init).setParent(this)
    argIns += reg
    reg
  }

  def argOut(init:Const[_<:AnyVal])(implicit design:PIR) = {
    val reg = Reg(init).setParent(this)
    argOuts += reg
    reg
  }

  def dramAddress(dram:DRAM)(implicit design:PIR) = {
    val reg = Reg().setParent(this)
    dramAddresses += dram -> reg
    LoadDef(List(reg), None)
  }

}

case class ControlHierarchy(controller:Controller)(implicit design:PIR) extends pirc.node.SubGraph[ControlHierarchy,ControlHierarchy]

case class Controller(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIR) extends Container {
  val tree = ControlHierarchy(this)

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
  def depDefs:List[Def] = deps.collect { case d:Def => d } 
  def localDepDefs = localDeps.collect { case d:Def => d } 
  def depedDefs:List[Def] = depeds.collect { case d:Def => d } 
  def localDepedDefs = localDepeds.collect { case d:Def => d } 

  val out = new Output()(this,design)
}

trait StoreNode extends Module { self:Product =>
  override def connectField(x:Node)(implicit design:Design):Unit = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Memory => this.connect(x.in)
      case x => super.connectField(x)
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

object Module {
  def toOutput(a:Any) = { // TODO: change this to implicit conversion inside this package
    a match {
      case a:Output => a
      case a:Def => a.out
      case a => throw new Exception(s"Don't know how to convert $a to Output")
    }
  }
}

trait Traversal extends pirc.node.Traversal {
  type N = Node 
  type P = Container
}

trait Collector extends Traversal { self =>

  def newTraversal[M<:Node:ClassTag](vf:N => List[N]) = new pirc.node.BFSTraversal {
    type T = (Iterable[M], Int)
    type N = self.N
    def visitNode(n:N, prev:T):T = {
      val (prevRes, depth) = prev
      n match {
        case n:M if depth > 0 => (prevRes ++ List(n), depth - 1)
        case _ if depth == 0 => (prevRes, 0)
        case _ => (prevRes, depth - 1)
      }
    }
    def visitFunc(n:N):List[N] = vf(n)
  }
 
  def collectUp[M<:Node:ClassTag](n:N, depth:Int=10):Iterable[M] = {
    newTraversal(visitUp _).traverse(n, (Nil, depth))._1
  }
}


trait Transformer {
  def removeNode(node:Node) = {
    node.ios.foreach { io => io.disconnect }
    node.parent.foreach { parent =>
      parent.removeChild(node)
      node.unsetParent
      (parent.children.filterNot { _ == node } :+ parent).foreach(removeUnusedIOs)
    }
  }

  def removeUnusedIOs(node:Node) = {
    node.ios.foreach { io => if (!io.isConnected) io.src.removeEdge(io) }
  }

  def transform[T<:Node](n:T):T = n match {
    case n:Product => // Default way to mirror node
      val fields = n.productIterator.toList
      //TODO: n.getClass.getConstructor(fields.map{_.getClass}:_*).newInstance(fields.map{
      // Some how this compiles but gives runtime error for not able to find the constructor when fields contain Int type since
      // field.getClass returns java.lang.Integer type but getConstructor expects typeOf[Int]
      n.getClass.getConstructors()(0).newInstance(fields.map { // Only works with a single constructor
        case n:Node => transform(n).asInstanceOf[Object]
        case n => n
      }).asInstanceOf[T]
    case n => throw new Exception(s"Don't know how to mirror $n")
  }
}

//trait GlobalIRDotCodegen extends IRDotCodegen {
  //def emitNode(n:N) = n match {
  //}
//}
