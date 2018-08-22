package prism
package codegen

import java.nio.file._
import java.io._
import scala.collection.mutable.Stack

trait Printer {

  trait StreamWriter {
    def outputStream:OutputStream
    lazy val writer = new PrintWriter(outputStream)
    def print(s:String) = { writer.print(s) }
    def println(s:String) = { writer.println(s) }
    def flush = writer.flush
    def close = writer.close
    def getPath:String
    var level = 0
    var listing = false
  }
  case class StdoutWriter() extends StreamWriter {
    val outputStream = System.out
    override def print(s:String) = { super.print(s); writer.flush }
    override def println(s:String) = { super.println(s); writer.flush }
    override def close = {} // Cannot close stdout
    def getPath = s"console"
  }
  case class ByteWriter() extends StreamWriter {
    override lazy val outputStream:ByteArrayOutputStream = new ByteArrayOutputStream()
    def getPath = "byteStream"
  }
  case class FileWriter(filePath:String, append:Boolean) extends StreamWriter {
    override lazy val outputStream:FileOutputStream = {
      mkdir(dirName(filePath))
      new FileOutputStream(new File(filePath), append)
    }
    var written = false
    override def print(s:String) = { written = true; super.print(s) }
    override def println(s:String) = { written = true; super.println(s) }
    override def flush = if (written) super.flush
    override def close = if (written) super.close 
    def getPath = filePath
  }

  val streamStack = Stack[StreamWriter]()

  def isOpened = streamStack.nonEmpty
  
  def sw:StreamWriter = { streamStack.headOption.getOrElse(throw new Exception(s"No Stream defined for $this")) }

  def openBuffer = {
    val bw = ByteWriter()
    streamStack.headOption.foreach { stream =>
      bw.level = stream.level
      bw.listing = stream.listing
    }
    open(bw)
  }

  def openStdout = open(StdoutWriter())

  def openFile(filePath:String, append:Boolean=false):StreamWriter = {
    open(FileWriter(filePath, append))
  }

  def openFile(dirName:String, fileName:String, append:Boolean):StreamWriter = {
    openFile(buildPath(dirName, fileName), append)
  }

  def withOpen[T](dirName:String, fileName:String, append:Boolean)(lambda: => T):T = {
    openFile(dirName, fileName, append)
    val res = try {
      lambda
    } catch {
      case e:Exception =>
        closeStream
        throw e
    }
    closeStream
    res
  }

  def withOpen[T](fileName:String, append:Boolean=false)(lambda: => T)(implicit compiler:Compiler):T = {
    withOpen(compiler.outDir, fileName, append)(lambda)
  }

  def open(stream:StreamWriter):StreamWriter = {
    streamStack.headOption.foreach { _.flush }
    streamStack.push(stream)
    stream
  }

  def isOpen = streamStack.nonEmpty

  def closeStream:Unit = {
    if (!isOpen) return
    val stream = streamStack.pop
    stream.flush
    stream.close
  }

  def closeAll:Unit = while (isOpen) closeStream

  def inBuffer = {
    streamStack.headOption.fold(false) {
      case _:ByteWriter => true
      case _ => false
    }
  }

  def closeBufferAndWrite:Unit = {
    if (!inBuffer) return
    val stream = streamStack.pop.asInstanceOf[ByteWriter]
    streamStack.headOption.foreach { nextStream =>
      nextStream.outputStream.write(stream.outputStream.toByteArray())
      nextStream.flush
    }
    stream.flush
    stream.close
  }

  def closeAllBuffersAndWrite:Unit = {
    while (inBuffer) closeBufferAndWrite
  }

  def closeBuffer:Unit = {
    if (!inBuffer) return
    closeStream
  }

  val tab = "  "
  def incLevel = sw.level += 1
  def decLevel = { sw.level -= 1; assert(sw.level >= 0) }
  def blist = { sw.listing = true; incLevel }
  def elist = { sw.listing = false; decLevel }

  trait Braces { def s:String; def e:String }
  case object Brackets extends Braces { def s = "["; def e = "]" }
  case object CurlyBraces extends Braces { def s = "{"; def e = "}" }
  case object Parentheses extends Braces { def s = "("; def e = ")" }
  case object NoneBraces extends Braces { def s = ""; def e = "" }

  def indent(s:String) = if (s=="") "" else s"${tab*sw.level}$s"
  def listFormat(s:String) = if (sw.listing) s"- $s" else s

  def write(s:String):Unit = sw.print(s)
  def writeln(s:String):Unit = sw.println(s)

  def emit:Unit = write("") 
  def emit(s:String):Unit = write(indent(listFormat(s)))
  def emitln(s:String):Unit = writeln(indent(listFormat(s)))
  def emitln:Unit = writeln("")

  def emit(s:Any):Unit = emit(s.toString) 

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

