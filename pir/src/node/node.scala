package pir

import prism.graph._

package object node {
  implicit class PIREdgeOp(e:Edge[PIRNode]) extends EdgeCollector[PIRNode](e)
}
