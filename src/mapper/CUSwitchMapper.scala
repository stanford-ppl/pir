package pir.graph.mapper
import pir.graph._
import pir.{Config, Design}
import pir.typealias._
import pir.codegen.{DotCodegen, Printer}
import pir.graph.traversal.{CUCtrlDotPrinter, CUDotPrinter, MapPrinter, PIRMapping}
import pir.plasticine.graph.{Node => PNode}
import pir.plasticine.main._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.util.{Failure, Success, Try}

class CUSwitchMapper(outputMapper:OutputMapper, ctrlMapper:Option[CtrlMapper])(implicit val design:Design) extends CUMapper with PlaceAndRoute {

  var debugRouting = false 
  def this (outputMapper:OutputMapper, ctrlMapper:CtrlMapper)(implicit design:Design) = {
    this(outputMapper, 
      //None
      Some(ctrlMapper)
     )
  }

  val typeStr = "CUSwitchMapper"
  override implicit val mapper:CUSwitchMapper = this

  override val exceptLimit = 200

  val minHop = 1
  val maxHop = 7

  // DEBUG
  val failPass:Throwable=>Unit = if (debugRouting) {
    {
      case e@FailToMapNode(_,n,es,mp) =>
        val node = n match {
          case (vi, cu) => (vi.asInstanceOf[VI].vector, cu)
          case n => n
        }
        println(s"Fail to map ${node} $es")
        new CUDotPrinter(true)(design).print(mp.asInstanceOf[M])
      case e:Throwable =>
        println(e)
    }
  } else {
    (e:Throwable) => ()
  }
  // DEBUG --

  val resMap:MMap[SCL, List[PCL]] = MMap.empty

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val cus = design.top.innerCUs
    val pcus = design.arch.cus
    val nodes = design.top::cus
    val reses = design.arch.top::pcus
    qualifyCheck(reses, nodes, resMap)
    val cu::restNodes = nodes 
    mapCUs(nodes)(m)
  }

  def mapCUs(remainNodes:List[SCL])(map:M) = {
    if (remainNodes.size==0) {
      finPass(map)
    } else {
      val cl::rest = remainNodes 
      mapDep(cl) {
        ctrlMapper.fold(mapCU(cl, rest) _) { _.mapCtrlIOs(cl) {
            mapCU(cl, rest) _
          } _
        }
      } (map)
    }
  }

  def mapCU(cl:SCL, restNodes:List[SCL])(map:M):M = {
    type R = PCL
    type N = SCL
    def constrain(cl:N, pcl:R, m:M, es:List[MappingException]):M = {
      val mp = cl.readers.foldLeft(bindCU(cl, pcl, m, es)) { case (pm, reader) =>
        if (pm.clmap.contains(reader)) {
          mapDep(reader)((m:M) => m)(pm) 
        } else { 
          pm
        }
      }
      ctrlMapper.fold(mp) { ctrlMapper =>
        cl.ctrlReaders.foldLeft(mp) { case (pm, reader) =>
          if (pm.clmap.contains(reader)) {
            ctrlMapper.mapCtrlIOs(reader)((m:M) => m)(pm)
          } else {
            pm
          }
        }
      }
    }
    val allPcus = if (map.clmap.contains(cl)) {
      List(map.clmap(cl))
    } else {
      resMap(cl).filter { pcl => !map.clmap.pmap.contains(pcl) }
    }
    def resFilter(triedRes:List[R], es:List[MappingException]):List[R] = {
      flattenExceptions(es).foldLeft(allPcus.diff(triedRes)) { case (remainCUs, except) =>
        except match {
          case NotReachable(target, ptarget, _, _) if (target==cl) =>
            remainCUs.filterNot(_ == ptarget)
          case _ => remainCUs
        }
      }
    }

    def next(m:M):M = {
      //if (debug) { new CUDotPrinter().print(m) }
      mapCUs(restNodes)(m)
    }
    val info = s"Mapping $cl"
    log(info, ((m:M) => ()), failPass) {
      recResWithExcept[R,N,M](cl, List(constrain _), resFilter _, next _, map)
    }
  }

  def advance(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap =
    advance((pne:PNE) => pne.vouts)(start, validCons, advanceCons)

  def getRoute(cl:SCL, pdep:(VI, PCL), m:M):PathMap = {
    val (vin, start) = pdep
    def validCons(toVin:PIB, path:Path) = {
      val to = toVin.src.asInstanceOf[PCL]
      // If cl has been mapped, valid path reaches current pcl
      m.clmap.get(cl).fold(resMap(cl).contains(to) && !m.clmap.pmap.contains(to)) { _ == to } &&
      (path.size >= minHop) && // path is with required hops
      (path.size < maxHop) && 
      (to!=start) // path doesn't end at depended CU
    }
    def advanceCons(psb:PSB, path:Path) = { 
      (path.size < maxHop) // path is within maximum hop to continue
    }
    val routes = advance(start, validCons _, advanceCons _) // start from depended cu
    //dprintln(s"from $cl to $start routes:${routes.size} ${routes.map(_._1)}")
    if (routes.size==0) {
      val scu = m.clmap.pmap(start)
      throw NotReachable(scu, start, cl, m.clmap.get(cl))
    }
    filterUsedRoutes(routes, m)
  }
  // Map inputs of the current cu. If source of the input is not mapped, postpond mapping of the
  // input until the source is mapped.
  def mapDep(cl:SCL)(fp:M => M)(map:M):M = {
    type N = (VI, PCL)
    type R = (PCL, Path)
    val pdeps = getDeps(cl.vins, map) // returns unmapped vins whose depended cu are mapped
    if (pdeps.size==0) return fp(map)
    // Function returns a list of available pathes to choose to map (vin, pdep)
    // Function check a specific path is valid for a given (vin, pdep) and exisiting mapping. If
    // valid, add path to the mapping and return the mapping
    def constrain(pdep:N, route:R, m:M, es:List[MappingException]):M = {
      val (reachedCU, path) = route // current cu
      val (vin, from) = pdep 
      var mp = m
      mp = bindCU(cl, reachedCU, mp, es)
      val pvin = path.last._2
      mp = mp.setVI(vin, pvin).setOP(vin.out, pvin.viport).setRT(vin, path.size)
      path.zipWithIndex.foreach { case ((pvout, pvin), i) => 
        mp = mp.setFB(pvin, pvout)
        if (pvout.src.isInstanceOf[PSB]) { // Config SwitchBox
          val to = pvout.voport
          val from = path(i-1)._2.viport
          mp = mp.setFP(to, from)
        }
      }
      mp
    }
    val pdep = pdeps.head
    // Get routes that goes from pcl's output in pdep to current cl 
    val allRoutes = getRoute(cl, pdep, map)
    def resFilter(triedRes:List[R], es:List[MappingException]):List[R] = {
      //dprintln(s"Filtering for mapping $pdep of $cl res:${allRoutes.diff(triedRes).size}")
      //dprintln(s"-- flattenExceptions: {${ flattenExceptions(es).mkString("\n") }}")
      flattenExceptions(es).foldLeft(allRoutes.diff(triedRes)) { case (remainRoutes, except) =>
        except match {
          case NotReachable(target, ptarget, _, _) if (cl == target) => 
            remainRoutes.filterNot { case (reachedCU, path) => reachedCU == ptarget }
          case _ => remainRoutes 
        }
      }
    }
    val (vin, pdepcu) = pdep
    val info = s"Mapping $vin in $cl from $pdepcu"
    log(info, ((m:M) => ()), failPass){
      recResWithExcept[R, N, M](pdep, List(constrain _), resFilter _, mapDep(cl)(fp) _, map)
    }
  }

  def bindCU(cl:SCL, pcl:PCL, map:M, es:List[MappingException]):M = {
    dprintln(s"Try $cl -> $pcl")
    flattenExceptions(es).collect { case e@NotReachable(to, topcu, _, _) =>
      if (to == cl && pcl == topcu) {
        throw e
      }
    }
    outputMapper.map(cl, map.setCL(cl, pcl))
  }

  /*
   * Returns unmapped vins whose depended CUs are already mapped
   * Returns (vin, pcu)
   * */
  def getDeps(vins:List[VI], map:M):List[(VI, PCL)] = {
    // Already mapped depended PCUs
    vins.flatMap { vin => 
      if (map.vimap.contains(vin)) { None
      } else {
        val writer = vin.vector.writer
        map.clmap.get(writer.ctrler).flatMap { pcl => 
          pcl match {
            case pcu:PCU => Some(vin ->pcu)
            case ptop:PTop => Some(vin -> ptop)
            case _ => None
          }
        }
      }
    }
  }

}

