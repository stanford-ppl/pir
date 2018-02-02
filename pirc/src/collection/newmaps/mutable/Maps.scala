package prism.collection.mutable

import pirc._
import pirc.exceptions._
import scala.reflect._

import scala.collection.mutable.Map
trait MapLike[K,V,VV] extends prism.collection.MapLike[K,V,VV] {
  def update(k:K, v:V):Unit
  def remove(k:K, v:V):Unit
  def remove(k:K):Unit
  def += (pair:(K,V)) = { val (k,v) = pair; update(k,v)}
  def clear:Unit
  def getOrElseUpdate(k:K)(vv: => VV):VV
}
trait UniMap[K,V,VV] extends MapLike[K,V,VV] with prism.collection.UniMap[K,V,VV] {
  override type M = Map[K,VV]
  val map:M = Map.empty 
  def update(k:K, v:V):Unit = check(k,v)
  def clear = { map.clear }
  def remove(k:K, v:V):Unit = remove(k)
  def remove(k:K):Unit = map -= k
  def getOrElse(k:K,vv:VV) = map.getOrElse(k,vv)
}

class OneToOneMap[K:ClassTag,V:ClassTag] extends UniMap[K,V,V] with prism.collection.OneToOneMap[K,V] {
  override def update(k:K, v:V):Unit = {
    super.update(k,v)
    map += (k -> v)
  }
  def getOrElseUpdate(k:K)(vv: => V):V = {
    if (!map.contains(k)) update(k,vv) 
    map(k)
  }
}

class OneToManyMap[K:ClassTag,V:ClassTag] extends UniMap[K,V,Set[V]] with prism.collection.OneToManyMap[K,V,Set[V]] {
  override def apply(k:K) = map.getOrElse(k, Set())
  override def update(k:K, v:V):Unit = {
    super.update(k,v)
    map += k -> (map.getOrElse(k, Set[V]()) + v)
  }
  def getOrElseUpdate(k:K)(vv: => Set[V]):Set[V] = {
    if (!map.contains(k)) vv.foreach { v => update(k,v) }
    map(k)
  }
  def update(k:K, vv:VV):Unit = vv.foreach(v => update(k,v))
}

trait BiMap[K,V,KK,VV] extends MapLike[K,V,VV] with prism.collection.BiMap[K,V,KK,VV] {
  def fmap:UniMap[K,V,VV]
  def bmap:UniMap[V,K,KK]

  def update(k:K, v:V):Unit = {
    fmap.check(k,v)
    bmap.check(v,k)
    fmap.update(k,v)
    bmap.update(v,k)
  }
  override def remove(k:K, v:V):Unit = {
    fmap.remove(k,v)
    bmap.remove(v,k)
  }
  def remove(k:K):Unit = {
    fmap.get(k).foreach { vv => toVs(vv).foreach(v => bmap.remove(v)) }
    fmap.remove(k)
  }
  def getOrElseUpdate(k:K)(vv: => VV):VV = {
    fmap.getOrElseUpdate(k){
      toVV(toVs(vv).map { v => bmap.update(v, k); v })
    }
  }

  def clear = { fmap.clear; bmap.clear }

  def toVs(vv:VV):Set[V]
  def toVV(vs:Set[V]):VV
}

trait ForwardOneToOneMap[K,V,KK] extends BiMap[K,V,KK,V] {
  def fmap:OneToOneMap[K,V]
  def toVs(vv:V):Set[V] = Set(vv)
  def toVV(vs:Set[V]):V = vs.head
}

trait ForwardOneToManyMap[K,V,KK] extends BiMap[K,V,KK,Set[V]]{
  def fmap:OneToManyMap[K,V]
  def toVs(vv:Set[V]):Set[V] = vv
  def toVV(vs:Set[V]):Set[V] = vs

  override def apply(k:K) = fmap.getOrElse(k, Set())
  def update(k:K, v:V):Unit
  def update(k:K, vv:Set[V]):Unit = vv.foreach(v => update(k,v))
  def ++= (kvv:(K,Set[V])):Unit = update(kvv._1, kvv._2)
}

trait BackwardOneToOneMap[K,V,VV] extends BiMap[K,V,K,VV] {
  def bmap:OneToOneMap[V,K]
}

trait BackwardOneToManyMap[K,V,VV] extends BiMap[K,V,Set[K],VV]{
  def bmap:OneToManyMap[V,K]
}

class BiOneToOneMap[K:ClassTag,V:ClassTag] extends ForwardOneToOneMap[K,V,K] with BackwardOneToOneMap[K,V,V] {
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
}

class BiOneToManyMap[K:ClassTag,V:ClassTag] extends ForwardOneToManyMap[K,V,K] with BackwardOneToOneMap[K,V,Set[V]] {
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
}

class BiManyToOneMap[K:ClassTag,V:ClassTag] extends ForwardOneToOneMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,V] {
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
}

class BiManyToManyMap[K:ClassTag,V:ClassTag] extends ForwardOneToManyMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,Set[V]] {
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
}
