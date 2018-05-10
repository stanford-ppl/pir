package prism
package traversal

trait GraphSchedular extends GraphTraversal { self =>
  type N
  type T = List[N]

  def visitFunc(n:N):List[N]

  override def visitNode(n:N, prev:T):T = super.visitNode(n, prev:+n)

  def schedule(n:N) = {
    resetTraversal
    traverseNode(n, Nil)
  }

  final def schedule(ns: => List[N]) = {
    resetTraversal
    traverse(ns, Nil)
  }
}
