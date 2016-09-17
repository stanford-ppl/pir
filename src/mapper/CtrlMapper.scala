package pir.graph.mapper
import pir._
import pir.typealias._
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
    val inner = cu.asInstanceOf[ICL]
    assert(inner.tokenOuts.size <= pcb.tokenOuts.size)
    assert(inner.tokenIns.size <= pcb.tokenIns.size)
    assert(inner.udcounters.size <= pcb.udcs.size)
    assert(inner.enLUTs.size <= pcu.ctrlBox.enLUTs.size)
    assert(inner.tokDownLUTs.size <= 1) //TODO
    assert(inner.tokOutLUTs.size <= pcu.ctrlBox.tokOutLUTs.size)

    inner.tokenIns.zipWithIndex.foreach { case (tin, i) => 
      ipmap += (tin -> pcb.tokenIns(i))
    }
    //println(s"--${inner}: ${inner.outers}---")
    /* Up-Down Counter mapping */
    val pudcs = pcb.udcs.to[ListBuffer]
    //println(s"udcs:${udcs}")
    inner.udcounters.foreach { case (ctrler, udc) =>
      val pudc = pudcs.remove(0)
      ucmap += (udc -> pudc)
    }
    /* Enable LUT mapping */
    //println(s"enluts:${enluts}")
    inner.enLUTs.foreach { case (en, enLut) =>
      val ctr = en.src
      val pctr = pirMap.ctmap(ctr)
      val penLut = pcb.enLUTs(indexOf(pctr))
      assert(enLut.ins.size <= penLut.numIns)
      lumap += (enLut -> penLut)
      val ptout = pcb.tokenOuts(indexOf(pctr))
      assert(!opmap.pmap.contains(ptout))
      opmap += (enLut.out -> ptout)
    }
    /* TokenDown LUT mapping */
    //println(s"tdluts:${tdluts}")
    inner.tokDownLUTs.foreach { tdlut =>
      val ptdlut = pcb.tokDownLUT 
      assert(tdlut.ins.size <= ptdlut.numIns)
      lumap += (tdlut -> ptdlut)
    }
    /* TokenOut LUT mapping */
    val ptoluts = pcb.tokOutLUTs.to[ListBuffer]
    //println(s"${cu} ${ptoluts} ${toluts}")
    //println(s"toluts:${toluts}")
    def findPto(tolut:TOLUT):Unit = {
      ptoluts.foreach { ptolut =>
        assert(tolut.ins.size <= ptolut.numIns)
        val ptout = pcb.tokenOuts(indexOf(ptolut))
        if (!opmap.pmap.contains(ptout)) {
          lumap += (tolut -> ptolut)
          opmap += (tolut.out -> ptout)
          return
        }
      }
      throw PIRException(s"Cannot map ${tolut} in ${pcu} ptoluts:${ptoluts} ${pcb.tokenOuts.map(po => indexOf(po)).mkString(",")}")
    }
    inner.tokOutLUTs.foreach { tolut => findPto(tolut) }

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
