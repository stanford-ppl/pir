package prism.collection.immutable

import prism._

import scala.collection.immutable.Set

trait MapLike[K,V,VV,S<:MapLike[K,V,VV,S]] extends prism.collection.MapLike[K,V,VV] { self:S =>
  def + (pair:(K,V)):S
  def - (pair:(K,V)):S
  def check (pair:(K,V)):Unit = { val (k,v) = pair; check(k,v) }
}

trait UniMap[K,V,VV,S<:UniMap[K,V,VV,S]] extends MapLike[K,V,VV,S] with prism.collection.UniMap[K,V,VV] { self:S =>
  override type M = Map[K,VV]
  val map:M
  def newInstance(m:M):S = {
    val constructor = this.getClass.getConstructors()(0)
    (constructor.getParameterTypes.toList match {
      case l if l == List(classOf[M], classOf[ClassTag[K]], classOf[ClassTag[V]]) => constructor.newInstance(m, kct, vct)
      case l if l == List(classOf[M]) => constructor.newInstance(m)
    }).asInstanceOf[S]
  }
}

trait OneToOneMapLike[K,V,S<:OneToOneMapLike[K,V,S]] extends UniMap[K,V,V,S] with prism.collection.OneToOneMap[K,V] { self:S =>
  override def + (pair:(K,V)):S = { check(pair); newInstance(map + pair) }
  def - (pair:(K,V)):S = { 
    val (k,v) = pair
    map.get(k).fold(this) { v => newInstance(map - k) }
  }
}
case class OneToOneMap[K:ClassTag,V:ClassTag](map:Map[K,V]) extends OneToOneMapLike[K,V,OneToOneMap[K,V]]
object OneToOneMap {
  def empty[K:ClassTag,V:ClassTag] = OneToOneMap[K,V](Map.empty)
}

trait OneToManyMapLike[K,V,S<:OneToManyMapLike[K,V,S]] extends UniMap[K,V,Set[V],S] with prism.collection.OneToManyMap[K,V,Set[V]] { self:S =>
  override def + (pair:(K,V)): S = { 
    check(pair)
    val (k,v) = pair
    newInstance(map + ((k,map.getOrElse(k, Set()) + v)))
  }
  def ++ (pair:(K,Set[V])):S = { 
    val (k,vv) = pair
    vv.foreach { v => check(k,v) }
    newInstance(map + ((k,map.getOrElse(k, Set()) ++ vv)))
  }
  def - (pair:(K,V)):S = { 
    val (k,v) = pair
    map.get(k).fold(this) { set => newInstance(map + (k -> (set - v))) }
  }
  def -- (pair:(K,Set[V])):S = { 
    val (k,vv) = pair
    map.get(k).fold(this) { set => newInstance(map + (k -> (set -- vv))) }
  }
}
case class OneToManyMap[K:ClassTag,V:ClassTag](map:Map[K,Set[V]]) extends OneToManyMapLike[K,V,OneToManyMap[K,V]]
object OneToManyMap {
  def empty[K:ClassTag,V:ClassTag] = OneToManyMap[K,V](Map.empty)
}

trait BiMap[K,V,KK,VV,FM<:UniMap[K,V,VV,FM],BM<:UniMap[V,K,KK,BM],S<:BiMap[K,V,KK,VV,FM,BM,S]] extends prism.collection.BiMap[K,V,KK,VV] with MapLike[K,V,VV,S] { self:S =>
  override def fmap:FM
  override def bmap:BM
  def newInstance(fm:FM, bm:BM):S = {
    val constructor = this.getClass.getConstructors()(0)
    (constructor.getParameterTypes.toList match {
      case l if l == List(fmap.getClass, bmap.getClass, classOf[ClassTag[K]], classOf[ClassTag[V]]) => constructor.newInstance(fm, bm, kct, vct)
      case l if l == List(fmap.getClass, bmap.getClass) => constructor.newInstance(fm, bm)
    }).asInstanceOf[S]
  }
  override def + (pair:(K,V)):S = {
    val (k,v) = pair
    val fm = (fmap + ((k,v)))
    val bm = (bmap + ((v,k)))
    newInstance(fm, bm)
  }
  override def - (pair:(K,V)):S = { 
    val (k,v) = pair
    val fm = (fmap - ((k,v)))
    val bm = (bmap - ((v,k)))
    newInstance(fm, bm)
  }
}

trait BiOneToOneMapLike[K,V,S<:BiOneToOneMapLike[K,V,S]] extends BiMap[K,V,K,V,OneToOneMap[K,V],OneToOneMap[V,K],S] { self:S => }
case class BiOneToOneMap[K:ClassTag,V:ClassTag](fmap:OneToOneMap[K,V], bmap:OneToOneMap[V,K]) extends BiOneToOneMapLike[K,V,BiOneToOneMap[K,V]]
object BiOneToOneMap {
  def empty[K:ClassTag,V:ClassTag] = BiOneToOneMap[K,V](OneToOneMap.empty, OneToOneMap.empty)
}

trait BiOneToManyMapLike[K,V,S<:BiOneToManyMapLike[K,V,S]] extends BiMap[K,V,K,Set[V],OneToManyMap[K,V],OneToOneMap[V,K],S] { self:S => 
  def ++ (pair:(K,Set[V])):S = { 
    val (k,vv) = pair
    val fm = fmap ++ ((k,vv))
    val bm = vv.foldLeft(bmap) { case (bm, v) => bm + ((v,k)) }
    newInstance(fm, bm)
  }
} 
case class BiOneToManyMap[K:ClassTag,V:ClassTag](fmap:OneToManyMap[K,V], bmap:OneToOneMap[V,K]) extends BiOneToManyMapLike[K,V,BiOneToManyMap[K,V]]
object BiOneToManyMap {
  def empty[K:ClassTag, V:ClassTag] = BiOneToManyMap[K,V](OneToManyMap.empty, OneToOneMap.empty)
}

trait BiManyToOneMapLike[K,V,S<:BiManyToOneMapLike[K,V,S]] extends BiMap[K,V,Set[K],V,OneToOneMap[K,V],OneToManyMap[V,K],S] { self:S => 
  def ++ (pair:(Set[K],V)):S = { 
    val (kk,v) = pair
    val fm = kk.foldLeft(fmap) { case (fm, k) => fm + ((k,v)) }
    val bm = bmap ++ (v,kk)
    newInstance(fm, bm)
  }
} 
case class BiManyToOneMap[K:ClassTag,V:ClassTag](fmap:OneToOneMap[K,V], bmap:OneToManyMap[V,K]) extends BiManyToOneMapLike[K,V,BiManyToOneMap[K,V]]
object BiManyToOneMap {
  def empty[K:ClassTag, V:ClassTag] = BiManyToOneMap[K,V](OneToOneMap.empty, OneToManyMap.empty)
}

trait BiManyToManyMapLike[K,V,S<:BiManyToManyMapLike[K,V,S]] extends BiMap[K,V,Set[K],Set[V],OneToManyMap[K,V],OneToManyMap[V,K],S] { self:S => 
  def ++ (pair:(Any,Any)):S = { 
    pair match {
      case (AsKK(kk), AsVV(vv)) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm ++ ((k,vv)) }
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm ++ ((v,kk)) }
        newInstance(fm, bm)
      case (AsK(k), AsVV(vv)) =>
        val fm = fmap ++ ((k,vv))
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm + ((v,k)) }
        newInstance(fm, bm)
      case (AsKK(kk), AsV(v)) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm + ((k,v)) }
        val bm = bmap ++ ((v,kk))
        newInstance(fm, bm)
    }
  }
  def -- (pair:(Any,Any)):S = { 
    pair match {
      case (AsKK(kk), AsVV(vv)) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm -- ((k,vv)) }
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm -- ((v,kk)) }
        newInstance(fm, bm)
      case (AsK(k), AsVV(vv)) =>
        val fm = fmap -- ((k,vv))
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm - ((v,k)) }
        newInstance(fm, bm)
      case (AsKK(kk), AsV(v)) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm - ((k,v)) }
        val bm = bmap -- (v,kk)
        newInstance(fm, bm)
    }
  }
} 
case class BiManyToManyMap[K:ClassTag,V:ClassTag](fmap:OneToManyMap[K,V], bmap:OneToManyMap[V,K]) extends BiManyToManyMapLike[K,V,BiManyToManyMap[K,V]]
object BiManyToManyMap {
  def empty[K:ClassTag, V:ClassTag] = BiManyToManyMap[K,V](OneToManyMap.empty, OneToManyMap.empty)
}
