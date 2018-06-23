package prism
package traversal

trait GraphSchedular extends GraphTraversal { self =>
  type N
  type T = List[N]

  def zero = Nil

  def visitFunc(n:N):List[N]

  override def visitNode(n:N, prev:T):T = super.visitNode(n, prev:+n)

  def scheduleNode(n:N) = {
    resetTraversal
    traverseNode(n, zero)
  }

}

trait ScopeSchedular extends GraphSchedular { self:HierarchicalTraversal => 

  def scheduleScope(n:N) = {
    resetTraversal
    traverseScope(n, zero)
  }
}
