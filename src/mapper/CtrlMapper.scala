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

class CtrlMapper(implicit val design:Design) extends Mapper with Metadata {
  implicit lazy val spade:Spade = design.arch
  val typeStr = "CtrlMapper"
  override def debug = Config.debugCtrlMapper

  if (Config.debugCtrlMapper) {
    warn("debugCtrlMapper is on, could be slow!")
  }

  type Edge = CUSwitchMapper.Edge
  type Path = CUSwitchMapper.Path
  type FatEdge = List[Edge] 
  type FatPaths = List[(PCL, FatPath)]
  type FatPath = List[FatEdge]
  type PathMap = CUSwitchMapper.PathMap 
  
  override val exceptLimit = 200

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

  def getCins(cl:SCL, mp:M) = {
    cl.ctrlIns.map(_.from.asInstanceOf[COP]).filter{ cin =>
      !mp.vimap.contains(cin) && mp.clmap.contains(cin.ctrler.asInstanceOf[SCL])
    }.toSet.toList
  }

  def mapCtrlIOs(cl:SCL)(fp:M => M)(pmap:M)(implicit cuMapper:CUSwitchMapper):M = {
    mapCtrlIns(cl, fp)(pmap)
    //new CUCtrlDotPrinter()(design).print(mp)
  }

  def getRoute(cl:SCL, co:COP, map:M)(implicit cuMapper:CUSwitchMapper):PathMap = {
    val fromcl = co.ctrler.asInstanceOf[SCL]
    val pfromcl = map.clmap(fromcl)
    def validCons(toCU:PCL, fatpath: FatPath):Option[FatPath] = {
      var valid = true
      //TODO
      // If co haven't been mapped, make sure pco is not used. If it's already placed, make sure
      // current fatpath starts from that pco 
      //val pco = fatpath.head._1
      //valid &&= map.vomap.get(co).fold(!map.vomap.pmap.contains(pco)) {
        //_ == pco
      //}
      // If cl has been mapped, valid fatpath reaches current pcl
      valid &&= map.clmap.get(cl).fold(cuMapper.resMap(cl).contains(toCU) && !map.clmap.pmap.contains(toCU)) {
        _ == toCU
      }
      // fatpath is with required hops
      valid &&= (fatpath.size >= minHop)
      valid &&= (fatpath.size < maxHop)

      /* Constrain on routing for hardwried connection in memory controller */
      //fromcl match {
        //case mc:MC =>
          //if (co==mc.commandFIFO.enqueueEnable.from) 
            //valid &&= (indexOf(vin) == spade.memCtrlCommandFIFOEnqBusIdx)
          //else if (mc.mctpe==TileStore && co==mc.dataFIFO.get.enqueueEnable.from) 
            //valid &&= (indexOf(vin) == spade.memCtrlDataFIFOEnqBusIdx)
          //else {
            //valid &&= (indexOf(vin) != spade.memCtrlCommandFIFOEnqBusIdx)
            //if (mc.mctpe==TileStore) {
              //valid &&= (indexOf(vin) != spade.memCtrlDataFIFOEnqBusIdx)
            //}
          //}
        //case _ =>
      //}
      //cl match {
        //case mc:MC =>
          //val cout = fatpath.head
          //if (co == mc.commandFIFO.notFull) 
            //valid &&= (indexOf(pco) == spade.memCtrlCommandFIFONotFullBusIdx)
          //else if (mc.mctpe==TileStore && co == mc.dataFIFO.get.notFull) 
            //valid &&= (indexOf(pco) == spade.memCtrlDataFIFONotFullBusIdx)
          //else {
            //valid &&= (indexOf(pco) != spade.memCtrlCommandFIFONotFullBusIdx)
            //if (mc.mctpe==TileStore) {
              //valid &&= (indexOf(pco) != spade.memCtrlDataFIFONotFullBusIdx)
            //}
          //}
        //case _ =>
      //}
      if (valid) {
        var fatedge::rest = fatpath
        fatedge = fatedge.filter { case (pco, pci) =>
          map.vomap.get(co).fold(!map.vomap.pmap.contains(pco)) { _ == pco }
        }
        if (fatedge.size==0) None
        else Some(fatedge::rest)
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
      throw NotReachable(fromcl, pfromcl, cl, map.clmap.get(cl))
    }
    filterUsedRoutes(routes, map)
  }

  def mapCtrlIns(cl:SCL, fp:M => M)(map:M)(implicit cuMapper:CUSwitchMapper):M = {
    type N = COP
    type R = (PCL, Path)
    val remainCtrlIns = getCins(cl, map)
    if (remainCtrlIns.size==0) return fp(map)
    val ci = remainCtrlIns.head
    def cons(n:N, r:R, m:M, es:List[MappingException]):M = {
      val (reachedCU, path) = r
      val pcin = path.last._2
      val pcout = path.head._1
      var mp = m.setVI(n, pcin).setVO(n, pcout).setRT(n, path.size)
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
    //fromCU match {
      //case mc:MC =>
        //new CUCtrlDotPrinter()(design).print(map)
        //println(s"$mc $ci")
      //case _ =>
    //}
    val info = s"Mapping $ci(${ci.to.filter{_.asInstanceOf[CIP].ctrler==cl}.mkString(",")}) in $cl from $fromCU" 
    log(info
    // Debug
    //, ((m:M) => ()),
      //{
        //case e@FailToMapNode(_,_,_,mp) =>
          //new CUCtrlDotPrinter()(design).print(mp.asInstanceOf[M])
          //println(info)
        //case e:Throwable =>
          //println(e)
      //}
    // Debug --
    ) {
      recResWithExcept[R,N,M](ci, List(cons _), resFilter _, mapCtrlIns(cl, fp) _, map)
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

  def advance(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], advanceCons:(PSB, FatPath) => Boolean):FatPaths = {
    def vouts(pne:PNE) = {
      pne match {
        case cu:PCL => cu.couts
        case sb:PSB => sb.vouts
      }
    }
    //CUSwitchMapper.advance(vouts _)(start, validCons, advanceCons)
    CtrlMapper.advance(vouts _)(start, validCons, advanceCons)
    //advanceBFS(vouts _)(start, validCons, advanceCons)
  }
  def filterUsedRoutes(routes:FatPaths, map:PIRMap)(implicit design:Design):PathMap
    = CtrlMapper.filterUsedRoutes(routes, map)

}
object CtrlMapper {
  type Edge = CUSwitchMapper.Edge
  type Path = CUSwitchMapper.Path
  type FatEdge = List[Edge] 
  type FatPaths = List[(PCL, FatPath)]
  type FatPath = List[FatEdge]
  type PathMap = CUSwitchMapper.PathMap 

  def advance(vouts:PNE => List[POB])(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
      advanceCons:(PSB, FatPath) => Boolean)(implicit design:Design):FatPaths
    = advanceBFS(vouts)(start, validCons, advanceCons)

  def advanceBFS(vouts:PNE => List[POB])(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
      advanceCons:(PSB, FatPath) => Boolean)(implicit design:Design):FatPaths = {
    val result = ListBuffer[(PCL, FatPath)]()
    val fatpaths = Queue[FatPath]()
    fatpaths += Nil
    while (fatpaths.size!=0) {
      val fatpath =  fatpaths.dequeue
      //fatpath.reduce { case ((i1, o1), (i2, o2)) => assert((i1==i2) && (o1==o2)) }
      val pne:PNE = fatpath.lastOption.fold[PNE](start) { _.head._2.src }
      val visited = fatpath.map{_.head}.map{ case (f,t) => f.src }
      if (!visited.contains(pne)) {
        val vos = vouts(pne).sortWith{ case (vo1, vo2) => vo1.src.isInstanceOf[PCU] || !vo2.src.isInstanceOf[PCU] }
        val edges = vos.flatMap { vout => vout.fanOuts.map { vin => (vout, vin) } }
        val bundle = edges.groupBy { case (vo, vi) => (vo.src, vi.src) }
        bundle.foreach { case ((fpne, tpne), fatEdge) =>
          val newPath = fatpath :+ fatEdge 
          tpne match {
            case cl:PCU => 
              validCons(cl, newPath).foreach { newPath => result += (cl -> newPath) }
            case cl:PTop =>
              validCons(cl, newPath).foreach { newPath => result += (cl -> newPath) }
            case sb:PSB if advanceCons(sb, newPath) => fatpaths += newPath
            case _ =>
          }
        }
      }
    }
    result.toList
  }

  def filterUsedRoutes(routes:FatPaths, map:PIRMap)(implicit design:Design):PathMap = {
    val availableRoutes = routes.flatMap { case (reached, fatpath) =>
      val filteredFatpath = fatpath.map { fatedge => // Find fatpath that has empty fatEdge after filter
        fatedge.filterNot { case (vout, vin) => // available edges
          val vinTaken = map.vimap.pmap.contains(vin)
          if (vinTaken) assert(vin.src.isInstanceOf[PCL])
          val voutTaken = map.vomap.pmap.contains(vout)
          val edgeTaken = map.fbmap.contains(vin)
          val switchTaken = {
            if (vout.src.isInstanceOf[PSB]) {
              val to = vout.voport // Check switch box
              map.fpmap.contains(to) // Conservative here
            } else false
          }
          vinTaken || voutTaken || edgeTaken || switchTaken
        }
      }
      if (filteredFatpath.exists{_.size==0}) None
      else Some((reached, filteredFatpath))
    }
    availableRoutes.map { case (reachedCU, fatpath) =>
      (reachedCU, fatpath.map{ fatedge => fatedge.head }) // For any fatpath, pick the first avalable edge
    }
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
