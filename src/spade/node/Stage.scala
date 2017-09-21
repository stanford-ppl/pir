package spade.node

import spade._
import spade.util._

import pirc.enums._

import scala.collection.mutable.Map

case class PipeRegConfig(init:Option[AnyVal]) extends Configuration

/* Phyiscal pipeline register */
case class PipeReg(stage:Stage, reg:ArchReg)(implicit spade:Spade, override val prt:ComputeUnit) extends Primitive with Simulatable with Configurable {
  import spademeta._
  override val typeStr = "pr"
  override def toString = s"pr(${quote(stage)},${quote(reg)})"
  type CT = PipeRegConfig

  val en = Input(Bit(), this, s"$this.en")
  val in = Input(Bus(prt.param.numLanes, Word()), this, s"$this.in")
  val out = Output(Bus(prt.param.numLanes, Word()), this, s"${this}.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    implicit val mp = sim.mapping
    fimap.get(this).foreach { _ =>
      val init = cfmap.get(this).map{_.init}.getOrElse(None)
      if (init.nonEmpty) {
        dprintln(s"${quote(in.v)}.init = $init")
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
            (sim.rst & (i==0)) -> { () => v.asSingle <<= init },
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
    import sim.util._

    cfmap.get(stage).foreach { config =>

      val inputs = List.tabulate(config.par) { i =>
        stage match {
          case pst:ReduceStage if config.isReduce =>
            val rdStageIdx = pst.reduceIdx
            val inStep = Math.pow(2, rdStageIdx).toInt
            val outStep = Math.pow(2, rdStageIdx + 1).toInt
            val inIdx = (0 until spade.numLanes by inStep).toList
            val outIdx = (0 until spade.numLanes by outStep).toList
            val groups = outIdx.map { oi => (oi, List(oi, oi + inStep)) }.toMap
            dprintln(s"reduce: ${quote(pst)}: rdStageIdx:$rdStageIdx inStep:$inStep outStep:$outStep")
            dprintln(s"inIdx:[${inIdx.mkString(",")}] outIdx:[${outIdx.mkString(",")}]")
            if (groups.contains(i))
              (operands, groups(i)).zipped.map { case (operand, ii) => operand.v.value(ii) }.toSeq
            else
              operands.map(_.v.value(i)).toSeq
          case pst => operands.map(_.v.value(i)).toSeq
        }
      }

      out.v.foreach { 
        case (v, i) if i < config.par => 
          config.accumInput match {
            case Some(oprd) => 
              v.set { v =>
                IfElse (prt.ctrlBox.asInstanceOf[InnerCtrlBox].accumPredUnit.out.vAt(stage.index)) {
                  v <<= oprd.v.asBus.update.value(i)
                } {
                  v.asSingle <<= eval(config.op, inputs(i).map(_.update):_*)
                }
              }
              dprintln(s"${quote(v)} := fu(${inputs(i).map(quote).mkString(", ")})")
            case _ =>
              v.asSingle := eval(config.op, inputs(i).map(_.update):_*)
              dprintln(s"pst: ${quote(stage)} ${quote(v)} := fu(${config.op})(${inputs(i).map(quote).mkString(", ")})")
          }
        case _ =>
      }

    }
  }
}

case class StageConfig (
  par:Int,
  op:Op,
  isReduce:Boolean,
  accumInput:Option[Input[_<:PortType, Module]]
) extends Configuration
/*
 * Phyical stage. 1 column of FU and Pipeline Register block accross lanes. 
 * @param reg Logical registers in current register block
 * */
class Stage(regs:List[ArchReg])(implicit spade:Spade, override val prt:ComputeUnit) extends Primitive 
  with Configurable {
  import spademeta._
  type CT = StageConfig

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

