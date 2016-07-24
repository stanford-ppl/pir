package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, Scalar => SL}
import pir.plasticine.graph.{ComputeUnit => PCU}
import pir.plasticine.graph.{ScalarBuffer => PSB}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarMapper extends Mapper {
  type N = SL 
  type R = PSB 
  type V = PSB 

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
    val sin = cu.pipeline.scalarIns.map(_._1).toList
    val psin = pcu.sins
    val sout = cu.pipeline.scalarOuts.map(_._1).toList
    val psout = pcu.souts
    val imap = simAneal(psin, sin, HashMap[N, V](), List(mapScalarIns _), OutOfScalarIn(this, pcu, _, _))
    simAneal(psout, sout, imap, List(mapScalarOuts _), OutOfScalarOut(this, pcu, _, _))
  }

}
