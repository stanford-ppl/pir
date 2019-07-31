package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._

import scala.collection.mutable

// BFS is slightly faster than DFS
trait GarbageCollector { self:PIRTransformer =>

  // Visit until a live node
  private def prefix(n:PIRNode) = isLive(n).fold(false) { l => l }

  private def visitIn(n:PIRNode):List[PIRNode] = n match {
    case n@UnderControlBlock(cb) if depDupHasRun => visitGlobalIn(cb)
    case n if depDupHasRun => cover[PIRNode, ControlBlock](visitGlobalIn(n))
    case n => visitGlobalIn(n)
  }

  private def visitOut(n:PIRNode):List[PIRNode] = n match {
    case n@UnderControlBlock(cb) if depDupHasRun => visitGlobalOut(cb)
    case n => visitGlobalOut(n)
  }

  private def visitFunc(n:PIRNode):List[PIRNode] = {
    val deps = visitIn(n).filter { x => visitOut(x).forall(x => collector.isVisited(x, -1)) }
    val parents = visitUp(n).filter { x => visitDown(x).forall(x => collector.isVisited(x, -1)) }
    dbg(s"$n deps=$deps parent=$parents")
    (deps ++ parents).distinct
  }

  private def accumulate(prev:Set[PIRNode], n:PIRNode) = if (!prev.contains(n) && (isLive(n) != Some(true))) (prev + n) else prev

  private lazy val collector = PrefixTraversal.get[PIRNode,Set[PIRNode]](prefix _, visitFunc _, accumulate _, Set.empty[PIRNode], None)

  var depDupHasRun = false

  def free(nodes:PIRNode):Unit = free(List(nodes))

  def free(nodes:Iterable[PIRNode]):Unit = dbgblk(s"free(${nodes.map{dquote(_)}})"){
    depDupHasRun = self.as[PIRPass].compiler.hasRunAll[DependencyDuplication]
    val ns = nodes.flatMap { n => 
      if (isDead(n)) Some((n, -1))
      else None
    }.toList
    var dead = ns.flatMap { _._1.descendents }
    collector.resetTraversal
    dead ++= collector.traverseNodes(ns)
    removeNodes(dead)
  }

  private def isDead(n:PIRNode):Boolean = {
    if (n.descendentTree.exists { n => isLive(n) == Some(true) }) return false
    visitOut(n).isEmpty
  }

  def free(input:Input[PIRNode]):Unit = dbgblk(s"free(${dquote(input)})") {
    val ns = input.neighbors
    input.disconnect
    free(ns)
  }

  def isLive(n:PIRNode):Option[Boolean] = n match {
    case n:HostRead => Some(true)
    case n:HostWrite => Some(true)
    case n:TokenRead => Some(true)
    case n:AssertIf => Some(true)
    case n:ExitIf => Some(true)
    case n:FringeStreamRead => Some(true)
    case n:HostInController => Some(true)
    case n:HostOutController => Some(true)
    case n:Controller if !depDupHasRun => Some(true)
    case n if n.isUnder[Controller] && !depDupHasRun => Some(true)
    case n => None
  }

}
