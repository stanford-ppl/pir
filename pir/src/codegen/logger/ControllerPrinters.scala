package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import pirc._
import pirc.util._
import prism.node._
import prism.traversal._
import prism.codegen._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable

class ControllerPrinter(implicit design:PIR) extends PIRPass with ChildFirstTraversal with Codegen {
  val fileName = "CtrlPrinter.txt"

  type N = Controller
  def shouldRun = Config.debug

  val dirName = design.outDir

  def quote(n:Any) = qdef(n)

  override def emitNode(n:N) = {
    emitBlock(qdef(n)) { traverse(n) }
  }

  override def runPass = {
    traverseNode(design.newTop.topController)
  }
}
