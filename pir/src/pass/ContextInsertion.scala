package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class ContextInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with PIRTransformer with UnitTraversal with DependencyAnalyzer {

  val ctxMap = mutable.Map[ControlTree, Context]() 

  override def visitNode(n:N) = n match {
    case n:PIRNode if n.ctrl.nonEmpty => moveToContext(n)
    case _ => super.visitNode(n)
  }

  override def finPass = {
    super.finPass
    ctxMap.clear
  }

  def moveToContext(n:PIRNode) = {
    val ctrl = n.ctrl.get
    val parent = n.global.getOrElse { pirTop }
    val ctx = within(parent, ctrl) { 
      ctxMap.getOrElseUpdate(n.ctrl.get, Context())
    }
    val belowParent = n.ancestorSlice(parent).dropRight(1).last
    dbg(s"$n parent=$parent, ctx=$ctx belowParent=$belowParent")
    swapParent(belowParent, ctx)
  }

}
