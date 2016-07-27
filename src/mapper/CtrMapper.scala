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

  override def printMap(m:Map[N,V])(implicit p:Printer) = {
    p.emitBS("ctrMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"$k -> $v")
    }
    p.emitBE
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val ctrs = cu.cchains.flatMap{cc => cc.counters}
    simAneal(pcu.ctrs, ctrs, HashMap[N, V](), List(mapCtr _), OutOfCtr(pcu, _, _))
  }

  def mapCtr(c:N, p:R, map:M):M = {
    map + (c -> p)
  }

}
case class OutOfCtr(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrMapper
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
