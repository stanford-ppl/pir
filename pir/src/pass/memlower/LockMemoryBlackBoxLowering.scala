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
    rmwpar:Int,
    lockMems:List[LockSRAM], // Lock address, one per outer loop lane
    igs:List[InnerAccessGroup] // A list of sparse accum group
  ) = dbgblk(s"lowerRMW($lock)"){
    within(pirTop, lock.getCtrl) {
      val blockCtx = within(pirTop, lock.getCtrl) { stage(Context().streaming(true)) }
      val lockAccums = lockMems.map { mem => LockAccum(mem.tp.get, mem.dims.get, mem.srcCtx.v, mem.name.v) }
      val accumMap = (lockMems,lockAccums).zipped.toMap
      val block = within(blockCtx, lock.getCtrl) { LockRMABlock(rmwpar,lockAccums) }
      block.mirrorMetas(lock)
      val reqs = igs.flatMap { ig =>
        if (ig.isLockedAccess) lowerLockedRMW(ig, block, blockCtx, accumMap)
        else lowerUnlockAccessGroup(ig, block, blockCtx, accumMap)
      }
      reqs.groupBy { case (mem, ctrl, reqs) => mem }.foreach { case (mem, reqs) =>
        val sorted = reqs.map { case (mem,ctrl,reqs) => (ctrl, reqs) }.sortBy { _._1.progorder.get }
        insertBarrier(mem, sorted)
      }
      stage(block)
    }
  }

  private def insertBarrier(mem:LockSRAM, reqs:List[(ControlTree, List[(Input[PIRNode], Output[PIRNode])])]) = {
    // Forward dependencies
    reqs.sliding(2,1).foreach {
      case List((actrl, as),(bctrl,bs)) =>
        as.foreach { case (areqst, aresp) =>
          val actx = aresp.src.ctx.get
          bs.foreach { case (breqst, bresp) =>
            val bctx = breqst.src.ctx.get
            val read = insertToken(actx, bctx, dep=Some(aresp))
            val original = breqst.singleConnected.get
            val forward = within(bctx, bctx.getCtrl) { stage(Forward().in(original).dummy(read.out)) }
            swapConnection(breqst, original, forward.out)
          }
        }
      case List(a) =>
    }
    //TODO: Carried dependency
  }

  private def lowerLockedRMW(ig:InnerAccessGroup, block:LockRMABlock, blockCtx:Context, accumMap:Map[LockSRAM,LockAccum]) = dbgblk(s"lowerLockedRMW($ig)"){
    val lanes = ig.group.flatMap { _.accesses.map { case UnrolledAccess(mem, lanes) => lanes }}.transpose
    val req = lanes.zipWithIndex.map { case (accesses,lane) =>

      val reads = accesses.collect { case access:LockRead => access }
      val writes = accesses.collect { case access:LockWrite => access }
      val exps = reads.flatMap { read => 
        read.out.accumTill[LockWrite]().filterNot { case access:Access => true; case _ => false }
      }.distinct
      dbg(s"accum exps: ${exps}")
      val accumCtrl = reads.head.getCtrl
      val accumCtx = within(pirTop, accumCtrl) { stage(Context().streaming(true)) }
      exps.foreach { exp => swapParent(exp, accumCtx) }
      bufferInput(accumCtx, fromCtx=Some(reads.head.ctx.get))

      // Wire up address port
      val addrCtx = within(pirTop, accumCtrl) { stage(Context()) }
      val addr = assertUnify(accesses, s"addr") { _.addr.singleConnected.get }
      accesses.reduce { (a1,a2) => if (matchInput(a1.en, a2.en)) a1 else err(s"$a1 and $a2 doesn't match in enable") }
      accesses.foreach { access => swapParent(access, addrCtx) }
      flattenEnable(accesses.head)
      val addrPort = block.lockAddrs(lane)
      addrPort(addr)
      bufferInput(addrPort, fromCtx=Some(addrCtx))

      // Wire up read data
      reads.foreach { read =>
        val readDataPort = block.lockDataOuts(accumMap(read.mem.T.as[LockSRAM]))(lane)
        read.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx, ins) =>
          ins.foreach { in =>
            swapConnection(in, read.out, readDataPort)
            bufferInput(in).foreach { read =>
              read.initToken := true
              read.inits := true
            }
          }
        }
      }

      // Wire up write data
      writes.foreach { write =>
        val writeDataPort = block.lockDataIns(accumMap(write.mem.T.as[LockSRAM]))(lane)
        writeDataPort(write.data.singleConnected.get)
        bufferInput(writeDataPort, fromCtx=Some(accumCtx))
      }

      val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
      val ackCtx = within(pirTop, accumCtrl) { stage(Context()) }
      dbg(s"ackCtx=$ackCtx")
      val accumAck = within(ackCtx, accumCtrl) {
        stage(AccumAck().ack(block.lockAcks(lane)))
      }
      bufferInput(accumAck.ack)

      (reqPort, accumAck.out)
    }
    val mems = ig.group.map { _.mem }.distinct
    mems.map { mem => (mem, ig.ctrl, req) }
  }

  private def lowerUnlockAccessGroup(ig:InnerAccessGroup, block:LockRMABlock, blockCtx:Context, accumMap:Map[LockSRAM,LockAccum]) = dbgblk(s"lowerUnlockAccessGroup(${ig})"){
    ig.group.map { case MemGroup(mem, accesses) =>
      assert(accesses.size == 1)
      val UnrolledAccess(mem, lanes) = accesses.head
      val accum = accumMap(mem)
      val reqs = if (lanes.size != block.par) {
        if (lanes.size != 1)
          err(s"Unlocked access must be either not outer loop parallelized or parallelized by the same amount as locked access ${quoteSrcCtx(accesses)}")
        val access = lanes.head
        List(lowerUnlockAccess(access, accum, (0 until block.par).toList, block, blockCtx))
      } else {
        lanes.zipWithIndex.map { case (access,lane) => 
          lowerUnlockAccess(access, accum, List(lane), block, blockCtx)
        }
      }
      (mem, ig.ctrl, reqs)
    }
  }

  private def lowerUnlockAccess(access:LockAccess, accum:LockAccum, lanes:List[Int], block:LockRMABlock, blockCtx:Context) = {
    val addrCtx = access match {
      case access:LockWrite => access.ctx.get
      case access:LockRead => within(pirTop, access.getCtrl) { Context() }
    }
    swapParent(access, addrCtx)
    flattenEnable(access)
    access match {
      case access:LockWrite =>
        val (reqPorts, ackPorts) = lanes.map { lane =>
          val addrPort = block.unlockWriteAddrs(accum)(lane)
          addrPort(access.addr.singleConnected.get)
          bufferInput(addrPort)
          val dataPort = block.unlockWriteDatas(accum)(lane)
          dataPort(access.data.singleConnected.get)
          bufferInput(dataPort)
          val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val ackPort = block.unlockWriteAcks(accum)(lane)
          (reqPort, ackPort)
        }.unzip
        val reqPort = assertIdentical(reqPorts, s"Broadcast lanes have different request ports").get
        val ackCtx = within(pirTop, access.getCtrl) { stage(Context()) }
        dbg(s"ackCtx = $ackCtx")
        val ackPort = within(ackCtx, access.getCtrl) {
          val ack = ackPorts.reduce[Output[PIRNode]] { case (r1,r2) =>
            stage(OpDef(FixAnd).addInput(r1,r2).out)
          }
          stage(AccumAck().ack(ack).out)
        }
        bufferInput(ackCtx)
        (reqPort, ackPort)
      case access:LockRead =>
        val (reqPorts, dataPorts) = lanes.map { lane =>
          val addrPort = block.unlockReadAddrs(accum)(lane)
          addrPort(access.addr.singleConnected.get)
          bufferInput(addrPort, fromCtx=Some(addrCtx))
          val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val dataPort = block.unlockReadDatas(accum)(lane)
          (reqPort, dataPort)
        }.unzip
        val reqPort = assertIdentical(reqPorts, s"Broadcast lanes have different request ports").get
        val dataPort = if (dataPorts.size == 1) {
          dataPorts.head
        } else {
          val readCtxs = access.out.connected.map { in => in.src.ctx.get }
          val respCtx = testOne(readCtxs).getOrElse { within(pirTop, access.getCtrl) { stage(Context()) } }
          val dataPort = within(respCtx, access.getCtrl) {
            dataPorts.reduce[Output[PIRNode]] { case (d1,d2) =>
              stage(OpDef(FixAnd).addInput(d1,d2).out)
            }
          }
          bufferInput(respCtx)
          dataPort
        }
        access.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx, ins) =>
          ins.foreach { in =>
            swapConnection(in, access.out, dataPort)
            bufferInput(in)
          }
        }
        (reqPort ,dataPort)
    }
  }

  def lowerPattern(n:Lock):Either[String,T] = dbgblk(s"lowerPattern($n)"){
    val lockOns = n.out.T.as[List[LockOnKeys]]
    val lockAccesses = lockOns.flatMap { _.out.T }.as[List[LockAccess]]
    val lockMems = lockAccesses.map { _.mem.T }.distinct
    val accesses = lockMems.flatMap { _.accesses.as[List[LockAccess]] }.distinct
    val lockKeys = lockOns.map { _.key.T }.distinct
    val preunrollKey = lockKeys.map { _.progorder.v }.distinct
    if (preunrollKey.size > 1) return Left(s"Multiple locked keys ${lockKeys.map { quoteSrcCtx }}")

    // A list of list accesses, each list contains unrolled accesses belong to the same
    // preunrolled access.
    // Each list of unrolled access is sorted by their unrolling order
    val sortedAccesses:List[UnrolledAccess] = accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => UnrolledAccess(as.head.mem.T.as[LockSRAM], as.sortBy { _.order.get }) }

    // Accum[InnerCtrl[Urolled[Access]]]
    val sortedGroupedAccesses = sortedAccesses.groupBy { ua => ua.lanes.head.getCtrl }
      .toList.sortBy { _._1.progorder.get }.map { case (ctrl,group) => 
        val mgroup = group.groupBy { _.mem }.map { case (mem, as) => MemGroup(mem, as) }
        InnerAccessGroup(ctrl,mgroup.toList)
      }

    dbgblk(s"sortedGroupedAccesses") {
      sortedGroupedAccesses.foreach { ig =>
        dbg(s"$ig")
      }
    }

    val noOuterPar = sortedAccesses.forall { ig => ig.lanes.size == 1 }
    if (noOuterPar & lockMems.size == 1) return Right(lowerUnparLockMem(lockMems.head))

    val (pure, impure) = sortedGroupedAccesses.partition { _.isPure }
    if (impure.nonEmpty) 
      return Left(s"Cannot have unlocked and locked statement in the same basic block: ${quoteSrcCtx(impure)}}")

    val (rmws, unlocked) = pure.partition { ig => ig.getRMWAddr.nonEmpty }
    if (rmws.size > 1) return Left(s"Cannot have more than 1 read modify write block ${quoteSrcCtx(rmws)}")
    if (rmws.size == 1) {
      val rmwpar = rmws.head.group.head.accesses.head.lanes.size
      unlocked.foreach { case InnerAccessGroup(ctrl,group) =>
        group.foreach { case MemGroup(mem, accesses) =>
          accesses.foreach { case UnrolledAccess(mem, lanes) =>
            val par = lanes.size
            if (par > 1 && par != rmwpar)
              return Left(s"Cannot parallelize the unlocked accesses ${quoteSrcCtx(lanes.head)} with different par with locked access for ${quoteSrcCtx(n)} ")
          }
        }
      }
      assertUnify(lockMems, s"${quoteSrcCtx(n)} have different types for read modify accumulators") { mem => mem.tp.get }
      assertUnify(lockMems, s"${quoteSrcCtx(n)} have different dimensions for read modify accumulators") { mem => mem.dims.get }
      return Right(lowerRMW(n, rmwpar, lockMems.as[List[LockSRAM]], pure))
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
    else testIdentical(addrs.map { _.get })
  }
}

