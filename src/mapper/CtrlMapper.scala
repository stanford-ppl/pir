package pir.mapper
import pir.{Config}
import pir.Design
import pir.util.typealias._
import pir.plasticine.main._
import pir.util.enums._
import pir.pass.{PIRMapping}
import pir.codegen.{CUCtrlDotPrinter}
import pir.exceptions._

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
        case cu:MP => mapCtrl(cu, pirMap)
        //case cu:SP => mapCtrl(cu, pirMap)
        //case cu:ICL => mapCtrl(cu, pirMap)
        //case cu:SC => mapCtrl(cu, pirMap)
        //case cu:OCL => mapCtrl(cu, pirMap)
        case cu:Top => mapCtrl(cu, pirMap)
        case cu => pirMap
      }
    }
  }

  //def mapCtrl(cu:CL, pirMap:M):M = {
    //var pmap = pirMap
    //val pcu = pmap.clmap(cu).asInstanceOf[PCU]
    //val pcb = pcu.ctrlBox
    //assert(cu.couts.size <= pcu.ctrlIO.outs.size)
    //assert(cu.cins.size <= pcu.ctrlIO.ins.size)
    //assert(cu.ctrlBox.udcounters.size <= pcu.ctrlBox.udcs.size)
    ////assert(cu.enLUTs.size <= pcu.ctrlBox.enLUTs.size)
    ////assert(cu.tokDownLUTs.size <= pcu.ctrlBox.tokenDownLUTs.size)
    ////assert(cu.tokOutLUTs.size <= pcu.ctrlBox.tokenOutLUTs.size)

    //var ucmap = pmap.ucmap
    //var pmmap = pmap.pmmap
    ///* Up-Down Counter mapping */
    //cu.ctrlBox.udcounters.groupBy { case (_, udc) => 
      //// Whether an udc can placed in the first pudc or not
      //udc.out.to.map{_.src}.collect{case enlut:EnLUT => enlut}.exists{_.ins.exists{_.from.src.isInstanceOf[AT]} } 
    //}.foreach {
      //case (true, udcs) => udcs.zip(pcb.udcs.tail.filterNot{ pudc => ucmap.pmap.contains(pudc)}).foreach { case ((_, udc), pudc) => ucmap += (udc -> pudc) }
      //case (false, udcs) => udcs.zip(pcb.udcs.filterNot{ pudc => ucmap.pmap.contains(pudc)}).foreach { case ((_, udc), pudc) => ucmap += (udc -> pudc) }
    //}

    ///* Enable LUT mapping */
    //cu.ctrlBox.enLUTs.foreach { enLut =>
      //val ctr = enLut.out.to.flatMap{ _.src match {
          //case ctr:Ctr if ctr.ctrler == enLut.ctrler => Some(ctr)
          //case _ => None 
        //}
      //}.head
      //val pctr = pmap.ctmap(ctr)
      ////val penLut = pcb.enLUTs(indexOf(pctr))
      ////assert(enLut.ins.size <= penLut.numIns)
      ////pmmap += (enLut -> penLut)
    //}

    //def findPto(lut:LUT, pluts:List[PLUT]):Unit = {
      //pluts.foreach { plut =>
        //assert(lut.ins.size <= plut.numIns)
        //if (!pmmap.pmap.contains(plut)) {
          //pmmap += (lut -> plut)
          //return
        //}
      //}
      //throw PIRException(s"Cannot map ${lut} in ${lut.ctrler} ${pcu} pluts:${pluts}}")
    //}

    ///* TokenDown LUT mapping */
    ////cu.tokDownLUTs.foreach { tdlut => 
      ////findPto(tdlut, pcb.tokenDownLUTs.filter(plut => !pmmap.pmap.contains(plut)))
    ////}

    ///* TokenOut LUT mapping */
    ////cu.tokOutLUTs.foreach { tolut => 
      ////findPto(tolut, pcb.tokenOutLUTs.filter(plut => !pmmap.pmap.contains(plut)))
    ////}

    //pmap.set(ucmap).set(pmmap)
  //}

  def mapCtrl(cu:ICL, pirMap:M):M = {
    var mp = pirMap
    mapCtrl(cu, pirMap)
    mp
  }

  //def mapDone(cu:CU, pirMap:M):M = {
    //var mp = pirMap
    //val pcu = pirMap.clmap(cu)
    //val pcb = pcu.ctrlBox
    ////val pOCtr = pirMap.ctmap(cu.localCChain.outer)
    ////mp = mp.setFI(pcb.doneXbar.in, pOCtr.done)
    //mp
  //} 

  def mapCtrl(cu:Top, pirMap:M):M = {
    var mp = pirMap
    val pcu = pirMap.clmap(cu)
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    mp = mapInPort(cb.status, pcb.status, mp)
    mp = mapOutPort(cb.command, pcb.command, mp)
    mp
  }

  def mapCtrl(cu:MP, pirMap:M):M = {
    var mp = pirMap
    val pcu = pirMap.clmap(cu)
    val cb = cu.ctrlBox
    val pcb = pcu.ctrlBox
    mp = mapDone(cu, pcu, mp)
    mp = mapSwapWrite(cu, pcu, mp)
    mp = mapSwapRead(cu, pcu.ctrlBox, mp)
    mp
  }

  def mapSwapWrite(cu:CU, pcu:PCU, pirMap:M):M = {
    var mp = pirMap
    cu.mbuffers.foreach { mbuf =>
      val pmbuf = mp.smmap(mbuf)
      val psw = pmbuf.swapWrite
      val po = mp.opmap(mbuf.swapWrite.from)
      mp = mp.setFI(psw, po)
    }
    //(cb.swapReads ++ cb.swapWrites).map { case (mbuf, (in, out)) =>
    //}
    mp
  }

  def mapSwapRead(cu:MP, pcb:PMCB, pirMap:M):M = {
    var mp = pirMap
    cu.smems.foreach { 
      case smem if forWrite(smem) =>
        val psmem = mp.smmap(smem)
        mp.setFI(psmem.swapRead, pcb.writeDoneXbar.out)
      case smem if forRead(smem) =>
        val psmem = mp.smmap(smem)
        mp.setFI(psmem.swapRead, pcb.readDoneXbar.out)
    }
    mp
  }

  def mapDone(cu:CU, pcu:PCU, pirMap:M):M = {
    var mp = pirMap
    (cu, pcu) match {
      case (cu:MP, pcu:PMCU) =>
        val writeCtr = mp.ctmap(cu.writeCChain.outer)
        val readCtr = mp.ctmap(cu.readCChain.outer)
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

  def mapEn(cb:CB, pcb:PCB, pirMap:M):M = {
    var mp = pirMap
    mp
  }

  def mapTokenOut(cu:CU, pcu:PCU, pirMap:M):M = {
    var mp = pirMap
    cu.ctrlBox.ctrlOuts.foreach { co =>
      mp.vomap(co).foreach { pco =>
        mp = mp.setFI(pco.ic, mp.opmap(co))
      }
    }
    mp
  }

}

case class NotReachable(to:CL, topcu:PCL, fromcu:CL, frompcu:Option[PCL], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
