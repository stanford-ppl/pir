package pir.graph.mapper
import pir.graph._
import pir._
import pir.typealias._

import scala.collection.mutable.{Map => MMap}
import scala.util.{Try, Success, Failure}

class CUP2PMapper(outputMapper:OutputMapper, viMapper:VecInMapper)(implicit val design:Design) extends CUMapper {
  type R = PCL
  type N = SCL
  val typeStr = "CUP2PMapper"

  val resMap:MMap[N, List[R]] = MMap.empty

  def mapCU(cu:N, pcu:R, pirMap:M):M = {
    val cmap = pirMap.setCL(cu, pcu) 
    /* Map CU */
    Try {
      outputMapper.map(cu, cmap)
    }.map { m =>
      viMapper.map(cu, m)
    } match {
      case Success(m) => m
      case Failure(e) => throw e
    }
  }

  val cons = List(mapCU _)

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val pcus = design.arch.cus
    val cus = design.top.innerCUs
    val nodes:List[SCL] = design.top::cus
    val reses = design.arch.top::pcus
    qualifyCheck(reses, nodes, resMap)
    def resFunc(cu:N, m:M, triedRes:List[R]):List[R] = {
      (resMap(cu).diff(triedRes)).filter { pcu => !m.clmap.pmap.contains(pcu)}
    }
    bind(nodes, m, cons, resFunc _, finPass _)
  }
}
