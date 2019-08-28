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
    val ctrlers = ctx.collectChildren[Controller]
    var ctrls = ctrlers.map { _.getCtrl }
    val map = ctx.children.view.filter { c => ctrls.contains(c.getCtrl) }.groupBy { _.getCtrl }
    ctrls = ctrls.sortBy { _.ancestors.size }
    if (ctrls.nonEmpty) {
      ctrls.tail.foldLeft[PIRNode](ctx) { case (prev, ctrl) =>
        val cb = within(prev, ctrl) { ControlBlock() }
        map(ctrl).foreach { n => swapParent(n, cb) }
        cb
      }
    }
  }

}

