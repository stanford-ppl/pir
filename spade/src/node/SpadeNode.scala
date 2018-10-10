package spade
package node
import param._

abstract class SpadeNode(implicit protected val design:SpadeDesign) extends prism.node.Node[SpadeNode] with SpadeCollector { self =>
  val id = design.nextId

  type N = SpadeNode
  type P = Module
  type A = Pin[_]

  def qindex = {
    import design.spademeta._
    s"${nameOf.get(this).getOrElse(className)}${id}${indexOf.get(this).fold("")(indices => s"[${indices.mkString(",")}]")}"
  }
}

