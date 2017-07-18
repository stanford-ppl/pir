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
case class PipeReg(stage:Stage, reg:ArchReg)(implicit spade:Spade, override val prt:ComputeUnit) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "pr"
  override def toString = s"pr(${quote(stage)},${quote(reg)})"
  val en = Input(Bit(), this, s"$this.en")
  val in = Input(Bus(prt.param.numLanes, Word()), this, s"$this.in")
  val out = Output(Bus(prt.param.numLanes, Word()), this, s"${this}.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    import sim.util._
    implicit val mp = sim.mapping
    fimap.get(this).foreach { _ =>
      val inits = rcmap.pmap(reg).flatMap{_.getInit}
      assert(inits.size<=1)
      if (inits.nonEmpty) {
        dprintln(s"${quote(in.v)}.init = ${inits.head}")
      }
      prt.ctrlBox match {
        case cb:InnerCtrlBox =>
          en.v := cb.en.out.vAt(stage.index) 
        case cb =>
          en.v := true //TODO: set this properly
      }
      // Enable on input
      in.v.foreachv { case (v, i) =>
        v.set { v =>
          Match(
            (sim.rst & (i==0)) -> { () => if (inits.nonEmpty) v.asSingle <<= inits.head },
            en.v -> { () => v <<= fimap(in).v.asBus.value(i) }
          ) {}
        }
      } { valid => valid := en.v }
      out.v := in.pv

      // Enable on output
      //out.v.foreach { case (v, i) =>
        //v.set { v =>
          //Match(
            //(sim.rst & inits.nonEmpty & (i==0)) -> { () => v.asSingle <<= inits.head },
            //en.v -> { () => v <<= in.pv.value(i) }
          //) {}
        //}
      //}
    }
  }
}

/* Function unit. 
 * @param numOprds number of operands
 * @param ops List of supported ops
 * @param stage which stage the FU locates
 * */
case class FuncUnit(numOprds:Int, ops:List[Op], stage:Stage)(implicit spade:Spade, override val prt:ComputeUnit)
  extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "fu"
  val operands = List.tabulate(numOprds) { i => Input(Bus(prt.param.numLanes, Word()), this, s"$this.oprd[$i]") } 
  val out = Output(Bus(prt.param.numLanes, Word()), this, s"$this.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.mapping._
    import sim.{dprintln, quote}
    def readsColor(operands:List[Input[Bus, FuncUnit]], color:RegColor):List[Input[Bus, FuncUnit]] = {
      operands.slice(0,2).flatMap { op => 
        fimap.get(op).flatMap { 
          _.propogate.src match {
            case PipeReg(_, r) if r.is(color) => Some(op)
            case _ => None 
          }
        }
      }
    }
    stmap.pmap.get(stage).foreach { st =>
      (stage, st) match {
        case (pst:ReduceStage, st) if readsColor(operands, ReduceReg).nonEmpty =>
          val rdStageIdx = pst.reduceIdx
          val inStep = Math.pow(2, rdStageIdx).toInt
          val outStep = Math.pow(2, rdStageIdx + 1).toInt
          val inIdx = (0 until spade.numLanes by inStep).toList
          val outIdx = (0 until spade.numLanes by outStep).toList
          val groups = outIdx.map { oi =>
            (oi, List(oi, oi + inStep))
          }.toMap
          dprintln(s"reduce: ${quote(pst)}: rdStageIdx:$rdStageIdx inStep:$inStep outStep:$outStep")
          dprintln(s"inIdx:[${inIdx.mkString(",")}] outIdx:[${outIdx.mkString(",")}]")
          out.v.foreach { 
            case (v, oi) if groups.contains(oi) => 
              val vals = (operands, groups(oi)).zipped.map { case (operand, ii) => operand.v.value(ii) }.toSeq
              v.asSingle := eval(st.fu.get.op, vals.map(_.update):_*)
              dprintln(s"${quote(v)} := fu(${vals.map(quote).mkString(", ")})")
            case (v, oi) =>
          }
        case (pst,st:pir.graph.AccumStage) =>
          val oprds = operands.filter{ oprd => isMapped(oprd)(sim.mapping) }
          val accumOp = readsColor(oprds, AccumReg)
          val inputOps = oprds diff accumOp
          assert(inputOps.size==1)
          val inputOp = inputOps.head
          out.v.foreach { case (ev,i) =>
            val vals = operands.map(_.v.value(i)).toSeq
            ev.set { ev =>
              IfElse (prt.ctrlBox.asInstanceOf[InnerCtrlBox].accumPredUnit.out.vAt(stage.index)) {
                ev <<= inputOp.v.update.value(i)
              } {
                ev.asSingle <<= eval(st.fu.get.op, vals.map(_.update):_*)
              }
            }
          }
        case (pst,st) =>
          out.v.foreach { case (v, i) => 
            val vals = operands.map(_.v.value(i)).toSeq
            v.asSingle := eval(st.fu.get.op, vals.map(_.update):_*)
            dprintln(s"")
            dprintln(s"pst: ${quote(pst)} ${quote(v)} := fu(${st.fu.get.op})(${vals.map(quote).mkString(", ")})")
          }
      }
    }
  }
}

/*
 * Phyical stage. 1 column of FU and Pipeline Register block accross lanes. 
 * @param reg Logical registers in current register block
 * */
class Stage(regs:List[ArchReg])(implicit spade:Spade, override val prt:ComputeUnit) extends Primitive {
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
case class EmptyStage(regs:List[ArchReg])(implicit spade:Spade, override val prt:ComputeUnit) extends Stage(regs) {
  override val typeStr = "etst"
}
/* Stage with Function unit */
class FUStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val prt:ComputeUnit)
  extends Stage(regs) {
  def fu:FuncUnit = funcUnit.get 
  override val funcUnit = Some(FuncUnit(numOprds, ops, this))
}
object FUStage {
  def apply(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, prt:ComputeUnit):FUStage = 
    new FUStage(numOprds, regs, ops) 
}
/* Reduction stage */
/*
 * Create a list of reduction stages
 * @param numOprds number of operand
 * @param regs list of logical registers in the stage
 * @param ops reduction operations
 * */
case class ReduceStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val prt:ComputeUnit) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "rdst"
  lazy val reduceIdx:Int = prt.rdstages.indexOf(this)
}
/* WriteAddr calculation stage */
case class WAStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val prt:ComputeUnit) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "wast"
}
case class RAStage(numOprds:Int, regs:List[ArchReg], ops:List[Op])(implicit spade:Spade, override val prt:ComputeUnit) 
  extends FUStage(numOprds, regs, ops) {
  override val typeStr = "rast"
}

