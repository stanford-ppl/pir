package pir
package mapper

import prism.collection.immutable._

trait MatchingConstrain extends Constrain {

  //def createCompleteBipartiteSubgraphs(fg:FG) = {
    //val subgraphs = mutable.Map[Set[V], mutable.Set[K]]()
    //fg.freeKeys.foreach { key =>
      //val values = fg.freeValues(key)
      //if (!subgraphs.contains(values)) {
        //subgraphs += (values -> mutable.Set[K]())
      //}
      //subgraphs.foreach { case (vs, ks) =>
        //if (vs.subsetOf(values)) ks += key
      //}
    //}
    //subgraphs
  //}

  def check(fg:FG, ks:Set[K], vs:Set[V]) = {
    val keySize = ks.size
    val valueSize = vs.size
    val fit = keySize <= valueSize
    dbgblk(s"MatchingConstrain: keySize=$keySize <= valueSize=$valueSize = $fit") {
      dbg(s"keys=${quote(ks)}")
      dbg(s"values=${quote(vs)}")
    }
    if (fit) Right(fg) else Left(MatchConstrainFailure(fg, ks.toSet))
  }
  
  def prune(fg:FG):EOption[FG] = {
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
case class MatchConstrainFailure[FG<:FactorGraphLike[_,_,FG]](@transient fg:FG, keys:Set[_]) extends MappingFailure {
  override def toString = s"MatchConstrainFailure(${fg.getClass.getSimpleName}, $keys)"
}
