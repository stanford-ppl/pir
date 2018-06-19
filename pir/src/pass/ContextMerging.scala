package pir
package pass

import pir.node._

class ContextMerging(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

  val forward = false

  override def runPass =  {
    val cus = compiler.top.collectDown[GlobalContainer]()
    mergeContexts(cus)
  }

  def checkControl(context:ComputeContext) = {
    val ctrls = context.descendents.flatMap { x => ctrlOf.get(x) }
    val inner = innerCtrlOf(context)
    val ancestorBranch = (inner :: inner.ancestors)
    ctrls.foreach { ctrl =>
      assert(ancestorBranch.contains(ctrl), s"$ctrl and $inner are not in the same ancestorBranch but both found in $context")
    }
  }

  /* 
   * ctx1 and ctx2 are connected in data flow graph without saperated by memory
   * */
  def areLocallyConnected(ctx1:ComputeContext, ctx2:ComputeContext) = dbgblk(s"areLocallyConnected($ctx1, $ctx2)"){
    def visitLocalIn(n:PIRNode) = n match {
      case n:Memory => Nil
      case n => n.visitLocalIn(n)
    }
    def visitLocalOut(n:PIRNode) = n match {
      case n:Memory => Nil
      case n => n.visitLocalOut(n)
    }
    ctx1.canReach(ctx2, visitLocalIn) || ctx1.canReach(ctx2, visitLocalOut)
  }

  /* 
   * ctx1 and ctx2 are not data dependent 
   * */
  def areIndependent(ctx1:ComputeContext, ctx2:ComputeContext) = dbgblk(s"areIndependent($ctx1, $ctx2)"){
    !ctx1.canReach(ctx2, visitLocalIn _) && !ctx2.canReach(ctx1, visitLocalIn _)
  }

  def mergeContexts(cus:Iterable[GlobalContainer]) = {
    cus.foreach { cu =>
      dbgblk(s"Merge context in ${cu}") {
        val contexts = cu.collectDown[ComputeContext]()
        dbg(s"contexts=${contexts}")
        val merged = ListBuffer[ComputeContext]() 
        contexts.zipWithIndex.foreach { 
          case (ctx, i) if merged.contains(ctx) =>
          case (ctx, i) =>
            checkControl(ctx)
            val others = contexts.slice(i+1,contexts.size).filterNot { o => merged.contains(o) }
            dbg(s"ctx=$ctx others=$others")
            val ctxCtrlLeaf = innerCtrlOf(ctx) 
            others.foreach { other =>
              val otherCtrlLeaf = innerCtrlOf(other)
              if ((areLocallyConnected(ctx, other) || areIndependent(ctx, other)) && ctxCtrlLeaf.areLinealInherited(otherCtrlLeaf)) {
                val from = other
                val to = ctx
                dbgblk(s"merge $from into $to") {
                  merged += from
                  from.children.foreach { child => swapParent(child, to) }
                  removeNode(from)
                }
              }
            }
        }
      }
    }
  }

}
