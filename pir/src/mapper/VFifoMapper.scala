package pir.mapper

import pir._
import pir.util.typealias._

import spade._

import pirc.exceptions._

class VFifoMapper(implicit val design:PIR) extends Mapper with LocalRouter {
  type N = VFIFO
  type R = PVMem
  val typeStr = "VecFifoMapper"
  override def debug = PIRConfig.debugVFifoMapper
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def constrain(n:N, r:R, m:M):M = {
    var mp = m
    mp = mp.setPM(n, r).setOP(n.readPort, r.readPort)
    mp = mapInPort(n.writePort, r.writePort, mp)
    mp = mapMux(n.writePortMux, r.writePortMux, mp)
    mp
  }

  def resFunc(n:N, m:M, triedRes:List[R]):List[R] = {
    val vis = pir.util.collectIn[GI](n.writePort)
    assert(vis.size==1, s"Vector FIFO can only have a single writer! ${n.ctrler}.$n's writer ${vis}")
    val pvi = m.vimap(vis.head)
    val rs = spade.util.collectOut[R](pvi)
    assert(rs.size==1, s"$pvi connect to multiple vfifos=${rs}")
    List(rs.head)
  }

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      bind(
        allNodes=cu.vfifos,
        initMap=pirMap, 
        constrain=constrain _,
        resFunc=resFunc _, //(n, m, triedRes) => List[R]
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

