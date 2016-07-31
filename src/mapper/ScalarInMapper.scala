package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ScalarIn => SI, Scalar, Top}
import pir.plasticine.graph.{Controller => PCL, InBus => PVI, ScalarIn => PSI, ScalarOut => PSO}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarInMapper extends Mapper {
  type N = SI
  type R = PSI 

  private def mapScalarIns(cl:CL, pcl:PCL)(n:N, p:R, cuMap:M):M = {
    val ib = cuMap.getVImap(cl)(n)
    val idx = cuMap.getSLmap(cl).getIdx(n.scalar)
    if (p.in.isConn(ib.outports(idx)))
      cuMap.setSI(cl, n, p)
    else
      throw ScalarInRouting(n, p)
  }

  def map(cl:CL, cuMap:M):M = {
    val pcl = cuMap.getPcu(cl)
    val sin = cl.sins
    val psin = pcl.sins
    // Assume one SI to one outport, no need to map
    
    simAneal(psin, sin, cuMap, List(mapScalarIns(cl, pcl) _),None,OutOfScalarIn(pcl, _, _))
  }

}

case class OutOfScalarIn(pcl:PCL, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = ScalarInMapper
  override val msg = s"Not enough Scalar Input Buffer in ${pcl} to map application."
}
case class ScalarInRouting(n:SI, p:PSI)(implicit design:Design) extends MappingException {
  override val mapper = ScalarInMapper
  override val msg = s"Fail to map ${n} to ${p}"
}
