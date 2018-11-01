package prism
package codegen

import prism.graph._

trait HtmlCodegen extends Codegen {
  override def dirName = buildPath(super.dirName, s"html") 

  def text(msg:String) = {
    emitElem("text",msg + "<br>")
  }

  def emitElem(header:String, msg:String, attrs:(String, Any)*):Unit = {
    emitln(elem(header, msg, attrs:_*))
  }

  def elem(header:String, msg:String, attrs:(String, Any)*):String = {
    var at = attrs.map { case (key,value) => s"$key=$value" }.mkString(" ")
    at = if (attrs.nonEmpty) " " + at else ""
    s"<$header$at>$msg</$header>"
  }

  def emitElem(header:String, attrs:(String, Any)*)(block: => Unit):Unit = {
    var at = attrs.map { case (key,value) => s"$key=$value" }.mkString(" ")
    at = if (attrs.nonEmpty) " " + at else ""
    emitln(s"<$header$at>")
    block
    emitln(s"</$header>")
  }

}

