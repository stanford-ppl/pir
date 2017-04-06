package pir.codegen

import pir._
import pir.exceptions._

import java.io.PrintWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.ByteArrayOutputStream
import scala.collection.mutable.Stack

trait Printer {
  var fileName:String = "System.out"
  var dirPath:String = Config.outDir 
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

  def pprint(s:String):Unit = { pw.print(s); if (stdOut) flush }
  def pprintln(s:String):Unit = { pw.println(s); if (stdOut) flush }
  def pprintln:Unit = { pw.println; if (stdOut) flush }

  def emit(s:String):Unit = pprint(s"${tab*level}${s}")
  def emit(s:Any):Unit = emit(s.toString) 
  def emit:Unit = emit("") 
  def emitln(s:String):Unit = pprintln(s"${tab*level}${s}")
  def emitln:Unit = pprintln
  def emitln(i:Int):Unit = (0 until i).foreach { i => pprintln }
  def emitBSln(s:String):Unit = { emit(s); emitBSln }
  def emitBSln:Unit = { pprintln(s"{"); level += 1 }
  def emitBS(s:String):Unit = { emit(s); emitBS }
  def emitBS:Unit = { pprint(s"{"); level += 1 }
  def emitBEln(s:String):Unit = { emitBE; pprintln(s) }
  def emitBEln = { emitBE; pprintln }
  def emitBE = { level -= 1; emit(s"}") }
  def emitCSln(s:String):Unit = { emit(s); emitCSln }
  def emitCSln:Unit = { pprintln(s"["); level += 1 }
  def emitCE = { level -= 1; emit(s"]") }
  def emitBlock[T](block: =>T):T = { emitBSln; val res = block; emitBEln; res }
  def emitBlock[T](s:String)(block: =>T):T = { emitBSln(s"$s "); val res = block; emitBEln; res }
  def emitTitleComment(title:String) = 
    emitln(s"/*****************************${title}****************************/")
  def flush = pw.flush()
  def close = {
    pw.flush()
    if (stream != System.out)
      pw.close()
  }

  def newStream(dp:String, fname:String):FileOutputStream = { 
    fileName = fname
    dirPath = dp
    val dir = new File(dirPath)
    if (!dir.exists()) {
      println(s"[pir] creating output directory: ${System.getProperty("user.dir")}${File.separator}${Config.outDir}");
      dir.mkdir();
    }
    new FileOutputStream(new File(s"${getPath}"))
  }
  def newStream(fname:String):FileOutputStream = { newStream(Config.outDir, fname) }

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
  def closeAndWriteBuffer = {
    val bufStream = buffers.pop
    val nextStream = buffers.headOption.getOrElse(stream)
    nextStream.write(bufStream.toByteArray())
    nextStream.flush
    buffers.push(bufStream)
    closeBuffer
  }

  /* Close the temporary stream */
  def closeBuffer = {
    val pw = bufferWriters.pop
    pw.flush()
    pw.close()
    buffers.pop
  }
}

trait Logger extends Printer {
  override def emitBSln(s:String):Unit = { super.emitBSln(s); flush }
  override def emitBEln(s:String):Unit = { super.emitBEln(s); flush }
  override def emitln(s:String):Unit = { super.emitln(s); flush } 
  override def emitBlock[T](block: =>T):T = { if (Config.debug) { val res = super.emitBlock(block); flush; res } else { block } }
  override def emitBlock[T](s:String)(block: =>T):T = { if (Config.debug) { val res = super.emitBlock(s)(block); flush; res } else { block } }
  def emitBlock[T](header:String, s:String)(block: =>T):T = { if (Config.debug) { val res = super.emitBlock(promp(Some(header), s))(block); flush; res } else { block } }
  def promp(header:Option[String], s:Any) = s"[debug${header.fold("") { h => s"-$h"}}] $s"
  def dprintln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitln(promp(header, s))
  def dprint(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emit(promp(header, s))
  def dprintln(pred:Boolean, header:String, s:Any):Unit = dprintln(pred, Some(header), s) 
  def dprint(pred:Boolean, header:String, s:Any):Unit = dprint(pred, Some(header), s) 
  def dprintln(header:String, s:Any):Unit = dprintln(Config.debug, header, s) 
  def dprint(header:String, s:Any):Unit = dprint(Config.debug, header, s) 
  def dprintln(pred:Boolean, s:Any):Unit = dprintln(pred, None, s) 
  def dprint(pred:Boolean, s:Any):Unit = dprintln(pred, None, s)
  def dprintln(s:Any):Unit = dprintln(Config.debug, None, s) 
  def dprint(s:Any):Unit = dprintln(Config.debug, None, s)
  def dbsln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitBSln(promp(header,s) + " ")
  def dbeln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitBEln(" " + promp(header, s))

  def info(s:String) = emitln(s"[pir] ${s}")
  def startInfo(s:String) = emit(s"[pir] ${s}")
  def endInfo(s:String) = { emitln(s" ${s}") }
  def warn(s:Any) = emitln(s"${Console.YELLOW}[warning] ${s}${Console.RESET}")
  def err(s:Any) = { emitln(s"${Console.RED}[error]${s}${Console.RESET}"); throw PIRException(s"$s") }
  def bp(s:Any) = emitln(s"${Console.RED}[break]${s}${Console.RESET}")
}

trait DebugLogger {
  def dprintln(s:Any):Unit = DebugLogger.dprintln(s) 
  def dprint(s:Any):Unit = DebugLogger.dprint(s) 
}
object DebugLogger extends Logger {
  override lazy val stream = newStream(Config.debugLog)
}
