package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseDRAMLowering extends SparseLowering {

  override def visitNode(n:N) = n match {
    case n@SparseMem(_, "ParDRAM",_) => lowerSparseDRAM(n, true)
    case n@SparseMem(_, "ParSRAM",_) => lowerSparseDRAM(n, false)
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
  
  private def lowerSparseDRAM(n:SparseMem, isDRAM:Boolean):Unit = dbgblk(s"lower($n, $isDRAM)"){
    dbg(s"accesses=${n.accesses}")
    val sortedAccesses:List[UnrolledAccess[SparseAccess]] = n.accesses.groupBy { a => a.progorder.get }.toList.sortBy { _._1 }
      .map { case (po, as) => UnrolledAccess(as.as[List[SparseAccess]].sortBy { _.order.get }) }
    dbg(s"accesses=${n.accesses}")

    dbg(s"srcCtx: ${n.srcCtx.get}")

    var container : BlackBoxContainer = BlackBoxContainer()
    val ctrl = leastCommonAncesstor(n.accesses.map { _.getCtrl }).get
    val block = within(pirTop, ctrl, n.srcCtx.get) {
      container = stage(BlackBoxContainer())
      within(container) {
        val ctx = stage(Context().streaming(true)) 
        // val ctx = stage(BlackBoxContainer())
        within(ctx) {
          if (isDRAM)
            stage(SparseDRAMBlock(n.dramPar))
          else
            stage(SparseParSRAMBlock(n.dramPar).swizzle(n.swizzle.get))
        }
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
          val b = Barrier(ctrl, bar_init, 16).name(s"__auto_bar_${from}_${to}")
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
      if (isDRAM && i==naccess-1) { // Last access
        synchronizeWithHost = ua.lanes.head match {
          case access:SparseRead => false
          case access:SparseRMW => (!access.dataOut.isConnected && access.key < 0)
          case access:SparseWrite => true
          case access:SparseRMWData => !access.dataOut.isConnected
        }
      }
      accessReqResp ++= lowerAccess(ua, block, container)
      /*lowerAccess(ua, block).foreach {
        acc =>
          acc match {
            case Some(x)  => accessReqResp += x
            case None => 
        }
      } */
      // accessReqResp ++= lowerAccess(ua, block)
      if (synchronizeWithHost) {
        assert(isDRAM)
        insertTokenToHost(ua)
      }
    }
    dbg(s"accesses=${n.accesses}")
    n.alias.reset
    free(n)
  }

  private def lowerAccess(uaccess:UnrolledAccess[SparseAccess], block:SparseParBlock, bb:BlackBoxContainer) = dbgblk(s"lowerAccess($uaccess)"){
    val par = uaccess.lanes.size
    val accessid = uaccess.lanes.head.id
    uaccess.lanes.zipWithIndex.map { case (access, idx) =>
      access.barriers.get.foreach { 
        case (barrier,true) => barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
        case (barrier,false) => barrierRead.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
      }
      val ctrl = access.getCtrl
      dbg(s"ctrl: $ctrl")
      dbg(s"access src ctx: ${access.srcCtx.get}")
      def shadow(a:PIRNode, c:ControlTree) = {
        a.controlShadowed(true)
        a.bbCtrl(ctrl)
      }
      //val remoteCtx = within(bb, ctrl, access.srcCtx.get) {
        //stage(Context().name("remoteCtx"))
      //}
      //dbg(s"remoteCtx: ${remoteCtx}")
      //dbg(s"remoteCtx.ctrl: ${remoteCtx.ctrl.get}")
      //swapParent(access, remoteCtx)
      val reqresp = within(access.srcCtx.get) {
        access match {
          case access:SparseRead =>
            val (readAddr, readData) = block.addReadPort(accessid)
            val addrCtx = within(pirTop, ctrl) {
              stage(Context().name("addrCtx"))
            }
            //val remoteCtx = within(pirTop, ctrl) {
              //stage(Context().name("remoteCtx"))
            //}
            swapParent(access, addrCtx)
            flattenEnable(access) // in addrCtx
            readAddr(access.addr.connected)
            bufferInput(readAddr, BufferParam(fromCtx=Some(addrCtx))).foreach { acc => 
              //swapParent(acc, remoteCtx)
              //acc.ctrl.reset
              //acc.ctrl(ctrl)
              shadow(acc, ctrl)
              acc.name := "readAddr"
            }
            val ins = access.out.connected
            ins.foreach { in =>
              swapConnection(in, access.out, readData)
            }
            ins.distinct.foreach { in =>
              bufferInput(in).foreach { read => 
                //swapParent(read.inAccess, remoteCtx)
                //read.inAccess.ctrl.reset
                //read.inAccess.ctrl(ctrl)
                //read.inAccess.bbCtrl(Some(ctrl))
                shadow(read.inAccess, ctrl)
                read.inAccess.name := "readOut" 
              }
            }
            val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
            val req = readAddr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
            val resp = reads.head.out
            access -> (Some(req),Some(resp))
          case access:SparseWrite =>
            val (writeAddr, writeData, writeAck) = block.addWritePort(accessid)
            flattenEnable(access) // in write ctx
            writeAddr(access.addr.connected)// .ctx := (remoteCtx)
            writeData(access.data.connected)// .ctx := (remoteCtx)
            //bufferInput(writeAddr).foreach { acc => acc.ctrl.reset; swapParent(acc, remoteCtx); acc.name := "writeAddr" }
            //bufferInput(writeData).foreach { acc => acc.ctrl.reset; swapParent(acc, remoteCtx); acc.name := "writeData" }
            bufferInput(writeAddr).foreach { acc => shadow(acc, ctrl); acc.name := "writeAddr" }
            bufferInput(writeData).foreach { acc => shadow(acc, ctrl); acc.name := "writeData" }
            // writeAck.bbCtrl(ctrl)
            val ackCtx = within(pirTop, ctrl) {
            // val ackCtx = within(bb, ctrl) {
              stage(Context().name("ackCtx"))
            }
            val accumAck = within(ackCtx, ctrl) {
              stage(AccumAck().ack(writeAck))
            }
            access.mem.disconnect
            bufferInput(accumAck.ack).foreach { read => 
              //read.inAccess.ctrl.reset
              //swapParent(read.inAccess, remoteCtx)
              read.name := "ack"
              read.inAccess.name := "ack"
            }
            shadow(writeAddr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite], ctrl)
            val req = writeAddr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
            access -> (Some(req),Some(accumAck.out))
          case access:SparseRMW =>
            val (rmwAddr, rmwDataIn, rmwDataOut) = block.addRMWPort(accessid, access.op, access.opOrder)
            // Comment out the following line for SpGEMM
            // flattenEnable(access) // in write ctx
            rmwAddr(access.addr.connected)
            rmwDataIn(access.input.connected)
            bufferInput(rmwAddr).foreach { in =>
              //in.ctrl.reset
              //in.ctrl(ctrl)
              //in.bbCtrl(Some(ctrl))
              shadow(in, ctrl)
              in.name := "rmwAddr"
            }
            bufferInput(rmwDataIn).foreach { in =>
              //in.ctrl.reset
              //in.ctrl(ctrl)
              //in.bbCtrl(Some(ctrl))
              shadow(in, ctrl)
              in.name := "rmwDataIn"
            }
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
                bufferInput(in).foreach { read => 
                  //read.inAccess.ctrl.reset
                  //read.inAccess.ctrl(ctrl)
                  // read.inAccess.bbCtrl(Some(ctrl))
                  shadow(read.inAccess, ctrl)
                  read.inAccess.name := "rmwDataOut"
                }
              }
              // Testing some new features for better routethrough elimination
              // rmwAddr.as[BufferRead].ctrl(uaccess.ctrl)
              // rmwDataIn.as[BufferRead].ctrl(uaccess.ctrl)
              // rmwDataOut.as[BufferWrite].ctrl(uaccess.ctrl)
              // End test
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
      // dbg(s"remoteCtx.ctrl: ${remoteCtx.ctrl.get}")
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
