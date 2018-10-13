package prism
package codegen

import prism.graph._

trait Codegen extends Pass with Printer with UnitTraversal {

  lazy val dirName = compiler.outDir
  val fileName:String
  val append = false

  lazy val outputPath = buildPath(dirName, fileName)

  override def initPass = {
    super.initPass
    openFile(dirName, fileName, append=append)
  }

  override def finPass = {
    closeStream
    info(s"Codegen to ${cstr(Console.CYAN,outputPath)}")
  }

  override def quote(n:Any):String = n match {
    case n:Map[_,_] => n.map{ case (k, v) => (quote(k), quote(v)) }.toString
    case n:Iterable[_] => n.map(quote).toString
    case n => n.toString
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

  override def visitNode(n:N, prev:T) = emitNode(n)

  def emitNode(n:N):Unit = {
    emitComment(s"TODO: Unmatched Node ${quote(n)}")
    super.visitNode(n,())
  } 

  def emitComment(msg:String):Unit = emitln(s"// $msg")

  def emitlnc(msg:String, comment:String):Unit = { emit(msg); emitComment(comment) }
}
