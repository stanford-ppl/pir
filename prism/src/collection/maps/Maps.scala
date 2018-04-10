package prism.collection

import prism._
import prism.exceptions._

import scala.collection.Map
import scala.collection.Set

abstract class MapType[TK:ClassTag,TV:ClassTag] extends prism.util.Serializable {
  type K = TK
  type V = TV
  type VV
  implicit val kct = implicitly[ClassTag[K]]
  implicit val vct = implicitly[ClassTag[V]]

}

trait MapLike[K,V] extends MapType[K,V] {
  val vvct:ClassTag[VV]
  def asK(x:Any) = x match { case x:K => Some(x); case _ => None }
  def asV(x:Any) = x match { case x:V => Some(x); case _ => None }
  def apply(n:K):VV

  def get(n:K):Option[VV]
  def foreach(lambda:((K,VV)) => Unit):Unit
  def contains(k:K):Boolean
  def keys:Iterable[K]
  def values:Iterable[VV]

  def check(pair:(K,V)):Unit
}

trait UniMap[K,V] extends MapLike[K,V] {
  type UM <: Map[K, VV]

  val map:UM
  def apply(n:K):VV = { map(n) }
  def foreach(lambda:((K,VV)) => Unit):Unit = map.foreach(lambda)
  def map[B](lambda:((K,VV)) => B):Iterable[B] = map.map(lambda)
  def get(n:K):Option[VV] =  { val m = map; m.get(n) }
  def contains(k:K) = map.contains(k)
  def keys = map.keys
  def values = map.values

  def isMapped(x:Any):Boolean
}

trait OneToOneMap[K,V] extends UniMap[K,V] {
  type VV = V
  val vvct = classTag[VV]
  def isMapped(x:Any) = x match {
    case x:K => map.contains(x)
    case x:V => map.values.toList.contains(x)
  }
  def check(pair:(K,V)):Unit = {
    val (k,v) = pair
    if (map.contains(k) && map(k)!=v) throw RebindingException(this, k, v)
  }
}

trait OneToManyMap[K,V] extends UniMap[K,V] {
  type VV <: Set[V]
  def isMapped(x:Any) = x match {
    case x:K => map.contains(x)
    case x:V => map.values.toList.flatten.contains(x)
  }
  def check(pair:(K,V)):Unit = {}
}

abstract class BiMap[K:ClassTag,V:ClassTag] extends UniMap[K,V] {
  val fmap:UniMap[K,V]
  val bmap:UniMap[V,K]
  type VV = fmap.VV
  type KK = bmap.VV
  type UM = fmap.UM
  lazy val kkct:ClassTag[KK] = bmap.vvct
  lazy val vvct:ClassTag[VV] = fmap.vvct

  lazy val map:UM = fmap.map

  def check(pair:(K,V)):Unit = {
    val (k,v) = pair
    fmap.check(k,v)
    bmap.check(v,k)
  }
  def isMapped(x:Any) = x match {
    case x:K => fmap.contains(x)
    case x:V => bmap.contains(x)
  }
}

case class RebindingException[K,V](map:OneToOneMap[K,V], k:K, v:V) extends PIRException {
  def msg = s"${map} already contains key $k -> ${map(k)} but try to rebind to $v"
}
