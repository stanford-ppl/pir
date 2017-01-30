package pir.graph.mapper
import pir._
import pir.typealias._
import pir.graph.traversal.PIRMapping
import scala.util.{Try, Success, Failure}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class ScalarInMapper(implicit val design:Design) extends Mapper with Metadata {
  type N = SI
  type R = PSI 
  val typeStr = "SIMapper"

  override def debug = Config.debugSIMapper
  def finPass(cl:CL)(m:M):M = m 

  private def mapScalarIns(vimap:VIMap, somap:SOMap)(n:N, p:R, map:M):M = {
    val ib = vimap(vecOf(n).asInstanceOf[VI])
    val idx = somap(n.scalar.writer).idx
    dprintln(s"Try $n -> $p $ib")
    assert(p.in.canFrom(ib.outports(idx)))
    map.setSI(n,p).setOP(n.out, p.out)
  }

  def resFunc(n:N, m:M, triedRes:List[R]):List[R] = {
    val ib = m.vimap(vecOf(n).asInstanceOf[VI])
    val idx = m.somap(n.scalar.writer).idx
    ib.outports(idx).fanOuts.map{_.src}.collect{ case psi:R => psi}.filter{ psi =>
      !triedRes.contains(psi) && !m.simap.pmap.contains(psi)
    }.toList
  }

  def map(cl:CL, pirMap:M):M = {
    val pcl = pirMap.clmap(cl)
    val sins = cl.sins
    val cons = List(mapScalarIns(pirMap.vimap, pirMap.somap) _)
    log(s"$cl($pcl) $sins") {
      bind(sins, pirMap, cons, resFunc _, finPass(cl) _)
    }
  }

}

case class OutOfScalarIn(pcl:PCL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Input Buffer in ${pcl} to map application."
}
case class ScalarInRouting(n:SI, p:PSI)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ${n} to ${p}"
}

