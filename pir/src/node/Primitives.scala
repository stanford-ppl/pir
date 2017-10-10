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

trait Primitive extends Module { implicit def ctrler:Controller }
/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */

class FuncUnit(val stage:Stage, oprds:List[OutPort], var op:Op, results:List[InPort])
  (implicit override val ctrler:ComputeUnit, design: PIR) extends Primitive {
  override val typeStr = "FU"
  val operands = oprds.zipWithIndex.map { case (oprd, i) => 
    val in = InPort(this, s"${this}.oprd($i)")
    oprd.src match {
      case PipeReg(s, r) if (s!=stage && s!=stage.prev.get) =>
        var info = s"ctrler:${ctrler}\n"
        info += s"stages:\n"
        ctrler.stages.foreach { stage =>
          if (stage.fu != null) // Refactor this
          info += s"$stage(op=${stage.fu.get.op})\n"
        }
        throw PIRException(s"Function Unit can only read from current stage($stage) or previous stage(${stage.prev}) but reads from $s\n$info")
      case PipeReg(s, r) =>
        s.addUse(r)
        in.connect(oprd)
      case _ => in.connect(oprd)
    }
    in
  }
  val out = OutPort(this, s"${this}.out")
  results.foreach { res => 
    res.src match {
      case PipeReg(s, r) if (s!=stage) => 
        throw PIRException(s"Function Unit can only write to current stage ($stage) but writes to $s")
      case PipeReg(s, r) =>
        res.connect(out)
        s.addDef(r)
      case sram:SRAM if sram.writeAddr==res => sram.wtAddr(out)
      case sram:SRAM if sram.readAddr==res => sram.rdAddr(out)
      case _ => res.connect(out) 
    }
  }
  override def toUpdate = super.toUpdate || operands.map { !_.isConnected }.reduce{_ | _} || !out.isConnected
  val defs:List[Reg] = results.flatMap { _.src match {
      case PipeReg(s, reg) => Some(reg)
      case _ => None
    } 
  }.toList
  def writesTo(reg:Reg) = defs.contains(reg) 
}

class Stage(implicit override val ctrler:ComputeUnit, design: PIR) extends Primitive {
  override val typeStr = "Stage"
  var fu:Option[FuncUnit] = _
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
  override def toUpdate = super.toUpdate || fu==null || (fu.isDefined && fu.get.toUpdate) 

  def addUse(reg:Reg):Unit = { uses += reg }
  def addDef(reg:Reg):Unit = { defs += reg }
  def addLiveIn(reg:Reg):Unit = { liveIns += reg}
  def addLiveOut(reg:Reg):Unit = { liveOuts += reg }
  def isHead = this==ctrler.stages.head
  def isLast = this==ctrler.stages.last
  def isReduce = this.isInstanceOf[ReduceStage]
} 
object Stage {
  /* No Sugar API */
  def apply(operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:PIR):Unit= {
    val stage = new LocalStage
    Stage(stage, operands, op, results)
  }
  def apply(stage:Stage, operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:PIR):Unit= {
    ctrler.addStage(stage)
    val oprds = operands.map { 
      case o:OutPort => o
      case CtrPR(ctr) if stage.prev.isEmpty => ctr.out
      case LoadPR(mem) if stage.prev.isEmpty => mem.load
      case r@TempPR(init) if stage.prev.isEmpty => stage.get(r).out // First stage read from initialized register
      case r@TempPR(init) if !stage.allPrevs.exists{_.defs.contains(r) } => stage.get(r).out
      case r:Reg => stage.prev.get.get(r).out
      case pr:PipeReg => pr.out
      case c:Const[_] => c.out
    }
    val res = results.map {
      case i:InPort => i
      case r:Reg => stage.get(r).in
      case pr:PipeReg => pr.in
    }
    stage.fu = Some(new FuncUnit(stage, oprds, op, res))
  }
  //TODO check init type matches with op type
  def reduce(op:Op, init:Const[_<:AnyVal])(implicit ctrler:InnerController, design:PIR):(List[Stage], Reg) = {
    reduce(op, init, Right(ctrler))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:ComputeUnit)(implicit ctrler:InnerController, design:PIR):(List[Stage], Reg) = {
    reduce(op, init, Right(accumParent))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:String)(implicit ctrler:InnerController, design:PIR):(List[Stage], Reg) = {
    reduce(op, init, Left(accumParent))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:Either[String, ComputeUnit])(implicit ctrler:InnerController, design:PIR):(List[Stage], Reg) = {
    if (ctrler.cchains.isEmpty) {
      CounterChain.dummy
    }
    val localCChain::rest = ctrler.cchains.filter { !_.isCopy }
    assert(rest.size==0)
    val numStages = (Math.ceil(Math.log(localCChain.inner.par))/Math.log(2)).toInt 
    val rdstages = List.tabulate(numStages) {i => ReduceStage() }
    rdstages.foreach { stage =>
      val preg = ctrler.reduce(ctrler.stages.last)
      val creg = ctrler.reduce(stage)
      Stage(stage, operands=List(preg.out, preg.out), op, results=List(creg.in))
    }
    val acc = ctrler.accum(init)
    acc.updateParent(accumParent)
    val accstage = AccumStage(acc)
    val areg = ctrler.accum(accstage, acc)
    val prevStage = (ctrler.stages ++ rdstages)
    Stage(
      stage=accstage, 
      operands=List(ctrler.reduce(prevStage.last).out, areg.read), 
      op=op, 
      results=List(areg.in)
    )
    (rdstages :+ accstage, acc)
  }
}
class LocalStage(implicit ctrler:ComputeUnit, design: PIR) extends Stage { override val typeStr = s"LStage" }
case class ReduceStage()(implicit ctrler:ComputeUnit, design: PIR)
 extends LocalStage {
  lazy val idx:Int = ctrler.stages.collect{ case rs:ReduceStage => rs }.indexOf(this)
  override val typeStr = s"RedStage"
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
object WAStage {
  def apply(operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:PIR):Unit= {
    val stage = new WAStage
    Stage(stage, operands, op, results)
  }
}
class RAStage (implicit ctrler:ComputeUnit, design: PIR) extends Stage {
  override val typeStr = "RAStage"
  var srams:Either[List[String], ListBuffer[SRAM]] = _
  override def toUpdate = super.toUpdate || srams==null
}
object RAStage {
  def apply(operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:PIR):Unit= {
    val stage = new RAStage
    Stage(stage, operands, op, results)
  }
}

abstract class Reg(implicit override val ctrler:ComputeUnit, design:PIR) extends Primitive { self:Product =>
  import pirmeta._
  lazy val regId:Int = ctrler.newTemp
  override def toString = scala.runtime.ScalaRunTime._toString(this).replace("(", s"$regId(")
  override def equals(that: Any) = that match {
    case n: Reg => regId == n.regId && ctrler == n.ctrler
    case _ => false 
  }
  override def hashCode:Int = { ctrler.hashCode *10 + regId }
  indexOf(this) = regId

  def isTemp = this.isInstanceOf[TempPR]
  def getInit:Option[AnyVal] = this match {
    case AccumPR(Const(init)) => Some(init)
    case r => None
  }
}
case class LoadPR(mem:OnChipMem)(implicit ctrler:ComputeUnit, design: PIR)               extends Reg {override val typeStr = "regld"}
case class StorePR(mem:OnChipMem)(implicit ctrler:InnerController, design: PIR)          extends Reg {override val typeStr = "regst"}
case class RdAddrPR(raPort:InPort)(implicit ctrler:InnerController, design: PIR)         extends Reg {override val typeStr = "regra"; }
case class WtAddrPR(waPort:InPort)(implicit ctrler:InnerController, sAdesign: PIR)       extends Reg {override val typeStr = "regwa"}
case class CtrPR(ctr:Counter)(implicit ctrler:ComputeUnit, design: PIR)                  extends Reg {override val typeStr = "regct"}
case class ReducePR()(implicit ctrler:InnerController, design: PIR)                      extends Reg {override val typeStr = "regrd"}
case class AccumPR(init:Const[_<:AnyVal])(implicit ctrler:InnerController, design: PIR)  extends Reg {
  override val typeStr = "regac"
  var accumParent:Either[String, ComputeUnit] = Right(ctrler)
  def updateParent(parent:Either[String, ComputeUnit]):Unit = {
    parent match { 
      case Left(parent) => 
        def updateFunc(parent:Node) = updateParent(Right(parent.asInstanceOf[ComputeUnit])) 
        design.updateLater(parent, updateFunc _ )
      case Right(parent) => accumParent = Right(parent)
    }
  }
}
case class VecInPR(vecIn:GlobalInput)(implicit ctrler:ComputeUnit, design: PIR)             extends Reg {override val typeStr = "regvi"}
case class VecOutPR(vecOut:GlobalOutput)(implicit ctrler:ComputeUnit, design: PIR)          extends Reg {override val typeStr = "regvo"}
case class ScalarInPR(scalarIn:GlobalInput)(implicit ctrler:ComputeUnit, design: PIR)    extends Reg {override val typeStr = "regsi"}
case class ScalarOutPR(scalarOut:GlobalOutput)(implicit ctrler:ComputeUnit, design: PIR) extends Reg {override val typeStr = "regso"}
case class TempPR(init:Option[AnyVal])(implicit ctrler:InnerController, design: PIR)  extends Reg {override val typeStr = "regtp"}
/*
 * A Pipeline Register keeping track of which stage (column) and which logical register (row)
 * the PR belongs to
 * @param n Optional user defined name
 * @param regId Register ID the PipeReg mapped to
 **/
case class PipeReg(stage:Stage, reg:Reg)(implicit val ctrler:Controller, design: PIR) extends Primitive{
  val in:InPort = InPort(this, s"${this}.in") 
  val out:OutPort = OutPort(this, {s"${this}.out"}) 
  def read:OutPort = out
  def write(p:OutPort):Unit = in.connect(p) 
  override val typeStr = "PR"
  override def toString = s"PR(${quote(stage)}, ${quote(reg)})" 
  override def equals(that: Any) = that match {
    case n: PipeReg => stage==n.stage && reg == n.reg && ctrler == n.ctrler
    case _ => false 
  }
}

case class Const[T<:AnyVal](value:T)(implicit design: PIR) extends Module {
  override val typeStr = "Const"
  this.name(value.toString)
  val out = OutPort(this, s"Const${id}(${value})")

  def isBool = value.isInstanceOf[Boolean]
  def isInt = value.isInstanceOf[Int]
  def isFloat = value.isInstanceOf[Float]
}

case class Mux()(implicit design:PIR, val ctrler:Controller) extends Primitive {
  override val typeStr = "Mux"
  val _inputs = ListBuffer[InPort]()
  val inputs = _inputs.toList
  val sel = InPort(this, s"${this}.sel") 
  val out = OutPort(this, s"$this.out")
  def addInput:InPort = { val i = _inputs.size; val in = InPort(this, s"$this.in$i"); _inputs += in; in }
}
