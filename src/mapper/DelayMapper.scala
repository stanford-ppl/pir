package pir.mapper
import pir.{Design, Config}
import pir.util.typealias._
import pir.pass.PIRMapping
import pir.plasticine.graph.{PipeReg => PPR}
import pir.plasticine.util._
import pir.plasticine.main._
import pir.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class DelayMapper(implicit val design:Design) extends Mapper {
  type N = VFIFO
  type R = PVMem
  val typeStr = "VecFifoMapper"
  override def debug = Config.debugMapper
  import pirmeta.{indexOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def map(cu:MP, pirMap:M):M = {
    var mp = pirMap
    val pmcu = mp.clmap(cu)
    val rstages = pmcu.stages.filter { pst => mp.stmap.pmap.get(pst).fold(false) { st => forRead(st) } }
    val wstages = pmcu.stages.filter { pst => mp.stmap.pmap.get(pst).fold(false) { st => forWrite(st) } }
    val ridxs = rstages.map(_.index)
    val widxs = wstages.map(_.index)
    // Last stage's FU is not pipelined
    val rdelay = (if (ridxs.nonEmpty) ridxs.max - ridxs.min else 0)
    val wdelay = if (widxs.nonEmpty) widxs.max - widxs.min else 0
    dprintln(s"$cu(${quote(pmcu)}) rdelay=$rdelay wdelay=$wdelay")
    mp = mp.setRT(pmcu.sram.readPort, rdelay)
    mp = mp.setRT(pmcu.sram.writePort, wdelay)
    mp
  }

  def map(cu:CL, pirMap:M):M = {
    var mp = cu match {
      case cu:MP => map(cu, pirMap)
      case cu => pirMap
    }
    finPass(cu)(mp)
  }
}

