package pir

package object mapper {

  type EOption[M] = prism.traversal.EOption[M]
  def flatFold[A,B](list:Iterable[A], zero:B)(op:(B,A) => EOption[B]):EOption[B] = {
    list.foldLeft[EOption[B]](Right(zero)) { case (zero, elem) => zero.flatMap(zero => op(zero, elem)) }
  }
}
