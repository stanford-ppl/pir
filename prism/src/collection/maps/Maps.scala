package prism
package collection

import scala.collection.Map

abstract class MapType[TK:ClassTag,TV:ClassTag] extends prism.util.Serializable {
  type K = TK
  type V = TV
  type KK
  type VV
  type _VV
  implicit val kct = implicitly[ClassTag[K]]
  implicit val vct = implicitly[ClassTag[V]]
  def _VVtoVV(_vv:_VV):VV

}

trait MapLike[K,V] extends MapType[K,V] with prism.util.ScalaUtilFunc {
  val vvct:ClassTag[VV]
  def asK(x:Any) = x match { case x:K => Some(x); case _ => None }
  def asV(x:Any) = x match { case x:V => Some(x); case _ => None }
  def apply(n:K):VV
  def get(n:K):Option[VV]
  def getOrElse(k:K,vv:VV):VV
  def foreach(lambda:((K,VV)) => Unit):Unit
  def contains(k:K):Boolean
  def keys:Iterable[K]
  def values:Iterable[VV]

  def rmap:Map[V,KK]
  def lookupV(v:V):KK
  def getV(v:V):Option[KK]
  def containsV(v:V):Boolean

  def toVs(vv:VV):Set[V]
  def toVV(vs:Set[V]):VV
  def toKs(kk:KK):Set[K]
  def toKK(ks:Set[K]):KK

  def check(pair:(K,V)):Unit
}

trait UniMap[K,V] extends MapLike[K,V] {
  type UM <: Map[K, _VV]

  val map:UM
  def apply(n:K):VV = { map.get(n).map(_VVtoVV).getOrElse(throw PIRException(s"$n not found in $this")) }
  def foreach(lambda:((K,VV)) => Unit):Unit = map.foreach{ case (k,_vv) => lambda(k, _VVtoVV(_vv)) }
  def map[B](lambda:((K,VV)) => B):Iterable[B] = map.map{ case (k,_vv) => lambda(k, _VVtoVV(_vv)) }
  def get(n:K):Option[VV] =  { val m = map; m.get(n).map { _vv => _VVtoVV(_vv) } }
  def getOrElse(k:K,vv:VV):VV = map.get(k).map(_VVtoVV).getOrElse(vv)
  def contains(k:K) = map.contains(k)
  def keys = map.keys
  def values = map.values.map(_VVtoVV)

  def rmap:Map[V,KK] = reverseMap(map).flatMap { case (_vv, ks) => toVs(_VVtoVV(_vv)).map { v => (v, toKK(ks)) } }
  def lookupV(v:V):KK = getV(v).get
  def getV(v:V):Option[KK] = {
    // v -> Set(k)
    val ks = map.flatMap { case (k, _vv) => 
      if (_vv == v) Some(k) else {
        if (toVs(_VVtoVV(_vv)).contains(v)) Some(k) else None
      }
    }
    if (ks.isEmpty) None else Some(toKK(ks.toSet))
  }
  def containsV(v:V):Boolean = values.exists { _ == v }

  def isMapped(x:Any):Boolean
}

trait OneToOneMap[K,V] extends UniMap[K,V] {
  type _VV = V
  type VV = V
  val vvct = classTag[VV]
  def toVs(vv:VV):Set[V] = Set(vv)
  def toVV(vs:Set[V]):VV = assertOne(vs, "OneToOneMap.toVV")
  def _VVtoVV(_vv:_VV):VV = _vv
  override def rmap:Map[V,KK] = reverseMap(map).map { case (v, ks) => (v, toKK(ks)) } // Performance optimization
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
  type VV = Set[V]
  def toVs(vv:VV):Set[V] = vv
  def toVV(vs:Set[V]):VV = vs
  def isMapped(x:Any) = x match {
    case x:K => map.contains(x)
    case x:V => containsV(x)
  }
  def check(pair:(K,V)):Unit = {}
}

abstract class BiMap[K:ClassTag,V:ClassTag] extends UniMap[K,V] {
  type FM <:UniMap[K,V]
  type BM <:UniMap[V,K]
  val fmap:FM
  val bmap:BM
  type VV = fmap.VV
  type KK = bmap.VV
  type _VV = fmap._VV
  type UM = fmap.UM
  lazy val kkct:ClassTag[KK] = bmap.vvct
  lazy val vvct:ClassTag[VV] = fmap.vvct

  lazy val map:UM = fmap.map

  def toKs(kk:KK):Set[K] = bmap.toVs(kk)
  def toKK(ks:Set[K]):KK = bmap.toVV(ks)
  def toVs(vv:VV):Set[V] = fmap.toVs(vv) 
  def toVV(vs:Set[V]):VV = fmap.toVV(vs) 

  override def rmap:Map[V,KK] = bmap.map.map { case (v, _kk) => (v, bmap._VVtoVV(_kk)) }
  override def lookupV(v:V):KK = bmap(v)
  override def getV(v:V):Option[KK] = bmap.get(v)
  override def containsV(v:V):Boolean = bmap.contains(v)

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

trait BiOneToOneMap[K,V] extends BiMap[K,V] {
  type FM <: OneToOneMap[K,V]
  type BM <: OneToOneMap[V,K]
  override type KK = K
  override type VV = V
  override type _VV = V
}

trait BiOneToManyMap[K,V] extends BiMap[K,V] {
  type FM <: OneToManyMap[K,V]
  type BM <: OneToOneMap[V,K]
  override type KK = K
  override type VV = Set[V]
  override def apply(k:K):VV = fmap.getOrElse(k, Set[V]())
}

trait BiManyToOneMap[K,V] extends BiMap[K,V] {
  type FM <: OneToOneMap[K,V]
  type BM <: OneToManyMap[V,K]
  override type KK = Set[K]
  override type VV = V
  override type _VV = V
}

trait BiManyToManyMap[K,V] extends BiMap[K,V] {
  type FM <: OneToManyMap[K,V]
  type BM <: OneToManyMap[V,K]
  override type KK = Set[K]
  override type VV = Set[V]
  override def apply(k:K):VV = fmap.getOrElse(k, Set())
}

case class RebindingException[K,V](map:OneToOneMap[K,V], k:K, v:V) extends PIRException {
  def msg = s"${map} already contains key $k -> ${map(k)} but try to rebind to $v"
}
