package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ScalarOut => SO}
import pir.plasticine.graph.{Controller => PCL}
import pir.plasticine.graph.{ScalarOut => PSO}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarOutMapper extends Mapper {
  type N = SO 
  type R = PSO 
  type V = PSO 

  private def mapScalarOuts(cl:CL)(n:N, p:R, cuMap:M):M = {
    val scalar = n.scalar
    val t = (p.outBus, p.idx)
    val cmap = cuMap.setSLmap(cl, cuMap.getSLmap(cl) + (scalar -> t))
    cmap.setSOmap(cl, cmap.getSOmap(cl) + (n -> p))
  }

  def map(cl:CL, pcl:PCL, cuMap:M)(implicit design: Design):M = {
    val souts = cl.souts
    val psouts = pcl.souts
    simAneal(psouts, souts, cuMap, List(mapScalarOuts(cl) _), None, OutOfScalarOut(pcl, _, _))
  }
}

case class OutOfScalarOut(pcl:PCL, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = ScalarOutMapper 
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcl} to map application."
}
