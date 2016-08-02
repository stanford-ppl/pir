package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Stage => ST, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Stage => PST}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object StageMapper extends Mapper {
  type R = PST
  type N = ST

  def map(cu:CU, cuMap:M):M = {
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    simAneal(pcu.stages, cu.stages.toList, cuMap, List(mapStage _), None, OutOfStage(pcu, _, _))
  }

  def mapStage(n:N, p:R, map:M):M = {
    return map.setST(n,p) 
  }

}
case class OutOfStage(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = StageMapper
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
case class StageRouting(n:ST, p:PST)(implicit design:Design) extends MappingException {
  override val mapper = StageMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
