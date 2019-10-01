package prism
package graph

trait Schedular extends Traversal {
  type N
  type T = List[N]

  def zero = Nil

  def visitFunc(n:N):List[N]

  override def visitNode(n:N, prev:T):T = super.visitNode(n, prev:+n)

  def scheduleNode(n:N) = {
    resetTraversal
    traverseNode(n, zero)
  }

  def scheduleNodes(ns: Iterable[N]) = {
    resetTraversal
    traverseNodes(ns.toList, zero)
  }

  def scheduleNodesInScope(scope:List[N], ns: Iterable[N]) = {
    resetTraversal
    traverseNodesInScope(scope, ns.toList, zero)
  }

  def scheduleScope(n:N):List[N] = {
    resetTraversal
    this match {
      case self:HierarchicalTraversal => 
        self.traverseScope(n.asInstanceOf[self.N], zero.asInstanceOf[List[self.N]]).asInstanceOf[List[N]]
      case _ => bug(s"cannot scheduleScope(n) on non HierarchicalTraversal $this")
    }
  }

  def scheduleScope(ns:List[N]):List[N] = {
    resetTraversal
    this match {
      case self:TopologicalTraversal => 
        self.traverseScope(ns.asInstanceOf[List[self.N]], zero.asInstanceOf[List[self.N]]).asInstanceOf[List[N]]
      case _ => bug(s"cannot scheduleScope(ns) on non TopologicalTraversal $this")
    }
  }
}

trait TreeSchedular extends Traversal {
  type N
  type T = List[List[N]]

  def zero = Nil

  def visitFunc(n:N):List[N]

  override def visitNode(n:N, prev:T):T = super.visitNode(n, prev :+ visitFunc(n).distinct)

  def scheduleNode(n:N) = {
    resetTraversal
    traverseNode(n, zero)
  }

  def scheduleNodes(ns: Iterable[N]) = {
    resetTraversal
    traverseNodes(ns.toList, zero)
  }

  def scheduleNodesInScope(scope:List[N], ns: Iterable[N]) = {
    resetTraversal
    traverseNodesInScope(scope, ns.toList, zero)
  }

}
