package pir.graph.mapper
import pir.Design
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

  type Edge = CUSwitchMapper.Edge 
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 
  
  def finPass(cu:ICL)(m:M):M = m

  val minHop = 1
  val maxHop = 5

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      val m = mapCtrl(cu, pirMap)
      finPass(cu)(m)
    }
  }

  def ioCons(n:OP, p:POB, m:M):M = {
    m
  }

  def mapCtrlOut(cu:ICL, co:OP, pmap:M):M = {
    type R = POB
    type N = OP
    //recRes
  }

  def mapCtrIn(cu:ICL, ci:IB, pmap:M):M = {
    val pcu = pmap.clmap(cu).asInstanceOf[PCU]
    def validCons(vin:PIB, path:Path) = {
      vin.src == pmap.clmap(cu) // If vin reaches current cu. src of ctrl bus is ctrler
      (path.size >= minHop) && // path is with required hops
      (path.size < maxHop) && 
      !path.exists { case (vout, vin) => m.fbmap.contains(vin) } // No edge in path has been used
    }
    def advanceCons(sb:PSB, path:Path) = {
      (path.size < maxHop) && // path is within maximum hop to continue
      !path.exists { case (vout, vin) => m.fbmap.contains(vin) } // No edge in path has been used
    }
    val co = ci.from
    if (pmap.vomap.contains(co)) {
      val pco = pmap.vomap(co)
      advance(pco.src, validCons _, advanceCons _)
    } else {
      mapCtrlOut(co.src, co, pmap) // Call back afterwards
    }
  }

  def mapCtrlIns(cu:ICL, pmap:M):M = {
    type R = PIB
    type N = IP
    cu.ctrlIns.foldLeft(pmap) { case (pm, ci) =>
      mapCtrlIn(cu, ci, pm)
    }
  }

  def mapCtrl(inner:ICL, pirMap:M):M = {
    var pmap = pirMap
    val pcu = pmap.clmap(inner).asInstanceOf[PCU]
    val pcb = pcu.ctrlBox
    assert(inner.ctrlOuts.size <= pcb.ctrlOuts.size)
    assert(inner.ctrlIns.size <= pcb.ctrlIns.size)
    assert(inner.udcounters.size <= pcb.udcs.size)
    assert(inner.enLUTs.size <= pcu.ctrlBox.enLUTs.size)
    assert(inner.tokDownLUTs.size <= pcu.ctrlBox.tokenDownLUTs.size)
    assert(inner.tokOutLUTs.size <= pcu.ctrlBox.tokenOutLUTs.size)

    //inner.ctrlIns.zipWithIndex.foreach { case (tin, i) => 
    //  vimap += (tin -> pcb.ctrlIns(i))
    //}
    var ucmap = pmap.ucmap
    var lumap = pmap.lumap
    var vimap = pmap.vimap
    var vomap = pmap.vomap
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
      val pctr = pmap.ctmap(ctr)
      val penLut = pcb.enLUTs(indexOf(pctr))
      assert(enLut.ins.size <= penLut.numIns)
      lumap += (enLut -> penLut)
    }

    def findPto(lut:LUT, pluts:List[PLUT]):Unit = {
      pluts.foreach { plut =>
        assert(lut.ins.size <= plut.numIns)
        val ptout = plut match {
          case td:PTDLUT => pcb.ctrlOuts(indexOf(plut))
          case to:PTOLUT => pcb.ctrlOuts(indexOf(plut) + pcb.tokenDownLUTs.size)
          case _ => throw PIRException(s"Don't know how to look up lut ${lut}")
        }
        if (!vomap.pmap.contains(ptout)) {
          lumap += (lut -> plut)
          vomap += (lut.out -> ptout)
          return
        }
      }
      throw PIRException(s"Cannot map ${lut} in ${lut.ctrler} ${pcu} pluts:${pluts}}")
    }

    /* TokenDown LUT mapping */
    inner.tokDownLUTs.foreach { tdlut => 
      findPto(tdlut, pcb.tokenDownLUTs.filter(plut => !lumap.pmap.contains(plut)))
    }
    /* TokenOut LUT mapping */
    inner.tokOutLUTs.foreach { tolut => 
      findPto(tolut, pcb.tokenOutLUTs.filter(plut => !lumap.pmap.contains(plut)))
    }

    pmap = pmap.set(ucmap).set(lumap).set(vomap).set(vimap)
    mapCtrlIn(inner, pmap)
  }

  def advance(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap =
    CUSwitchMapper.advance(start, validCons, advanceCons)

}

case class OutOfUDC(pcu:PCU, cu:ICL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough UDC in ${pcu} to map ${cu}."
}
case class OutOfEnLUT(pcu:PCU, cu:ICL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough EnLUT in ${pcu} to map ${cu}."
}
case class OutOfTokenIn(pcu:PCU, cu:ICL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenIn in ${pcu} to map ${cu}."
}
case class OutOfTokenOut(pcu:PCU, cu:ICL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenOut in ${pcu} to map ${cu}."
}
case class OutOfTokenOutLUT(pcu:PCU, cu:ICL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenOutLUT in ${pcu} to map ${cu}."
}
case class OutOfTokenDownLUT(pcu:PCU, cu:ICL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TokenDownLUT in ${pcu} to map ${cu}."
}
//case class CtrRouting(n:Ctr, p:PCtr)(implicit val mapper:Mapper, design:Design) extends MappingException {
//  override val msg = s"Fail to map ${n} to ${p}"
//}
