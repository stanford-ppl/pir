package spade
package param2

import prism._
import prism.graph._

trait Parameter extends ProductNode[Parameter] {
  implicit val Nct = classTag[Parameter]
}
