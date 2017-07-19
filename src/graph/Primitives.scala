package pir.graph

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.math.max
import scala.reflect.runtime.universe._
import scala.language.existentials
import pir.{Design, Config}
import pir.graph._
import pir.util.enums._
import pir.exceptions._
import pir.pass.ForwardRef
import pir.util._


abstract class Primitive(implicit val ctrler:Controller, design:Design) extends Node 
/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */

trait IO extends Primitive
trait Input extends IO {
  def writer:Output
  def variable:Variable
  def out:OutPort
}
trait Output extends IO {
  def readers:List[Input]
}
trait VectorIO[T <: IO] { self:T => 
  def vector:Vector
  def isConnected:Boolean
}

case class ScalarIn(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) 
  extends Input {
  scalar.addReader(this)
  override val typeStr = "ScalIn"
  override def equals(that: Any) = that match {
    case n: ScalarIn => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  override def variable:Scalar = scalar
  override def writer = scalar.writer
  val out = OutPort(this, s"${this}.out")
}

object ScalarIn {
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn = 
    ScalarIn(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn =
    ScalarIn(Some(name), scalar)
}

case class ScalarOut(name: Option[String], scalar:Scalar)(implicit override val ctrler:Controller, design: Design) extends Output{
  scalar.setWriter(this)
  override val typeStr = "ScalOut"
  override def equals(that: Any) = that match {
    case n: ScalarOut => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  val in = InPort(this, s"${this}.in")
  def readers = scalar.readers.toList
}
object ScalarOut {
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(Some(name), scalar)
}

case class VecIn(name: Option[String], vector:Vector)(implicit ctrler:Controller, design: Design) 
  extends Input with VectorIO[Input] {
  vector.addReader(this)
  override val typeStr = "VecIn"
  val out = OutPort(this, {s"${this}.out"}) 
  override def equals(that: Any) = that match {
    case n: VecIn => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  override def variable:Vector = vector
  override def writer = vector.writer
  def isConnected = writer!=null

  /* Associated TokenIn for this VecIn */
  def tokenIn:Option[InPort] = {
    ctrler match {
      case c:Controller =>
        val cins = c.cins.filter{_.asInstanceOf[CtrlInPort].ctrler==writer.ctrler}
        if (cins.size==0) None
        else {
          assert(cins.size==1, s"$this should only have <= one tokenIn associated with but has ${cins}")
          Some(cins.head)
        }
      case _ => None
    }
  }
}
object VecIn {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(Some(name), vector)
}

class DummyVecIn(name: Option[String], override val vector:DummyVector)(implicit ctrler:Controller, design: Design) extends VecIn(name, vector) {
  override val typeStr = "DVecIn"
  override def writer:DummyVecOut = vector.writer
}

class VecOut(val name: Option[String], val vector:Vector)(implicit override val ctrler:Controller, design: Design) extends Output with VectorIO[Output] {
  vector.setWriter(this)
  override val typeStr = "VecOut"
  override def equals(that: Any) = that match {
    case n: VecOut => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
  val in = InPort(this, s"${this}.in")
  def isConnected = vector.readers.size!=0
  def readers = vector.readers
}
object VecOut {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    new VecOut(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    new VecOut(Some(name), vector)
}

class DummyVecOut(name: Option[String], override val vector:DummyVector)(implicit ctrler:Controller, design: Design) extends VecOut(name, vector) {
  override val typeStr = "DVecOut"
  def scalarOuts = vector.scalars.map(_.writer)
  override def readers:List[DummyVecIn] = vector.readers.toList
}

class FuncUnit(val stage:Stage, oprds:List[OutPort], var op:Op, results:List[InPort])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "FU"
  override val name = None
  val operands = List.tabulate(oprds.size) { i => 
    val in = InPort(this, s"${this}.oprd($i)")
    in.connect(oprds(i))
    in
  }
  val out = OutPort(this, s"${this}.out")
  results.foreach { res => 
    res.src match {
      case PipeReg(s, r) if (s!=stage) => 
        throw PIRException(s"Function Unit can only write to current stage ($stage) but writes to $s")
      case _ =>
    }
    res.connect(out) 
  }
  override def toUpdate = super.toUpdate || operands.map { !_.isConnected }.reduce{_ | _} || !out.isConnected
  val defs:List[Reg] = results.flatMap { _.src match {
      case PipeReg(s, reg) => Some(reg)
      case _ => None
    } 
  }.toList
  def writesTo(reg:Reg) = defs.contains(reg) 
}

class Stage(override val name:Option[String])(implicit override val ctrler:ComputeUnit, design: Design) extends Primitive {
  override val typeStr = "Stage"
  var fu:Option[FuncUnit] = _
  val _prs:Map[Reg, PipeReg] = Map.empty
  def prs:List[PipeReg] = _prs.values.toList
  def get(reg:Reg) = _prs.getOrElseUpdate(reg, PipeReg(this, reg))
  def remove(reg:Reg) = _prs -= reg
  val defs:Set[Reg] = Set.empty
  val uses:Set[Reg] = Set.empty
  var liveIns:ISet[Reg] = ISet.empty
  var liveOuts:ISet[Reg] = ISet.empty
  var prev:Option[Stage] = None
  var next:Option[Stage] = None
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
            (implicit ctrler:InnerController, design:Design):Unit= {
    val stage = LocalStage(None) 
    Stage(stage, operands, op, results)
  }
  def apply(stage:Stage, operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:Design):Unit= {
    ctrler.addStage(stage)
    val oprds = operands.map { 
      case o:OutPort => o
      case CtrPR(ctr) if stage.prev.isEmpty => ctr.out
      case LoadPR(mem) if stage.prev.isEmpty => mem.load
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
  def reduce(op:Op, init:Const[_<:AnyVal])(implicit ctrler:InnerController, design:Design):(List[Stage], PipeReg) = {
    reduce(op, init, Right(ctrler))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:ComputeUnit)(implicit ctrler:InnerController, design:Design):(List[Stage], PipeReg) = {
    reduce(op, init, Right(accumParent))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:String)(implicit ctrler:InnerController, design:Design):(List[Stage], PipeReg) = {
    reduce(op, init, Left(accumParent))
  }
  def reduce(op:Op, init:Const[_<:AnyVal], accumParent:Either[String, ComputeUnit])(implicit ctrler:InnerController, design:Design):(List[Stage], PipeReg) = {
    val localCChain::rest = ctrler.cchains.filter { !_.isCopy }
    assert(rest.size==0)
    val numStages = (Math.ceil(Math.log(localCChain.inner.par))/Math.log(2)).toInt 
    val rdstages = List.tabulate(numStages) {i => ReduceStage(None) }
    rdstages.foreach { stage =>
      val preg = ctrler.reduce(ctrler.stages.last)
      val creg = ctrler.reduce(stage)
      Stage(stage, operands=List(preg.out, preg.out), op, results=List(creg.in))
    }
    val acc = ctrler.accum(init)
    acc.updateParent(accumParent)
    val accstage = AccumStage(acc)
    val areg = ctrler.accum(accstage, acc)
    Stage(
      stage=accstage, 
      operands=List(ctrler.reduce((ctrler.stages ++ rdstages).last).out, areg.read), 
      op=op, 
      results=List(areg.in)
    )
    (rdstages :+ accstage, areg)
  }
}
trait LocalStage extends Stage { override val typeStr = s"LStage" }
object LocalStage {
  def apply(name:Option[String])(implicit ctrler:ComputeUnit, design: Design) =
    new Stage(name) with LocalStage
}
case class ReduceStage(override val name:Option[String])(implicit ctrler:ComputeUnit, design: Design)
 extends Stage(name) with LocalStage {
  lazy val idx:Int = ctrler.stages.collect{ case rs:ReduceStage => rs }.indexOf(this)
  override val typeStr = s"RedStage"
}

trait AccumStage extends LocalStage {
  val accReg:AccumPR
  override def toUpdate = super.toUpdate || accReg==null
  override val typeStr = s"AccStage"
}
object AccumStage {
  def apply(acc:AccumPR)(implicit ctrler:ComputeUnit, design: Design) = 
    new {override val accReg = acc} with Stage(None) with AccumStage
}
class WAStage (override val name:Option[String])
  (implicit ctrler:ComputeUnit, design: Design) extends Stage(name) {
  override val typeStr = "WAStage"
  var srams:Either[List[String], ListBuffer[SRAM]] = _
  override def toUpdate = super.toUpdate || srams==null

  def updateSRAM(n:Node) = {
    srams match {
      case Left(_) => srams = Right(ListBuffer(n.asInstanceOf[SRAM]))
      case Right(l) => l += n.asInstanceOf[SRAM]
    }
  }
  def updateSRAMs[T](srams:List[T])(implicit ev:TypeTag[T]):WAStage = {
    typeOf[T] match {
      case t if t =:= typeOf[String] => 
        this.srams = Left(srams.asInstanceOf[List[String]])
        srams.asInstanceOf[List[String]].foreach { s =>
          design.updateLater(ForwardRef.getPrimName(ctrler, s), updateSRAM _)
        }
      case t if t <:< typeOf[SRAM] => 
        this.srams = Right(srams.asInstanceOf[List[SRAM]].to[ListBuffer])
    }
    this
  }
}
object WAStage {
  def apply(operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:Design):Unit= {
    val stage = WAStage(Nil) 
    Stage(stage, operands, op, results)
  }
  def apply[T](srams:List[T])(implicit ev:TypeTag[T], ctrler:InnerController, design: Design)  = new WAStage(None).updateSRAMs(srams)
}
class RAStage (override val name:Option[String])
  (implicit ctrler:ComputeUnit, design: Design) extends Stage(name) {
  override val typeStr = "RAStage"
  var srams:Either[List[String], ListBuffer[SRAM]] = _
  override def toUpdate = super.toUpdate || srams==null

  def updateSRAM(n:Node) = {
    srams match {
      case Left(_) => srams = Right(ListBuffer(n.asInstanceOf[SRAM]))
      case Right(l) => l += n.asInstanceOf[SRAM]
    }
  }

  def updateSRAMs[T](srams:List[T])(implicit ev:TypeTag[T]):RAStage = {
    typeOf[T] match {
      case t if t =:= typeOf[String] => 
        this.srams = Left(srams.asInstanceOf[List[String]])
        srams.asInstanceOf[List[String]].foreach { s =>
          design.updateLater(ForwardRef.getPrimName(ctrler, s), updateSRAM _)
        }
      case t if t <:< typeOf[SRAM] => 
        this.srams = Right(srams.asInstanceOf[List[SRAM]].to[ListBuffer])
    }
    this
  }
}
object RAStage {
  def apply(operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:Design):Unit= {
    val stage = RAStage(Nil) 
    Stage(stage, operands, op, results)
  }
  def apply[T](srams:List[T])(implicit ev:TypeTag[T], ctrler:InnerController, design: Design)  = new RAStage(None).updateSRAMs(srams)
}
//trait EmptyStage extends Stage {
  //override val typeStr = "EmptyStage"
  //fu = None
//}
//object EmptyStage {
  //def apply(name:Option[String])(implicit ctrler:ComputeUnit, design: Design):EmptyStage  = 
    //new Stage(name) with EmptyStage
  //def apply()(implicit ctrler:ComputeUnit, design: Design):EmptyStage  = 
    //new Stage(None) with EmptyStage
//}

abstract class Reg(implicit override val ctrler:ComputeUnit, design:Design) extends Primitive {
  import pirmeta._
  lazy val regId:Int = ctrler.newTemp
  override val typeStr = "reg"
  override val name = None
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
case class LoadPR(mem:OnChipMem)(implicit ctrler:ComputeUnit, design: Design)               extends Reg {override val typeStr = "regld"}
case class StorePR(mem:OnChipMem)(implicit ctrler:InnerController, design: Design)          extends Reg {override val typeStr = "regst"}
case class RdAddrPR(raPort:RdAddrInPort)(implicit ctrler:InnerController, design: Design)   extends Reg {override val typeStr = "regra"; }
case class WtAddrPR(waPort:WtAddrInPort)(implicit ctrler:InnerController, sAdesign: Design) extends Reg {override val typeStr = "regwa"}
case class CtrPR(ctr:Counter)(implicit ctrler:ComputeUnit, design: Design)                  extends Reg {override val typeStr = "regct"}
case class ReducePR()(implicit ctrler:InnerController, design: Design)                      extends Reg {override val typeStr = "regrd"}
case class AccumPR(init:Const[_<:AnyVal])(implicit ctrler:InnerController, design: Design)  extends Reg {
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
case class VecInPR(vecIn:VecIn)(implicit ctrler:ComputeUnit, design: Design)                extends Reg {override val typeStr = "regvi"}
case class VecOutPR(vecOut:VecOut)(implicit ctrler:ComputeUnit, design: Design)             extends Reg {override val typeStr = "regvo"}
case class ScalarInPR(scalarIn:ScalarIn)(implicit ctrler:ComputeUnit, design: Design)       extends Reg {override val typeStr = "regsi"}
case class ScalarOutPR(scalarOut:ScalarOut)(implicit ctrler:ComputeUnit, design: Design)    extends Reg {override val typeStr = "regso"}
case class TempPR()(implicit ctrler:InnerController, design: Design)                        extends Reg {override val typeStr = "regtp"}
/*
 * A Pipeline Register keeping track of which stage (column) and which logical register (row)
 * the PR belongs to
 * @param n Optional user defined name
 * @param regId Register ID the PipeReg mapped to
 **/
case class PipeReg(stage:Stage, reg:Reg)(implicit ctrler:Controller, design: Design) extends Primitive{
  override val name = None
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

case class Const[T<:AnyVal](value:T)(implicit design: Design) extends Node {
  override val typeStr = "Const"
  val name:Option[String] = Some(s"$value")
  val out = OutPort(this, s"Const${id}(${value})")

  def isBool = value.isInstanceOf[Boolean]
  def isInt = value.isInstanceOf[Int]
  def isFloat = value.isInstanceOf[Float]
}
