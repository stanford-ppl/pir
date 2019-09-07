package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

class ControlBlockInsertion(implicit compiler:PIR) extends PIRTransformer with BufferAnalyzer {
  
  override def runPass = {
    insertControBlock
  }

  def insertControBlock:Unit = {
    val ctxs = pirTop.collectDown[Context]()
    ctxs.foreach(insertControBlock)
  }

  def insertControBlock(ctx:Context):Unit = {
    // Remove existing control block
    ctx.collectChildren[ControlBlock].foreach { cb =>
      cb.children.foreach { c => swapParent(c, ctx) }
      removeNodes(List(cb))
    }

    // Insert one control block per ctrl. Controller and buffer reads are outside control block
    val map = ctx.children.view.groupBy { _.getCtrl }
    val ctrls = map.keys.toSeq.sortBy { _.ancestors.size }

    ctrls.foldLeft[PIRNode](ctx) { case (prev, ctrl) =>
      val (extern, intern) = map(ctrl).partition { case ctrler:Controller => true; case buffer:LocalOutAccess => true; case const:Const => true; case _ => false }
      extern.foreach { n => swapParent(n, prev) }
      val ctrlers = extern.collect { case ctrler:Controller => true }
      if (ctrlers.isEmpty) {
        // Make it easier for codegen. One to one correspondance between ctrl and controller
        val ctrler = within(prev, ctrl) { stage(UnitController()) }
        ctrler.collectPeer[LocalAccess]().foreach { la =>
          la.done.singleConnected match {
            case Some(OutputField(c@Const(true), _)) if c.getCtrl == ctrl =>
              swapConnection(la.done, c.out, ctrler.childDone)
            case _ =>
          }
        }
      }
      val cb = within(prev, ctrl) { stage(ControlBlock()) }
      intern.foreach { n => swapParent(n, cb) }
      cb
    }
  }

}
