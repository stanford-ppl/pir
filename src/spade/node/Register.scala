package spade.node

import spade._
import spade.util._

import pirc.enums._

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

