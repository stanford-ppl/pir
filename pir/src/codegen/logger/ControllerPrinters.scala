package pir.codegen
import pir.node._

import prism.traversal._

class ControllerPrinter(implicit design:PIR) extends PIRPass with ChildFirstTraversal with Codegen {
  val fileName = "CtrlPrinter.txt"

  type N = Controller
  def shouldRun = Config.debug

  override def quote(n:Any) = qdef(n)

  override def emitNode(n:N) = {
    emitBlock(qdef(n)) { traverse(n) }
  }

  override def runPass = {
    traverseNode(design.design.top.topController)
  }
}
