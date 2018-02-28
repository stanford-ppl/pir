package prism.test

import prism._
import prism.util._
import prism.node._
import prism.traversal._
import prism.codegen._

class TestDotCodegen(val fileName:String)(implicit design:TestDesign) extends IRDotCodegen with ChildFirstTraversal {
  type N = TestNode
  val dirName = Config.outDir + "/" + design.getClass.getSimpleName

  val forward = true

  def visitLocalIn(n:N):List[N] = n.localDeps.toList
  def visitLocalOut(n:N):List[N] = n.localDepeds.toList

  def quote(n:Any) = n.toString

  override def runPass = {
    traverseNode(design.top, ())
  }

}

