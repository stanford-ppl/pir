package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

class MemoryLowering(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer with DependencyAnalyzer with CUCostUtil {

  override def visitNode(n:N) = n match {
    case n:Memory => lowerMem(n)
    case _ => super.visitNode(n)
  }

  def lowerMem(mem:Memory):Unit = dbgblk(s"lowerMem(${dquote(mem)})"){
    val accesses = mem.accesses
    accesses.foreach { access =>
      dbg(s"access=$access order=${access.order.v}")
    }
    val noBankedAccess = accesses.forall { !_.isInstanceOf[BankedAccess] }
    val singleWriter = mem.inAccesses.size <= 1
    val singleFIFOReader = !mem.isFIFO | mem.outAccesses.size <= 1
    var toBuffer = true
    toBuffer &= noBankedAccess
    toBuffer &= singleWriter //&& singleFIFOReader
    toBuffer &= !mem.nonBlocking.get
    if (mem.isFIFO && !singleWriter) {
      todo(s"Do not support multiple writers to FIFO on Plasticine yet ${quoteSrcCtx(mem)}")
    }
    if (toBuffer) {
      bufferLowering(mem)
    } else {
      createMemGlobal(mem)
    }
  }

  def lowerLUT(mem:Memory):Unit = {
    if (!mem.isInstanceOf[LUT]) return
    val lut = mem.as[LUT]
    dbgblk(s"lowerLUT($lut)") {
      val bank = lut.outAccesses.map { _.getVec }.sum
      dbg(s"bank=$bank")
      mem.banks.reset
      mem.banks := List(bank)
      var bankid = 0
      lut.outAccesses.foreach { outAccess =>
        val read = outAccess.as[BankedRead]
        // Use offset to carry bank address fornow. After flatten bank address,
        // swap bank addr with offset. 
        read.offset.disconnect
        val bk = within(read.parent.get, read.getCtrl) {
          stage(Const(List.tabulate(read.getVec) { i => bankid + i }).out)
        }
        read.offset(bk)
        bankid += read.getVec
      }
    }
  }

  def lowerLUTAccess(mem:Memory, access:Access):Unit = {
    if (!mem.isInstanceOf[LUT]) return
    val lut = mem.as[LUT]
    val read = access.as[BankedRead]
    dbgblk(s"lowerLUTAccess($mem, $access)") {
      val offset = read.bank.singleConnected.get
      read.bank.disconnect
      read.bank(read.offset.singleConnected)
      read.offset.disconnect
      read.offset(offset)
    }
  }

  def createMemGlobal(mem:Memory) = {
    val memCU = within(pirTop) { MemoryContainer() }
    // Create Memory Context
    swapParent(mem, memCU)
    lowerLUT(mem)
    val bankids = (0 until mem.banks.get.product).toList
    mem.bankids := bankids
    val accesses = mem.accesses
    val newAccesses = List(mem.inAccesses, mem.outAccesses).flatMap { accesses =>
      groupAccess(mem, accesses).flatMap { group =>
        group.head match {
          case _:BankedAccess => 
            val newAccess = lowerBankedAccesses(mem, memCU, group.asInstanceOf[Set[BankedAccess]])
            Some(newAccess)
          case _ => 
            lowerAccess(mem, memCU, assertOne(group, s"$mem.access group"))
            None
        }
      }
    }
    val addrCtxs:Map[Access, Context] = newAccesses.toMap // addrCtx -> newAccess
    sequencedScheduleBarrierInsertion(mem)
    //breakPoint(s"insert token for $mem")
    multiBufferBarrierInsertion(mem)
    enforceDataDependencyInSameController(mem)
    //fifoBarrierInsertion(mem)
    mem.accesses.foreach { access =>
      val ctx = access.ctx.get
      bufferInput(ctx, fromCtx=addrCtxs.get(access))
    }
  }

  // Remove accesses that are been broadcasted
  def resolveBroadcast(accesses:List[Access]):List[Access] = {
    accesses.groupBy { _.castgroup.v }.flatMap { 
      case (None, accesses) => accesses
      case (Some(grp), accesses) =>
        val (heads, tail) = accesses.partition { a => 
          val broadcast = assertIdentical(a.broadcast.get, s"$a.broadcast").get
          broadcast == 0
        }
        val head = assertOne(heads, 
          s"broadcast in castgroup $grp for ${accesses.head.mem} ${accesses}")
        tail.foreach { tail =>
          (head, tail) match {
            case (head:BankedRead, tail:BankedRead) =>
              swapOutput(tail.out, head.out)
              tail.mem.disconnect
            case (head, tail) => err(s"Invalid broadcast from $head to $tail")
          }
        }
        if (tail.nonEmpty) 
          dbg(s"broadcast $head => $tail")
        removeNodes(tail)
        List(head)
    }.toList
  }

  def groupAccess(mem:Memory, accesses:List[Access]):List[Set[Access]] = dbgblk(s"groupAccess($mem)") {
    accesses.groupBy { _.port.v }.flatMap { case (group, accesses) =>
      accesses.groupBy { _.muxPort.v }.map { case (muxPort, accesses) =>
        resolveBroadcast(accesses).toSet
      }
    }.toList
  }

  def lowerAccess(mem:Memory, memCU:MemoryContainer, access:Access) = dbgblk(s"lowerAccess($mem, $memCU, $access)") {
    val ctx = within(memCU, access.ctx.get.getCtrl) { Context() }
    if (mem.isFIFO) {
      ctx.streaming(false)
    } else {
      ctx.streaming(true)
    }
    swapParent(access, ctx)
    access match {
      case access:MemRead =>
        bufferOutput(access.out)
      case access:MemWrite =>
        if (!mem.isFIFO) {
          bufferInput(access.data)
          bufferInput(access.en)
        }
        //val writeEns = access.en.T
        //dbg(s"writeEns=$writeEns")
        //val fromValid = writeEns.forall { case en:CounterValid => true }
        //if (!fromValid) bufferInput(access.en)
    }
    // Connect access.done
    if (mem.isFIFO) {
      access.done(childDone(access.getCtrl, ctx))
    } else if (mem.depth.get > 1 && access.port.get.nonEmpty) {
      val ctrlMap = leastMatchedPeers(mem.accesses.filterNot{_.port.get.isEmpty}.map { _.getCtrl} ).get
      val ctrl = ctrlMap(access.getCtrl)
      access.done(done(ctrl, ctx))
      bufferInput(access.done)
    }
  }

  def lowerBankedAccesses(mem:Memory, memCU:MemoryContainer, accesses:Set[BankedAccess]) = dbgblk(s"lowerBankedAccesses($mem, $memCU, $accesses)") {
    val headAccess = accesses.head
    val mergeCtrl = headAccess.getCtrl
    val mergeCtx = within(memCU, headAccess.ctx.get.getCtrl) { Context() }
    // Optimize for fully unrolled case
    val constAddr = accesses.forall { access =>
      access.bank.connected.forall { case (OutputField(c:Const, "out")) => true; case _ => false } &&
      access.offset.connected.forall { case (OutputField(c:Const, "out")) => true; case _ => false } &&
      !access.en.isConnected
    }
    dbg(s"mergeCtrl = $mergeCtrl")
    dbg(s"mergeCtx=$mergeCtx")
    dbg(s"constAddr=$constAddr")
    val addrCtxs = mutable.Map[BankedAccess, Context]()
    val red = within(mergeCtx, mergeCtrl) {
      val requests = accesses.map { access =>
        val addrCtx = access match {
          //case access if accesses.size == 1 || constAddr => mergeCtx
          //case access:BankedWrite => access.ctx.get 
          case access => within(memCU, access.ctx.get.getCtrl) { Context() }
        }
        addrCtxs += access -> addrCtx
        dbg(s"addrCtx for $access = $addrCtx")
        swapParent(access, addrCtx)
        val (ofsOut,bank) = within(addrCtx, addrCtx.getCtrl) {
          flattenBankAddr(access)
          lowerLUTAccess(mem, access)
          val bank = access.bank.connected
          var ofsOut = access.offset.singleConnected.get
          access.en.singleConnected.foreach { en =>
            ofsOut = stage(OpDef(Mux).addInput(en, ofsOut, allocConst(-1).out).out)
          }
          (ofsOut,bank)
        }
        val ofs = stage(Shuffle(-1).from(bank).to(allocConst(mem.bankids.get)).base(ofsOut))
        bufferInput(ofs.base, fromCtx=Some(addrCtx))
        bufferInput(ofs.from, fromCtx=Some(addrCtx))
        val data = access match {
          case access:BankedWrite => 
            val shuffle = stage(Shuffle(0).from(bank).to(allocConst(mem.bankids.get)).base(access.data.connected))
            bufferInput(shuffle.base) // Prevent copying data producer into addrCtx
            bufferInput(shuffle.from, fromCtx=Some(addrCtx))
            Some(shuffle)
          case access => None
        }
        dbg(s"ofs:${dquote(ofs)} data:${data.map{dquote}}")
        (ofs.out, data.map { _.out })
      }
      var red:List[(Output[PIRNode], Option[Output[PIRNode]])] = requests.toList
      while(red.size > 1) {
        red = red.sliding(2,2).map{ 
          case List((o1, d1),(o2, d2)) =>
            val of = stage(OpDef(SelectNonNeg).addInput(o1, o2))
            val dt = zipOption(d1, d2).map { case (d1, d2) =>
              val dt = stage(OpDef(FixOr).addInput(d1,d2))
              dt
            }
            (stage(of.out), dt.map { dt => stage(dt.out) })
          case List((o1, d1)) => (o1, d1)
        }.toList
      }
      red
    }

    val List((ofs, data)) = red
    val accessCtx = within(memCU, headAccess.ctx.get.getCtrl) { Context().streaming(true) }
    val newAccess = within(accessCtx) {
      data.fold[FlatBankedAccess]{
        stage(FlatBankedRead().offset(ofs).mem(mem).mirrorMetas(headAccess))
      } { data => 
        stage(FlatBankedWrite().offset(ofs).data(data).mem(mem).mirrorMetas(headAccess))
      }
    }
    newAccess.to[FlatBankedRead].foreach { newAccess =>
      newAccess.out.vecMeta.reset
      newAccess.out.vecMeta := mem.nBanks
    }
    bufferInput(accessCtx)

    val addrCtx = addrCtxs(headAccess)
    // Connect access.done
    if (mem.depth.get > 1 && newAccess.port.get.nonEmpty) {
      val ctrlMap = leastMatchedPeers(mem.accesses.filterNot{_.port.get.isEmpty}.map { _.getCtrl} ).get
      val ctrl = ctrlMap(mergeCtrl)
      newAccess.done(done(ctrl, accessCtx))
      bufferInput(newAccess.done, fromCtx=Some(addrCtx))
    }

    newAccess.to[FlatBankedRead].foreach { newAccess =>
      accesses.asInstanceOf[Set[BankedRead]].foreach { access =>
        access.out.connected.distinct.groupBy { in => in.src.ctx.get }.foreach { case (inCtx, ins) =>
          val shuffle = within(inCtx, inCtx.getCtrl)  {
            stage(Shuffle(0).from(allocConst(mem.bankids.get)).to(access.bank.connected).base(newAccess.out))
          }
          dbg(s"val $shuffle = Shuffle() // bankRead")
          bufferInput(shuffle.base)
          bufferInput(shuffle.to, fromCtx=Some(addrCtxs(access)))
          ins.foreach { in =>
            swapConnection(in, access.out, shuffle.out)
          }
        }
      }
    }

    removeNodes(accesses)
    //breakPoint(s"lowerBankedAccesses $mem")
    (newAccess, addrCtx)
  }

  def flattenBankAddr(access:BankedAccess):Unit = dbgblk(s"flattenBankAddr($access)"){
    val mem = access.mem.T
    val parent = access.parent.get
    within(parent, parent.getCtrl) {
      // Flatten BankeAddress
      if (access.bank.T.size > 1) {
        def flattenND(inds:List[Output[PIRNode]], dims:List[Int]):Output[PIRNode] = {
          if (inds.size==1) return inds.head
          assert(inds.size == dims.size, s"flattenND inds=$inds dims=$dims have different size for access=$access")
          val i::irest = inds
          val d::drest = dims
          stage(OpDef(FixFMA).addInput(i,allocConst(drest.product), flattenND(irest, drest)).out)
        }
        val dims = mem match {
          case mem:SRAM => mem.banks.get
          case mem:LUT => mem.dims.get
          case mem:RegFile => mem.dims.get
        }
        val fbank = flattenND(access.bank.connected.toList, dims)
        dbg(s"flattenBankAddr ${access.bank.T} => $fbank in $parent")
        access.bank.disconnect
        access.bank(fbank)
      }

      // And enable signals
      val ens = access.en.connected
      if (ens.size > 1) {
        var red:List[Output[PIRNode]] = ens.toList
        while (red.size > 1) {
          red = red.sliding(2,2).map{ 
            case List(en1, en2) => stage(OpDef(And).addInput(en1,en2)).out
            case List(en1) => en1
          }.toList
        }
        val en = red.head
        dbg(s"And enable signals $ens => $en")
        access.en.disconnect
        access.en(en)
      }
    }
  }

    // Insert token for sequencial control dependency
  def sequencedScheduleBarrierInsertion(mem:Memory):Unit = {
    if (mem.isFIFO) return
    dbgblk(s"sequencedScheduleBarrierInsertion($mem)") {
      val ctrls = mem.accesses.toStream.flatMap { a => a.getCtrl.ancestorTree }.distinct
      ctrls.foreach { ctrl =>
        if (ctrl.schedule == Sequenced) {
          val accesses = ctrl.children.flatMap { childCtrl => 
            val childAccesses = mem.accesses.filter { a => 
              a.getCtrl.isDescendentOf(childCtrl) || a.getCtrl == childCtrl
            }
            if (childAccesses.nonEmpty) Some((childCtrl, childAccesses)) else None
          }
          if (accesses.nonEmpty) {
            dbgblk(s"Insert token for sequenced schedule of $ctrl") {
              accesses.sliding(2, 1).foreach{
                case List((fromCtrl, from), (toCtrl, to)) =>
                  from.foreach { fromAccess =>
                    to.foreach { toAccess =>
                      dbg(s"Insert token between $fromAccess ($fromCtrl) and $toAccess ($toCtrl)")
                      insertToken(fromAccess.ctx.get, toAccess.ctx.get).depth(1)
                    }
                  }
                case _ =>
              }
            }
          }
        }
      }
    }
  }

  def multiBufferBarrierInsertion(mem:Memory):Unit = {
    if (mem.isFIFO) return
    // None multi buffer doesn't need to connect access.done
    if (mem.depth.get == 1) return
    dbgblk(s"multiBufferBarrierInsertion($mem)") {
      val accesses = mem.accesses.filter { _.port.nonEmpty }
      val ctrlMap = leastMatchedPeers(accesses.map { _.getCtrl} ).get
      val portMap = mem.accesses.filter { _.port.v.get.nonEmpty }.groupBy { access =>
        access.port.v.get.get
      }
      val portIds = portMap.keys.toList.sorted
      portIds.sliding(2,1).foreach {
        case List(fromid, toid) =>
          portMap(fromid).foreach { fromAccess =>
            portMap(toid).foreach { toAccess =>
              dbg(s"Insert token for multibuffer between $fromAccess and $toAccess")
              val token = insertToken(
                fromAccess.ctx.get, 
                toAccess.ctx.get
              )
              val depth = toid - fromid + 1
              dbg(s"$token.depth = $depth")
              token.depth(depth)
            }
          }
        case _ =>
      }
    }
  }

  /*
   * If write => read are not in the same loop, they should be handled in multibuffer or sequential
   * controller. This is to handle the case where write and read are in the same controller
   * */
  //TODO: consider dependency between any controllers. Insert token only if there is not already a
  //token between the writer and the reader
  def enforceDataDependencyInSameController(mem:Memory):Unit = dbgblk(s"enforceDataDependencyInSameController($mem)"){
    val accesses = mem.accesses.filter { _.port.nonEmpty }
    accesses.groupBy { _.port.get }.foreach { case (port, accesses) =>
      val (inAccesses, outAccesses) =  accesses.partition { _.isInAccess }
      inAccesses.foreach { inAccess =>
        outAccesses.foreach { outAccess =>
          if (inAccess.getCtrl == outAccess.getCtrl) {
            dbg(s"Insert token for same loop data dependency between $inAccess and $outAccess")
            val token = insertToken(
              inAccess.ctx.get, 
              outAccess.ctx.get
            )
            if (token.depth.isEmpty) {
              token.depth(1)
            }
            if (inAccess.order.get > outAccess.order.get) {
              dbg(s"$token.initToken = true")
              token.initToken := true
              token.inits := true
              token.depth.reset // HACK to mem reduce. 
                                // if token.depth = 1, write is blocked since ready is low. 
              token.depth := 2
            }
          }
        }
      }
    }
  }

  //def fifoBarrierInsertion(mem:Memory):Unit = {
    //if (!mem.isFIFO) return
    //dbgblk(s"fifoBarrierInsertion($mem)") {
      //val w = assertOne(mem.inAccesses, s"$mem.inAccesses")
      //val r = assertOne(mem.outAccesses, s"$mem.outAccesses")
      //insertToken(w.ctx.get,r.ctx.get,isFIFO=true)
    //}
  //}

  def bufferLowering(mem:Memory) = dbgblk(s"bufferLowering($mem)") {
    mem.outAccesses.foreach { outAccess =>
      within(outAccess.parent.get) {
        val inAccess = mem.inAccesses.head.as[MemWrite]
        val (enq, deq) = compEnqDeq(
          mem.isFIFO, 
          inAccess.ctx.get, 
          outAccess.ctx.get, 
          Some(inAccess.data.connected.head), 
          outAccess.out.connected
        )
        val write = within(inAccess.parent.get, inAccess.ctrl.get) {
          allocate[BufferWrite]{ write => 
            write.isFIFO == mem.isFIFO &&
            matchInput(write.data, inAccess.data) &&
            matchInput(write.en,inAccess.en.connected) && 
            matchInput(write.done,enq)
          } {
            stage(BufferWrite(mem.isFIFO)
              .presetVec(inAccess.inferVec.get)
              .data(inAccess.data.connected)
              .mirrorMetas(inAccess)
              .en(inAccess.en.connected)
              .done(enq))
          }
        }
        val readCtx = outAccess.parent.get.as[Context]
        val readCtrl = outAccess.ctrl.get
        val (remoteReadEns, localReadEns) = outAccess.en.connected.partition { !canDuplicate(_) }
        dbg(s"remoteReadEns=${remoteReadEns.map(dquote)}")
        val remoteReadEn = if (remoteReadEns.nonEmpty) {
          val enCtx = within(pirTop, readCtrl) { 
            allocate[Context]({ ctx => ctx.getCtrl == readCtrl && ctx != readCtx }, allowDuplicates=true) { Context() }
          }
          within(enCtx) {
            val en = remoteReadEns.reduce[Output[PIRNode]]{ case (en1, en2) => 
              stage(OpDef(And).addInput(en1,en2).out)
            }
            dbg(s"remoteReadEn = ${dquote(en)}")
            Some((enCtx,en))
          }
        } else None
        val read = within(readCtx, readCtrl) {
          allocate[BufferRead]{ read => 
            read.isFIFO == mem.isFIFO &&
            matchInput(read.in, write.out) &&
            matchInput(read.en,outAccess.en.connected) && 
            matchInput(read.done,deq)
          } {
            stage(BufferRead(mem.isFIFO)
              .in(write.out)
              .mirrorMetas(mem)
              .mirrorMetas(outAccess)
              .en(localReadEns).en(remoteReadEn.map{_._2})
              .done(deq)
              .presetVec(outAccess.inferVec.get))
          }
        }
        remoteReadEn.foreach { case (enCtx,en) =>
          bufferInputFrom(en, read.en, enCtx)
        }
        if (inAccess.order.get > outAccess.order.get ) {
          dbg(s"$read.initToken = true")
          read.initToken := true
        }
        swapOutput(outAccess.out, read.out)
      }
    }
    removeNodes(mem.accesses :+ mem)
  }

  def depCtx(streamCtx:Context) = {
    streamCtx.collectDown[BufferRead]().headOption.map { _.inAccess.ctx.get }.getOrElse {
      within(streamCtx.global.get, streamCtx.getCtrl) {
        Context()
      }
    }
  }

}
