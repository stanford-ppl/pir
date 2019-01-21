package prism
package graph

import implicits._

import scala.collection.mutable

/*
 * DefNode only has a single or less output
 * */
trait DefNode[N<:Node[N]] extends Node[N] { self:N =>
  def output:Option[Output[N]] = assertOneOrLess(localOuts, s"DefNode $self's localOuts")
}
