package prism
package codegen

import java.io.FileOutputStream

trait Logging extends Serializable {

  def debug = Config.debug
  @transient lazy val logger = new Printer {
    override def emit(s:String):Unit = if (debug && isOpened) { super.emit(s); flush }
    override def emitln(s:String):Unit = if (debug && isOpened) { super.emitln(s); flush }

    override def emitBlock[T](bs:Option[String], b:Option[Braces], es:Option[()=>String])(block: =>T):T = if (debug && isOpened) { 
      emitBSln(bs, b, None)
      val res = block
      val resHeader = s"result${bs.fold("") { bs => s" [$bs]"}} ="
      res match {
        case res:Unit =>
        //case res:Iterable[_] if res.size < 4 =>
          //dbg(resHeader + s" ${res.map(quote)}")
        case res:Iterable[_] =>
          dbg(resHeader)
          res.foreach { res => dbg(s" - ${quote(res)}") }
        case res:Iterator[_] =>
          dbg(resHeader)
          res.foreach { res => dbg(s" - ${quote(res)}") }
        case res => dbg(resHeader + s" ${quote(res)}")
      }
      emitBEln(None, b, es.map(es => es()))
      res
    } else block
  }

  def quote(n:Any):String = n.toString

  private def promp(header:Option[String], s:Any) = s"${header.fold("") { h => s"[$h] "}}$s"

  def dbgblk[T](pred:Boolean, header:Option[String], s:String)(block: =>T):T = if (pred) logger.emitBlock(promp(header, s))(block) else block
  def dbgblk[T](s:String)(block: =>T):T = dbgblk(debug, None, s)(block)
  def dbgblk[T](pred:Boolean, s:String)(block: =>T):T = dbgblk(pred, None, s)(block)

  def dbg(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) logger.emitln(promp(header, s))
  def dbg(pred:Boolean, header:String, s:Any):Unit = dbg(pred, Some(header), s) 
  def dbg(header:String, s:Any):Unit = dbg(debug, header, s) 
  def dbg(pred:Boolean, s:Any):Unit = dbg(pred, None, s) 
  def dbg(s:Any):Unit = dbg(debug, None, s) 
  def dbsln(s:String):Unit = logger.emitBSln(s)
  def dbeln(s:String):Unit = logger.emitBEln(s)
  def dbeln:Unit = logger.emitBEln("")

  def withLog[T](outDir:String, logFile:String, append:Boolean=false)(lambda: => T):T = {
    if (logger.isOpen) lambda 
    else logger.withOpen[T](outDir, logFile, append) { lambda }
  }
}
