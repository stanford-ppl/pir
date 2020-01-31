package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryLowering extends GenericMemoryLowering 
  with LockMemoryUnparRMWLoweirng 
  with LockMemoryUnparLoweirng 
  with LockMemoryBackBoxLowering {

  override def visitNode(n:N) = n match {
    case n:Lock => lowerPattern(n)
    case n:LockMem =>
    case _ => super.visitNode(n)
  }

  def lowerPattern(n:Lock):Unit = {
    dbg(s"lowerPattern($n)")
    val lockOns = n.out.T.as[List[LockOnKeys]]
    val lockAccesses = lockOns.flatMap { _.out.T }.as[List[LockAccess]].toSet
    val lockMems = lockAccesses.map { _.mem.T }.toList.sortBy { _.progorder.get }.as[List[LockMem]]
    val accesses = lockMems.flatMap { _.accesses.as[List[LockAccess]] }.distinct
    val lockKeys = lockOns.map { _.key.T }.distinct
    val preunrollKey = lockKeys.map { _.progorder.v }.distinct
    if (preunrollKey.size > 1) err(s"Multiple locked keys ${lockKeys.map { quoteSrcCtx }}")

    // A list of list accesses, each list contains unrolled accesses belong to the same
    // preunrolled access.
    // Each list of unrolled access is sorted by their unrolling order
    val sortedAccesses:List[UnrolledAccess[LockAccess]] = accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => UnrolledAccess(as.sortBy { _.order.get }) }

    // List of InnerAccessGroup sorted by program order on the same lock
    val sortedGroupedAccesses:List[InnerAccessGroup] = sortedAccesses.groupBy { ua => ua.lanes.head.getCtrl }
      .toList.sortBy { _._1.progorder.get }.map { case (ctrl:ControlTree,accesses:List[UnrolledAccess[LockAccess]]) => 
        InnerAccessGroup(ctrl,accesses)
      }

    dbgblk(s"sortedGroupedAccesses") {
      sortedGroupedAccesses.foreach { ig =>
        dbg(s"$ig")
      }
    }

    val noOuterPar = sortedAccesses.forall { ig => ig.lanes.size == 1 }
    val isDRAM = assertUnify(lockMems, s"Cannot have both DRAM and SRAM on the same lock ${quoteSrcCtx(n)}") { _.isDRAM }.get

    val (lockConsis, lockInconsist) = sortedGroupedAccesses.partition { _.isLockConsist }
    if (lockInconsist.nonEmpty) 
      err(s"Cannot have unlocked and locked statement in the same basic block: ${quoteSrcCtx(lockInconsist)}}")

    val rmws = sortedGroupedAccesses.collect { case rmw:InnerAccessRMW => rmw }
    val others = sortedGroupedAccesses.collect { case ig:InnerAccessGroup if !ig.isInstanceOf[InnerAccessRMW] => ig }
    val (locked, unlocked) = others.partition { _.isLockedAccess }
    if (locked.nonEmpty) err(s"Do not handle locked non read modify write accesses ${quoteSrcCtx(others)}")
    if (rmws.size > 1) err(s"Cannot have more than 1 read modify write block ${quoteSrcCtx(rmws)}")

    //if (noOuterPar && !isDRAM && rmws.size == 1) return mems.map { mem => lowerUnparRMW(mem) }
    def specialRMW(exps:List[PIRNode]) = exps match {
      case List(exp@OpDef(FixAdd | FixMul | FltAdd)) => true
      case _ => false
    }
    if (noOuterPar && !isDRAM && rmws.forall { _.accums.forall { case (mem, read, write, exps) => specialRMW(exps) } }) {
      lockMems.foreach { mem => lowerUnparRMW(mem) }
      return
    }
    if (noOuterPar && lockMems.size == 1 && !isDRAM) return lowerUnparLockMem(lockMems.head)

    if (rmws.size == 1) {
      val rmwpar = rmws.head.addrs.size
      unlocked.foreach { case InnerAccessGroup(ctrl,accesses) =>
        //group.foreach { case InnerAccessMemGroup(mem, accesses) =>
          accesses.foreach { case UnrolledAccess(lanes) =>
            val par = lanes.size
            if (par > 1 && par != rmwpar)
              err(s"Cannot parallelize the unlocked accesses ${quoteSrcCtx(lanes.head)} with different par with locked access for ${quoteSrcCtx(n)} ")
          }
        //}
      }
      assertUnify(lockMems, s"${quoteSrcCtx(n)} have different types for read modify accumulators") { mem => mem.tp.get }
      assertUnify(lockMems, s"${quoteSrcCtx(n)} have different dimensions for read modify accumulators") { mem => mem.dims.get }
      return lowerRMW(n, rmwpar, lockMems, sortedGroupedAccesses)
    } else {
      err(s"Unsupported access pattern ${quoteSrcCtx(n)}")
    }
  }

  override def quoteSrcCtx(x:Any):String = x match {
    case UnrolledAccess(lanes) => quoteSrcCtx(lanes.head)
    case x:InnerAccessRMW => quoteSrcCtx(x.ctrl)
    case InnerAccessGroup(ctrl, group) => quoteSrcCtx(ctrl)
    case l:List[_] => l.map { quoteSrcCtx }.mkString("\n")
    case x => super.quoteSrcCtx(x)
  }

  implicit class UnrolledAccessUtil(n:UnrolledAccess[LockAccess]) {
    def isLockedAccess = n.lanes.head.isLockedAccess
    def isLockedRead = isLockedAccess && n.lanes.head.isRead
    def isLockedWrite = isLockedAccess && n.lanes.head.isWrite
    def isUnlockedAccess = !isLockedAccess
  }

  implicit class LockAccessUtil(n:LockAccess) {
    def isLockedAccess = n.lock.isConnected
    def isUnlockedAccess = !isLockedAccess
  }

  /*
   * A list of accesses belonging to the same basic block/inner loop
   * */
  class InnerAccessGroup(val ctrl:ControlTree, val accesses:List[UnrolledAccess[LockAccess]]) {
    def isUnlockedAccess = accesses.forall { _.isUnlockedAccess }
    def isLockedAccess = accesses.forall { _.isLockedAccess }
    /*
     * returns whether accesses within a basic block is all locked or all unlocked
     * */
    def isLockConsist = accesses.forall { a => a.isUnlockedAccess | a.isLockedAccess }
  }

  object InnerAccessGroup {
    def apply(ctrl:ControlTree, accesses:List[UnrolledAccess[LockAccess]]):InnerAccessGroup = {
      val as = accesses.groupBy { _.lanes.head.mem.T.as[LockMem] }.map { 
        case (mem, List(a,b)) =>
          val isLockRMW = a.isLockedRead && b.isLockedWrite && a.lanes.head.addr.T == b.lanes.head.addr.T
          if (isLockRMW) {
            val addrs:List[PIRNode] = a.lanes.map { _.addr.T }
            val exps:List[PIRNode] = a.lanes.head.as[LockRead].out.accumTill[LockWrite]().filterNot { case access:Access => true; case _ => false }
            (mem,a,b,addrs,exps)
          } else {
            return new InnerAccessGroup(ctrl, accesses)
          }
        case _ => return new InnerAccessGroup(ctrl, accesses)
      }.toList
      val addrs:List[List[PIRNode]] = as.map { case (mem, read, write, addrs, exps) => addrs }
      testIdentical(addrs) match {
        case Some(addr) => 
          InnerAccessRMW(ctrl, addr, as.map { case (mem, read, write, addr, exps) => (mem, read, write, exps) })
        case None => new InnerAccessGroup(ctrl, accesses)
      }
    }
    def unapply(x:InnerAccessGroup) = Some((x.ctrl, x.accesses))
  }

  case class InnerAccessRMW (
    override val ctrl:ControlTree,
    addrs:List[PIRNode], 
    // (memory, reads, write, rmwexp)
    // rmwexp: expression for read modify write in first outer lane
    accums:List[(Memory, UnrolledAccess[LockAccess], UnrolledAccess[LockAccess], List[PIRNode])],
  ) extends InnerAccessGroup(ctrl, accums.flatMap { case (mem, read, write, exp) => List(read, write) }) {
  }

}
