package pir.mapper
import pir.{Design, Config}
import pir.util.typealias._
import pir.pass.PIRMapping
import pir.graph.{PipeReg => PR, VecInPR, LoadPR}
import pir.spade.graph.{PipeReg => PPR}
import pir.spade.util._
import pir.spade.main._
import pir.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class VFifoMapper(implicit val design:Design) extends Mapper with LocalRouter {
  type N = VFIFO
  type R = PVMem
  val typeStr = "VecFifoMapper"
  override def debug = Config.debugVFifoMapper
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def constrain(n:N, r:R, m:M):M = {
    var mp = m
    mp = mp.setSM(n, r).setOP(n.readPort, r.readPort)
    mp = mapInPort(n.writePort, r.writePort, mp)
    mp = mapMux(n.writePortMux, r.writePortMux, mp)
    mp
  }

  def resFunc(n:N, m:M, triedRes:List[R]):List[R] = {
    val vis = pir.util.collectIn[VI](n.writePort)
    assert(vis.size==1, s"Vector FIFO can only have a single writer! ${n.ctrler}.$n's writer ${vis}")
    val pvi = m.vimap(vis.head)
    val rs = pir.spade.util.collectOut[R](pvi)
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

