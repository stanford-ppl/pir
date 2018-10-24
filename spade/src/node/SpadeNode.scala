package spade
package node

import spade.param2._

import prism.graph._

abstract class SpadeNode(implicit env:BuildEnvironment) extends EnvNode[SpadeNode] with Product {
  lazy val Nct = classTag[SpadeNode]
  //def qindex = {
    //import design.spademeta._
    //s"${nameOf.get(this).getOrElse(className)}${id}${indexOf.get(this).fold("")(indices => s"[${indices.mkString(",")}]")}"
  //}

  def params:Option[Parameter] = assertOneOrLess(productIterator.collect { 
    case p:Parameter => p
  }.toList, s"$this's param")

  env.initNode(this)
}

object Param {
  def unapply(n:SpadeNode):Option[Parameter] = n.params
}
