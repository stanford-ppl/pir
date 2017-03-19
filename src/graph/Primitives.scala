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
import pir.graph.enums._
import pir.mapper.PIRException
import pir.graph.traversal.ForwardRef
import pir.misc._


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
  override def toString = s"${super.toString}($scalar)"
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
  override def toString = s"${super.toString}($scalar)"
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
        val cins = c.ctrlIns.filter{_.asInstanceOf[CtrlInPort].ctrler==writer.ctrler}
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
  val operands = List.tabulate(oprds.size){ i => 
    //if (i==1) {
      ////TODO: HACK on mod operation
      //op match {
        //case FixMod =>
          //assert(oprds.size==2)
          //val opB = oprds(i)
          //opB.src match {
            //case Const(value) =>
              //if (value.isInstanceOf[Int]) throw PIRException(s"Do not support mod of non integer value")
              //val num = value.asInstanceOf[Int]
              //val log = Math.log(num) / Math.log(2)
              //if (Math.round(log)==log) {
                //val const = Const(Math.round(log).toInt)
                //op = FixSra
                //InPort(this, const.out, s"oprd(${const.out})")
              //} else
                //throw PIRException(s"Do not support mod of non power of 2 number")
            //case _ => throw PIRException(s"Do not support mod of non constant!")
          //}
        //case _ => InPort(this, oprds(i), s"oprd(${oprds(i)})")
      //}
    //} else {
      InPort(this, oprds(i), s"${oprds(i)}")
    //}
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
  override def toUpdate = 
    super.toUpdate || operands.map { !_.isConnected }.reduce{_ | _} || !out.isConnected
    val defs:List[Reg] = results.flatMap { _.src match {
        case PipeReg(s, reg) => Some(reg)
        case _ => None
      } 
    }.toList
  def defines(reg:Reg) = defs.contains(reg) 
}

class Stage(override val name:Option[String])(implicit override val ctrler:ComputeUnit, design: Design) extends Primitive {
  override val typeStr = "Stage"
  var fu:Option[FuncUnit] = _
  val prs:Map[Reg, PipeReg] = Map.empty
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
} 
object Stage {
  /* No Sugar API */
  def apply(operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:Design):Unit= {
    val stage = ctrler.stage
    Stage(stage, operands, op, results)
  }
  def apply(stage:Stage, operands:List[Any], op:Op, results:List[Any])
            (implicit ctrler:InnerController, design:Design):Unit= {
    val oprds = operands.map { 
      case o:OutPort => o
      case r:Reg => PipeReg(stage.prev.get, r).out
      case pr:PipeReg => pr.out
      case c:Const[_] => c.out
      case c:Counter => c.out
    }
    val res = results.map {
      case i:InPort => i
      case r:Reg => PipeReg(stage, r).in
      case pr:PipeReg => pr.in
    }
    stage.fu = Some(new FuncUnit(stage, oprds, op, res))
  }
  //def apply(stage:Stage, operands:List[OutPort], op:Op, results:List[InPort])
            //(implicit ctrler:InnerController, design:Design):Unit= {
    //stage.fu = Some(new FuncUnit(stage, operands, op, results))
    ////ctrler.addStage(stage)
  //}
  /* Sugar API */
  def apply(stage:Stage, op1:OutPort, op:Op, result:InPort)(implicit ctrler:InnerController, design:Design):Unit =
    Stage(stage, List(op1), op, List(result))
  def apply(stage:Stage, op1:OutPort, op2:OutPort, op:Op, result:InPort)(implicit ctrler:InnerController, design:Design):Unit = 
    Stage(stage, List(op1, op2), op, List(result))
  def apply(stage:Stage, op1:OutPort, op2:OutPort, op3:OutPort, op:Op, result:InPort)(implicit ctrler:InnerController, design:Design):Unit =
    Stage(stage, List(op1, op2, op3), op, List(result))

  //TODO check init type matches with op type
  def reduce(op:Op, init:Const[_<:AnyVal])(implicit ctrler:InnerController, design:Design):(List[Stage], PipeReg) = {
    val numStages = (Math.ceil(Math.log(design.arch.numLanes))/Math.log(2)).toInt 
    val rdstages = Stages.reduce(numStages, op) 
    val acc = ctrler.accum(init)
    val (accstage, reg) = Stages.accum(ctrler.reduce(rdstages.last), op, acc) 
    (rdstages :+ accstage, reg)
  }
}
object Stages {
  def apply(n:Int) (implicit ctrler:InnerController, design: Design):List[LocalStage] = {
    //List.tabulate(n) { i => LocalStage(None) }
    List.tabulate(n) { i => ctrler.stage }
  }
  def reduce(n:Int, op:Op) (implicit ctrler:InnerController, design: Design):List[ReduceStage] = {
    val rdStages = List.tabulate(n) {i => ctrler.reduceStage }
    rdStages.foreach { stage =>
      val preg = ctrler.reduce(stage.prev.get)
      val creg = ctrler.reduce(stage)
      Stage(stage, op1=preg.out, op2=preg.out, op, result=creg.in)
    }
    rdStages
  }
  /* Create an accumulation stage
   * @param operand operand to accumulate. i.e. acc = acc + operand
   * @init initial value of accumulator
   * @op accumulation operand
   * Returns the accumulation stage and PipeReg of the accumulator
   * */
  def accum(operand:PipeReg, op:Op, acc:AccumPR) (implicit ctrler:InnerController, design: Design):
    (AccumStage, PipeReg) = {
    val s = AccumStage(acc)
    val areg = ctrler.accum(s, acc)
    Stage(s, op1=operand.out, op2=areg.read, op, areg.in)
    (s, areg)
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
  var srams:Either[List[String], ListBuffer[SRAMOnWrite]] = _
  override def toUpdate = super.toUpdate || srams==null

  def updateSRAM(n:Node) = {
    srams match {
      case Left(_) => srams = Right(ListBuffer(n.asInstanceOf[SRAMOnWrite]))
      case Right(l) => l += n.asInstanceOf[SRAMOnWrite]
    }
  }

  def updateSRAMs[T](srams:List[T])(implicit ev:TypeTag[T]):WAStage = {
    typeOf[T] match {
      case t if t =:= typeOf[String] => 
        this.srams = Left(srams.asInstanceOf[List[String]])
        srams.asInstanceOf[List[String]].foreach { s =>
          design.updateLater(ForwardRef.getPrimName(ctrler, s), updateSRAM _)
        }
      case t if t <:< typeOf[SRAMOnWrite] => 
        this.srams = Right(srams.asInstanceOf[List[SRAMOnWrite]].to[ListBuffer])
    }
    this
  }
}
class RAStage (override val name:Option[String])
  (implicit ctrler:ComputeUnit, design: Design) extends Stage(name) {
  override val typeStr = "RAStage"
  var srams:Either[List[String], ListBuffer[SRAMOnRead]] = _
  override def toUpdate = super.toUpdate || srams==null

  def updateSRAM(n:Node) = {
    srams match {
      case Left(_) => srams = Right(ListBuffer(n.asInstanceOf[SRAMOnRead]))
      case Right(l) => l += n.asInstanceOf[SRAMOnRead]
    }
  }

  def updateSRAMs[T](srams:List[T])(implicit ev:TypeTag[T]):RAStage = {
    typeOf[T] match {
      case t if t =:= typeOf[String] => 
        this.srams = Left(srams.asInstanceOf[List[String]])
        srams.asInstanceOf[List[String]].foreach { s =>
          design.updateLater(ForwardRef.getPrimName(ctrler, s), updateSRAM _)
        }
      case t if t <:< typeOf[SRAMOnRead] => 
        this.srams = Right(srams.asInstanceOf[List[SRAMOnRead]].to[ListBuffer])
    }
    this
  }
}

object WAStage {
  def apply[T](srams:List[T])(implicit ev:TypeTag[T], ctrler:InnerController, design: Design)  = new WAStage(None).updateSRAMs(srams)
}
object WAStages {
  def apply[T](n:Int, srams:List[T]) (implicit ev:TypeTag[T], ctrler:InnerController, design: Design):List[WAStage] = {
    val was = List.tabulate(n) { i => WAStage(srams) }
    ctrler.addWAStages(was)
    was
  }
}
object RAStage {
  def apply[T](srams:List[T])(implicit ev:TypeTag[T], ctrler:InnerController, design: Design)  = new RAStage(None).updateSRAMs(srams)
}
object RAStages {
  def apply[T](n:Int, srams:List[T]) (implicit ev:TypeTag[T], ctrler:InnerController, design: Design):List[RAStage] = {
    val ras = List.tabulate(n) { i => RAStage(srams) }
    ctrler.addRAStages(ras)
    ras
  }
}
trait EmptyStage extends Stage {
  override val typeStr = "EmptyStage"
  fu = None
}
object EmptyStage {
  def apply(name:Option[String])(implicit ctrler:ComputeUnit, design: Design):EmptyStage  = 
    new Stage(name) with EmptyStage
  def apply()(implicit ctrler:ComputeUnit, design: Design):EmptyStage  = 
    new Stage(None) with EmptyStage
}

trait Reg extends Primitive {
  val regId:Int
  override val typeStr = "reg"
  override val name = None
  override def toString = s"${super.toString}_${regId}"
  override def equals(that: Any) = that match {
    case n: Reg => regId == n.regId && ctrler == n.ctrler
    case _ => false 
  }
  override def hashCode:Int = { ctrler.hashCode *10 + regId }
}
object Reg {
  def apply(rid:Int)(implicit ctrler:Controller, design:Design) = new Reg {override val regId = rid}
}
case class LoadPR(override val regId:Int, mem:OnChipMem)(implicit ctrler:ComputeUnit, design: Design)         extends Reg {override val typeStr = "regld"}
case class StorePR(override val regId:Int, mem:OnChipMem)(implicit ctrler:InnerController, design: Design)        extends Reg {override val typeStr = "regst"}
//case class RdAddrPR(override val regId:Int)(implicit ctrler:Controller, design: Design)                           extends Reg {override val typeStr = "regra"; val raPorts = ListBuffer[InPort]()}
case class WtAddrPR(override val regId:Int, waPort:WtAddrInPort)(implicit ctrler:InnerController, sAdesign: Design)         extends Reg {override val typeStr = "regwa"}
case class CtrPR(override val regId:Int, ctr:Counter)(implicit ctrler:ComputeUnit, design: Design)                 extends Reg {override val typeStr = "regct"}
case class ReducePR(override val regId:Int)(implicit ctrler:InnerController, design: Design)                           extends Reg {override val typeStr = "regrd"}
case class AccumPR(override val regId:Int, init:Const[_<:AnyVal])(implicit ctrler:InnerController, design: Design)                extends Reg {override val typeStr = "regac"}
case class VecInPR(override val regId:Int, vecIn:VecIn)(implicit ctrler:Controller, design: Design)               extends Reg {override val typeStr = "regvi"}
case class VecOutPR(override val regId:Int)(implicit ctrler:Controller, design: Design)                           extends Reg {override val typeStr = "regvo"; var vecOut:VecOut = _}
case class ScalarInPR(override val regId:Int, scalarIn:ScalarIn)(implicit ctrler:Controller, design: Design)      extends Reg {override val typeStr = "regsi"}
case class ScalarOutPR(override val regId:Int, scalarOut:ScalarOut)(implicit ctrler:Controller, design: Design)   extends Reg {override val typeStr = "regso"}
/*
 * A Pipeline Register keeping track of which stage (column) and which logical register (row)
 * the PR belongs to
 * @param n Optional user defined name
 * @param regId Register ID the PipeReg mapped to
 **/
case class PipeReg(stage:Stage, reg:Reg)(implicit ctrler:Controller, design: Design) extends Primitive{
  override val name = None
  val in:InPort = InPort(this, s"${this}") 
  val out:OutPort = OutPort(this, {s"${this}"}) 
  def read:OutPort = out
  def write(p:OutPort):Unit = in.connect(p) 
  override val typeStr = "PR"
  override def toString = s"s${stage.id}_${reg}" 
  override def equals(that: Any) = that match {
    case n: PipeReg => stage==n.stage && reg == n.reg && ctrler == n.ctrler
    case _ => false 
  }
}

case class Const[T<:AnyVal](value:T)(implicit design: Design) extends Node {
  override val typeStr = "Const"
  val name:Option[String] = Some(s"$value")
  val out = OutPort(this, s"Const(${value})")
}
