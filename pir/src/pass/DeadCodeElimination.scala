package pir.pass

import pir.node._
import prism.traversal._
import scala.collection.mutable

class DeadCodeElimination(implicit compiler:PIR) extends PIRTransformer with DFSBottomUpTopologicalTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

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
        val parent = n.parent
        dbg(s"eliminate ${qdef(n)} from parent=${parent}")
        val neighbors = n.neighbors
        removeNode(n)
        n.parent.foreach { parent =>
          assert(!parent.children.contains(n), s"$parent still contains $n after removeNode")
        }
        neighbors.foreach { nb =>
          dbg(s"neighbor=$nb, neighbor.neighbors=${nb.neighbors}")
          assert(!nb.neighbors.asInstanceOf[Set[N]].contains(n))
        }
        pirmeta.removeAll(n)
      case (n, true) => 
    }
  }

  def depedsExistsLive(n:N):Boolean = {
    val (analyzedDepeds, unanalyzedDepeds) = depFunc(n).partition { deped => isLive(deped).nonEmpty }
    val live = analyzedDepeds.exists { deped => isLive(deped).get }
    if (live) return true
    if (unanalyzedDepeds.isEmpty) return false
    if (aggressive) {
      dbg(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be aggressive here")
      return false
    } else {
      dbg(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be conservative here")
      return true
    }
  }

  def isLive(n:N):Option[Boolean] = n match {
    case n if liveMap.contains(n) => Some(liveMap(n))
    case n:ArgOut => Some(true)
    case n:StreamOut => Some(true)
    case n:TokenOut => Some(true)
    case n:Primitive if isCounter(n) && !compiler.session.hasRunAll[AccessControlLowering] => Some(true)
    //case n:GlobalContainer if !compiler.session.hasRunAll[ControlAllocation] => Some(true)
    case n => None
  }

  def markLive(n:N) = dbgblk(s"markLive:$n deped=${n.depeds.map { deped => s"$deped, liveness=${liveMap.get(deped)}"}}") {
    val live = n match {
      case n if isLive(n).nonEmpty => isLive(n).get
      case n:Counter =>
        val CounterChain(counters) = n.collectUp[CounterChain]().head
        counters.exists(depedsExistsLive)
      case n => depedsExistsLive(n) 
    }
    liveMap += (n -> live)
    live
  }

  override def isDepFree(n:N) = 
    isLive(n).nonEmpty || 
    (depFunc(n).exists { deped => isLive(deped) == Some(true)}) ||
    super.isDepFree(n)

  override def scheduleDepFree(nodes:List[N]):List[N] = {
    val unvisited = nodes.filterNot(isVisited) 
    var depFree = unvisited.filter(isDepFree) 
    if (unvisited.nonEmpty && depFree.isEmpty) {
      dbgblk(s"unvisited") {
        unvisited.foreach { n => dbg(s"$n, deps=${depFunc(n)}") }
      }
    }
    super.scheduleDepFree(nodes)
  }

  override def visitNode(n:N):T = {
    markLive(n)
    super.visitNode(n)
  }

  override def check = {
    val cus = compiler.top.collectDown[GlobalContainer]()
    cus.foreach { cu =>
      val mems = cu.collectDown[Memory]()
      mems.foreach { mem =>
        mem match {
          case mem:ArgIn =>
          case mem:StreamIn =>
          case mem if writersOf(mem).isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have writer")
          case _ =>
        }
        mem match {
          case mem:TokenOut =>
          case mem:ArgOut =>
          case mem:StreamOut =>
          case mem if readersOf(mem).isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have reader")
          case _ =>
        }
      }
    }
  }

}
