package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

class MemoryLowering(implicit compiler:PIR) extends BufferAnalyzer {

  override def runPass = {
    pirTop.collectDown[Memory]().foreach(lowerMem)
  }

  def lowerMem(mem:Memory):Unit = dbgblk(s"lowerMem($mem)"){
    val accesses = mem.accesses
    accesses.foreach { access =>
      dbg(s"access=$access order=${access.order.v}")
    }
    var cannotToBuffer = accesses.exists { _.isInstanceOf[BanckedAccess] }
    // If read access is branch dependent, the ctx cannot block on the input for its activation
    cannotToBuffer |= mem.outAccesses.exists { _.en.T.nonEmpty }
    cannotToBuffer |= mem.inAccesses.size > 1
    if (mem.isFIFO) cannotToBuffer |= mem.outAccesses.size > 1
    if (cannotToBuffer) {
      createMemGlobal(mem)
    } else {
      lowerToBuffer(mem)
    }
  }

  def createMemGlobal(mem:Memory) = {
    val memCU = within(mem.parent.get.as[PIRNode]) { MemoryContainer() }
    // Create Memory Context
    swapParent(mem, memCU)
    val accesses = mem.accesses
    accesses.foreach { access =>
      access.getVec
      access match {
        case access:BanckedAccess => flattenBankAddr(access)
        case access =>
      }
    }
    groupAccess(mem, mem.outAccesses).foreach { accesses =>
      lowerAccesses(mem, memCU, accesses)
    }
    groupAccess(mem, mem.inAccesses).foreach { accesses =>
      lowerAccesses(mem, memCU, accesses)
    }
    sequencedScheduleBarrierInsertion(mem)
    multiBufferBarrierInsertion(mem)
    fifoBarrierInsertion(mem)
    //enforceProgramOrder(mem)
    enforceDataDependency(mem)
  }

  def groupAccess(mem:Memory, accesses:List[Access]):List[Set[Access]] = dbgblk(s"groupAccess($mem)") {
    accesses.groupBy { _.port.v }.flatMap { case (group, accesses) =>
      if (accesses.size == 1) List(Set(accesses.head)) else {
        val head::rest = accesses
        rest.foldLeft(List(Set(head))) { case (groups, access) =>
          val (shared, notshared) = groups.partition { group =>
            assertUnify(group, s"share concurrency with $access(${access.getCtrl}) ${group.map { a => s"$a(${a.getCtrl})" }}") { a => 
              val lca = leastCommonAncesstor(a.getCtrl, access.getCtrl).get.as[ControlTree]
              dbg(s"lca=$lca ${lca.schedule}")
              lca.schedule == "ForkJoin" || (a.getCtrl == access.getCtrl && lca.schedule == "Pipelined")
              // Inaccesses/Outaccesses who are concurrently operating on the same buffer port must be banked
              // Can only coalesce accesses with the same count
            }.get
          }
          dbg(s"access=$access shared=$shared")
          val merged = shared.reduceOption { _ ++ _ }.getOrElse(Set.empty)
          (merged + access) :: notshared
        }
      }
    }.toList
  }

  def lowerAccesses(mem:Memory, memCU:MemoryContainer, accesses:Set[Access]) = {
    if (accesses.size==1) {
      dbgblk(s"lowerAccesses($mem, $memCU, ${accesses.head})") {
        within(memCU) {
          val accessCtx = Context()
          val access = accesses.head
          swapParent(access, accessCtx)
          access match {
            case access:BankedRead => 
              bufferOutput(access.out)
            case access:MemRead =>
              bufferOutput(access.out)
            case access:BankedWrite => 
              bufferInput(access.data)
            case access:MemWrite =>
              bufferInput(access.data)
              val writeEns = access.en.T
              dbg(s"writeEns=$writeEns")
              val fromValid = writeEns.forall { case en:CounterValid => true }
              if (!fromValid) bufferInput(access.en)
          }
        }
      }
    } else {
      mergeAccesses(mem, memCU, accesses)
    }
  }

  def mergeAccesses(mem:Memory, memCU:MemoryContainer, accesses:Set[Access]) = dbgblk(s"MergeAccess($mem, $memCU)") {
    dbg(s"accesses=$accesses")
    //breakPoint(s"mergeAccesses($mem, accesses=$accesses)", None)
    val headAccess = accesses.head
    within(pirTop) {
      val mergeCtrl = headAccess.getCtrl
      dbg(s"mergeCtrl = $mergeCtrl")
      val mergeCtx = Context()
      dbg(s"mergeCtx=$mergeCtx")
      val writes = accesses.map { case access:BanckedAccess =>
        val accessCtx = access match {
          case access:BankedWrite => access.ctx.get
          case access:BankedRead => Context()
        }
        dbg(s"$access accessCtx=$accessCtx for addr calculation")
        val (enq, deq) = compEnqDeq(access.ctrl.get, mergeCtrl, isFIFO=true, accessCtx, mergeCtx)
        val (bank, ofs, data, vec) = within(accessCtx, access.getCtrl) {
          val bank = BufferWrite().data(access.bank.connected).done(enq)
          val ofs = BufferWrite().data(access.offset.connected).done(enq)
          val data = access match {
            case access:BankedWrite => 
              val data = BufferWrite().data(access.data.connected).done(enq)
              Some(data)
            case access:BankedRead => None
          }
          (bank, ofs, data, access.getVec)
        }
        (bank, ofs, data, deq, vec)
      }.toList
      val newAccessCtx = within(memCU) { Context() }
      dbg(s"newAccessCtx=$newAccessCtx")
      val (enq, deq) = compEnqDeq(mergeCtrl, mergeCtrl, isFIFO=true, mergeCtx, newAccessCtx)
      val (bank, ofs, data) = within(mergeCtx, mergeCtrl) {
        val buffered = writes.map { case (bankwrite, ofswrite, datawrite, deq, vec) =>
          val bank = BufferRead(isFIFO=true).in(bankwrite.out).done(deq).banks(List(vec)).out
          val ofs = BufferRead(isFIFO=true).in(ofswrite.out).done(deq).banks(List(vec)).out
          val data = datawrite.map { datawrite => BufferRead(isFIFO=true).in(datawrite.out).done(deq).banks(List(vec)).out }
          (bank, ofs, data)
        }
        var red:List[(Output, Output, Option[Output])] = buffered
        while(red.size > 1) {
          red = red.sliding(2,2).map{ 
            case List((b1, o1, d1),(b2, o2, d2)) =>
              val bk = BankCoalesce().in1(b1).in2(b2)
              val of = SelectCoalesce().in1(o1).in2(o2).select(bk.select)
              dbg(s"add bk = $bk.in1(${b1.src}.$b1).in2(${b2.src}.$b2)")
              dbg(s"add of = $of.select(${bk}.${bk.select}).in1(${o1.src}.$o1).in2(${o2.src}.$o2")
              val dt = zipOption(d1, d2).map { case (d1, d2) =>
                val dt = SelectCoalesce().in1(d1).in2(d2).select(bk.select)
                dbg(s"add dt = $dt.select(${bk}.${bk.select}).in1(${d1.src}.$d1).in2(${d2.src}.$d2)")
                dt
              }
              (bk.out, of.out, dt.map { _.out })
            case List((b1, o1, d1)) => (b1, o1, d1)
          }.toList
        }
        red.head
        val List((bank, ofs, data)) = red.toList
        val bankWrite = BufferWrite().data(bank).done(enq)
        val ofsWrite = BufferWrite().data(ofs).done(enq)
        val dataWrite = data.map { data => BufferWrite().data(data).done(enq) }
        (bankWrite, ofsWrite, dataWrite)
      }
      within(newAccessCtx, mergeCtrl) {
        //val vec = write.map { _.vec }.sum
        val bankRead = BufferRead(isFIFO=true).in(bank.out).done(deq)
        val ofsRead = BufferRead(isFIFO=true).in(ofs.out).done(deq)
        //TODO: handle enables
        //TODO: handle vec
        data.fold[Unit]{
          val newRead = BankedRead().bank(bankRead.out).offset(ofsRead.out)/*.vec(vec)*/.mem(mem).mirrorMetas(headAccess)
          accesses.foreach { case read:BankedRead =>
            read.out.connected.foreach{ in => 
              dbg(s"${in.src}.$in.swapInput($read.out, $newRead.out)")
              swapConnection(in.as[Input], read.out, newRead.out)
            }
          }
          bufferOutput(newRead.out)
        } { data => 
          val dataRead = BufferRead(isFIFO=true).in(data.out).done(deq)
          BankedWrite().bank(bankRead.out).offset(ofsRead.out).data(dataRead.out)/*.vec(vec)*/.mem(mem).mirrorMetas(headAccess)
        }
        accesses.foreach { removeNode }
      }
    }
  }

  def flattenBankAddr(access:BanckedAccess):Unit = {
    if (access.bank.T.size == 1) return
    val mem = access.mem.T
    within(access.parent.get.as[PIRNode], access.getCtrl) {
      def flattenND(inds:List[Edge], dims:List[Int]):Edge = {
        if (inds.size==1) return inds.head
        assert(inds.size == dims.size, s"flattenND inds=$inds dims=$dims have different size for access=$access")
        val i::irest = inds
        val d::drest = dims
        OpDef(FixFMA).input(i,Const(drest.product), flattenND(irest, drest)).out
      }
      val dims = mem match {
        case mem:SRAM => mem.banks.get
        case mem:LUT => mem.dims.get
      }
      val fbank = flattenND(access.bank.connected.toList, dims)
      dbg(s"flattenBankAddr ${access.bank.T} => $fbank")
      access.bank.disconnect
      access.bank(fbank)
    }
  }

    // Insert token for sequencial control dependency
  def sequencedScheduleBarrierInsertion(mem:Memory) = {
    dbgblk(s"sequencedScheduleBarrierInsertion($mem)") {
      val ctrls = mem.accesses.flatMap { a => a.getCtrl :: a.getCtrl.ancestors }.distinct.asInstanceOf[Seq[ControlTree]]
      ctrls.foreach { ctrl =>
        if (ctrl.schedule == "Sequenced") {
          val accesses = ctrl.children.flatMap { c => 
            val childCtrl = c.as[ControlTree]
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
                      insertToken(fromAccess.ctx.get, toAccess.ctx.get, fromCtrl, toCtrl).depth(1)
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
    if (mem.depth.get == 1) return
    dbgblk(s"multiBufferBarrierInsertion($mem)") {
      val accesses = mem.accesses.filter { _.port.nonEmpty }
      val ctrlMap = leastMatchedPeers(accesses.map { _.getCtrl} ).get
      // Connect access.done
      accesses.foreach { access =>
        val ctrl = ctrlMap(access.getCtrl).as[ControlTree]
        access.done(ctrlDone(ctrl, access.ctx.get))
      }
      val portMap = mem.accesses.groupBy { access =>
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
                toAccess.ctx.get, 
                ctrlMap(fromAccess.getCtrl).as[ControlTree], 
                ctrlMap(toAccess.getCtrl).as[ControlTree]
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

  def enforceDataDependency(mem:Memory):Unit = dbgblk(s"enforceDataDependency($mem)"){
    val accesses = mem.accesses.filter { _.port.nonEmpty }
    accesses.groupBy { _.port.get }.foreach { case (port, accesses) =>
      val (inAccesses, outAccesses) =  accesses.partition { _.isInstanceOf[InAccess] }
      inAccesses.foreach { inAccess =>
        outAccesses.foreach { outAccess =>
          dbg(s"Insert token for data dependency between $inAccess and $outAccess")
          val token = insertToken(
            inAccess.ctx.get, 
            outAccess.ctx.get, 
            inAccess.getCtrl.as[ControlTree], 
            outAccess.getCtrl.as[ControlTree]
          )
          if (token.depth.isEmpty) {
            token.depth(1)
          }
          if (inAccess.order.get > outAccess.order.get) {
            dbg(s"$token.initToken = true")
            token.initToken := true
          }
        }
      }
    }
  }

  def fifoBarrierInsertion(mem:Memory):Unit = {
    if (!mem.isFIFO) return
    dbgblk(s"fifoBarrierInsertion($mem)") {
      val w = assertOne(mem.inAccesses, s"$mem.inAccesses")
      val r = assertOne(mem.outAccesses, s"$mem.outAccesses")
      insertToken(w.ctx.get,r.ctx.get,w.getCtrl, r.getCtrl)
    }
  }

  def enforceProgramOrder(mem:Memory) = {
    dbgblk(s"enforceProgramOrder($mem)") {
      val accesses = mem.accesses
       //Insert token between accesses based on program order
      val sorted = accesses.sortBy { _.order.get }
      sorted.sliding(2, 1).foreach {
        case List(a, b) => insertToken(a.ctx.get,b.ctx.get,a.getCtrl, b.getCtrl)
        case List(a) =>
      }
       //Insert token for loop carried dependency
      val lcaCtrl = leastCommonAncesstor(accesses.map(_.ctrl.get)).get
      (lcaCtrl::lcaCtrl.descendents).foreach { ctrl =>
        if (ctrl.as[ControlTree].ctrler.get.isInstanceOf[LoopController]) {
          val accesses = sorted.filter { a => a.ctrl.get.isDescendentOf(ctrl) || a.ctrl == ctrl }
          if (accesses.nonEmpty) {
            dbg(s"$ctrl accesses = ${accesses}")
            zipOption(accesses.head.to[ReadAccess], accesses.last.to[WriteAccess]).foreach { case (r, w) =>
              val token = insertToken(w.ctx.get, r.ctx.get, w.getCtrl, r.getCtrl)
              dbg(s"$token.initToken = true")
              token.initToken := true
            }
          }
        }
      }
    }
  }

  def lowerToBuffer(mem:Memory) = {
    dbg(s"Lower $mem to InputBuffer")
    mem.outAccesses.foreach { outAccess =>
      within(outAccess.parent.get.as[PIRNode]) {
        val inAccess = mem.inAccesses.head.as[MemWrite]
        val (enq, deq) = compEnqDeq(inAccess.ctrl.get, outAccess.ctrl.get, mem.isFIFO, inAccess.ctx.get, outAccess.ctx.get)
        val write = within(inAccess.parent.get.as[PIRNode], inAccess.ctrl.get) {
          allocate[BufferWrite]{ write => 
            write.data.evalTo(inAccess.data.neighbors) &&
            write.en.evalTo(inAccess.en.neighbors) && 
            write.done.evalTo(enq)
          } {
            val write = BufferWrite().data(inAccess.data.connected).mirrorMetas(inAccess).en(inAccess.en.T).done(enq)
            dbg(s"create $write.data(${inAccess.data.neighbors}).done(${write.done.T})")
            write
          }
        }
        val read = within(outAccess.parent.get.as[PIRNode], outAccess.ctrl.get) {
          BufferRead(mem.isFIFO).in(write.out).mirrorMetas(mem).mirrorMetas(outAccess).done(deq)
        }
        dbg(s"create $read.in(${write}).done($deq)")
        if (inAccess.order.get > outAccess.order.get ) {
          dbg(s"$read.initToken = true")
          read.initToken := true
        }
        outAccess.depeds.foreach { deped =>
          swapInput(deped, outAccess.out, read.out)
        }
      }
    }
    mem.accesses.foreach { a => removeNode(a) }
    removeNode(mem)
  }

}
