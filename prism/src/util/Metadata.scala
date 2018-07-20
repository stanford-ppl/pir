package prism
package util

import prism.collection.mutable._

trait Metadata extends Serializable {

  lazy val maps = scala.collection.mutable.ListBuffer[MetadataMap]()

  def reset = maps.foreach(_.clear)

  def summerize(n:Any, maps:MetadataMap*):List[String] = { maps.flatMap { map => map.info(n) }.toList }

  def summary(n:Any):List[String] = summerize(n, maps.toSeq:_*)

  def mirror(from:Any, to:Any, logger:Option[Logging]=None) = {
    if (from != to) maps.foreach { map => map.mirror(from, to, logger) }
  }

  def migrate(from:Any, to:Any, logger:Option[Logging]=None) = {
    if (from != to) maps.foreach { map => map.migrate(from, to, logger) }
  }

  def removeAll(node:Any) = maps.foreach { map => map.removeAll(node) }

  trait MetadataMap { 
    maps += this
    type K
    type V
    implicit val kct:ClassTag[K]
    implicit val vct:ClassTag[V]
    type VV
    type KK

    def asK(x:Any):Option[K]
    def toVs(vv:VV):Set[V]
    def toKs(kk:KK):Set[K]
  
    def clear:Unit
    def get(k:K):Option[VV]
    def contains(k:K):Boolean
    def removeAll(a:Any):Unit
    def update(k:K, v:V):Unit
    def remove(k:K, v:V):Unit

    def getV(v:V):Option[KK]
  
    var nameOpt:Option[String] = None
    def setName(s:String) = nameOpt = Some(s)
    def name = nameOpt.getOrElse(super.toString)
    override def toString = name

    def isDefinedAt(k:K) = contains(k)
    // Default just copy over
    def mirror(from:Any, to:Any, logger:Option[Logging]=None):Unit = {
      (from, to) match {
        case (from:K, to:K) => get(from).foreach { vv => toVs(vv).foreach { v => mirrorKey(from, to, v, logger) } }
        case _ =>
      }
      (from, to) match {
        case (from:V, to:V) => getV(from).foreach { kk => toKs(kk).foreach { k => mirrorValue(from, to, k, logger) } }
        case _ =>
      }
    }
    def mirrorKey(from:K, to:K, v:V, logger:Option[Logging]) = {
      dbg(logger, s"$name.mirrorKey $from -> $v => $to -> $v")
      update(to, v)
    }
    def mirrorValue(from:V, to:V, k:K, logger:Option[Logging]) = {
      dbg(logger, s"$name.mirrorValue $k -> $from => $k -> $to")
      update(k, to)
    }
    def migrate(from:Any, to:Any, logger:Option[Logging]=None):Unit = {
      (from, to) match {
        case (from:K, to:K) => get(from).foreach { vv => toVs(vv).foreach { v => migrateKey(from, to, v, logger) } }
        case _ =>
      }
      (from, to) match {
        case (from:V, to:V) => getV(from).foreach { kk => toKs(kk).foreach { k => migrateValue(from, to, k, logger) } }
        case _ =>
      }
    }
    def migrateKey(from:K, to:K, v:V, logger:Option[Logging]) = {
      dbg(logger, s"$name.migrateKey $from -> $v => $to -> $v")
      update(to, v)
    }
    def migrateValue(from:V, to:V, k:K, logger:Option[Logging]) = {
      dbg(logger, s"$name.migrateValue $k -> $from => $k -> $to")
      update(k, to)
    }
    def info(a:Any):Option[String] = { 
      a match {
        case k:K => get(k).map { vv => s"${name}($k)=$vv" }
        case v:V =>
          this match {
            case map:BiMap[_,_] => map.asInstanceOf[BiMap[K,V]].bmap.get(v).map( kk => s"${name}.bmap($v) = $kk" )
            case _ => None
          }
        case _ => None
      }
    }
  }
}
