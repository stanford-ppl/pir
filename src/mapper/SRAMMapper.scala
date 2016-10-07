package pir.graph.mapper
import pir._
import pir.typealias._
import pir.graph.traversal.PIRMapping
import pir.graph.{PipeReg => PR, VecInPR}
import pir.plasticine.graph.{PipeReg => PPR}

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class SRAMMapper(implicit val design:Design) extends Mapper {
  type N = SRAM 
  type R = PSRAM 
  val typeStr = "SramMapper"
  override def debug = Config.debugSMMapper

  def finPass(cu:ICL)(m:M):M = m 

  private def mapSRAM(vimap:VIMap)(s:N, p:R, map:M):M = {
    s.writePort.from.src match {
      case PR(stage, VecInPR(_, vi)) if stage.isInstanceOf[EST] =>
        val ib = vimap(vi)
        val ibpregs = ib.viport.fanOuts.map(_.src).collect{case PPR(s,r) => r}
        // Regsiter mapped in empty stage
        val srampregs = p.writePort.fanIns.filter{ fi => 
          val PPR(pstage, _) = fi.src
          pstage == p.ctrler.stages.head
        }.map(_.src.asInstanceOf[PPR].reg).toList
        if ((ibpregs intersect srampregs).size == 0) throw SRAMRouting(s,p)
      case _ => () // Local write, assume always a reg mapped to write port of sram. Checked at RegAlloc
    }
    map.setSM(s, p)
      .setOP(s.readPort, p.readPort)
      .setIP(s.writeAddr, p.writeAddr)
      .setIP(s.readAddr, p.readAddr)
      .setIP(s.writePort, p.writePort)
  }

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
      val cons = List(mapSRAM(pirMap.vimap) _)
      bind(pcu.srams, cu.srams, pirMap, cons, finPass(cu) _)
    }
  }
}

case class OutOfSram(pcu:PCU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough SRAMs in ${pcu} to map application."
}
case class SRAMRouting(n:SRAM, p:PSRAM)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ${n} to ${p}"
}
