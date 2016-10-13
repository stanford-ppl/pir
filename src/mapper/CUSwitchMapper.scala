package pir.graph.mapper
import pir.graph._
import pir.{Design, Config}
import pir.typealias._
import pir.codegen.{Printer, DotCodegen}
import pir.graph.traversal.{PIRMapping, MapPrinter, CUDotPrinter}
import pir.plasticine.graph.{Node => PNode}
import pir.plasticine.main._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.util.{Try, Success, Failure}

object CUSwitchMapper {
  type Edge = (POB, PIB)
  type Path = List[Edge]
  type PathMap = List[(PCL, Path)]
  def quote(io:PIO[PNE])(implicit design:Design):String = {
    io.src match {
      case cu:PCU => io.toString
      case sb:PSB => DotCodegen.quote(sb) 
    }
  }
  def quote(path:CUSwitchMapper.Path)(implicit design:Design):String = {
    path.map { case (from, to) => s"${quote(from)} -> ${quote(to)}"}.mkString(", ")
  }
}
class CUSwitchMapper(outputMapper:OutputMapper)(implicit val design:Design) extends CUMapper {
  type Edge = CUSwitchMapper.Edge 
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 

  val typeStr = "CUSwitchMapper"

  val resMap:MMap[SCL, List[PCL]] = MMap.empty

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val pcus = design.arch.cus
    val cus = design.top.innerCUs
    val grp = cus.groupBy(_.isInstanceOf[TT]) 
    val pgrp = pcus.groupBy(_.isInstanceOf[PTT])
    val tts = grp.getOrElse(true, Nil)
    val ptts = pgrp.getOrElse(true, Nil)
    val rcus = grp.getOrElse(false, Nil)
    val prcus = pgrp.getOrElse(false, Nil)
    if (tts.size > ptts.size) throw OutOfPTT(ptts.size, tts.size)
    if (rcus.size > prcus.size) throw OutOfPCU(prcus.size, rcus.size)

    val nodes = design.top::cus
    val reses = design.arch.top::pcus
    CUMapper.qualifyCheck(reses, nodes, resMap)
    mapCUs(finPass _)(nodes, m)
  }

  def mapCUs(fp:M => M)(remainNodes:List[SCL], map:M):M = {
    type R = PCL
    type N = SCL
    if (remainNodes.size==0) return fp(map) // throw MappingException
    val cu::restNodes = remainNodes 
    val pdeps = getDeps(cu.vins, map) // returns unmapped vins whose depended cu are mapped
    dbsln(s"Mapping $cu")
    Try {
    if (pdeps.size==0) { // If there's no dependency
      def constrain(cu:N, pcu:R, m:M):M = {
        cu.readers.foldLeft(mapCU(cu, pcu, m)) { case (pm, reader) =>
          val rpdeps = getDeps(reader.vins, pm)
          if (pm.clmap.contains(reader) && rpdeps.size!=0) {
            mapDep(reader, (m:M) => m)(rpdeps, pm) 
          } else { 
            pm
          }
        }
      }
      def resFunc(cu:N, m:M):List[R] = {
        if (m.clmap.contains(cu)) List(m.clmap(cu))
        else resMap(cu).filter { pcu => !m.clmap.pmap.contains(pcu) }
      }
      recRes[R,N,M](List(constrain _), resFunc _, mapCUs(fp) _)(cu, restNodes, map)
    } else { // There is dependency
      mapDep(cu, mapCUs(fp)(remainNodes, _))(pdeps, map)
    }
    } match {
      case Success(m) => dbeln(s" Mapping $cu (success) "); m
      case Failure(e) => dbeln(s" Mapping $cu (failed) "); throw e
    }
  }

  val minHop = 1
  val maxHop = 5
  def mapDep(cl:SCL, fp:M => M)(remainDeps:List[(VI,PCL)], map:M):M = {
    type N = (VI, PCL)
    type R = (PCL, Path)
    if (remainDeps.size==0) return fp(map)
    // Function returns a list of available pathes to choose to map (vin, pdep)
    def resFunc(pdep:N, m:M):List[R] = {
      val (vin, start) = pdep
      val pcl = m.clmap.get(cl) 
      def validCons(toVin:PIB, path:Path) = {
        val to = toVin.src
        //if (to.id==15549) {
        //  dprintln(s"to: $to")
        //  dprintln(pcl.fold(resMap(cl).contains(to)) { _ == to })
        //  dprintln(path.size >= minHop)
        //  dprintln(s"${path.size < maxHop} ${path}")
        //  dprintln(to!=start)
        //  dprintln(!path.exists { case (vout, vin) => m.fbmap.contains(vin)} )
        //}
        pcl.fold(resMap(cl).contains(to)) { _ == to } && // If cl has been mapped, valid path reaches current pcl
        (path.size >= minHop) && // path is with required hops
        (path.size < maxHop) && 
        (to!=start) && // path doesn't end at depended CU
        !path.exists { case (vout, vin) => m.fbmap.contains(vin) } // No edge in path has been used
      }
      def advanceCons(psb:PSB, path:Path) = { 
        (path.size < maxHop) && // path is within maximum hop to continue
        !path.exists { case (vout, vin) => m.fbmap.contains(vin) } // No edge in path has been used
      }
      advance(start, validCons _, advanceCons _)
    }
    // Function check a specific path is valid for a given (vin, pdep) and exisiting mapping. If
    // valid, add path to the mapping and return the mapping
    def constrain(pdep:N, route:R, m:M):M = {
      val (pcl, path) = route
      val (vin, from) = pdep 
      var mp = m
      if (mp.clmap.contains(cl)) {
        assert(mp.clmap(cl)==pcl)
      } else {
        mp = mapCU(cl, pcl, m)
      }
      val pvin = path.last._2
      mp = mp.setVI(vin, pvin).setOP(vin.out, pvin.viport)
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
    val pdep::restDeps = remainDeps 
    val (vin, pdepcu) = pdep
    recRes[R, N, M](List(constrain _), resFunc _, mapDep(cl, fp) _)(pdep, restDeps, map)
  }

  def mapCU(cl:SCL, pcl:PCL, map:M):M = {
    dprintln(s"Try $cl -> $pcl")
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

  /* 
   * Traverse interconnection graph to find qualified neighbor PCUs recursively that's within hop
   * count range minHop and maxHop (exclusive) around the starting CU. Return a list of  
   * reachable pcu and corresponding path to reach pcu based sorted by hop count
   * @param start starting Spade cu of traversal 
   * @param validCons condition on whether the path is valid based on the last CU encountered and the
   * current path 
   * @param advanceCons condition on whether continue advancing based on the current switchbox
   * encountered and path went through so far 
   * */
  def advance(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap = {
    advanceDFS(start, validCons, advanceCons)
  }

  def advanceDFS(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap = {
    def rec(pne:PNE, path:Path, map:PathMap):PathMap = {
      val visited = path.map{ case (f,t) => f.src }
      if (visited.contains(pne)) return map
      pne.vouts.foldLeft(map) { case (preMap, vout) =>
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

  def advanceBFS(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap = {
    val result = ListBuffer[(PCL, Path)]()
    val paths = Queue[Path]()
    paths += Nil
    while (paths.size!=0) {
      val path =  paths.dequeue
      val pne:PNE = path.lastOption.fold[PNE](start) { _._2.src }
      val visited = path.map{ case (f,t) => f.src }
      if (!visited.contains(pne)) {
        pne.vouts.foreach { vout =>
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

}
