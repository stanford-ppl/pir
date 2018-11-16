package prism
package collection
package immutable

/*
 * A collection that is a case class. Each case class is a field with unique type
 * */
trait CaseTuple[S<:CaseTuple[S]] { self:Product =>
  lazy val fields = productIterator.toList
  def get[F:ClassTag]:F = {
    assertOne(fields.collect { case f:F => f }, s"$this.field[${classTag[F]}]")
  }
  def swap(idx:Int, newField:Any):S = {
    val args = fields.zipWithIndex.map { 
      case (field, i) if i == idx => newField
      case (field, i) => field
    }
    this.newInstance(args)
  }
  def set[F:ClassTag](newField:F):S = {
    val args = fields.map {
      case field:F => newField 
      case field => field
    }
    this.newInstance(args)
  }
  def map[F:ClassTag](lambda: F => F):S = {
    val args = fields.map {
      case field:F => lambda(field)
      case field => field
    }
    this.newInstance(args)
  }
  def flatMap[F:ClassTag](lambda: F => EOption[F]):EOption[S] = {
    lambda(get[F]).map { set[F] }
  }
  def flatMapAll(lambda: Any => EOption[Any]):EOption[S] = {
    val args = fields.foldLeft[EOption[List[Any]]](Right(List[Any]())) { 
      case (Right(args), field) => 
        lambda(field) match {
          case Right(newField) => Right(args :+ newField)
          case Left(e) => Left(e)
        }
      case (Left(e), field) => Left(e)
    }
    args.map { args => this.newInstance[S](args) }
  }
}

