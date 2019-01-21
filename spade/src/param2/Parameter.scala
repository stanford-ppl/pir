package spade
package param

import prism.graph._

trait Parameter extends ProductNode[Parameter] {
  override lazy val Nct = classTag[Parameter]
}
object Parameter {
  implicit class ParameterCollector(node:Parameter) extends NodeCollector[Parameter](node)
}
