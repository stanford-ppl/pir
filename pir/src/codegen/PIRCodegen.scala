package pir.codegen

import prism.traversal._

trait PIRCodegen extends pir.pass.PIRTraversal with DFSTopDownTopologicalTraversal with Codegen {

  val forward = true

  override def quote(n:Any):String = qdef(n)

  override def runPass = {
    traverseNode(compiler.top)
  }
}
