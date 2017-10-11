package pir.node

import pir._

import scala.collection.mutable.ListBuffer
import scala.math.max

/** Base class for all PIR nodes. 
  * @param name: optional user name for a node 
  * @param typeStr: Consice name for a type of node for printing purpose 
  */
abstract class Node (implicit val design: PIR) { 
  val pirmeta:PIRMetadata = design
  import pirmeta._

  val typeStr:String = this.getClass.getSimpleName()
	design.addNode(this)
  val id : Int = design.nextId // Unique id for each node
  def toUpdate:Boolean = false // Whether fields of the node is not yet defined, 
                               // which would be updated later. Debug purpose only 

  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  override def toString = s"${typeStr}${id}${nameOf.get(this).fold("")(n => s"_${n}")}" 
  def bound(b:AnyVal):this.type = { boundOf(this) = b; this }
  def name(n:String):this.type = { nameOf(this) = n; this }
  def name:Option[String] = nameOf.get(this)
}

trait Module extends Node {
  implicit val self:Module = this
  val _ins = ListBuffer[Input]()
  def ins:List[Input] = _ins.toList
  val _outs = ListBuffer[Output]()
  def outs:List[Output] = _outs.toList
  def addIO(io:IO) = io match {
    case input:Input => _ins += input
    case output:Output => _outs += output
  }
  def ios:List[IO] = ins ++ outs

  def isConst:Boolean = this.isInstanceOf[Const[_]]

  def asSRAM:SRAM = this.asInstanceOf[SRAM]
}
