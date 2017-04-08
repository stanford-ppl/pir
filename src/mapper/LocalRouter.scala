package pir.mapper
import pir._
import pir.util.typealias._
import pir.util.enums._
import pir.graph.{Const, PipeReg}
import pir.pass.{PIRMapping, MapPrinter}
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

trait LocalRouter extends Mapper {

  def mapConst(const:Const[_], pconst:PConst, map:M):M = {
    map.setOP(const.out, pconst.out).setPM(const, pconst)
  }

  def mapInPort(n:IP, r:PI[_<:PModule], mp:M):M = {
    var map = mp
    if (map.fimap.contains(r) && map.ipmap.contains(n)) return map
    val pop:PO[_<:PModule] = n.from.src match {
      case const@Const(c) =>
        val pconsts = mappingOf[PConst](r)
        if (pconsts.isEmpty) {
          val info = s"${n} is Const, but ${r} cannot be configured to constant"
          throw InPortRouting(n, r, info, map)
        } else {
          val pconst = pconsts.head
          map = mapConst(const, pconst, map)
          pconst.out
        }
      case pr@PipeReg(stage, reg) => 
        val preg = map.rcmap(reg)
        val pstage = map.stmap(stage)
        val ppr = pstage.get(preg) //TODO
        stageOf(r) match {
          case Some(pcurStage) => // src of the inport belongs to a stage
            if (!regsOf(r).contains(preg)) { // inport doens't map to logical reg
              val info = s"${n} connects to PipeReg ${pr}. Mapped to PipeReg: ${ppr}. r's mappedReg:${regsOf(r).mkString(",")}"
              throw InPortRouting(n, r, info, map) 
            } else { // inport map to logical reg but have some slack to reach pr
              var info = "" 
              if (pstage.before(pcurStage)) {
                pcurStage.pre.get.get(preg).out
              } else if (pstage == pcurStage) {
                if (!r.canConnect(ppr.out)) {
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
          case None => // src of the inport doesn't belong to a stage. e.g. counter max
                     // and inport is from a PipeReg
            var pop:PO[_<:PModule] = null
            var curppr:PPR = null
            // Check if stretch the pipeline can inport reaches desired out reg
            do {
              if (curppr==null) curppr = ppr // First iteration
              else {
                curppr = curppr.stage.next.get.get(curppr.reg) 
              }
              if (r.canConnect(curppr.out)) pop = ppr.out
            } while (!curppr.stage.isLast) 
            if (pop==null) throw InPortRouting(n, r, s"Cannot connect ${r} to ${ppr.out}", map)
            else pop
        }
      case s => // src of the inport doesn't belong to a stage
                // and inport is not from a PipeReg
        val pport = map.opmap(n.from)
        val info = s"${n} connects to ${n.from}. Mapped OutPort:${pport}"
        if (!r.canConnect(pport)) {
          dprintln(s"Fail mapping $n because $r can't from $pport. ${r.fanIns}")
          throw InPortRouting(n, r, info, map)
        }
        else pport
    }
    assert(map.opmap(n.from) == pop)
    val cmap = if (map.ipmap.contains(n)) map else map.setIP(n,r)
    cmap.setFI(r, pop)
  } 

  def mapOutPort(n:OP, r:PO[_<:PModule], map:M):M = {
    val cmap = map.setOP(n,r)
    n.to.foldLeft(cmap) { case (pmap, ip) =>
      val ipmap = pmap.ipmap
      if (ipmap.contains(ip)) {
        mapInPort(ip, ipmap(ip), pmap)
      } else pmap
    }
  }

}
case class InPortRouting(n:IP, p:PI[_<:PModule], info:String, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}. info:${info}"
}
