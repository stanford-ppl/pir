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
        val accessCtx = Context()
        swapParent(access, accessCtx)
        access match {
          case access:BankedRead => 
            bufferOutput(access.out)
          case access:BankedWrite => 
            bufferInput(access.bank)
            bufferInput(access.offset)
            bufferInput(access.data)
        }
      }
      // Insert token between accesses based on program order
      val sorted = accesses.sortBy { _.order.get }
      sorted.sliding(2, 1).foreach {
        case List(a, b) => insertToken(a,b)
        case List(a) =>
      }
      // Insert token for loop carried dependency
      val lcaCtrl = leastCommonAncesstor(accesses.map(_.ctrl.get)).get
      (lcaCtrl::lcaCtrl.descendents).foreach { ctrl =>
        if (ctrl.as[ControlTree].ctrler.get.isInstanceOf[LoopController]) {
          val accesses = sorted.filter { a => a.ctrl.get.isDescendentOf(ctrl) || a.ctrl == ctrl }
          if (accesses.nonEmpty) {
            dbg(s"$ctrl accesses = ${accesses}")
            zipOption(accesses.head.to[ReadAccess], accesses.last.to[WriteAccess]).foreach { case (r, w) =>
              val token = insertToken(w, r)
              token.initToken := true
            }
          }
        }
      }
    }
  }

  def insertToken(a:Access, b:Access) = {
    val actx = a.ctx.get
    val bctx = b.ctx.get
    val actrl = a.ctrl.get
    val bctrl = b.ctrl.get
    val (enq, deq) = compEnqDeq(actrl, bctrl, false, actx, bctx)
    val write = within(actx, actrl) {
      BufferWrite().data(Const(true)).en(enq)
    }
    within(bctx, bctrl) {
      val tr = bctx.collectDown[TokenRead]().headOption.getOrElse(TokenRead())
      BufferRead(isFIFO = a.ctrl.get==b.ctrl.get).in(write).en(deq).out(tr.input)
    }
  }

  def lowerToBuffer(mem:Memory) = {
    dbg(s"Lower $mem to InputBuffer")
    mem.outAccess.foreach { outAccess =>
      within(outAccess.parent.get.as[PIRNode]) {
        val inAccess = mem.inAccess.head.as[MemWrite]
        val (enq, deq) = compEnqDeq(inAccess.ctrl.get, outAccess.ctrl.get, mem.isFIFO, inAccess.ctx.get, outAccess.ctx.get)
        val write = within(inAccess.parent.get.as[PIRNode], inAccess.ctrl.get) {
          BufferWrite().data(inAccess.data.connected).mirrorMetas(inAccess).en(enq)
        }
        dbg(s"create $write.data(${inAccess.data.neighbors}).en($enq)")
        val read = within(outAccess.parent.get.as[PIRNode], outAccess.ctrl.get) {
          BufferRead(mem.isFIFO).in(write.out).mirrorMetas(mem).en(deq)
        }
        dbg(s"create $read.in(${write}).en($deq)")
        if (inAccess.order.get < outAccess.order.get ) read.initToken := true
        outAccess.depeds.foreach { deped =>
          swapConnection(deped, outAccess.as[Def].out, read.out)
        }
      }
    }
  }

}
