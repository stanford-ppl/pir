package prism.mapper

import prism.exceptions._

// Continue search when exception is raised
trait MappingFailure

case class SearchFailure(msg:String) extends MappingFailure
case object NotReachedEnd extends MappingFailure

case class BindingTrace[P,M](pnode:P, mapping:M) extends MappingFailure {
  val traces = ListBuffer[MappingFailure]()
  def append(e:EOption[M]) = e.left.flatMap { f => traces += f; Left(this) }
  def last:Option[MappingFailure] = traces.lastOption.map {
    case f:BindingTrace[_,_] => f.last.getOrElse(f)
    case f => f
  }
}

