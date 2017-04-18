package pir.pass
import pir.graph._
import pir.Design
import pir.Config
import pir.util._
import pir.util.misc._
import pir.plasticine.util.SpadeMetadata
import pir.plasticine.main._
import pir.util.typealias._
import pir.util.PIRMetadata

import scala.collection.mutable.Set

abstract class Pass(implicit val design:Design) {
  implicit lazy val spade:Spade = design.arch
  lazy val spademeta: SpadeMetadata = design.arch
  lazy val pirmeta:PIRMetadata = design

  var isInit = false
  var hasRun = false
  def shouldRun:Boolean
  lazy val name = this.getClass.getSimpleName

  def reset {
    isInit = false
    hasRun = false
  }
  
  def run = {
    initPass
    isInit = true
    traverse
    finPass
    hasRun = true
  }

  def traverse:Unit

  def initPass:Unit = {
    startInfo(s"Begin $name ...")
  }

  def finPass:Unit = {
    endInfo(s"Finishing $name ...")
  }

  def quote(n:Any):String = n match {
    case n:Node => pir.util.quote(n) 
    case n:PNode => pir.plasticine.util.quote(n)
  }
}
