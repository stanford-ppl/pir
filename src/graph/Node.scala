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
  val name: String = nameStr.getOrElse("")
  val id : Int = design.nextId

  val DefaultPrecision = 32

  override def toString = s"${typeStr}${id}${if(nameStr.isDefined) "_" else ""}${name}"
  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && name == n.name
    case _ => super.equals(that)
  }
}

class Range (s:Wire, e:Wire) {
  val start:Wire = s
  val end:Wire = e
  def by(step:Wire) = (start, end, step)
}

/**
 * An unassigned wire used to represent scalar outputs prior to assignment
 * @param tp: The type of value represented by this wire
 */
abstract class Wire(nameStr: Option[String], w:Option[Int], typeStr:String)(implicit design: Design) extends Node(nameStr, typeStr) {
  val width:Int = w.getOrElse(DefaultPrecision)

  def by(step:Wire) = (Const(0l, step.width), this, step)
  def until(max:Wire) = new Range(this, max)
  def isConst = this.isInstanceOf[Const] 
}

case class Const(nameStr:Option[String], v:Option[Long], w:Option[Int])(implicit design: Design) extends Wire(nameStr, w, "Const"){
  val value:Option[Long] = v 
  override def toString = s"Const(${value.get})"
}
object Const {
  def apply(v:Long, w:Int) (implicit design:Design):Const = Const(None, Some(v), Some(w))
  def apply(v:Long) (implicit design:Design):Const = Const(None, Some(v), None)
  def apply(name:String, v:Long) (implicit design:Design):Const = Const(Some(name), Some(v), None)
}

case class ArgIn(nameStr:Option[String], w:Option[Int])(implicit design: Design) extends Wire(nameStr, w, "ArgIn"){
}
object ArgIn {
  def apply() (implicit design:Design):ArgIn = ArgIn(None, None)
  def apply(w:Int) (implicit design:Design):ArgIn = ArgIn(None, Some(w))
  def apply(name:String, w:Int) (implicit design:Design):ArgIn = ArgIn(Some(name), Some(w))
}


/** Scalar values passed from accelerator to host CPU code via memory-mapped registers.
 *  Represent scalar outputs from the accelerator, and are write-only from accelerator.
 *  @param value: Combinational node whose value is to be output to CPU
 */
case class ArgOut(nameStr:Option[String], value:Wire, w:Option[Int])(implicit design: Design) extends Wire(nameStr, w, "ArgOut"){
}
object ArgOut {
  def apply(value:Wire) (implicit design:Design):ArgOut = ArgOut(None, value, None)
  def apply(value:Wire, w:Int) (implicit design:Design):ArgOut = ArgOut(None, value, Some(w))
  def apply(name:String, value:Wire, w:Int) (implicit design:Design):ArgOut = ArgOut(Some(name), value, Some(w))
}

/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */
case class CounterChain(nameStr:Option[String], counters: (Wire, Wire, Wire)*)(implicit design: Design) extends Node(nameStr, "CC") {
  val ctrs = counters
  def apply(x: Int) = ctrs(x)
  override def toString = {
    super.toString + { 
    if (counters.size > 0)
      counters.map{ case (min, max, step) => s"(${min}, ${max}, ${step})" }.reduce(_+_)
    else
      "()"
    }
  }
}
object CounterChain {
  def apply(counters: (Wire, Wire, Wire)*)(implicit design: Design):CounterChain = CounterChain(None, counters:_*)
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


abstract class Controller(nameStr: Option[String], cchains: List[Node], typeStr:String)(implicit design: Design) extends Node(nameStr, typeStr){
  def content = List(s"""  cchains: [
  ${if (cchains.size > 0) cchains.map{cc => cc.toString}.reduce{_ + ",\n" + _}} 
  ]""")

  override def toString = {
    s"""${super.toString} { 
  ${content.reduce(_+"\n"+_)}
}"""
  }
}

case class ComputeUnit(nameStr: Option[String], cchains: List[Node])(implicit design: Design) extends Controller(nameStr, cchains, "CU"){
  override def content = super.content :+
  s"""  stages:{
  }"""
}
object ComputeUnit {
  def apply (name:Option[String], block: => Any) (implicit design: Design):ComputeUnit = {
    val cchains = design.addBlock(block, (n:Node) => n.isInstanceOf[CounterChain]) 
    ComputeUnit(name, cchains)
  } 
  def apply (block: => Any) (implicit design: Design):ComputeUnit = ComputeUnit.apply(None, block)
  def apply (name:String) (block: => Any) (implicit design: Design):ComputeUnit = ComputeUnit.apply(Some(name), block)
}

case class MemoryController(nameStr: Option[String], cchains: List[Node])(implicit design: Design) extends Controller(nameStr, cchains, "MemCtrl"){
}

object MemoryController {
  def apply (name:Option[String], block: => Any) (implicit design: Design):MemoryController = {
    val cchains = design.addBlock(block, (n:Node) => n.isInstanceOf[CounterChain]) 
    MemoryController(name, cchains)
  } 
  def apply (block: => Any) (implicit design: Design):MemoryController = MemoryController.apply(None, block)
  def apply (name:String) (block: => Any) (implicit design: Design):MemoryController = MemoryController.apply(Some(name), block)
}

case class Stage(nameStr: Option[String]) (implicit design: Design) extends Node(nameStr, "stage") {
}
object Stage {
}
