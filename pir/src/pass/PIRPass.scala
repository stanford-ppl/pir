package pir.pass

import pir._
import pir.node._

import spade._
import prism._
import prism.enums._
import prism.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max

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
    case n:SNode => n.qindex(compiler.arch.design)
    case n => qtype(n)
  }

}
