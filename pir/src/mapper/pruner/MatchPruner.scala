package pir
package mapper

import prism.collection.immutable._

class MatchPruner(implicit compiler:PIR) extends MatchingConstrainPruner {
  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic => prune[CUMap.K,CUMap.V,CUMap](x).asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }
}

