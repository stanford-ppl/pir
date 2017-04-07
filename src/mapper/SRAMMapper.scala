package pir.mapper
import pir.{Design, Config}
import pir.util.typealias._
import pir.pass.PIRMapping
import pir.graph.{PipeReg => PR, VecInPR}
import pir.plasticine.graph.{PipeReg => PPR}
import pir.plasticine.util._
import pir.plasticine.main._
import pir.exceptions._
import pir.util.PIRMetadata

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class SRAMMapper(implicit val design:Design) extends Mapper {
  type N = OnMem 
  type R = PSRAM 
  val typeStr = "SramMapper"
  override def debug = Config.debugSMMapper
  implicit val spade:Spade = design.arch
  val pirmeta:PIRMetadata = design
  val spademeta: SpadeMetadata = spade
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:ICL)(m:M):M = m 

  // Not Used
  private def mapSRAM(vimap:VIMap)(s:N, p:R, map:M):M = {
    s.writePort.from.src match {
      case vi:VI =>
        val ib = vimap(vi)
        val buf = { val bufs = bufsOf(ib); assert(bufs.size==1); bufs.head }
        val vopregs = mappingOf(buf.in)
        // Regsiter mapped in empty stage
        val srampregs = p.writePort.fanIns.filter{ fi => 
          val PPR(pstage, _) = fi.src
          pstage == p.ctrler.stages.head
        }.map(_.src.asInstanceOf[PPR].reg).toList
        if ((vopregs intersect srampregs).size == 0) throw new SRAMRouting(s,p,map)
      case _ => () // Local write, assume always a reg mapped to write port of sram. Checked at RegAlloc
    }
    var mp = map.setSM(s, p).setOP(s.readPort, p.readPort).setIP(s.writePort, p.writePort)
    s match { case s:SOW => mp = mp.setIP(s.writeAddr, p.writeAddr); case _ => }
    s match { case s:SOR => mp = mp.setIP(s.readAddr, p.readAddr); case _ => }
    mp
  }

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
      //val cons = List(mapSRAM(pirMap.vimap) _)
      //bind(pcu.srams, cu.mems, pirMap, cons, finPass(cu) _)
      
      //One to one mapping. No need to map
      var mp = pirMap
      cu.mems.filter{_.isRemoteWrite}.foreach { s =>
        val vi = s.writePort.from.src
        val ib = mp.vimap(vi)
        val ps = pcu.srams(indexOf(mp.vimap(vi)))
        mp = mp.setSM(s, ps).setOP(s.readPort, ps.readPort).setIP(s.writePort, ps.writePort)
        s match { case s:SOW => mp = mp.setIP(s.writeAddr, ps.writeAddr); case _ => }
        s match { case s:SOR => mp = mp.setIP(s.readAddr, ps.readAddr); case _ => }
        mp
      }
      cu.mems.filter{!_.isRemoteWrite}.foreach { s =>
        val ps = pcu.srams.filter { p => !mp.smmap.pmap.contains(p) }.head
        mp = mp.setSM(s, ps).setOP(s.readPort, ps.readPort).setIP(s.writePort, ps.writePort)
        s match { case s:SOW => mp = mp.setIP(s.writeAddr, ps.writeAddr); case _ => }
        s match { case s:SOR => mp = mp.setIP(s.readAddr, ps.readAddr); case _ => }
        mp
      }
      mp
    }
  }
}

case class SRAMRouting(n:OnMem, p:PSRAM, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Fail to map ${n} to ${p}"
}
