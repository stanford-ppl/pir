package pir
package pass

import pir.node._
import pir.mapper._
import pir.codegen._

trait IgraphPartitioner extends GlobalPartioner { self =>
  import pirmeta._
  import PIRConfig._

  def newIgraphCodegen(cu:GlobalContainer) = option[String]("splitting-algo") match {
    case "weighted_igraph" => new IgraphCodegen(cu) with WeightedIgraphCodegen
    case "alias_igraph" => new IgraphCodegen(cu) with AliasIgraphCodegen
    case "alias_weighted_igraph" => new IgraphCodegen(cu) with AliasWeightedIgraphCodegen
  }

  override def split(cu:GlobalContainer, fit:CUMap.K => Boolean) = 
  if (option[String]("splitting-algo").contains("igraph")){
    val codegen = newIgraphCodegen(cu)
    codegen.run
    val vertexMap = codegen.getResult // Node -> Partition
    val partitionMap = reverseMap(vertexMap) // Partition -> Node
    var partitions:List[GlobalContainer] = List.fill(partitionMap.keys.size) { 
      withParent(compiler.top) { 
        val m = CUContainer()
        pirmeta.mirror(cu, m)
        m
      }
    }
    dbgblk(s"partitionResult") {
      breakPoint(List(cu), s"split $cu") {
        partitionMap.foreach { case (p, vertices) =>
          val cu = partitions(p)
          dbgblk(s"cu=${cu}") {
            vertices.foreach { v => swapParent(v, cu) }
          }
        }
        partitions
      }
      partitions = fixCycle(partitions)
    }
    removeNode(cu)
    partitions.toSet
  } else super.split(cu, fit)

  private val schedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    override lazy val logger = self.logger
    val forward = false
    override def visitIn(n:N):List[N] = visitLocalIn(n)
    override def visitOut(n:N):List[N] = visitLocalOut(n)
    override def isDepFree(n:N) = depFunc(n).exists(isVisited)
  }

  // TODO: this might run into infinite loop if nodes get moved to cu1 and then moved to cu2.
  // Example P4 on DMeshCB4x4
  // Fixing cycle by continuously moving nodes needed by the large cu in to the large cu
  def fixCycle(partitions:List[GlobalContainer]):List[GlobalContainer] = {
    assert(partitions.size==2, s"partition size != 2 $partitions") // For now assume always 2 partitions
    val cu1::cu2::Nil = partitions
    val deps1 = cu1.deps.filter { _.isDescendentOf(cu2) }
    val deps2 = cu2.deps.filter { _.isDescendentOf(cu1) }
    if (hasCycle(cu1, cu2)) {
      def otherCU(n:N) = {
        val cu = globalOf(n).get
        cu match {
          case `cu1` => cu2
          case `cu2` => cu1
        }
      }
      dbgblk(s"Cycle between $cu1 and $cu2") {
        val deps1 = cu1.deps.filter { _.isDescendentOf(cu2) }
        val deps2 = cu2.deps.filter { _.isDescendentOf(cu1) }
        val dep = (deps1 ++ deps2).foldLeft[Option[N]](None) { 
          case (None, dep) =>
            val other = otherCU(dep)
            val tree = schedular.scheduleNode(dep)
            if (tree.forall { n => n.deps.forall { !_.isDescendentOf(other) } }) {
              Some(dep)
            } else None
          case (prev, dep) => prev
        }.getOrElse(throw PIRException(s"This shouldn't happen. The original dataflow is not a dag!"))
        val tree = schedular.scheduleNode(dep)
        val other = otherCU(dep)
        breakPoint(partitions, s"Cycle in $partitions") {
          tree.foreach { n =>
            swapParent(n, other)
          }
          partitions
        }
        fixCycle(partitions)
      }
    } else partitions
  }

  def hasCycle(cu1:GlobalContainer, cu2:GlobalContainer) = {
    cu1.children.exists { _.deps.exists { _.isDescendentOf(cu2) } } &&
    cu2.children.exists { _.deps.exists { _.isDescendentOf(cu1) } }
  }

}
