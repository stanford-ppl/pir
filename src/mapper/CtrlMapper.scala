package pir.graph.mapper
import pir.{Config}
import pir.Design
import pir.typealias._
import pir.plasticine.main._
import pir.graph.traversal.PIRMapping

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.{ Map => MMap }
import scala.util.{Try, Success, Failure}

class CtrlMapper(implicit val design:Design) extends Mapper with Metadata {
  implicit lazy val spade:Spade = design.arch
  val typeStr = "CtrlMapper"

  override def debug = Config.debugCtrlMapper

  type Edge = CUSwitchMapper.Edge 
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 
  
  def finPass(cu:SCL)(m:M):M = m

  val minHop = 1
  val maxHop = 10

  def map(cu:SCL, pirMap:M):M = {
    log(cu) {
      val pmap = cu match {
        case cu:ICL => mapCtrl(cu, pirMap)
        case top:Top => pirMap
      }
      Try {
        mapCtrlIns(cu, finPass(cu) _)(cu.ctrlIns, pmap)
      } match {
        case Success(m) => m
        case Failure(e) =>
          e match {
            case me:MappingException => throw PassThroughException(this, me, pmap)
            case e => throw e
          }
      }
    }
  }

  def mapCtrlIn(cu:SCL, fp:M => M)(ci:IP, remainCtrlIns:List[IP], pmap:M):M = {
    type N = IP
    type R = (PCL, Path)
    val co = ci.from
    def resFunc(ci:N, m:M):List[R] = {
      def validCons(vin:PIB, path:Path) = {
        // If co haven't been mapped, make sure pco is not used. If it's already placed, make sure
        // current path starts from that pco
        val pco = path.head._1
        m.vomap.get(co).fold(!m.vomap.pmap.contains(pco)){ _ == pco } &&
        // If vin reaches current cu. src of ctrl bus is ctrler
        vin.src == pmap.clmap(cu) &&
        // path is with required hops
        (path.size >= minHop) &&
        (path.size < maxHop) && 
        // No edge in path has been used
        !path.exists { case (vout, vin) => pmap.fbmap.contains(vin) }
      }
      def advanceCons(sb:PSB, path:Path) = {
        // path is within maximum hop to continue
        (path.size < maxHop) &&
        // No edge in path has been used
        !path.exists { case (vout, vin) => pmap.fbmap.contains(vin) }
      }
      val fromcu = co.src match {
        case prim:PRIM=> prim.ctrler.asInstanceOf[CU].inner
        case top:Top => top 
      }
      val pfromcu = pmap.clmap(fromcu)
      advance(pfromcu, validCons _, advanceCons _)
    }
    def cons(n:N, r:R, m:M):M = {
      val (from, path) = r
      val pcin = path.last._2
      var mp = m.setVI(n, pcin)
      val pcout = path.head._1
      mp = mp.setVO(n.from, pcout)
      path.zipWithIndex.foreach { case ((vout, vin), i) => 
        mp = mp.setFB(vin, vout)
        if (vout.src.isInstanceOf[PSB]) { // Config SwitchBox
          val to = vout.voport
          val from = path(i-1)._2.viport
          mp = mp.setFP(to, from)
        }
      }
      mp
    } 
    recRes[R,N,M](List(cons _), resFunc _, mapCtrlIns(cu, fp) _) (ci, remainCtrlIns, pmap)
  }

  def mapCtrlIns(cl:SCL, fp:M => M)(remainCtrlIns:List[IP], pmap:M):M = {
    type N = IP
    type R = (PCL, Path)
    if (remainCtrlIns.size==0) return fp(pmap)
    val ci::restCins = remainCtrlIns
    val co = ci.from
    val fromcu = co.src match {
      case prim:PRIM=> prim.ctrler.asInstanceOf[CU].inner
      case top:Top => top 
    }
    val pfromcu = pmap.clmap(fromcu)
    mapCtrlIn(cl, fp)(ci, restCins, pmap)
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
        //val ptout = plut match {
        //  case td:PTDLUT => pcb.ctrlOuts(indexOf(plut))
        //  case to:PTOLUT => pcb.ctrlOuts(indexOf(plut) + pcb.tokenDownLUTs.size)
        //  case _ => throw PIRException(s"Don't know how to look up lut ${lut}")
        //}
        if (!lumap.pmap.contains(plut)) {
          lumap += (lut -> plut)
          return
        }
        //if (!vomap.pmap.contains(ptout)) {
        //  lumap += (lut -> plut)
        //  vomap += (lut.out -> ptout)
        //  return
        //}
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

    pmap.set(ucmap).set(lumap).set(vomap).set(vimap)
  }

  def advance(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap = {
    def vouts(pne:PNE) = {
      pne match {
        case cu:PCL => cu.couts
        case sb:PSB => sb.vouts
      }
    }
    CUSwitchMapper.advance(vouts _)(start, validCons, advanceCons)
  }

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
