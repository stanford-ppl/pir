package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, ScalarOut => SO}
import pir.plasticine.graph.{ComputeUnit => PCU}
import pir.plasticine.graph.{ScalarBuffer => PSB}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarOutMapper extends Mapper {
  type N = SO 
  type R = PSB 
  type V = PSB 

  def printMap(m:M)(implicit p:Printer) = {
    p.emitBS("scalarOutMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"($k -> $v)")
    }
    p.emitBE
  }

  private def mapScalarOuts(n:N, p:R, map:M):M = {
    map + (n -> p)
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val sout = cu.scalarOuts.map(_._1).toList
    val psout = pcu.souts
    simAneal(psout, sout, HashMap[N, V](), List(mapScalarOuts _), OutOfScalarOut(this, pcu, _, _))
  }

}
