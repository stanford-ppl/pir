package prism
package exceptions

import prism.collection._

trait PIRException extends Exception {
  def msg:String
  override def toString = s"[pir] $msg"
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
