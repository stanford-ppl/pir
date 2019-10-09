package pir
package node

import spade.param._
import prism.graph._

trait MemoryNode extends PIRNode {
  /*  ------- Metadata -------- */
  val inits = Metadata[Any]("inits")
  val dims = Metadata[List[Int]]("dims", default=List(1))
  val darkVolume = Metadata[Int]("darkVolume", default=0)
  // Total bank dimension of the original memory
  val banks = Metadata[List[Int]]("banks", default=List(1))
  // Assigned bank ids for this memory. Before splitting, it's List(0 until totlBanks)
  val bankids = Metadata[List[Int]]("bankids") 
  // Number of partitions after splitting
  val numPart = Metadata[Int]("numPart", default=1) 
  val depth = Metadata[Int]("depth", default=1)
  val isInnerAccum = Metadata[Boolean]("isInnerAccum", default=false)
  def getBanks = banks.get
  def getDepth = depth.get
  def bankDims = getBanks.size
  def totalBanks = banks.get.product
  def nBanks:Int = bankids.get.size
  def size:Int = dims.get.product + darkVolume.get // User declared size
  def bankSize:Int = this match {
    case lut:LUT => size // LUT will duplicate data in all banks
    case _ => size /! totalBanks
  }
  def capacity:Int = getDepth * bankSize * nBanks // Total capacity of this memory.
}

abstract class Memory(implicit env:Env) extends MemoryNode with DefNode[PIRNode] {

  val nonBlocking = Metadata[Boolean]("nonBlocking", default=false)
  /*  ------- Fields -------- */
  val in = new InputField[List[Access]]("in")
  val out = new OutputField[List[Access]]("out")

  override def asInput = Some(in)
  override def asOutput = Some(out)
}

case class Reg()(implicit env:Env) extends Memory
case class FIFO()(implicit env:Env) extends Memory
case class SRAM()(implicit env:Env) extends Memory
case class RegFile()(implicit env:Env) extends Memory
case class LUT()(implicit env:Env) extends Memory
case class LockSRAM()(implicit env:Env) extends Memory

case class Lock()(implicit env:Env) extends BlackBox with DefNode[PIRNode] {
  val lock = new InputField[PIRNode]("lock")
  val unlock = new InputField[PIRNode]("unlock")
  val out = new OutputField[List[PIRNode]]("out").tp(Bool).presetVec(1)
  override def asOutput = Some(out)
}
case class Splitter()(implicit env:Env) extends BlackBox {
  def addrIn = getDynamicInputFields[PIRNode]("addrIn")
  def addrOut = getDynamicOutputFields[PIRNode]("addrOut")
  def addAddrIn(xs:Any*) = DynamicInputFields[PIRNode]("addrIn", xs)
  def addAddrOut(num:Int) = DynamicOutputFields[PIRNode]("addrOut", num)
}
case class MergeBuffer(ways:Int, par:Int)(implicit env:Env) extends BlackBox with Def {
  val inputs = List.tabulate(ways) { i => new InputField[PIRNode](s"in$i") }
  val bounds = List.tabulate(ways) { i => new InputField[PIRNode](s"bound$i") }
  val init = new InputField[PIRNode](s"init").tp(Bool).presetVec(1)
  val outBound = new OutputField[List[PIRNode]]("outBound")
  val outInit = new OutputField[List[PIRNode]]("outInit").tp(Bool).presetVec(1)
}
case class Forward()(implicit env:Env) extends Def {
  val in = new InputField[PIRNode]("in")
  val dummy = new InputField[List[PIRNode]]("dummy")
}
case class LockOnKeys()(implicit env:Env) extends Def {
  val lock = new InputField[Lock]("lock")
  val key = new InputField[PIRNode]("key")
  presetVec(1)
  tp(Bool)
}

case class Top()(implicit env:Env) extends PIRNode {
  var topCtrl:ControlTree = _
  var hostInCtrl:ControlTree = _
  var hostOutCtrl:ControlTree = _
  var argFringe:ArgFringe = _
}

trait GlobalContainer extends PIRNode {
  val isDAG = Metadata[Boolean]("isDAG", default=false)
}
case class ArgFringe()(implicit env:Env) extends GlobalContainer {
  val hostInCtrler = new ChildField[HostInController, HostInController]("hostInController")
  val hostOutCtrler = new ChildField[HostOutController, HostOutController]("hostOutController")
}
case class MemoryContainer()(implicit env:Env) extends GlobalContainer
case class ComputeContainer()(implicit env:Env) extends GlobalContainer
case class DRAMFringe()(implicit env:Env) extends GlobalContainer

trait GlobalIO extends PIRNode
case class GlobalInput()(implicit env:Env) extends GlobalIO {
  override def className = "gi"
  val in = new InputField[GlobalOutput]("in")
  val out = new OutputField[List[LocalOutAccess]]("outs")
}
case class GlobalOutput()(implicit env:Env) extends GlobalIO {
  override def className = "go"
  val in = new InputField[LocalInAccess]("in")
  val out = new OutputField[List[GlobalInput]]("outs")
}

case class Context()(implicit env:Env) extends PIRNode {
  val streaming = new Metadata[Boolean]("streaming", default=Some(false))
  val active = new Metadata[Long]("active")
  val state = new Metadata[String]("state")
}

trait Def extends PIRNode with DefNode[PIRNode] {
  final val out = new OutputField[List[PIRNode]]("out")
  override def asOutput = Some(out)
}

case class Const(value:Any)(implicit env:Env) extends Def
case class AccumAck()(implicit env:Env) extends Def {
  val ack = new InputField[PIRNode]("ack")
  tp(Bool)
  presetVec(1)
}
case class PrintIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en").tp(Bool)
  val msg = new InputField[PIRNode]("mgs")
  tp(Bool)
}
case class AssertIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en").tp(Bool)
  val cond = new InputField[List[PIRNode]]("cond").tp(Bool)
  val msg = new InputField[Option[PIRNode]]("msg")
}
case class ExitIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en").tp(Bool)
  val cond = new InputField[List[PIRNode]]("cond").tp(Bool)
  val msg = new InputField[PIRNode]("mgs")
}
trait OpNode extends PIRNode
case class OpDef(op:Opcode)(implicit env:Env) extends OpNode with Def {
  def addInput(xs:Any*) = DynamicInputFields[PIRNode]("input", xs)
  def inputs = getDynamicInputFields[PIRNode]("input")
}
// op can be eigher a string, if from spatial, or a list of reduction op if
// transformed in graph initialization
case class RegAccumFMA(identity:Any)(implicit env:Env) extends OpNode with Def {
  val in1 = new InputField[PIRNode]("in1")
  val in2 = new InputField[PIRNode]("in2")
  val en = new InputField[Set[PIRNode]]("en").tp(Bool)
  val first = new InputField[Option[PIRNode]]("first")
  val init = new InputField[Option[PIRNode]]("init")
  presetVec(1)
}
// op can be eigher a string, if from spatial, or a list of reduction op if
// transformed in graph initialization
case class RegAccumOp(op:Any, identity:Any)(implicit env:Env) extends OpNode with Def {
  val in = new InputField[PIRNode]("in")
  val en = new InputField[Set[PIRNode]]("en").tp(Bool)
  val first = new InputField[Option[PIRNode]]("first")
  val init = new InputField[Option[PIRNode]]("init")
  presetVec(1)
}
// Filled can be "0" or "-0". based on shuffling address or data
case class Shuffle(filled:Any)(implicit env:Env) extends OpNode with Def {
  val from = new InputField[PIRNode]("from")
  val to = new InputField[PIRNode]("to")
  val base = new InputField[PIRNode]("base")
}
case class HostRead()(implicit env:Env) extends Def {
  val input = new InputField[PIRNode]("input")
  presetVec(1)
}
case class HostWrite()(implicit env:Env) extends Def {
  presetVec(1)
}
case class DRAMAddr(dram:DRAM)(implicit env:Env) extends Def {
  presetVec(1)
}
case class Counter(par:Int, isForever:Boolean=false)(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val min = new InputField[Option[PIRNode]]("min")
  val step = new InputField[Option[PIRNode]]("step")
  val max = new InputField[Option[PIRNode]]("max")
  val out = new OutputField[List[PIRNode]]("out")
  val isFirstIter = new OutputField[List[PIRNode]]("isFirstIter")
  override def asOutput = Some(out)

  def iters = this.collectOut[CounterIter]()
  def valids = this.collectOut[CounterValid]()
  def ctrler = this.collectUp[Controller]().head

  // Lane valids that can be statically derived
  val constValids = new Metadata[List[Option[Boolean]]]("constValids")
}

case class CounterIter(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
  tp(Fix(true, 32, 0))
}
case class CounterValid(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
  tp(Bool)
}

abstract class Controller(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val en = new InputField[List[PIRNode]]("en").tp(Bool).presetVec(1)
  val parentEn = new InputField[Option[Controller]]("parentEn").tp(Bool).presetVec(1)

  val done = new OutputField[List[PIRNode]]("done").tp(Bool).presetVec(1)
  val childDone = new OutputField[List[PIRNode]]("childDone").tp(Bool).presetVec(1)
  val stepped = new OutputField[List[PIRNode]]("stepped").tp(Bool).presetVec(1)

  def isForever = this.collectDown[Counter]().exists { _.isForever }
  def hasBranch = this.ctrl.v.get == Fork || this.to[LoopController].fold(false) { _.stopWhen.isConnected }
  val uen = new InputField[List[PIRNode]]("uen").tp(Bool).presetVec(1)
  val laneValid = new OutputField[List[PIRNode]]("laneValid").tp(Bool)

  // Parallelization of the controller. Set during staging.
  val par = new Metadata[Int]("par")
}

case class HostInController()(implicit env:Env) extends Controller
case class HostOutController()(implicit env:Env) extends Controller
case class UnitController()(implicit env:Env) extends Controller
case class TopController()(implicit env:Env) extends Controller
case class LoopController()(implicit env:Env) extends Controller {
  /*  ------- Fields -------- */
  val cchain = new ChildField[Counter, List[Counter]]("cchain")
  val firstIter = new OutputField[List[PIRNode]]("firstIter").tp(Bool).presetVec(1)
  val stopWhen = new InputField[Option[PIRNode]]("stopWhen").tp(Bool)

  val constLaneValids = new Metadata[List[Option[Boolean]]]("constLaneValids")
}

case class ControlBlock()(implicit env:Env) extends PIRNode {
  val ctrlers = new ChildField[Controller, List[Controller]]("ctrlers")
}

trait MemoryUtil extends CollectorImplicit {

  implicit class MemOp[M<:Memory](n:M) {
    def inAccesses = n.collect[InAccess](visitGlobalIn _)
    def outAccesses = n.collect[OutAccess](visitGlobalOut _)
    def accesses:List[Access] = inAccesses ++ outAccesses

    def isFIFO = n match {
      case n:FIFO => true
      case _ => false
    }

    def isLockSRAM = n match {
      case n:LockSRAM => true
      case _ => false
    }

    def addAccess(a:Access):M = {
      a.setMem(n)
      n
    }
  }

  implicit class GlobalOp(n:GlobalContainer) {
    def isArgFringe = n match {
      case n:ArgFringe => true
      case _ => false
    }
  }

  implicit class ControllerOp(n:Controller) {
    def childCtrlers:Seq[Controller] = {
      n.childDone.T.collect { case ctrler:Controller => ctrler }
    }
    def leafCtrlers:Seq[Controller] = {
      val childCtrlers = this.childCtrlers
      if (childCtrlers.isEmpty) List(n)
      else childCtrlers.flatMap { c => c.leafCtrlers }
    }
    def ancestorTreeCtrlers:Seq[Controller] = {
      n.parentEn.T.map { _.ancestorTreeCtrlers }.getOrElse(Nil) :+ n
    }
  }

}
