package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{CtrlBox => CB, Counter => Ctr, InPort => IP, TokenOutLUT => TOLUT, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{CtrlBox => PCB, Counter => PCtr, SRAM => PSRAM, InPort => PIP, OutPort => POP, Const => PConst}
import pir.plasticine.main._
import pir.graph.traversal.PIRMapping

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.{ Map => MMap }

class CtrlMapper(implicit val design:Design) extends Mapper with Metadata {
  implicit lazy val spade:Spade = design.arch
  val typeStr = "CtrlMapper"

  type R = PCB
  type N = CB
  
  def finPass(cu:CU)(m:M):M = m

  def map(cu:CU, pirMap:M):M = {
    var ucmap = pirMap.ucmap
    var lumap = pirMap.lumap
    var opmap = pirMap.opmap
    var ipmap = pirMap.ipmap
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    val pcb = pcu.ctrlBox
    val cb = cu.ctrlBox
    val inner = cu.asInstanceOf[InnerController]
    val locals = inner :: inner.outers
    val ptouts = pcb.tokenOuts
    val touts = locals.flatMap(_.ctrlBox.getTokenOuts)
    if (touts.size > ptouts.size) throw OutOfTokenOut(pcu, cu, ptouts.size, touts.size)
    val ptins = pcb.tokenIns
    val tins = locals.flatMap(_.ctrlBox.getTokenIns)
    tins.zipWithIndex.foreach { case (tin, i) => 
      ipmap += (tin -> ptins(i))
    }
    if (tins.size > ptins.size) throw OutOfTokenIn(pcu, cu, ptins.size, tins.size)
    //println(s"--${inner}: ${inner.outers}---")
    /* Up-Down Counter mapping */
    val udcs = locals.flatMap{ _.ctrlBox.udcounters }
    val pudcs = pcb.udcs.to[ListBuffer]
    if (pudcs.size < udcs.size) throw OutOfUDC(pcu, cu, pudcs.size, udcs.size)
    //println(s"udcs:${udcs}")
    udcs.foreach { case (ctrler, udc) =>
      val pudc = pudcs.remove(0)
      ucmap += (udc -> pudc)
    }
    /* Enable LUT mapping */
    val penluts = pcu.ctrlBox.enLUTs 
    val enluts = locals.flatMap(_.ctrlBox.enLUTs)
    if (penluts.size < enluts.size) throw OutOfEnLUT(pcu, cu, penluts.size, enluts.size)
    //println(s"enluts:${enluts}")
    enluts.foreach { case (en, enLut) =>
      val ctr = en.src
      val pctr = pirMap.ctmap(ctr)
      val penLut = pcb.enLUTs(indexOf(pctr))
      assert(enLut.ins.size <= penLut.numIns)
      lumap += (enLut -> penLut)
      val ptout = ptouts(indexOf(pctr))
      assert(!opmap.pmap.contains(ptout))
      opmap += (enLut.out -> ptout)
    }
    /* TokenDown LUT mapping */
    val tdluts = locals.flatMap(_.ctrlBox.tokDownLUTs)
    //println(s"tdluts:${tdluts}")
    if (tdluts.size>1) throw OutOfTokenDownLUT(pcu, cu, 1, tdluts.size)
    tdluts.foreach { tdlut =>
      val ptdlut = pcb.tokDownLUT 
      assert(tdlut.ins.size <= ptdlut.numIns)
      lumap += (tdlut -> ptdlut)
    }
    /* TokenOut LUT mapping */
    val ptoluts = pcb.tokOutLUTs.to[ListBuffer]
    val toluts = cb.tokOutLUTs ++ inner.outers.flatMap(_.ctrlBox.tokOutLUTs)
    //println(s"${cu} ${ptoluts} ${toluts}")
    if (ptoluts.size < toluts.size) throw OutOfTokenOutLUT(pcu, cu, ptoluts.size, toluts.size)
    //println(s"toluts:${toluts}")
    def findPto(tolut:TOLUT):Unit = {
      ptoluts.foreach { ptolut =>
        assert(tolut.ins.size <= ptolut.numIns)
        val ptout = ptouts(indexOf(ptolut))
        if (!opmap.pmap.contains(ptout)) {
          lumap += (tolut -> ptolut)
          opmap += (tolut.out -> ptout)
          return
        }
      }
      throw PIRException(s"Cannot map ${tolut} in ${pcu} ptoluts:${ptoluts} ${ptouts.map(po => indexOf(po)).mkString(",")}")
    }
    toluts.foreach { tolut => findPto(tolut) }

    val cmap = pirMap.set(ucmap).set(lumap).set(opmap).set(ipmap)
    finPass(cu)(cmap)
  }

}

case class OutOfUDC(pcu:PCU, cu:CU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough UDC in ${pcu} to map ${cu}."
}
case class OutOfEnLUT(pcu:PCU, cu:CU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough EnLUT in ${pcu} to map ${cu}."
}
case class OutOfTokenIn(pcu:PCU, cu:CU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenIn in ${pcu} to map ${cu}."
}
case class OutOfTokenOut(pcu:PCU, cu:CU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenOut in ${pcu} to map ${cu}."
}
case class OutOfTokenOutLUT(pcu:PCU, cu:CU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenOutLUT in ${pcu} to map ${cu}."
}
case class OutOfTokenDownLUT(pcu:PCU, cu:CU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenDownLUT in ${pcu} to map ${cu}."
}
//case class CtrRouting(n:Ctr, p:PCtr)(implicit val mapper:Mapper, design:Design) extends MappingException {
//  override val msg = s"Fail to map ${n} to ${p}"
//}
