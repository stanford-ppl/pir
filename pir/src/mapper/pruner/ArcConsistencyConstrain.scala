package pir
package mapper

import prism.collection.immutable._

trait ArcConsistencyConstrain extends Constrain {
  def prune(fg:FG):EOption[FG] = {
    flatFold(fg.freeKeys,fg) { case (fg, k) => ac3[K,V,FG](fg, k) }
  }
  def ac3[K,V,FG<:FactorGraphLike[K,V,FG]](fg:FG, k:K):EOption[FG] = {
    flatFold(fg(k),fg) { case (fg, v) =>
      val neighbors = fg.freeKeys(v).filterNot { _ == k }
      val nfg = fg.set(k,v)
      nfg match {
        case Left(_) => fg - (k,v)
        case Right(nfg) =>
          flatFold(neighbors, fg) { case (fg, neighbor) => 
            if (ac3[K,V,FG](nfg, neighbor).isLeft) fg - (k,v) else Right(fg)
          }
      }
    }
  }
}
