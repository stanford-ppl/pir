package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class DRAMBarrierInsertion(implicit compiler:PIR) extends PIRPass with PIRTransformer with DependencyAnalyzer {

  override def runPass = {
    val dramCtxs = pirTop.collectChildren[Context].flatMap { ctx =>
      ctx.collectFirstChild[DRAMCommand].map { cmd => (ctx,cmd) }
    }
    val dramMap = dramCtxs.groupBy { case (ctx, cmd) => cmd.dram }
    dramMap.foreach { case (dram, ctxs) => if (ctxs.size > 1) process(dram, ctxs) }
  }

  def process(dram:DRAM, ctxs:List[(Context, DRAMCommand)]) = dbgblk(s"process($dram)"){
    // Sorted by program order
    val sorted = ctxs.sortBy { _._2.id }
    sorted.sliding(2,1).foreach { case List((fromCtx,fromCmd),(toCtx,toCmd)) =>
      val fromCtrl = fromCtx.getCtrl
      val toCtrl = toCtx.getCtrl
      val lca = leastCommonAncesstor(fromCtrl, toCtrl).get
      if (lca.schedule == Sequenced) {
        insertToken(fromCtx, fromCmd, toCtx, toCmd)
      }
    }
    if (sorted.size > 1) {
      val (fromCtx, fromCmd) = sorted.last
      val (toCtx, toCmd) = sorted.head
      val fromCtrl = fromCtx.getCtrl
      val toCtrl = toCtx.getCtrl
      val lca = leastCommonAncesstor(fromCtrl, toCtrl).get
      if (lca.schedule == Sequenced && lca.isLoop.get) {
        val read = insertToken(fromCtx, fromCmd, toCtx, toCmd)
        read.initToken := true
        read.inits(true)
      }
    }
  }

  def insertToken(fromCtx:Context, fromCmd:DRAMCommand, toCtx:Context, toCmd:DRAMCommand):TokenRead = dbgblk(s"insertToken($fromCmd, $toCmd)"){
    val returnOut = fromCmd match {
      case fromCmd:DRAMStoreCommand => fromCmd.ack
      case fromCmd:DRAMLoadCommand => fromCmd.data
    }
    val read = returnOut.collectFirst[BufferRead]()
    val accum = within(read.getCtrl, read.ctx.get) {
      stage(AccumAck().ack(read.out))
    }
    val issueIn = toCmd match {
      case toCmd:DRAMDenseCommand => toCmd.offset
      case toCmd:DRAMSparseCommand => toCmd.addr
    }
    val write = issueIn.collectFirst[BufferWrite]()
    val tokenRead = insertToken(read.ctx.get, write.ctx.get, dep=Some(accum.out))
    val forward = within(write.getCtrl, write.ctx.get) {
      val original = write.data.singleConnected.get
      val forward = stage(Forward().in(original).dummy(tokenRead.out))
      swapConnection(write.data, original, forward.out)
      forward
    }
    //breakPoint(s"insertToken($fromCmd, $toCmd, $fromCtx, $toCtx)")
    tokenRead
  }

}


