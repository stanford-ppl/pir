package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, ScalarIn => SI}
import pir.plasticine.graph.{ComputeUnit => PCU, InBus => PVI, ScalarIn => PSI}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object ScalarInMapper extends Mapper {
  type N = SI
  type R = PSI 
  type V = PSI 

  def printMap(m:M)(implicit p:Printer) = {
    p.emitBS("scalarInMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"$k -> $v")
    }
    p.emitBE
  }

  private def mapScalarIns(cuMap:CUMapper.M)(n:N, p:R, map:M)(implicit design: Design):M = {
    val vmap = cuMap(n.ctrler.asInstanceOf[CU])._2
    val dep = n.scalar.writers.head
    val pvin = vmap(dep)
    val validOPorts = pvin.outports.filter(op => p.in.isConn(op))
    if (validOPorts.size == 0) throw ScalarInRouting(p, pvin) 
    map + (n -> p)
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
    val sin = cu.sins
    val psin = pcu.sins
    simAneal(psin, sin, HashMap[N, V](), List(mapScalarIns(cuMap) _), OutOfScalarIn(pcu, _, _))
  }

}

case class OutOfScalarIn(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = ScalarInMapper
  override val msg = s"Not enough Scalar Input Buffer in ${pcu} to map application."
}
case class ScalarInRouting(psi:PSI, pvin:PVI)(implicit design:Design) extends MappingException {
  override val mapper = ScalarInMapper
  override val msg = s"Fail to route ${psi} to ${pvin}"
}
