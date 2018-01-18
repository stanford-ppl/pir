package pirc.newcollection

import pirc._
import pirc.exceptions._

import scala.collection.Map
import scala.collection.Set
import scala.reflect._

trait MapLike[K,V,VV] extends Serializable {
  val name:String = this.getClass().getSimpleName().replace("$","")
  def apply(n:K):VV
  def get(n:K):Option[VV]
  def contains(k:K):Boolean
  def keys:Iterable[K]
  def values:Iterable[VV]

  def check(k:K, v:V):Unit
}

trait UniMap[K,V,VV] extends MapLike[K,V,VV] {
  type M <: Map[K, VV]

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
    if (map.contains(k) && map(k)!=v) throw RebindingException(this, k, v)
  }
}

trait OneToManyMap[K,V,VV<:Set[V]] extends UniMap[K,V,VV] {
  def isMapped(v:V) = map.values.toList.flatten.contains(v)
  def check(k:K, v:V):Unit = {}
}

trait BiMap[K,V,KK,VV] extends MapLike[K,V,VV] with UniMap[K,V,VV] {
  val fmap:UniMap[K,V,VV]
  val bmap:UniMap[V,K,KK]
  val map:M = fmap.map.asInstanceOf[M]
  def check(k:K, v:V):Unit = {
    fmap.check(k,v)
    bmap.check(v,k)
  }
  def isMapped(v:V) = bmap.contains(v)
}

package object mutable {
  import scala.collection.mutable.Set
  import scala.collection.mutable.Map
  trait MapLike[K,V,VV] extends pirc.newcollection.MapLike[K,V,VV] {
    def update(k:K, v:V):Unit
    def += (pair:(K,V)) = { val (k,v) = pair; update(k,v)}
    def clear:Unit
    def getOrElseUpdate(k:K)(vv: => VV):VV
  }
  trait UniMap[K,V,VV] extends MapLike[K,V,VV] with pirc.newcollection.UniMap[K,V,VV] {
    override type M = Map[K,VV]
    val map:M = Map.empty 
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

  trait BiMap[K,V,KK,VV] extends MapLike[K,V,VV] with pirc.newcollection.BiMap[K,V,KK,VV] {
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

  trait MapLike[K,V,VV,S] extends pirc.newcollection.MapLike[K,V,VV] { self:S =>
    def + (pair:(K,V)):S
    def check (pair:(K,V)):Unit = { val (k,v) = pair; check(k,v) }
  }

  trait UniMap[K,V,VV,S] extends MapLike[K,V,VV,S] with pirc.newcollection.UniMap[K,V,VV] { self:S =>
    override type M = Map[K,VV]
    val map:M
    def newInstance(m:M):S = {
      this.getClass.getConstructor(classOf[M]).newInstance(m)
    }
  }

  trait OneToOneMapLike[K,V,S] extends UniMap[K,V,V,S] with pirc.newcollection.OneToOneMap[K,V] { self:S =>
    override def + (pair:(K,V)):S = { check(pair); newInstance(map + pair) }
  }
  case class OneToOneMap[K,V](map:Map[K,V]=Map[K,V]()) extends OneToOneMapLike[K,V,OneToOneMap[K,V]]

  trait OneToManyMapLike[K,V,S] extends UniMap[K,V,Set[V],S] with pirc.newcollection.OneToManyMap[K,V,Set[V]] { self:S =>
    override def + (pair:(K,V)):S = { 
      check(pair)
      val (k,v) = pair
      val vv = map.getOrElse(k, Set()) + v
      newInstance(map + ((k,vv)))
    }
  }
  case class OneToManyMap[K,V](map:Map[K,Set[V]]=Map[K,Set[V]]()) extends OneToManyMapLike[K,V,OneToManyMap[K,V]]

  trait BiMap[K,V,KK,VV,FM<:UniMap[K,V,VV,_],BM<:UniMap[V,K,KK,_],S] extends MapLike[K,V,VV,S] with pirc.newcollection.BiMap[K,V,KK,VV] { self:S =>
    override val fmap:FM
    override val bmap:BM
    def newInstance(fm:FM, bm:BM):S = {
      this.getClass.getConstructor(fm.getClass, bm.getClass).newInstance(fm, bm)
    }
    override def + (pair:(K,V)):S = {
      val (k,v) = pair
      val fm = (fmap + ((k,v))).asInstanceOf[FM]
      val bm = (bmap + ((v,k))).asInstanceOf[BM]
      newInstance(fm, bm)
    }
  }

  trait BiOneToOneMapLike[K,V,S] extends BiMap[K,V,K,V,OneToOneMap[K,V],OneToOneMap[V,K],S] { self:S => }
  case class BiOneToOneMap[K,V](fmap:OneToOneMap[K,V]=OneToOneMap[K,V](), bmap:OneToOneMap[V,K]=OneToOneMap[V,K]()) extends BiOneToOneMapLike[K,V,BiOneToOneMap[K,V]]
  
  trait BiOneToManyMapLike[K,V,S] extends BiMap[K,V,K,Set[V],OneToManyMap[K,V],OneToOneMap[V,K],S] { self:S => } 
  case class BiOneToManyMap[K,V](fmap:OneToManyMap[K,V]=OneToManyMap[K,V](), bmap:OneToOneMap[V,K]=OneToOneMap[V,K]()) extends BiOneToManyMapLike[K,V,BiOneToManyMap[K,V]]
  
  trait BiManyToOneMapLike[K,V,S] extends BiMap[K,V,Set[K],V,OneToOneMap[K,V],OneToManyMap[V,K],S] { self:S => } 
  case class BiManyToOneMap[K,V](fmap:OneToOneMap[K,V]=OneToOneMap[K,V](), bmap:OneToManyMap[V,K]=OneToManyMap[V,K]()) extends BiManyToOneMapLike[K,V,BiManyToOneMap[K,V]]
  
  trait BiManyToManyMapLike[K,V,S] extends BiMap[K,V,Set[K],Set[V],OneToManyMap[K,V],OneToManyMap[V,K],S] { self:S => } 
  case class BiManyToManyMap[K,V](fmap:OneToManyMap[K,V]=OneToManyMap[K,V](), bmap:OneToManyMap[V,K]=OneToManyMap[V,K]()) extends BiManyToManyMapLike[K,V,BiManyToManyMap[K,V]]

}
