package pir
package pass

import pir.node._
import pir.mapper._

abstract class PIRPass(implicit override val compiler:PIR) extends Pass 
  with prism.traversal.GraphUtil with RuntimeUtil with TypeUtil with MappingUtil with PIRNodeUtil {

  implicit val design:PIRDesign = compiler.design
  lazy val pirmeta = compiler.pirmeta
  lazy val spademeta = compiler.spademeta

  def qdef(n:Any) = n match {
    case n:PIRNode => n.qdef
    case n => s"$n"
  }

  def qtype(n:Any) = n match {
    case n:IR => n.qtype
    case n => s"$n"
  }

  override def quote(n:Any) = n match {
    case n:Iterable[_] => n.map(quote).toString
    case n:Option[_] => n.map(quote).toString
    case n:SNode => n.qindex
    case n => qtype(n)
  }

}
