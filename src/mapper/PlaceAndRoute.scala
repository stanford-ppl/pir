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

trait PlaceAndRoute {
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
  def quote(path:Path)(implicit design:Design):String = {
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
  def advance(outs:PNE => List[POB])(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean)(implicit design:Design):PathMap = {
    advanceDFS(outs)(start, validCons, advanceCons)
  }

  def advanceDFS(outs:PNE => List[POB])(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean)(implicit design:Design):PathMap = {
    def rec(pne:PNE, path:Path, map:PathMap):PathMap = {
      val visited = path.map{ case (f,t) => f.src }
      if (visited.contains(pne)) return map
      //Prioritize visiting PCU to finish faster on hit
      val os = outs(pne).sortWith{ case (o1, o2) => o1.src.isInstanceOf[PCU] || !o2.src.isInstanceOf[PCU] }
      os.foldLeft(map) { case (preMap, out) =>
        out.fanOuts.foldLeft(preMap) { case (pm, in) =>
          val newPath = path :+ (out, in)
          in.src match {
            case cl:PCL if validCons(in, newPath) => pm :+ (cl, newPath)
            case sb:PSB if advanceCons(sb, newPath) => rec(in.src, newPath, pm)
            case _ =>  pm 
          }
        }
      }
    }
    rec(start, Nil, Nil).sortWith(_._2.size < _._2.size)
  }

  def advanceBFS(outs:PNE => List[POB])(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean)(implicit design:Design):PathMap = {
    val result = ListBuffer[(PCL, Path)]()
    val paths = Queue[Path]()
    paths += Nil
    while (paths.size!=0) {
      val path =  paths.dequeue
      val pne:PNE = path.lastOption.fold[PNE](start) { _._2.src }
      val visited = path.map{ case (f,t) => f.src }
      if (!visited.contains(pne)) {
        val os = outs(pne).sortWith{ case (o1, o2) => o1.src.isInstanceOf[PCU] || !o2.src.isInstanceOf[PCU] }
        os.foreach { out =>
          out.fanOuts.foreach { in =>
            val newPath = path :+ (out, in)
            in.src match {
              case cl:PCU if validCons(in, newPath) => result += (cl ->newPath)
              case cl:PTop if validCons(in, newPath) => result += (cl ->newPath)
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
      path.zipWithIndex.exists { case ((out, in), i) =>
        val inTaken = map.vimap.pmap.contains(in)
        if (inTaken) assert(in.src.isInstanceOf[PCL])
        //val edgeTaken = map.fbmap.get(in).fold(false) { o =>
          //(o != out) //TODO edge consider not taken if have the same mapping. Potential risk here?
        //}
        val edgeTaken = map.fbmap.contains(in)
        val switchTaken = {
          if (out.src.isInstanceOf[PSB]) {
            // Check switch box
            val to = out.voport
            val from = path(i - 1)._2.viport
            map.fpmap.contains(to)
            //map.fpmap.get(to).fold(false) {
              //_ != from
            //}
          } else false
        } // no edge has been taken
        inTaken || edgeTaken || switchTaken
      }
    }
  }
}

trait FatPlaceAndRoute {
  type FatEdge = List[Edge] 
  type FatPaths = List[(PCL, FatPath)]
  type FatPath = List[FatEdge]
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
  def quote(path:Path)(implicit design:Design):String = {
    path.map { case (from, to) => s"${quote(from)} -> ${quote(to)}"}.mkString(", ")
  }

  def advance(outs:PNE => List[POB])(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
      advanceCons:(PSB, FatPath) => Boolean)(implicit design:Design):FatPaths
    = advanceBFS(outs)(start, validCons, advanceCons)

  def advanceBFS(outs:PNE => List[POB])(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
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
        val os = outs(pne).sortWith{ case (o1, o2) => o1.src.isInstanceOf[PCU] || !o2.src.isInstanceOf[PCU] }
        val edges = os.flatMap { out => out.fanOuts.map { in => (out, in) } }
        val bundle = edges.groupBy { case (o, vi) => (o.src, vi.src) }
        bundle.foreach { case ((fpne, tpne), fatEdge) =>
          val newPath = fatpath :+ fatEdge 
          tpne match {
            case cl:PCL => 
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
        fatedge.filterNot { case (out, in) => // available edges
          val inTaken = map.vimap.pmap.contains(in)
          if (inTaken) assert(in.src.isInstanceOf[PCL])
          val outTaken = map.vomap.pmap.contains(out)
          val edgeTaken = map.fbmap.contains(in)
          val switchTaken = {
            if (out.src.isInstanceOf[PSB]) {
              val to = out.voport // Check switch box
              map.fpmap.contains(to) // Conservative here
            } else false
          }
          inTaken || outTaken || edgeTaken || switchTaken
        }
      }
      if (isFatPathValid(filteredFatpath)) Some((reached, filteredFatpath)) else None
    }
    availableRoutes.map { case (reachedCU, fatpath) =>
      (reachedCU, fatpath.map{ fatedge => fatedge.head }) // For any fatpath, pick the first avalable edge
    }
  }

  def isFatPathValid(fatpath:FatPath) = { !fatpath.exists{_.size==0} }

}
