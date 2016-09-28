package pir.graph.mapper
import pir.graph._
import pir._
import pir.typealias._
import pir.codegen.Printer
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
  type PathMap = List[(PCU, Path)]
  def quote(io:PIO[PNE])(implicit spade:Spade):String = {
    io.src match {
      case cu:PCU => io.toString
      case sb:PSB => PNode.quote(sb) 
    }
  }
  def quote(path:CUSwitchMapper.Path)(implicit spade:Spade):String = {
    path.map { case (from, to) => s"${quote(from)} -> ${quote(to)}"}.mkString(", ")
  }
}
class CUSwitchMapper(soMapper:ScalarOutMapper)(implicit val design:Design) extends Mapper {
  type Edge = CUSwitchMapper.Edge 
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 

  val typeStr = "CUSwitchMapper"

  def finPass(m:M):M = m

  val resMap:MMap[CL, List[PCL]] = MMap.empty

  /* 
   * Traverse interconnection graph to find qualified neighbor PCUs recursively that's within hop
   * count range minHop and maxHop (exclusive) around the starting CU. Return a mapping 
   * of HopCount -> List of (qualified PCU -> Path to qualified PCU)
   * @param start starting Spade cu of traversal 
   * @param minHop minimum hop count starting to record (inclusive)
   * @param maxHop maximum hop count stopping to record and traverse (exclusive)
   * */
  def advance(start:PCU, cuCons:(PCU, Path) => Boolean, sbCons:(PSB, Path) => Boolean):PathMap = {
    advanceDFS(start, cuCons, sbCons)
  }

  def advanceDFS(start:PCU, cuCons:(PCU, Path) => Boolean, sbCons:(PSB, Path) => Boolean):PathMap = {
    def rec(pne:PNE, path:Path, map:PathMap):PathMap = {
      val visited = path.map{ case (f,t) => f.src }
      if (visited.contains(pne)) return map
      pne.vouts.foldLeft(map) { case (preMap, vout) =>
        vout.fanOuts.foldLeft(preMap) { case (pm, vin) =>
          val newPath = path :+ (vout, vin)
          vin.src match {
            case cu:PCU if cuCons(cu, newPath) => pm :+ (cu, newPath)
            case sb:PSB if sbCons(sb, newPath) => rec(vin.src, newPath, pm)
            case _ => pm 
          }
        }
      }
    }
    rec(start, Nil, Nil).sortWith(_._2.size < _._2.size)
  }

  def advanceBFS(start:PCU, cuCons:(PCU, Path) => Boolean, sbCons:(PSB, Path) => Boolean):PathMap = {
    val result = ListBuffer[(PCU, Path)]()
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
              case cu:PCU if cuCons(cu, newPath) => result += (cu ->newPath)
              case sb:PSB if sbCons(sb, newPath) => paths += newPath
              case _ =>
            }
          }
        }
      }
    }
    result.toList
  }

  def getDeps(vins:List[VI], map:M):List[(VI, PCU)] = {
    // Already mapped depended PCUs
    vins.flatMap { vin => 
      if (map.vimap.contains(vin)) { None
      } else {
        val writer = vin.vector.writer
        map.clmap.get(writer.ctrler).flatMap { pcl => 
          pcl match {
            case pcu:PCU => Some(vin ->pcu)
            case _ => None
          }
        }
      }
    }
  }

  def mapCUs(finPass:M => M)(remainNodes:List[CU], map:M):M = {
    type R = PCU
    type N = CU
    if (remainNodes.size==0) return finPass(map) // throw MappingException
    val cu::restNodes = remainNodes 
    val pdeps = getDeps(cu.vins, map)
    if (pdeps.size==0) { // If there's no dependency
      def constrain(cu:N, pcu:R, m:M):M = { 
        val mp = m.setCL(cu, pcu)
        cu.readers.foldLeft(mp) { case (pm, reader) =>
          reader match {
            case rd:CU if (pm.clmap.contains(rd)) => mapDep(rd, (m:M) => m)(getDeps(rd.vins, pm), pm) 
            case _ => pm
          }
        }
      }
      def resFunc(cu:N, m:M):List[R] = {
        resMap(cu).filter { pcu => !m.clmap.pmap.contains(pcu) }.asInstanceOf[List[R]]
      }
      recRes[R,N,M](List(constrain _), resFunc _, mapCUs((m:M) => m) _)(cu, restNodes, map)
    } else { // There is dependency
      mapDep(cu, mapCUs((m:M) => m)(restNodes, _))(pdeps, map)
    }
  }

  val minHop = 1
  val maxHop = 4
  def mapDep(cu:CU, finPass:M => M)(remainDeps:List[(VI,PCU)], map:M):M = {
    type N = (VI, PCU)
    type R = (PCU, Path)
    if (remainDeps.size==0) return finPass(map)
    def resFunc(pdep:N, m:M):List[R] = {
      val (vin, start) = pdep
      val pcu = m.clmap.get(cu) 
      def cuCons(to:PCU, path:Path) = {
        pcu.fold(true) { _ == to } && (path.size >= minHop) && (path.size < maxHop) && (to!=start) &&
        !path.exists { case (vout, vin) => m.fbmap.contains(vin) } // No edge in path has been used
      }
      def sbCons(psb:PSB, path:Path) = { 
        (path.size < maxHop) &&
        !path.exists { case (vout, vin) => m.fbmap.contains(vin) } // No edge in path has been used
      }
      advance(start, cuCons _, sbCons _)
    }
    def constrain(pdep:N, route:R, m:M):M = {
      val (pcu, path) = route
      val (vin, from) = pdep 
      var mp = m
      if (mp.clmap.contains(cu)) {
        assert(mp.clmap(cu)==pcu)
      } else {
        mp = mp.setCL(cu, pcu)
      }
      mp = mp.setVI(vin, path.last._2)
      path.zipWithIndex.foreach { case ((vout, vin), i) => 
        mp = mp.setFB(vin, vout)
        if (vout.src.isInstanceOf[PSB]) {
          val to = vout.voport
          val from = path(i-1)._2.viport
          mp = mp.setFP(to, from)
        }
      }
      mp
    }
    val pdep::restDeps = remainDeps 
    recRes[R, N, M](List(constrain _), resFunc _, mapDep(cu, finPass) _)(pdep, restDeps, map)
  }

  def mapCUs(pcus:List[PCU], cus:List[ICL], m:M, finPass:M => M):M = {
    CUMapper.qualifyCheck(pcus, cus, resMap)
    mapCUs(finPass)(cus, m)
  }

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val mp = m.setCL(design.top, design.arch.top)
    mapCUs(design.arch.cus, design.top.innerCUs, mp, finPass _)
  }
}
