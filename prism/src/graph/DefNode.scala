package prism
package graph

import implicits._

import scala.collection.mutable

/*
 * DefNode only has a single or less output
 * */
trait DefNode[N<:DefNode[N]] extends Node[N] { self:N =>
  def output:Option[Output] = assertOneOrLess(localOuts, s"DefNode $self's localOuts")
}
