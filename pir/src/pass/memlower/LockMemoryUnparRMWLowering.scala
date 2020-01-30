package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryUnparRMWLoweirng extends GenericMemoryLowering { self:LockMemoryLowering =>
  protected def lowerUnparRMW(n:Memory) = dbgblk(s"lowerUnparRMW($n)"){
    val memCU = within(pirTop) { MemoryContainer() }
    swapParent(n, memCU)

    val sortedAccesses = n.accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => assertOne(as, s"UnparRMW has outer par ${n} $as").as[LockAccess] }

    val sortedGroupedAccesses = sortedAccesses.groupBy { _.getCtrl }
      .toList.sortBy { _._1.progorder.get }.map { 
        case (ctrl,List(access)) if access.isUnlockedAccess => lowerUnlockAccess(memCU, ctrl, access)
        case (ctrl, List(read:LockRead, write:LockWrite)) =>  lowerRMW(memCU, ctrl, read, write)
        case (ctrl, accesses) => err(s"Unexpected lock access ${quoteSrcCtx(ctrl)}")
      }
  }

  private def lowerUnlockAccess(memCU:MemoryContainer, ctrl:ControlTree, access:LockAccess) = {
    access match {
      case access:LockWrite =>
        within(memCU, ctrl) {
          flattenEnable(access)
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(accessCtx)
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = access.ack
          (req,resp)
        }
      case access:LockRead =>
        within(memCU, ctrl) {
          val addrCtx = stage(Context())
          swapParent(access, addrCtx)
          flattenEnable(access)
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(access.addr, fromCtx=Some(addrCtx))
          access.out.connected.foreach { in =>
            bufferInput(in)
          }
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = access.out
          (req,resp)
        }
    }
    breakPoint(s"$access")
  }

  private def lowerRMW(memCU:MemoryContainer, ctrl:ControlTree, read:LockRead, write:LockWrite) = {
    within(memCU, ctrl) {
      flattenEnable(write)
      val accessCtx = stage(Context().streaming(true))
      val exps = read.out.accumTill[LockWrite]().filterNot { case access:Access => true; case _ => false }
      val exp = assertOne(exps, s"more than one op for rmw ${exps}")
      val inputs = exp.localIns.flatMap { _.connected }.filterNot { _ == read.out }
      val input = assertOne(inputs, s"$read $write RMW.input")
      val rmw = within(accessCtx) {
        stage(LockRMW(exp)
          .addr(read.addr.connected)
          .input(input)
          .en(write.en.connected)
        )
      }
      rmw.order.reset
      rmw.mirrorMetas(read)
      rmw.order.reset
      rmw.muxPort.reset
      rmw.mirrorMetas(write)
      val writeCtx = write.ctx.get
      bufferInput(rmw.addr, fromCtx=Some(writeCtx))
      bufferInput(rmw.input, fromCtx=Some(writeCtx))
      write.mem.disconnect
      free(write)
      val req = rmw.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
      val resp = rmw.ack
      breakPoint(s"$read $write $rmw")
      (req,resp)
    }
  }

}
