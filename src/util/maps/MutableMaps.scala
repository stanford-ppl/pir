package pir.util.maps
import pir.codegen.{Printer}
import pir.exceptions._

import scala.collection.mutable.Map
import scala.collection.mutable.Set
trait MOneToOneMap extends OneToOneMap {
  override type M = Map[K, VV]
  val map:Map[K, VV] = Map.empty
  def update(n:K, v:V):Unit = { super.check((n,v)); map += (n -> v) }
}

trait MBiOneToOneMap extends MOneToOneMap with BiOneToOneMap {
  override type IM = Map[V, KK]
  val pmap:IM = Map.empty
  override def update(n:K, v:V):Unit = { super.check((n,v)); super.update(n, v); pmap += (v -> n) }
}

trait MOneToManyMap extends OneToManyMap {
  override type VV = Set[V]
  override type M = Map[K, VV]
  val map:Map[K, VV] = Map.empty
  def update(n:K, v:V):Unit = map.getOrElseUpdate(n, Set[V]()) += v
}

trait MBiOneToManyMap extends MOneToManyMap with BiOneToManyMap {
  override type IM = Map[V, KK]
  val pmap:IM = Map.empty
  override def update(n:K, v:V):Unit = { super.check((n,v)); super.update(n,v); pmap += (v -> n) } 
}

trait MBiManyToOne extends MOneToOneMap with BiManyToOneMap {
  override type KK = Set[K]
  override type IM = Map[V, KK]
  val pmap:IM = Map.empty
  override def update(n:K, v:V):Unit = { super.check((n,v)); super.update(n,v); pmap.getOrElseUpdate(v, Set[K]()) += n } 
}

trait MBiManyToMany extends MOneToManyMap with BiManyToManyMap {
  override type KK = Set[K]
  override type IM = Map[V, KK]
  val pmap:IM = Map.empty
  override def update(n:K, v:V):Unit = { super.update(n,v); pmap.getOrElseUpdate(v, Set[K]()) += n } 
}
