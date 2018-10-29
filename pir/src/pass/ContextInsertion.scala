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
    //case n:ControlTree =>
      //val pnodes = n.pnodes.get.filterNot {
        //case mem:Memory => true
        //case _ => false
      //}
      //if (pnodes.nonEmpty) {
        //dbg(s"pnodes for $n = $pnodes")
        //val global = assertOneOrLess(pnodes.flatMap { _.ancestors.collect { case n:GlobalContainer => n } }.distinct, 
          //s"global for $pnodes")
        //val parent = global.getOrElse(pirTop).as[PIRNode]
        //within(parent) {
          //val ctx = Context()
          //val ancestorMap = leastMatchedPeers(pnodes, parent)
          //pnodes.foreach { node =>
            //swapParent(ancestorMap(node), ctx)
          //}
          //dbg(s"ctx=$ctx")
        //}
      //}
      //super.visitNode(n)
    case _ => super.visitNode(n)
  }

}
