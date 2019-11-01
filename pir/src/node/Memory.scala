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

  override def compType(n:IR) = n match {
    case n if n == this => 
      val was = this.inAccesses.collect { case wa:WriteAccess => wa }
      assertOptionUnify(was, s"${this}.type") { wa => 
        wa.data.inferTp
      }
    case `out` => this.inferTp
    case _ => super.compType(n)
  }
}

case class Reg()(implicit env:Env) extends Memory
case class FIFO()(implicit env:Env) extends Memory
case class SRAM()(implicit env:Env) extends Memory
case class RegFile()(implicit env:Env) extends Memory
case class LUT()(implicit env:Env) extends Memory
case class LockMem(isDRAM:Boolean=false)(implicit env:Env) extends Memory

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
  override def compType(n:IR) = n match {
    case n@OutputField(_,"addrOut") => addrIn(n.dynamicIdx.get).inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case n@OutputField(_,"addrOut") => addrIn(n.dynamicIdx.get).inferVec
    case _ => super.compVec(n)
  }
}
case class MergeBuffer(ways:Int, par:Int)(implicit env:Env) extends BlackBox with Def { self =>
  val inputs = List.tabulate(ways) { i => new InputField[PIRNode](s"in$i") }
  val bounds = List.tabulate(ways) { i => new InputField[PIRNode](s"bound$i") }
  val init = new InputField[PIRNode](s"init").tp(Bool).presetVec(1)
  val outBound = new OutputField[List[PIRNode]]("outBound")
  val outInit = new OutputField[List[PIRNode]]("outInit").tp(Bool).presetVec(1)
  override def compType(n:IR) = n match {
    case `outBound` => assertUnify(bounds, s"$n.outBound.tp") { _.inferTp }.get
    case `out` => self.inferTp
    case `self` => assertUnify(inputs, s"$n.out.tp") { _.inferTp }.get
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `out` => flatReduce(inputs.map { _.inferVec }) { Math.max(_,_) }
    case `outBound` => flatReduce(bounds.map { _.inferVec }) { Math.max(_,_) }
    case _ => super.compVec(n)
  }
}
case class Forward()(implicit env:Env) extends Def {
  val in = new InputField[PIRNode]("in")
  val dummy = new InputField[List[PIRNode]]("dummy")
  override def compType(n:IR) = n match {
    case `out` => in.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `out` => in.inferVec
    case _ => super.compVec(n)
  }
}
case class LockOnKeys()(implicit env:Env) extends Def {
  val lock = new InputField[Lock]("lock")
  val key = new InputField[PIRNode]("key")
  presetVec(1)
  tp(Bool)
}

case class LockAccum(tp:BitType, dims:List[Int], srcCtx:Option[String], name:Option[String], dram:Option[String])
case class LockRMWBlock(
  outerPar:Int,
  //memPar:Int,
  accums:List[LockAccum],
)(implicit env:Env) extends GlobalBlackBox {
  val memPar = outerPar
  val numIns = Metadata[Int]("numIns") // This is not known before instantiate the template

  val unlockReadAddrs = accums.map { a => a -> List.fill(outerPar) { new InputField[Option[PIRNode]](s"unlockReadAddr").tp(Fix(true, 32, 0)) } }.toMap
  val unlockReadDatas = accums.map { a => a -> List.fill(outerPar) { new OutputField[Option[PIRNode]](s"unlockReadData").tp(a.tp) } }.toMap

  val unlockWriteAddrs = accums.map { a => a -> List.fill(outerPar) { new InputField[Option[PIRNode]](s"unlockWriteAddr").tp(Fix(true, 32, 0)) } }.toMap
  val unlockWriteDatas = accums.map { a => a -> List.fill(outerPar) { new InputField[Option[PIRNode]](s"unlockWriteData").tp(a.tp) } }.toMap
  val unlockWriteAcks = accums.map { a => a -> List.fill(outerPar) { new OutputField[Option[PIRNode]](s"unlockWriteAck").tp(Bool).presetVec(1) } }.toMap

  def addLockInputIn = DynamicInputFields[PIRNode](s"lockInputIn")
  def addLockInputOut = DynamicOutputFields[PIRNode](s"lockInputOut")
  // MemPar[NumIn[OuterPar[]]]
  def lockInputIns = {
    val ins = getDynamicInputFields[PIRNode](s"lockInputIn")
    ins.grouped(outerPar).toList.grouped(numIns.get).toList
  }
  // MemPar[NumIn[]]
  def lockInputOuts = {
    val outs = getDynamicOutputFields[PIRNode](s"lockInputOut")
    outs.grouped(numIns.get).toList
  }
  val lockAddrs = List.fill(outerPar) { new InputField[PIRNode](s"lockAddr").tp(Fix(true,32,0)) }
  val lockDataIns = accums.map { a => a -> List.fill(memPar) { new InputField[PIRNode](s"lockDataIn").tp(a.tp) } }.toMap
  val lockDataOuts = accums.map { a => a -> List.fill(memPar) { new OutputField[PIRNode](s"lockDataOut").tp(a.tp) } }.toMap
  val lockAcks = List.fill(outerPar) { new OutputField[PIRNode](s"lockAck").tp(Bool).presetVec(1) }

  val accumMap:Map[Edge[_,_,_],LockAccum] = 
    unlockReadAddrs.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockReadDatas.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockWriteAddrs.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockWriteDatas.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockWriteAcks.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    lockDataIns.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    lockDataOuts.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ }

  def treeInMap:Map[Edge[_,_,_],Int] =
    lockInputIns.flatMap { _.flatMap { _.zipWithIndex } }.toMap

  def laneMap:Map[Edge[_,_,_],Int] = //def because of dynamic edges
    unlockReadAddrs.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    unlockReadDatas.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    unlockWriteAddrs.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++ 
    unlockWriteDatas.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    unlockWriteAcks.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    lockAddrs.zipWithIndex.toMap ++
    lockDataIns.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    lockDataOuts.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    lockInputIns.zipWithIndex.flatMap { case (ins,lane) => ins.flatten.map { _ -> lane } }.toMap ++
    lockInputOuts.zipWithIndex.flatMap { case (outs,lane) => outs.map { _ -> lane } }.toMap ++
    lockAcks.zipWithIndex.toMap

  def inputMap:Map[Edge[_,_,_],Int] = 
    lockInputIns.flatMap { _.zipWithIndex.flatMap { case (ins, inId) => ins.map { _ -> inId } } }.toMap ++
    lockInputOuts.flatMap { _.zipWithIndex }.toMap

  override def compVec(n:IR) = n match {
    case n@OutputField(_,"lockDataOut") => 
      lockAddrs(laneMap(n)).inferVec
    case n@OutputField(_,"unlockReadData") => 
      unlockReadAddrs(accumMap(n))(laneMap(n)).inferVec
    case n@OutputField(_,"lockInputOut") => 
      getDynamicInputFields[PIRNode](s"lockInputIn")(n.dynamicIdx.get).inferVec
    case _ => super.compVec(n)
  }
  override def compType(n:IR) = n match {
    case n@OutputField(_,"lockInputOut") => 
      getDynamicInputFields[PIRNode](s"lockInputIn")(n.dynamicIdx.get).inferTp
    case _ => super.compType(n)
  }

  def isDRAM = assertUnify(accums, s"$this have both DRAM and SRAm in accums=${accums}") { _.dram.nonEmpty }.get
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
case class BlackBoxContainer()(implicit env:Env) extends GlobalContainer
case class DRAMFringe()(implicit env:Env) extends GlobalContainer

trait GlobalIO extends PIRNode
case class GlobalInput()(implicit env:Env) extends GlobalIO {
  override def className = "gi"
  val in = new InputField[GlobalOutput]("in")
  val out = new OutputField[List[LocalOutAccess]]("outs")
  override def compType(n:IR) = n match {
    case `out` => in.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `out` => in.inferVec
    case _ => super.compVec(n)
  }
}
case class GlobalOutput()(implicit env:Env) extends GlobalIO {
  override def className = "go"
  val in = new InputField[LocalInAccess]("in")
  val out = new OutputField[List[GlobalInput]]("outs")
  override def compType(n:IR) = n match {
    case `out` => in.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `out` => in.inferVec
    case _ => super.compVec(n)
  }
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

case class Const(value:Any)(implicit env:Env) extends Def {
  override def compType(n:IR) = n match { // Value int can have long type
    case `out` => 
      val tp = value match {
        case _:Boolean => Bool
        case _:Int => Fix(true, 32, 0)
        case _:Float => Flt(23, 8)
        case _:Double => Flt(23, 8)
        case _:String => Text
        case (_:Boolean)::_ => Bool
        case (_:Int)::_ => Fix(true, 32, 0)
        case (_:Float)::_ => Flt(23, 8)
        case (_:Double)::_ => Flt(23, 8)
      }
      Some(tp)
    case _ => super.compType(n)
  }
  out.presetVec(value match {
    case (l:List[_]) => l.size
    case _ => 1
  })
}
case class AccumAck()(implicit env:Env) extends Def {
  val ack = new InputField[PIRNode]("ack")
  out.presetVec(1)
  out.tp(Bool)
}
trait DelayOp extends Def {
  val cycle:Int
  val in = new InputField[PIRNode]("in")
  override def compVec(n:IR) = n match {
    case `out` => in.inferVec
    case _ => super.compVec(n)
  }
  override def compType(n:IR) = n match {
    case `out` => in.inferTp
    case _ => super.compType(n)
  }
}
// Mapped to CU retiming buffer
case class Delay(cycle:Int)(implicit env:Env) extends DelayOp
// Mapped to PMU 
case class ScratchpadDelay(cycle:Int)(implicit env:Env) extends DelayOp with BlackBox
case class PrintIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en").tp(Bool)
  val msg = new InputField[PIRNode]("mgs")
  out.tp(Bool)
  override def compVec(n:IR) = n match {
    case `out` => msg.inferVec
    case _ => super.compVec(n)
  }
}
case class AssertIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en").tp(Bool)
  val cond = new InputField[List[PIRNode]]("cond").tp(Bool)
  val msg = new InputField[Option[PIRNode]]("msg")
  out.tp(Bool)
  override def compVec(n:IR) = n match {
    case `out` => msg.inferVec
    case _ => super.compVec(n)
  }
}
case class ExitIf()(implicit env:Env) extends Def {
  val en = new InputField[List[PIRNode]]("en").tp(Bool)
  val cond = new InputField[List[PIRNode]]("cond").tp(Bool)
  val msg = new InputField[PIRNode]("mgs")
  out.tp(Bool)
  override def compVec(n:IR) = n match {
    case `out` => msg.inferVec
    case _ => super.compVec(n)
  }
}
trait OpNode extends PIRNode
case class OpDef(op:Opcode)(implicit env:Env) extends OpNode with Def {
  def addInput(xs:Any*) = DynamicInputFields[PIRNode]("input", xs)
  def inputs = getDynamicInputFields[PIRNode]("input")
  override def compType(n:IR) = (n,op) match {
    case (`out`,_:BitOp) => Some(Bool)
    case (`out`,_:TextOp) => Some(Text)
    case (`out`,FixSLA | FixSRA) => inputs(0).inferTp
    case (`out`,_:FixOp | _:FltOp) => assertUnify(inputs, s"$n.tp") { _.inferTp }.get
    case (`out`,Mux) => assertUnify(inputs.slice(1,3), s"$n.tp") { _.inferTp }.get
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = (n,op) match {
    case (`out`,_:FixOp | _:FltOp | _:BitOp | _:TextOp | Mux | BitsAsData) => 
      flatReduce(inputs.map{ _.inferVec}) { Math.max(_,_) }
    case _ => super.compVec(n)
  }
}
// op can be eigher a string, if from spatial, or a list of reduction op if
// transformed in graph initialization
case class RegAccumFMA(identity:Any)(implicit env:Env) extends OpNode with Def {
  val in1 = new InputField[PIRNode]("in1")
  val in2 = new InputField[PIRNode]("in2")
  val en = new InputField[Set[PIRNode]]("en").tp(Bool)
  val first = new InputField[Option[PIRNode]]("first")
  val init = new InputField[Option[PIRNode]]("init")
  override def compType(n:IR) = n match {
    case `out` => in1.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `en` => en.connected.map { o => o.inferVec }.maxOption.getOrElse(Some(1))
    case _ => super.compVec(n)
  }
  out.presetVec(1)
}
// op can be eigher a string, if from spatial, or a list of reduction op if
// transformed in graph initialization
case class RegAccumOp(op:Any, identity:Any)(implicit env:Env) extends OpNode with Def {
  val in = new InputField[PIRNode]("in")
  val en = new InputField[Set[PIRNode]]("en").tp(Bool)
  val first = new InputField[Option[PIRNode]]("first")
  val init = new InputField[Option[PIRNode]]("init")
  override def compType(n:IR) = n match {
    case `out` => in.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `en` => en.connected.map { o => o.inferVec }.maxOption.getOrElse(Some(1))
    case _ => super.compVec(n)
  }
  out.presetVec(1)
}
// Filled can be "0" or "-0". based on shuffling address or data
case class Shuffle(filled:Any)(implicit env:Env) extends OpNode with Def {
  val from = new InputField[PIRNode]("from")
  val to = new InputField[PIRNode]("to")
  val base = new InputField[PIRNode]("base")
  override def compType(n:IR) = n match {
    case `out` => base.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `out` => to.inferVec
    case `from` | `base` => 
      zipMap(base.singleConnected.get.inferVec, from.singleConnected.get.inferVec) { case (a,b) => Math.max(a,b) }
    case _ => super.compVec(n)
  }
}
case class HostRead()(implicit env:Env) extends Def {
  val input = new InputField[PIRNode]("input")
  out.presetVec(1)
  out.tp(Fix(true,64,0))
}
case class HostWrite()(implicit env:Env) extends Def {
  out.presetVec(1)
  out.tp(Fix(true,64,0))
}
case class DRAMAddr(dram:DRAM)(implicit env:Env) extends Def {
  out.presetVec(1)
  out.tp(Fix(true,64,0))
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
  // Lane iters that can be statically derived
  val constIters = new Metadata[List[Option[Int]]]("constIters")
}

case class CounterIter(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
  out.tp(Fix(true, 32, 0)).presetVec(is.size)
}
case class CounterValid(is:List[Int])(implicit env:Env) extends Def {
  val counter = new InputField[Counter]("counter")
  out.tp(Bool).presetVec(is.size)
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

  override def compVec(n:IR) = n match {
    case `laneValid` => par.v
    case _ => super.compVec(n)
  }

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
  val dummy = new InputField[Option[PIRNode]]("dummy").tp(Bool).presetVec(1)
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

    def isLockMem = n match {
      case n:LockMem => true
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
