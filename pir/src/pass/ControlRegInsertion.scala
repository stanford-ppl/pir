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
    val writeCtx = ComputeContext().setParent(cu)
    val reg = TokenIn().setParent(cu)
    WriteMem(reg, argFringe.tokenInDef).setParent(writeCtx).ctrl(argInCtrl)
    val innerCtrl = innerCtrlOf(ctx)
    val read = ReadMem(reg).setParent(ctx).ctrl(innerCtrl)
    ProcessControlToken(read).setParent(ctx).ctrl(innerCtrl)
  }

}
