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
  //override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
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
    sim.mapping.clmap.pmap.get(pne).foreach { cu =>
      val outPar = cu match {
        case cu if cu.isMP => 1
        case cu:pir.graph.ComputeUnit => cu.parLanes
      }
      val head = out.v.head.asWord //TODO: Add type parameter to Bus
      out.v.foreach { 
        case (v, i) if (i==0) =>
          head.set { headv =>
            IfElse (en.v) {
              headv <<= headv + (step.v * outPar)
              If (headv >= max.v) {
                headv <<= min.v.value
              }
            } {
              headv <<= min.v.value
            }
          }
        case (v, i) if i < outPar =>
          val tail = v.asWord 
          tail <<= head + (step.v * i)
        case (v, i) =>
      }
      done.v.set { donev =>
        donev.setLow
        out.v.foreach { case (outv, i) =>
          If (outv.asWord > max.v) {
            donev.setHigh
          }
        }
      }
    }
  }
}

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:ArchReg)(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  import spademeta._
  override val typeStr = "pr"
  override def toString = s"pr(${quote(stage)},${quote(reg)})"
  val in = Input(Bus(Word()), this, s"$this.in")
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
  val operands = List.tabulate(numOprds) { i => Input(Bus(Word()), this, s"$this.oprd[$i]") } 
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
  override val typeStr = "rast"
  override val funcUnit = Some(FuncUnit(numOprds, ops, this))
}

class Const()(implicit spade:Spade) extends Simulatable {
  override val typeStr = "const"
  val out = Output(Word(), this, s"$this.out")
  override def register(implicit sim:Simulator):Unit = {
    super.register
    sim.mapping.pmmap.get(this).foreach { case c:pir.graph.Const[_] => 
      //out.v.value = Some(c.toFloat.value) //TODO
      out.v := Some(c.toFloat.value) 
    }
  }
}
object Const {
  def apply()(implicit spade:Spade) = new Const()
}

case class Delay[P<:PortType](tp:P, delay:Int, ts:Option[String])(implicit spade:Spade, pne:NetworkElement) extends Primitive with Simulatable {
  override val typeStr = ts.getOrElse("delay")
  val in = Input(tp, this, s"${this}_in(0)")
  val out = Output(tp.clone, this, s"${this}_out")
  override def register(implicit sim:Simulator):Unit = {
    super.register
    out.v := in.vAt(delay) 
  }
}
object Delay {
  def apply(tp:Bit, delay:Int,ts:Option[String])
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = {
    val d = new Delay(tp, delay, ts)(spade, pne)
    ctrlBox.delays += d
    d
  }
  def apply(tp:Bit, delay:Int,ts:String)
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, delay, Some(ts))
  def apply(tp:Bit, delay:Int)
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, delay, None)
}
