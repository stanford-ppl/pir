package pir.spade.graph

import pir.graph._
import pir.util.enums._
import pir.spade.main._
import pir.spade.util._

import scala.language.reflectiveCalls
import scala.collection.mutable.Map
import scala.collection.mutable.Set

/* Logical register (1 row of pipeline registers for all stages) */
case class ArchReg()(implicit spade:Spade) extends Node {
  import spademeta._
  override val typeStr = "reg"
  val _colors = Set[RegColor]()
  def colors = _colors.toList
  def color(c:RegColor) = _colors += c
  def is(c:RegColor) = _colors.contains(c)
}

