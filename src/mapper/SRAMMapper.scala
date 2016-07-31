package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM}
import pir.graph.traversal.PIRMapping

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object SRAMMapper extends Mapper {
  type N = SRAM 
  type R = PSRAM 

  private def mapSRAM(cu:CU)(s:N, p:R, cuMap:M):M = {
    val suc = s.writePort.src match {
      case wp:VecIn => 
        val ib = cuMap.vimap(wp)
        p.writePort.isConn(ib.outports(0))
      case _ => true //TODO
    }
    assert(suc) //TODO: Current arch this should always success
    cuMap.setSM(s, p) 
  }

  // No need to try. Assume 1 to 1 correspondent between vecIn and sram write port in arch
  def map(cu:CU, cuMap:M):M = {
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    val cons = List(mapSRAM(cu) _)
    simAneal(pcu.srams, cu.srams, cuMap, cons, None, OutOfSram(pcu, _, _))
  }
}

case class OutOfSram(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = SRAMMapper
  override val msg = s"Not enough SRAMs in ${pcu} to map application."
}
