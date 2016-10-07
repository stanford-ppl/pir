package pir.graph.mapper
import pir._
import pir.typealias._
import pir.graph.enums._
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class OutputMapper(implicit val design:Design) extends Mapper {
  type N = VO 
  type R = POB 
  val typeStr = "SOMapper"
  override def debug = Config.debugSOMapper

  def finPass(scl:SCL)(m:M):M = m

  private def mapVecOut(scl:SCL)(n:N, p:R, cuMap:M):M = {
    scl match {
      case tt:TT if tt.mctpe==TileLoad => 
        val so = tt.souts.head
        val pso = cuMap.clmap(scl).asInstanceOf[PTT].addrOut
        // ScalarOut of TileTransfer is internally connected to MC
        cuMap.setVO(n,p).setSO(so, pso).setIP(so.in, pso.in)
      case tt:TT if tt.mctpe==TileStore =>
        val so = tt.souts.head
        val pso = cuMap.clmap(scl).asInstanceOf[PTT].addrOut
        cuMap.setSO(so, pso).setIP(so.in, pso.in)
      case _:MC => cuMap
      case _:CU | _:Top =>
        val mp = cuMap.setVO(n,p)
        n match {
          case dvo:DVO =>
            dvo.scalarOuts.zipWithIndex.foldLeft(mp) { case (pm, (so, i)) =>
              val psos = p.inports(i).fanIns.map(_.src).collect{ case pso:PSO => pso }
              assert(psos.size==1)
              val pso = psos.head
              pm.setSO(so, pso).setIP(so.in, pso.in)
            }
          case _ => mp 
        } 
    }
  }

  def map(scl:SCL, cuMap:M):M = {
    val pcl = cuMap.clmap(scl)
    scl match {
      // TileStore doesn't have vout but has sout 
      case c:TT if c.mctpe == TileStore => mapVecOut(c)(null, null, cuMap)
      case c:MC => cuMap
      case c:SCL if (c.vouts.size==1) => 
        assert(pcl.vouts.size==1)
        mapVecOut(c)(c.vouts.head, pcl.vouts.head, cuMap)
      case top:Top =>
        val cons = List(mapVecOut(scl) _)
        bind(pcl.vouts, scl.vouts, cuMap, cons, finPass(scl) _)
      case _ => cuMap
    }
  }
}

case class OutOfScalarOut(pcl:PCL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcl} to map application."
}
