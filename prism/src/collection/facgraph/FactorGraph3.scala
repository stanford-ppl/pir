package prism.collection3.immutable

import prism.mapper._
import prism.collection1.immutable._

trait FactorGraphLike[K,V,S<:FactorGraphLike[K,V,S]] extends BiManyToManyMapLike[K,V,S] { self:S =>
  def -* (pair:(K,V)) = {
    val (k,v) = pair
    (this - (k,v)).check(k)
  }
  def --* (pair:(Any,Any)) = { 
    val nfg = -- (pair)
    pair._1 match {
      case AsK(k) => nfg.check(k)
      case AsKK(kk) => flatFold(kk, nfg) { case (prev, k) => nfg.check(k) }
    }
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
  def filterNot(k:K)(lambda:V => Boolean):EOption[S] = {
    val vv = fmap(k).filter { v => lambda(v) }
    --* (k, vv)
  }
  def set(k:K, v:V):EOption[S]
  def check(k:K):EOption[S] = if (fmap(k).isEmpty) Left(InvalidFactorGraph(this, k)) else Right(this)
}

trait OneToOneFactorGraphLike[K,V,S<:OneToOneFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  def set(k:K, v:V):EOption[S] = {
    assert(fmap.get(k).fold(false) { vv => vv.contains(v) })
    val vv = fmap(k) - v
    val kk = bmap(v) - k
    var nfg = this
    nfg --= ((k, vv))
    nfg --= ((kk, v))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }

  def freeKeys = keys.filter { k => !isMapped(k) }
  def mappedKeys = keys.filter { k => isMapped(k) }
  def mapped = fmap.map.flatMap { case (k, vv) => if (isMapped(k)) Some((k,vv.head)) else None }

  override def isMapped(x:Any) = x match {
    case AsK(x) => fmap(x).size==1
    case AsV(x) => bmap(x).size==1
  }

  def mappedValue(k:K) = {
    if (fmap(k).size==1) Some(fmap(k).head) else None
  }
  def mappedKey(v:V) = {
    if (bmap(v).size==1) Some(bmap(v).head) else None
  }
}

case class OneToOneFactorGraph[K:ClassTag,V:ClassTag](
  fmap:OneToManyMap[K,V], 
  bmap:OneToManyMap[V,K]
) extends OneToOneFactorGraphLike[K,V,OneToOneFactorGraph[K,V]]
object OneToOneFactorGraph {
  def empty[K:ClassTag, V:ClassTag] = OneToOneFactorGraph[K,V](OneToManyMap.empty, OneToManyMap.empty)
}

trait OneToManyFactorGraphLike[K,V,S<:OneToManyFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  def set(k:K, v:V):EOption[S] = {
    assert(fmap.get(k).fold(false) { vv => vv.contains(v) })
    val kk = bmap(v) - k
    var nfg = this
    nfg --= ((kk, v))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }
}

case class InvalidFactorGraph[K,FG<:FactorGraphLike[K,_,FG]](@transient fg:FG, k:K) extends MappingFailure {
  val msg = s"InvalidFactorGraph ${fg.getClass.getSimpleName} at key=$k"
}

