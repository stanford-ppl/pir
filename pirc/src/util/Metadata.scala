package pirc.util

import pirc.collection.mutable._
import pirc._

import scala.util.{Try, Success, Failure}

trait Metadata extends { self:Design =>

  val maps = scala.collection.mutable.ListBuffer[MetadataMaps]()

  def reset = {
    maps.foreach(_.reset)
  }

  def summerize(n:Any, maps:MetadataMaps*):List[String] = {
    maps.flatMap { map => 
      Try {
        n.asInstanceOf[map.K]
      } match {
        case Success(n) => map.get(n).map { v => s"${map.name}=$v" }
        case Failure(e) => None
      }
    }.toList
  }

  def summary(n:Any):List[String] = summerize(n, maps.toSeq:_*)

  trait MetadataMaps extends MMap { 
    maps += this
    def info(n:K):String = { s"${name}($n)=${get(n)}" }
    def reset = map.clear
  }
}

