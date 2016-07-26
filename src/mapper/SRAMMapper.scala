package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, MemoryController => MC}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{ComputeUnit => PCU, MemoryController => PMC}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM}
import pir.graph.traversal.PIRMapping

import scala.collection.mutable.ListBuffer
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
      p.emitln(s"$k -> $v")
    }
    p.emitBE
  }

  //private def mapSRAM(cuMap:CUMapper.M)(s:N, p:R, map:M):M = {
  //    map + (s -> p)
  //}

  // No need to try. Assume 1 to 1 correspondent between vecIn and sram write port in arch
  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val usedRes = ListBuffer[PSRAM]()
    cu.srams.foldLeft(Map[N,R]()) { case (pmap, sram) =>
      val psram = if (sram.writePort.src.isInstanceOf[VecIn]) {
        val writer = sram.writePort.src.asInstanceOf[VecIn].vector.writers.head 
        val vmap = cuMap(cu)._2
        val pvin = vmap(writer)
        pcu.srams.filter(s => s.writePort.isConn(pvin.outports(0))).head
      } else {
        pcu.srams.filter(psram => !usedRes.contains(psram)).head
      }
      assert(!usedRes.contains(psram))
      usedRes += psram
      pmap + (sram ->psram)
    }
    //simAneal(pcu.srams, cu.srams, HashMap[N, R](), List(mapSRAM(cuMap) _), OutOfSram(pcu, _, _))
  }
}

case class OutOfSram(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = SRAMMapper
  override val msg = s"Not enough SRAMs in ${pcu} to map application."
}
