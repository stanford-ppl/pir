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
    n.accesses.foreach { access =>
      lowerAccess(n, memCU, access.as[LockAccess])
    }
    sequencedScheduleBarrierInsertion(n)
    n.accesses.foreach { access =>
      val ctx = access.ctx.get
      bufferInput(ctx, fromCtx=addrCtxs.get(access.getCtrl))
    }
    addrCtxs.clear
  }

  private def lowerAccess(mem:Memory, memCU:MemoryContainer, access:LockAccess) = {
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
    val (splitCtx) = access.lock.T.map { lockOn =>
      val lock = lockOn.lock.T
      val key = lockOn.key.singleConnected.get
      val (splitAddr, splitKey, splitCtx) = allocateSplitter(ctrl, addr, key)
      bufferInput(splitCtx, fromCtx=Some(addrCtx))
      addr = splitAddr
      if (!canReach(lock.key,splitKey)) {
        lock.key(splitKey)
        bufferInput(lock.key, fromCtx=Some(splitCtx))
      }
      swapConnection(access.lock, lockOn.out, lock.out)
      val lockCtx = lock.ctx.get
      bufferInput(access.lock)
      splitCtx
    }
    // Setting up access within PMU
    val accessCtx = within(memCU, ctrl) { Context().streaming(true) }
    swapParent(access, accessCtx)
    swapConnection(access.addr, access.addr.singleConnected.get, addr)
    bufferInput(access.addr, fromCtx=Some(splitCtx.getOrElse(addrCtx)))
    bufferInput(accessCtx)
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
