package pir.exceptions
import pir._
import pir.util.typealias._
import pir.spade.config._
import pir.mapper._
import pir.codegen.{CUCtrlDotPrinter, CUScalarDotPrinter, CUVectorDotPrinter}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap

trait PIRException extends Exception {
  def msg:String
  override def toString = s"[pir] $msg"
}
object PIRException {
  def apply(s:String) = new {override val msg = s} with PIRException
}

abstract class MappingException[M](val mapping:M)(implicit design:Design, val mapper:Mapper) extends PIRException with scala.util.control.NoStackTrace {
  def msg:String
  def typeStr = this.getClass().getSimpleName() 
  override def toString = s"[$mapper] $typeStr: $msg"
  if (mapper.exceedExceptLimit) {
    throw ExceedExceptionLimit(mapping) 
  } else {
    mapper.addException(this)
  }
}
object MappingException {
  def apply[M](mp:M, info:String)(implicit design:Design, mapper:Mapper):MappingException[M] = {
    new MappingException(mp) {
      def msg = info
      override def typeStr = s"MappingException"
    }
  }
}

case class NoSolFound[M](exceps:List[MappingException[_]], mp:M)(implicit design:Design, mapper:Mapper) extends MappingException(mp) {
  override def toString = s"[$mapper] $typeStr"
  override val msg = s"No solution found to map nodes to resources. Exceptions:{\n ${exceps.map(e => s"$e ${e.msg}").mkString("\n")}\n}"
  //override val msg = s"No solution found to map nodes to resources."
}

case class FailToMapNode[M](n:Any, exceps:List[MappingException[_]], mp:M)(implicit design:Design, mapper:Mapper) extends MappingException(mp) {
  //override def toString = s"[$mapper] $typeStr $n"
  val s = if (n.isInstanceOf[PRIM]) s"${n} in ${n.asInstanceOf[PRIM].ctrler}" else s"$n"
  //override val msg = s"No resource can map ${s}. Exceptions:\n ${exceps.mkString("\n")}"
  override val msg = s"No resource can map ${s}. Exceptions: {\n ${exceps.map(e => s"$e ${e.msg}").mkString("\n")}\n}"
}

/* Binding succeeded but don't mark resource as used */
case class ResourceNotUsed[M](n:Node, r:PNode, mp:M)(implicit design:Design, mapper:Mapper) extends MappingException(mp) {
  override val msg = s"Binding succeeded for $n on $r but don't mark $r as used"
}

case class OutOfResource[M](msg:String, pnodes:List[PNode], nodes:List[Node], mp:M)(implicit design:Design, mapper:Mapper) extends MappingException(mp) {
  override def toString = s"$msg. numRes:${pnodes.size}, numNode:${nodes.size}."
}

/* Exxception that wrap arounds MappingException that allowing MappingException to passing through
 * higher level of recursion unless explicit caught */
case class PassThroughException[M](except:Exception, mp:PIRMap)(implicit design:Design, mapper:Mapper) extends PIRException {
  override val msg = s"PassThrough: $mapper except:$except"
}

case class ExceedExceptionLimit[M](mapping:M)(implicit val mapper:Mapper) extends PIRException {
  println(s"Exiting due to exceed exception limits...")
  override def toString = s"[$mapper] $msg"
  override val msg = s"Exception Limit (=${mapper.exceptLimit}) Exceeded"
}

/* Compiler Error */
case class TODOException(s:String) extends PIRException {
  override val msg = s"TODO: ${s}"
}

