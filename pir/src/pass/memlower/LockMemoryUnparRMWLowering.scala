package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LockMemoryUnparRMWLoweirng extends GenericMemoryLowering { self:LockMemoryLowering =>
  protected def lowerUnparRMW(n:Memory) = dbgblk(s"lowerUnparRMW($n)"){
    val memCU = within(pirTop) { stage(MemoryContainer()) }
    swapParent(n, memCU)

    val sortedAccesses = n.accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => assertOne(as, s"UnparRMW has outer par ${n} $as").as[LockAccess] }

    val sortedGroupedAccesses = sortedAccesses.groupBy { _.getCtrl }
      .toList.sortBy { _._1.progorder.get }

    val reqresp = sortedGroupedAccesses.map { 
      case (ctrl,List(access)) if access.isUnlockedAccess => lowerUnlockAccess(memCU, ctrl, access)
      case (ctrl, List(read:LockRead, write:LockWrite)) =>  lowerRMW(memCU, ctrl, read, write)
      case (ctrl, accesses) => err(s"Unexpected locked access ${quoteSrcCtx(ctrl)}")
    }.toMap

    consistencyBarrier(reqresp.keys.toList)(dependsOn){ case (from,to,carried,depth) =>
      insertBarrier(from, reqresp(from)._2, to, reqresp(to)._1, carried, depth)
    }
  }

  // No multibuffer. Every one depends on everyone
  private def dependsOn(deped:Access, dep:Access):Option[Int] = {
    val lca = leastCommonAncesstor(deped.getCtrl, dep.getCtrl).get
    lca.schedule match {
      case Fork => None
      case ForkJoin => None
      case _ => Some(1)
    }
  }

  private def insertBarrier(
    from:LockAccess, 
    fromresp:Output[PIRNode],
    to:LockAccess, 
    torqst:Input[PIRNode],
    carried:Boolean,
    depth:Int,
  ) = {
    val fromctx = fromresp.src.ctx.get
    val toctx = torqst.src.ctx.get
    val read = insertToken(fromctx, toctx, dep=Some(fromresp)).depth(depth)
    if (carried) {
      read.initToken := true
      read.inits := true
    }
    val original = torqst.singleConnected.get
    val forward = within(toctx, toctx.getCtrl) { stage(Forward().in(original).dummy(read.out)) }
    swapConnection(torqst, original, forward.out)
  }

  private def lowerUnlockAccess(memCU:MemoryContainer, ctrl:ControlTree, access:LockAccess) = {
    access match {
      case access:LockWrite =>
        within(memCU, ctrl) {
          flattenEnable(access) // in write ctx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(accessCtx)
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val ackCtx = stage(Context().name("ackCtx"))
          val accumAck = within(ackCtx, ctrl) {
            stage(AccumAck().ack(access.ack))
          }
          bufferInput(accumAck.ack)
          access -> (req,accumAck.out)
        }
      case access:LockRead =>
        within(memCU, ctrl) {
          val addrCtx = stage(Context().name("addrCtx"))
          swapParent(access, addrCtx)
          flattenEnable(access) // in addrCtx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(access.addr, fromCtx=Some(addrCtx))
          access.out.connected.foreach { in =>
            bufferInput(in)
          }
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = access.out
          access -> (req,resp)
        }
    }
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
        stage(LockRMW(exp.as[OpDef].op)
          .mem(write.mem.T)
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
      val ackCtx = stage(Context().name("ackCtx"))
      val accumAck = within(ackCtx, ctrl) {
        stage(AccumAck().ack(rmw.ack))
      }
      bufferInput(accumAck.ack)
      rmw -> (req,accumAck.out)
    }
  }

}
