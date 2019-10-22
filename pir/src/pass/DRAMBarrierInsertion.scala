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
  type AccessGroup = List[(Context,DRAMCommand)]

  def process(dram:DRAM, ctxs:List[(Context, DRAMCommand)]) = dbgblk(s"process(${dram} (${dram.sid})"){
    // Sorted by program order
    //TODO: this get wrong result for DRAMDoubleBuffer_0
    //val sorted = ctxs.sortBy { _._2.progorder.get }
    val sorted = ctxs.sortBy { _._2.id }
    val grouped:List[AccessGroup] = groupAccesses(sorted, forward=true)
    // Handle forward dependency
    grouped.sliding(2,1).foreach { 
      case List(from,to) =>
        from.foreach { case (fromCtx, fromCmd) =>
          to.foreach { case (toCtx, toCmd) =>
            insertToken(fromCtx, fromCmd, toCtx, toCmd, forward=true)
          }
        }
      case List(from) =>
    }
    grouped.last.foreach { 
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
      val groupUnderLoop = grouped.map { _.filter { _._2.getCtrl.isDescendentOf(ctrl) } }.filter { _.nonEmpty }
      if (groupUnderLoop.size > 1) dbgblk(s"enforceCarriedDependency(${quoteSrcCtx(ctrl)})") {
        val fromGroup:AccessGroup = groupUnderLoop.last
        val toGroup:AccessGroup = groupUnderLoop.head
        fromGroup.foreach { case (fromCtx, fromCmd) =>
          toGroup.foreach { case (toCtx, toCmd) =>
            insertToken(fromCtx, fromCmd, toCtx, toCmd, forward=false)
          }
        }
      }
    }
  }

  // Give a list of accesses sorted by program order, grouping accesses based on whether they can
  // concurrently active. Return a list of group of accesses, with their 
  def groupAccesses(accesses:AccessGroup, forward:Boolean) = {
    val grouped = partialOrderedReduce[AccessGroup](accesses.map { a => List(a) }) { case (a,b) => 
      val conflict = a.exists { case (ctxa, cmda) =>
        b.exists { case (ctxb, cmdb) =>
          canConflict(cmda,cmdb,forward)
        }
      }
      if (conflict) None else Some(a ++ b)
    }
    dbgblk(s"grouped(forward=$forward)") {
      grouped.foreach { group =>
        dbg(s"----")
        group.foreach { case (ctx,cmd) =>
          dbg(s"$ctx ${quoteSrcCtx(cmd)}")
        }
      }
    }
    grouped
  }

  def canConflict(fromCmd:DRAMCommand, toCmd:DRAMCommand, forward:Boolean):Boolean = {
    // From unrolled dram accesses
    if (fromCmd.progorder.get == toCmd.progorder.get) return false
    // Both are read
    if (fromCmd.isInstanceOf[DRAMLoadCommand] && toCmd.isInstanceOf[DRAMLoadCommand]) return false
    val from = fromCmd.getCtrl
    val to = toCmd.getCtrl
    // Not in the same parallelized outer loop
    //if (from.uid.get != to.uid.get) return false
    val lca = leastCommonAncesstor(from, to).get
    lca.schedule match {
      case Sequenced => return true
      case Pipelined | Streaming if !forward => return true
      case _ => return false
    }
  }

  val inserted = mutable.HashSet[(DRAMCommand,DRAMCommand)]()
  def insertToken(
    fromCtx:Context, 
    fromCmd:DRAMCommand, 
    toCtx:Context, 
    toCmd:DRAMCommand,
    forward:Boolean
  ):Unit = {
    if (!canConflict(fromCmd, toCmd, forward)) return
    if (fromCtx == toCtx) return
    if (inserted.contains((fromCmd,toCmd))) return
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


