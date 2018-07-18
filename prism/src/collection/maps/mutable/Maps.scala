package prism
package collection.mutable

import scala.collection.mutable.{ Set => MSet }
import scala.collection.mutable.Map

trait MapLike[K,V] extends prism.collection.MapLike[K,V] {
  def update(k:K, v:V):Unit
  def getOrElseUpdate(k:K)(vv: => VV):VV
  def remove(k:K, v:V):Unit
  def removeKey(k:K):Unit
  def removeValue(v:V):Unit
  def clear:Unit

  def += (pair:(K,V)) = { val (k,v) = pair; update(k,v)}
  def removeAll(a:Any) = {
    a match {
      case a:K => removeKey(a)
      case _ =>
    }
    a match {
      case a:V => removeValue(a)
      case _ =>
    }
  }
}
trait UniMap[K,V] extends prism.collection.UniMap[K,V] with MapLike[K,V] {
  override type UM = Map[K,_VV]
  val map:UM = Map.empty 
  def update(k:K, v:V):Unit = check(k,v)
  def removeKey(k:K):Unit = map -= k
  def removeValue(v:V):Unit = {
    map.foreach { case (k,_vv) =>
      val vv = _VVtoVV(_vv)
      if (toVs(vv).contains(v)) remove(k,v)
    }
  }
  def clear = { map.clear }
}

class OneToOneMap[K:ClassTag,V:ClassTag] extends prism.collection.OneToOneMap[K,V] with UniMap[K,V] {
  type KK = Set[K]
  def toKs(kk:KK):Set[K] = kk
  def toKK(ks:Set[K]):KK = ks
  override def update(k:K, v:V):Unit = {
    super.update(k,v)
    map += (k -> v)
  }
  def remove(k:K, v:V):Unit = removeKey(k)
  def getOrElseUpdate(k:K)(vv: => V):V = {
    if (!map.contains(k)) update(k,vv) 
    map(k)
  }
  def swap[T](x:T, y:T) = {
    (x,y) match {
      case (x:K,y:K) =>
        if (map.contains(x)) {
          val vv = map(x)
          map -= x 
          map += y -> vv
        }
      case (x:V,y:V) =>
        map.foreach { 
          case (k,`x`) => map += k -> y
          case _ =>
        }
    }
  }
}

class OneToManyMap[K:ClassTag,V:ClassTag] extends prism.collection.OneToManyMap[K,V] with UniMap[K,V] {
  type _VV = MSet[V]
  def _VVtoVV(_vv:_VV):VV = _vv.toSet
  val vvct = classTag[VV]
  type KK = Set[K]
  def toKs(kk:KK):Set[K] = kk
  def toKK(ks:Set[K]):KK = ks
  override def apply(k:K) = map.getOrElse(k, Set()).toSet
  override def update(k:K, v:V):Unit = {
    updateSingle(k,v)
  }
  def updateSingle(k:K, v:V):Unit = {
    super.update(k,v)
    map += k -> (map.getOrElse(k, MSet[V]()) + v)
  }
  def remove(k:K, v:V):Unit = {
    map.get(k).foreach { case vv:_VV =>
      vv -= v
      if (vv.isEmpty) removeKey(k)
    }
  }
  def getOrElseUpdate(k:K)(vv: => Set[V]):Set[V] = {
    if (!map.contains(k)) vv.foreach { v => update(k,v) }
    map(k).toSet
  }
  def update(k:K, it:Iterable[V]):Unit = it match { 
    case v:V => updateSingle(k,v)
    case vv => vv.foreach(v => updateSingle(k,v))
  }
}

trait BiMap[K,V] extends prism.collection.BiMap[K,V] with MapLike[K,V] {
  type FM <: UniMap[K,V]
  type BM <: UniMap[V,K]

  def update(k:K, v:V):Unit = {
    super.check(k,v)
    fmap.update(k,v)
    bmap.update(v,k)
  }
  override def remove(k:K, v:V):Unit = {
    fmap.remove(k,v)
    bmap.remove(v,k)
  }
  def removeKey(k:K):Unit = {
    fmap.get(k).foreach { vv => toVs(vv).foreach(v => bmap.remove(v,k)) }
    fmap.removeKey(k)
  }
  def removeValue(v:V):Unit = {
    bmap.get(v).foreach { kk => toKs(kk).foreach(k => fmap.remove(k,v) ) }
    bmap.removeKey(v)
  }
  def getOrElseUpdate(k:K)(vv: => VV):VV = {
    fmap.getOrElseUpdate(k){
      toVV(toVs(vv).map { v => bmap.update(v, k); v })
    }
  }

  def clear = { fmap.clear; bmap.clear }

}

class BiOneToOneMap[K:ClassTag,V:ClassTag] extends prism.collection.BiOneToOneMap[K,V] with BiMap[K,V] {
  def _VVtoVV(_vv:_VV):VV = _vv
  type FM = OneToOneMap[K,V]
  type BM = OneToOneMap[V,K]
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
}

class BiOneToManyMap[K:ClassTag,V:ClassTag] extends prism.collection.BiOneToManyMap[K,V] with BiMap[K,V] {
  def _VVtoVV(_vv:_VV):VV = _vv.toSet
  type FM = OneToManyMap[K,V]
  type BM = OneToOneMap[V,K]
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
  def update(k:K, vv:Iterable[V]):Unit = vv.foreach(v => update(k,v))
  def ++= (kvv:(K,Iterable[V])):Unit = update(kvv._1, kvv._2)
}

class BiManyToOneMap[K:ClassTag,V:ClassTag] extends prism.collection.BiManyToOneMap[K,V] with BiMap[K,V] {
  def _VVtoVV(_vv:_VV):VV = _vv
  type FM = OneToOneMap[K,V]
  type BM = OneToManyMap[V,K]
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
}

class BiManyToManyMap[K:ClassTag,V:ClassTag] extends prism.collection.BiManyToManyMap[K,V] with BiMap[K,V] {
  def _VVtoVV(_vv:_VV):VV = _vv.toSet
  type FM = OneToManyMap[K,V]
  type BM = OneToManyMap[V,K]
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
  def update(k:K, vv:Iterable[V]):Unit = vv.foreach(v => update(k,v))
  def ++= (kvv:(K,Iterable[V])):Unit = update(kvv._1, kvv._2)
}
