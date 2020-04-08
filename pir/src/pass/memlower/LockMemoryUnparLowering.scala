
package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryUnparLoweirng extends GenericMemoryLowering { self:LockMemoryLowering =>
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
    accesses.foreach { access =>
      lowerAccess(n, memCU, access, lastAccesses.contains(access))
    }
    val syncAccesses = accesses.groupBy { _.getCtrl }.flatMap { case (ctrl, accesses) =>
      val (locked, unlocked) = accesses.partition { _.lock.isConnected }
      unlocked ++ locked.headOption
    }.toList
    consistencyBarrier(syncAccesses)(dependsOn){ case (from,to,carried,depth) =>
      insertToken(from.ctx.get, to.ctx.get).depth(depth)
    }
    accesses.foreach { access =>
      val ctx = access.ctx.get
      bufferInput(ctx, BufferParam(fromCtx=addrCtxs.get(access.getCtrl)))
    }
    addrCtxs.clear
  }

  private def dependsOn(deped:Access, dep:Access):Option[Int] = {
    val lca = leastCommonAncesstor(deped.getCtrl, dep.getCtrl).get
    lca.schedule match {
      case Fork => return None
      case ForkJoin => return None
      case _ =>
    }
    val carried = dep.progorder.get > deped.progorder.get
    if (!carried) Some(1) else None
  }

  private def lowerAccess(mem:Memory, memCU:MemoryContainer, access:LockAccess, isLast:Boolean) = {
    val isSplitAccess = access.lock.isConnected
    val ctrl = access.ctx.get.getCtrl
    // Setting up address calculation
    val addrCtx = addrCtxs.getOrElseUpdate(ctrl, {
      // Optimization. Put address calculation in PMU if doesn't have lock
      val cu = if (isSplitAccess) pirTop else memCU
      within(cu, ctrl) { stage(Context()) }
    })
    swapParent(access, addrCtx)
    flattenEnable(access)
    var addr = access.addr.singleConnected.get
    // Setting up splitter and lock if it's sparse access
    val pack = access.lock.T.map { lockOn =>
      val lock = lockOn.lock.T
      val key = lockOn.key.singleConnected.get
      val (splitAddr, splitKey, splitCtx) = allocateSplitter(ctrl, addr, key)
      bufferInput(splitCtx, BufferParam(fromCtx=Some(addrCtx)))
      addr = splitAddr
      if (!canReach(lock.lock,splitKey)) {
        lock.lock(splitKey)
        setSplit(true) {
          bufferInput(lock.lock, BufferParam(fromCtx=Some(splitCtx)))
        }
      }
      swapConnection(access.lock, lockOn.out, lock.out)
      (splitCtx, lock, splitKey)
    }
    val splitCtx = pack.map { _._1 }
    val lock = pack.map { _._2 }
    val splitKey = pack.map { _._3 }
    // Setting up access within PMU
    val accessCtx = within(memCU, ctrl) { stage(Context().streaming(true)) }
    swapParent(access, accessCtx)
    swapConnection(access.addr, access.addr.singleConnected.get, addr)
    setSplit(isSplitAccess) {
      bufferInput(access.addr, BufferParam(fromCtx=Some(splitCtx.getOrElse(addrCtx))))
      bufferInput(accessCtx)
    }
    if (isLast) {
      val mergeCtx = within(memCU, ctrl) { stage(Context().streaming(true)) }
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
        setSplit(true) { bufferInput(lock.unlock).map { _.setSrcCtx("unlock") } }
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
  private def setSplit[T<:LocalOutAccess](isSplit:Boolean)(alloc: => Seq[T]):Seq[T] = {
    val saved = _isSplit
    _isSplit = isSplit
    val reads = alloc.map { read =>
      read.isSplit := _isSplit
      read.inAccess.isSplit := _isSplit
      read
    }
    _isSplit = saved
    reads
  }

  override def childDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (_isSplit) {
      val ctrler = if (ctx.streaming.get) {
        within(ctx, ctrl) { 
          allocate[UnitController]()(stage(UnitController().par(1)))
        }
      } else {
        assert(!compiler.hasRun[DependencyDuplication])
        // Centralized controller
        ctrl.ctrler.get
      }
      ctrler.stepped
    } else {
      super.childDone(ctrl, ctx)
    }
  }

  private def allocateSplitter(ctrl:ControlTree, addr:Output[PIRNode], key:Output[PIRNode]) = {
    val ctx = splitCtxs.getOrElseUpdate(ctrl, {
      val ctx = within(pirTop, ctrl) { stage(Context().streaming(true)) }
      within(ctx, ctrl) {
        stage(Splitter())
      }
      ctx
    })
    val splitter = ctx.collectFirstChild[Splitter].get
    val splitAddr = allocAddr(splitter, addr)
    val splitKey = allocAddr(splitter, key)
    stage(splitter)
    (splitAddr, splitKey, ctx)
  }

  private def allocAddr(splitter:Splitter, addr:Output[PIRNode]) = {
    splitter.addrIn.find { canReach(_,addr) }.fold {
      splitter.addAddrIn(addr).addAddrOut(1).head
    } { addrIn =>
      splitter.addrOut(splitter.addrIn.indexOf(addrIn))
    }
  }
}
