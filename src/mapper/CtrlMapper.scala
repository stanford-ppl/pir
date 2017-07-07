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
    if (!design.ctrlAlloc.hasRun) return pirMap
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
    mp = mp.setPM(cu.ctrlBox, pcu.ctrlBox)
    mp = mapEnOut(cu, pcu, mp)
    mp = mapCounters(cu, pcu, mp)
    mp = mapPredUnits(cu, pcu, mp)
    mp = mapDone(cu, pcu, mp)
    mp = mapUDCs(cu, pcu, mp)
    mp = mapMemoryWrite(cu, pcu, mp)
    mp = mapAndTrees(cu, pcu, mp)
    mp = mapUDCIns(cu, pcu, mp)
    mp = mapMemoryRead(cu, pcu, mp)
    mp = mapEnIn(cu, pcu, mp)
    mp = mapEnAnd(cu, pcu, mp)
    mp = mapTokenOut(cu, pcu, mp)
    mp
  }

  def mapCtrl(cu:Top, pirMap:M):M = {
    var mp = pirMap
    val pcu = pirMap.clmap(cu)
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    mp = mp.setPM(cu.ctrlBox, pcu.ctrlBox)
    mp = mapInPort(cb.status, pcb.status, mp)
    mp = mapOutPort(cb.command, pcb.command, mp)
    mp = mapTokenOut(cu, pcu, mp)
    mp
  }

  def mapEnAnd(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    (cu, pcu.ctrlBox) match {
      case (cu:OCL, pcb:POCB) =>
        val pin = pcb.enAnd.ins(1)
        val pout = cu match {
          case cu:Seq => pin.fanIns.filter{_.src.isInstanceOf[PUDSM]}.head
          case cu => pin.fanIns.filter{_.src.isInstanceOf[PConst]}.head
        }
        mp = mp.setFI(pin, pout)
      case _ =>
    }
    return mp
  }

  def mapMemoryWrite(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    cu.mems.foreach { mem => 
      val pmem = mp.smmap(mem)
      (mem, pmem, pcu) match {
        case (mem:SFIFO, pmem:PSMem, pcu:PCL) => 
          mp = mp.setIP(mem.enqueueEnable, pmem.incWritePtr)
          mp = mp.setOP(mem.notEmpty, pmem.notEmpty)
          mp = mp.setOP(mem.notFull, pmem.notFull)
        case (mem:VFIFO, pmem:PVMem, pcu:PCL) => // enqueEnable is implicit through databus
          mp = mp.setIP(mem.enqueueEnable, pmem.incWritePtr)
          mp = mp.setOP(mem.notEmpty, pmem.notEmpty)
          mp = mp.setOP(mem.notFull, pmem.notFull)
        case (mem:MBuf, pmem:POCM, pcu:PCU) =>
          if (mem.swapWrite.isConnected) {
            mp = mapInPort(mem.swapWrite, pmem.incWritePtr, mp)
          } else {
            mp = mp.setIP(mem.swapWrite, pmem.incWritePtr)
          }
      }
    }
    mp
  }

  def mapMemoryRead(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val pcb = pcu.ctrlBox
    cu.mbuffers.foreach { mbuf => 
      val pmem = mp.smmap(mbuf)
      if (mbuf.swapRead.isConnected) {
        mp = mapInPort(mbuf.swapRead, pmem.incReadPtr, mp)
      } else {
        mp = mp.setIP(mbuf.swapRead, pmem.incReadPtr)
      }
    }
    cu.fifos.foreach { fifo =>
      val pmem = mp.smmap(fifo)
      if (fifo.dequeueEnable.isConnected)
        mp = mapInPort(fifo.dequeueEnable, pmem.incReadPtr, mp)
      else
        mp = mp.setIP(fifo.dequeueEnable, pmem.incReadPtr)
    }
    mp
  }

  def mapDone(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    (cu.ctrlBox, pcu.ctrlBox) match {
      case (cb:MCB, pcb:PMCB) =>
        mp = mp.setOP(cb.readDone.out, pcb.readDoneXbar.out)
        mp = mp.setOP(cb.writeDone.out, pcb.writeDoneXbar.out)
        mp = mapInPort(cb.writeDone.in, pcb.writeDoneXbar.in, mp)
        mp = mapInPort(cb.readDone.in, pcb.readDoneXbar.in, mp)
      case (cb:ICB, pcb:PICB) =>
        mp = mapInPort(cb.done.in, pcb.doneXbar.in, mp)
        mp = mp.setOP(cb.done.out, pcb.doneXbar.out)
        mp = mp.setOP(cb.doneOut, pcb.doneDelay.out)
      case (cb:OCB, pcb:POCB) =>
        mp = mapInPort(cb.done.in, pcb.doneXbar.in, mp)
        mp = mp.setOP(cb.done.out, pcb.doneXbar.out)
        mp = mp.setOP(cb.doneOut, pcb.udsm.doneOut)
      case (cb:MCCB, pcb:PMCCB) if cb.ctrler.mctpe==TileLoad =>
        mp = mp.setOP(cb.doneOut, pcb.rdone)
      case (cb:MCCB, pcb:PMCCB) if cb.ctrler.mctpe==TileStore =>
        mp = mp.setOP(cb.doneOut, pcb.wdone)
    }
    mp
  }

  def mapAndTree(at:AT, pat:PAT, pirMap:M):M = {
    implicit var mp = pirMap
    mp = mp.setPM(at, pat)
    mp = mp.setOP(at.out, pat.out)
    at.ins.foreach { in =>
      val po = if (in.isCtrlIn) { mp.vimap(in).ic } else { mp.opmap(in.from.asCtrl) }
      val pins = pat.ins.filter { _.canConnect(po) }
      var info = s"Mapping $at to $pat in ${at.ctrler}\n"
      info += s"in=$in, from=${in.from}, po=$po \n"
      info += s"$pat'ins mapped to $po = [${pins.mkString(",")}]"
      assert(pins.size==1, info)
      val pin = pins.head
      mp = mp.setIP(in, pin)
      mp = mp.setFI(pin, po)
    }
    pat.ins.foreach { pin =>
      if (!isMapped(pin)) {
        val po = pin.fanIns.filter { _.src.isConst }.head
        mp = mp.setFI(pin, po)
      }
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
        mp = mapAndTree(cb.streamAndTree, pcb.streamAndTree, mp)
        mp = mapAndTree(cb.pipeAndTree, pcb.pipeAndTree, mp)
      case (cb:OCB, pcb:POCB) =>
        mp = mapAndTree(cb.siblingAndTree, pcb.siblingAndTree, mp)
        mp = mapAndTree(cb.childrenAndTree, pcb.childrenAndTree, mp)
      case (cb:MCB, pcb:PMCB) =>
        mp = mapAndTree(cb.readFifoAndTree, pcb.readFifoAndTree, mp)
        mp = mapAndTree(cb.writeFifoAndTree, pcb.writeFifoAndTree, mp)
      case (cb:MCCB, pcb:PMCCB) =>
        mp = mapAndTree(cb.fifoAndTree, pcb.fifoAndTree, mp)
      case _ =>
    }
    mp
  }

  def mapEnOut(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    (cb, pcb) match {
      case (cb:MCB, pcb:PMCB) =>
        mp = mp.setOP(cb.readEn.out, pcb.readEn.out)
        mp = mp.setOP(cb.writeEn.out, pcb.writeEn.out)
      case (cb:OCB, pcb:POCB) => 
        mp = mp.setOP(cb.en.out, pcb.en.out)
        //mp = mp.setOP(cb.enOut, pcb.en.out)
      case (cb:ICB, pcb:PICB) =>
        mp = mp.setOP(cb.en.out, pcb.en.out)
        mp = mp.setOP(cb.enOut, pcb.enDelay.out)
      case (cb:MCCB, pcb:PMCCB) =>
        mp = mp.setOP(cb.en.out, pcb.en.out)
    }
    mp
  }

  def mapEnIn(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    (cb, pcb) match {
      case (cb:MCB, pcb:PMCB) =>
        mp = mp.setIP(cb.readEn.in, pcb.readEn.in)
        mp = mp.setIP(cb.writeEn.in, pcb.writeEn.in)
      case (cb:OCB, pcb:POCB) => 
        mp = mp.setIP(cb.en.in, pcb.en.in)
      case (cb:ICB, pcb:PICB) =>
        mp = mapInPort(cb.en.in, pcb.en.in, mp)
      case (cb:MCCB, pcb:PMCCB) =>
        mp = mp.setIP(cb.en.in, pcb.en.in)
      case (cb:CB, pcb:PCB) =>
        assert(cb.ctrler.isInstanceOf[MC])
        assert(pcb.prt.isInstanceOf[PMC])
    }
    mp
  }

  def mapCounters(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    cu.cchains.flatMap(_.counters).foreach { ctr =>
      val pctr = mp.ctmap(ctr)
      mp = mp.setIP(ctr.en, pctr.en)
      mp = mp.setOP(ctr.done, pctr.done)
    }
    cu.cchains.flatMap(_.counters).foreach { ctr =>
      val pctr = mp.ctmap(ctr)
      mp = mp.setFI(pctr.en, mp.opmap(ctr.en.from.asCtrl))
    }
    mp
  }

  def mapTokenOut(cu:CL, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    cb.ctrlOuts.foreach { co =>
      (co.src, pcb) match {
        case (cb:TCB, pcb:PTCB) =>
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, pcb.command )
          }
        case (_, _) =>
          mp.vomap(co).foreach { pco =>
            mp = mp.setFI(pco.ic, mp.opmap(co))
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
    }
    mp
  }

  def mapUDCIns(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    cb.udcounters.foreach { case (dep, udc) =>
      val pudc = mp.pmmap(udc)
      mp = mapInPort(udc.inc, pudc.inc, mp)
      mp = mapInPort(udc.dec, pudc.dec, mp)
    }
    mp
  }

  def mapPredUnits(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    (cu.ctrlBox, pcu.ctrlBox) match {
      case (cu:ICB, pcb:PICB) =>
        cu.accumPredUnit.foreach { apu =>
          mp = mp.setPM(apu, pcb.accumPredUnit)
          mp = mapInPort(apu.in, pcb.accumPredUnit.in, mp)
          mp = mapOutPort(apu.out, pcb.accumPredUnit.out, mp)
        }
      case (cu, pcb) =>
    }
    mp
  }

}

case class NotReachable(to:CL, topcu:PCL, fromcu:CL, frompcu:Option[PCL], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
