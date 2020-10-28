package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

class ScanLowering(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer with BufferAnalyzer with MemoryAnalyzer {

  override def runPass = {
    withGC(false) {
      super.runPass
    }
  }

  override def visitNode(n:N) = {
    processScanCounter(n)
    processDataScanCounter(n)

    super.visitNode(n)
  } 

  def processDataScanCounter(n:N) = {
    // Insert a Scanner for loop controller including scancounters
    n.to[LoopController].foreach { n =>
      val ctrs = n.cchain.T
      if (ctrs.exists { case x:DataScanCounter => true; case _ => false }) {
        val refCtr = ctrs.head.as[DataScanCounter]
        val scanCtrl = refCtr.mask.T.asInstanceOf[BufferRead].inAccess.as[BufferWrite].data.T.getCtrl
        val locScanCtrl = refCtr.getCtrl
        within(pirTop, scanCtrl) {
          val scannerCtx = stage(Context().streaming(true))
          val scanner = within(scannerCtx) { stage(DataScanner()) }

          // Assume a fixed number of counters---one for data, one for count
          assert(ctrs.size == 2, "Counters have size " + ctrs.size)
          val c0 = ctrs(0).as[DataScanCounter]
          val c1 = ctrs(1).as[DataScanCounter]
          assert(!c0.data)
          assert(c1.data)

          //val read = c0.mask.T.asInstanceOf[MemRead]
          //val writer = assertOne(read.mem.T.inAccesses, s"$n.mask writer").as[MemWrite]
          //val scanRead = writer.data.T.asInstanceOf[OutAccess]
          //scanRead.out.vecMeta.reset
          //scanRead.out.presetVec(16)
          //scanner.mask(scanRead)

          // Setup mask relative to a reference counter
          val scanBR = c0.mask.T.as[BufferRead]
          val scanBW = scanBR.inAccess.as[BufferWrite]
          val dataBR = c1.mask.T.as[BufferRead]
          val dataBW = dataBR.inAccess.as[BufferWrite]

          // val br = refCtr.mask.T.as[BufferRead]
          //val bw = scanBR.inAccess.as[BufferWrite]
          dbg(s"ScanBR: ${scanBR}")
          dbg(s"ScanBW: ${scanBW}")
          dbg(s"ScanBW.data.connected: ${scanBW.data.connected}")
          val outField = scanBW.data.connected.head
          dbg(s"outField.src: ${outField.src}")
          val scanBWReal = outField.src.as[BufferRead].inAccess.as[BufferWrite]
          dbg(s"scanBWReal: ${scanBWReal}")
          dbg(s"scanBWReal.data.connected: ${scanBWReal.data.connected}")

          scanner.mask(scanBWReal.data.connected)
          bufferInput(scanner.mask)

          c0.mask.disconnect
          c1.mask.disconnect
          // free(scanBR)
          // free(dataBR)

          c0.cnt(scanner.cnt)
          c0.indOrData(scanner.index)
          c1.cnt(scanner.cnt)
          c1.indOrData(scanner.data)

          bufferOutputMulti(scanner.cnt).foreach { n =>
            n.ctrl.reset
            n.ctrl(locScanCtrl)
            n.done.disconnect
            n.done(tileDone(locScanCtrl, refCtr.ctx.get)) 
          }
          bufferOutputMulti(scanner.index).foreach { n =>
            n.ctrl.reset
            n.ctrl(locScanCtrl)
            n.done.disconnect
            n.done(subTileDone(locScanCtrl, refCtr.ctx.get)) 
          }
          bufferOutputMulti(scanner.data).foreach { n =>
            n.ctrl.reset
            n.ctrl(locScanCtrl)
            n.done.disconnect
            n.done(subTileDone(locScanCtrl, refCtr.ctx.get)) 
          }
        }
      }
    }
  }

  def processScanCounter(n:N) = {
    // Insert a Scanner for loop controller including scancounters
    n.to[LoopController].foreach { n =>
      val ctrs = n.cchain.T
      if (ctrs.exists { case x:ScanCounter => true; case _ => false }) {
        val refCtr = ctrs.head.as[ScanCounter]
        val par = refCtr.par
        val scanCtrl = refCtr.mask.T.asInstanceOf[BufferRead].inAccess.as[BufferWrite].data.T.getCtrl
        // val scanCtrl = refCtr.getCtrl
        val locScanCtrl = refCtr.getCtrl
        //val scanner = within(pirTop, scanCtrl, n.srcCtx.get) {
        dbg(s"scanCtrl: ${scanCtrl}")
        within(pirTop, scanCtrl) {
          val scannerCTX = stage(Context().streaming(true))
          val scanner = within(scannerCTX) { stage(Scanner(par, ctrs.size/2, refCtr.mode)) }

          val ctrsIndex = ctrs.filter { n => !n.isInstanceOf[ScanCounterDataFollower] }
          val ctrsData = ctrs.filter { n => n.isInstanceOf[ScanCounterDataFollower] }
          // Loop over counter pairs, and connect ctrl/vecTotalSet/packCntIdx
          (ctrsIndex zip ctrsData zip scanner.masks zip scanner.vecTotals).foreach { case (((ctr, follow), mask), vecTotalSet) =>
            val scanCtr = ctr.as[ScanCounter]
            val dataCtr = follow.as[ScanCounterDataFollower]

            // Setup mask relative to a reference counter
            val scanBR = scanCtr.mask.T.as[BufferRead]
            val scanBW = scanBR.inAccess.as[BufferWrite]
            val dataBR = dataCtr.mask.T.as[BufferRead]
            val dataBW = dataBR.inAccess.as[BufferWrite]

            // val br = refCtr.mask.T.as[BufferRead]
            //val bw = scanBR.inAccess.as[BufferWrite]
            dbg(s"ScanBR: ${scanBR}")
            dbg(s"ScanBW: ${scanBW}")
            dbg(s"ScanBW.data.connected: ${scanBW.data.connected}")
            val outField = scanBW.data.connected.head
            dbg(s"outField.src: ${outField.src}")
            val scanBWReal = outField.src.as[BufferRead].inAccess.as[BufferWrite]
            dbg(s"scanBWReal: ${scanBWReal}")
            dbg(s"scanBWReal.data.connected: ${scanBWReal.data.connected}")

            mask(scanBWReal.data.connected)
            bufferInput(mask)

            scanCtr.mask.disconnect
            dataCtr.mask.disconnect
            // free(scanBR)
            // free(dataBR)

            scanCtr.ctrlWord(scanner.ctrlWord)
            dataCtr.ctrlWord(scanner.ctrlWord)

            scanCtr.packCntIdx(scanner.packedCntIdx)
            dataCtr.packCntIdx(scanner.packedCntIdx)
            
            dataCtr.vecTotalSet(vecTotalSet)
            bufferInput(dataCtr.vecTotalSet, param=BufferParam(isFIFO=false)).foreach { n => 
              n.ctrl.reset
              n.ctrl(locScanCtrl)
              n.done.disconnect
              n.done(tileDone(locScanCtrl, refCtr.ctx.get)) 
            }
          }
          bufferOutputMulti(scanner.ctrlWord, param=BufferParam(isFIFO=false)).foreach { n =>
            n.ctrl.reset
            n.ctrl(locScanCtrl)
            n.done.disconnect
            n.done(tileDone(locScanCtrl, refCtr.ctx.get)) 
          }
          bufferOutputMulti(scanner.packedCntIdx, param=BufferParam(isFIFO=false)).foreach { n =>
            n.ctrl.reset
            n.ctrl(locScanCtrl)
            n.done.disconnect
            n.done(subTileDone(locScanCtrl, refCtr.ctx.get)) 
          }
        }
      }
    }
  }
}
