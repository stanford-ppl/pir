package prism.util

import prism.collection.mutable._

trait Metadata extends Serializable {

  lazy val maps = scala.collection.mutable.ListBuffer[MetadataMap]()

  def reset = maps.foreach(_.clear)

  def summerize(n:Any, maps:MetadataMap*):List[String] = { maps.flatMap { map => map.info(n) }.toList }

  def summary(n:Any):List[String] = summerize(n, maps.toSeq:_*)

  def mirrorOnly(orig:Any, clone:Any, logger:Option[Logging]=None, includes:List[MetadataMap]=Nil) = {
    if (orig != clone) includes.foreach { map => 
      (map.asK(orig), map.asK(clone)) match {
        case (Some(orig), Some(clone)) => map.mirror(orig, clone, logger)
        case _ =>
      }
    }
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
    type VV
    def asK(k:Any):Option[K]
    def asV(v:Any):Option[V]
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
    def mirror(orig:K, clone:K, logger:Option[Logging]=None):Unit = {
      logger.foreach { _.dbg(s"$this($clone)=$name($orig)=${get(orig)}") }
      get(orig).foreach { vv => 
        toVs(vv).foreach { v => update(clone, v) }
      }
    }
    def info(a:Any):Option[String] = { 
      asK(a) match {
        case Some(k) => get(k).map { vv => s"${name}($k)=$vv" }
        case None =>
          this match {
            case map:BiMap[_,_,_,_] => 
              map.asV(a).flatMap { v => 
                map.bmap.get(v).map( kk => s"${name}.bmap($v) = $kk" )
              }
            case _ => None
          }
      }
    }
  }
}
