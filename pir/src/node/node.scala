package pir

import prism.graph._

package object node {
  implicit class PIREdgeOp[A<:Edge[PIRNode,A,B],B<:Edge[PIRNode,B,A]](e:Edge[PIRNode,A,B]) extends EdgeCollector[PIRNode,A,B](e)

  implicit class PIRInputOp(e:Input[PIRNode]) {
    def from:Option[Output[PIRNode]] = e.singleConnected
  }
}
