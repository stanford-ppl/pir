package spade
package param3

import prism._
import prism.graph._
import scalapb.json4s.JsonFormat

trait Parameter extends ProductNode[Parameter] {
  implicit val Nct = classTag[Parameter]
}

trait ParamImplicit {
  //TODO move to message helper
  implicit class ParamHelper(p:scalapb.GeneratedMessage) {
    def toJsonString = JsonFormat.toJson(p)
  }
}
