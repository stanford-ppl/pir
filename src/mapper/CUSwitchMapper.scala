package pir.graph.mapper
import pir.graph._
import pir._
import pir.typealias._
import pir.codegen.Printer
import pir.graph.traversal.{PIRMapping, MapPrinter, CUDotPrinter}
import pir.plasticine.graph._
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
  def quote(io:IO[PNE])(implicit spade:Spade):String = {
    io.src match {
      case cu:PCU => io.toString
      case sb:PSB => CUDotPrinter.quote(sb) 
    }
  }
  def quote(path:CUSwitchMapper.Path)(implicit spade:Spade):String = {
    path.map { case (from, to) => s"${quote(from)} -> ${quote(to)}"}.mkString(", ")
  }
}
class CUSwitchMapper(soMapper:ScalarOutMapper)(implicit val design:Design) extends Mapper {
  type R = PCL
  type N = CL
  type V = CLMap.V
  type Edge = CUSwitchMapper.Edge 
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 

  val typeStr = "CUSwitchMapper"

  def finPass(m:M):M = m

  val resMap:MMap[N, List[R]] = MMap.empty

  /* 
   * Traverse interconnection graph to find qualified neighbor PCUs recursively that's within hop
   * count range minHop and maxHop (exclusive) around the starting CU. Return a mapping 
   * of HopCount -> List of (qualified PCU -> Path to qualified PCU)
   * @param start starting Spade cu of traversal 
   * @param minHop minimum hop count starting to record (inclusive)
   * @param maxHop maximum hop count stopping to record and traverse (exclusive)
   * */
  def advance(start:PCU, minHop:Int, maxHop:Int):PathMap = { 
    advanceDFS(start, minHop, maxHop)
  }

  def advanceDFS(start:PCU, minHop:Int, maxHop:Int):PathMap = {
    def rec(pne:PNE, path:Path, map:PathMap):PathMap = {
      val visited = path.map{ case (f,t) => f.src }
      if (visited.contains(pne)) return map
      pne.vouts.foldLeft(map) { case (preMap, vout) =>
        vout.fanOuts.foldLeft(preMap) { case (pm, vin) =>
          val newPath = path :+ (vout, vin)
          val hop = newPath.size 
          vin.src match {
            case cu:PCU if (hop>=minHop && hop<maxHop && start!=cu) => pm :+ (cu, newPath)
            case sb:PSB if (hop<maxHop) => rec(vin.src, newPath, pm)
            case _ => pm 
          }
        }
      }
    }
    rec(start, Nil, Nil).sortWith(_._2.size < _._2.size)
  }

  def advanceBFS(start:PCU, minHop:Int, maxHop:Int):PathMap = {
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
            val hop = newPath.size
            vin.src match {
              case cu:PCU if (hop>=minHop && hop<maxHop && start!=cu) => result += (cu ->newPath)
              case sb:PSB if (hop<maxHop) => paths += newPath
              case _ =>
            }
          }
        }
      }
    }
    result.toList
  }

  def search(cu:CU, m:M) = {
    val pdeps:List[PCU] = cu.vins.flatMap { vin => 
      val writer = vin.vector.writer
      m.clmap.get(writer.ctrler).flatMap { pcl =>
        pcl match {
          case pcu:PCU => Some(pcu)
          case top:PTop => None
          case _ => None
        }
      }
    }

    val results = pdeps.map{ pdep => (pdep, advance(pdep, 1, 7)) }

    val validCUs = results.map( _._2.map{ case (cu, path) => cu }.toSet ).reduce(_ intersect _)
    val validPaths = results.map { case (pdep, result) =>
      pdep -> result.filter { case (cu, path) => validCUs.contains(cu) }
    }
  }

  def resFunc(cu:N, m:M, remainRes:List[R]):List[R] = {
    resMap(cu).filter { pcu => !m.clmap.pmap.contains(pcu) }
  }

  def mapCU(cu:N, pcu:R, pirMap:M):M = {
    if (cu.isInstanceOf[TT]) assert(pcu.isInstanceOf[PTT], s"$cu, $pcu") 
    val cmap = pirMap.setCL(cu, pcu) 
    /* Map CU */
    Try {
      soMapper.map(cu, cmap)
    }.map { m =>
      val ins = cu match {
        case cl:TT => cu.sins // Assume tile transfer vin internallly connected
        case _ => cu.vins ++ cu.sins
      }
      m
    } match {
      case Success(m) => dprintln(s"$cu -> $pcu (succeeded)"); m
      case Failure(e) => dprintln(s"$cu -> $pcu (failed)"); throw e
    }
  }

  val cons = List(mapCU _)

  def mapCUs(pcus:List[PCU], cus:List[ICL], pirMap:M, finPass:M => M):M = {
    CUMapper.qualifyCheck(pcus, cus, resMap)
    // Bind nodes to resources
    bind(pcus, cus, pirMap, cons, resFunc _, finPass)
  }

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    Try{
      mapCU(design.top, design.arch.top, m) 
    } match {
      case Success(mp) => mapCUs(design.arch.cus, design.top.innerCUs, mp, finPass _)
      case Failure(e) => throw e
    }
  }
}
