package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._

import scala.collection.mutable

trait GarbageCollector { self:PIRTransformer =>

  private var aggressiveGC = true
  def withGC[T](aggressiveGC:Boolean)(block: => T) = {
    val saved = this.aggressiveGC
    this.aggressiveGC = aggressiveGC
    val res = block
    this.aggressiveGC = saved
    res
  }

  // Visit until a live node
  private def prefix(n:PIRNode) = mustLive(n)

  private def visitIn(n:PIRNode):Stream[PIRNode] = n match {
    case n@UnderControlBlock(cb) if depDupHasRun => visitGlobalIn(cb)
    case n if depDupHasRun => cover[PIRNode, ControlBlock](visitGlobalIn(n))
    case n => visitGlobalIn(n)
  }

  private def visitOut(n:PIRNode):Stream[PIRNode] = n match {
    case n@UnderControlBlock(cb) if depDupHasRun => visitGlobalOut(cb)
    case n => visitGlobalOut(n)
  }

  private def visitFunc(n:PIRNode):Stream[PIRNode] = {
    val deps = visitIn(n).toStream.filter { x => mustDead(x) }
    val parents = visitUp(n).toStream.flatMap { x => 
      if (aggressiveGC) {
        (x.ancestorTree++x.leaves).filter { x => mustDead(x) }
      } else {
        if (mustDead(x)) Stream(x) else Stream()
      }
    }
    dbg(s"$n deps=${deps} parent=${parents}")
    (deps ++ parents).distinct.toStream
  }

  private def accumulate(prev:Set[PIRNode], n:PIRNode) = if (!prev.contains(n) && !mustLive(n)) (prev + n) else prev

  private lazy val collector = PrefixTraversal.get[PIRNode,Set[PIRNode]](prefix _, visitFunc _, accumulate _, Set.empty[PIRNode], None)

  var depDupHasRun = false

  def free(nodes:PIRNode):Unit = free(Stream(nodes))

  def free(nodes:Iterable[PIRNode]):Unit = dbgblk(s"free(${nodes.map{dquote(_)}})"){
    depDupHasRun = self.as[PIRPass].compiler.hasRunAll[DependencyDuplication]
    val ns = nodes.flatMap { n => 
      if (mustDead(n)) Some((n, -1))
      else None
    }.toStream
    var dead = ns.flatMap { _._1.descendents }
    collector.prefix = prefix
    collector.vf = visitFunc
    collector.accumulate = accumulate
    //collector.logging = Some(this)
    collector.logging = None
    collector.resetTraversal
    dead ++= collector.traverseNodes(ns)
    removeNodes(dead)
  }

  private def mustLive(n:PIRNode) = {
    n.descendentTree.exists { n => isLive(n) == Some(true) || liveNodes.contains(n) }
  }

  private def mustDead(n:PIRNode):Boolean = {
    if (mustLive(n)) return false
    if (collector.isVisited((n,-1))) return true
    val noLiveOut = visitOut(n).forall { x =>
      collector.isVisited((x,-1))
    }
    noLiveOut
  }

  def free(input:Input[PIRNode]):Unit = dbgblk(s"free(${dquote(input)})") {
    val ns = input.neighbors
    input.disconnect
    free(ns)
  }

  private var liveNodes:Set[Any] = Set.empty
  def withLive[T](live:Any*)(block: => T):T = {
    val prev = liveNodes
    liveNodes = live.toSet
    val res = block
    liveNodes = prev
    res
  }

  def isLive(n:PIRNode):Option[Boolean] = n match {
    case n:Top => Some(true)
    case n:HostRead => Some(true)
    case n:HostWrite => Some(true)
    case n:TokenRead => Some(true)
    case n:AssertIf => Some(true)
    case n:PrintIf => Some(true)
    case n:ExitIf => Some(true)
    case n:FringeStreamRead => Some(true)
    case n:HostInController => Some(true)
    case n:HostOutController => Some(true)
    case n:Controller if !depDupHasRun => Some(true)
    case n if n.isUnder[Controller] && !depDupHasRun => Some(true)
    case n => None
  }

}
