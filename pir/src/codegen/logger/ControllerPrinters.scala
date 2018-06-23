package pir
package codegen

import pir.pass._
import pir.node._

class ControllerPrinter(implicit design:PIR) extends PIRPass with ControllerChildFirstTraversal with Codegen {
  val fileName = "CtrlPrinter.txt"

  override def quote(n:Any) = qdef(n)

  override def emitNode(n:N) = {
    emitBlock(qdef(n)) { super.visitNode(n) }
  }

}
