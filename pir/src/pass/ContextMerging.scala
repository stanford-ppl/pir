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
    mergeContexts(cus)
  }

  def checkControl(context:ComputeContext) = {
    val ctrls = ctrlsOf(context)
    val inner = innerCtrlOf(context)
    val ancestorBranch = (inner :: inner.ancestors)
    ctrls.foreach { ctrl =>
      assert(ancestorBranch.contains(ctrl), s"$ctrl and $inner are not in the same ancestorBranch but both found in $context")
    }
  }

  def visitLocal(n:PIRNode) = n match {
    case n:Memory => Nil
    case n => super.visitLocal(n)
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
            checkControl(ctx)
            val others = contexts.slice(i+1,contexts.size).filterNot { o => merged.contains(o) }
            dbg(s"ctx=$ctx others=$others")
            val ctxCtrlLeaf = innerCtrlOf(ctx) 
            others.foreach { other =>
              val otherCtrlLeaf = innerCtrlOf(other)
              if (canReach(ctx, other, visitLocal) && areLinealInherited(ctxCtrlLeaf, otherCtrlLeaf)) {
                val from = other
                val to = ctx
                dbg(s"merge $from into $to")
                merged += from
                from.children.foreach { child => swapParent(child, to) }
                removeNode(from)
              }
            }
        }
      }
    }
  }

  override def check = {
  }

}
