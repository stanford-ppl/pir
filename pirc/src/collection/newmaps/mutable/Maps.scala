package prism.collection.mutable

import pirc._
import pirc.exceptions._
import scala.reflect._

import scala.collection.mutable.Set
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
}

// Concrete
trait OneToOneMap[K,V] extends UniMap[K,V,V] with prism.collection.OneToOneMap[K,V] {
  override def update(k:K, v:V):Unit = {
    super.update(k,v)
    map += (k -> v)
  }
  def getOrElseUpdate(k:K)(vv: => V):V = {
    if (!map.contains(k)) update(k,vv) 
    map(k)
  }
}

// Concrete
trait OneToManyMap[K,V] extends UniMap[K,V,Set[V]] with prism.collection.OneToManyMap[K,V,Set[V]] {
  override def apply(k:K) = map.getOrElse(k, Set())
  override def update(k:K, v:V):Unit = {
    super.update(k,v)
    map.getOrElseUpdate(k, Set[V]()) += v
  }
  def getOrElseUpdate(k:K)(vv: => Set[V]):Set[V] = {
    if (!map.contains(k)) vv.foreach { v => update(k,v) }
    map(k)
  }
  def update(k:K, vv:VV):Unit = vv.foreach(v => update(k,v))
}

trait BiMap[K,V,KK,VV] extends MapLike[K,V,VV] with prism.collection.BiMap[K,V,KK,VV] {
  override def fmap:UniMap[K,V,VV]
  override def bmap:UniMap[V,K,KK]

  def update(k:K, v:V):Unit = {
    fmap.update(k,v)
    bmap.update(v,k)
  }
  override def remove(k:K, v:V):Unit = {
    fmap.remove(k,v)
    bmap.remove(v,k)
  }

  def clear = { fmap.clear; bmap.clear }
}

trait ForwardOneToOneMap[K,V,KK] extends BiMap[K,V,KK,V] {
  val fmap = new OneToOneMap[K,V]{}

  override def remove(k:K):Unit = {
    fmap.get(k).foreach { v => bmap.remove(v,k) }
    fmap.remove(k)
  }

  def getOrElseUpdate(k:K)(vv: => V):V = {
    fmap.getOrElseUpdate(k){
      val v = vv
      bmap.update(v,k)
      v
    }
  }
}

trait ForwardOneToManyMap[K,V,KK] extends BiMap[K,V,KK,Set[V]] {
  val fmap = new OneToManyMap[K,V]{}

  override def apply(k:K) = map.getOrElse(k, Set())
  def update(k:K, vv:VV):Unit = vv.foreach(v => update(k,v))

  override def remove(k:K):Unit = {
    fmap.get(k).foreach { vs => vs.foreach { v => bmap.remove(v,k) } }
    fmap.remove(k)
  }

  def getOrElseUpdate(k:K)(vv: => Set[V]):Set[V] = {
    fmap.getOrElseUpdate(k){
      vv.map { v => bmap.update(v, k); v }
    }
  }
}

trait BackwardOneToOneMap[K,V,VV] extends BiMap[K,V,K,VV] {
  val bmap = new OneToOneMap[V,K]{}
}

trait BackwardOneToManyMap[K,V,VV] extends BiMap[K,V,Set[K],VV] {
  val bmap = new OneToManyMap[V,K]{}
}

// Concrete
trait BiOneToOneMap[K,V] extends ForwardOneToOneMap[K,V,K] with BackwardOneToOneMap[K,V,V]

// Concrete
trait BiOneToManyMap[K,V] extends ForwardOneToManyMap[K,V,K] with BackwardOneToOneMap[K,V,Set[V]]

// Concrete
trait BiManyToOneMap[K,V] extends ForwardOneToOneMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,V]

// Concrete
trait BiManyToManyMap[K,V] extends ForwardOneToManyMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,Set[V]]
