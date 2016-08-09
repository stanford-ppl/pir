package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Stage => ST, EmptyStage => EST, WAStage => WAST, ReduceStage => RDST, AccumStage, Const, PipeReg}
import pir.graph.{InPort => IP, OutPort => OP}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{EmptyStage => PEST, FUStage => PFUST, Stage => PST, WAStage => PWAST, ReduceStage => PRDST}
import pir.plasticine.graph.{Const => PConst, InPort => PIP, OutPort => POP}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

object StageMapper extends Mapper {
  type R = PST
  type N = ST

  val finPass = None

  def map(cu:CU, cuMap:M):M = {
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    val pest :: pfusts = pcu.stages
    val est :: fusts = cu.stages.toList
    val cmap = cuMap.setST(est, pest) // Map empty stage
    Try { //TODO: Currently if fail take a while to finish 
      inordBind(pfusts, fusts, cmap, List(mapStage _), finPass, OutOfStage(pcu, _, _))
    } match  {
      case Success(m) => return m
      case Failure(e) => e.printStackTrace; System.exit(-1)
    }
    cmap 
  }

  def mapStage(n:N, p:R, map:M):M = {
    val stmap = map.stmap + (n -> p)
    n match {
      case s:WAST => if (!p.isInstanceOf[PWAST]) throw StageRouting(n, p)
      case s:RDST => if (!p.isInstanceOf[PRDST]) throw StageRouting(n, p)
      case _ =>
    }
    val fu = n.fu.get
    val pfu = p.asInstanceOf[PFUST].fu
    val opmap = mapResult(map.rcmap, stmap)(pfu.out, fu.out, map.opmap)
    val oprds = fu.operands
    val poprds = pfu.operands
    val oprdCons = List(mapOprd(map.rcmap, stmap, opmap) _)
    val ipmap = simAneal(poprds, oprds, map.ipmap, oprdCons, None, OutOfOperand(p, n, _, _))
    map.set(stmap).set(opmap).set(ipmap)
  }

  def mapOprd(rcmap:RCMap, stmap:STMap, opmap:OPMap)(n:IP, r:PIP, map:IPMap):IPMap = {
    n.src match {
      case s:Const =>
        if (!r.isConn(PConst)) throw OperandRouting(n, r)
      case PipeReg(stage, reg) => 
        val preg = rcmap(reg)
        val pstage = stmap(stage)
        val ppr = pstage.prs(preg)
        if (!r.isConn(ppr.out)) throw OperandRouting(n, r) 
      case s => 
        val pport = opmap(n.from)
        if (!r.isConn(pport)) throw OperandRouting(n, r)
    }
    map + (n -> r)
  } 

  def mapResult(rcmap:RCMap, stmap:STMap)(r:POP, n:OP, map:OPMap):OPMap = {
    val opmap = map + (n -> r)
    opmap //TODO
    //n.src match {
    //  case PipeReg(stage, reg) =>
    //    val preg = rcmap(reg)
    //    val pstage = stmap.getStage(stage)
    //    if (!r.prAccess.contains(preg)) throw ResultRouting(n,r)
    //    if (!r.prAccess(preg).contains(pstage)) throw ResultRouting(n,r)
    //  case _ => throw ResultRouting(n, r) 
    //}
  }
}
case class OutOfStage(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = StageMapper
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
case class OutOfOperand(ps:PST, s:ST, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = StageMapper
  override val msg = s"Not enough operands in ${ps} to map ${s}."
}
case class StageRouting(n:ST, p:PST)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
case class OperandRouting(n:IP, p:PIP)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
case class ResultRouting(n:OP, p:POP)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
