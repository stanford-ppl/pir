package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object CtrMapper extends Mapper {
  type R = PCtr
  type N = Ctr

  def map(cu:CU, pcu:PCU, cuMap:M):M = {
    val ctrs = cu.cchains.flatMap{cc => cc.counters}
    simAneal(pcu.ctrs, ctrs, cuMap, List(mapCtr(cu, pcu) _), None, OutOfCtr(pcu, _, _))
  }

  def mapCtr(cu:CU, pcu:PCU)(c:N, p:R, map:M):M = {
    map.setCt(cu, c, p)
  }

}
case class OutOfCtr(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrMapper
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
