package pir
package pass

import pir.node._

class ControlPropogation(implicit compiler:PIR) extends PIRTraversal with BFSBottomUpTopologicalTraversal with UnitTraversal {
  import pirmeta._

  override def initPass = {
    super.initPass
    controllerTraversal.resetTraversal
  }

  val forward = false

  override def runPass =  {
    controllerTraversal.traverseTop
    traverseTop
  }

  override def finPass = {
    //val cus = compiler.top.collectDown[GlobalContainer]()
    //cus.foreach { cu =>
      //checkCtrl(cu)
      //checkStageCtrl(cu)
    //}
    super.finPass
  }

  def checkCtrl(cu:GlobalContainer) = {
    val computes = cu.collectDown[ComputeNode]()
    computes.foreach { comp =>
      assert(ctrlOf.contains(comp), s"${qtype(comp)} in $cu doesn't have ctrl defined")
      comp match {
        case stageDef:StageDef =>
          val ctrl = ctrlOf(stageDef)
          assert(ctrl.isInnerControl, s"${qtype(stageDef)}.ctrl.level = ${ctrl.level}. stageDef.ctrl=${ctrl}")
        case _ =>
      }
    }
  }

  def checkStageCtrl(cu:GlobalContainer) = {
    val computes = cu.collectDown[StageDef]()
    computes.foreach { comp =>
      val ctrl = ctrlOf(comp)
      assert(ctrl.isInnerControl, s"${qtype(comp)}.ctrl.level = ${ctrl.level}. comp.ctrl=${ctrl}")
    }
  }

  def resetController(n:PIRNode, ctrl:Controller):Unit = n match {
    case n:CounterChain =>
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      ctrlOf(n) = ctrl
      n.children.foreach(c => resetController(c, ctrl))
    case n:ComputeNode => 
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      ctrlOf(n) = ctrl
      n.deps.foreach(d => resetController(d, ctrl))
    case n =>
  }

  val controllerTraversal = new ControllerSiblingFirstTraversal with prism.traversal.UnitTraversal {
    override lazy val logger = ControlPropogation.this.logger
    implicit val designP = ControlPropogation.this.designP
    override def visitNode(n:N, prev:T):T = {
      n match {
        case n:LoopController => resetController(n.cchain, n)
        case _ =>
      }
      super.visitNode(n, prev)
    }
  }

  override def visitNode(n:N, prev:T):T = dbgblk(s"visitNode ${qdef(n)}") {
    n match {
      case n:ComputeNode =>
        if (!ctrlOf.isDefinedAt(n)) {
          val deps = depFunc(n).filter { dep => ctrlOf.isDefinedAt(dep) }
          if (deps.nonEmpty) {
            ctrlOf(n) = assertUnify(deps , "ctrl") { dep => ctrlOf(dep) }.get
            dbg(ctrlOf.info(n).get)
          }
        }
      case n => 
    }
    super.visitNode(n, prev) 
  }

}

