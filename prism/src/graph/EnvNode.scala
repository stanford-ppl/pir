package prism
package graph

import scala.collection.mutable

abstract class EnvNode[N](implicit env:BuildEnvironment) extends Node[N] { self:N =>
  env.initNode(this)
}
