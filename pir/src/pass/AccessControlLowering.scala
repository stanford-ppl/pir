package pir.pass

import pir.node._

import scala.collection.mutable

class AccessControlLowering(implicit compiler:PIR) extends ControlAnalysis with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N):Unit = {
    val node = transform(n)
    super.visitNode(node)
  }

  val lowered = mutable.Map[N,N]()

  def lowered(n:Def) = {
    n match {
      case enOut:ContextEnableOut => enOut.collectPeer[ContextEnable]().head
      case n => n
    }
  }

  def transform(n:N):N = lowered.getOrElseUpdate(n, dbgblk(s"transform($n)") {
    val node = n match {
      case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
        swapNode(n,EnabledLoadMem(mem, addr, lowered(accessDoneOf(n))).setParent(n.parent.get))
      case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) =>
        val gdata = accessDoneOf(n) match {
          case DataValid(gin) => gin
          case _ => data
        }
        swapNode(n,EnabledStoreMem(mem, addr, gdata, lowered(accessDoneOf(n))).setParent(n.parent.get))
      case Def(n:Counter, Counter(min, max, step, par)) =>
        val context = contextOf(n).head
        val cchain = n.collectUp[CounterChain]().head
        if (enableOf.isDefinedAt(cchain)) {
          val counters = cchain.counters
          val idx = counters.indexOf(n)
          val en = if (idx == 0) {
            lowered(enableOf(cchain))
          } else {
            val prev = counters(idx-1)
            allocateCounterDone(transform(prev).asInstanceOf[Primitive])
          }
          val ctr = EnabledCounter(min, max, step, par, en).setParent(context)
          pirmeta.mirror(n, ctr)
          swapNode(n, ctr)
        } else n
      case n => n
    }
    visited += node
    lowered += n -> node
    node
  })
}

