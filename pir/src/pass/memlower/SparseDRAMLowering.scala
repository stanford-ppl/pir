package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseDRAMLowering extends SparseLowering {

  override def visitNode(n:N) = n match {
    case n@SparseMem(true,_) => lowerSparseDRAM(n)
    case _ => super.visitNode(n)
  }
  
  private def lowerSparseDRAM(n:SparseMem):Unit = dbgblk(s"lower($n)"){
    val sortedAccesses:List[UnrolledAccess[SparseAccess]] = n.accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => UnrolledAccess(as.as[List[SparseAccess]].sortBy { _.order.get }) }

    val ctrl = leastCommonAncesstor(n.accesses.map { _.getCtrl }).get
    val block = within(pirTop, ctrl) {
      val ctx = stage(Context().streaming(true)) 
      within(ctx) {
        stage(SparseDRAMBlock(n.dramPar))
      }
    }
    block.mirrorMetas(n)

    val naccess = sortedAccesses.size
    sortedAccesses.zipWithIndex.foreach { case (ua, i) =>
      var synchronizeWithHost = false
      if (i==naccess-1) { // Last access
        synchronizeWithHost = ua.lanes.head match {
          case access:SparseRead => false
          case access:SparseRMW => !access.dataOut.isConnected
          case access:SparseWrite => true
        }
      }
      accessReqResp ++= lowerAccess(ua, block)
      if (synchronizeWithHost) {
        insertTokenToHost(ua)
      }
    }
    n.alias.reset
    free(n)
  }

  private def lowerAccess(uaccess:UnrolledAccess[SparseAccess], block:SparseDRAMBlock) = dbgblk(s"lowerAccess($uaccess)"){
    val par = uaccess.lanes.size
    val accessid = uaccess.lanes.head.id
    uaccess.lanes.map { access =>
      access.barriers.get.foreach { 
        case (barrier,true) => barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
        case (barrier,false) => barrierRead.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
      }
      val ctrl = access.getCtrl
      val reqresp = access match {
        case access:SparseRead =>
          val (readAddr, readData) = block.addReadPort(accessid)
          val addrCtx = within(pirTop, ctrl) {
            stage(Context().name("addrCtx"))
          }
          swapParent(access, addrCtx)
          flattenEnable(access) // in addrCtx
          readAddr(access.addr.connected)
          bufferInput(readAddr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "readAddr" }
          val ins = access.out.connected
          ins.foreach { in =>
            swapConnection(in, access.out, readData)
          }
          ins.distinct.foreach { in =>
            bufferInput(in).foreach { read => read.inAccess.name := "readOut" }
          }
          val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
          val req = readAddr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = reads.head.out
          access -> (req,resp)
        case access:SparseWrite =>
          val (writeAddr, writeData, writeAck) = block.addWritePort(accessid)
          flattenEnable(access) // in write ctx
          writeAddr(access.addr.connected)
          writeData(access.data.connected)
          bufferInput(writeAddr).foreach { _.name := "writeAddr" }
          bufferInput(writeData).foreach { _.name := "writeData" }
          val ackCtx = within(pirTop, ctrl) {
            stage(Context().name("ackCtx"))
          }
          val accumAck = within(ackCtx, ctrl) {
            stage(AccumAck().ack(writeAck))
          }
          access.mem.disconnect
          bufferInput(accumAck.ack).foreach { read => 
            read.name := "ack"
            read.inAccess.name := "ack"
          }
          val req = writeAddr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          access -> (req,accumAck.out)
        case access:SparseRMW =>
          val (rmwAddr, rmwDataIn, rmwDataOut) = block.addRMWPort(accessid, access.op, access.opOrder)
          flattenEnable(access) // in write ctx
          rmwAddr(access.addr.connected)
          rmwDataIn(access.input.connected)
          bufferInput(rmwAddr).foreach { _.name := "rmwAddr" }
          bufferInput(rmwDataIn).foreach { _.name := "rmwDataIn" }
          var ins = access.dataOut.connected
          if (ins.size == 0) {
            val accumAck = within(pirTop, access.getCtrl) {
              val ctx = stage(Context().name("RMW_ackAccum"))
              within(ctx, access.getCtrl) {
                stage(AccumAck().ack(access.dataOut))
              }
            }
            ins = List(accumAck.ack)
          }
          ins.foreach { in =>
            swapConnection(in, access.dataOut, rmwDataOut)
          }
          ins.distinct.foreach { in =>
            bufferInput(in).foreach { read => read.inAccess.name := "rmwDataOut" }
          }
          val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
          val req = rmwAddr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = reads.head.out
          access -> (req,resp)
      }
      free(access)
      reqresp
    }
  }

  def insertTokenToHost(ua:UnrolledAccess[SparseAccess]) = dbgblk(s"insertTokenToHost($ua)"){
    val hostOutCtx = pirTop.argFringe.collectFirst[HostOutController](visitDown _).ctx.get
    ua.lanes.foreach { access =>
      val resp = accessReqResp(access)._2
      val tokenRead = insertToken(resp.src.ctx.get, hostOutCtx, dep=Some(resp))
      within(hostOutCtx, hostOutCtx.getCtrl) {
        stage(HostRead().input(tokenRead.out))
      }
    }
  }

}
