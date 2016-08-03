package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Stage => ST, WAStage => WST, ReduceStage => RST, AccumStage, Port => P, Const, PipeReg}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Stage => PST, WAStage => PWST, ReduceStage => PRST}
import pir.plasticine.graph.{Const => PConst, OutPort, FUInPort => PFIP, FUOutPort => PFOP, 
                            FUPort => PFP}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

object StageMapper extends Mapper {
  type R = PST
  type N = ST
  type OM = STMap.OM 

  val finPass = None

  def map(cu:CU, cuMap:M):M = {
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    val psts = pcu.stages
    val sts = cu.stages.toList
    Try { //TODO: Currently if fail doesn't finish mapping 
      inordBind(psts, sts, cuMap, List(mapStage _), finPass, OutOfStage(pcu, _, _))
    } match  {
      case Success(m) => return m
      case Failure(e) => e.printStackTrace; System.exit(0)
    }
    cuMap
  }

  type OMIM = (OM, IPMap)
  def mapStage(n:N, p:R, map:M):M = {
    val stmap = map.stmap + (n -> (p, Map.empty))
    n match {
      case s:WST => 
        if (!p.isInstanceOf[PWST]) throw StageRouting(n, p)
      case s:RST =>
        if (!p.isInstanceOf[PRST]) throw StageRouting(n, p)
        else return map.set(stmap)
      case _ =>
    }
    val oprdCons = List(mapOprd(map.rcmap, stmap, map.opmap) _)
    val oprds = n.operands
    val poprds = p.fu.oprds
    val oprdInit:OMIM = (Map.empty, map.ipmap)
    val (oprdmap, ipmap) = simAneal[PFIP,P,OMIM](poprds, oprds, oprdInit, oprdCons, 
                          None, OutOfOperand(p, n, _, _))
    map.set(map.stmap + (n -> (p, oprdmap)))
  }

  def mapOprd(rcmap:RCMap, stmap:STMap, opmap:OPMap)(n:P, r:PFIP, map:OMIM):OMIM = {
    var (om, ipmap) = map
    n.src match {
      case s:Const =>
        if (!r.isConn(PConst)) throw OperandRouting(n, r)
      case PipeReg(stage, reg) => 
        val preg = rcmap(reg)
        val pstage = stmap.getStage(stage)
        if (!r.prAccess.contains(preg)) { throw OperandRouting(n, r) } 
        if (!r.prAccess(preg).contains(pstage)) {
          println(s"${stage} ${pstage} not in ${r.prAccess(preg)}")
          throw OperandRouting(n, r)
        } 
      case s => 
        val pport = opmap(n)
        if (!r.isConn(pport)) throw OperandRouting(n, r)
        ipmap += (r -> pport)
    }
    (om + (n -> r), ipmap)
  } 

  def mapResult(rcmap:RCMap, stmap:STMap)(r:PFOP, n:P):Unit = {
    n.src match {
      case PipeReg(stage, reg) =>
        val preg = rcmap(reg)
        val pstage = stmap.getStage(stage)
        if (!r.prAccess.contains(preg)) throw ResultRouting(n,r)
        if (!r.prAccess(preg).contains(pstage)) throw ResultRouting(n,r)
      case _ => throw ResultRouting(n, r) 
    }
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
case class OperandRouting(n:P, p:PFIP)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
case class ResultRouting(n:P, p:PFOP)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
