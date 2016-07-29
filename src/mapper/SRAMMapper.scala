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
  type V = PSRAM 

  def printMap(m:MP)(implicit p:Printer) = {
    p.emitBS("sramMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"$k -> $v")
    }
    p.emitBE
  }

  private def mapSRAM(cu:CU, pcu:PCU)(s:N, p:R, cuMap:M):M = {
    val suc = s.writePort.src match {
      case wp:VecIn =>
        val writers = s.writePort.src.asInstanceOf[VecIn].vector.writers 
        assert(writers.size==1)
        p.writePort.isConn(VecMapper.getIB(cuMap, cu, wp).outports(0))
      case _ => true
    }
    assert(suc) //TODO: Current arch this should always success
    CUMapper.setSmap(cuMap, cu, CUMapper.getSmap(cuMap, cu) + (s -> p))
  }

  // No need to try. Assume 1 to 1 correspondent between vecIn and sram write port in arch
  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val cons = List(mapSRAM(cu, pcu) _)
    simAneal(pcu.srams, cu.srams, cuMap, cons, None, OutOfSram(pcu, _, _))
  }
}

case class OutOfSram(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = SRAMMapper
  override val msg = s"Not enough SRAMs in ${pcu} to map application."
}
