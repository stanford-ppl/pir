package pir
package pass

import prism.util._
import pir.node._

import scala.collection.mutable

trait ContextLinearizer extends PIRTransformer with PIRNodeUtil with Logging with prism.traversal.GraphUtil with AccessControlUtil with Memorization { self:ControlAllocation =>
  implicit val compiler:PIR
  val pirmeta:PIRMetadata
  import pirmeta._

  def linearizeAccessContext(mem:Memory):List[Memory] = 
    logger.withOpen(compiler.outDir, s"ContextLinearizer.log", append=true) {
      dbgblk(s"linearizeAccessContext($mem)") {
        withMemory {
          val ctxs = accessesOf(mem).map { a => contextOf(a).get }.toSet.toList
          ctxs.foreach { ctx => assert(isInCtx(ctx, mem) != isOutCtx(ctx, mem)) }
          val sorted = sortContexts(mem, ctxs)
          muteAccess(mem, sorted)
          tokenInsertion(mem, sorted)
        }
      }
    }

  private def isInCtx(ctx:ComputeContext, mem:Memory) = accessesOf(ctx, mem).forall(isInAccess)
  private def isOutCtx(ctx:ComputeContext, mem:Memory) = accessesOf(ctx, mem).forall(isOutAccess)

  private def ctxDetail(ctx:ComputeContext, mem:Memory) = {
    s"$ctx: ${accessesOf(ctx, mem).map { a => s"$a ${srcOrderOf.get(a)}"}.mkString(" ")}"
  }

  private def ordersOf(ctx:ComputeContext, mem:Memory):Option[List[Int]] = {
    val as = accessesOf(ctx, mem).toList
    val fromSrc = assertUnify(as, s"srcOrderOf.contains") { a => srcOrderOf.contains(a) }.get
    if (fromSrc) {
      Some(as.map { a => srcOrderOf(a) })
    } else {
      None
    }
  }

  private def sortContexts(mem:Memory, ctxs:List[ComputeContext]) = {
    val sorted = ctxs.sortWith { case (ctx1, ctx2) =>
      val order1 = ordersOf(ctx1, mem)
      val order2 = ordersOf(ctx2, mem) 
      zipOption(order1, order2).fold[Boolean] {
        assertOne(List(ctx1, ctx2).filter { ctx => isInCtx(ctx, mem) }, s"inCtx")
        isInCtx(ctx1, mem)
      } { case (order1, order2) =>
        if (order1.max < order2.min) {
          true
        } else if (order1.min > order2.max) {
          false
        } else if (order1.min == order2.max && order1.max == order2.min) {
          assertOne(List(ctx1, ctx2).filter { ctx => isInCtx(ctx, mem) }, s"inCtx")
          isInCtx(ctx1, mem)
        } else {
          var msg = s"$mem's access context contains accesses with overlapping program order\n"
          msg += ctxDetail(ctx1, mem)
          msg += ctxDetail(ctx2, mem)
          throw PIRException(msg)
        }
      }
    }

    sorted.foreach { ctx =>
      dbg(ctxDetail(ctx, mem))
    }
    sorted
  }

  private def muteAccess(mem:Memory, sorted:List[ComputeContext]) = {
    sorted.sliding(size=2,step=1).foreach { 
      case List(ctx1, ctx2) if isInCtx(ctx1, mem) && isOutCtx(ctx2, mem) => 
      case List(ctx1, ctx2) if isInCtx(ctx1, mem) =>
        dbg(s"MuteAccess In $ctx1")
        accessesOf(ctx1, mem).filter(isInAccess).foreach { access =>
          isMuted(access) = true
        }
      case List(ctx1, ctx2) if isOutCtx(ctx2, mem) =>
        dbg(s"MuteAccess In $ctx2")
        accessesOf(ctx1, mem).filter(isOutAccess).foreach { access =>
          isMuted(access) = true
        }
      case List(ctx1, ctx2) =>
    }
  }

  private def tokenInsertion(mem:Memory, sorted:List[ComputeContext]) = {
    val mems = scala.collection.mutable.ListBuffer[Memory]()
    sorted.sliding(size=2,step=1).foreach { 
      case List(ctx1, ctx2) if isInCtx(ctx1, mem) && isOutCtx(ctx2, mem) => 
      // Optimization. out to read dependency is captured in accumulation in program. No need to
      // insert token
      case List(ctx1, ctx2) if isOutCtx(ctx1, mem) && isInCtx(ctx2, mem) && isAccum(mem) =>
      case List(ctx1, ctx2) =>
        dbgblk(s"Insert Token ${ctx1} -> ${ctx2}") {
          val cu = globalOf(mem).get
          withParentCtrl(cu, ctrlOf(mem)) {
            val token = TokenIn()
            mems += token
            withParentCtrl(ctx1, innerCtrlOf(ctx1)) {
              WriteMem(token, High())
            }
            withParentCtrl(ctx2, innerCtrlOf(ctx2)) {
              ProcessControlToken(ReadMem(token))
            }
          }
          ()
        }
    }
    mems.toList
  }

}
