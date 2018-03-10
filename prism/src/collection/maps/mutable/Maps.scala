package prism.collection.mutable

import prism._
import prism.exceptions._

import scala.collection.mutable.Set
import scala.collection.mutable.Map
trait MapLike[K,V,VV] extends prism.collection.MapLike[K,V,VV] {
  def update(k:K, v:V):Unit
  def getOrElseUpdate(k:K)(vv: => VV):VV
  def remove(k:K, v:V):Unit
  def removeKey(k:K):Unit
  def removeValue(v:V):Unit
  def clear:Unit
  def toVs(vv:VV):Set[V]
  def toVV(vs:Set[V]):VV

  def += (pair:(K,V)) = { val (k,v) = pair; update(k,v)}
  def removeAll(a:Any) = {
    asK(a).foreach { k => removeKey(k) }
    asV(a).foreach { v => removeValue(v) }
  }
}
trait UniMap[K,V,VV] extends MapLike[K,V,VV] with prism.collection.UniMap[K,V,VV] {
  override type M = Map[K,VV]
  val map:M = Map.empty 
  def update(k:K, v:V):Unit = check(k,v)
  def remove(k:K, v:V):Unit = {
    map.get(k).foreach { vv =>
      val vs = toVs(vv)
      vs -= v
      if (vs.isEmpty) removeKey(k)
    }
  }
  def removeKey(k:K):Unit = map -= k
  def removeValue(v:V):Unit = {
    map.foreach { case (k,vv) =>
      if (toVs(vv).contains(v)) remove(k,v)
    }
  }
  def clear = { map.clear }
  def getOrElse(k:K,vv:VV) = map.getOrElse(k,vv)
}

class OneToOneMap[K:ClassTag,V:ClassTag] extends UniMap[K,V,V] with prism.collection.OneToOneMap[K,V] {
  def toVs(vv:VV):Set[V] = Set(vv)
  def toVV(vs:Set[V]):V = vs.head
  override def update(k:K, v:V):Unit = {
    super.update(k,v)
    map += (k -> v)
  }
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

class OneToManyMap[K:ClassTag,V:ClassTag] extends UniMap[K,V,Set[V]] with prism.collection.OneToManyMap[K,V,Set[V]] {
  def toVs(vv:VV):Set[V] = vv
  def toVV(vs:Set[V]):Set[V] = vs
  override def apply(k:K) = map.getOrElse(k, Set())
  override def update(k:K, v:V):Unit = {
    super.update(k,v)
    map += k -> (map.getOrElse(k, Set[V]()) + v)
  }
  def getOrElseUpdate(k:K)(vv: => Set[V]):Set[V] = {
    if (!map.contains(k)) vv.foreach { v => update(k,v) }
    map(k)
  }
  def update(k:K, vv:VV):Unit = vv.foreach(v => update(k,v))
}

trait BiMap[K,V,KK,VV] extends MapLike[K,V,VV] with prism.collection.BiMap[K,V,KK,VV] {
  def fmap:UniMap[K,V,VV]
  def bmap:UniMap[V,K,KK]

  def toKs(kk:KK):Set[K] = bmap.toVs(kk)
  def toKK(ks:Set[K]):KK = bmap.toVV(ks)
  def toVs(vv:VV):Set[V] = fmap.toVs(vv) 
  def toVV(vs:Set[V]):VV = fmap.toVV(vs) 
  def update(k:K, v:V):Unit = {
    fmap.check(k,v)
    bmap.check(v,k)
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

trait ForwardOneToOneMap[K,V,KK] extends BiMap[K,V,KK,V] {
  def fmap:OneToOneMap[K,V]
}

trait ForwardOneToManyMap[K,V,KK] extends BiMap[K,V,KK,Set[V]]{
  def fmap:OneToManyMap[K,V]

  override def apply(k:K) = fmap.getOrElse(k, Set())
  def update(k:K, v:V):Unit
  def update(k:K, vv:Iterable[V]):Unit = vv.foreach(v => update(k,v))
  def ++= (kvv:(K,Iterable[V])):Unit = update(kvv._1, kvv._2)
}

trait BackwardOneToOneMap[K,V,VV] extends BiMap[K,V,K,VV] {
  def bmap:OneToOneMap[V,K]
}

trait BackwardOneToManyMap[K,V,VV] extends BiMap[K,V,Set[K],VV]{
  def bmap:OneToManyMap[V,K]
}

class BiOneToOneMap[K:ClassTag,V:ClassTag] extends ForwardOneToOneMap[K,V,K] with BackwardOneToOneMap[K,V,V] {
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
}

class BiOneToManyMap[K:ClassTag,V:ClassTag] extends ForwardOneToManyMap[K,V,K] with BackwardOneToOneMap[K,V,Set[V]] {
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
}

class BiManyToOneMap[K:ClassTag,V:ClassTag] extends ForwardOneToOneMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,V] {
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
}

class BiManyToManyMap[K:ClassTag,V:ClassTag] extends ForwardOneToManyMap[K,V,Set[K]] with BackwardOneToManyMap[K,V,Set[V]] {
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
}
