package prism
package traversal

import prism.node._

import scala.collection.mutable

trait Traversal extends Pass with GraphTraversal {
  override def reset = { super.reset; resetTraversal }
  override def initPass = { 
    super.initPass
    resetTraversal
  }

  override def runPass = {
    this match {
      case self:HierarchicalTraversal => self.traverseTop
      case self => throw PIRException(s"traverseTop is not defined for $this")
    }
  }
}

