package pir
package mapper

import prism.collection.immutable._

trait ArcConsistencyPruner extends ConstrainPruner {
  def prune[K,V,S<:FG[K,V,S]](fg:S) = {
    flatFold(fg.freeKeys,fg) { case (fg, k) => ac3[K,V,S](fg, k) }
  }
  def ac3[K,V,S<:FG[K,V,S]](fg:S, k:K):EOption[S] = {
    flatFold(fg(k),fg) { case (fg, v) =>
      val neighbors = fg.freeKeysOf(v).filterNot { _ == k }
      val nfg = fg.set(k,v)
      nfg match {
        case Left(_) => fg - (k,v)
        case Right(nfg) =>
          flatFold(neighbors, fg) { case (fg, neighbor) => 
            if (ac3[K,V,S](nfg, neighbor).isLeft) fg - (k,v) else Right(fg)
          }
      }
    }
  }
}
