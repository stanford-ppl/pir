package pir
package pass

import pir.node._
import prism.graph._

class DependencyDuplication(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with Transformer with UnitTraversal {
  val forward = false

  override def visitNode(n:N) = {
    dbg(s"visitNode($n)")
    super.visitNode(n)
  }

  override def selectFrontier(unvisited:List[N]) = {
    dbg(s"frontier:${frontier}")
    super.selectFrontier(unvisited)
  }
}

trait ContextTraversal extends PIRTraversal with TopologicalTraversal {
  override def visitIn(n:N):List[N] = visitGlobalIn(n).map {
    case x:Memory if !isBuffer(x) => x
    case x:Context => x
    case x => x.collectUp[Context]().head
  }
  override def visitOut(n:N):List[N] = visitGlobalOut(n).map {
    case x:Memory if !isBuffer(x) => x
    case x:Context => x
    case x => x.collectUp[Context]().head
  }
  override def runPass = {
    val contexts = top.collectDown[Context]()
    val mems = top.collectDown[Memory]().filterNot(isBuffer(_))
    traverseScope(contexts ++ mems, zero)
  }
}

