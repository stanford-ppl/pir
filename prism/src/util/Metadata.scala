package prism
package util

import prism.collection.mutable._

trait Metadata extends Serializable {

  lazy val maps = scala.collection.mutable.ListBuffer[MetadataMap]()

  def reset = maps.foreach(_.clear)

  def summerize(n:Any, maps:MetadataMap*):List[String] = { maps.flatMap { map => map.info(n) }.toList }

  def summary(n:Any):List[String] = summerize(n, maps.toSeq:_*)

  def mirrorOnly(orig:Any, clone:Any, logger:Option[Logging]=None, includes:List[MetadataMap]=Nil) = {
    if (orig != clone) includes.foreach { map => map.mirror(orig, clone, logger) }
  }

  def mirror(orig:Any, clone:Any, logger:Option[Logging]=None) = mirrorOnly(orig, clone, logger, maps.toList)

  def mirrorExcept(orig:Any, clone:Any, logger:Option[Logging]=None, excludes:List[MetadataMap]=Nil) = {
    val includes = (maps.toList diff excludes)
    mirrorOnly(orig, clone, logger, includes)
  }

  def removeAll(node:Any) = maps.foreach { map => map.removeAll(node) }

  trait MetadataMap { 
    maps += this
    type K
    type V
    implicit val kct:ClassTag[K]
    implicit val vct:ClassTag[V]
    type VV

    def asK(x:Any):Option[K]
    def toVs(vv:VV):scala.collection.Set[V]
  
    def clear:Unit
    def get(k:K):Option[VV]
    def contains(k:K):Boolean
    def removeAll(a:Any):Unit
    def update(k:K, v:V):Unit
  
    var nameOpt:Option[String] = None
    def setName(s:String) = nameOpt = Some(s)
    def name = nameOpt.getOrElse(super.toString)
    override def toString = name

    def isDefinedAt(k:K) = contains(k)
    // Default just copy over
    def mirror(orig:Any, clone:Any, logger:Option[Logging]=None):Unit = {
      (orig, clone) match {
        case (orig:K, clone:K) =>
          get(orig).foreach { vv => 
            dbg(logger, s"$this($clone)=$name($orig)=$vv")
            toVs(vv).foreach { v => update(clone, v) }
          }
        case _ =>
      }
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
