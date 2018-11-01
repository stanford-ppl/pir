package prism
package codegen

import prism.graph._

trait Codegen extends Pass with Printer with DFSTraversal with UnitTraversal {

  def dirName = compiler.outDir
  def fileName:String
  val append = false

  lazy val outputPath = buildPath(dirName, fileName)

  def clearGen = clearDir(dirName)

  override def initPass = {
    super.initPass
    clearGen
    openFile(dirName, fileName, append=append)
  }

  override def finPass = {
    closeStream
    codegenInfo
  }

  def codegenInfo = {
    info(s"Codegen to ${cstr(Console.CYAN,outputPath)}")
  }

  override def quote(n:Any):String = n match {
    case n:Map[_,_] => n.map{ case (k, v) => (quote(k), quote(v)) }.toString
    case n:Iterable[_] => n.map(quote).toString
    case n => s"$n"
  }

  override def run:this.type = {
    try {
      super.run
    } catch {
      case e:Exception =>
        closeStream
        throw e
    }
  }

  final override def visitNode(n:N, prev:T) = emitNode(n)

  def emitNode(n:N):Unit = {
    emitComment(s"TODO: Unmatched Node ${quote(n)}")
    visitNode(n)
  } 

  def emitComment(msg:String):Unit = emitln(s"// $msg")

  def emitlnc(msg:String, comment:String):Unit = { emit(msg); emitComment(comment) }
}

