package spade
package node

import spade.param._

import prism.graph._

abstract class SpadeNode(implicit env:BuildEnvironment) extends EnvNode[SpadeNode] with Product {
  lazy val Nct = classTag[SpadeNode]

  def params:Option[Parameter] = assertOneOrLess(productIterator.collect { 
    case p:Parameter => p
  }.toList, s"$this's param")

  env.initNode(this)
}
object SpadeNode {
  implicit class SpadeNodeCollector[N<:SpadeNode](node:N) extends NodeCollector[SpadeNode, N](node)
}

object Param {
  def unapply(n:SpadeNode):Option[Parameter] = n.params
}
