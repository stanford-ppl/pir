package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.plasticine.main._
import pir.codegen.DotCodegen

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

trait Primitive extends Module {
  val ctrler:Controller
}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM()(implicit spade:Spade, val ctrler:ComputeUnit) extends Primitive {
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val readPort = RMOutPort(this, s"${this}.rp")
  val writePort = RMInPort(this, s"${this}.wp")
  val readAddr = RMInPort(this, s"${this}.ra")
  val writeAddr = RMInPort(this, s"${this}.wa")
}

/** Physical Counter  */
case class Counter()(implicit spade:Spade, val ctrler:ComputeUnit) extends Primitive {
  override val typeStr = "ctr"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val min = InPort(this, s"${this}.min")
  val max = InPort(this, s"${this}.max")
  val step = InPort(this, s"${this}.step")
  val out = RMOutPort(this, s"${this}.out")
  val en = InWire(this, s"${this}.en")
  val done = OutWire(this, s"${this}.done")
  def isDep(c:Counter) = en.canConnect(c.done)
}

/* Logical register (1 row of pipeline registers for all stages) */
case class Reg()(implicit spade:Spade, val ctrler:ComputeUnit) extends Primitive {
  override val typeStr = "reg"
}

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:Reg)(implicit spade:Spade) extends Module {
  override val typeStr = "pr"
  override def toString = s"pr(${DotCodegen.quote(stage, spade)},${DotCodegen.quote(reg, spade)})"
  val in = new RMInPort[this.type](this) with Stagable { 
    override def toString = s"$src.i"
    override val stage = PipeReg.this.stage
  }
  val out = new RMOutPort[this.type](this) with Stagable {
    override def toString = s"$src.o"
    override val stage = PipeReg.this.stage
  } 
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
class ScalarBuffer(implicit spade:Spade) extends Module {
  val in:InPort[this.type] = InPort(this, s"${this}.i") 
  val out:OutPort[this.type] = OutPort(this, s"${this}.o")
} 
/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarIn(outport:Option[OutPort[NetworkElement]])(implicit spade:Spade) extends ScalarBuffer {
  outport.foreach { this.in <== _ }
  override val typeStr = "si"
  override val out:RMOutPort[this.type] = RMOutPort(this, s"${this}.o")
  def inBus:InBus[NetworkElement] = busOf(outport.get).asInstanceOf[InBus[NetworkElement]]
  def idx = indexOf(outport.get)
} 
object ScalarIn {
  def apply(outport:OutPort[NetworkElement])(implicit spade:Spade):ScalarIn = ScalarIn(Some(outport))
}
/* Scalar buffer between the last stage and the bus output. Output connects to 1 in port 
 * of the OutBus */
case class ScalarOut(inport:Option[InPort[NetworkElement]])(implicit spade:Spade) extends ScalarBuffer {
  inport.foreach( _ <== this.out )
  override val typeStr = "so"
  override val in:RMInPort[this.type] = RMInPort(this, s"${this}.i")
  def outBus:OutBus[NetworkElement] = busOf(inport.get).asInstanceOf[OutBus[NetworkElement]]
  def idx = indexOf(inport.get)
}
object ScalarOut {
  def apply(inport:InPort[NetworkElement])(implicit spade:Spade):ScalarOut = ScalarOut(Some(inport))
}
/* ScalarOut of TileTransfer CU, whos AddrOut has dedicated scalar network that goes to
 * Memory Controller */
trait AddrOut extends ScalarOut {
  override val typeStr = "ado"
}
object AddrOut {
  def apply()(implicit spade:Spade) = new ScalarOut(None) with AddrOut
}

/* Function unit. 
 * @param numOprds number of operands
 * @param ops List of supported ops
 * @param stage which stage the FU locates
 * */
case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage)(implicit spade:Spade) extends Module {
  override val typeStr = "fu"
  val operands = List.fill(numOprds) (
    new RMInPort[this.type](this) with Stagable { 
      override def toString = s"$src.oprd${id}"
      override val stage = FuncUnit.this.stage
    }
  ) 
  val out = new RMOutPort[this.type](this) with Stagable {
    override def toString = s"$src.out"
    override val stage = FuncUnit.this.stage
  } 
}

/*
 * Phyical stage. 1 column of FU and Pipeline Register block accross lanes. 
 * @param reg Logical registers in current register block
 * */
class Stage(regs:List[Reg])(implicit spade:Spade) extends Module {
  val funcUnit:Option[FuncUnit] = None
  val prs = Map[Reg, PipeReg]() // Mapping between logical register and physical register
  regs.foreach { reg => prs += (reg -> PipeReg(this, reg)) }
  var pre:Option[Stage] = None // changed in addStage in PController
  var next:Option[Stage] = None 
  def isLast = next.isEmpty
  def isHead = pre.isEmpty
  def before(s:Stage) = indexOf(this) < indexOf(s)
  def after(s:Stage) = indexOf(this) > indexOf(s)
  override val typeStr = "st"
}
/* Dummy stage that only has register block */
trait EmptyStage extends Stage {
  override val typeStr = "etst"
}
object EmptyStage {
  def apply(regs:List[Reg])(implicit spade:Spade) = new Stage(regs) with EmptyStage
}
/* Stage with Function unit */
trait FUStage extends Stage {
  def fu:FuncUnit = funcUnit.get 
}
object FUStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op])(implicit spade:Spade):FUStage = 
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
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op])(implicit spade:Spade):ReduceStage = 
    new Stage(regs) with ReduceStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}
/* WriteAddr calculation stage */
trait WAStage extends FUStage {
  override val typeStr = "wast"
}
object WAStage {
  def apply(numOprds:Int, regs:List[Reg], ops:List[Op])(implicit spade:Spade):WAStage = 
    new Stage(regs) with WAStage { override val funcUnit = Some(FuncUnit(numOprds, ops, this)) }
}

case class ConstVal[T](v:T)(implicit spade:Spade) extends Module {
  val out = OutPort(this, s"Const($v)")
}
case class Const()(implicit spade:Spade) extends Module {
  val out = RMOutPort(this, s"Const")
}

trait LUT extends Node {
  val numIns:Int
}
case class EnLUT(numIns:Int)(implicit spade:Spade) extends LUT {
  override val typeStr = "enlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
}
case class TokenOutLUT()(implicit spade:Spade) extends LUT{
  override val typeStr = "tolut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override val numIns = 2 // Token out is a combination of two output
}
case class TokenDownLUT(numIns:Int)(implicit spade:Spade) extends LUT {
  override val typeStr = "tdlut"
}
object TokenDownLUT extends Metadata {
  def apply(idx:Int, numIns:Int)(implicit spade:Spade):TokenDownLUT = TokenDownLUT(numIns).index(idx)
}
case class UDCounter()(implicit spade:Spade) extends Node {
  override val typeStr = "udlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  //val init = InPort(this, s"${this}.init")
  //val inc = InPort(this, s"${this}.inc")
  //val dec = InPort(this, s"${this}.dec")
  //val out = OutPort(this, s"${this}.out")
}
object UDCounter extends Metadata {
  def apply(idx:Int)(implicit spade:Spade):UDCounter = UDCounter().index(idx)
}

class CtrlBox[+C<:Controller](numUDCs:Int) (implicit spade:Spade, override val ctrler:C) extends Primitive {
  val udcs = List.tabulate(numUDCs) { i => UDCounter(i) }
}

