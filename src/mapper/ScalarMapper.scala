package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU}
import pir.plasticine.graph.{ComputeUnit => PCU}
import pir.plasticine.graph.{Port => PPort}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarMapper extends Mapper {
  type N = Int 
  type R = PPort
  type V = PPort
  def printMap(m:M)(implicit p:Printer) = {
    p.emitBS("scalarMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"($k -> $v)")
    }
    p.emitBE
  }

  private def mapScalarIns(n:N, p:R, map:M):M = {
    map + (n -> p)
  }
  private def mapScalarOuts(n:N, p:R, map:M):M = {
    map + (n -> p)
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val sin = cu.pipeline.scalarIns.toList
    val psin = pcu.sinPorts
    val sout = cu.pipeline.scalarOuts.toList
    val psout = pcu.soutPorts
    if (sin.size > psin.size) {
      throw OutOfScalarIns(pcu)
    } else if (sout.size > psout.size) {
      throw OutOfScalarOuts(pcu)
    } else {
      val imap = simAneal(psin, sin, HashMap[N, V](), List(mapScalarIns _))
      simAneal(psout, sout, imap, List(mapScalarOuts _))
    }
  }

}
