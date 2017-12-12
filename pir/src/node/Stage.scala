package pir.node

import pir._
import pir.pass.ForwardRef
import pir.util._

import pirc._
import pirc.enums._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import scala.language.existentials

case class FuncUnit(stage:Stage)(implicit override val ctrler:ComputeUnit, design: PIR) extends Primitive {
  override val typeStr = "FU"
  var op:Op = _
  val _operands = ListBuffer[Input]()
  def operands = _operands.toList
  val out = Output(this, s"${this}.out")
  def addOperand(out:Output) = {
    val idx = _operands.size
    val in = Input(this, s"${this}.oprd($idx)")
    _operands += in
    in.connect(out)
    in
  }
  override def toUpdate = super.toUpdate || operands.map { !_.isConnected }.reduce{_ | _} || !out.isConnected
}

class Stage(implicit override val ctrler:ComputeUnit, design: PIR) extends Primitive {
  override val typeStr = "Stage"
  val fu = FuncUnit(this)
  val _prs = mutable.Map[Reg, PipeReg]()
  def prs:List[PipeReg] = _prs.values.toList
  def get(reg:Reg) = _prs.getOrElseUpdate(reg, PipeReg(this, reg))
  def remove(reg:Reg) = _prs -= reg
  val defs:mutable.Set[Reg] = mutable.Set.empty
  val uses:mutable.Set[Reg] = mutable.Set.empty
  var liveIns:Set[Reg] = Set.empty
  var liveOuts:Set[Reg] = Set.empty
  var prev:Option[Stage] = None
  var next:Option[Stage] = None
  def allPrevs:List[Stage] = {
    prev.map { prev => prev.allPrevs :+ prev }.getOrElse(Nil)
  }
  //override def toUpdate = super.toUpdate || fu==null || (fu.isDefined && fu.get.toUpdate) 

  def addUse(reg:Reg):Unit = { uses += reg }
  def addDef(reg:Reg):Unit = { defs += reg }
  def addLiveIn(reg:Reg):Unit = { liveIns += reg}
  def addLiveOut(reg:Reg):Unit = { liveOuts += reg }
  def isHead = this==ctrler.stages.head
  def isLast = this==ctrler.stages.last
  def isReduce = this.isInstanceOf[ReduceStage]

  //TODO
  var operands:List[Any] = Nil
  var op:Op = _
  var results:List[Any] = Nil

  ctrler.addStage(this)
} 
object Stage {
  def apply(operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:ComputeUnit, design:PIR):Stage= {
    val stage = new LocalStage
    stage.operands = operands
    stage.op = op
    stage.results = results
    stage
  }
  def reduce(op:Op, init:Const[_<:AnyVal])(implicit ctrler:ComputeUnit, design:PIR):(List[Stage], Reg) = {
    reduce(op, init, Right(ctrler))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:ComputeUnit)(implicit ctrler:ComputeUnit, design:PIR):(List[Stage], Reg) = {
    reduce(op, init, Right(accumParent))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:String)(implicit ctrler:ComputeUnit, design:PIR):(List[Stage], Reg) = {
    reduce(op, init, Left(accumParent))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:Either[String, ComputeUnit])(implicit ctrler:ComputeUnit, design:PIR):(List[Stage], Reg) = {
    val acc = ctrler.accum(init)
    acc.updateParent(accumParent)
    val stages = ReduceStages(
      operands=List(ctrler.reduce, acc), 
      op=op,
      results=List(acc)
    )
    (stages, acc)
  }
}
class LocalStage(implicit ctrler:ComputeUnit, design: PIR) extends Stage { override val typeStr = s"LStage" }
case class ReduceStage()(implicit ctrler:ComputeUnit, design: PIR) extends LocalStage {
  lazy val idx:Int = ctrler.stages.collect{ case rs:ReduceStage => rs }.indexOf(this)
  override val typeStr = s"RedStage"
}
object ReduceStages {
  def apply(operands:List[Any], op:Op, results:List[Any]) (implicit ctrler:ComputeUnit, design:PIR):List[Stage]= {
    val localCChain::rest = ctrler.cchains.filter { !_.isCopy }
    assert(rest.size==0)
    val numStages = (Math.ceil(Math.log(localCChain.inner.par))/Math.log(2)).toInt 
    val accumReg = operands.collect { case reg:AccumPR => reg }.head
    val reduceReg = operands.collect { case reg:ReducePR => reg}.head
    val rdstages = List.tabulate(numStages) {i => 
      val stage = ReduceStage()
      stage.operands = List(reduceReg, reduceReg)
      stage.op = op
      stage.results = List(reduceReg)
      stage
    }
    val accstage = AccumStage(accumReg)
    accstage.operands = List(ctrler.reduce, accumReg)
    accstage.op = op
    accstage.results = results 
    rdstages :+ accstage
  }
}

case class AccumStage(acc:AccumPR)(implicit ctrler:ComputeUnit, design: PIR) extends LocalStage {
  override def toUpdate = super.toUpdate || acc==null
  override val typeStr = s"AccStage"
}
class WAStage (implicit ctrler:ComputeUnit, design: PIR) extends Stage {
  override val typeStr = "WAStage"
  var srams:Either[List[String], ListBuffer[SRAM]] = _
  override def toUpdate = super.toUpdate || srams==null
}
//object WAStage {
  //def apply(operands:List[Any], op:Op, results:List[Any])
            //(implicit ctrler:ComputeUnit, design:PIR):Unit= {
    //val stage = new WAStage
    //Stage(stage, operands, op, results)
  //}
//}
class RAStage (implicit ctrler:ComputeUnit, design: PIR) extends Stage {
  override val typeStr = "RAStage"
  var srams:Either[List[String], ListBuffer[SRAM]] = _
  override def toUpdate = super.toUpdate || srams==null
}
//object RAStage {
  //def apply(operands:List[Any], op:Op, results:List[Any])
            //(implicit ctrler:ComputeUnit, design:PIR):Unit= {
    //val stage = new RAStage
    //Stage(stage, operands, op, results)
  //}
//}

