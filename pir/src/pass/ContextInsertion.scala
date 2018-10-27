package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class ContextInsertion(implicit compiler:PIR) extends PIRPass with ControlTreeTraversal with Transformer with SiblingFirstTraversal with UnitTraversal {
  import compiler.env._

  override def visitNode(n:N) = n match {
    case n:ControlTree =>
      val pnodes = n.pnodes.get.filterNot {
        case mem:InputBuffer => false
        case mem:Memory => true
        case _ => false
      }
      if (pnodes.nonEmpty) {
        dbg(s"pnodes for $n = $pnodes")
        dbg(s"pnodes, parents for $n = ${pnodes.map{_.parent}}")
        var lca = leastCommonAncesstor(pnodes).get.to[PIRNode]
        dbg(s"lca=$lca")
        if (lca.children.isEmpty) lca = lca.parent.get.to[PIRNode]
        within(lca) {
          val ctx = Context()
          val ancestorMap = leastMatchedPeers(pnodes, lca)
          pnodes.foreach { node =>
            swapParent(ancestorMap(node), ctx)
          }
          dbg(s"ctx=$ctx")
          //breakPoint(s"pnodes for $n = $pnodes lca=$lca ctx=$ctx")
        }
      }
      super.visitNode(n)
    case _ => super.visitNode(n)
  }

}
