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

case class CtrMapping(cu:CU, pcu:PCU, cuMap:Map[CU, PrimMapping])(implicit val design: Design) extends Mapping {

  override type R = PCtr
  override type N = Ctr
  override type V = PCtr

  lazy private val arch = design.arch
  lazy private val top = design.top
  lazy private val allNodes = design.allNodes

  val ctrs = cu.cchains.flatMap{cc => cc.counters}
  override val mapping = if (ctrs.size > pcu.ctrs.size) {
    throw OutOfCtr(pcu)
  } else {
    val (csuc, cmap) = simAneal(pcu.ctrs, ctrs, HashMap[N, V](), List(mapCtr _))
    cmap
  }

  def mapCtr(c:N, p:R, map:Map[N, V]) = {
    map + (c -> p)
  }

  import PIRMapping._
  override def printMap = {
    emitBS("ctrMap")
    mapping.foreach{ case (k,v) =>
      emitln(s"($k -> $v)")
    }
    emitBE
  }

}
