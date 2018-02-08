package pir.pass

import pir._
import pir.node._

import pirc._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._


class ControlPropogation(implicit design:PIR) extends PIRTraversal with BottomUpTopologicalTraversal with BFSTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  override def initPass = {
    super.initPass
    controllerTraversal.resetTraversal
  }

  val forward = false

  override def runPass =  {
    controllerTraversal.traverseNode(design.newTop.topController, ())
    traverseScope(design.newTop, ())
  }

  override def check = {
    val computes = collectDown[ComputeNode](design.newTop)
    computes.foreach { computes =>
      assert(ctrlOf.contains(computes), s"$computes's controller is not set")
    }
  }

  def resetController(n:Node, ctrl:Controller):Unit = n match {
    case n:CounterChain =>
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      n.ctrl(ctrl)
      n.children.foreach(c => resetController(c, ctrl))
    case n:ComputeNode => 
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      n.ctrl(ctrl)
      n.deps.foreach(d => resetController(d, ctrl))
    case n =>
  }

  val controllerTraversal = new ControllerTraversal with UnitTraversal{
    override def visitNode(n:N, prev:T):T = {
      n match {
        case n:LoopController => resetController(n.cchain, n)
        case _ =>
      }
      super.visitNode(n, prev)
    }
  }

  override def visitNode(n:N, prev:T):T = {
    dbg(s"visitNode(${qtype(n)}, n.ctrl=${ctrlOf.get(n)})")
    n match {
      case n:ComputeNode =>
        if (!ctrlOf.isDefinedAt(n)) {
          assert(depFunc(n).forall(ctrlOf.isDefinedAt), s"$ctrlOf is not defined at ${depFunc(n).filterNot(ctrlOf.isDefinedAt)}")
          val ctrls = depFunc(n).map(ctrlOf.apply).toSet
          assert(ctrls.size==1, s"deps have different controls ${depFunc(n).map(d => (d, ctrlOf(d)))}")
          ctrlOf(n) = ctrls.head
        }
        dbg(ctrlOf.info(n).get)
      case n => 
    }
    super.visitNode(n, prev) 
  }

}

