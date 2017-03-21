package pir.util.maps
import pir.codegen.{Printer}
import pir.exceptions._

import scala.collection.Map
import scala.collection.Set

trait BiMap extends UniMap {
  type KK
  type IM <: Map[V, KK]
  val pmap:IM
  def printPMap(ks:List[V])(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (pmap.contains(k))
            p.emitln(s"$k <- ${pmap(k)}")
          //if (!pmap.contains(k))
          //  p.emitln(s"$k <- no map")
          //else
          //  p.emitln(s"$k <- ${pmap(k)}")
        }
      }
    }
  }
  def printPMap(ks:List[V], lambda:V=>Unit)(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (pmap.contains(k))
            p.emitBlock(s"$k <- ${pmap(k)}") { lambda(k) }
          //if (!pmap.contains(k))
          //  p.emitln(s"$k <- no map")
          //else {
          //  p.emitBlock(s"$k <- ${pmap(k)}") { lambda(k) }
          //}
        }
      }
    }
  }
}

trait BiOneToOneMap extends OneToOneMap with BiMap {
  type KK = K
  override def check(rec:(K,V)):Unit =  {
    super.check(rec)
    if (pmap.contains(rec._2) && pmap(rec._2)!=rec._1)
      throw PIRException(s"${name} already contains key ${rec._2} -> ${pmap(rec._2)} but try to rebind to ${rec._1}")
  }
}

trait BiOneToManyMap extends OneToManyMap with BiMap {
  type KK = K
  override def check(rec:(K,V)):Unit =  {
    if (pmap.contains(rec._2) && pmap(rec._2)!=rec._1)
      throw PIRException(s"${name} already contains key ${rec._2} -> ${pmap(rec._2)} but try to rebind to ${rec._1}")
  }
}

trait BiManyToOneMap extends OneToOneMap with BiMap {
  type KK <: Set[K]
  override def check(rec:(K,V)):Unit = {
    if (map.contains(rec._1) && map(rec._1) != rec._2)
      throw PIRException(s"${name} already contains key ${rec._1} -> ${map(rec._1)} but try to rebind to ${rec._2}")
  }
}

trait BiManyToManyMap extends OneToManyMap with BiMap {
  type KK <: Set[K]
  val pmap:IM
}
