package prism.mapper

import prism.exceptions._

// Continue search when exception is raised
trait MappingFailure

case class SearchFailure(msg:String) extends MappingFailure
case object NotReachedEnd extends MappingFailure

case class BindingTrace[P](pnode:P) extends MappingFailure {
  val traces = ListBuffer[MappingFailure]()
  def append[M](e:EOption[M]) = e match {
    case Right(m) => Right(m)
    case Left(f) => traces += f; Left(this)
  }
}

