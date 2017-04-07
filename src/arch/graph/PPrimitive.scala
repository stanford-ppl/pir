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

abstract class Primitive(implicit spade:Spade, val ctrler:Controller) extends Module

/** Physical Counter  */
case class Counter()(implicit spade:Spade, ctrler:ComputeUnit) extends Primitive {
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
}

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:ArchReg)(implicit spade:Spade, ctrler:Controller) extends Primitive {
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
  def asVBuf = this.asInstanceOf[VectorBuffer]
  def asSBuf = this.asInstanceOf[ScalarBuffer]
}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM()(implicit spade:Spade, ctrler:ComputeUnit) extends OnChipMem {
  import spademeta._
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val readAddr = Input(Word(), this, s"${this}.ra")
  val writeAddr = Input(Word(), this, s"${this}.wa")
  val readPort:Output[Bus, this.type] = Output(Bus(Word()), this, s"${this}.rp")
  val writePort:Input[Bus, this.type] = Input(Bus(Word()), this, s"${this}.wp")
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
trait LocalBuffer extends OnChipMem 

/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarBuffer()(implicit spade:Spade, ctrler:Controller) extends LocalBuffer {
  override val typeStr = "si"
  val writePort = Input(Word(), this, s"${this}.wp")
  val readPort = Output(Word(), this, s"${this}.rp")
}
/* Vector buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class VectorBuffer()(implicit spade:Spade, ctrler:Controller) extends LocalBuffer {
  override val typeStr = "vb"
  val writePort = Input(Bus(Word()), this, s"${this}.in")
  val readPort = Output(Bus(Word()), this, s"${this}.out") 
}

/* Function unit. 
 * @param numOprds number of operands
 * @param ops List of supported ops
 * @param stage which stage the FU locates
 * */
case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage)(implicit spade:Spade, ctrler:Controller) extends Primitive {
  import spademeta._
  override val typeStr = "fu"
  val operands = List.fill(numOprds) (Input(Bus(Word()), this, s"$this.oprd${id}")) 
  val out = Output(Bus(Word()), this, s"$this.out")
}

/*
 * Phyical stage. 1 column of FU and Pipeline Register block accross lanes. 
 * @param reg Logical registers in current register block
 * */
class Stage(regs:List[ArchReg])(implicit spade:Spade, ctrler:Controller) extends Primitive {
  import spademeta._
  val funcUnit:Option[FuncUnit] = None
  val _prs = Map[ArchReg, PipeReg]() // Mapping between logical register and physical register
  regs.foreach { reg => _prs += (reg -> PipeReg(this, reg)) }
  def prs:List[PipeReg] = regs.map { r => _prs(r) }
  def get(reg:ArchReg):PipeReg = _prs(reg)
  var pre:Option[Stage] = None // changed in addStage in PController
  var next:Option[Stage] = None 
  def isLast = next.isEmpty
  def isHead = pre.isEmpty
  def before(s:Stage) = indexOf(this) < indexOf(s)
  def after(s:Stage) = indexOf(this) > indexOf(s)
  override val typeStr = "st"
}
/* Dummy stage that only has register block */
case class EmptyStage(regs:List[ArchReg])(implicit spade:Spade, ctrler:Controller) extends Stage(regs) {
  override val typeStr = "etst"
}
/* Stage with Function unit */
trait FUStage extends Stage {
  def fu:FuncUnit = funcUnit.get 
}
object FUStage {
  def apply(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, ctrler:Controller):FUStage = 
    new Stage(regs) with FUStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}
/* Reduction stage */
trait ReduceStage extends FUStage {
  override val typeStr = "rdst"
}
object ReduceStage {
  /*
   * Create a list of reduction stages
   * @param numOprds number of operand
   * @param regs list of logical registers in the stage
   * @param ops reduction operations
   * */
  def apply(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, ctrler:Controller):ReduceStage = 
    new Stage(regs) with ReduceStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}
/* WriteAddr calculation stage */
trait WAStage extends FUStage {
  override val typeStr = "wast"
}
object WAStage {
  def apply(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, ctrler:Controller):WAStage = 
    new Stage(regs) with WAStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}

class Const()(implicit spade:Spade) extends Module {
  val out = Output(Word(), this, s"Const")
}
object Const {
  def apply()(implicit spade:Spade) = new Const()
}

abstract class LUT(implicit spade:Spade, ctrler:Controller) extends Node {
  val numIns:Int
}
case class EnLUT(numIns:Int)(implicit spade:Spade, ctrler:Controller) extends LUT {
  import spademeta._
  override val typeStr = "enlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
}
case class TokenOutLUT()(implicit spade:Spade, ctrler:Controller) extends LUT{
  import spademeta._
  override val typeStr = "tolut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override val numIns = 2 // Token out is a combination of two output
}
case class TokenDownLUT(numIns:Int)(implicit spade:Spade, ctrler:Controller) extends LUT {
  override val typeStr = "tdlut"
}
object TokenDownLUT {
  def apply(idx:Int, numIns:Int)(implicit spade:Spade, ctrler:Controller):TokenDownLUT = 
    TokenDownLUT(numIns).index(idx)
}
case class UDCounter()(implicit spade:Spade, ctrler:Controller) extends Primitive {
  import spademeta._
  override val typeStr = "udlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  //val init = Input(Word(), this, s"${this}.init")
  //val inc = Input(Word(), this, s"${this}.inc")
  //val dec = Input(Word(), this, s"${this}.dec")
  //val out = Output(Word(), this, s"${this}.out")
}
object UDCounter {
  def apply(idx:Int)(implicit spade:Spade, ctrler:Controller):UDCounter = UDCounter().index(idx)
}

class CtrlBox(numUDCs:Int) (implicit spade:Spade, ctrler:Controller) extends Primitive {
  import spademeta._
  val udcs = List.tabulate(numUDCs) { i => UDCounter(i) }
}

case class TopCtrlBox()(implicit spade:Spade, override val ctrler:Top) extends CtrlBox(0) with Simulatable {
  val command = Output(Bit(), this, s"command")
  val status = Input(Bit(), this, s"status")
  override def register(implicit sim:Simulator):Unit = {
    super.register
    //sim.dprintln(s"setting $command")
    command.v.set { case (sim, v) => 
      if (sim.cycle == 1)
        v.value.value = Some(false) 
      else if (sim.cycle == 2)
        v.value.value = Some(true) 
      else if (v.prevValue.value==Some(true))
        v.value.value = Some(false)
      //sim.dprintln(s"#${sim.cycle} ${o} ${v.value.s}")
    }
    sim.mapping.xbmap.get(status).foreach { in => status.v <= in }
  }
}
