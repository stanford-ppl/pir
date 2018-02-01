package prism.collection.immutable

import pirc._
import pirc.exceptions._

import scala.reflect._

import scala.collection.immutable.Set

trait MapLike[K,V,VV,S] extends prism.collection.MapLike[K,V,VV] { self:S =>
  def + (pair:(K,V)):S
  def check (pair:(K,V)):Unit = { val (k,v) = pair; check(k,v) }
}

trait UniMap[K,V,VV,S] extends MapLike[K,V,VV,S] with prism.collection.UniMap[K,V,VV] { self:S =>
  override type M = Map[K,VV]
  val map:M
  def newInstance(m:M):S = {
    this.getClass.getConstructor(classOf[M]).newInstance(m)
  }
}

trait OneToOneMapLike[K,V,S] extends UniMap[K,V,V,S] with prism.collection.OneToOneMap[K,V] { self:S =>
  override def + (pair:(K,V)):S = { check(pair); newInstance(map + pair) }
}
case class OneToOneMap[K,V](map:Map[K,V]=Map[K,V]()) extends OneToOneMapLike[K,V,OneToOneMap[K,V]]

trait OneToManyMapLike[K,V,S] extends UniMap[K,V,Set[V],S] with prism.collection.OneToManyMap[K,V,Set[V]] { self:S =>
  override def + (pair:(K,V)): S = { 
    check(pair)
    val (k,v) = pair
    val vv = map.getOrElse(k, Set()) + v
    newInstance(map + ((k,vv)))
  }
  def ++ (pair:(K,Set[V])):S = { 
    val (k,vv) = pair
    vv.foldLeft(this) { case (prev, v) => (prev + (k,v)).asInstanceOf[OneToManyMapLike[K,V,S] with S] }
  }
}
case class OneToManyMap[K,V](map:Map[K,Set[V]]=Map[K,Set[V]]()) extends OneToManyMapLike[K,V,OneToManyMap[K,V]]

trait BiMap[K,V,KK,VV,FM<:UniMap[K,V,VV,_],BM<:UniMap[V,K,KK,_],S] extends MapLike[K,V,VV,S] with prism.collection.BiMap[K,V,KK,VV] { self:S =>
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

trait BiOneToManyMapLike[K,V,S] extends BiMap[K,V,K,Set[V],OneToManyMap[K,V],OneToOneMap[V,K],S] { self:S => 
  def ++ (pair:(K,Set[V])):S = { 
    val (k,vv) = pair
    vv.foldLeft(this) { case (prev, v) => (prev + (k,v)).asInstanceOf[BiOneToManyMapLike[K,V,S] with S] }
  }
} 
case class BiOneToManyMap[K,V](fmap:OneToManyMap[K,V]=OneToManyMap[K,V](), bmap:OneToOneMap[V,K]=OneToOneMap[V,K]()) extends BiOneToManyMapLike[K,V,BiOneToManyMap[K,V]]

trait BiManyToOneMapLike[K,V,S] extends BiMap[K,V,Set[K],V,OneToOneMap[K,V],OneToManyMap[V,K],S] { self:S => } 
case class BiManyToOneMap[K,V](fmap:OneToOneMap[K,V]=OneToOneMap[K,V](), bmap:OneToManyMap[V,K]=OneToManyMap[V,K]()) extends BiManyToOneMapLike[K,V,BiManyToOneMap[K,V]]

trait BiManyToManyMapLike[K,V,S] extends BiMap[K,V,Set[K],Set[V],OneToManyMap[K,V],OneToManyMap[V,K],S] { self:S => } 
case class BiManyToManyMap[K,V](fmap:OneToManyMap[K,V]=OneToManyMap[K,V](), bmap:OneToManyMap[V,K]=OneToManyMap[V,K]()) extends BiManyToManyMapLike[K,V,BiManyToManyMap[K,V]]

