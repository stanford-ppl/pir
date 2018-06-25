package pir
package pass

import pir.node._
import pir.mapper._
import pir.codegen._

class IgraphPartioner(implicit compiler:PIR) extends GlobalPartioner with Debugger {
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
    var partitions = List.fill(partitionMap.keys.size) { mirrorMapping.clear; mirror(cu, Some(compiler.top)) }
    dbgblk(s"partitionResult") {
      partitionMap.foreach { case (p, vertices) =>
        val cu = partitions(p)
        dbgblk(s"cu=${cu}") {
          vertices.foreach { v => swapParent(v, cu) }
        }
      }
      partitions = fixCycle(partitions)
    }
    removeNode(cu)
    partitions.toSet
  }


  // Fixing cycle by continuously moving nodes needed by the large cu in to the large cu
  def fixCycle(partitions:List[GlobalContainer]):List[GlobalContainer] = {
    assert(partitions.size==2) // For now assume always 2 partitions
    val schedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
      override lazy val logger = IgraphPartioner.this.logger
      val forward = false
      override def visitIn(n:N):List[N] = visitLocalIn(n)
      override def visitOut(n:N):List[N] = visitLocalOut(n)
      override def isDepFree(n:N) = depFunc(n).exists(isVisited)
    }
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
        //breakPoint(partitions) {
          tree.foreach { n =>
            swapParent(n, other)
          }
          //partitions
        //}
        fixCycle(partitions)
      }
    } else partitions
  }

  def hasCycle(cu1:GlobalContainer, cu2:GlobalContainer) = {
    cu1.children.exists { _.deps.exists { _.isDescendentOf(cu2) } } &&
    cu2.children.exists { _.deps.exists { _.isDescendentOf(cu1) } }
  }


  //val schedular = new PIRTraversal with BFSBottomUpTopologicalTraversal with prism.traversal.ScopeSchedular {
    //override lazy val logger = IgraphPartioner.this.logger
    //val forward = false
    //override def visitIn(n:N):List[N] = visitLocalIn(n)
    //override def visitOut(n:N):List[N] = visitLocalOut(n)
  //}

  //// Fixing cycle by putting dependent nodes in the large cu into a saperate cu
  //def fixCycle(partitions:List[GlobalContainer]):List[GlobalContainer] = {
    //assert(partitions.size==2) // For now assume always 2 partitions
    //val cu1::cu2::Nil = partitions
    //val deps1 = cu1.deps.filter { _.isDescendentOf(cu2) }
    //val deps2 = cu2.deps.filter { _.isDescendentOf(cu1) }
    //if (deps1.nonEmpty && deps2.nonEmpty) {
      //dbgblk(s"Cycle between $cu1 and $cu2") {
        //dbgblk(s"$cu1"){
          //schedular.scheduleScope(cu1).reverseIterator.foreach { n => dbg(qdef(n)) }
        //}
        //val tree1 = schedular.scheduleNodes(deps1)
        //dbg(s"deps1=${deps1}")
        //dbg(s"tree1=${tree1}")
        //dbgblk(s"$cu2"){
          //schedular.scheduleScope(cu2).reverseIterator.foreach { n => dbg(qdef(n)) }
        //}
        //val tree2 = schedular.scheduleNodes(deps2)
        //dbg(s"deps2=${deps2}")
        //dbg(s"tree2=${tree2}")
        //breakPoint(partitions) { 
          //val cu3 = if (cu1.children.size > cu2.children.size) {
            //val cu3 = mirror(cu1, container=Some(compiler.top))
            //dbgblk(s"move tree2 -> $cu3") {
              //tree2.foreach { dep => swapParent(dep, cu3) }
            //}
            //cu3
          //} else {
            //val cu3 = mirror(cu2, container=Some(compiler.top))
            //dbgblk(s"move tree1 -> $cu3") {
              //tree1.foreach { dep => swapParent(dep, cu3) }
            //}
            //cu3
          //}
          //partitions :+ cu3
        //}
      //}
    //} else partitions
  //}

  def breakPoint(origPartitions:List[GlobalContainer])(newPartitionBlk: => List[GlobalContainer]):List[GlobalContainer] = if (PIRConfig.enableBreakPoint) {
    var newPartitions:Option[List[GlobalContainer]] = None
    val act:BreakAction = {
      case ("o", bp) =>
        new PartitalDotCodegen("before.dot", origPartitions).run.open
        newPartitions = Some(newPartitionBlk)
        new PartitalDotCodegen("after.dot", newPartitions.get).run.open
        bp(())
    }
    breakPoint(s"Cycle in $origPartitions", act)
    newPartitions.getOrElse(newPartitionBlk)
  } else newPartitionBlk

}
