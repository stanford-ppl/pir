package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design

/** Base class for all DHDL nodes. This class contains
  * implementations of common methods that every DHDL node
  * can use and override
  */
abstract class Node(nameStr:Option[String], typeStr:String) (implicit design: Design) extends Product {
	design.addNode(this)
  //val name: String
  val name = nameStr.getOrElse("")
  val id : Int = design.nextId

  val DefaultPrecision = 32

  override def toString = name + typeStr
  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && name == n.name
    case _ => super.equals(that)
  }
}

/**
 * An unassigned wire used to represent scalar outputs prior to assignment
 * @param tp: The type of value represented by this wire
 */
case class Wire(nameStr: Option[String], v:Option[Long], prec:Option[Int])(implicit design: Design) extends Node(nameStr, "wire") {
  val precision:Int = prec.getOrElse(DefaultPrecision)
  val value:Option[Long] = v 

  def by(step:Wire) = (Const(0l, step.precision), this, step)
  def until(max:Wire) = Range(this, max)
  def isConst = value.isDefined
}
object Wire {
  def apply(name:String, v:Long, prec:Int):Wire = Wire(name, v, prec)
}
class Range (s:Wire, e:Wire) {
  val start:Wire = s
  val end:Wire = e
  def by(step:Const) = (start, end, step)
}
object Const {
  def apply(v:Long, prec:Int):Wire = Wire(None, Some(v), Some(prec))
  def apply(v:Long):Wire = Wire(None, Some(v), None)
  def apply(name:String, v:Long):Wire = Some(Some(name), Some(v), None)
}


/** Argument passed from host CPU code to accelerator using a memory-mapped register.
 *  Represents scalar input to accelerator, and are read-only from accelerator.
 *  @param id: String identifier used by user to identify argument
 *  @param tp: Optional type of argument, defaults to 32-bit signed integer
 */
case class ArgIn(nameStr:Option[String])(implicit design: Design) extends Node(nameStr, "argin"){
  val arg = design.nextId

}

object ArgIn {
  /** Companion object's apply method which takes in a type parameter for the argument */
  //def apply(tp: Type)(id: String)(implicit design: Design) = new { override val t = tp } with ArgIn(id)
}

/** Scalar values passed from accelerator to host CPU code via memory-mapped registers.
 *  Represent scalar outputs from the accelerator, and are write-only from accelerator.
 *  @param value: Combinational node whose value is to be output to CPU
 */
case class ArgOut(nameStr:Option[String], value: Wire)(implicit design: Design) extends Node(nameStr, "argout") {
  val arg = design.nextId

}



/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */
case class CounterChain(nameStr:Option[String], ctrs: (Wire, Wire, Wire)*)(implicit design: Design) extends Node(nameStr, "cc") {
  val ctrs: List[Wire] = Nil //TODO
  val max:Wire = null //TODO
  def apply(x: Int) = ctrs(x)
}
object CounterChain {
  //def apply(maxNStride: (Node, Int)*)(implicit design: Design) = {
  //  new CounterChain()(maxNStride:_*)
  //}
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
case class SRAM(nameStr: Option[String], size: List[Int])(implicit design: Design) extends Node(nameStr, "sram"){
  var readAddr: Wire = _
  var writeAddr: Wire = _
}

object SRAM {
  def apply(size: List[Int])(implicit design: Design): SRAM
    = SRAM(None, size)
  def apply(name:String, size:List[Int])(implicit design: Design): SRAM
    = SRAM(Some(name), size)
  def apply(name:String, size:List[Int], ra:Wire, wa:Wire)(implicit design: Design): SRAM
    = new SRAM(Some(name), size) { readAddr = ra; writeAddr = wa }
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
case class Pipeline(nameStr: Option[String], s:List[Stage])(implicit design: Design) extends Node(nameStr, "pipeline"){
  val stages:List[Stage] = s
}

object Pipeline {
  def apply(stages:List[Stage])(implicit design: Design): Pipeline
    = Pipeline(None, stages)
  def apply(name:String, stages:List[Stage])(implicit design: Design): Pipeline
    = Pipeline(Some(name), stages)
  //def apply(name:String) (stages: => Any) (implicit design: Design)
  //  = val s = design.getStages(stages); Pipeline(Some(name), List(s))
}

case class ComputeUnit(nameStr: Option[String], n:List[Node])(implicit design: Design) extends Node(nameStr, "cu"){
  val nodes = n
}
object ComputeUnit {
  //val apply (block: => Any):ComputeUnit = ComputeUnit(None, design.addBlock(block))
  //val apply (name:String) (block: => Any) = ComputeUnit(Some(name), design.addBlock(block))
}

case class Stage(nameStr: Option[String]) (implicit design: Design) extends Node(nameStr, "stage") {
}
object Stage {
}
