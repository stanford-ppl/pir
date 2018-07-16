package pir
package pass

import pir.node._

import scala.collection.mutable

class AccessAnalyzer(implicit compiler:PIR) extends SiblingFirstTraversal with UnitTraversal with prism.util.Memorization {
  import pirmeta._

  memorizing = true
  override def initPass = { super.initPass; resetAllCaches }

  override def runPass = {
    //super.runPass
  }

  var currMem:Option[Memory] = None
  def mem = currMem.get

  override def visitNode(n:N) = { 
    n match {
      case n:Memory => 
        currMem = Some(n)
        analyze
        currMem = None
      case n => super.visitNode(n)
    }
  }

  val controllerSchedular = new ControllerTopologicalTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    implicit val designP = AccessAnalyzer.this.designP
    lazy val pirmeta = designP.pirmeta
    override lazy val logger = AccessAnalyzer.this.logger
    val forward = true
    override def visitIn(n:N):List[N] = visitLocalIn(n)
    override def visitOut(n:N):List[N] = visitLocalOut(n)
    override def scheduleScope(ns:List[N]):List[N] = memorize("controllerScheduleScope", ns) { ns => super.scheduleScope(ns) }
  }

  val ctxSchedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    override lazy val logger = AccessAnalyzer.this.logger
    val forward = true

    def accessesOf(ctx:ComputeContext) = ctx.collectDown[LocalAccess]().filter { a => memsOf(a).contains(mem) }
    def inAccessesOf(ctx:ComputeContext) = accessesOf(ctx).filter(isOutAccess) // reads
    def outAccessesOf(ctx:ComputeContext) = accessesOf(ctx).filter(isInAccess) // writes + resets

    override def visitIn(n:N):List[N] = memorize("ctxVisitIn",n) { n => 
      dbgblk(s"visitIn($n)") {
        n match {
          case n:ComputeContext => 
            val antiDeps = accessesOf(n).flatMap { ia => antiDepsOf(ia).map { a => contextOf(a).get } }.toSet.toList
            val inputs = inAccessesOf(n).flatMap { a =>  // reads
              memsOf(a).flatMap { mem => 
                inAccessesOf(mem).filterNot { ia => 
                  antiDepsOf(ia).contains(a)
                }.map { ia => contextOf(ia).get }
              }
            }.toSet.toList
            antiDeps ++ inputs
        }
      } 
    }
    override def visitOut(n:N):List[N] = memorize("ctxVisitOut", n) { n => 
      dbgblk(s"visitOut($n)"){
        n match {
          case n:ComputeContext => 
            val accesses = accessesOf(n)
            val antiDepeds = scope.filter { ctx => 
              accessesOf(ctx.asInstanceOf[ComputeContext]).exists { a => // If n is antiDep of ctx
                antiDepsOf(a).exists { antiDep => accesses.contains(antiDep) }
              }
            }
            val outputs = outAccessesOf(n).flatMap { a => // writes + reset
              memsOf(a).flatMap { mem => 
                outAccessesOf(mem).filterNot { oa => 
                  antiDepsOf(a).contains(oa)
                }.map { oa => contextOf(oa).get }
              }
            }
            antiDepeds ++ outputs
        }
      }
    } 
  }

  def sortContext(ctrl:Controller, ctrlMap:Map[Controller, Set[ComputeContext]]):List[List[ComputeContext]] = dbgblk(s"sortContext($ctrl)") {
    val currCtxs = {
      val ctxes = ctrlMap.get(ctrl).getOrElse(Set.empty)
      val nodes = ctxes.toList
      val scheduled = ctxSchedular.scheduleScope(nodes)
      dbgs(s"scheduled $ctrl.ctxes=${scheduled}")
      scheduled.collect { case a:ComputeContext => List(a) }
    }
    val descendentCtxs = {
      ctrl.style match {
        case ForkJoin =>
          val clist = ctrl.children.map { c => sortContext(c, ctrlMap) }.filter { _.nonEmpty }
          if (clist.isEmpty) Nil else {
            assertUnify(clist, s"$ctrl.children ordered for $mem") { _.size }
            List.tabulate(clist.head.size) { i => clist.flatMap { l => l(i) } }
          }
        case _ => 
          val children = controllerSchedular.scheduleScope(ctrl.children)
          dbgs(s"scheduled $ctrl.children:${children}")
          children.map { c => sortContext(c, ctrlMap) }.flatten
      }
    }
    mergeSortAccesses(currCtxs, descendentCtxs)
  }

  def mergeSortAccesses(currCtxs:List[List[ComputeContext]], descendentCtxs:List[List[ComputeContext]]) = {
    (currCtxs, descendentCtxs) match {
      case (Nil, Nil) => Nil
      case (Nil, descendentCtxs) => descendentCtxs
      case (currCtxs, Nil) => currCtxs 
      case (currCtxs, descendentCtxs) =>
        throw PIRException(
          s"Accesses from both parent and descendent controller\n" + 
          s"ctrl=${currCtxs.flatten.map { a => (a,ctrlOf(a)) }.mkString(",")}\n" +
          s"descendent=${descendentCtxs.flatten.map { a => (a,ctrlOf(a)) }.mkString(",")}"
        )
    }
  }

  def sortCheck(sorted:List[List[ComputeContext]], ctrlMap:Map[Controller, Set[ComputeContext]]) = {
    assert(sorted.size == ctrlMap.values.flatten.size)
    if (isLocalMem(mem)) {
      assert(sorted.forall { _.size == 1 }, s"$mem's sorted ctxs not all size=1 ctxs=${quote(sorted)}")
      assert(sorted.size == 2, s"$mem sorted ctxs.size != 2 ctxs=${quote(sorted)}")
      val List(List(ctx1), List(ctx2)) = sorted
      assert(ctxSchedular.accessesOf(ctx1).forall(isInAccess) , s"$mem sorted access=${quote(sorted)}")
      assert(ctxSchedular.accessesOf(ctx2).forall(isOutAccess) , s"$mem sorted access=${quote(sorted)}")
    } else {
    }
    sorted
  }

  def analyze = dbgblk(s"analyze($mem)") {
    val allContexts = accessesOf(mem).map { a => contextOf(a).get }.toSet
    val ctrlMap = reverseMap(allContexts.map { ctx => (ctx, innerCtrlOf(ctx)) }.toMap) // ctrl -> Set(ctx)
    dbg(s"mem ctrl=${ctrlOf(mem)}")
    dbgblk(s"ctrlMap") {
      ctrlMap.foreach { case (ctrl, ctxs) =>
        dbg(s"$ctrl -> $ctxs")
      }
    }

    sortCheck(sortContext(ctrlOf(mem), ctrlMap), ctrlMap)
  }

  override def quote(n:Any) = n match {
    case n:ComputeContext => s"$n(${ctxSchedular.accessesOf(n).mkString(",")})"
    case n => super.quote(n)
  }

}

