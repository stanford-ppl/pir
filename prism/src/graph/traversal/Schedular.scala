package prism
package graph

trait Schedular extends Traversal {
  type N
  type T = Stream[N]

  def zero = Stream()

  def visitFunc(n:N):Stream[N]

  override def visitNode(n:N, prev:T):T = super.visitNode(n, prev:+n)

  def scheduleNode(n:N) = {
    resetTraversal
    traverseNode(n, zero)
  }

  def scheduleNodes(ns: Iterable[N]) = {
    resetTraversal
    traverseNodes(ns.toStream, zero)
  }

  def scheduleNodesInScope(scope:Stream[N], ns: Iterable[N]) = {
    resetTraversal
    traverseNodesInScope(scope, ns.toStream, zero)
  }

  def scheduleScope(n:N):Stream[N] = {
    resetTraversal
    this match {
      case self:HierarchicalTraversal => 
        self.traverseScope(n.asInstanceOf[self.N], zero.asInstanceOf[Stream[self.N]]).asInstanceOf[Stream[N]]
      case _ => bug(s"cannot scheduleScope(n) on non HierarchicalTraversal $this")
    }
  }

  def scheduleScope(ns:Stream[N]):Stream[N] = {
    resetTraversal
    this match {
      case self:TopologicalTraversal => 
        self.traverseScope(ns.asInstanceOf[Stream[self.N]], zero.asInstanceOf[Stream[self.N]]).asInstanceOf[Stream[N]]
      case _ => bug(s"cannot scheduleScope(ns) on non TopologicalTraversal $this")
    }
  }
}
