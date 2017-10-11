package pir.mapper

import pir._
import pir.util.typealias._

import spade._
import spade.util._

import pirc._
import pirc.enums._

class SFifoMapper(implicit val design:PIR) extends Mapper with LocalRouter {
  type N = SMem
  type R = PSMem
  val typeStr = "ScalFifoMapper"
  override def debug = PIRConfig.debugSFifoMapper
  import pirmeta.{indexOf => _, nameOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def constrain(n:N, r:R, map:M):M = {
    var mp = map
    mp = mp.setPM(n, r)
    mp = mapOutPort(n.readPort, r.readPort, mp)
    mp = mapInPort(n.writePort, r.writePort, mp)
    mp = mapMux(n.writePortMux, r.writePortMux, mp)
    mp
  }

  def resFunc(cu:CU)(n:N, m:M, triedRes:List[R]):List[R] = emitBlock(s"resFunc($cu.$n)"){
    val pcu = m.pmmap(cu)
    val reses = cu match {
      case cu:MC if n.name.get=="data" => pcu.sfifos.filter { sbuf => nameOf(sbuf) == s"s${n.name.get}" }
      case cu:MC if cu.mctpe==TileLoad => pcu.sfifos.filter { sbuf => nameOf(sbuf) == s"r${n.name.get}" }
      case cu:MC if cu.mctpe==TileStore => pcu.sfifos.filter { sbuf => nameOf(sbuf) == s"w${n.name.get}" }
      case cu => pcu.sfifos
    }
    dprintln(s"MC filtered reses=[${reses.mkString(",")}]")
    reses.diff(triedRes).filterNot{ r => m.pmmap.contains(r) }
  }

  def map(cu:CU, pirMap:M):M = {
    log(cu) {
      bind(
        allNodes=cu.smems,
        initMap=pirMap, 
        constrain=constrain _,
        resFunc=resFunc(cu) _, //(n, m, triedRes) => List[R]
        finPass=(m:M) => m
      )
    }
  }

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:CU => map(cu, pirMap)
      case cu => finPass(cu)(pirMap)
    }
  }
}
