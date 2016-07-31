package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, ScalarOut => SO}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU}
import pir.plasticine.graph.{ScalarOut => PSO}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarOutMapper extends Mapper {
  type N = SO 
  type R = PSO 
  type V = PSO 

  private def mapScalarOuts(cu:CU, pcu:PCL)(n:N, p:R, cuMap:M):M = {
    cuMap.setSOmap(cu, cuMap.getSOmap(cu) + (n -> p))
  }

  def map(cu:CU, pcu:PCL, cuMap:M)(implicit design: Design):M = {
    val sout = cu.souts
    val psout = pcu.souts
    simAneal(psout, sout, cuMap, List(mapScalarOuts(cu, pcu) _), None, OutOfScalarOut(pcu, _, _))
  }
}

case class OutOfScalarOut(pcu:PCL, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = ScalarOutMapper 
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcu} to map application."
}
