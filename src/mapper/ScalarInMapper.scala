package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, ScalarIn => SI, Scalar, Top}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, InBus => PVI, ScalarIn => PSI, ScalarOut => PSO}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarInMapper extends Mapper {
  type N = SI
  type R = PSI 
  type V = (PSI, PSO) 

  private def mapScalarIns(cu:CU, pcu:PCL)(n:N, p:R, cuMap:M)(implicit design: Design):M = {
    //val vmap = cuMap(n.ctrler.asInstanceOf[CU])._2
    //val dep = n.scalar.writers.head
    //val validSouts = dep match {
    //  case c:CU =>
    //    val pvin = vmap(dep)
    //    pvin.outports.filter(op => p.in.isConn(op))
    //  case c:Top =>
    //    design.arch.argIns.filter(ai => p.in.isConn(ai))
    //}
    //if (validSouts.size == 0) throw ScalarInRouting(p, pvin) 
    cuMap.setSImap(cu, cuMap.getSImap(cu) + (n -> (p, null)))
  }

  def map(cu:CU, pcu:PCL, cuMap:M)(implicit design: Design):M = {
    val sin = cu.sins
    val psin = pcu.sins
    simAneal(psin, sin, cuMap, List(mapScalarIns(cu, pcu) _),None,OutOfScalarIn(pcu, _, _))
  }

}

case class OutOfScalarIn(pcu:PCL, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = ScalarInMapper
  override val msg = s"Not enough Scalar Input Buffer in ${pcu} to map application."
}
case class ScalarInRouting(psi:PSI, pvin:PVI)(implicit design:Design) extends MappingException {
  override val mapper = ScalarInMapper
  override val msg = s"Fail to route ${psi} to ${pvin}"
}
