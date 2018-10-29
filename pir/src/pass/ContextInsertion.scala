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
        case mem:Memory => true
        case _ => false
      }
      if (pnodes.nonEmpty) {
        dbg(s"pnodes for $n = $pnodes")
        val global = assertOneOrLess(pnodes.flatMap { _.ancestors.collect { case n:GlobalContainer => n } }.distinct, 
          s"global for $pnodes")
        val parent = global.getOrElse(pirTop).as[PIRNode]
        within(parent) {
          val ctx = Context()
          val ancestorMap = leastMatchedPeers(pnodes, parent)
          pnodes.foreach { node =>
            swapParent(ancestorMap(node), ctx)
          }
          dbg(s"ctx=$ctx")
        }
      }
      super.visitNode(n)
    case _ => super.visitNode(n)
  }

}
