package pir.codegen

import pir._
import pir.pass._
import prism.codegen._

trait PIRCodegen extends PIRTraversal with Codegen with DFSTopDownTopologicalTraversal {

  val forward = true
  val dirName = design.outDir

  def quote(n:Any):String = qdef(n)

  override def runPass = {
    traverseNode(design.newTop)
  }
  
}
