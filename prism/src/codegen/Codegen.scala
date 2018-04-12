package prism.codegen

import prism.traversal._

trait Codegen extends Pass with prism.codegen.Printer with GraphTraversal with UnitTraversal {

  val dirName = compiler.outDir
  val fileName:String
  val append = false

  lazy val outputPath = buildPath(dirName, fileName)

  override def initPass(runner:RunPass[_]) = {
    super.initPass(runner)
    openFile(dirName, fileName, append=append)
  }

  override def finPass(runner:RunPass[_]) = {
    info(s"Finished ${runner.name} to ${outputPath} in ${toc("ms")}ms")
    closeStream
    check
  }

  override def quote(n:Any):String = n match {
    case n:Map[_,_] => n.map{ case (k, v) => (quote(k), quote(v)) }.toString
    case n:Iterable[_] => n.map(quote).toString
    case n => n.toString
  }

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
    emitComment(s"TODO: Unmatched Node ${quote(n)}")
    super.visitNode(n)
  } 

  def emitComment(msg:String):Unit = emitln(s"// $msg")

  def emitlnc(msg:String, comment:String):Unit = { emit(msg); emitComment(comment) }
}
