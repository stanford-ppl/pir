package pir
package pass

import pir.node._

trait ControllerTraversal extends prism.traversal.Traversal { self:prism.traversal.HierarchicalTraversal =>
  type N = Controller

  def top = compiler.asInstanceOf[PIR].top.topController

}

trait ControllerSiblingFirstTraversal extends ControllerTraversal with prism.traversal.SiblingFirstTraversal
trait ControllerChildFirstTraversal extends ControllerTraversal with prism.traversal.ChildFirstTraversal
