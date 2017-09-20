package pir.pass
import pir.node._
import pir.PIR
import pir.Config
import pir.util._
import pirc.util._
import spade.util.SpadeMetadata
import spade._
import pir.util.typealias._
import pir.util.PIRMetadata

import scala.collection.mutable

abstract class Pass(implicit val design:PIR) extends pirc.pass.Pass {

  implicit lazy val arch:Spade = design.arch
  lazy val spademeta: SpadeMetadata = design.arch
  lazy val pirmeta:PIRMetadata = design

  def quote(n:Any):String = n match {
    case n:Node => pir.util.quote(n) 
    case n:PNode => spade.util.quote(n)
  }

}
