package spade
package node

import prism.graph._

trait SpadeNode extends Node[SpadeNode] {
  val Nct = classTag[SpadeNode]
  //def qindex = {
    //import design.spademeta._
    //s"${nameOf.get(this).getOrElse(className)}${id}${indexOf.get(this).fold("")(indices => s"[${indices.mkString(",")}]")}"
  //}

  def within(scope: => Any)(implicit env:BuildEnvironment) = {
    env.withParent(this) (scope)
    this
  }
}
