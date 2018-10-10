package spade
package node
import param._

case class SpadeDesign(param:DesignParam) extends prism.node.Design {
  val spademeta = new SpadeMetadata
  val top:Top = Factory.create(param.topParam)
}
