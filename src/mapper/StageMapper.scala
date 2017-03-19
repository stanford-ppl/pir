package pir.mapper
import pir._
import pir.typealias._
import pir.util.enums._
import pir.graph.{Const, PipeReg}
import pir.plasticine.graph.{ConstVal}
import pir.graph.traversal.{PIRMapping, MapPrinter}
import pir.util._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

class StageMapper(implicit val design:Design) extends Mapper {
  type R = PST
  type N = ST
  val typeStr = "StageMapper"
  override def debug = Config.debugSTMapper
  override val exceptLimit = 200

  def finPass(cu:ICL)(m:M):M = m

  def map(cu:ICL, cuMap:M):M = {
    log(cu) {
      val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
      val pest :: pfusts = pcu.stages
      val est :: fusts = cu.stages.toList
      var cmap = mapStage(est, pest, cuMap)
      log(s"$cu bindStages") {
        def oor(pnodes:List[PST], nodes:List[ST], mapping:PIRMap) = OutOfStage(pcu, cu, pnodes, nodes, mapping)
        cmap = bindInOrder(pfusts, fusts, cmap, List(mapStage _), finPass(cu) _, oor _)
      }
      stageFowarding(pcu, cmap)
    }
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
    log(s"Try $n -> $p") {
      var cmap = map.setST(n, p)
      n match {
        case s:EST => 
          if (!p.isInstanceOf[PEST]) throw StageRouting(n, p, cmap)
          cmap = mapPROut(n, p, cmap)
          cmap = mapPRIn(n, p, cmap)
        case fs => fs match {
            case s:WAST => if (!p.isInstanceOf[PWAST]) throw StageRouting(n, p, cmap)
            case s:RDST => if (!p.isInstanceOf[PRDST]) throw StageRouting(n, p, cmap)
            case _ => if (!p.isInstanceOf[PFUST]) throw StageRouting(n, p, cmap)
          }
          cmap = mapPROut(n, p, cmap)
          cmap = mapFUOut(n, p, cmap)
          cmap = mapPRIn(n, p, cmap)
          cmap = mapFUIn(n, p, cmap)
      }
      cmap
    }
  }

  def mapFUIn(n:ST, p:PST, map:M):M = {
    val fu = n.fu.get
    val pfu = p.asInstanceOf[PFUST].fu
    // Check Operand 
    val oprds = fu.operands
    val poprds = pfu.operands
    fu.op match {
      case Mux =>
        fu.operands.zipWithIndex.foldLeft(map){ case (map, (oprd, i)) =>
          mapInPort(oprd, pfu.operands(i), map)
        }
      case _ =>
        log(s"$n bind FU Inputs") {
          def oor(pnodes:List[PIP], nodes:List[IP], mapping:M) = OutOfOperand(p, n, pnodes, nodes, mapping)
          bind(poprds, oprds, map, mapInPort _, (m:M) => m, oor _)
        }
    }
  }

  def mapFUOut(n:ST, p:PST, map:M):M = {
    val fu = n.fu.get
    val pfu = p.asInstanceOf[PFUST].fu
    // Check Operation 
    if (!pfu.ops.contains(fu.op)) throw OpNotSupported(p, n, map)
    // Check Result 
    mapOutPort(p)(fu.out, pfu.out, map)
  }

  def mapPRIn(stage:ST, pstage:PST, map:M):M = {
    val rcmap = map.rcmap
    stage.prs.foldLeft(map) { case (pmap, (reg, pr)) =>
      val preg = rcmap(reg)
      val ppr = pstage.prs(preg)
      mapInPort(pr.in, ppr.in, pmap)
    }
  }

  def mapPROut(stage:ST, pstage:PST, map:M):M = {
    val nres = pstage.prs.size
    val nnode = stage.prs.size
    if (nnode > nres) throw OutOfPipeReg(pstage, stage, pstage.prs.values.toList, stage.prs.values.toList, map)

    val rcmap = map.rcmap
    stage.prs.foldLeft(map) { case (pmap, (reg, pr)) =>
      val preg = rcmap(reg)
      val ppr = pstage.prs(preg)
      mapOutPort(pstage)(pr.out, ppr.out, pmap)
    }
  }

  def mapInPort(n:IP, r:PIP, map:M):M = {
    if (map.fpmap.contains(r) && map.ipmap.contains(n)) return map
    val rcmap = map.rcmap
    val stmap = map.stmap
    val opmap = map.opmap
    val ipmap = map.ipmap
    val pop:POP = n.from.src match {
      case Const(c) =>
        if (!r.canFrom(design.arch.const.out)) {
          val info = s"${n} is Const, but ${r} cannot be configured to constant"
          throw InPortRouting(n, r, info, map)
        } else ConstVal(c)(design.arch).out
      case pr@PipeReg(stage, reg) => 
        val preg = rcmap(reg)
        val pstage = stmap(stage)
        val ppr = pstage.prs(preg) //TODO
        r match {
          case rp:Stagable => // src of the inport belongs to a stage
            val rmp = r.asInstanceOf[PRMPT] 
            if (!rmp.isMappedTo(preg)) { // inport doens't map to logical reg
              val info = s"${n} connects to PipeReg ${pr}. Mapped to PipeReg: ${ppr}. r's mappedReg:${rmp.mappedRegs}"
              throw InPortRouting(n, r, info, map) 
            } else { // inport map to logical reg but have some slack to reach pr
              var info = "" 
              val pcurStage = rp.stage
              if (pstage.before(pcurStage)) {
                pcurStage.pre.get.prs(preg).out
              } else if (pstage == pcurStage) {
                if (!rp.canFrom(ppr.out)) {
                  info = s"Cannot find connection to ${ppr}: " 
                  throw InPortRouting(n, r, info, map)
                } else {
                  ppr.out
                }
              } else {
                info = s"Cannot read later stage. reader:${pcurStage} to:${pstage}"
                throw InPortRouting(n, r, info, map)
              }
            } 
          case rp => // src of the inport doesn't belong to a stage. e.g. counter max
                     // and inport is from a PipeReg
            var pop:POP = null
            var curppr:PPR = null
            // Check if stretch the pipeline can inport reaches desired out reg
            do {
              if (curppr==null) curppr = ppr // First iteration
              else {
                curppr = curppr.stage.next.get.prs(curppr.reg) 
              }
              if (r.canFrom(curppr.out)) pop = ppr.out
            } while (!curppr.stage.isLast) 
            if (pop==null) throw InPortRouting(n, r, s"Cannot connect ${r} to ${ppr.out}", map)
            else pop
        }
      case s => // src of the inport doesn't belong to a stage
                // and inport is not from a PipeReg
        val pport = opmap(n.from)
        val info = s"${n} connects to ${n.from}. Mapped OutPort:${pport}"
        if (!r.canFrom(pport)) {
          dprintln(s"Fail mapping $n because $r can't from $pport. ${r.fanIns}")
          throw InPortRouting(n, r, info, map)
        }
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
case class OutOfStage(pcu:PCU, cu:ICL, pnodes:List[PST], nodes:List[ST], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends OutOfResource(mp) {
  override val msg = s"Not enough Stages in ${pcu} to map ${cu}."
}
case class OpNotSupported(ps:PST, s:ST, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"${ps}:[${ps.funcUnit.get.ops}] doesn't support op:${s.fu.get.op} in ${s}"
}
case class OutOfPipeReg(ps:PST, s:ST, pnodes:List[PPR], nodes:List[PR], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends OutOfResource(mp) {
  override val msg = s"Not enough PipeReg in ${ps} to map ${s}."
}
case class OutOfOperand(ps:PST, s:ST, pnodes:List[PIP], nodes:List[IP], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends OutOfResource(mp) {
  override val msg = s"Not enough operands in ${ps} to map ${s}."
}
case class StageRouting(n:ST, p:PST, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}"
}
case class InPortRouting(n:IP, p:PIP, info:String, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}. info:${info}"
}
