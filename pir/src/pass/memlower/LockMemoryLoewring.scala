package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryLowering extends GenericMemoryLowering {
  override def visitNode(n:N) = n match {
    case n:Memory if n.isLockSRAM => lowerLockMem(n)
    case _ => super.visitNode(n)
  }

  override def finPass = {
    splitCtxs.clear
    super.finPass
  }

  private val splitCtxs = mutable.Map[ControlTree, Context]()
  private val addrCtxs = mutable.Map[ControlTree, Context]()
  private def lowerLockMem(n:Memory) = dbgblk(s"lowerLockMem($n)"){
    val memCU = within(pirTop) { MemoryContainer() }
    swapParent(n, memCU)
    val lockMap = n.accesses.groupBy { access => access.as[LockAccess].lock.T }
    val lastAccesses = lockMap.flatMap {
      case (None, accesses) => None
      case (Some(lock), accesses) => Some(accesses.maxBy { _.order.get })
    }.toSet
    dbg(s"lastAccesses=${lastAccesses.mkString(",")}")
    n.accesses.foreach { access =>
      lowerAccess(n, memCU, access.as[LockAccess], lastAccesses.contains(access))
    }
    sequencedScheduleBarrierInsertion(n)
    n.accesses.foreach { access =>
      val ctx = access.ctx.get
      bufferInput(ctx, fromCtx=addrCtxs.get(access.getCtrl))
    }
    addrCtxs.clear
  }

  private def lowerAccess(mem:Memory, memCU:MemoryContainer, access:LockAccess, isLast:Boolean) = {
    val ctrl = access.ctx.get.getCtrl
    // Setting up address calculation
    val addrCtx = addrCtxs.getOrElseUpdate(ctrl, {
      // Optimization. Put address calculation in PMU of doesn't have lock
      val cu = if (access.lock.isConnected) pirTop else memCU
      within(cu, ctrl) { Context() }
    })
    swapParent(access, addrCtx)
    var addr = access.addr.singleConnected.get
    within(addrCtx, ctrl) {
      flattenEnable(access)
      access.en.singleConnected.foreach { en =>
        addr = stage(OpDef(Mux).addInput(en, addr, allocConst(-1).out).out)
      }
      access.en.disconnect
    }
    // Setting up splitter and lock if it's sparse access
    val pack = access.lock.T.map { lockOn =>
      val lock = lockOn.lock.T
      val key = lockOn.key.singleConnected.get
      val (splitAddr, splitKey, splitCtx) = allocateSplitter(ctrl, addr, key)
      bufferInput(splitCtx, fromCtx=Some(addrCtx))
      addr = splitAddr
      if (!canReach(lock.lock,splitKey)) {
        lock.lock(splitKey)
        bufferInput(lock.lock, fromCtx=Some(splitCtx))
      }
      swapConnection(access.lock, lockOn.out, lock.out)
      (splitCtx, lock, splitKey)
    }
    val splitCtx = pack.map { _._1 }
    val lock = pack.map { _._2 }
    val splitKey = pack.map { _._3 }
    // Setting up access within PMU
    val accessCtx = within(memCU, ctrl) { Context().streaming(true) }
    swapParent(access, accessCtx)
    swapConnection(access.addr, access.addr.singleConnected.get, addr)
    bufferInput(access.addr, fromCtx=Some(splitCtx.getOrElse(addrCtx)))
    bufferInput(accessCtx)
    if (isLast) {
      val mergeCtx = within(memCU, ctrl) { Context().streaming(true) }
      val dep = access match {
        case access:LockRead => access.out
        case access:LockWrite => access.ack
      }
      val unlock = within(mergeCtx, ctrl) {
        stage(Forward().in(splitKey.get).dummy(dep)).out
      }
      bufferInput(mergeCtx)
      lock.foreach { lock =>
        lock.unlock(unlock)
        bufferInput(lock.unlock)
      }
    }
    // Setting up connection with PCU
    access.to[LockRead].foreach { access =>
      access.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx,ins) =>
        val in::rest = ins
        val read = bufferInput(in).head
        rest.foreach { in =>
          swapConnection(in, access.out, read.out)
        }
      }
    }
  }

  def allocateSplitter(ctrl:ControlTree, addr:Output[PIRNode], key:Output[PIRNode]) = {
    val ctx = splitCtxs.getOrElseUpdate(ctrl, {
      val ctx = within(pirTop, ctrl) { Context().streaming(true) }
      within(ctx, ctrl) {
        Splitter()
      }
      ctx
    })
    val splitter = ctx.collectFirstChild[Splitter].get
    val splitAddr = allocAddr(splitter, addr)
    val splitKey = allocAddr(splitter, key)
    stage(splitter)
    (splitAddr, splitKey, ctx)
  }

  def allocAddr(splitter:Splitter, addr:Output[PIRNode]) = {
    splitter.addrIn.find { canReach(_,addr) }.fold {
      splitter.addAddrIn(addr).addAddrOut(1).head
    } { addrIn =>
      splitter.addrOut(splitter.addrIn.indexOf(addrIn))
    }
  }
}
