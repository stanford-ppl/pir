package pir.util.maps
import pir.codegen.{Printer}
import pir.exceptions._

import scala.collection.Map
import scala.collection.Set

trait UniMap {
  type K
  type VV
  type V
  type M <: Map[K, VV]

  val map:M
  val name:String = this.getClass().getSimpleName() 
  def apply(n:K):VV = { val m = map; m(n) }
  def get(n:K):Option[VV] =  { val m = map; m.get(n) }
  def contains(k:K) = map.contains(k)
  def quote(n:Any) = n.toString 
  def keys = map.keys
  def values = map.values
  def check(rec:(K,V)):Unit = {}
  def isMapped(v:V):Boolean

  def printMap(quote:Any => String)(implicit p:Printer):Unit = {
    if (map.size!=0) {
      p.emitBlock(name) {
        map.foreach{ case (k,v) =>
          p.emitln(s"${quote(k)} -> ${quote(v)}")
        }
      }
    }
  }
  def printMap(quote:Any => String, ks:List[K])(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (!map.contains(k))
            p.emitln(s"${quote(k)} -> failed")
          else
            p.emitln(s"${quote(k)} -> ${quote(map(k))}")
        }
      }
    }
  }
  def printMap(quote:Any => String, ks:List[K], lambda:K=>Unit)(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (!map.contains(k))
            p.emitln(s"${quote(k)} -> failed")
          else {
            //p.emitln(s"$k -> ${map(k)}")
            p.emitBlock(s"${quote(k)} -> ${quote(map(k))}") { lambda(k) }
          }
        }
      }
    }
  }
}

trait OneToOneMap extends UniMap {
  type VV = V
  def isMapped(v:V) = map.values.toList.contains(v)
  override def check(rec:(K,V)):Unit =  {
    if (map.contains(rec._1) && map(rec._1)!=rec._2)
      throw PIRException(s"${name} already contains key ${rec._1} -> ${map(rec._1)} but try to rebind to ${rec._2}")
  }
}

trait OneToManyMap extends UniMap {
  type VV <: Set[V]
  def isMapped(v:V) = map.values.toList.flatten.contains(v)
}
