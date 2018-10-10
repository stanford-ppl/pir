package spade
package param

import prism.node._

abstract class Parameter extends ProductNode[Parameter](None) with ProductAtom[Parameter] with Serializable {
  override val id = this.hashCode & 0xfffffff

  def newIn = ParameterInput(this)
  def newOut = ParameterOutput(this)
}

case class ParameterInput(src:Parameter) extends prism.node.Input[Parameter] {
  type A = Parameter
  override val id = this.hashCode & 0xfffffff
}
case class ParameterOutput(src:Parameter) extends prism.node.Output[Parameter] {
  type A = Parameter
  override val id = this.hashCode & 0xfffffff
}

