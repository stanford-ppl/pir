package pir
package pass

import pir.node._
import prism.graph._

trait ContextTraversal extends PIRTraversal with TopologicalTraversal {
  override def visitIn(n:N):List[N] = visitGlobalIn(n).flatMap {
    case x:GlobalIO => visitIn(x)
    case x:Context => List(x)
    case x:Memory => Nil
    case x => x.collectUp[Context]()
  }.distinct
  override def visitOut(n:N):List[N] = visitGlobalOut(n).flatMap {
    case x:GlobalIO => visitOut(x)
    case x:Context => List(x)
    case x:Memory => Nil
    case x => x.collectUp[Context]()
  }.distinct
  override def runPass = {
    val contexts = top.collectDown[Context]()
    traverseScope(contexts, zero)
  }
}

