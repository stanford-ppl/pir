package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._

import scala.collection.mutable

// BFS is slightly faster than DFS
class DeadCodeElimination(implicit compiler:PIR) extends PIRTraversal with PIRTransformer with BFSBottomUpTopologicalTraversal with UnitTraversal {

  val forward = false

  val liveMap = mutable.Map[N, Boolean]()

  override def resetTraversal = {
    super.resetTraversal
    liveMap.clear
  }

  override def runPass =  {
    depDupHasRun = compiler.hasRunAll[DependencyDuplication]
    // Mark dead code
    traverseTop
    // Remove dead code
    val toRemove = liveMap.flatMap { case (n, false) => Some(n); case (n, true) => None }
    removeNodes(toRemove)
  }

  // Breaking loop in traversal
  override def visitIn(n:N):List[N] = n match {
    case n:LocalOutAccess => n.in.neighbors.toList ++ 
      n.done.neighbors.filterNot { case c:Controller => true; case _ => false } ++
      n.en.neighbors.filterNot { case c:Controller => true; case _ => false }
    case n@UnderControlBlock(cb) if depDupHasRun => 
      super.visitIn(n) ++
      super.visitIn(cb)
    case n if depDupHasRun => cover[PIRNode, ControlBlock](super.visitIn(n))
    case n => super.visitIn(n)
  }

  override def visitOut(n:N):List[N] = n match {
    case n:CounterIter => super.visitOut(n)
    case n:CounterValid => super.visitOut(n)
    case n:CounterReset => super.visitOut(n)
    case n@UnderControlBlock(cb) if depDupHasRun => 
      super.visitOut(n) ++
      super.visitOut(cb).tryFilter { case x:LocalOutAccess => false; case _ => true }.toList
    case n:ControlBlock if depDupHasRun => 
      super.visitOut(n).tryFilter { case x:LocalOutAccess => false; case _ => true }.toList
    case n => super.visitOut(n)
  }

  def depedsExistsLive(n:N):Boolean = {
    val depeds = depFunc(n)
    val (analyzedDepeds, unanalyzedDepeds) = depeds.partition { deped => isLive(deped).nonEmpty }
    val live = analyzedDepeds.exists { deped => isLive(deped).get }
    if (live) return true
    if (unanalyzedDepeds.isEmpty) return false
    if (config.aggressive_dce) {
      dbg(s"depeds=${depeds.map { deped => (deped, isLive(deped)) }}")
      dbg(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be aggressive here")
      //warn(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be aggressive here")
      return false
    } else {
      dbg(s"depeds=${depeds.map { deped => (deped, isLive(deped)) }}")
      dbg(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be conservative here")
      //warn(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be conservative here")
      return true
    }
  }

  override def isLive(n:N):Option[Boolean] = n match {
    case n if liveMap.contains(n) => Some(liveMap(n))
    case n => super.isLive(n)
  }

  override def isDepFree(n:N) = 
    isLive(n).nonEmpty || 
    (depFunc(n).exists { deped => isLive(deped) == Some(true)}) ||
    super.isDepFree(n)

  override def selectFrontier(unvisited:List[N]) = {
    if (config.aggressive_dce) {
      dbgblk(s"Aggressive DCE: unvisited all dead") {
        unvisited.foreach { n => 
          liveMap += (n -> false)
          dbg(s"$n, deps=${depFunc(n)}")
        }
      }
    } else {
      dbgblk(s"Conservative DCE: unvisited all live") {
        unvisited.foreach { n => 
          liveMap += (n -> true)
          dbg(s"$n, deps=${depFunc(n)}")
        }
      }
    }
    Nil
  }

  override def visitNode(n:N):T = /*dbgblk(s"visitNode:${quote(n)}")*/ {
    val live = isLive(n).getOrElse(depedsExistsLive(n))
    liveMap += (n -> live)
    if (!live) dbg(s"live(${n})=${live}")
    super.visitNode(n)
  }

}
