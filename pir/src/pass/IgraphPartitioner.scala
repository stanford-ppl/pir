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
    val partitions = List.fill(partitionMap.keys.size) { mirrorMapping.clear; mirror(cu, Some(compiler.top)) }
    dbgblk(s"partitionResult") {
      partitionMap.foreach { case (p, vertices) =>
        val cu = partitions(p)
        dbgblk(s"cu=${cu}") {
          vertices.foreach { v => swapParent(v, cu) }
        }
      }
      fixCycle(partitions)
    }
    removeNode(cu)
    partitions.toSet
  }

  val schedular = new PIRTraversal with BFSBottomUpTopologicalTraversal with prism.traversal.ScopeSchedular {
    override lazy val logger = IgraphPartioner.this.logger
    val forward = false
    override def visitIn(n:N):List[N] = visitLocalIn(n)
    override def visitOut(n:N):List[N] = visitLocalOut(n)
  }

  def fixCycle(partitions:List[GlobalContainer]) = {
    assert(partitions.size==2) // For now assume always 2 partitions
    val cu1::cu2::Nil = partitions
    val deps1 = cu1.deps.filter { _.isDescendentOf(cu2) }
    val deps2 = cu2.deps.filter { _.isDescendentOf(cu1) }
    if (deps1.nonEmpty && deps2.nonEmpty) {
      warn(s"Found cycle between $cu1 and $cu2")
      dbgblk(s"Cycle between $cu1 and $cu2") {
        dbg(s"deps1=${deps1}")
        dbgblk(s"$cu1"){
          schedular.scheduleScope(cu1).reverseIterator.foreach { n => dbg(qdef(n)) }
        }
        dbg(s"deps2=${deps2}")
        dbgblk(s"$cu2"){
          schedular.scheduleScope(cu2).reverseIterator.foreach { n => dbg(qdef(n)) }
        }
      }
    }
  }

}
