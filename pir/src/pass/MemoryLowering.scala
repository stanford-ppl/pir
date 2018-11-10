package pir
package pass

import pir.node._
import prism.graph._

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
    cannotToBuffer |= mem.outAccess.exists { _.en.T.nonEmpty }
    cannotToBuffer |= mem.inAccess.size > 1
    if (mem.isFIFO) cannotToBuffer |= mem.outAccess.size > 1
    if (cannotToBuffer) {
      createMemCtx(mem)
    } else {
      lowerToBuffer(mem)
    }
  }

  def createMemCtx(mem:Memory) = {
    val memCU = within(mem.parent.get.as[PIRNode]) { MemoryContainer() }
    within(memCU) {
      // Create Memory Context
      swapParent(mem, memCU)
      val accesses = mem.accesses
      accesses.foreach { access =>
        access.getVec
        val accessCtx = Context()
        swapParent(access, accessCtx)
        access match {
          case access:BankedRead => 
            bufferOutput(access.out)
          case access:BankedWrite => 
            //bufferInput(access.bank)
            //bufferInput(access.offset)
            bufferInput(access.data)
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
      sequencedScheduleBarrierInsertion(mem)
      multiBufferBarrierInsertion(mem)
      fifoBarrierInsertion(mem)
      //enforceProgramOrder(mem)
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
        access.port.v.get
      }
      val portIds = portMap.keys.toList.sorted
      portIds.sliding(2,1).foreach {
        case List(fromid, toid) =>
          portMap(fromid).foreach { fromAccess =>
            portMap(toid).foreach { toAccess =>
              dbg(s"Insert token for multibuffer between $fromAccess and $toAccess")
              val buffer = insertToken(
                fromAccess.ctx.get, 
                toAccess.ctx.get, 
                ctrlMap(fromAccess.getCtrl).as[ControlTree], 
                ctrlMap(toAccess.getCtrl).as[ControlTree]
              )
              //if (fromAccess.isInAccess == toAccess.isInAccess) { // Single buffer token
                //buffer.depth(1)
              //} else { // Double buffer token
                buffer.depth(2)
              //}
            }
          }
        case _ =>
      }
    }
  }

  def fifoBarrierInsertion(mem:Memory):Unit = {
    if (!mem.isFIFO) return
    dbgblk(s"fifoBarrierInsertion($mem)") {
      assert(mem.inAccess.size==1, s"$mem.inAccess")
      assert(mem.outAccess.size==1, s"$mem.outAccess")
      val w = mem.inAccess.head
      val r = mem.outAccess.head
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
              token.initToken := true
            }
          }
        }
      }
    }
  }

  def lowerToBuffer(mem:Memory) = {
    dbg(s"Lower $mem to InputBuffer")
    mem.outAccess.foreach { outAccess =>
      within(outAccess.parent.get.as[PIRNode]) {
        val inAccess = mem.inAccess.head.as[MemWrite]
        var (enq, deq) = compEnqDeq(inAccess.ctrl.get, outAccess.ctrl.get, mem.isFIFO, inAccess.ctx.get, outAccess.ctx.get)
        val write = within(inAccess.parent.get.as[PIRNode], inAccess.ctrl.get) {
          BufferWrite().data(inAccess.data.connected).mirrorMetas(inAccess).en(inAccess.en.T)
        }
        inAccess.data.T match {
          case data:DRAMCommand => write.done(data.respondValid)
          case data => write.done(enq)
        }
        dbg(s"create $write.data(${inAccess.data.neighbors}).done(${write.done.T})")
        val read = within(outAccess.parent.get.as[PIRNode], outAccess.ctrl.get) {
          BufferRead(mem.isFIFO).in(write.out).mirrorMetas(mem).mirrorMetas(outAccess).done(deq)
        }
        dbg(s"create $read.in(${write}).done($deq)")
        if (inAccess.order.get < outAccess.order.get ) read.initToken := true
        outAccess.depeds.foreach { deped =>
          swapConnection(deped, outAccess.as[Def].out, read.out)
        }
      }
    }
  }

}
