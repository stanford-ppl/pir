package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, ScalarIn => SI}
import pir.plasticine.graph.{ComputeUnit => PCU}
import pir.plasticine.graph.{ScalarBuffer => PSB}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarInMapper extends Mapper {
  type N = SI
  type R = PSB 
  type V = PSB 

  def printMap(m:M)(implicit p:Printer) = {
    p.emitBS("scalarInMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"($k -> $v)")
    }
    p.emitBE
  }

  private def mapScalarIns(n:N, p:R, map:M):M = {
    map + (n -> p)
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val sin = cu.scalarIns.map(_._1).toList
    val psin = pcu.sins
    simAneal(psin, sin, HashMap[N, V](), List(mapScalarIns _), OutOfScalarIn(this, pcu, _, _))
  }

}
