package spade
package param2

import prism.graph._

trait Parameter extends ProductNode[Parameter] {
  override lazy val Nct = classTag[Parameter]
}
