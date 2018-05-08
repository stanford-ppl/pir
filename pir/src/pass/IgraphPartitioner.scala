package pir.pass

import pir.node._
import pir.mapper._
import pir.codegen._

class IgraphPartioner(implicit compiler:PIR) extends PIRTransformer with GlobalPartioner {
  import pirmeta._

  def split(cu:GlobalContainer) = dbgblk(s"split($cu)") {
    val igraph = new IgraphCodegen(cu)
    compiler.session.getCurrentRunner(igraph).run
    val vertexMap = igraph.getResult
    dbgblk(s"loadResult") {
      vertexMap.foreach { case (k,v) => dbg(s"$k -> $v") }
    }
    val partitions = vertexMap.values.toSet
    val mcus = List.fill(partitions.size - 1) { mirrorMapping.clear; mirror(cu, Some(compiler.top)) }
    val partitionMap = partitions.zip(cu::mcus).toMap
    dbgblk(s"partitionMap") {
      partitionMap.foreach { case (p, cu) => dbg(s"$p -> $cu") }
    }
    vertexMap.foreach { case (vertex, partition) =>
      val cu = partitionMap(partition)
      swapParent(vertex, cu)
    }
    partitionMap.values.toSet
  }

}
