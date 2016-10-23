package pir.graph.mapper
import pir._
import pir.typealias._
import pir.plasticine.config._
import pir.graph.mapper._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap

abstract class MappingException(implicit design:Design) extends PIRException {
  if (design.mapExceps.size>500) {
    println(s"Exiting due to exceed exception limits...")
    throw PIRException(s"Exception Limit Exceeded")
  }
  design.mapExceps += this
  val msg:String
  val mapper:Mapper
  val typeStr = this.getClass().getSimpleName() 
  override def toString = s"[$mapper] $typeStr: $msg"
  //println(s"$typeStr")
}

case class NoSolFound(mapper:Mapper, exceps:List[MappingException])(implicit design:Design) extends MappingException {
  override def toString = s"[$mapper] $typeStr"
  override val msg = s"No solution found to map nodes to resources. Exceptions:{\n ${exceps.map(e => s"$e ${e.msg}").mkString("\n")}\n}"
  //override val msg = s"No solution found to map nodes to resources."
}

case class FailToMapNode[M](mapper:Mapper, n:Any, exceps:List[MappingException], mapping:M)(implicit design:Design) extends MappingException {
  override def toString = s"[$mapper] $typeStr $n"
  val s = if (n.isInstanceOf[PRIM]) s"${n} in ${n.asInstanceOf[PRIM].ctrler}" else s"$n"
  //override val msg = s"No resource can map ${s}. Exceptions:\n ${exceps.mkString("\n")}"
  override val msg = s"No resource can map ${s}. Exceptions: {\n ${exceps.map(e => s"$e ${e.msg}").mkString("\n")}\n}"
}

/* Binding succeeded but don't mark resource as used */
case class ResourceNotUsed[M](mapper:Mapper, n:Node, r:PNode, m:M)(implicit design:Design) extends MappingException {
  override val msg = s"Binding succeeded for $n on $r but don't mark $r as used"
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

/* Exxception that wrap arounds MappingException that allowing MappingException to passing through
 * higher level of recursion unless explicit caught */
case class PassThroughException(mapper:Mapper, except:MappingException, mapping:PIRMap)(implicit design:Design) extends PIRException {
  override val msg = s"PassThrough: $mapper except:$except"
}

/* Compiler Error */
case class TODOException(s:String) extends PIRException {
  override val msg = s"TODO: ${s}"
}

