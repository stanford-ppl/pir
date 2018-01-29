package prism.codegen

import pirc._

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
  }
  case class StdoutWriter() extends StreamWriter {
    val outputStream = System.out
    override def print(s:String) = { super.print(s); writer.flush }
    override def println(s:String) = { super.println(s); writer.flush }
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
    override def print(s:String) = { written = true; super.println(s) }
    override def println(s:String) = { written = true; super.println(s) }
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
    lambda
    closeStream
  }

  def withOpen(dirName:String, fileName:String, append:Boolean)(lambda: => Unit) = {
    openFile(dirName, fileName, append)
    lambda
    close
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
  def print(s:String):Unit = sw.print(indent(listFormat(s)))
  def println(s:String):Unit = sw.println(indent(listFormat(s)))

  def print(s:Any):Unit = print(s.toString) 
  def print:Unit = print("") 
  def println:Unit = sw.println("")

  def printBSln:Unit = printBSln(None, None, None)
  def printBSln(b:Braces):Unit = printBSln(None, Some(b), None)
  def printBSln(bs:String):Unit = printBSln(Some(bs),None, None)
  def printBSln(bs:String, b:Braces):Unit = printBSln(Some(bs), Some(b), None)
  def printBSln(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = { 
    println(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).s}${es.fold(""){ es => s" $es"}}"); incLevel
  }

  def printBS:Unit = printBS(None, None, None)
  def printBS(b:Braces):Unit = printBS(None, Some(b), None)
  def printBS(bs:String):Unit = printBS(Some(bs),None, None)
  def printBS(bs:String, b:Braces):Unit = printBS(Some(bs), Some(b), None)
  def printBS(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = { 
    print(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).s}${es.fold(""){ es => s" $es"}}"); incLevel
  }

  def printBEln(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = {
    decLevel; println(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).e}${es.fold(""){ es => s" $es"}}")
  }
  def printBEln(bs:String, b:Braces):Unit = printBEln(Some(bs), Some(b), None) 
  def printBEln(es:String):Unit = printBEln(None, None, Some(es))
  def printBEln(b:Braces):Unit = printBEln(None, Some(b), None)
  def printBEln:Unit = printBEln(None, None, None)

  def printBE(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = {
    decLevel; print(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).e}${es.fold(""){ es => s" $es"}}")
  }
  def printBE(bs:String, b:Braces):Unit = printBE(Some(bs), Some(b), None) 
  def printBE(es:String):Unit = printBE(None, None, Some(es))
  def printBE(b:Braces):Unit = printBE(None, Some(b), None)
  def printBE:Unit = printBE(None, None, None)

  def printBlock[T](block: =>T):T = printBlock(None, None, None)(block)
  def printBlock[T](b:Braces)(block: =>T):T = printBlock(None, Some(b), None)(block)
  def printBlock[T](bs:String)(block: =>T):T = printBlock(Some(bs), None, None)(block)
  def printBlock[T](bs:String, b:Braces)(block: =>T):T = printBlock(Some(bs), Some(b), None)(block)
  def printBlock[T](bs:String, block: =>T, es: => String):T = printBlock(Some(bs), None, Some(es _))(block)
  def printBlock[T](bs:Option[String], b:Option[Braces], es:Option[()=>String])(block: =>T):T = 
    { printBSln(bs, b, None); val res = block; printBEln(None, b, es.map(es => es())); res }

  def printList[T](s:String)(block: => T) = { println(s); blist; val res = block; elist; res}

  def printTitleComment(title:String) = 
    println(s"/*****************************${title}****************************/")
  def flush = sw.flush
  def close = sw.close

}

