package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

// Insert memory access for escape variables used within splitter block
class SplitterTransformer(implicit compiler:PIR) extends PIRPass with PIRTransformer {

  override def runPass = {
    val ctxs = pirTop.collectChildren[Context]
    ctxs.foreach { ctx =>
      ctx.children.collect { case sc:SplitController => sc }.foreach { sc =>
        closure(ctx, sc)
      }
    }
  }

  def closure(ctx:Context, sc:SplitController) = dbgblk(s"closure($ctx)"){
    val ctrl = ctx.getCtrl
    val splitter = within(pirTop, ctrl, sc.srcCtx.v) {
      val ctx = stage(Context().streaming(true))
      within(ctx, ctx.getCtrl) {
        stage(SplitLeader().addrIn(sc.splitOn.connected))
      }
    }
    sc.splitOn.disconnect
    sc.splitOn(splitter.ctrlOut)
    bufferInput(splitter.addrIn)
    ctx.ins.foreach { in =>
      bufferInput(in)
    }
  }

}
