package pir.pass

import pir._
import pir.node._

import spade._
import pirc._
import pirc.enums._
import pirc.util._

import prism.traversal._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

abstract class PIRPass(implicit val design:PIR) extends Pass with PIRCollector {

  lazy val pirmeta = design.newTop.metadata

  def qdef(n:Any) = n match {
    case n:IR => s"${n.name.getOrElse(n.toString)} = ${n.productName}"
    case n => n.toString
  }

  def qtype(n:Any) = n match {
    case n:IR => n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")
    case n => n.toString
  }

}
