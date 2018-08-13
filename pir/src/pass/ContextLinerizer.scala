package pir
package pass

import prism.util._
import pir.node._

import scala.collection.mutable

trait ContextLinerizer extends PIRTransformer with PIRNodeUtil with Logging with prism.traversal.GraphUtil with AccessControlUtil with Memorization { self:ControlAllocation =>
  implicit val compiler:PIR
  val pirmeta:PIRMetadata
  import pirmeta._

  private var currMem:Option[Memory] = None
  private def mem = currMem.get

  private var ctxGroups:Map[ComputeContext, ContextGroup] = Map.empty

  def linearizeAccessContext(n:Memory):List[Memory] = dbgblk(s"linearizeAccessContext($n)") {
    withMemory {
      currMem = Some(n)
      val ctxs = accessesOf(mem).map { a => contextOf(a).get }.toSet.toList
      ctxs.foreach { ctx => assert(isInCtx(ctx) != isOutCtx(ctx)) }
      createContextGroup(ctxs)
      val sorted = sortContext(mem, ctxs)
      muteAccess(mem, sorted)
      val mems = tokenInsertion(n, sorted)
      //insertMatchingAccesses(mem, sorted)
      ctxGroups = Map.empty
      currMem = None
      mems
    }
  }

  private def connect(ctx1:ComputeContext, ctx2:ComputeContext, info:String) = {
    ctxGroups(ctx1).connect(ctxGroups(ctx2))
    dbg(s"${quote(ctxGroups(ctx1))} => ${quote(ctxGroups(ctx2))} $info")
  }

  private def accessesOf(ctx:ComputeContext):List[LocalAccess] = accessesOf(ctx, mem)
  private def accessesOf(cg:ContextGroup):List[LocalAccess] = accessesOf(cg.ctx)
  private def isInCtx(ctx:ComputeContext) = accessesOf(ctx).forall(isInAccess)
  private def isOutCtx(ctx:ComputeContext) = accessesOf(ctx).forall(isOutAccess)
  private def isInGroup(cg:ContextGroup) = isInCtx(cg.ctx)
  private def isOutGroup(cg:ContextGroup) = isOutCtx(cg.ctx)

  private def createContextGroup(ctxs:List[ComputeContext]) = {
    val groups = ctxs.map { ctx => ContextGroup(mem, ctx) }
    ctxGroups = groups.map { case cg@ContextGroup(mem, ctx) => ctx -> cg }.toMap

    ctxs.foreach { ctx1 =>
      ctxs.foreach { ctx2 =>
        if (ctx1 != ctx2) {
          val as1 = accessesOf(ctx1, mem)
          val as2 = accessesOf(ctx2, mem)
          val anti2 = as2.flatMap { a => antiDepsOf(a) }
          val ctrl1 = innerCtrlOf(ctx1)
          val ctrl2 = innerCtrlOf(ctx2)
          val lca = leastCommonAncesstor(ctrl1, ctrl2).get
          val top1 = getTopCtrl(ctrl1, lca)
          val top2 = getTopCtrl(ctrl2, lca)
          // ctx1 -> ctx2
          if (anti2.exists { anti2 => as1.contains(anti2) }) { // exist anti dependency
            //connect(ctx1, ctx2, s"Anti dependency anti dep of $as2=${anti2}")
          } else if (lca.style == SeqPipe && top1.id < top2.id) {
            connect(ctx1, ctx2, s"Sequential lca=$lca")
          }
        }
      }
    }
    
    // Data dependency in a second pass
    ctxs.foreach { ctx1 =>
      ctxs.foreach { ctx2 =>
        if (ctx1 != ctx2) {
          val as1 = accessesOf(ctx1, mem)
          val as2 = accessesOf(ctx2, mem)
          if (!ctxGroups(ctx1).deps.contains(ctxGroups(ctx2)) && as1.forall(isInAccess) && as2.forall(isOutAccess)) {
            connect(ctx1, ctx2, s"Data dependency")
          }
        }
      }
    }

    ctxGroups.values.foreach { cg =>
      dbg(s"${quote(cg)}: deps=${cg.deps.map(quote)}")
    }
  }

  private def sortContext(mem:Memory, ctxs:List[ComputeContext]):List[ContextGroup] = dbgblk(s"sortContext($ctxs)") {
    val scheduled = ContextSchedular.scheduleScope(ctxGroups.values.toList)
    sortCheck(scheduled)
  }

  def sortCheck(sorted:List[ContextGroup]) = {
    if (isLocalMem(mem)) {
      assert(sorted.size == 2, s"$mem sorted ctxs.size != 2 ctxs=${quote(sorted)}")
      assert(isInGroup(sorted(0)) , s"$mem sorted access=${quote(sorted)}")
      assert(isOutGroup(sorted(1)) , s"$mem sorted access=${quote(sorted)}")
    } else {
    }
    sorted
  }

  private def muteAccess(n:Memory, sorted:List[ContextGroup]) = {
    sorted match {
      case List(InGroup(ctx1), OutGroup(ctx2)) =>
      case List(InGroup(ctx1), InGroup(ctx2), OutGroup(ctx3)) =>
        dbg(s"MuteAccess In $ctx1 (mute) => In $ctx2 => Out $ctx3")
        accessesOf(ctx1, mem).filter(isInAccess).foreach { access =>
          isMuted(access) = true
          isMuted.info(access).foreach(dbg)
        }
      case List(InGroup(ctx1), OutGroup(ctx2), InGroup(ctx3s)) =>
      case List(InGroup(ctx1), OutGroup(ctx2), OutGroup(ctx3)) =>
        dbg(s"MuteAccess In $ctx1 => Out $ctx2 => Out $ctx3 (mute)")
        accessesOf(ctx3, mem).filter(isInAccess).foreach { access =>
          isMuted(access) = true
          isMuted.info(access).foreach(dbg)
        }
      case groups => 
        throw PIRException(s"Unmatched access pattern ${quote(groups)}")
    }
  }

  /*
   * insert accesses such that in accesses and out accesses are balanced
   * e.g.
   * W W R => W R W R
   * W R R => W R W R
   *
   * */
  private def insertMatchingAccesses(n:Memory, sorted:List[ContextGroup]) = {
    sorted match {
      case List(InGroup(ctx1), OutGroup(ctx2)) =>
      case List(InGroup(ctx1), InGroup(ctx2), OutGroup(ctx3)) =>
        dbgblk(s"InsertMatchingAccess In $ctx1 => (Out) In $ctx2 => Out $ctx3") {
          val ctrl1 = innerCtrlOf(ctx1)
          val ctrl2 = innerCtrlOf(ctx2)
          val lca = leastCommonAncesstor(ctrl1, ctrl2).get
          val topCtrl = getTopCtrl(ctrl2, lca)
          withParentCtrl(ctx2, innerCtrlOf(ctx2)) {
            ProcessControlToken(EnabledDequeueMem(n, allocateControllerDone(topCtrl)))
          }
          ()
        }
      case List(InGroup(ctx1), OutGroup(ctx2), InGroup(ctx3s)) =>
      case List(InGroup(ctx1), OutGroup(ctx2), OutGroup(ctx3)) =>
        dbgblk(s"InsertMatchingAccess In $ctx1 => Out $ctx2 (In) => Out $ctx3") {
          val ctrl2 = innerCtrlOf(ctx2)
          val ctrl3 = innerCtrlOf(ctx3)
          val lca = leastCommonAncesstor(ctrl2, ctrl3).get
          val topCtrl = getTopCtrl(ctrl2, lca)
          withParentCtrl(ctx2, innerCtrlOf(ctx2)) {
            EnabledEnqueueMem(n, allocateControllerDone(topCtrl))
          }
          ()
        }
      case groups => 
        throw PIRException(s"Unmatched access pattern ${quote(groups)}")
    }
  }

  private def tokenInsertion(n:Memory, sorted:List[ContextGroup]) = {
    val mems = scala.collection.mutable.ListBuffer[Memory]()
    sorted.sliding(size=2,step=1).foreach { 
      case List(InGroup(ctx1), OutGroup(ctx2)) => 
      case List(ContextGroup(_, ctx1), ContextGroup(_, ctx2)) =>
        dbgblk(s"Insert Token ${quote(ctx1)} -> ${quote(ctx2)}") {
          val cu = globalOf(n).get
          withParentCtrl(cu, ctrlOf(n)) {
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

  override def quote(n:Any) = n match {
    case n:ContextGroup => s"CG(${quote(n.ctx)})"
    case n:ComputeContext if currMem.nonEmpty => s"$n(${accessesOf(n, mem).mkString(",")})"
    case n => super.quote(n)
  }

  object InGroup {
    def unapply(g:Any) = g match {
      case g:ContextGroup if isInGroup(g) => Some((g.ctx))
      case _ => None
    }
  }
  object OutGroup {
    def unapply(g:Any) = g match {
      case g:ContextGroup if isOutGroup(g) => Some((g.ctx))
      case _ => None
    }
  }
}

object ContextDesign extends prism.node.Design
case class ContextGroup(mem:Memory, ctx:ComputeContext) extends prism.node.Atom[ContextGroup] {
  override type A = ContextGroup
  val id = ContextDesign.nextId
  def connect(other:ContextGroup) = {
    val out = new ContextOutput(this)
    val in = new ContextInput(other)
    out.connect(in)
  }
}
class ContextInput(override val src:ContextGroup) extends prism.node.Input[ContextGroup] {
  override type A = ContextGroup
  type E = ContextOutput
  val id = ContextDesign.nextId
}
class ContextOutput(override val src:ContextGroup) extends prism.node.Output[ContextGroup] {
  override type A = ContextGroup
  type E = ContextInput
  val id = ContextDesign.nextId
}

object ContextSchedular extends prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
  type N = ContextGroup
  val forward = true

  override def selectFrontier(unvisited:List[N]) = {
    throw PIRException(s"Loop between contexts $scope!")
  }
}

