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
  val in = InputField[List[Access]]
  val out = OutputField[List[Access]]

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
case class SparseMem(isDRAM:Boolean=false)(implicit env:Env) extends Memory

case class Lock()(implicit env:Env) extends BlackBox with DefNode[PIRNode] {
  val lock = InputField[PIRNode]
  val unlock = InputField[PIRNode]
  val out = OutputField[List[PIRNode]].tp(Bool).presetVec(1)
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
case class SplitLeader()(implicit env:Env) extends BlackBox {
  val addrIn = InputField[PIRNode]
  val addrOut = OutputField[PIRNode]
  val ctrlOut = OutputField[List[PIRNode]].tp(Bool)
  override def compType(n:IR) = n match {
    case `addrOut` => addrIn.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `addrOut` => addrIn.inferVec
    case `ctrlOut` => addrIn.inferVec
    case _ => super.compVec(n)
  }
}
case class MergeBuffer(ways:Int, par:Int)(implicit env:Env) extends BlackBox with Def { self =>
  val inputs = List.tabulate(ways) { i => new InputField[PIRNode](s"in$i") }
  val bounds = List.tabulate(ways) { i => new InputField[PIRNode](s"bound$i") }
  val init = InputField[PIRNode].tp(Bool).presetVec(1)
  val outBound = OutputField[List[PIRNode]]
  val outInit = OutputField[List[PIRNode]].tp(Bool).presetVec(1)
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
  val in = InputField[PIRNode]
  val dummy = InputField[List[PIRNode]]
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
  val lock = InputField[Lock]
  val key = InputField[PIRNode]
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

  val unlockReadAddr = accums.map { a => a -> List.fill(outerPar) { InputField[Option[PIRNode]].tp(Fix(true, 32, 0)) } }.toMap
  val unlockReadData = accums.map { a => a -> List.fill(outerPar) { OutputField[Option[PIRNode]].tp(a.tp) } }.toMap

  val unlockWriteAddr = accums.map { a => a -> List.fill(outerPar) { InputField[Option[PIRNode]].tp(Fix(true, 32, 0)) } }.toMap
  val unlockWriteData = accums.map { a => a -> List.fill(outerPar) { InputField[Option[PIRNode]].tp(a.tp) } }.toMap
  val unlockWriteAck = accums.map { a => a -> List.fill(outerPar) { OutputField[Option[PIRNode]].tp(Bool).presetVec(1) } }.toMap

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
  val lockAddrs = List.fill(outerPar) { InputField[PIRNode].tp(Fix(true,32,0)) }
  assert(lockAddrs.head.name == "lockAddrs")
  val lockDataIn = accums.map { a => a -> List.fill(memPar) { InputField[PIRNode].tp(a.tp) } }.toMap
  val lockDataOut = accums.map { a => a -> List.fill(memPar) { OutputField[PIRNode].tp(a.tp) } }.toMap
  val lockAcks = List.fill(outerPar) { OutputField[PIRNode].tp(Bool).presetVec(1) }

  val accumMap:Map[Edge[_,_,_],LockAccum] = 
    unlockReadAddr.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockReadData.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockWriteAddr.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockWriteData.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    unlockWriteAck.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    lockDataIn.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ } ++
    lockDataOut.map { case (k,vs) => vs.map { v => (v,k) }.toMap }.reduce { _ ++ _ }

  def treeInMap:Map[Edge[_,_,_],Int] =
    lockInputIns.flatMap { _.flatMap { _.zipWithIndex } }.toMap

  def laneMap:Map[Edge[_,_,_],Int] = //def because of dynamic edges
    unlockReadAddr.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    unlockReadData.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    unlockWriteAddr.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++ 
    unlockWriteData.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    unlockWriteAck.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    lockAddrs.zipWithIndex.toMap ++
    lockDataIn.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
    lockDataOut.map { case (k,vs) => vs.zipWithIndex.toMap }.reduce { _ ++ _ } ++
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
      unlockReadAddr(accumMap(n))(laneMap(n)).inferVec
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
  var accelTopCtrl:ControlTree = _
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
  val in = InputField[GlobalOutput]
  val out = OutputField[List[LocalOutAccess]]
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
  val in = InputField[LocalInAccess]
  val out = OutputField[List[GlobalInput]]
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
  final val out = OutputField[List[PIRNode]]
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
  val ack = InputField[PIRNode]
  out.presetVec(1)
  out.tp(Bool)
}
trait DelayOp extends Def {
  val cycle:Int
  val in = InputField[PIRNode]
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
  val en = InputField[List[PIRNode]].tp(Bool)
  val msg = InputField[PIRNode]
  out.tp(Bool).presetVec(1)
}
case class AssertIf()(implicit env:Env) extends Def {
  val en = InputField[List[PIRNode]].tp(Bool)
  val cond = InputField[List[PIRNode]].tp(Bool)
  val msg = InputField[Option[PIRNode]]
  out.tp(Bool).presetVec(1)
}
case class ExitIf()(implicit env:Env) extends Def {
  val en = InputField[List[PIRNode]].tp(Bool)
  val cond = InputField[List[PIRNode]].tp(Bool)
  val msg = InputField[PIRNode]
  out.tp(Bool).presetVec(1)
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
  val in1 = InputField[PIRNode]
  val in2 = InputField[PIRNode]
  val en = InputField[Set[PIRNode]].tp(Bool)
  val first = InputField[Option[PIRNode]]
  val init = InputField[Option[PIRNode]]
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
  val in = InputField[PIRNode]
  val en = InputField[Set[PIRNode]].tp(Bool)
  val first = InputField[Option[PIRNode]]
  val init = InputField[Option[PIRNode]]
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
case class Shuffle(filled:Any, aid:Int)(implicit env:Env) extends OpNode with Def {
  val from = InputField[PIRNode]
  val to = InputField[PIRNode]
  val base = InputField[PIRNode]
  val offset = InputField[Option[PIRNode]]
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
  val input = InputField[PIRNode]
  override def compType(n:IR) = input.singleConnected.flatMap { _.inferTp }
  out.presetVec(1)
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
  val min = InputField[Option[PIRNode]]
  val step = InputField[Option[PIRNode]]
  val max = InputField[Option[PIRNode]]
  val out = OutputField[List[PIRNode]]
  val isFirstIter = OutputField[List[PIRNode]]
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
  val counter = InputField[Counter]
  out.tp(Fix(true, 32, 0)).presetVec(is.size)
}
case class CounterValid(is:List[Int])(implicit env:Env) extends Def {
  val counter = InputField[Counter]
  out.tp(Bool).presetVec(is.size)
}

abstract class Controller(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val en = InputField[List[PIRNode]].tp(Bool).presetVec(1)
  val parentEn = InputField[Option[Controller]].tp(Bool).presetVec(1)

  val done = OutputField[List[PIRNode]].tp(Bool).presetVec(1)
  val childDone = OutputField[List[PIRNode]].tp(Bool).presetVec(1)
  val stepped = OutputField[List[PIRNode]].tp(Bool).presetVec(1)

  def isForever = this.collectDown[Counter]().exists { _.isForever }
  def hasBranch = this.ctrl.v.get == Fork || this.to[LoopController].fold(false) { _.stopWhen.isConnected }
  val uen = InputField[List[PIRNode]].tp(Bool).presetVec(1)
  val laneValid = OutputField[List[PIRNode]].tp(Bool)

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
  val firstIter = OutputField[List[PIRNode]].tp(Bool).presetVec(1)
  val stopWhen = InputField[Option[PIRNode]].tp(Bool)

  val constLaneValids = new Metadata[List[Option[Boolean]]]("constLaneValids")
}

case class SplitController()(implicit env:Env) extends Controller {
  /*  ------- Fields -------- */
  val splitOn = InputField[PIRNode]
}

case class ControlBlock()(implicit env:Env) extends PIRNode {
  val dummy = InputField[Option[PIRNode]].tp(Bool).presetVec(1)
  val ctrlers = new ChildField[Controller, List[Controller]]("ctrlers")
}

trait MemoryUtil extends CollectorImplicit {

  implicit class MemOp[M<:Memory](n:M) {
    def inAccesses = n.collect[InAccess](visitGlobalIn _)
    def outAccesses = n.collect[OutAccess](visitGlobalOut _)
    def accesses:List[Access] = n.collect[Access](visitGlobalIn _) ++ n.collect[Access](visitGlobalOut _)

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

    def isAccum:Boolean = {
      if (n.accesses.exists { _.progorder.v.isEmpty }) return false
      n.accesses.toList.sortBy { _.progorder.get }.sliding(2,1).exists {
        case List(prev:ReadAccess, next:WriteAccess) =>
          val lca = leastCommonAncesstor(prev.getCtrl, next.getCtrl).get
          lca.ancestorTree.exists { _.isLoop.get }
        case _ => false
      }
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
