package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait LocalMemoryLowering extends GenericMemoryLowering {
  private val enCtxs = mutable.Map[ControlTree, Context]()

  private var fifoDepth = 0
  private var sramParam:SRAMParam = null
  override def initPass = {
    super.initPass
    fifoDepth = assertUnify(spadeParam.traceIn[FIFOParam], "fifoParam") { _.depth }.get
    sramParam = assertIdentical(spadeParam.traceIn[PMUParam], "PMUParam").get.sramParam
  }

  override def finPass = {
    super.finPass
    enCtxs.clear
  }

  def canLocalize(mem:Memory) = {
    val noBankedAccess = mem.accesses.forall { !_.isInstanceOf[BankedAccess] }
    val singleWriter = mem.inAccesses.size <= 1
    val singleFIFOReader = !mem.isFIFO | mem.outAccesses.size <= 1
    var toBuffer = true
    toBuffer &= noBankedAccess
    toBuffer &= singleWriter //&& singleFIFOReader
    toBuffer &= !mem.nonBlocking.get
    // toBuffer |= mem.isFIFO && mem.retiming.get //RETIMING FIX
    if (mem.isFIFO && !singleWriter) {
      todo(s"Do not support multiple writers to FIFO on Plasticine yet ${quoteSrcCtx(mem)}")
    }
    toBuffer
  }

  def isScratchpadFIFO(mem:Memory):Boolean = {
    dbg(s"Test scractchpad FIFO: $mem")
    dbg(s"inAccesses: ${mem.inAccesses}")
    dbg(s"outAccesses: ${mem.outAccesses}")
    if (!mem.isFIFO) return false
    val writer = testOne(mem.inAccesses).getOrElse(return false)
    val reader = testOne(mem.outAccesses).getOrElse(return false)
    dbg(s"writerVec: ${writer.getVec}")
    dbg(s"readerVec: ${reader.getVec}")
    if (writer.getVec != reader.getVec) return false
    if (writer.getVec == 1) {
      mem.depth.get > fifoDepth*16
    } else {
      mem.depth.get > fifoDepth
    }
  }

  override def visitNode(n:N) = n match {
    case n:Memory if canLocalize(n) => 
      if (isScratchpadFIFO(n)) lowerScratchpadFIFO(n) else bufferLowering(n)
    case _ => super.visitNode(n)
  }

  private def lowerScratchpadFIFO(mem:Memory) = {
    val reader = mem.outAccesses.head.as[MemRead]
    val toscan = reader.toScanController.get 
    val writer = mem.inAccesses.head.as[MemWrite]
    val sramDepth = sramParam.sizeInWord / reader.getVec
    within(pirTop, reader.getCtrl) {
      val glob = stage(MemoryContainer().name.mirror(mem.name).srcCtx.mirror(mem.srcCtx))
      val delay = within(glob) {
        val ctx = stage(Context().streaming(true).name.mirror(mem.name).srcCtx.mirror(mem.srcCtx))
        within(ctx) {
          stage(ScratchpadDelay(sramDepth)
            .name.mirror(mem.name)
            .srcCtx.mirror(mem.srcCtx)
            .in(writer.data.connected))
        }
      }
      bufferInput(delay.in).foreach { read =>
        read.inAccess.en(writer.en.connected)
        read.retiming(mem.as[FIFO].retiming.get)
      }
      val readEn = reader.en.connected
      swapOutput(reader.out, delay.out)
      delay.out.connected.foreach { in =>
        bufferInput(in).foreach { read =>
          read.en(readEn)
          dbg(s"Read: ${in}")
        }
        dbg(s"ToScan: ${toscan}")
        dbg(s"In: ${in}")
        dbg(s"In.T: ${in.connected.head}")
        dbg(s"In.T.parent: ${in.connected.head.src}")
        // dbg(s"In.T.MemRead: ${in.connected.head.T.asInstanceOf[MemRead]}")
        in.connected.head.src.asInstanceOf[BufferRead].toScanController(toscan)
        // in.toScanController(reader.toScanController.get)
      }
    }
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
            val write = BufferWrite(mem.isFIFO)
              .data(inAccess.data.connected)
              .mirrorMetas(inAccess)
              .en(inAccess.en.connected)
              .done(enq)
            write.out.presetVec(inAccess.inferVec.get)
            stage(write)
          }
        }
        val readCtx = outAccess.parent.get.as[Context]
        val readCtrl = outAccess.ctrl.get
        val (remoteReadEns, localReadEns) = outAccess.en.connected.partition { !canDuplicate(_) }
        dbg(s"remoteReadEns=${remoteReadEns.map(dquote)}")
        val remoteReadEn = if (remoteReadEns.nonEmpty) {
          val enCtx = enCtxs.getOrElseUpdate(readCtrl, within(pirTop, readCtrl) { stage(Context()) } )
          within(enCtx, readCtrl) {
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
            val read = BufferRead(mem.isFIFO)
              .in(write.out)
              .mirrorMetas(outAccess)
              .mirrorMetas(mem)
              .en(localReadEns).en(remoteReadEn.map{_._2})
              .done(deq)
            read.out.presetVec(outAccess.out.inferVec.get)
            read.banks.reset
            read.banks(List(outAccess.out.inferVec.get))
            if (mem.depth.get > fifoDepth) {
              read.deepScalar(true)
            }
            stage(read)
          }
        }
        remoteReadEn.foreach { case (enCtx,en) =>
          bufferInputFrom(en, read.en, enCtx)
        }
        if (inAccess.order.get > outAccess.order.get ) {
          dbg(s"$read.initToken = 1")
          read.initToken := 1
        }
        swapOutput(outAccess.out, read.out)
      }
    }
    removeNodes(mem.accesses :+ mem)
  }

}

