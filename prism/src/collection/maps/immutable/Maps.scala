package prism
package collection.immutable

trait MapLike[K,V,S<:MapLike[K,V,S]] extends prism.collection.MapLike[K,V] { self:S =>
  override type _VV = VV
  override def _VVtoVV(_vv:_VV):VV = _vv
  def + (pair:(K,V)):S
  def - (pair:(K,V)):S
}

trait UniMap[K,V,S<:UniMap[K,V,S]] extends prism.collection.UniMap[K,V] with MapLike[K,V,S]  { self:S =>
  override type UM = Map[K,_VV]

  def ++ (pair:(K,VV)):S
  def -- (pair:(K,VV)):S
  def - (k:K):S = newInstance(map - k)
  def newInstance(m:UM):S = {
    val constructor = this.getClass.getConstructors()(0)
    (constructor.getParameterTypes.toList match {
      case l if l == List(classOf[UM], classOf[ClassTag[K]], classOf[ClassTag[V]]) => constructor.newInstance(m, kct, vct)
      case l if l == List(classOf[UM]) => constructor.newInstance(m)
    }).asInstanceOf[S]
  }
}

trait OneToOneMapLike[K,V,S<:OneToOneMapLike[K,V,S]] extends prism.collection.OneToOneMap[K,V] with UniMap[K,V,S] { self:S =>
  type KK = Set[K]
  def toKs(kk:KK):Set[K] = kk
  def toKK(ks:Set[K]):KK = ks
  def + (pair:(K,V)):S = { check(pair); newInstance(map + pair) }
  def ++ (pair:(K,VV)):S = this + pair
  def - (pair:(K,V)):S = { 
    val (k,v) = pair
    map.get(k).fold(this) { v => newInstance(map - k) }
  }
  def -- (pair:(K,VV)):S = this - pair
}
case class OneToOneMap[K:ClassTag,V:ClassTag](map:Map[K,V]) extends OneToOneMapLike[K,V,OneToOneMap[K,V]]
object OneToOneMap {
  def empty[K:ClassTag,V:ClassTag] = OneToOneMap[K,V](Map.empty)
}

trait OneToManyMapLike[K,V,S<:OneToManyMapLike[K,V,S]] extends prism.collection.OneToManyMap[K,V] with UniMap[K,V,S] { self:S =>
  val vvct = classTag[VV]
  type KK = Set[K]
  def toKs(kk:KK):Set[K] = kk
  def toKK(ks:Set[K]):KK = ks
  def + (pair:(K,V)): S = { 
    check(pair)
    val (k,v) = pair
    newInstance(map + ((k,map.getOrElse(k, Set()) + v)))
  }
  def ++ (pair:(K,VV)):S = { 
    val (k,vv) = pair
    vv.foreach { v => check(k,v) }
    newInstance(map + ((k,map.getOrElse(k, Set()) ++ vv)))
  }
  def - (pair:(K,V)):S = { 
    val (k,v) = pair
    map.get(k).fold(this) { set => newInstance(map + (k -> (set - v))) }
  }
  def -- (pair:(K,VV)):S = { 
    val (k,vv) = pair
    map.get(k).fold(this) { set => newInstance(map + (k -> (set -- vv))) }
  }
}
case class OneToManyMap[K:ClassTag,V:ClassTag](map:Map[K,Set[V]]) extends OneToManyMapLike[K,V,OneToManyMap[K,V]]
object OneToManyMap {
  def empty[K:ClassTag,V:ClassTag] = OneToManyMap[K,V](Map.empty)
}

trait BiMap[K,V,S<:BiMap[K,V,S]] extends prism.collection.BiMap[K,V] with MapLike[K,V,S] { self:S =>
  override type UM = Map[K,VV]
  type FM <: UniMap[K,V,FM]
  type BM <: UniMap[V,K,BM]
  override val fmap:FM
  override val bmap:BM
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

trait BiOneToOneMapLike[K,V,S<:BiOneToOneMapLike[K,V,S]] extends BiMap[K,V,S] with prism.collection.BiOneToOneMap[K,V]{ self:S => 
  type FM = OneToOneMap[K,V]
  type BM = OneToOneMap[V,K]
}
case class BiOneToOneMap[K:ClassTag,V:ClassTag](fmap:OneToOneMap[K,V], bmap:OneToOneMap[V,K]) extends BiOneToOneMapLike[K,V,BiOneToOneMap[K,V]]
object BiOneToOneMap {
  def empty[K:ClassTag,V:ClassTag] = BiOneToOneMap[K,V](OneToOneMap.empty, OneToOneMap.empty)
}

trait BiOneToManyMapLike[K,V,S<:BiOneToManyMapLike[K,V,S]] extends BiMap[K,V,S] with prism.collection.BiOneToManyMap[K,V]{ self:S => 
  type FM = OneToManyMap[K,V]
  type BM = OneToOneMap[V,K]
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

trait BiManyToOneMapLike[K,V,S<:BiManyToOneMapLike[K,V,S]] extends BiMap[K,V,S] with prism.collection.BiManyToOneMap[K,V]{ self:S => 
  type FM = OneToOneMap[K,V]
  type BM = OneToManyMap[V,K]
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

abstract class BiManyToManyMapLike[K:ClassTag,V:ClassTag,S<:BiManyToManyMapLike[K,V,S]] extends BiMap[K,V,S] with prism.collection.BiManyToManyMap[K,V] { self:S => 
  type FM = OneToManyMap[K,V]
  type BM = OneToManyMap[V,K]
  override lazy val kkct:ClassTag[KK] = bmap.vvct
  override lazy val vvct:ClassTag[VV] = fmap.vvct
  def ++ (pair:(Any,Any)):S = { 
    pair match {
      case (kkct(kk), vvct(vv)) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm ++ ((k,vv)) }
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm ++ ((v,kk)) }
        newInstance(fm, bm)
      case (k:K, vvct(vv)) =>
        val fm = fmap ++ ((k,vv))
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm + ((v,k)) }
        newInstance(fm, bm)
      case (kkct(kk), v:V) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm + ((k,v)) }
        val bm = bmap ++ ((v,kk))
        newInstance(fm, bm)
    }
  }
  def -- (pair:(Any,Any)):S = { 
    pair match {
      case (kkct(kk), vvct(vv)) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm -- ((k,vv)) }
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm -- ((v,kk)) }
        newInstance(fm, bm)
      case (k:K, vvct(vv)) =>
        val fm = fmap -- ((k,vv))
        val bm = vv.foldLeft(bmap) { case (bm, v) => bm - ((v,k)) }
        newInstance(fm, bm)
      case (kkct(kk), v:V) =>
        val fm = kk.foldLeft(fmap) { case (fm, k) => fm - ((k,v)) }
        val bm = bmap -- (v,kk)
        newInstance(fm, bm)
    }
  }
  override def - (pair:(K,V)):S = super.-(pair)

  def - (x:Any):S = x match {
    case k:K => 
      val vv:VV = fmap(k)
      val bm = vv.foldLeft(bmap) { case (bm, v) => bm - ((v,k)) }
      newInstance(fmap - k, bm)
    case v:V =>
      val kk:KK = bmap(v)
      val fm = kk.foldLeft(fmap) { case (fm, k) => fm - ((k,v)) }
      newInstance(fm, bmap - v)
  }
} 
case class BiManyToManyMap[K:ClassTag,V:ClassTag](fmap:OneToManyMap[K,V], bmap:OneToManyMap[V,K]) extends BiManyToManyMapLike[K,V,BiManyToManyMap[K,V]]
object BiManyToManyMap {
  def empty[K:ClassTag, V:ClassTag] = BiManyToManyMap[K,V](OneToManyMap.empty, OneToManyMap.empty)
}
