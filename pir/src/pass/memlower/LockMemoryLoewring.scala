package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryLowering extends GenericMemoryLowering {
  override def finPass = {
    splitCtxs.clear
    super.finPass
  }

  private val splitCtxs = mutable.Map[ControlTree, Context]()
  private val addrCtxs = mutable.Map[ControlTree, Context]()
  protected def lowerUnparLockMem(n:Memory) = dbgblk(s"lowerUnparLockMem($n)"){
    val memCU = within(pirTop) { MemoryContainer() }
    swapParent(n, memCU)
    val accesses = n.accesses.as[List[LockAccess]]
    val lockMap = accesses.groupBy { access => access.lock.T }
    val lastAccesses = lockMap.flatMap {
      case (None, accesses) => None
      case (Some(lock), accesses) => Some(accesses.maxBy { _.order.get })
    }.toSet
    dbg(s"lastAccesses=${lastAccesses.mkString(",")}")
    withLive(accesses:_*) {
      accesses.foreach { access =>
        lowerAccess(n, memCU, access, lastAccesses.contains(access))
      }
      val syncAccesses = accesses.groupBy { _.getCtrl }.flatMap { case (ctrl, accesses) =>
        val (locked, unlocked) = accesses.partition { _.lock.isConnected }
        unlocked ++ locked.headOption
      }.toList
      consistencyBarrier(syncAccesses) { case (deped, dep) =>
        val carried = dep.progorder.get > deped.progorder.get
        !carried
      }{ case (from,to,carried) =>
        insertToken(from.ctx.get, to.ctx.get).depth(1)
      }
      accesses.foreach { access =>
        val ctx = access.ctx.get
        bufferInput(ctx, fromCtx=addrCtxs.get(access.getCtrl))
      }
    }
    free(accesses)
    addrCtxs.clear
  }

  private def lowerAccess(mem:Memory, memCU:MemoryContainer, access:LockAccess, isLast:Boolean) = {
    val isSplitAccess = access.lock.isConnected
    val ctrl = access.ctx.get.getCtrl
    // Setting up address calculation
    val addrCtx = addrCtxs.getOrElseUpdate(ctrl, {
      // Optimization. Put address calculation in PMU of doesn't have lock
      val cu = if (isSplitAccess) pirTop else memCU
      within(cu, ctrl) { Context() }
    })
    swapParent(access, addrCtx)
    flattenEnable(access)
    var addr = access.addr.singleConnected.get
    // Setting up splitter and lock if it's sparse access
    val pack = access.lock.T.map { lockOn =>
      val lock = lockOn.lock.T
      val key = lockOn.key.singleConnected.get
      val (splitAddr, splitKey, splitCtx) = allocateSplitter(ctrl, addr, key)
      bufferInput(splitCtx, fromCtx=Some(addrCtx))
      addr = splitAddr
      if (!canReach(lock.lock,splitKey)) {
        lock.lock(splitKey)
        setSplit(true) {
          bufferInput(lock.lock, fromCtx=Some(splitCtx))
        }
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
    setSplit(isSplitAccess) {
      bufferInput(access.addr, fromCtx=Some(splitCtx.getOrElse(addrCtx)))
      bufferInput(accessCtx)
    }
    if (isLast) {
      val mergeCtx = within(memCU, ctrl) { Context().streaming(true) }
      val dep = access match {
        case access:LockRead => access.out
        case access:LockWrite => access.ack
      }
      val unlock = within(mergeCtx, ctrl) {
        stage(Forward().in(splitKey.get).dummy(dep)).out
      }
      setSplit(true) { bufferInput(mergeCtx) }
      lock.foreach { lock =>
        lock.unlock(unlock)
        setSplit(true) { bufferInput(lock.unlock) }
      }
    }
    // Setting up connection with PCU
    access.to[LockRead].foreach { access =>
      access.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx,ins) =>
        val in::rest = ins
        val read = setSplit(isSplitAccess) { bufferInput(in) }.head
        rest.foreach { in =>
          swapConnection(in, access.out, read.out)
        }
      }
    }
  }

  private var _isSplit = false
  def setSplit[T<:LocalOutAccess](isSplit:Boolean)(alloc: => Seq[T]):Seq[T] = {
    val saved = _isSplit
    _isSplit = isSplit
    val reads = alloc
    _isSplit = saved
    reads
  }

  override def insertBuffer(depOut:Output[PIRNode], depedIn:Input[PIRNode], fromCtx:Option[Context]=None, isFIFO:Boolean=true):Option[BufferRead] = {
    super.insertBuffer(depOut,depedIn,fromCtx,isFIFO).map { read =>
      read.isSplit := _isSplit
      read.inAccess.isSplit := _isSplit
      read
    }
  }

  override def childDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    val ctrler = if (ctx.streaming.get) {
      within(ctx, ctrl) { 
        allocate[UnitController]()(stage(UnitController().par(1)))
      }
    } else {
      assert(!compiler.hasRun[DependencyDuplication])
      // Centralized controller
      ctrl.ctrler.get
    }
    if (_isSplit)
      ctrler.stepped
    else
      ctrler.childDone
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
