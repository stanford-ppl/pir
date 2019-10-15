package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryBackBoxLowering extends GenericMemoryLowering {

  override def visitNode(n:N) = n match {
    case n:Lock => lowerLock(n)
    case n:Memory if n.isLockSRAM => 
    case _ => super.visitNode(n)
  }

  override def finPass = {
    splitCtxs.clear
    super.finPass
  }

  private def lowerLock(n:Lock) = {
    val lockOns = n.out.T.as[List[LockOnKeys]]
    val lockedAccesses = lockOns.flatMap { _.out.T }.as[List[LockAccess]]
    val lockMems = lockedAccesses.map { _.mem.T }.distinct
    dbg(s"lockMems=${lockMems}")
    lockMems.foreach { mem =>
      // sort by program order. 
      val sorted = mem.accesses.groupBy { _.progorder.get }.toList.sortBy { _._1 }
      dbgblk(s"sorted") {
        sorted.foreach { case (order, group) =>
          dbgblk(s"order $order") {
            group.foreach { access =>
              dbg(s"${quoteSrcCtx(access)} ${access.getCtrl} ${access.getCtrl.uid.get}")
            }
          }
        }
      }
    }
    //breakPoint(s"$lockMems")
  }

  private val splitCtxs = mutable.Map[ControlTree, Context]()
  private val addrCtxs = mutable.Map[ControlTree, Context]()
  private def lowerLockMem(n:Memory) = dbgblk(s"lowerLockMem($n)") {
  }

}

