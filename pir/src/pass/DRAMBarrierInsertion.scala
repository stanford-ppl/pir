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
    inserted.clear
  }

  // LCA ctrl of the accesses and the group of accesses
  type AccessGroup = (ControlTree, List[(Context,DRAMCommand)])

  def process(dram:DRAM, ctxs:List[(Context, DRAMCommand)]) = dbgblk(s"process(${dram} (${dram.sid})"){
    // Sorted by program order
    val sorted = ctxs.sortBy { _._2.id }
    val grouped:List[AccessGroup] = groupAccesses(sorted, forward=true)
    dbgblk(s"grouped") {
      grouped.foreach { case (lca,group) =>
        dbg(s"lca:${quoteSrcCtx(lca)}")
        group.foreach { case (ctx,cmd) =>
          dbg(s"$ctx ${quoteSrcCtx(cmd)}")
        }
      }
    }
    // Handle forward dependency
    grouped.sliding(2,1).foreach { 
      case List((fromCtrl, from),(toCtrl,to)) =>
        from.foreach { case (fromCtx, fromCmd) =>
          to.foreach { case (toCtx, toCmd) =>
            insertToken(fromCtx, fromCmd, toCtx, toCmd)
          }
        }
      case List((fromCtrl, from)) =>
    }
    grouped.last._2.foreach { 
      case (ctx,cmd:DRAMStoreCommand) => insertTokenToHost(ctx,cmd)
      case (ctx,cmd) =>
    }
    // Handle carried dependencies
    val backGroups = groupAccesses(sorted, forward=false)
    pirTop.topCtrl.descendentTree.foreach { ctrl =>
      enforceCarriedDependency(ctrl, backGroups)
    }
  }

  def enforceCarriedDependency(ctrl:ControlTree, grouped:List[AccessGroup]) = {
    if (ctrl.isLoop.get) {
      // A list of list of Access Groups that belong to each child of this ctrl. The accessGroups
      // should already be ordered by forward token. Only need to take the last access group and
      // order with the first of the access group that are under this loop
      val groupUnderLoop = grouped.filter { _._1.isDescendentOf(ctrl) }
      if (groupUnderLoop.size > 1) dbgblk(s"enforceCarriedDependency(${quoteSrcCtx(ctrl)})") {
        val fromGroup:AccessGroup = groupUnderLoop.last
        val toGroup:AccessGroup = groupUnderLoop.head
        fromGroup._2.foreach { case (fromCtx, fromCmd) =>
          toGroup._2.foreach { case (toCtx, toCmd) =>
            insertToken(fromCtx, fromCmd, toCtx, toCmd).foreach { read =>
              read.initToken := true
              read.inits(true)
            }
          }
        }
      }
    }
  }

  // Give a list of accesses sorted by program order, grouping accesses based on whether they can
  // concurrently active. Return a list of group of accesses, with their 
  def groupAccesses(accesses:List[(Context,DRAMCommand)], forward:Boolean) = {
    partialReduce[AccessGroup](accesses.map { a => (a._1.getCtrl,List(a)) }) { case (a,b) =>
      val ctrlA = a._1
      val ctrlB = b._1
      val lca = leastCommonAncesstor(ctrlA, ctrlB).get
      val canGroup = lca.schedule match {
        case Sequenced => false
        case Pipelined if !forward => false
        case Streaming if !forward => false // this shouldn't happen if streaming is from spatial
        case _ => true
      }
      if (canGroup) Some((lca, a._2 ++ b._2)) else None
    }
  }

  def canConflict(from:ControlTree, to:ControlTree) = {
    from.uid.get == to.uid.get
  }

  val inserted = mutable.HashSet[(DRAMCommand,DRAMCommand)]()
  def insertToken(fromCtx:Context, fromCmd:DRAMCommand, toCtx:Context, toCmd:DRAMCommand):Option[TokenRead] = {
    if (!canConflict(fromCtx.getCtrl, toCtx.getCtrl)) return None
    if (fromCtx == toCtx) return None
    if (inserted.contains((fromCmd,toCmd))) return None
    inserted += ((fromCmd, toCmd))
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
        case toCmd:DRAMDenseCommand => toCmd.offset
        case toCmd:DRAMSparseCommand => toCmd.addr
      }
      val write = issueIn.collectFirst[BufferWrite]()
      val tokenRead = insertToken(read.ctx.get, write.ctx.get, dep=Some(accum.out))
      val tokenWrite = tokenRead.inAccess.as[TokenWrite]
      //connectLaneEnable(tokenRead)
      //connectLaneEnable(tokenWrite)
      val forward = within(write.getCtrl, write.ctx.get) {
        val original = write.data.singleConnected.get
        val forward = stage(Forward().in(original).dummy(tokenRead.out))
        swapConnection(write.data, original, forward.out)
        forward
      }
      //breakPoint(s"insertToken($fromCmd, $toCmd, $fromCtx, $toCtx)")
      Some(tokenRead)
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


