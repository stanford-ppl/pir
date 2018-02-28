package prism.codegen

import prism._

import prism.util._

import prism.traversal._

trait Codegen extends Pass with prism.codegen.Printer with GraphTraversal with UnitTraversal {

  val dirName:String
  val fileName:String
  val append = false

  val outputPath = buildPath(dirName, fileName)

  override def initPass(runner:RunPass[_]) = {
    openFile(dirName, fileName, append=append)
    info(s"Running $name to ${sw.getPath}")
  }

  override def finPass = {
    super.finPass
    closeStream
  }

  def quote(n:Any):String

  override def traverseNode(n:N):T = {
    try {
      super.traverseNode(n)
    } catch {
      case e:Exception =>
        closeStream
        throw e
    }
  }

  override def visitNode(n:N, prev:T) = emitNode(n)

  def emitNode(n:N):Unit = {
    emitln(s"// TODO: Unmatched Node ${quote(n)}")
    super.visitNode(n)
  } 

}
