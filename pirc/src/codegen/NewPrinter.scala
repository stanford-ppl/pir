package prism.codegen

import pirc._

import java.nio.file._
import java.io._
import scala.collection.mutable.Stack

trait Printer {

  trait StreamWriter {
    def outputStream:OutputStream
    lazy val writer = new PrintWriter(outputStream)
    def emit(s:String) = { writer.print(s) }
    def emitln(s:String) = { writer.println(s) }
    def flush = writer.flush
    def close = writer.close
  }
  case class StdoutWriter() extends StreamWriter {
    val outputStream = System.out
    override def emit(s:String) = { super.emit(s); writer.flush }
    override def emitln(s:String) = { super.emitln(s); writer.flush }
    override def close = {} // Cannot close stdout
  }
  case class ByteWriter() extends StreamWriter {
    override lazy val outputStream:ByteArrayOutputStream = new ByteArrayOutputStream()
  }
  case class FileWriter(dirName:String, fileName:String, append:Boolean) extends StreamWriter {
    val path = s"${dirName}${File.separator}${fileName}"
    override lazy val outputStream:FileOutputStream = {
      val dir = new File(dirName)
      if (!dir.exists()) {
        println(s"[pir] creating output directory: $dirName");
        dir.mkdir();
      }
      val file = new File(path)
      new FileOutputStream(file, append)
    }
    var written = false
    override def emit(s:String) = { written = true; super.emitln(s) }
    override def emitln(s:String) = { written = true; super.emitln(s) }
    override def flush = if (written) super.flush
    override def close = if (written) super.close 
  }

  val streamStack = Stack[StreamWriter]()

  def sw:StreamWriter = { streamStack.headOption.getOrElse(throw new Exception(s"No Stream defined for $this")) }

  def openBuffer = open(ByteWriter())

  def openStdout = open(StdoutWriter())

  def openFile(dirName:String, fileName:String, append:Boolean):StreamWriter = {
    open(FileWriter(dirName, fileName, append))
  }

  def openFile(fileName:String, append:Boolean=false)(implicit design:Design):StreamWriter = {
    openFile(design.outDir, fileName, append)
  }

  def withOpen(fileName:String, append:Boolean=false)(lambda: => Unit)(implicit design:Design) = {
    openFile(fileName, append)
    try {
      lambda
    } catch {
      case e:Exception =>
        closeStream
        throw e
    }
    closeStream
  }

  def open(stream:StreamWriter):StreamWriter = {
    streamStack.headOption.foreach { _.flush }
    streamStack.push(stream)
    stream
  }

  def closeAndWriteNext:Unit = {
    if (streamStack.isEmpty) return
    val stream = streamStack.pop
    stream match {
      case stream:ByteWriter =>
        streamStack.headOption.foreach { nextStream =>
          nextStream.outputStream.write(stream.outputStream.toByteArray())
          nextStream.flush
        }
      case stream =>
    }
    stream.flush
    stream.close
  }

  def closeAndWriteNextAll:Unit = while (streamStack.nonEmpty) closeAndWriteNext

  def closeStream:Unit = {
    if (streamStack.isEmpty) return
    val stream = streamStack.pop
    stream.flush
    stream.close
  }

  def closeAll:Unit = while (streamStack.nonEmpty) closeStream

  val tab = "  "
  var level = 0
  var listing = false
  def incLevel = level += 1
  def decLevel = { level -= 1; assert(level >= 0) }
  def blist = { listing = true; incLevel }
  def elist = { listing = false; decLevel }

  trait Braces { def s:String; def e:String }
  case object Brackets extends Braces { def s = "["; def e = "]" }
  case object CurlyBraces extends Braces { def s = "{"; def e = "}" }
  case object Parentheses extends Braces { def s = "("; def e = ")" }

  def indent(s:String) = if (s=="") "" else s"${tab*level}$s"
  def listFormat(s:String) = if (listing) s"- $s" else s
  def emit(s:String):Unit = sw.emit(indent(listFormat(s)))
  def emitln(s:String):Unit = sw.emitln(indent(listFormat(s)))

  def emit(s:Any):Unit = emit(s.toString) 
  def emit:Unit = emit("") 
  def emitln:Unit = sw.emitln("")

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
  def flush = sw.flush
  def close = sw.close

}

