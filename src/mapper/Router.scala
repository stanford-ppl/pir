package pir.mapper
import pir.graph._
import pir.misc._
import pir.{Config, Design}
import pir.typealias._
import scala.reflect.runtime.universe._
import pir.codegen.{DotCodegen, Printer}
import pir.graph.traversal.{CUCtrlDotPrinter, CUScalarDotPrinter, CUVectorDotPrinter, MapPrinter, PIRMapping}
import pir.plasticine.graph.{Node => PNode}
import pir.plasticine.main._
import pir.util._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.util.{Failure, Success, Try}

abstract class Router(implicit design:Design) extends Mapper {
  implicit def spade:Spade = design.arch

  val minHop = 1
  val maxHop = design.arch.diameter
  override val exceptLimit = 100

  type I<:Node
  type O<:Node
  type R = (PCL, Path[FEdge])
  type Edge = (PIO[PNE], PIO[PNE])
  type FEdge = (POB, PIB)
  type REdge = (PIB, POB)
  type Path[E<:Edge] = List[E]
  type Paths[E<:Edge] = List[(PCL, Path[E])]
  type FatEdge[E<:Edge] = List[E] 
  type FatPaths[E<:Edge] = List[(PCL, FatPath[E])]
  type FatPath[E<:Edge] = List[FatEdge[E]]

  def quote[T](n:T)(implicit spade:Spade, ev:TypeTag[T]):String = {
    n match {
      case io:PIO[_] =>
        io.src match {
          case cu:PCU => s"${super.quote(cu)}.${io}"
          case sb:PSB => s"${super.quote(sb)}.${io}" 
          case top:PTop => s"${super.quote(top)}.${io}" 
        }
      case edge if typeOf[T] =:= typeOf[FEdge] =>
        val (from, to) = edge; s"${quote(from)} -> ${quote(to)}"
      case edge if typeOf[T] =:= typeOf[REdge] =>
        val (to, from) = edge; s"${quote(to)} <- ${quote(from)}"
      case n => super.quote(n)
    }
  }
  def quote(fe:FatEdge[_]):String = { fe.map(quote).mkString(" | ") }
  def quote[E<:Edge](path:FatPath[E])(implicit ev:TypeTag[E]):String = path match {
    case path:FatPath[_] if typeOf[E] =:= typeOf[FEdge] =>
      path.map(quote _).mkString("\n=>\n")
    case path:FatPath[_] if typeOf[E] =:= typeOf[REdge] =>
      path.map(quote _).mkString("\n<=\n")
    case path:Path[_] =>
      path.map(quote).mkString(", ")
  }

  def ctrler(io:Node):CL
  def from(in:I):O
  def to(out:O):List[I]
  def ins(cl:CL):List[I]
  def outs(cl:CL):List[O]
  def io(pne:PNE):PGIO[PNE]
  def isExtern(in:I):Boolean = { ctrler(in)!=ctrler(from(in)) }

    // DEBUG
  def breakPoint(mp:M, info:String):Unit = if (debug) {
    bp(info)
    //val arch = design.arch.asInstanceOf[SwitchNetwork]
    //val ocu = arch.ocuArray(0)(4)
    //ocu.ctrlIO.ins.foreach { pin =>
      //println(s"$ocu $pin -> ${mp.vimap.pmap.get(pin)}")
    //}
    this match {
      case router:VectorRouter =>
        new CUVectorDotPrinter(true)(design).print(mp.asInstanceOf[M])
      case router:ScalarRouter =>
        new CUScalarDotPrinter(true)(design).print(mp.asInstanceOf[M])
      case router:ControlRouter =>
        new CUCtrlDotPrinter(true)(design).print(mp.asInstanceOf[M])
    }
  }
  def failPass(e:Throwable):Unit = if (debug) {
    e match {
      case e:MappingException[_] =>
        breakPoint(e.mapping.asInstanceOf[PIRMap], s"$e")
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

  def filterTraverse[E<:Edge](start: I => CL, inputs:List[I], reses:List[PNE], m:PIRMap, advanceFunc: AdvanceFunc[E]) = {
    inputs.foldLeft(reses) { case (reses, in) =>
      def validCons(reached:PCL, fatpath:FatPath[E]):Option[FatPath[E]] = {
        val header = s"validCons(reached:$reached, fatpath:${fatpath.size})"
        var valid = true
        valid = logCond(header, valid, reses.contains(reached), s"invalid res")
        valid = logCond(header, valid, !m.clmap.pmap.contains(reached), s"res $reached is used ")
        if (valid) Some(fatpath) else None
      }
      advanceFunc(m.clmap(start(in)), Some(validCons _), None, None, minHop, maxHop).map { _._1 }
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
    reses = log((s"filterOutIns", true)) { filterOutIns(cl, reses, m) }
    reses = log((s"filterIns", true)) { filterIns(cl, reses, m) }
    reses
  }

  type ValidCons[E<:Edge] = (PCL, FatPath[E]) => Option[FatPath[E]]
  type AdvanceCons[E<:Edge] = (PSB, FatPath[E]) => Option[FatPath[E]]
  //type AdvanceFunc[E<:Edge] = (PNE, ValidCons[E], AdvanceCons[E]) =>FatPaths[E] 
  type AdvanceFunc[E<:Edge] = (PNE, Option[ValidCons[E]], Option[AdvanceCons[E]], Option[PNE], Int, Int) => FatPaths[E] 

  def vldCons[E<:Edge](validCons:Option[ValidCons[E]], reached:Option[PNE], minHop:Int, maxHop:Int)
             (reachedCU:PCL, fatpath:FatPath[E]):Option[FatPath[E]] = {
    val header = s"validCons(reached:$reached, fatpath:${fatpath.size})"
    var valid = true
    //valid = logCond(header, valid, fatpath.size >= minHop, s"path ${fatpath.size} less than minHop $minHop")
    //valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
    //valid = logCond(header, valid, reached == pcl, s"reached:${reached} != pcl:${pcl}")
    valid &&= (fatpath.size >= minHop)
    valid &&= (fatpath.size < maxHop)
    reached.foreach { reached => valid &&= (reached == reachedCU) }
    if (valid) { validCons.fold[Option[FatPath[E]]](Some(fatpath)){ vc => vc(reachedCU, fatpath) } } else None
  }

  def advCons[E<:Edge](advanceCons:Option[AdvanceCons[E]], maxHop:Int)
             (psb:PSB, fatpath:FatPath[E]):Option[FatPath[E]] = {
    val header = s"advanceCons(psb:$psb fatpath:${fatpath.size})"
    var valid = true
    //valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
    valid &&= (fatpath.size < maxHop)
    if (valid) { advanceCons.fold[Option[FatPath[E]]](Some(fatpath)){ av => av(psb, fatpath) } } else None
  }

  def advance(
      start:PNE, 
      validCons:Option[ValidCons[FEdge]] = None, 
      advanceCons:Option[AdvanceCons[FEdge]] = None,
      reached:Option[PNE] = None,
      minHop:Int = this.minHop, 
      maxHop:Int = this.maxHop
    ):FatPaths[FEdge] = {
    advance(start, vldCons(validCons, reached, minHop, maxHop) _, advCons(advanceCons, maxHop) _)
  }

  def advance(
      start:PNE, 
      validCons:ValidCons[FEdge], 
      advanceCons:AdvanceCons[FEdge]
    ):FatPaths[FEdge] = {
    //tic
    val routes = advanceBFS(start, validCons, advanceCons)
    //toc("advance", "ms")
    routes
  }

  def advanceBFS(start:PNE, validCons:ValidCons[FEdge], 
      advanceCons:AdvanceCons[FEdge]):FatPaths[FEdge] = {
    val result = ListBuffer[(PCL, FatPath[FEdge])]()
    val fatpaths = Queue[FatPath[FEdge]]()
    val visited = ListBuffer[PNE]()
    fatpaths += Nil
    while (fatpaths.size!=0) {
      val fatpath =  fatpaths.dequeue
      //fatpath.reduce { case ((i1, o1), (i2, o2)) => assert((i1==i2) && (o1==o2)) }
      val pne:PNE = fatpath.lastOption.fold[PNE](start) { _.head._2.src }
      if (!visited.contains(pne)) {
        visited += pne
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

  def revAdvance(
      start:PNE, 
      validCons:Option[ValidCons[REdge]] = None, 
      advanceCons:Option[AdvanceCons[REdge]] = None,
      reached:Option[PNE] = None,
      minHop:Int = this.minHop, 
      maxHop:Int = this.maxHop
    ):FatPaths[REdge] = {
    revAdvance(start, vldCons(validCons, reached, minHop, maxHop) _, advCons(advanceCons, maxHop) _)
  }

  def revAdvance(start:PNE, validCons:ValidCons[REdge], 
      advanceCons:AdvanceCons[REdge]):FatPaths[REdge] = {
    val result = ListBuffer[(PCL, FatPath[REdge])]()
    val fatpaths = Queue[FatPath[REdge]]()
    val visited = ListBuffer[PNE]()
    fatpaths += Nil
    while (fatpaths.size!=0) {
      val fatpath =  fatpaths.dequeue
      //fatpath.reduce { case ((i1, o1), (i2, o2)) => assert((i1==i2) && (o1==o2)) }
      val pne:PNE = fatpath.lastOption.fold[PNE](start) { _.head._2.src }
      if (!visited.contains(pne)) {
        visited += pne
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

  def filterUsedPaths(in:I, out:O, fatpath:FatPath[FEdge], map:PIRMap):Option[FatPath[FEdge]] = {
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
      if (isFatPathValid(filteredFatpath)) Some(filteredFatpath) else {
        dprintln(s"fatpath:\n${quote(fatpath)}")
        dprintln(s"filteredFathpath:\n${quote(filteredFatpath)}")
        None
      }
  }

  //def filterUsedFatMaps(in:I, out:O, routes:FatPaths[FEdge], map:PIRMap):Paths[FEdge] = {
    //val available = routes.flatMap { case (reached, fatpath) =>
      //filterUsedPaths(in, out, fatpath, map).map{ fatpath => (reached, fatpath) }
    //}
    //head(available)
  //}

  def head(fatpaths:FatPaths[FEdge]):Paths[FEdge] = {
    fatpaths.map { case (reachedCU, fatpath) =>
      (reachedCU, fatpath.map{ fatedge => fatedge.head }) // For any fatpath, pick the first avalable edge
    }
  }

  def isFatPathValid(fatpath:FatPath[FEdge]) = { !fatpath.exists{_.size==0} }

  def resFilter(in:I, m:M, triedRes:Paths[FEdge]):Paths[FEdge] = {
    val cl = ctrler(in)
    val pcl = m.clmap(cl)
    val out = from(in) 
    val fcl = ctrler(from(in))
    val pfcl = m.clmap(fcl)
    def validCons(reached:PCL, fatpath:FatPath[FEdge]):Option[FatPath[FEdge]] = {
      val header = s"validCons(reached:$reached, fatpath:${fatpath.size})"
      var valid = true
      val filtered = if (valid) filterUsedPaths(in, out, fatpath, m) else None
      valid = logCond(header, valid, filtered.nonEmpty, s"fatpath ${fatpath.size} all used")
      filtered
    }
    def advanceCons(psb:PSB, fatpath:FatPath[FEdge]):Option[FatPath[FEdge]] = {
      val header = s"advanceCons(psb:$psb fatpath:${fatpath.size})"
      var valid = true
      val filtered = if (valid) filterUsedPaths(in, out, fatpath, m) else None
      valid = logCond(header, valid, filtered.nonEmpty, s"fatpath ${fatpath.size} all used")
      filtered
    }
    log((s"$in resFunc", true), (r:Paths[FEdge]) => (), failPass) {
      val routes = advance(
        start=pfcl, 
        validCons=Some(validCons _), 
        advanceCons=Some(advanceCons _),
        reached=Some(pcl), 
        minHop=minHop,
        maxHop=maxHop
      )
      val remain = routes.diff(triedRes)
      if (remain.isEmpty) {
        dprintln(s"advanced routes:${routes.mkString("\n")}")
        dprintln(s"not tried routes:${remain.mkString("\n")}")
        throw MappingException(this, m, s"No route available for $in of $cl(${quote(pcl)}) from $out of $fcl(${quote(pfcl)})")
      }
      head(remain)
    }
    //val froutes = filterUsedFatMaps(in, out, routes, m)
    //if (froutes.isEmpty) { breakPoint(m, s"resFunc: $in of $fcl -> routes:${routes.size} froutes:${froutes.size}") }
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

    log((info, false), ((m:M) => ()), failPass) {
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
        isExtern(in) && !m.vimap.contains(in) && m.clmap.contains(ctrler(in))
      }
    }
    dprintln(s"$cl outs:${outs(cl).mkString(",")} outIns:${outins.mkString(",")}")
    var mp = mapIns(s"Routing outIns of $cl")(outins, m)
    val inputs = ins(cl).filter { in =>
      !m.vimap.contains(in) && m.clmap.contains(ctrler(from(in)))
    }
    dprintln(s"$cl ins:${ins(cl).mkString(",")} inputs:${inputs.mkString(",")}")
    //if (cl.id==421) breakPoint(mp, s"$cl(${quote(pcl)}) inputs:$inputs")
    mp = mapIns(s"Routing ins of $cl")(inputs, mp)
    mp
  }

  override def mappingCheck(mapping:M) = {
    design.top.ctrlers.foreach { ctrler =>
      ins(ctrler).foreach { in =>
        if(!mapping.vimap.contains(in))
          throw PIRException(s"${in} in ${ctrler} from ${from(in)} wasn't mapped")
      }
      outs(ctrler).foreach { out =>
        if(!mapping.vomap.contains(out))
          throw PIRException(s"${out} in ${ctrler} to [${to(out).mkString(",")}] wasn't mapped")
      }
    }
  }
}

class VectorRouter()(implicit val design:Design) extends Router {
  override val typeStr = "VecRouter"

  type I = VI
  type O = VO

  override def debug:Boolean = Config.debugVecRouter

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

  override def debug:Boolean = Config.debugScalRouter 

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

  override def debug:Boolean = Config.debugCtrlRouter

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
