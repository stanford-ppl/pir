package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

abstract class Primitive(implicit spade:Spade, val pne:NetworkElement) extends Module

/** Physical Counter  */
case class Counter()(implicit spade:Spade, pne:ComputeUnit) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "ctr"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val min = Input(Word(), this, s"${this}.min")
  val max = Input(Word(), this, s"${this}.max")
  val step = Input(Word(), this, s"${this}.step")
  val out = Output(Bus(Word()), this, s"${this}.out")
  val en = Input(Bit(), this, s"${this}.en")
  val done = Output(Bit(), this, s"${this}.done")
  def isDep(c:Counter) = en.canConnect(c.done)

  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    super.register
    val outPar = sim.mapping.clmap.pmap(pne) match {
      case cu if cu.isMP => 1
      case cu => cu.parLanes
    }
    out.v.set { v =>
      val head = v.value.head.asWord //TODO: Add type parameter to Bus
      if (en.ev.value.isHigh) {
        head := zip(step.ev.value.value, Some(outPar), head.value){ case (a, b, c) => a * b + c }
        if (isHigh(zip(head.value, max.ev.value.value)(_ >= _)))
          head := min.ev.value.value
      } else if (en.ev.value.isLow) {
        head := min.ev.value.value
      }
      v.value.foreach { 
        case (vl, i) if i > 0 & i < outPar => 
          val v = vl.asWord 
          v := zip(head.value, Some(i.toFloat))(_+_)
        case (vl, i) =>
      }
    }
    done.v.set { v =>
      val head:Option[Float] = out.ev.value.head.asWord.value
      val isDone = zip(head, max.ev.value.value, Some(1.0), en.ev.value.value){ 
        case (h, m, o, e) => e && (h == (m - o))
      }
      if (isHigh(isDone))
        v.value := Some(true)
    }
  }
}

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:ArchReg)(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  import spademeta._
  override val typeStr = "pr"
  override def toString = s"pr(${quote(stage)},${quote(reg)})"
  val in = Input(Bus(Word()), this, s"$this.i")
  val out = Output(Bus(Word()), this, s"${this}.out")
}

trait OnChipMem extends Primitive {
  import spademeta._
  val readPort:Output[_<:PortType, OnChipMem]
  val writePort:Input[_<:PortType, OnChipMem]
  def asSRAM = this.asInstanceOf[SRAM]
  def asVBuf = this.asInstanceOf[VectorMem]
  def asSBuf = this.asInstanceOf[ScalarMem]
  def asBuf = this.asInstanceOf[LocalBuffer]
  val incReadPtr = Input(Bit(), this, s"${this}.incReadPtr")
  val incWritePtr = Input(Bit(), this, s"${this}.incWritePtr")
}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM()(implicit spade:Spade, pne:ComputeUnit) extends OnChipMem {
  import spademeta._
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val readAddr = Input(Word(), this, s"${this}.ra")
  val writeAddr = Input(Word(), this, s"${this}.wa")
  val readPort = Output(Bus(Word()), this, s"${this}.rp")
  val writePort = Input(Bus(Word()), this, s"${this}.wp")
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
trait LocalBuffer extends OnChipMem {
  val notEmpty = Output(Bit(), this, s"${this}.notEmpty")
  val notFull = Output(Bit(), this, s"${this}.notFull")
}

/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarMem()(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "sm"
  val writePort = Input(Word(), this, s"${this}.wp")
  val readPort = Output(Word(), this, s"${this}.rp")
}
/* Vector buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class VectorMem()(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "vm"
  val writePort = Input(Bus(Word()), this, s"${this}.in")
  val readPort = Output(Bus(Word()), this, s"${this}.out") 
}

/* Function unit. 
 * @param numOprds number of operands
 * @param ops List of supported ops
 * @param stage which stage the FU locates
 * */
case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage)(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  import spademeta._
  override val typeStr = "fu"
  val operands = List.fill(numOprds) (Input(Bus(Word()), this, s"$this.oprd${id}")) 
  val out = Output(Bus(Word()), this, s"$this.out")
}

/*
 * Phyical stage. 1 column of FU and Pipeline Register block accross lanes. 
 * @param reg Logical registers in current register block
 * */
class Stage(regs:List[ArchReg])(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  import spademeta._
  val funcUnit:Option[FuncUnit] = None
  val _prs = Map[ArchReg, PipeReg]() // Mapping between logical register and physical register
  regs.foreach { reg => _prs += (reg -> PipeReg(this, reg)) }
  def prs:List[PipeReg] = regs.map { r => _prs(r) }
  def get(reg:ArchReg):PipeReg = _prs(reg)
  var prev:Option[Stage] = None // changed in addStage in PController
  var next:Option[Stage] = None 
  def isLast = next.isEmpty
  def isHead = prev.isEmpty
  def isPrev(s:Stage) = s.prev == Some(this)
  def isNext(s:Stage) = s.next == Some(this)
  def before(s:Stage) = indexOf(this) < indexOf(s)
  def after(s:Stage) = indexOf(this) > indexOf(s)
  override val typeStr = "st"
}
/* Dummy stage that only has register block */
case class EmptyStage(regs:List[ArchReg])(implicit spade:Spade, pne:NetworkElement) extends Stage(regs) {
  override val typeStr = "etst"
}
/* Stage with Function unit */
class FUStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, pne:NetworkElement) extends Stage(regs) {
  def fu:FuncUnit = funcUnit.get 
  override val funcUnit = Some(FuncUnit(numOprds, ops, this))
}
object FUStage {
  def apply(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, pne:NetworkElement):FUStage = 
    new FUStage(numOprds, regs, ops) 
}
/* Reduction stage */
/*
 * Create a list of reduction stages
 * @param numOprds number of operand
 * @param regs list of logical registers in the stage
 * @param ops reduction operations
 * */
case class ReduceStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, pne:NetworkElement) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "rdst"
  override val funcUnit = Some(FuncUnit(numOprds, ops, this))
}
/* WriteAddr calculation stage */
case class WAStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, pne:NetworkElement) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "wast"
  override val funcUnit = Some(FuncUnit(numOprds, ops, this))
}
case class RAStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, pne:NetworkElement) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "wast"
  override val funcUnit = Some(FuncUnit(numOprds, ops, this))
}

class Const()(implicit spade:Spade) extends Simulatable {
  override val typeStr = "const"
  val out = Output(Word(), this, s"$this.out")
  override def register(implicit sim:Simulator):Unit = {
    super.register
    sim.mapping.pmmap.get(this).foreach { c => 
      out.v.set { v => v.value.value = Some(c.toFloat.value) }
    }
  }
}
object Const {
  def apply()(implicit spade:Spade) = new Const()
}

case class Delay[P<:PortType](tp:P, numReg:Int)(implicit spade:Spade, pne:NetworkElement) extends Primitive with Simulatable {
  val in = Input(tp, this, s"${this}_in(0)")
  val out = Output(tp, this, s"${this}_out")
  val prevValues = List.tabulate(numReg) { i => if (i==0) in else Input(tp, this, s"${this}_in($i)") }
  override def register(implicit sim:Simulator):Unit = {
    super.register
    prevValues.zipWithIndex.foreach { case (pv,i) => if (i!=0) { pv.v <== prevValues(i-1) } }
    if (numReg==0) out.v <= in
    else out.v <== prevValues.last
  }
}
