package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object CtrMapper extends Mapper {
  type R = PCtr
  type N = Ctr
  type V = PCtr

  override def printMap(m:MP)(implicit p:Printer) = {
    p.emitBS("ctrMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"$k -> $v")
    }
    p.emitBE
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val ctrs = cu.cchains.flatMap{cc => cc.counters}
    simAneal(pcu.ctrs, ctrs, cuMap, List(mapCtr(cu, pcu) _), None, OutOfCtr(pcu, _, _))
  }

  def mapCtr(cu:CU, pcu:PCU)(c:N, p:R, map:M):M = {
    CUMapper.setCtmap(map, cu, CUMapper.getCtmap(map, cu) + (c -> p))
  }

}
case class OutOfCtr(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrMapper
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
