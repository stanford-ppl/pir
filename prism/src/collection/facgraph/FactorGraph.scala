package prism.collection.immutable

import prism._
import prism.mapper._

trait FactorGraphLike[K,V,S] extends BiManyToManyMapLike[K,V,S] { self:S =>
  override def - (pair:(K,V)):S with FactorGraphLike[K,V,S] = super.- (pair).asInstanceOf[S with FactorGraphLike[K,V,S]]
  override def -- (pair:(K,Set[V])):S with FactorGraphLike[K,V,S] = super.-- (pair).asInstanceOf[S with FactorGraphLike[K,V,S]]
  override def --- (pair:(Set[K],V)):S with FactorGraphLike[K,V,S] = super.--- (pair).asInstanceOf[S with FactorGraphLike[K,V,S]]
  def -* (pair:(K,V)) = {
    val (k,v) = pair
    (this - (k,v)).check(k)
  }
  def --* (pair:(K,Set[V])) = {
    val (k,vv) = pair
    val nfg = --(pair)
    nfg.check(k)
  }
  def ---* (pair:(Set[K],V))= {
    val (kk,v) = pair
    val nfg = --- (pair)
    flatFold(kk, nfg) { case (prev, k) => nfg.check(k) }
  }
  def filter(lambda:(K,V) => Boolean):EOption[S] = {
    def notLambda(k:K, v:V) = !lambda(k,v)
    filterNot(notLambda _)
  }
  def filterNot(lambda:(K,V) => Boolean):EOption[S] = {
    val pairs = fmap.map.toSeq.map { case (k,vv) =>
      (k, vv.filter { v => lambda(k,v) })
    }
    flatFold(pairs, this) { case (prev, (k, vv)) => prev --* (k,vv) }
  }
  def set(k:K, v:V) = {
    assert(fmap.get(k).fold(false) { vv => vv.contains(v) })
    val vv = fmap(k) - v
    val kk = bmap(v) - k
    var nfg = this
    nfg --= ((k, vv))
    nfg ---= ((kk, v))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }
  def check(k:K):EOption[S with FactorGraphLike[K,V,S]] = if (fmap(k).isEmpty) Left(InvalidFactorGraph(this, k)) else Right(this)
}
case class FactorGraph[K:ClassTag,V:ClassTag](
  fmap:OneToManyMap[K,V], 
  bmap:OneToManyMap[V,K]
) extends FactorGraphLike[K,V,FactorGraph[K,V]]
object FactorGraph {
  def empty[K:ClassTag, V:ClassTag] = FactorGraph[K,V](OneToManyMap.empty, OneToManyMap.empty)
}
case class InvalidFactorGraph[K,FG<:FactorGraphLike[K,_,_]](@transient fg:FG, k:K) extends MappingFailure {
  var info = s"InvalidFactorGraph ${fg.getClass.getSimpleName} at key=$k\n"
  info += s"freeValues: \n"
  info += ""; fg.fmap.map[String] { case (k, vs) => s"$k -> $vs" }.mkString("\n") + "\n"
  val msg = info
}

