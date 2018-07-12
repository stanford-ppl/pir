package pir
package pass

import pir.node._

import scala.collection.mutable

class AccessAnalyzer(implicit compiler:PIR) extends SiblingFirstTraversal with UnitTraversal with prism.util.Memorization {
  import pirmeta._

  memorizing = true
  override def initPass = { super.initPass; resetAllCaches }

  override def visitNode(n:N) = { 
    n match {
      case n:Memory => analyze(n)
      case n => super.visitNode(n)
    }
  }

  val controllerSchedular = new ControllerTopologicalTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    implicit val designP = AccessAnalyzer.this.designP
    override lazy val logger = AccessAnalyzer.this.logger
    val forward = true
    override def visitIn(n:N):List[N] = visitLocalIn(n)
    override def visitOut(n:N):List[N] = visitLocalOut(n)
    override def scheduleScope(ns:List[N]):List[N] = memorize("controllerScheduleScope", ns) { ns => super.scheduleScope(ns) }
  }

  val accessSchedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    override lazy val logger = AccessAnalyzer.this.logger
    val forward = true
    override def visitIn(n:N):List[N] = memorize("accessVisitIn",n) { n => 
      dbgblk(s"visitIn($n)") {
        n match {
          case n:LocalAccess if isInAccess(n) => antiDepsOf(n).toList.asInstanceOf[List[LocalAccess]]
          case n:LocalAccess if isOutAccess(n) => 
            memsOf(n).flatMap { m => inAccessesOf(m) }.filterNot { a => antiDepsOf(a).contains(n) }
        }
      } 
    }
    override def visitOut(n:N):List[N] = memorize("accessVisitOut", n) { n => 
      dbgblk(s"visitOut($n)"){
        n match {
          case n:LocalAccess if isInAccess(n) => 
            memsOf(n).flatMap { m => outAccessesOf(m) }.filterNot { a => antiDepsOf(n).contains(a) }
          case n:LocalAccess if isOutAccess(n) => scope.filter { a => antiDepsOf(a).contains(n) }
        }
      }
    } 
  }

  def analyze(mem:Memory) = dbgblk(s"analyze($mem)") {
    val allAccesses = accessesOf(mem)
    val ctrlMap = reverseMap(allAccesses.map { a => (a, ctrlOf(a)) }.toMap) // ctrl -> Set(access)

    def order(ctrl:Controller):List[List[LocalAccess]] = dbgblk(s"order($ctrl)") {
      if (ctrlMap.contains(ctrl)) {
        val accesses = ctrlMap(ctrl)
        allAccesses.map { a =>
          assert(!ctrlOf(a).isDescendentOf(ctrl), 
            s"access at both $ctrl($accesses) and its descendents=${ctrlOf(a)}($a) to $mem")
        }
        val nodes = accesses.toList
        val scheduled = accessSchedular.scheduleScope(nodes)
        dbgs(s"scheduled $ctrl.accesses=${scheduled}")
        scheduled.collect { case a:LocalAccess => List(a) }
      } else {
          ctrl.style match {
            case ForkJoin =>
              val clist = ctrl.children.map { c => order(c) }.filter { _.nonEmpty }
              if (clist.isEmpty) Nil else {
                assertUnify(clist, s"$ctrl.children ordered for $mem") { _.size }
                List.tabulate(clist.head.size) { i => clist.flatMap { l => l(i) } }
              }
            case _ => 
              val children = controllerSchedular.scheduleScope(ctrl.children)
              dbgs(s"scheduled $ctrl.children:${children}")
              children.map { c => order(c) }.flatten
          }
      }
    }

    order(ctrlOf(mem))
  }

}

