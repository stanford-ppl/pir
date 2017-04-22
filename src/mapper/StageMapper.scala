package pir.mapper
import pir._
import pir.util.typealias._
import pir.util.enums._
import pir.graph.{Const, ScalarOutPR, VecOutPR}
import pir.pass.{PIRMapping}
import pir.util._
import pir.plasticine.main._
import pir.plasticine.graph.{Const => PConst, PipeReg => PPR, _}
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
    if (cu.stages.isEmpty) return cuMap
    log(cu) {
      var mp = cuMap
      val pcu = mp.clmap(cu).asCU
      //val pest :: pfusts = pcu.stages
      //val est :: fusts = cu.stages.toList
      //var cmap = mapStage(est, pest, cuMap)
      val nodes = cu.stages
      val reses = pcu.fustages
      def oor(pnodes:List[R], nodes:List[N], m:M) = OutOfStage(pcu, cu, pnodes, nodes, m)
      mp = bindInOrder(reses, nodes, mp, List(mapStage _), finPass(cu) _, oor _)
      mp = stageFowarding(pcu, mp)
      mp
    }
  }

  /* Forward liveOut regs in pstages that doesn't have stage corresponding to them */
  def stageFowarding(pcu:PCU, cuMap:M):M = {
    var mp = cuMap
    var preLiveOuts:Set[Reg] = Set.empty
    pcu.stages.foreach { pstage =>
      val ppregs:Set[PReg] = preLiveOuts.map {reg => mp.rcmap(reg) }
      if (mp.stmap.pmap.contains(pstage)) {
        val stage = mp.stmap.pmap(pstage)
        preLiveOuts = stage.liveOuts 
      } else {
        pstage.prs.foreach { ppr =>
          if (ppregs.contains(ppr.reg)) mp = mp.setFI(ppr.in, pstage.prev.get.get(ppr.reg).out)
        }
      }
    }
    pcu.stages.last.prs.foreach { 
      case ppr@PPR(ps, pr) if pr.is(ScalarOutReg) & mp.rcmap.pmap.contains(pr) =>
        val ScalarOutPR(so) = mp.rcmap.pmap(pr)
        mp.vomap(so).foreach { pso =>
          mp = mp.setFI(pso.ic, ppr.out)
        }
      case ppr@PPR(ps, pr) if pr.is(VecOutReg) & mp.rcmap.pmap.contains(pr) =>
        val VecOutPR(vo) = mp.rcmap.pmap(pr)
        mp.vomap(vo).foreach { pvo =>
          mp = mp.setFI(pvo.ic, ppr.out)
        }
      case pr =>
    }
    mp
  }

  def mapStage(n:N, p:R, map:M):M = {
    log(s"Try $n -> $p") {
      var mp = map
      mp = mp.setST(n, p)
      n match {
        case s:EST => 
          if (!p.isInstanceOf[PEST]) throw StageRouting(n, p, mp)
          mp = mapPROut(n, p, mp)
          mp = mapPRIn(n, p, mp)
        case fs => fs match {
            case s:WAST => if (!p.isInstanceOf[PWAST]) throw StageRouting(n, p, mp)
            case s:RDST => if (!p.isInstanceOf[PRDST]) throw StageRouting(n, p, mp)
            case _ => if (!p.isInstanceOf[PFUST]) throw StageRouting(n, p, mp)
          }
          mp = mapPROut(n, p, mp)
          mp = mapFUOut(n, p, mp)
          mp = mapPRIn(n, p, mp)
          mp = mapFUIn(n, p, mp)
      }
      mp
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
