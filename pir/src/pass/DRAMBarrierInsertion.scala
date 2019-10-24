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
    dramMap.foreach { case (dram, ctxs) => process(dram, ctxs) }
  }

  def dependsOn(deped:DRAMCommand, dep:DRAMCommand):Boolean = /*dbgblk(s"dependsOn(${dquote(deped)}, ${dquote(dep)})")*/{
    if (deped.isInstanceOf[DRAMLoadCommand] && dep.isInstanceOf[DRAMLoadCommand]) return false
    val lca = leastCommonAncesstor(deped.getCtrl, dep.getCtrl).get
    lca.schedule match {
      case Sequenced => true
      case Fork => false
      case Pipelined | Streaming => 
        (dep.isInstanceOf[DRAMStoreCommand] && deped.isInstanceOf[DRAMLoadCommand])
      case ForkJoin => false
    }
  }

  def process(dram:DRAM, ctxs:List[(Context, DRAMCommand)]) = dbgblk(s"process(${dram} (${dram.sid})"){
    val accesses = ctxs.map { _._2 }
    val sorted = consistencyBarrier(accesses)(dependsOn) { case (from,to,carried) =>
      insertToken(from.ctx.get, from, to.ctx.get, to, !carried)
    }
    sorted.reverseIterator.find { ua =>
      ua.lanes.head.isInstanceOf[DRAMStoreCommand]
    }.foreach { ua =>
      ua.lanes.foreach { 
        case cmd:DRAMStoreCommand => insertTokenToHost(cmd.ctx.get,cmd)
        case _ =>
      }
    }
  }

  def insertToken(
    fromCtx:Context, 
    fromCmd:DRAMCommand, 
    toCtx:Context, 
    toCmd:DRAMCommand,
    forward:Boolean
  ):Unit = {
    dbgblk(s"insertToken($fromCmd, $toCmd)"){
      dbg(s"from=${quoteSrcCtx(fromCtx.getCtrl)} to=${quoteSrcCtx(toCtx.getCtrl)}")
      val returnOut = fromCmd match {
        case fromCmd:DRAMStoreCommand => fromCmd.ack
        case fromCmd:DRAMLoadCommand => fromCmd.data
      }
      val read = returnOut.collectFirst[BufferRead]()
      val accum = within(read.getCtrl, read.ctx.get) {
        allocate[AccumAck]{ accumAck =>
          canReach(accumAck.ack, read.out)
        } {
          stage(AccumAck().ack(read.out))
        }
      }
      val issueIn = toCmd match {
        case toCmd:DRAMDenseCommand => toCmd.offset.T.as[BufferRead].in
        case toCmd:DRAMSparseCommand => toCmd.addr.T.as[BufferRead].in
      }
      val write = issueIn.collectFirst[BufferWrite]()
      val tokenRead = insertToken(read.ctx.get, write.ctx.get, dep=Some(accum.out))
      val tokenWrite = tokenRead.inAccess.as[TokenWrite]
      connectLaneEnable(tokenRead)
      connectLaneEnable(tokenWrite)
      within(write.getCtrl, write.ctx.get) {
        val original = write.data.singleConnected.get
        val forward = stage(Forward().in(original).dummy(tokenRead.out))
        swapConnection(write.data, original, forward.out)
      }
      //breakPoint(s"insertToken($fromCmd, $toCmd, $fromCtx, $toCtx)")
      if (!forward) {
        tokenRead.initToken := true
        tokenRead.inits(true)
      }
    }
  }

  def connectLaneEnable(tokenAccess:TokenAccess) = {
    tokenAccess.getCtrl.ancestorTree.foreach { c => 
      c.ctrler.v.foreach { ctrler =>
        val ens = ctrler.en.T.collect { case v:CounterValid => v }
        dbg(s"${quoteSrcCtx(c)} ${ctrler} ${ens}")
        ens.foreach { en =>
          if (!tokenAccess.en.isConnectedTo(en.out)) {
            tokenAccess.en(en.out)
          }
        }
      }
    }
  }

  def insertTokenToHost(fromCtx:Context, fromCmd:DRAMStoreCommand) = dbgblk(s"insertTokenToHost($fromCtx,$fromCmd)"){
    dbg(s"from=${quoteSrcCtx(fromCtx.getCtrl)}")
    val hostOutCtx = pirTop.argFringe.collectFirst[HostOutController](visitDown _).ctx.get
    val returnOut = fromCmd.ack
    val accumAck = returnOut.collectFirst[AccumAck]()
    val tokenRead = insertToken(accumAck.ctx.get, hostOutCtx, dep=Some(accumAck.out))
    within(hostOutCtx, hostOutCtx.getCtrl) {
      stage(HostRead().input(tokenRead.out))
    }
  }
}


