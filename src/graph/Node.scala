package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import pir._
import pir.graph._

/** Base class for all PIR nodes. 
  * @param name: optional user name for a node 
  * @param typeStr: Consice name for a type of node for printing purpose 
  */
abstract class Node (implicit val design: Design) extends Metadata { 
  val name:Option[String]
  val typeStr:String
	design.addNode(this)
  val id : Int = design.nextId // Unique id for each node
  def toUpdate:Boolean = false // Whether fields of the node is not yet defined, 
                               // which would be updated later. Debug purpose only 

  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }
  override def toString = s"${typeStr}${id}${name.fold("")(n => s"_${n}")}" 
}
object Node extends Metadata {
  def quote(n:Node)(implicit design:Design):String = {
    n match {
      case s:Stage => s"${s}[${indexOf(s)}]"
      case _ => n.toString
    }
  }
}
