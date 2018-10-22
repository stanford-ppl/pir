package spade
package node

import spade.param2._

import prism.graph._

abstract class SpadeNode(implicit env:BuildEnvironment) extends Node[SpadeNode] with Product {
  val Nct = classTag[SpadeNode]
  env.initNode(this)
  //def qindex = {
    //import design.spademeta._
    //s"${nameOf.get(this).getOrElse(className)}${id}${indexOf.get(this).fold("")(indices => s"[${indices.mkString(",")}]")}"
  //}

  def within[T](scope: => T)(implicit env:BuildEnvironment):T = {
    env.withParent(this) (scope)
  }

  def params:Option[Parameter] = assertOneOrLess(productIterator.collect { 
    case p:Parameter => p
  }.toList, s"$this's param")
}

object Param {
  def unapply(n:SpadeNode):Option[Parameter] = n.params
}
