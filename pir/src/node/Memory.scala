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
  val accessGroup = Metadata[ListBuffer[List[Int]]]("accessGroup", default=ListBuffer.empty[List[Int]])
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
}
case class PrintIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en")
  val msg = new InputField[PIRNode]("mgs")
}
case class AssertIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en")
  val cond = new InputField[List[PIRNode]]("cond")
  val msg = new InputField[PIRNode]("mgs")
}
case class ExitIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en")
  val cond = new InputField[List[PIRNode]]("cond")
  val msg = new InputField[PIRNode]("mgs")
}
trait OpNode extends PIRNode
case class OpDef(op:Opcode)(implicit env:Env) extends OpNode with Def {
  def addInput(xs:Any*) = DynamicInputFields[PIRNode]("input", xs)
  def inputs = getDynamicInputFields[PIRNode]("input")
}
// op can be eigher a string, if from spatial, or a list of reduction op if
// transformed in graph initialization
case class RegAccumFMA()(implicit env:Env) extends OpNode with Def {
  val in1 = new InputField[PIRNode]("in1")
  val in2 = new InputField[PIRNode]("in2")
  val en = new InputField[Set[PIRNode]]("en")
  val first = new InputField[Option[PIRNode]]("first")
  val init = new InputField[Option[PIRNode]]("init")
}
// op can be eigher a string, if from spatial, or a list of reduction op if
// transformed in graph initialization
case class RegAccumOp(op:Any)(implicit env:Env) extends OpNode with Def {
  val in = new InputField[PIRNode]("in")
  val en = new InputField[Set[PIRNode]]("en")
  val first = new InputField[Option[PIRNode]]("first")
  val init = new InputField[Option[PIRNode]]("init")
}
// Filled can be "0" or "-0". based on shuffling address or data
case class Shuffle(filled:Any)(implicit env:Env) extends OpNode with Def {
  val from = new InputField[PIRNode]("from")
  val to = new InputField[PIRNode]("to")
  val base = new InputField[PIRNode]("base")
}
case class HostRead()(implicit env:Env) extends Def {
  val input = new InputField[PIRNode]("input")
}
case class HostWrite()(implicit env:Env) extends Def
case class DRAMAddr(dram:DRAM)(implicit env:Env) extends Def
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
}

case class CounterIter(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
}
case class CounterValid(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
}

abstract class Controller(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val en = new InputField[List[PIRNode]]("en")
  val parentEn = new InputField[Option[PIRNode]]("parentEn")

  val done = new OutputField[List[PIRNode]]("done")
  val childDone = new OutputField[List[PIRNode]]("childDone")

  def isForever = this.collectDown[Counter]().exists { _.isForever }
  def hasBranch = this.ctrl.v.get == Fork || this.to[LoopController].fold(false) { _.stopWhen.isConnected }

  val par = new Metadata[Int]("par")
}

case class HostInController()(implicit env:Env) extends Controller
case class HostOutController()(implicit env:Env) extends Controller
case class UnitController()(implicit env:Env) extends Controller
case class TopController()(implicit env:Env) extends Controller
case class LoopController()(implicit env:Env) extends Controller {
  /*  ------- Fields -------- */
  val cchain = new ChildField[Counter, List[Counter]]("cchain")
  val firstIter = new OutputField[List[PIRNode]]("firstIter")
  val stopWhen = new InputField[Option[PIRNode]]("stopWhen")
}

case class ControlBlock()(implicit env:Env) extends PIRNode {
  val ctrlers = new ChildField[Controller, List[Controller]]("ctrlers")
}

trait MemoryUtil extends CollectorImplicit {

  implicit class MemOp[M<:Memory](n:M) {
    def inAccesses = n.collect[InAccess](visitGlobalIn _)
    def outAccesses = n.collect[OutAccess](visitGlobalOut _)
    def accesses = inAccesses ++ outAccesses

    def isFIFO = n match {
      case n:FIFO => true
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
    def isLeaf = childCtrlers.isEmpty
    def leaves:Seq[Controller] = {
      val childCtrlers = this.childCtrlers
      if (childCtrlers.isEmpty) List(n)
      else childCtrlers.flatMap { c => c.leaves }
    }
  }

}
