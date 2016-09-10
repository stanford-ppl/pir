package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, InnerComputeUnit => ICU, TileTransfer => TT}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM}
import pir.graph.traversal.PIRMapping

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class SRAMMapper(implicit val design:Design) extends Mapper {
  type N = SRAM 
  type R = PSRAM 

  def finPass(cu:ICU)(m:M):M = m 

  private def mapSRAM(vimap:VIMap)(s:N, p:R, map:M):M = {
    s.writePort.from.src match {
      case wp:VecIn => // Remote write 
        val ib = vimap(wp)
        if(!p.writePort.canFrom(ib.viport)) throw SRAMRouting(s, p)
      case _ => () // Local write, assume always a reg mapped to write port of sram. Checked at RegAlloc
    }
    map.setSM(s, p)
      .setOP(s.readPort, p.readPort)
      .setIP(s.writeAddr, p.writeAddr)
      .setIP(s.readAddr, p.readAddr)
      .setIP(s.writePort, p.writePort)
  }

  def map(cu:ICU, pirMap:M):M = {
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    val cons = List(mapSRAM(pirMap.vimap) _)
    simAneal(pcu.srams, cu.srams, pirMap, cons, finPass(cu) _, OutOfSram(pcu, _, _))
  }
}

case class OutOfSram(pcu:PCU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough SRAMs in ${pcu} to map application."
}
case class SRAMRouting(n:SRAM, p:PSRAM)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ${n} to ${p}"
}
