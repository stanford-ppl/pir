package pir.graph.mapper
import pir.{Config}
import pir.Design
import pir.misc._
import pir.typealias._
import pir.plasticine.main._
import pir.graph.enums._
import pir.graph.traversal.{PIRMapping, CUCtrlDotPrinter}

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.{ Map => MMap }
import scala.util.{Try, Success, Failure}

class CtrlMapper(implicit val design:Design) extends Mapper with FatPlaceAndRoute with Metadata {
  implicit def spade:Spade = design.arch
  val typeStr = "CtrlMapper"
  override def debug = Config.debugCtrlMapper

  var debugRouting = false 
  val minHop = 1
  val maxHop = 7

  override val exceptLimit = 200
  if (Config.debugCtrlMapper) {
    warn("debugCtrlMapper is on, could be slow!")
  }

  // DEBUG
  val failPass:Throwable=>Unit = if (debugRouting) {
    {
      case e@FailToMapNode(_,n,es,mp) =>
        println(s"${es.mkString("\n")}")
        new CUCtrlDotPrinter(true)(design).print(mp.asInstanceOf[M])
      case e:Throwable =>
        println(e)
    }
  } else {
    (e:Throwable) => ()
  }
  // DEBUG --

  def finPass(cu:CL)(m:M):M = m

  def map(cu:CL, pirMap:M):M = {
    log(cu) {
      var pmap = pirMap
      pmap = cu match {
        case cu:ICL => mapCtrl(cu, pmap)
        case top:Top => pirMap
      }
      pmap
    }
  }

  def getCins(cl:CL, mp:M):List[CIP] = {
    val sameSrcMap = cl.ctrlIns.groupBy{ ci => ci.from.asInstanceOf[COP] }
    sameSrcMap.flatMap { case (co, cins) =>
      val mapped = cins.exists{ ci => mp.vimap.contains(ci) }
      if (mp.clmap.contains(co.ctrler) && !mapped) cins else Nil
    }.toList
  }

  def mapCtrlIOs(cl:CL)(fp:M => M)(pmap:M)(implicit cuMapper:CUSwitchMapper):M = {
    mapCtrlIns(cl, fp)(pmap)
    //new CUCtrlDotPrinter()(design).print(mp)
  }

  def getRoute(cl:CL, ci:CIP, map:M)(implicit cuMapper:CUSwitchMapper):PathMap = {
    val co = ci.from.asInstanceOf[COP]
    val fromcl = co.ctrler.asInstanceOf[CL]
    val pfromcl = map.clmap(fromcl)
    def validCons(toCU:PCL, fatpath: FatPath):Option[FatPath] = {
      var valid = true
      // If cl has been mapped, valid fatpath reaches current pcl
      valid &&= map.clmap.get(cl).fold(cuMapper.resMap(cl).contains(toCU) && !map.clmap.pmap.contains(toCU)) {
        _ == toCU
      }
      // fatpath is with required hops
      valid &&= (fatpath.size >= minHop)
      valid &&= (fatpath.size < maxHop)

      /* Constrain on routing for hardwried connection in memory controller */
      if (valid) {
        var fp = fatpath
        /* Constrian on MC's inputs */
        cl match {
          case mc:MC =>
            var (rest, last) = fp.splitAt(fp.size-1)
            var fatedge = last.head
            fatedge = fatedge.filter { edge =>
              val (pco, pci) = edge
              if (ci==mc.ofsFIFO.get.enqueueEnable) 
                (indexOf(pci) == spade.memCtrlCommandFIFOEnqBusIdx)
              else if (mc.mctpe==TileStore && ci==mc.dataFIFO.get.enqueueEnable) 
                (indexOf(pci) == spade.memCtrlDataFIFOEnqBusIdx)
              else {
                var vld = (indexOf(pci) != spade.memCtrlCommandFIFOEnqBusIdx)
                if (mc.mctpe==TileStore) {
                  vld &&= (indexOf(pci) != spade.memCtrlDataFIFOEnqBusIdx)
                }
                vld
              }
            }
            fp = rest :+ fatedge
          case _ =>
        }
        /* Constrian on MC's output */
        fromcl match {
          case mc:MC =>
            var fatedge::rest = fp
            fatedge = fatedge.filter { edge =>
              val (pco, pci) = edge
              if (co == mc.ofsFIFO.get.notFull) 
                (indexOf(pco) == spade.memCtrlCommandFIFONotFullBusIdx)
              else if (mc.mctpe==TileStore && co == mc.dataFIFO.get.notFull) 
                (indexOf(pco) == spade.memCtrlDataFIFONotFullBusIdx)
              else if (co==mc.dataValid)
                (indexOf(pco) == spade.memCtrlDataValidBusIdx)
              else {
                var vld = (indexOf(pco) != spade.memCtrlCommandFIFONotFullBusIdx)
                if (mc.mctpe==TileStore) {
                  vld &&= (indexOf(pco) != spade.memCtrlDataFIFONotFullBusIdx)
                }
                vld
              }
            }
            fp = fatedge::rest 
          case _ =>
        }

        // Make sure picked pco is not already used
        var fatedge::rest = fp 
        fatedge = fatedge.filter { case (pco, pci) => !map.vomap.pmap.contains(pco) }
        // If co is already placed, check if current fatpath can start from that pco, if not pick
        // any unused pco
        val optfatedge = fatedge.filter { case (pco, pci) =>
          map.vomap.get(co).fold(!map.vomap.pmap.contains(pco)) { _ == pco }
        }
        if (optfatedge.size!=0) fatedge = optfatedge
        fp = fatedge::rest

        if (isFatPathValid(fp)) Some(fp) else None
      } else {
        None
      }
    }
    def advanceCons(sb: PSB, fatpath: FatPath):Boolean = {
      var valid = true
      // If co is mapped, make sure start from that pco
      //if (path.size == 1) map.vomap.get(co).fold(true) { pco => path.head._1 == pco } else true
      // path is within maximum hop to continue
      valid &&= (fatpath.size < maxHop)
      valid
    }
    val routes = advance(pfromcl, validCons _, advanceCons _)
    if (routes.size == 0) {
      throw NotReachable(fromcl, pfromcl.asInstanceOf[PCL], cl, map.clmap.get(cl).asInstanceOf[Option[PCL]])
    }
    filterUsedRoutes(routes, map)
  }

  def mapCtrlIns(cl:CL, fp:M => M)(map:M)(implicit cuMapper:CUSwitchMapper):M = {
    type N = CIP
    type R = (PCL, Path)
    val remainCtrlIns = getCins(cl, map)
    if (remainCtrlIns.size==0) return fp(map)
    val ci = remainCtrlIns.head
    def cons(n:N, r:R, m:M, es:List[MappingException]):M = {
      val (reachedCU, path) = r
      val pcin = path.last._2
      val pcout = path.head._1
      // Map all cin with the same source
      val sameSrcMap = cl.ctrlIns.groupBy{ ci => ci.from }
      var mp = sameSrcMap(n.from).foldLeft(m) { case (pm, n) =>
        pm.setVI(n, pcin).setVO(n.from, pcout).setRT(n, path.size)
      }
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
      val remainRoutes = allRoutes.diff(triedRes)
      flattenExceptions(es).foldLeft(remainRoutes) { case (remainRoutes, except) =>
        except match {
          case NotReachable(target, ptarget, _, _) if (cl == target) => 
            remainRoutes.filterNot { case (reachedCU, path) => reachedCU == ptarget }
          case _ => remainRoutes 
        }
      }
    }
    val fromCU = ci.ctrler
    val info = s"Mapping $ci in $cl from ${ci.from} in $fromCU" 
    log(info, ((m:M) => ()), failPass) {
      recResWithExcept[R,N,M](ci, cons _, resFilter _, mapCtrlIns(cl, fp) _, map)
    }
  }

  def mapCtrl(inner:ICL, pirMap:M):M = {
    var pmap = pirMap
    val pcu = pmap.clmap(inner).asInstanceOf[PCU]
    val pcb = pcu.ctrlBox
    assert(inner.ctrlOuts.size <= pcu.ctrlIO.outs.size)
    assert(inner.ctrlIns.size <= pcu.ctrlIO.ins.size)
    assert(inner.udcounters.size <= pcu.ctrlBox.udcs.size)
    //assert(inner.enLUTs.size <= pcu.ctrlBox.enLUTs.size)
    //assert(inner.tokDownLUTs.size <= pcu.ctrlBox.tokenDownLUTs.size)
    //assert(inner.tokOutLUTs.size <= pcu.ctrlBox.tokenOutLUTs.size)

    var ucmap = pmap.ucmap
    var lumap = pmap.lumap
    /* Up-Down Counter mapping */
    inner.udcounters.groupBy { case (_, udc) => 
      // Whether an udc can placed in the first pudc or not
      udc.out.to.map{_.src}.collect{case enlut:EnLUT => enlut}.exists{_.ins.exists{_.from.src.isInstanceOf[AT]} } 
    }.foreach {
      case (true, udcs) => udcs.zip(pcb.udcs.tail.filterNot{ pudc => ucmap.pmap.contains(pudc)}).foreach { case ((_, udc), pudc) => ucmap += (udc -> pudc) }
      case (false, udcs) => udcs.zip(pcb.udcs.filterNot{ pudc => ucmap.pmap.contains(pudc)}).foreach { case ((_, udc), pudc) => ucmap += (udc -> pudc) }
    }

    /* Enable LUT mapping */
    inner.enLUTs.foreach { enLut =>
      val ctr = enLut.out.to.flatMap{ _.src match {
          case ctr:Ctr if ctr.ctrler == enLut.ctrler => Some(ctr)
          case _ => None 
        }
      }.head
      val pctr = pmap.ctmap(ctr)
      //val penLut = pcb.enLUTs(indexOf(pctr))
      //assert(enLut.ins.size <= penLut.numIns)
      //lumap += (enLut -> penLut)
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
    //inner.tokDownLUTs.foreach { tdlut => 
      //findPto(tdlut, pcb.tokenDownLUTs.filter(plut => !lumap.pmap.contains(plut)))
    //}

    /* TokenOut LUT mapping */
    //inner.tokOutLUTs.foreach { tolut => 
      //findPto(tolut, pcb.tokenOutLUTs.filter(plut => !lumap.pmap.contains(plut)))
    //}

    pmap.set(ucmap).set(lumap)
  }

  def advance(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], advanceCons:(PSB, FatPath) => Boolean):FatPaths = {
    //CUSwitchMapper.advance(vouts _)(start, validCons, advanceCons)
    advance((pne:PNE) => pne.ctrlIO.outs)(start, validCons, advanceCons)
    //advanceBFS(vouts _)(start, validCons, advanceCons)
  }
}

case class BindingException(cl:CL, pcl:PCL, except:NotReachable)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Not mapping $cl to $pcl due to $except"
}
case class NotReachable(to:CL, topcu:PCL, fromcu:CL, frompcu:Option[PCL])(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
case class CtrlRouting(cu:CL, e:MappingException)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ctrl for ${cu} due to $e"
}
//case class CtrRouting(n:Ctr, p:PCtr)(implicit val mapper:Mapper, design:Design) extends MappingException {
//  override val msg = s"Fail to map ${n} to ${p}"
//}
