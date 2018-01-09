package pirc.codegen

import pirc._

import java.nio.file._
import java.io._
import scala.collection.mutable.Stack

trait Printer {

  var _append = false
  def append = { stream; _append }
  var fileName:String = "System.out"
  var dirPath:String = Config.outDir 
  var file:File = _
  lazy val stream:OutputStream = System.out
  def stdOut = fileName=="System.out" 

  def getPath = {
    if (stdOut) "console"
    else s"${dirPath}${File.separator}${fileName}"
  }

  lazy val writer = new PrintWriter(stream)
  //lazy val pw = new PrintWriter(stream)
  def pw:PrintWriter = { bufferWriters.headOption.getOrElse(writer) }
  val tab = "  "
  var level = 0
  var listing = false
  def incLevel = level += 1
  def decLevel = { level -= 1; assert(level >= 0) }
  def blist = { listing = true; incLevel }
  def elist = { listing = false; decLevel }

  def pprint(s:String):Unit = { pw.print(s); if (stdOut) flush }
  def pprintln(s:String):Unit = { pw.println(s); if (stdOut) flush }
  def pprintln:Unit = { pw.println; if (stdOut) flush }

  trait Braces { def s:String; def e:String }
  case object Brackets extends Braces { def s = "["; def e = "]" }
  case object CurlyBraces extends Braces { def s = "{"; def e = "}" }
  case object Parentheses extends Braces { def s = "("; def e = ")" }

  def emit(s:String):Unit = pprint(s"${tab*level}${if (listing) "- " else ""}${s}")
  def emit(s:Any):Unit = emit(s.toString) 
  def emit:Unit = emit("") 
  def emitln(s:String):Unit = pprintln(s"${tab*level}${if (listing) "- " else ""}${s}")
  def emitln:Unit = pprintln
  def emitln(i:Int):Unit = (0 until i).foreach { i => pprintln }

  def emitBSln:Unit = emitBSln(None, None, None)
  def emitBSln(b:Braces):Unit = emitBSln(None, Some(b), None)
  def emitBSln(bs:String):Unit = emitBSln(Some(bs),None, None)
  def emitBSln(bs:String, b:Braces):Unit = emitBSln(Some(bs), Some(b), None)
  def emitBSln(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = { 
    emitln(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).s}${es.fold(""){ es => s" $es"}}"); incLevel
  }

  def emitBS:Unit = emitBS(None, None, None)
  def emitBS(b:Braces):Unit = emitBS(None, Some(b), None)
  def emitBS(bs:String):Unit = emitBS(Some(bs),None, None)
  def emitBS(bs:String, b:Braces):Unit = emitBS(Some(bs), Some(b), None)
  def emitBS(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = { 
    emit(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).s}${es.fold(""){ es => s" $es"}}"); incLevel
  }

  def emitBEln(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = {
    decLevel; emitln(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).e}${es.fold(""){ es => s" $es"}}")
  }
  def emitBEln(bs:String, b:Braces):Unit = emitBEln(Some(bs), Some(b), None) 
  def emitBEln(es:String):Unit = emitBEln(None, None, Some(es))
  def emitBEln(b:Braces):Unit = emitBEln(None, Some(b), None)
  def emitBEln:Unit = emitBEln(None, None, None)

  def emitBE(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = {
    decLevel; emit(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).e}${es.fold(""){ es => s" $es"}}")
  }
  def emitBE(bs:String, b:Braces):Unit = emitBE(Some(bs), Some(b), None) 
  def emitBE(es:String):Unit = emitBE(None, None, Some(es))
  def emitBE(b:Braces):Unit = emitBE(None, Some(b), None)
  def emitBE:Unit = emitBE(None, None, None)

  def emitBlock[T](block: =>T):T = emitBlock(None, None, None)(block)
  def emitBlock[T](b:Braces)(block: =>T):T = emitBlock(None, Some(b), None)(block)
  def emitBlock[T](bs:String)(block: =>T):T = emitBlock(Some(bs), None, None)(block)
  def emitBlock[T](bs:String, b:Braces)(block: =>T):T = emitBlock(Some(bs), Some(b), None)(block)
  def emitBlock[T](bs:String, block: =>T, es: => String):T = emitBlock(Some(bs), None, Some(es _))(block)
  def emitBlock[T](bs:Option[String], b:Option[Braces], es:Option[()=>String])(block: =>T):T = 
    { emitBSln(bs, b, None); val res = block; emitBEln(None, b, es.map(es => es())); res }

  def emitList[T](s:String)(block: => T) = { emitln(s); blist; val res = block; elist; res}

  def emitTitleComment(title:String) = 
    emitln(s"/*****************************${title}****************************/")
  def flush = pw.flush()
  def close = {
    pw.flush()
    if (stream != System.out)
      pw.close()
  }

  def fileExist = Files.exists(Paths.get(getPath))
  def fileEmpty = {
    stream
    file.length==0
  }

  def newStream(dp:String, fname:String, append:Boolean=false):FileOutputStream = { 
    fileName = fname
    dirPath = dp
    _append = append
    val dir = new File(dirPath)
    if (!dir.exists()) {
      println(s"[pir] creating output directory: $dirPath");
      dir.mkdir();
    }
    file = new File(getPath)
    new FileOutputStream(file, append)
  }
  def newStream(fname:String)(implicit design:Design):FileOutputStream = { newStream(design.outDir, fname) }
  def newStream(fname:String, append:Boolean)(implicit design:Design):FileOutputStream =
    newStream(design.outDir, fname, append)

  /* A temporary stream to write all data */
  val buffers = Stack[ByteArrayOutputStream]()
  val bufferWriters = Stack[PrintWriter]()
  def openBuffer = {
    pw.flush
    buffers.push(new ByteArrayOutputStream())
    bufferWriters.push(new PrintWriter(buffers.top))
  }
  /*
   * Close the temporary stream and write all content in the temp stream to actual file
   * */
  def closeAndWriteBuffer:Unit = {
    if (buffers.isEmpty) return
    val bufStream = buffers.pop
    val nextStream = buffers.headOption.getOrElse(stream)
    nextStream.write(bufStream.toByteArray())
    nextStream.flush
    buffers.push(bufStream) // Let closeBuffer to do the cleanning
    closeBuffer
  }

  /* Close the temporary stream */
  def closeBuffer:Unit = {
    if (buffers.isEmpty) return
    val pw = bufferWriters.pop
    pw.flush()
    pw.close()
    buffers.pop
  }

  def closeAndWriteAllBuffers = {
    while (buffers.nonEmpty) {
      closeAndWriteBuffer
    }
  }
}
