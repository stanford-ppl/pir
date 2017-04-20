package pir.mapper
import pir._
import pir.util.typealias._
import pir.util.enums._
import pir.graph.{Const, PipeReg}
import pir.pass.{PIRMapping}
import pir.util._
import pir.plasticine.main._
import pir.plasticine.graph.{Const => PConst}
import pir.plasticine.util._
import pir.exceptions._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}
import scala.language.existentials

class StageMapper(implicit val design:Design) extends Mapper with LocalRouter {

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
    var fimap = cuMap.fimap
    pcu.stages.foreach { pstage =>
      val ppregs:Set[PReg] = preLiveOuts.map {reg => cuMap.rcmap(reg) }
      if (cuMap.stmap.pmap.contains(pstage)) {
        val stage = cuMap.stmap.pmap(pstage)
        preLiveOuts = stage.liveOuts 
      } else {
        pstage.prs.foreach { ppr =>
          if (ppregs.contains(ppr.reg)) fimap += ppr.in -> pstage.prev.get.get(ppr.reg).out
        }
      }
    }
    cuMap.set(fimap)
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
          def oor(pnodes:List[PI[_<:PModule]], nodes:List[IP], mapping:M) = OutOfOperand(p, n, pnodes, nodes, mapping)
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
    mapOutPort(fu.out, pfu.out, map)
  }

  def mapPRIn(stage:ST, pstage:PST, map:M):M = {
    val rcmap = map.rcmap
    stage.prs.foldLeft(map) { case (pmap, pr) =>
      val preg = rcmap(pr.reg)
      val ppr = pstage.get(preg)
      mapInPort(pr.in, ppr.in, pmap)
    }
  }

  def mapPROut(stage:ST, pstage:PST, map:M):M = {
    val nres = pstage.prs.size
    val nnode = stage.prs.size
    if (nnode > nres) throw OutOfPipeReg(pstage, stage, pstage.prs, stage.prs, map)

    val rcmap = map.rcmap
    stage.prs.foldLeft(map) { case (pmap, pr) =>
      val preg = rcmap(pr.reg)
      val ppr = pstage.get(preg)
      mapOutPort(pr.out, ppr.out, pmap)
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
case class OutOfOperand(ps:PST, s:ST, pnodes:List[PI[_<:PModule]], nodes:List[IP], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends OutOfResource(mp) {
  override val msg = s"Not enough operands in ${ps} to map ${s}."
}
case class StageRouting(n:ST, p:PST, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}"
}
