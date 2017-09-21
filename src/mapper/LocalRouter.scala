package pir.mapper

import pir._
import pir.node.{Const, PipeReg}
import pir.util.typealias._

import spade._
import spade.node.{PipeReg => PPR}
import spade.util._

import pirc.exceptions._

import scala.language.existentials
import scala.reflect.{ClassTag, classTag}

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

  def mapFanIn[T](pin:PI[PModule], map:M)(implicit ev:ClassTag[T]):M = {
    var mp = map
    if (mp.fimap.contains(pin)) return mp
    if (pin.fanIns.size==1) { mp = mp.setFI(pin, pin.fanIns.head); return mp }
    val pouts = fromInstanceOf[T](pin)
    if (pouts.size==1) {
      mp = mp.setFI(pin, pouts.head.asInstanceOf[PO[PModule]])
    }
    mp
  }

  def mapFanIn(in:IP, pin:PI[PModule], map:M):M = {
    var mp = map
    if (!in.isConnected || mp.fimap.contains(pin)) return mp
    val out = in.from
    (out.src, pin.src) match {
      case (osrc@Const(c), pisrc) =>
        mappingOf[PConst](pin).filterNot{ pc => mp.pmmap.contains(pc) }.headOption.fold {
          val info = s"$in is Const, but $pin cannot be configured to constant"
          throw InPortRouting(in, pin, info, mp)
        } { pconst =>
          mp = mapConst(osrc, pconst, mp)
          val (m, connected) = connect(pin, pconst.out, mp)
          mp = m
          if (!connected) throw LocalRouting(s"No connection between $pin to constant $pconst", mp)
        }
      case (osrc@PipeReg(oStage, oReg), pisrc@PPR(piStage, piReg)) => // output is from pipeReg and input is to pipeReg
        assert(mp.rcmap(oReg).contains(piReg))
        val poStage = mp.pmmap(oStage)
        val poPpr = poStage.get(piReg)
        mp = propogate(poPpr, pin, mp).getOrElse {
          throw InPortRouting(in, pin, s"Cannot propogate $poPpr to $pisrc", mp)
        }
      case (osrc@PipeReg(oStage, oReg), pisrc) => // output is from pipeReg and input is to pipeReg
        // Register value is passed to a non register input
        // Find pregs mapped to reg. Propogate preg values along the pipeline stages
        // individually until the output of ppr propogation reaches input pin
        val poStage = mp.pmmap(oStage)
        val poPprs = mp.rcmap(oReg).map { poReg => poStage.get(poReg) }

        val propogatedMap = poPprs.foldLeft[Option[M]](None) { case (prev, poPpr) =>
          if (prev.isEmpty) propogate(poPpr, pin, mp) else prev
        }
        mp = propogatedMap.getOrElse {
          throw InPortRouting(in, pin, s"Cannot connect $pin to ${osrc.out}", mp)
        }
      case (osrc, pisrc) => 
        // src of the inport doesn't belong to a stage and inport is not from a PipeReg
        in match {
          case in if in.isGlobal => 
            val pop = mp.vimap(in).ic
            mp = mp.setFI(pin, pop)
          case in => 
            mp.opmap.get(in.from).foreach { pops =>
              var pops = mp.opmap(in.from)
              val found = pops.foldLeft(false) { 
                case (false, pop) =>
                  val (m, connected) = connect(pin, pop, mp)
                  mp = m
                  connected
                case (true, pop) => true
              }
              if (!found) throw InPortRouting(in, pin, s"Cannot connect $pin to pops=$pops in=$in in.from=${in.from}", mp)
            }
            mp = mapFanIn(pin, mp)
        }
    }
    mp
  }

  def mapInPort(in:IP, pin:PI[PModule], map:M):M = {
    var mp = map
    if (mp.fimap.contains(pin) && mp.ipmap.contains(in)) return mp
    mp = mp.setIP(in, pin)
    mp = mapFanIn(in, pin, mp)
    mp
  } 

  def mapOutPort(out:OP, pout:PO[_<:PModule], map:M):M = {
    var mp = map
    mp = mp.setOP(out,pout)
    //dprintln(s"mapping $out -> $pout")
    out.to.foreach { 
      case ip if (mp.ipmap.contains(ip)) =>
        mp = mapInPort(ip, mp.ipmap(ip), mp)
      case ip =>
    }
    mp
  }

  def mapMux(mux:Mux, pmux:PMux[_], map:M):M = {
    var mp = map
    mp = mp.setPM(mux, pmux)
    if (pmux.ins.size < mux.ins.size) throw PassThroughException(
        MuxUnderSize(s"${quote(pmux.prt)}.$pmux undersize! $pmux.ins=${pmux.ins.size} $mux.ins=${mux.ins.size}"), mp)
    (mux.ins, pmux.ins).zipped.foreach { case (n, r) => mp = mapInPort(n, r, mp) }
    mp = mapInPort(mux.sel, pmux.sel, mp)
    mp = mapOutPort(mux.out, pmux.out.asInstanceOf[PO[PModule]], mp)
    mp
  }

}

case class MuxUnderSize(info:String) (implicit mapper:Mapper, design:PIR) extends MappingException(PIRMap.empty) {
  override val msg = info
} 
case class InPortRouting(n:IP, p:PI[_<:PModule], info:String, mp:PIRMap)(implicit mapper:Mapper, design:PIR) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}. info:${info}"
}
case class LocalRouting(info:String, mp:PIRMap)(implicit mapper:Mapper, design:PIR) extends MappingException(mp) {
  override val msg = s"${info}"
}
