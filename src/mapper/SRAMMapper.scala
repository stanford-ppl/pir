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

  val finPass = None

  type MP = (SMMap, OPMap, IPMap)
  private def mapSRAM(vimap:VIMap)(s:N, p:R, maps:MP):MP = {
    var (smmap, opmap, ipmap) = maps
    s.writePort.from.src match {
      case wp:VecIn => // Remote write 
        val ib = vimap(wp)
        if(!p.writePort.isConn(ib.viport)) throw SRAMRouting(s, p)
      case _ => () // Local write, assume always a reg mapped to write port of sram. Checked at RegAlloc
    }
    smmap += (s -> p)
    opmap += (s.readPort -> p.readPort)
    ipmap += (s.writeAddr -> p.writeAddr)
    ipmap += (s.readAddr -> p.readAddr)
    ipmap += (s.writePort -> p.writePort)
    (smmap, opmap, ipmap)
  }

  def map(cu:CU, pirMap:M):M = {
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    val cons = List(mapSRAM(pirMap.vimap) _)
    val maps = (pirMap.smmap, pirMap.opmap, pirMap.ipmap)
    val (simap, opmap, ipmap) = simAneal(pcu.srams, cu.srams, maps, cons, finPass, OutOfSram(pcu, _, _))
    pirMap.set(simap).set(opmap).set(ipmap)
  }
}

case class OutOfSram(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = SRAMMapper
  override val msg = s"Not enough SRAMs in ${pcu} to map application."
}
case class SRAMRouting(n:SRAM, p:PSRAM)(implicit design:Design) extends MappingException {
  override val mapper = SRAMMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
