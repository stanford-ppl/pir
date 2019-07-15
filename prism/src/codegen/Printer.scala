package prism
package codegen

import java.nio.file._
import java.io._
import scala.collection.mutable.Stack
import scala.collection.mutable

trait Printer extends FormatPrinter {

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
  case class ByteWriter(name:Option[String]=None) extends StreamWriter {
    override lazy val outputStream:ByteArrayOutputStream = new ByteArrayOutputStream()
    def getPath = "byteStream"
    def flushTo(toStream:StreamWriter) = {
      toStream.flush
      toStream.outputStream.write(outputStream.toByteArray())
      toStream.flush
      outputStream.reset
    }
    def reset = outputStream.reset
  }
  case class FileWriter(filePath:String, append:Boolean) extends StreamWriter {
    override lazy val outputStream:FileOutputStream = {
      mkdir(dirName(filePath))
      val file = new File(filePath)
      new FileOutputStream(file, append)
    }
    var written = false
    override def print(s:String) = { written = true; super.print(s) }
    override def println(s:String) = { written = true; super.println(s) }
    override def flush = if (written) super.flush
    override def close = if (written) super.close
    def getPath = filePath
  }

  val streamStack = Stack[StreamWriter]()

  def sw:StreamWriter = { streamStack.headOption.getOrElse(throw new Exception(s"No Stream defined for $this")) }

  def openBuffer(name:Option[String]=None) = {
    val bw = ByteWriter(name)
    streamStack.headOption.foreach { stream =>
      bw.level = stream.level
      bw.listing = stream.listing
    }
    open(bw)
  }

  def openStdout = open(StdoutWriter())

  def openFile(filePath:String, append:Boolean=false):StreamWriter = {
    //open(FileWriter(filePath, append))
    startStream(filePath, FileWriter(filePath, append))
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

  def withOpen[T](fullPath:String, append:Boolean)(lambda: => T):T = {
    withOpen(dirName(fullPath), baseName(fullPath), append)(lambda)
  }

  def open(stream:StreamWriter):StreamWriter = {
    streamStack.headOption.foreach { _.flush }
    streamStack.push(stream)
    stream
  }

  def isOpen = streamStack.nonEmpty

  def popStream:Option[StreamWriter] = {
    if (!isOpen) return None
    val stream = streamStack.pop
    stream.flush
    Some(stream)
  }

  def closeStream:Option[StreamWriter] = {
    popStream.map { stream =>
      stream.close
      stream
    }
  }

  def closeAll:Unit = {
    while (isOpen) closeStream
    streamMap.values.foreach { _.close }
  }

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
      stream.flushTo(nextStream)
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

  val streamMap = mutable.Map[String, StreamWriter]()
  def startStream[T](name:String, newStream: => StreamWriter):StreamWriter = {
    open(streamMap.getOrElseUpdate(name, newStream))
  }
  def enterStream[T](name:String, newStream: => StreamWriter)(block: => T) = {
    val stream = startStream(name, newStream)
    val res = try {
      block
    } catch {
      case e:Exception =>
        closeStream
        throw e
    }
    popStream
    res
  }
  def enterBuffer[T](name:String, level:Int = 0)(block: => T) = {
    enterStream(name, ByteWriter(Some(name))){
      sw.level = level
      block
    }
  }
  def enterFile[T](dirName:String, fileName:String, append:Boolean=false)(block: => T):T = {
    val path = buildPath(dirName, fileName)
    enterFile(path, append)(block)
  }
  def enterFile[T](path:String, append:Boolean)(block: => T):T = {
    enterStream(path, FileWriter(path, append))(block)
  }

  def getStream(name:String) = streamMap.get(name)
  def getBuffer(name:String) = getStream(name).asInstanceOf[Option[ByteWriter]]

}
