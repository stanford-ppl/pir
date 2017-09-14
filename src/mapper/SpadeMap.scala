package pir.mapper
import pir._
import pir.util.maps._
import pir.spade.graph._
import pir.spade.util.typealias._

import scala.reflect.runtime.universe._
import scala.language.existentials

/* FanIn map: a mapping between a PInput and the POutput it connects to */
case class FIMap(map:FIMap.M, pmap:FIMap.IM) extends IBiManyToOneMap {
  type K = FIMap.K
  type V = FIMap.V
  override type M = FIMap.M
  override def check(rec:(K,V)):Unit =  {
    super.check(rec)
    val (i, o) = rec
    assert(i.canConnect(o), s"$i cannot connect to $o but trying map $i to $o in FIMap")
  }
  override def + (rec:(K,V)) = { 
    check(rec); 
    val set:Set[K] = (pmap.getOrElse(rec._2, Set.empty) + rec._1)
    val v:V = rec._2
    val npmap:IM = pmap + ((v, set))
    FIMap(map + rec, npmap)
  }
  def get(k:PGI[_<:PModule]) = { map.get(k).asInstanceOf[Option[PGO[_<:PModule]]] }
}
object FIMap extends IBiManyToOneObj {
  type K = PI[_<:PModule]
  type V = PO[_<:PModule]
  def empty:FIMap = FIMap(Map.empty, Map.empty)
}

case class CFMap(map:CFMap.M) extends IOneToOneMap {
  type K = CFMap.K
  type V = CFMap.V
  override type M = CFMap.M
  override def + (rec:(K,V)) = { super.check(rec); CFMap(map + rec) }
  def apply(n:Configurable) = n.toConfig(map(n))
  def get(n:Configurable) = map.get(n).map{ c => n.toConfig(c) }
}
object CFMap extends IOneToOneObj {
  type K = PNode 
  type V = PConfig
  def empty:CFMap = CFMap(Map.empty)
}
