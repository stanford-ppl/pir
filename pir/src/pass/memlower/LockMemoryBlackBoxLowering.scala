package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryBackBoxLowering extends LockMemoryLowering {

  override def visitNode(n:N) = n match {
    case n:Lock => 
      lowerPattern(n) match {
        case Right(ret) => ret
        case Left(msg) =>
          dbg(msg)
          err(msg) 
      }
    case n:LockSRAM =>
    case _ => super.visitNode(n)
  }

  override def finPass = {
    super.finPass
  }

  private def lowerRMW(
    lock:Lock, 
    lockMems:List[LockSRAM], // Lock address, one per outer loop lane
    rmwig:InnerAccessGroup, // Inner Ctrl with Read Modify Write
    igs:List[InnerAccessGroup] // A list of sparse accum group
  ) = dbgblk(s"lowerRMW($lock)"){
    within(pirTop, lock.getCtrl) {
      val blockCtx = within(pirTop, lock.getCtrl) { stage(Context().streaming(true)) }
      val block = within(blockCtx, lock.getCtrl) { LockRMABlock(lockMems) }
      val reqs = igs.flatMap { ig =>
        if (ig.isLockedAccess) lowerLockedRMW(ig, block, blockCtx)
        else lowerUnlockAccessGroup(ig, block, blockCtx)
      }
      reqs.groupBy { case (mem, ctrl, reqs) => mem }.foreach { case (mem, reqs) =>
        val sorted = reqs.map { case (mem,ctrl,reqs) => (ctrl, reqs) }.sortBy { _._1.progorder.get }
        insertBarrier(mem, sorted)
      }
    }
    breakPoint(s"")
  }

  private def insertBarrier(mem:LockSRAM, reqs:List[(ControlTree, List[(Input[PIRNode], Output[PIRNode])])]) = {
    reqs.groupBy { _._1 }.foreach { case (mem, accesses) =>
      // Forward dependencies
      accesses.sliding(2,1).foreach {
        case List(a,b) =>
        case List(a) =>
      }
    }
  }

  private def lowerLockedRMW(ig:InnerAccessGroup, block:LockRMABlock, blockCtx:Context) = {
    val lanes = ig.group.flatMap { _.accesses.map { case UnrolledAccess(mem, lanes) => lanes }}.transpose
    val req = lanes.map { accesses =>

      val reads = accesses.collect { case access:LockRead => access }
      val writes = accesses.collect { case access:LockWrite => access }
      val exps = reads.flatMap { read => 
        read.out.accumTill[LockWrite]().filterNot { case access:Access => true; case _ => false }
      }.distinct
      dbg(s"accum exps: ${exps}")
      val accumCtx = within(pirTop, ig.ctrl) { Context().streaming(true) }
      exps.foreach { exp => swapParent(exp, accumCtx) }

      // Wire up address port
      val addrCtx = within(pirTop, ig.ctrl) { Context() }
      val addr = assertUnify(accesses, s"addr") { _.addr.singleConnected.get }
      accesses.reduce { (a1,a2) => if (matchInput(a1.en, a2.en)) a1 else err(s"$a1 and $a2 doesn't match in enable") }
      accesses.foreach { access => swapParent(access, addrCtx) }
      flattenEnable(accesses.head)
      val addrPort = block.addLockAddr
      addrPort(addr)
      bufferInput(addrPort, fromCtx=Some(addrCtx))

      // Wire up read data
      reads.foreach { read =>
        val readDataPort = block.addLockDataOuts(read.mem.T.as[LockSRAM])
        read.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx, ins) =>
          ins.foreach { in =>
            swapConnection(in, read.out, readDataPort)
            bufferInput(in)
          }
        }
      }

      // Wire up write data
      writes.foreach { write =>
        val writeDataPort = block.addLockDataIns(write.mem.T.as[LockSRAM])
        writeDataPort(write.data.singleConnected.get)
        bufferInput(writeDataPort, fromCtx=Some(accumCtx))
      }

      val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
      val ackPort = block.addLockAck

      (reqPort, ackPort)
    }
    val mems = ig.group.map { _.mem }.distinct
    mems.map { mem => (mem, ig.ctrl, req) }
  }

  private def lowerUnlockAccessGroup(ig:InnerAccessGroup, block:LockRMABlock, blockCtx:Context) = {
    ig.group.map { case MemGroup(mem, accesses) =>
      assert(accesses.size == 1)
      val UnrolledAccess(mem, lanes) = accesses.head
      (mem, ig.ctrl, lanes.map { lane => lowerUnlockAccess(lane, block, blockCtx) })
    }
  }

  private def lowerUnlockAccess(access:LockAccess, block:LockRMABlock, blockCtx:Context) = {
    val mem = access.mem.T.as[LockSRAM]
    val addrCtx = access match {
      case access:LockWrite => access.ctx.get
      case access:LockRead => within(pirTop, access.getCtrl) { Context() }
    }
    swapParent(access, addrCtx)
    flattenEnable(access)
    access match {
      case access:LockWrite =>
        val addrPort = block.addUnlockWriteAddrs(mem)
        addrPort(access.addr.singleConnected.get)
        val dataPort = block.addUnlockWriteDatas(mem)
        dataPort(access.data.singleConnected.get)
        bufferInput(addrPort)
        bufferInput(dataPort)
        val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
        val ackPort = block.addUnlockWriteAcks(mem)
        (reqPort, ackPort)
      case access:LockRead =>
        val addrPort = block.addUnlockReadAddrs(mem)
        addrPort(access.addr.singleConnected.get)
        bufferInput(addrPort, fromCtx=Some(addrCtx))
        val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
        val dataPort = block.addUnlockReadDatas(mem)
        access.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx, ins) =>
          ins.foreach { in =>
            swapConnection(in, access.out, dataPort)
            bufferInput(in)
          }
        }
        (reqPort ,dataPort)
    }
  }

  def lowerPattern(n:Lock):Either[String,T] = {
    val lockOns = n.out.T.as[List[LockOnKeys]]
    val lockAccesses = lockOns.flatMap { _.out.T }.as[List[LockAccess]]
    val lockMems = lockAccesses.map { _.mem.T }.distinct
    val lockKeys = lockOns.map { _.key.T }.distinct
    val preunrollKey = lockKeys.map { _.progorder.v }.distinct
    if (preunrollKey.size > 1) return Left(s"Multiple locked keys ${lockKeys.map { quoteSrcCtx }}")

    // A list of list accesses, each list contains unrolled accesses belong to the same
    // preunrolled access.
    // Each list of unrolled access is sorted by their unrolling order
    val sortedAccesses:List[UnrolledAccess] = lockAccesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => UnrolledAccess(as.head.mem.T.as[LockSRAM], as.sortBy { _.order.get }) }

    // Accum[InnerCtrl[Urolled[Access]]]
    val sortedGroupedAccesses = sortedAccesses.groupBy { ua => ua.lanes.head.getCtrl }
      .toList.sortBy { _._1.progorder.get }.map { case (ctrl,group) => 
        val mgroup = group.groupBy { _.mem }.map { case (mem, as) => MemGroup(mem, as) }
        InnerAccessGroup(ctrl,mgroup.toList)
      }

    val noOuterPar = sortedAccesses.forall { ig => ig.lanes.size == 1 }
    if (noOuterPar & lockMems.size == 1) return Right(lowerUnparLockMem(lockMems.head))

    val (pure, impure) = sortedGroupedAccesses.partition { _.isPure }
    if (impure.nonEmpty) 
      return Left(s"Cannot have unlocked and locked statement in the same basic block: ${quoteSrcCtx(impure)}}")

    val rmwAddrs = pure.map { ig => (ig.getRMWAddr,ig) }.filter { _._1.nonEmpty }
    if (rmwAddrs.size > 1) return Left(s"Cannot have more than 1 read modify write block ${quoteSrcCtx(rmwAddrs.map { _._2 })}")
    if (rmwAddrs.size == 1) {
      val (Some(addrs), ig) = rmwAddrs.head
      return Right(lowerRMW(n, lockMems.as[List[LockSRAM]], ig, pure))
    } else {
      return Left(s"Unsupported access pattern ${quoteSrcCtx(n)}")
    }
  }

  override def quoteSrcCtx(x:Any):String = x match {
    case UnrolledAccess(mem, lanes) => quoteSrcCtx(lanes.head)
    case MemGroup(mem, accesses) => quoteSrcCtx(accesses)
    case InnerAccessGroup(ctrl, group) => quoteSrcCtx(ctrl)
    case l:List[_] => l.map { quoteSrcCtx }.mkString("\n")
  }

}

case class LockGroup(
  mem:LockSRAM, // sparse accum
  initWrites:List[LockWrite], // One per outer loop lane
  lockReads:List[LockRead],
  lockWrites:List[LockWrite],
  flushReads:List[LockRead],
)

/*
 * A list of accesses unrolled from the same preunrolled access.
 * as are sorted by their unrolling order
 * */
case class UnrolledAccess(mem:LockSRAM,lanes:List[LockAccess]) {
  def isLockedAccess = lanes.head.lock.isConnected
  def isLockedRead = lanes.head match {
    case a:LockRead if a.lock.isConnected => true
    case _ => false
  }
  def isLockedWrite = lanes.head match {
    case a:LockWrite if a.lock.isConnected => true
    case _ => false
  }
  def isUnlockedAccess = !lanes.head.lock.isConnected
}
/*
 * A list of accesses belonging to the same basic block/inner loop and the same memory, sorted by program order
 * */
case class MemGroup(mem:LockSRAM,accesses:List[UnrolledAccess]) {
  def isUnlockedAccess = accesses.forall { _.isUnlockedAccess }
  def isLockedAccess = accesses.forall { _.isLockedAccess }
  def isPure = accesses.forall { a => a.isUnlockedAccess | a.isLockedAccess }
  def getRMWAddr = accesses match {
    case List(a,b) => 
      val isLockRMW = a.isLockedRead && b.isLockedWrite && a.lanes.head.addr.T == b.lanes.head.addr.T
      if (isLockRMW) Some((a.lanes.map { _.addr.T })) else None
    case _ => None
  }
}

/*
 * A list of accesses belonging to the same basic block/inner loop
 * */
case class InnerAccessGroup(ctrl:ControlTree, group:List[MemGroup]) {
  def isUnlockedAccess = group.forall { _.isUnlockedAccess }
  def isLockedAccess = group.forall { _.isLockedAccess }
  def isPure = group.forall { a => a.isUnlockedAccess | a.isLockedAccess }
  def getRMWAddr = {
    val addrs = group.map { _.getRMWAddr }
    if (addrs.exists{_.isEmpty}) None
    else testUnique(addrs.map { _.get })
  }
}

