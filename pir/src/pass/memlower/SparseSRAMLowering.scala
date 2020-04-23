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
    val memCU = within(pirTop,n.srcCtx.get) { stage(MemoryContainer()) }
    swapParent(n, memCU)
    dbg(s"accesses=${n.accesses}")
    n.accesses.foreach { access =>
      accessReqResp += lowerSparseAccess(access.as[SparseAccess], memCU)
    }
  }

  private def lowerSparseAccess(access:SparseAccess, memCU:MemoryContainer) = dbgblk(s"lower($access)"){
    access.barriers.get.foreach { 
      case (barrier,true) => 
        dbg(s"Adding $barrier to write")
        barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
      case (barrier,false) => 
        dbg(s"Adding $barrier to read")
        barrierRead.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
    }
    val ctrl = access.getCtrl
    access match {
      case access:SparseWrite =>
        within(memCU, ctrl,access.srcCtx.get) {
          flattenEnable(access) // in write ctx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          if (access.data.T.getCtrl != ctrl || access.addr.T.getCtrl != ctrl) {
            val addrCtx = stage(Context().name("addrCtx"))
            bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "addr" }
            bufferInput(access.data, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "data" }
          } else {
            bufferInput(access.addr).foreach { _.name := "addr" }
            bufferInput(access.data).foreach { _.name := "data" }
          }
          bufferInput(accessCtx)
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val ackCtx = stage(Context().name("ackCtx"))
          val accumAck = within(ackCtx, ctrl) {
            stage(AccumAck().ack(access.ack))
          }
          bufferInput(accumAck.ack).foreach { _.name := "ack" }
          access -> (req,accumAck.out)
        }
      case access:SparseRead =>
        within(memCU, ctrl, access.srcCtx.get) {
          val addrCtx = stage(Context().name("addrCtx"))
          swapParent(access, addrCtx)
          flattenEnable(access) // in addrCtx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "addr" }
          val ins = access.out.connected
          ins.distinct.foreach { in =>
            bufferInput(in).foreach { read => read.inAccess.name := "readOut" }
          }
          val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = reads.headOption.getOrElse(err(s"${quoteSrcCtx(access)} is not used by anyone!")).out
          access -> (req,resp)
        }
      case access:SparseRMW =>
        within(memCU, ctrl, access.srcCtx.get) {
          flattenEnable(access) // in wirte ctx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          if (access.addr.T.getCtrl != ctrl || access.input.T.getCtrl != ctrl || access.remoteAddr) {
            val addrCtx = stage(Context().name("addrCtx"))
            bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "addr" }
            bufferInput(access.input, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "input" }
          } else {
            bufferInput(access.addr).foreach { _.name := "addr" }
            bufferInput(access.input).foreach { _.name := "input" }
          }

          val ins = access.dataOut.connected
          ins.distinct.foreach { in =>
            bufferInput(in).foreach { read => read.inAccess.name := "readOut" }
          }
          val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
          val resp = reads.headOption.map { _.out }.getOrElse {
            val ackCtx = stage(Context().name("ackCtx"))
            val accumAck = within(ackCtx, ctrl) {
              stage(AccumAck().ack(access.dataOut))
            }
            bufferInput(accumAck.ack)
            accumAck.out
          }
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          access -> (req,resp)
        }
    }
  }

}
