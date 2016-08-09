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

  val finPass = None

  type MP = (SIMap, OPMap)
  private def mapScalarIns(vimap:VIMap, slmap:SLMap)(n:N, p:R, maps:MP):MP = {
    val (simap, opmap) = maps 
    val ib = vimap(n)
    val idx = slmap.getIdx(n.scalar)
    if (p.in.isConn(ib.outports(idx))) {
      (simap + (n -> p), opmap + (n.out -> p.out))
    } else
      throw ScalarInRouting(n, p)
  }

  def map(cl:CL, pirMap:M):M = {
    val pcl = pirMap.clmap(cl)
    val sin = cl.sins
    val psin = pcl.sins
    // Assume one SI to one outport, no need to map
    val cons = List(mapScalarIns(pirMap.vimap, pirMap.slmap) _)
    val maps = (pirMap.simap, pirMap.opmap)
    val (simap, opmap) = simAneal(psin, sin, maps, cons, finPass, OutOfScalarIn(pcl, _, _))
    pirMap.set(simap).set(opmap)
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
