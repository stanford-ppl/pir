package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{Counter => Ctr, InPort => IP, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{Counter => PCtr, SRAM => PSRAM, InPort => PIP, OutPort => POP, Const => PConst}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class CtrMapper(implicit val design:Design) extends Mapper {
  type R = PCtr
  type N = Ctr
  
  def finPass(cu:CU)(m:M):M = m

  def map(cu:CU, pirMap:M):M = {
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    // Mapping inner counter first converges faster
    val ctrs = cu.cchains.flatMap{cc => cc.counters}.reverse 
    val pctrs = pcu.ctrs
    map(ctrs, pctrs, pirMap, finPass(cu) _)
  }

  def map(ctrs:List[N], pctrs:List[R], initMap:M, finPass:M => M) = {
    bind(
      allRes = pctrs,
      allNodes=ctrs,
      initMap=initMap,
      constrains=List(mapCtr _), 
      resFunc=resFunc _, 
      finPass=finPass
    )
  }

  def resFunc(n:N, m:M, remainRes:List[R]):List[R] = {
    val ptop = design.arch.top
    val enCtrs = if (n.en.isConnected) {
      val dep = n.en.from.src.asInstanceOf[Ctr]
      m.ctmap.get(dep).fold(remainRes) { pdep =>
        pdep.done.fanOuts.map{ fo => fo.src.get }.collect{ case pc:R => pc }
          .filter{ pc => !m.ctmap.pmap.contains(pc) }.toList
      }
    } else { // Inner most counter
      remainRes.filter{ pc => !m.ctmap.pmap.contains(pc) && pc.en.canFrom(ptop.clk)}
    }
    val doneCtrs = n.done.to.map { d =>
      val deped = d.src.asInstanceOf[Ctr]
      m.ctmap.get(deped).fold(remainRes) { pdeped =>
        pdeped.en.fanIns.map{ fi => fi.src.get}.collect{case pc:R => pc}
          .filter{ pc => !m.ctmap.pmap.contains(pc) }.toList
      }
    }.reduceOption{ _ intersect _ }.getOrElse(remainRes)

    enCtrs intersect doneCtrs
  }

  def mapCtr(n:N, p:R, map:M):M = {
    var ipmap = map.ipmap
    var fpmap = map.fpmap
    def mapInPort(n:IP, p:PIP) = {
      ipmap += n -> p 
      n.from.src match {
        case Const(_, v) => fpmap += p -> PConst(v).out
        case _ =>
      }
    }
    mapInPort(n.min, p.min)
    mapInPort(n.max, p.max)
    mapInPort(n.step, p.step)
    return map.setCt(n,p).setOP(n.out, p.out).set(ipmap).set(fpmap)
  }

}
case class OutOfCtr(pcu:PCU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
case class CtrRouting(n:Ctr, p:PCtr)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ${n} to ${p}"
}
