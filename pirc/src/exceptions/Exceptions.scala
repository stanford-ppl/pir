package pirc.exceptions

import prism.collection._

trait PIRException extends Exception {
  def msg:String
  override def toString = s"[pir] $msg"
}
object PIRException {
  def apply(s:String) = new {override val msg = s} with PIRException
}

// Continue search when exception is raised
trait SearchFailure extends PIRException

/* Compiler Error */
case class TODOException(s:String) extends PIRException {
  override val msg = s"TODO: ${s}"
}

case class AssertError(info:String) extends PIRException {
  def msg = s"[assert] $info"
}

case class RebindingException[K,V](map:OneToOneMap[K,V], k:K, v:V) extends PIRException {
  def msg = s"${map.name} already contains key $k -> ${map(k)} but try to rebind to $v"
}
