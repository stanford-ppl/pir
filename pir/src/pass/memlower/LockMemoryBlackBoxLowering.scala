package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable


trait LockMemoryBackBoxLowering extends GenericMemoryLowering { self:LockMemoryLowering =>

  protected def lowerRMW(
    lock:Lock, 
    rmwpar:Int,
    lockMems:List[LockMem], // Lock address, one per outer loop lane
    igs:List[InnerAccessGroup] // A list of sparse accum group
  ):Unit = dbgblk(s"lowerRMW($lock)"){
    within(pirTop, lock.getCtrl) {
      val blockCtx = within(pirTop, lock.getCtrl) { stage(Context().streaming(true)) }
      val lockAccums = lockMems.map { mem => 
        LockAccum(mem.tp.get, mem.dims.get, mem.srcCtx.v, mem.name.v, if (mem.isDRAM) Some(mem.sname.get) else None)
      }
      val accumMap = (lockMems,lockAccums).zipped.toMap
      val block = within(blockCtx, lock.getCtrl) { LockRMWBlock(rmwpar,lockAccums) }
      val accesses = lockMems.flatMap { _.accesses }
      block.mirrorMetas(lock)
      withLive(accesses:_*) { // Make sure don't delete these IRs until after synchronization
        val reqresp = igs.flatMap { ig =>
          if (ig.isLockedAccess) lowerLockedRMW(ig, block, blockCtx, accumMap)
          else lowerUnlockAccessGroup(ig, block, blockCtx, accumMap)
        }
        // Insert tokens across accesses to enforce ordering
        reqresp.groupBy { case (access,reqresp) => access.mem.T }.foreach { case (mem, accesses) =>
          val reqrespMap = accesses.toMap
          val sorted = consistencyBarrier(reqrespMap.keys.toList)(dependsOn){ case (from,to,carried,depth) =>
            insertBarrier(from, reqrespMap(from)._2, to, reqrespMap(to)._1, carried, depth)
          }
          if (block.isDRAM) {
            sorted.reverseIterator.find { ua =>
              val access = ua.lanes.head
              access.isWrite || access.isLockedAccess
            }.foreach { ua =>
              ua.lanes.foreach { access =>
                insertTokenToHost(access, reqrespMap(access)._2)
              }
            }
          }
        }
      }
      removeLive(lockMems:_*)
      free(accesses)
      stage(block)
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
      read.initToken := 1
      read.inits := true
    }
    val original = torqst.singleConnected.get
    val forward = within(toctx, toctx.getCtrl) { stage(Forward().in(original).dummy(read.out)) }
    swapConnection(torqst, original, forward.out)
  }

  private def insertTokenToHost(
    from:LockAccess, 
    fromresp:Output[PIRNode],
  ) = {
    val fromctx = fromresp.src.ctx.get
    val hostOutCtx = pirTop.argFringe.collectFirst[HostOutController](visitDown _).ctx.get
    val read = insertToken(fromctx, hostOutCtx, dep=Some(fromresp))
    within(hostOutCtx, hostOutCtx.getCtrl) {
      stage(HostRead().input(read.out))
    }
  }

  private def lowerLockedRMW(
    ig:InnerAccessGroup, 
    block:LockRMWBlock, 
    blockCtx:Context, 
    accumMap:Map[LockMem,LockAccum]
  ):List[(LockAccess, (Input[PIRNode], Output[PIRNode]))] = dbgblk(s"lowerLockedRMW($ig)"){
    val lanes = ig.accesses.map { case UnrolledAccess(lanes) => lanes }.transpose
    lanes.zipWithIndex.map { case (accesses,lane) =>

      val reads = accesses.collect { case access:LockRead => access }
      val writes = accesses.collect { case access:LockWrite => access }
      val exps = reads.flatMap { read => 
        read.out.accumTill[LockWrite]().filterNot { case access:Access => true; case _ => false }
      }.distinct
      dbg(s"accum exps: ${exps}")
      val accumCtrl = reads.head.getCtrl
      val accumCtx = within(pirTop, accumCtrl) { stage(Context().streaming(true)) }
      exps.foreach { exp => swapParent(exp, accumCtx) }
      val unShuffledCtx = reads.head.ctx.get
      val ins = accumCtx.depsFrom.filter { 
        case (OutputField(_:LockRead, _),ins) => false
        case (out, ins) if !isVecLink(out) => false
        case (out,ins) => true
      }
      block.numIns := ins.size
      ins.foreach { 
        case (out, ins) =>
          (0 until block.outerPar).foreach { i => // Foreach tree in
            val lockInputIn = block.addLockInputIn
            lockInputIn(out)
            bufferInput(lockInputIn, BufferParam(fromCtx=Some(unShuffledCtx)))
          }
          val lockInputOut = block.addLockInputOut
          ins.foreach { in =>
            swapConnection(in, out, lockInputOut)
            bufferInput(in)
          }
      }
      bufferInput(accumCtx, BufferParam(fromCtx=Some(unShuffledCtx)))

      // Wire up address port
      val addrCtx = within(pirTop, accumCtrl) { stage(Context()) }
      val addr = assertUnify(accesses, s"addr") { _.addr.singleConnected.get }
      accesses.reduce { (a1,a2) => if (matchInput(a1.en, a2.en)) a1 else err(s"$a1 and $a2 doesn't match in enable") }
      accesses.foreach { access => swapParent(access, addrCtx) }
      flattenEnable(accesses.head)
      val addrPort = block.lockAddrs(lane)
      addrPort(addr)
      bufferInput(addrPort, BufferParam(fromCtx=Some(addrCtx)))

      // Wire up read data
      reads.foreach { read =>
        val readDataPort = block.lockDataOut(accumMap(read.mem.T.as[LockMem]))(lane)
        read.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx, ins) =>
          ins.foreach { in =>
            swapConnection(in, read.out, readDataPort)
            bufferInput(in)
          }
        }
      }

      // Wire up write data
      writes.foreach { write =>
        val writeDataPort = block.lockDataIn(accumMap(write.mem.T.as[LockMem]))(lane)
        writeDataPort(write.data.singleConnected.get)
        bufferInput(writeDataPort, BufferParam(fromCtx=Some(accumCtx)))
      }

      val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
      val ackCtx = within(pirTop, accumCtrl) { stage(Context()) }
      dbg(s"ackCtx=$ackCtx")
      val accumAck = within(ackCtx, accumCtrl) {
        stage(AccumAck().ack(block.lockAcks(lane)))
      }
      bufferInput(accumAck.ack)

      // Only needs to synchronize one of the access with the others
      accesses.head -> (reqPort, accumAck.out)
    }
  }

  private def lowerUnlockAccessGroup(
    ig:InnerAccessGroup, 
    block:LockRMWBlock, 
    blockCtx:Context, 
    accumMap:Map[LockMem,LockAccum]
  ):List[(LockAccess, (Input[PIRNode], Output[PIRNode]))] = dbgblk(s"lowerUnlockAccessGroup(${ig})"){
    ig.accesses.flatMap { case ua@UnrolledAccess(lanes) =>
      val accum = accumMap(lanes.head.mem.T.as[LockMem])
      if (lanes.size != block.outerPar) {
        if (lanes.size != 1)
          err(s"Unlocked access must be either not outer loop parallelized or parallelized by the same amount as locked access ${quoteSrcCtx(ua)}")
        val access = lanes.head
        List(access -> lowerUnlockAccess(access, accum, (0 until block.outerPar).toList, block, blockCtx))
      } else {
        lanes.zipWithIndex.map { case (access,lane) => 
          access -> lowerUnlockAccess(access, accum, List(lane), block, blockCtx)
        }
      }
    }
  }

  private def lowerUnlockAccess(access:LockAccess, accum:LockAccum, lanes:List[Int], block:LockRMWBlock, blockCtx:Context) = {
    val addrCtx = access match {
      case access:LockWrite => access.ctx.get
      case access:LockRead => within(pirTop, access.getCtrl) { Context() }
    }
    swapParent(access, addrCtx)
    flattenEnable(access)
    access match {
      case access:LockWrite =>
        val (reqPorts, ackPorts) = lanes.map { lane =>
          val addrPort = block.unlockWriteAddr(accum)(lane)
          addrPort(access.addr.singleConnected.get)
          bufferInput(addrPort)
          val dataPort = block.unlockWriteData(accum)(lane)
          dataPort(access.data.singleConnected.get)
          bufferInput(dataPort)
          val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val ackPort = block.unlockWriteAck(accum)(lane)
          (reqPort, ackPort)
        }.unzip
        val reqPort = assertIdentical(reqPorts, s"Broadcast lanes have different request ports").get
        val ackCtx = within(pirTop, access.getCtrl) { stage(Context()) }
        dbg(s"ackCtx = $ackCtx")
        val ackPort = within(ackCtx, access.getCtrl) {
          val ack = ackPorts.reduce[Output[PIRNode]] { case (r1,r2) =>
            stage(OpDef(FixAnd).addInput(r1,r2).out)
          }
          stage(AccumAck().ack(ack).out)
        }
        bufferInput(ackCtx)
        (reqPort, ackPort)
      case access:LockRead =>
        val (reqPorts, dataPorts) = lanes.map { lane =>
          val addrPort = block.unlockReadAddr(accum)(lane)
          addrPort(access.addr.singleConnected.get)
          bufferInput(addrPort, BufferParam(fromCtx=Some(addrCtx)))
          val reqPort = addrPort.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val dataPort = block.unlockReadData(accum)(lane)
          (reqPort, dataPort)
        }.unzip
        val reqPort = assertIdentical(reqPorts, s"Broadcast lanes have different request ports").get
        val dataPort = if (dataPorts.size == 1) {
          dataPorts.head
        } else {
          val readCtxs = access.out.connected.map { in => in.src.ctx.get }
          val respCtx = testOne(readCtxs).getOrElse { within(pirTop, access.getCtrl) { stage(Context()) } }
          val dataPort = within(respCtx, access.getCtrl) {
            dataPorts.reduce[Output[PIRNode]] { case (d1,d2) =>
              stage(OpDef(FixAnd).addInput(d1,d2).out)
            }
          }
          bufferInput(respCtx)
          dataPort
        }
        access.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx, ins) =>
          ins.foreach { in =>
            swapConnection(in, access.out, dataPort)
            bufferInput(in)
          }
        }
        (reqPort ,dataPort)
    }
  }

}

