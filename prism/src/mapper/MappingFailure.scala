package prism.mapper

import prism.exceptions._

// Continue search when exception is raised
trait MappingFailure

case class SearchFailure(msg:String) extends MappingFailure
case object NotReachedEnd extends MappingFailure

case class EmptyBinding[P](pnode:P) extends MappingFailure

