package pir.graph

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._
import pir.graph.mapper.PIRException


abstract class Primitive(implicit val ctrler:Controller, design:Design) extends Node 
/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */
case class CounterChain(name:Option[String])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "CC"
  /* Fields */
  var counters:List[Counter] = Nil 
  /* Pointers */
  var dep:Option[CounterChain] = None
  var copy:Option[CounterChain] = None
  var updated = false // Become updated if copied counter is updated

  override def toUpdate = super.toUpdate || (!updated) 

  def apply(num: Int)(implicit ctrler:Controller, design: Design):Counter = {
    if (!updated) {
      // Speculatively create counters base on need and check index out of bound during update
      this.counters = List.tabulate(num+1) { i => 
        if (i < counters.size) counters(i)
        else Counter()
      }
    }
    counters(num)
  }
  def copy(cp:CounterChain):Unit = {
    // Check whether speculative wire allocation was correct
    assert(counters.size <= cp.counters.size, 
      s"Accessed counter ${counters.size-1} of ${this} is out of bound")
    val addiCtrs = (counters.size until cp.counters.size).map {i => Counter()}
    counters = counters ++ addiCtrs
    counters.zipWithIndex.foreach { case(c,i) => 
      c.copy(cp.counters(i))
    }
    updateDep
    this.copy = Some(cp)
    updated = true
  }
  def update(bds: Seq[(OutPort, OutPort, OutPort)]):Unit = {
    counters = bds.zipWithIndex.map {case ((mi, ma, s),i) => Counter(mi, ma, s)}.toList
    updateDep
    this.copy = None 
    updated = true
  }

  def updateDep = {
    for (i <- 0 until counters.size - 1) {
      counters(i).setDep(counters(i+1))  
    }
    counters.last.setDep(None)
  }
}
object CounterChain {
  def apply(bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:Controller, design: Design):CounterChain =
    {val c = CounterChain(None); c.update(bds); c}
  def apply(name:String, bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:Controller, design: Design):CounterChain =
    {val c = CounterChain(Some(name)); c.update(bds); c}
  def copy(from:String, name:String) (implicit ctrler:Controller, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_${name}_copy"))
    def updateFunc(cp:Node) = cc.copy(cp.asInstanceOf[CounterChain])
    design.updateLater(s"${from}_${name}", updateFunc)
    cc
  }
  def copy(from:Controller, name:String) (implicit ctrler:Controller, design: Design):CounterChain = {
    copy(from.name.getOrElse(""), name)
  }
}

case class Counter(val name:Option[String])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "Ctr"
  /* Fields */
  val min:InPort = InPort(this, s"${this}.min")
  val max:InPort = InPort(this, s"${this}.max")
  val step:InPort = InPort(this, s"${this}.step")
  val out:CtrOutPort = CtrOutPort(this, {s"${this}.out"}) 
  var dep:Option[Counter] = _
  var deped:Option[Counter] = None

  def update(mi:OutPort, ma:OutPort, s:OutPort):Unit = {
    min.connect(mi)
    max.connect(ma)
    step.connect(s)
  }
  def setDep(c:Counter) = { dep = Some(c); c.deped = Some(this) }
  def setDep(c:Option[Counter]) = { 
    if (c.isDefined) { c.get.deped = Some(this) }
    dep = c
  }
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
          val reg = ScalarInPR(cu.newTemp, ScalarIn(n, scalar))
          val pr = PipeReg(cu.emptyStage, reg)
          cu.emptyStage.prs += (reg -> pr)
          pr.out
        case _ => throw new Exception(s"Don't know how to copy port")
      }
    }
    update(copyOutPort(c.min.from), copyOutPort(c.max.from), copyOutPort(c.step.from))
  } 
}
object Counter{
  def apply(min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:Controller, design: Design):Counter =
    { val c = Counter(None); c.update(min, max, step); c }
  def apply(name:String, min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:Controller, design: Design):Counter =
    { val c = Counter(Some(name)); c.update(min, max, step); c }
  def apply()(implicit ctrler:Controller, design: Design):Counter = Counter(None)
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
case class SRAM(name: Option[String], size: Int)(implicit ctrler:Controller, design: Design) 
  extends Primitive {
  override val typeStr = "SRAM"

  val readAddr: RdAddrInPort = RdAddrInPort(this, s"${this}.ra")
  val writeAddr: WtAddrInPort = WtAddrInPort(this, s"${this}.wa")
  val readPort: ReadOutPort = ReadOutPort(this, s"${this}.rp") 
  val writePort: WriteInPort = WriteInPort(this, s"${this}.wp")

  override def toUpdate = super.toUpdate || !readAddr.isConnected || 
    !writeAddr.isConnected || !writePort.isConnected || !readPort.isConnected

  def updateRA(ra:OutPort):SRAM = { 
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
  def updateWA(wa:OutPort):SRAM = { 
    writeAddr.connect(wa)
    wa.src match {
      case PipeReg(stage, r) =>
        throw PIRException(s"Currently don't support register to writeAddr! sram:${this}")
        //val reg:WtAddrPR = r.asInstanceOf[WtAddrPR]
        //reg.waPorts += writeAddr
      case _ =>
    }
    this 
  }
  def updateWP(wp:OutPort):SRAM = { writePort.connect(wp); this } 
  def updateWP(vec:Vector):SRAM = updateWP(VecIn(vec).out)

  def load = readPort
}
object SRAM {
  /* Remote Write */
  def apply(size:Int, vec:Vector)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size).updateWP(vec)
  def apply(name:String, size:Int, vec:Vector)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size).updateWP(vec)
  def apply(size:Int, vec:Vector, readAddr:OutPort)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size).updateRA(readAddr).updateWP(vec)
  def apply(size:Int, vec:Vector, readAddr:OutPort, writeAddr:OutPort)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size).updateRA(readAddr).updateWA(writeAddr).updateWP(vec)
  def apply(name:String, size:Int, vec:Vector, readAddr:OutPort)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size).updateRA(readAddr).updateWP(vec)
  def apply(name:String, size:Int, vec:Vector, readAddr:OutPort, writeAddr:OutPort)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size).updateRA(readAddr).updateWA(writeAddr).updateWP(vec)

  /* Local Write */
  def apply(size:Int)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size)
  def apply(name:String, size:Int)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size)
  def apply(size:Int, readAddr:OutPort, writeAddr:OutPort)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size).updateRA(readAddr).updateWA(writeAddr)
  def apply(name:String, size:Int, readAddr:OutPort, writeAddr:OutPort)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size).updateRA(readAddr).updateWA(writeAddr)
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
  val out:OutPort = OutPort(this, {s"${this}.out"}) 
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
}
object VecOut {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    VecOut(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    VecOut(Some(name), vector)
}

class FuncUnit(stage:Stage, oprds:List[OutPort], o:Op, reses:List[InPort])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "FU"
  override val name = None
  val operands = List.tabulate(oprds.size){ i => InPort(stage, oprds(i), s"${this}.oprd") }
  val op = o
  val results = List.tabulate(reses.size) { i => OutPort(stage, reses(i), s"${this}.out") } 
  override def toUpdate = 
    super.toUpdate || operands.map { !_.isConnected }.reduce{_ | _} || results.map { !_.isConnected }. reduce{_ | _} 
  val defs:List[Reg] = results.flatMap{ op => 
    op.to.src match {
      case PipeReg(s, reg) => if (s == stage) Some(reg) else None
      case _ => None
    } 
  }
  def defines(reg:Reg) = defs.contains(reg) 
}

case class Stage(name:Option[String])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "Stage"
  var prForward = false
  var fu:Option[FuncUnit] = _
  val prs:Map[Reg, PipeReg] = Map.empty
  def operands:List[InPort] = if (fu.isDefined) fu.get.operands else Nil
  def results:List[OutPort] = if (fu.isDefined) fu.get.results else Nil
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
    List.tabulate(n) {i => 
      val s = new {override val idx = i} with Stage(None) with ReduceStage
      val rreg = ctrler.reduce(s)
      Stage(s, op1=rreg.out, op, result=rreg.in)
      s
    }
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
  (implicit ctrler:Controller, design: Design) extends Stage(name) {
  var sram:SRAM = _
  override val typeStr = "WAStage"
  override def toUpdate = super.toUpdate || sram==null

  def updateSRAM[T](sram:T):WAStage = {
    sram match {
      case s:String => design.updateLater(s"${ctrler.name.getOrElse(s"")}_${s}", (n:Node) => this.sram = n.asInstanceOf[SRAM])
      case s:SRAM => this.sram = s
    }
    this
  }

}
object WAStage {
  def apply[T](sram:T)(implicit ctrler:Controller, design: Design)  = new WAStage(None).updateSRAM(sram)
}
object WAStages {
  def apply[T](n:Int, sram:T) (implicit ctrler:ComputeUnit, design: Design):List[WAStage] = {
    val was = List.tabulate(n) { i => WAStage(sram) }
    ctrler.addWAStages(was)
    was
  }
}
trait EmptyStage extends Stage {
  override val typeStr = "EmptyStage"
  fu = None
}
object EmptyStage {
  def apply(name:Option[String])(implicit ctrler:Controller, design: Design):EmptyStage  = 
    new Stage(name) with EmptyStage
  def apply()(implicit ctrler:Controller, design: Design):EmptyStage  = 
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
//case class WtAddrPR(override val regId:Int)(implicit ctrler:Controller, design: Design)                           extends Reg {override val typeStr = "regwa"; val waPorts = ListBuffer[InPort]()}
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

case class Const(name:Option[String], value:Long)(implicit design: Design) extends Node {
  override val typeStr = "Const"
  override def toString = s"Const(${value})"
  val out = OutPort(this, s"Const(${value})")
}
object Const {
  def apply(v:Long)(implicit design: Design):Const = Const(None, v)
  def apply(name:String, v:Long)(implicit design: Design):Const =
    Const(Some(name), v)
}
