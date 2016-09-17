package pir.codegen

import pir._

import java.io.PrintWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

trait Printer {
  var fileName:String = "System.out"
  val stream:OutputStream = System.out
  def stdOut = stream == System.out

  def getPath = {
    if (stdOut) "console"
    else s"${Config.outDir}${File.separator}${fileName}"
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

  def newStream(fname:String) = { 
    fileName = fname
    val dir = new File(Config.outDir);
    if (!dir.exists()) {
      println(s"[info] creating output directory: ${System.getProperty("user.dir")}${File.separator}${Config.outDir}");
      dir.mkdir();
    }
    new FileOutputStream(new File(s"${getPath}"))
  }
}
