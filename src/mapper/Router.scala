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
  type R = (PCL, Path[Edge])
  type Edge = (POB, PIB)
  type REdge = (PIB, POB)
  type Path[E] = List[E]
  type Paths[E] = List[(PCL, Path[E])]
  type FatEdge[E] = List[E] 
  type FatPaths[E] = List[(PCL, FatPath[E])]
  type FatPath[E] = List[FatEdge[E]]

  override def quote(n:Any)(implicit spade:Spade):String = {
    n match {
      case io:PIO[_] =>
        io.src match {
          case cu:PCU => io.toString
          case sb:PSB => super.quote(sb) 
          case top:PTop => top.toString
        }
      case n => super.quote(n)
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

  def logCond(header:String, valid:Boolean, cond:Boolean, info:String):Boolean = {
    if (valid && !cond) {
      dprintln(header, s"condition not passed : $info")
    }
    valid && cond
  }

  def filterTraverse[E](start: I => CL, inputs:List[I], reses:List[PNE], m:PIRMap, advanceFunc: AdvanceFunc[E]) = {
    inputs.foldLeft(reses) { case (reses, in) =>
      def validCons(reached:PCL, fatpath:FatPath[E]):Option[FatPath[E]] = {
        val header = s"validCons(reached:$reached, fatpath:${fatpath.size})"
        var valid = true
        valid = logCond(header, valid, reses.contains(reached), s"invalid res")
        valid = logCond(header, valid, !m.clmap.pmap.contains(reached), s"res $reached is used ")
        valid = logCond(header, valid, fatpath.size >= minHop, s"path ${fatpath.size} less than minHop $minHop")
        valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
        //valid &&= (reses.contains(reached) && !m.clmap.pmap.contains(reached))
        //valid &&= (fatpath.size >= minHop)
        //valid &&= (fatpath.size < maxHop)
        if (valid) Some(fatpath) else None
      }
      def advanceCons(psb:PSB, fatpath:FatPath[E]):Option[FatPath[E]] = {
        val header = s"advanceCons(psb:$psb fatpath:${fatpath.size})"
        var valid = true
        valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
        //valid &&= (fatpath.size < maxHop)
        if (valid) Some(fatpath) else None
      }
      advanceFunc(m.clmap(start(in)), validCons _, advanceCons _).map { _._1 }
    }
  }

  def filterOutIns(cl:CL, reses:List[PNE], m:PIRMap) = {
    val outins:List[I] = outs(cl).flatMap { out =>
      to(out).filter { in => 
        !m.vimap.contains(in) && m.clmap.contains(ctrler(in))
      }
    }
    def start(in:I) = ctrler(in)
    filterTraverse(start _, outins, reses, m, revAdvance _)
  }

  def filterIns(cl:CL, reses:List[PNE], m:PIRMap) = {
    val inputs:List[I] = ins(cl).filter { in =>
      !m.vimap.contains(in) && m.clmap.contains(ctrler(from(in)))
    }
    def start(in:I) = ctrler(from(in))
    filterTraverse(start _, inputs, reses, m, advance _)
  }

  def filterPNE(cl:CL, pnes:List[PNE], m:PIRMap):List[PNE] = {
    var reses = pnes 
    reses = emitBlock(s"filterOutIns") { filterOutIns(cl, reses, m) }
    reses = emitBlock(s"filterIns") { filterIns(cl, reses, m) }
    reses
  }

  def quote(path:Path[Edge]):String = {
    path.map { case (from, to) => s"${quote(from)} -> ${quote(to)}"}.mkString(", ")
  }

  type AdvanceFunc[E] = (PNE, (PCL, FatPath[E]) => Option[FatPath[E]], (PSB, FatPath[E]) => Option[FatPath[E]]) =>FatPaths[E] 

  def advance(start:PNE, validCons:(PCL, FatPath[Edge]) => Option[FatPath[Edge]], 
      advanceCons:(PSB, FatPath[Edge]) => Option[FatPath[Edge]]):FatPaths[Edge] = {
    advanceBFS(start, validCons, advanceCons)
  }

  def advanceBFS(start:PNE, validCons:(PCL, FatPath[Edge]) => Option[FatPath[Edge]], 
      advanceCons:(PSB, FatPath[Edge]) => Option[FatPath[Edge]]):FatPaths[Edge] = {
    val result = ListBuffer[(PCL, FatPath[Edge])]()
    val fatpaths = Queue[FatPath[Edge]]()
    fatpaths += Nil
    while (fatpaths.size!=0) {
      val fatpath =  fatpaths.dequeue
      //fatpath.reduce { case ((i1, o1), (i2, o2)) => assert((i1==i2) && (o1==o2)) }
      val pne:PNE = fatpath.lastOption.fold[PNE](start) { _.head._2.src }
      val visited = fatpath.map{_.head}.map{ case (f,t) => f.src }
      if (!visited.contains(pne)) {
        val os = io(pne).outs.sortWith{ case (o1, o2) => o1.src.isInstanceOf[PCU] || !o2.src.isInstanceOf[PCU] }
        val edges = os.flatMap { out => out.fanOuts.map { in => (out, in) } }
        val bundle = edges.groupBy { case (o, i) => (o.src, i.src) }
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

  def revAdvance(start:PNE, validCons:(PCL, FatPath[REdge]) => Option[FatPath[REdge]], 
      advanceCons:(PSB, FatPath[REdge]) => Option[FatPath[REdge]]):FatPaths[REdge] = {
    val result = ListBuffer[(PCL, FatPath[REdge])]()
    val fatpaths = Queue[FatPath[REdge]]()
    fatpaths += Nil
    while (fatpaths.size!=0) {
      val fatpath =  fatpaths.dequeue
      //fatpath.reduce { case ((i1, o1), (i2, o2)) => assert((i1==i2) && (o1==o2)) }
      val pne:PNE = fatpath.lastOption.fold[PNE](start) { _.head._2.src }
      val visited = fatpath.map{_.head}.map{ case (f,t) => f.src }
      if (!visited.contains(pne)) {
        val is = io(pne).ins.sortWith{ case (i1, i2) => i1.src.isInstanceOf[PCU] || !i2.src.isInstanceOf[PCU] }
        val edges = is.flatMap { in => in.fanIns.map { out => (in, out) } }
        val bundle = edges.groupBy { case (i, o) => (i.src, o.src) }
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

  def filterUsedPaths(in:I, out:O, fatpath:FatPath[Edge], map:PIRMap):Option[FatPath[Edge]] = {
      // If out is already placed, use the mapped pout if possible. Otherwise use an unused pout
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

  //def filterUsedFatMaps(in:I, out:O, routes:FatPaths[Edge], map:PIRMap):Paths[Edge] = {
    //val available = routes.flatMap { case (reached, fatpath) =>
      //filterUsedPaths(in, out, fatpath, map).map{ fatpath => (reached, fatpath) }
    //}
    //head(available)
  //}

  def head(fatpaths:FatPaths[Edge]):Paths[Edge] = {
    fatpaths.map { case (reachedCU, fatpath) =>
      (reachedCU, fatpath.map{ fatedge => fatedge.head }) // For any fatpath, pick the first avalable edge
    }
  }

  def isFatPathValid(fatpath:FatPath[Edge]) = { !fatpath.exists{_.size==0} }

  def resFilter(in:I, m:M, triedRes:Paths[Edge]):Paths[Edge] = {
    val cl = ctrler(in)
    val pcl = m.clmap(cl)
    val out = from(in) 
    def validCons(reached:PCL, fatpath:FatPath[Edge]):Option[FatPath[Edge]] = {
      val header = s"validCons(reached:$reached, fatpath:${fatpath.size})"
      var valid = true
      valid = logCond(header, valid, reached == pcl, s"reached:${reached} != pcl:${pcl}")
      valid = logCond(header, valid, fatpath.size >= minHop, s"path ${fatpath.size} less than minHop $minHop")
      valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
      //valid &&= (reached == pcl)
      //valid &&= (fatpath.size >= minHop)
      //valid &&= (fatpath.size < maxHop)
      val filtered = if (valid) filterUsedPaths(in, out, fatpath, m) else None
      valid = logCond(header, valid, filtered.nonEmpty, s"fatpath ${fatpath.size} all used")
      filtered
    }
    def advanceCons(psb:PSB, fatpath:FatPath[Edge]):Option[FatPath[Edge]] = {
      val header = s"advanceCons(psb:$psb fatpath:${fatpath.size})"
      var valid = true
      //valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
      valid &&= (fatpath.size < maxHop)
      val filtered = if (valid) filterUsedPaths(in, out, fatpath, m) else None
      valid = logCond(header, valid, filtered.nonEmpty, s"fatpath ${fatpath.size} all used")
      filtered
    }
    val fcl = ctrler(from(in))
    val pfcl = m.clmap(fcl)
    emitBlock(s"$in resFunc: $in of $cl(${quote(pcl)}) from $out of $fcl(${quote(pfcl)})") {
      val routes = advance(pfcl, validCons _, advanceCons _)
      val remain = routes.diff(triedRes)
      if (remain.isEmpty) {
        dprintln(s"advanced routes:${routes.mkString("\n")}")
        dprintln(s"not tried routes:${remain.mkString("\n")}")
      }
      head(remain)
    }
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

  def debugRouting:Boolean = Config.debug && true

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

  def debugRouting:Boolean = Config.debug && true

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

  def debugRouting:Boolean = Config.debug && true

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
