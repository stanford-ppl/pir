package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseMemoryLowering extends GenericMemoryLowering {

  val accessReqResp = mutable.Map[Access, (Input[PIRNode], Output[PIRNode])]()
  val barrierWrite = mutable.Map[Barrier, mutable.ListBuffer[Access]]()
  val barrierRead = mutable.Map[Barrier, mutable.ListBuffer[Access]]()

  override def visitNode(n:N) = n match {
    case n@SparseMem(false) => lowerSparseSRAM(n)
    case _ => super.visitNode(n)
  }

  override def finPass = {
    barrierInsertion
    accessReqResp.clear
    barrierWrite.clear
    barrierRead.clear
  }
  
  private def barrierInsertion = {
    val barriers = barrierWrite.keys.toSet ++ barrierRead.keys
    barriers.foreach { barrier =>
      val barrierCtx = within(pirTop, barrier.ctrl) {
        stage(Context().name("barrier_ctx").streaming(true))
      }
      val writes = barrierWrite.get(barrier).getOrElse(mutable.ListBuffer.empty)
      val intokens:Iterable[Output[PIRNode]] = writes.map { writer =>
        val resp = accessReqResp(writer)._2
        insertToken(fctx=resp.src.ctx.get, tctx=barrierCtx, dep=Some(resp)).out
      }
      val merged = within(barrierCtx, barrier.ctrl) {
        intokens.reduce[Output[PIRNode]]{ case (out1, out2) =>
          stage(Forward().in(out1).dummy(out2)).out
        }
      }
      val reads = barrierRead.get(barrier).getOrElse(mutable.ListBuffer.empty)
      reads.foreach { access =>
        val req = accessReqResp(access)._1
        val reqctx = req.src.ctx.get
        val token = insertToken(fctx=barrierCtx, tctx=reqctx, dep=Some(merged))
        token.initToken := barrier.init
        token.inits := true
        val forward = within(reqctx, req.src.getCtrl) {
          stage(Forward().in(req.connected).dummy(token.out))
        }
        swapConnection(req, req.singleConnected.get, forward.out)
      }
      //breakPoint(s"barrier=$barrier ${writes} ${reads}")
    }
  }

  private def lowerSparseSRAM(n:SparseMem):Unit = dbgblk(s"lower($n)"){
    val memCU = within(pirTop) { stage(MemoryContainer()) }
    swapParent(n, memCU)
    val reqresp = n.accesses.map { access =>
      lowerSparseAccess(access.as[SparseAccess], memCU)
    }
    accessReqResp ++= reqresp
  }

  private def lowerSparseAccess(access:SparseAccess, memCU:MemoryContainer) = dbgblk(s"lower($access)"){
    val ctrl = access.getCtrl
    access.barriers.get.foreach { 
      case (barrier,true) => barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
      case (barrier,false) => barrierRead.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
    }
    access match {
      case access:SparseWrite =>
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
      case access:SparseRead =>
        within(memCU, ctrl) {
          val addrCtx = stage(Context().name("addrCtx"))
          swapParent(access, addrCtx)
          flattenEnable(access) // in addrCtx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx)))
          val reads = access.out.connected.flatMap { in =>
            bufferInput(in)
          }
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = reads.head.out
          access -> (req,resp)
        }
      case access:SparseRMW =>
        within(memCU, ctrl) {
          flattenEnable(access) // in wirte ctx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(access.addr)
          bufferInput(access.input)
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val ackCtx = stage(Context().name("ackCtx"))
          val accumAck = within(ackCtx, ctrl) {
            stage(AccumAck().ack(access.ack))
          }
          bufferInput(accumAck.ack)
          access -> (req,accumAck.out)
        }
    }
  }

}
