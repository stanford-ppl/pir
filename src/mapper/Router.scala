package pir.mapper
import pir.graph._
import pir.{Config, Design}
import pir.util.typealias._
import scala.reflect.runtime.universe._
import pir.codegen.{DotCodegen, Printer}
import pir.codegen.{CUCtrlDotPrinter, CUScalarDotPrinter, CUVectorDotPrinter}
import pir.pass.{PIRMapping}
import pir.plasticine.graph.{Node => PNode}
import pir.plasticine.main._
import pir.exceptions._
import scala.language.existentials

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.util.{Failure, Success, Try}

abstract class Router(implicit design:Design) extends Mapper {

  lazy val minHop = 1
  lazy val maxHop = design.arch.diameter
  override val exceptLimit = 100

  type I<:Node
  type O<:Node
  type R = (PCL, Path[FEdge])
  type Edge = (PGIO[PNE], PGIO[PNE])
  type FEdge = (PGO[PNE], PGI[PNE])
  type REdge = (PGI[PNE], PGO[PNE])
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
          case mc:PMC => s"${super.quote(mc)}.${io}"
          case top:PTop => s"${super.quote(top)}.${io}" 
        }
      case n if typeOf[T] =:= typeOf[FEdge] =>
        val (from, to) = n; s"${quote(from)} -> ${quote(to)}"
      case n if typeOf[T] =:= typeOf[REdge] =>
        val (to, from) = n; s"${quote(to)} <- ${quote(from)}"
      case n if typeOf[T] =:= typeOf[FatPath[FEdge]] =>
        n.asInstanceOf[FatPath[FEdge]].map(quote[FatEdge[FEdge]] _).mkString("\n=>\n")
      case n if typeOf[T] =:= typeOf[FatPath[REdge]] =>
        n.asInstanceOf[FatPath[REdge]].map(quote[FatEdge[REdge]] _).mkString("\n<=\n")
      case n if typeOf[T] =:= typeOf[FatEdge[FEdge]] =>
        n.asInstanceOf[FatEdge[FEdge]].map(quote[FEdge]).mkString(" | ")
      case n if typeOf[T] =:= typeOf[FatEdge[REdge]] =>
        n.asInstanceOf[FatEdge[REdge]].map(quote[REdge]).mkString(" | ")
      case n if typeOf[T] =:= typeOf[Path[FEdge]] =>
        n.asInstanceOf[Path[FEdge]].map(quote[FEdge]).mkString(", ")
      case n if typeOf[T] =:= typeOf[Path[REdge]] =>
        n.asInstanceOf[Path[REdge]].map(quote[REdge]).mkString(", ")
      case n => 
        super.quote(n)
    }
  }
  //def quote[E<:Edge](fe:FatEdge[E])(implicit ev:TypeTag[E]):String = { fe.map(quote).mkString(" | ") }
  //def quote[T](n:T)(implicit ev:TypeTag[T]):String = n match {
  //}

  def ctrler(io:Node):CL
  def from(in:I):O
  def to(out:O):List[I]
  def ins(cl:CL):List[I]
  def outs(cl:CL):List[O]
  def io(pne:PNE):PGrid[PNE]
  def isExtern(in:I):Boolean = { ctrler(in)!=ctrler(from(in)) }

    // DEBUG
  def breakPoint(mp:M, info:String):Unit = if (debug) {
    pir.util.misc.bp(info)
    //val arch = design.arch.asInstanceOf[SwitchNetwork]
    //val ocu = arch.ocuArray(0)(4)
    //ocu.ctrlIO.ins.foreach { pin =>
      //println(s"$ocu $pin -> ${mp.vimap.pmap.get(pin)}")
    //}
    this match {
      case router:VectorRouter =>
        new CUVectorDotPrinter(true)(design).print(Some(mp))
      case router:ScalarRouter =>
        new CUScalarDotPrinter(true)(design).print(Some(mp))
      case router:ControlRouter =>
        new CUCtrlDotPrinter(true)(design).print(Some(mp))
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
    //if (valid && !cond) {
      //dprintln(header, s"not passed : $info")
    //}
    valid && cond
  }

  def filterTraverse[E<:Edge](start: I => CL, inputs:List[I], reses:List[PCL], m:PIRMap, advanceFunc: AdvanceFunc[E]):List[PCL] = {
    inputs.foldLeft(reses) { case (reses, in) =>
      val scl = start(in)
      val spcu = m.clmap(scl)
      def validCons(reached:PCL, fatpath:FatPath[E]):Option[FatPath[E]] = {
        var header = s"validCons(in:$in($scl) start:${quote(spcu)} reached:${quote(reached)}, fatpath:${fatpath.size})"
        header += s" minHop:$minHop maxHop:$maxHop"
        var valid = true
        valid = logCond(header, valid, reses.contains(reached), s"invalid res")
        valid = logCond(header, valid, !m.clmap.pmap.contains(reached), s"res $reached is used ")
        if (valid) Some(fatpath) else None
      }
      advanceFunc(m.clmap(start(in)), Some(validCons _), None, None, minHop, maxHop).map { _._1 }.toSet.toList
    }
  }

  def filterOutIns(cl:CL, pnes:List[PCL], m:PIRMap):List[PCL] = {
    val outins:List[I] = outs(cl).flatMap { out =>
      to(out).filter { in => 
        !m.vimap.contains(in) && m.clmap.contains(ctrler(in))
      }
    }
    def start(in:I) = ctrler(in)
    val reses = filterTraverse(start _, outins, pnes, m, revAdvance _)
    if (reses.isEmpty) 
      throw MappingException(this, m, s"No pnes can route outins of $cl. ins:${outins} from:${outins.map(in => from(in))}")
    else reses
  }

  def filterIns(cl:CL, pnes:List[PCL], m:PIRMap):List[PCL] = {
    val inputs:List[I] = ins(cl).filter { in =>
      !m.vimap.contains(in) && m.clmap.contains(ctrler(from(in)))
    }
    def start(in:I) = ctrler(from(in))
    val reses = filterTraverse(start _, inputs, pnes, m, fwdAdvance _)
    if (reses.isEmpty) 
      throw MappingException(this, m, s"No pnes can route inputs of $cl. ins:${inputs} to ${inputs.map(in => from(in))}")
    else reses
  }

  def filterPCL(cl:CL, pnes:List[PCL], m:PIRMap):List[PCL] = {
    var reses = pnes 
    if (reses.isEmpty)
      throw MappingException(this, m, s"No pnes to filter for $cl")
    reses = log((s"filterOutIns", true)) { filterOutIns(cl, reses, m) }
    reses = log((s"filterIns", true)) { filterIns(cl, reses, m) }
    reses
  }

  type ValidCons[E<:Edge] = (PCL, FatPath[E]) => Option[FatPath[E]]
  type AdvanceCons[E<:Edge] = (PSB, FatPath[E]) => Option[FatPath[E]]
  //type AdvanceFunc[E<:Edge] = (PNE, ValidCons[E], AdvanceCons[E]) =>FatPaths[E] 
  type AdvanceFunc[E<:Edge] = (PCL, Option[ValidCons[E]], Option[AdvanceCons[E]], Option[PNE], Int, Int) => FatPaths[E] 

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

  def fwdAdvance(
      start:PCL, 
      validCons:Option[ValidCons[FEdge]] = None, 
      advanceCons:Option[AdvanceCons[FEdge]] = None,
      reached:Option[PNE] = None,
      minHop:Int = this.minHop, 
      maxHop:Int = this.maxHop
    ):FatPaths[FEdge] = {
    advance[FEdge]((io:PGrid[PNE]) => io.outs)(start, vldCons(validCons, reached, minHop, maxHop) _, advCons(advanceCons, maxHop) _)
  }

  def revAdvance(
      start:PCL, 
      validCons:Option[ValidCons[REdge]] = None, 
      advanceCons:Option[AdvanceCons[REdge]] = None,
      reached:Option[PNE] = None,
      minHop:Int = this.minHop, 
      maxHop:Int = this.maxHop
    ):FatPaths[REdge] = {
    advance[REdge]((io:PGrid[PNE]) => io.ins)(start, vldCons(validCons, reached, minHop, maxHop) _, advCons(advanceCons, maxHop) _)
  }

  def tailToHeads(io:PIO[PNE]):List[PIO[PNE]] = io match {
    case io:PI[_] => io.fanIns.asInstanceOf[List[PO[PNE]]]
    case io:PO[_] => io.fanOuts.asInstanceOf[List[PI[PNE]]]
  }

  def advance[E<:Edge](edgeTails:PGrid[PNE] => List[PIO[PNE]])(
      start:PCL, 
      validCons:ValidCons[E], 
      advanceCons:AdvanceCons[E]
    ):FatPaths[E] = {
    val result = ListBuffer[(PCL, FatPath[E])]()
    val fatpaths = Queue[FatPath[E]]()
    val visited = ListBuffer[PNE]()
    fatpaths += Nil
    while (fatpaths.size!=0) {
      val fatpath =  fatpaths.dequeue
      //fatpath.reduce { case ((i1, o1), (i2, o2)) => assert((i1==i2) && (o1==o2)) }
      val pne:PNE = fatpath.lastOption.fold[PNE](start) { _.head._2.src }
      if (!visited.contains(pne)) {
        visited += pne
        val ets = edgeTails(io(pne)).sortWith{ case (et1, et2) => et1.src.isInstanceOf[PCU] || !et2.src.isInstanceOf[PCU] }
        val edges = ets.flatMap { et => tailToHeads(et).map { eh => (et, eh).asInstanceOf[E] } }
        val bundle = edges.groupBy { case (et, eh) => (et.src, eh.src) }
        bundle.foreach { 
          case ((fpne, tpne), fatEdge) if !visited.contains(tpne) =>
            val newPath = fatpath :+ fatEdge 
            tpne match {
              case cl:PCL => 
                validCons(cl, newPath).foreach { newPath => result += (cl -> newPath) }
              case sb:PSB =>
                advanceCons(sb, newPath).foreach { newPath => fatpaths += newPath }
              case _ =>
            }
          case ((fpne, tpne), fatEdge) =>
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
          val edgeTaken = map.fimap.get(pi).fold(false) { _ != po }
          val switchTaken = {
            if (po.src.isInstanceOf[PSB]) {  // Check switch box
              map.fimap.contains(po.ic) // Conservative here
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
      val routes = fwdAdvance(
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
        var mp = m
        val (reachedCU, path) = r
        val pin = path.last._2
        val pout = path.head._1
        sameSrcMap(ctrler(n))(from(n)).foreach { n =>
          mp = mp.setVI(n, pin).setVO(from(n), pout).setRT(n, path.size)
          n match {
            case n:VI => mp = mp.setOP(n.out, pin.ic)
            case n:SI => mp = mp.setOP(n.out, pin.ic)
            case n =>
          }
          //One from(n) may map to multiple pout. but ipmap is not a one to many map
          //from(n) match {
            //case f:VO => mp = mp.setIP(f.in, pout.ic)
            //case f:SO => mp = mp.setIP(f.in, pout.ic)
            //case n =>
          //}
        }
        path.zipWithIndex.foreach { case ((out, in), i) => 
          mp = mp.setFI(in, out)
          if (out.src.isInstanceOf[PSB]) { // Config SwitchBox
            val to = out
            val from = path(i-1)._2
            mp = mp.setFI(to.ic, from.ic)
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

  def io(pne:PNE):PGrid[PNE] = pne.vectorIO

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

  def io(pne:PNE):PGrid[PNE] = pne.scalarIO

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

  def io(pne:PNE):PGrid[PNE] = pne.ctrlIO

  def ctrler(io:Node):CL = {
    io match {
      case in:I => in.src.asInstanceOf[PRIM].ctrler
      case out:O => out.src.asInstanceOf[PRIM].ctrler
    }
  }
  def from(in:I):O = in.from
  def to(out:O) = out.to.toList
  def ins(cl:CL):List[I] = cl.cins
  def outs(cl:CL):List[O] = cl.couts
}
