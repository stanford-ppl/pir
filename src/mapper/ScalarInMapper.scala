package pir.mapper
import pir._
import pir.util.typealias._
import pir.pass.PIRMapping
import pir.plasticine.util.SpadeMetadata
import scala.util.{Try, Success, Failure}
import pir.util.PIRMetadata
import pir.plasticine.main._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class ScalarInMapper(implicit val design:Design) extends Mapper {
  type N = SI
  type R = PSI 
  val typeStr = "SIMapper"
  override def debug = Config.debugSIMapper
  implicit val spade:Spade = design.arch
  val pirmeta:PIRMetadata = design
  val spademeta: SpadeMetadata = spade 
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cl:CL)(m:M):M = m 

  private def mapScalarIns(vimap:VIMap, somap:SOMap)(n:N, p:R, map:M):M = {
    val ib = vimap(n)
    val idx = somap(n.scalar.writer).index
    dprintln(s"Try $n -> $p $ib")
    assert(busesOf(p).contains(ib))
    map.setSI(n,p).setOP(n.out, p.readPort)
  }

  def resFunc(n:N, m:M, triedRes:List[R]):List[R] = {
    val ib = m.vimap(n)
    bufsOf(ib).collect{ case psi:PSI => psi }.filter { psi =>
      !triedRes.contains(psi) && !m.simap.pmap.contains(psi)
    }.toList
  }

  def map(cl:CL, pirMap:M):M = {
    val pcl = pirMap.clmap(cl)
    val sins = cl.sins
    log(s"$cl($pcl) $sins") {
      bind(sins, pirMap, mapScalarIns(pirMap.vimap, pirMap.somap) _, resFunc _, finPass(cl) _)
    }
  }

}

