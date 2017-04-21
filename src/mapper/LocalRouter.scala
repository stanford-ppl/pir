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

trait LocalRouter extends Mapper {

  def mapConst(const:Const[_], pconst:PConst, map:M):M = {
    map.setOP(const.out, pconst.out).setPM(const, pconst)
  }

  def mapInPort(n:IP, r:PI[_<:PModule], map:M):M = {
    var mp = map
    if (mp.fimap.contains(r) && mp.ipmap.contains(n)) return mp
    n.from.src match {
      case const@Const(c) =>
        val pconsts = mappingOf[PConst](r)
        if (pconsts.isEmpty) {
          val info = s"${n} is Const, but ${r} cannot be configured to constant"
          throw InPortRouting(n, r, info, mp)
        } else {
          val pconst = pconsts.head
          mp = mapConst(const, pconst, mp)
          mp = mp.setFI(r, pconst.out)
        }
      case pr@PipeReg(stage, reg) => 
        val preg = mp.rcmap(reg)
        val pstage = mp.stmap(stage)
        val ppr = pstage.get(preg) //TODO
        stageOf(r) match {
          case Some(pcurStage) => // src of the inport belongs to a stage
            if (!regsOf(r).contains(preg)) { // inport doens't map to logical reg
              val info = s"${n} connects to PipeReg ${pr}. Mapped to PipeReg: ${ppr}. r's mappedReg:${regsOf(r).mkString(",")}"
              throw InPortRouting(n, r, info, mp) 
            } else { // inport map to logical reg but have some slack to reach pr
              var info = "" 
              val pop = if (pstage.before(pcurStage)) {
                pcurStage.prev.get.get(preg).out
              } else if (pstage == pcurStage) {
                if (!r.canConnect(ppr.out)) {
                  info = s"Cannot find connection to ${ppr}: " 
                  throw InPortRouting(n, r, info, mp)
                } else {
                  ppr.out
                }
              } else {
                info = s"Cannot read later stage. reader:${pcurStage} to:${pstage}"
                throw InPortRouting(n, r, info, mp)
              }
              mp = mp.setFI(r, pop)
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
            if (pop==null) throw InPortRouting(n, r, s"Cannot connect ${r} to ${ppr.out}", mp)
            mp = mp.setFI(r, pop)
        }
      case s => // src of the inport doesn't belong to a stage
                // and inport is not from a PipeReg
        val pop = mp.opmap(n.from)
        //mp.opmap.get(n.from).foreach { pop =>
        mp = mp.setFI(r, pop)
        //}
    }
    mp = if (mp.ipmap.contains(n)) mp else mp.setIP(n,r)
    //dprintln(s"Mapping IP:${n} -> ${cmap.ipmap(n)}")
    mp
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
