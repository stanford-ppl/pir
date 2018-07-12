package prism
package collection.mutable

import scala.collection.mutable.Set
import scala.collection.mutable.Map
trait MapLike[K,V] extends prism.collection.MapLike[K,V] {
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
    a match {
      case a:K => removeKey(a)
      case a:V => removeValue(a)
      case _ =>
    }
  }
}
trait UniMap[K,V] extends prism.collection.UniMap[K,V] with MapLike[K,V] {
  override type UM = Map[K,VV]
  val map:UM = Map.empty 
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

class OneToOneMap[K:ClassTag,V:ClassTag] extends prism.collection.OneToOneMap[K,V] with UniMap[K,V] {
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

class OneToManyMap[K:ClassTag,V:ClassTag] extends prism.collection.OneToManyMap[K,V] with UniMap[K,V] {
  type VV = Set[V]
  val vvct = classTag[VV]
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
  def update(k:K, vv:Iterable[V]):Unit = vv.foreach(v => update(k,v))
}

trait BiMap[K,V] extends prism.collection.BiMap[K,V] with MapLike[K,V] {
  override type UM = Map[K,VV]
  val fmap:UniMap[K,V]
  val bmap:UniMap[V,K]

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

class BiOneToOneMap[K:ClassTag,V:ClassTag] extends BiMap[K,V] {
  override type KK = K
  override type VV = V
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
}

class BiOneToManyMap[K:ClassTag,V:ClassTag] extends BiMap[K,V] {
  override type KK = K
  override type VV = Set[V]
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToOneMap[V,K]()
  override def apply(k:K):VV = fmap.getOrElse(k, Set())
  def update(k:K, vv:Iterable[V]):Unit = vv.foreach(v => update(k,v))
  def ++= (kvv:(K,Iterable[V])):Unit = update(kvv._1, kvv._2)
}

class BiManyToOneMap[K:ClassTag,V:ClassTag] extends BiMap[K,V] {
  override type KK = Set[K]
  override type VV = V
  val fmap = new OneToOneMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
}

class BiManyToManyMap[K:ClassTag,V:ClassTag] extends BiMap[K,V] {
  override type KK = Set[K]
  override type VV = Set[V]
  val fmap = new OneToManyMap[K,V]()
  val bmap = new OneToManyMap[V,K]()
  override def apply(k:K):VV = fmap.getOrElse(k, Set())
  def update(k:K, vv:Iterable[V]):Unit = vv.foreach(v => update(k,v))
  def ++= (kvv:(K,Iterable[V])):Unit = update(kvv._1, kvv._2)
}
