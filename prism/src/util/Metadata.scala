package prism.util

import prism._
import prism.util._

import prism.collection.mutable._
import scala.util.{Try, Success, Failure}

trait Metadata extends Serializable {

  lazy val maps = getDeclaredObjects(this).collect { case o:MetadataMap => o }

  def reset = maps.foreach(_.clear)

  def summerize(n:Any, maps:MetadataMap*):List[String] = { maps.flatMap { map => map.info(n) }.toList }

  def summary(n:Any):List[String] = summerize(n, maps.toSeq:_*)

  def mirrorOnly(orig:Any, clone:Any, includes:MetadataMap*) = {
    if (orig != clone) includes.foreach { map => 
      (map.asK(orig), map.asK(clone)) match {
        case (Some(orig), Some(clone)) => map.mirror(orig, clone)
        case _ =>
      }
    }
  }

  def mirror(orig:Any, clone:Any) = mirrorOnly(orig, clone, maps.toSeq:_*)

  def mirrorExcept(orig:Any, clone:Any, excludes:MetadataMap*) = {
    val includes = (maps.toList diff excludes.toList)
    mirrorOnly(orig, clone, includes:_*)
  }

  def removeAll(node:Any) = maps.foreach { map => map.removeAll(node) }

  trait MetadataMap { 
    type K
    type V
    type VV
    def asK(k:Any):Option[K]
    def asV(v:Any):Option[V]
    def asVV(vv:Any):Option[VV]
  
    def name:String
    def clear:Unit
    def get(k:K):Option[VV]
    def contains(k:K):Boolean
    def removeAll(a:Any):Unit
    def update(k:K,vv:VV):Unit
  
    def isDefinedAt(k:K) = contains(k)
    // Default just copy over
    def mirror(orig:K, clone:K):Unit = {
      get(orig).foreach { vv => update(clone, vv) }
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
