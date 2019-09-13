package prism
package exceptions
import scala.util.control.NoStackTrace

trait PIRException extends Exception {
  def msg:String
  override def toString = s"[bug] $msg"
}
object PIRException {
  def apply(s:String) = new {override val msg = s} with PIRException
}

/* Compiler Error */
case class TODOException(s:String) extends PIRException {
  override val msg = s"TODO: ${s}"
}

case class AssertError(info:String) extends PIRException {
  def msg = s"[assert] $info"
}

case class CompileError(s:String) extends Exception with NoStackTrace
