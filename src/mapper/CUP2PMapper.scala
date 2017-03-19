package pir.mapper
import pir.graph._
import pir._
import pir.typealias._

import scala.collection.mutable.{Map => MMap}
import scala.util.{Try, Success, Failure}

class CUP2PMapper(outputMapper:OutputMapper, viMapper:VecInMapper)(implicit design:Design) extends CUMapper {
  type R = PNE
  type N = CL
  override def typeStr = "CUP2PMapper"

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

  override def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val pcus = design.arch.cus
    val cus = design.top.innerCUs
    val nodes:List[CL] = design.top::cus
    val reses = design.arch.top::pcus
    def resFunc(cu:N, m:M, triedRes:List[R]):List[R] = {
      (resMap(cu).diff(triedRes)).filter { pne => !m.clmap.pmap.contains(pne)}
    }
    bind(nodes, m, mapCU _, resFunc _, finPass _)
  }
}
