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
    sim.mapping.ctmap.pmap.get(this).foreach { ctr =>
      val cu = ctr.ctrler
      val prevCtr = sim.mapping.fimap(en).src match {
        case c:Counter => Some(c)
        case _ => None
      }
      val outPar = getPar(ctr) 
      sim.dprintln(s"$this outPar = $outPar")
      val head = out.v.head.asWord //TODO: Add type parameter to Bus
      out.v.foreach { 
        case (v, i) if (i==0) =>
          head.set { headv =>
            Match(
              sim.rst -> { () => headv <<= min.v },
              (done.pv & prevCtr.fold(done.pv) { _.done.pv } ) -> { () => headv <<= min.v },
              en.v -> { () => headv <<= headv + step.v }
            ) {}
          }
        case (v, i) if i < outPar =>
          v.asWord := head + (step.v * (outPar * i))
        case (v, i) =>
      }
      done.v.set { donev =>
        donev.setLow
        out.v.update.foreach { case (outv, i) =>
          If (outv.asWord >= max.v) {
            donev.setHigh
          }
        }
      }
    }
    super.register
  }
}

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:ArchReg)(implicit spade:Spade, pne:NetworkElement) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "pr"
  override def toString = s"pr(${quote(stage)},${quote(reg)})"
  val en = Input(Bit(), this, s"$this.en")
  val in = Input(Bus(Word()), this, s"$this.in")
  val out = Output(Bus(Word()), this, s"${this}.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    implicit val mp = sim.mapping
    mp.fimap.get(this).foreach { _ =>
      val inits = mp.rcmap.pmap(reg).flatMap{_.getInit}.collect{ case c:Int => c; case c:Float => c}
      assert(inits.size<=1)
      en.v := true //TODO: set this properly
      out.v.foreach { case (v, i) =>
        v.set { v =>
          Match(
            (sim.rst & inits.nonEmpty & (i==0)) -> { () => v.asWord <<= inits.head.toFloat },
            en.v -> { () => v <<= in.pv.value(i) }
          ) {}
        }
      }
    }
    super.register
  }
}

trait OnChipMem extends Primitive {
  import spademeta._
  val size:Int
  type P<:PortType
  val readPort:Output[P, OnChipMem]
  val writePort:Input[P, OnChipMem]
  val incReadPtr = Input(Bit(), this, s"${this}.incReadPtr")
  val incWritePtr = Input(Bit(), this, s"${this}.incWritePtr")
  val writePtr = Output(Word(), this, s"${this}.writePtr")
  val readPtr = Output(Word(), this, s"${this}.readPtr")
  val count = Output(Word(), this, s"${this}.count")
  def asSRAM = this.asInstanceOf[SRAM]
  def asVBuf = this.asInstanceOf[VectorMem]
  def asSBuf = this.asInstanceOf[ScalarMem]
  def asBuf = this.asInstanceOf[LocalBuffer]
  def createArray(capacity:Int):Array[P]

}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM(size:Int)(implicit spade:Spade, pne:ComputeUnit) extends OnChipMem {
  import spademeta._
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  type P = Bus 
  def createArray(capacity:Int):Array[P] = Array.tabulate(capacity) { i => readPort.tp.clone }
  val readAddr = Input(Word(), this, s"${this}.ra")
  val writeAddr = Input(Word(), this, s"${this}.wa")
  val readPort = Output(Bus(Word()), this, s"${this}.rp")
  val writePort = Input(Bus(Word()), this, s"${this}.wp")
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
trait LocalBuffer extends OnChipMem with Simulatable {
  val notEmpty = Output(Bit(), this, s"${this}.notEmpty")
  val notFull = Output(Bit(), this, s"${this}.notFull")

  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.smmap.pmap.get(this).fold {
      notEmpty.v.set { v => v.setHigh }
      notFull.v.set { v => v.setHigh }
    } { mem =>
      val capacity = mem match {
        case mem:pir.graph.FIFO => mem.size
        case mem:pir.graph.MultiBuffering => mem.buffering
      }
      val array:Array[P] = createArray(capacity)
      def incPtr(v:WordValue) = {
        v <<= v + 1; if (v.value.get.toInt>=capacity) v <<= 0
      }
      readPtr.v <<= 0
      writePtr.v <<= 0
      count.v <<= 0
      readPtr.v.set { v => If(incReadPtr.v) { incPtr(v) } }
      writePtr.v.set { v => If(incWritePtr.v) { incPtr(v) }; array(v.value.get.toInt) <<= writePort.v }
      count.v.set { v => If(incReadPtr.v) { v <<= v - 1 }; If(incWritePtr.v) { v <<= v + 1 } }
      readPort.v.set { v => 
        writePtr.v.update
        readPtr.v.update
        v <<= array(readPtr.v.value.get.toInt)
      }
      notEmpty.v := (if (capacity==1) Some(true) else count.v > 0)
      notFull.v := (if (capacity==1) Some(true) else count.v < capacity) //TODO: implement almost full
    }
    super.register
  }
}

/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarMem(size:Int)(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "sm"
  type P = Word
  val writePort = Input(Word(), this, s"${this}.wp")
  val readPort = Output(Word(), this, s"${this}.rp")
  def createArray(capacity:Int):Array[P] = Array.tabulate(capacity) { i => readPort.tp.clone }
}
/* Vector buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class VectorMem(size:Int)(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "vm"
  type P = Bus
  val writePort = Input(Bus(Word()), this, s"${this}.in")
  val readPort = Output(Bus(Word()), this, s"${this}.out") 
  def createArray(capacity:Int):Array[P] = Array.tabulate(capacity) { i => readPort.tp.clone }
}

/* Function unit. 
 * @param numOprds number of operands
 * @param ops List of supported ops
 * @param stage which stage the FU locates
 * */
case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage)(implicit spade:Spade, pne:NetworkElement) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "fu"
  val operands = List.tabulate(numOprds) { i => Input(Bus(Word()), this, s"$this.oprd[$i]") } 
  val out = Output(Bus(Word()), this, s"$this.out")
  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.stmap.pmap.get(stage).foreach { st =>
      out.v.foreach { case (v, i) => 
        val vals = operands.map(_.v.value(i)).toSeq
        v.asWord := eval(st.fu.get.op, vals.map(_.update):_*)
        //v.asWord := {
          //val res = eval(st.fu.get.op, vals.map(_.update):_*)
          //sim.dprintln(s"${sim.quote(v)} := eval(${vals.map(op => s"${sim.quote(op)}=${op.value}" )})")
          //res
        //}
      }
    }
    super.register
  }
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
  override def index(i:Int)(implicit spade:Spade):this.type = { super.index(i); funcUnit.foreach(_.index(i)); this }
  override def index(implicit spade:Spade):Int = { super.index }
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
