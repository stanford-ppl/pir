package spade
package param

import prism.graph._

trait Parameter extends ProductNode[Parameter] {
  override lazy val Nct = classTag[Parameter]
}
object Parameter {
  implicit class ParameterCollector[N<:Parameter](node:N) extends NodeCollector[Parameter, N](node)
}
