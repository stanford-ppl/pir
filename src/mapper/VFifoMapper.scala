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

class VFifoMapper(implicit val design:Design) extends Mapper {
  type N = VFIFO
  type R = PVMem
  val typeStr = "VecFifoMapper"
  override def debug = Config.debugSMMapper //TODO
  implicit val spade:Spade = design.arch
  val pirmeta:PIRMetadata = design
  val spademeta: SpadeMetadata = spade
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:ICL)(m:M):M = m 

  def constrain(n:N, r:R, m:M):M = {
    m.setSM(n, r)
      .setOP(n.readPort, r.readPort)
      .setIP(n.writePort, r.writePort)
  }

  def resFunc(n:N, m:M, triedRes:List[R]):List[R] = {
    val vi = m.vimap(n.writePort.from.asInstanceOf[VI])
    val r = vi.ic.fanOuts.head.src.asInstanceOf[R]
    List(r)
  }

  def map(cu:ICL, pirMap:M):M = {
    val pcu = pirMap.clmap(cu).asCU
    log(cu) {
      bind(
        allNodes=cu.vfifos,
        initMap=pirMap, 
        constrain=constrain _,
        resFunc=resFunc _, //(n, m, triedRes) => List[R]
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

