package pir.mapper
import pir.{Design, Config}
import pir.util.typealias._
import pir.pass.PIRMapping
import pir.graph.{PipeReg => PR, VecInPR, LoadPR}
import pir.plasticine.graph.{PipeReg => PPR}
import pir.plasticine.util._
import pir.plasticine.main._
import pir.exceptions._
import pir.util.enums._
import pir.util.PIRMetadata

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class SFifoMapper(implicit val design:Design) extends Mapper with LocalRouter {
  type N = SMem
  type R = PSMem
  val typeStr = "ScalFifoMapper"
  override def debug = Config.debugSFifoMapper
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def constrain(n:N, r:R, map:M):M = {
    var mp = map
    mp = mp.setSM(n, r)
    mp = mapOutPort(n.readPort, r.readPort, mp)
    mp = mapInPort(n.writePort, r.writePort, mp)
    mp = mapMux(n.writePortMux, r.writePortMux, mp)
    mp
  }

  // After RegAlloc
  def resFunc(cu:CU)(n:N, m:M, triedRes:List[R]):List[R] = emitBlock(s"resFunc($cu.$n)"){
    val pcu = m.clmap(cu)
    val reses = cu match {
      case cu:MC if n.name.get=="data" => pcu.sbufs.filter { sbuf => nameOf(sbuf) == s"s${n.name.get}" }
      case cu:MC if cu.mctpe==TileLoad => pcu.sbufs.filter { sbuf => nameOf(sbuf) == s"r${n.name.get}" }
      case cu:MC if cu.mctpe==TileStore => pcu.sbufs.filter { sbuf => nameOf(sbuf) == s"w${n.name.get}" }
      case cu => pcu.sbufs
    }
    dprintln(s"MC filtered reses=[${reses.mkString(",")}]")
    reses.diff(triedRes).filterNot{ r => m.smmap.pmap.contains(r) }
  }

  def map(cu:CU, pirMap:M):M = {
    log(cu) {
      bind(
        allNodes=cu.smems,
        initMap=pirMap, 
        constrain=constrain _,
        resFunc=resFunc(cu) _, //(n, m, triedRes) => List[R]
        finPass=(m:M) => m
      )
    }
  }

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:CU => map(cu, pirMap)
      case cu => finPass(cu)(pirMap)
    }
  }
}
