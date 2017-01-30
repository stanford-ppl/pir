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

  def finPass(scl:CL)(m:M):M = m

  private def mapVecOut(scl:CL)(n:N, p:R, cuMap:M):M = {
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

  def map(scl:CL, cuMap:M):M = {
    val pcl = cuMap.clmap(scl)
    scl match {
      case top:Top =>
        val cons = List(mapVecOut(scl) _)
        bind(pcl.vouts, scl.vouts, cuMap, cons, finPass(scl) _)
      case c:CL if (c.vouts.size==1) => 
        mapVecOut(c)(c.vouts.head, pcl.vouts.head, cuMap)
      case c:CL if (c.vouts.size==0) => cuMap
    }
  }
}

case class OutOfScalarOut(pcl:PCL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcl} to map application."
}
