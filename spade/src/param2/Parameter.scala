package spade
package param

import prism.graph._

trait Parameter extends ProductNode[Parameter] {
  override lazy val Nct = classTag[Parameter]
}
