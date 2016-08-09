package pir.graph.mapper
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, Node, Primitive}
import pir.Design
import pir.Config
import pir.plasticine.config._
import pir.plasticine.graph.{Node => PNode, Controller => PCL, ComputeUnit => PCU}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap

abstract class MappingException(implicit design:Design) extends PIRException{
  design.mapExceps += this
  val msg:String
  val mapper:Mapper
  val typeStr = this.getClass().getSimpleName() 
  override def toString = s"[$mapper] $typeStr: $msg"
  //println(s"$typeStr")
}

case class NoSolFound(mapper:Mapper, exceps:List[MappingException])(implicit design:Design) extends MappingException {
  override val msg = s"No solution found to map nodes to resources. Exceptions:{\n ${exceps.mkString("\n")}\n}"
}

//TODO: n should be node
case class FailToMapNode(mapper:Mapper, n:Node, exceps:List[MappingException])(implicit design:Design) extends MappingException {
  val s = if (n.isInstanceOf[Primitive]) s"${n} in ${n.asInstanceOf[Primitive].ctrler}" else s"$n"
  override val msg = s"No resource can map ${s}. Exceptions:\n ${exceps.mkString("\n")}"
}

trait OutOfResource extends MappingException {
  val nres:Int
  val nnode:Int
  override def toString = s"${super.toString}. numRes:${nres}, numNode:${nnode}."
}

/* Constrain exceptions */


trait PIRException extends Exception{
  val msg:String
  override def toString = s"[pir] $msg"
}
object PIRException {
  def apply(s:String) = new {override val msg = s} with PIRException
}

/* Compiler Error */
case class TODOException(s:String) extends PIRException {
  override val msg = s"TODO: ${s}"
}

