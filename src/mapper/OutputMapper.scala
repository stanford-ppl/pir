package pir.mapper
import pir._
import pir.util.typealias._
import pir.util.enums._
import pir.pass.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class OutputMapper(implicit val design:PIR) extends Mapper {
  type N = VO 
  type R = PGO[_<:PModule]
  val typeStr = "SOMapper"
  override def debug = Config.debugSOMapper

  def finPass(scl:CL)(m:M):M = m

  private def mapVecOut(scl:CL)(n:N, p:R, cuMap:M):M = {
    val mp = cuMap.setVO(n,p)
    n match {
      case dvo:DVO => mp
        //TODO
        //dvo.scalarOuts.zipWithIndex.foldLeft(mp) { case (pm, (so, i)) =>
          //val psos = p.inports(i).fanIns.map(_.src).collect{ case pso:PSO => pso }
          //assert(psos.size==1)
          //val pso = psos.head
          //pm.setSO(so, pso).setIP(so.in, pso.in)
        //}
      case _ => mp 
    } 
  }

  def map(scl:CL, cuMap:M):M = {
    val pcl = cuMap.pmmap(scl).asInstanceOf[PCL]
    scl match {
      case top:Top =>
        bind(pcl.vouts, scl.vouts, cuMap, mapVecOut(scl) _, finPass(scl) _)
      case c:CL if (c.vouts.size==1) => 
        mapVecOut(c)(c.vouts.head, pcl.vouts.head, cuMap)
      case c:CL if (c.vouts.size==0) => cuMap
    }
  }
}

