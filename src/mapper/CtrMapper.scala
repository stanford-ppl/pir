package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, MemoryController => MC}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{ComputeUnit => PCU, MemoryController => PMC}
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
      p.emitln(s"($k -> $v)")
    }
    p.emitBE
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val ctrs = cu.cchains.flatMap{cc => cc.counters}
    if (ctrs.size > pcu.ctrs.size) throw OutOfCtr(pcu)
    else simAneal(pcu.ctrs, ctrs, HashMap[N, V](), List(mapCtr _))
  }

  def mapCtr(c:N, p:R, map:Map[N, V]):M = {
    map + (c -> p)
  }

}
