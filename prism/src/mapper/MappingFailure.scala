package prism.mapper

import prism.exceptions._

// Continue search when exception is raised
trait MappingFailure extends PIRException

case class SearchFailure(msg:String) extends MappingFailure
case object NotReachedEnd extends MappingFailure { def msg = toString }

case class EmptyBinding[P](pnode:P) extends MappingFailure {
  val msg = toString
}

