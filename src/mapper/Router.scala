package pir.graph.mapper
import pir.graph._
import pir.misc._
import pir.{Config, Design}
import pir.typealias._
import pir.codegen.{DotCodegen, Printer}
import pir.graph.traversal.{CUCtrlDotPrinter, CUScalarDotPrinter, CUVectorDotPrinter, MapPrinter, PIRMapping}
import pir.plasticine.graph.{Node => PNode}
import pir.plasticine.main._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.util.{Failure, Success, Try}

abstract class Router(implicit design:Design) extends Mapper {
  implicit def spade:Spade = design.arch

  val minHop = 1
  val maxHop = 7
  override val exceptLimit = 100

  def debugRouting:Boolean

  type I<:Node
  type O<:Node
  type R = (PCL, Path)
  type FatEdge = List[Edge] 
  type FatPaths = List[(PCL, FatPath)]
  type FatPath = List[FatEdge]
  type Edge = (POB, PIB)
  type Path = List[Edge]
  type Paths = List[(PCL, Path)]
  def quote(io:PIO[PNE]):String = {
    io.src match {
      case cu:PCU => io.toString
      case sb:PSB => DotCodegen.quote(sb) 
      case top:PTop => top.toString
    }
  }

  def ctrler(io:Node):CL
  def from(in:I):O
  def to(out:O):List[I]
  def ins(cl:CL):List[I]
  def outs(cl:CL):List[O]

  def io(pne:PNE):PGIO[PNE]

    // DEBUG
  def failPass(mp:M, info:String):Unit = if (debugRouting) {
    err(info)
    dprintln(info)
    this match {
      case router:VectorRouter =>
        new CUVectorDotPrinter(true)(design).print(mp.asInstanceOf[M])
      case router:ScalarRouter =>
        new CUScalarDotPrinter(true)(design).print(mp.asInstanceOf[M])
      case router:ControlRouter =>
        new CUCtrlDotPrinter(true)(design).print(mp.asInstanceOf[M])
    }
  }
  def failPass(e:Throwable):Unit = if (debugRouting) {
    e match {
      case e:MappingException[_] =>
        failPass(e.mapping.asInstanceOf[PIRMap], s"$e")
      case e:Throwable =>
        println(e)
    }
  } else {
    (e:Throwable) => ()
  }
    // DEBUG --

  def filterPNE(cl:CL, pnes:List[PNE], m:PIRMap):List[PNE] = {
    pnes
  }

  def quote(path:Path):String = {
    path.map { case (from, to) => s"${quote(from)} -> ${quote(to)}"}.mkString(", ")
  }

  def advance(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
      advanceCons:(PSB, FatPath) => Option[FatPath]):FatPaths = {
    advanceBFS(start, validCons, advanceCons)
  }

  def advanceBFS(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
      advanceCons:(PSB, FatPath) => Option[FatPath]):FatPaths = {
    val result = ListBuffer[(PCL, FatPath)]()
    val fatpaths = Queue[FatPath]()
    fatpaths += Nil
    while (fatpaths.size!=0) {
      val fatpath =  fatpaths.dequeue
      //fatpath.reduce { case ((i1, o1), (i2, o2)) => assert((i1==i2) && (o1==o2)) }
      val pne:PNE = fatpath.lastOption.fold[PNE](start) { _.head._2.src }
      val visited = fatpath.map{_.head}.map{ case (f,t) => f.src }
      if (!visited.contains(pne)) {
        val os = io(pne).outs.sortWith{ case (o1, o2) => o1.src.isInstanceOf[PCU] || !o2.src.isInstanceOf[PCU] }
        val edges = os.flatMap { out => out.fanOuts.map { in => (out, in) } }
        val bundle = edges.groupBy { case (o, vi) => (o.src, vi.src) }
        bundle.foreach { case ((fpne, tpne), fatEdge) =>
          val newPath = fatpath :+ fatEdge 
          tpne match {
            case cl:PCL => 
              validCons(cl, newPath).foreach { newPath => result += (cl -> newPath) }
            case sb:PSB =>
              advanceCons(sb, newPath).foreach { newPath => fatpaths += newPath }
            case _ =>
          }
        }
      }
    }
    result.toList
  }

  def filterUsedPaths(in:I, out:O, fatpath:FatPath, map:PIRMap):Option[FatPath] = {
      // If out is already placed, use the mapped pout. Otherwise use an unused pout
      val pouts = map.vomap.get(out)
      val filteredFatpath = fatpath.map { fe => // Find fatpath that has empty fatEdge after filter
        var fatedge = fe
        val feOutNotUsed = fatedge.filterNot{ case (po, pi) => map.vomap.pmap.contains(po)}
        fatedge = pouts.fold(feOutNotUsed) { pouts =>
          val feOutReused = fatedge.filter { case (po, pi) => pouts.contains(po) }
          if (!feOutReused.isEmpty) feOutReused else feOutNotUsed
        }
        fatedge = fatedge.filter { case (po, pi) => 
          map.vimap.pmap.get(pi).fold(true) { ins => from(ins.head.asInstanceOf[I])==from(in) }
        }
        fatedge = fatedge.filterNot { case (po, pi) => // available edges
          val edgeTaken = map.fbmap.get(pi).fold(false) { _ != po }
          val switchTaken = {
            if (po.src.isInstanceOf[PSB]) {
              val to = po.voport // Check switch box
              map.fpmap.contains(to) // Conservative here
            } else false
          }
          val invalid = edgeTaken || switchTaken
          invalid
        }
        fatedge
      }
      if (isFatPathValid(filteredFatpath)) Some(filteredFatpath) else None
  }

  //def filterUsedFatMaps(in:I, out:O, routes:FatPaths, map:PIRMap):Paths = {
    //val available = routes.flatMap { case (reached, fatpath) =>
      //filterUsedPaths(in, out, fatpath, map).map{ fatpath => (reached, fatpath) }
    //}
    //head(available)
  //}

  def head(fatpaths:FatPaths):Paths = {
    fatpaths.map { case (reachedCU, fatpath) =>
      (reachedCU, fatpath.map{ fatedge => fatedge.head }) // For any fatpath, pick the first avalable edge
    }
  }

  def isFatPathValid(fatpath:FatPath) = { !fatpath.exists{_.size==0} }

  def resFilter(in:I, m:M, triedRes:Paths):Paths = {
    val cl = ctrler(in)
    val pcl = m.clmap(cl)
    val out = from(in) 
    def validCons(reached:PCL, fatpath:FatPath):Option[FatPath] = {
      var valid = true
      valid &&= (reached == pcl)
      valid &&= (fatpath.size >= minHop)
      valid &&= (fatpath.size < maxHop)
      if (valid) { filterUsedPaths(in, out, fatpath, m) } else { None }
    }
    def advanceCons(psb:PSB, fatpath:FatPath):Option[FatPath] = {
      var valid = true
      valid &&= (fatpath.size < maxHop)
      if (valid) { filterUsedPaths(in, out, fatpath, m) } else None
    }
    val fcl = ctrler(from(in))
    val pfcl = m.clmap(fcl)
    val routes = advance(pfcl, validCons _, advanceCons _)
    val remain = routes.diff(triedRes)
    if (remain.isEmpty) {
      val (x1, y1) = pcl.coord
      val (x2, y2) = pfcl.coord
      failPass(m, s"resFunc: $in of $cl($pcl[$x1, $y1]) from $out of $fcl($pfcl[$x2, $y2]) -> routes:${routes.size}")
    }
    head(remain)
    //val froutes = filterUsedFatMaps(in, out, routes, m)
    //if (froutes.isEmpty) { failPass(m, s"resFunc: $in of $fcl -> routes:${routes.size} froutes:${froutes.size}") }
    //froutes
  }

  def mapIns(info:String)(ins:List[I], m:M):M = {

    val sameSrcMap:Map[CL, Map[O, List[I]]] = ins.groupBy{ in => ctrler(in) }.map { case (ctrler, ins) =>
      ctrler -> ins.groupBy { in => from(in) }
    }

    def cons(n:I, r:R, m:M):M = {
      log(s"Try $n -> $r", (m:M) => (), failPass) {
        val (reachedCU, path) = r
        val pin = path.last._2
        val pout = path.head._1
        var mp = sameSrcMap(ctrler(n))(from(n)).foldLeft(m) { case (pm, n) =>
          pm.setVI(n, pin).setVO(from(n), pout).setRT(n, path.size)
        }
        path.zipWithIndex.foreach { case ((out, in), i) => 
          mp = mp.setFB(in, out)
          if (out.src.isInstanceOf[PSB]) { // Config SwitchBox
            val to = out.voport
            val from = path(i-1)._2.viport
            mp = mp.setFP(to, from)
          }
        }
        mp
      }
    }

    val uniqueIns = sameSrcMap.flatMap { case (ctlrer, srcMap) => 
      srcMap.map { case (out, ins) => ins.head }
    }.toList


    log(info, ((m:M) => ()), failPass) {
      bind[R,I,M](
        allNodes=uniqueIns, 
        initMap=m, 
        constrain=cons _, 
        resFunc=resFilter _, 
        finPass= (m:M) => m
      )
    }
  }

  def route(cl:CL, m:M):M = {
    val pcl = m.clmap(cl)
    val outins = outs(cl).flatMap { out =>
      to(out).filter { in => 
        !m.vimap.contains(in) && m.clmap.contains(ctrler(in))
      }
    }
    var mp = mapIns(s"Routing outIns of $cl")(outins, m)
    val inputs = ins(cl).filter { in =>
      !m.vimap.contains(in) && m.clmap.contains(ctrler(from(in)))
    }
    mp = mapIns(s"Routing ins of $cl")(inputs, mp)
    mp
  }

  override def mappingCheck(mapping:M) = {
    design.top.ctrlers.foreach { ctrler =>
      ins(ctrler).foreach { in =>
        if(!mapping.vimap.contains(in))
          throw PIRException(s"${in} in ${ctrler} wasn't mapped")
      }
      outs(ctrler).foreach { out =>
        if(!mapping.vomap.contains(out))
          throw PIRException(s"${out} in ${ctrler} wasn't mapped")
      }
    }
  }
}

class VectorRouter()(implicit val design:Design) extends Router {
  override val typeStr = "VecRouter"

  type I = VI
  type O = VO

  def debugRouting:Boolean = false 

  def io(pne:PNE):PGIO[PNE] = pne.vectorIO

  def ctrler(io:Node):CL = {
    io match {
      case in:I => in.ctrler
      case out:O => out.ctrler
    }
  }
  def from(in:I):O = in.writer
  def to(out:O) = out.readers
  def ins(cl:CL):List[I] = cl.vins
  def outs(cl:CL):List[O] = cl.vouts

}

class ScalarRouter()(implicit val design:Design) extends Router {
  override val typeStr = "ScalRouter"

  type I = SI
  type O = SO

  def debugRouting:Boolean = false 

  def io(pne:PNE):PGIO[PNE] = pne.scalarIO

  def ctrler(io:Node):CL = {
    io match {
      case in:I => in.ctrler
      case out:O => out.ctrler
    }
  }
  def from(in:I):O = in.writer
  def to(out:O) = out.readers
  def ins(cl:CL):List[I] = cl.sins
  def outs(cl:CL):List[O] = cl.souts
}

class ControlRouter()(implicit val design:Design) extends Router {
  override val typeStr = "CtrlRouter"

  type I = IP
  type O = OP

  def debugRouting:Boolean = true

  def io(pne:PNE):PGIO[PNE] = pne.ctrlIO

  def ctrler(io:Node):CL = {
    io match {
      case in:I => in.src.asInstanceOf[PRIM].ctrler
      case out:O => out.src.asInstanceOf[PRIM].ctrler
    }
  }
  def from(in:I):O = in.from
  def to(out:O) = out.to.toList
  def ins(cl:CL):List[I] = cl.ctrlIns
  def outs(cl:CL):List[O] = cl.ctrlOuts
}
