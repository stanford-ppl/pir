package pirc.collection.mutable

import pirc._
import pirc.collection._

import scala.collection.mutable.Map
import scala.collection.mutable.Set

trait MMap extends UniMap {
  override type M = Map[K,VV]
  def clear = { map.clear }
  def update(k:K, v:V):Unit
  def getOrElseUpdate(k:K)(v: => VV):VV
  def transform(f: (K, VV) ⇒ VV): M = map.transform(f)
  def filterNot(p: ((K, VV)) ⇒ Boolean) = map.filterNot(p)
  def retain(p: (K, VV) ⇒ Boolean): M = map.retain(p)
}

trait MBiMap extends BiMap with MMap {
  override type IM = Map[V, KK]
  override def clear = { super.clear; imap.clear }
}

trait MOneToOneMap extends OneToOneMap with MMap {
  override type M = Map[K, VV]
  val map:Map[K, VV] = Map.empty
  def update(k:K, v:V):Unit = { check((k,v)); map += (k -> v) }
  def getOrElseUpdate(k:K)(v: => VV):VV = {
    if (!map.contains(k)) update(k,v) 
    map(k)
  }
}

trait MBiOneToOneMap extends MOneToOneMap with BiOneToOneMap with MBiMap {
  override type IM = Map[V, KK]
  val imap:IM = Map.empty
  override def update(k:K, v:V):Unit = { check((k,v)); super.update(k, v); imap += (v -> k) }
}

trait MOneToManyMap extends OneToManyMap with MMap {
  override type VV = Set[V]
  override type M = Map[K, VV]
  val map:Map[K, VV] = Map.empty
  def update(k:K, v:V):Unit = map.getOrElseUpdate(k, Set[V]()) += v
  def getOrElseUpdate(k:K)(v: => VV):VV = {
    if (!map.contains(k)) v.foreach { v => update(k,v) }
    map(k)
  }
  def update(k:K, vv:VV):Unit = map += k -> vv
}

trait MBiOneToManyMap extends MOneToManyMap with BiOneToManyMap with MBiMap {
  override type IM = Map[V, KK]
  val imap:IM = Map.empty
  override def update(k:K, v:V):Unit = { check((k,v)); super.update(k,v); imap += (v -> k) } 
  override def update(k:K, vv:VV):Unit = {
    if (map.contains(k)) {
      map(k).foreach { v => imap.remove(v) }
      map.remove(k)
    }
    vv.foreach { v => update(k, v) }
  }
}

trait MBiManyToOneMap extends MOneToOneMap with BiManyToOneMap with MMap {
  override type KK = Set[K]
  override type IM = Map[V, KK]
  val imap:IM = Map.empty
  override def update(k:K, v:V):Unit = { check((k,v)); super.update(k,v); imap.getOrElseUpdate(v, Set[K]()) += k } 
}

trait MBiManyToManyMap extends MOneToManyMap with BiManyToManyMap with MBiMap {
  override type KK = Set[K]
  override type IM = Map[V, KK]
  val imap:IM = Map.empty
  override def update(k:K, v:V):Unit = { super.update(k,v); imap.getOrElseUpdate(v, Set[K]()) += k } 
  override def update(k:K, vv:Set[V]) = {
    if (map.contains(k)) {
      map(k).foreach { v => imap(v) -= k }
      map.remove(k)
    }
    vv.foreach { v => update(k, v) }
  }
}
