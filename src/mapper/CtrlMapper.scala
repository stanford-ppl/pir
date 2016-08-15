package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{CtrlBox => CB, Counter => Ctr, InPort => IP, TokenOutLUT => TOLUT, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{CtrlBox => PCB, Counter => PCtr, SRAM => PSRAM, InPort => PIP, OutPort => POP, Const => PConst}
import pir.graph.traversal.PIRMapping

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.{ Map => MMap }

object CtrlMapper extends Mapper {
  type R = PCB
  type N = CB
  
  def next(cu:CU, pirMap:M) = {
    val cmap = RegAlloc.map(cu, pirMap)
    StageMapper.map(cu, cmap)
  }
  def map(cu:CU, pirMap:M):M = {
    var ucmap = pirMap.ucmap
    var lumap = pirMap.lumap
    var opmap = pirMap.opmap
    var ipmap = pirMap.ipmap
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    val pcb = pcu.ctrlBox
    val cb = cu.ctrlBox
    val inner = cu.asInstanceOf[InnerComputeUnit]
    val locals = inner :: inner.outers
    val ptouts = pcb.tokenOuts
    val touts = locals.flatMap(_.ctrlBox.getTokenOuts)
    if (touts.size > ptouts.size) throw OutOfTokenOut(pcu, ptouts.size, touts.size)
    val ptins = pcb.tokenIns
    val tins = locals.flatMap(_.ctrlBox.getTokenIns)
    tins.zipWithIndex.foreach { case (tin, i) => 
      ipmap += (tin -> ptins(i))
    }
    if (tins.size > ptins.size) throw OutOfTokenIn(pcu, ptins.size, tins.size)
    //println(s"--${inner}: ${inner.outers}---")
    /* Up-Down Counter mapping */
    val udcs = locals.flatMap{ _.ctrlBox.udcounters }
    val pudcs = pcb.udcs.to[ListBuffer]
    if (pudcs.size < udcs.size) throw OutOfUDC(pcu, pudcs.size, udcs.size)
    //println(s"udcs:${udcs}")
    udcs.foreach { case (ctrler, udc) =>
      val pudc = pudcs.remove(0)
      ucmap += (udc -> pudc)
    }
    /* Enable LUT mapping */
    val penluts = pcu.ctrlBox.enLUTs 
    val enluts = locals.flatMap(_.ctrlBox.enLUTs)
    if (penluts.size < enluts.size) throw OutOfEnLUT(pcu, penluts.size, enluts.size)
    //println(s"enluts:${enluts}")
    enluts.foreach { case (en, enLut) =>
      val ctr = en.src
      val pctr = pirMap.ctmap(ctr)
      val penLut = pcb.enLUTs(pctr.idx)
      assert(enLut.ins.size <= penLut.numIns)
      lumap += (enLut -> penLut)
      val ptout = ptouts(pctr.idx)
      assert(!opmap.pmap.contains(ptout))
      opmap += (enLut.out -> ptout)
    }
    /* TokenDown LUT mapping */
    val tdluts = locals.flatMap(_.ctrlBox.tokDownLUTs)
    //println(s"tdluts:${tdluts}")
    if (tdluts.size>1) throw OutOfTokenDownLUT(pcu, 1, enluts.size)
    tdluts.foreach { tdlut =>
      val ptdlut = pcb.tokDownLUT 
      assert(tdlut.ins.size <= ptdlut.numIns)
      lumap += (tdlut -> ptdlut)
    }
    /* TokenOut LUT mapping */
    val ptoluts = pcb.tokOutLUTs.to[ListBuffer]
    val toluts = cb.tokOutLUTs ++ inner.outers.flatMap(_.ctrlBox.tokOutLUTs)
    //println(s"${cu} ${ptoluts} ${toluts}")
    if (ptoluts.size < toluts.size) throw OutOfTokenOutLUT(pcu, ptoluts.size, toluts.size)
    //println(s"toluts:${toluts}")
    def findPto(tolut:TOLUT):Unit = {
      ptoluts.foreach { ptolut =>
        assert(tolut.ins.size <= ptolut.numIns)
        val ptout = ptouts(ptolut.idx)
        if (!opmap.pmap.contains(ptout)) {
          lumap += (tolut -> ptolut)
          opmap += (tolut.out -> ptout)
          return
        }
      }
      throw PIRException(s"Cannot map ${tolut} in ${pcu} ptoluts:${ptoluts} ${ptouts.map(_.idx).mkString(",")}")
    }
    toluts.foreach { tolut => findPto(tolut) }

    val cmap = pirMap.set(ucmap).set(lumap).set(opmap).set(ipmap)
    val finPass:Option[M => M] = Some(next(cu, _))
    finPass.fold(cmap)(_(cmap))
  }

}
case class OutOfUDC(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough UDC in ${pcu} to map application."
}
case class OutOfEnLUT(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough EnLUT in ${pcu} to map application."
}
case class OutOfTokenIn(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough TokenIn in ${pcu} to map application."
}
case class OutOfTokenOut(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough TokenOut in ${pcu} to map application."
}
case class OutOfTokenOutLUT(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough TokenOutLUT in ${pcu} to map application."
}
case class OutOfTokenDownLUT(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough TokenDownLUT in ${pcu} to map application."
}
//case class CtrRouting(n:Ctr, p:PCtr)(implicit design:Design) extends MappingException {
//  override val mapper = CtrlBox
//  override val msg = s"Fail to map ${n} to ${p}"
//}
