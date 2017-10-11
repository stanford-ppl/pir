package pir.mapper

import pir._
import pir.util.typealias._

import spade._
import spade.util._

import pirc.enums._
import pirc.exceptions._

import scala.language.existentials

class CtrlMapper(implicit val design:PIR) extends Mapper with LocalRouter {
  import pirmeta._

  val typeStr = "CtrlMapper"
  override def debug = PIRConfig.debugCtrlMapper

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
    val pcu = pirMap.pmmap(cu)
    mp = mapCtrlBox(cu, pcu, mp)
    mp = mapDelays(cu, pcu, mp)
    mp = mapCounters(cu, pcu, mp)
    mp = mapPredUnits(cu, pcu, mp)
    mp = mapUDCs(cu, pcu, mp)
    mp = mapMemoryControl(cu, pcu, mp)
    mp = mapAndTrees(cu, pcu, mp)
    mp = mapEnAnd(cu, pcu, mp)
    mp = mapTokenOut(cu, pcu, mp)
    mp
  }

  def mapCtrlBox(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    mp = mp.setPM(cb, pcb)
    (cb, pcb) match {
      case (cb:MCCB, pcb:PMCCB) =>
        mp = mapOutPort(cb.running, pcb.running, mp)
      case _ =>
    }
    mp
  }

  def mapCtrl(cu:Top, pirMap:M):M = {
    var mp = pirMap
    val pcu = pirMap.pmmap(cu)
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
        cu match {
          case cu:Seq => mp = mapFanIn[PUDSM](pin, mp)
          case cu => mp = mapFanIn[PConst](pin, mp)
        }
      case _ =>
    }
    return mp
  }

  def mapMemoryControl(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val pcb = pcu.ctrlBox
    cu.mems.foreach { mem =>
      val pmem = mp.pmmap(mem)
      mp = mapInPort(mem.enqueueEnable, pmem.enqueueEnable, mp)
      mp = mapInPort(mem.dequeueEnable, pmem.dequeueEnable, mp)
      mp = mapOutPort(mem.notFull, pmem.notFull, mp)
      mp = mapOutPort(mem.notEmpty, pmem.notEmpty, mp)
      (mem, pmem) match {
        case (mem:FIFO, pmem:PLMem) =>
          mp = mapInPort(mem.predicate, pmem.predicate, mp)
        case (mem, pmem) =>
      }
      pmem match {
        case pmem:PLMem =>
          mp = mapFanIn[PConst](pmem.predicate, mp)
        case pmem =>
      }
    }
    mp
  }

  def mapAndTree(at:AT, pat:PAT, pirMap:M):M = {
    implicit var mp = pirMap
    mp = mp.setPM(at, pat)
    mp = mapOutPort(at.out, pat.out, mp)
    at.ins.foreach { in =>
      val po = if (in.isGlobal) { 
        mp.vimap(in).ic
      } else { 
        mp.opmap(in.from).head
      }
      val pins = pat.ins.filter { _.canConnect(po) }
      var info = s"Mapping $at to $pat in ${at.ctrler}\n"
      info += s"in=$in, from=${in.from}, po=$po \n"
      info += s"$pat'ins mapped to $po = [${pins.mkString(",")}]"
      assert(pins.size==1, info)
      val pin = pins.head
      mp = mp.setIP(in, pin)
      mp = mp.setFI(pin, po)
    }
    pat.ins.foreach { 
      case pin if !isMapped(pin)=> mp = mapFanIn[PConst](pin, mp)
      case pin =>
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
      case (cb:OCB, pcb:POCB) =>
        mp = mapAndTree(cb.siblingAndTree, pcb.siblingAndTree, mp)
        mp = mapAndTree(cb.childrenAndTree, pcb.childrenAndTree, mp)
      case (cb:MCB, pcb:PMCB) =>
        mp = mapAndTree(cb.readFifoAndTree, pcb.readFifoAndTree, mp)
        mp = mapAndTree(cb.writeFifoAndTree, pcb.writeFifoAndTree, mp)
        mp = mapAndTree(cb.tokenInAndTree, pcb.tokenInAndTree, mp)
      case (cb:MCCB, pcb:PMCCB) =>
        mp = mapAndTree(cb.fifoAndTree, pcb.fifoAndTree, mp)
      case _ =>
    }
    mp
  }

  def mapDelay(delay:D, pdelay:PD, pirMap:M):M = {
    var mp = pirMap
    mp = mp.setPM(delay, pdelay)
    mp = mapInPort(delay.in, pdelay.in, mp)
    mp = mapOutPort(delay.out, pdelay.out, mp)
    mp
  }

  def mapDelays(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    (cb, pcb) match {
      case (cb:MCB, pcb:PMCB) =>
        mp = mapDelay(cb.readEn, pcb.readEn, mp)
        mp = mapDelay(cb.readEnDelay, pcb.readEnDelay, mp)
        mp = mapDelay(cb.writeEn, pcb.writeEn, mp)
        mp = mapDelay(cb.writeEnDelay, pcb.writeEnDelay, mp)
        mp = mapDelay(cb.readDone, pcb.readDone, mp)
        mp = mapDelay(cb.readDoneDelay, pcb.readDoneDelay, mp)
        mp = mapDelay(cb.writeDone, pcb.writeDone, mp)
        mp = mapDelay(cb.writeDoneDelay, pcb.writeDoneDelay, mp)
      case (cb:OCB, pcb:POCB) => 
        mp = mapDelay(cb.en, pcb.en, mp)
        mp = mapDelay(cb.done, pcb.done, mp)
        mp = mapOutPort(cb.doneOut, pcb.udsm.doneOut, mp) 
      case (cb:ICB, pcb:PICB) =>
        mp = mapDelay(cb.en, pcb.en, mp)
        mp = mapDelay(cb.enDelay, pcb.enDelay, mp)
        mp = mapDelay(cb.done, pcb.done, mp)
        mp = mapDelay(cb.doneDelay, pcb.doneDelay, mp)
      case (cb:MCCB, pcb:PMCCB) if cb.ctrler.mctpe==TileLoad =>
        mp = mapDelay(cb.en, pcb.en, mp)
        mp = mapOutPort(cb.doneOut, pcb.rdone, mp)
      case (cb:MCCB, pcb:PMCCB) if cb.ctrler.mctpe==TileStore =>
        mp = mapDelay(cb.en, pcb.en, mp)
        mp = mapOutPort(cb.doneOut, pcb.wdone, mp)
    }
    mp
  }

  def mapCounters(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    cu.cchains.flatMap(_.counters).foreach { ctr =>
      val pctr = mp.pmmap(ctr)
      mp = mapInPort(ctr.en, pctr.en, mp)
      mp = mapOutPort(ctr.done, pctr.done, mp)
    }
    mp
  }

  def mapTokenOut(cu:CL, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    //TODO
    //cb.ctrlOuts.foreach { co =>
      //(co.src, pcb) match {
        //case (cb:TCB, pcb:PTCB) =>
          //mp.vomap(co).foreach { pco =>
            //mp = mp.setFI(pco.ic, pcb.command )
          //}
        //case (_, _) =>
          //mp.vomap(co).foreach { pco =>
            //mp = mp.setFI(pco.ic, mp.opmap(co).head)
          //}
      //}
    //}
    mp
  }

  def mapUDCs(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    cb.udcounters.foreach { case (dep, udc) =>
      val pudc = pcb.udcs.filterNot { pudc => mp.pmmap.contains(pudc) }.head
      mp = mp.setPM(udc, pudc)
      mp = mapInPort(udc.inc, pudc.inc, mp)
      mp = mapInPort(udc.dec, pudc.dec, mp)
      mp = mapOutPort(udc.out, pudc.out, mp)
    }
    mp
  }

  def mapPredUnits(cu:CU, pcu:PCL, pirMap:M):M = {
    var mp = pirMap
    (cu.ctrlBox, pcu.ctrlBox) match {
      case (cu:ICB, pcb:PICB) =>
        cu.accumPredUnit.foreach { pu =>
          mp = mp.setPM(pu, pcb.accumPredUnit)
          mp = mapInPort(pu.in, pcb.accumPredUnit.in, mp)
          mp = mapOutPort(pu.out, pcb.accumPredUnit.out, mp)
        }
        cu.fifoPredUnit.foreach { pu =>
          mp = mp.setPM(pu, pcb.fifoPredUnit)
          mp = mapInPort(pu.in, pcb.fifoPredUnit.in, mp)
          mp = mapOutPort(pu.out, pcb.fifoPredUnit.out, mp)
        }
      case (cu, pcb) =>
    }
    mp
  }

}

case class NotReachable(to:CL, topcu:PCL, fromcu:CL, frompcu:Option[PCL], mp:PIRMap)(implicit mapper:Mapper, design:PIR) extends MappingException(mp) {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
