package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LocalMemoryLowering extends GenericMemoryLowering {
  def canLocalize(mem:Memory) = {
    val noBankedAccess = mem.accesses.forall { !_.isInstanceOf[BankedAccess] }
    val singleWriter = mem.inAccesses.size <= 1
    val singleFIFOReader = !mem.isFIFO | mem.outAccesses.size <= 1
    var toBuffer = true
    toBuffer &= noBankedAccess
    toBuffer &= singleWriter //&& singleFIFOReader
    toBuffer &= !mem.nonBlocking.get
    if (mem.isFIFO && !singleWriter) {
      todo(s"Do not support multiple writers to FIFO on Plasticine yet ${quoteSrcCtx(mem)}")
    }
    toBuffer
  }
  private val enCtxs = mutable.Map[ControlTree, Context]()

  override def finPass = {
    super.finPass
    enCtxs.clear
  }

  override def visitNode(n:N) = n match {
    case n:Memory if canLocalize(n) => bufferLowering(n)
    case _ => super.visitNode(n)
  }

  private def bufferLowering(mem:Memory) = dbgblk(s"bufferLowering($mem)") {
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
          val enCtx = enCtxs.getOrElseUpdate(readCtrl, within(pirTop, readCtrl) { stage(Context()) } )
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

}

