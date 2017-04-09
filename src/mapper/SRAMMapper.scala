package pir.mapper
import pir.{Design, Config}
import pir.util.typealias._
import pir.pass.PIRMapping
import pir.graph.{PipeReg => PR, VecInPR, LoadPR}
import pir.plasticine.graph.{PipeReg => PPR}
import pir.plasticine.util._
import pir.plasticine.main._
import pir.exceptions._
import pir.util.PIRMetadata

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class SramMapper(implicit val design:Design) extends Mapper {
  type N = SRAM
  type R = PSRAM
  val typeStr = "SramMapper"
  override def debug = Config.debugSramMapper
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:ICL)(m:M):M = m 

  def constrain(n:N, r:R, m:M):M = {
    m.setSM(n, r)
      .setOP(n.readPort, r.readPort)
      .setIP(n.writePort, r.writePort)
      .setIP(n.writeAddr, r.writeAddr)
      .setIP(n.readAddr, r.readAddr)
  }

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      val pcu = pirMap.clmap(cu).asCU
      val srams = cu.srams
      val psrams = pirMap.clmap(cu).asCU.srams
      bind(
        allRes=psrams,
        allNodes=srams,
        initMap=pirMap,
        constrain=constrain _,
        finPass=(m:M) => m
      )
    }
  }

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:ICL => map(cu, pirMap)
      case cu => pirMap
    }
  }
}

