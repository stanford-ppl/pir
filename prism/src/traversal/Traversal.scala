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

  def traverseTop:T = {
    this match {
      case self:HierarchicalTraversal => self.traverseScope(self.top, self.zero).asInstanceOf[T]
      case self => throw PIRException(s"traverseTop is not defined for $this")
    }
  }

  override def runPass = {
    traverseTop
  }
}

