package prism.codegen

import pirc._

import java.io.FileOutputStream

trait Logging {

  def name:String

  private val debug = Config.debug
  val logger = new Printer {
    override def print(s:String):Unit = if (debug) super.print(s)
    override def println(s:String):Unit = if (debug) super.print(s)

    override def printBlock[T](bs:Option[String], b:Option[Braces], es:Option[()=>String])(block: =>T):T = { 
      printBSln(bs, b, None)
      val res = block
      val resHeader = s"result${bs.fold("") { bs => s" [$bs]"}} ="
      res match {
        case res:Unit =>
        case res:Iterable[_] =>
          dbg(resHeader)
          res.foreach { res => dbg(s" - $res") }
        case res:Iterator[_] =>
          dbg(resHeader)
          res.foreach { res => dbg(s" - $res") }
        case res => dbg(resHeader + s" $res")
      }
      printBEln(None, b, es.map(es => es()))
      res
    }
  }

  private def promp(header:Option[String], s:Any) = s"${header.fold("") { h => s"[$h] "}}$s"

  def dbgblk[T](header:String, s:String)(block: =>T):T = logger.printBlock(promp(Some(header), s))(block)
  def dbgblk[T](s:String)(block: =>T):T = dbgblk(name, s)(block)

  def dbg(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) logger.println(promp(header, s))
  def dbg(pred:Boolean, header:String, s:Any):Unit = dbg(pred, Some(header), s) 
  def dbg(header:String, s:Any):Unit = dbg(debug, header, s) 
  def dbg(pred:Boolean, s:Any):Unit = dbg(pred, None, s) 
  def dbg(s:Any):Unit = dbg(debug, None, s) 
  //TODO
  //def dbsln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) logger.printBSln(promp(header,s))
  //def dbeln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) logger.printBEln(promp(header, s))

}
