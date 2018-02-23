package pir.pass

import pir._
import pir.node._

import pirc._
import prism.traversal._

import scala.collection.mutable
import scala.reflect._
import pirc.util._

class AccessControlLowering(implicit design:PIR) extends ControlAnalysis with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  override def runPass =  {
    traverseNode(design.top)
  }

  override def visitNode(n:N):Unit = {
    val node = transform(n)
    super.visitNode(node)
  }

  val lowered = mutable.Map[N,N]()

  def transform(n:N):N = lowered.getOrElseUpdate(n, dbgblk(s"transform($n)") {
    val node = n match {
      case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
        swapNode(n,EnabledLoadMem(mem, addr, accessDoneOf(n)).setParent(n.parent.get))
      case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) =>
        val gdata = accessDoneOf(n) match {
          case DataValid(gin) => gin
          case _ => data
        }
        swapNode(n,EnabledStoreMem(mem, addr, gdata, accessDoneOf(n)).setParent(n.parent.get))
      case Def(n:Counter, Counter(min, max, step, par)) =>
        val context = contextOf(n).head
        val cchain = collectUp[CounterChain](n).head
        if (enableOf.isDefinedAt(cchain)) {
          val counters = cchain.counters
          val idx = counters.indexOf(n)
          val en = if (idx == 0) {
            enableOf(cchain)
          } else {
            val prev = counters(idx-1)
            allocateCounterDone(transform(prev).asInstanceOf[Primitive])
          }
          val ctr = EnabledCounter(min, max, step, par, en).setParent(context)
          pirmeta.mirror(n, ctr)
          swapNode(n, ctr)
          ctr
        } else n
      case n => n
    }
    visited += node
    lowered += n -> node
    node
  })
}

