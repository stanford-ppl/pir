package pir.graph.mapper
import pir._
import pir.typealias._
import pir.graph.traversal.{PIRMapping, CtrDotPrinter}
import pir.graph.Const
import pir.plasticine.graph.{ ConstVal => PConstVal }

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

class CtrMapper(implicit val design:Design) extends Mapper {
  type R = PCtr
  type N = Ctr
  val typeStr = "CtrMapper"
  override def debug = Config.debugCTMapper
  override val exceptLimit = 200
  
  def finPass(cu:ICL)(m:M):M = m

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
      // Mapping inner counter first converges faster
      val ctrs = cu.cchains.flatMap{cc => cc.counters}.reverse 
      val pctrs = pcu.ctrs
      map(ctrs, pctrs, pirMap, finPass(cu) _)
    }
  }

  def map(ctrs:List[N], pctrs:List[R], initMap:M, finPass:M => M) = {
    bind(
      allNodes=ctrs,
      initMap=initMap,
      constrains=List(mapCtr(pctrs) _), 
      resFunc=resFunc(pctrs) _, 
      finPass=finPass
    )
  }

  def resFunc(allRes:List[R])(n:N, m:M, triedRes:List[R]):List[R] = {
    val remainRes = allRes.diff(triedRes).filter( pc => !m.ctmap.pmap.contains(pc))
    val ptop = design.arch.top
    val enCtrs = n.en.from.src match {
      case dep:Ctr if n.ctrler.inner == dep.ctrler.inner => // Counter in the same CU
        m.ctmap.get(dep).fold(remainRes) { pdep =>
          pdep.done.fanOuts.map{ fo => fo.src }.collect{ case pc:R => pc }.toList
        }
      case _:EnLUT => // Inner most counter or copied inner most counter whose enable is routed 
                      // fron network
        remainRes.filter{ pc => pc.en.canFrom(ptop.clk) } //TODO
      case d => throw PIRException(s"unknown driver of ${n}'s enable ${d}")
    }
    val doneCtrs = n.done.to.map { done =>
      done.src match {
        case deped:Ctr if n.ctrler.inner==deped.ctrler.inner =>
          m.ctmap.get(deped).fold(remainRes) { pdeped =>
            pdeped.en.fanIns.map{ fi => fi.src}.collect{case pc:R => pc}.toList
          }
        case _ => remainRes
      }
    }.reduceOption{ _ intersect _ }.getOrElse(remainRes)

    val resPool = enCtrs intersect doneCtrs
    if (resPool.size==1) {
      new CtrDotPrinter().print(allRes, m)
      println(s"here")
    }
    //println(s"$n ${n.ctrler} -------")
    //println(s"enctrs:${enCtrs} ")
    //println(s"donectrs:${doneCtrs}")
    //println(s"respool:$resPool")
    resPool
  }

  def mapCtr(pctrs:List[R])(n:N, p:R, map:M):M = {
    var ipmap = map.ipmap
    var fpmap = map.fpmap
    def mapInPort(n:IP, p:PIP):Unit = {
      ipmap += n -> p 
      n.from.src match {
        case Const(_, v) => fpmap += p -> PConstVal(v)(design.arch).out
        case _ =>
      }
    }
    mapInPort(n.min, p.min)
    mapInPort(n.max, p.max)
    mapInPort(n.step, p.step)
    dprintln(s"mapping $n -> $p")
    val mp = map.setCt(n,p).setOP(n.out, p.out).set(ipmap).set(fpmap)
    //if (n.ctrler.id==433)
      //new CtrDotPrinter().print(pctrs, mp)
    mp
  }

}
case class OutOfCtr(pcu:PCU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
case class CtrRouting(n:Ctr, p:PCtr)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ${n} to ${p}"
}
