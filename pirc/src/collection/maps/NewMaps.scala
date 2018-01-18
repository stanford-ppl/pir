package pirc.newcollection

import pirc._

import scala.collection.Set

trait Map[K,V,VV] extends Serializable {
  val name:String = this.getClass().getSimpleName().replace("$","")
  def apply(n:K):VV
  def get(n:K):Option[VV]
  def contains(k:K):Boolean
  def keys:Iterable[K]
  def values:Iterable[VV]

  def check(k:K, v:V):Unit
}

trait UniMap[K,V,VV] extends Map[K,V,VV] {
  type M <: scala.collection.Map[K, VV]

  val map:M
  def apply(n:K):VV = { val m = map; m(n) }
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
    if (map.contains(k) && map(k)!=v)
      throw PIRException(s"${name} already contains key $k -> ${map(k)} but try to rebind to $v")
  }
}

trait OneToManyMap[K,V,VV<:Set[V]] extends UniMap[K,V,VV] {
  def isMapped(v:V) = map.values.toList.flatten.contains(v)
  def check(k:K, v:V):Unit = {}
}

trait BiMap[K,V,KK,VV] extends Map[K,V,VV] with UniMap[K,V,VV] {
  val fmap:UniMap[K,V,VV]
  val bmap:UniMap[V,K,KK]
  val map:M = fmap.asInstanceOf[M]
  def check(k:K, v:V):Unit = {
    fmap.check(k,v)
    bmap.check(v,k)
  }
  def isMapped(v:V) = bmap.contains(v)
}

package object mutable {
  import scala.collection.mutable.Set
  trait Map[K,V,VV] extends pirc.newcollection.Map[K,V,VV] {
    def update(k:K, v:V):Unit
    def += (pair:(K,V)) = { val (k,v) = pair; update(k,v)}
    def clear:Unit
    def getOrElseUpdate(k:K)(vv: => VV):VV
  }
  trait UniMap[K,V,VV] extends Map[K,V,VV] with pirc.newcollection.UniMap[K,V,VV] {
    override type M = scala.collection.mutable.Map[K,VV]
    val map:M = scala.collection.mutable.Map.empty
    def update(k:K, v:V):Unit = check(k,v)
    def clear = { map.clear }
  }

  class OneToOneMap[K,V]() extends UniMap[K,V,V] with pirc.newcollection.OneToOneMap[K,V] {
    override def update(k:K, v:V):Unit = {
      super.update(k,v)
      map += (k -> v)
    }
    def getOrElseUpdate(k:K)(vv: => V):V = {
      if (!map.contains(k)) update(k,vv) 
      map(k)
    }
  }

  class OneToManyMap[K,V]() extends UniMap[K,V,Set[V]] with pirc.newcollection.OneToManyMap[K,V,Set[V]] {
    override def update(k:K, v:V):Unit = {
      super.update(k,v)
      map.getOrElseUpdate(k, Set[V]()) += v
    }
    def getOrElseUpdate(k:K)(vv: => Set[V]):Set[V] = {
      if (!map.contains(k)) vv.foreach { v => update(k,v) }
      map(k)
    }
  }

  trait BiMap[K,V,KK,VV] extends Map[K,V,VV] with pirc.newcollection.BiMap[K,V,KK,VV] {
    override val fmap:UniMap[K,V,VV]
    override val bmap:UniMap[V,K,KK]

    def update(k:K, v:V):Unit = {
      fmap.update(k,v)
      bmap.update(v,k)
    }

    def clear = { fmap.clear; bmap.clear }
  }

  trait ForwardOneToOneMap[K,V,KK] extends BiMap[K,V,KK,V] {
    val fmap = new OneToOneMap[K,V]()

    def getOrElseUpdate(k:K)(vv: => V):V = {
      fmap.getOrElseUpdate(k){
        val v = vv
        bmap.update(v,k)
        v
      }
    }
  }

  trait ForwardOneToManyMap[K,V,KK] extends BiMap[K,V,KK,Set[V]] {
    val fmap = new OneToManyMap[K,V]()

    def getOrElseUpdate(k:K)(vv: => Set[V]):Set[V] = {
      fmap.getOrElseUpdate(k){
        vv.map { v => bmap.update(v, k); v }
      }
    }
  }

  trait BackwardOneToOneMap[K,V,VV] extends BiMap[K,V,K,VV] {
    val bmap = new OneToOneMap[V,K]()
  }

  trait BackwardOneToManyMap[K,V,VV] extends BiMap[K,V,Set[K],VV] {
    val bmap = new OneToManyMap[V,K]()
  }

  class BiOneToOneMap[K,V]() extends ForwardOneToOneMap[K,V,K] with BackwardOneToOneMap[K,V,V]
  
  class BiOneToManyMap[K,V]() extends ForwardOneToManyMap[K,V,K] with BackwardOneToOneMap[K,V,Set[V]]
  
  class BiManyToOneMap[K,V] extends ForwardOneToOneMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,V]
  
  class BiManyToManyMap[K,V] extends ForwardOneToManyMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,Set[V]]

}

package object immutable {

  import scala.collection.immutable.Set
  import scala.collection.immutable.{Map => SMap}

  trait Map[K,V,VV,S] extends pirc.newcollection.Map[K,V,VV] { self:S =>
    def + (pair:(K,V)):S
    def check (pair:(K,V)):Unit = { val (k,v) = pair; check(k,v) }
  }

  trait UniMap[K,V,VV,S] extends Map[K,V,VV,S] with pirc.newcollection.UniMap[K,V,VV] { self:S =>
    override type M = SMap[K,VV]
    val map:M
    def newInstance(m:M):S
  }

  trait OneToOneMapLike[K,V,S] extends UniMap[K,V,V,S] with pirc.newcollection.OneToOneMap[K,V] { self:S =>
    override def + (pair:(K,V)):S = { check(pair); newInstance(map + pair) }
  }

  class OneToOneMap[K,V](val map:SMap[K,V]) extends OneToOneMapLike[K,V,OneToOneMap[K,V]] {
    def newInstance(m:M) = new OneToOneMap[K,V](m)
  }
  object OneToOneMap {
    def empty[K,V] = new OneToOneMap[K,V](SMap[K,V]())
  }

  trait OneToManyMapLike[K,V,S] extends UniMap[K,V,Set[V],S] with pirc.newcollection.OneToManyMap[K,V,Set[V]] { self:S =>
    override def + (pair:(K,V)):S = { check(pair); 
      val (k,v) = pair
      val vv = map.getOrElse(k, Set(v))
      newInstance(map + ((k,vv)))
    }
  }

  class OneToManyMap[K,V](val map:SMap[K,Set[V]]) extends OneToManyMapLike[K,V,OneToManyMap[K,V]] {
    def newInstance(m:M) = new OneToManyMap[K,V](m)
  }
  object OneToManyMap {
    def empty[K,V] = new OneToManyMap[K,V](SMap[K,Set[V]]())
  }

  trait BiMap[K,V,KK,VV,FM<:UniMap[K,V,VV,_],BM<:UniMap[V,K,KK,_],S] extends Map[K,V,VV,S] with pirc.newcollection.BiMap[K,V,KK,VV] { self:S =>
    override val fmap:FM
    override val bmap:BM
    def newInstance(fm:FM, bm:BM):S
    override def + (pair:(K,V)):S = {
      val (k,v) = pair
      val fm = (fmap + ((k,v))).asInstanceOf[FM]
      val bm = (bmap + ((v,k))).asInstanceOf[BM]
      newInstance(fm, bm)
    }
  }

  trait BiOneToOneMapLike[K,V,S] extends BiMap[K,V,K,V,OneToOneMap[K,V],OneToOneMap[V,K],S] { self:S => }
  class BiOneToOneMap[K,V](val fmap:OneToOneMap[K,V]=OneToOneMap.empty, val bmap:OneToOneMap[V,K]=OneToOneMap.empty) extends BiOneToOneMapLike[K,V,BiOneToOneMap[K,V]] {
    def newInstance(fm:OneToOneMap[K,V], bm:OneToOneMap[V,K]) = new BiOneToOneMap(fm, bm)
  }
  
  trait BiOneToManyMapLike[K,V,S] extends BiMap[K,V,K,Set[V],OneToManyMap[K,V],OneToOneMap[V,K],S] { self:S => } 
  class BiOneToManyMap[K,V](val fmap:OneToManyMap[K,V]=OneToManyMap.empty, val bmap:OneToOneMap[V,K]=OneToOneMap.empty) extends BiOneToManyMapLike[K,V,BiOneToManyMap[K,V]]  {
    def newInstance(fm:OneToManyMap[K,V], bm:OneToOneMap[V,K]) = new BiOneToManyMap(fm, bm)
  }
  
  trait BiManyToOneMapLike[K,V,S] extends BiMap[K,V,Set[K],V,OneToOneMap[K,V],OneToManyMap[V,K],S] { self:S => } 
  class BiManyToOneMap[K,V](val fmap:OneToOneMap[K,V]=OneToOneMap.empty, val bmap:OneToManyMap[V,K]=OneToManyMap.empty) extends BiManyToOneMapLike[K,V,BiManyToOneMap[K,V]]  {
    def newInstance(fm:OneToOneMap[K,V], bm:OneToManyMap[V,K]) = new BiManyToOneMap(fm, bm)
  }
  
  trait BiManyToManyMapLike[K,V,S] extends BiMap[K,V,Set[K],Set[V],OneToManyMap[K,V],OneToManyMap[V,K],S] { self:S => } 
  class BiManyToManyMap[K,V](val fmap:OneToManyMap[K,V]=OneToManyMap.empty, val bmap:OneToManyMap[V,K]=OneToManyMap.empty) extends BiManyToManyMapLike[K,V,BiManyToManyMap[K,V]]  {
    def newInstance(fm:OneToManyMap[K,V], bm:OneToManyMap[V,K]) = new BiManyToManyMap(fm, bm)
  }

}
