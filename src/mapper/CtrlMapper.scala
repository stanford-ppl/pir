package pir.graph.mapper
import pir.{Config}
import pir.Design
import pir.misc._
import pir.typealias._
import pir.plasticine.main._
import pir.graph.traversal.{PIRMapping, CUCtrlDotPrinter}

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.{ Map => MMap }
import scala.util.{Try, Success, Failure}

class CtrlMapper(implicit val design:Design) extends Mapper with Metadata {
  implicit lazy val spade:Spade = design.arch
  val typeStr = "CtrlMapper"
  override def debug = Config.debugCtrlMapper

  if (Config.debugCtrlMapper) {
    warn("debugCtrlMapper is on, could be slow!")
  }

  type Edge = CUSwitchMapper.Edge 
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 
  
  def finPass(cu:SCL)(m:M):M = m

  val minHop = 1
  val maxHop = 5

  def map(cu:SCL, pirMap:M):M = {
    log(cu) {
      var pmap = pirMap
      pmap = cu match {
        case cu:ICL => mapCtrl(cu, pmap)
        case top:Top => pirMap
      }
      pmap
    }
  }

  def getCoutSrc(co:OP) = {
    co.src match {
      case prim:PRIM=> prim.ctrler.asInstanceOf[CU].inner
      case top:Top => top 
    }
  }

  def getCins(cl:SCL, mp:M) = {
    cl.ctrlIns.filter{ cin =>
      !mp.vimap.contains(cin) && mp.clmap.contains(getCoutSrc(cin.from))
    }
  }

  def mapCtrlIOs(cl:SCL)(fp:M => M)(pmap:M)(implicit cuMapper:CUSwitchMapper):M = {
    //mapCtrlIns(cl, fp)(pmap)
    fp(pmap)
    //Try {
      //mapCtrlIns(cl, fp)(pmap)
    //} match {
      //case Success(m) => m
      //case Failure(e) =>
        //e match {
          //case e@FailToMapNode(_, n:IP, exceps, m) =>
            //val mp = m.asInstanceOf[M]
            ////if (Config.debugCtrlMapper) {
            ////  new CUCtrlDotPrinter()(design).print(mp)
            ////}
            //throw e
            ////throw PassThroughException(this, CtrlRouting(cl, e), mp)
          //case e => throw e
        //}
    //}
  }

  def getRoute(cl:SCL, ci:IP, map:M)(implicit cuMapper:CUSwitchMapper):PathMap = {
    val co = ci.from
    val fromcl = getCoutSrc(co)
    val pfromcl = map.clmap(fromcl)
    def validCons(vin:PIB, path:Path) = {
      // If co haven't been mapped, make sure pco is not used. If it's already placed, make sure
      // current path starts from that pco //TODO why would it possible that co has already been
      // mapped
      val pco = path.head._1
      map.vomap.get(co).fold(!map.vomap.pmap.contains(pco)){ _ == pco } &&
      // If cl has been mapped, valid path reaches current pcl
      map.clmap.get(cl).fold(cuMapper.resMap(cl).contains(vin.src)) { _ == vin.src } &&
      // path is with required hops
      (path.size >= minHop) &&
      (path.size < maxHop)
    }
    def advanceCons(sb:PSB, path:Path) = {
      // path is within maximum hop to continue
      (path.size < maxHop)
    }
    val routes = advance(pfromcl, validCons _, advanceCons _)
    if (routes.size==0) {
      throw NotReachable(fromcl, pfromcl, cl, map.clmap.get(cl))
    }
    routes.filter { case r@(reached, path) =>
      !path.exists { case (vout, vin) => map.fbmap.contains(vin) }// no edge has been taken
    }
  }

  def mapCtrlIns(cl:SCL, fp:M => M)(map:M)(implicit cuMapper:CUSwitchMapper):M = {
    type N = IP
    type R = (PCL, Path)
    val remainCtrlIns = getCins(cl, map)
    dprintln(s"Mapping ctrl of $cl $remainCtrlIns")
    //printCaller
    if (remainCtrlIns.size==0) return fp(map)
    val ci = remainCtrlIns.head
    def cons(n:N, r:R, m:M, es:List[MappingException]):M = {
      val (reachedCU, path) = r
      val pcin = path.last._2
      var mp = m.setVI(n, pcin)
      val pcout = path.head._1
      mp = mp.setVO(n.from, pcout)
      mp = cuMapper.bindCU(cl, reachedCU, mp, es)
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
    val allRoutes = getRoute(cl, ci, map)
    def resFilter(triedRes:List[R], es:List[MappingException]) = {
      allRoutes.diff(triedRes)
    }
    val newMap = log (s"Mapping $ci in $cl from ${getCoutSrc(ci.from)}") {
      recResWithExcept[R,N,M](ci, List(cons _), resFilter _, mapCtrlIns(cl, fp) _, map)
    }
    assert(newMap.vimap.contains(ci))
    newMap
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
        if (!lumap.pmap.contains(plut)) {
          lumap += (lut -> plut)
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

    pmap.set(ucmap).set(lumap)
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

case class BindingException(cl:SCL, pcl:PCL, except:NotReachable)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Not mapping $cl to $pcl due to $except"
}
case class NotReachable(to:SCL, topcu:PCL, fromcu:SCL, frompcu:Option[PCL])(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
case class CtrlRouting(cu:SCL, e:MappingException)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ctrl for ${cu} due to $e"
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
