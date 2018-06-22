package pir
package pass

import pir.node._
import pir.mapper._
import pir.codegen._

class IgraphPartioner(implicit compiler:PIR) extends GlobalPartioner {
  import pirmeta._

  override def initPass = {
    super.initPass
  }

  def newIgraphCodegen(cu:GlobalContainer) = PIRConfig.option[String]("splitting-algo") match {
    case "weighted_igraph" => new IgraphCodegen(cu) with WeightedIgraphCodegen
    case "alias_igraph" => new IgraphCodegen(cu) with AliasIgraphCodegen
    case "alias_weighted_igraph" => new IgraphCodegen(cu) with AliasWeightedIgraphCodegen
  }

  def split(cu:GlobalContainer) = {
    val codegen = newIgraphCodegen(cu)
    compiler.session.getCurrentRunner(codegen).run
    val vertexMap = codegen.getResult // Node -> Partition
    val partitionMap = revertMap(vertexMap) // Partition -> Node
    val mcus = List.fill(partitionMap.keys.size - 1) { mirrorMapping.clear; mirror(cu, Some(compiler.top)) }
    val partitions = cu :: mcus
    dbgblk(s"partitionResult") {
      partitionMap.foreach { case (p, vertices) =>
        val cu = partitions(p)
        dbgblk(s"cu=${cu}") {
          vertices.foreach { v => swapParent(v, cu) }
        }
      }
    }
    partitions.toSet
  }

}
