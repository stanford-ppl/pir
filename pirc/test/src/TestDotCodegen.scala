package pirc.test

import pirc._
import pirc.util._
import prism.node._
import prism.traversal._
import prism.codegen._

class TestDotCodegen(top:TestSubGraph, val fileName:String)(implicit design:Design) extends IRDotCodegen with ChildFirstTraversal {
  type N = TestNode
  val dirName = design.outDir

  val forward = true

  def visitLocalIn(n:N):List[N] = n.localDeps.toList
  def visitLocalOut(n:N):List[N] = n.localDepeds.toList

  def quote(n:Any) = n.toString

  override def runPass = {
    traverseNode(top, ())
  }
}

