package pir.mapper
import pir.{Config}
import pir.Design
import pir.util.typealias._
import pir.plasticine.main._
import pir.util.enums._
import pir.pass.{PIRMapping}
import pir.codegen.{CUCtrlDotPrinter}
import pir.exceptions._
import pir.plasticine.util._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.{ Map => MMap }
import scala.util.{Try, Success, Failure}
import scala.language.existentials

class CtrlMapper(implicit val design:Design) extends Mapper with LocalRouter {
  import pirmeta._

  val typeStr = "CtrlMapper"
  override def debug = Config.debugCtrlMapper

  def finPass(cu:CL)(m:M):M = m

  def map(cu:CL, pirMap:M):M = {
    log(cu) {
      cu match {
        case cu:Top => mapCtrl(cu, pirMap)
        case cu:CU => mapCtrl(cu, pirMap) 
      }
    }
  }

  def mapCtrl(cu:CU, pirMap:M):M = {
    var mp = pirMap
    val pcu = pirMap.clmap(cu)
    mp = mapDone(cu, pcu, mp)
    mp = mapUDCs(cu, pcu, mp)
    mp = mapAndTrees(cu, pcu, mp)
    mp = mapMemoryWrite(cu, pcu, mp)
    mp = mapMemoryRead(cu, pcu, mp)
    mp = mapTokenOut(cu, pcu, mp)
    mp = mapEn(cu, pcu, mp)
    mp
  }

  def mapCtrl(cu:Top, pirMap:M):M = {
    var mp = pirMap
    val pcu = pirMap.clmap(cu)
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    mp = mapInPort(cb.status, pcb.status, mp)
    mp = mapOutPort(cb.command, pcb.command, mp)
    mp = mapTokenOut(cu, pcu, mp)
    mp
  }

  def mapMemoryWrite(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    cu.mems.foreach { mem => 
      val pmem = mp.smmap(mem)
      (mem, pcu) match {
        case (mem:SFIFO, pcu:PCL) => 
          val pin = mp.vimap(mem.enqueueEnable)
          mp = mp.setFI(pmem.incWritePtr, pin.ic) // enqEnable
        case (mem:VFIFO, pcu:PCU) => // enqueEnable is implicit through databus
        case (mem:SRAM, pcu:PMCU) =>
          mp = mp.setFI(pmem.incWritePtr, pcu.ctrlBox.writeDoneXbar.out) // swapWrite
        case (mem:SBuf, pcu:PCU) if mem.swapWrite.isCtrlIn =>
          val pvi = mp.vimap(mem.swapWrite)
          mp = mp.setFI(pmem.incWritePtr, pvi.ic) // swapWrite
        case (mem:SBuf, pcu:PCU) if (mem.buffering == 1) => // Not multibuffered
        case (mem:SBuf, pcu:PCU) if (mem.swapWrite.isConnected) =>
          throw new Exception(s"$mem's swapWrite is not ctrlIn, ${mem.swapWrite.from}")
      }
    }
    mp
  }

  def mapMemoryRead(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val pcb = pcu.ctrlBox
    cu.mbuffers.foreach { mbuf => 
      (mbuf, pcb) match {
        case (sram:SRAM, pcb:PMCB) =>
          val psram = mp.smmap(sram)
          mp.setFI(psram.incReadPtr, pcb.readDoneXbar.out)
        case (smem:SMem, pcb:PMCB) if forWrite(smem) =>
          val psmem = mp.smmap(smem)
          mp.setFI(psmem.incReadPtr, pcb.writeDoneXbar.out)
        case (smem:SMem, pcb:PMCB) if forRead(smem) =>
          val psmem = mp.smmap(smem)
          mp.setFI(psmem.incReadPtr, pcb.readDoneXbar.out)
        case (smem:SMem, pcb:PICB) =>
          val psmem = mp.smmap(smem)
          mp.setFI(psmem.incReadPtr, pcb.doneXbar.out)
        case (smem:SMem, pcb:POCB) =>
          val psmem = mp.smmap(smem)
          mp.setFI(psmem.incReadPtr, pcb.doneXbar.out)
      }
    }
    mp
  }

  def mapDone(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    (cu, pcu.ctrlBox) match {
      case (cu:MP, pcb:PMCB) =>
        val writeCtr = mp.ctmap(writeCChainsOf(cu).last.outer)
        val readCtr = mp.ctmap(readCChainsOf(cu).last.outer)
        mp = mp.setFI(pcb.writeDoneXbar.in, writeCtr.done)
        mp = mp.setFI(pcb.readDoneXbar.in, readCtr.done)
      case (cu:ICL, pcb:PICB) =>
        val doneCtr = mp.ctmap(compCChainsOf(cu).last.outer)
        mp = mp.setFI(pcb.doneXbar.in, doneCtr.done)
      case (cu:OCL, pcb:POCB) =>
        val doneCtr = mp.ctmap(compCChainsOf(cu).last.outer)
        mp = mp.setFI(pcb.doneXbar.in, doneCtr.done)
      case (cu, pcb) =>
    }
    mp
  }

  def mapAndTree(at:AT, pat:PAT, pirMap:M):M = {
    var mp = pirMap
    mp = mp.setPM(at, pat)
    mp = mp.setOP(at.out, pat.out)
    at.ins.foreach { in =>
      val po = if (in.isCtrlIn) { mp.vimap(in).ic } else { mp.opmap(in.from) }
      val pins = pat.ins.filter { _.canConnect(po) }
      var info = s"Mapping $at to $pat in ${at.ctrler}\n"
      info += s"in=$in, from=${in.from}, po=$po \n"
      info += s"$pat'ins mapped to $po = [${pins.mkString(",")}]"
      assert(pins.size==1, info)
      mp = mp.setIP(in, pins.head)
    }
    mp
  }

  def mapAndTrees(cu:CU, pcu:PCL, pirMap:M):M = {
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    var mp = pirMap
    (cb, pcb) match {
      case (cb:ICB, pcb:PICB) =>
        mp = mapAndTree(cb.siblingAndTree, pcb.siblingAndTree, mp)
        mp = mapAndTree(cb.fifoAndTree, pcb.fifoAndTree, mp)
        mp = mapAndTree(cb.tokenInAndTree, pcb.tokenInAndTree, mp)
        mp = mapAndTree(cb.andTree, pcb.andTree, mp)
      case (cb:OCB, pcb:POCB) =>
        mp = mapAndTree(cb.siblingAndTree, pcb.siblingAndTree, mp)
        mp = mapAndTree(cb.childrenAndTree, pcb.childrenAndTree, mp)
      case (cb:MCB, pcb:PMCB) =>
        mp = mapAndTree(cb.readFifoAndTree, pcb.readFifoAndTree, mp)
        mp = mapAndTree(cb.writeFifoAndTree, pcb.writeFifoAndTree, mp)
      case _ =>
    }
    mp
  }

  def mapEn(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    (cb, pcb) match {
      case (cb:MCB, pcb:PMCB) =>
        mp = mp.setFI(pcb.readEn.in, pcb.readFifoAndTree.out)
        mp = mp.setFI(pcb.writeEn.in, pcb.writeFifoAndTree.out)
        mp = mp.setOP(cb.readEnable, pcb.readEn.out)
        mp = mp.setOP(cb.writeEnable, pcb.writeEn.out)
      case (cb:OCB, pcb:POCB) =>
        mp = mp.setFI(pcb.en.in, pcb.childrenAndTree.out)
        mp = mp.setOP(cb.enable, pcb.en.out)
      case (cb:ICB, pcb:PICB) if isStreaming(cu) =>
        mp = mp.setFI(pcb.en.in, pcb.andTree.out)
        mp = mp.setOP(cb.enable, pcb.en.out)
      case (cb:ICB, pcb:PICB) if isPipelining(cu) =>
        mp = mp.setFI(pcb.en.in, pcb.siblingAndTree.out)
        mp = mp.setOP(cb.enable, pcb.en.out)
      case (cb:CB, pcb:PCB) =>
        assert(cb.ctrler.isInstanceOf[MC])
        assert(pcb.pne.isInstanceOf[PMC])
    }
    cu.cchains.flatMap(_.counters).foreach { ctr =>
      val pctr = mp.ctmap(ctr)
      mp = mp.setFI(pctr.en, mp.opmap(ctr.en.from))
    }
    mp
  }

  def mapTokenOut(cu:CL, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    cb.ctrlOuts.foreach { co =>
      (cb, co.src, pcb) match {
        case (cb:CB, ctr:Ctr, pcb:PMCB) if forWrite(ctr) => // writeDone
          assert(mp.fimap(pcb.writeDoneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.writeDoneXbar.out)
          }
        case (cb:CB, ctr:Ctr, pcb:PMCB) if forRead(ctr) => // readDone
          assert(mp.fimap(pcb.readDoneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.readDoneXbar.out)
          }
        case (cb:CB, ctr:Ctr, pcb:PICB) => // done
          assert(mp.fimap(pcb.doneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.doneXbar.out)
          }
        case (cb:CB, ctr:Ctr, pcb:POCB) => // done
          assert(mp.fimap(pcb.doneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.doneXbar.out)
          }
        case (cb:ICB, at:AT, pcb:PICB) if at == cb.siblingAndTree =>
          assert(false, s"Unsupported connection from siblingAndTree to tokenOut in PCU")
        case (cb:OCB, at:AT, pcb:POCB) if at == cb.siblingAndTree => // siblingAndTree.out
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.siblingAndTree.out)
          }
        case (_, cb:ICB, pcb:PICB) if co == cb.enable => // enable
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.en.out)
          }
        case (_, cb:OCB, pcb:POCB) if co == cb.pulserSMOut =>
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.pulserSM.out )
          }
        case (_, cb:TCB, pcb:PTCB) =>
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.command )
          }
      }
    }
    mp
  }

  def mapUDCs(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    cb.udcounters.foreach { case (dep, udc) =>
      val pudc = pcb.udcs.filterNot { pudc => mp.pmmap.pmap.contains(pudc) }.head
      mp = mp.setPM(udc, pudc).setIP(udc.inc, pudc.inc).setIP(udc.dec, pudc.dec).setOP(udc.out, pudc.out)
      assert(udc.inc.isCtrlIn)
      val pvi = mp.vimap(udc.inc)
      mp = mp.setFI(pudc.inc, pvi.ic)
      pcb match {
        case pcb:PICB =>
          println(udc)
          assert(mp.opmap(udc.dec.from) == mp.fimap(pcb.doneXbar.in))
          mp = mp.setFI(pudc.dec, pcb.doneXbar.out)
        case pcb:POCB if isPipelining(cu) =>
          assert(mp.opmap(udc.dec.from) == mp.fimap(pcb.doneXbar.in))
          mp = mp.setFI(pudc.dec, pcb.doneXbar.out)
        case pcb:POCB if isStreaming(cu) =>
          mp = mapInPort(udc.dec, pudc.dec, mp)
      }
    }
    mp
  }

}

case class NotReachable(to:CL, topcu:PCL, fromcu:CL, frompcu:Option[PCL], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
