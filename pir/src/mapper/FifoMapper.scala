package pir.mapper

import pir._
import pir.util.typealias._

import pirc.enums._

class FifoMapper(implicit val design:PIR) extends Mapper with LocalRouter {
  type N = LMem
  type R = PLMem
  val typeStr = "FifoMapper"
  override def debug = PIRConfig.debugSFifoMapper
  import pirmeta.{indexOf => _, nameOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def constrain(n:N, r:R, map:M):M = {
    var mp = map
    mp = mp.setPM(n, r)
    mp = mapOutput(n.readPort, r.readPort, mp)
    mp = mapInput(n.writePort, r.writePort, mp)
    mp = mapMux(n.writePortMux, r.writePortMux, mp)
    mp
  }

  def resFunc(n:N, m:M, triedRes:List[R]):List[R] = emitBlock(s"resFunc(${n.ctrler}.$n)"){
    (n match {
      case n:SMem => getFIFOs(n, m)
      case n => getFIFOs(n, m)
    }).filterNot { r => triedRes.contains(r) || m.pmmap.contains(r) }
  }

  def getFIFOs(n:N, m:M):List[R] = {
    def visitOut(x:Any):Iterable[Any] = x match {
      case x:PVMux[_] => Set(x.out)
      case x:PGI[_] => Set(x.ic)
      case x:PAT => Set() // Add control primitive
      case x:PD => Set()
      case x => spade.util.visitOut(x)
    }
    val ins = pir.util.collectIn[GI](n.writePort)
    val pins = ins.map { in => m.vimap(in) }
    dprintln(s"pins=${quote(pins)}")
    pins.map { pin => 
      val pmems = spade.util.collectOut[R](pin, visitOut=visitOut, logger=None) 
      dprintln(s"pin=${quote(pin)} pmems=${quote(pmems)}")
      pmems
    }.reduce { _ intersect _ }.toList
  }

  def getFIFOs(n:SMem, m:M):List[R] = {
    val cu = n.ctrler
    val pcu = m.pmmap(cu)
    val reses = cu match {
      case cu:MC if n.name.get=="data" => pcu.sfifos.filter { sbuf => nameOf(sbuf) == s"s${n.name.get}" }
      case cu:MC if cu.mctpe==TileLoad => pcu.sfifos.filter { sbuf => nameOf(sbuf) == s"r${n.name.get}" }
      case cu:MC if cu.mctpe==TileStore => pcu.sfifos.filter { sbuf => nameOf(sbuf) == s"w${n.name.get}" }
      case cu => pcu.sfifos
    }
    dprintln(s"MC filtered reses=[${reses.mkString(",")}]")
    reses
  }

  def map(cu:CU, pirMap:M):M = {
    log((cu, true)) {
      bind[R,N,M](
        allNodes=cu.lmems,
        initMap=pirMap, 
        constrain=constrain _,
        resFunc=resFunc _, //(n, m, triedRes) => List[R]
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
