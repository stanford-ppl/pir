package pir.codegen

import pir._

import java.io.PrintWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

trait Printer {
  var fileName:String = "System.out"
  var dirPath:String = Config.outDir 
  val stream:OutputStream = System.out
  def stdOut = stream == System.out

  def getPath = {
    if (stdOut) "console"
    else s"${dirPath}${File.separator}${fileName}"
  }

  lazy val pw = new PrintWriter(stream)
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
  def emitBSln(s:String):Unit = { emit(s); emitBSln }
  def emitBSln:Unit = { pprintln(s"{"); level += 1 }
  def emitBEln(s:String):Unit = { emitBE; pprintln(s) }
  def emitBEln = { emitBE; pprintln }
  def emitBE = { level -= 1; emit(s"}") }
  def emitCSln(s:String):Unit = { emit(s); emitCSln }
  def emitCSln:Unit = { pprintln(s"["); level += 1 }
  def emitCE = { level -= 1; emit(s"]") }
  def emitBlock(block: =>Any) = { emitBSln; block; emitBEln }
  def emitBlock(s:String)(block: =>Any) = { emitBSln(s"$s "); block; emitBEln }
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
      println(s"[info] creating output directory: ${System.getProperty("user.dir")}${File.separator}${Config.outDir}");
      dir.mkdir();
    }
    new FileOutputStream(new File(s"${getPath}"))
  }
  def newStream(fname:String):FileOutputStream = { newStream(Config.outDir, fname) }
}

trait Logger extends Printer {
  override def emitBSln(s:String):Unit = { super.emitBSln(s); flush }
  override def emitBEln(s:String):Unit = { super.emitBEln(s); flush }
  override def emitln(s:String):Unit = { super.emitln(s); flush } 
  def promp(header:Option[String], s:Any) = s"[debug${header.fold("") { h => s"-$h"}}] $s"
  def dprintln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitln(promp(header, s))
  def dprint(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emit(promp(header, s))
  def dprintln(pred:Boolean, header:String, s:Any):Unit = dprintln(pred, Some(header), s) 
  def dprint(pred:Boolean, header:String, s:Any):Unit = dprint(pred, Some(header), s) 
  def dprintln(header:String, s:Any):Unit = dprintln(Config.debug, header, s) 
  def dprint(header:String, s:Any):Unit = dprint(Config.debug, header, s) 
  def dprintln(s:Any):Unit = dprintln(Config.debug, None, s) 
  def dprint(s:Any):Unit = dprintln(Config.debug, None, s)
  def dbsln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitBSln(promp(header,s) + " ")
  def dbeln(pred:Boolean, header:Option[String], s:Any):Unit = if (pred) emitBEln(" " + promp(header, s))

  def info(s:String) = emitln(s"[pir] ${s}")
  def warn(s:String) = emitln(s"${Console.YELLOW}[warning] ${s}${Console.RESET}")
  def err(s:String) = emitln(s"${Console.RED}[error]${s}${Console.RESET}")
}

trait DebugLogger {
  def dprintln(s:Any):Unit = DebugLogger.dprintln(s) 
  def dprint(s:Any):Unit = DebugLogger.dprint(s) 
}
object DebugLogger extends Logger {
  override val stream = newStream(Config.debugLog)
}
