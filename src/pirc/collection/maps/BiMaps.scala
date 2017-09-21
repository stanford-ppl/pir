package pirc.collection

import pirc._

import scala.collection.Map
import scala.collection.Set

trait BiMap extends UniMap {
  type KK
  type IM <: Map[V, KK]
  protected def imap:IM
  def printPMap(ks:List[V])(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (imap.contains(k))
            p.emitln(s"$k <- ${imap(k)}")
          //if (!imap.contains(k))
          //  p.emitln(s"$k <- no map")
          //else
          //  p.emitln(s"$k <- ${imap(k)}")
        }
      }
    }
  }
  def printPMap(ks:List[V], lambda:V=>Unit)(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (imap.contains(k))
            p.emitBlock(s"$k <- ${imap(k)}") { lambda(k) }
          //if (!imap.contains(k))
          //  p.emitln(s"$k <- no map")
          //else {
          //  p.emitBlock(s"$k <- ${imap(k)}") { lambda(k) }
          //}
        }
      }
    }
  }
  override def isMapped(v:V) = imap.contains(v)

  def icontains(v:V) = imap.contains(v)
  def iget(v:V) = imap.get(v)
}

trait BiOneToOneMap extends OneToOneMap with BiMap {
  type KK = K
  override def check(rec:(K,V)):Unit =  {
    super.check(rec)
    if (imap.contains(rec._2) && imap(rec._2)!=rec._1)
      throw PIRException(s"${name} already contains key ${rec._2} -> ${imap(rec._2)} but try to rebind to ${rec._1}")
  }
}

trait BiOneToManyMap extends OneToManyMap with BiMap {
  type KK = K
  override def check(rec:(K,V)):Unit =  {
    if (imap.contains(rec._2) && imap(rec._2)!=rec._1)
      throw PIRException(s"${name} already contains key ${rec._2} -> ${imap(rec._2)} but try to rebind to ${rec._1}")
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
  val imap:IM
}
