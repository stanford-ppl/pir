package pir.pass

import pir._
import pir.util.typealias._

import spade._

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
