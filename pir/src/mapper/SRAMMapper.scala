package pir.mapper

import pir._
import pir.util.typealias._

import spade._
import spade.util._

import pirc.exceptions._

class SramMapper(implicit val design:PIR) extends Mapper with LocalRouter {
  type N = SRAM
  type R = PSRAM
  val typeStr = "SramMapper"
  override def debug = PIRConfig.debugSramMapper
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def constrain(n:N, r:R, m:M):M = {
    var mp = m
    val sizePerBuffer = r.size / n.buffering
    if (n.size > sizePerBuffer) throw MappingException(m, s"$n'size=${n.size} > $r.sizePerBuffer=$sizePerBuffer")
    if (n.banks > r.banks) throw MappingException(m, s"$n.banks=${n.banks} > $r.banks=${r.banks}")
    mp = m.setPM(n, r)
    mp = mapOutput(n.readPort, r.readPort, mp)
    mp = mapInput(n.writePort, r.writePort, mp)
    mp = mapInput(n.writeAddr, r.writeAddr, mp)
    mp = mapInput(n.readAddr, r.readAddr, mp)
    mp = mapMux(n.writePortMux, r.writePortMux, mp)
    mp = mapMux(n.writeAddrMux, r.writeAddrMux, mp)
    mp = mapMux(n.readAddrMux, r.readAddrMux, mp)
    mp
  }

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      val srams = cu.srams
      val psrams = pirMap.pmmap(cu) match {
        case pcu:PCU => pcu.srams
        case _ => Nil
      }
      bind(
        allRes=psrams,
        allNodes=srams,
        initMap=pirMap,
        constrain=constrain _,
        finPass=(m:M) => m
      )
    }
  }

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:ICL => map(cu, pirMap)
      case cu => finPass(cu)(pirMap)
    }
  }
}

