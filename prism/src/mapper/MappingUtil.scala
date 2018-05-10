package prism
package mapper

trait MappingUtil {
  type EOption[T] = Either[MappingFailure, T]
  def flatFold[A,B](list:Iterable[A], zero:B)(op:(B,A) => EOption[B]):EOption[B] = {
    list.foldLeft[EOption[B]](Right(zero)) { case (zero, elem) => zero.flatMap(zero => op(zero, elem)) }
  }
}
