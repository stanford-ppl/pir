package pir

package object mapper {
  type MOption[T] = Either[MappingFailure, T]

  def flatFold[A,B](list:Iterable[A], zero:B)(op:(B,A) => MOption[B]):MOption[B] = {
    list.foldLeft[MOption[B]](Right(zero)) { case (zero, elem) => zero.flatMap(zero => op(zero, elem)) }
  }
}
