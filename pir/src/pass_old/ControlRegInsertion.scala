package pir
package pass

import pir.node._

class ControlRegInsertion(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def visitNode(n:N) = { 
    n match {
      case n:ContextEnableOut =>
        val ctx = contextOf(n).get
        if (ctx.depeds.nonEmpty) {
          val loads = ctx.collectDown[LocalLoad]()
          globalOf(ctx).get match { // has output but no data dependency
            case _:ArgFringe =>
            case _:FringeStreamIn =>
            case _ if loads.isEmpty => insertControlReg(ctx)
            case _ =>
          }
        }
      case n => super.visitNode(n)
    }
  }

  def insertControlReg(ctx:ComputeContext) = dbgblk(s"insertControlReg ${quote(ctx)} depeds=${ctx.depeds}") {
    val argFringe = designP.top.argFringe
    val argInCtrl = argFringe.argInController
    val cu = globalOf(ctx).get
    withParent(cu) {
      val reg = TokenIn()
      withParentCtrl(ComputeContext(), argInCtrl) {
        WriteMem(reg, argFringe.tokenInDef)
      }
      withParentCtrl(ctx, innerCtrlOf(ctx)) {
        val read = ReadMem(reg)
        ProcessControlToken(read)
      }
    }
  }

}
