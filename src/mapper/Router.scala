package pir.mapper
import pir.graph._
import pir.{Config, Design}
import pir.util.typealias._
import scala.reflect.runtime.universe._
import pir.codegen.{DotCodegen, Printer}
import pir.pass.{PIRMapping}
import pir.spade.graph.{Node => PNode}
import pir.spade.main._
import pir.exceptions._
import scala.language.existentials

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.util.{Failure, Success, Try}

abstract class Router(implicit design:Design) extends Mapper {
  import spademeta._

  lazy val minHop = 1
  lazy val maxHop = design.arch.diameter
  override val exceptLimit = 200

  type I<:Node
  type O<:Node
  type R = (PCL, Path[FEdge])
  type Edge = (PGIO[PRT], PGIO[PRT])
  type FEdge = (PGO[PRT], PGI[PRT])
  type REdge = (PGI[PRT], PGO[PRT])
  type Path[E<:Edge] = List[E]
  type Paths[E<:Edge] = List[(PCL, Path[E])]
  type FatEdge[E<:Edge] = List[E] 
  type FatPaths[E<:Edge] = List[(PCL, FatPath[E])]
  type FatPath[E<:Edge] = List[FatEdge[E]]
  type ValidCons[E<:Edge] = (PCL, FatPath[E]) => Option[FatPath[E]]
  type AdvanceCons[E<:Edge] = (PSB, FatPath[E]) => Option[FatPath[E]]
  type AdvanceFunc[E<:Edge] = (PCL, Option[ValidCons[E]], Option[AdvanceCons[E]], Option[PRT], Int, Int) => FatPaths[E] 

  def quote[T](n:T)(implicit spade:Spade, ev:TypeTag[T]):String = {
    n match {
      case io:PIO[_] => s"${super.quote(io.src)}.${io}" 
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
  def failPass(e:Throwable):Unit = if (debug) {
    e match {
      case e:MappingException[_] =>
        //breakPoint(e.mapping.asInstanceOf[PIRMap], s"$e", true)
      case e:PassThroughException[_] =>
      case e:Throwable =>
        println(e)
    }
  } else {
    (e:Throwable) => ()
  }
  //def quote[E<:Edge](fe:FatEdge[E])(implicit ev:TypeTag[E]):String = { fe.map(quote).mkString(" | ") }
  //def quote[T](n:T)(implicit ev:TypeTag[T]):String = n match {
  //}

  def ctrler(io:Node):CL
  def from(in:I):O
  def to(out:O):List[I]
  def ins(cl:CL):List[I]
  def outs(cl:CL):List[O]
  def io(prt:PRT):PGrid[PRT]
  def isExtern(in:I):Boolean = { ctrler(in)!=ctrler(from(in)) }

  def logCond(header:String, valid:Boolean, cond:Boolean, info:String):Boolean = {
    //if (valid && !cond) dprintln(header, s"not passed : $info")
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
      var fatpaths = advanceFunc(
        spcu, // start
        Some(validCons _), // validCons
        None, // advanceCons
        None, // reached
        minHop, // minHop
        maxHop // maxHop
      ).toSet.toList
      fatpaths = fatpaths.sortBy { case (reached, fatpath) => fatpath.size }
      if (fatpaths.isEmpty) {
        val out = from(in)
        val icl = ctrler(in)
        val ocl = ctrler(out)
        var info = s"No resource filtered for $icl.$in[${m.clmap.get(icl).map(quote)}]"
        info += s" from: $ocl.$out[${m.clmap.get(ocl).map(quote)}]" 
        throw MappingException(m, info)
      }
      fatpaths.map { _._1 }
    }
  }

  def filterOutIns(cl:CL, prts:List[PCL], m:PIRMap):List[PCL] = {
    val outins:List[I] = outs(cl).flatMap { out =>
      to(out).filter { in => 
        !m.vimap.contains(in) && m.clmap.contains(ctrler(in))
      }
    }
    def start(in:I) = ctrler(in)
    filterTraverse(start _, outins, prts, m, revAdvance _)
  }

  def filterIns(cl:CL, prts:List[PCL], m:PIRMap):List[PCL] = {
    val inputs:List[I] = ins(cl).filter { in =>
      !m.vimap.contains(in) && m.clmap.contains(ctrler(from(in)))
    }
    def start(in:I) = ctrler(from(in))
    filterTraverse(start _, inputs, prts, m, fwdAdvance _)
  }

  def filterPCL(cl:CL, prts:List[PCL], m:PIRMap):List[PCL] = {
    var reses = prts 
    if (reses.isEmpty) throw MappingException(m, s"No prts to filter for $cl")
    reses = log((s"filterOutIns", true)) { filterOutIns(cl, reses, m) }
    reses = log((s"filterIns", true)) { filterIns(cl, reses, m) }
    reses
  }

  def vldCons[E<:Edge](validCons:Option[ValidCons[E]], minHop:Int, maxHop:Int)
             (reachedCU:PCL, fatpath:FatPath[E]):Option[FatPath[E]] = {
    val header = s"validCons(reached:$reachedCU, fatpath:${fatpath.size})"
    var valid = true
    valid = logCond(header, valid, fatpath.size >= minHop, s"path ${fatpath.size} less than minHop $minHop")
    valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
    if (valid) { validCons.fold[Option[FatPath[E]]](Some(fatpath)) { vc => vc(reachedCU, fatpath) } } else None
  }

  def advCons[E<:Edge](advanceCons:Option[AdvanceCons[E]], maxHop:Int)
             (psb:PSB, fatpath:FatPath[E]):Option[FatPath[E]] = {
    val header = s"advanceCons(psb:$psb fatpath:${fatpath.size})"
    var valid = true
    valid = logCond(header, valid, fatpath.size < maxHop, s"path ${fatpath.size} more than maxHop $maxHop")
    if (valid) { advanceCons.fold[Option[FatPath[E]]](Some(fatpath)){ av => av(psb, fatpath) } } else None
  }

  def fwdAdvance(
      start:PCL, 
      validCons:Option[ValidCons[FEdge]] = None, 
      advanceCons:Option[AdvanceCons[FEdge]] = None,
      end:Option[PRT] = None,
      minHop:Int = this.minHop, 
      maxHop:Int = this.maxHop
    ):FatPaths[FEdge] = {
    advance[FEdge]((io:PGrid[PRT]) => io.outs)(
      start=start, 
      end=end,
      validCons=vldCons(validCons, minHop, maxHop) _, 
      advanceCons=advCons(advanceCons, maxHop) _
    )
  }

  def revAdvance(
      start:PCL, 
      validCons:Option[ValidCons[REdge]] = None, 
      advanceCons:Option[AdvanceCons[REdge]] = None,
      end:Option[PRT] = None,
      minHop:Int = this.minHop, 
      maxHop:Int = this.maxHop
    ):FatPaths[REdge] = {
    advance[REdge]((io:PGrid[PRT]) => io.ins)(
      start=start, 
      end=end,
      validCons=vldCons(validCons, minHop, maxHop) _, 
      advanceCons=advCons(advanceCons, maxHop) _
    )
  }

  def tailToHeads(io:PIO[PRT]):List[PIO[PRT]] = io match {
    case io:PI[_] => io.fanIns.asInstanceOf[List[PO[PRT]]]
    case io:PO[_] => io.fanOuts.asInstanceOf[List[PI[PRT]]]
  }

  def advance[E<:Edge](edgeTails:PGrid[PRT] => List[PIO[PRT]])(
      start:PCL, 
      end:Option[PRT],
      validCons:ValidCons[E], 
      advanceCons:AdvanceCons[E]
    ):FatPaths[E] = {
    val result = ListBuffer[(PCL, FatPath[E])]()
    val fatpaths = Queue[(List[PSB], FatPath[E])]()
    fatpaths += ((Nil, Nil))
    val thresh = 2
    def shouldVisit(visited:List[PSB], psb:PSB):Boolean = { // reduce exponential space to quadratic space
      if (visited.contains(psb)) return false
      val (cx,cy) = coordOf(psb)
      var valid = true 
      if (visited.nonEmpty) {
        val coordXs = visited.map( vpsb => coordOf(vpsb)._1 )
        val coordYs = visited.map( vpsb => coordOf(vpsb)._2 )
        (start, end) match {
          case (start, None) =>
            valid &= ((cx <= coordXs.min) || (cx >= coordXs.max))
            valid &= ((cy <= coordYs.min) || (cy >= coordYs.max))
          case (start, Some(end:PTop)) =>
            valid &= ((cx <= coordXs.min) || (cx >= coordXs.max))
            valid &= ((cy <= coordYs.min) || (cy >= coordYs.max))
          case (start, Some(end)) =>
            val (ex, ey) = coordOf(end)
            if ((Math.abs(ex-cx) <= thresh) && (Math.abs(ey - cy) <= thresh)) {
              valid &= ((cx <= coordXs.min + thresh) || (cx >= coordXs.max - thresh))
              valid &= ((cy <= coordYs.min + thresh) || (cy >= coordYs.max - thresh))
            } else {
              valid &= ((cx <= coordXs.min) || (cx >= coordXs.max))
              valid &= ((cy <= coordYs.min) || (cy >= coordYs.max))
              if (!start.isTop) {
                val (sx,sy) = coordOf(start)
                valid &= ( ((ex >= sx) && (cx >= sx)) || ((ex <= sx) && (cx <= sx)) )
              }
            }
        }
      }
      valid
    }
    val totalVisit = mutable.Map[PRT, Int]() // reduce quadratic space 
    while (fatpaths.size!=0) {
      val (visited, fatpath) =  fatpaths.dequeue
      val prt:PRT = fatpath.lastOption.fold[PRT](start) { _.head._2.src }
      val ets = edgeTails(io(prt)).sortWith{ case (et1, et2) => et1.src.isInstanceOf[PCU] || !et2.src.isInstanceOf[PCU] }
      val edges = ets.flatMap { et => tailToHeads(et).map { eh => (et, eh).asInstanceOf[E] } }
      val bundle = edges.groupBy { case (et, eh) => (et.src, eh.src) }
      bundle.foreach { case ((fprt, tprt), fatEdge) =>
        //breakPoint(s"start=${quote(start)} end=${end.map(quote)} visited=[${visited.map(quote)}] tprt=${quote(tprt)}", true)
        val tvisit = totalVisit.getOrElse(tprt, 0)
        //tprt match {
          //case psb:PSB => 
            //dprintln(s"start=${quote(start)} end=${end.map(quote)} visited=[${visited.map(quote)}] tprt=${quote(tprt)} shouldVisit:${shouldVisit(visited, psb)} ${tvisit}")
          //case pcl:PCL => 
            //dprintln(s"start=${quote(start)} end=${end.map(quote)} visited=[${visited.map(quote)}] tprt=${quote(tprt)} ${end} ${tvisit}")
        //}
        tprt match {
          case psb:PSB if shouldVisit(visited, psb) & (tvisit < 3) =>
            val newPath = fatpath :+ fatEdge 
            advanceCons(psb, newPath).foreach { newPath => 
              totalVisit += tprt -> (tvisit + 1)
              fatpaths += ((visited :+ psb, newPath))
            }
          case pcl:PCL if end.fold(true) { _ == pcl } & (tvisit < 3) => 
            val newPath = fatpath :+ fatEdge 
            validCons(pcl, newPath).foreach { newPath => 
              totalVisit += tprt -> (tvisit + 1)
              result += (pcl -> newPath)
            }
          case _ =>
        }
      }
    }
    result.toList
  }

  // Removing edges that are used
  def filterUsedFatEdge(out:O, fatedge:FatEdge[FEdge], map:PIRMap):Option[FatEdge[FEdge]] = {
    val filtered = fatedge.filter { e => 
      val edge:FEdge = e
      val (po, pi) = edge
      val pomk = map.mkmap.get(po)
      val pimk = map.mkmap.get(pi)
      val edgeMatch = pomk.fold(true) { _ == out } && pimk.fold(true) { _ == out }
      dprintln(!edgeMatch, s"${quote(edge)} not match outMarker=${pomk} inMarker=${pimk}")
      val ins = map.vimap.pmap.get(pi)
      val inputSrcMatch = ins.fold(true) { ins => from(ins.head.asInstanceOf[I]) == out }
      dprintln(!inputSrcMatch, s"${quote(edge)} not match input source vimap=${ins}")
      val edgeTaken = map.fimap.get(pi).fold(false) { _ != po }
      dprintln(edgeTaken, s"${quote(edge)} edge is taken")
      // check whether marker of the switch is the same
      val switchTaken = map.fimap.get(po.ic).fold(false) { piic =>
        map.mkmap.get(piic.src.asInstanceOf[PGI[PModule]]).fold(false) { _ == out }
      }
      dprintln(switchTaken, s"$po's switch is taken ${pi}")
      edgeMatch && inputSrcMatch && !edgeTaken && !switchTaken
    }
    if (filtered.isEmpty) None else Some(filtered)
  }

  def filterUsedPaths(out:O, fatpath:FatPath[FEdge], map:PIRMap):Option[FatPath[FEdge]] = {
    // If out is already placed, use the mapped pout if possible. Otherwise use an unused pout
    //val pouts = map.vomap.get(out)
    val fp = fatpath.map { fatedge => // Find fatpath that has empty fatEdge after filter
      val fe = filterUsedFatEdge(out, fatedge, map)
      if (fe.isEmpty) return None
      fe.get
    }
    Some(fp)
  }

  // Not generalized to optimized switch taken
  //def slimDown(fatpath:FatPath[FEdge], mp):Path[FEdge] = {
  //  fatpath.map{ fatedge => fatedge.head } // For any fatpath, pick the first avalable edge
  //}

  // Too Slow for control network with extremely fatpath
  //def slimDown(fatpath:FatPath[FEdge], mp:PIRMap):Option[Path[FEdge]] = log("slimDown") {
    //val hfedge::rfedges = fatpath
    //val paths = ListBuffer[Path[FEdge]]()
    //paths ++= hfedge.map(e => List(e))
    //rfedges.map { fedge =>
      //val prepaths = paths.toList
      //paths.clear
      //prepaths.foreach { p =>
        //val (prevo, previ) = p.last
        //fedge.foreach { case e@(curo, curi) =>
          //if (mp.fimap.get(curo.ic).fold(true) { _ == previ.ic }) {
            //paths += p :+ e 
          //}
        //}
      //}
    //}
    //paths.headOption
  //}

  // Faster version. No need to find all posibility before return. Find one directly return
  //def slimDown(fatpath:FatPath[FEdge], mp:PIRMap):Option[Path[FEdge]] = log("slimDown") {
    //val length = fatpath.size 
    //def forward(cure:FEdge, i:Int, path:Path[FEdge]):Option[Path[FEdge]] = {
      //if (i==length-1) return Some(path :+ cure)
      //val (curo, curi) = cure
      //val nextfe = fatpath(i+1)
      //nextfe.foreach { nexte =>
        //val (nexto, nexti) = nexte
        //if (mp.fimap.get(nexto.ic).fold(true) { _ == curi.ic } ) {
          //forward(nexte, i+1, path :+ cure).foreach { path => return Some(path) }
        //}
      //}
      //return None
    //}
    //fatpath.head.foreach { cure =>
      //forward(cure, 0, Nil).foreach { path => return Some(path) }
    //}
    //return None
  //}

  // More optimized version. Prioritize reuse
  def slimDown(fatpath:FatPath[FEdge], mp:PIRMap):Option[Path[FEdge]] = {
    val length = fatpath.size 
    def forward(cure:FEdge, i:Int, path:Path[FEdge]):Option[Path[FEdge]] = {
      if (i==length-1) return Some(path :+ cure)
      val (curo, curi) = cure
      val nextfe = fatpath(i+1)
      nextfe.foreach { nexte =>
        val (nexto, nexti) = nexte
        mp.fimap.get(nexto.ic).foreach { iic => 
          if (iic == curi.ic) forward(nexte, i+1, path :+ cure).foreach { path => return Some(path) }
        }
      }
      nextfe.foreach { nexte =>
        val (nexto, nexti) = nexte
        if (!mp.fimap.contains(nexto.ic)) forward(nexte, i+1, path :+ cure).foreach { path => return Some(path) }
      }
      return None
    }
    fatpath.head.foreach { cure =>
      forward(cure, 0, Nil).foreach { path => return Some(path) }
    }
    return None
  }

  def slimDown(fatpaths:FatPaths[FEdge], mp:M):Paths[FEdge] = {
    val paths:Paths[FEdge] = fatpaths.flatMap { case (reachedCU, fatpath) =>
      slimDown(fatpath, mp).map{ path => (reachedCU, path) }
    }
    if (paths.isEmpty) throw MappingException(mp, s"No valid connected path available")
    else paths
  }

  def isFatPathValid(fatpath:FatPath[FEdge]) = { !fatpath.exists{_.size==0} }

  def resFilter(in:I, m:M, triedRes:Paths[FEdge]):Paths[FEdge] = {
    val icl = ctrler(in)
    val picl = m.clmap(icl)
    val out = from(in) 
    val ocl = ctrler(out)
    val pocl = m.clmap(ocl)
    def filterUsed(prt:PRT, fatpath:FatPath[FEdge]):Option[FatPath[FEdge]] = {
      val (heads, last) = fatpath.splitAt(fatpath.size-1)
      filterUsedFatEdge(out, last.head, m).map{ last => heads :+ last }
    }
    log((s"$ocl.$out(${quote(pocl)}) -> $icl.$in(${quote(picl)}) resFunc", true), (r:Paths[FEdge]) => (), failPass) {
      val routes = fwdAdvance(
        start=pocl, 
        end=Some(picl), 
        validCons=Some(filterUsed _), 
        advanceCons=Some(filterUsed _),
        minHop=minHop,
        maxHop=maxHop
      )
      if (routes.isEmpty) {
        var info = s"No route available for searching range=[$minHop, $maxHop]\n"
        throw PassThroughException(ReplaceController(List(icl -> picl, ocl -> pocl), m), m)
      }
      //var remain = routes.flatMap { case (pcl, fp) => filterUsedPaths(out, fp, m).map( fp => (pcl, fp)) }
      //remain = remain.diff(triedRes)
      val remain = routes.diff(triedRes)
      if (remain.isEmpty) {
        var info = s"No remaining route available for triedRes.size=${triedRes.size} searching range=[$minHop, $maxHop]\n"
        throw MappingException(m, info)
      }
      slimDown(remain, m)
    }
  }

  def mapIns(info:String, check:Option[M => Unit]=None)(ins:List[I], m:M):M = {

    val sameSrcMap:Map[CL, Map[O, List[I]]] = ins.groupBy{ in => ctrler(in) }.map { case (ctrler, ins) =>
      ctrler -> ins.groupBy { in => from(in) }
    }

    def cons(n:I, r:R, m:M):M = {
      log(s"Try $n -> $r", (m:M) => (), failPass) {
        var mp = m
        val (reachedCU, path) = r
        val pin = path.last._2
        val pout = path.head._1
        val in = n
        val out = from(n)
        sameSrcMap(ctrler(in))(out).foreach { in =>
          mp = mp.setVI(in, pin).setVO(out, pout).setRT(in, path.size).setMK(pout, out).setMK(pin, out)
          in match {
            case in:VI => mp = mp.setOP(in.out, pin.ic)
            case in:SI => mp = mp.setOP(in.out, pin.ic)
            case in =>
          }
          //One from(n) may map to multiple pout. but ipmap is not a one to many map
          //from(n) match {
            //case f:VO => mp = mp.setIP(f.in, pout.ic)
            //case f:SO => mp = mp.setIP(f.in, pout.ic)
            //case n =>
          //}
        }
        path.zipWithIndex.foreach { case ((pout, pin), i) => 
          mp = mp.setFI(pin, pout).setMK(pout, out).setMK(pin, out)
          if (pout.src.isInstanceOf[PSB]) { // Config SwitchBox
            val to = pout
            val from = path(i-1)._2
            mp = mp.setFI(to.ic, from.ic)
          }
        }
        check.foreach { _(mp) }
        mp
      }
    }

    val uniqueIns = sameSrcMap.flatMap { case (ctlrer, srcMap) => 
      srcMap.map { case (out, ins) => ins.head }
    }.toList

    log((info, true), ((m:M) => ()), failPass) {
      bind[R,I,M](
        allNodes=uniqueIns, 
        initMap=m, 
        constrain=cons _, 
        resFunc=resFilter _, 
        finPass= (m:M) => m
      )
    }
  }

  def checkOut(cl:CL, m:M) = {
    val pcl = m.clmap(cl)
    val unmapped = outs(cl).filterNot { out => m.vomap.contains(out) }
    val unused = io(pcl).outs.filterNot { pout => m.vomap.pmap.contains(pout) }
    if (unused.size < unmapped.size) {
      var info = s"Not enough pouts for in ${quote(pcl)} to map $cl\n"
      info += outs(cl).map { out => s"$out -> [${m.vomap.get(out).mkString(",")}]" }.mkString("\n")
      throw MappingException(m, info)
    }
  }

  def route(cl:CL, m:M):M = {
    val pcl = m.clmap(cl)
    // Mapping inputs connecting the outputs
    val outins = outs(cl).flatMap { out =>
      to(out).filter { in => 
        isExtern(in) && !m.vimap.contains(in) && m.clmap.contains(ctrler(in))
      }
    }
    dprintln(s"$cl outs:${outs(cl).mkString(",")} outIns:${outins.mkString(",")}")
    var mp = mapIns(s"Routing outIns of $cl at ${quote(pcl)}", Some(checkOut(cl, _)))(outins, m)

    // Mapping inputs of cl
    val inputs = ins(cl).filter { in =>
      !m.vimap.contains(in) && m.clmap.contains(ctrler(from(in)))
    }
    dprintln(s"$cl ins:${ins(cl).mkString(",")} inputs:${inputs.mkString(",")}")
    mp = mapIns(s"Routing ins of $cl at ${quote(pcl)}")(inputs, mp)

    mp
  }

  override def mappingCheck(mapping:M) = {
    design.top.ctrlers.foreach { ctrler =>
      ins(ctrler).foreach { in =>
        if(!mapping.vimap.contains(in))
          throw PIRException(s"${in} in ${ctrler} from ${from(in)} wasn't mapped")
      }
      outs(ctrler).foreach { out =>
        (out match {
          case vo:VO if vo.readers.size!=0 => Some(out)
          case so:SO if so.readers.size!=0 => Some(out)
          case co:OP if co.to.size!=0 => Some(out)
          case out => None
        }).foreach { out =>
          if(!mapping.vomap.contains(out))
            throw PIRException(s"${out} in ${ctrler} to [${to(out).mkString(",")}] wasn't mapped")
        }
      }
    }
  }
}

class VectorRouter()(implicit val design:Design) extends Router {
  override val typeStr = "VecRouter"

  type I = VI
  type O = VO

  override def debug:Boolean = Config.debugVecRouter

  def io(prt:PRT):PGrid[PRT] = prt.vectorIO

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

  def io(prt:PRT):PGrid[PRT] = prt.scalarIO

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

  def io(prt:PRT):PGrid[PRT] = prt.ctrlIO

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
