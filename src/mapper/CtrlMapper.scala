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
    mp = mapAndTree(cu.ctrlBox, pcu.ctrlBox, mp)
    mp = mapDone(cu, pcu, mp)
    mp = mapUDCs(cu, pcu, mp)
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

  def mapMemoryWrite(cu:CU, pcu:PCU, pirMap:M):M = {
    var mp = pirMap
    pcu.mems.foreach { pmem =>
      mp.smmap.pmap.get(pmem).foreach { mem =>
        (mem, pcu) match {
          case (mem:SFIFO, pcu:PCU) =>
            //mp = mp.setFI
          case (mem:SRAM, pcu:PMCU) =>
            mp = mp.setFI(pmem.incWritePtr, pcu.ctrlBox.writeDoneXbar.out)
          case (mem:SBuf, pcu:PCU) if mem.swapWrite.isCtrlIn =>
            val pvi = mp.vimap(mem.swapWrite)
            mp = mp.setFI(pmem.incWritePtr, pvi.ic)
          case (mem:SBuf, pcu:PCU) if (mem.buffering == 1) => // Not multibuffered
          case (mem:SBuf, pcu:PCU) if (mem.swapWrite.isConnected) =>
            throw new Exception(s"$mem's swapWrite is not ctrlIn, ${mem.swapWrite.from}")
        }
      }
    }
    mp
  }

  def mapMemoryRead(cu:CU, pcu:PCU, pirMap:M):M = {
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

  def mapDone(cu:CU, pcu:PCU, pirMap:M):M = {
    var mp = pirMap
    (cu, pcu) match {
      case (cu:MP, pcu:PMCU) =>
        val writeCtr = mp.ctmap(writeCChainsOf(cu).last.outer)
        val readCtr = mp.ctmap(readCChainsOf(cu).last.outer)
        mp = mp.setFI(pcu.ctrlBox.writeDoneXbar.in, writeCtr.done)
        mp = mp.setFI(pcu.ctrlBox.readDoneXbar.in, readCtr.done)
      case (cu:ICL, pcu:PCU) =>
        assert(cu.cchains.size==1)
        val ctr = mp.ctmap(cu.cchains.head.outer)
        mp = mp.setFI(pcu.ctrlBox.asInstanceOf[PICB].doneXbar.in, ctr.done)
      case (cu:OCL, pcu:POCU) =>
        assert(cu.cchains.size==1)
        val ctr = mp.ctmap(cu.cchains.head.outer)
        mp = mp.setFI(pcu.ctrlBox.doneXbar.in, ctr.done)
    }
    mp
  }

  def mapAndTree(cb:CB, pcb:PCB, pirMap:M):M = {
    var mp = pirMap
    (cb, pcb) match {
      case (cb:SCB, pcb:PICB) =>
        mp = mp.setOP(cb.siblingAndTree.out, pcb.siblingAndTree.out)
      case (cb:SCB, pcb:POCB) =>
        mp = mp.setOP(cb.siblingAndTree.out, pcb.siblingAndTree.out)
      case _ =>
    }
    (cb, pcb) match {
      case (cb:OCB, pcb:POCB) =>
        mp = mp.setOP(cb.childrenAndTree.out, pcb.childrenAndTree.out)
      case _ =>
    }
    mp
  }

  def mapEn(cu:CU, pcu:PCU, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    pcb match {
      case pcb:PMCB =>
        mp = mp.setFI(pcb.readEn.in, pcb.readFIFOAndTree.out)
        mp = mp.setFI(pcb.writeEn.in, pcb.writeFIFOAndTree.out)
        cu.mems.foreach { mem =>
          val pbuf = mp.smmap(mem).asBuf
          mem match {
            case mem if forRead(mem) => pcb.readFIFOAndTree <== pbuf.notEmpty
            case mem if forWrite(mem) => pcb.writeFIFOAndTree <== pbuf.notEmpty
          }
        }
        pcu.ctrs.foreach { pctr =>
          mp.ctmap.pmap.get(pctr).foreach { ctr =>
            if (ctr.en.isConnected) {
              val prevCtr = ctr.en.from.src.asInstanceOf[Ctr]
              val pPrevCtr = mp.ctmap(prevCtr)
              mp = mp.setFI(pctr.en, pPrevCtr.done)
            } else if (ctr==readCChainsOf(mp.clmap.pmap(pcb.pne)).head.inner) {
              mp = mp.setFI(pctr.en, pcb.readEn.out)
            } else if (ctr==writeCChainsOf(mp.clmap.pmap(pcb.pne)).head.inner) {
              mp = mp.setFI(pctr.en, pcb.writeEn.out)
            }
          }
        }
      case pcb:POCB =>
        mp = mp.setFI(pcb.en.in, pcb.childrenAndTree.out)
      case pcb:PICB => // TODO check streaming or pipelining
        //mp = mp.setFI(pcb.en.in, pcb.childrenAndTree.out)
    }
    mp
  }

  def mapTokenOut(cu:CL, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val pcb = pcu.ctrlBox
    cu.ctrlBox.ctrlOuts.foreach { co =>
      (co.src, pcb) match {
        case (ctr:Ctr, pcb:PMCB) if forWrite(ctr) => // writeDone
          assert(mp.fimap(pcb.writeDoneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.writeDoneXbar.out)
          }
        case (ctr:Ctr, pcb:PMCB) if forRead(ctr) => // readDone
          assert(mp.fimap(pcb.readDoneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.readDoneXbar.out)
          }
        case (ctr:Ctr, pcb:PICB) => // done
          assert(mp.fimap(pcb.doneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.doneXbar.out)
          }
        case (ctr:Ctr, pcb:POCB) => // done
          assert(mp.fimap(pcb.doneXbar.in) == mp.opmap(co))
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.doneXbar.out)
          }
        case (sbat:SBAT, pcb:PICB) => // siblingAndTree.out
          assert(false, s"Unsupported connection from siblingAndTree to tokenOut in PCU")
        case (sbat:SBAT, pcb:POCB) => // siblingAndTree.out
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.siblingAndTree.out)
          }
        case (cb:ICB, pcb:PICB) if co == cb.enable => // enable
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.en.out)
          }
        case (cb:OCB, pcb:POCB) if co == cb.pulserSMOut =>
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.pulserSM.out )
          }
        case (cb:TCB, pcb:PTCB) =>
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.command )
          }
      }
    }
    mp
  }

  def mapUDCs(cu:CU, pcu:PCU, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    cb.udcounters.foreach { case (dep, udc) =>
      val pudc = pcb.udcs.filterNot { pudc => mp.pmmap.pmap.contains(pudc) }.head
      mp = mp.setPM(udc, pudc).setIP(udc.inc, pudc.inc).setIP(udc.dec, pudc.dec).setOP(udc.out, pudc.out)
      //TODO
      //pcb match {
        //case pcb:PICB =>
          //assert(mp.opmap(udc.dec.from) == mp.fimap(pcb.doneXbar.in))
          //mp = mp.setFI(pudc.dec, pcb.doneXbar.out)
        //case pcb:POCB =>
          //assert(mp.opmap(udc.dec.from) == mp.fimap(pcb.doneXbar.in))
          //mp = mp.setFI(pudc.dec, pcb.doneXbar.out)
      //}
      assert(udc.inc.isCtrlIn)
      val pvi = mp.vimap(udc.inc)
      mp = mp.setFI(pudc.inc, pvi.ic)
    }
    mp
  }

}

case class NotReachable(to:CL, topcu:PCL, fromcu:CL, frompcu:Option[PCL], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
