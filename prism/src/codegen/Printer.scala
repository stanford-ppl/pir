package prism
package codegen

import java.nio.file._
import java.io._
import scala.collection.mutable.Stack
import scala.collection.mutable

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

trait Printer extends FormatPrinter {

  val streamStack = Stack[StreamWriter]()

  def isOpened = streamStack.nonEmpty
  
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
  def enterStream[T](name:String, newStream: => StreamWriter)(block: => T) = {
    val stream = open(streamMap.getOrElseUpdate(name, newStream))
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

  def enterBuffer[T](name:String)(block: => T) = {
    enterStream(name, ByteWriter(Some(name)))(block)
  }

  def getBuffer(name:String) = streamMap.get(name).asInstanceOf[Option[ByteWriter]]

}
