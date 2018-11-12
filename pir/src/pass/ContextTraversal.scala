package pir
package pass

import pir.node._
import prism.graph._

trait ContextTraversal extends PIRTraversal with TopologicalTraversal {
  override def visitIn(n:N):List[N] = visitGlobalIn(n).map {
    case x:GlobalIO => x
    case x:Memory => x
    case x:Context => x
    case x => x.collectUp[Context]().head
  }
  override def visitOut(n:N):List[N] = visitGlobalOut(n).map {
    case x:GlobalIO => x
    case x:Memory => x
    case x:Context => x
    case x => x.collectUp[Context]().head
  }
  override def runPass = {
    val contexts = top.collectDown[Context]()
    val mems = top.collectDown[Memory]()
    val gios = top.collectDown[GlobalIO]()
    traverseScope(contexts ++ mems ++ gios, zero)
  }
}

