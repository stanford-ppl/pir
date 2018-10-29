package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class ContextInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with Transformer with UnitTraversal {
  import compiler.env._

  val ctxMap = mutable.Map[ControlTree, Context]() 

  override def visitNode(n:N) = n match {
    case n:PIRNode if n.ctrl.nonEmpty =>
      val parent = n.global.getOrElse { pirTop }
      val ctx = within(parent) { ctxMap.getOrElseUpdate(n.ctrl.get, Context()) }
      val belowParent = n.ancestorSlice(parent).dropRight(1).last
      dbg(s"$n parent=$parent, ctx=$ctx belowParent=$belowParent")
      swapParent(belowParent, ctx)
    case _ => super.visitNode(n)
  }

}
