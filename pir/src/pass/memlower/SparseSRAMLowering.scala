package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseSRAMLowering extends SparseLowering {

  override def visitNode(n:N) = n match {
    case n@SparseMem(false,_) => lowerSparseSRAM(n)
    case _ => super.visitNode(n)
  }
  
  private def lowerSparseSRAM(n:SparseMem):Unit = dbgblk(s"lower($n)"){
    val memCU = within(pirTop) { stage(MemoryContainer()) }
    swapParent(n, memCU)
    n.accesses.foreach { access =>
      accessReqResp += lowerSparseAccess(access.as[SparseAccess], memCU)
    }
  }

  private def lowerSparseAccess(access:SparseAccess, memCU:MemoryContainer) = dbgblk(s"lower($access)"){
    access.barriers.get.foreach { 
      case (barrier,true) => barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
      case (barrier,false) => barrierRead.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
    }
    val ctrl = access.getCtrl
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
            stage(AccumAck().ack(access.dataOut))
          }
          bufferInput(accumAck.ack)
          access -> (req,accumAck.out)
        }
    }
  }

}
