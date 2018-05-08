package pir.pass

import pir.node._
import pir.mapper._
import pir.codegen._

class GlobalPartioner(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

  override def runPass =  {
    val topP = compiler.top
    val pnodes = topP.collectDown[CUMap.K]()
    val cu = pnodes.filter { _.children.size > 20 }.head
    val igraph = new IgraphCodegen(cu)
    compiler.session.getCurrentRunner(igraph).run
    val vertexMap = igraph.getResult
    dbgblk(s"loadResult") {
      vertexMap.foreach { case (k,v) => dbg(s"$k -> $v") }
    }
    val partitions = vertexMap.values.toSet
    val mcus = List.fill(partitions.size - 1) { mirrorMapping.clear; mirror(cu, Some(compiler.top)) }
    val partitionMap = partitions.zip(cu::mcus).toMap
    vertexMap.foreach { case (vertex, partition) =>
      val cu = partitionMap(partition)
      swapParent(vertex, cu)
    }
  }
}
