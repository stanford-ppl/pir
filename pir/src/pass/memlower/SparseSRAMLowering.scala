package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseSRAMLowering extends SparseLowering {

  override def visitNode(n:N) = n match {
    case n@SparseMem(_, "SRAM",_) => lowerSparseSRAM(n)
    case _ => super.visitNode(n)
  }

  private def dependsOn(deped:Access, dep:Access):Option[Int] = {
    val lca = leastCommonAncesstor(deped.getCtrl, dep.getCtrl).get
    dbg(s"\t depedCtrl: ${dquote(deped.getCtrl)} depCtrl: ${dquote(dep.getCtrl)} lca: ${dquote(lca)}")
    lca.schedule match {
      case Fork => return None
      case ForkJoin => return None
      case _ => return Some(1)
    }
    Some(1) 
    /* val carried = dep.progorder.get > deped.progorder.get
    if (dep.getCtrl == deped.getCtrl) {
      // HACK to mem reduce. Allow writer and read to operate concurrently because we know they
      // don't overlap in range
      if (!carried) None else Some(2)
    } else {
      if (carried) None else {
        val depth = zipMap(deped.port.get, dep.port.get) { case (depedport, depport) =>
          depedport - depport + 1
        }.getOrElse(1)
        Some(depth)
      }
    } */
  }
  
  // private val addrCtxs = mutable.Map[Either[ControlTree, Access], Context]()
  // private val mergeCtxs = mutable.Map[Access, Context]()
  private def lowerSparseSRAM(n:SparseMem):Unit = dbgblk(s"lower($n)"){
    val memCU = within(pirTop,n.srcCtx.get) { stage(MemoryContainer()) }
    val init_ctrl = n.ctrlTree
    swapParent(n, memCU)
    dbg(s"accesses=${n.accesses}")
    n.accesses.foreach { access =>
      accessReqResp += lowerSparseAccess(access.as[SparseAccess], memCU)
    }
    consistencyBarrier(n.accesses)(dependsOn){ case (from,to,carried,depth) =>
      dbg(s"Insert sparse barrier ${from}->${to}")
      val bar_init = if (carried) 1 else 0
      // val ctrl = if (carried) leastCommonAncesstor(to.getCtrl, from.getCtrl).get else init_ctrl
      val ctrl = leastCommonAncesstor(to.getCtrl, from.getCtrl).get 
      val b = Barrier(ctrl, bar_init).name(s"__auto_bar_${from}_${to}")
      barrierWrite.getOrElseUpdate(b, mutable.ListBuffer()) += from
      barrierRead.getOrElseUpdate(b, mutable.ListBuffer()) += to
      // insertBarrier(from,to,carried,depth)
    }
    // n.accesses.foreach { access =>
      // val ctx = access.ctx.get
      // val key = if (config.shareAddrCtx) Left(access.getCtrl) else Right(access)
      // val fromCtx = if (config.mergeDone) {
        // mergeCtxs.get(access) orElse addrCtxs.get(key)
      // } else addrCtxs.get(key)
      // bufferInput(ctx, BufferParam(fromCtx=fromCtx))
    // }
    // addrCtxs.clear
    // mergeCtxs.clear
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
