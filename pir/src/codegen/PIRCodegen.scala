package pir.codegen

import pir.pass._

trait PIRCodegen extends DFSTopDownTopologicalTraversal with Codegen {

  val forward = true

  override def quote(n:Any):String = qdef(n)

  override def runPass = {
    traverseNode(compiler.top)
  }
}
