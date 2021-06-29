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

  def dependsOn(deped:DRAMCommand, dep:DRAMCommand):Option[Int] = /*dbgblk(s"dependsOn(${dquote(deped)}, ${dquote(dep)})")*/{
    if (deped.isInstanceOf[DRAMLoadCommand] && dep.isInstanceOf[DRAMLoadCommand]) return None
    val lca = leastCommonAncesstor(deped.getCtrl, dep.getCtrl).get
    lca.schedule match {
      case Sequenced => Some(1)
      case Fork => None
      case Pipelined | Streaming => 
        if (dep.isInstanceOf[DRAMStoreCommand] && deped.isInstanceOf[DRAMLoadCommand]) Some(1) else None
      case ForkJoin => None
    }
  }

  def process(dram:DRAM, ctxs:List[(Context, DRAMCommand)]) = dbgblk(s"process(${dram} (${dram.sid})"){
    val accesses = ctxs.map { _._2 }
    val sorted = consistencyBarrier(accesses)(dependsOn) { case (from,to,carried,depth) =>
      val barInit = if (carried && depth == 1) dram.parAllowed.get else 1
      val barDepth = if (carried && depth == 1) dram.parAllowed.get else depth
      dbg(s"barDepth ${barDepth} barInit ${barInit} dram.parAllowed ${dram.parAllowed.get} carried ${carried}")
      insertToken(from.ctx.get, from, to.ctx.get, to, !carried, barDepth, barInit)
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
    forward:Boolean,
    depth:Int,
    initVals:Int
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
      val tokenRead = insertToken(read.ctx.get, write.ctx.get, dep=Some(accum.out)).depth(depth)
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
        tokenRead.initToken := initVals
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


