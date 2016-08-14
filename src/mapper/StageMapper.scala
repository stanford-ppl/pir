package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Stage => ST, EmptyStage => EST, WAStage => WAST, ReduceStage => RDST, 
                  AccumStage, Const, PipeReg, Reg, Primitive}
import pir.graph.{InPort => IP, OutPort => OP}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{EmptyStage => PEST, FUStage => PFUST, Stage => PST, WAStage => PWAST, 
                            ReduceStage => PRDST, Reg => PReg}
import pir.plasticine.graph.{Const => PConst, InPort => PIP, OutPort => POP, RMPort, Stagable}
import pir.graph.traversal.{PIRMapping, MapPrinter}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

object StageMapper extends Mapper {
  type R = PST
  type N = ST

  val finPass = None

  def map(cu:CU, cuMap:M):M = {
    println(s"stage mapper: ${cu}")
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    val pest :: pfusts = pcu.stages
    val est :: fusts = cu.stages.toList
    var cmap = inordBind(List(pest), List(est), cuMap, List(mapStage _), finPass, OutOfStage(pcu, cu, _, _))
    cmap = Try { //TODO: Currently if fail take a while to finish 
      inordBind(pfusts, fusts, cmap, List(mapStage _), finPass, OutOfStage(pcu, cu, _, _))
    } match  {
      case Success(m) => m
      case Failure(e) => 
        e.printStackTrace
        design.pirMapping.printMap
        System.exit(-1); cmap
    }
    stageFowarding(pcu, cmap)
  }

  /* Forward liveOut regs in pstages that doesn't have stage corresponding to them */
  def stageFowarding(pcu:PCU, cuMap:M):M = {
    var preLiveOuts:Set[Reg] = Set.empty
    var fpmap = cuMap.fpmap
    pcu.stages.foreach { pstage =>
      val ppregs:Set[PReg] = preLiveOuts.map {reg => cuMap.rcmap(reg) }
      if (cuMap.stmap.pmap.contains(pstage)) {
        val stage = cuMap.stmap.pmap(pstage)
        preLiveOuts = stage.liveOuts 
      } else {
        pstage.prs.foreach { case (preg, ppr) =>
          if (ppregs.contains(preg))
            fpmap += ppr.in -> pstage.pre.get.prs(preg).out
        }
      }
    }
    cuMap.set(fpmap)
  }

  def mapStage(n:N, p:R, map:M):M = {
    var cmap = map.setST(n, p)
    n match {
      case s:EST => 
        if (!p.isInstanceOf[PEST]) throw StageRouting(n, p)
        cmap = mapPROut(n, p, cmap)
        cmap = mapPRIn(n, p, cmap)
      case fs => fs match {
          case s:WAST => if (!p.isInstanceOf[PWAST]) throw StageRouting(n, p)
          case s:RDST => if (!p.isInstanceOf[PRDST]) throw StageRouting(n, p)
          case _ => if (!p.isInstanceOf[PFUST]) throw StageRouting(n, p)
        }
        cmap = mapPROut(n, p, cmap)
        cmap = mapFUOut(n, p, cmap)
        cmap = mapPRIn(n, p, cmap)
        cmap = mapFUIn(n, p, cmap)
    }
    cmap
  }

  def mapFUIn(n:ST, p:PST, map:M):M = {
    val fu = n.fu.get
    val pfu = p.asInstanceOf[PFUST].fu
    // Check Operand 
    val oprds = fu.operands
    val poprds = pfu.operands
    val oprdCons = List(mapInPort _)
    simAneal(poprds, oprds, map, oprdCons, None, OutOfOperand(p, n, _, _))
  }

  def mapFUOut(n:ST, p:PST, map:M):M = {
    val fu = n.fu.get
    val pfu = p.asInstanceOf[PFUST].fu
    // Check Operation 
    if (!pfu.ops.contains(fu.op)) throw OpNotSupported(p, n)
    // Check Result 
    mapOutPort(p)(fu.out, pfu.out, map)
  }

  def mapPRIn(stage:ST, pstage:PST, map:M):M = {
    val rcmap = map.rcmap
    stage.prs.foldLeft(map) { case (pmap, (reg, pr)) =>
      println(s"${stage} ${stage.ctrler.sins} ${pr.in.from} ${pr.in.from.src} ${pr.out.to}")
      val preg = rcmap(reg)
      val ppr = pstage.prs(preg)
      mapInPort(pr.in, ppr.in, pmap)
    }
  }

  def mapPROut(stage:ST, pstage:PST, map:M):M = {
    val nres = pstage.prs.size
    val nnode = stage.prs.size
    if (nnode > nres) throw OutOfPipeReg(pstage, stage, nres, nnode)

    val rcmap = map.rcmap
    stage.prs.foldLeft(map) { case (pmap, (reg, pr)) =>
      val preg = rcmap(reg)
      val ppr = pstage.prs(preg)
      mapOutPort(pstage)(pr.out, ppr.out, pmap)
    }
  }

  def mapInPort(n:IP, r:PIP, map:M):M = {
    if (map.fpmap.contains(r)) return map
    val rcmap = map.rcmap
    val stmap = map.stmap
    val opmap = map.opmap
    val ipmap = map.ipmap
    val pop:POP = n.from.src match {
      case Const(_, c) =>
        if (!r.isConn(PConst.out)) {
          val info = s"${n} is Const, but ${r} cannot be configured to constant"
          throw InPortRouting(n, r, info)
        } else PConst(c).out
      case pr@PipeReg(stage, reg) => 
        val preg = rcmap(reg)
        val pstage = stmap(stage)
        val ppr = pstage.prs(preg) //TODO
        r match {
          case rp:Stagable => // src of the inport belongs to a stage
            val rmp = r.asInstanceOf[RMPort] 
            if (!rmp.isMappedTo(preg)) { // inport doens't map to logical reg
              val info = s"${n} connects to PipeReg ${pr}. Mapped to PipeReg: ${ppr}. r's mappedReg:${rmp.mappedRegs}"
              throw InPortRouting(n, r, info) 
            } else { // inport map to logical reg but have some slack to reach pr
              var info = "" 
              val pcurStage = rp.stage
              if (pstage.before(pcurStage)) {
                pcurStage.pre.get.prs(preg).out
              } else if (pstage == pcurStage) {
                if (!rp.isConn(ppr.out)) {
                  info = s"Cannot find connection to ${ppr}: " 
                  throw InPortRouting(n, r, info)
                } else {
                  ppr.out
                }
              } else {
                info = s"Cannot read later stage. reader:${pcurStage} to:${pstage}"
                throw InPortRouting(n, r, info)
              }
            } 
          case rp => // src of the inport doesn't belong to a stage. e.g. counter max
            if (!r.isConn(ppr.out)) throw InPortRouting(n, r, s"Cannot connect ${r} to ${ppr.out}")
            else ppr.out
        }
      case s => 
        val pport = opmap(n.from)
        val info = s"${n} connects to ${n.from}. Mapped OutPort:${pport}"
        if (!r.isConn(pport)) throw InPortRouting(n, r, info)
        else pport
    }
    val cmap = if (map.ipmap.contains(n)) map else map.setIP(n,r)
    cmap.setFP(r, pop)
  } 

  def mapOutPort(pcurStage:PST)(n:OP, r:POP, map:M):M = {
    val cmap = map.setOP(n,r)
    n.to.foldLeft(cmap) { case (pmap, ip) =>
      val ipmap = pmap.ipmap
      if (ipmap.contains(ip)) {
        mapInPort(ip, ipmap(ip), pmap)
      } else pmap
    }
  }

}
case class OutOfStage(pcu:PCU, cu:CU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = StageMapper
  override val msg = s"Not enough Stages in ${pcu} to map ${cu}."
}
case class OpNotSupported(ps:PST, s:ST)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper
  override val msg = s"${ps}:[${ps.funcUnit.get.ops}] doesn't support op:${s.fu.get.op} in ${s}"
}
case class OutOfPipeReg(ps:PST, s:ST, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = StageMapper
  override val msg = s"Not enough PipeReg in ${ps} to map ${s}."
}
case class OutOfOperand(ps:PST, s:ST, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = StageMapper
  override val msg = s"Not enough operands in ${ps} to map ${s}."
}
case class StageRouting(n:ST, p:PST)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
case class InPortRouting(n:IP, p:PIP, info:String)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}. info:${info}"
}
