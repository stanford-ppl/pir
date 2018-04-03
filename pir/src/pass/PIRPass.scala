package pir.pass

import pir.node._

abstract class PIRPass(implicit override val compiler:PIR) extends Pass with prism.traversal.GraphUtil {

  implicit val design:PIRDesign = compiler.design
  lazy val pirmeta = compiler.pirmeta
  lazy val spademeta = compiler.spademeta

  def qdef(n:Any) = n match {
    case n:PIRNode => n.qdef
    case n => n.toString
  }

  def qtype(n:Any) = n match {
    case n:IR => n.qtype
    case n => s"$n"
  }

  override def quote(n:Any) = n match {
    case n:SNode => n.qindex
    case n => qtype(n)
  }

}
