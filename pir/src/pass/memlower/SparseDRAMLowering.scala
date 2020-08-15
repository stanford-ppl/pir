package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseDRAMLowering extends SparseLowering {

  override def visitNode(n:N) = n match {
    case n@SparseMem(_, "ParDRAM",_) => lowerSparseDRAM(n)
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
  }
  
  private def lowerSparseDRAM(n:SparseMem):Unit = dbgblk(s"lower($n)"){
    dbg(s"accesses=${n.accesses}")
    val sortedAccesses:List[UnrolledAccess[SparseAccess]] = n.accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => UnrolledAccess(as.as[List[SparseAccess]].sortBy { _.order.get }) }
    dbg(s"accesses=${n.accesses}")

    val ctrl = leastCommonAncesstor(n.accesses.map { _.getCtrl }).get
    val block = within(pirTop, ctrl, n.srcCtx.get) {
      val ctx = stage(Context().streaming(true)) 
      within(ctx) {
        stage(SparseDRAMBlock(n.dramPar))
      }
    }
    dbg(s"accesses=${n.accesses}")
    block.mirrorMetas(n)
    dbg(s"accesses=${n.accesses}")

    if (n.autoBar.get) {
      dbg(s"Perform automatic barrier insertion for $n")
      consistencyBarrier(n.accesses)(dependsOn){ case (from,to,carried,depth) =>

        val stallTo = to.asInstanceOf[SparseAccess].barriers.get.map { bar =>
          bar match {
            case (barrier,true) => true // false
            case (barrier,false) => true
          }
        }.reduceOption({_|_}).getOrElse(false)
        if (stallTo || from.isInstanceOf[SparseRMWData] || to.isInstanceOf[SparseRMWData]) {
          dbg(s"Skip sparse barrier ${from}->${to}; user has already inserted a barrier holding back this memory")
        } else {
          dbg(s"Insert sparse barrier ${from}->${to}")
          val bar_init = if (carried) 1 else 0
          val ctrl = leastCommonAncesstor(to.getCtrl, from.getCtrl).get 
          val b = Barrier(ctrl, bar_init).name(s"__auto_bar_${from}_${to}")
          barrierWrite.getOrElseUpdate(b, mutable.ListBuffer()) += from
          barrierRead.getOrElseUpdate(b, mutable.ListBuffer()) += to
        }
      }
    } else {
      dbg(s"Skip automatic barrier insertion for $n")
    }

    val naccess = sortedAccesses.size
    sortedAccesses.zipWithIndex.foreach { case (ua, i) =>
      var synchronizeWithHost = false
      if (i==naccess-1) { // Last access
        synchronizeWithHost = ua.lanes.head match {
          case access:SparseRead => false
          case access:SparseRMW => (!access.dataOut.isConnected && access.key < 0)
          case access:SparseWrite => true
          case access:SparseRMWData => !access.dataOut.isConnected
        }
      }
      accessReqResp ++= lowerAccess(ua, block)
      /*lowerAccess(ua, block).foreach {
        acc =>
          acc match {
            case Some(x)  => accessReqResp += x
            case None => 
        }
      } */
      // accessReqResp ++= lowerAccess(ua, block)
      if (synchronizeWithHost) {
        insertTokenToHost(ua)
      }
    }
    dbg(s"accesses=${n.accesses}")
    n.alias.reset
    free(n)
  }

  private def lowerAccess(uaccess:UnrolledAccess[SparseAccess], block:SparseDRAMBlock) = dbgblk(s"lowerAccess($uaccess)"){
    val par = uaccess.lanes.size
    val accessid = uaccess.lanes.head.id
    uaccess.lanes.zipWithIndex.map { case (access, idx) =>
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
            access -> (Some(req),Some(resp))
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
            access -> (Some(req),Some(accumAck.out))
          case access:SparseRMW =>
            val (rmwAddr, rmwDataIn, rmwDataOut) = block.addRMWPort(accessid, access.op, access.opOrder)
            flattenEnable(access) // in write ctx
            rmwAddr(access.addr.connected)
            rmwDataIn(access.input.connected)
            bufferInput(rmwAddr).foreach { _.name := "rmwAddr" }
            bufferInput(rmwDataIn).foreach { _.name := "rmwDataIn" }
            if (access.key < 0) {
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
              access -> (Some(req),Some(resp))
            } else {
              rmwKeyIDMap += access.key -> (accessid)
              val req = rmwAddr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
              access -> (Some(req),None)
            }
          case access:SparseRMWData =>
            // TODO: fixme
            // access.forceVec(access.addr.inferVec.get)
            dbg(s"Lower SparseRMWData addr.vec: ${access.addr.inferVec.get}  input.vec: ${access.input.inferVec.get}")
            flattenEnable(access)
            val accessid_match = rmwKeyIDMap(access.key)
            val rmwDataOut = block.fakeRMWRead(accessid_match, idx)
            access.dataOut.vecMeta.reset
            access.dataOut.presetVec(access.addr.inferVec.get)
            rmwDataOut.vecMeta.reset
            rmwDataOut.presetVec(access.addr.inferVec.get)
            // access.en.vecMeta.reset
            // access.en.presetVec(access.addr.inferVec.get)
            // access.en.vecMeta.reset
            // access.en.presetVec(access.addr.inferVec.get)
            var ins = access.dataOut.connected
            // assert(ins.size > 0)
            if (ins.size == 0) {
              val accumAck = within(pirTop, access.getCtrl) {
                val ctx = stage(Context().name("RMWData_ackAccum"))
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
              // bufferInput(in).foreach { read => read.inAccess.name := "rmwDataOut"; read.out.vecMeta.reset; read.out.presetVec(access.addr.inferVec.get); read.en.vecMeta.reset; read.en.presetVec(access.addr.inferVec.get) }
              bufferInput(in).foreach { read => read.inAccess.name := "rmwDataOut"; read.out.vecMeta.reset; read.out.presetVec(access.addr.inferVec.get); read.en.disconnect; read.inAccess.en.disconnect }
            }
            val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
            val resp = reads.head.out
            access.addr.disconnect
            access.input.disconnect
            dbg(s"Lower SparseRMWData out vec: ${access.dataOut.inferVec.get}")
            access -> (None,Some(resp))
        }
      }
      free(access)
      reqresp
    }
  }

  def insertTokenToHost(ua:UnrolledAccess[SparseAccess]) = dbgblk(s"insertTokenToHost($ua)"){
    val hostOutCtx = pirTop.argFringe.collectFirst[HostOutController](visitDown _).ctx.get
    ua.lanes.foreach { access =>
      within(access.srcCtx.get) {
        val resp = accessReqResp(access)._2.get
        val tokenRead = insertToken(resp.src.ctx.get, hostOutCtx, dep=Some(resp))
        within(hostOutCtx, hostOutCtx.getCtrl) {
          stage(HostRead().input(tokenRead.out))
        }
      }
    }
  }

}
