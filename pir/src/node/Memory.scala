package pir
package node

import spade.param._
import prism.graph._

trait MemoryNode extends PIRNode {
  /*  ------- Metadata -------- */
  val inits = Metadata[Any]("inits")
  val dims = Metadata[List[Int]]("dims", default=List(1))
  // Total bank dimension of the original memory
  val banks = Metadata[List[Int]]("banks", default=List(1))
  // Assigned bank ids for this memory. Before splitting, it's List(0 until totlBanks)
  val bankids = Metadata[List[Int]]("bankids") 
  val depth = Metadata[Int]("depth", default=1)
  val isInnerAccum = Metadata[Boolean]("isInnerAccum", default=false)
  def getBanks = banks.get
  def getDepth = depth.get
  def bankDims = getBanks.size
  def totalBanks = banks.get.product
  def nBanks:Int = bankids.get.size
  def size:Int = dims.get.product // User declared size
  def bankSize:Int = size /! totalBanks
  def capacity:Int = getDepth * bankSize * nBanks // Total capacity of this memory.
}

abstract class Memory(implicit env:Env) extends MemoryNode with DefNode[PIRNode] {

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
trait OpNode extends PIRNode
case class OpDef(op:Opcode)(implicit env:Env) extends OpNode with Def {
  //val input = new InputField[List[PIRNode]]("input")
  def addInput(xs:Any*) = {
    xs.foreach { x => new InputField[PIRNode]("input").dynamic(true).apply(x) }
    this
  }
  def inputs:List[InputField[PIRNode]] = localIns.filter { _.as[Field[_]].name == "input" }.toList.as
}
case class PrintIf()(implicit env:Env) extends OpNode with Def {
  val en = new InputField[List[PIRNode]]("en")
  val msg = new InputField[PIRNode]("mgs")
}
case class AssertIf()(implicit env:Env) extends OpNode with Def {
  val en = new InputField[List[PIRNode]]("en")
  val cond = new InputField[List[PIRNode]]("cond")
  val msg = new InputField[PIRNode]("mgs")
}
case class ExitIf()(implicit env:Env) extends OpNode with Def {
  val en = new InputField[List[PIRNode]]("en")
  val cond = new InputField[List[PIRNode]]("cond")
  val msg = new InputField[PIRNode]("mgs")
}
// op can be eigher a string, if from spatial, or a list of reduction op if
// transformed in graph initialization
case class RegAccumOp(op:Any)(implicit env:Env) extends OpNode with Def {
  val in = new InputField[PIRNode]("input")
  val en = new InputField[Set[PIRNode]]("en")
  val first = new InputField[PIRNode]("first")
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
case class CountAck()(implicit env:Env) extends Def {
  val input = new InputField[List[PIRNode]]("input")
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
}

case class CounterIter(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
}
case class CounterValid(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
}

abstract class Controller(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val en = new InputField[Option[PIRNode]]("en")
  val parentEn = new InputField[Option[PIRNode]]("parentEn")
  val stopWhen = new InputField[Option[PIRNode]]("stopWhen")

  val valid = new OutputField[List[PIRNode]]("valid")
  val done = new OutputField[List[PIRNode]]("done")
  val childDone = new OutputField[List[PIRNode]]("childDone")

  def isForever = this.collectDown[Counter]().exists { _.isForever }

  val par = new Metadata[Int]("par")
}

case class HostInController()(implicit env:Env) extends Controller
case class HostOutController()(implicit env:Env) extends Controller
case class UnitController()(implicit env:Env) extends Controller
case class TopController()(implicit env:Env) extends Controller
case class LoopController()(implicit env:Env) extends Controller {
  /*  ------- Fields -------- */
  val cchain = new ChildField[Counter, List[Counter]]("cchain")
}

case class ControlBlock()(implicit env:Env) extends PIRNode {
  val ctrlers = new ChildField[Controller, List[Controller]]("ctrlers")
}

trait MemoryUtil extends CollectorImplicit {

  implicit class MemOp(n:Memory) {
    def inAccesses = n.collect[InAccess](visitGlobalIn _)
    def outAccesses = n.collect[OutAccess](visitGlobalOut _)
    def accesses = inAccesses ++ outAccesses

    def isFIFO = n match {
      case n:FIFO => true
      case _ => false
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
      n.valid.T.collect { case ctrler:Controller => ctrler }
    }
    def isLeaf = childCtrlers.isEmpty
    def leaves:Seq[Controller] = {
      val childCtrlers = this.childCtrlers
      if (childCtrlers.isEmpty) List(n)
      else childCtrlers.flatMap { c => c.leaves }
    }
  }

}
