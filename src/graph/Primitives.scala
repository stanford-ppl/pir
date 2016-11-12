package pir.graph

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.math.max
import scala.reflect.runtime.universe._
import pir.Design
import pir.graph._
import pir.graph.enums._
import pir.graph.mapper.PIRException
import pir.graph.traversal.ForwardRef


abstract class Primitive(implicit val ctrler:Controller, design:Design) extends Node 
/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */
case class CounterChain(name:Option[String])(implicit ctrler:ComputeUnit, design: Design) extends Primitive {
  override val typeStr = "CC"
  /* Fields */
  val _counters = ListBuffer[Counter]()
  def counters:List[Counter] = _counters.toList

  /* Pointers */
  // Pointer to the original copy
  private var _copy:Option[Either[String, CounterChain]] = None
  def copy:Option[Either[String, CounterChain]] = _copy
  def setCopy(cc:String) = { _copy = Some(Left(cc)) }
  def setCopy(cc:CounterChain) = { _copy = Some(Right(cc)); cc.addCopied(this) }

  // List of copies to this original Counterchain 
  private val _copied = ListBuffer[CounterChain]()
  def addCopied(cc:CounterChain) = _copied += cc
  def copied = _copied.toList

  /*
   * Whether CounterChain is a copy of other CounterChain
   * */
  def isCopy = copy.isDefined
  def isLocal = (!isCopy) && (ctrler match { 
    case tt:TileTransfer => tt.mctpe==TileLoad && streaming==false
    case _ => true 
  }) 
  /*
   * Whether CounterChain is not a copy or is a copy and has been updated
   * */
  def isDefined = copy.fold(true) { e => e.isRight }
  /*
   * The original copy of this CounterChain
   * */
  lazy val original = copy.fold(this) { e => e.right.get}

  val wasrams = ListBuffer[SRAMOnWrite]()
  def addWASram(sram:SRAMOnWrite):Unit = {
    wasrams += sram
  }
  var streaming = false
  def isStreaming(s:Boolean) = streaming = s

  override def toUpdate = super.toUpdate

  def outer:Counter = counters.head
  def inner:Counter = counters.last

  def addCounter(ctr:Counter):Unit = {
    _counters.lastOption.foreach { pre =>
      pre.setDep(ctr)
    }
    _counters += ctr
  }

  def addCounters(ctrs:List[Counter]):Unit = {ctrs.foreach { ctr => addCounter(ctr) } }
  def addOuterCounter(ctr:Counter):Unit = {
    _counters.headOption.foreach { next =>
      ctr.setDep(next)
    }
    _counters.insert(0, ctr)
  }

  def this(name:Option[String], bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design) = {
    this(name)
    bds.zipWithIndex.foreach {case ((mi, ma, s),i) => addCounter(Counter(this, mi, ma, s)(ctrler, design)) }
  }

  def apply(num: Int)(implicit ctrler:ComputeUnit, design: Design):Counter = {
    if (isCopy) {
      // Speculatively create extra counters base on need and check bound during update
      addCounters(List.fill(num+1-counters.size)(Counter(this)))
    }
    counters(num)
  }

  def copy(cp:CounterChain):Unit = {
    // Check whether speculative wire allocation was correct
    assert(counters.size <= cp.counters.size, 
      s"Accessed counter ${counters.size-1} of ${this} is out of bound")
    assert(!cp.isCopy, s"Can only copy original CounterChain. Target ${cp} is a copy of ${cp.original}")
    val addiCtrs = List.fill(cp.counters.size-counters.size)(Counter(this))
    addCounters(addiCtrs)
    counters.zipWithIndex.foreach { case(c,i) => c.copy(cp.counters(i)) }
    this.setCopy(cp)
    ctrler.addCChain(this)
  }

}
object CounterChain {
  def apply(bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    new CounterChain(None, bds:_*)
  }
  def apply(name:String, bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design):CounterChain =
    new CounterChain(Some(name), bds:_*)
  /*
   * @param from: User defined name for Controller of the copying CounterChain 
   * @param name: User defined name for Primitive 
   * */
  def copy(from:String, name:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    copy(ForwardRef.getPrimName(from, name))
  }
  /*
   * @param from: Controller of the copying CounterChain 
   * @param name: User defined name for Primitive 
   * */
  def copy(from:ComputeUnit, name:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    copy(ForwardRef.getPrimName(from, name))
  }
  /*
   * @param from: full name of Primitive 
   * */
  def copy(from:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_copy"))
    cc.setCopy(from)
    def updateFunc(cp:Node) = cc.copy(cp.asInstanceOf[CounterChain])
    design.updateLater(from, updateFunc _ )
    cc
  }
  def copy(from:CounterChain)(implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_copy"))
    cc.copy(from)
    cc
  }
}

case class Counter(name:Option[String])(implicit override val ctrler:ComputeUnit, design: Design) extends Primitive {
  override val typeStr = "Ctr"
  /* Fields */
  val min:InPort = InPort(this, s"${this}.min")
  val max:InPort = InPort(this, s"${this}.max")
  val step:InPort = InPort(this, s"${this}.step")
  val out:OutPort = OutPort(this, {s"${this}.out"}) 
  val en:EnInPort = EnInPort(this, s"${this}.en")
  val done:DoneOutPort = DoneOutPort(this, s"${this}.done")
  var _cchain:CounterChain = _
  def cchain:CounterChain = _cchain
  def cchain(cc:CounterChain):Counter = {
    en.disconnect
    done.disconnect
    _cchain = cc
    this
  }
  override def toUpdate = super.toUpdate || cchain==null

  def update(mi:OutPort, ma:OutPort, s:OutPort):Unit = {
    min.connect(mi)
    max.connect(ma)
    step.connect(s)
  }

  def isInner = { en.isConnected && en.from.src.isInstanceOf[EnLUT] }
  def isOuter = { !done.isConnected || done.to.forall{!_.src.isInstanceOf[Counter]} } 
  def next:Counter = {
    val ns = done.to.map(_.src).collect{ case c:Counter => c}
    assert(ns.size==1, s"$this has not exactly 1 next counter ${done.to} ${ns}")
    ns.head
  }
  def prev:Counter = en.from.src.asInstanceOf[Counter]

  def setDep(c:Counter) = { en.connect(c.done) }

  def copy(c:Counter) = {
    assert(min.from==null, 
      s"Overriding existing counter ${this} with min ${c.min}")
    assert(max.from==null, 
      s"Overriding existing counter ${this} with min ${c.max}")
    assert(step.from==null, 
      s"Overriding existing counter ${this} with min ${c.step}")
    def copyOutPort(p:OutPort):OutPort = {
      p.src match {
        case s:Const => s.out
        case s:PipeReg => 
          assert(s.stage.isInstanceOf[EmptyStage])
          assert(s.reg.isInstanceOf[ScalarInPR])
          val ScalarIn(n, scalar) = s.reg.asInstanceOf[ScalarInPR].scalarIn
          val cu = ctrler.asInstanceOf[ComputeUnit]
          val pr = cu.scalarIn(cu.emptyStage, scalar)
          pr.out
        case _ => throw new Exception(s"Don't know how to copy port")
      }
    }
    update(copyOutPort(c.min.from), copyOutPort(c.max.from), copyOutPort(c.step.from))
  } 
}
object Counter {
  def apply(name:Option[String], cc:CounterChain)(implicit ctrler:ComputeUnit, design: Design):Counter = {
    Counter(name).cchain(cc)
  }
  def apply(cchain:CounterChain, min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:ComputeUnit, design: Design):Counter =
    { val c = Counter(None, cchain); c.update(min, max, step); c }
  def apply(name:String, cchain:CounterChain, min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:ComputeUnit, design: Design):Counter =
    { val c = Counter(Some(name), cchain); c.update(min, max, step); c }
  def apply(cchain:CounterChain)(implicit ctrler:ComputeUnit, design: Design):Counter = 
    Counter(None, cchain)
}

abstract class OnChipMem(implicit override val ctrler:InnerController, design:Design) extends Primitive {
  val size:Int
  val banking:Banking
  val buffering:Buffering

  val readPort: ReadOutPort = ReadOutPort(this, s"${this}.rp") 
  val writePort: WriteInPort = WriteInPort(this, s"${this}.wp")

  def isRemoteWrite = writePort.from.src.isInstanceOf[VecIn] 
  def writer:InnerController = {
    writePort.from.src match {
      case VecIn(_, vector) => vector.writer.ctrler.asInstanceOf[InnerController]
      case PipeReg(stage, StorePR(_,_)) if stage==ctrler.stages.last => ctrler
      case p => throw PIRException(s"Unknown SRAM write port ${p}")
    }
  }

  def wtPort(wp:OutPort):this.type = { writePort.connect(wp); this } 
  def wtPort(vec:Vector):this.type = { wtPort(ctrler.newVin(vec).out) }
  def load = readPort
}

trait SRAMOnRead extends OnChipMem {
  val readAddr: RdAddrInPort = RdAddrInPort(this, s"${this}.ra")
  def rdAddr(ra:OutPort):this.type = { 
    readAddr.connect(ra); 
    ra.src match {
      case PipeReg(stage,r) =>
        throw PIRException(s"Currently don't support register to readAddr! sram:${this}")
      case _ =>
    }
    this
  } 
}
trait FIFOOnRead extends OnChipMem {
  /* Control Signals */
  val notEmpty = CtrlOutPort(this, s"$this.notEmpty")
  val dequeueEnable = CtrlInPort(this, s"$this.deqEn")
  //val counter = Counter()
}

trait SRAMOnWrite extends OnChipMem {
  val writeAddr: WtAddrInPort = WtAddrInPort(this, s"${this}.wa")
  var writeCtr:Counter = _
  def wtAddr(wa:OutPort):this.type = { 
    writeAddr.connect(wa)
    this 
  }
  def wtCtr(ct:Counter):this.type = { writeCtr = ct; this }

  override def toUpdate = super.toUpdate || writeCtr == null
}
trait FIFOOnWrite extends OnChipMem {
  var _start:Option[OutPort] = None
  var _end:Option[OutPort] = None 
  def start(op:OutPort):this.type = { _start = Some(op); this }
  def end(op:OutPort):this.type = { _end = Some(op); this }
  def start:Option[OutPort] = _start
  def end:Option[OutPort] = _end 

  /* Control Signals */
  val notFull = CtrlOutPort(this, s"$this.notFull")
  val enqueueEnable = CtrlInPort(this, s"$this.enqEn")
  override def toUpdate = super.toUpdate
}
/** SRAM 
 *  @param name: user defined optional name of SRAM 
 *  @param size: size of SRAM in all dimensions 
 *  @param banking: Banking mode of SRAM
 *  @param buffering: Double buffer mode of sram 
 *  @param writeCtr: TODO what was this again? counter that controls the write enable and used to
 *  calculate write address?
 */
case class SRAM(name: Option[String], size: Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design) 
  extends OnChipMem with SRAMOnRead with SRAMOnWrite {
  override val typeStr = "SRAM"
}
object SRAM {
  //TODO remove after apps are regenerated
  def apply(size:Int, banking:Banking, buffering:Buffering, writeCtr:Counter)(implicit ctrler:InnerController, design: Design): SRAM
    = SRAM(None, size, banking, buffering).wtCtr(writeCtr)
  def apply(name:String, size:Int, banking:Banking, buffering:Buffering, writeCtr:Counter)(implicit ctrler:InnerController, design: Design): SRAM
    = SRAM(Some(name), size, banking, buffering).wtCtr(writeCtr)

  def apply(size:Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design): SRAM
    = SRAM(None, size, banking, buffering)
  def apply(name:String, size:Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design): SRAM
    = SRAM(Some(name), size, banking, buffering)
}

case class FIFO(name: Option[String], size: Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design) 
  extends OnChipMem with FIFOOnRead with FIFOOnWrite {
  override val typeStr = "FIFO"
}
object FIFO {
  def apply(size:Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design): FIFO
    = FIFO(None, size, banking, buffering)
  def apply(name:String, size:Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design): FIFO
    = FIFO(Some(name), size, banking, buffering)
}

case class SemiFIFO(name: Option[String], size: Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design) 
  extends OnChipMem with SRAMOnRead with FIFOOnWrite {
  override val typeStr = "FIFO"
}
object SemiFIFO {
  def apply(size:Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design): SemiFIFO
    = SemiFIFO(None, size, banking, buffering)
  def apply(name:String, size:Int, banking:Banking, buffering:Buffering)(implicit ctrler:InnerController, design: Design): SemiFIFO
    = SemiFIFO(Some(name), size, banking, buffering)
}

trait IO extends Primitive
trait Input extends IO {
  def writer:Output
  def variable:Variable
}
trait Output extends IO 
trait VectorIO[T <: IO] { self:T => 
  def vector:Vector
  def isConnected:Boolean
}

case class ScalarIn(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) 
  extends Input {
  scalar.addReader(this)
  override val typeStr = "ScalIn"
  override def toString = s"${super.toString}($scalar)"
  override def equals(that: Any) = that match {
    case n: ScalarIn => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  override def variable:Scalar = scalar
  override def writer = scalar.writer
  val out = OutPort(this, s"${this}.out")
}

object ScalarIn {
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn = 
    ScalarIn(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn =
    ScalarIn(Some(name), scalar)
}

case class ScalarOut(name: Option[String], scalar:Scalar)(implicit override val ctrler:SpadeController, design: Design) extends Output{
  scalar.setWriter(this)
  override val typeStr = "ScalOut"
  override def toString = s"${super.toString}($scalar)"
  override def equals(that: Any) = that match {
    case n: ScalarOut => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  val in = InPort(this, s"${this}.in")
}
object ScalarOut {
  def apply(scalar:Scalar)(implicit ctrler:SpadeController, design: Design):ScalarOut = 
    ScalarOut(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:SpadeController, design: Design):ScalarOut = 
    ScalarOut(Some(name), scalar)
}

case class VecIn(name: Option[String], vector:Vector)(implicit ctrler:Controller, design: Design) 
  extends Input with VectorIO[Input] {
  vector.addReader(this)
  override val typeStr = "VecIn"
  val out = OutPort(this, {s"${this}.out"}) 
  override def equals(that: Any) = that match {
    case n: VecIn => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  override def variable:Vector = vector
  override def writer = vector.writer
  def isConnected = writer!=null

  /* Associated TokenIn for this VecIn */
  def tokenIn:Option[InPort] = {
    ctrler match {
      case c:SpadeController =>
        val cins = c.ctrlIns.filter{_.asInstanceOf[CtrlInPort].isCtrlIn}
        if (cins.size==0) None
        else {
          assert(cins.size==1, s"$this should only have <= one tokenIn associated with but has ${cins}")
          Some(cins.head)
        }
      case _ => None
    }
  }
}
object VecIn {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(Some(name), vector)
}

class DummyVecIn(name: Option[String], override val vector:DummyVector)(implicit ctrler:Controller, design: Design) extends VecIn(name, vector) {
  override val typeStr = "DVecIn"
  override def writer:DummyVecOut = vector.writer
}

class VecOut(val name: Option[String], val vector:Vector)(implicit override val ctrler:SpadeController, design: Design) extends Output with VectorIO[Output] {
  vector.setWriter(this)
  override val typeStr = "VecOut"
  override def equals(that: Any) = that match {
    case n: VecOut => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  val in = InPort(this, s"${this}.in")
  def isConnected = vector.readers.size!=0
}
object VecOut {
  def apply(vector:Vector)(implicit ctrler:SpadeController, design: Design):VecOut = 
    new VecOut(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:SpadeController, design: Design):VecOut = 
    new VecOut(Some(name), vector)
}

class DummyVecOut(name: Option[String], override val vector:DummyVector)(implicit ctrler:SpadeController, design: Design) extends VecOut(name, vector) {
  override val typeStr = "DVecOut"
  def scalarOuts = vector.scalars.map(_.writer)
}

class FuncUnit(val stage:Stage, oprds:List[OutPort], val op:Op, results:List[InPort])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "FU"
  override val name = None
  val operands = List.tabulate(oprds.size){ i => 
    stage match {
      case wast:WAStage =>
        oprds(i).src match {
          case PipeReg(_, CtrPR(_, ctr)) => wast.srams.right.get.foreach { sram => ctr.cchain.addWASram(sram) }
          case c:Counter => wast.srams.right.get.foreach { sram => c.cchain.addWASram(sram) }
          case _ =>
        }
      case _ =>
    }
    InPort(this, oprds(i), s"${oprds(i)}")
  }
  val out = OutPort(this, s"${this}.out")
  results.foreach { res => 
    res.src match {
      case PipeReg(s, r) if (s!=stage) => 
        throw PIRException(s"Function Unit can only write to current stage")
      case _ =>
    }
    res.connect(out) 
  }
  override def toUpdate = 
    super.toUpdate || operands.map { !_.isConnected }.reduce{_ | _} || !out.isConnected
    val defs:List[Reg] = results.flatMap { _.src match {
        case PipeReg(s, reg) => Some(reg)
        case _ => None
      } 
    }.toList
  def defines(reg:Reg) = defs.contains(reg) 
}

case class Stage(name:Option[String])(implicit override val ctrler:ComputeUnit, design: Design) extends Primitive {
  override val typeStr = "Stage"
  var fu:Option[FuncUnit] = _
  val prs:Map[Reg, PipeReg] = Map.empty
  val defs:Set[Reg] = Set.empty
  val uses:Set[Reg] = Set.empty
  var liveIns:ISet[Reg] = ISet.empty
  var liveOuts:ISet[Reg] = ISet.empty
  override def toUpdate = super.toUpdate || fu==null || (fu.isDefined && fu.get.toUpdate) 

  def addUse(reg:Reg):Unit = { uses += reg }
  def addDef(reg:Reg):Unit = { defs += reg }
  def addLiveIn(reg:Reg):Unit = { liveIns += reg}
  def addLiveOut(reg:Reg):Unit = { liveOuts += reg }
  def isHead = this==ctrler.stages.head
  def isLast = this==ctrler.stages.last
} 
object Stage {
  /* No Sugar API */
  def apply(stage:Stage, operands:List[OutPort], op:Op, results:List[InPort])
            (implicit ctrler:InnerComputeUnit, design:Design):Unit= {
    stage.fu = Some(new FuncUnit(stage, operands, op, results))
    ctrler.addStage(stage)
  }
  /* Sugar API */
  def apply(stage:Stage, op1:OutPort, op:Op, result:InPort)(implicit ctrler:InnerComputeUnit, design:Design):Unit =
    Stage(stage, List(op1), op, List(result))
  def apply(stage:Stage, op1:OutPort, op2:OutPort, op:Op, result:InPort)(implicit ctrler:InnerComputeUnit, design:Design):Unit = 
    Stage(stage, List(op1, op2), op, List(result))
  def apply(stage:Stage, op1:OutPort, op2:OutPort, op3:OutPort, op:Op, result:InPort)(implicit ctrler:InnerComputeUnit, design:Design):Unit =
    Stage(stage, List(op1, op2, op3), op, List(result))

  def reduce(op:Op, init:Const)(implicit ctrler:InnerComputeUnit, design:Design):(AccumStage, PipeReg) = {
    val numStages = (Math.ceil(Math.log(design.arch.numLanes))/Math.log(2)).toInt 
    val rdstages = Stages.reduce(numStages, op) 
    val acc = ctrler.accum(init)
    Stages.accum(ctrler.reduce(rdstages.last), op, acc) 
  }
}
object Stages {
  def apply(n:Int) (implicit ctrler:InnerComputeUnit, design: Design):List[LocalStage] = {
    List.tabulate(n) { i => LocalStage(None) }
  }
  def reduce(n:Int, op:Op) (implicit ctrler:InnerComputeUnit, design: Design):List[ReduceStage] = {
    val preStage = ctrler.stages.last
    val rdStages = List.tabulate(n) {i => 
      new { override val idx = i } with Stage(None) with ReduceStage
    }
    val stages = preStage :: rdStages
    for ( i <- 1 until stages.size ) {
      val preg = ctrler.reduce(stages(i-1))
      val creg = ctrler.reduce(stages(i))
      Stage(stages(i), op1=preg.out, op2=preg.out, op, result=creg.in)
    }
    rdStages
  }
  /* Create an accumulation stage
   * @param operand operand to accumulate. i.e. acc = acc + operand
   * @init initial value of accumulator
   * @op accumulation operand
   * Returns the accumulation stage and PipeReg of the accumulator
   * */
  def accum(operand:PipeReg, op:Op, acc:AccumPR) (implicit ctrler:InnerComputeUnit, design: Design):
    (AccumStage, PipeReg) = {
    val s = AccumStage(acc)
    val areg = ctrler.accum(s, acc)
    Stage(s, op1=operand.out, op2=areg.read, op, areg.in)
    (s, areg)
  }
}
trait LocalStage extends Stage { override val typeStr = s"LStage" }
object LocalStage {
  def apply(name:Option[String])(implicit ctrler:ComputeUnit, design: Design) =
    new Stage(name) with LocalStage
}
trait ReduceStage extends LocalStage {
  val idx:Int
  override val typeStr = s"RedStage"
}
trait AccumStage extends LocalStage {
  val accReg:AccumPR
  override def toUpdate = super.toUpdate || accReg==null
  override val typeStr = s"AccStage"
}
object AccumStage {
  def apply(acc:AccumPR)(implicit ctrler:ComputeUnit, design: Design) = 
    new {override val accReg = acc} with Stage(None) with AccumStage
}
class WAStage (override val name:Option[String])
  (implicit ctrler:ComputeUnit, design: Design) extends Stage(name) {
  var srams:Either[List[String], ListBuffer[SRAMOnWrite]] = _
  override val typeStr = "WAStage"
  override def toUpdate = super.toUpdate || srams==null

  def updateSRAM(n:Node) = {
    srams match {
      case Left(_) => srams = Right(ListBuffer(n.asInstanceOf[SRAMOnWrite]))
      case Right(l) => l += n.asInstanceOf[SRAMOnWrite]
    }
  }

  def updateSRAMs[T](srams:List[T])(implicit ev:TypeTag[T]):WAStage = {
    typeOf[T] match {
      case t if t =:= typeOf[String] => 
        this.srams = Left(srams.asInstanceOf[List[String]])
        srams.asInstanceOf[List[String]].foreach { s =>
          design.updateLater(ForwardRef.getPrimName(ctrler, s), updateSRAM _)
        }
      case t if t <:< typeOf[SRAMOnWrite] => 
        this.srams = Right(srams.asInstanceOf[List[SRAMOnWrite]].to[ListBuffer])
    }
    this
  }

}
object WAStage {
  def apply[T](srams:List[T])(implicit ev:TypeTag[T], ctrler:InnerComputeUnit, design: Design)  = new WAStage(None).updateSRAMs(srams)
}
object WAStages {
  def apply[T](n:Int, srams:List[T]) (implicit ev:TypeTag[T], ctrler:InnerComputeUnit, design: Design):List[WAStage] = {
    val was = List.tabulate(n) { i => WAStage(srams) }
    ctrler.addWAStages(was)
    was
  }
}
trait EmptyStage extends Stage {
  override val typeStr = "EmptyStage"
  fu = None
}
object EmptyStage {
  def apply(name:Option[String])(implicit ctrler:ComputeUnit, design: Design):EmptyStage  = 
    new Stage(name) with EmptyStage
  def apply()(implicit ctrler:ComputeUnit, design: Design):EmptyStage  = 
    new Stage(None) with EmptyStage
}

trait Reg extends Primitive {
  val regId:Int
  override val typeStr = "reg"
  override val name = None
  override def toString = s"${super.toString}_${regId}"
  override def equals(that: Any) = that match {
    case n: Reg => regId == n.regId && ctrler == n.ctrler
    case _ => false 
  }
  override def hashCode:Int = { ctrler.hashCode *10 + regId }
}
object Reg {
  def apply(rid:Int)(implicit ctrler:Controller, design:Design) = new Reg {override val regId = rid}
}
case class LoadPR(override val regId:Int, mem:OnChipMem)(implicit ctrler:InnerController, design: Design)         extends Reg {override val typeStr = "regld"}
case class StorePR(override val regId:Int, mem:OnChipMem)(implicit ctrler:InnerController, design: Design)        extends Reg {override val typeStr = "regst"}
//case class RdAddrPR(override val regId:Int)(implicit ctrler:Controller, design: Design)                           extends Reg {override val typeStr = "regra"; val raPorts = ListBuffer[InPort]()}
case class WtAddrPR(override val regId:Int, waPort:WtAddrInPort)(implicit ctrler:InnerController, sAdesign: Design)         extends Reg {override val typeStr = "regwa"}
case class CtrPR(override val regId:Int, ctr:Counter)(implicit ctrler:ComputeUnit, design: Design)                 extends Reg {override val typeStr = "regct"}
case class ReducePR(override val regId:Int)(implicit ctrler:InnerController, design: Design)                           extends Reg {override val typeStr = "regrd"}
case class AccumPR(override val regId:Int, init:Const)(implicit ctrler:InnerController, design: Design)                extends Reg {override val typeStr = "regac"}
case class VecInPR(override val regId:Int, vecIn:VecIn)(implicit ctrler:Controller, design: Design)               extends Reg {override val typeStr = "regvi"}
case class VecOutPR(override val regId:Int)(implicit ctrler:Controller, design: Design)                           extends Reg {override val typeStr = "regvo"; var vecOut:VecOut = _}
case class ScalarInPR(override val regId:Int, scalarIn:ScalarIn)(implicit ctrler:Controller, design: Design)      extends Reg {override val typeStr = "regsi"}
case class ScalarOutPR(override val regId:Int, scalarOut:ScalarOut)(implicit ctrler:Controller, design: Design)   extends Reg {override val typeStr = "regso"}
/*
 * A Pipeline Register keeping track of which stage (column) and which logical register (row)
 * the PR belongs to
 * @param n Optional user defined name
 * @param regId Register ID the PipeReg mapped to
 **/
case class PipeReg(stage:Stage, reg:Reg)(implicit ctrler:Controller, design: Design) extends Primitive{
  override val name = None
  val in:InPort = InPort(this, s"${this}") 
  val out:OutPort = OutPort(this, {s"${this}"}) 
  def read:OutPort = out
  def write(p:OutPort):Unit = in.connect(p) 
  override val typeStr = "PR"
  override def toString = s"s${stage.id}_${reg}" 
  override def equals(that: Any) = that match {
    case n: PipeReg => stage==n.stage && reg == n.reg && ctrler == n.ctrler
    case _ => false 
  }
}

case class Const(name:Option[String], value:String)(implicit design: Design) extends Node {
  override val typeStr = "Const"
  override def toString = s"Const(${value})"
  val out = OutPort(this, s"Const(${value})")
}
object Const {
  def apply(v:String)(implicit design: Design):Const = Const(None, v)
  def apply(name:String, v:String)(implicit design: Design):Const =
    Const(Some(name), v)
}

