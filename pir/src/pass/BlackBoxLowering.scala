package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class BlackBoxLowering(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with PIRTransformer with UnitTraversal with DependencyAnalyzer {

  override def visitNode(n:N) = n match {
    case n:BlackBox => moveToContext(n)
    case _ => super.visitNode(n)
  }

  def moveToContext(n:BlackBox):Unit = {
    //if (n.parent.get.isInstanceOf[Context]) return
    val ctrl = n.ctrl.get
    val ctx = within(pirTop, ctrl) { 
      stage(Context()).streaming(true)
    }
    swapParent(n, ctx)
    (n.localDeps ++ n.localDepeds).foreach { neighbor =>
      neighbor.to[Access].foreach { access =>
        swapParent(access, ctx)
      }
    }
    bufferInput(ctx)
    n.to[Lock].foreach { n =>
      val memCU = within(pirTop) { stage(MemoryContainer()) }
      swapParent(ctx,memCU)
    }
  }

}

