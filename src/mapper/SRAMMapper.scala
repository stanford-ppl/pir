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

object SRAMMapper extends Mapper {
  type N = SRAM 
  type R = PSRAM 
  type V = PSRAM 

  def printMap(m:M)(implicit p:Printer) = {
    p.emitBS("sramMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"($k -> $v)")
    }
    p.emitBE
  }

  private def mapSRAM(s:N, p:R, map:M):M = {
    map + (s -> p)
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    if (cu.srams.size > pcu.srams.size)
      throw OutOfSram(pcu)
    else
      simAneal(pcu.srams, cu.srams, HashMap[N, R](), List(mapSRAM _))
  }
}
