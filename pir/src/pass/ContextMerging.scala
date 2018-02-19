package pir.pass

import pir._
import pir.node._

import pirc._

import scala.collection.mutable
import scala.reflect._
import pirc.util._

class ContextMerging(implicit design:PIR) extends PIRTransformer {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    val cus = collectDown[GlobalContainer](design.top)
    setControlChain(cus)
    mergeContexts(cus)
  }

  def setControlChain(cus:Iterable[GlobalContainer]):Unit = {
    val contexts = collectDown[ComputeContext](design.top)
    contexts.foreach { context => setControlChain(context) }
  }

  def setControlChain(context:ComputeContext):Unit = {
    ctrlChainOf(context) = dbgblk(s"analyze controls for $context in ${globalOf(context)}") {
      val ctrls = context.descendents.map { prim =>
        ctrlOf(prim)
      }.toSet[Controller].toList.sortBy { _.ancestors.size }.reverse
      dbg(s"sorted ctrls:${ctrls}")
      ctrls.zipWithIndex.flatMap { case (ctrl, i) =>
        val next = if (i==(ctrls.size-1)) None else Some(ctrls(i+1))
        if (next.fold(true) { _.isParentOf(ctrl) }) {
          List(ctrl)
        } else {
          val nextIdx = ctrl.ancestors.indexOf(next.get)
          assert(nextIdx != -1, s"${next.get} is not ancestor of $ctrl!")
          ctrl :: ctrl.ancestors.slice(0, nextIdx)
        }
      }
    }
  }

  def mergeContexts(cus:Iterable[GlobalContainer]) = {
    cus.foreach { cu =>
      dbgblk(s"Merge context in ${cu}") {
        val contexts = collectDown[ComputeContext](cu)
        dbg(s"contexts=${contexts}")
        val merged = mutable.ListBuffer[ComputeContext]() 
        contexts.zipWithIndex.foreach { 
          case (ctx, i) if merged.contains(ctx) =>
          case (ctx, i) =>
            val others = contexts.slice(i+1,contexts.size).filterNot { o => merged.contains(o) }
            dbg(s"ctx=$ctx others=$others")
            others.foreach { other =>
              val ctxCtrlLeaf = ctrlChainOf(ctx).head
              val otherCtrlLeaf = ctrlChainOf(other).head
              if (!areWeaklyConnected(ctx, other) && areLinealInherited(ctxCtrlLeaf, otherCtrlLeaf)) {
                val from = other
                val to = ctx
                dbg(s"merge $from into $to")
                merged += from
                from.children.foreach { child => swapParent(child, to) }
                removeNode(from)
                ctrlChainOf.removeKey(ctx)
                setControlChain(ctx)
              }
            }
        }
      }
    }
  }

  override def check = {
  }

}
