package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Counter => Ctr, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object CtrMapper extends Mapper {
  type R = PCtr
  type N = Ctr
  
  def next(cu:CU, cuMap:M) = {
    var cmap = RegAlloc.map(cu, cuMap)
    StageMapper.map(cu, cmap)
  }
  def map(cu:CU, cuMap:M):M = {
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    // Mapping inner counter first converges faster
    val ctrs = cu.cchains.flatMap{cc => cc.counters}.reverse 
    val finPass:Option[M => M] = Some(next(cu, _))
    simAneal(pcu.ctrs, ctrs, cuMap, List(mapCtr _), finPass, OutOfCtr(pcu, _, _))
  }

  def mapCtr(c:N, p:R, map:M):M = {
    c.dep.foreach { dep =>
      if (map.ctmap.contains(dep)) {
        val pdep = map.ctmap(dep); if (!p.isDep(pdep)) throw CtrRouting(c, p)
      }
    }
    c.deped.foreach { deped =>
      if (map.ctmap.contains(deped)) {
        val pdeped = map.ctmap(deped); if (!pdeped.isDep(p)) throw CtrRouting(c, p)
      }
    }
    val opmap = map.opmap + (c.out -> p.out)
    return map.setCt(c,p).set(opmap) 
  }

}
case class OutOfCtr(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrMapper
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
case class CtrRouting(n:Ctr, p:PCtr)(implicit design:Design) extends MappingException {
  override val mapper = CtrMapper 
  override val msg = s"Fail to map ${n} to ${p}"
}
