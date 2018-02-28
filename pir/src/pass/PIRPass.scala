package pir.pass

import pir._
import pir.node._

import spade._
import prism._
import prism.enums._
import prism.util._

import prism.traversal._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

abstract class PIRPass(implicit val compiler:PIR) extends Pass with PIRCollector {

  implicit val design:PIRDesign = compiler.top
  lazy val pirmeta = design.pirmeta

  def qdef(n:Any) = n match {
    case n:PIRNode => s"${n.name.getOrElse(n.toString)} = ${n.productName}"
    case n => n.toString
  }

  def qtype(n:Any) = n match {
    case n:IR => n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")
    case n => s"$n"
  }

}
