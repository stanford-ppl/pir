package prism.test

import prism._
import prism.util._
import prism.node._
import prism.traversal._
import prism.codegen._

class TestDotCodegen(val fileName:String)(implicit design:TestDesign) extends Pass()(design.compiler) with IRDotCodegen with ChildFirstTraversal with Logging {
  type N = TestNode

  val forward = true

  def visitLocalIn(n:N):List[N] = n.localDeps.toList
  def visitLocalOut(n:N):List[N] = n.localDepeds.toList

  override def runner = prism.Runner[TestDotCodegen](null, 0).setPass(this)

  override def visitNode(n:N,prev:T):T = {
    super.visitNode(n,prev)
  }

  override def runPass = {
    traverseNode(design.top, ())
  }

}

