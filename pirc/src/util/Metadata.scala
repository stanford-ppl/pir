package pirc.util

import pirc.collection.mutable._
import pirc._

import scala.util.{Try, Success, Failure}

trait Metadata extends { self:Design =>

  val maps = scala.collection.mutable.ListBuffer[MetadataMaps]()

  def reset = {
    info(s"Reseting $name metadata")
    maps.foreach(_.reset)
  }

  def summary(n:Any):List[String] = {
    maps.flatMap { map => 
      Try {
        n.asInstanceOf[map.K]
      } match {
        case Success(n) => Some(map.info(n))
        case Failure(e) => None
      }
    }.toList
  }

  trait MetadataMaps extends MMap { 
    maps += this
    def info(n:K):String = { s"${name}($n)=${get(n)}" }
    def reset = map.clear
  }
}

