package pir.pass

import pir._
import pir.node._

import prism._
import prism.util._
import scala.collection.mutable

class DeadCodeElimination(implicit compiler:PIR) extends PIRTransformer with DFSBottomUpTopologicalTraversal {
  import pirmeta._

  type T = Map[N, Boolean]

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    // Mark dead code
    val liveMap = traverseNode(compiler.top, Map.empty)
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

  def markLive(liveMap:T, n:N) = liveMap + (n -> dbgblk(s"markLive:$n deped=${n.depeds.map { deped => s"$deped, liveness=${liveMap.get(deped)}"}}") {
    def depedsExistsLive(n:N) = {
      depFunc(n).exists{ d => 
        liveMap.getOrElse(d, {
          dbg(s"n=$n dependency=$d liveness unknown! be conservative here")
          true
        })
      }
    }

    n match {
      case n:ArgOut => true
      case n:StreamOut => true
      case n:Primitive if isCounter(n) && !compiler.session.hasRunAll[AccessControlLowering] => true
      case n:GlobalContainer if !compiler.session.hasRunAll[ControlAllocation] => true
      case n:Counter =>
        val CounterChain(counters) = collectUp[CounterChain](n).head
        counters.exists(depedsExistsLive)
      case n => depedsExistsLive(n) 
    }
  })

  override def visitNode(n:N, prev:T):T = {
    super.visitNode(n, markLive(prev, n))
  }

  override def check = {
    val cus = collectDown[GlobalContainer](compiler.top)
    cus.foreach { cu =>
      val mems = collectDown[Memory](cu)
      mems.foreach { mem =>
        mem match {
          case mem:ArgIn =>
          case mem:StreamIn =>
          case mem if mem.writers.isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have writer")
          case _ =>
        }
        mem match {
          case mem:ArgOut =>
          case mem:StreamOut =>
          case mem:StreamIn if mem.field == "ack" =>
          case mem if mem.readers.isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have reader")
          case _ =>
        }
      }
    }
  }

}


