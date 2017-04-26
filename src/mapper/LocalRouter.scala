package pir.mapper
import pir._
import pir.util.typealias._
import pir.util.enums._
import pir.graph.{Const, PipeReg}
import pir.pass.{PIRMapping}
import pir.util._
import pir.plasticine.main._
import pir.plasticine.graph.{Const => PConst, PipeReg => PPR}
import pir.plasticine.util._
import pir.exceptions._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}
import scala.language.existentials

trait LocalRouter extends Mapper {

  def mapConst(n:Const[_], r:PConst, map:M):M = {
    var mp = map
    dprintln(s"mapping $n -> $r")
    mp = mp.setOP(n.out, r.out)
    mp = mp.setPM(n, r)
    mp
  }

  /* If propogate Pipeline Reg can reaches pin, propogate ppr and map register in/out along the
   * path, and return Some(map), otherwise return None */
  def propogate(ppr:PPR, pin:PI[PModule], map:M):Option[M] = {
    var curppr = ppr
    var mp = map
    dprintln(s"propogating $ppr to $pin")
    while (!ppr.out.canConnect(pin)) {
      curppr.stage.next.fold {
        return None
      } { nextStage =>
        val nextPpr = nextStage.get(curppr.reg)
        mp = mp.setFI(nextPpr.in, curppr.out)
        curppr = nextPpr
      }
    }
    mp = mp.setFI(pin, ppr.out)
    return Some(mp)
  }

  def mapInPort(n:IP, r:PI[PModule], map:M):M = {
    var mp = map
    if (mp.fimap.contains(r) && mp.ipmap.contains(n)) return mp
    (n.from.src, r.src) match {
      case (oSrc@Const(c), piSrc) =>
        mappingOf[PConst](r).filterNot{ pc => mp.pmmap.pmap.contains(pc) }.headOption.fold {
          val info = s"${n} is Const, but ${r} cannot be configured to constant"
          throw InPortRouting(n, r, info, mp)
        } { pconst =>
          mp = mapConst(oSrc, pconst, mp)
          mp = mp.setFI(r, pconst.out)
        }
      case (oSrc@PipeReg(oStage, oReg), piSrc@PPR(piStage, piReg)) => // output is from pipeReg and input is to pipeReg
        assert(mp.rcmap(oReg).contains(piReg))
        val poStage = mp.stmap(oStage)
        val poPpr = poStage.get(piReg)
        mp = propogate(poPpr, r, mp).getOrElse {
          throw InPortRouting(n, r, s"Cannot propogate $poPpr to $piSrc", mp)
        }
      case (oSrc@PipeReg(oStage, oReg), piSrc) => // output is from pipeReg and input is to pipeReg
        // Register value is passed to a non register input
        // Find pregs mapped to reg. Propogate preg values along the pipeline stages
        // individually until the output of ppr propogation reaches input r
        val poStage = mp.stmap(oStage)
        val poPprs = mp.rcmap(oReg).map { poReg => poStage.get(poReg) }

        val propogatedMap = poPprs.foldLeft[Option[M]](None) { case (prev, poPpr) =>
          if (prev.isEmpty) propogate(poPpr, r, mp) else prev
        }
        mp = propogatedMap.getOrElse {
          throw InPortRouting(n, r, s"Cannot connect ${r} to ${oSrc.out}", mp)
        }
      case (os, pis) => 
        // src of the inport doesn't belong to a stage and inport is not from a PipeReg
        val pop = n match {
          case n if n.isCtrlIn => mp.vimap(n).ic
          case n => 
            val pops = mp.opmap(n.from).filter{ pop => r.canConnect(pop) }
            if(pops.size!=1) throw InPortRouting(n, r, s"Cannot connect ${r} to ${pops} n=$n n.from=${n.from} r=$r pops=${pops}", mp)
            pops.head
        }
        mp = mp.setFI(r, pop)
    }
    mp = if (mp.ipmap.contains(n)) mp else mp.setIP(n,r)
    //dprintln(s"Mapping IP:${n} -> ${cmap.ipmap(n)}")
    mp
  } 

  def mapOutPort(n:OP, r:PO[_<:PModule], map:M):M = {
    var mp = map
    mp = mp.setOP(n,r)
    dprintln(s"mapping $n -> $r")
    mp = n.to.foldLeft(mp) { case (pmap, ip) =>
      val ipmap = pmap.ipmap
      if (ipmap.contains(ip)) {
        mapInPort(ip, ipmap(ip), pmap)
      } else pmap
    }
    mp
  }

}
case class InPortRouting(n:IP, p:PI[_<:PModule], info:String, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}. info:${info}"
}
