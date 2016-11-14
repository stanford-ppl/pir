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

class CUSwitchMapper(outputMapper:OutputMapper, ctrlMapper:Option[CtrlMapper])(implicit val design:Design) extends CUMapper {

  def this (outputMapper:OutputMapper, ctrlMapper:CtrlMapper)(implicit design:Design) = {
    this(outputMapper, Some(ctrlMapper))
  }

  type Edge = CUSwitchMapper.Edge 
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 

  val typeStr = "CUSwitchMapper"
  override implicit val mapper:CUSwitchMapper = this

  override val exceptLimit = 200

  val resMap:MMap[SCL, List[PCL]] = MMap.empty

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val cus = design.top.innerCUs
    val pcus = design.arch.cus
    val nodes = design.top::cus
    val reses = design.arch.top::pcus
    CUMapper.qualifyCheck(reses, nodes, resMap)
    val cu::restNodes = nodes 
    mapCUs(nodes)(m)
  }

  def mapCUs(remainNodes:List[SCL])(map:M) = {
    if (remainNodes.size==0) {
      finPass(map)
    } else {
      val cl::rest = remainNodes 
      mapDep(cl) {
        ctrlMapper.fold((m:M) => m) { _.mapCtrlIOs(cl) {
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
    log(info
    // Debug
    //, ((m:M) => ()),
      //{
        //case e@FailToMapNode(_,_,_,mp) =>
          //new CUDotPrinter()(design).print(mp.asInstanceOf[M])
          //println(info)
        //case e:Throwable =>
          //println(e)
      //}
    // Debug --
    ) {
      recResWithExcept[R,N,M](cl, List(constrain _), resFilter _, next _, map)
    }
  }

  val minHop = 1
  val maxHop = 6

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
    CUSwitchMapper.filterUsedRoutes(routes, m)
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
    log( info
       //Debug
      , ((m:M) => ()),
        {
          case e@FailToMapNode(_,_,exceps,mp) =>
            new CUDotPrinter()(design).print(mp.asInstanceOf[M])
            println(info)
          case e:Throwable =>
            println(e)
        }
      // Debug --
    ){
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

  def advance(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap =
    CUSwitchMapper.advance((pne:PNE) => pne.vouts)(start, validCons, advanceCons)
}
object CUSwitchMapper {
  type Edge = (POB, PIB)
  type Path = List[Edge]
  type PathMap = List[(PCL, Path)]
  def quote(io:PIO[PNE])(implicit design:Design):String = {
    io.src match {
      case cu:PCU => io.toString
      case sb:PSB => DotCodegen.quote(sb) 
      case top:PTop => top.toString
    }
  }
  def quote(path:CUSwitchMapper.Path)(implicit design:Design):String = {
    path.map { case (from, to) => s"${quote(from)} -> ${quote(to)}"}.mkString(", ")
  }

  /* 
   * Traverse interconnection graph to find qualified neighbor PCUs recursively that's within hop
   * count range minHop and maxHop (exclusive) around the starting CU. Return a list of  
   * reachable pcu and corresponding path to reach pcu based sorted by hop count
   * Returns a list of [reachedCU, path from start to reachedCU]
   * @param start starting Spade cu of traversal 
   * @param validCons condition on whether the path is valid based on the last CU encountered and the
   * current path 
   * @param advanceCons condition on whether continue advancing based on the current switchbox
   * encountered and path went through so far 
   * */
  def advance(vouts:PNE => List[POB])(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean)(implicit design:Design):PathMap = {
    advanceDFS(vouts)(start, validCons, advanceCons)
  }

  def advanceDFS(vouts:PNE => List[POB])(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean)(implicit design:Design):PathMap = {
    def rec(pne:PNE, path:Path, map:PathMap):PathMap = {
      val visited = path.map{ case (f,t) => f.src }
      if (visited.contains(pne)) return map
      //Prioritize visiting PCU to finish faster on hit
      val vos = vouts(pne).sortWith{ case (vo1, vo2) => vo1.src.isInstanceOf[PCU] || !vo2.src.isInstanceOf[PCU] }
      vos.foldLeft(map) { case (preMap, vout) =>
        vout.fanOuts.foldLeft(preMap) { case (pm, vin) =>
          val newPath = path :+ (vout, vin)
          vin.src match {
            case cl:PCU if validCons(vin, newPath) => pm :+ (cl, newPath)
            case cl:PTop if validCons(vin, newPath) => pm :+ (cl, newPath)
            case sb:PSB if advanceCons(sb, newPath) => rec(vin.src, newPath, pm)
            case _ =>  pm 
          }
        }
      }
    }
    rec(start, Nil, Nil).sortWith(_._2.size < _._2.size)
  }

  def advanceBFS(vouts:PNE => List[POB])(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean)(implicit design:Design):PathMap = {
    val result = ListBuffer[(PCL, Path)]()
    val paths = Queue[Path]()
    paths += Nil
    while (paths.size!=0) {
      val path =  paths.dequeue
      val pne:PNE = path.lastOption.fold[PNE](start) { _._2.src }
      val visited = path.map{ case (f,t) => f.src }
      if (!visited.contains(pne)) {
        val vos = vouts(pne).sortWith{ case (vo1, vo2) => vo1.src.isInstanceOf[PCU] || !vo2.src.isInstanceOf[PCU] }
        vos.foreach { vout =>
          vout.fanOuts.foreach { vin =>
            val newPath = path :+ (vout, vin)
            vin.src match {
              case cl:PCU if validCons(vin, newPath) => result += (cl ->newPath)
              case cl:PTop if validCons(vin, newPath) => result += (cl ->newPath)
              case sb:PSB if advanceCons(sb, newPath) => paths += newPath
              case _ =>
            }
          }
        }
      }
    }
    result.toList
  }

  def filterUsedRoutes(routes:PathMap, map:PIRMap):PathMap = {
    routes.filterNot { case r@(reached, path) =>
      path.zipWithIndex.exists { case ((vout, vin), i) =>
        val vinTaken = map.vimap.pmap.contains(vin)
        if (vinTaken) assert(vin.src.isInstanceOf[PCL])
        //val edgeTaken = map.fbmap.get(vin).fold(false) { vo =>
          //(vo != vout) //TODO edge consider not taken if have the same mapping. Potential risk here?
        //}
        val edgeTaken = map.fbmap.contains(vin)
        val switchTaken = {
          if (vout.src.isInstanceOf[PSB]) {
            // Check switch box
            val to = vout.voport
            val from = path(i - 1)._2.viport
            map.fpmap.contains(to)
            //map.fpmap.get(to).fold(false) {
              //_ != from
            //}
          } else false
        } // no edge has been taken
        vinTaken || edgeTaken || switchTaken
      }
    }
  }
}
