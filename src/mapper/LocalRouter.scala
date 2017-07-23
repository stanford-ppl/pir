package pir.mapper
import pir._
import pir.util.typealias._
import pir.util.enums._
import pir.graph.{Const, PipeReg}
import pir.pass.{PIRMapping}
import pir.util._
import pir.plasticine.main._
import pir.plasticine.graph.{PipeReg => PPR}
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
    while (!curppr.out.propogate.canConnect(pin)) {
      curppr.stage.next.fold {
        return None
      } { nextStage =>
        val nextPpr = nextStage.get(curppr.reg)
        mp = mp.setFI(nextPpr.in, curppr.out)
        curppr = nextPpr
      }
    }
    mp = mp.setFI(pin, curppr.out.propogate)
    return Some(mp)
  }

  /*
   * Find connection from pin to pout by checkout pout + pout.slices + pout.broadcasts to pin +
   * pin.slices
   * @return mapping and whether successfully connected
   * */
  def connect(pin:PI[PModule], pout:PO[PModule], map:M):(M, Boolean) = {
    var mp = map
    val pouts = pout :: pout.slices.map{_.out} ++ pout.broadcasts.map{_.out}
    val pins = pin :: pin.slices.map{_.in}
    var connected = false
    pins.foreach { pi => 
      pouts.foreach { po => 
        if (pi.canConnect(po)) {
          if (!connected) {
            mp = mp.setFI(pi, po)
            pin.slices.filter { _.in == pi }.foreach { slice => mp = mp.setFI(pin, slice.out) }
            pout.slices.filter { _.out == po }.foreach { slice => mp = mp.setFI(slice.in, pout) }
            pout.broadcasts.filter { _.out == po }.foreach { broadcast => mp = mp.setFI(broadcast.in, pout) }
            connected = true
          } else {
            throw LocalRouting(s"More than 1 connection between pin=$pin and pout=$pout", mp)
          }
        }
      }
    }
    (mp, connected)
  }

  def mapFanIn(n:IP, r:PI[PModule], map:M):M = {
    var mp = map
    if (!n.isConnected) return mp
    (n.from.src, r.src) match {
      case (oSrc@Const(c), piSrc) =>
        mappingOf[PConst](r).filterNot{ pc => mp.pmmap.pmap.contains(pc) }.headOption.fold {
          val info = s"${n} is Const, but ${r} cannot be configured to constant"
          throw InPortRouting(n, r, info, mp)
        } { pconst =>
          mp = mapConst(oSrc, pconst, mp)
          val (m, connected) = connect(r, pconst.out, mp)
          mp = m
          if (!connected) throw LocalRouting(s"No connection between $r to constant $pconst", mp)
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
        n match {
          case n if n.isGlobal => 
            val pop = mp.vimap(n).ic
            mp = mp.setFI(r, pop)
          case n => 
            mp.opmap.get(n.from).foreach { pops =>
              var pops = mp.opmap(n.from)
              val found = pops.foldLeft(false) { 
                case (false, pop) =>
                  val (m, connected) = connect(r, pop, mp)
                  mp = m
                  connected
                case (true, pop) => true
              }
              if (!found) throw InPortRouting(n, r, s"Cannot connect ${r} to pops=$pops n=$n n.from=${n.from}", mp)
            }
        }
    }
    mp
  }

  def mapInPort(n:IP, r:PI[PModule], map:M):M = {
    var mp = map
    if (mp.fimap.contains(r) && mp.ipmap.contains(n)) return mp
    mp = mp.setIP(n, r)
    mp = mapFanIn(n, r, mp)
    mp
  } 

  def mapOutPort(n:OP, r:PO[_<:PModule], map:M):M = {
    var mp = map
    mp = mp.setOP(n,r)
    //dprintln(s"mapping $n -> $r")
    n.to.foreach { 
      case ip if (mp.ipmap.contains(ip)) =>
        mp = mapInPort(ip, mp.ipmap(ip), mp)
      case ip =>
    }
    mp
  }

}
case class InPortRouting(n:IP, p:PI[_<:PModule], info:String, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}. info:${info}"
}
case class LocalRouting(info:String, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"${info}"
}
