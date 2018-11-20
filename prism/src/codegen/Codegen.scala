package prism
package codegen

import prism.graph._

trait Codegen extends Pass with Printer with DFSTraversal with UnitTraversal {

  def dirName = compiler.outDir
  def fileName:String
  val append = false
  def codegenPostfix = fileName
  override def name = s"${super.name} $codegenPostfix"

  lazy val outputPath = buildPath(dirName, fileName)

  override def initPass = {
    super.initPass
    startStream(outputPath, FileWriter(outputPath, false))
  }

  override def finPass = {
    super.finPass
    closeAll
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
        closeAll
        throw e
    }
  }

  final override def visitNode(n:N, prev:T) = emitNode(n)

  def emitNode(n:N):Unit = {
    emitComment(s"TODO: Unmatched Node ${quote(n)}")
    visitNode(n)
  } 

  def emitVisitNode(n:N):Unit = { emitNode(n); visited += n }

  def emitComment(msg:String):Unit = emitln(s"// $msg")

  def emitlnc(msg:String, comment:String):Unit = { emit(msg); emitComment(comment) }
}

