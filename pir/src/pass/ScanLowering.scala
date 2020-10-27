package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

class ScanLowering(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer with BufferAnalyzer {

  override def runPass = {
    withGC(false) {
      super.runPass
    }
  }

  override def visitNode(n:N) = {
    processScanCounter(n)
    // TODO: processDataScanCounter(n)

    super.visitNode(n)
  } 

  /*def processDataScanCounter(n:N) = {
    // Insert a Scanner for loop controller including scancounters
    n.to[LoopController].foreach { n =>
      val ctrs = n.cchain.T
      if (ctrs.exists { case x:DataScanCounter => true; case _ => false }) {
        // val par = ctrs.last.as[ScanCounter].par
        val scanCtrl = ctrs.head.as[DataScanCounter].mask.T.asInstanceOf[MemRead].mem.T.inAccesses.head.as[MemWrite].data.T.getCtrl
        val scanner = within(pirTop, scanCtrl, n.srcCtx.get) {
          stage(DataScanner())
        }
        val cntFIFO = within(pirTop) {
          stage(FIFO().banks(List(1)).name("cntFIFO"))
        }
        val cntWrite = within(pirTop, scanCtrl) {
          stage(MemWrite().setMem(cntFIFO).data(scanner.cnt))
        }
        val cntRead = within(pirTop, n.getCtrl) {
          stage(MemRead().setMem(cntFIFO).toScanController(true)).done(n.done)
        }

        // Assume a fixed number of counters---one for data, one for count
        assert(ctrs.size == 2, "Counters have size " + ctrs.size)
        val c0 = ctrs(0).as[DataScanCounter]
        val c1 = ctrs(1).as[DataScanCounter]
        assert(!c0.data)
        assert(c1.data)

        val read = c0.mask.T.asInstanceOf[MemRead]
        val writer = assertOne(read.mem.T.inAccesses, s"$n.mask writer").as[MemWrite]
        val scanRead = writer.data.T.asInstanceOf[OutAccess]
        scanRead.out.vecMeta.reset
        scanRead.out.presetVec(16)
        scanner.mask(scanRead)

        val indexFIFO = within(pirTop) {
          stage(FIFO().banks(List(1)).name("indexFIFO"))
        }
        val indexWrite = within(pirTop, scanCtrl) {
          stage(MemWrite().setMem(indexFIFO).data(scanner.index))
        }
        val indexRead = within(pirTop, n.getCtrl) {
          stage(MemRead().setMem(indexFIFO).toScanController(true))
        }
        val dataFIFO = within(pirTop) {
          stage(FIFO().banks(List(1)).name("dataFIFO"))
        }
        val dataWrite = within(pirTop, scanCtrl) {
          stage(MemWrite().setMem(dataFIFO).data(scanner.data))
        }
        val dataRead = within(pirTop, n.getCtrl) {
          stage(MemRead().setMem(dataFIFO).toScanController(true))
        }

        c0.mask.disconnect
        c0.cnt(cntRead.out)
        c0.indOrData(indexRead.out)
        c1.mask.disconnect
        c1.cnt(cntRead.out)
        c1.indOrData(dataRead.out)
        cntRead.done(n.done)
      }
    }
  } */

  def processScanCounter(n:N) = {
    // Insert a Scanner for loop controller including scancounters
    n.to[LoopController].foreach { n =>
      val ctrs = n.cchain.T
      if (ctrs.exists { case x:ScanCounter => true; case _ => false }) {
        val refCtr = ctrs.head.as[ScanCounter]
        val par = refCtr.par
        val scanCtrl = refCtr.mask.T.asInstanceOf[BufferRead].inAccess.as[BufferWrite].data.T.getCtrl
        //val scanner = within(pirTop, scanCtrl, n.srcCtx.get) {
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
            free(scanBR)
            free(dataBR)

            scanCtr.ctrlWord(scanner.ctrlWord)
            dataCtr.ctrlWord(scanner.ctrlWord)
            // dataCtr.ctrlWord(scanner.ctrlWord.as[BufferWrite])
            bufferOutputMulti(scanner.ctrlWord)


            scanCtr.packCntIdx(scanner.packedCntIdx)
            dataCtr.packCntIdx(scanner.packedCntIdx)
            bufferOutputMulti(scanner.packedCntIdx)

            // bufferInput(scanCtr.ctrlWord)
            // bufferInput(scanCtr.packCntIdx)


            // bufferInput(dataCtr.ctrlWord)
            // bufferInput(dataCtr.packCntIdx)
            
            dataCtr.vecTotalSet(vecTotalSet)
            bufferInput(dataCtr.vecTotalSet)

          }
        }
      }
    }
  }
}
