package pir.pass

import pir.node._
import pirc._
import prism.traversal._

trait ControllerTraversal extends GraphTraversal with SiblingFirstTraversal with GraphUtil {
  type N = Controller
}

