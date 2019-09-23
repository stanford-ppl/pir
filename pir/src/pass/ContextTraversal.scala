package pir
package pass

import pir.node._
import prism.graph._

trait ContextTraversal extends PIRTraversal with TopologicalTraversal {
  override def visitIn(n:N):Stream[N] = visitGlobalIn(n).flatMap {
    case x:GlobalIO => visitIn(x)
    case x:Context => Stream(x)
    case x:Memory => Stream()
    case x => x.collectUp[Context]()
  }.distinct
  override def visitOut(n:N):Stream[N] = visitGlobalOut(n).flatMap {
    case x:GlobalIO => visitOut(x)
    case x:Context => Stream(x)
    case x:Memory => Stream()
    case x => x.collectUp[Context]()
  }.distinct
  override def runPass = {
    val contexts = top.collectDown[Context]()
    traverseScope(contexts, zero)
  }
}

