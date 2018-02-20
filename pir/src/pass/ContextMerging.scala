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

  /* 
   * ctx1 and ctx2 are connected in data flow graph without saperated by memory
   * */
  def areLocallyConnected(ctx1:ComputeContext, ctx2:ComputeContext) = {
    def visitLocalIn(n:PIRNode) = n match {
      case n:Memory => Nil
      case n => super.visitLocalIn(n)
    }
    def visitLocalOut(n:PIRNode) = n match {
      case n:Memory => Nil
      case n => super.visitLocalOut(n)
    }
    canReach(ctx1, ctx2, visitLocalIn) || canReach(ctx1, ctx2, visitLocalOut)
  }

  /* 
   * ctx1 and ctx2 are not data dependent 
   * */
  def areIndependent(ctx1:ComputeContext, ctx2:ComputeContext) = {
    !canReach(ctx1, ctx2, visitLocalIn[PIRNode]) && !canReach(ctx1, ctx2, visitLocalOut[PIRNode])
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
              if ((areLocallyConnected(ctx, other) || areIndependent(ctx, other)) && areLinealInherited(ctxCtrlLeaf, otherCtrlLeaf)) {
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
