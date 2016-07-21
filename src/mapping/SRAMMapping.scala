package pir.graph.mapping
import pir.Design
import pir.Config
import pir.graph.{ComputeUnit => CU, MemoryController => MC}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{ComputeUnit => PCU, MemoryController => PMC}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM}
import pir.graph.mapping.CUMapping.PrimMapping
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

case class SRAMMapping(cu:CU, pcu:PCU, cuMap:Map[CU, PrimMapping])(implicit val design: Design) extends Mapping {

  type N = SRAM 
  type R = PSRAM 
  type V = PSRAM 

  lazy private val arch = design.arch
  lazy private val top = design.top
  lazy private val allNodes = design.allNodes

  def mapSRAM(s:N, p:R, map:Map[N, V]) = {
    map + (s -> p)
  }

  override val mapping = if (cu.srams.size > pcu.srams.size) {
    throw OutOfSram(pcu)
  } else {
    val (ssuc, smap) = simAneal(pcu.srams, cu.srams, HashMap[N, R](), List(mapSRAM _))
    smap
  }

  import PIRMapping._
  override def printMap = {
    emitBS("sramMap")
    mapping.foreach{ case (k,v) =>
      emitln(s"($k -> $v)")
    }
    emitBE
  }

}
