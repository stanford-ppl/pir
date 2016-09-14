package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ScalarIn => SI, Scalar, Top}
import pir.plasticine.graph.{Controller => PCL, InBus => PVI, ScalarIn => PSI, ScalarOut => PSO}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class ScalarInMapper(implicit val design:Design) extends Mapper {
  type N = SI
  type R = PSI 

  def finPass(cl:CL)(m:M):M = m 

  private def mapScalarIns(vimap:VIMap, somap:SOMap)(n:N, p:R, map:M):M = {
    val simap = map.simap
    val opmap = map.opmap
    val ib = vimap(n)
    val idx = somap(n.scalar.writer).idx
    if (p.in.canFrom(ib.outports(idx))) {
      map.setSI(n,p).setOP(n.out, p.out)
    } else
      throw ScalarInRouting(n, p)
  }

  def map(cl:CL, pirMap:M):M = {
    val pcl = pirMap.clmap(cl)
    val sin = cl.sins
    val psin = pcl.sins
    // Assume one SI to one outport, no need to map
    val cons = List(mapScalarIns(pirMap.vimap, pirMap.somap) _)
    simAneal(psin, sin, pirMap, cons, finPass(cl) _, OutOfScalarIn(pcl, _, _))
  }

}

case class OutOfScalarIn(pcl:PCL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Input Buffer in ${pcl} to map application."
}
case class ScalarInRouting(n:SI, p:PSI)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ${n} to ${p}"
}

