package prism
package traversal

import prism.node._

import scala.collection.mutable

trait Traversal extends Pass { self:HierarchicalTraversal =>
  override def reset = { super.reset; resetTraversal }
  override def initPass = { 
    super.initPass
    resetTraversal
  }

  def traverseTop:T = {
    traverseScope(top, zero)
  }

  override def runPass = {
    traverseTop
  }
}

