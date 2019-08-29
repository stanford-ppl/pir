package prism
package codegen

trait JsonCodegen extends Codegen {
  var startElem = true

  val denseFormat = false
  override def indent(s:String) = if (denseFormat) s"$s" else super.indent(s)

  def emitk(key:Any) = {
    if (!startElem) {
      if (denseFormat) write(s",") else writeln(s",")
    } else {
      startElem = false
    }
    emit(s"${quote(key)}: ")
  }

  def emitkv(key:Any, value:Any) = {
    emitk(key)
    write(quote(value))
  }

  def emitkv(key:Any, value:List[Any]) = {
    emitk(key)
    write(s"[${value.map(quote).mkString(",")}]")
  }

  def emitkb(key:Any)(block: => Any) = {
    emitk(key)
    emitb(block)
  }

  def emitb(block: => Any) = {
    if (denseFormat) emitBS(None, None, None) else emitBSln(None, None, None)
    val saved = startElem
    startElem = true
    block
    if (!denseFormat) writeln("")
    startElem = saved
    emitBE(None, None, None)
  }

  override def quote(n:Any):String = n match {
    case n:String => n.qstr
    case n => super.quote(n)
  }
}

