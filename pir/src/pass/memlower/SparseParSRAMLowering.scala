package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseParSRAMLowering extends SparseLowering {

  override def visitNode(n:N) = n match {
    case n@SparseMem("ParSRAM",_) => lowerSparseParSRAM(n)
    case _ => super.visitNode(n)
  }
  
  private def lowerSparseParSRAM(n:SparseMem):Unit = dbgblk(s"lower($n)"){
    val sortedAccesses:List[UnrolledAccess[SparseAccess]] = n.accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => UnrolledAccess(as.as[List[SparseAccess]].sortBy { _.order.get }) }

    val ctrl = leastCommonAncesstor(n.accesses.map { _.getCtrl }).get
    val block = within(pirTop, ctrl, n.srcCtx.get) {
      val ctx = stage(Context().streaming(true)) 
      within(ctx) {
        stage(SparseParSRAMBlock(n.dramPar))
      }
    }
    block.mirrorMetas(n)

    n.alias.reset
    free(n)
  }

  private def lowerAccess(uaccess:UnrolledAccess[SparseAccess], block:SparseParSRAMBlock) = dbgblk(s"lowerAccess($uaccess)"){
    val par = uaccess.lanes.size
    val accessid = uaccess.lanes.head.id
    uaccess.lanes.map { access =>
      access.barriers.get.foreach { 
        case (barrier,true) => barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
        case (barrier,false) => barrierRead.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
      }
      val ctrl = access.getCtrl
      val reqresp = within(access.srcCtx.get) {
        access match {
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
                within(ctx) {
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
      }
      free(access)
      reqresp
    }
  }

}
