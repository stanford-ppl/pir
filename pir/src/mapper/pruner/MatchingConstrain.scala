package pir
package mapper

import prism.collection.immutable._

trait MatchingConstrain extends FactorConstrain {

  def check[K,V,S<:FG[K,V,S]](fg:S, ks:Set[K], vs:Set[V]) = {
    val keySize = ks.size
    val valueSize = vs.size
    val fit = keySize <= valueSize
    dbgblk(s"MatchingConstrain: keySize=$keySize <= valueSize=$valueSize = $fit") {
      dbg(s"keys=${quote(ks)}")
      dbg(s"values=${quote(vs)}")
    }
    if (fit) Right(fg) else Left(MatchConstrainFailure[K,V,S](fg, ks.toSet))
  }
  
  def prune[K,V,S<:FG[K,V,S]](fg:S) = {
    log(fg)
    val sets = fg.freeKeys.groupBy { key => fg.freeValues(key) }.map { case (values, keys) =>
      (keys.head, values)
    }
    flatFold(sets, fg) { case (fg, (key, values)) =>
      val keys = values.flatMap { value => fg.freeKeys(value) }.filter { key => 
        fg.freeValues(key).subsetOf(values)
      }
      check(fg, keys, values)
    }
  }

}
case class MatchConstrainFailure[K,V,S<:FG[K,V,S]](fg:S, keys:Set[_]) extends MappingFailure {
  override def toString = s"MatchConstrainFailure(${fg.getClass.getSimpleName}, $keys)"
}
