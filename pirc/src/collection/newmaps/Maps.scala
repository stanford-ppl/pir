package prism.collection 

import pirc._
import pirc.exceptions._

import scala.collection.Map
import scala.collection.Set
import scala.reflect._

abstract class MapType[TK:ClassTag,TV:ClassTag,TVV:ClassTag] {
  type K = TK
  type V = TV
  type VV = TVV
  val kct = implicitly[ClassTag[K]]
  val vct = implicitly[ClassTag[V]]
  val vvct = implicitly[ClassTag[VV]]

  def asK(k:Any) = k match { case k:TK => Some(k); case _ => None }
  def asV(v:Any) = v match { case v:TV => Some(v); case _ => None }
  def asVV(vv:Any) = vv match { case vv:TVV => Some(vv); case _ => None }
}

trait MapLike[K,V,VV] extends MapType[K,V,VV] with Serializable {
  val name:String = this.getClass().getSimpleName().replace("$","")
  def apply(n:K):VV
  def get(n:K):Option[VV]
  def foreach(lambda:((K,VV)) => Unit):Unit
  def contains(k:K):Boolean
  def keys:Iterable[K]
  def values:Iterable[VV]

  def check(k:K, v:V):Unit
}

trait UniMap[K,V,VV] extends MapLike[K,V,VV] {
  type M <: Map[K, VV]

  def map:M
  def apply(n:K):VV = { map(n) }
  def foreach(lambda:((K,VV)) => Unit):Unit = map.foreach(lambda)
  def get(n:K):Option[VV] =  { val m = map; m.get(n) }
  def contains(k:K) = map.contains(k)
  def keys = map.keys
  def values = map.values

  def check(k:K, v:V):Unit
  def isMapped(v:V):Boolean
}

trait OneToOneMap[K,V] extends UniMap[K,V,V] {
  def isMapped(v:V) = map.values.toList.contains(v)
  def check(k:K, v:V):Unit = {
    if (map.contains(k) && map(k)!=v) throw RebindingException(this, k, v)
  }
}

trait OneToManyMap[K,V,VV<:Set[V]] extends UniMap[K,V,VV] {
  def isMapped(v:V) = map.values.toList.flatten.contains(v)
  def check(k:K, v:V):Unit = {}
}

trait BiMapType[TK,TV,TKK, TVV] {
  type KK = TKK
}

trait BiMap[K,V,KK,VV] extends MapLike[K,V,VV] with UniMap[K,V,VV] with BiMapType[K,V,KK,VV] {
  type TKK = KK
  def fmap:UniMap[K,V,VV]
  def bmap:UniMap[V,K,KK]
  def map:M = fmap.map.asInstanceOf[M]
  def check(k:K, v:V):Unit = {
    fmap.check(k,v)
    bmap.check(v,k)
  }
  def isMapped(v:V) = bmap.contains(v)
}
