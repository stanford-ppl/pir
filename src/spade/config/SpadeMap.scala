package spade.config

import spade.node._
import spade.util.typealias._

import pirc.collection.immutable._

import scala.reflect.runtime.universe._
import scala.language.existentials

trait SpadeMap {
  def fimap:FIMap
  def cfmap:CFMap
}

/* FanIn map: a mapping between a PInput and the POutput it connects to */
case class FIMap(map:FIMap.M, imap:FIMap.IM) extends IBiManyToOneMap {
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
    val set:Set[K] = (imap.getOrElse(rec._2, Set.empty) + rec._1)
    val v:V = rec._2
    val npmap:IM = imap + ((v, set))
    FIMap(map + rec, npmap)
  }
  def get(k:PGI[_<:PModule]) = { map.get(k).asInstanceOf[Option[PGO[_<:PModule]]] }

  def apply(v:V):KK = imap(v)
  def get(v:V):Option[KK]         = imap.get(v)
  def contains(v:V) = imap.contains(v)
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

  override def apply(n:Configurable) = n.toConfig(map(n))
  override def get(n:Configurable) = map.get(n).map{ c => n.toConfig(c) }

  def isMapped(n:K) = map.contains(n)
}
object CFMap extends IOneToOneObj {
  type K = Configurable
  type V = Configuration
  def empty:CFMap = CFMap(Map.empty)
}
