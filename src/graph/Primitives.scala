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
  var counters:List[Counter] = Nil 
  /* Pointers */
  var copy:Option[CounterChain] = None
  var isCopy = false
  val wasrams = ListBuffer[SRAM]()
  var streaming = false
  def isStreaming(s:Boolean) = streaming = s

  override def toUpdate = super.toUpdate

  def outer:Counter = counters.head
  def inner:Counter = counters.last

  def apply(num: Int)(implicit ctrler:ComputeUnit, design: Design):Counter = {
    if (isCopy) {
      // Speculatively create extra counters base on need and check bound during update
      this.counters = counters ++ List.tabulate(num+1-counters.size) { i =>Counter(this) }
    }
    counters(num)
  }
  def copy(cp:CounterChain):Unit = {
    // Check whether speculative wire allocation was correct
    assert(counters.size <= cp.counters.size, 
      s"Accessed counter ${counters.size-1} of ${this} is out of bound")
    val addiCtrs = (counters.size until cp.counters.size).map {i => Counter(this)}
    counters = counters ++ addiCtrs
    counters.zipWithIndex.foreach { case(c,i) => 
      c.copy(cp.counters(i))
    }
    updateDep
    this.copy = Some(cp)
  }
  def update(bds: Seq[(OutPort, OutPort, OutPort)]):Unit = {
    counters = bds.zipWithIndex.map {case ((mi, ma, s),i) => Counter(this, mi, ma, s)}.toList
    updateDep
    this.copy = None 
  }

  def updateDep = {
    for (i <- 0 until counters.size - 1) {
      counters(i).setDep(counters(i+1))  
    }
  }
}
object CounterChain {
  def apply(bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design):CounterChain =
    {val c = CounterChain(None); c.update(bds); c}
  def apply(name:String, bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design):CounterChain =
    {val c = CounterChain(Some(name)); c.update(bds); c}
  def copy(from:String, name:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_${name}_copy"))
    cc.isCopy = true
    def updateFunc(cp:Node) = cc.copy(cp.asInstanceOf[CounterChain])
    design.updateLater(ForwardRef.getPrimName(from, name), updateFunc _ )
    cc
  }
  def copy(from:ComputeUnit, name:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    copy(from.name.getOrElse(""), name)
  }
  def copy(from:CounterChain)(implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_copy"))
    cc.isCopy = true
    cc.copy(from)
    cc
  }
}

case class Counter(name:Option[String], cchain:CounterChain)(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "Ctr"
  /* Fields */
  val min:InPort = InPort(this, s"${this}.min")
  val max:InPort = InPort(this, s"${this}.max")
  val step:InPort = InPort(this, s"${this}.step")
  val out:CtrOutPort = CtrOutPort(this, {s"${this}.out"}) 
  val en:EnInPort = EnInPort(this, s"${this}.en")
  val done:DoneOutPort = DoneOutPort(this, s"${this}.done")
  override def toUpdate = super.toUpdate 

  def update(mi:OutPort, ma:OutPort, s:OutPort):Unit = {
    min.connect(mi)
    max.connect(ma)
    step.connect(s)
  }

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
object Counter{
  def apply(cchain:CounterChain, min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:Controller, design: Design):Counter =
    { val c = Counter(None, cchain); c.update(min, max, step); c }
  def apply(name:String, cchain:CounterChain, min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:Controller, design: Design):Counter =
    { val c = Counter(Some(name), cchain); c.update(min, max, step); c }
  def apply(cchain:CounterChain)(implicit ctrler:Controller, design: Design):Counter = 
    Counter(None, cchain)
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
case class SRAM(name: Option[String], size: Int, banking:Banking, doubleBuffer:Boolean, 
  writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit override val ctrler:ComputeUnit, design: Design) 
  extends Primitive {
  override val typeStr = "SRAM"

  val readAddr: RdAddrInPort = RdAddrInPort(this, s"${this}.ra")
  val writeAddr: WtAddrInPort = WtAddrInPort(this, s"${this}.wa")
  val readPort: ReadOutPort = ReadOutPort(this, s"${this}.rp") 
  val writePort: WriteInPort = WriteInPort(this, s"${this}.wp")

  override def toUpdate = super.toUpdate || !readAddr.isConnected || 
    !writeAddr.isConnected || !writePort.isConnected || !readPort.isConnected

  def writer = {
    writePort.from.src match {
      case VecIn(_, vec) => vec.writer.ctrler
      case r:StorePR => ctrler.asInstanceOf[ComputeUnit] 
      case p => throw PIRException(s"Unknown SRAM write port ${p}")
    }
  }

  def rdAddr(ra:OutPort):SRAM = { 
    readAddr.connect(ra); 
    ra.src match {
      case PipeReg(stage,r) =>
        throw PIRException(s"Currently don't support register to readAddr! sram:${this}")
        //val reg:RdAddrPR = r.asInstanceOf[RdAddrPR]
        //reg.raPorts += readAddr 
      case _ =>
    }
    this
  } 
  def wtAddr(wa:OutPort):SRAM = { 
    writeAddr.connect(wa)
    this 
  }
  def wtPort(wp:OutPort):SRAM = { writePort.connect(wp); this } 
  def wtPort(vec:Vector):SRAM = wtPort(ctrler.vecIn(vec).out)

  def load = readPort

}
object SRAM {
  /* Remote Write */
  def apply(size:Int, vec:Vector, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(None, size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).wtPort(vec)
  def apply(name:String, size:Int, vec:Vector, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(Some(name), size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).wtPort(vec)
  def apply(size:Int, vec:Vector, readAddr:OutPort, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(None, size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).rdAddr(readAddr).wtPort(vec)
  def apply(size:Int, vec:Vector, readAddr:OutPort, writeAddr:OutPort, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(None, size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).rdAddr(readAddr).wtAddr(writeAddr).wtPort(vec)
  def apply(name:String, size:Int, vec:Vector, readAddr:OutPort, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(Some(name), size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).rdAddr(readAddr).wtPort(vec)
  def apply(name:String, size:Int, vec:Vector, readAddr:OutPort, writeAddr:OutPort, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(Some(name), size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).rdAddr(readAddr).wtAddr(writeAddr).wtPort(vec)

  /* Local Write */
  def apply(size:Int, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(None, size, banking, doubleBuffer, writeCtr, swapRead, swapWrite)
  def apply(name:String, size:Int, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(Some(name), size, banking, doubleBuffer, writeCtr, swapRead, swapWrite)
  def apply(size:Int, readAddr:OutPort, writeAddr:OutPort, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(None, size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).rdAddr(readAddr).wtAddr(writeAddr)
  def apply(name:String, size:Int, readAddr:OutPort, writeAddr:OutPort, banking:Banking, doubleBuffer:Boolean, writeCtr:Counter, swapRead:Counter, swapWrite:Counter)(implicit ctrler:ComputeUnit, design: Design): SRAM
    = SRAM(Some(name), size, banking, doubleBuffer, writeCtr, swapRead, swapWrite).rdAddr(readAddr).wtAddr(writeAddr)
}

trait Output extends Primitive
trait Input extends Primitive
case class ScalarIn(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) 
  extends Input{
  scalar.addReader(this)
  override val typeStr = "ScalIn"
  override def toString = s"${super.toString}($scalar)"
  override def equals(that: Any) = that match {
    case n: ScalarIn => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  val out = ScalarInOutPort(this, s"${this}.out")
}

object ScalarIn {
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn = 
    ScalarIn(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn =
    ScalarIn(Some(name), scalar)
}

case class ScalarOut(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) extends Output{
  scalar.setWriter(this)
  override val typeStr = "ScalOut"
  override def toString = s"${super.toString}($scalar)"
  override def equals(that: Any) = that match {
    case n: ScalarOut => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  val in = ScalarOutInPort(this, s"${this}.out")
}
object ScalarOut {
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(Some(name), scalar)
}

case class VecIn(name: Option[String], vector:Vector)(implicit ctrler:Controller, design: Design) 
  extends Input{
  vector.addReader(this)
  override val typeStr = "VecIn"
  val out = VecInOutPort(this, {s"${this}.out"}) 
  override def equals(that: Any) = that match {
    case n: VecIn => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
}

object VecIn {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(Some(name), vector)
}

case class VecOut(name: Option[String], vector:Vector)(implicit ctrler:Controller, design: Design) extends Output{
  vector.setWriter(this)
  override val typeStr = "VecOut"
  override def equals(that: Any) = that match {
    case n: VecOut => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  val in = VecOutInPort(this, s"${this}.in")
}
object VecOut {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    VecOut(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    VecOut(Some(name), vector)
}

class FuncUnit(val stage:Stage, oprds:List[OutPort], o:Op, reses:List[InPort])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "FU"
  override val name = None
  val operands = List.tabulate(oprds.size){ i => InPort(this, oprds(i), s"${this}.oprd") }
  val op = o
  val out = OutPort(this, s"${this}.out")
  reses.foreach { _.connect(out) }
  override def toUpdate = 
    super.toUpdate || operands.map { !_.isConnected }.reduce{_ | _} || !out.isConnected
  val defs:List[Reg] = out.to.flatMap { _.src match {
        case PipeReg(s, reg) => if (s == stage) Some(reg) else None
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
} 
object Stage {
  /* No Sugar API */
  def apply(stage:Stage, operands:List[OutPort], op:Op, results:List[InPort])
            (implicit ctrler:ComputeUnit, design:Design):Unit= {
    stage.fu = Some(new FuncUnit(stage, operands, op, results))
    ctrler.addStage(stage)
  }
  /* Sugar API */
  def apply(stage:Stage, op1:OutPort, op:Op, result:InPort)(implicit ctrler:ComputeUnit, design:Design):Unit =
    Stage(stage, List(op1), op, List(result))
  def apply(stage:Stage, op1:OutPort, op2:OutPort, op:Op, result:InPort)(implicit ctrler:ComputeUnit, design:Design):Unit = 
    Stage(stage, List(op1, op2), op, List(result))
  def apply(stage:Stage, op1:OutPort, op2:OutPort, op3:OutPort, op:Op, result:InPort)(implicit ctrler:ComputeUnit, design:Design):Unit =
    Stage(stage, List(op1, op2, op3), op, List(result))
  def reduce(op:Op, init:Const)(implicit ctrler:ComputeUnit, design:Design):(AccumStage, PipeReg) = {
    val numStages = (Math.ceil(Math.log(design.arch.numLanes))/Math.log(2)).toInt 
    val rdstages = Stages.reduce(numStages, op) 
    val acc = ctrler.accum(init)
    Stages.accum(ctrler.reduce(rdstages.last), op, acc) 
  }
}
object Stages {
  def apply(n:Int) (implicit ctrler:ComputeUnit, design: Design):List[LocalStage] = {
    List.tabulate(n) { i => LocalStage(None) }
  }
  def reduce(n:Int, op:Op) (implicit ctrler:ComputeUnit, design: Design):List[ReduceStage] = {
    val preStage = ctrler.stages.last
    val rdStages = List.tabulate(n) {i => 
      new { override val idx = i } with Stage(None) with ReduceStage
    }
    val stages = preStage :: rdStages
    for ( i <- 1 until stages.size ) {
      val preg = ctrler.reduce(stages(i-1))
      val creg = ctrler.reduce(stages(i))
      Stage(stages(i), op1=preg.out, op, result=creg.in)
    }
    rdStages
  }
  /* Create an accumulation stage
   * @param operand operand to accumulate. i.e. acc = acc + operand
   * @init initial value of accumulator
   * @op accumulation operand
   * Returns the accumulation stage and PipeReg of the accumulator
   * */
  def accum(operand:PipeReg, op:Op, acc:AccumPR) (implicit ctrler:ComputeUnit, design: Design):
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
  var srams:Either[List[String], ListBuffer[SRAM]] = _
  override val typeStr = "WAStage"
  override def toUpdate = super.toUpdate || srams==null

  def updateSRAM(n:Node) = {
    srams match {
      case Left(_) => srams = Right(ListBuffer(n.asInstanceOf[SRAM]))
      case Right(l) => l += n.asInstanceOf[SRAM]
    }
  }

  def updateSRAMs[T](srams:List[T])(implicit ev:TypeTag[T]):WAStage = {
    typeOf[T] match {
      case t if t =:= typeOf[String] => 
        this.srams = Left(srams.asInstanceOf[List[String]])
        srams.asInstanceOf[List[String]].foreach { s =>
          design.updateLater(ForwardRef.getPrimName(ctrler, s), updateSRAM _)
        }
      case t if t =:= typeOf[SRAM] => 
        this.srams = Right(srams.asInstanceOf[List[SRAM]].to[ListBuffer])
    }
    this
  }

}
object WAStage {
  def apply[T](srams:List[T])(implicit ev:TypeTag[T], ctrler:ComputeUnit, design: Design)  = new WAStage(None).updateSRAMs(srams)
}
object WAStages {
  def apply[T](n:Int, srams:List[T]) (implicit ev:TypeTag[T], ctrler:ComputeUnit, design: Design):List[WAStage] = {
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
case class LoadPR(override val regId:Int, rdPort:ReadOutPort)(implicit ctrler:Controller, design: Design)         extends Reg {override val typeStr = "regld"}
case class StorePR(override val regId:Int, wtPort:WriteInPort)(implicit ctrler:Controller, design: Design)        extends Reg {override val typeStr = "regst"}
//case class RdAddrPR(override val regId:Int)(implicit ctrler:Controller, design: Design)                           extends Reg {override val typeStr = "regra"; val raPorts = ListBuffer[InPort]()}
case class WtAddrPR(override val regId:Int, waPort:InPort)(implicit ctrler:Controller, sAdesign: Design)         extends Reg {override val typeStr = "regwa"}
case class CtrPR(override val regId:Int, ctr:Counter)(implicit ctrler:Controller, design: Design)                 extends Reg {override val typeStr = "regct"}
case class ReducePR(override val regId:Int)(implicit ctrler:Controller, design: Design)                           extends Reg {override val typeStr = "regrd"}
case class AccumPR(override val regId:Int, init:Const)(implicit ctrler:Controller, design: Design)                extends Reg {override val typeStr = "regac"}
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
  val in:PRInPort = PRInPort(this, s"${this}") 
  val out:OutPort = PROutPort(this, {s"${this}"}) 
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
  val out = ConstOutPort(this, s"Const(${value})")
}
object Const {
  def apply(v:String)(implicit design: Design):Const = Const(None, v)
  def apply(name:String, v:String)(implicit design: Design):Const =
    Const(Some(name), v)
}

abstract class UDCounter(implicit ctrler:Controller, design: Design) extends Primitive {
  val initVal:Int
  val inc = InPort(this, s"${this}.inc")
  val dec = InPort(this, s"${this}.dec")
  val init = InPort(this, s"${this}.init")
  val out = OutPort(this, s"${this}.o")
}
case class TokenBuffer(dep:ComputeUnit, initVal:Int)
  (implicit ctrler:Controller, design: Design) extends UDCounter{
  override val name = None
  override val typeStr = "TokBuf"
}
case class CreditBuffer(deped:ComputeUnit)(implicit ctrler:Controller, design: Design) 
  extends UDCounter{
  override val initVal = 2
  override val name = None
  override val typeStr = "CredBuf"
}

case class TransferFunction(tf:(Map[OutPort, Int], List[Boolean]) => Boolean, info:String)
object TransferFunction {
  def apply(info:String)(transFunc:(Map[OutPort, Int], List[Boolean]) => Boolean):TransferFunction = {
    TransferFunction(transFunc, info)
  }
}
abstract class LUT(implicit override val ctrler:ComputeUnit, design: Design) extends Primitive {
  override val name = None
  val transFunc:TransferFunction
  val numIns:Int
  val ins = List.fill(numIns) { InPort(this,s"${this}.i") } 
  val out = OutPort(this, s"${this}.o")
}
case class TokenDownLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:ComputeUnit, design: Design) extends LUT {
  override val typeStr = "TokDownLUT"
}
object TokenDownLUT {
  def apply(cu:ComputeUnit, outs:List[OutPort], transFunc:TransferFunction)
  (implicit ctrler:ComputeUnit, design: Design):OutPort = {
    val lut = TokenDownLUT(outs.size, transFunc)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    cu.ctrlBox.tokDownLUTs += lut
    lut.out
  }
}
case class TokenOutLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:ComputeUnit, design: Design) extends LUT {
  override val typeStr = "TokOutLUT"
}
object TokenOutLUT {
  def apply(cu:ComputeUnit, outs:List[OutPort], transFunc:TransferFunction)
  (implicit ctrler:ComputeUnit, design: Design):OutPort = {
    val lut = TokenOutLUT(outs.size, transFunc)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    cu.ctrlBox.tokOutLUTs += lut
    lut.out
  }
}
case class EnLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:ComputeUnit, design: Design) extends LUT {
  override val typeStr = "EnLUT"
}
object EnLUT {
  def apply(cu:ComputeUnit, outs:List[OutPort], transFunc:TransferFunction, en:EnInPort)
  (implicit ctrler:ComputeUnit, design: Design):EnLUT = {
    val lut = EnLUT(outs.size, transFunc)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    en.connect(lut.out)
    cu.ctrlBox.enLUTs += (en -> lut)
    lut
  }
}
case class CtrlBox()(implicit cu:ComputeUnit, design: Design) extends Primitive {
  override val ctrler:ComputeUnit = cu
  override val name = None
  override val typeStr = "CtrlBox"
  val tokenBuffers = Map[Controller, TokenBuffer]()
  val creditBuffers = Map[ComputeUnit, CreditBuffer]()
  def udcounters:Map[Controller, UDCounter] = tokenBuffers ++ creditBuffers
  val enLUTs = Map[EnInPort, EnLUT]()
  val tokOutLUTs = ListBuffer[TokenOutLUT]()
  val tokDownLUTs = ListBuffer[TokenDownLUT]()
  def luts = enLUTs.map(_._2).toList ++ tokOutLUTs.toList ++ tokDownLUTs.toList 
  def innerCtrEn:EnInPort = cu match {
    case cu:InnerComputeUnit => cu.localCChain.inner.en 
    case cu:OuterComputeUnit => cu.inner.cchainMap(cu.localCChain).inner.en
  }
  def outerCtrDone:DoneOutPort = cu match {
    case cu:InnerComputeUnit => cu.localCChain.outer.done 
    case cu:OuterComputeUnit => cu.inner.cchainMap(cu.localCChain).outer.done
  }
  var tokenOut:Option[OutPort] = _ 
  // only outer controller have token down, which is the init signal first child stage
  var tokenDown:Option[OutPort] = _

  def getTokenIns:List[InPort] = {
    val tokenIns = ListBuffer[InPort]()
    udcounters.foreach { case (ctrler, udc) =>
      if (udc.inc.isConnected)
        tokenIns += udc.inc
      if (udc.init.isConnected)
        tokenIns += udc.init
    }
    if (cu.isInstanceOf[InnerComputeUnit]) {
      cu.cchains.foreach { cc =>
        if (cc.inner.en.isConnected) {
          val from = cc.inner.en.from.src.asInstanceOf[Primitive].ctrler
          assert(from.isInstanceOf[InnerComputeUnit])
          if (from!=cu)
            tokenIns += cc.inner.en
        }
      }
    }
    cu.ctrlBox.tokDownLUTs.foreach { tdl => 
      tdl.ins.foreach { in => 
        in.from.src match {
          case top:Top =>
            tokenIns += in
          case ctrler:ComputeUnit => 
            if (ctrler!=cu)
              tokenIns += in
          case _ =>
        }
      }
    }
    tokenIns.toList
  }
  def getTokenOuts:List[OutPort] = {
    val tos = ListBuffer[OutPort]()
    tokenOut.foreach { to => tos += to }
    tokenDown.foreach { td => tos += td }
    if (cu.isInstanceOf[InnerComputeUnit]) {
      cu.ctrlBox.enLUTs.foreach { case (en, enlut) =>
        if (en.src.ctrler!=cu) tos += enlut.out 
      }
      cu.cchains.foreach { cc =>
        val en = cc.inner.en 
        if (en.isConnected) {
          if (en.from.src.asInstanceOf[Primitive].ctrler!=cu) {
          }
        }
      }
    }
    tos.toList
  }

  override def toUpdate = super.toUpdate || tokenOut == null || tokenDown == null
}

