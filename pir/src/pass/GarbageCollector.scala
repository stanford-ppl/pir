package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._

import scala.collection.mutable

trait GarbageCollector { self:PIRTransformer =>

  private var aggressiveGC = false
  def withGC[T](aggressiveGC:Boolean)(block: => T) = {
    val saved = this.aggressiveGC
    this.aggressiveGC = aggressiveGC
    val res = block
    this.aggressiveGC = saved
    res
  }

  // Visit until a live node
  private def prefix(n:PIRNode) = mustLive(n)

  private def visitIn(n:PIRNode):List[PIRNode] = n match {
    case n@UnderControlBlock(cb) if depDupHasRun => visitGlobalIn(cb)
    case n if depDupHasRun => cover[PIRNode, ControlBlock](visitGlobalIn(n))
    case n => visitGlobalIn(n)
  }

  private def visitOut(n:PIRNode):List[PIRNode] = n match {
    case n@UnderControlBlock(cb) if depDupHasRun => visitGlobalOut(cb) ++ visitGlobalOut(n)
    case n => visitGlobalOut(n)
  }

  private def visitFunc(n:PIRNode):List[PIRNode] = {
    val deps = visitIn(n).toStream.filter { x => mustDead(x) }
    val parents = if (!aggressiveGC) Stream() else visitUp(n).toStream.flatMap {
      case x:Top => x.children.filter { mustDead }
      case x => (x.ancestorTree++x.leaves).filter { mustDead }
    }
    //dbg(s"$n deps=${deps.toList} parent=${parents.toList}")
    (deps ++ parents).distinct.toList
  }

  private def accumulate(prev:Set[PIRNode], n:PIRNode) = if (!prev.contains(n) && !mustLive(n)) (prev + n) else prev

  private lazy val collector = PrefixTraversal.get[PIRNode,Set[PIRNode]](prefix _, visitFunc _, accumulate _, Set.empty[PIRNode], None)

  var depDupHasRun = false
  var dramBarrierInsertionHasRun = false

  def free(nodes:PIRNode):List[PIRNode] = free(List(nodes))

  def free(nodes:Iterable[PIRNode], assertDead:Iterable[PIRNode]=Nil):List[PIRNode] = dbgblk(s"free(${nodes.map{dquote(_)}})"){
    depDupHasRun = self.as[PIRPass].compiler.hasRunAll[DependencyDuplication]
    dramBarrierInsertionHasRun = self.as[PIRPass].compiler.hasRunAll[DRAMBarrierInsertion]
    val ns = nodes.flatMap { n => 
      if (mustDead(n)) Some((n, -1))
      else None
    }.toList
    var dead = ns.flatMap { _._1.descendents }
    collector.prefix = prefix
    collector.vf = visitFunc
    collector.accumulate = accumulate
    collector.logging = None
    //collector.logging = Some(this)
    collector.resetTraversal
    dead ++= collector.traverseNodes(ns)
    dbg(s"liveNodes:${states.liveNodes}")
    assertDead.foreach { n => 
      if (!dead.contains(n)) {
        val liveOut = visitOut(n)
        val mustLive = n.descendentTree.filter { isLive(_) == Some(true) }
        bug(s"assert dead ${n} still alive! out=${liveOut} mustLive=${mustLive}")
      }
    }
    removeNodes(dead)
    dead
  }

  private def mustLive(n:PIRNode) = {
    n.descendentTree.exists { n => isLive(n) == Some(true) }
  }

  private def mustDead(n:PIRNode):Boolean = {
    if (mustLive(n)) return false
    if (collector.isVisited((n,-1))) return true
    val noLiveOut = visitOut(n).forall { x =>
      collector.isVisited((x,-1))
    }
    noLiveOut
  }

  def free(input:Input[PIRNode]):List[PIRNode] = dbgblk(s"free(${dquote(input)})") {
    val ns = input.neighbors
    input.disconnect
    free(ns)
  }

  def withLive[T](live:ND*)(block: => T):T = {
    val prev = states.liveNodes
    states.liveNodes = prev ++ live.toSet
    val res = block
    states.liveNodes = prev
    res
  }

  def addLive(live:ND*) = {
    states.liveNodes ++= live
  }
  def removeLive(nodes:ND*) = {
    states.liveNodes --= nodes
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
    case n:AccumAck if !dramBarrierInsertionHasRun => Some(true)
    case n if n.isUnder[Controller] && !depDupHasRun => Some(true)
    case n if states.liveNodes.contains(n) => Some(true)
    case n => None
  }

}
