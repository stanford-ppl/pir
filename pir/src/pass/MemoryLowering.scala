package pir
package pass

import pir.node._
import prism.graph._

class MemoryLowering(implicit compiler:PIR) extends MemoryAnalyzer {

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
      lowerToInputBuffer(mem)
    }
  }

  def createMemCtx(mem:Memory) = {
    within(mem.parent.get.to[PIRNode]) {
      val memCU = MemoryContainer()
      swapParent(mem, memCU)
      mem.accesses.foreach { access =>
        within(memCU) {
          val accessCtx = Context()
          swapParent(access, accessCtx)
          access match {
            case access:InAccess => bufferInput(accessCtx)
            case access:OutAccess => bufferInput(accessCtx) // duplicateInputs(access)
          }
        }
      }
    }
  }

  def lowerToInputBuffer(mem:Memory) = {
    dbg(s"Lower $mem to InputBuffer")
    mem.outAccess.foreach { outAccess =>
      within(outAccess.parent.get.to[PIRNode]) {
        val inAccess = mem.inAccess.head.to[MemWrite]
        val (enq, deq) = compEnqDeq(inAccess.ctrl.get, outAccess.ctrl.get, mem.isFIFO)
        val write = within(inAccess.parent.get.to[PIRNode], inAccess.ctrl.get) {
          BufferWrite().data(inAccess.data.connected).mirrorMetas(inAccess).en(enq)
        }
        val read = within(outAccess.parent.get.to[PIRNode], outAccess.ctrl.get) {
          BufferRead(mem.isFIFO).in(write.out).mirrorMetas(mem).en(deq)
        }
        bufferInput(write)
        bufferInput(read)
        outAccess.depeds.foreach { deped =>
          swapConnection(deped, outAccess.to[Def].out, read.out)
        }
      }
    }
  }

}
