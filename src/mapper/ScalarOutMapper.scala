package pir.graph.mapper
import pir._
import pir.typealias._
import pir.graph.enums._
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class ScalarOutMapper(implicit val design:Design) extends Mapper {
  type N = VO 
  type R = POB 
  val typeStr = "SOMapper"
  override def debug = Config.debugSOMapper

  def finPass(cl:CL)(m:M):M = m

  private def mapVecOut(cl:CL)(n:N, p:R, cuMap:M):M = {
    cl match {
      case tt:TT if tt.mctpe==TileLoad => cuMap.setVO(n,p)
      case tt:TT if tt.mctpe==TileStore => cuMap
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

  def map(cl:CL, cuMap:M):M = {
    val pcl = cuMap.clmap(cl)
    val mp = cl match {
      case c:TT if c.mctpe == TileLoad => 
        val p = pcl.asInstanceOf[PTT].addrOut
        val n = c.souts.head
        cuMap.setSO(n, p).setIP(n.in, p.in) // ScalarOut of TileTransfer is internally connected to MC
      case c:MC => cuMap
      case _ => cuMap
    }
    val cons = List(mapVecOut(cl) _)
    bind(pcl.vouts, cl.vouts, mp, cons, finPass(cl) _)
  }
}

case class OutOfScalarOut(pcl:PCL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcl} to map application."
}
