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

abstract class Reg(implicit override val ctrler:ComputeUnit, design:PIR) extends Primitive { self:Product =>
  import pirmeta._
  lazy val regId:Int = ctrler.newTemp
  override def toString = scala.runtime.ScalaRunTime._toString(this).replace("(", s"${regId}${this.name.fold("")(n => s"_${n}")}(")
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
case class StorePR(mem:OnChipMem)(implicit ctrler:ComputeUnit, design: PIR)          extends Reg {override val typeStr = "regst"}
case class RdAddrPR(raPort:Input)(implicit ctrler:ComputeUnit, design: PIR)         extends Reg {override val typeStr = "regra"; }
case class WtAddrPR(waPort:Input)(implicit ctrler:ComputeUnit, sAdesign: PIR)       extends Reg {override val typeStr = "regwa"}
case class CtrPR(ctr:Counter)(implicit ctrler:ComputeUnit, design: PIR)                  extends Reg {override val typeStr = "regct"}
case class ReducePR()(implicit ctrler:ComputeUnit, design: PIR)                      extends Reg {override val typeStr = "regrd"}
case class AccumPR(init:Const[_<:AnyVal])(implicit ctrler:ComputeUnit, design: PIR)  extends Reg {
  override val typeStr = "regac"
  var accumParent:Either[String, ComputeUnit] = Right(ctrler)
  def updateParent(parent:Either[String, ComputeUnit]):Unit = {
    parent match { 
      case Left(parent) => 
        def updateFunc(parent:Node) = updateParent(Right(parent.asInstanceOf[ComputeUnit])) 
        design.lazyUpdate(parent, updateFunc _ )
      case Right(parent) => accumParent = Right(parent)
    }
  }
  def parent(parent:String) = { updateParent(Left(parent)); this }
  def parent(parent:ComputeUnit) = { updateParent(Right(parent)); this }
}
case class VecInPR(vecIn:GlobalInput)(implicit ctrler:ComputeUnit, design: PIR)             extends Reg {override val typeStr = "regvi"}
case class VecOutPR(vecOut:GlobalOutput)(implicit ctrler:ComputeUnit, design: PIR)          extends Reg {override val typeStr = "regvo"}
case class ScalarInPR(scalarIn:GlobalInput)(implicit ctrler:ComputeUnit, design: PIR)    extends Reg {override val typeStr = "regsi"}
case class ScalarOutPR(scalarOut:GlobalOutput)(implicit ctrler:ComputeUnit, design: PIR) extends Reg {override val typeStr = "regso"}
case class TempPR(init:Option[AnyVal])(implicit ctrler:ComputeUnit, design: PIR)  extends Reg {override val typeStr = "regtp"}
/*
 * A Pipeline Register keeping track of which stage (column) and which logical register (row)
 * the PR belongs to
 * @param n Optional user defined name
 * @param regId Register ID the PipeReg mapped to
 **/
case class PipeReg(stage:Stage, reg:Reg)(implicit val ctrler:Controller, design: PIR) extends Primitive{
  import design.pirmeta._
  val in:Input = Input(this, s"${this}.in") 
  val out:Output = Output(this, {s"${this}.out"}) 
  def read:Output = out
  def write(p:Output):Unit = in.connect(p) 
  override val typeStr = "PR"
  override def toString = s"PR(${quote(stage)}, ${quote(reg)})" 
  override def equals(that: Any) = that match {
    case n: PipeReg => stage==n.stage && reg == n.reg && ctrler == n.ctrler
    case _ => false 
  }
  override def name(n:String):this.type = { nameOf(this) = n; nameOf(reg) = n; this }
}

case class Const[T<:AnyVal](value:T)(implicit design: PIR) extends Module {
  override val typeStr = "Const"
  this.name(value.toString)
  val out = Output(this, s"Const${id}(${value})")

  def isBool = value.isInstanceOf[Boolean]
  def isInt = value.isInstanceOf[Int]
  def isFloat = value.isInstanceOf[Float]
}

trait MuxLike extends Primitive {
  override val typeStr = "Mux"
  val _inputs = ListBuffer[Input]()
  def inputs = _inputs.toList
  val sel = Input(this, s"${this}.sel") 
  val out = Output(this, s"$this.out")
  def addInput:Input = { val i = _inputs.size; val in = Input(this, s"$this.in$i"); _inputs += in; in }
}

case class Mux()(implicit design:PIR, val ctrler:Controller) extends MuxLike
case class ValidMux()(implicit design:PIR, val ctrler:Controller) extends MuxLike {
  val valid = Output(this, s"$this.valid")
}
