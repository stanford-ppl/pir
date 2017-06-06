package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:ArchReg)(implicit spade:Spade, override val pne:ComputeUnit) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "pr"
  override def toString = s"pr(${quote(stage)},${quote(reg)})"
  val en = Input(Bit(), this, s"$this.en")
  val in = Input(Bus(pne.numLanes, Word()), this, s"$this.in")
  val out = Output(Bus(pne.numLanes, Word()), this, s"${this}.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    import sim.mapping._
    implicit val mp = sim.mapping
    fimap.get(this).foreach { _ =>
      val inits = rcmap.pmap(reg).flatMap{_.getInit}.collect{ case c:Int => c; case c:Float => c}
      assert(inits.size<=1)
      pne.ctrlBox match {
        case cb:InnerCtrlBox =>
          en.v := cb.en.out.vAt(stage.index) 
        case cb =>
          en.v := true //TODO: set this properly
      }
      // Enable on input
      in.v.foreachv { case (v, i) =>
        v.set { v =>
          Match(
            (sim.rst & inits.nonEmpty & (i==0)) -> { () => v.asWord <<= inits.head.toFloat },
            en.v -> { () => v <<= fimap(in).v.asBus.value(i) }
          ) {}
        }
      } { valid => valid := en.v }
      out.v := in.pv

      // Enable on output
      //out.v.foreach { case (v, i) =>
        //v.set { v =>
          //Match(
            //(sim.rst & inits.nonEmpty & (i==0)) -> { () => v.asWord <<= inits.head.toFloat },
            //en.v -> { () => v <<= in.pv.value(i) }
          //) {}
        //}
      //}
    }
    super.register
  }
}

/* Function unit. 
 * @param numOprds number of operands
 * @param ops List of supported ops
 * @param stage which stage the FU locates
 * */
case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage)(implicit spade:Spade, override val pne:ComputeUnit)
  extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "fu"
  val operands = List.tabulate(numOprds) { i => Input(Bus(pne.numLanes, Word()), this, s"$this.oprd[$i]") } 
  val out = Output(Bus(pne.numLanes, Word()), this, s"$this.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.mapping._
    import sim.{dprintln, quote}
    def readsReduceReg(operands:List[Input[Bus, FuncUnit]]) = {
      operands.slice(0,2).forall{ op => fimap.get(op).fold(false) { _.propogate.src match {
        case PipeReg(_, r) => r.is(ReduceReg); case _ => false }
      }
    }}
    stmap.pmap.get(stage).foreach { st =>
      stage match {
        case stage:ReduceStage if readsReduceReg(operands) =>
          val rdStageIdx = stage.reduceIdx
          val inStep = Math.pow(2, rdStageIdx).toInt
          val outStep = Math.pow(2, rdStageIdx + 1).toInt
          val inIdx = (0 until spade.numLanes by inStep).toList
          val outIdx = (0 until spade.numLanes by outStep).toList
          val groups = outIdx.map { oi =>
            (oi, List(oi, oi + inStep))
          }.toMap
          dprintln(s"reduce: ${quote(stage)}: rdStageIdx:$rdStageIdx inStep:$inStep outStep:$outStep")
          dprintln(s"inIdx:[${inIdx.mkString(",")}] outIdx:[${outIdx.mkString(",")}]")
          out.v.foreach { 
            case (v, oi) if groups.contains(oi) => 
              val vals = (operands, groups(oi)).zipped.map { case (operand, ii) => operand.v.value(ii) }.toSeq
              v.asWord := eval(st.fu.get.op, vals.map(_.update):_*)
              dprintln(s"${quote(v)} := fu(${vals.map(quote).mkString(", ")})")
            case (v, oi) =>
          }
        case stage =>
          out.v.foreach { case (v, i) => 
            val vals = operands.map(_.v.value(i)).toSeq
            v.asWord := eval(st.fu.get.op, vals.map(_.update):_*)
            dprintln(s"")
            dprintln(s"stage: ${quote(stage)} ${quote(v)} := fu(${st.fu.get.op})(${vals.map(quote).mkString(", ")})")
          }
      }
    }
    super.register
  }
}

/*
 * Phyical stage. 1 column of FU and Pipeline Register block accross lanes. 
 * @param reg Logical registers in current register block
 * */
class Stage(regs:List[ArchReg])(implicit spade:Spade, override val pne:ComputeUnit) extends Primitive {
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
case class EmptyStage(regs:List[ArchReg])(implicit spade:Spade, override val pne:ComputeUnit) extends Stage(regs) {
  override val typeStr = "etst"
}
/* Stage with Function unit */
class FUStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val pne:ComputeUnit)
  extends Stage(regs) {
  def fu:FuncUnit = funcUnit.get 
  override val funcUnit = Some(FuncUnit(numOprds, ops, this))
}
object FUStage {
  def apply(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, pne:ComputeUnit):FUStage = 
    new FUStage(numOprds, regs, ops) 
}
/* Reduction stage */
/*
 * Create a list of reduction stages
 * @param numOprds number of operand
 * @param regs list of logical registers in the stage
 * @param ops reduction operations
 * */
case class ReduceStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val pne:ComputeUnit) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "rdst"
  lazy val reduceIdx:Int = pne.rdstages.indexOf(this)
}
/* WriteAddr calculation stage */
case class WAStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val pne:ComputeUnit) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "wast"
}
case class RAStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val pne:ComputeUnit) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "rast"
}

