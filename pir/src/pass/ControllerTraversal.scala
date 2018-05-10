package pir
package pass

import pir.node._
trait ControllerTraversal extends prism.traversal.SiblingFirstTraversal {
  type N = Controller
}

