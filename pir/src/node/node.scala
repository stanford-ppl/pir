package pir

import prism.graph._

package object node {
  implicit class PIREdgeOp(e:Edge) extends EdgeCollector[PIRNode](e)

}
