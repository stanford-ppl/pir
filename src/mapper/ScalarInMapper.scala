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

  private def mapScalarIns(n:N, p:R, pirMap:M):M = {
    val ib = pirMap.vimap(n)
    val idx = pirMap.slmap.getIdx(n.scalar)
    if (p.in.isConn(ib.outports(idx))) {
      //TODO: pirMap.setSI(n, p).setOP(n.out, p.out)
    } else
      throw ScalarInRouting(n, p)
    pirMap
  }

  def map(cl:CL, pirMap:M):M = {
    val pcl = pirMap.clmap(cl)
    val sin = cl.sins
    val psin = pcl.sins
    // Assume one SI to one outport, no need to map
    
    val finPass = None
    simAneal(psin, sin, pirMap, List(mapScalarIns _), finPass, OutOfScalarIn(pcl, _, _))
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
