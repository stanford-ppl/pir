package pir
package pass

import pir.node._
import scala.collection.mutable

class DeadCodeElimination(implicit compiler:PIR) extends PIRTransformer with DFSBottomUpTopologicalTraversal with UnitTraversal {
  import pirmeta._

  val forward = false

  def aggressive = PIRConfig.aggressive_dce

  val liveMap = mutable.Map[N, Boolean]()

  override def resetTraversal = {
    super.resetTraversal
    liveMap.clear
  }

  override def runPass =  {
    // Mark dead code
    traverseNode(compiler.top)
    // Remove dead code
    liveMap.foreach { 
      case (n, false) =>
        dbg(s"eliminate ${qdef(n)} from parent=${n.parent}")
        removeNode(n)
        pirmeta.removeAll(n)
      case (n, true) => 
    }
  }

  override def depFunc(n:N) = n match {
    case n:Counter => List(cchainOf(n))
    case n:CounterChain => n.children.flatMap { c => super.depFunc(c) }.toSet.toList
    case n => super.depFunc(n)
  }

  def depedsExistsLive(n:N):Boolean = {
    val depeds = depFunc(n)
    val (analyzedDepeds, unanalyzedDepeds) = depeds.partition { deped => isLive(deped).nonEmpty }
    val live = analyzedDepeds.exists { deped => isLive(deped).get }
    if (live) return true
    if (unanalyzedDepeds.isEmpty) return false
    if (aggressive) {
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

  def isLive(n:N):Option[Boolean] = n match {
    case n if liveMap.contains(n) => Some(liveMap(n))
    case n:HostRead => Some(true)
    case n:ProcessStreamOut => Some(true)
    case n:ProcessControlToken => Some(true)
    case n:CounterChain if !compiler.session.hasRunAll[ControlAllocation] => Some(true)
    case n:TokenInDef if !compiler.session.hasRunAll[ControlAllocation] => Some(true) 
    case n => None
  }

  override def isDepFree(n:N) = 
    isLive(n).nonEmpty || 
    (depFunc(n).exists { deped => isLive(deped) == Some(true)}) ||
    super.isDepFree(n)

  override def selectFrontier(unvisited:List[N]) = {
    dbgblk(s"unvisited") {
      unvisited.foreach { n => dbg(s"$n, deps=${depFunc(n)}") }
    }
    unvisited
  }

  override def visitNode(n:N):T = /*dbgblk(s"visitNode:${qdef(n)}") */{
    val live = n match {
      case n if isLive(n).nonEmpty => isLive(n).get
      case n => depedsExistsLive(n) 
    }
    liveMap += (n -> live)
    if (!live) dbg(s"live(${n})=${live}")
    super.visitNode(n)
  }

  override def finPass = {
    val cus = compiler.top.collectDown[GlobalContainer]()
    cus.foreach { cu =>
      val mems = cu.collectDown[Memory]()
      mems.foreach { mem =>
        mem match {
          case mem if (writersOf(mem) ++ resetersOf(mem)).isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have writer")
          case _ =>
        }
        mem match {
          case mem if readersOf(mem).isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have reader")
          case _ =>
        }
      }
    }
    super.finPass
  }

}
