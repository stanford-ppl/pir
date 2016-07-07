package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design
import dhdl.graph._

class Range (s:Wire, e:Wire) {
  val start:Wire = s
  val end:Wire = e
  def by(step:Wire) = (start, end, step)
}

/**
 * An unassigned wire used to represent scalar outputs prior to assignment
 * @param tp: The type of value represented by this wire
 */
class Wire(nameStr: Option[String], w:Option[Int], typeStr:String)(implicit design: Design) extends Node(nameStr, typeStr) {
  val width:Int = w.getOrElse(DefaultPrecision)

  def by(step:Wire) = (Const(0l, step.width), this, step)
  def until(max:Wire) = new Range(this, max)
  def isConst = this.isInstanceOf[Const] 
}
object Wire {
  def apply() (implicit design:Design):Wire = new Wire(None, None, "Wire")
  def apply(w:Int) (implicit design:Design):Wire = new Wire(None, Some(w), "Wire")
  def apply(name:String, w:Int) (implicit design:Design):Wire = new Wire(Some(name), Some(w), "Wire")
}

trait Const extends Wire {
  val value:Option[Long]
  override def toString = s"Const(${value.get})"
}
object Const {
  def apply(nameStr:Option[String], v:Option[Long], w:Option[Int])(implicit design: Design) =
   new {override val value = v} with Wire(nameStr, w, "Const") with Const
  def apply(v:Long, w:Int) (implicit design:Design):Const = Const(None, Some(v), Some(w))
  def apply(v:Long) (implicit design:Design):Const = Const(None, Some(v), None)
  def apply(name:String, v:Long) (implicit design:Design):Const = Const(Some(name), Some(v), None)
}

trait ArgIn extends Wire
object ArgIn {
  def apply(nameStr:Option[String], w:Option[Int])(implicit design: Design) =
    new Wire(nameStr, w, "ArgIn") with ArgIn
  def apply() (implicit design:Design):ArgIn = ArgIn(None, None)
  def apply(w:Int) (implicit design:Design):ArgIn = ArgIn(None, Some(w))
  def apply(name:String, w:Int) (implicit design:Design):ArgIn = ArgIn(Some(name), Some(w))
}

/** Scalar values passed from accelerator to host CPU code via memory-mapped registers.
 *  Represent scalar outputs from the accelerator, and are write-only from accelerator.
 *  @param value: Combinational node whose value is to be output to CPU
 */
trait ArgOut extends Wire
object ArgOut {
  def apply(nameStr:Option[String], value:Wire, w:Option[Int])(implicit design: Design) =
    new Wire(nameStr, w, "ArgOut") with ArgOut
  def apply(value:Wire) (implicit design:Design):ArgOut = ArgOut(None, value, None)
  def apply(value:Wire, w:Int) (implicit design:Design):ArgOut = ArgOut(None, value, Some(w))
  def apply(name:String, value:Wire, w:Int) (implicit design:Design):ArgOut = ArgOut(Some(name), value, Some(w))
}
