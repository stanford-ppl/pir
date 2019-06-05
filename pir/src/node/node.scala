package pir

import prism.graph._

package object node {
  implicit class PIREdgeOp(e:Edge[PIRNode,_,_]) extends EdgeCollector[PIRNode](e)

  implicit class PIRInputOp(e:Input[PIRNode]) {
    def from:Option[Output[PIRNode]] = e.singleConnected
  }
}
