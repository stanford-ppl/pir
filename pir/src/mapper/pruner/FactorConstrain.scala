package pir
package mapper

import prism.collection.immutable._

trait FactorConstrain extends Constrain {
  def prune(field:Any):EOption[Any] = {
    field match {
      case field:FG[_,_,_] => prune(field)
      case field => Right(field)
    }
  }
  def prune[K,V,S<:FG[K,V,S]](fg:S):EOption[S]
}
