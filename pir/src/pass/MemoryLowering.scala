package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

class MemoryLowering(implicit compiler:PIR) extends BufferAnalyzer with DependencyAnalyzer {

  override def runPass = {
    pirTop.collectDown[Memory]().foreach(lowerMem)
  }

  def lowerMem(mem:Memory):Unit = dbgblk(s"lowerMem($mem)"){
    val accesses = mem.accesses
    accesses.foreach { access =>
      dbg(s"access=$access order=${access.order.v}")
    }
    var cannotToBuffer = accesses.exists { _.isInstanceOf[BankedAccess] }
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
    val bankids = (0 until mem.banks.get.product).toList
    mem.bankids := bankids
    val accesses = mem.accesses
    List(mem.inAccesses, mem.outAccesses).foreach { accesses =>
      groupAccess(mem, accesses).foreach { group =>
        group.head match {
          case _:BankedAccess => lowerBankedAccesses(mem, memCU, group.asInstanceOf[Set[BankedAccess]])
          case _ => lowerAccess(mem, memCU, assertOne(group, s"$mem.access group"))
        }
      }
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

  def lowerAccess(mem:Memory, memCU:MemoryContainer, access:Access) = dbgblk(s"lowerAccess($mem, $memCU, $access)") {
    val mergeCtx = within(memCU, access.ctx.get.getCtrl) { Context() }
    swapParent(access, mergeCtx)
    access match {
      case access:MemRead =>
        bufferOutput(access.out)
      case access:MemWrite =>
        bufferInput(access.data)
        val writeEns = access.en.T
        dbg(s"writeEns=$writeEns")
        val fromValid = writeEns.forall { case en:CounterValid => true }
        if (!fromValid) bufferInput(access.en)
    }
  }

  def lowerBankedAccesses(mem:Memory, memCU:MemoryContainer, accesses:Set[BankedAccess]) = dbgblk(s"lowerBankedAccesses($mem, $memCU, $accesses)") {
    val headAccess = accesses.head
    val mergeCtrl = headAccess.getCtrl
    val mergeCtx = within(memCU, headAccess.ctx.get.getCtrl) { Context() }
    dbg(s"mergeCtrl = $mergeCtrl")
    dbg(s"mergeCtx=$mergeCtx")
    within(mergeCtx, mergeCtrl) {
      val inputs = accesses.asInstanceOf[Set[BankedAccess]].map { access =>
        val addrCtx = access match {
          case access if accesses.size == 1 => mergeCtx
          case access:BankedRead => within(pirTop, access.ctx.get.getCtrl) { Context() }
          case access:BankedWrite => access.ctx.get
        }
        dbg(s"addrCtx for $access = $addrCtx")
        swapParent(access, addrCtx)
        within(addrCtx, access.getCtrl) {
          flattenBankAddr(access)
          val bank = access.bank.connected
          val ofs = Shuffle().from(bank).to(allocConst(mem.bankids.get)).base(access.offset.connected)
          dbg(s"val $ofs = Shuffle() //ofs")
          val data = access match {
            case access:BankedWrite => 
              val data = Shuffle().from(bank).to(allocConst(mem.bankids.get)).base(access.data.connected)
              bufferInput(data.base) // Force fifo control among unrolled controllers
              dbg(s"val $data = Shuffle() //data")
              Some(data)
            case access => None
          }
          (ofs.out, data.map{_.out})
        }
      }
      var red:List[(Output, Option[Output])] = inputs.toList
      while(red.size > 1) {
        red = red.sliding(2,2).map{ 
          case List((o1, d1),(o2, d2)) =>
            val of = OpDef(FixOr).input(o1, o2)
            dbg(s"add of = $of.input(${o1.src}.$o1,${o2.src}.$o2")
            bufferInput(of.input)
            val dt = zipOption(d1, d2).map { case (d1, d2) =>
              val dt = OpDef(FixOr).input(d1,d2)
              bufferInput(dt.input)
              dbg(s"add dt = $dt.input(${d1.src}.$d1,${d2.src}.$d2)")
              dt
            }
            (of.out, dt.map { _.out })
          case List((o1, d1)) => (o1, d1)
        }.toList
      }
      val List((ofs, data)) = red
      data.fold[Unit]{
        val newRead = BankedRead().offset(ofs).mem(mem).mirrorMetas(headAccess)
        accesses.asInstanceOf[Set[BankedRead]].foreach { access =>
          access.out.connected.distinct.foreach{ in => 
            val inCtx = in.src.as[PIRNode].ctx.get
            val shuffle = within(inCtx, in.src.as[PIRNode].getCtrl)  {
              Shuffle().from(allocConst(mem.bankids.get)).to(access.bank.connected).base(newRead.out)
            }
            bufferInput(shuffle.base)
            bufferInput(shuffle.to) //TODO: can also not buffer if not expensive to duplicate calculation.
            dbg(s"${in.src}.$in.swapInput($access.out, $shuffle.out)")
            swapConnection(in.as[Input], access.out, shuffle.out)
          }
        }
      } { data => 
        BankedWrite().offset(ofs).data(data).mem(mem).mirrorMetas(headAccess)
      }
    }
    removeNodes(accesses)
  }

  def flattenBankAddr(access:BankedAccess):Unit = {
    if (access.bank.T.size == 1) return
    val mem = access.mem.T
    val parent = access.parent.get.as[PIRNode]
    within(parent, access.getCtrl) {
      def flattenND(inds:List[Edge], dims:List[Int]):Edge = {
        if (inds.size==1) return inds.head
        assert(inds.size == dims.size, s"flattenND inds=$inds dims=$dims have different size for access=$access")
        val i::irest = inds
        val d::drest = dims
        OpDef(FixFMA).input(i,allocConst(drest.product), flattenND(irest, drest)).out
      }
      val dims = mem match {
        case mem:SRAM => mem.banks.get
        case mem:LUT => mem.dims.get
      }
      val fbank = flattenND(access.bank.connected.toList, dims)
      dbg(s"flattenBankAddr ${access.bank.T} => $fbank in $parent")
      access.bank.disconnect
      access.bank(fbank)
    }
  }

    // Insert token for sequencial control dependency
  def sequencedScheduleBarrierInsertion(mem:Memory) = {
    dbgblk(s"sequencedScheduleBarrierInsertion($mem)") {
      val ctrls = mem.accesses.flatMap { a => a.getCtrl :: a.getCtrl.ancestors }.distinct.as[Seq[ControlTree]]
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

  def enforceDataDependency(mem:Memory):Unit = dbgblk(s"enforceDataDependency($mem)"){
    val accesses = mem.accesses.filter { _.port.nonEmpty }
    accesses.groupBy { _.port.get }.foreach { case (port, accesses) =>
      val (inAccesses, outAccesses) =  accesses.partition { _.isInstanceOf[InAccess] }
      inAccesses.foreach { inAccess =>
        outAccesses.foreach { outAccess =>
          dbg(s"Insert token for data dependency between $inAccess and $outAccess")
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
      insertToken(w.ctx.get,r.ctx.get)
    }
  }

  def enforceProgramOrder(mem:Memory) = {
    dbgblk(s"enforceProgramOrder($mem)") {
      val accesses = mem.accesses
       //Insert token between accesses based on program order
      val sorted = accesses.sortBy { _.order.get }
      sorted.sliding(2, 1).foreach {
        case List(a, b) => insertToken(a.ctx.get,b.ctx.get)
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
              val token = insertToken(w.ctx.get, r.ctx.get)
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
        val (enq, deq) = compEnqDeq(mem.isFIFO, inAccess.ctx.get, outAccess.ctx.get, Some(inAccess.data.connected.head.as[Output]), outAccess.out.connected.as[Seq[Input]])
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
          BufferRead().in(write.out).mirrorMetas(mem).mirrorMetas(outAccess).done(deq)
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
    removeNodes(mem.accesses :+ mem)
  }

}
