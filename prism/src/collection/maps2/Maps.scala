package prism
package collection2

import scala.collection.Map
import scala.collection.Set

trait MapType[TK,TV,TKK,TVV] {
  type K = TK
  type V = TV
  type KK = TKK
  type VV = TVV
}

abstract class MapLike[K:ClassTag,V:ClassTag,KK:ClassTag,VV:ClassTag] extends Serializable with MapType[K,V,KK,VV] {
  val name:String = this.getClass().getSimpleName().replace("$","")
  def check(k:K, v:V):Unit = {}
}

trait ForwardMap[K,VV] {
  def fmap:Map[K,VV]
  def apply(n:K):VV = fmap(n)
  def get(n:K):Option[VV] = fmap.get(n) 
  def contains(k:K):Boolean = fmap.contains(k) 
  def keys:Iterable[K] = fmap.keys 
  def values:Iterable[VV] = fmap.values 
}

package object mutable {
  import scala.collection.mutable.Set
  import scala.collection.mutable.Map
  trait MapLike[K,V,KK,VV] extends collection2.MapLike[K,V,KK,VV] {
    def update(k:K, v:V) = check(k,v)
    def remove(k:K, v:V):Unit = {}
    def remove(k:K):Unit
  }

  trait ForwardMap[K,VV] extends collection2.ForwardMap[K,VV] {
    def fmap:Map[K,VV]
    def remove(k:K):Unit = fmap -= k
  }

  trait ForwardOneToOneMap[K,V,KK] extends MapLike[K,V,KK,V] with ForwardMap[K,V] {
    val fmap = Map[K,V]()
    override def check(k:K,v:V) = {
      super.check(k,v)
      fmap.get(k).foreach { oldv => 
        assert(oldv == v)
      }
    }
    override def update(k:K, v:V) = {
      super.update(k,v)
      fmap += k -> v
    }
    override def remove(k:K, v:V) = {
      super.remove(k,v)
      fmap -= k
    }
  }

  trait ForwardOneToManyMap[K,V,KK] extends MapLike[K,V,KK,Set[V]] with ForwardMap[K,Set[V]] {
    val fmap = Map[K,Set[V]]()
    override def update(k:K, v:V) = {
      super.update(k,v)
      fmap.getOrElseUpdate(k, Set[V]()) += v
    }
    override def remove(k:K, v:V) = {
      super.remove(k,v)
      fmap -= k
    }
  }

  trait BackwardOneToOneMap[K,V,VV] extends MapLike[K,V,K,VV] {
    val bmap = Map[V,K]()
    override def check(k:K,v:V) = {
      super.check(k,v)
      bmap.get(v).foreach { oldk => 
        assert(oldk == k)
      }
    }
    override def update(k:K, v:V) = {
      super.update(k,v)
      bmap += v -> k
    }
    override def remove(k:K, v:V) = {
      super.remove(k,v)
      bmap -= v
    }
  }

  trait BackwardOneToManyMap[K,V,VV] extends MapLike[K,V,Set[K],VV] {
    val bmap = Map[V,Set[K]]()
    override def update(k:K, v:V) = {
      super.update(k,v)
      bmap.getOrElseUpdate(v, Set[K]()) += k
    }
    override def remove(k:K, v:V) = {
      super.remove(k,v)
      bmap -= v
    }
  }

  class OneToOneMap[K:ClassTag,V:ClassTag] extends ForwardOneToOneMap[K,V,K]
  class OneToManyMap[K:ClassTag,V:ClassTag] extends ForwardOneToManyMap[K,V,K]

  class BiOneToOneMap[K:ClassTag,V:ClassTag] extends ForwardOneToOneMap[K,V,K] with BackwardOneToOneMap[K,V,V]
  class BiOneToManyMap[K:ClassTag,V:ClassTag] extends ForwardOneToManyMap[K,V,K] with BackwardOneToOneMap[K,V,Set[V]]
  class BiManyToOneMap[K:ClassTag,V:ClassTag] extends ForwardOneToOneMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,V]
  class BiManyToManyMap[K:ClassTag,V:ClassTag] extends ForwardOneToManyMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,Set[V]]

}
