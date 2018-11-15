package pir
package mapper

import prism.collection.immutable._

trait FactorConstrain extends Constrain {
  def prune[T](field:T):EOption[T] = {
    field match {
      case field:FG[_,_,_] => prune(field).asInstanceOf[EOption[T]]
      case field => Right(field)
    }
  }
  def prune[K,V,S<:FG[K,V,S]](fg:S):EOption[S]
}
