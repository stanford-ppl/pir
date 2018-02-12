package pir.node

import pir._

import pirc._
import pirc.enums._
import pirc.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._
import scala.reflect.runtime.universe._

trait IR extends prism.node.IR { 
  def name(n:String)(implicit design:PIR):this.type = {
    design.newTop.metadata.nameOf(this) = n
    this
  } 
  def name(n:Option[String])(implicit design:PIR):this.type = { 
    n.foreach{n => this.name(n)}; 
    this 
  }
  def name(implicit design:PIR) = design.newTop.metadata.nameOf.get(this)

  def ctrl(ctrler:Any)(implicit design:PIR):this.type = {
    (this, ctrler) match {
      case (self:Controller, ctrler:Controller) => self.setParent(ctrler)
      case (self:Controller, top:Top) => self.setParent(top.topController)
      case (_, top:Top) =>
      case (self:Memory, _) =>
      case (self:PIRNode, ctrler:Controller) => design.newTop.metadata.ctrlOf(self) = ctrler 
    }
    this
  }

}

abstract class PIRNode(implicit design:PIR) extends prism.node.Node[PIRNode] with IR { self =>
  design.addNode(this)
  type N = PIRNode
  type P = Container
  type A = Primitive
  override def ins:List[Input]
  override def outs:List[Output]
  override def ios:List[IO] = ins ++ outs
}

abstract class Container(implicit design:PIR) extends PIRNode with prism.node.SubGraph[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]

  override def connectFields(x:Any)(implicit design:Design):Any = {
    implicit val ev = nct
    x match {
      case x:N => this.addChild(x); x
      case x => super.connectFields(x)
    }
  }
}

abstract class Primitive(implicit design: PIR) extends PIRNode with prism.node.Atom[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]

  lazy val out = new Output
  out //Make sure lazy val is evaluated so in swapOutput the IO patterns are the same

  def connect(io:IO)(implicit design:PIR):IO = {
    io match {
      case io:Input => out.connect(io)
      case io:Output => new Input()(this, design).connect(io)
    }
  }

  override def connectFields(x:Any)(implicit design:Design):Any = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Primitive => this.connect(x.out) // StoreMem override this function. it connects to Memory.in
      case x => super.connectFields(x)
    }
  }

  override def evaluateFields(x:Any):Any = x match {
    case x:Input => x.singleConnected.map{_.src}.getOrElse(null)
    case x => super.evaluateFields(x)
  }
}

abstract class IO(override val src:Primitive)(implicit design:PIR) extends prism.node.Edge[PIRNode]() {
  override type A = Primitive
  override def connect(p:prism.node.Edge[PIRNode]):this.type = {
    err(this.isInstanceOf[Input] && this.isConnected && !this.isConnectedTo(p), s"$this is already connected to ${connected}, reconnecting to $p")
    super.connect(p)
  }
}
class Input(implicit src:Primitive, design:PIR) extends IO(src) with prism.node.Input[PIRNode] {
  type E = Output
  def from = connected.head
  override def connect(e:prism.node.Edge[PIRNode]):this.type = {
    val p = e.asInstanceOf[E]
    super.connect(p)
  }
}
class Output(implicit src:Primitive, design:PIR) extends IO(src) with prism.node.Output[PIRNode] {
  type E = Input
  def to = connected
  override def connect(e:prism.node.Edge[PIRNode]):this.type = {
    val p = e.asInstanceOf[E]
    super.connect(p)
  }
}

case class DRAM()(implicit design:PIR) extends IR

abstract class Memory(implicit design:PIR) extends Primitive { self =>
  def newIn(implicit design:PIR):Input = {
    ins.filterNot(_.isConnected).headOption.getOrElse(new Input)
  }

  def writers = deps.collect { case s: LocalStore => s }
  def readers = depeds.collect { case l: LocalLoad => l }
  def accesses = writers ++ readers
}

case class SRAM(size:Int, banking:Banking)(implicit design:PIR) extends Memory
case class RegFile(sizes:List[Int], inits:Option[List[AnyVal]])(implicit design:PIR) extends Memory
case class FIFO(size:Int)(implicit design:PIR) extends Memory

case class Reg(init:Option[AnyVal])(implicit design:PIR) extends Memory
object Reg {
  def apply(init:AnyVal)(implicit design:PIR):Reg = Reg(Some(init))
  def apply()(implicit design:PIR):Reg = Reg(None)
}

case class ArgIn(init:Option[AnyVal])(implicit design:PIR) extends Memory
object ArgIn {
  def apply(init:AnyVal)(implicit design:PIR):ArgIn = ArgIn(Some(init))
  def apply()(implicit design:PIR):ArgIn = ArgIn(None)
}

case class ArgOut(init:Option[AnyVal])(implicit design:PIR) extends Memory
object ArgOut {
  def apply(init:AnyVal)(implicit design:PIR):ArgOut = ArgOut(Some(init))
  def apply()(implicit design:PIR):ArgOut = ArgOut(None)
}

case class StreamIn(field:String)(implicit design:PIR) extends Memory
case class StreamOut(field:String)(implicit design:PIR) extends Memory

case class RetimingFIFO()(implicit design:PIR) extends Memory

trait GlobalContainer extends Container { self => }

case class CUContainer(contains:PIRNode*)(implicit design:PIR) extends GlobalContainer

case class FringeContainer(dram:DRAM, contains:PIRNode*)(implicit design:PIR) extends GlobalContainer

case class ArgFringe(argController:ArgController)(implicit design:PIR) extends GlobalContainer {
  val argInDef = ArgInDef().setParent(this).ctrl(argController)
}

case class Top()(implicit design: PIR) extends GlobalContainer { 
  val metadata = new PIRMetadata

  val topController:TopController = TopController()
  val argController = ArgController().setParent(topController)
  lazy val argFringe = ArgFringe(argController).setParent(this)

  val argIns = mutable.ListBuffer[ArgIn]()
  val argOuts = mutable.ListBuffer[ArgOut]()
  val dramAddresses = mutable.Map[DRAM, ArgIn]()

  def argIn(init:AnyVal)(implicit design:PIR) = {
    val reg = ArgIn(init).setParent(this)
    argIns += reg
    StoreMem(List(reg), None, argFringe.argInDef).setParent(argFringe).ctrl(argController)
    reg
  }

  def argOut(init:AnyVal)(implicit design:PIR) = {
    val reg = ArgOut(init)
    argOuts += reg
    argFringe.addChild(reg)
    reg
  }

  def dramAddress(dram:DRAM)(implicit design:PIR) = {
    val reg = ArgIn().setParent(this)
    reg.name(s"DramAddr${reg.id}")
    dramAddresses += dram -> reg
    StoreMem(List(reg), None, argFringe.argInDef).setParent(argFringe).ctrl(argController)
    LoadMem(reg, None)
  }

}

trait ComputeNode extends PIRNode

trait Controller extends prism.node.SubGraph[Controller] with IR {
  type P = Controller
  val style:ControlStyle
  val level:ControlLevel
  def isInnerControl = level==InnerControl 
  def isOuterControl = level==OuterControl
}
case class LoopController(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIR) extends Controller {
  override def className = s"$style"
}
case class UnitController(style:ControlStyle, level:ControlLevel)(implicit design:PIR) extends Controller
case class TopController()(implicit design:PIR) extends Controller {
  val style = SeqPipe
  val level = OuterControl 
}
case class ArgController()(implicit design:PIR) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}
case class CounterChain(counters:List[Counter])(implicit design:PIR) extends Container with ComputeNode
object CounterChain {
  def unit(implicit design:PIR) = {
    CounterChain(List(Counter(Const(0), Const(1), Const(1), 1)))
  }
}

case class Counter(min:Def, max:Def, step:Def, par:Int)(implicit design:PIR) extends Primitive with ComputeNode {
}

abstract class Def(implicit design:PIR) extends Primitive with ComputeNode { self =>
  def depDefs:Set[Def] = deps.collect { case d:Def => d } 
  def localDepDefs = localDeps.collect { case d:Def => d } 
  def depedDefs:Set[Def] = depeds.collect { case d:Def => d } 
  def localDepedDefs = localDepeds.collect { case d:Def => d } 
}
object Def {
  def unapply[T](x:T)(implicit design:Design):Option[(T, PIRNode)] = {
    x match {
      case n:PIRNode => Some((x, n.newInstance(n.values, staging=false)))
      case _ => None
    }
  }
}


trait StageDef extends Def
trait LocalLoad extends Def
object LocalLoad {
  def unapply(n:Any)(implicit design:PIR):Option[(List[Memory], Option[List[Def]])] = n match {
    case LoadMem(mem, addrs) => Some((List(mem), addrs))
    case LoadBanks(banks, addrs) => Some((banks, Some(addrs)))
    case _ => None
  }
}
trait LocalStore extends Def {
  override def connectFields(x:Any)(implicit design:Design):Any = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Memory => this.out.connect(x.newIn); this.out
      case x:List[_] if x.forall(_.isInstanceOf[Memory]) => 
        x.foreach { case x:Memory => this.out.connect(x.newIn) }; this.out
      case x => super.connectFields(x)
    }
  }
  override def evaluateFields(x:Any):Any = (this, x) match {
    case ((_:StoreBanks | _:StoreMem), x:Output) => x.connected.map(_.src)
    case ((_:StoreBank), x:Output) => x.singleConnected.map(_.src).getOrElse(null)
    case _ => super.evaluateFields(x)
  }
}
object LocalStore {
  def unapply(n:Any)(implicit design:PIR):Option[(List[Memory], Option[List[Def]], Def)] = n match {
    case StoreMem(mems, addrs, data) => Some((mems, addrs, data))
    case StoreBanks(banks, addrs, data) => Some((banks.flatten, Some(addrs), data))
    case StoreBank(bank, addrs, data) => Some((List(bank), Some(addrs), data))
    case _ => None
  }
}
object WithWriters {
  def unapply(n:Any):Option[List[LocalStore]] = n match {
    case n:Memory => Some(n.writers.toList)
    case _ => None
  }
}

case class CounterIter(counter:Counter, offset:Option[Int])(implicit design:PIR) extends Def 
case class OpDef(op:Op, inputs:List[Def])(implicit design:PIR) extends StageDef
case class ReduceOp(op:Op, input:Def)(implicit design:PIR) extends StageDef
case class AccumOp(op:Op, input:Def, accum:Def)(implicit design:PIR) extends StageDef
// Generated IR from spatial
case class LoadMem(mem:Memory, addrs:Option[List[Def]])(implicit design:PIR) extends LocalLoad
case class StoreMem(mems:List[Memory], addrs:Option[List[Def]], data:Def)(implicit design:PIR) extends LocalStore
case class LoadBanks(banks:List[Memory], addrs:List[Def])(implicit design:PIR) extends LocalLoad
/*
 * @param mems: Mems[Banks]
 * */
case class StoreBanks(mems:List[List[Memory]], addrs:List[Def], data:Def)(implicit design:PIR) extends LocalStore
case class SelectBanks(bankLoads:List[LocalLoad])(implicit design:PIR) extends Def
case class StoreBank(bank:Memory, addrs:List[Def], data:Def)(implicit design:PIR) extends LocalStore

case class FIFOEmpty(mem:Memory)(implicit design:PIR) extends Def
case class FIFOPeak(mem:Memory)(implicit design:PIR) extends Def
case class FIFONumel(mem:Memory)(implicit design:PIR) extends Def

// IR's doesn't matter in spatial. such as valid for counters. Should be dead code eliminated
case class DummyOp()(implicit design:PIR) extends Def
case class Const[T<:AnyVal](value:T)(implicit design:PIR) extends Def

case class ArgInDef()(implicit design:PIR) extends Def


/*
 * Control Nodes
 * */

//case class NotEmpty(mem:Memory) extends Def
//case class NotFull(mem:Memory) extends Def

sealed trait IOType extends Enum
case object Vector extends IOType
case object Scalar extends IOType
case object Control extends IOType

sealed trait ControlStyle extends Enum
case object InnerPipe extends ControlStyle
case object SeqPipe extends ControlStyle
case object MetaPipe extends ControlStyle
case object StreamPipe extends ControlStyle
case object ForkSwitch extends ControlStyle

sealed trait ControlLevel extends Enum
case object InnerControl extends ControlLevel
case object OuterControl extends ControlLevel

