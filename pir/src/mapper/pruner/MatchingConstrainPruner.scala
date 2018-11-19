package pir
package mapper

import prism.collection.immutable._

trait MatchingConstrainPruner extends ConstrainPruner {

  def check[K,V,S<:FG[K,V,S]](fg:S, ks:Set[K], vs:Set[V]) = {
    val keySize = ks.size
    val valueSize = vs.size
    val fit = keySize <= valueSize
    dbgblk(s"MatchingConstrain: keySize=$keySize <= valueSize=$valueSize = $fit") {
      dbg(s"keys=${quote(ks)}")
      dbg(s"values=${quote(vs)}")
    }
    if (fit) Right(fg) else Left(MatchConstrainFailure[K,V,S](fg, ks, vs))
  }

  def prune[K,V,S<:FG[K,V,S]](x:S):EOption[S] = {
    val sets = x.freeKeys.groupBy { key => x.freeValuesOf(key) }.map { case (values, keys) =>
      (keys.head, values)
    }
    flatFold(sets, x) { case (x, (key, values)) =>
      val keys = values.flatMap { value => x.freeKeysOf(value) }.filter { key => 
        x.freeValuesOf(key).subsetOf(values)
      }
      check(x, keys, values)
    }
  }
  
}
case class MatchConstrainFailure[K,V,S<:FG[K,V,S]](fg:S, keys:Set[K], values:Set[V]) extends MappingFailure {
  override def toString = s"MatchConstrainFailure(${fg.getClass.getSimpleName}, ${keys.size}, ${values.size})"
}
